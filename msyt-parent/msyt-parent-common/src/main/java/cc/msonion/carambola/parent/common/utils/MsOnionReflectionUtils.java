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

package cc.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.constants.MsOnionTypeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @Title: MsOnionReflectionUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 反射工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午9:05:06
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午9:05:06
 * @Modify-version: V2.0.0
 * @Modify-description: 修改：注释
 */

/**
 * @ClassName: MsOnionReflectionUtils
 * @Description: 反射工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午9:05:06
 */
public final class MsOnionReflectionUtils extends org.springframework.util.ReflectionUtils {

    private MsOnionReflectionUtils() {
    }

    /**
     * 获取父类的泛型列表中的第一个泛型的类型
     *
     * @param clazz Class实例对象
     * @return 返回父类的泛型列表中的第一个泛型的类型
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:05:06
     */
    public static Class<?> getSuperclassParameterizedType(Class<?> clazz) throws MsOnionException {
        return getSuperclassParameterizedType(clazz, 0);
    }

    /**
     * 获取父类的泛型列表中的某一个泛型的类型
     *
     * @param clazz Class实例对象
     * @param index 从0开始
     * @return 返回父类的泛型列表中的某一个泛型的类型
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:05:06
     */
    public static Class<?> getSuperclassParameterizedType(Class<?> clazz, int index) throws MsOnionException {
        try {
            if (null == clazz) {
                return null;
            }
            Type genericSuperclass = clazz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (null != actualTypeArguments && actualTypeArguments.length > index) {
                    return (Class<?>) actualTypeArguments[index];
                }
            }
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
        return null;
    }

    /**
     * 获取父类的泛型列表中的某一个泛型的类型，它的简单名称
     *
     * @param clazz Class实例对象
     * @param index 从0开始
     * @return 返回父类的泛型列表中的某一个泛型的类型，它的简单名称
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:05:06
     */
    public static String getSuperclassParameterizedTypeSimpleName(Class<?> clazz, int index) throws MsOnionException {
        Class<?> superclassParameterizedType = getSuperclassParameterizedType(clazz, index);
        if (null != superclassParameterizedType) {
            return superclassParameterizedType.getSimpleName();
        }
        return null;
    }

    /**
     * 获取父类的泛型列表中的第一个泛型的类型，它的简单名称
     *
     * @param clazz Class实例对象
     * @return 返回父类的泛型列表中的第一个泛型的类型，它的简单名称
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:05:06
     */
    public static String getSuperclassParameterizedTypeSimpleName(Class<?> clazz) throws MsOnionException {
        Class<?> superclassParameterizedType = getSuperclassParameterizedType(clazz, 0);
        if (null != superclassParameterizedType) {
            return superclassParameterizedType.getSimpleName();
        }
        return null;
    }

    /**
     * 获取参数类型
     *
     * @param parameters 参数
     * @return 返回参数类型
     */
    public static Class<?>[] getParameterTypes(final Object[] parameters) {
        Class<?>[] result = new Class<?>[parameters.length];
        int i = 0;
        for (Object each : parameters) {
            result[i] = each.getClass();
            i++;
        }
        return result;
    }

    /**
     * 通过反射机制给对象设置值
     *
     * @param t 泛型对象
     * @param <T>      POJO
     * @param paramNumber 字段序号
     * @param value 字段赋值
     * @return <T> 泛型对象
     * @throws Exception 异常
     */
    public static <T> T reflectFieldArray(T t, int paramNumber, Object value) throws Exception {
        // 获取属性名
        Field[] fields = t.getClass().getDeclaredFields();
        // 返回的是一个参数类型
        String type = fields[paramNumber].getGenericType().toString();
        // 返回的是一个类对象
        PropertyDescriptor pd = new PropertyDescriptor(fields[paramNumber].getName(), t.getClass());
        // 获取某个属性的set方法
        Method setmd = pd.getWriteMethod();
        if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_INTEGER_NAME)) {
            setmd.invoke(t, MsOnionNumberUtils.toInt(String.valueOf(value), 0));
        } else if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_DOUBLE_NAME)) {
            setmd.invoke(t, MsOnionNumberUtils.toDouble(String.valueOf(value), 0));
        } else if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_FLOAT_NAME)) {
            setmd.invoke(t, MsOnionNumberUtils.toFloat(String.valueOf(value), 0));
        } else if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_LONG_NAME)) {
            setmd.invoke(t, MsOnionNumberUtils.toLong(String.valueOf(value), 0));
        } else if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_SHORT_NAME)) {
            setmd.invoke(t, MsOnionNumberUtils.toShort(String.valueOf(value), (short) 0));
        } else if (type.contains(MsOnionTypeConstants.TYPE_REFERENCE_BOOLEAN_NAME)) {
            setmd.invoke(t, BooleanUtils.toBoolean(String.valueOf(value)));
        } else {
            setmd.invoke(t, String.valueOf(value));
        }
        return t;
    }


    /**
     * 更改父类属性的值
     * @param obj 实体对象
     * @param fieldName 字段名
     * @param value 设置字段的值
     * @throws MsOnionException 异常
     */
    public static void setParentFieldValue(Object obj, String fieldName, Object value)throws MsOnionException {
        try {
            Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }


    /**
     * 暴力反射获取字段值
     *
     * @param obj       实例对象
     * @param fieldName 字段名
     * @return 属性值
     * @throws MsOnionException 异常
     */
    public static Object getFieldValue(Object obj, String fieldName) throws MsOnionException {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 暴力反射获取实体父类字段值
     *
     * @param obj       实例对象
     * @param fieldName 字段名
     * @return 属性值
     * @throws MsOnionException 异常
     */
    public static Object getParentFieldValue(Object obj, String fieldName) throws MsOnionException {
        try {
            Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }
}
