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
package cc.msonion.carambola.member.service;

import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.MemberExample;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

/**
 * @ClassName: MemberService
 * @Description: 成员（公司内部）Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月22日 下午6:19:21
 *
 */
public interface MemberService extends MsOnionBaseService<Member, MemberExample> {

	/**
	 * @Title: inspectParameter
	 * @Description: 检查参数
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 上午11:01:28
	 *
	 * @param apiVersion Api版本
	 * @param param
	 * @param type 参数类型，type为类型，可选参数1、2、3分别代表name、phone、email ，{@link cc.msonion.carambola.member.common.constants.ParamTypeConstants}
	 * @param targerIdx  目标idx，当idx不为空时，需要过滤查询不等于idx的数据
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param,
								   Integer type, Long targerIdx) throws MsOnionIllegalArgumentException, MsOnionException;

	/**
	 * @Title: register
	 * @Description: 注册成员（公司内部） 
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午10:59:47
	 *
	 * @param apiVersion Api版本
	 * @param member
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult register(MsOnionApiVersion apiVersion, Member member) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: login 
	 * @Description: 登录
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午1:02:24
	 *
	 * @param apiVersion Api版本
	 * @param name 成员名
	 * @param password 密码，明文
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult login(MsOnionApiVersion apiVersion, String name, String password) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: login 
	 * @Description: 登录
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午2:36:04
	 *
	 * @param apiVersion Api版本
	 * @param token
	 * @param username
	 * @param password 明文密码
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult login(MsOnionApiVersion apiVersion, String token, String username, String password) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: login 
	 * @Description: 登录
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午2:35:26
	 *
	 * @param token
	 * @param sessionExpire
	 * @param username
	 * @param password 明文密码
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult login(MsOnionApiVersion apiVersion, String token, int sessionExpire, String username, String password) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: loginForSecurity 
	 * @Description: 安全登录，密码通过加密
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午1:04:06
	 *
	 * @param apiVersion Api版本
	 * @param username 用户名
	 * @param password 密码，加密
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String username, String password) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: loginForSecurity 
	 * @Description: 安全登录，密码通过加密 
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午2:14:47
	 *
	 * @param apiVersion Api版本
	 * @param token 
	 * @param username
	 * @param password
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String token, String username, String password)
			throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: loginForSecurity 
	 * @Description: 安全登录，密码通过加密 
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午2:26:58
	 *
	 * @param apiVersion Api版本
	 * @param token
	 * @param sessionExpire Session周期，单位：秒
	 * @param username
	 * @param password
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	 MsOnionResult loginForSecurity(MsOnionApiVersion apiVersion, String token, int sessionExpire, String username, String password)
			throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: login 
	 * @Description: 登录 
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午3:08:34
	 *
	 * @param token
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	 MsOnionResult login(MsOnionApiVersion apiVersion, String token) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: login 
	 * @Description: 登录
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午3:08:22
	 *
	 * @param apiVersion Api版本
	 * @param token
	 * @param sessionExpire
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult login(MsOnionApiVersion apiVersion, String token, int sessionExpire) throws MsOnionIllegalArgumentException, MsOnionException;

	/** 
	 * @Title: logout 
	 * @Description: 注销
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date 2017年2月26日 下午5:31:32
	 *
	 * @param apiVersion Api版本
	 * @param token
	 * @return
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException
	 */
	MsOnionResult logout(MsOnionApiVersion apiVersion, String token) throws MsOnionIllegalArgumentException, MsOnionException;
	
	/**
	* @Title: update 
	* @Description: 更新用户（成员）信息，不包括密码，密码需要加密处理，需要单独对密码进行处理
	* @Author: HeroCao hero-cao@msyc.cc
	* @Date: 2017年3月3日 下午6:58:22
	*
	* @param apiVersion
	* @param member
	* @return
	* @throws MsOnionIllegalArgumentException
	* @throws MsOnionException
	 */
	MsOnionResult update(MsOnionApiVersion apiVersion, Member member) throws MsOnionIllegalArgumentException, MsOnionException;
	
	/**
	* @Title: updatePassword 
	* @Description: 更新密码，基于主键idx
	* @Author: HeroCao hero-cao@msyc.cc
	* @Date: 2017年3月3日 下午7:03:35
	*
	* @param apiVersion 版本号
	* @param idx 用户（成员）主键
	* @param oldPassword 原始密码（明文密码）
	* @param newPassword 新密码（明文密码）
	* @return
	* @throws MsOnionIllegalArgumentException
	* @throws MsOnionException
	 */
	MsOnionResult updatePassword(MsOnionApiVersion apiVersion, Long idx, String oldPassword, String newPassword) throws MsOnionIllegalArgumentException, MsOnionException;
	
	/**
	* @Title: updatePassword 
	* @Description: 更新密码，基于Email
	* @Author: HeroCao hero-cao@msyc.cc
	* @Date: 2017年3月3日 下午9:04:31
	*
	* @param apiVersion Api版本号
	* @param email 邮箱
	* @param newPassword 新密码（明文密码）
	* @return
	* @throws MsOnionIllegalArgumentException
	* @throws MsOnionException
	 */
	MsOnionResult updatePassword(MsOnionApiVersion apiVersion, String email, String newPassword) throws MsOnionIllegalArgumentException, MsOnionException;
	
	/**
	* @Title: delete 
	* @Description: 删除成员
	* @Author: HeroCao hero-cao@msyc.cc
	* @Date: 2017年3月7日 下午8:46:43
	*
	* @param apiVersion Api版本号
	* @param idx 成员主键idx
	* @return 返回MsOnionResult
	* @throws MsOnionIllegalArgumentException 非法参数异常
	* @throws MsOnionException 异常
	 */
	MsOnionResult delete(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;


	/**
	 * @Title: selectMemberByLoginName
	 * @Description: 通过登录名查询成员
	 * @Author: HeroCao hero-cao@msyc.cc
	 * @Date: 2017年3月7日 下午8:46:43
	 *
	 * @param apiVersion Api版本号
	 * @param loginName 登录名
	 * @return 返回MsOnionResult
	 * @throws MsOnionIllegalArgumentException 非法参数异常
	 * @throws MsOnionException 异常
	 */
	MsOnionResult selectMemberByLoginName(MsOnionApiVersion apiVersion, String loginName) throws MsOnionException;


	/**
	 * 修改用户成员密码
	 * @param msOnionApiVersion 版本号
	 * @param curIdx  当前修改人的idx
	 * @param curPassword 当前修改人的密码
	 * @param idx 修改账号idx
	 * @param newPassword 新密码
	 * @return MsOnionResult
	 * @throws MsOnionException 异常
	 */
	MsOnionResult editMemberPassword(MsOnionApiVersion msOnionApiVersion, Long curIdx, String curPassword, Long idx, String newPassword) throws MsOnionException;



	/**
	 * 将用户验证码的值放入redis
	 *
	 * @param key key
	 * @param value 值
	 * @param expire 生效时间
	 * @throws MsOnionIllegalArgumentException 非法参数异常
	 * @throws MsOnionException 异常
	 */
	@Deprecated
	void setUserVefiyCache(String key, String value, int expire) throws MsOnionException;

	/**
	 * 从redis获取用户验证码的值
	 *
	 * @param  key key
	 * @return 返回MsOnionResult
	 * @throws MsOnionIllegalArgumentException
	 * @throws MsOnionException 异常
	 */
	String getUserVefiyCache(String key) throws MsOnionException;

	/**
	 * 清除验证码
	 * @param key key
	 * @throws MsOnionException 异常
	 */
	@Deprecated
	void delUserVefiyCache(String key) throws MsOnionException;
}
