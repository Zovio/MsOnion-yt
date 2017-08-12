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
package cc.msonion.carambola.collector.common.constants;

/**
 * @Title: ItemConstants.java
 * @Package: cc.msonion.carambola.collector.common.constants
 * @Description: 商品相关信息常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/3/31 11:44
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017/3/31 11:44
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：条形码、中文名称、英文名称、规格、采集备注最大长度
 */

/**
 * @ClassName: ItemConstants
 * @Description: 商品相关信息常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/3/31 11:44
 */
public final class ItemConstants {

    private ItemConstants() {
    }

    /**
     * @Fields ITEM_BARCODE_MAX_LENGTH : 条形码最大长度，20个字符
     */
    public static final int ITEM_BARCODE_MAX_LENGTH = 20;

    /**
     * @Fields ITEM_CN_NAME_MAX_LENGTH : 中文名称最大长度，20个字符
     */
    public static final int ITEM_CN_NAME_MAX_LENGTH = 50;

    /**
     * @Fields ITEM_EN_NAME_MAX_LENGTH : 英文名称最大长度，20个字符
     */
    public static final int ITEM_EN_NAME_MAX_LENGTH = 200;

    /**
     * @Fields ITEM_SPECIFICATION_MAX_LENGTH : 规格最大长度，20个字符
     */
    public static final int ITEM_SPECIFICATION_MAX_LENGTH = 100;

    /**
     * @Fields ITEM_COLLECTION_REMARK_MAX_LENGTH : 采集备注最大长度，20个字符
     */
    public static final int ITEM_COLLECTION_REMARK_MAX_LENGTH = 500;

    /**
     * @Fields ITEM_STATE_MIN_VALUE : 商品状态最小值
     */
    public static final int ITEM_STATE_MIN_VALUE = 1;
    /**
     * @Fields ITEM_STATE_MAX_VALUE : 商品状态最大值
     */
    public static final int ITEM_STATE_MAX_VALUE = 8;
    /**
     * @Fields DEFAULT_PRESENT_IDX : 默认父级主键idx
     */
    public static final Long DEFAULT_PRESENT_IDX = 0L;

    /**
     * @Fields 默认商品价格最大值
     */
    public static final Integer MAX_ITEM_PRICE = 2147483647;


}
