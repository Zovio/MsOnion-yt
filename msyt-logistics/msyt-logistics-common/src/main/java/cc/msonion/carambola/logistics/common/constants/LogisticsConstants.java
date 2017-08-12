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


package cc.msonion.carambola.logistics.common.constants;

/**
 * @Title: LogisticsConstants.java
 * @Package: cc.msonion.carambola.logistics.common.constants
 * @Description: 物流中心常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月22日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月22日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

/**
 * @ClassName: LogisticsConstants
 * @Description: 物流中心常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月22日
 */
public final class LogisticsConstants {

    private LogisticsConstants() {

    }

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
     * 正式商品报关信息已存在
     */
    public static final String MESSAGE_ITEM_CUSTOM_OFFICIAL_EXIST = "正式商品报关信息已存在";

    /**
     * 报关品名长度
     */
    public static final int MESSAGE_ITEM_CUSTOM_NAME_MAX_LENGTH = 50;
    /**
     * 报关品名最多50个字符
     */
    public static final String MESSAGE_ITEM_CUSTOM_NAME_EXCEED_LENGTH = "报关品名最多50个字符";
    /**
     * 第一数量不能为空
     */
    public static final String MESSAGE_ITEM_FIRST_QUANTITY_NULL = "第一数量不能为空";
    /**
     * 第一数量最大值
     */
    public static final int MESSAGE_ITEM_FIRST_QUANTITY_MAX = 2147483647;
    /**
     * 第一数量超过最大值
     */
    public static final String MESSAGE_ITEM_FIRST_QUANTITY_EXCEED_MAX = "第一数量超过最大值";


    /**
     * 第二数量不能为空
     */
    public static final String MESSAGE_ITEM_SECOND_QUANTITY_NULL = "第二数量不能为空";
    /**
     * 第二数量最大值
     */
    public static final int MESSAGE_ITEM_SECOND_QUANTITY_MAX = 2147483647;
    /**
     * 第二数量超过最大值
     */
    public static final String MESSAGE_ITEM_SECOND_QUANTITY_EXCEED_MAX = "第二数量超过最大值";


    /**
     * 第一单位参数非法
     */
    public static final String MESSAGE_ITEM_FIRST_PARAM_ILLEGAL = "第一单位参数非法";

    /**
     * 第一单位查询不到
     */
    public static final String MESSAGE_ITEM_FIRST_UNIT_NOT_FOUND = "第一单位查询不到";

    /**
     * 第二单位参数非法
     */
    public static final String MESSAGE_ITEM_SECOND_PARAM_ILLEGAL = "第二单位参数非法";

    /**
     * 第二单位查询不到
     */
    public static final String MESSAGE_ITEM_SECOND_UNIT_NOT_FOUND = "第二单位查询不到";

    /**
     * 行邮税率不能为空
     */
    public static final String MESSAGE_ITEM_POSTALARTICLESTAXRATE_NULL = "行邮税率不能为空";

    /**
     * 行邮税率最大值
     */
    public static final int MESSAGE_ITEM_POSTALARTICLESTAXRATE_MAX = 100000;

    /**
     * 行邮税率超过最大值
     */
    public static final String MESSAGE_ITEM_POSTALARTICLESTAXRATE_EXCEED_MAX = "行邮税率不能超过1";


    /**
     * 跨境税率不能为空
     */
    public static final String MESSAGE_ITEM_CROSSBORDERTAXRATE_NULL = "跨境税率不能为空";

    /**
     * 跨境税率最大值
     */
    public static final int MESSAGE_ITEM_CROSSBORDERTAXRATE_MAX = 100000;

    /**
     * 跨境税率超过最大值
     */
    public static final String MESSAGE_ITEM_CROSSBORDERTAXRATE_EXCEED_MAX = "跨境税率不能超过1";

    /**
     * 价格最大值
     */
    public static final int MESSAGE_ITEM_PRICE_MAX = 2147483647;


    /**
     * BC价不能为空
     */
    public static final String MESSAGE_ITEM_BCPRICE_NULL = "BC价不能为空";

    /**
     * BC价超过最大值
     */
    public static final String MESSAGE_ITEM_BCPRICE_EXCEED_MAX = "BC价超过最大值";


    /**
     * HS编码不能为空
     */
    public static final String MESSAGE_ITEM_HSCODE_NULL = "HS编码不能为空";

    /**
     * HS编码最大值
     */
    public static final long MESSAGE_ITEM_HSCODE_MAX = 9999999999L;

    /**
     * HS编码超过最大值
     */
    public static final String MESSAGE_ITEM_HSCODE_EXCEED_MAX = "HS编码超过最大值";

    /**
     * 毛重长度最大值
     */
    public static final long MESSAGE_ITEM_GROSSWEIGHT_MAX_LENGTH = 20;

    /**
     * 毛重长度超过最大值
     */
    public static final String MESSAGE_ITEM_GROSSWEIGHT_EXCEED_MAX = "毛重长度不能超过20";


    /**
     * 行邮税号不能为空
     */
    public static final String MESSAGE_ITEM_POSTALARTICLESTAXNUMBER_NULL = "行邮税号不能为空";

    /**
     * 行邮税号最大值
     */
    public static final long MESSAGE_ITEM_POSTALARTICLESTAXNUMBER_MAX = 9999999999L;

    /**
     * 行邮税号超过最大值
     */
    public static final String MESSAGE_ITEM_POSTALARTICLESTAXNUMBER_EXCEED_MAX = "行邮税号超过最大值";


    /**
     * 是否含有消费税不能为空
     */
    public static final String MESSAGE_ITEM_ISCONTAINEXCISE_NULL = "是否含有消费税不能为空不能为空";

    /**
     * 是否含有消费税最大值
     */
    public static final short MESSAGE_ITEM_ISCONTAINEXCISE_MAX = 9;

    /**
     * 是否含有消费税超过最大值
     */
    public static final String MESSAGE_ITEM_ISCONTAINEXCISE_EXCEED_MAX = "是否含有消费税不能为空超过最大值";


    /**
     * 保存商品报关数据失败
     */
    public static final String MESSAGE_BATCH_CUSTOM_ERROR = "保存商品报关数据失败";
}
