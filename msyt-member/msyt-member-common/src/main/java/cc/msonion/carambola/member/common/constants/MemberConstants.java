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
 * @ClassName: MemberConstants
 * @Description: 成员常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午8:09:06
 *
 */
public final class MemberConstants {

	private MemberConstants() { }

	/**
	 * V2.0.0
	 */
	public static final String API_VERSION = "V2.0.0";

	//////////////////// Token，Begin  //////////////////////////////

	/**
	 * 异常状态码：Token非法
	 */
//	public static final int EXCEPTION_STATUS_TOKEN_ILLEGAL = 1000100;

	//////////////////// Token，End  //////////////////////////////


	//////////////////// 注销（Logout）、登录，Begin  //////////////////////////////

//	/**
//	 * 状态码：注销失败
//	 */
//	public static final int STATUS_LOGOUT_FAILURE = 1000200;
//	/**
//	 * 状态码：登录失败
//	 */
//	public static final int STATUS_LOGIN_FAILURE = 1000300;

	//////////////////// 注销（Logout）、登录，End  //////////////////////////////


	//////////////////// 用户名（成员名）和密码等基本信息，Begin  //////////////////////////////

	/**
	 * @Fields MEMBER_NAME_MIN_LENGTH : 用户名最小长度，4个字符
	 */
	public static final int MEMBER_NAME_MIN_LENGTH = 4;

	/**
	 * @Fields MEMBER_NAME_MAX_LENGTH : 用户名最大长度，20个字符
	 */
	public static final int MEMBER_NAME_MAX_LENGTH = 20;

	/**
	 * @Fields MEMBER_PASSWORD_MIN_LENGTH : 密码最小长度，6 个字符
	 */
	public static final int MEMBER_PASSWORD_MIN_LENGTH = 6;

	/**
	 * @Fields MEMBER_PASSWORD_MAX_LENGTH : 密码最大长度，20个字符
	 */
	public static final int MEMBER_PASSWORD_MAX_LENGTH = 20;

	/**
	 * @Fields MEMBER_PASSWORD_ENCODE_MIN_LENGTH : 加密后密码最小长度，32位
	 */
	public static final int MEMBER_PASSWORD_ENCODE_MIN_LENGTH = 32;

	/**
	 * @Fields MEMBER_PASSWORD_ENCODE_MAX_LENGTH : 加密后密码最大长度，32位，MD5是32位
	 */
	public static final int MEMBER_PASSWORD_ENCODE_MAX_LENGTH = 32;
	/**
	 * @Fields MEMBER_EMAIL_MIN_LENGTH : 邮箱最小长度，6个字符
	 */
	public static final int MEMBER_EMAIL_MIN_LENGTH = 6;

	/**
	 * @Fields MEMBER_EMAIL_MAX_LENGTH : Email最大长度，30个字符
	 */
	public static final int MEMBER_EMAIL_MAX_LENGTH = 30;
	/**
	 * @Fields MEMBER_REMARK_MIN_LENGTH : 备注最小长度，0个字符
	 */
	public static final int MEMBER_REMARK_MIN_LENGTH = 0;
	/**
	 * @Fields MEMBER_REMARK_MAX_LENGTH : 备注最大长度，50个字符
	 */
	public static final int MEMBER_REMARK_MAX_LENGTH = 50;
	/**
	 * @Fields MEMBER_TEL_MAX_LENGTH : 电话最大长度，20个字符
	 */
	public static final int MEMBER_TEL_MAX_LENGTH = 20;
	/**
	 * @Fields MEMBER_EXT_MAX_LENGTH : 扩展最大长度，50个字符
	 */
	public static final int MEMBER_EXT_MAX_LENGTH = 50;
	/**
	 * @Fields MEMBER_FULLNAME_MAX_LENGTH : 全名最大长度，20个字符
	 */
	public static final int MEMBER_FULLNAME_MAX_LENGTH = 20;

	//////////////////// 用户名（成员名）和密码等基本信息，End  //////////////////////////////




}
