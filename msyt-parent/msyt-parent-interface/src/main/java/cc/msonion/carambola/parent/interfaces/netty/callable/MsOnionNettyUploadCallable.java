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

package cc.msonion.carambola.parent.interfaces.netty.callable;

import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.interfaces.callable.MsOnionComplexPlusCallable;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * MsOnionNettyUploadCallable
 *
 * @Title: MsOnionNettyUploadCallable.java
 * @Package: cc.msonion.carambola.parent.interfaces.netty.callable
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午2:28:17
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月1日 下午2:28:17
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * MsOnionNettyUploadCallable
 *
 * @param <RESPONSE> MsOnionNettyResponse
 * @param <REQUEST>  MsOnionNettyRequest
 * @param <SERVICE>  Service
 * @ClassName: MsOnionNettyUploadCallable
 * @Description: MsOnionNettyUploadCallable
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午2:28:17
 */
public abstract class MsOnionNettyUploadCallable<RESPONSE extends MsOnionNettyResponse,
        REQUEST extends MsOnionNettyRequest, SERVICE extends Serializable>
        implements MsOnionComplexPlusCallable<RESPONSE, REQUEST, SERVICE> {

    /**
     * 消息Id
     */
    private String messageId;

    /**
     * Netty客户端适配器
     */
    private MsOnionNettyClientAdapter client;

    /**
     * SERVICE实现类中方法名称
     */
    private String method;

    /**
     * SERVICE实现类中方法的参数
     */
    private Object[] parameters;

    /**
     * 父类泛型类型的索引：0 ，也就是： RESPONSE
     */
    private static final int SUPERCLASS_PARAMETERIZED_TYPE_INDEX_0 = 0;

    /**
     * 父类泛型类型的索引：1 ，也就是： REQUEST
     */
    private static final int SUPERCLASS_PARAMETERIZED_TYPE_INDEX_1 = 1;

    /**
     * 父类泛型类型的索引：2 ，也就是： SERVICE
     */
    private static final int SUPERCLASS_PARAMETERIZED_TYPE_INDEX_2 = 2;

    /**
     * MsOnionNettyUploadCallable
     *
     * @param client     Netty客户端实例对象
     * @param method     SERVICE实现类中方法名称，例如：uploadFile、downloadFile、upload、download
     * @param parameters SERVICE实现类中方法的参数
     */
    public MsOnionNettyUploadCallable(MsOnionNettyClientAdapter client, String method, Object... parameters) {
        this(null, client, method, parameters);
    }

    /**
     * MsOnionNettyUploadCallable
     *
     * @param messageId  消息Id，如果不设置，默认使用UUID
     * @param client     Netty客户端实例对象
     * @param method     SERVICE实现类中方法名称，例如：uploadFile、downloadFile、upload、download
     * @param parameters SERVICE实现类中方法的参数
     */
    public MsOnionNettyUploadCallable(String messageId, MsOnionNettyClientAdapter client, String method, Object... parameters) {
        this.messageId = messageId;
        this.client = client;
        this.method = method.trim();
        this.parameters = parameters;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public RESPONSE call() throws Exception {

//        Class<?> responseClass = MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), SUPERCLASS_PARAMETERIZED_TYPE_INDEX_0);
        Class<?> requestClass = MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), SUPERCLASS_PARAMETERIZED_TYPE_INDEX_1);
        Class<?> serviceClass = MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), SUPERCLASS_PARAMETERIZED_TYPE_INDEX_2);

        // 构造方法的参数数组
        Object[] constructorParameters = null;

        if (StringUtils.isNotBlank(messageId)) {
            constructorParameters = new Object[]{messageId, serviceClass, method, parameters};
        } else {
            constructorParameters = new Object[]{serviceClass, method, parameters};
        }

        // 构造方法的参数类型数组
        Class<?>[] constructorParameterClasses = MsOnionReflectionUtils.getParameterTypes(constructorParameters);

        // 根据参数类型 调用构造方法的
        Constructor<?> requestConstructor = requestClass.getConstructor(constructorParameterClasses);

        // 通过构造方法，创建实例对象
        // 构造方法参数：
        Object request = null;

        if (StringUtils.isNotBlank(messageId)) {
            messageId = messageId.trim();
            request = requestConstructor.newInstance(messageId, serviceClass, method, parameters);
        } else {
            request = requestConstructor.newInstance(serviceClass, method, parameters);
        }
        MsOnionNettyResponse result = client.sent((MsOnionNettyRequest) request);

        return (RESPONSE) result;
    }
}
