package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/5/1.
 */
public class TestMsOnionFilenameUtils {

    @Test
    public void testGenerateFilename() throws MsOnionException {

        String filename = "11.jpg";


        String name = MsOnionFilenameUtils.generateFilename(filename);

        // 20170501203428411_472.jpg
        // 20170501203452116_212.jpg
        System.out.println("生成文件名称： name=" + name);

    }

}
