package test.msonion.carambola.member.service.impl;

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeroCao on 2017/5/19.
 */
//@ContextConfiguration(locations = "classpath:dev/spring/applicationContext-*.xml")
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class TestMemberServiceImpl2 extends AbstractJUnit4SpringContextTests {

    // TODO：必须注意，必须首先运行 msyt-log-service 服务才可以 ！！！

    @Autowired
    private MemberService memberService;

    @Autowired
    private MsOnionDomain msOnionDomain;

    /**
     * Zookeeper服务连接重试策略
     */
    @Autowired
    private RetryPolicy retryPolicy;

    @Autowired
    private MsOnionCuratorZookeeperClient msOnionCuratorZookeeperClient;

    // Spring 异步 ！！！
//    @Async

    @Test
    public void testLogin() throws MsOnionException {

        System.out.println("测试登录 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

//        String name = "super";
//
//        String password = "Aa123456";

        String name = "msyt-01";

        String password = "123456@88";

        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);


        System.out.println("测试登录 ## Beginning ## msOnionResult=" + msOnionResult);


    }

    @Test
    public void testLogin2() throws MsOnionException {

        System.out.println("测试登录 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

//        String name = "super";
//
//        String password = "Aa123456";

//        String name = "msyt-02";

        String name = "msyt-03";

        String password = "123456@88";

        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);


        System.out.println("测试登录 ## Beginning ## msOnionResult=" + msOnionResult);


    }

    @Test
    public void testRegister() throws MsOnionException {

        System.out.println("测试注册 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

//        String name = "RocketMQ";
//
//        String password = "123456@88";
//
//        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);



        Member member = new Member();
        member.setName("msyt-01");
        member.setPassword("123456@88");
        member.setEmail("msyt-01@msyc.cc");
        member.setFullName("测试洋桃01");
        member.setPhone("13911226688");
        member.setSex((short)1);
        member.setTel("020-88998899");
        member.setRemark("测试备注，密码：123456@88");

        MsOnionResult msOnionResult = memberService.register(apiVersion, member);

        // id=
//        long idx =

//        memberService.updateStatus()

        // 注意， 第一次 id=为null  ，  id=6

        System.out.println("测试注册 ## End ## msOnionResult=" + msOnionResult);

        /**
         * 注册成功之后，第一次 id 是没有值
         *
         * 测试注册 ## End ## msOnionResult=MsOnionResult [status=200, msg=OK, data=Member [Hash = 367066629, idx=55, name=msyt-01,
         * fullName=测试洋桃01, code=YT5FpW, password=FDE95F7876A5AA11916354B593BBF4FF, phone=13911226688, tel=020-88998899,
         * sex=1, email=msyt-01@msyc.cc, createByMemberIdx=55, updateByMemberIdx=55,
         * createTime=Fri May 19 23:22:41 CST 2017, updateTime=Fri May 19 23:22:41 CST 2017, status=2, version=5,
         * remark=测试备注，密码：123456@88, storeIdx=0, departmentIdx=0, vipMemberIdx=0, memberCateIdx=0, lastLoginIp=, lastLoginTimeEnYyyyMMddHHmmss=, lastLoginTime=Fri May 19 23:22:41 CST 2017, ext=, serialVersionUID=1, id=]]
         *
         *
         */


    }

    @Test
    public void testRegister2() throws MsOnionException {

        System.out.println("测试注册 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

//        String name = "RocketMQ";
//
//        String password = "123456@88";
//
//        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);



//        Member member = new Member();
//        member.setName("msyt-02");
//        member.setPassword("123456@88");
//        member.setEmail("msyt-02@msyc.cc");
//        member.setFullName("测试洋桃02");
//        member.setPhone("13922226688");
//        member.setSex((short)1);
//        member.setTel("020-88228899");
//        member.setRemark("测试备注，密码：123456@88");

        // 测试洋桃03
        Member member = new Member();
        member.setName("msyt-05");
        member.setPassword("123456@88");
        member.setEmail("msyt-05@msyc.cc");
        member.setFullName("测试洋桃05");
        member.setPhone("13922556688");
        member.setSex((short)1);
        member.setTel("020-88558899");
        member.setRemark("测试备注，密码：123456@88");

        MsOnionResult msOnionResult = memberService.register(apiVersion, member);

//        MsOnionResult msOnionResult = memberService.register()

        // id=
//        long idx =

//        memberService.updateStatus()

        // 注意， 第一次 id=为null  ，  id=6

        System.out.println("测试注册 ## End ## msOnionResult=" + msOnionResult);

        /**
         * 注册成功之后，第一次 id 是没有值
         *
         * 测试注册 ## End ## msOnionResult=MsOnionResult [status=200, msg=OK, data=Member [Hash = 367066629, idx=55, name=msyt-01,
         * fullName=测试洋桃01, code=YT5FpW, password=FDE95F7876A5AA11916354B593BBF4FF, phone=13911226688, tel=020-88998899,
         * sex=1, email=msyt-01@msyc.cc, createByMemberIdx=55, updateByMemberIdx=55,
         * createTime=Fri May 19 23:22:41 CST 2017, updateTime=Fri May 19 23:22:41 CST 2017, status=2, version=5,
         * remark=测试备注，密码：123456@88, storeIdx=0, departmentIdx=0, vipMemberIdx=0, memberCateIdx=0, lastLoginIp=, lastLoginTimeEnYyyyMMddHHmmss=, lastLoginTime=Fri May 19 23:22:41 CST 2017, ext=, serialVersionUID=1, id=]]
         *
         *
         */


    }


    @Test
    public void testUpdateStatus() throws MsOnionException {

        System.out.println("测试修改状态 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        String name = "msyt-01";

        String password = "123456@88";

//        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);

        int result = memberService.updateStatus(apiVersion, 55l, (short)1);

        // id=
//        long idx =

//        memberService.updateStatus()

        // 注意， 第一次 id=为null  ，  id=6

        // 测试修改状态 ## End ## msOnionResult=MsOnionResult [status=401, msg=当前用户账号未激活, data=null]

//        System.out.println("测试修改状态 ## End ## msOnionResult=" + msOnionResult);

        System.out.println("测试修改状态 ## End ## result=" + result);


    }

    @Test
    public void testUpdateStatus2() throws MsOnionException {

        System.out.println("测试修改状态2 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        String name = "msyt-02";

        String password = "123456@88";

//        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);

//        int result = memberService.updateStatus(apiVersion, 56l, (short)1);

//        int result = memberService.updateStatus(apiVersion, 57L, (short)1);

        // 删除 ！！！
        int result = memberService.updateStatus(apiVersion, 57L, (short)2);

        // id=
//        long idx =

//        memberService.updateStatus()

        // 注意， 第一次 id=为null  ，  id=6

        // 测试修改状态 ## End ## msOnionResult=MsOnionResult [status=401, msg=当前用户账号未激活, data=null]

//        System.out.println("测试修改状态 ## End ## msOnionResult=" + msOnionResult);

        System.out.println("测试修改状态2 ## End ## result=" + result);


    }


    @Test
    public void testDelete() throws MsOnionException {

        System.out.println("测试删除 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        String name = "msyt-02";

        String password = "123456@88";

//        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);

//        int result = memberService.updateStatus(apiVersion, 56l, (short)1);

//        MsOnionResult msOnionResult = memberService.delete(apiVersion, 56l);

        MsOnionResultAdapter msOnionResult = memberService.deleteByPrimaryKeyForResult(apiVersion, 56L);

        // id=
//        long idx =

//        memberService.updateStatus()

        // 注意， 第一次 id=为null  ，  id=6

        // 测试修改状态 ## End ## msOnionResult=MsOnionResult [status=401, msg=当前用户账号未激活, data=null]

//        System.out.println("测试修改状态 ## End ## msOnionResult=" + msOnionResult);

        System.out.println("测试修改状态2 ## End ## result=" + msOnionResult);


    }

    @Test
    public void testDelete2() throws MsOnionException {

        System.out.println("测试删除 ## Beginning ...");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        String name = "msyt-02";

        String password = "123456@88";

//        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);

//        int result = memberService.updateStatus(apiVersion, 56l, (short)1);

//        MsOnionResult msOnionResult = memberService.delete(apiVersion, 56l);

//        MsOnionResultAdapter msOnionResult = memberService.deleteByPrimaryKeyForResult(apiVersion, 56L);

//        MsOnionResultAdapter msOnionResult = memberService.deleteByPrimaryKeyForResult(apiVersion, 57L);


//        MsOnionResultAdapter msOnionResult = memberService.deleteByPrimaryKeyForResult(apiVersion, 23L);

//        MsOnionResultAdapter msOnionResult = memberService.deleteByPrimaryKeyForResult(apiVersion, 24L);

        MsOnionResultAdapter msOnionResult = memberService.deleteByPrimaryKeyForResult(apiVersion, 25L);

        // id=
//        long idx =

//        memberService.updateStatus()

        // 注意， 第一次 id=为null  ，  id=6

        // 测试修改状态 ## End ## msOnionResult=MsOnionResult [status=401, msg=当前用户账号未激活, data=null]

//        System.out.println("测试修改状态 ## End ## msOnionResult=" + msOnionResult);

        System.out.println("测试修改状态2 ## End ## result=" + msOnionResult);


    }

    @Test
    public void testQuery01() throws MsOnionException {


        System.out.println("测试查询， Beginning ...");


        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        int pageNum = 1;
        int pageSize = 10;

//        MsOnionResultAdapter msOnionResultAdapter = memberService.queryListByPageForResult(apiVersion, pageNum, pageSize);
//
//        System.out.println("测试查询，11 结果 # msOnionResultAdapter=" + msOnionResultAdapter);




        List<Member> memberList = memberService.queryListByPage(apiVersion, pageNum, pageSize);

        System.out.println("测试查询 22， 结果 # memberList=" + memberList);





        System.out.println("测试查询， End !!!");
    }

    @Test
    public void testQuery02() throws MsOnionException {


        System.out.println("测试查询2， Beginning ...");


        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        int pageNum = 1;
        int pageSize = 12;

//        MsOnionResultAdapter msOnionResultAdapter = memberService.queryListByPageForResult(apiVersion, pageNum, pageSize);
//
//        System.out.println("测试查询，11 结果 # msOnionResultAdapter=" + msOnionResultAdapter);




        List<Member> memberList = memberService.queryListByPage(apiVersion, pageNum, pageSize);

        System.out.println("测试查询 22， 结果 # memberList=" + memberList);





        System.out.println("测试查询2， End !!!");
    }

    @Test
    public void testBatchDelete() throws MsOnionException {

        // 测试批量删除

        System.out.println("测试批量删除， Beginning ...");


        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        List<Long> idxes = new ArrayList<>();
        idxes.add(3L);
        idxes.add(4L);

        int result = memberService.deleteByIdxes(apiVersion, idxes);

        System.out.println("测试批量删除， 结果 # result=" + result);


        System.out.println("测试批量删除， End ！！！ ...");


    }

    // TODO: 修改 状态，需要提供 memberIdx ，把成员传递进来
    // TODO: updateByExample 有问题 ！！！

    @Test
    public void testIdx() throws MsOnionException {

        for (int i = 0; i < 50; i++) {

            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MEMBER_IDX_PATH);

            System.out.println("idx=" + idx);

        }

    }


    @Test
    public void testIdx2() throws MsOnionException {

        for (int i = 0; i < 100; i++) {

            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_ROLE_IDX_PATH);

            System.out.println("idx=" + idx);

            long idx2 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MENU_IDX_PATH);

            System.out.println("idx2=" + idx2);

            long idx3 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_RESOURCE_IDX_PATH);

            System.out.println("idx3=" + idx3);

            long idx4 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_RESOURCEGROUP_IDX_PATH);

            System.out.println("idx4=" + idx4);

//            long idx5 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
//                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MENU_IDX_PATH);
//
//            System.out.println("idx5=" + idx5);



        }

    }


    @Test
    public void testIdx3() throws MsOnionException {

        for (int i = 0; i < 100; i++) {

            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_IMAGE_IDX_PATH);

            System.out.println("idx=" + idx);

            long idx2 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_IDX_PATH);

            System.out.println("idx2=" + idx2);

//            long idx3 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
//                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_ATTRIBUTE_IDX_PATH);
//
//            System.out.println("idx3=" + idx3);
//
//            long idx4 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
//                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_COLLECTION_IDX_PATH);

//            System.out.println("idx4=" + idx4);

//            long idx5 = MsOnionDistributedAtomicLongUtils.getIdx(this.msOnionDomain, this.msOnionCuratorZookeeperClient,
//                    this.retryPolicy, MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MENU_IDX_PATH);
//
//            System.out.println("idx5=" + idx5);



        }

    }

}
