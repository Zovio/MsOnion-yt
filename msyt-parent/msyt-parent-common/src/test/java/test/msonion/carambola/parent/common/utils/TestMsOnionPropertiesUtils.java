package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionPropertiesUtils;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by HeroCao on 2017/4/23.
 */
public class TestMsOnionPropertiesUtils {

    @Test
    public void testLoadProperties() throws MsOnionException {

        /**
         * ### 测试
         mail.transport.protocol=smtp
         mail.smtp.host =smtp.qq.com
         mail.smtp.port =465
         mail.smtp.auth =true
         mail.smtp.ssl.enable=true
         mail.debug=false
         */

        // add  / , Error :
        String propertiesName = "/properties/config-test.properties";

        System.out.println("before ## propertiesName=" + propertiesName);

        if (propertiesName.startsWith("/")) {
            propertiesName = propertiesName.substring(1, propertiesName.length());
        }

        System.out.println("after ## propertiesName =" + propertiesName);

        // OK
//        String propertiesName = "properties/config-test.properties";

        Properties properties = MsOnionPropertiesUtils.loadProperties(null, propertiesName);

        System.out.println("properties=" + properties);


        System.out.println("properties # mail.transport.protocol=" + properties.getProperty("mail.transport.protocol"));

        /*
        properties={mail.debug=false, mail.smtp.port=465, mail.transport.protocol=smtp, mail.smtp.auth=true, mail.smtp.host=smtp.qq.com, mail.smtp.ssl.enable=true}
properties # mail.transport.protocol=smtp

         */
    }

}
