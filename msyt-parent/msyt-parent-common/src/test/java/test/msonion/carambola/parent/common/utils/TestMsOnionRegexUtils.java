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
* @Title: TestMsOnionRegexUtils.java
* @Package: test.msonion.carambola.parent.common.utils 
* @Description: TODO(描述该文件做什么) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc  
* @Date: 2017年3月7日 下午12:27:27 
* @Version: V2.0.0
* @Modify-by: HeroCao hero-cao@msyc.cc
* @Modify-date: 2017年3月7日 下午12:27:27  
* @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
* @Modify-description: TODO(描述修改什么内容) 
*/    
package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import org.junit.Test;

/** 
* @ClassName: TestMsOnionRegexUtils
* @Description: TODO(描述这个类的作用) 
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc
* @Date: 2017年3月7日 下午12:27:27 
*  
*/
public class TestMsOnionRegexUtils {

	
	@Test
	public void testCheckEmail01() {
		
		
		String email = "hero.cao@msyc.cc";


		boolean checkEmail = MsOnionRegexUtils.isEmail(email);
		System.out.println(checkEmail);

//		MSLogger.info(getClass(), "checkEmail=" + checkEmail);


		String email2 = "Hello,  hero-cao@msyc.cc , this is your Email ?";


		boolean checkEmail2 = MsOnionRegexUtils.isEmail(email2);
		System.out.println(checkEmail2);


//		MSLogger.info(getClass(), "checkEmail2=" + checkEmail2);
//
		//^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
		
		
	}
	
	
	@Test
	public void testCheckEmail02() {
		
		
//		String email = "herocao@msyc.cc";
//
//
//		boolean checkEmail = MsOnionRegexUtils.checkEmail(email);
//
//
//		MSLogger.info(getClass(), "checkEmail=" + checkEmail);
//
//
//		String email2 = "Hello,  hero-cao@msyc.cc , this is your Email ?";
//
//
//		boolean checkEmail2 = MsOnionRegexUtils.checkEmail(email2);
//
//
//		MSLogger.info(getClass(), "checkEmail2=" + checkEmail2);
//
//
		
		
	}

	@Test
	public void test03() {

		// 测试
		String key = "ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10";


//		String key = "ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E";

//		String key = "ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:ss:C7F97D24CD4077E4CBAFEF23CA64944E";

		boolean isRedisKey = MsOnionRegexUtils.isRedisKey(key);


		System.out.println("校验是为Redis的Key规则， isRedisKey=" + isRedisKey);

		System.out.println("校验是为Redis的Key规则， key=" + key);


	}


	@Test
	public void test05() {


		/*
		1、正则表达式验证数字、字母、特殊字符的组合，
		这个要求3者都有 var reg=/(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i

		特殊 字符 为~!@#$%^&*其中之一

		 2、正则表达式验证（数字+字母）或者（数字+特殊字符）或者（字母+特殊字符），不能是纯数字、纯字母、纯特殊字符，即只要符合这3个组合其中之一都为true

		 var reg=/((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[#@!~%^&*])|(?=.*\d)(?=.*[#@!~%^&*]))[a-z\d#@!~%^&*]{8,16}/i




		 ///


		 var newpwd = $("#newpassword").val();

//var pattern = "([A-Za-z]|[0-9]|-|_){4,16}";
//var reg = new RegExp(pattern,"g");
var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}\[\]:";'<>?,.\/]).{4,16}$/;
var flag = reg.test(newpwd);
if(flag == false){
alert("新密码必须由 4-16位字母、数字、特殊符号线组成.");
return false;
}


http://blog.csdn.net/hu_shengyang/article/details/16062071


http://blog.csdn.net/hu_shengyang/article/details/16062071


		 */


//		String regex = "/^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}\\[\\]:\";'<>?,.\\/]).{6,20}$/";



//		String regex = "/^((?=.*[a-z])(?=.*\\d)|(?=[a-z])(?=.*[#@!~%^&*])|(?=.*\\d)(?=.*[#@!~%^&*]))[a-z\\d#@!~%^&*]{6,20}$/i";


		String regex = "/(?=.*[a-z])(?=.*\\d)(?=.*[#@!~%^&*])[a-z\\d#@!~%^&*]{8,16}/i";

//		String str = "Hero";

		String str = "Hero88@";

		boolean regular = MsOnionRegexUtils.regular(str, regex);


		System.out.println("验证结果 # regular=" + regular);

	}

	@Test
	public void test06() {

		/*

		https://www.zybang.com/question/8a0c4115dcb0550431ab1015fea87e27.html

		1.本来不想回答的 因为我也没有很好的解法，但是先回答的那个错了
2.这个正则里面的特殊字符只是~!@#$%^&*? 如果不够 自行添加 如果要匹配的文本不是独立的，此正则无效。 匹配的文本必须是一个独立的字符串，不能被包含。因为用到了^$表示字符串的开头和结尾。
正则为(?!^(\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\w~!@#$%\^&*?]+$
3.以经过测试， 能有效解决你的问题，但是不够简洁。如果有好的解决方案，请私信我，让我也学习下
		 */

//		String str = "Hero88@"; // OK

		String str = "12345+Az";

		// OK
//		String regex = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%\\^&*?]+$";

		String regex = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*+?]+)$)^[\\w~!@#$%\\^&*+?]+$";

		// 新增 ()_-+=
//		String regex = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%\\^&\\*\\(\\)_-\\+=?]+)$)^[\\w~!@#$%\\^&\\*\\(\\)_-\\+=?]{6,20}$"; // + ==> {6,20}

		boolean regular = MsOnionRegexUtils.regular(str, regex);


		System.out.println("验证结果 # regular=" + regular);

	}


	@Test
	public void test07() {


//		String str = "12345+Az";

//		String str = "123456";


		String str = "ABCEFG";

		boolean regular = MsOnionRegexUtils.isPasswordLevel(str);


		System.out.println("验证结果 # regular=" + regular);

	}


	@Test
	public void test08() {

		// String pattern = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";

		String pattern = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";

//		String url = "https://b2b.tmall.com/login?spm=a320o.7694341.mainright.1.KyYohn&redirectURL=https://b.tmall.com/";

		 String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E8%8E%B7%E5%8F%96%E5%9F%9F%E5%90%8D%E7%9A%84%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F&rsv_pq=a109476700175e21&rsv_t=760a6wiuS0gvuASij65msaQUIw0kD3WHTlTXj16VtzYhvMFku2z7x0MZNCc&rqlang=cn&rsv_enter=1&rsv_sug3=8&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&inputT=7983&rsv_sug4=7984";

//		String url = "https://b2b.tmall.com/login?spm=a320o.7694341.";


		// url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url


		boolean regular = MsOnionRegexUtils.regular(url, pattern);


		System.out.println("验证结果 # regular=" + regular);

		String domain = MsOnionRegexUtils.getDomain(url);

		System.out.println("验证结果 # domain=" + domain);

	}
	
	
}
