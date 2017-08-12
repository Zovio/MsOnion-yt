/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 *
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
 *
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 *
 * 洋桃商城：http://www.yunyangtao.com
 *
 */
package cc.msonion.carambola.member.common.constants;
/**
 * @ClassName: MsOnionMessageConstants
 * @Description: 消息常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午8:52:32
 *
 */
public final class MessageConstants {

	private MessageConstants() { }

	//////////////////// 用户名（成员名）和密码等信息，Begin ////////////////////

	/**
	 * 用户名不能为空
	 */
	public static final String MESSAGE_MEMBER_NAME_NULL = "用户名不能为空";

	/**
	 * 密码不能为空
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_NULL = "密码不能为空";

	/**
	 * 用户名或密码错误
	 */
	public static final String MESSAGE_MEMBER_NAME_OR_PASSWORD_ERROR = "用户名或密码错误";

	/**
	 * 用户信息不能空
	 */
	public static final String MESSAGE_MEMBER_INFO_NULL = "用户信息不能空";

	/**
	 * 用户名非法，4-20个字符
	 */
	public static final String MESSAGE_MEMBER_NAME_LENGTH_ILLEGAL = "成员名非法，4-20个字符";

	/**
	 * 成员名非法，包含敏感词
	 */
	public static final String MESSAGE_MEMBER_NAME_ILLEGAL_HASH_SENSITIVE_WORDS = "成员名称非法，包含敏感词";

	/**
	 * 密码非法，6-20个字符
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_LENGTH_ILLEGAL = "密码非法，6-20个字符";

	/**
	 * 您的密码与账号或者邮箱太重合，有被盗风险，请更换
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UNFRIENDLY = "您的密码与账号或者邮箱太重合，有被盗风险，请更换";

	/**
	 * 密码必须是字母或符号和数字的结合，请更换
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_COMPOSE_ILLEGAL = "密码必须是字母或符号和数字的结合，请更换";

	/**
	 * Email非法，6-30个字符
	 */
	public static final String MESSAGE_MEMBER_EMAIL_LENGTH_ILLEGAL = "Email非法，6-30个字符";

	/**
	 * Email不能为空
	 */
	public static final String MESSAGE_MEMBER_EMAIL_NULL = "Email不能为空";
	/**
	 * Email非法，格式错误
	 */
	public static final String MESSAGE_MEMBER_EMAIL_FORMAT_ILLEGAL = "Email非法，格式错误";
	/**
	 * Email非法
	 */
	public static final String MESSAGE_MEMBER_EMAIL_ILLEGAL = "Email非法";

	/**
	 * 手机号码非法
	 */
	public static final String MESSAGE_MEMBER_PHONE_ILLEGAL = "手机号码非法";

	/**
	 * 手机号码不能为空
	 */
	public static final String MESSAGE_MEMBER_PHONE_NULL = "手机号码不能为空";

	/**
	 * 当前成员账号未激活
	 */
	public static final String MESSAGE_MEMBER_ACCOUNT_DISABLE = "当前成员账号未激活";

	/**
	 * 电话号码长度非法
	 */
	public static final String MESSAGE_MEMBER_TEL_LENGTH_ILLEGAL = "电话号码长度非法";

	/**
	 * 电话号码非法
	 */
	public static final String MESSAGE_MEMBER_TEL_ILLEGAL = "电话号码非法";

	/**
	 * 成员名全非法，4-20个字符
	 */
	public static final String MESSAGE_MEMBER_FULLNAME_LENGTH_ILLEGAL = "成员全名非法，4-20个字符";

	/**
	 * 成员全名非法，包含敏感词
	 */
	public static final String MESSAGE_MEMBER_FULLNAME_ILLEGAL_HASH_SENSITIVE_WORDS = "成员全名非法，包含敏感词";

	/**
	 * 备注内容非法，最多50个字符
	 */
	public static final String MESSAGE_MEMBER_REMARK_LENGTH_ILLEGAL = "备注内容非法，最多50个字符";

	/**
	 * 备注内容非法，包含敏感词
	 */
	public static final String MESSAGE_MEMBER_REMARK_ILLEGAL_HASH_SENSITIVE_WORDS = "备注内容非法，包含敏感词";


	/**
	 * 扩展内容非法，最多100个字符
	 */
	public static final String MESSAGE_MEMBER_EXT_LENGTH_ILLEGAL = "扩展内容非法，最多100个字符";

	/**
	 * 扩展内容非法，包含敏感词
	 */
	public static final String MESSAGE_MEMBER_EX_ILLEGAL_HASH_SENSITIVE_WORDS = "扩展内容非法，包含敏感词";

	/**
	 * 参数非法，未知参数类型
	 */
	public static final String MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL = "参数非法，未知参数类型";

	/**
	 * 修改密码失败
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE = "修改密码失败";

	/**
	 * 修改密码成功
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_SUCCESS = "修改密码成功";

	/**
	 * 修改密码失败，账号原始密码错误
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_OLD_PASSWORD_ERROR = "修改密码失败，账号原始密码错误";

	/**
	 * 成员账号非法
	 */
	public static final String MESSAGE_MEMBER_IDX_ILLEGAL = "成员账号非法";

	/**
	 * 原始密码非法
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_OLD_PASSWORD_ILLEGAL = "原始密码非法";

	/**
	 * 新密码非法
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_NEW_PASSWORD_ILLEGAL = "新密码非法";
	/**
	 * 修改密码失败，邮箱错误
	 */
	public static final String MESSAGE_MEMBER_PASSWORD_UPDATE_FAILURE_EMAIL_ERROR = "修改密码失败，邮箱错误";


	/**
	 * 删除成员失败，非法主键idx
	 */
	public static final String MESSAGE_MEMBER_DELETE_BY_IDX_ILLEGAL = "删除成员失败，非法主键idx";
	/**
	 * 删除成员成功
	 */
	public static final String MESSAGE_MEMBER_DELETE_SUCCESS = "删除成员成功";
	/**
	 * 删除成员失败
	 */
	public static final String MESSAGE_MEMBER_DELETE_FAILURE = "删除成员失败，成员不存在";

	/**
	 * 更新成员信息失败
	 */
	public static final String MESSAGE_MEMBER_UPDATE_FULLNAME = "更新成员信息失败";

	/**
	 * 更新成员信息成功
	 */
	public static final String MESSAGE_MEMBER_UPDATE_SUCCESS = "更新成员信息成功";

	//////////////////// 成员名（成员名）和密码等信息，End ////////////////////


	//////////////////// Token，Begin  ////////////////////

	/**
	 * 登录失败
	 */
	public static final String MESSAGE_TOKEN_ERROR = "登录失败";

	/**
	 * 非法参数，Token不能为空
	 */
	public static final String MESSAGE_TOKEN_ILLEGAL = "非法参数，Token不能为空";

	//////////////////// Token，End ////////////////////


	//////////////////// Logout（注销），Begin  ////////////////////
	/**
	 * MESSAGE_LOGOUT_FAILURE_TOKEN_NULL
	 */
	public static final String MESSAGE_LOGOUT_FAILURE_TOKEN_NULL = "注销失败，Token不能为空";

	/**
	 * 注销失败，成员信息不存在
	 */
	public static final String MESSAGE_LOGOUT_FAILURE_MEMBER_INFO_ERROR = "注销失败，成员信息不存在";

	/**
	 * 注销成功
	 */
	public static final String MESSAGE_LOGOUT_SUCCESS = "注销成功";

	//////////////////// Logout（注销），End  ////////////////////


	//////////////////// Api版本号，Begin  ////////////////////

	/**
	 * API版本号，不可以为空
	 */
	public static final String MESSAGE_API_VERSION_NULL = "API版本号，不可以为空";
	//////////////////// Api版本号，End  ////////////////////
	/**
	 * 目标POJO实例为null
	 */
	public static final String MESSAGE_INFO_NULL = "目标POJO实例为null";

	/**
	 * 操作失败
	 */
	public static final String MESSAGE_INFO_ERROR = "操作失败";







}
