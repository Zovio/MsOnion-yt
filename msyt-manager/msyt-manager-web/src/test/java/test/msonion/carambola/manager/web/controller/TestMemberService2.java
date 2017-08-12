package test.msonion.carambola.manager.web.controller;

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


    @Test
    public void testLogin01() throws MsOnionException {

        // 测试登录 ，优化SQL语句

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        /*
         SQL：[SELECT count(0) FROM tb_member WHERE (status = ? AND name = ? AND password = ?) OR (status = ? AND password = ? AND email = ?) OR (status = ? AND password = ? AND phone = ?)]，执行耗时：[1629 ms]
         */

        String name = "super";
        String password = "Aa123456";

        MsOnionResult msOnionResult = memberService.login(apiVersion, name, password);

        System.out.println("分析SQL执行语句，测试登录 ## msOnionResult=" + msOnionResult);


    }

    @Test
    public void testAddMenu01() throws MsOnionException {

        // 添加菜单

        Menu menu = new Menu();

        menu.setCode("test-menu-102");
        menu.setLevel((short)1);
        menu.setName("测试菜单-102");
        menu.setRemark("备注：测试菜单-102");
        menu.setTopShow((short)1);
        menu.setZindex((short)1);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        MsOnionResult msOnionResult = menuService.saveOrUpdate(apiVersion, menu);


        System.out.println("新增菜单 ## msOnionResult=" + msOnionResult);

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
