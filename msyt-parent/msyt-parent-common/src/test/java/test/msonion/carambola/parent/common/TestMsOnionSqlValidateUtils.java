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
* @Title: TestMsOnionSqlValidateUtils.java
* @Package: test.msonion.carambola.parent.common 
* @Description: TODO(描述该文件做什么) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc  
* @Date: 2017年2月26日 下午2:23:12 
* @Version: V2.0.0
* @Modify-by: HeroCao hero-cao@msyc.cc
* @Modify-date: 2017年2月26日 下午2:23:12  
* @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
* @Modify-description: TODO(描述修改什么内容) 
*/    
package test.msonion.carambola.parent.common;

import cc.msonion.carambola.parent.common.utils.MsOnionSqlValidateUtils;
import org.junit.Test;

//import cc.msonion.carambola.parent.common.utils.MSLogger;


/** 
* @ClassName: TestMsOnionSqlValidateUtils
* @Description: TODO(描述这个类的作用) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc
* @Date: 2017年2月26日 下午2:23:12 
*  
*/
public class TestMsOnionSqlValidateUtils {

	 public final static String regex = "'|%|--|and|or|not|use|insert|delete|update|select|count|group|union" +  
             "|create|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|source|sql|where|from|*";  
	
	 
	 
	 
	/**
	* @Title: testSqlValidate 
	* @Description: 测试SQL校验
	* @Author: HeroCao hero-cao@msyc.cc
	* @Date: 2017年2月26日 下午2:24:23
	*
	 */
	@Test
	public void testSqlValidate01() {
		
		String str = "select * from tb_user where id=1 and name = 'test'  or   status =1 AND password ='123456' or status=1 OR flag=2";
		
		boolean sqlValidate = MsOnionSqlValidateUtils.sqlValidate(str);
		
		
//		MSLogger.info(getClass(), "sqlValidate=" + sqlValidate);
		
	}
	
	@Test
	public void testSqlValidate02() {
		
//		String str = "select * from tb_user where id=1 and name = 'test' or status =1";
		
//		String str = "Android、''";
		
		
//		String str = "FROMD";
		
		String str = "android、Android";
		
//		String str = null;
		
		boolean sqlValidate = MsOnionSqlValidateUtils.sqlValidate(str);
		
		
//		MSLogger.info(getClass(), "sqlValidate=" + sqlValidate + " , str=" + str);
		
	}
	
	/**
	* @Title: testSqlFilter 
	* @Description: 测试SQL过滤 
	* @Author: HeroCao hero-cao@msyc.cc
	* @Date: 2017年2月26日 下午2:24:41
	*
	 */
	@Test
	public void testSqlFilter() {
		
		String str = "select * from tb_user where idx=1 and name = 'test' OR status =1 AND password='12345688' , Hello、Java EE、Android、iOS";
		
		String str2 = MsOnionSqlValidateUtils.sqlFilter(str);
		
//		MSLogger.info(getClass(), "SQL过滤的结果，str2=" + str2);
		
	}
	
	@Test
	public void testSqlFilter02() {
		
//		String str = "select * from tb_user where idx=1 and name = 'test' OR status =1 AND password='12345688' , Hello、Java EE、Android、iOS";
		
//		String str = null;
		
//		String str = "Android";
		
//		String str = "fromd from delete select update,DROP drop DELETE UPDATE *";
		
//		String str = "SELECT * FROM user_table WHERE username='' or 1 = 1 -- and password=''";
		
		String str = "SELECT * FROM user_table WHERE username='' ;DROP DATABASE (DB Name) --' and password=''";
		
		String str2 = MsOnionSqlValidateUtils.sqlFilter(str);
		
	
		
//		MSLogger.info(getClass(), "SQL过滤的结果，str2=" + str2);
		
	}
	
	
	@Test
	public void testSqlFilter03() {
		
		
		 String str = "sElect * from test where id = 1 And name != 'sql' ";  
		
//		 return param.replaceAll("(?i)"+regex, "");  //(?i)不区分大小写替换  
		 
		String str2 = MsOnionSqlValidateUtils.sqlFilter2(str);
		
		
//		MSLogger.info(getClass(), "SQL过滤的结果，str2=" + str2);
		
	}
	
	
	@Test
	public void testSqlFilter05() {
		
		
		 String str = "sElect * from test where id = 1 And name != 'sql' ";  
		
//		 return param.replaceAll("(?i)"+regex, "");  //(?i)不区分大小写替换  
		 
		String str2 = str.replaceAll("(?i)"+regex, "");
		
		
//		MSLogger.info(getClass(), "SQL过滤的结果，str2=" + str2);
		
	}
	
	@Test
	public void testSqlFilter06() {
		
		// ; 
		String str1 = "'|%|--|and|or|not|use|insert|delete|update|select|count|group|union|net user|table|database|sitename|char";
		String str2 = "|create|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|source|sql|where|from|idx|name|password";  // |*";  包括 * 正则表达式报错
		String str3 = "|like|//|/|#|;|group_concat|column_name|information_schema.columns|table_schema|order|by|chr|mid|master|column|useradd|passwd";

		
		StringBuilder sb = new StringBuilder();
		
		sb.append(str1);
		sb.append(str2);
		sb.append(str3);
		
		String regex1 = sb.toString();
		
		
//		MSLogger.info(getClass(), "SQL过滤的结果，regex1=" + regex1);

//		 String str = "sElect * from test where id = 1 And name != 'sql' ";  
		 
		 
//			String str = "SELECT * FROM user_table WHERE username='' or 1 = 1 -- and password=''";
			
			String str = "SELECT * FROM user_table WHERE username='' ;DROP DATABASE (DB Name) --' and password=''";
			
			
		
//		 return param.replaceAll("(?i)"+regex, "");  //(?i)不区分大小写替换  
		 
		String str22 = str.replaceAll("(?i)"+regex1, "");
		
		
//		MSLogger.info(getClass(), "SQL过滤的结果，str22=" + str22);
		
	}
	
	
	@Test
	public void testSqlFilter07() {
		
		
//		 String str = "sElect * from test where id = 1 And name != 'sql' ";  
		 
		 
			String str = "SELECT * FROM user_table WHERE username='' or 1 = 1 -- and password=''";
			
//			String str = "SELECT * FROM user_table WHERE username='' ;DROP DATABASE (DB Name) --' and password=''";
			
			
			String str2 = MsOnionSqlValidateUtils.sqlFilter(str);
			
			
//			MSLogger.info(getClass(), "SQL过滤的结果，str2=" + str2);
	}
	
}
