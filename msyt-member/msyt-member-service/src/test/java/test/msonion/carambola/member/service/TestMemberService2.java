package test.msonion.carambola.member.service;

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.member.pojo.Menu;
import cc.msonion.carambola.member.pojo.MenuExample;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.member.service.MenuService;
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

/**
 * Created by HeroCao on 2017/5/19.
 */
@ContextConfiguration(locations = "classpath:dev/spring/applicationContext-*.xml")
//public class TestMemberServiceImpl2 extends AbstractJUnit4SpringContextTests {
public class TestMemberService2 extends AbstractJUnit4SpringContextTests {


    // TODO：必须注意，必须首先运行 msyt-log-service 服务才可以 ！！！

    @Autowired
    private MemberService memberService;

    @Autowired
    private MsOnionDomain msOnionDomain;

    @Autowired
    private MenuService menuService;

    /**
     * Zookeeper服务连接重试策略
     */
    @Autowired
    private RetryPolicy retryPolicy;

    @Autowired
    private MsOnionCuratorZookeeperClient msOnionCuratorZookeeperClient;

//    @Test
//    public void testIdx() {
//
//        for (int i = 0; i < 130; i++) {
//
//
//        }
//    }


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
    public void testAddMenu01() throws MsOnionException, InterruptedException {

        // 添加菜单

        Menu menu = new Menu();

        menu.setCode("test-menu-110");
        menu.setLevel((short)1);
        menu.setName("测试菜单-110");
        menu.setRemark("备注：测试菜单-110");
        menu.setTopShow((short)1);
        menu.setZindex((short)1);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        MsOnionResult msOnionResult = menuService.saveOrUpdate(apiVersion, menu);


        System.out.println("新增菜单 ## msOnionResult=" + msOnionResult);

        /*
        新增菜单 ## msOnionResult=MsOnionResult [status=200, msg=OK, data=Menu [Hash = 1421650289, idx=123, pidx=0, code=test-menu-105, name=测试菜单-105, url=, urlMapping=, remark=备注：测试菜单-105, zindex=1, topShow=1, level=1, iconUrl=, menuCateIdx=0, markButton=0, status=1, version=23, createByMemberIdx=0, updateByMemberIdx=0, createTime=Mon May 29 17:00:20 CST 2017,
        updateTime=Mon May 29 17:00:20 CST 2017, ext=, serialVersionUID=1, id=, pid=]]
         */

        /*
        [status=200, msg=OK, data=Menu [Hash = 1549840544, idx=124,

         */

        System.out.println("休眠 2分钟 ！！！");

        // 休眠2分钟
        Thread.sleep(2000L);


    }

    @Test
    public void testUpdateMenu() throws MsOnionException, InterruptedException {

        Menu menu = new Menu();

        menu.setCode("test-menu-107");
        menu.setLevel((short)1);
        menu.setName("测试菜单-107，修改5");
        menu.setRemark("备注：测试菜单-107，修改5");
        menu.setTopShow((short)1);
        menu.setZindex((short)2);
        long idx = 125L;
        menu.setStatus((short)1);

        menu.setIdx(idx);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        int result = menuService.updateByPrimaryKey(apiVersion, menu);

        System.out.println("result=" + result);


        System.out.println("休眠500 毫秒 。。。");

        // 线程休眠
        Thread.sleep(2000L);

    }



    @Test
    public void testQueryMenu() throws MsOnionException {

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        int pageNum = 2;
        int pageSize = 10;
//        MsOnionResultAdapter msOnionResultAdapter = memberService.queryListByPageForResult(apiVersion, pageNum, pageSize);

        MsOnionResultAdapter msOnionResultAdapter = menuService.queryListByPageForResult(apiVersion, pageNum, pageSize);

        System.out.println("新增菜单 ## msOnionResultAdapter=" + msOnionResultAdapter);

    }

    @Test
    public void testQueryMenu2() throws MsOnionException {

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        int pageNum = 2;
        int pageSize = 10;
//        MsOnionResultAdapter msOnionResultAdapter = memberService.queryListByPageForResult(apiVersion, pageNum, pageSize);

//        MsOnionResultAdapter msOnionResultAdapter = menuService.queryListByPageForResult(apiVersion, pageNum, pageSize);

        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();

        criteria.andIdxBetween(1L, 100L);

        MsOnionResultAdapter msOnionResultAdapter = menuService.queryByExampleForResult(apiVersion, example);


        System.out.println("新增菜单 ## msOnionResultAdapter=" + msOnionResultAdapter);

    }



}
