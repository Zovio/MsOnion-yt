package test.msonion.carambola.manager.web.controller;

import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Created by HeroCao on 2017/3/11.
 */
@Controller
public class TestCopyProperties {

    @Test
    public void test01() {


        Member member = new Member();

        member.setPassword("password-111");
        member.setCode("code-111");
        member.setCreateByMemberIdx(111L);
        member.setCreateTime(new Date());
        member.setDepartmentIdx(111L);
        member.setEmail("test-111@gmail.com");
        member.setExt("扩展-111");
        member.setFullName("全名-111");
        member.setIdx(111L);
        member.setPhone("15811226688");
        member.setName("测试-01");
        member.setStoreIdx(111L);
        member.setUpdateTime(new Date());

        Member member2 = new Member();

        member2.setPassword("password-111");
        member2.setCode("code-111");
        member2.setCreateByMemberIdx(111L);
        member2.setCreateTime(new Date());
        member2.setDepartmentIdx(111L);
        member2.setEmail("test-111@gmail.com");
        member2.setExt("扩展-111");
        member2.setFullName("全名-111");
        member2.setIdx(111L);
        member2.setPhone("15811226688");
        member2.setName("测试-01");
        member2.setStoreIdx(111L);
        member2.setUpdateTime(new Date());


//        BeanUtils.copyProperties(des, orig);

        try {

            // 为空值 转换 ！！！
            // org.apache.commons.beanutils.ConversionException: No value specified for 'Date'

            // 注册类型转换器
            ConvertUtils.register(new DateConverter(), java.util.Date.class);

            BeanUtils.copyProperties(member, member2);

            MsOnionLogger.doInfo(getClass().getName(), "member=" + member);

            MsOnionLogger.doInfo(getClass().getName(), "member2=" + member2);


        } catch (Exception e) {
//            e.printStackTrace();


            String stackTrace = ExceptionUtils.getStackTrace(e);

            MsOnionLogger.doInfo(getClass().getName(), " ， 22， e=" + e);

            MsOnionLogger.doInfo(getClass().getName(), " ， 33， stackTrace=" + stackTrace);
        }

    }

    @Test
    public void testApiVersion() {

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();

        apiVersion.setRemark("测试备注01");
        apiVersion.setRequestApiVersion("V2.1.0");
        MsOnionLogger.doInfo(TestCopyProperties.class.getName(), "apiVersion=" + apiVersion);

        for (int i = 0; i < 10; i++) {

            MsOnionApiVersion version = MsOnionApiVersionUtils.getApiVersion();

            version.setRemark("备注：" + i);
            version.setRequestApiVersion("V2.0." + i);

            MsOnionLogger.doInfo(TestCopyProperties.class.getName(), "version=" + version);
        }
    }

}
