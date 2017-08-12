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

package cc.msonion.carambola.parent.interfaces.service;

/**
 * 分布式底层架构公共Service
 *
 * @Title: MsOnionService.java
 * @Package: cc.msonion.carambola.parent.interfaces.service
 * @Description: 对Service封装
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午5:11:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月17日 下午5:11:38
 * @Modify-version: V2.0.0
 * @Modify-description: 修改：日志，使用MsOnionLog，throws MsOnionException
 */

import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.interfaces.listener.MsOnionSyncRedisCacheListener;
import cc.msonion.carambola.parent.interfaces.log.adapter.MsOnionLoggerAdapter;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.interfaces.redis.clients.jedis.MsOnionJedisAdapter;
import cc.msonion.carambola.parent.interfaces.redis.key.MsOnionRedisKeyGeneratorAdapter;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.interfaces.setting.adapter.MsOnionDynamicSettingAdapter;
import cc.msonion.carambola.parent.pojo.*;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.curator.RetryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 分布式底层架构公共Service
 *
 * @param <T> 目标POJO
 * @param <E> 目标POJO的Example
 * @ClassName: MsOnionService
 * @Description: MsOnionService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午5:11:38
 */
@Deprecated // 打上废弃的注解，是避免大家直接调用，不可以直接调用 MsOnionService，都统一换成使用 MsOnionServiceExt
public abstract class MsOnionService<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> extends MsOnionRedisService<T, E> implements MsOnionBaseService<T, E> {

//    /**
//     * 默认返回值
//     */
//    private static final long DEFAULT_RETUAN_LONG_VALUE = -1L;

    /**
     * 默认更新（软删除）的结果
     */
    private static final int DEFAULT_UPDATE_FOR_DELETE_RESULT = 1;

    /**
     * 默认根据主键idx查询的结果（数据库行数）
     */
    private static final int DEFAULT_QUERY_BY_IDX_RESULT = 1;

    /**
     * MsOnionService 当前实现类的实例对象，方便于多线程中获取当前this
     */
    private MsOnionService msOnionService = this;

    /**
     * 目标POJO的Class实例对象
     */
    private Class<T> targetClazz;

    /**
     * 目标POJO的EXample的Class实例对象
     */
    private Class<E> exampleClazz;

    /**
     * MsOnionBaseMapper
     */
    @Autowired
    private MsOnionBaseMapper<T, E> baseMapper;  // 不可以把 private 修改成 protected，不可以直接调用

    /**
     * Redis的Jedis适配器
     */
    @Autowired
    private MsOnionJedisAdapter msOnionJedisAdapter;  // 把 private 修改成 protected

    /**
     * Redis的Key生成器适配器
     */
    @Autowired
    private MsOnionRedisKeyGeneratorAdapter<T, E> redisKeyGeneratorAdapter;  // 把 private 修改成 protected

    /**
     * 动态配置适配器
     */
    @Autowired
    private MsOnionDynamicSettingAdapter msOnionDynamicSettingAdapter;

    /**
     * Zookeeper客户端
     */
    @Autowired
    private MsOnionCuratorZookeeperClient msOnionCuratorZookeeperClient;

    /**
     * Zookeeper服务连接重试策略
     */
    @Autowired
    private RetryPolicy retryPolicy;

    /**
     * 开发、测试、稳定、预发布、生产（线上）环境的域名
     */
    @Autowired
    private MsOnionDomain msOnionDomain;

    /**
     * @Fields is_disabled_redis_query_cache : 是否禁用Redis的查询缓存，
     * false：不禁用（可用），true：禁用，也就是不会使用Redis缓存
     */
    @Value("${msonion_global_is_disabled_redis_query_cache}")
    private boolean isDisabledRedisQueryCache;

    /**
     * @Fields redis_cache_expire : Redis全局缓存周期
     */
    @Value("${msonion_global_redis_cache_expire}")
    private int redisCacheExpire;

    /**
     * SQL语句中status列可用值，是使用于 IN 子查询的，例如：IN (1,2) , IN(1) , 0: 表示删除， 1：正常，激活， 2：未激活
     */
    @Value("${msonion_sql_column_status_available_values}")
    private String sqlColumnStatusAvailableValues;

    /**
     * SQL语句中status列可用值，是使用于 IN 子查询的，例如：IN (1,2) , IN(1) , 0: 表示删除， 1：正常，激活， 2：未激活
     */
    private List<Short> statusAvailableValues;

    /**
     * SQL语句中，密码列名称，是用于分页查询的时候，把密码设置为null，安全考虑
     */
    @Value("${msonion_sql_column_password_names}")
    private String sqlColumnPasswordNames;

    /**
     * SQL语句中，密码列名称，是用于分页查询的时候，把查询的列表中密码设置为null，安全考虑
     */
    private List<String> passwordColumnNames;

    /**
     * POJO在分页查询的时候，查询的列表中密码需要设置为null的POJO，例如：User，Member
     */
    @Value("${msonion_pojo_need_to_set_null_for_password_names}")
    private String pojoNeedToSetNullForPasswordNames;

    /**
     * POJO在分页查询的时候，查询的列表中密码需要设置为null的POJO，例如：User，Member
     */
    private List<String> pojoSecurityForPasswordNames;

    /**
     * MsOnion日志
     */
    @Autowired
    private MsOnionLoggerAdapter msOnionLogger;

    /**
     * 状态列，表示删除状态的值，例如：0
     */
    @Value("${msonion_sql_column_delete_status_value}")
    private short sqlColumnDeleteStatusValue;

    /**
     * 数据库表状态列名称：status
     */
    private static final String TABLE_STATUS_COLUMN_NAME = "status";

    /**
     * 默认更新成功值: 1
     */
    private static final int DEFAULT_UPDATE_SUCCESS_RESULT = 1;

    /**
     * 默认更新失败值: 0
     */
    private static final int DEFAULT_UPDATE_FAIL_RESULT = 0;

    /**
     * 需要去更新值: -2 (还没有更新）
     */
    private static final int DEFAULT_UPDATE_NEED_TO_RESULT = -1;

    /**
     * @Fields serialVersionUID : 自动生成serialVersionUID
     */
    private static final long serialVersionUID = -7605018751910004790L;

    // // JSON数组是以 "[" 开头，以 "]" 结束
    // JSON对象是以 "{" 开头，以 "}" 结束

    /**
     * JSON字符串数组开始标记：JSON数组是以 "[" 开头
     */
    private static final String JSON_ARRAY_START_FLAG = "[";

    /**
     * JSON字符串数组结束标记：JSON数组是以 "]" 结束
     */
    private static final String JSON_ARRAY_END_FLAG = "]";

    /**
     * JSON字符串对象开始标记：JSON对象是以 "{" 开头
     */
    private static final String JSON_OBJECT_START_FLAG = "{";

    /**
     * JSON字符串对象结束标记：JSON对象是以 以 "}" 结束
     */
    private static final String JSON_OBJECT_END_FLAG = "}";

//    /**
//     * 获取目标POJO的Class
//     *
//     * @return 返回目标POJO的Class
//     * @throws MsOnionException 异常
//     * @Title: getTargetClazz
//     * @Description: 获取目标POJO的Class
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午2:31:10
//     */
//    @SuppressWarnings("unchecked")
//    public Class<T> getTargetClazz() throws MsOnionException {
//        if (null != this.targetClazz) {
//            return this.targetClazz;
//        }
//        return this.targetClazz = (Class<T>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass());
//    }
//
//    /**
//     * 获取目标POJO的Example的Class
//     *
//     * @return 返回目标POJO的Example的Class
//     * @throws MsOnionException 异常
//     * @Title: getExampleClazz
//     * @Description: 获取目标POJO的Example的Class
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午2:33:02
//     */
//    @SuppressWarnings("unchecked")
//    public Class<E> getExampleClazz() throws MsOnionException {
//        if (null != this.exampleClazz) {
//            return this.exampleClazz;
//        }
//        return this.exampleClazz = (Class<E>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), 1);
//    }
//
//    /**
//     * 获取KEYS的名称，例如：KEYS
//     *
//     * @return 返回KEYS的名称，例如：KEYS
//     * @throws MsOnionException 异常
//     * @Title: getKeysName
//     * @Description: 获取KEYS的名称，例如：KEYS
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:56:24
//     */
//    public String getKeysName() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getKeysName();
//    }
//
//    /**
//     * 返回KEYS的标识，例如：:KEYS:
//     *
//     * @return 返回KEYS的标识，例如：:KEYS:
//     * @throws MsOnionException 异常
//     * @Title: getKeysIdentification
//     * @Description: 获取KEYS的标识，例如：:KEYS:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:56:41
//     */
//    public String getKeysIdentification() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getKeysIdentification();
//    }
//
//    /**
//     * 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
//     *
//     * @return 目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
//     * @throws MsOnionException 异常
//     * @Title: getTargetName
//     * @Description: 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:42:52
//     */
//    public String getTargetName() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getTargetName();
//    }
//
//    /**
//     * 取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
//     *
//     * @return 目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
//     * @throws MsOnionException 异常
//     * @Title: getTargetIdentification
//     * @Description: 获取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:43:41
//     */
//    public String getTargetIdentification() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getTargetIdentification();
//    }
//
//    /**
//     * 获取前缀，例如：_INFO
//     *
//     * @return 返回前缀，例如：_INFO
//     * @throws MsOnionException 异常
//     * @Title: getPrefix
//     * @Description: 获取前缀，例如：_INFO
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月14日 下午8:33:02
//     */
//    public String getPrefix() throws MsOnionException {
//        return this.redisKeyGeneratorAdapter.getPrefix();
//    }
//
//    /**
//     * 获取Example名称，例如：ITEMEXAMPLE
//     *
//     * @return 返回Example名称，例如：ITEMEXAMPLE
//     * @throws MsOnionException 异常
//     * @Title: getExampleName
//     * @Description: 获取Example名称，例如：ITEMEXAMPLE
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:47:49
//     */
//    public String getExampleName() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getExampleName();
//    }
//
//    /**
//     * 获取Example标识，例如：:ITEMEXAMPLE:
//     *
//     * @return 返回Example标识，例如：:ITEMEXAMPLE:
//     * @throws MsOnionException 异常
//     * @Title: getExampleIdentification
//     * @Description: 获取Example标识，例如：:ITEMEXAMPLE:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:48:13
//     */
//    public String getExampleIdentification() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getExampleIdentification();
//    }
//
//    /**
//     * 获取单一Example名称，例如：EXAMPLE
//     *
//     * @return 返回单一Example名称，例如：EXAMPLE
//     * @throws MsOnionException 异常
//     * @Title: getSingleExampleName
//     * @Description: 获取单一Example名称，例如：EXAMPLE
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午11:41:55
//     */
//    public String getSingleExampleName() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getSingleExampleName();
//    }
//
//    /**
//     * 获取单一Example标识，例如：:EXAMPLE:
//     *
//     * @return 返回单一Example标识，例如：:EXAMPLE:
//     * @throws MsOnionException 异常
//     * @Title: getSingleExampleIdentification
//     * @Description: 获取单一Example标识，例如：:EXAMPLE:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午11:43:32
//     */
//    public String getSingleExampleIdentification() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getSingleExampleIdentification();
//    }
//
//    /**
//     * 获取PAGE名称，例如：PAGE（分页）
//     *
//     * @return 返回PAGE名称，例如：PAGE（分页）
//     * @throws MsOnionException 异常
//     * @Title: getPageName
//     * @Description: 获取PAGE名称，例如：PAGE
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:50:34
//     */
//    public String getPageName() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getPageName();
//    }
//
//    /**
//     * 获取PAGE的标识，例如：:PAGE: （分页）
//     *
//     * @return 获取PAGE的标识，例如：:PAGE:
//     * @throws MsOnionException 异常
//     * @Title: getPageIdentification
//     * @Description: 获取PAGE的标识，例如：:PAGE:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午10:51:55
//     */
//    public String getPageIdentification() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getPageIdentification();
//    }
//
//    /**
//     * 获取Session标识，例如：SESSION:
//     *
//     * @return 返回Session标识，例如：SESSION:
//     * @throws MsOnionException 异常
//     * @Title: getSessionIdentification
//     * @Description: 获取Session标识，例如：SESSION:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月14日 上午10:43:41
//     */
//    public String getSessionIdentification() throws MsOnionException {
//        return this.redisKeyGeneratorAdapter.getSessionIdentification();
//    }
//
//    /**
//     * 获取Count标识，例如：COUNT:
//     *
//     * @return 返回Count标识，例如：COUNT:
//     * @throws MsOnionException 异常
//     * @Title: getCountIdentification
//     * @Description: 获取Count标识，例如：COUNT:
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月15日 上午10:43:41
//     */
//    public String getCountIdentification() throws MsOnionException {
//        // 没有前面的“:”
//        return this.redisKeyGeneratorAdapter.getCountIdentification();
//    }
//
//    /**
//     * 加密目标POJO
//     *
//     * @param record 目标POJO实例对象
//     * @return 返回加密之后数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Title: encodeValue
//     * @Description:加密目标POJO
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午3:37:16
//     */
//    protected String encodeValue(T record) throws MsOnionIllegalArgumentException, MsOnionException {
//        if (null == record) {
//            return null;
//        }
//        String json = MsOnionJsonUtils.objectToJson(record);
//        if (StringUtils.isBlank(json)) {
//            return null;
//        }
//        return MsOnionSecurityUtils.encodeForRedis(json);
//    }
//
//    /**
//     * 加密Value
//     *
//     * @param value 需要加密数据
//     * @return 返回加密之后数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Title: encodeValue 加密
//     * @Description: 加密Value
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午3:48:20
//     */
//    protected String encodeValue(String value) throws MsOnionIllegalArgumentException, MsOnionException {
//        if (StringUtils.isBlank(value)) {
//            return null;
//        }
//        return MsOnionSecurityUtils.encodeForRedis(value);
//    }
//
//    /**
//     * 解密
//     *
//     * @param encryptText 加密Text
//     * @return
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Title: decodeValue
//     * @Description: 解密Value
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午3:41:17
//     */
//    protected String decodeValue(String encryptText) throws MsOnionIllegalArgumentException, MsOnionException {
//        if (StringUtils.isBlank(encryptText)) {
//            return null;
//        }
//        return MsOnionSecurityUtils.decodeForRedis(encryptText);
//    }
//
//    /**
//     * @return 返回Redis中Keys的Key，更新Redis缓存需要使用到，Set集合
//     * @throws MsOnionException 异常
//     * @Title: getKey
//     * @Description: 获取Redis中Keys的Key，更新Redis缓存需要使用到，Set集合
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午2:01:47
//     */
//    protected String getKey() throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getKey();
//    }
//
//    /**
//     * 获取Redis中当前所有Key对应的Keys，更新Redis缓存需要使用到
//     *
//     * @return 返回Redis中当前所有Key对应的Keys，更新Redis缓存需要使用到
//     * @throws MsOnionException 异常
//     * @Title: getSetKeysValues
//     * @Description: 获取Redis中当前所有Key对应的Keys，更新Redis缓存需要使用到
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午4:07:07
//     */
//    protected Set<String> getSetKeysValues() throws MsOnionException {
//        String key4Keys = getKey();
//        if (StringUtils.isBlank(key4Keys)) {
//            return null;
//        }
//        return this.msOnionJedisAdapter.smembers(key4Keys);
//    }
//
//    /**
//     * 获取Example的Redis中的Key
//     *
//     * @param example 目标POJO的Example实例对象
//     * @return 返回Example的Redis中的Key
//     * @throws MsOnionException 异常
//     * @Title: getKey
//     * @Description: 获取Example的Redis中的Key
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午1:08:59
//     */
//    protected String getKey(E example) throws MsOnionException {
//        return this.getRedisKeyGeneratorAdapter().getKey(example);
//    }
//
//    /**
//     * 添加Redis中的Keys的Key，Set集合
//     *
//     * @param key    Redis中的Key
//     * @param expire Redis缓存周期，单位：秒
//     * @return 返回Redis添加操作结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Title: addKey
//     * @Description: 添加Redis中的Keys的Key，Set集合
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午2:44:51
//     */
//    private Long addKey(String key, int expire) throws MsOnionIllegalArgumentException, MsOnionException {
//        if (StringUtils.isBlank(key)) {
//            return DEFAULT_RETUAN_LONG_VALUE;
//        }
//        String key4Keys = getKey();
//        if (StringUtils.isBlank(key4Keys)) {
//            return DEFAULT_RETUAN_LONG_VALUE;
//        }
//        // 加密
//        key = encodeValue(key);
//        // 添加 Set集合Key
//        Long result = this.msOnionJedisAdapter.sadd(key4Keys, key);
//        // 设置过期时间
//        this.msOnionJedisAdapter.expire(key4Keys, expire);
//        return result;
//    }
//
//    /**
//     * 删除Redis中的Keys的Key，Set集合
//     *
//     * @param apiVersion 版本号
//     * @param key        Redis缓存中的Key
//     * @return 返回Redis删除操作结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Title: deleteKey
//     * @Description: 删除Redis中的Keys的Key，Set集合
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午3:10:37
//     */
//    private Long deleteKey(MsOnionApiVersion apiVersion, String key) throws MsOnionIllegalArgumentException, MsOnionException {
//        String key4Keys = getKey();
//        // 打印日志
//        this.msOnionLogger.debug(getClass().getName(),
//                String.format("deleteKey # 删除Set集合中成员(key)，明文key=%s，key4Keys=%s",
//                        key, key4Keys));
//        // 加密
//        key = encodeValue(key);
//        if (StringUtils.isBlank(key) || StringUtils.isBlank(key4Keys)) {
//            return DEFAULT_RETUAN_LONG_VALUE;
//        }
//
//        // 打印日志
//        this.msOnionLogger.debug(getClass().getName(),
//                String.format("deleteKey # 删除Set集合中成员(key)，加密key=%s，key4Keys=%s",
//                        key, key4Keys));
//        return this.msOnionJedisAdapter.srem(key4Keys, key);
//    }

    /////////////////////////////////////////  End  ################ ////////////////////

    ///////////////////////////////////////// 具体实现， Begin ################ ////////////////////

//	/**
//	 * @Title: queryByPage
//	 * @Description: 分页查询
//	 * @Author: HeroCao hero-cao@msyc.cc
//	 * @Date 2017年2月15日 下午5:15:48
//	 *
//	 * @param example
//	 * @param pageNum 页数，从1开始
//	 * @param pageSize 每页数量
//	 * @return
//	 */
//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum, int pageSize)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.queryByPage(apiVersion, example, pageNum, pageSize, this.isDisabledRedisQueryCache);
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter,
// E example, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.queryByPage(apiVersion, parameter, example, pageNum, pageSize, null,
// this.isDisabledRedisQueryCache, this.getRedisCacheExpire());
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter,
// E example, int pageNum, int pageSize, String orderBy)
// throws MsOnionIllegalArgumentException, MsOnionException {
//
//		//		// 使用PageHelper设置分页信息
//		//		PageHelper.startPage(pageNum, pageSize);
//		//		// 使用 itemMapper 查询
//		//		// 创建 ItemExample，没有设置查询条件，默认查询所有，但是，前面已经设置分页
//		//		//		List<T> list = this.itemMapper.selectByExample(example);
//		//		List<T> list = this.baseMapper.selectByExample(example);
//		//		return new PageInfo<T>(list);
//
//		return this.queryByPage(apiVersion, example, pageNum, pageSize, this.isDisabledRedisQueryCache);
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example,
// int pageNum, int pageSize, boolean isDisabledRedisQueryCache)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.queryByPage(apiVersion, example, pageNum, pageSize, isDisabledRedisQueryCache, this.getRedisCacheExpire());
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum,
// int pageSize, boolean isDisabledRedisQueryCache, int redisCacheExpire)
// throws MsOnionIllegalArgumentException, MsOnionException {
////		List<T> list = this.queryListByPage(apiVersion, example, pageNum, pageSize, isDisabledRedisQueryCache, redisCacheExpire);
////		return new PageInfo<T>(list);
//
//		return this.queryByPage(apiVersion, example, pageNum, pageSize, null, isDisabledRedisQueryCache);
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum,
// int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		List<T> list = this.queryListByPage(apiVersion, example, pageNum, pageSize, isDisabledRedisQueryCache, redisCacheExpire);
//		return new PageInfo<T>(list);
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, int pageNum, int pageSize, String orderBy)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		return null;
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter,
// int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException {
//		return null;
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter,
// int pageNum, int pageSize, String orderBy)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.queryByPage(apiVersion, parameter, null, pageNum, pageSize,
// orderBy, this.isDisabledRedisQueryCache, this.getRedisCacheExpire());
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum,
// int pageSize, String orderBy, boolean isDisabledRedisQueryCache)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.queryByPage(apiVersion, null, example, pageNum, pageSize, orderBy,
// isDisabledRedisQueryCache, this.getRedisCacheExpire());
//	}

//	@Override
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter,
// E example, int pageNum, int pageSize, String orderBy,
// boolean isDisabledRedisQueryCache, int redisCacheExpire)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		List<T> list = this.queryListByPage(apiVersion, parameter, example, pageNum,
// pageSize, orderBy, isDisabledRedisQueryCache, redisCacheExpire);
//		return new PageInfo<T>(list);
//	}


    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, int pageNum, int pageSize)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, pageNum, pageSize, null);
    }

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, int pageNum, int pageSize,
                                   String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, null, pageNum, pageSize, orderBy, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum,
                                   int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, parameter, pageNum,
                pageSize, orderBy, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                   int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, parameter, pageNum, pageSize, orderBy,
                isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum,
                                   int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {

        // 检查参数
        inspectParameters(apiVersion);
        try {
//            E example = getExampleClazz().newInstance();
//            Method declaredMethod = example.getClass().getDeclaredMethod("createCriteria", null);
//
//            Object obj = declaredMethod.invoke(example, null);

            E example = getExample();
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到 ，
            //  不要在这里调用，而是在 queryListByPage 中统一调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());

            return this.queryListByPage(apiVersion, parameter, example, pageNum,
                    pageSize, orderBy, isDisabledRedisQueryCache, redisCacheExpire);
        } catch (MsOnionIllegalArgumentException ex) {
            throw ex;
        } catch (MsOnionException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, example, pageNum, pageSize, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize,
                                   boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, example, pageNum, pageSize, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    //

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize,
                                   boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryListByPage(apiVersion, example, pageNum, pageSize, null, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /// Begin

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize,
                                   String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, null, example, pageNum, pageSize, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                                   int pageNum, int pageSize, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, parameter, example, pageNum, pageSize, null, isDisabledRedisQueryCache);
    }

    /// End

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                                   int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryListByPage(apiVersion, parameter, example, pageNum, pageSize, orderBy, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

//	@Override
//	public List<T> queryListByPage(ApiVersion apiVersion, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
//		return queryListByPage(apiVersion, null, example, pageNum, pageSize, orderBy, isDisabledRedisQueryCache);
//	}

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                   E example, int pageNum, int pageSize, String orderBy)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPage(apiVersion, parameter, example, pageNum, pageSize, orderBy, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize,
                                   String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryListByPage(apiVersion, null, example, pageNum, pageSize, orderBy, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                                   int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache,
                                   int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, example);
        // 检查分页参数
        inspectParameterForPaging(parameter);

        long totalCounts = this.countByExample(apiVersion, parameter, example);
        // 打印日志
        this.msOnionLogger.debug(getClass().getName(), String.format("queryListByPage#分页查询#totalCounts=%s", totalCounts));

        pageSize = inspectParameterForPageSize(pageSize);

        pageNum = inspectParameterForPageNum(totalCounts, pageNum, pageSize);

        MsOnionPagingParameter pagingParameter = null;
        if (null == parameter) {
            pagingParameter = new MsOnionPagingParameter();
        } else {
            // 由于前面已经检查过为分页参数了，就不要再判断
            pagingParameter = (MsOnionPagingParameter) parameter;
        }

        // 设置分页信息
        pagingParameter.setTotalCounts(totalCounts);
        pagingParameter.setPageNum(pageNum);
        pagingParameter.setPageSize(pageSize);

        try {
            // 多次调用 invokeAndStatusIn 是否有问题？？？
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
//            invokeAndStatusIn(example, getStatusAvailableValues());

            // 如果禁用Redis缓存
            if (isDisabledRedisQueryCache) {
                // 从数据库中查询
                return this.queryListByPageAndAddCache(apiVersion, parameter, example,
                        pageNum, pageSize, orderBy, isDisabledRedisQueryCache, redisCacheExpire);
            }
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }

        String key = null;
        try {
            // 如果没有禁用Redis缓存
            // 获取Key , 如果需要分组，只能通过 parameter 把分组参数传递进来
            key = this.getRedisKeyGeneratorAdapter().getKey(example, pageNum, pageSize, orderBy);
        } catch (Exception ex) {
            this.msOnionLogger.error(this.getClass().getName(), ex);
        }
        // 如果Key为null 、空字符串
        if (StringUtils.isBlank(key)) {
            // 从数据库中查询
            return this.queryListByPageAndAddCache(apiVersion, parameter, example,
                    pageNum, pageSize, orderBy, isDisabledRedisQueryCache, redisCacheExpire);
        }

        try {

            // 从Redis缓存中获取数据
            String value = this.msOnionJedisAdapter.get(key);
            // 解密
            String json = decodeValue(value);
            if (StringUtils.isNotBlank(json)) {
                Class<T> clazz = getTargetClazz();
                if (null != clazz) {
                    List<T> list = MsOnionJsonUtils.jsonToList(json, clazz);
                    if (!MsOnionCollectionUtils.isEmpty(list)) {
                        return list; // 从Redis缓存中读取
                    }
                }
            }

        } catch (Exception ex) {
            this.msOnionLogger.error(this.getClass().getName(), ex);
        }

        try {
            // 从数据库中查询
            return this.queryListByPageAndAddCache(apiVersion, parameter, example,
                    pageNum, pageSize, orderBy, isDisabledRedisQueryCache, redisCacheExpire);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 分页查询，从数据库中查询，和添加到数据中
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    private List<T> queryListByPageAndAddCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                               E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache,
                                               int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        // 从数据库中查询
        List<T> list = null;
//		// 使用PageHelper设置分页信息
//		PageHelper.startPage(pageNum, pageSize);
        // 排序分页
        if (StringUtils.isNotBlank(orderBy)) {
//			PageHelper.orderBy(orderBy);
            // 兼容JQuery EaseUI自带排序功能，createByMemberIdx 转换成数据库的表列名：create_by_member_idx
            orderBy = MsOnionStringUtils.getCorrectOrderByForDB(orderBy);
            // PageHelper 5.x 把排序独立出来
//            PageHelper.startPage(pageNum, pageSize, orderBy);
            // PageHelper 5.x 实现  ## Begin
            // false：不进行count查询
            PageHelper.startPage(pageNum, pageSize, false);
            // OrderByHelper.orderBy("idx desc");
            OrderByHelper.orderBy(orderBy);
            // PageHelper 5.x 实现  ## End
        } else {
            // 使用PageHelper设置分页信息
            PageHelper.startPage(pageNum, pageSize);
        }
//		PageHelper.startPage()
        // String orderBy
        try {
            // 查询
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());
            // 查询
            list = this.baseMapper.selectByExample(example);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

        if (MsOnionCollectionUtils.isEmpty(list)) {
            return list;
        }

        // 屏蔽密码
        shieldPasswordForPojoSecurity(list);

        try {
            // 存储到 Redis中
            // 获取Key ， 如果需要分组，只能通过 parameter 把分组参数传递进来
            String key = this.getRedisKeyGeneratorAdapter().getKey(example, pageNum, pageSize, orderBy);
            String json = MsOnionJsonUtils.objectToJson(list);
            if (StringUtils.isNotBlank(key) && StringUtils.isNoneBlank(json)) {
                json = encodeValue(json);
                // 缓存
                this.msOnionJedisAdapter.set(key, json);
                // 设置周期
                this.msOnionJedisAdapter.expire(key, redisCacheExpire);

                // 保存当前Key，为了以后更新缓存使用的
                //				String key4Keys = this.getRedisKeyGeneratorAdapter().getKey();
                //				Set<String> members = this.ttJedis.smembers(key4Keys);
                // 1、smembers 没有存在 ， 2、smembers 存在并不包括key4Keys ，3、 smembers 存在但包括key4Keys
                //				if (MsOnionCollectionUtils.isNotEmpty(smembers) && !smembers.contains(key)) {
                //					this.ttJedis.sadd(key4Keys, key);
                //				}
                //				members

                // 直接添加，Redis底层会判断是否存在
                //				this.ttJedis.sadd(key4Keys, key);

                // 保存当前Key，为了以后更新缓存使用的
                this.addKey(key, redisCacheExpire);

            }
        } catch (Exception e) {
            // 如果添加到缓存中报错，不允许throw异常，因为会影响其他正常代码
            this.msOnionLogger.error(this.getClass().getName(), e);
        }

        return list;
    }

    /**
     * 根据目标POJO的Example来获取记录数
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public long countByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                               E example) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查ApiVersion参数
        inspectParameters(apiVersion, example);

        try {
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());

            // 先从Redis中获取，不使用getKey ，为了区分COUNT:
//            String key = this.getRedisKeyGeneratorAdapter().getKey(example);
            // 获取Count在Redis中的Key
            String key = this.getRedisKeyGeneratorAdapter().getCountKey(example);
            if (StringUtils.isNotBlank(key)) {
                // 如果没有，则返回 null
                String data = this.msOnionJedisAdapter.get(key);
                // 解密
                data = decodeValue(data);
                if (StringUtils.isNotBlank(data) && StringUtils.isNumeric(data)) {
                    int result = Integer.parseInt(data);
                    //					if (result > 0) {
                    //						result result;
                    //					}
                    return result;  // 不管是否为0 都返回，因为这是查询记录数，而不是CUD 结果
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        try {
            // 性能优化，必须加上分页
            // 使用PageHelper设置分页信息
            // PageHelper 4.x
//            PageHelper.startPage(MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGENUM_VALUE, MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGESIZE_VALUE);
            // PageHelper 5.x
            PageHelper.startPage(MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGENUM_VALUE,
                    MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGESIZE_VALUE,
                    MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGING_IS_COUNT);
            // 查询记录数，查询记录数是可以根据条件来查询，因此不要考虑分页、分组！！！
            // 因为需要的是记录数，或者某一个条件的总记录数！！！
            long count = this.baseMapper.countByExample(example);
            // 添加Redis， 不使用addRedisCache ，为了区分COUNT:
//            this.addRedisCache(apiVersion, parameter, example, count);
            this.addRedisCacheForCount(apiVersion, parameter, example, count);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("countByExample#查询记录数#count=%s", count));
            return count;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 根据目标POJO的Example来获取记录数
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public long countByExample(MsOnionApiVersion apiVersion, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.countByExample(apiVersion, null, example);
    }

    ///////////////////////////////// 直接删除，而不是软删除，禁止， Begin ///////////////

//    /**
//     * 根据目标POJO的Example删除，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                               E example) throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查ApiVersion参数
//        inspectParameters(apiVersion, example);
//
//        try {
//            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
//            // 如果是0：删除的，不可以查询到
//            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());
//            // 执行删除
//            int result = this.baseMapper.deleteByExample(example);
//            // 删除缓存
//            this.deleteRedisCache(apiVersion, parameter, example, result);
//            // 删除列表Redis缓存
//            this.syncPageAndExampleRedisCacheForDelete(apiVersion, parameter, example, result);
//            return result;
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }
//    }

//    /**
//     * 根据目标POJO的Example删除，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByExample(MsOnionApiVersion apiVersion, E example)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByExample(apiVersion, null, example);
//    }

//    /**
//     * 通过主键Idx删除，不允许有删除数据功能，都是通过软删除，其实就是更新状态
//     *
//     * @param idx
//     * @return
//     * @Title: deleteByPrimaryKey
//     * @Description: 通过主键Idx删除
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月15日 下午6:30:12
//     */
//    @Override
//    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, Long idx)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return deleteByPrimaryKey(apiVersion, null, idx);
//    }

//    /**
//     * 通过主键Idx删除，不允许有删除数据功能，都是通过软删除，（不允许直接删除数据）
//     *
//     * @param idx
//     * @return
//     * @Title: deleteByPrimaryKey
//     * @Description: 通过主键Idx删除
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月15日 下午6:30:12
//     */
//    @Override
//    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, Long idx)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return deleteByPrimaryKey(apiVersion, null, idx);
//    }

//    /**
//     * 根据主键idx删除，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param idx               主键idx
//     * @return 如果返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                  Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查ApiVersion参数
//        inspectParameters(apiVersion, idx);
//
//        try {
//            int result = this.baseMapper.deleteByPrimaryKey(idx);
//            // 删除Redis缓存
//            this.deleteRedisCache(apiVersion, parameter, idx, result);
//
//            this.syncPageAndExampleRedisCacheForDelete(apiVersion, parameter, idx, result);
//            return result;
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }
//    }

    ///////////////////////////////// 直接删除，而不是软删除，禁止， End ///////////////


    ///////////////////////////////// 软删除， Begin ///////////////////////////////////////

//    /**
//     * 通过主键Idx删除，不允许有删除数据功能，都是通过软删除，其实就是更新状态
//     *
//     * @param idx
//     * @return
//     * @Title: deleteByPrimaryKey
//     * @Description: 通过主键Idx删除
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月15日 下午6:30:12
//     */
//    @Override
//    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, Long idx)
//            throws MsOnionIllegalArgumentException, MsOnionException {
////        return deleteByPrimaryKey(apiVersion, null, idx);
//
//        return 0;
//    }

//    /**
//     * 根据目标POJO的Example删除，（不允许直接删除数据，软删除，就是更新状态）
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param example    目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                               E example) throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查ApiVersion参数
//        inspectParameters(apiVersion, example);
//
//        try {
//            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
//            // 如果是0：删除的，不可以查询到
//            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());
//
//            this.baseMapper.updateByExample()
//
//            // 删除缓存
////            this.deleteRedisCache(apiVersion, parameter, example, result);
////            // 删除列表Redis缓存
////            this.syncPageAndExampleRedisCacheForDelete(apiVersion, parameter, example, result);
//            return result;
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }
//    }

//    /**
//     * 根据目标POJO的Example删除，（不允许直接删除数据，软删除，就是更新状态）
//     *
//     * @param apiVersion api版本
//     * @param example    目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByExample(MsOnionApiVersion apiVersion, E example)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByExample(apiVersion, null, example);
//    }

    /**
     * 通过主键Idx删除，不允许有删除数据功能，都是通过软删除，其实就是更新状态
     *
     * @param idx
     * @return
     * @Title: deleteByPrimaryKey
     * @Description: 通过主键Idx删除
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月15日 下午6:30:12
     */
    @Override
    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return deleteByPrimaryKey(apiVersion, null, idx);
    }

    /**
     * 根据主键idx删除，（不允许直接删除数据、软删除，就是更新状态）
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                  Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查ApiVersion参数
        inspectParameters(apiVersion, idx);

        // 删除用户，其实就是更新状态，统一调用更新状态的方法！！！
//        try {
////            int result = this.baseMapper.deleteByPrimaryKey(idx);
////            // 删除Redis缓存
////            this.deleteRedisCache(apiVersion, parameter, idx, result);
////
////            this.syncPageAndExampleRedisCacheForDelete(apiVersion, parameter, idx, result);
////            return result;
//
//            // 根据主键查询
//            T t = this.queryByPrimaryKey(apiVersion, parameter, idx);
//            if (null == t) {
//                return 0;  // 查不到这条记录
//            }
//
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), String.format("根据主键idx删除(软删除，更新状态),idx=%s, t=%s", idx, t));
//
//            // 如果查询到，状态已经是删除了
//            // 因为数据库所有表都有status 列
//            Field field = t.getClass().getDeclaredField(TABLE_STATUS_COLUMN_NAME);
//            // 设置允许访问
//            field.setAccessible(true);
//            short value = (short) field.get(t);
//            if (value == this.sqlColumnDeleteStatusValue) {
//                return this.DEFAULT_UPDATE_SUCCESS_RESULT;
//            }
//
//            // 获取目标POJO的Example实例
//            E example = getExample();
//            // 反射方式，更新状态值
//            this.invokeAndStatusEqualTo(example, this.sqlColumnDeleteStatusValue);
//
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), String.format("根据主键idx删除(软删除，更新状态)#更新状态之后，idx=%s, t=%s", idx, t));
//
//            // 执行更新
//            // 实例对象返回数据库更新影响行数，1：更新成功（软删除），0：更新失败（软删除）
//            int result = this.updateByExample(apiVersion, parameter, t, example);
//
//            // 更新Redis缓存 （Example、Page缓存）, 是否有BUG，可以更新达到缓存吗 ？？？
//            this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, t, example, result);
//            // 更新主键idx缓存（也是添加缓存）
//            this.addRedisCache(apiVersion, parameter, t, idx, result, this.getRedisCacheExpire());
//
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), String.format("根据主键idx删除(软删除，更新状态)#已经更新所有关联的缓存，idx=%s,
// result=%s, t=%s", idx, result, t));
//
//            return result;
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }

        // 删除用户，其实就是更新状态
        return this.updateStatus(apiVersion, parameter, idx, this.sqlColumnDeleteStatusValue);

    }

    ///////////////////////////////// 软删除， End ////////////////////////////////////////


    /**
     * 根据主键idx集合进行批量删除（软删除）
     *
     * @param apiVersion api版本
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int deleteByIdxes(MsOnionApiVersion apiVersion, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.deleteByIdxes(apiVersion, null, idxes);
    }

    /**
     * 根据主键idx集合进行批量删除（软删除）
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int deleteByIdxes(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                             List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, idxes);

        try {

            E example = getExample();

            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
//            invokeAndStatusIn(example, getStatusAvailableValues());

            // 必须同时使用 status： 1 ，2 来限制
//            this.invokeAndStatusInAndIdxIn(example, getStatusAvailableValues(), idxes);

            this.invokeAndStatusInAndIdxIn(example, null, idxes);

            // 这里无法删除缓存，因为需要
//            return this.deleteByIdxesWithExample(apiVersion, parameter, example);

            int result = this.baseMapper.deleteByIdxesWithExample(example);

            this.msOnionLogger.debug(getClass().getName(), "deleteByIdxes # 批量删除， result=" + result + ", idxes=" + idxes);


            // TODO: 应该要遍历 ！！！
//            this.syncPageAndExampleRedisCacheForUpdate(apiVersion, null , result);

            // 要删除缓存，可能存在只是部分删除成功
            // 遍历删除缓存
//            for (long idx : idxes) {
//                // 遍历删除缓存
//                this.deleteRedisCache(apiVersion, parameter, idx, result);
//                // 同步目标POJO的列表缓存
//                // 这里有问题！！！  还没有同步列表缓存 ！！！
//                // syncPageAndExampleRedisCacheForDelete 这个方法还没有实现！！！
//                this.syncPageAndExampleRedisCacheForDeleteIdxes(apiVersion, parameter, idxes, result);
//            }

            // 要删除缓存，可能存在只是部分删除成功，result 和 idxes 的数量不一致，但是也要按照idxes 集合来删除
            // TODO: 其实删除，就是更新状态，没有实际删除操作！！！可以换成 更新方式实现 ！！！
            this.syncPageAndExampleRedisCacheForDeleteIdxes(apiVersion, parameter, idxes, result);

            return result;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }


//    /**
//     * 根据主键idx集合进行删除，也就是批量删除，其实是软删除，
//     * <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
//     *
//     * @param example 目标POJO的Example实例对象
//     * @return 返回删除成功记录条数
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByIdxesWithExample(E example) throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByIdxesWithExample(apiV);
//    }

//    /**
//     * 根据主键idx集合进行删除，也就是批量删除，其实是软删除，
//     * <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
//     *
//     * @param apiVersion
//     * @param example    目标POJO的Example实例对象
//     * @return 返回删除成功记录条数
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByIdxesWithExample(ApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByIdxesWithExample(apiVersion, null, example);
//    }

//    /**
//     * 根据主键idx集合进行删除，也就是批量删除，其实是软删除，
//     * <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param example    目标POJO的Example实例对象
//     * @return 返回删除成功记录条数
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public int deleteByIdxesWithExample(ApiVersion apiVersion, MsOnionParameterAdapter parameter, E example)
// throws MsOnionIllegalArgumentException, MsOnionException {
//
//        // 检查API版本
//        inspectParameters(apiVersion);
//        inspectParameter(example);
//
//        // 使用反射
//        /*
//         MemberExample example = new MemberExample();
//            Criteria criteria = example.createCriteria();
//            criteria.andIdxIn(idxes);
//         */
////        example.getClass().getDeclaredMethod()
//
//        try {
//            int result = this.baseMapper.deleteByIdxesWithExample(example);
//
//            // 如果删除成功更新缓存
//            if (result >= 1) {
//                //  // 删除列表、分页、条件查询 Redis缓存，必须在添加缓存之前，否则到又删除当前的缓存
////                this.syncPageAndExampleRedisCacheForAdd(apiVersion, record, result);
//
//                // TODO: 这里可能有BUG
//                this.syncPageAndExampleRedisCacheForDelete(apiVersion, example, result);
//
//            }
//
//            return result;
//
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }
//    }

    /**
     * 保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public int save(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                    T record) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, record);

        try {
            this.msOnionLogger.debug(getClass().getName(), "save , 新增，检查和SQL注入过滤之前，record=" + record);

            // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
            inspectPojoFieldValue(apiVersion, parameter, record);

            this.msOnionLogger.debug(getClass().getName(), "save , 新增，检查和SQL注入过滤之后，record=" + record);

            // 设置新注册的所有用户默认是2 ？？ 不要最好通过代码来实现，因为有可能
            // 影响都其他模块的默认值，商品新增之后，默认就是1 ，成员新增之后，就是2（未激活）
            int result = this.baseMapper.insert(record);

            // 删除列表、分页、条件查询 Redis缓存，必须在添加缓存之前，否则到又删除当前的缓存
            this.syncPageAndExampleRedisCacheForAdd(apiVersion, parameter, record, result);

            // 添加Redis缓存
            // 优化：新增的数据，就不放到Redis缓存中，根据主键查询、条件查询 modify by 2017-07-04 22:30
//            this.addRedisCache(apiVersion, parameter, record, result);

            return result;
        } catch (MsOnionIllegalArgumentException e) {
            throw e;
        } catch (MsOnionException e) {
            throw e;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public int save(MsOnionApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.save(apiVersion, null, record);
    }

//	/**
//	 * (non-Javadoc)
//	 *
//	 *
//	 * @param record
//	 * @return
//	 * @throws MsOnionIllegalArgumentException
//	 * @throws MsOnionException
//	 */
//	@Override
//	public int saveSelective(ApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException {
//		try {
//			// 检查POJO的字段的值，如果为null，都设置对应类型的默认值
//			inspectPojoFieldValue(apiVersion, record);
//
//			int result = this.baseMapper.insertSelective(record);
//
//			// 删除列表、分页、条件查询 Redis缓存，必须在添加缓存之前，否则到又删除当前的缓存
//			this.syncPageAndExampleRedisCacheForAdd(apiVersion, record, result);
//
//			// 添加Redis缓存
//			this.addRedisCache(apiVersion, record, result);
//
//			return result;
//		} catch (Exception e) {
//			throw new MsOnionException(e);
//		}
//	}

    //	@Override
    //	public List<T> queryByExampleWithBLOBs(ApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException {
    //		return this.baseMapper.selectByExampleWithBLOBs(example);
    //	}

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryByExample(MsOnionApiVersion apiVersion,
                                  E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExample(apiVersion, example, this.isDisabledRedisQueryCache);
    }

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryByExample(MsOnionApiVersion apiVersion, E example,
                                  boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExample(apiVersion, example, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryByExample(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache,
                                  int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExample(apiVersion, null, example, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                  E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExample(apiVersion, parameter, example, this.isDisabledRedisQueryCache);
    }

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                                  boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache,
                                  int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {

        // 检查参数
        inspectParameters(apiVersion, example);

//        try {
//            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
//            // 如果是0：删除的，不可以查询到
//            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }

        // 优先级：当前如果已经是true，那么全局的就不需要判断了，如果当前的为false，就需要判断全局
        // 当前true > 全局true | false
        if (!isDisabledRedisQueryCache) {  // 如果没有禁用Redis查询缓存
            try {
                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("queryByExample # 查询，可以使用缓存 # 根据example得到key=%s",
                                key));
                if (StringUtils.isNotBlank(key)) {
                    String json = this.msOnionJedisAdapter.get(key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("queryByExample # 查询，可以使用缓存 # 根据example得到key=%s，根据Key查询缓存加密json=%s",
                                    key, json));
                    // 解密
                    json = decodeValue(json);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("queryByExample # 查询，可以使用缓存 # 根据example得到key=%s，解密之后的json=%s",
                                    key, json));
                    if (StringUtils.isNotBlank(json)) {
                        Class<T> clazz = getTargetClazz();
                        List<T> list = MsOnionJsonUtils.jsonToList(json, clazz);
                        if (!MsOnionCollectionUtils.isEmpty(list)) {
                            return list;
                        }
                    }
                }
            } catch (Exception e) {
                this.msOnionLogger.error(this.getClass().getName(), e);
            }
        }

        List<T> list = null;
        try {
            // 查询数据库
            list = this.baseMapper.selectByExample(example);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryByExample # 查询，直接查询MySQL # getTargetName()=%s，查询列表记录数list=%s",
                            getTargetName(), list));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }

        // 添加到Redis缓存中
        // 不管当前是否禁用缓存，查询结果都是存在到缓存中
        try {
            if (!MsOnionCollectionUtils.isEmpty(list)) {
                String json = MsOnionJsonUtils.objectToJson(list);
                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("queryByExample # 查询，直接查询MySQL # 准备添加到Redis缓存中 # 根据Example得到key=%s，查询列表json=%s",
                                key, json));
                if (StringUtils.isNotBlank(json) && StringUtils.isNotBlank(key)) {
                    // tNbbJ , nbbJ , Tnb , tnb
                    // 加密
                    json = encodeValue(json);
                    // 2个方法实现的
//                    this.msOnionJedisAdapter.set(key, json);
//                    // 设置周期
//                    this.msOnionJedisAdapter.expire(key, redisCacheExpire);
                    // 1个方法，添加到Redis缓存中
                    setForRedis(parameter, key, json, redisCacheExpire);
                    // 保存当前Key，为了以后更新缓存使用的
                    this.addKey(key, redisCacheExpire);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("queryByExample # 查询，直接查询MySQL # 已添加到Redis缓存中 # 根据Example得到key=%s，加密json=%s，缓存周期redisCacheExpire=%s",
                                    key, json, redisCacheExpire));
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return list;
    }

    /**
     * 根据目标POJO的Example查询，查询记录最多一条
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    protected T queryOneByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                  E example, boolean isDisabledRedisQueryCache,
                                  int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, example);

//        try {
//            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
//            // 如果是0：删除的，不可以查询到
//            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }

        // 优先级：当前如果已经是true，那么全局的就不需要判断了，如果当前的为false，就需要判断全局
        // 当前true > 全局true | false
        if (!isDisabledRedisQueryCache) {  // 如果没有禁用Redis查询缓存
            try {
                // 不可以使用 getKey(example); 导致 queryOne 和 queryByExample 冲突
//                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                String key = this.getRedisKeyGeneratorAdapter().getKeyForQueryOne(example);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("queryOneByExample # 查询One，可以使用缓存 # 根据example得到key=%s",
                                key));
                if (StringUtils.isNotBlank(key)) {
                    String json = this.msOnionJedisAdapter.get(key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("queryOneByExample # 查询One，可以使用缓存 # 根据example得到key=%s，根据Key查询缓存加密json=%s",
                                    key, json));
                    // 解密
                    json = decodeValue(json);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("queryOneByExample # 查询One，可以使用缓存 # 根据example得到key=%s，解密之后的json=%s",
                                    key, json));
                    if (StringUtils.isNotBlank(json)) {
                        Class<T> clazz = getTargetClazz();
                        // 由于保存的是 List，尽管 List中只有一个元素
//                        T pojo = MsOnionJsonUtils.jsonToPojo(json, clazz);
//                        if (null != pojo) {
//                            return pojo;
//                        }
                        // 存储都是List，也要按照List来解析
                        List<T> list = MsOnionJsonUtils.jsonToList(json, clazz);
                        if (MsOnionCollectionUtils.isNotEmpty(list)) {
                            // 只有一个元素
                            return list.get(0);
                        }
                    }
                }
            } catch (Exception e) {
                this.msOnionLogger.error(this.getClass().getName(), e);
            }
        }

        List<T> list = null;
        try {
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryOneByExample # 查询One，直接查询MySQL#分页查询(1,1)# getTargetName()=%s，",
                            getTargetName()));
            // 使用PageHelper设置分页信息
            // PageHelper 4.x
//            PageHelper.startPage(MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGENUM_VALUE, MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGESIZE_VALUE);
            // PageHelper 5.x
            PageHelper.startPage(MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGENUM_VALUE,
                    MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGESIZE_VALUE,
                    MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGING_IS_COUNT);
            // 查询数据库
            list = this.baseMapper.selectByExample(example);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryOneByExample # 查询One，直接查询MySQL # getTargetName()=%s，查询列表记录数list=%s",
                            getTargetName(), list));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }

        // 添加到Redis缓存中
        // 不管当前是否禁用缓存，查询结果都是存在到缓存中
        try {
            if (!MsOnionCollectionUtils.isEmpty(list)) {
                String json = MsOnionJsonUtils.objectToJson(list);
                // 不可以使用 getKey(example); 导致 queryOne 和 queryByExample 冲突
//                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                String key = this.getRedisKeyGeneratorAdapter().getKeyForQueryOne(example);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("queryOneByExample # 查询One，直接查询MySQL # 准备添加到Redis缓存中 # 根据Example得到key=%s，查询列表json=%s",
                                key, json));
                if (StringUtils.isNotBlank(json) && StringUtils.isNotBlank(key)) {
                    // tNbbJ , nbbJ , Tnb , tnb
                    // 加密
                    json = encodeValue(json);
                    // 2个方法实现的
//                    this.msOnionJedisAdapter.set(key, json);
//                    // 设置周期
//                    this.msOnionJedisAdapter.expire(key, redisCacheExpire);
                    // 1个方法，添加到Redis缓存中
                    setForRedis(parameter, key, json, redisCacheExpire);
                    // 保存当前Key，为了以后更新缓存使用的
                    this.addKey(key, redisCacheExpire);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("queryOneByExample # 查询One，直接查询MySQL # 已添加到Redis缓存中 # 根据Example得到key=%s，加密json=%s，缓存周期redisCacheExpire=%s",
                                    key, json, redisCacheExpire));
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return MsOnionCollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public T queryByPrimaryKey(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKey(apiVersion, idx, this.isDisabledRedisQueryCache);
    }

    /**
     * 根据主键idx查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public T queryByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                               Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKey(apiVersion, parameter, idx, this.isDisabledRedisQueryCache);
    }

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public T queryByPrimaryKey(MsOnionApiVersion apiVersion, Long idx,
                               boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKey(apiVersion, idx, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

//	@Override
//	public T queryByPrimaryKey(ApiVersion apiVersion, MsOnionParameterAdapter parameter,
// Long idx, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.queryByPrimaryKey(apiVersion, idx, isDisabledRedisQueryCache, this.getRedisCacheExpire());
//	}

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public T queryByPrimaryKey(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache,
                               int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKey(apiVersion, null, idx, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public T queryByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryByPrimaryKey(apiVersion, parameter, idx, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public T queryByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx,
                               boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {

        // 检查参数
        inspectParameters(apiVersion, idx);

        // 如果已经禁用缓存
        if (isDisabledRedisQueryCache) {
            return this.queryByPrimaryKeyAndAddCache(apiVersion, parameter, idx,
                    isDisabledRedisQueryCache, redisCacheExpire);
        }

        // 如果没有禁用Redis缓存
        // 获取Key
        String key = null;
        try {
            // 获取Key
            key = this.getRedisKeyGeneratorAdapter().getKey(idx);
        } catch (Exception ex) {
            // 从Redis缓存中查询数据，不可以抛异常影响正常业务
            this.msOnionLogger.error(getClass().getName(), ex);
        }

        if (StringUtils.isBlank(key)) {
            return this.queryByPrimaryKeyAndAddCache(apiVersion, parameter, idx,
                    isDisabledRedisQueryCache, redisCacheExpire);
        }
        try {
            String json = this.msOnionJedisAdapter.get(key);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryByPrimaryKey # 根据idx查询，查询Redis缓存 # 根据idx=%s得到key=%s，加密json=%s，缓存周期redisCacheExpire=%s",
                            idx, key, json, redisCacheExpire));
            // 解密
            json = decodeValue(json);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryByPrimaryKey # 根据idx查询，查询Redis缓存 # 根据idx=%s得到key=%s，解密json=%s",
                            idx, key, json));
            if (StringUtils.isNotBlank(json)) {
                Class<T> clazz = getTargetClazz();
                if (null != clazz) {
                    T pojo = MsOnionJsonUtils.jsonToPojo(json, clazz);
                    if (null != pojo) {
                        return pojo;  // 从 Redis缓存中获取
                    }
                }
            }
        } catch (Exception e) {
            // 从Redis缓存中查询数据，不可以抛异常影响正常业务
            this.msOnionLogger.error(this.getClass().getName(), e);
        }

        return this.queryByPrimaryKeyAndAddCache(apiVersion, parameter, idx,
                isDisabledRedisQueryCache, redisCacheExpire);

//        // 查询数据库
//        T pojo = null;
//        try {
//
//
//            // 这里通过反射创建 Example，然后调用
////            pojo = this.baseMapper.selectByPrimaryKey(idx);
////            E example = getExampleClazz().newInstance();
//
//            E example = getExample();
//            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
//            // 如果是0：删除的，不可以查询到
//            // 必须在 生成 Redis Key之前调用。
////            this.invokeAndStatusIn(example, getStatusAvailableValues());
////            // 设置 idx 值
////            this.invokeAndIdxEqualTo(example, idx);
//            // 必须同时调用 ，因为 Example ，Criteria 必须是同一个对象
//            this.invokeAndStatusInAndIdxEqualTo(example, getStatusAvailableValues(), idx);
//
//            List<T> list = this.baseMapper.selectByExample(example);
//
//            if (MsOnionCollectionUtils.isEmpty(list)) {
//                return null;
//            }
//
//            // 根据主键查询，就只有一条记录
//            pojo = list.get(0);
//
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }
//
//        try {
//            // 必须使用 try - catch , 因为数据库查询已经执行，下面是添加缓存，不可以影响正常业务逻辑
//            if (null == pojo) {
//                return pojo;
//            }
//            // 获取Key
//            String key = this.getRedisKeyGeneratorAdapter().getKey(idx);
//            String json = MsOnionJsonUtils.objectToJson(pojo);
//            if (StringUtils.isNotBlank(json) && StringUtils.isNoneBlank(key)) {
//                // 加密
//                json = encodeValue(json);
//                this.getMsOnionJedisAdapter().set(key, json);
//                // 设置周期
//                this.getMsOnionJedisAdapter().expire(key, redisCacheExpire);
//                // 保存当前Key，为了以后更新缓存使用的
//                this.addKey(key, redisCacheExpire);
//            }
//        } catch (Exception e) {
//            this.msOnionLogger.error(this.getClass().getName(), e);
//        }
//        return pojo;
    }

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    private T queryByPrimaryKeyAndAddCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx,
                                           boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {

        // 查询数据库
        T pojo = null;
        try {

            // 不可以直接调用，因为没有对状态进行限制 ！！
            // 这里通过反射创建 Example，然后调用
            pojo = this.baseMapper.selectByPrimaryKey(idx);

//            E example = getExample();
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
            // 必须在 生成 Redis Key之前调用。
//            this.invokeAndStatusIn(example, getStatusAvailableValues());
//            // 设置 idx 值
//            this.invokeAndIdxEqualTo(example, idx);
            // 必须同时调用 ，因为 Example ，Criteria 必须是同一个对象
//            this.invokeAndStatusInAndIdxEqualTo(example, getStatusAvailableValues(), idx);

//            List<T> list = this.baseMapper.selectByExample(example);

            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(),
//                    String.format("queryByPrimaryKeyAndAddCache # 根据id=%s查询，添加到Redis缓存，查询列表list=%s",
//                            idx, list));
//
//            if (MsOnionCollectionUtils.isEmpty(list)) {
//                return null;
//            }
//
//            // 根据主键查询，就只有一条记录
//            pojo = list.get(0);

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryByPrimaryKeyAndAddCache # 根据id=%s查询，添加到Redis缓存，第一个条记录pojo=%s",
                            idx, pojo));

        } catch (Exception e) {
            throw new MsOnionException(e);
        }

        if (null == pojo) {
            return pojo;
        }

        // 原始的，Begin
        /*try {
            // 必须使用 try - catch , 因为数据库查询已经执行，下面是添加缓存，不可以影响正常业务逻辑
//            if (null == pojo) {
//                return pojo;
//            }
            // 获取Key
            String key = this.getRedisKeyGeneratorAdapter().getKey(idx);
            String json = MsOnionJsonUtils.objectToJson(pojo);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("queryByPrimaryKeyAndAddCache # 根据id=%s查询，添加到Redis缓存，第一个条记录pojo=%s，根据idx得到key=%s，pojo对应json=%s",
                            idx, pojo, key, json));
            if (StringUtils.isNotBlank(json) && StringUtils.isNoneBlank(key)) {
                // 加密
                json = encodeValue(json);
                // 2行代码实现
//                this.msOnionJedisAdapter.set(key, json);
//                // 设置周期
//                this.msOnionJedisAdapter.expire(key, redisCacheExpire);
                // 1个方法实现，保存数据到Redis
                setForRedis(parameter, key, json, redisCacheExpire);
                // 保存当前Key，为了以后更新缓存使用的
                this.addKey(key, redisCacheExpire);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("queryByPrimaryKeyAndAddCache # 根据id=%s查询，已添加到Redis缓存，第一个条记录pojo=%s，根据idx得到key=%s，pojo对应加密json=%s，缓存周期redisCacheExpire=%s",
                                idx, pojo, key, json, redisCacheExpire));
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }*/
        // 原始的，End

        // 新增，封装到另外一个方法中
        addRedisCache(apiVersion, parameter, pojo, idx, DEFAULT_QUERY_BY_IDX_RESULT, redisCacheExpire);
        return pojo;
    }

//	@Override
//	public int updateByExampleSelective(ApiVersion apiVersion, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException {
//		return this.updateByExampleSelective(apiVersion, null, record, example);
//	}

//		@Override
//	public int updateByExampleSelective(ApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example)
// throws MsOnionIllegalArgumentException, MsOnionException {
//
//		try {
//			// 检查POJO的字段的值，如果为null，都设置对应类型的默认值
//			inspectPojoFieldValue(apiVersion, record);
//
//			int result = this.baseMapper.updateByExampleSelective(record, example);
//			// 更新Redis中的列表
//			//		this.updateRedisForUpdate(record, example, result);
//
//			// 添加Redis
//			this.addRedisCache(apiVersion, record, example, result);
//			return result;
//		} catch (Exception e) {
//			throw new MsOnionException(e);
//		}
//	}

    //	@Override
    //	public int updateByExampleWithBLOBs(T record, E example) throws MsOnionIllegalArgumentException, MsOnionException {
    //		return this.baseMapper.updateByExampleWithBLOBs(record, example);
    //	}

    /**
     * 通过目标POJO的Example进行更新
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 返回目标POJO的Example进行更新
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    @Override
    @Deprecated  // 底层有BUG，暂时没有使用，需要使用要解决BUG
    public int updateByExample(MsOnionApiVersion apiVersion, T record,
                               E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.updateByExample(apiVersion, null, record, example);
    }

    /**
     * 通过目标POJO的Example进行更新
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 返回目标POJO的Example进行更新
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    @Override
    @Deprecated  // 底层有BUG，暂时没有使用，需要使用要解决BUG
    public int updateByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                               T record, E example) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, record, example);

        try {
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
            // 必须在 生成 Redis Key之前调用。
//            this.invokeAndStatusIn(example, getStatusAvailableValues());

            // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
            inspectPojoFieldValue(apiVersion, parameter, record);

            int result = this.baseMapper.updateByExample(record, example);

            // 更新Redis中的列表，必须在添加缓存之前
            this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, record, example, result);
            // 更新主键idx缓存，例如： 需要更新 MEMBER_INFO:MEMBER:1 ，就是根据主键idx更新缓存
            // 优化：新增的数据，就不放到Redis缓存中，根据主键查询、条件查询 modify by 2017-07-04 22:30
//            this.addRedisCache(apiVersion, parameter, record, null, result, redisCacheExpire);

            // 添加Redis，根据Example添加Redis的缓存
            // 优化：新增的数据，就不放到Redis缓存中，根据主键查询、条件查询 modify by 2017-07-04 22:30
//            this.addRedisCache(apiVersion, parameter, record, example, result);
            return result;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

//	@Override
//	public int updateByPrimaryKeySelective(ApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException {
//		return updateByPrimaryKeySelective(apiVersion, null, record);
//	}

//		@Override
//	public int updateByPrimaryKeySelective(ApiVersion apiVersion, MsOnionParameterAdapter parameter, T record)
// throws MsOnionIllegalArgumentException, MsOnionException {
//		try {
//			// 检查POJO的字段的值，如果为null，都设置对应类型的默认值
//			inspectPojoFieldValue(apiVersion, record);
//
//			int result = this.baseMapper.updateByPrimaryKeySelective(record);
//			// 更新Redis中的列表
//			this.syncPageAndExampleRedisCacheForUpdate(apiVersion, record, result);
//			// 添加缓存
//			this.addRedisCache(apiVersion, record, result);
//			return result;
//		} catch (Exception e) {
//			throw new MsOnionException(e);
//		}
//	}

    //	@Override
    //	public int updateByPrimaryKeyWithBLOBs(T record) throws MsOnionIllegalArgumentException, MsOnionException {
    //		return this.baseMapper.updateByPrimaryKeyWithBLOBs(record);
    //	}

    /**
     * 根据主键更新目标POJO
     *
     * @param apiVersion api版本
     * @param record     目标POJO
     * @return 返回数据库更新操作影响行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    @Override
    public int updateByPrimaryKey(MsOnionApiVersion apiVersion, T record)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return updateByPrimaryKey(apiVersion, null, record);
    }

    /**
     * 根据目标POJO的主键idx更新
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO的实例
     * @return 返回数据库更新操作影响行数，1：更新成功，0：更新失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    @Override
    public int updateByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                  T record) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, record);

        try {
            // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
            inspectPojoFieldValue(apiVersion, parameter, record);

            // 由于idxCode是后面添加的，因此编辑的时候，没有把idxCode的值，带过来，就导致编辑之后，idxCode为0
            // 底层框架，统一同步
            syncIdxCodeForUpdate(apiVersion, parameter, record);

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("updateByPrimaryKey # 更新POJO # record=%s", record));

            int result = this.baseMapper.updateByPrimaryKey(record);
            // 是否，更新成功之后，应该把之前的缓存删除 或者 更新 ？？
            // 更新Redis中的列表
            // 原先的 Begin
//            this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, record, result);
            // 添加Redis，由于当前是更新，之前已经有缓存了，只需要上面更新缓存就可以了。
//            addRedisCache(apiVersion, parameter, record, result);
            // 原先的 End

            // 换成下面，这里的 example 为 null，更新POJO，更新成功之后，只需要更新Redis缓存
            // 就可以了，不需要再添加到 Redis缓存中 ，modify by Hero @ 2017-04-14 22:59
            this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, record, null, result);
            //  上面没有更新 PrimaryKey 缓存
            // 根据主键idx同步缓存，同时也需要添加更新之后POJO实例对象的到缓存
//            this.syncByIdxRedisCacheForUpdate(apiVersion, parameter, record, null, result);
            // 这里设置 idx 为：null ，添加缓存和更新缓存一样 ！！！
            // 优化：新增的数据，就不放到Redis缓存中，根据主键查询、条件查询 modify by 2017-07-04 22:30
//            this.addRedisCache(apiVersion, parameter, record, null, result, redisCacheExpire);

            return result;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    //////////////////////////////////////  ForResult，返回MsOnionResultAdapter ， Begin ///////////////////////////////

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO的List集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, int pageNum,
                                                         int pageSize) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, pageNum, pageSize, null);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO的List集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, int pageNum, int pageSize,
                                                         String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, null, pageNum, pageSize, orderBy,
                this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO的List集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         int pageNum, int pageSize, String orderBy)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, parameter, pageNum, pageSize, orderBy, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, parameter, pageNum, pageSize, orderBy,
                isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         int pageNum, int pageSize, String orderBy,
                                                         boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 由于分页返回的结果需要包括分页信息，如果parameter为 null ，无法传递分页信息
        // 这里不需要判断类型，后续的方法统一判断
        if (null == parameter) {
            parameter = new MsOnionPagingParameter();
        }
        List<T> list = this.queryListByPage(apiVersion, parameter, pageNum, pageSize, orderBy,
                isDisabledRedisQueryCache, redisCacheExpire);

        // 分页
        return this.resultForQueryWithPaging(apiVersion, parameter, list);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, example, pageNum, pageSize, this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, example, pageNum, pageSize, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, example, pageNum, pageSize, null, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         E example, int pageNum, int pageSize, String orderBy)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, parameter, example, pageNum, pageSize, orderBy,
                this.isDisabledRedisQueryCache);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum,
                                                         int pageSize, String orderBy)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, null, example, pageNum, pageSize, orderBy);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存      @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, parameter, example, pageNum, pageSize, orderBy,
                isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, parameter, example, pageNum, pageSize,
                null, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example,
                                                         int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache,
                                                         int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryListByPageForResult(apiVersion, null, example, pageNum, pageSize,
                orderBy, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache,
                                                         int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        // 由于分页返回的结果需要包括分页信息，如果parameter为 null ，无法传递分页信息
        // 这里不需要判断类型，后续的方法统一判断
        if (null == parameter) {
            parameter = new MsOnionPagingParameter();
        }
        List<T> list = this.queryListByPage(apiVersion, parameter, example, pageNum, pageSize, orderBy,
                isDisabledRedisQueryCache, redisCacheExpire);

        // 分页
        return this.resultForQueryWithPaging(apiVersion, parameter, list);
    }

    /**
     * 根据主键idx集合进行批量删除（软删除），返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter deleteByIdxesForResult(MsOnionApiVersion apiVersion,
                                                       List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.deleteByIdxesForResult(apiVersion, null, idxes);
    }

    /**
     * 根据主键idx集合进行批量删除（软删除），返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public MsOnionResultAdapter deleteByIdxesForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                       List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException {
        int result = this.deleteByIdxes(apiVersion, parameter, idxes);
        // deleteByIdxesForResult 其实是批量更新 status 为 0 ，就是删除（软删除）
        return this.resultForUpdate(result);
    }

    /**
     * 根据目标POJO的Example来获取记录数，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter countByExampleForResult(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.countByExampleForResult(apiVersion, null, example);
    }

    /**
     * 根据目标POJO的Example来获取记录数，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter countByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                        E example) throws MsOnionIllegalArgumentException, MsOnionException {
        long result = this.countByExample(apiVersion, parameter, example);

        return this.resultForCount(result);
    }

    //////////////////////// #### 直接删除，禁止，必须使用软删除（通过更新状态），Begin ##### ///////////////////////

//    /**
//     * 根据目标POJO的Example删除，返回结果：MsOnionResultAdapter，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public MsOnionResultAdapter deleteByExampleForResult(MsOnionApiVersion apiVersion, E example)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByExampleForResult(apiVersion, null, example);
//    }

//    /**
//     * 根据目标POJO的Example删除，返回结果：MsOnionResultAdapter，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public MsOnionResultAdapter deleteByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                                         E example) throws MsOnionIllegalArgumentException, MsOnionException {
//        int result = this.deleteByExample(apiVersion, parameter, example);
//
//        return this.resultForDelete(result);
//    }

//    /**
//     * 通过主键Idx删除，返回结果：MsOnionResultAdapter
//     *
//     * @param apiVersion api版本
//     * @param idx               主键idx
//     * @return
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion,
//                                                            Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByPrimaryKeyForResult(apiVersion, null, idx);
//    }

//    /**
//     * 根据主键idx删除，返回结果：MsOnionResultAdapter
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param idx               主键idx
//     * @return 如果返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                                            Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
//        int result = this.deleteByPrimaryKey(apiVersion, parameter, idx);
//
//        return this.resultForDelete(result);
//    }

    //////////////////////// #### 直接删除，禁止，必须使用软删除（通过更新状态），End ##### ///////////////////////


    //////////////////////// #### 使用软删除（通过更新状态），Begin ##### ///////////////////////


//    /**
//     * 根据目标POJO的Example删除，返回结果：MsOnionResultAdapter，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param example    目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public MsOnionResultAdapter deleteByExampleForResult(MsOnionApiVersion apiVersion, E example)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return this.deleteByExampleForResult(apiVersion, null, example);
//    }

//    /**
//     * 根据目标POJO的Example删除，返回结果：MsOnionResultAdapter，（不允许直接删除数据）
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param example    目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 下午6:30:12
//     */
//    @Override
//    public MsOnionResultAdapter deleteByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                                         E example) throws MsOnionIllegalArgumentException, MsOnionException {
//        int result = this.deleteByExample(apiVersion, parameter, example);
//
//        return this.resultForDelete(result);
//    }

    /**
     * 通过主键Idx删除，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion,
                                                            Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.deleteByPrimaryKeyForResult(apiVersion, null, idx);
    }

    /**
     * 根据主键idx删除，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                            Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        int result = this.deleteByPrimaryKey(apiVersion, parameter, idx);

        return this.resultForDelete(result);
    }


    //////////////////////// #### 使用软删除（通过更新状态），End ##### ///////////////////////


    /**
     * 保存（新增）目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter saveForResult(MsOnionApiVersion apiVersion, T record)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.saveForResult(apiVersion, null, record);
    }

    /**
     * 保存（新增）目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter saveForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                              T record) throws MsOnionIllegalArgumentException, MsOnionException {
        int result = this.save(apiVersion, parameter, record);

        return this.resultForSave(result);
    }

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleForResult(apiVersion, null, example);
    }

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                        E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleForResult(apiVersion, parameter, example, this.isDisabledRedisQueryCache);
    }

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleForResult(apiVersion, example, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                        E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleForResult(apiVersion, parameter, example,
                isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, E example,
                                                        boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleForResult(apiVersion, null, example,
                isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                        E example, boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        List<T> list = this.queryByExample(apiVersion, parameter, example,
                isDisabledRedisQueryCache, redisCacheExpire);

        return this.resultForQuery(apiVersion, parameter, list);
    }

    /**
     * 根据主键idx查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKeyForResult(apiVersion, null, idx);
    }

    /**
     * 根据主键idx查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                           Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKeyForResult(apiVersion, parameter, idx, this.isDisabledRedisQueryCache);
    }

    /**
     * 根据主键idx进行查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKeyForResult(apiVersion, null, idx, isDisabledRedisQueryCache);
    }

    /**
     * 根据主键idx进行查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                           Long idx, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKeyForResult(apiVersion, parameter, idx, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据主键idx进行查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @param redisCacheExpire          Redis的缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache,
                                                           int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByPrimaryKeyForResult(apiVersion, null, idx, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据主键idx进行查询，返回：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis的缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                           Long idx, boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        T result = this.queryByPrimaryKey(apiVersion, parameter, idx, isDisabledRedisQueryCache, redisCacheExpire);

        return this.resultForQuery(result);
    }

    /**
     * 根据目标POJO的Example来更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter updateByExampleForResult(MsOnionApiVersion apiVersion, T record, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.updateByExampleForResult(apiVersion, null, record, example);
    }

    /**
     * 根据目标POJO的Example来更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter updateByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         T record, E example) throws MsOnionIllegalArgumentException, MsOnionException {
        int result = this.updateByExample(apiVersion, parameter, record, example);

        return this.resultForUpdate(result);
    }

    /**
     * 根据主键Idx进行更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param record     目标POJO
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter updateByPrimaryKeyForResult(MsOnionApiVersion apiVersion, T record)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.updateByPrimaryKeyForResult(apiVersion, null, record);
    }

    /**
     * 根据主键Idx进行更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午20:50:12
     */
    @Override
    public MsOnionResultAdapter updateByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                            T record) throws MsOnionIllegalArgumentException, MsOnionException {
        int result = this.updateByPrimaryKey(apiVersion, parameter, record);
//        int status = MsOnionStatusConstants.STATUS_400;
//        String msg = MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE;
//        if (result >= 1) {
//            status = MsOnionStatusConstants.STATUS_200;
//            msg = MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS;
//        }
//        return MsOnionResult.build(status, msg, result);

        return this.resultForUpdate(result);
    }

    //////////////////////////////////////  ForResult，返回MsOnionResultAdapter ， End ///////////////////////////////

    /**
     * 根据主键idx添加Redis的缓存（也是更新缓存）
     *
     * @param apiVersion       api版本
     * @param parameter        参数
     * @param pojo             目标POJO实例对象
     * @param idx              主键idx
     * @param result           操作数据库影响行数
     * @param redisCacheExpire 缓存周期
     * @return 返回添加缓存的结果
     */
    protected final String addRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T pojo, Long idx,
                                         int result, int redisCacheExpire) {
        try {
            // 必须使用 try - catch , 因为数据库查询已经执行，下面是添加缓存，不可以影响正常业务逻辑
            if (null == pojo) {
                return null;
            }
            if (null == idx) {
                idx = Long.parseLong(pojo.getId());
            }
            // 获取Key
            String key = this.getRedisKeyGeneratorAdapter().getKey(idx);
            String json = MsOnionJsonUtils.objectToJson(pojo);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("addRedisCache#根据idx=%s，添加到Redis缓存，第一个条记录pojo=%s，根据idx得到key=%s，pojo对应json=%s",
                            idx, pojo, key, json));
            if (StringUtils.isNotBlank(json) && StringUtils.isNoneBlank(key)) {
                // 加密
                json = encodeValue(json);
                // 2行代码实现
//                this.msOnionJedisAdapter.set(key, json);
//                // 设置周期
//                this.msOnionJedisAdapter.expire(key, redisCacheExpire);
                // 1个方法实现，保存数据到Redis
                setForRedis(parameter, key, json, redisCacheExpire);
                // 保存当前Key，为了以后更新缓存使用的
                this.addKey(key, redisCacheExpire);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("addRedisCache#根据idx=%s，已添加到Redis缓存，第一个条记录pojo=%s，根据idx得到key=%s，pojo对应加密json=%s，缓存周期redisCacheExpire=%s",
                                idx, pojo, key, json, redisCacheExpire));
            }
        } catch (Exception ex) {
            this.msOnionLogger.error(this.getClass().getName(), ex);
        }
        return null;
    }

    /**
     * 添加Redis缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO实例对象
     * @param result     数据库操作影响行数
     * @return 返回添加Redis缓存的结果
     * @Title: addRedisCache
     * @Description: 添加Redis缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    protected final String addRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, int result) {
        // 添加到缓存中
        try {
            if (result <= 0) {
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("addRedisCache # 添加缓存 # 没有添加缓存，直接退出，pojo=%s，result=%s",
                        record, result));
                return null;
            }
            String key = this.getRedisKeyGeneratorAdapter().getKey(record);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("addRedisCache # 添加缓存 # 根据pojo=%s，获取key=%s",
                    record, key));
            if (StringUtils.isNotBlank(key)) {
                String json = MsOnionJsonUtils.objectToJson(record);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("addRedisCache # 添加缓存 # POJO对应的json=%s，获取key=%s",
                        json, key));
                if (StringUtils.isNoneBlank(json)) {
                    json = encodeValue(json);
                    String reslut = this.msOnionJedisAdapter.set(key, json);
                    // 设置周期
                    this.msOnionJedisAdapter.expire(key, this.getRedisCacheExpire());
                    // 保存当前Key，为了以后更新缓存使用的
                    this.addKey(key, this.getRedisCacheExpire());
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(), String.format("addRedisCache # 已添加缓存 # POJO对应的加密json=%s，获取key=%s，缓存周期redisCacheExpire=%s",
                            json, key, redisCacheExpire));
                    return reslut;
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 添加Redis缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO的实例对象
     * @param example    目标POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @return 返回添加Redis缓存的结果
     * @Title: addRedisCache
     * @Description: 添加Redis缓存中
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午1:56:18
     */
    protected String addRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example, int result) {
        // 添加到缓存中
        try {
            if (result > 0) {
                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                if (StringUtils.isNotBlank(key)) {
                    String json = MsOnionJsonUtils.objectToJson(record);
                    if (StringUtils.isNoneBlank(json)) {
                        json = encodeValue(json);
                        String reslut = this.msOnionJedisAdapter.set(key, json);
                        // 设置周期
                        this.msOnionJedisAdapter.expire(key, this.getRedisCacheExpire());
                        // 保存当前Key，为了以后更新缓存使用的
                        this.addKey(key, this.getRedisCacheExpire());
                        return reslut;
                    }
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 添加Redis缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param example    目标POJO的Example实例对象
     * @param count      数据库操作影响行数
     * @return 返回添加Redis缓存的结果
     * @Title: addRedisCache
     * @Description: 添加Redis缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午1:30:38
     */
    protected String addRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, long count) {
        // 添加到缓存中
        try {
            // result 就是查询的结果，而不是修改，删除，新增的影响行数
            if (count >= 0) {
                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                if (StringUtils.isNotBlank(key)) {
                    // 不是缓存 查询条件 example
                    //					String json = MsOnionJsonUtils.objectToJson(example);
                    //					if (StringUtils.isNoneBlank(json)) {
                    // 是缓存 查询记录结果 ， data
                    String value = encodeValue(count + "");
                    String result = this.msOnionJedisAdapter.set(key, value);
                    // 设置周期
                    this.msOnionJedisAdapter.expire(key, this.getRedisCacheExpire());

                    // 保存当前Key，为了以后更新缓存使用的
                    this.addKey(key, this.getRedisCacheExpire());
                    return result;
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 添加Redis缓存
     * <p>
     * <p>Count记录数专用</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param example    目标POJO的Example实例对象
     * @param count      数据库操作影响行数
     * @return 返回添加Redis缓存的结果
     * @Title: addRedisCache
     * @Description: 添加Redis缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午1:30:38
     */
    protected String addRedisCacheForCount(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, long count) {
        // 添加到缓存中
        try {
            // result 就是查询的结果，而不是修改，删除，新增的影响行数
            if (count >= 0) {
                // 为了区分 COUNT:
//                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                String key = this.getRedisKeyGeneratorAdapter().getCountKey(example);
                if (StringUtils.isNotBlank(key)) {
                    // 不是缓存 查询条件 example
                    //					String json = MsOnionJsonUtils.objectToJson(example);
                    //					if (StringUtils.isNoneBlank(json)) {
                    // 是缓存 查询记录结果 ， data
                    String value = encodeValue(count + "");
                    String result = this.msOnionJedisAdapter.set(key, value);
                    // 设置周期
                    this.msOnionJedisAdapter.expire(key, this.getRedisCacheExpire());

                    // 保存当前Key，为了以后更新缓存使用的
                    this.addKey(key, this.getRedisCacheExpire());
                    return result;
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 删除Redis缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param idx        主键idx
     * @param result     数据库操作影响行数
     * @return 返回删除Redis的结果
     * @Title: deleteRedisCache
     * @Description: 删除Redis缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午1:06:54
     */
    private long deleteRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, int result) {
        try {
            if (result <= 0) {
                return DEFAULT_RETUAN_LONG_VALUE;
            }
            String key = this.getRedisKeyGeneratorAdapter().getKey(idx);
            if (StringUtils.isNotBlank(key)) {
                long rows = this.msOnionJedisAdapter.del(key);
                if (rows > 0) {
                    // 删除 key4Keys
                    this.deleteKey(apiVersion, key);
                }
                return rows;
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return DEFAULT_RETUAN_LONG_VALUE;
    }

    /**
     * 删除Redis中的Key
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param example    目标POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @return 返回删除Redis缓存的结果
     * @Title: deleteRedisCache
     * @Description: 删除Redis中的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午1:11:14
     */
    private long deleteRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int result) {
        try {
            if (result <= 0) {
                return DEFAULT_RETUAN_LONG_VALUE;
            }
            String key = this.getRedisKeyGeneratorAdapter().getKey(example);
            if (StringUtils.isNotBlank(key)) {
                long rows = this.msOnionJedisAdapter.del(key);
                if (rows > 0) {
                    // 删除 key4Keys
                    this.deleteKey(apiVersion, key);
                }
                return rows;
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
        return DEFAULT_RETUAN_LONG_VALUE;
    }


    /////////////////////////  更新 Redis 缓存 /////////////////////////

    /**
     * 更新操作，根据主键idx更新Redis缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO实例对象
     * @param example    目标POJO的Example实例对象
     * @param result     数据库删除操作影响行数
     */
    protected void syncByIdxRedisCacheForUpdate(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                T record, E example, int result) {
        // queryByPrimaryKey
        try {
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("更新操作，根据主键idx更新Redis缓存# record=%s", record));
            // 获取Key
            String key = this.getRedisKeyGeneratorAdapter().getKey(Long.parseLong(record.getId()));
            // 删除当前的缓存
//            this.msOnionJedisAdapter.del(key);
            // 不删了，直接更新当前key的值
//            addRedisCache(apiVersion, parameter, record, result);
            // 没有实现！！！
//            addRedisCache(apiVersion, parameter, )
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("更新操作，根据主键idx更新Redis缓存#已经更新缓存# key=%s,record=%s", key, record));
        } catch (Exception ex) {
            this.msOnionLogger.error(getClass().getName(), ex);
        }

    }


    //	/**
    //	* @Title: syncPageAndExampleRedisCacheForAdd
    //	* @Description: 同步当前POJO列表的Redis中的缓存，新增操作
    //	* @Author: HeroCao hero-cao@msyc.cc
    //	* @Date: 2017年2月17日 下午1:41:34
    //	*
    //	* @param record
    //	* @param example
    //	* @param result
    //	*/
    //	protected void syncPageAndExampleRedisCacheForAdd(T record, E example, int result) {
    //		syncPageAndExampleRedisCache(record, example, result);
    //	}

    //	/**
    //	* @Title: syncPageAndExampleRedisCacheForDelete
    //	* @Description: 同步当前POJO列表的Redis中的缓存，删除操作
    //	* @Author: HeroCao hero-cao@msyc.cc
    //	* @Date: 2017年2月17日 下午1:42:51
    //	*
    //	* @param record
    //	* @param example
    //	* @param result
    //	*/
    //	protected void syncPageAndExampleRedisCacheForDelete(T record, E example, int result) {
    //		syncPageAndExampleRedisCache(record, example, result);
    //	}

    /**
     * 同步当前POJO列表的Redis中的缓存，更新操作
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO实例对象
     * @param example    目标POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @Title: syncPageAndExampleRedisCacheForUpdate
     * @Description: 同步当前POJO列表的Redis中的缓存，更新操作
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:43:04
     */
    protected void syncPageAndExampleRedisCacheForUpdate(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         T record, E example, int result) {
        syncPageAndExampleRedisCache(apiVersion, parameter, record, example, result, false);
    }

    /**
     * 同步当前POJO列表的Redis中的缓存，增、删、改都调用这个方法同步Redis的缓存！！！
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO实例对象
     * @param example    目标POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @param isSave     是否新增（插入）
     * @Title: syncPageAndExampleRedisCache
     * @Description: 同步当前POJO列表的Redis中的缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午5:57:20
     */
    private void syncPageAndExampleRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                              T record, E example, int result, boolean isSave) {
        try {
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("syncPageAndExampleRedisCache#准备同步缓存，result=%s,record=%s", result, record));

            if (result <= 0) {
                return;
            }

            // 获取当前POJO的 keysValues
            Set<String> keysValues = getSetKeysValues();

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("syncPageAndExampleRedisCache#准备同步缓存，result=%s,keysValues=%s", result, keysValues));

            // 判断
            if (MsOnionCollectionUtils.isEmpty(keysValues)) {
                return;
            }

            // 遍历
            for (String keyValue : keysValues) {
                // 解密
                String key = this.decodeValue(keyValue);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("同步缓存，当前需要同步的key=%s", key));
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCache # 同步当前POJO=%s列表的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s",
                                record, key, getSingleExampleIdentification(), getPageIdentification()));

                if (StringUtils.isBlank(key)) {
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCache # 直接退出 # 同步当前POJO=%s列表的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s",
                                    record, key, getSingleExampleIdentification(), getPageIdentification()));
                    continue;
                }

                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCache # 准备判断分页 # 同步当前POJO=%s列表的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s",
                                record, key, getSingleExampleIdentification(), getPageIdentification()));

                // 注意， SESSION 不在 key4Keys 中 ，需要添加进来 ！！！

                // 当前所有的 Redis 都是使用 String ，除了 Set 记录 Keys之外
                // 删除所有包括，:EXAMPLE: 和 :PAGE: 的 Key的，购物车缓存
                // 由于keysValues 就是当前POJO的，因此不会把其他的POJO的 :EXAMPLE: 和 :PAGE: 缓存删除
                // query one 的 Key 也是包括： :EXAMPLE: ，不要处理 queryOne , :EXAMPLE:ONE: ，后面当做Json对象处理
                // 删除分页列表的缓存
                if (key.contains(getPageIdentification()) || (key.contains(getSingleExampleIdentification()) && !key.contains(getOneIdentification()))) {
                    // 主要包括PAGE: 都直接删除
                    String value = this.msOnionJedisAdapter.get(key);
                    this.msOnionJedisAdapter.del(key);  // 都是使用 String ，没有 hdel 获取其他，
                    // 移除当前 Set 中 key4Keys
                    this.deleteKey(apiVersion, key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCache # 同步当前POJO=%s分页列表的Redis中的缓存，已删除缓存，key=%s，删除value=%s",
                                    record, key, value));
                    continue;
                }

                // 记录数Count的缓存，例如：100、80、200，不是Json
                // 必须在 SESSION、EXAMPLE 之前，
                // 记录数Count的缓存Key，也会包括 EXAMPLE: ， 只要包括 COUNT: 都是记录数缓存，也就是缓存中数据
                // 都不是Json，都是记录数，例如：100、80、200
                if (key.contains(getCountIdentification())) {
                    // 主要包括COUNT: 都直接删除
                    String count = this.msOnionJedisAdapter.get(key);
                    this.msOnionJedisAdapter.del(key);  // 都是使用 String ，没有 hdel 获取其他，
                    // 移除当前 Set 中 key4Keys
                    this.deleteKey(apiVersion, key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCache # 同步当前POJO=%s记录数Count的Redis中的缓存，已删除缓存，key=%s，删除count=%s",
                                    record, key, count));
                    continue;
                }

                // 如果是新增，就不需要更新对应的 idx 缓存，因为是新增的
                if (isSave) {
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCache # 同步当前POJO=%s记录数Count的Redis中的缓存，删除缓存，key=%s，是新增，后续不处理，直接遍历下一个Keys，isSave=%s",
                                    record, key, isSave));
                    continue;
                }

                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCache # 准备判断SESSION、Example # 同步当前POJO=%s列表的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s,getSessionIdentification()=%s",
                                record, key, getSingleExampleIdentification(), getPageIdentification(), getSessionIdentification()));

                // 不包括 SESSION: 、EXAMPLE: 直接 continue 下一个循环
                // 这里是否有BUG： &&
//                if (!key.contains(getSessionIdentification()) && !key.contains(getSingleExampleIdentification())) {
//                    // 打印日志
//                    // MEMBER_INFO:MEMBER:18
//                    this.msOnionLogger.debug(getClass().getName(), String.format("退出了，不需要处理，有BUG吗？ key=%s", key));
//                    continue;
//                }

                // 如果有Session，也需要同时把自己当前的Session 删除
//                this.redisKeyGeneratorAdapter.getSessionIdentification();
                // 当前是，可能是更新，也可能是删除！！！ 其实，删除都是更新状态！！！
                // 问题：有户名 + 密码 登录生成的Token， 和 Email + 密码 登录生成的Token ， 手机号码 + 密码登录生成的Token
                // 都是不一样的！！！
                // 解决方案：找到和当前POJO同idx的缓存清除掉！！！ 不执行更新，因为更新可能存在BUG，直接删除缓存！！！
                // 当前POJO的缓存中，包括 SESSION: 和 EXAMPLE: ，对应id 相同都删除缓存
//                if (key.contains(getSessionIdentification()) || key.contains(getSingleExampleIdentification())) {
                // 必须注意，自定义的Redis的Key，不可以包括 SESSION: ，否则在这里也会删除缓存！！！
                String sessionJson = this.msOnionJedisAdapter.get(key);
                // 解密
                sessionJson = decodeValue(sessionJson);

                if (StringUtils.isBlank(sessionJson)) {
                    this.msOnionLogger.debug(getClass().getName(), String.format("同步缓存，遍历下一个，当前需要同步的key=%s，删除key4Keys中key, sessionJson=%s", key, sessionJson));
                    // key 对应缓存中的value 为null ，就清除 key
                    // 移除当前 Set 中 key4Keys
                    deleteKey(apiVersion, key);
                    continue;
                }

                sessionJson = sessionJson.trim();

                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCache # 准备删除当前POJO=%s的Redis中的Session、Example缓存，key=%s, sessionJson=%s",
                                record, key, sessionJson));

                // 处理 :SESSION: 、:EXAMPLE:ONE:
                // 可能是JSON数组，也可能是JSONObject
                // JSON数组是以 "[" 开头，以 "]" 结束
                // JSON对象是以 "{" 开头，以 "}" 结束
                if (sessionJson.startsWith(JSON_ARRAY_START_FLAG) && sessionJson.endsWith(JSON_ARRAY_END_FLAG)) {
                    // Json数组
                    deleteCacheFromRedisWithJsonArray(apiVersion, parameter, record, example, result, key, sessionJson);
                    continue;
                } else if (sessionJson.startsWith(JSON_OBJECT_START_FLAG) && sessionJson.endsWith(JSON_OBJECT_END_FLAG)) {
                    // Json对象
                    deleteCacheFromRedisWithJsonObject(apiVersion, parameter, record, example, result, key, sessionJson);
                    continue;
                }
                // else 如果不是 Json 不处理，处理不了，其实 Count上面已经处理了

                // 如果是纯数组，就是idx ，例如：1088
                if (MsOnionRegexUtils.isNumeric(sessionJson)) {
                    // 处理 idx
                    deleteCacheFromRedisWithIdx(apiVersion, parameter, record, example, result, key, sessionJson);
                    continue;
                }

                // [INFO] 2017-05-26 19:09:31:744 [DubboServerHandler-172.16.10.129:20813-thread-200] cc.msonion.carambola.member.service.impl.MenuServiceImpl, 同步缓存，没有处理直接到遍历下一个，End，
                // //当前需要同步的key=MENU_INFO:MENU:EXAMPLE:MENUEXAMPLE:580B48FC9E0E3144B4FB9D9C9FDCDD86
                this.msOnionLogger.debug(getClass().getName(), String.format("同步缓存，没有处理直接到遍历下一个，End，当前需要同步的key=%s", key));
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
    }

    /////////////////////////////////////// 同步缓存， Begin ////////////////////////


    /**
     * 删除Redis缓存，根据缓存中Json对象数据
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     当前POJO实例对象
     * @param example    当前POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @param key        Redis中的Key
     * @param jsonObject Json数据
     * @throws MsOnionException 异常
     */
    private void deleteCacheFromRedisWithJsonObject(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                    T record, E example, int result, String key, String jsonObject)
            throws MsOnionException {
        T t = MsOnionJsonUtils.jsonToPojo(jsonObject, getTargetClazz());
        // 这里 调用 t 或者 record 之后，后面 t.getId() 和 record.getId() 才有值
        if (null == t || null == record) {
            return;
        }
        if (StringUtils.isNotBlank(t.getId()) && t.getId().equals(record.getId())) {
            // 获取
            String value = this.msOnionJedisAdapter.get(key);
            // 删除
            this.msOnionJedisAdapter.del(key);  // 都是使用 String
            // 移除当前 Set 中 key4Keys
            this.deleteKey(apiVersion, key);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("deleteCacheFromRedisWithJsonObject # 已删除当前POJO=%s的Redis中的Session、Example缓存，已删除缓存（JSON对象），key=%s, jsonObject=%s,缓存中t=%s,删除value=%s",
                            record, key, jsonObject, t, value));
        }
    }

    /**
     * 删除Redis缓存，根据缓存中idx
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     当前POJO实例对象
     * @param example    当前POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @param key        Redis中的Key
     * @param idxStr     idx字符串
     * @throws MsOnionException 异常
     */
    private void deleteCacheFromRedisWithIdx(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                             T record, E example, int result, String key, String idxStr)
            throws MsOnionException {
        // 这里 调用 t 或者 record 之后，后面 t.getId() 和 record.getId() 才有值
        if (null == record || MsOnionStringUtils.isNotBlank(idxStr)) {
            return;
        }
        idxStr = idxStr.trim();
        if (idxStr.equals(record.getId())) {
            // 获取
            String value = this.msOnionJedisAdapter.get(key);
            // 删除
            this.msOnionJedisAdapter.del(key);  // 都是使用 String
            // 移除当前 Set 中 key4Keys
            this.deleteKey(apiVersion, key);
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("deleteCacheFromRedisWithIdx # 已删除当前POJO=%s的Redis中的Session、Example缓存，已删除缓存（JSON对象），key=%s, 缓存中idxStr=%s,删除value=%s",
                            record, key, idxStr, value));
        }
    }

    /**
     * 删除Redis缓存，根据缓存中Json数组数据
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     当前POJO实例对象
     * @param example    当前POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @param key        Redis中的Key
     * @param jsonArray  Json数据
     * @throws MsOnionException 异常
     */
    private void deleteCacheFromRedisWithJsonArray(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                   T record, E example, int result, String key, String jsonArray)
            throws MsOnionException {
        // 这里判断 目的是调用 record，后面调用 record.getId() 才有值
        if (null == record) {
            return;
        }
        List<T> list = MsOnionJsonUtils.jsonToList(jsonArray, getTargetClazz());
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return;
        }

        // 遍历list
        for (T t : list) {
            // null != t 这里判断 目的是调用 t，后面调用 t.getId() 才有值
            if (null != t && StringUtils.isNotBlank(t.getId()) && t.getId().equals(record.getId())) {
                // 获取
                String value = this.msOnionJedisAdapter.get(key);
                // 删除
                this.msOnionJedisAdapter.del(key);  // 都是使用 String
                // 移除当前 Set 中 key4Keys
                this.deleteKey(apiVersion, key);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("deleteCacheFromRedisWithJsonArray # 已删除当前POJO=%s的Redis中的Session、Example缓存，已删除缓存（JSON数组），key=%s, jsonArray=%s,删除value=%s",
                                record, key, jsonArray, value));
            }
        }
    }

    /**
     * 删除Redis缓存，根据缓存中Json对象数据
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      已经删除(软删除，也就是更新状态)的主键idx集合
     * @param result     数据库操作影响行数
     * @param key        Redis中的Key
     * @param jsonObject Json数据
     * @throws MsOnionException 异常
     */
    private void deleteCacheFromRedisWithJsonObject(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                    List<Long> idxes, int result, String key, String jsonObject)
            throws MsOnionException {
        T t = MsOnionJsonUtils.jsonToPojo(jsonObject, getTargetClazz());
        if (null == t) {
            return;
        }

        for (Long idx : idxes) {
            if (StringUtils.isNotBlank(t.getId()) && t.getId().equals(String.valueOf(idx))) {
                // 获取数据
                String value = this.msOnionJedisAdapter.get(key);
                // 删除
                this.msOnionJedisAdapter.del(key);  // 都是使用 String
                // 移除当前 Set 中 key4Keys
                this.deleteKey(apiVersion, key);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("deleteCacheFromRedisWithJsonObject # 已删除当前idx=%s的Redis中的Session、Example缓存，已删除缓存（JSON对象），key=%s, jsonObject=%s,缓存中t=%s,删除value=%s",
                                idx, key, jsonObject, t, value));
            }
        }
    }

//    /**
//     * 删除Redis缓存，根据缓存中Json数组数据
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param idxes     已经删除(软删除，也就是更新状态)的主键idx集合
//     * @param result     数据库操作影响行数
//     * @param key        Redis中的Key
//     * @param countValue  Redis中缓存的Count记录数值
//     * @throws MsOnionException 异常
//     */
//    private void deleteCacheFromRedisForCount(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                                   List<Long> idxes, int result, String key, String countValue)
//            throws MsOnionException {
////        List<T> list = MsOnionJsonUtils.jsonToList(jsonArray, getTargetClazz());
////        if (MsOnionCollectionUtils.isEmpty(list)) {
////            return;
////        }
////
////        // 遍历list
////        for (T t : list) {
//            // 遍历当前删除(软删除）的idx集合
//            for (Long idx : idxes) {
//                if (StringUtils.isNotBlank(t.getId()) && t.getId().equals(idx)) {
//                    String value = this.msOnionJedisAdapter.get(key);
//                    // 删除
//                    this.msOnionJedisAdapter.del(key);  // 都是使用 String
//                    // 移除当前 Set 中 key4Keys
//                    this.deleteKey(apiVersion, key);
//                    // 打印日志
//                    this.msOnionLogger.debug(getClass().getName(),
//                            String.format("deleteCacheFromRedisWithJsonArray #
// 已删除当前idx=%s的Redis中的Session、Example缓存，已删除缓存（JSON数组），key=%s, jsonArray=%s,删除value=%s",
//                                    idx, key, jsonArray, value));
//                }
//            }
////        }
//    }

    /**
     * 删除Redis缓存，根据缓存中Json数组数据
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      已经删除(软删除，也就是更新状态)的主键idx集合
     * @param result     数据库操作影响行数
     * @param key        Redis中的Key
     * @param jsonArray  Json数据
     * @throws MsOnionException 异常
     */
    private void deleteCacheFromRedisWithJsonArray(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                   List<Long> idxes, int result, String key, String jsonArray)
            throws MsOnionException {
        List<T> list = MsOnionJsonUtils.jsonToList(jsonArray, getTargetClazz());
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return;
        }

        // 遍历list
        for (T t : list) {
            // 遍历当前删除(软删除）的idx集合
            // 这里判断 null == t ,目的就是调用 t , 后面 t.getId() 才有值
            if (null == t) {
                continue;
            }
            for (Long idx : idxes) {
                if (StringUtils.isNotBlank(t.getId()) && t.getId().equals(String.valueOf(idx))) {
                    String value = this.msOnionJedisAdapter.get(key);
                    // 删除
                    this.msOnionJedisAdapter.del(key);  // 都是使用 String
                    // 移除当前 Set 中 key4Keys
                    this.deleteKey(apiVersion, key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("deleteCacheFromRedisWithJsonArray # 已删除当前idx=%s的Redis中的Session、Example缓存，已删除缓存（JSON数组），key=%s, jsonArray=%s,删除value=%s",
                                    idx, key, jsonArray, value));
                }
            }
        }
    }

    /**
     * 删除Redis缓存，根据缓存中idx
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      批量删除的idxes集合
     * @param result     数据库操作影响行数
     * @param key        Redis中的Key
     * @param idxStr     idx字符串
     * @throws MsOnionException 异常
     */
    private void deleteCacheFromRedisWithIdxes(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                               List<Long> idxes, int result, String key, String idxStr)
            throws MsOnionException {
        // 这里 调用 t 或者 record 之后，后面 t.getId() 和 record.getId() 才有值
//        if (null == record || MsOnionStringUtils.isNotBlank(idxStr)) {
//            return;
//        }
        idxStr = idxStr.trim();
        for (long idx : idxes) {
            if (idxStr.equals(String.valueOf(idx))) {
                // 获取
                String value = this.msOnionJedisAdapter.get(key);
                // 删除
                this.msOnionJedisAdapter.del(key);  // 都是使用 String
                // 移除当前 Set 中 key4Keys
                this.deleteKey(apiVersion, key);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("deleteCacheFromRedisWithIdxes # 已删除当前idx=%s的Redis中的Session、Example缓存，已删除缓存（JSON对象），key=%s, 缓存中idxStr=%s,删除value=%s",
                                idx, key, idxStr, value));
            }
        }
    }


    /**
     * 同步当前POJO列表的Redis中的缓存，删除操作，删除都是软删除，也就是更新状态，
     * 如果是直接删除，不可以调用这个方法！！！
     * <p>删除idx列表专用（软删除）</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param idxes      主键idx集合
     * @param result     数据库操作结果
     * @Title: syncPageAndExampleRedisCacheForDelete
     * @Description: 同步当前POJO列表的Redis中的缓存，删除操作
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:44:35
     */
    protected void syncPageAndExampleRedisCacheForDeleteIdxes(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                              List<Long> idxes, int result) {
//            for (long idx : idxes) {
//                // 遍历删除缓存
////                this.deleteRedisCache(apiVersion, parameter, idx, result);
        try {
            if (result <= 0) {
                return;
            }

            // 获取当前POJO的 keysValues
            Set<String> keysValues = getSetKeysValues();

            // 判断
            if (MsOnionCollectionUtils.isEmpty(keysValues)) {
                return;
            }

            // 主键idx的key
            String idxKey = null;
            // 遍历
            for (String keyValue : keysValues) {
                // 解密
                String key = this.decodeValue(keyValue);
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCacheForDeleteIdxes # 同步当前删除的idxes=%s集合的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s",
                                idxes, key, getSingleExampleIdentification(), getPageIdentification()));

                if (StringUtils.isBlank(key)) {
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCacheForDeleteIdxes # 直接退出 # 同步当前删除的idxes=%s集合的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s",
                                    idxes, key, getSingleExampleIdentification(), getPageIdentification()));
                    continue;
                }

                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCacheForDeleteIdxes # 准备判断分页 # 同步当前删除的idxes=%s集合的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s",
                                idxes, key, getSingleExampleIdentification(), getPageIdentification()));

                // 当前所有的 Redis 都是使用 String ，除了 Set 记录 Keys之外
                // 删除所有包括，:EXAMPLE: 和 :PAGE: 的 Key的，购物车缓存
                // 由于keysValues 就是当前POJO的，因此不会把其他的POJO的 :EXAMPLE: 和 :PAGE: 缓存删除
                // 删除分页列表的缓存
                if (key.contains(getPageIdentification())) {
                    this.msOnionJedisAdapter.del(key);  // 都是使用 String ，没有 hdel 获取其他，
                    // 移除当前 Set 中 key4Keys
                    this.deleteKey(apiVersion, key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCacheForDeleteIdxes # 同步当前删除的idxes=%s集合的Redis中的缓存，已删除缓存，key=%s",
                                    idxes, key));
                    continue;
                }

                // 记录数Count的缓存，例如：100、80、200，不是Json
                // 必须在 SESSION、EXAMPLE 之前，
                // 记录数Count的缓存Key，也会包括 EXAMPLE: ， 只要包括 COUNT: 都是记录数缓存，也就是缓存中数据
                // 都不是Json，都是记录数，例如：100、80、200
                if (key.contains(getCountIdentification())) {
                    // 主要包括COUNT: 都直接删除 , 尽管是同时删除多个idx，不需要遍历idxes ，直接删除 key
                    // 因为和PAGE: 一样，都是直接删除
                    String count = this.msOnionJedisAdapter.get(key);
                    this.msOnionJedisAdapter.del(key);  // 都是使用 String ，没有 hdel 获取其他，
                    // 移除当前 Set 中 key4Keys
                    this.deleteKey(apiVersion, key);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCache # 删除idxes=%s记录数Count的Redis中的缓存，已删除缓存，key=%s，删除count=%s",
                                    idxes, key, count));
                    continue;
                }

                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCacheForDeleteIdxes # 准备判断SESSION、Example # 同步当前删除的idxes=%s集合的Redis中的缓存，也就是删除所有列表、分页缓存，key=%s, getSingleExampleIdentification=%s,getPageIdentification()=%s,getSessionIdentification()=%s",
                                idxes, key, getSingleExampleIdentification(), getPageIdentification(), getSessionIdentification()));

                // 不包括 SESSION: 、EXAMPLE: 直接 continue 下一个循环
//                if (!key.contains(getSessionIdentification()) && !key.contains(getSingleExampleIdentification())) {
//                    continue;
//                }

                // 如果有Session，也需要同时把自己当前的Session 删除
                // 当前是，可能是更新，也可能是删除！！！ 其实，删除都是更新状态！！！
                // 问题：有户名 + 密码 登录生成的Token， 和 Email + 密码 登录生成的Token ， 手机号码 + 密码登录生成的Token
                // 都是不一样的！！！
                // 解决方案：找到和当前POJO同idx的缓存清除掉！！！ 不执行更新，因为更新可能存在BUG，直接删除缓存！！！
                // 当前POJO的缓存中，包括 SESSION: 和 EXAMPLE: ，对应id 相同都删除缓存
//                if (key.contains(getSessionIdentification()) || key.contains(getSingleExampleIdentification())) {
                // 必须注意，自定义的Redis的Key，不可以包括 SESSION: ，否则在这里也会删除缓存！！！
                String sessionJson = this.msOnionJedisAdapter.get(key);
                // 解密
                sessionJson = decodeValue(sessionJson);

                if (StringUtils.isBlank(sessionJson)) {
                    // 有key 没有值，说明 key已经过期了，但是，还保存在 keys中
                    deleteKey(apiVersion, key);
                    continue;
                }

                sessionJson = sessionJson.trim();

                // 打印日志
                this.msOnionLogger.debug(getClass().getName(),
                        String.format("syncPageAndExampleRedisCacheForDeleteIdxes # 准备删除当前删除的idxes=%s集合的Redis中的缓存，key=%s, sessionJson=%s",
                                idxes, key, sessionJson));

                // 可能是JSON数组，也可能是JSONObject
                // JSON数组是以 "[" 开头，以 "]" 结束
                // JSON对象是以 "{" 开头，以 "}" 结束
                if (sessionJson.startsWith(JSON_ARRAY_START_FLAG) && sessionJson.endsWith(JSON_ARRAY_END_FLAG)) {
                    // Json数组
                    deleteCacheFromRedisWithJsonArray(apiVersion, parameter, idxes, result, key, sessionJson);
                    continue;
                } else if (sessionJson.startsWith(JSON_OBJECT_START_FLAG) && sessionJson.endsWith(JSON_OBJECT_END_FLAG)) {
                    // Json对象
                    deleteCacheFromRedisWithJsonObject(apiVersion, parameter, idxes, result, key, sessionJson);
                    continue;
                } else {
                    // 不是Json，也就是 COUNT的结果，例如： 20 ， 100

                    // 如果是纯数组，就是idx ，例如：1088
                    if (MsOnionRegexUtils.isNumeric(sessionJson)) {
                        // 处理 idx
                        deleteCacheFromRedisWithIdxes(apiVersion, parameter, idxes, result, key, sessionJson);
                        continue;
                    }

                    // BUTTON_INFO:BUTTON:COUNT:EXAMPLE:BUTTONEXAMPLE:845EC8755F6E020AA5876D7A284847EC ===> 3 或者 100
                    // TODO: 把所有的COUNT 都删除吗？？？
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("syncPageAndExampleRedisCacheForDeleteIdxes # COUNT的结果，例如:20 #准备删除当前删除的idxes=%s集合的Redis中的缓存，key=%s, sessionJson=%s",
                                    idxes, key, sessionJson));

                    // 根据主键idx删除Redis中的缓存，其实不存在删除，因为没有删除操作，都是更新状态而已，
                    // 只不过这里为了方便，直接删除key的缓存
//                    idxKey = this.getRedisKeyGeneratorAdapter().getKey()
//                    deleteRedisKeyByIdx(apiVersion, parameter, sessionJson);
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
    }

    /**
     * 根据主键idx删除缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param value      主键idx的值，字符串类型的
     * @return 返回结果
     */
    private String deleteRedisKeyByIdx(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, String value) {
        try {
            // 不是Json，也就是 COUNT的结果，例如： 20 ， 100
            // 根据主键idx删除Redis中的缓存，其实不存在删除，因为没有删除操作，都是更新状态而已，
            // 只不过这里为了方便，因为不获取POJO了，就不采取更新key的缓存方式 ，而是直接删除key的缓存
            if (MsOnionStringUtils.isBlank(value)) {
                return null;
            }
            value = value.trim();
            if (!NumberUtils.isDigits(value)) {
                return null;
            }
            long idx = Long.parseLong(value);
//            String key = this.getRedisKeyGeneratorAdapter().getKey(idx);
            deleteRedisCache(apiVersion, parameter, idx, DEFAULT_UPDATE_FOR_DELETE_RESULT);
        } catch (Exception ex) {
            this.msOnionLogger.error(getClass().getName(), String.format("deleteRedisKeyByIdx # 删除主键缓存报错 # value（idx）=%s", value));
        }
        return null;
    }

    /**
     * 同步当前POJO列表的Redis中的缓存，删除操作
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param example    目标POJO的Example实例对象
     * @param result     数据库操作影响行数
     * @Title: syncPageAndExampleRedisCacheForDelete
     * @Description: 同步当前POJO列表的Redis中的缓存，删除操作
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:45:20
     */
    protected void syncPageAndExampleRedisCacheForDelete(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         E example, int result) {
        this.syncPageAndExampleRedisCache(apiVersion, parameter, null, example, result, false);
    }

    /**
     * 同步当前POJO列表的Redis中的缓存，新增操作
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO实例对象
     * @param result     数据库操作影响行数
     * @Title: syncPageAndExampleRedisCacheForAdd
     * @Description: 同步当前POJO列表的Redis中的缓存，新增操作
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:45:52
     */
    protected void syncPageAndExampleRedisCacheForAdd(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                      T record, int result) {
        this.syncPageAndExampleRedisCache(apiVersion, parameter, record, null, result, true);
    }

    /**
     * 同步当前POJO列表的Redis中的缓存，更新操作
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO实例对象
     * @param result     数据库操作影响行数
     * @Title: syncPageAndExampleRedisCacheForUpdate
     * @Description: 同步当前POJO列表的Redis中的缓存，更新操作
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:46:16
     */
    protected void syncPageAndExampleRedisCacheForUpdate(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                         T record, int result) {
        this.doSyncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, record, null, result);
    }

    /**
     * 购物车是，只是当前用户关联，因此可以根据当前用户，更新 Redis中数据，同时 购物车列表，
     * 没有分页，都是一次加载70条数据（京东没有登录的购物车数据最多70条）
     * ，因此可以根据当前用户更新Redis中数据，但是，如果不是只是当前用户关联的，也是这样更新是有问题的
     * ，例如，商品是全局的，不只是一个用户的，所有用户都可能有关联，
     * 因此，新增商品需要删除所有Redis中列表相关商品，注意，分页有的可能是1-30 ， 1-50 ， 等等不同
     * ，必须删除
     * 修改商品，可以查询所有Redis中，找到当前商品（商品Idx相同），更新当前商品
     * 删除商品：涉及到分页，1-20 ，1-30 ， 1-50  ， 50-100  ，删除之后，需要后面数据替换，比较麻烦
     * 因此，删除商品和 新增商品，都是一样，必须全部删除所有商品列表。
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO实例对象
     * @param example    目标POJO的Example实例对象
     * @param result     数据库操作影响行数
     */
    private void doSyncPageAndExampleRedisCacheForUpdate(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example, int result) {

        try {
            this.syncPageAndExampleListRedisCache(apiVersion, parameter, record, result, new MsOnionSyncRedisCacheListener<T>() {

                @Override
                public boolean doSync(MsOnionParameterAdapter parameterAdapter, List<T> list, T record) {
                    try {
                        return doSyncRedisCacheForUpdate(apiVersion, parameterAdapter, list, record);
                    } catch (Exception e) {
                        msOnionLogger.error(MsOnionService.class.getName(), e);
                        return false;
                    }
                }

                /**
                 * 执行同步
                 * @param parameterAdapter        参数适配器
                 * @param list             目标POJO的集合
                 * @param key              Redis中缓存的Key
                 * @param originalJson     原先Json
                 * @param redisCacheExpire Redis缓存周期
                 * @param isCanExecute 是否可以执行
                 * @Title: doExecute
                 * @Description: 执行同步
                 * @Author: HeroCao hero-cao@msyc.cc
                 * @Date: 2017年2月17日 下午4:07:16
                 */
                @Override
                public void doExecute(MsOnionParameterAdapter parameterAdapter, List<T> list, String key, String originalJson, int redisCacheExpire,
                                      boolean isCanExecute) {
                    try {
                        // 已经更新了，必须同步 json
                        String json = MsOnionJsonUtils.objectToJson(list);
                        // 加密
                        json = encodeValue(json);
                        // 缓存Redis
                        msOnionJedisAdapter.set(key, json);
                        // 设置周期，更新当前POJO的记录，不需要更新缓存周期！！！重新登录、查询才需要！！！
//                        msOnionJedisAdapter.expire(key, redisCacheExpire);
                    } catch (Exception ex) {
                        msOnionLogger.error(MsOnionService.class.getName(), ex);
                    }
                }
            });
        } catch (MsOnionException e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
    }

    /**
     * 同步当前POJO实例对象，在Redis中的缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param list       目标POJO的List集合
     * @param record     目标POJO实例对象
     * @return 返回是否同步成功，true：成功，false：失败
     * @throws MsOnionException 异常
     * @Title: doSyncRedisCacheForUpdate
     * @Description: 同步当前POJO实例对象，在Redis中的缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:56:51
     */
    private boolean doSyncRedisCacheForUpdate(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                              List<T> list, T record) throws MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(list) || null == record) {
            return false;
        }
        // 遍历
        for (T t : list) {
            // 使用 父类 String id
            if (StringUtils.isNotBlank(record.getId()) && StringUtils.isNumeric(record.getId())
                    && record.getId().equals(t.getId())) {

                try {
                    // TODO: 解决高并发问题，需要提供 父类 version ， 使用 _version_ , Solr
                    // t.get_version_() == record.get_version_();
                    //				if (t.get_Version_() == record.get_Version_().longValue()) {
                    //					// 拷贝属性，
                    //					// 更新，拷贝属性
                    //					BeanUtils.copyProperties(record, t);
                    //					return true;
                    //				}

                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(),
                            String.format("doSyncRedisCacheForUpdate # 同步当前POJO实例对象，在Redis中的缓存，属性拷贝# 原先record=%s，拷贝到t=%s", record, t));

                    // 更新，拷贝属性 , 必须考虑 高并发了，电商项目必须加上 ！！！
                    // 将 record的属性拷贝到 t
                    BeanUtils.copyProperties(t, record);
                    return true;
                } catch (Exception ex) {
                    throw new MsOnionException(ex);
                }
            }
        }
        return false;
    }

    /**
     * 同步目标POJO的列表的Redis缓存
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO实例对象
     * @param result     数据库操作影响行数
     * @param listener   监听器
     * @throws MsOnionException 异常
     * @Title: syncPageAndExampleListRedisCache
     * @Description: 同步目标POJO的列表的Redis缓存
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午3:53:43
     */
    protected void syncPageAndExampleListRedisCache(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                    T record, int result, MsOnionSyncRedisCacheListener<T> listener)
            throws MsOnionException {
        if (result <= 0) {
            return;
        }
        if (null == record) {
            return;
        }
        Set<String> keysValues = getSetKeysValues();
        // 判断
        if (MsOnionCollectionUtils.isEmpty(keysValues)) {
            return;
        }
        // 遍历
        for (String keyValue : keysValues) {
            // 解密
            String key = this.decodeValue(keyValue);
            // 当前所有的 Redis 都是使用 String ，除了 Set 记录 Keys之外

            // 删除所有包括，:EXAMPLE: 和 :PAGE: 的 Key的，购物车缓存
            if (StringUtils.isNotBlank(key) && (key.contains(getSingleExampleIdentification())
                    || key.contains(getPageIdentification()))) {
                //				this.ttJedis.del(key);  // 都是使用 String ，没有 hdel 获取其他，

                // 移除当前 Set 中 key4Keys
                //				this.deleteKey(key);

                // 一一更新当前POJO列表，在Redis缓存数据
                try {
                    // 获取数据
                    String json = this.msOnionJedisAdapter.get(key);
                    // 解密
                    json = decodeValue(json);
                    // 判断
                    if (StringUtils.isBlank(json)) {
                        continue;  // 下一个
                    }
                    // 解析Json
                    List<T> list = MsOnionJsonUtils.jsonToList(json, getTargetClazz());

                    if (MsOnionCollectionUtils.isNotEmpty(list)) {
                        boolean isDone = listener.doSync(parameter, list, record);
                        listener.doExecute(parameter, list, key, json, this.getRedisCacheExpire(), isDone);
                    }

                } catch (Exception e) {
                    this.msOnionLogger.error(this.getClass().getName(), e);
                }
            }
        }
    }

    /**
     * @param apiVersion api版本
     * @param parameter  参数
     * @param pojo       目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 主键idx非法异常
     * @throws MsOnionException                异常
     * @Title: inspectPojoFieldValue
     * @Description: 检查Pojo字段值，如果为null值，都设置为对应类型的默认值，Long、int、Short类型的默认值：0，String类型的默认值：空字符串（""）, Date类型的默认值为当前时间，过滤SQL注入
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月25日 下午5:06:05
     */
    protected void inspectPojoFieldValue(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, MsOnionBasePojoAdapter pojo)
            throws MsOnionIllegalArgumentException, MsOnionException {

        /*
            getDeclaredField是可以获取一个类的所有字段.
            getField只能获取类的public 字段.
         */
        try {
            Field idxField = pojo.getClass().getDeclaredField("idx");
            if (null == idxField) {
                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL, MsOnionStatusConstants.STATUS_400);
            }
            // 允许访问
            idxField.setAccessible(true);
            Object idxObj = idxField.get(pojo);
            if (null == idxObj || !StringUtils.isNoneBlank(idxObj + "") || !StringUtils.isNumeric(idxObj + "")) {
                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL, MsOnionStatusConstants.STATUS_400);
            }
            long idx = Long.parseLong(idxObj + "");
            if (idx <= 0 || idx >= Long.MAX_VALUE) {
                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL, MsOnionStatusConstants.STATUS_400);
            }
        } catch (Exception e) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL, e, MsOnionStatusConstants.STATUS_400);
        }

        // 新增一个 ext （扩展）字段
        //		Field field = new Field;
        //		MsOnionReflectionUtils.getField(field, target);
        // Zookeeper 的 分布式原子 Value 来记录，后面需要再删除 ！！！
        // 不可以使用 static ，因为static 方法，无法注入 分布式原子 Value

        MsOnionReflectionUtils.doWithFields(pojo.getClass(), new ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                try {
                    // 允许访问
                    field.setAccessible(true);
                    String name = field.getName();
                    Object value = field.get(pojo);
                    String typeName = field.getType().getName();
                    // equals , equalsIgnoreCase
                    if (MsOnionTypeConstants.TYPE_REFERENCE_STRING_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_REFERENCE_CHARSEQUENCE_NAME.equals(typeName)) {  // 字符串类型 String

                        // 是null 或者 "" , " " ， "   "
                        if (null == value || StringUtils.isBlank(String.valueOf(value))) {
                            // 设置默认空字符串
                            field.set(pojo, "");
                            // 获取值
                            Object value2 = field.get(pojo);
                        } else if (StringUtils.isNotBlank(String.valueOf(value))) {
                            // 过滤SQL注入关键词
                            String newValue = MsOnionSqlValidateUtils.sqlFilter(String.valueOf(value));

//						// 去掉左右两边空格
//						newValue = newValue.trim();
//
//						// 过滤SQL注入关键词之后，设置值给Field
//						field.set(pojo, newValue);

                            // 去掉左右两边空格
                            trimForString(field, pojo, newValue);
                        } else {
                            // 去掉左右两边空格
//						String newValue = value + "";
//						// 去掉左右两边空格
//						newValue = newValue.trim();
//						// 过滤SQL注入关键词之后，设置值给Field
//						field.set(pojo, newValue);

                            trimForString(field, pojo, String.valueOf(value));
                        }
                    } else if (MsOnionTypeConstants.TYPE_REFERENCE_LONG_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_LONG_NAME.equals(typeName)) {  // Long类型

                        // 报错的，因为 field.getLong(member) 返回值 是 long , 而不是 Long
                        //					this.msOnionLogger.debug(getClass(), "doWithFields # 字段, Long，field.get(member)=" + field.getLong(member));

                        if (null == value) {
                            // 设置默认值
                            // 必须加上 0L ,否则报错
                            field.set(pojo, 0L);  // 0 , 0L

                            // 获取值
                            Object value2 = field.get(pojo);
                        }
                    } else if (MsOnionTypeConstants.TYPE_REFERENCE_DATE_NAME.equals(typeName)) {  // 日期类型 Date
                        if (null == value) {
                            // 设置日期为当前时间
                            // Docker容器中，存在时区问题
//                            field.set(pojo, new Date());
                            // 解决Docker容器中，存在时区问题，获取当前东8区的时区
                            field.set(pojo, MsOnionDateUtils.getNowDate());
                        }
                    } else if (MsOnionTypeConstants.TYPE_REFERENCE_INTEGER_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_INT_NAME.equals(typeName)) {  // int类型
                        if (null == value) {
                            // 设置默认值为0
                            field.set(pojo, 0);
                        }
                    } else if (MsOnionTypeConstants.TYPE_SHORT_NAME.equals(typeName)
                            || MsOnionTypeConstants.TYPE_REFERENCE_SHORT_NAME.equals(typeName)) {  // Short类型
                        if (null == value) {
                            // 设置默认值为0
                            // 必须类型转换为： (short)0
                            field.set(pojo, (short) 0);
                        }
                    }
                } catch (Exception ex) {
                    getMsOnionLogger().error(MsOnionException.class.getName(), ex);
                }
            }
        });
    }

    /////////////////////////////////////// 检查参数， Begin ///////////////////////////////////

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: inspectParameters
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午6:10:12
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, int pageNum, int pageSize)
            throws MsOnionIllegalArgumentException, MsOnionException {

        this.inspectParameters(apiVersion);

        // 容错：当页码为null、<= 0 、大于最大值，都默认为1页
        if (pageNum <= 0 || pageNum > MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MAX_VALUE) {
            pageNum = 1;
        }

        // 容错：当每页容量为null、小于最小值 、大于最大值，都默认为最小值
        if (pageSize < MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE
                || pageSize > MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE) {
            pageSize = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;
        }
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param idxes      主键idx集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: inspectParameters
     * @Description: 检查参数
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午6:18:09
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        inspectParameters(apiVersion);
        if (MsOnionCollectionUtils.isEmpty(idxes)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IDXS_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 字符串去掉两边空格
     *
     * @param field 字段
     * @param pojo  目标POJO实例对象
     * @param value 值
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void trimForString(Field field, MsOnionBasePojoAdapter pojo, String value) throws MsOnionException {
        try {
            String newValue = value.trim();

            // 去掉左右两边空格之后，设置值给Field
            field.set(pojo, newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException {
        if (null == apiVersion) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_APIVERSION_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }

        if (StringUtils.isBlank(apiVersion.getRequestApiVersion())) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_APIVERSION_REQUEST_APIVERSION_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数
     *
     * @param parameter 参数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameter(MsOnionParameterAdapter parameter) throws MsOnionIllegalArgumentException {
        if (null == parameter) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_APIVERSION_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数
     *
     * @param idx 主键idx
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameter(Long idx) throws MsOnionIllegalArgumentException {
        if (null == idx || idx <= 0 || idx >= Long.MAX_VALUE) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数
     *
     * @param status 状态，0：删除、1：正常、激活，2：下架、未激活
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameter(short status) throws MsOnionIllegalArgumentException {
        if (status < MsOnionStatusConstants.SQL_STATUS_MIN_VALUE || status > MsOnionStatusConstants.SQL_STATUS_MAX_VALUE) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_SQL_STATUS_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数
     *
     * @param t 目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameter(T t) throws MsOnionIllegalArgumentException {
        if (null == t || StringUtils.isBlank(t.getId())) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数
     *
     * @param example 目标POJO的Example实例对象
     * @throws MsOnionIllegalArgumentException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月17日 下午6:18:09
     */
    protected void inspectParameter(E example) throws MsOnionIllegalArgumentException {
        if (null == example) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_EXAMPLE_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException {
        inspectParameters(apiVersion);
        inspectParameter(idx);
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @param status     状态
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, Long idx, short status) throws MsOnionIllegalArgumentException {
        inspectParameters(apiVersion);
        inspectParameter(idx);
        inspectParameter(status);
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param t          目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, T t) throws MsOnionIllegalArgumentException {
        inspectParameters(apiVersion);
        inspectParameter(t);
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param t          目标POJO实例对象
     * @param example    目标POJOde的Example实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月17日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, T t, E example) throws MsOnionIllegalArgumentException {
        inspectParameters(apiVersion, t);
        inspectParameter(example);
    }

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameters(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException {
        inspectParameters(apiVersion);
        inspectParameter(example);
    }

    /**
     * 检查分页的每页数量，如果非法使用默认值
     *
     * @param pageSize 每页数量
     * @return 返回正确每页数量
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected int inspectParameterForPageSize(int pageSize) {
        // 如果非法，设置默认的
        if (pageSize < MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;
        }
        if (pageSize > MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE;
        }
        return pageSize;
    }

    /**
     * 检查分页的页码，如果方法使用默认值
     *
     * @param totalCounts 数据库中符合当前条件的总记录数
     * @param pageNum     页码
     * @param pageSize    每页数量
     * @return 返回正确的页码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected int inspectParameterForPageNum(long totalCounts, int pageNum, int pageSize) {
        // 如果非法，设置默认的
        if (pageNum < MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE) {
            return MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
        }

        int totalPageNum = MsOnionPagingUtils.computeTotalPageNum(totalCounts, pageSize);

        if (pageNum > totalPageNum) {
            return totalPageNum;
        }
        return pageNum;
    }

    /**
     * 检查分页参数
     *
     * @param parameter 分页参数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameterForPaging(MsOnionParameterAdapter parameter)
            throws MsOnionIllegalArgumentException {
        if (null != parameter && !(parameter instanceof MsOnionPagingParameter)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_PAGING_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查缓存周期参数： redisCacheExpire
     *
     * @param redisCacheExpire Redis缓存周期
     * @return 返回正确的Redis缓存周期
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected int inspectParameterForRedisCacheExpire(int redisCacheExpire) {
        // 如果非法，设置默认的
        if (redisCacheExpire < MsOnionConstants.REDIS_CACHE_EXPIRE_MIN) {
            return MsOnionConstants.REDIS_CACHE_EXPIRE_MIN;
        }
        if (redisCacheExpire > MsOnionConstants.REDIS_CACHE_EXPIRE_MAX) {
            return MsOnionConstants.REDIS_CACHE_EXPIRE_MAX;
        }
        return redisCacheExpire;
    }

    /////////////////////////////////////// 检查参数， End ///////////////////////////////////

    ///////////////////////////////////////  通过properties 配置， Begin /////////////////////////

//    /**
//     * 获取数据库表中 status 可用的值,0: 删除； 1：可用、激活 ， 2：未激活（用户、成员）
//     *
//     * @return 返回数据库表中 status 可用的值集合
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月17日 下午6:18:09
//     */
//    protected List<Short> getStatusAvailableValues() throws MsOnionIllegalArgumentException, MsOnionException {
//        // 框架底层不对 status 进行限制，因为很难控制各种需求 @ 2017-06-27
////        if (StringUtils.isBlank(this.sqlColumnStatusAvailableValues)) {
////            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_SQL_COLUMN_STATUS_AVAILABLE_VALUES_ILLEGAL,
//// MsOnionStatusConstants.STATUS_400);
////        }
////
////        if (MsOnionCollectionUtils.isNotEmpty(this.statusAvailableValues)) {
////            return this.statusAvailableValues;
////        }
////
////        try {
////            // 将 1,2,3 分割
////            String values = this.sqlColumnStatusAvailableValues.trim();
////            String[] data = values.split(",");
////            this.statusAvailableValues = new ArrayList<Short>();
////
////            // 遍历
////            for (String value : data) {
////                if (StringUtils.isNotBlank(value)) {
////                    Short status = Short.parseShort(value.trim());
////                    this.statusAvailableValues.add(status);
////                }
////            }
////        } catch (Exception e) {
////            // 出现异常，清空
////            this.statusAvailableValues = null;
////            throw new MsOnionException(e);
////        }
////        return this.statusAvailableValues;
//
//        // 框架底层不对 status 进行限制，因为很难控制各种需求 @ 2017-06-27
//        return null;
//    }

    /**
     * 获取数据库表中密码列的名称集合，用于分页查询的时候，把密码设置为null，安全考虑
     *
     * @return 返回数据库表中密码列的名称集合，用于分页查询的时候，把密码设置为nul的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月17日 下午6:18:09
     */
    protected List<String> getPasswordColumnNames() throws MsOnionIllegalArgumentException, MsOnionException {

        if (StringUtils.isBlank(this.sqlColumnStatusAvailableValues)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_SQL_COLUMN_PASSWORD_NAMES_ILLEGAL, MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionCollectionUtils.isNotEmpty(this.passwordColumnNames)) {
            return this.passwordColumnNames;
        }

        try {
            // 将 password,pwd 分割
            String namesStr = this.sqlColumnPasswordNames.trim();
            String[] names = namesStr.split(",");
            this.passwordColumnNames = new ArrayList<String>();
//            // 遍历
            for (String name : names) {
                if (StringUtils.isNotBlank(name)) {
                    this.passwordColumnNames.add(name.trim());
                }
            }
        } catch (Exception e) {
            // 出现异常，清空
            this.passwordColumnNames = null;
            throw new MsOnionException(e);
        }

        return this.passwordColumnNames;
    }

    /**
     * 分页查询的时候，查询列表需要设置密码为null的POJO
     *
     * @return 返回需要处理密码的POJO名称的List集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月17日 下午6:18:09
     */
    protected List<String> getPojoSecurityForPasswordNames() throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(this.pojoNeedToSetNullForPasswordNames)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_POJO_SECURITY_FOR_PASSWORD_NAMES_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionCollectionUtils.isNotEmpty(this.pojoSecurityForPasswordNames)) {
            return this.pojoSecurityForPasswordNames;
        }

        try {
            // 将 password,pwd 分割
            String namesStr = this.pojoNeedToSetNullForPasswordNames.trim();
            String[] names = namesStr.split(",");
            this.pojoSecurityForPasswordNames = new ArrayList<String>();
//            // 遍历
            for (String name : names) {
                if (StringUtils.isNotBlank(name)) {
                    this.pojoSecurityForPasswordNames.add(name.trim());
                }
            }
        } catch (Exception e) {
            // 出现异常，清空
            this.pojoSecurityForPasswordNames = null;
            throw new MsOnionException(e);
        }

        return this.pojoSecurityForPasswordNames;
    }

    ///////////////////////////////////////  通过properties 配置， End /////////////////////////

    /**
     * 获取到目标POJO的Example对应的Criteria实例对象
     *
     * @return 返回目标POJO的Example对应的Criteria实例对象
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected Object getCriteria() throws MsOnionException {
        try {
            E example = getExample();
            Method declaredMethod = example.getClass().getDeclaredMethod("createCriteria", null);
            return declaredMethod.invoke(example, null);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取到目标POJO的Example对应的Criteria实例对象
     *
     * @param example 获取到目标POJO的Example的实例对象
     * @return 返回Example的Criteria实例对象
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected Object getCriteria(E example) throws MsOnionException {
        try {
            Method declaredMethod = example.getClass().getDeclaredMethod("createCriteria", null);
            return declaredMethod.invoke(example, null);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取到目标POJO的Example的实例对象
     *
     * @return 返回目标POJO的Example的实例对象
     * @throws IllegalAccessException 非法参数异常
     * @throws MsOnionException       异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected E getExample() throws IllegalAccessException, MsOnionException {
        try {
            return getExampleClazz().newInstance();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////////////////////// 使用方式调用方法，Begin ///////////////////////////////////

    /**
     * 使用反射方式调用 andIdxIn
     *
     * @param example 目标POJO的Example实例对象
     * @param idxes   主键idx集合
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void invokeAndIdxIn(E example, List<Long> idxes) throws MsOnionException {
        try {
            Object criteria = getCriteria(example);
            Method andIdxIn = criteria.getClass().getDeclaredMethod("andIdxIn", List.class);
            // 排序 idxes , 按照 从小大的顺序
            Collections.sort(idxes);
            // 调用 andIdxIn 方法
            andIdxIn.invoke(criteria, idxes);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 使用反射调用andIdxEqualTo
     *
     * @param example 目标POJO的Example实例对象
     * @param idx     主键idx
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void invokeAndIdxEqualTo(E example, Long idx) throws MsOnionException {
        try {
            Object criteria = getCriteria(example);
            Method andIdxEqualTo = criteria.getClass().getDeclaredMethod("andIdxEqualTo", Long.class);
            // 调用 andIdxEqualTo 方法
            andIdxEqualTo.invoke(criteria, idx);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

//    /**
//     * 使用反射方式调用 andStatusIn
//     *
//     * @param example  目标POJO的Example实例对象
//     * @param statuses 状态的集合
//     * @throws MsOnionException 异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    protected void invokeAndStatusIn(E example, List<Short> statuses) throws MsOnionException {
//        // 框架底层不对 status 进行限制，因为很难控制各种需求 @ 2017-06-27
////        try {
////            Object criteria = getCriteria(example);
////            Method andStatusIn = criteria.getClass().getDeclaredMethod("andStatusIn", List.class);
////            // 排序 statuses , 按照 从小大的顺序
////            Collections.sort(statuses);
////            andStatusIn.invoke(criteria, statuses);
////        } catch (Exception ex) {
////            throw new MsOnionException(ex);
////        }
//    }

//    /**
//     * 使用反射调用 andStatusIn 和 andIdxEqualTo
//     *
//     * @param example  目标POJO的Example实例对象
//     * @param statuses 状态集合
//     * @param idx      主键idx
//     * @throws MsOnionException 异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    protected void invokeAndStatusInAndIdxEqualTo(E example, List<Short> statuses, Long idx) throws MsOnionException {
//        try {
//            Object criteria = getCriteria(example);
//            // andStatusIn
//            Method andStatusIn = criteria.getClass().getDeclaredMethod("andStatusIn", List.class);
//            // 排序 statuses , 按照 从小大的顺序
//            Collections.sort(statuses);
//            andStatusIn.invoke(criteria, statuses);
//
//            // andIdxEqualTo
//            Method andIdxEqualTo = criteria.getClass().getDeclaredMethod("andIdxEqualTo", Long.class);
//            // 调用 andIdxEqualTo 方法
//            andIdxEqualTo.invoke(criteria, idx);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }

    /**
     * 使用反射调用 andStatusIn 和 andIdxIn
     *
     * @param example  目标POJO的Example实例对象
     * @param statuses 状态集合
     * @param idxes    主键idx集合
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void invokeAndStatusInAndIdxIn(E example, List<Short> statuses, List<Long> idxes) throws MsOnionException {
        try {
            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
            // 如果是0：删除的，不可以查询到
            // 必须注意，必须同时使用同一个 criteria 实例对象，否则是不行的！！！
            Object criteria = getCriteria(example);
            // andStatusIn
//            Method andStatusIn = criteria.getClass().getDeclaredMethod("andStatusIn", List.class);
            // 排序 statuses , 按照 从小大的顺序
//            Collections.sort(statuses);
//            andStatusIn.invoke(criteria, statuses);

            // andIdxEqualTo
            Method andIdxIn = criteria.getClass().getDeclaredMethod("andIdxIn", List.class);
            // 排序 idxes , 按照 从小大的顺序
            Collections.sort(idxes);
            // 调用 andIdxIn 方法
            andIdxIn.invoke(criteria, idxes);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 使用反射调用andStatusEqualTo
     *
     * @param example 目标POJO的Example实例对象
     * @param status  状态值，例如：0：删除、1：可用、激活、2：未激活、不可用、下架
     * @return criteria Example的Criteria
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected Object invokeAndStatusEqualTo(E example, short status) throws MsOnionException {
        try {
            Object criteria = getCriteria(example);
            // andStatusEqualTo
            Method andStatusEqualTo = criteria.getClass().getDeclaredMethod("andStatusEqualTo", Short.class);
            // 调用 andStatusEqualTo 方法
            andStatusEqualTo.invoke(criteria, status);
            return criteria;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 更新状态值
     *
     * @param pojo   目标POJO实例对象
     * @param status 状态值
     * @throws MsOnionException 异常
     */
    private int updateStatusForPojo(T pojo, short status) throws MsOnionException {
        if (null == pojo) {
            return DEFAULT_UPDATE_FAIL_RESULT;
        }
        try {
            // 通过反射获取 status 字段
            /*
            getDeclaredField是可以获取一个类的所有字段.
            getField只能获取类的public 字段.
             */
//            Field statusField = pojo.getClass().getDeclaredField("status");
//            // 设置允许访问
//            statusField.setAccessible(true);


            // 如果查询到，状态已经是当前需要更新状态了
            // 因为数据库所有表都有status 列
            Field field = pojo.getClass().getDeclaredField(TABLE_STATUS_COLUMN_NAME);
            // 设置允许访问
            field.setAccessible(true);
            short value = (short) field.get(pojo);
            if (value == status) {
                return this.DEFAULT_UPDATE_SUCCESS_RESULT;
            }

//            short originalStatus = statusField.getShort(pojo);
            // Error : Can not set java.lang.Short field cc.msonion.carambola.member.pojo.Member.status to (short)1
//            statusField.setShort(pojo, status);
            // 必须类型转换为： (short)0
//            statusField.set(pojo, (short) 0);
            field.set(pojo, status);
            // 上面通过方式设置 状态的值，需要其他更新
            return this.DEFAULT_UPDATE_NEED_TO_RESULT;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////////////////////// 使用方式调用方法，End ///////////////////////////////////

    /**
     * 为了安全考虑，屏蔽密码，设置为null，在分页查询列表中
     *
     * @param list 目标POJO的List集合
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void shieldPasswordForPojoSecurity(List<T> list) {
        try {
            // 必须在缓存如Redis之前，就把密码设置为null
            // 多层嵌套，性能有问题，还可以优化吗？？？
            for (String name : getPojoSecurityForPasswordNames()) {
                this.msOnionLogger.debug(getClass().getName(), String.format("准备屏蔽密码# POJO的密码，name=%s", name));
                if (getTargetName().equalsIgnoreCase(name)) {
                    // 再遍历
                    for (String column : getPasswordColumnNames()) {
                        this.msOnionLogger.debug(getClass().getName(), String.format("准备屏蔽密码# 表密码列# name=%s,column=%s", name, column));
                        for (T t : list) {
                            // 需要反射
                            Field passwordField = t.getClass().getDeclaredField(column);
                            // 设置允许访问
                            passwordField.setAccessible(true);
                            // 设置值为null
                            passwordField.set(t, null);
                            // 打印日志
                            this.msOnionLogger.debug(getClass().getName(), String.format("屏蔽密码之后# name=%s,t=%s", name, t));
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.msOnionLogger.error(this.getClass().getName(), e);
        }
    }

    ////////////////////////////////////////////////  resultForXxx , Begin /////////////////////////////////////////////////

    /**
     * 返回更新操作结果
     *
     * @param result 数据库操作影响行数
     * @return 返回MsOnionResult
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForUpdate(int result) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE;
        if (result >= 1) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS;
        }
        return MsOnionResult.build(status, msg, result);
    }

    /**
     * 返回删除操作结果
     *
     * @param result 数据库操作影响行数
     * @return 返回MsOnionResult
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForDelete(int result) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_DELETE_FAILURE;
        if (result >= 1) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS;
        }
        return MsOnionResult.build(status, msg, result);
    }

    /**
     * 返回保存、插入、新增操作结果
     *
     * @param result 数据库操作影响行数
     * @return 返回MsOnionResult
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForSave(int result) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_SAVE_FAILURE;
        if (result >= 1) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS;
        }
        return MsOnionResult.build(status, msg, result);
    }

//    /**
//     *  返回查询操作结果
//     * @param result 数据库操作影响行数
//     * @return
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月18日 下午6:18:09
//     */
//    protected MsOnionResultAdapter resultForQuery(int result) {
//        int status = MsOnionStatusConstants.STATUS_400;
//        String msg = MsOnionMessageConstants.MESSAGE_QUERY_FAILURE;
//        if (result >= 1) {
//            status = MsOnionStatusConstants.STATUS_200;
//            msg = MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS;
//        }
//        return MsOnionResult.build(status, msg, result);
//    }

    /**
     * 返回查询操作结果
     *
     * @param result 查询结果的目标POJO实例对象
     * @return 返回MsOnionResult
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForQuery(T result) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_QUERY_FAILURE;
        if (null != result) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS;
        }
        return MsOnionResult.build(status, msg, result);
    }

    /**
     * 返回查询操作结果
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param list       查询结果的目标POJO实例对象的集合
     * @return 返回结果适配器实现类实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForQuery(MsOnionApiVersion apiVersion,
                                                  MsOnionParameterAdapter parameter, List<T> list) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_QUERY_FAILURE;
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS;
        }
        return MsOnionResult.build(status, msg, list);
    }

    /**
     * 返回查询操作结果，分页
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param list       查询结果的目标POJO实例对象的集合
     * @return 返回结果适配器实现：MsOnionPagingResult
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForQueryWithPaging(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                            List<T> list) throws MsOnionException {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_QUERY_FAILURE;
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS;
        }

        try {
            MsOnionPagingParameter pagingParameter = (MsOnionPagingParameter) parameter;
            // 分页结果
            return MsOnionPagingResult.build(status, msg, list, pagingParameter.getTotalCounts(),
                    pagingParameter.getPageSize(), pagingParameter.getPageNum());
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 返回查询记录结果
     *
     * @param result 查询记录结果
     * @return 返回结果适配器
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForCount(long result) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_COUNT_FAILURE;
        if (result >= 1) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_COUNT_SUCCESS;
        }
        return MsOnionResult.build(status, msg, result);
    }

    ////////////////////////////////////////////////  resultForXxx , End /////////////////////////////////////////////////

    ////////////////////////////////////////////////  Getters , Begin /////////////////////////////////////////////////

//    /**
//     * 获取BaseMapper，子类不可以直接调用
//     *
//     * @return 返回BaseMapper
//     */
//    public MsOnionBaseMapper<T, E> getBaseMapper() {
//        return baseMapper;
//    }

//    /**
//     * 获取Redis客户端Jedis的适配器（单机版本、集群版本），
//     * 不允许直接调用，为了统一维护Redis的Key，按照规则生成Key，
//     * 否则后续维护就很难了！！！
//     *
//     * @return 返回Redis客户端Jedis的适配器（单机版本、集群版本）
//     */
//    protected MsOnionJedisAdapter getMsOnionJedisAdapter() {
//        return msOnionJedisAdapter;
//    }

    /**
     * 获取Redis的Key生成器适配器
     *
     * @return 返回Redis的Key生成器适配器
     */
    public MsOnionRedisKeyGeneratorAdapter<T, E> getRedisKeyGeneratorAdapter() {
        return this.redisKeyGeneratorAdapter;
    }

    /**
     * 获取Zookeeper客户端
     *
     * @return 返回Zookeeper客户端
     */
    public MsOnionCuratorZookeeperClient getMsOnionCuratorZookeeperClient() {
        return msOnionCuratorZookeeperClient;
    }

    /**
     * 获取Zookeeper重试策略
     *
     * @return 返回Zookeeper重试策略
     */
    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    /**
     * 获取封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
     *
     * @return 返回封装生产环境、预览环境、稳定环境、测试环境、开发环境的域名
     */
    public MsOnionDomain getMsOnionDomain() {
        return msOnionDomain;
    }

    /**
     * 获取是否禁止Redis的查询缓存，是通过配置文件properties设置
     *
     * @return 返回是否禁止Redis的查询缓存
     */
    public boolean isDisabledRedisQueryCache() {
        // 从 V2.0.5 开始，从ZK中动态配置
//        return isDisabledRedisQueryCache;

        // 从ZK中获取值 , 无非调用 MsOnionZookeeperUtils ，在 msyt-parent-ext 模块中！！！
        // 子类重写 ！！！
        try {
            return getMsOnionDynamicSettingAdapter().getRedisCacheGlobalQueryDisabled(getMsOnionDomain(),
                    getMsOnionCuratorZookeeperClient(), getRetryPolicy());
        } catch (MsOnionException e) {
            getMsOnionLogger().error(getClass().getName(), e);
            return false;
        }
    }

    /**
     * 获取Redis缓存周期，是通过配置文件properties设置，单位：秒
     *
     * @return Redis缓存周期，单位：秒
     */
    public int getRedisCacheExpire() {
        // 从 V2.0.5 开始，使用动态配置
//        return redisCacheExpire;
        // 使用动态配置
        try {
            return getMsOnionDynamicSettingAdapter().getRedisGlobalCacheExpire(getMsOnionDomain(), getMsOnionCuratorZookeeperClient(), getRetryPolicy());
        } catch (MsOnionException e) {
            getMsOnionLogger().error(getClass().getName(), e);
            // 返回默认值
            return redisCacheExpire;
        }
    }

    /**
     * 获取SQL中状态列可用值，是通过配置文件properties设置
     *
     * @return 获取SQL中状态列可用值
     */
    public String getSqlColumnStatusAvailableValues() {
        return sqlColumnStatusAvailableValues;
    }

    /**
     * 获取SQL中密码列名称，多个列使用,分割，例如：password,pwd
     *
     * @return 返回SQL中密码列名称
     */
    public String getSqlColumnPasswordNames() {
        return sqlColumnPasswordNames;
    }

    /**
     * 获取目标POJO需要设置密码为null的名称，多个使用,分隔，例如：User,Member
     *
     * @return 获取目标POJO需要设置密码为null的名称
     */
    public String getPojoNeedToSetNullForPasswordNames() {
        return pojoNeedToSetNullForPasswordNames;
    }

    /**
     * 获取日志操作
     *
     * @return 返回日志操作
     */
    public MsOnionLoggerAdapter getMsOnionLogger() {
        return msOnionLogger;
    }


    ////////////////////////////////////////////////  Getters , End /////////////////////////////////////////////////


    //////////////////////////////// 更新状态，Begin /////////////////////////////////////////////////////

    /**
     * 更新状态status
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @param status     目标更新状态值，例如：0（删除）
     * @return 返回数据库更新操作记录行数，1：更新成功，0：更新失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public int updateStatus(MsOnionApiVersion apiVersion, long idx, short status) throws MsOnionIllegalArgumentException, MsOnionException {
        return updateStatus(apiVersion, null, idx, status);
    }

    /**
     * 更新状态status
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param status     目标更新状态值，例如：0（删除）
     * @return 返回数据库更新操作记录行数，1：更新成功，0：更新失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public int updateStatus(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, short status) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查ApiVersion参数
        inspectParameters(apiVersion, idx, status);

        try {
//            int result = this.baseMapper.deleteByPrimaryKey(idx);
//            // 删除Redis缓存
//            this.deleteRedisCache(apiVersion, parameter, idx, result);
//
//            this.syncPageAndExampleRedisCacheForDelete(apiVersion, parameter, idx, result);
//            return result;

            // 根据主键查询
            T t = this.queryByPrimaryKey(apiVersion, parameter, idx);
            if (null == t) {
                return this.DEFAULT_UPDATE_FAIL_RESULT;  // 查不到这条记录
            }

//            // 如果查询到，状态已经是当前需要更新状态了
//            // 因为数据库所有表都有status 列
//            Field field = t.getClass().getDeclaredField(TABLE_STATUS_COLUMN_NAME);
//            // 设置允许访问
//            field.setAccessible(true);
//            short value = (short) field.get(t);
//            if (value == status) {
//                return this.DEFAULT_UPDATE_SUCCESS_RESULT;
//            }

            // 如果查询到，状态已经是删除了
            // 因为数据库所有表都有status 列
//            Field field = t.getClass().getField(TABLE_STATUS_COLUMN_NAME);
//            // 设置允许访问
//            field.setAccessible(true);
//            short value = (short) field.get(t);
//            if (value == this.sqlColumnDeleteStatusValue) {
//                return this.DEFAULT_UPDATE_SUCCESS_RESULT;
//            }

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("更新状态status#准备更新的pojo=%s", t));

            // 不要通过 Example 的方式更新，存在BUG ## Begin //////////////
//            // 获取目标POJO的Example实例
//            E example = getExample();
//            // 反射方式，更新状态值
//            this.invokeAndStatusEqualTo(example, status);
//
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), String.format("更新状态status#已经更新的pojo=%s", t));
//
//            // 执行更新
//            // 实例对象返回数据库更新影响行数，1：更新成功（软删除），0：更新失败（软删除）
////            int result = this.deleteByExample(apiVersion, parameter, example);
//
//            // updateByExample : set idx = #{record.idx,jdbcType=BIGINT}, 主键 idx 也更新了，不可以这样使用！！！
//            int result = this.updateByExample(apiVersion, parameter, t, example);

            // 不要通过 Example 的方式更新，存在BUG ## End  //////////////

            // 通过反射更新状态值
            int result = updateStatusForPojo(t, status);
            if (result == this.DEFAULT_UPDATE_FAIL_RESULT) {
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("updateStatus#更新状态#更新失败#idx=%s,status=%s,pojo=%s", idx, status, t));
                return this.DEFAULT_UPDATE_FAIL_RESULT;
            } else if (result == this.DEFAULT_UPDATE_SUCCESS_RESULT) {
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("updateStatus#更新状态#已经更新了，不需要再更新，直接返回#idx=%s,status=%s,pojo=%s", idx, status, t));
                // 已经更新了
                this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, t, null, result);

                return this.DEFAULT_UPDATE_SUCCESS_RESULT;
            }

            // 需要去更新状态值，因为还没有更新！！！
            result = this.updateByPrimaryKey(apiVersion, parameter, t);

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("更新状态status#已经更新# 更新主键idx的缓存，pojo=%s，更新数据库影响行数result=%s", t, result));

            // 必须注意：updateByPrimaryKey 中，已经同步缓存了，就不需要再同步缓存了！！！
            // 更新Redis缓存 , 是否有BUG，可以更新达到缓存吗 ？？？
//            // 这里设置 example 为 null
//            this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, t, null, result);
//
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), String.format("更新状态status#已经更新# 更新主键idx的缓存，pojo=%s，
//  更新数据库影响行数result=%s", t, result));
//
//            // 上面还没有同步（当前pojo的主键缓存），需要删除主键idx缓存，也是更新缓存
//            this.addRedisCache(apiVersion, parameter, t, idx, result, redisCacheExpire);

            return result;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    //////////////////////////////// 更新状态，End /////////////////////////////////////////////////////

//    //////////////////////////////// 提供简单Redis缓存操作，Begin /////////////////////////////////////////////////////
//
//    ////////////////// String字符串类型操作，Begin ///////////
//
//    /**
//     * 将数据缓存到Redis中
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key     Redis缓存的Key
//     * @param value   Redis缓存中的数据
//     * @param seconds Redis缓存周期，单位：秒
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public String setForRedis(String key, String value, int seconds)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return setForRedis(null, key, value, seconds);
//    }
//
//    /**
//     * 将数据缓存到Redis中
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis缓存的Key
//     * @param value     Redis缓存中的数据
//     * @param seconds   Redis缓存周期，单位：秒
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public String setForRedis(MsOnionParameterAdapter parameter, String key, String value,
//                              int seconds) throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//
//        if (StringUtils.isBlank(value)) {
//            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_VALUE_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//
//        if (seconds <= MsOnionConstants.REDIS_EXPIRE_MIN_VALUE || seconds >= MsOnionConstants.REDIS_EXPIRE_MAX_VALUE) {
//            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_SECONDS_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//
//        try {
//            value = value.trim();
//            this.msOnionLogger.debug(getClass().getName(),
//                    String.format("setForRedis # 设置Redis缓存 # key=%s，value=%s，seconds=%s",
//                            key, value, seconds));
//
//            // 设置缓存
//            String result = this.msOnionJedisAdapter.set(key, value);
//            // 设置缓存周期
//            this.msOnionJedisAdapter.expire(key, seconds);
//            // 保存当前Key，为了以后更新缓存使用的
//            this.addKey(key, seconds);
//            return result;
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 将数据缓存到Redis中
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key   Redis缓存的Key
//     * @param value Redis缓存中的数据
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public String setForRedis(String key, String value) throws MsOnionIllegalArgumentException, MsOnionException {
//        return setForRedis(null, key, value);
//    }
//
//    /**
//     * 将数据缓存到Redis中
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis缓存的Key
//     * @param value     Redis缓存中的数据
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public String setForRedis(MsOnionParameterAdapter parameter, String key, String value)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        try {
//            if (StringUtils.isBlank(value)) {
//                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_VALUE_ILLEGAL,
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            value = value.trim();
//            this.msOnionLogger.debug(getClass().getName(),
//                    String.format("setForRedis # 设置Redis缓存 # key=%s，value=%s",
//                            key, value));
//
//            // 设置缓存
//            String result = this.msOnionJedisAdapter.set(key, value);
//            // 不设置缓存周期
//            // 保存当前Key，为了以后更新缓存使用的，这里没有缓存周期参数，只能使用全局周期
//            this.addKey(key, this.getRedisCacheExpire());
//            return result;
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * <p>设置key的过期时间，超过时间后，将会自动删除该key。
//     * 在Redis的术语中一个key的相关超时是不确定的。
//     * 超时后只有对key执行DEL命令或者SET命令或者GETSET时才会清除。
//     * 这意味着，从概念上讲所有改变key的值的操作都会使他清除。
//     * 例如，INCR递增key的值，执行LPUSH操作，或者用HSET改变hash的field所有这些操作都会触发删除动作。
//     * 使用PERSIST命令可以清除超时，使其变成一个永久的key。
//     * 如果key被RENAME命令修改，相关的超时时间会转移到新key上面。
//     * 如果key被RENAME命令修改，比如原来就存在Key_A,然后调用RENAME Key_B Key_A命令，
//     * 这时不管原来Key_A是永久的还是设置为超时的，都会由Key_B的有效期状态覆盖。
//     * 刷新过期时间
//     * 对已经有过期时间的key执行EXPIRE操作，将会更新它的过期时间。
//     * 有很多应用有这种业务场景，例如记录会话的session。</p>
//     *
//     * @param key     Redis的Key
//     * @param seconds Redis缓存周期，单位：秒
//     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Long expireForRedis(String key, int seconds)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return expireForRedis(null, key, seconds);
//    }
//
//    /**
//     * <p>设置key的过期时间，超过时间后，将会自动删除该key。
//     * 在Redis的术语中一个key的相关超时是不确定的。
//     * 超时后只有对key执行DEL命令或者SET命令或者GETSET时才会清除。
//     * 这意味着，从概念上讲所有改变key的值的操作都会使他清除。
//     * 例如，INCR递增key的值，执行LPUSH操作，或者用HSET改变hash的field所有这些操作都会触发删除动作。
//     * 使用PERSIST命令可以清除超时，使其变成一个永久的key。
//     * 如果key被RENAME命令修改，相关的超时时间会转移到新key上面。
//     * 如果key被RENAME命令修改，比如原来就存在Key_A,然后调用RENAME Key_B Key_A命令，
//     * 这时不管原来Key_A是永久的还是设置为超时的，都会由Key_B的有效期状态覆盖。
//     * 刷新过期时间
//     * 对已经有过期时间的key执行EXPIRE操作，将会更新它的过期时间。
//     * 有很多应用有这种业务场景，例如记录会话的session。</p>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param seconds   Redis缓存周期，单位：秒
//     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Long expireForRedis(MsOnionParameterAdapter parameter, String key, int seconds)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        if (seconds <= MsOnionConstants.REDIS_EXPIRE_MIN_VALUE || seconds >= MsOnionConstants.REDIS_EXPIRE_MAX_VALUE) {
//            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_SECONDS_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//
//        try {
//            return this.msOnionJedisAdapter.expire(key, seconds);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 删除Redis中的缓存
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis缓存的Key
//     * @return 返回删除结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public Long delKeyForRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return delKeyForRedis(null, key);
//    }
//
//    /**
//     * 删除Redis中的缓存
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis缓存的Key
//     * @return 返回删除结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public Long delKeyForRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//
//        try {
//            return this.msOnionJedisAdapter.del(key);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 获取Redis缓存中数据
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回Redis缓存中数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    public String getFromRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException {
//        return getFromRedis(null, key);
//    }
//
//    /**
//     * 获取Redis缓存中数据
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回Redis缓存中数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    @Override
//    public String getFromRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        try {
//            return this.msOnionJedisAdapter.get(key);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    ////////////////// String字符串类型操作，End ///////////
//
//
//    ////////////////// Set类型操作，Begin ///////////
//    ////// Redis set对外提供功能与list类似，是一个列表的功能，特殊之处在于set是可以自动排重的，不会出现重复的数据！！！
//
//    /**
//     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
//     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
//     * 如果key 的类型不是集合则返回错误。</p>
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key    Redis的Key
//     * @param member 添加到Set集合中的成员数据
//     * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Long saddForRedis(String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return saddForRedis(null, key, member);
//    }
//
//    /**
//     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
//     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
//     * 如果key 的类型不是集合则返回错误。</p>
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param member    添加到Set集合中的成员数据
//     * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Long saddForRedis(MsOnionParameterAdapter parameter, String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        inspectParameterForRedisMember(member);
//        try {
//            return this.msOnionJedisAdapter.sadd(key);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 删除并获取Redis中一个集合里面的元素
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回删除之后集合中原始
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public String spopForRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return spopForRedis(null, key);
//    }
//
//    /**
//     * 删除并获取Redis中一个集合里面的元素
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回删除之后集合中原始
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public String spopForRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        try {
//            return this.msOnionJedisAdapter.spop(key);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 获取key的Set集合所有的元素
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回key的Set集合所有的元素
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Set<String> smembersForRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return smembersForRedis(null, key);
//    }
//
//    /**
//     * 获取key的Set集合所有的元素
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回key的Set集合所有的元素
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Set<String> smembersForRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        try {
//            return this.msOnionJedisAdapter.smembers(key);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
//     * 如果member元素是集合key的成员，则返回1
//     * 如果member元素不是key的成员，或者集合key不存在，则返回0
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key    Redis的Key
//     * @param member 成员
//     * @return 返回Set集合中成员 member 是否是存储的集合 key的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Boolean sismemberForRedis(String key, String member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return sismemberForRedis(null, key, member);
//    }
//
//    /**
//     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
//     * 如果member元素是集合key的成员，则返回1
//     * 如果member元素不是key的成员，或者集合key不存在，则返回0
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param member    成员
//     * @return 返回Set集合中成员 member 是否是存储的集合 key的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Boolean sismemberForRedis(MsOnionParameterAdapter parameter, String key, String member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        if (StringUtils.isBlank(member)) {
//            return false;
//        }
//        member = member.trim();
//        try {
//            return this.msOnionJedisAdapter.sismember(key, member);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    ////////////////// Set类型操作，End ///////////
//
//    /**
//     * Redis缓存中key是否存在
//     * <p>1：key存在</p>
//     * <p>0：key不存在</p>
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回1 如果key存在，0 如果key不存在
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Boolean existsKeyFromRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return existsKeyFromRedis(null, key);
//    }
//
//    /**
//     * Redis缓存中key是否存在
//     * <p>1：key存在</p>
//     * <p>0：key不存在</p>
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回1 如果key存在，0 如果key不存在
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Boolean existsKeyFromRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        inspectParametersForRedisKey(parameter, key);
//        try {
//            return this.msOnionJedisAdapter.exists(key);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * <p>在key集合中移除指定的元素，
//     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
//     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
//     * 如果key的类型不是一个集合,则返回错误。</p>
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key    Redis中的Key
//     * @param member 需要从Redis的Set集合中删除的成员
//     * @return 从集合中移除元素的个数，不包括不存在的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Long sremForRedis(String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return sremForRedis(null, key, member);
//    }
//
//    /**
//     * <p>在key集合中移除指定的元素，
//     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
//     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
//     * 如果key的类型不是一个集合,则返回错误。</p>
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis中的Key
//     * @param member    需要从Redis的Set集合中删除的成员
//     * @return 从集合中移除元素的个数，不包括不存在的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public Long sremForRedis(MsOnionParameterAdapter parameter, String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        inspectParameterForRedisMember(member);
//        try {
//            return this.msOnionJedisAdapter.srem(key, member);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    //    /**
////     * <p>从 key 指定的哈希集中移除指定的域。在哈希集中不存在的域将被忽略。</p>
////     * <p>如果 key 指定的哈希集不存在，它将被认为是一个空的哈希集，该命令将返回0。</p>
////     *
////     * @param key   Redis的Key
////     * @param field 哈希集中的域
////     * @return 返回删除结果
////     * @throws MsOnionIllegalArgumentException 非法参数异常
////     * @throws MsOnionException                异常
////     */
////    public Long hdelRedisKey(String key, String... field)
////            throws MsOnionIllegalArgumentException, MsOnionException {
////        return hdelRedisKey(null, key, field);
////    }
////
////    /**
////     * <p>从 key 指定的哈希集中移除指定的域。在哈希集中不存在的域将被忽略。</p>
////     * <p>如果 key 指定的哈希集不存在，它将被认为是一个空的哈希集，该命令将返回0。</p>
////     *
////     * @param parameter 参数
////     * @param key       Redis的Key
////     * @param field     哈希集中的域
////     * @return 返回删除结果
////     * @throws MsOnionIllegalArgumentException 非法参数异常
////     * @throws MsOnionException                异常
////     */
////    public Long hdelRedisKey(MsOnionParameterAdapter parameter, String key, String... field)
////            throws MsOnionIllegalArgumentException, MsOnionException {
////        // 检查参数
////        inspectParametersForRedisKey(parameter, key, field);
////        try {
////            return this.msOnionJedisAdapter.hdel(key, field);
////        } catch (Exception ex) {
////            throw new MsOnionException(ex);
////        }
////    }
////
////    /**
////     * <p>设置 key 指定的哈希集中指定字段的值。该命令将重写所有在哈希集中存在的字段。</p>
////     * <p>如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联</p>
////     *
////     * @param key  Redis中Key
////     * @param hash Map数据
////     * @return 返回结果
////     * @throws MsOnionIllegalArgumentException 非法参数异常
////     * @throws MsOnionException                异常
////     */
////    public String hmset(String key, Map<String, String> hash)
////            throws MsOnionIllegalArgumentException, MsOnionException {
////        return hmset(null, key, hash);
////    }
////
////    /**
////     * <p>设置 key 指定的哈希集中指定字段的值。该命令将重写所有在哈希集中存在的字段。</p>
////     * <p>如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联</p>
////     *
////     * @param parameter 参数
////     * @param key       Redis中Key
////     * @param hash      Map数据
////     * @return 返回结果
////     * @throws MsOnionIllegalArgumentException 非法参数异常
////     * @throws MsOnionException                异常
////     */
////    public String hmset(MsOnionParameterAdapter parameter, String key, Map<String, String> hash)
////            throws MsOnionIllegalArgumentException, MsOnionException {
////        // 检查参数
////        inspectParametersForRedisKey(parameter, key);
////        inspectParameterForRedisField(hash);
////
////        try {
////            return this.msOnionJedisAdapter.hmset(key, hash);
////        } catch (Exception ex) {
////            throw new MsOnionException(ex);
////        }
////    }
//
//    //////////////// 提供简单Redis缓存操作，检查参数，Begin /////////////////////
//
//    /**
//     * 检查Redis的Key参数
//     *
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 分页参数
//     * @param key       Redis的Key
//     * @param field     Redis中数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    protected void inspectParametersForRedisKey(MsOnionParameterAdapter parameter, String key, String... field)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        inspectParametersForRedisKey(parameter, key);
//        inspectParameterForRedisField(field);
//    }
//
//    /**
//     * 检查Redis的Key参数
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 分页参数
//     * @param key       Redis的Key
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    private void inspectParametersForRedisKey(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        try {
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), "inspectParametersForRedisKey # 检查参数 # key=" + key);
//
//            if (StringUtils.isBlank(key)) {
//                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_ILLEGAL,
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // Redis 的Key规则类似
//            // String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(),
// getSessionIdentification(), token.toUpperCase());
//
//            key = key.trim();
//            // 判断Key规则是否符合  getTargetName()
//            if (!key.contains(getTargetName())) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL, getTargetName()),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // 判断Key规则是否符合  getPrefix()
//            if (!key.contains(getPrefix())) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL,
//                        getPrefix()),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // 判断Key规则是否符合  getTargetIdentification()
//            if (!key.contains(getTargetIdentification())) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL,
//                        getTargetIdentification()),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // 判断Key规则是否包括 ::
//            if (key.contains(MsOnionConstants.REDIS_KEY_ILLEGAL_MULTI_COLON)) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_MULTI_COLON,
//                        MsOnionConstants.REDIS_KEY_ILLEGAL_MULTI_COLON),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // Redis的Key不可以":"开头
//            if (key.startsWith(MsOnionConstants.REDIS_KEY_COLON)) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_STARTS_WITH,
//                        MsOnionConstants.REDIS_KEY_COLON),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // Redis的Key不可以":"结尾
//            if (key.endsWith(MsOnionConstants.REDIS_KEY_COLON)) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_ENDS_WITH,
//                        MsOnionConstants.REDIS_KEY_COLON),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // 规则的Key，按照规则生成的Key
//            String regulationKey = String.format("%s%s%s", getTargetName(),
//                    getPrefix(), getTargetIdentification());
//
//            // 打印日志
//            this.msOnionLogger.debug(getClass().getName(), "inspectParametersForRedisKey # 检查参数 # Redis的Key规则：regulationKey=" + regulationKey);
//
//            // 判断Key规则是否符合  regulationKey
//            if (!key.contains(regulationKey)) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL,
//                        regulationKey),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//
//            // 校验正则表达式
//            if (!MsOnionRegexUtils.isRedisKey(key)) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，key=%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_REGEX,
//                        key),
//                        MsOnionStatusConstants.STATUS_400);
//            }
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * 检查field参数
//     *
//     * @param field Redis中数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    protected void inspectParameterForRedisField(String... field)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        if (null == field || field.length <= 0) {
//            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_FIELD_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//    }
//
//    /**
//     * 检查member参数
//     *
//     * @param member Redis中Set集合中的数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    protected void inspectParameterForRedisMember(String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        if (null == member || member.length <= 0) {
//            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_MEMBER_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//    }
//
//    /**
//     * 检查hash参数
//     *
//     * @param hash Map数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月14日 下午6:18:09
//     */
//    protected void inspectParameterForRedisHash(Map<String, String> hash)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        if (null == hash || hash.isEmpty()) {
//            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_HASH_ILLEGAL,
//                    MsOnionStatusConstants.STATUS_400);
//        }
//    }
//
//    //////////////////// 提供简单Redis缓存操作，检查参数，End //////////////////////////


    //////////////////// 由于idxCode是后面才添加的，编辑的时候，业务层并没有将idxCode值设置，导致编辑之后为0，Begin //////////////////////////

    /**
     * 同步IdxCode
     * <p>由于idxCode是后面添加的，因此编辑的时候，没有把idxCode的值，带过来，就导致编辑之后，idxCode为0</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO
     * @return 返回idxCode
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    private long syncIdxCodeForUpdate(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                      T record) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == record) {
            return 0;
        }
        try {
            // 由于idxCode是后面添加的，因此编辑的时候，没有把idxCode的值，带过来，就导致编辑之后，idxCode为0,
            // 因为底层框架，统一处理默认值
            // 先根据主键idx把数据查询出来，因为业务层已经根据idx查询出当前POJO，才能修改，已经有Redis缓存了
            // 则，这里修改是从Redis的缓存中获取，除非是有其他的增、删、改或者清除缓存，才不会有缓存
            // record.getId() 这里就是 idx的字符串类型值
            // 统一更新 idxCode
            Field idxCodeField = record.getClass().getDeclaredField("idxCode");
            // 设置允许访问
            idxCodeField.setAccessible(true);
            // 当前的idxCode
            Object idxCode = idxCodeField.get(record);
            if (null != idxCode && ((long) idxCode) >= 1) {
                // 已经有IdxCode，就不需要处理
                return (long) idxCode;
            }
            long idx = Long.parseLong(record.getId());
            // 数据库、缓存中的数据，也就是还没有编辑的原始数据
            T originalRecord = queryByPrimaryKey(apiVersion, parameter, idx);
            // 原始的
            Field originalIdxCodeField = originalRecord.getClass().getDeclaredField("idxCode");
            // 设置允许访问
            originalIdxCodeField.setAccessible(true);
            idxCode = originalIdxCodeField.get(originalRecord);
            // 设置 idxCode
            idxCodeField.set(record, idxCode);
            // 打印日志
            getMsOnionLogger().debug(getClass().getName(), String.format("syncIdxCodeForUpdate#idx=%s,idxCode=%s,原始originalRecord=%s,最新record=%s",
                    idx, idxCode, originalRecord, record));
            return (Long) idxCode;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    //////////////////// 由于idxCode是后面才添加的，编辑的时候，业务层并没有将idxCode值设置，导致编辑之后为0，End //////////////////////////

    ////////////////////////// 动态配置适配器 ## Begin ////////////////////

    /**
     * 动态配置适配器
     *
     * @return 动态配置适配器
     */
    public MsOnionDynamicSettingAdapter getMsOnionDynamicSettingAdapter() {
        return msOnionDynamicSettingAdapter;
    }


    ////////////////////////// 动态配置适配器 ## End ////////////////////

}
