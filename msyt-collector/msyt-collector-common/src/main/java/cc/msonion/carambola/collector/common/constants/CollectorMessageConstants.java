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
 * @Title: CollectorMessageConstants.java
 * @Package: cc.msonion.carambola.collector.common.constants
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
 * @ClassName: CollectorMessageConstants
 * @Description: 消息常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Johnny woo 3028554324@qq.com
 * @Date: 2017年3月30日 下午9:14:32
 */
public final class CollectorMessageConstants {

    private CollectorMessageConstants() {
    }

    //////////////////// 商品基础资料，Begin ////////////////////

    /**
     * 商品信息不能为空
     */
    public static final String MESSAGE_ITEM_INFO_NULL = "商品信息不能为空";

    /**
     * 条形码不能为空
     */
    public static final String MESSAGE_ITEM_BARCODE_NULL = "条形码不能为空";

    /**
     * 条形码非法，最多20个字符
     */
    public static final String MESSAGE_ITEM_BARCODE_LENGTH_ILLEGAL = "条形码非法，最多20个字符";

    /**
     * 条形码已存在
     */
    public static final String MESSAGE_ITEM_BARCODE_EXIST = "条形码已存在";

    /**
     * 中文品名不能为空
     */
    public static final String MESSAGE_ITEM_CN_NAME_NULL = "中文品名不能为空";

    /**
     * 英文品名不能为空
     */
    public static final String MESSAGE_ITEM_EN_NAME_NULL = "英文品名不能为空";

    /**
     * 中文品名非法，最多50个字符
     */
    public static final String MESSAGE_ITEM_CN_NAME_LENGTH_ILLEGAL = "中文品名非法，最多50个字符";

    /**
     * 英文品名非法，最多200个字符
     */
    public static final String MESSAGE_ITEM_EN_NAME_LENGTH_ILLEGAL = "英文品名非法，最多200个字符";

    /**
     * 品牌不能为空
     */
    public static final String MESSAGE_ITEM_BRAND_NULL = "品牌不能为空";

    /**
     * 产地不能为空
     */
    public static final String MESSAGE_ITEM_ORIGIN_NULL = "产地不能为空";

    /**
     * 商品状态不能为空
     */
    public static final String MESSAGE_ITEM_STATUS_NULL = "商品状态不能为空";

    /**
     * 类目不能为空
     */
    public static final String MESSAGE_ITEM_CATEGORY_NULL = "类目不能为空";

    /**
     * 规格非法，最多100个字符
     */
    public static final String MESSAGE_ITEM_SPECIFICATION_LENGTH_ILLEGAL = "规格非法，最多100个字符";

    /**
     * 批次不能为空
     */
    public static final String MESSAGE_ITEM_BATCH_NULL = "批次不能为空";

    /**
     * 权重不能为空
     */
    public static final String MESSAGE_ITEM_WEIGHT_NULL = "权重不能为空";

    /**
     * 采集备注非法，最多500个字符
     */
    public static final String MESSAGE_ITEM_COLLECTION_REMARK_LENGTH_ILLEGAL = "采集备注非法，最多500个字符";

    //////////////////// 商品基础资料，End ////////////////////

    /**
     * 商品竞价信息不能为空
     */
    public static final String MESSAGE_ITEM_BIDDING_INFO_NULL = "商品竞价信息不能为空";

    /**
     * 商品价格信息不能为空
     */
    public static final String MESSAGE_ITEM_PRICE_INFO_NULL = "商品价格信息不能为空";

    /**
     * 属性组信息不能为空
     */
    public static final String MESSAGE_ATTRIBUTE_INFO_NULL = "属性信息不能为空";

    /**
     * 属性组信息不能为空
     */
    public static final String MESSAGE_ATTRIBUTE_GROUP_INFO_NULL = "属性组信息不能为空";

    /**
     * 属性组属性信息不能为空
     */
    public static final String MESSAGE_ATTRIBUTE_GROUP_ATTRIBUTE_INFO_NULL = "属性组属性信息不能为空";

    /**
     * 成功获取仓库类型列表
     */
    public static final String MESSAGE_GET_WAREHOUSE_TYPE_LIST_SUCCESS = "成功获取仓库类型列表";

    /**
     * 获取仓库类型列表失败
     */
    public static final String MESSAGE_GET_WAREHOUSE_TYPE_LIST_FAIL = "获取仓库类型列表失败";

    /**
     * 获取仓库类型列表失败
     */
    public static final String MESSAGE_WAREHOUSE_TYPE_INFO_NULL = "仓库类型信息不能为空";

    /**
     * 确认采购成功
     */
    public static final String MESSAGE_CONFIRM_PURCHASE_SUCCESS = "确认采购成功";

    /**
     * 确认采购失败
     */
    public static final String MESSAGE_CONFIRM_PURCHASE_FAIL = "确认采购失败";

    /**
     * 生成商品货号成功
     */
    public static final String MESSAGE_GENERATE_ITEM_NO_SUCCESS = "生成商品货号成功";

    /**
     * 该商品已确认采购
     */
    public static final String MESSAGE_ITEM_PURCHASE_CONFIRMED = "该商品已确认采购";

    /**
     * 修改类目失败
     */
    public static final String MESSAGE_UPDATE_CATEGORY_FAIL = "修改类目失败";

    /**
     * 请先确认采购
     */
    public static final String MESSAGE_FIRST_CONFIRM_PURCHASE = "请先确认采购";

    /**
     * 找不到该商品的价格
     */
    public static final String MESSAGE_ITEM_NOT_FOUND_PRICE = "找不到该商品的价格";

    /**
     * 该商品价格异常
     */
    public static final String MESSAGE_ITEM_PRICE_EXCEPTION = "该商品价格异常";

    /**
     * 该商品销售状态
     */
    public static final String MESSAGE_ITEM_STATE_EXCEPTION = "该商品价格异常";

    /**
     * 找不到该商品报关信息
     */
    public static final String MESSAGE_ITEM_NOT_FOUND_CUSTOM = "找不到该商品报关信息";

    /**
     * 该商品报关信息异常
     */
    public static final String MESSAGE_ITEM_CUSTOM_EXCEPTION = "该商品报关信息异常";

    /**
     * 商品报关信息不能为空
     */
    public static final String MESSAGE_ITEM_CUSTOM_INFO_NULL = "商品报关信息不能为空";

    /**
     * 找不到该商品竞价信息
     */
    public static final String MESSAGE_ITEM_NOT_FOUND_BIDDING = "找不到该商品竞价信息";

    /**
     * 找不到该商品采集信息
     */
    public static final String MESSAGE_ITEM_NOT_FOUND_COLLECTION = "找不到该商品采集信息";

    /**
     * 找不到该商品内容
     */
    public static final String MESSAGE_ITEM_NOT_FOUND_CONTENT = "找不到该商品内容";

    /**
     * 采编不能为空
     */
    public static final String MESSAGE_ITEM_EDITOR_NULL = "采编不能为空";

    /**
     * 找不到该属性组下的属性
     */
    public static final String MESSAGE_NOT_FOUND_ATTRIBUTE = "找不到该属性组下的属性";

    /**
     * 找不到商品属性值
     */
    public static final String MESSAGE_NOT_FOUND_ITEM_ATTRIBUTE = "找不到商品属性值";

    /**
     * 找不到商品
     */
    public static final String MESSAGE_NOT_FOUND_ITEM = "找不到商品";

    /**
     * 找不到商品类目
     */
    public static final String MESSAGE_NOT_FOUND_ITEM_CATEGORY = "找不到商品类目";

    /**
     * 找不到属性组属性
     */
    public static final String MESSAGE_NOT_FOUND_ATTRIBUTE_GROUP_ATTRIBUTE = "找不到属性组属性";

    /**
     * 商品内容不能为空
     */
    public static final String MESSAGE_ITEM_CONTENT_NULL = "商品内容不能为空";

    /**
     * 请先选择属性值
     */
    public static final String MESSAGE_SELECT_ATTRIBUTE_FIRST = "请先选择属性值";

    /**
     * 商品ID不存在
     */
    public static final String MESSAGE_ITEM_NOT_EXIST = "商品不存在";


    /**
     * 修改仓库失败
     */
    public static final String MESSAGE_EDIT_WAREHOUSE_FAIL = "修改仓库失败";

    /**
     * 类目序号已经存在
     */
    public static final String MESSAGE_SEQUENCE_EXIST = "类目序号已经存在";


    /**
     * 市场价不能为空
     */
    public static final String MESSAGE_ITEM_MARKET_PRICE_NULL = "市场价不能为空";

    /**
     * 市场价超过最大值
     */
    public static final String MESSAGE_ITEM_MARKET_PRICE_EXCEED_MAX = "市场价超过最大值";

    /**
     * 售价不能为空
     */
    public static final String MESSAGE_ITEM_SELLING_PRICE_NULL = "售价不能为空";

    /**
     * 售价超过最大值
     */
    public static final String MESSAGE_ITEM_SELLING_PRICE_EXCEED_MAX = "售价超过最大值";

    /**
     * 供货价不能为空
     */
    public static final String MESSAGE_ITEM_SUPPLY_PRICE_NULL = "供货价不能为空";

    /**
     * 报关品名最多50个字符
     */
    public static final String MESSAGE_ITEM_SUPPLY_PRICE_EXCEED_MAX = "报关品名最多50个字符";


    /**
     * 保存商品失败
     */
    public static final String MESSAGE_ITEM_SAVE_ITEM_FAIL = "保存商品失败";
    /**
     * 保存商品成功，保存商品竞价失败
     */
    public static final String MESSAGE_ITEM_SAVE_ITEM_BIDDING_FAIL = "保存商品成功，保存商品竞价失败";

    /**
     * 保存商品价格失败
     */
    public static final String MESSAGE_ITEM_SAVE_ITEM_PRICE_FAIL = "保存商品价格失败";

    /**
     * 保存报关失败
     */
    public static final String MESSAGE_ITEM_SAVE_CUSTOMS_FAIL = "保存商品价格失败";

    /**
     * 请选择第三类目
     */
    public static final String MESSAGE_PLEASE_SELECT_THREE_CATEGORY = "请选择第三类目";

    /**
     * 采集完成后才能预发布
     */
    public static final String MESSAGE_PLEASE_FIRST_COLLECTION_COMPLETED = "采集完成后才能预发布";

    /**
     * 条形码%s已存在
     */
    public static final String MESSAGE_ITEM_BARCODE_EXIST_FORMT = "条形码 %s 已存在";

    /**
     * 商品ID重复
     */
    public static final String ITEM_IDX_REPEAT = "商品ID重复";
}
