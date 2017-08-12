package test.msonion.carambola.parent.ext.service.member;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import test.msonion.carambola.parent.ext.service.member.service.TestMemberService;

/**
 * Created by HeroCao on 2017/5/19.
 */
@ContextConfiguration(locations = "classpath:dev/spring/applicationContext-*.xml")
public class TestMsOnionService extends AbstractJUnit4SpringContextTests {

    @Autowired
    private TestMemberService testMemberService;

    @Test
    public void testRegisterMember() {



    }

    @Test
    public void testLogin() throws MsOnionException {

        System.out.println("testLogin # 测试登录 。。。");

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion("V2.0.0");

        String username = "super";
        String password = "Aa123456";

        MsOnionResult msOnionResult = testMemberService.login(apiVersion, username, password);


        System.out.println("测试，查询 # msOnionResult=" + msOnionResult);

    }


}
