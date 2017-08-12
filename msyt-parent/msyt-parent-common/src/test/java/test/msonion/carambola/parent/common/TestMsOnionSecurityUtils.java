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
* @Title: TestMsOnionSecurityUtils.java
* @Package: test.msonion.carambola.parent.common 
* @Description: TODO(描述该文件做什么) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc  
* @Date: 2017年2月27日 下午2:32:28 
* @Version: V2.0.0
* @Modify-by: HeroCao hero-cao@msyc.cc
* @Modify-date: 2017年2月27日 下午2:32:28  
* @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
* @Modify-description: TODO(描述修改什么内容) 
*/    
package test.msonion.carambola.parent.common;

import java.security.NoSuchAlgorithmException;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurity3DESUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import org.junit.Test;
//import cc.msonion.carambola.parent.common.utils.MSLogger;


/** 
* @ClassName: TestMsOnionSecurityUtils
* @Description: TODO(描述这个类的作用) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc
* @Date: 2017年2月27日 下午2:32:28 
*  
*/
public class TestMsOnionSecurityUtils {

	@Test
	public void testMD5ForPassword01() {
		
		String password = "12345688@2018";
//		String md5ForPassword = MsOnionSecurityUtils.md5ForPassword(password);
		
//		MSLogger.info(getClass(), "md5ForPassword=" + md5ForPassword);
//
//		MSLogger.info(getClass(), "md5ForPassword.length()=" + md5ForPassword.length());
		
		/**
		 * test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, md5ForPassword=84314EAE776983A7C05FD17CA9E582B5
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, md5ForPassword.length()=32


test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, md5ForPassword=2F8996E6606133DFDB1D21CEC25468E9
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, md5ForPassword.length()=32


		 */
	}
	
	@Test
	public void test01() {
		
		
		String password = "123456";
		
		String md5Hex = MsOnionSecurityUtils.md5Hex(password);
		
//		MSLogger.info(getClass(), "md5Hex=" + md5Hex);
//
//		MSLogger.info(getClass(), "md5Hex.length()=" + md5Hex.length());
		
		/**
		 * test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, md5Hex=e10adc3949ba59abbe56e057f20f883e
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, md5Hex.length()=32
		 */
		
	}
	
	@Test
	public void test02() throws NoSuchAlgorithmException, MsOnionException {
		
		String data = "123456";
		
		String md5Hex = MsOnionSecurityUtils.md5ForRedisKey(data);
		
//		MSLogger.info(getClass(), "md5Hex=" + md5Hex);
		
//		MSLogger.info(getClass(), "md5Hex.length()=" + md5Hex.length());
		
	}
	
	
	@Test
	public void test03() throws MsOnionException {
		
		String plainText = "测试3DES加密";
		
		String encode = MsOnionSecurity3DESUtils.encode(plainText);
		
		String decode = MsOnionSecurity3DESUtils.decode(encode);
		
//		MSLogger.info(getClass(), "加密后，encode=" + encode);
		
//		MSLogger.info(getClass(), "解密后，decode=" + decode);
		
		/**
		 * test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，encode=j5BinCLwPa2qytnd3PqnQ/QjniJeRGr/
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 解密后，decode=测试3DES加密
		 */
		
	}
	
	

	@Test
	public void test04() throws MsOnionException {
		
		String plainText = "12";
		
		String encode = MsOnionSecurity3DESUtils.encode(plainText);
		
		String decode = MsOnionSecurity3DESUtils.decode(encode);
		
//		MSLogger.info(getClass(), "加密后，encode=" + encode);
		
//		MSLogger.info(getClass(), "解密后，decode=" + decode);
		
		/**
		 * test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，encode=j5BinCLwPa2qytnd3PqnQ/QjniJeRGr/
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 解密后，decode=测试3DES加密


test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，encode=MhGpACVjj84=
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 解密后，decode=1

test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，encode=8WClXuJjqs8=
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 解密后，decode=A

test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，encode=7mnZu7OMaUE=
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 解密后，decode=12


		 */
		
		
	}
	
	
	@Test
	public void testEncodeForCookie01() throws MsOnionException {
		
		String plainText = "测试3DES，加入干扰测试，洋葱海外仓";
		
//		String plainText = "A";
		
		String encodeForCookie = MsOnionSecurityUtils.encodeForCookie(plainText);
		
//		MSLogger.info(getClass(), "加密后，encodeForCookie=" + encodeForCookie);
		
		
		// 解密
		
		String decodeForCookie = MsOnionSecurityUtils.decodeForCookie(encodeForCookie);
		
		
//		MSLogger.info(getClass(), "解密后，decodeForCookie=" + decodeForCookie);
	}
	
	@Test
	public void test3DES01() throws MsOnionException {
		
		
		String plainText = "j5BinCLw2F8996E6606133DFDB1D21CEC25468E9Pa0EqpiAxIXWz+pKyBB9mJZoFEFzQg62Z2w=84314EAE776983A7C05FD17CA9E582B5";
	
		
		String encode = MsOnionSecurity3DESUtils.encode(plainText);
		
//		MSLogger.info(getClass(), "加密后，encode=" + encode);
		
		// 
	
	}
	
	
	
	@Test
	public void testDecodeForCookie01() throws MsOnionException {
		
		String str = "测试3DES，加入干扰测试";
		
//		MsOnionSecurityUtils.decodeForCookie(encryptText)
		
		String encryptText = "C6nWqv05FXrNMEjY2bt377zpGLKgdCI4+YAPy0vWQxKp9GZOjwjuifYyC8Vd jBH7sd8kKzc8br/dQH68uLIUgGcYpzH0XI4apLjy4gSJTFnDlXFwUGF+TRSY n1majF0s7Mx9M2P+JG+GEbiMN/Q2OhnsaN0ofRHZ3+ObZVLbE5FW7JxtTMce tA==";
		
		String decodeForRedis = MsOnionSecurityUtils.decodeForRedis(encryptText);
		
//		MSLogger.info(getClass(), "解密后，decodeForRedis=" + decodeForRedis);
		
		String decodeForRedis2 = MsOnionSecurityUtils.decodeForRedis(decodeForRedis);
		
//		MSLogger.info(getClass(), "解密后2，decodeForRedis2=" + decodeForRedis2);
		
	}
	
	
	@Test
	public void testForRedisAndCookie() throws MsOnionException {
		
		
		String plainText = "广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。 \r\n 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]";
		
//		String encodeForRedis = MsOnionSecurityUtils.encodeForRedis(plainText);
		
		String encodeForRedis = MsOnionSecurityUtils.encodeForCookie(plainText);
		
		
//		MSLogger.info(getClass(), "加密后，encodeForRedis=" + encodeForRedis);
		
		
//		String decodeForRedis = MsOnionSecurityUtils.decodeForRedis(encodeForRedis);
		
		String decodeForRedis = MsOnionSecurityUtils.decodeForCookie(encodeForRedis);
		
//		MSLogger.info(getClass(), "加密后，decodeForRedis=" + decodeForRedis);
		
	}
	
	@Test
	public void testSHA() {
		
		String plainText = "广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。 \r\n 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]";

		
		String sha512Hex = MsOnionSecurityUtils.sha512Hex(plainText);
		
//		MSLogger.info(getClass(), "加密后，sha512Hex=" + sha512Hex);
		
//		MSLogger.info(getClass(), "加密后，sha512Hex.length()=" + sha512Hex.length());
		
		/**
		 * test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，sha512Hex=8d5d063a78087a3a699cf4cf8592fda0f303705cc9986651c10f04788045215d264fe9a8664704985e2e65187f1e45213d2b042d3895fc0900676dab95294ea4
test.msonion.carambola.parent.common.TestMsOnionSecurityUtils, 加密后，sha512Hex.length()=128

		 */
		
	}
	
}
