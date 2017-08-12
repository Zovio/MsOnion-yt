package test.msonion.carambola.parent.ext.dfs.clients;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.dfs.clients.MsOnionFastDFSClient;
import cc.msonion.carambola.parent.ext.pojo.dfs.MsOnionFastDFSParameter;
import cc.msonion.carambola.parent.ext.utils.dfs.MsOnionFastDFSClientUtils;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/4/20.
 */
public class TestMsOnionFastDFSClient {

    @Test
    public void testUpload01() throws MsOnionException {

        String configFilename = "/config/config-fastdfs.conf";

        MsOnionFastDFSClient client = new MsOnionFastDFSClient(configFilename);

        MsOnionFastDFSParameter parameter = new MsOnionFastDFSParameter();

//        String filename = "C:/Users/HeroCao/Desktop/Version的生成-20170413113927.png";

        String filename = "C:/Users/HeroCao/Desktop/3.jpg";

        parameter.setFilename(filename);

        MsOnionResultAdapter adapter = client.uploadFile(parameter);

        /**
         * 上传完成 , adapter=MsOnionResult [status=200, msg=OK, data=http://yt02-dev/group1/M00/00/00/rBAy8lj4zNCAWt2bAAG12zMD0aA429.png]
         上传完成！！！
         */
        System.out.println("上传完成 , adapter=" + adapter);

        System.out.println("上传完成！！！");

    }

    @Test
    public void testUpload02() throws MsOnionException {

        String configFilename = "/config/config-fastdfs.conf";

        // JavaEE.jpg
        // Version的生成-20170413113927.png
//        String local_filename = "C:/Users/HeroCao/Desktop/JavaEE.jpg";

        String local_filename = "C:/Users/HeroCao/Desktop/Version的生成-20170413113927.png";

//        String local_filename = "C:/Users/HeroCao/Desktop/yt.jpg";

        MsOnionFastDFSClient client = new MsOnionFastDFSClient(configFilename);


        String[] paths = MsOnionFastDFSClientUtils.uploadFile(client, local_filename);

        // path=group1/M00/00/00/rBAy8lj4vQKAeksaAAFG13Pg-VU809.jpg
//        System.out.println("path=" + path);

        for (String path : paths) {
            System.out.println("path=" + path);
        }


        System.out.println("上传完成！！！");
    }

}
