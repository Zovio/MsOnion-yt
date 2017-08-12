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
 * @Title: MsOnionMessageConstants.java
 * @Package: cc.msonion.carambola.parent.common.constants
 * @Description: 信息提示常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午7:53:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月26日 下午7:53:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: MsOnionMessageConstants
 * @Description: 信息提示常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月14日 下午7:53:58
 */
public final class MsOnionMessageConstants {

    private MsOnionMessageConstants() {
    }

    /**
     * Api版本非法
     */
    public static final String MESSAGE_PARAMETER_APIVERSION_ILLEGAL = "Api版本参数非法";

    /**
     * 请求的Api版本非法
     */
    public static final String MESSAGE_PARAMETER_APIVERSION_REQUEST_APIVERSION_ILLEGAL = "请求的Api版本参数非法";

    /**
     * Api版本不可以为空
     */
    public static final String MESSAGE_PARAMETER_APIVERSION_NULL = "Api版本不可以为空";

    /**
     * 主键idx集合参数非法
     */
    public static final String MESSAGE_PARAMETER_IDXS_ILLEGAL = "主键idx集合参数非法";

    /**
     * 主键idx参数非法
     */
    public static final String MESSAGE_PARAMETER_IDX_ILLEGAL = "主键idx参数非法";

    /**
     * 动态设置idxCode值异常
     */
    public static final String MESSAGE_SET_IDX_CODE_ERROR = "动态设置idxCode值异常";

    /**
     * POJO为null或主键idx参数非法
     */
    public static final String MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL = "目标POJO实例为null或主键idx参数非法";

    /**
     * 目标POJO的Example实例对象参数为null
     */
    public static final String MESSAGE_PARAMETER_EXAMPLE_ILLEGAL = "目标POJO的Example实例对象参数为null";

    /**
     * 数据库表对应status列的值，非法
     */
    public static final String MESSAGE_SQL_COLUMN_STATUS_AVAILABLE_VALUES_ILLEGAL = "数据库表对应status列的值，非法";

    /**
     * 数据库表密码列名称非法
     */
    public static final String MESSAGE_SQL_COLUMN_PASSWORD_NAMES_ILLEGAL = "数据库表密码列名称非法";

    /**
     * 分页查询列表中，把密码设置为null的POJO名称非法
     */
    public static final String MESSAGE_POJO_SECURITY_FOR_PASSWORD_NAMES_ILLEGAL = "分页查询列表中，把密码设置为null的POJO名称非法";

    /**
     * 新增（插入、保存）成功
     */
    public static final String MESSAGE_SAVE_SUCCESS = "成功";

    /**
     * 新增（插入、保存）失败
     */
    public static final String MESSAGE_SAVE_FAILURE = "失败";

    /**
     * 查询成功
     */
    public static final String MESSAGE_QUERY_SUCCESS = "成功";

    /**
     * 查询失败
     */
    public static final String MESSAGE_QUERY_FAILURE = "失败";

    /**
     * 查询记录数成功
     */
    public static final String MESSAGE_COUNT_SUCCESS = "成功";

    /**
     * 没有查询到记录数
     */
    public static final String MESSAGE_COUNT_FAILURE = "没有记录";

    /**
     * 更新成功
     */
    public static final String MESSAGE_UPDATE_SUCCESS = "成功";

    /**
     * 更新失败
     */
    public static final String MESSAGE_UPDATE_FAILURE = "失败";

    /**
     * 删除成功
     */
    public static final String MESSAGE_DELETE_SUCCESS = "成功";

    /**
     * 删除失败
     */
    public static final String MESSAGE_DELETE_FAILURE = "失败";

    /**
     * 禁用成功
     */
    public static final String MESSAGE_DISABLE_SUCCESS = "成功";

    /**
     * 禁用失败
     */
    public static final String MESSAGE_DISABLE_FAILURE = "失败";

    /**
     * 激活成功
     */
    public static final String MESSAGE_ENABLE_SUCCESS = "成功";

    /**
     * 激活失败
     */
    public static final String MESSAGE_ENABLE_FAILURE = "失败";

    /**
     * 批量插入目标POJO的集合非法
     */
    public static final String MESSAGE_PARAMETER_POJO_LIST_ILLEGAL = "批量插入目标POJO的集合非法";

    /**
     * 分页参数非法，必须为MsOnionPagingParameter及其子类
     */
    public static final String MESSAGE_PARAMETER_PAGING_ILLEGAL = "分页参数非法，必须为MsOnionPagingParameter及其子类";

    /**
     * Cookie名称非法
     */
    public static final String MESSAGE_COOKIE_NAME_ILLEGAL = "Cookie名称非法";

    /**
     * 参数非法，未知参数类型
     */
    public static final String MESSAGE_PARAMETER_TYPE_ILLEGAL = "参数非法，未知参数类型";

    /**
     * 状态值参数非法
     */
    public static final String MESSAGE_PARAMETER_SQL_STATUS_ILLEGAL = "状态值参数非法";

    //////////////////// Redis 缓存相关 ， Begin  //////////////////////////////

    /**
     * 参数key非法
     */
    public static final String MESSAGE_PARAMETER_REDIS_KEY_ILLEGAL = "参数key非法";

    /**
     * 参数key非法，不符合规则，没有包括
     */
    public static final String MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL = "参数key非法，不符合规则，没有包括";

    /**
     * 参数key非法，不符合规则，不能包括 "::"
     */
    public static final String MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_MULTI_COLON = "参数key非法，不符合规则，不能包括";

    /**
     * 参数key非法，不符合规则，第一个字符不能为 ":"
     */
    public static final String MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_STARTS_WITH = "参数key非法，不符合规则，第一个字符不能为";

    /**
     * 参数key非法，不符合规则，第后字符不能为 ":"
     */
    public static final String MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_ENDS_WITH = "参数key非法，不符合规则，第后字符不能为";

    /**
     * 参数key非法，不符合规则：大写英文字母 + 数字 + "_" + "-" + ":"
     */
    public static final String MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_REGEX = "参数key非法，不符合规则：大写英文字母 + 数字 + \"_\" + \"-\" + \":\"";

    /**
     * 参数value非法
     */
    public static final String MESSAGE_PARAMETER_REDIS_VALUE_ILLEGAL = "参数value非法";

    /**
     * 参数seconds非法
     */
    public static final String MESSAGE_PARAMETER_REDIS_SECONDS_ILLEGAL = "参数seconds非法";

    /**
     * 参数field非法
     */
    public static final String MESSAGE_PARAMETER_REDIS_FIELD_ILLEGAL = "参数field非法";


    /**
     * 参数member非法
     */
    public static final String MESSAGE_PARAMETER_REDIS_MEMBER_ILLEGAL = "参数member非法";

    /**
     * 参数hash非法
     */
    public static final String MESSAGE_PARAMETER_REDIS_HASH_ILLEGAL = "参数hash非法";

    //////////////////// Redis 缓存相关 ， End  //////////////////////////////

    //////////////////// 图片相关 ， Begin  //////////////////////////////

    /**
     * 图片路径非法
     */
    public static final String MESSAGE_PARAMETER_IMAGE_PATH_ILLEGAL = "图片路径非法";

    /**
     * 图片保存路径非法
     */
    public static final String MESSAGE_PARAMETER_IMAGE_SAVE_PATH_ILLEGAL = "图片保存路径非法";

    /**
     * 图片资源非法
     */
    public static final String MESSAGE_PARAMETER_IMAGE_SOURCE_ILLEGAL = "图片资源非法";

    /**
     * Properties资源文件非法
     */
    public static final String MESSAGE_PARAMETER_PROPERTIES_NAME_ILLEGAL = "Properties资源文件非法";

    //////////////////// 图片相关 ， End  //////////////////////////////

    /**
     * Netty服务配置的协议非法，只能支持https、http
     */
    public static final String MESSAGE_NETTY_PROTOCOL_ILLEGAL = "Netty服务配置的协议非法，只能支持https、http";

    /**
     * Netty上传文件资源根目录非法
     */
    public static final String MESSAGE_PARAMETER_NETTY_UPLOAD_ROOT_DIR_ILLEGAL = "Netty上传文件资源根目录非法";

    /**
     * 文件名非法
     */
    public static final String MESSAGE_FILENAME_ILLEGAL = "文件名非法";

    /**
     * 找不到父级
     */
    public static final String MESSAGE_PARENT_NULL = "找不到父级";


    ////////////////////////// 搜索引擎 ### Begin #### //////////////////////////////

    /**
     * 搜索条件参数非法
     */
    public static final String MESSAGE_PARAMETER_CONDITION_ILLEGAL = "搜索条件参数非法";

    /**
     * 搜索成功
     */
    public static final String MESSAGE_SEARCH_SUCCESS = "搜索成功";

    /**
     * 搜索失败
     */
    public static final String MESSAGE_SEARCH_FAILURE = "搜索失败";

    ////////////////////////// 搜索引擎 ### End #### //////////////////////////////

    /**
     * 非法操作
     */
    public static final String MESSAGE_ILLEGAL_OPERATION = "非法操作";

    ////////////////////  批量操作 ### Begin ### ////////////////////////////

    /**
     * 批量操作，list参数非法
     */
    public static final String MESSAGE_PARAMETER_LIST_ILLEGAL = "批量操作，list参数非法";

    /**
     * 批量新增失败
     */
    public static final String MESSAGE_BATCH_SAVE_FAILURE = "批量新增失败";

    /**
     * 批量更新失败
     */
    public static final String MESSAGE_BATCH_UPDATE_FAILURE = "批量更新失败";

    ////////////////////  批量操作 ### End ### ////////////////////////////

}
