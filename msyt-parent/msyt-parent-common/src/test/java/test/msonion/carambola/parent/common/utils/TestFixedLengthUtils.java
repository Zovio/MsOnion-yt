package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/4/9.
 */
public class TestFixedLengthUtils {

    @Test
    public void test01() throws MsOnionException {

        String fixZero = MsOnionFixedLengthUtils.fixZero(5);

        String fixLength = MsOnionFixedLengthUtils.fixLength(2L, 8);


        System.out.println("fixZero=" + fixZero);

        System.out.println("fixLength=" + fixLength);



    }

    @Test
    public void test02() {

//        MsOnionDateUtils.is



    }

}
