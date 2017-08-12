/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.parent.dao.mybatis;

import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import cc.msonion.carambola.parent.pojo.zk.retrypolicy.MsOnionRetryNTimes;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.*;

/**
 * SQL分析拦截器，日志级别为：WARN
 *
 * @Title: MsOnionSqlAnalyzeInterceptor.java
 * @Package: cc.msonion.carambola.parent.dao.mybatis
 * @Description: SQL分析拦截器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月23日 下午11:16:09
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月23日 下午11:16:09
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * SQL分析拦截器，日志级别为：WARN
 *
 * @ClassName: MsOnionSqlAnalyzeInterceptor
 * @Description: SQL分析拦截器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月23日 下午11:16:09
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class MsOnionSqlAnalyzeInterceptor implements Interceptor {

    /**
     * 日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * 域名封装，封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
     */
    @Autowired
    private MsOnionDomain msOnionDomain;

    /**
     * 重试策略
     */
    @Autowired
    private MsOnionRetryNTimes msOnionRetryNTimes;

    /**
     * ZK客户端
     */
    @Autowired
    private MsOnionCuratorZookeeperClient zookeeperClient;

    /**
     * 日期类型，field.getType()=class java.util.Date
     */
    private static final String TYPE_DATE = "java.util.Date";

    /**
     * SQL执行耗时时间，500毫秒之内，不会打印日志
     */
    private static final long SQL_COST_TIME_LEVEL = 5; // 上线是：200

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();

        // 当前时间
        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) target;
        try {
            return invocation.proceed();
        } finally {
            // SQL执行完成的时间
            long endTime = System.currentTimeMillis();
            // 执行SQL消耗的时间
            long sqlCostTime = endTime - startTime;

            String sql = null;
            // 打印日志 , Warn
            // msOnionLog 还为 null ，Spring 还没有初始化 beans ，服务还无法调用！！！
//            msOnionLog.warn(getClass().getName(), String.format("SQL：[%s]，执行耗时：[%s ms]", sql, sqlCostTime));
            // 100毫秒之内的不会打印日志
            // 无法使用  MsOnionZookeeperUtils.getSqlCostTimeLevel() ，因为Spring 还没有加载到 ZK客户端和相关Bean
            if (null != msOnionDomain && null != msOnionLogger && null != msOnionRetryNTimes && null != zookeeperClient) {
                // 如果SQL执行的耗时时间，超过ZK中配置的时间值（毫秒单位），就打印日志
                if (sqlCostTime >= MsOnionZookeeperUtils.getSqlCostTimeLevel(msOnionDomain, zookeeperClient, msOnionRetryNTimes)) {
                    // 格式化Sql语句
                    sql = getFormatSql(statementHandler);
                    msOnionLogger.warn(getClass().getName(), String.format("SQL：[%s]，执行耗时：[%s ms]", sql, sqlCostTime));
                }
            } else if (sqlCostTime > SQL_COST_TIME_LEVEL) {

//                BoundSql boundSql = statementHandler.getBoundSql();
//                String sql = boundSql.getSql();
//                Object parameterObject = boundSql.getParameterObject();
//                List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
//
//                // 格式化Sql语句，去除换行符，替换参数
//                sql = formatSql(sql, parameterObject, parameterMappingList);

                // 格式化Sql语句
                sql = getFormatSql(statementHandler);
                if (null == msOnionLogger) {
                    MsOnionLogger.doWarn(getClass().getName(), String.format("SQL：[%s]，执行耗时：[%s ms]", sql, sqlCostTime));
                } else {
                    msOnionLogger.warn(getClass().getName(), String.format("SQL：[%s]，执行耗时：[%s ms]", sql, sqlCostTime));
                }
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 获取格式化的SQL语句
     * @param statementHandler StatementHandler实例对象
     * @return 返回格式化的SQL语句
     */
    private String getFormatSql(StatementHandler statementHandler) {
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

        // 格式化Sql语句，去除换行符，替换参数
        return formatSql(sql, parameterObject, parameterMappingList);
    }

    /**
     * 格式化SQL语句
     *
     * @param sql                  执行的SQL语句
     * @param parameterObject      参数
     * @param parameterMappingList 参数集合
     * @return 返回格式化SQL语句
     */
    private String formatSql(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
        // 输入sql字符串空判断
        if (MsOnionStringUtils.isBlank(sql)) {
            return "";
        }

        // 美化sql
        sql = beautifySql(sql);

        // 不传参数的场景，直接把Sql美化一下返回出去
        if (null == parameterObject || MsOnionCollectionUtils.isEmpty(parameterMappingList)) {
            return sql;
        }

        // 定义一个没有替换过占位符的sql，用于出异常时返回
        String sqlWithoutReplacePlaceholder = sql;

        try {
            if (null != parameterMappingList) {
                Class<?> parameterObjectClass = parameterObject.getClass();

                // 如果参数是StrictMap且Value类型为Collection，获取key="list"的属性，这里主要是为了处理<foreach>循环时传入List这种参数的占位符替换
                // 例如select * from xxx where id in <foreach collection="list">...</foreach>
                if (isStrictMap(parameterObjectClass)) {
                    DefaultSqlSession.StrictMap<Collection<?>> strictMap = (DefaultSqlSession.StrictMap<Collection<?>>) parameterObject;

                    if (isList(strictMap.get("list").getClass())) {
                        sql = handleListParameter(sql, strictMap.get("list"));
                    }
                } else if (isMap(parameterObjectClass)) {
                    // 如果参数是Map则直接强转，通过map.get(key)方法获取真正的属性值
                    // 这里主要是为了处理<insert>、<delete>、<update>、<select>时传入parameterType为map的场景
                    Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
                    sql = handleMapParameter(sql, paramMap, parameterMappingList);
                } else {
                    // 通用场景，比如传的是一个自定义的对象或者八种基本数据类型之一或者String
                    sql = handleCommonParameter(sql, parameterMappingList, parameterObjectClass, parameterObject);
                }
            }
        } catch (Exception e) {
            // 占位符替换过程中出现异常，则返回没有替换过占位符但是格式美化过的sql，这样至少保证sql语句比BoundSql中的sql更好看
            return sqlWithoutReplacePlaceholder;
        }
        return sql;
    }

    /**
     * 美化SQL语句
     *
     * @param sql 执行的SQL语句
     * @return 返回美化之后的SQL语句
     */
    private String beautifySql(String sql) {
        sql = sql.replace("\n", "").replace("\t", "")
                .replace("      ", " ").replace("( ", "(")
                .replace(" )", ")").replace(" ,", ",")
                .replace("  ", " ");

        return sql;
    }

    /**
     * 处理参数为List的场景
     *
     * @param sql        执行的SQL语句
     * @param collection 集合
     * @return 返回处理结果
     */
    private String handleListParameter(String sql, Collection<?> collection) {
        if (null != collection && collection.size() != 0) {
            for (Object obj : collection) {
                String value = null;
                Class<?> objClass = obj.getClass();

                // 只处理基本数据类型、基本数据类型的包装类、String这三种
                // 如果是复合类型也是可以的，不过复杂点且这种场景较少，写代码的时候要判断一下要拿到的是复合类型中的哪个属性
                if (isPrimitiveOrPrimitiveWrapper(objClass)) {
                    value = obj.toString();
                } else if (objClass.isAssignableFrom(String.class)) {
                    value = "\"" + obj.toString() + "\"";
                }

                sql = sql.replaceFirst("\\?", value);
            }
        }
        return sql;
    }

    /**
     * 处理参数为Map的场景
     *
     * @param sql                  执行SQL语句
     * @param paramMap             参数
     * @param parameterMappingList 参数集合
     * @return 返回处理结果
     */
    private String handleMapParameter(String sql, Map<?, ?> paramMap, List<ParameterMapping> parameterMappingList) {
        for (ParameterMapping parameterMapping : parameterMappingList) {
            Object propertyName = parameterMapping.getProperty();
            Object propertyValue = paramMap.get(propertyName);
            if (propertyValue != null) {
                if (propertyValue.getClass().isAssignableFrom(String.class)) {
                    propertyValue = "\"" + propertyValue + "\"";
                }
                sql = sql.replaceFirst("\\?", propertyValue.toString());
            }
        }
        return sql;
    }

    /**
     * 处理通用的场景
     *
     * @param sql                  执行SQL语句
     * @param parameterMappingList ParameterMapping集合
     * @param parameterObjectClass parameterObjectClass
     * @param parameterObject      parameterObject
     * @return 返回处理结果
     * @throws Exception 异常
     */
    private String handleCommonParameter(String sql, List<ParameterMapping> parameterMappingList, Class<?> parameterObjectClass,
                                         Object parameterObject) throws Exception {
        for (ParameterMapping parameterMapping : parameterMappingList) {
            String propertyValue = null;
            // 基本数据类型或者基本数据类型的包装类，直接toString即可获取其真正的参数值，其余直接取paramterMapping中的property属性即可
            if (isPrimitiveOrPrimitiveWrapper(parameterObjectClass)) {
                propertyValue = parameterObject.toString();
            } else {
                String propertyName = parameterMapping.getProperty();

                Field field = parameterObjectClass.getDeclaredField(propertyName);
                // 要获取Field中的属性值，这里必须将私有属性的accessible设置为true
                field.setAccessible(true);

                // 打印日志
//                MsOnionLogger.doWarn(getClass().getName(),
// String.format("===handleCommonParameter=处理通用的场景= # field.getName()=%s，field.getType()=%s",
//                        field.getName(), field.getType()));
                if (field.getType().toString().contains(TYPE_DATE)) {
                    propertyValue = MsOnionDateUtils.formatYyyyMMddHHmmss((Date) field.get(parameterObject));
                } else {
                    propertyValue = String.valueOf(field.get(parameterObject));
                    // 处理敏感信息，手机号码、Email、密码、code，使用* 替换掉部分
                    // 例如： 138******** ，只显示前面3位，其他都使用* 替换
                     propertyValue = MsOnionStringUtils.filterSensitiveField(propertyName, propertyValue);
                }
                if (parameterMapping.getJavaType().isAssignableFrom(String.class)) {
                    propertyValue = "\"" + propertyValue + "\"";
                }
            }
            sql = sql.replaceFirst("\\?", propertyValue);
        }
        return sql;
    }

    /**
     * 是否基本数据类型或者基本数据类型的包装类
     *
     * @param parameterObjectClass parameterObjectClass
     * @return 是否基本数据类型或者基本数据类型的包装类
     */
    private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
        return parameterObjectClass.isPrimitive()
                || (parameterObjectClass.isAssignableFrom(Byte.class) || parameterObjectClass.isAssignableFrom(Short.class)
                || parameterObjectClass.isAssignableFrom(Integer.class) || parameterObjectClass.isAssignableFrom(Long.class)
                || parameterObjectClass.isAssignableFrom(Double.class) || parameterObjectClass.isAssignableFrom(Float.class)
                || parameterObjectClass.isAssignableFrom(Character.class) || parameterObjectClass.isAssignableFrom(Boolean.class));
    }

    /**
     * 是否DefaultSqlSession的内部类StrictMap
     *
     * @param parameterObjectClass parameterObjectClass
     * @return 是否DefaultSqlSession的内部类StrictMap
     */
    private boolean isStrictMap(Class<?> parameterObjectClass) {
        return parameterObjectClass.isAssignableFrom(DefaultSqlSession.StrictMap.class);
    }

    /**
     * 是否List的实现类
     *
     * @param clazz Class
     * @return 是否List的实现类
     */
    private boolean isList(Class<?> clazz) {
        Class<?>[] interfaceClasses = clazz.getInterfaces();
        for (Class<?> interfaceClass : interfaceClasses) {
            if (interfaceClass.isAssignableFrom(List.class)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否Map的实现类
     *
     * @param parameterObjectClass parameterObjectClass
     * @return 是否Map的实现类
     */
    private boolean isMap(Class<?> parameterObjectClass) {
        Class<?>[] interfaceClasses = parameterObjectClass.getInterfaces();
        for (Class<?> interfaceClass : interfaceClasses) {
            if (interfaceClass.isAssignableFrom(Map.class)) {
                return true;
            }
        }
        return false;
    }


}
