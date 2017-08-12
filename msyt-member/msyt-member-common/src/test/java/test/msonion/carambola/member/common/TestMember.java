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
* @Title: TestMember.java 
* @Package: test.msonion.carambola.member.common 
* @Description: TODO(描述该文件做什么) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc  
* @Date: 2017年2月27日 上午8:56:12 
* @Version: V2.0.0
* @Modify-by: HeroCao hero-cao@msyc.cc
* @Modify-date: 2017年2月27日 上午8:56:12  
* @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
* @Modify-description: TODO(描述修改什么内容) 
*/    
package test.msonion.carambola.member.common;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import org.junit.Test;

/** 
* @ClassName: TestMember 
* @Description: TODO(描述这个类的作用) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc
* @Date: 2017年2月27日 上午8:56:12 
*  
*/
public class TestMember {

	@Test
	public void testPassword01() {
		
		String password = "893efbd2ecba30d815ce1de1f60dc66f";
		
		
		// password.length=32
		// 8-22 位
		
		// 京东 
		// 明文密码：6-20 字符， 建议字母、数字、符号两种及以上组合
		// 用户名：中文、字母、数字、- 、_ 组合， 4-20 字符 
		// status：1：可用，正常， 2：未激活（disable） ， 3：删除（软删除）
		MsOnionLogger.doInfo(getClass().getName(), "password=" + password + ",  password.length=" + password.length());
		
		
		// MD5 之后， 32 位 ！！！
	}
	
	
	@Test
	public void testPassword02() throws MsOnionException {
		
		// 20 
		String password = "1234567890ABCDEFGHIJ";
		
		String md5ForPassword = MsOnionSecurityUtils.md5ForPassword(password);

		MsOnionLogger.doInfo(getClass().getName(), "明文密码，password=" + password);
		
		// 2161B083CB875D561E0BFD84FE55A786
		MsOnionLogger.doInfo(getClass().getName(), "加密的密码，md5ForPassword=" + md5ForPassword);
		
		// md5ForPassword.length()=32
		MsOnionLogger.doInfo(getClass().getName(), "加密的密码，md5ForPassword.length()=" + md5ForPassword.length());
		
		/**
		 * test.msonion.carambola.member.common.TestMember, 明文密码，password=1234567890ABCDEFGHIJ
test.msonion.carambola.member.common.TestMember, 加密的密码，md5ForPassword=2161B083CB875D561E0BFD84FE55A786
test.msonion.carambola.member.common.TestMember, 加密的密码，md5ForPassword.length()=32

		 */
	}
	
	
	@Test
	public void testSHA01() {
		
		String data = "12345688";
		byte[] sha512 = MsOnionSecurityUtils.sha512(data);
		
		
	}
	
}
