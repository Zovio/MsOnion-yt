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

package cc.msonion.carambola.item.common.constants;

/**
 * @Title: MsOnionMessageConstants.java
 * @Package: cc.msonion.carambola.item.common.constants
 * @Description: 消息常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Johnny woo 3028554324@qq.com
 * @Date: 2017年3月30日 下午9:14:32
 * @Version: V2.0.0
 * @Modify-by: Johnny woo 3028554324@qq.com
 * @Modify-date: 2017年3月30日 下午9:14:32
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：商品基础资料
 */

/**
 * @ClassName: MsOnionMessageConstants
 * @Description: 消息常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Johnny woo 3028554324@qq.com
 * @Date: 2017年3月30日 下午9:14:32
 */
public final class ItemMessageConstants {

    private ItemMessageConstants() {
    }

    /**
     * 商品上架成功
     */
    public static final String MESSAGE_ITEM_OFFICIAL_ONLINE_SUCCESS = "商品上架成功";

    /**
     * 商品上架失败
     */
    public static final String MESSAGE_ITEM_OFFICIAL_ONLINE_FAIL = "商品上架失败";

    /**
     * 商品下架成功
     */
    public static final String MESSAGE_ITEM_OFFICIAL_OFFLINE_SUCCESS = "商品下架成功";

    /**
     * 商品下架失败
     */
    public static final String MESSAGE_ITEM_OFFICIAL_OFFLINE_FAIL = "商品下架失败";

    /**
     * 选中商品包含已上架的商品，请重新选择
     */
    public static final String MESSAGE_ITEM_OFFICIAL_ALREADY_ONLINE = "选中商品包含已上架的商品，请重新选择";

    /**
     * 选中商品包含下架/待上架商品，请重新选择
     */
    public static final String MESSAGE_ITEM_OFFICIAL_ALREADY_OFFLINE = "选中商品包含下架/待上架商品，请重新选择";

    /**
     * 选中商品包含下架/待上架商品，请重新选择
     */
    public static final String MESSAGE_ITEM_OFFICIAL_NOT_ONLINE = "选中商品包含下架/待上架商品，请重新选择";

    /**
     * 待上架商品信息不完整，请先完善信息后再上架
     */
    public static final String MESSAGE_ITEM_OFFICIAL_NOT_COMPLETE = "待上架商品信息不完整，请先完善信息后再上架";


}
