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

/**   
 * @Title: ExceptionStatusConstants.java 
 * @Package: cc.msonion.carambola.commons.common.constants 
 * @Description: 异常状态码常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc  
 * @Date: 2017年2月26日 上午10:20:27 
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月26日 上午10:20:27  
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：ApiVersion异常状态码
 */    
package cc.msonion.carambola.commons.common.constants;    

/** 
 * @ClassName: ExceptionStatusConstants 
 * @Description: 异常状态码常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 上午10:20:27 
 *  
 */
@Deprecated
public class ExceptionStatusConstants {

	private ExceptionStatusConstants() { }

	////////////////////////////  ApiVersion ，Begin  //////////////////////

	/**
	 * 异常状态码：ApiVersion为null
	 */
	public static final int MS_ONION_EXCEPTION_STATUS_APIVERSION_NULL = 100000100;

	/**
	 * 异常状态码：请求ApiVersion为非法
	 */
	public static final int MS_ONION_EXCEPTION_STATUS_APIVERSION_REQUESTAPIVERSION_ILLEGAL = 100000101;

	//////////////////////////// ApiVersion ，End  //////////////////////

	//////////////////////////// 主键Idx ，Begin  //////////////////////

	/**
	 * 异常状态码：主键idx非法
	 */
	public static final int MS_ONION_EXCEPTION_STATUS_IDX_ILLEGAL = 100000200;

	/**
	 * 异常状态码：主键idx集合idxs非法
	 */
	public static final int MS_ONION_EXCEPTION_STATUS_IDXS_ILLEGAL = 100000201;

	//////////////////////////// 主键Idx ，End  //////////////////////

	
	//////////////////////////// Paging （分页），Begin  //////////////////////

	/**
	 * 异常状态码：分页页码非法，为null、超过int值范围、<=0
	 */
	public static final int MS_ONION_EXCEPTION_STATUS_PAGING_PAGENUM_ILLEGAL = 100000300;

	//////////////////////////// Paging （分页），End  //////////////////////

}
