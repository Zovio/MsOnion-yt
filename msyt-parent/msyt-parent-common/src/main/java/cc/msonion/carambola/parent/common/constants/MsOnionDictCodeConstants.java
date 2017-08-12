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


package cc.msonion.carambola.parent.common.constants;

/**
 * @Title: MsOnionDictCodeConstants.java
 * @Package: cc.msonion.carambola.parent.common.constants
 * @Description: 数据字典 字典编码常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月25日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月25日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

/**
 * @ClassName: MsOnionDictCodeConstants
 * @Description: 数据字典 字典编码常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月25日
 */
public final class MsOnionDictCodeConstants {

    private MsOnionDictCodeConstants() {
    }

    /**
     * 数据库中 状态 0：已删除 1：正常 2：禁用
     */
    public static final String MSYT_STATUS = "MSYT_STATUS";


    /**
     * 登录状态 1：成功 2：失败
     */
    public static final String MSYT_LOGIN_STATUS = "MSYT_LOGIN_STATUS";

    /**
     * 是否 1：是 0：否
     */
    public static final String MSYT_YES_NO = "MSYT_YES_NO";

    /**
     * 性别 1：男， 2：女
     */
    public static final String MSYT_SEX = "MSYT_SEX";

    /**
     * 采购状态 1：确认采购，2：未确认采购
     */
    public static final String PURCHASE_STATUS = "PURCHASE_STATUS";

    /**
     * 采集状态 1：未采集，2：采集完成，3：编辑完成，4：文案通过
     */
    public static final String COLLECTION_STATUS = "COLLECTION_STATUS";

    /**
     * 采集状态 1：采集相册，2：正式相册
     */
    public static final String MSYT_ABLUM_TYPE = "MSYT_ABLUM_TYPE";

    /**
     * 首页顶部显示 默认6
     */
    public static final String MSYT_TOPSHOW_NUM = "MSYT_TOPSHOW_NUM";

    /**
     * 仓库类型 20：20仓，30：30仓
     */
    public static final String WAREHOUSE_TYPE = "WAREHO_TYPE";



    /**
     * 商品日志修改位置,对应ItemModifyLocationUtils
     */
    public static final String ITEM_MODIFY_LOCATION = "ITEM_MODIFY_LOCATION";


    /**
     * 发布状态 0-未发布 1-已发布
     */
    public static final String PUBLISH_STATUS = "PUBLISH_STATUS";


    /**
     * 是否包邮 0-否 1-是
     */
    public static final String ISFREESHIPPING_STATUS = "ISFREESHIPPING_STATUS";


    /**
     * 是否一键下单 0-否 1-是
     */
    public static final String ISKEYORDER_STATUS = "ISKEYORDER_STATUS";



    /**
     * 是否含有消费税 0-否 1-是
     */
    public static final String ISCONTAINEXCISE_STATUS = "ISCONTAINEXCISE_STATUS";

    /**
     *  发布渠道（0：全部；1：洋桃；2：洋葱）
     */
    public static final String PUBLISH_CHANNEL = "PUBLISH_CHANNEL";

    /**
     * 验证码图片类型
     */
    public static final String VERIFY_CODE_PICTURE_TYPE = "VERIFY_CODE_PICTURE_TYPE";

    /**
     * 文件模板类别
     */
    public static final String TEMPLATE_CATEGORY = "TEMPLATE_CATEGORY";


    /**
     * 应用平台
     */
    public static final String APP_PLATFORM = "APP_PLATFORM";

}
