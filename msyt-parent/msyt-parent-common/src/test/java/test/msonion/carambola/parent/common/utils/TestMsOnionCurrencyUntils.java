package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/6/2.
 */
public class TestMsOnionCurrencyUntils {

    @Test
    public void test01() throws MsOnionException {

        String fixLength = MsOnionFixedLengthUtils.fixLength(1, 5);


        System.out.println("fixLength=" + fixLength);
    }

}
