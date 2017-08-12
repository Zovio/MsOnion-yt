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
 * @Title: TestDomain.java 
 * @Package: test.msonion.carambola.parent.pojo 
 * @Description: TODO(描述该文件做什么) 
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc  
 * @Date: 2017年2月16日 下午6:07:45 
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午6:07:45  
 * @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
 * @Modify-description: TODO(描述修改什么内容) 
 */    
package test.msonion.carambola.parent.pojo;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import org.springframework.test.context.TestExecutionListeners;

import java.util.UUID;

/** 
 * @ClassName: TestDomain 
 * @Description: TODO(描述这个类的作用) 
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午6:07:45 
 *  
 */
public class TestDomain {

	//	@Test
	//	public void test01() {
	//		
	//		
	//		
	//	}

	public static void main(String[] args) {

		test1();



	}

	private static void test1() {

		// 加载 Spring 配置文件

		String configLocation = "spring/applicationContext-*.xml";

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);


		MsOnionDomain msOnionDomain = applicationContext.getBean(MsOnionDomain.class);

		//				System.out.print("domain=" + domain);


		//				Environment environment = applicationContext.getBean(Environment.class);

		//				System.out.print("environment=" + environment);

		System.out.println("环境 # getEnvironment=" + msOnionDomain.getEnvironment());

		System.out.println("协议 # getProtocol=" + msOnionDomain.getProtocol());



		String collectorApi = msOnionDomain.getCollectorApi();

		System.out.println("collectorApi=" + collectorApi);

		System.out.println("getCollectorService=" + msOnionDomain.getCollectorService());

		System.out.println("getCollectorWeb=" + msOnionDomain.getCollectorWeb());

		System.out.println("getContentApi=" + msOnionDomain.getContentApi());


		System.out.println("getContentService=" + msOnionDomain.getContentService());


		System.out.println("getContentWeb=" + msOnionDomain.getContentWeb());


		System.out.println("getItemApi=" + msOnionDomain.getItemApi());


		///

		System.out.println("getItemService=" + msOnionDomain.getItemService());

		System.out.println("getItemWeb=" + msOnionDomain.getItemWeb());

		System.out.println("getManagerApi=" + msOnionDomain.getManagerApi());

		System.out.println("getManagerService=" + msOnionDomain.getManagerService());


		System.out.println("getManagerWeb=" + msOnionDomain.getManagerWeb());


	 /// 
		System.out.println("getMemberAPI=" + msOnionDomain.getMemberApi());


		
		System.out.println("getMemberService=" + msOnionDomain.getMemberService());
		
		System.out.println("getMemberWeb=" + msOnionDomain.getMemberWeb());
		
		
		System.out.println("getPortalWeb=" + msOnionDomain.getPortalWeb());
		
		System.out.println("getSearchApi=" + msOnionDomain.getSearchApi());
		
		System.out.println("getSearchService=" + msOnionDomain.getSearchService());
		
		System.out.println("getSearchWeb=" + msOnionDomain.getSearchWeb());
		
		
		System.out.println("getSso=" + msOnionDomain.getSsoWeb());
		
		System.out.println("getUserApi=" + msOnionDomain.getUserApi());
		
		System.out.println("getUserService=" + msOnionDomain.getUserService());
		
		System.out.println("getUserWeb=" + msOnionDomain.getUserWeb());
		
	
		
		System.out.println("日志模块，getLogApi=" + msOnionDomain.getLogApi());
		
		System.out.println("日志模块，getLogService=" + msOnionDomain.getLogService());
		
		System.out.println("日志模块，getLogWeb=" + msOnionDomain.getLogWeb());


	}

	@Test
	public void test02() {


		UUID uuid = UUID.randomUUID();


		System.out.println("uuid=" + uuid.toString());


		for (int i = 0; i < 10; i++) {

			UUID uuid2 = UUID.randomUUID();


			System.out.println("i=" + i + ", uuid2=" + uuid2.toString());

		}

		/**
		 * i=0, uuid2=18f294c1-4fab-46be-9eae-a09f8bde8092
		 i=1, uuid2=3f5d0fd0-0281-4c03-ac16-4509a8fa6226
		 i=2, uuid2=7f86d0c8-664a-449d-9071-15ece385a9ab
		 i=3, uuid2=d2b72c7e-2c12-40fb-bfea-e5c68c1bf317
		 i=4, uuid2=4bf4718e-fd71-43e2-b972-28ae3dd418fe
		 i=5, uuid2=c902d527-4428-4f55-abc8-b1c973a061ac
		 i=6, uuid2=a3cd2cb2-4e42-4331-ad3b-7757fd0d19bc
		 i=7, uuid2=a23ecd29-ddd5-4710-8f6a-9911e85459a8
		 i=8, uuid2=f62461bc-5741-47cf-9cbe-584ea2f3ca56
		 i=9, uuid2=cd96f2df-0627-4f82-afa2-3927c19beb35
		 */




	}


	@Test
	public void test03() {


		UUID uuid = UUID.randomUUID();


		System.out.println("uuid=" + uuid.toString());


		for (int i = 0; i < 10; i++) {

			UUID uuid2 = UUID.randomUUID();


			System.out.println("i=" + i + ", uuid2=" + uuid2.toString());


			String uuidStr = uuid2.toString().replace("-", "");

			System.out.println("i=" + i + ", uuidStr=" + uuidStr);


			String[] data = uuid2.toString().split("-");

			// 5


			String str = data[3] + data[4];

			System.out.println("i=" + i + ", str=" + str + "， str.length()=" + str.length());

		}

		/**
		 i=0, uuid2=0abe532c-f33b-49f5-959f-7452887b30d8
		 i=0, uuidStr=0abe532cf33b49f5959f7452887b30d8
		 i=0, str=959f7452887b30d8， str.length()=16
		 i=1, uuid2=b6f702fc-a977-455b-8bea-b0303810fc2c
		 i=1, uuidStr=b6f702fca977455b8beab0303810fc2c
		 i=1, str=8beab0303810fc2c， str.length()=16
		 i=2, uuid2=d1c207b7-e523-43f0-8a02-97c8852d263b
		 i=2, uuidStr=d1c207b7e52343f08a0297c8852d263b
		 i=2, str=8a0297c8852d263b， str.length()=16
		 i=3, uuid2=d2b2997a-9af5-460c-b2c9-ea8c0257d61b
		 i=3, uuidStr=d2b2997a9af5460cb2c9ea8c0257d61b
		 i=3, str=b2c9ea8c0257d61b， str.length()=16
		 i=4, uuid2=f863f1ee-4277-4a90-8ebf-2f5fbbc5e403
		 i=4, uuidStr=f863f1ee42774a908ebf2f5fbbc5e403
		 i=4, str=8ebf2f5fbbc5e403， str.length()=16
		 i=5, uuid2=c0181366-39ae-4a54-8ea6-473c2f49d31a
		 i=5, uuidStr=c018136639ae4a548ea6473c2f49d31a
		 i=5, str=8ea6473c2f49d31a， str.length()=16
		 i=6, uuid2=298500d8-5641-4a96-aefd-0329d442316d
		 i=6, uuidStr=298500d856414a96aefd0329d442316d
		 i=6, str=aefd0329d442316d， str.length()=16
		 i=7, uuid2=10b571a8-ebf9-461b-9d68-9376fdf1d07f
		 i=7, uuidStr=10b571a8ebf9461b9d689376fdf1d07f
		 i=7, str=9d689376fdf1d07f， str.length()=16
		 i=8, uuid2=a963047a-d494-4fe1-ba3b-94726e49c927
		 i=8, uuidStr=a963047ad4944fe1ba3b94726e49c927
		 i=8, str=ba3b94726e49c927， str.length()=16
		 i=9, uuid2=cd190158-ae9a-491b-8705-d49184bf2b36
		 i=9, uuidStr=cd190158ae9a491b8705d49184bf2b36
		 i=9, str=8705d49184bf2b36， str.length()=16
		 */




	}



}
