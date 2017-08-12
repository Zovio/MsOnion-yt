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

package cc.msonion.carambola.manager.common.constants;


/**
 * @Title: MsOnionMessageConstants.java
 * @Package: cc.msonion.carambola.manager.common.constants
 * @Description: 提示消息常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月28日 下午9:08:20
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月28日 下午9:08:20
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：服务器忙的提示
 */

/**
 * @ClassName: MsOnionMessageConstants
 * @Description: 提示消息常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月28日 下午9:08:20
 */
public final class MessageConstants {

    private MessageConstants() {
    }

    /**
     * 注册失败，服务器忙，请稍后重试！！！
     */
    public static final String MESSAGE_MEMBER_REGISTER_FAILURE = "注册失败，服务器忙，请稍后重试！！！";

    /**
     * 服务器忙，请稍后重试 ...
     */
    public static final String MESSAGE_SERVER_ERROR = "服务器忙，请稍后重试 ...";

    /**
     * 参数非法
     */
    public static final String MESSAGE_PARAMETER_ILLEGAL = "参数非法";

    /**
     * 没有任何数据
     */
    public static final String DATA_NONE_MEMBER = "没有任何数据";

    /**
     * OK
     */
    public static final String MESSAGE_OK = "OK";

    /**
     * 查询用户不存在
     */
    public static final String MESSAGE_DATA_NON_EXISTENT = "查询数据不存在";

    /**
     * 验证码错误
     */
    public static final String MESSAGE_VERIFICATION_CODE_ERROR = "请点击图片旋转至正向朝上";

    /**
     * 已确认采购
     */
    public static final String MESSAGE_ITEM_PURCHASE_CONFIRMED = "已确认采购，不可再次确认";

    /**
     * 修改密码失败，链接已失效，请重新申请。
     */
    public static final String MESSAGE_EMAIL_TOKEN_ERROR = "修改密码失败，链接已失效，请重新申请！！！";

    /**
     * 密码已经修改，链接无效！！！
     */
    public static final String MESSAGE_EMAIL_TOKEN_USED = "密码已经修改，链接无效！！！";

    /**
     * 文件不存在
     */
    public static final String MESSAGE_EXCEL_FILE_NOT_EXIST = "文件不存在";

    /**
     * 同步发布出错
     */
    public static final String MESSAGE_ITEM_SYNC_ERROR = "商品ID %d 发布失败";

    /**
     * 同步ERP出错
     */
    public static final String MESSAGE_ITEM_SYNC_ERP_ERROR = "商品ID %d 同步失败";


    /**
     * 资源下载，默认自带的表头属性
     */
    public static final String DEFAULT_ATTR_STR = "itemIdx|cc.msonion.carambola.item.pojo.ItemOfficial,itemNo|cc.msonion.carambola.item.pojo.ItemBasicOfficial,";

    /**
     * 资源下载，默认自带的表头
     */
    public static final String DEFAULT_ATTR_NAME = "商品ID,货号,";

    /**
     * 资源下载，默认自带的表头
     */
    public static final String UEDITOR_UP_PATH_ERROR = "文件目录不能为空";

    /**
     * 保存并发布失败，请先发布该商品
     */
    public static final String SAVE_AND_PUBLISH_FAIL = "请先到预发布商品页面发布该商品";



}
