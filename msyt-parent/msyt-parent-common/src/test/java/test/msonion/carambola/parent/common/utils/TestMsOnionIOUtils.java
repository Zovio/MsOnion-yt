package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.utils.MsOnionIOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HeroCao on 2017/4/21.
 */
public class TestMsOnionIOUtils {

    @Test
    private void test01() throws IOException {

        // 关闭输入输出流
        // InputStream , OutputStream
//        MsOnionIOUtils.closeQuietly();

        // 伪代码
        InputStream is = null;
        // IOException 抛出 IOException 异常，最后要转换成 MsOnionException
        // 使用 try - catch 之后 转换 ！！！
        MsOnionIOUtils.toByteArray(is);

    }

}
