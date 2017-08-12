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
* @Title: TestException.java 
* @Package: test.msonion.carambola.parent.common 
* @Description: TODO(描述该文件做什么) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc  
* @Date: 2017年2月26日 上午10:59:22 
* @Version: V2.0.0
* @Modify-by: HeroCao hero-cao@msyc.cc
* @Modify-date: 2017年2月26日 上午10:59:22  
* @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
* @Modify-description: TODO(描述修改什么内容) 
*/    
package test.msonion.carambola.parent.common;

import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/** 
* @ClassName: TestException 
* @Description: TODO(描述这个类的作用) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc
* @Date: 2017年2月26日 上午10:59:22 
*  
*/
public class TestException {

	@Test
	public void testException01() {
//		
//		MsOnionIllegalArgumentException illegalArgumentException = new MsOnionIllegalArgumentException(11);
//		
//		int exceptionStatus = illegalArgumentException.getExceptionStatus();
//		
//		MSLogger.info(getClass(), "状态码：exceptionStatus=" + exceptionStatus);

//		NumberUtils.toLong()

		String str1 = "199.85";

		long price1 = NumberUtils.toLong(str1);

		int price2 = NumberUtils.toInt(str1);

		System.out.println("str1=" + str1);

		System.out.println("price1=" + price1);


		System.out.println("price2=" + price2);

		/**
		 * str1=199.85
		 price1=0
		 price2=0

		 */

		float price3 = NumberUtils.toFloat(str1);

		System.out.println("price3=" + price3);



//		System.out.println("price3=" + price3);

//		NumberUtils.


		int p1 = (int) (price3 * 100);

		long p2 = (long) (price3 * 100);

		System.out.println("p1=" + p1);

		System.out.println("p2=" + p2);


	}
	
	@Test
	public void testException02() {
		
		MsOnionIllegalArgumentException illegalArgumentException = new MsOnionIllegalArgumentException("测试02", 12);
		
		int exceptionStatus = illegalArgumentException.getExceptionStatus();
		String message = illegalArgumentException.getMessage();
//
//		MSLogger.info(getClass(), "状态码：exceptionStatus=" + exceptionStatus);
//
//		MSLogger.info(getClass(), "异常信息：message=" + message);
		
	}
	
}
