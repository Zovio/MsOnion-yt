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
 * @Title: TestReflection.java 
 * @Package: test.msonion.carambola.member.api 
 * @Description: TODO(描述该文件做什么) 
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc  
 * @Date: 2017年2月25日 下午3:07:05 
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月25日 下午3:07:05  
 * @Modify-version: TODO(描述当前修改版本号，例如：V2.0.0、V2.0.1) 
 * @Modify-description: TODO(描述修改什么内容) 
 */    
package test.msonion.carambola.member.api;

import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.exception.MsOnionIdxllegalException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import org.junit.Test;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;
import java.util.Date;

/** 
 * @ClassName: TestReflection 
 * @Description: TODO(描述这个类的作用) 
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月25日 下午3:07:05 
 *  
 */
public class TestReflection {

	// 是否非法主键idx 
	private boolean isIllegalIdx = false;
	// 是否返回
	private boolean isReturn = false;

	@Test
	public void test01() {

		//		MsOnionReflectionUtils.doWithFields(clazz, fc);

		//		MsOnionReflectionUtils.doWithFields(Member.class, null, null);

		MsOnionReflectionUtils.doWithFields(Member.class, new FieldCallback() {

			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

				MsOnionLogger.doInfo(TestReflection.class.getName(), "方法内11，Thread.currentThread().getId() =" + Thread.currentThread().getId());

				MsOnionLogger.doInfo(TestReflection.class.getName(), "doWithFields # 字段，getType=" + field.getType() + "， field.getName()=" + field.getName());



			}
		});

		MsOnionLogger.doInfo(TestReflection.class.getName(), "方法外22，Thread.currentThread().getId() =" + Thread.currentThread().getId());

	}

	@Test
	public void test02() {

		MsOnionReflectionUtils.doWithLocalFields(Member.class, new FieldCallback() {

			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {


				MsOnionLogger.doInfo(TestReflection.class.getName(), "doWithLocalFields # 字段，getType=" + field.getType() + "， field.getName()=" + field.getName());

			}
		});

	}


	@Test
	public void test03() {

		Member member = new Member();
		//		member.setIdx(2L);
		member.setName("测试成员名-02");

		MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段# 开始 , member=" + member);



		MsOnionReflectionUtils.doWithFields(member.getClass(), new FieldCallback() {

			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

				if (isReturn) {
					return;
				}

				MsOnionLogger.doInfo(TestReflection.class.getName(), "doWithFields # 字段，getType=" + field.getType() + "， field.getName()=" + field.getName());


				// test.msonion.carambola.member.api.TestReflection, doWithFields # 字段，field.getType().getName()=java.lang.Long，field.getType().getSimpleName()=Long

				MsOnionLogger.doInfo(TestReflection.class.getName(), "doWithFields # 字段，field.getType().getName()=" + field.getType().getName() + "，field.getType().getSimpleName()=" + field.getType().getSimpleName());

				// 允许访问
				field.setAccessible(true);

				String name = field.getName();
				Object value = field.get(member);
				String typeName = field.getType().getName();

				MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段值，value=" + value);



				// equals , equalsIgnoreCase
				if (typeName.equals("java.lang.String")) {


					MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, String，field.get(member)=" + field.get(member));

					if (null == value) {

						// 设置默认空字符串 
						field.set(member, "");

						// 获取值

						Object value2 = field.get(member);

						MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段 String , 获取设置字段默认值，value2=" + value2);

						//						MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, String，field.get(member)=" + field.get(member));
					}


					//					 java.lang.
				} else if (typeName.equals("java.lang.Long")) {

					// 报错的，因为 field.getLong(member) 返回值 是 long , 而不是 Long 
					//					MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, Long，field.get(member)=" + field.getLong(member));


					if (null == value) {
						// 设置默认值
						// 报错
						//						field.setLong(member, 0L);

						// Error : java.lang.IllegalArgumentException: Can not set java.lang.Long field cc.msonion.carambola.member.pojo.Member.createByMemberIdx to java.lang.Integer

						MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, 11 设置字段默认值， Long，field.getName()=" + field.getName());

						if ("idx".equalsIgnoreCase(name)) {
							// 主键idx为null，抛出异常
							//							new MsOnionIdxllegalException("主键idx不能为null，必须是分布式架构，全局唯一递增Long类型");

							//							new RuntimeException("主键idx不能为null，必须是分布式架构，全局唯一递增Long类型");

							//							return;

							isIllegalIdx = true;
							isReturn = true;
							return;
						}

						// 必须加上 0L ,否则报错 
						field.set(member, 0L);  // 0 , 0L

						// Error : java.lang.IllegalArgumentException: Can not set java.lang.Long field cc.msonion.carambola.member.pojo.Member.createByMemberIdx to (long)0
						//						field.setLong(member, 0L);

						MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, 22 设置字段默认值, Long ，field.getName()=" + field.getName());

						// 获取值

						Object value2 = field.get(member);

						MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, 获取设置字段默认值，value2=" + value2);
					} 

					MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, String，field.get(member)=" + field.get(member));

				} else if (typeName.equals("java.util.Date")) {

					if (null == value) {
						//						DateFormat df = new SimpleDateFormat("yyyy-MM-dd mm:ss:sss");

						//						df.format(new Date());

						// Error : java.lang.IllegalArgumentException: Can not set java.util.Date field cc.msonion.carambola.member.pojo.Member.createTime to java.lang.String

						field.set(member, new Date());



					}

				} else if ("java.lang.Short".equals(typeName)) {

					if (null == value) {
						// 设置默认值为0

						// Error : java.lang.IllegalArgumentException: Can not set java.lang.Short field cc.msonion.carambola.member.pojo.Member.status to java.lang.Integer

						// 必须类型转换为： (short)0
						field.set(member, (short)0);

						//						field.set(member, new Short((short) 0));
					}

				} else if (field.getType().getName().equals("long")) {

					MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段, long ，field.get(member)=" + field.getLong(member));
				} 

			}
		});

		if (isIllegalIdx) {
			// 主键idx为null，抛出异常
			//			new MsOnionIdxllegalException("主键idx不能为null，必须是分布式架构，全局唯一递增Long类型");

			new RuntimeException("主键idx不能为null，必须是分布式架构，全局唯一递增Long类型");

		}

		MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段# 结束 ！！！ member=" + member);


	}


	@Test
	public void test05() throws MsOnionIdxllegalException {


		Member member = new Member();
				member.setIdx(2L);
		member.setName("测试成员名-02");
		
		member.setStatus((short) 8);

		MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段# 开始 , member=" + member);


//		MsOnionServiceExt<MsOnionBasePojoAdapter, MsOnionBaseExampleAdapter>

		MsOnionLogger.doInfo(getClass().getName(), "doWithFields # 字段# 检查之后 , member=" + member);

//		new IllegalArgumentException("测试非法参数异常！！！");
		
		new RuntimeException("测试非法参数异常！！！");

	}

}
