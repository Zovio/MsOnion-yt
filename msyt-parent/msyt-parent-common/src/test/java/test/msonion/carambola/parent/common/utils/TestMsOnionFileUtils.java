package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created by HeroCao on 2017/5/1.
 */
public class TestMsOnionFileUtils {


    @Test
    public void test01() throws MsOnionException {

//        String path = "/";

        String path = "C:/Users/HeroCao/Downloads/";

        File dir = MsOnionFileUtils.getFileResourceUploadRootDir(path);

        System.out.println("dir=" + dir);

    }
}
