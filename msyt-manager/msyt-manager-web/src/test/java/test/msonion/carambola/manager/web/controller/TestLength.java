package test.msonion.carambola.manager.web.controller;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import org.junit.Test;

public class TestLength {




    /**
     * 描述信息
     */
    @Test
    public void test() throws Exception {
        //String str="Biofermin是若本制药株式会社旗下的品牌，主营肠胃用药，更是日本最畅销整肠乳酸菌产品之一。因为卓越的医药疗效，深受亚洲各国家庭的喜爱。";

        String str="Lamisil是澳洲知护理保健品品牌。其足部护理受到欧洲皮肤科医生，药剂师青睐，成为欧洲家庭家喻户晓的护足产品。";


        System.out.println(str.length());
        System.out.println(str.toCharArray().length);
        System.out.println(MsOnionStringUtils.length(str));
    }

}
