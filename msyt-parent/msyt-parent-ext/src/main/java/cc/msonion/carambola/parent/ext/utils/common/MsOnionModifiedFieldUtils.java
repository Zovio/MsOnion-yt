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

package cc.msonion.carambola.parent.ext.utils.common;

import cc.msonion.carambola.parent.common.constants.MsOnionTypeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityMD5Utils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionModifiedField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * POJO的字段值已经是否修改的工具类
 *
 * @Title: MsOnionModifiedFieldUtils.java
 * @Package: cc.msonion.carambola.parent.ext.utils.common
 * @Description: POJO的字段值已经是否修改的工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月15日 下午5:11:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月15日 下午5:11:38
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * POJO的字段值已经是否修改的工具类
 *
 * @ClassName: MsOnionModifiedFieldUtils
 * @Description: MsOnionModifiedFieldUtils
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月15日 下午5:11:38
 */
public final class MsOnionModifiedFieldUtils {

    /**
     * 默认 Hash Code
     */
    public static final int DEFAULT_HASH_CODE = -1;

    private MsOnionModifiedFieldUtils() {

    }

    /**
     * 获取HashCode
     *
     * @param pojo 目标POJO实例对象
     * @return 返回HashCode
     * @throws MsOnionException 异常
     */
    public static Map<String, Object> getModifiedField(final MsOnionBasePojoAdapter pojo) throws MsOnionException {
        return getModifiedField(null, pojo);
    }

    /**
     * 获取HashCode
     *
     * @param msOnionLogger  日志
     * @param pojo 目标POJO实例对象
     * @return 返回HashCode
     * @throws MsOnionException 异常
     */
    public static Map<String, Object> getModifiedField(final MsOnionLogger msOnionLogger, final MsOnionBasePojoAdapter pojo) throws MsOnionException {
        try {
            if (null == pojo) {
                return null;
            }

            final Map<String, Object> map = new HashMap<>();
            // 遍历所有的字段
            MsOnionReflectionUtils.doWithFields(pojo.getClass(), new ReflectionUtils.FieldCallback() {
                /**
                 * Perform an operation using the given field.
                 *
                 * @param field the field to operate on
                 */
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    // MySQL数据库表的列中，已经有 @Modified 注解的字段
                    field.setAccessible(true);
                    MsOnionModifiedField annotation = field.getAnnotation(MsOnionModifiedField.class);
                    if (null == annotation) {
                        // 当前字段不需要比较 HashCode
                        return;
                    }
                    // MySQL数据库表的列中，已经有 @Modified 注解的字段
                    Object value = field.get(pojo);
                    if (null != msOnionLogger) {
                        msOnionLogger.info(MsOnionModifiedFieldUtils.class.getName(),
                                String.format("获取需要校验字段field=%s的值value=%s是否改变了", field.getName(), value));
                    }
                    map.put(field.getName(), field);
                }
            });

            return map;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 是否已经修改
     *
     * @param originalPojo 原始的目标POJO实例对象
     * @param newPojo      新的目标POJO实例对象
     * @return 返回是否已经修改字段的值
     * @throws MsOnionException 异常
     */
    public static boolean hasModified(final MsOnionBasePojoAdapter originalPojo, final MsOnionBasePojoAdapter newPojo)
            throws MsOnionException {
        return hasModified(null, originalPojo, newPojo);
    }

    /**
     * 是否已经修改
     *
     * @param log          日志
     * @param originalPojo 原始的目标POJO实例对象
     * @param newPojo      新的目标POJO实例对象
     * @return 返回是否已经修改字段的值
     * @throws MsOnionException 异常
     */
    public static boolean hasModified(final MsOnionLogger log, final MsOnionBasePojoAdapter originalPojo, final MsOnionBasePojoAdapter newPojo)
            throws MsOnionException {
        // 获取原始的
        Map<String, Object> originalModifiedFields = getModifiedField(log, originalPojo);
        Map<String, Object> newModifiedFields = getModifiedField(log, newPojo);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : originalModifiedFields.entrySet()) {
            Field field = (Field) entry.getValue();
            try {
                sb.append(field.get(originalPojo));
            } catch (IllegalAccessException e) {
                throw new MsOnionException(e);
            }
        }

        String originalData = MsOnionSecurityMD5Utils.md5(sb.toString());
        sb = new StringBuffer();

        for (Map.Entry<String, Object> entry : newModifiedFields.entrySet()) {
            Field field = (Field) entry.getValue();
            String typeName = field.getType().getTypeName();
            Object value;
            try {
                value = field.get(newPojo);
            } catch (IllegalAccessException e) {
                throw new MsOnionException(e);
            }

            if (null == value) {
                if (MsOnionTypeConstants.TYPE_REFERENCE_INTEGER_NAME.equals(typeName)
                        || MsOnionTypeConstants.TYPE_REFERENCE_LONG_NAME.equals(typeName)) {
                    sb.append("0");
                }
            } else {
                sb.append(value);
            }
        }

        String newData = MsOnionSecurityMD5Utils.md5(sb.toString());
        int compareResult = StringUtils.compare(originalData, newData);
        return (compareResult != 0);
    }


    /**
     * 获取 @MsOnionModifiedField 的字段的name和value
     *
     * @param log  日志
     * @param pojo 目标POJO实例对象
     * @return 返回HashCode
     * @throws MsOnionException 异常
     */
    public static Map<String, Object> getModifiedFieldNameAndValue(final MsOnionLogger log, final MsOnionBasePojoAdapter pojo) throws MsOnionException {
        try {
            if (null == pojo) {
                return null;
            }

            final Map<String, Object> map = new HashMap<>();
            // 遍历所有的字段
            MsOnionReflectionUtils.doWithFields(pojo.getClass(), new ReflectionUtils.FieldCallback() {
                /**
                 * Perform an operation using the given field.
                 *
                 * @param field the field to operate on
                 */
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    // MySQL数据库表的列中，已经有 @Modified 注解的字段
                    field.setAccessible(true);
                    MsOnionModifiedField annotation = field.getAnnotation(MsOnionModifiedField.class);
                    if (null == annotation) {
                        // 当前字段不需要比较 HashCode
                        return;
                    }
                    // MySQL数据库表的列中，已经有 @Modified 注解的字段
                    Object value = field.get(pojo);
                    if (null != log) {
                        log.info(MsOnionModifiedFieldUtils.class.getName(),
                                String.format("获取需要校验字段field=%s的值value=%s是否改变了", field.getName(), value));
                    }
                    map.put(field.getName(), value);
                }
            });

            return map;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }
}
