package test.msonion.carambola.commons.common.dfs.clients;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * Created by HeroCao on 2017/4/20.
 */
public class TestFastDFS {


    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public TestFastDFS(String conf) throws Exception {

//        System.out.println("/ getPath2=" + this.getClass().getResource("/").getPath());

        System.out.println("conf 1=" + conf);

        if (conf.contains("classpath:")) {
            conf = this.getClass().getResource(conf.replace("classpath:", "")).getPath();
        } else {
            conf = this.getClass().getResource(conf).getPath();
        }

        System.out.println("conf 2=" + conf);

        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName 文件全路径
     * @param extName 文件扩展名，不包含（.）
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent 文件的内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }


    public static void main(String[] args) throws Exception {


//        System.out.println("/ getPath=" + this.getClass().getResource("/").getPath());


        // OK
        System.out.println("/ getPath1=" + TestFastDFS.class.getClassLoader().getResource("config/config-fastdfs.conf").getPath());

        // 报错：
//        System.out.println("/ getPath2=" + TestFastDFS.class.getClassLoader().getResource("/config/config-fastdfs.conf").getPath());

        // 测试上传
        testUpload();

    }

    private static void testUpload() throws Exception {

        String configFilename = "/config/config-fastdfs.conf";

//        String configFilename = "C:/Users/HeroCao/Downloads/HeroCao/Code/GitLab/2017-03-22/Ms.Onion-YT/msyt-commons/msyt-commons-common/config-fastdfs.conf";

        TestFastDFS client = new TestFastDFS(configFilename);

//        String local_filename = "C:\\Users\\HeroCao\\Desktop\\2017-2.jpg";

        String local_filename = "C:/Users/HeroCao/Desktop/2017-2.jpg";

        String file_ext_name = "jpg";  // 扩展名，不包含.(点）

        // 上传
//        client.uploadFile()
        String info = client.uploadFile(local_filename, file_ext_name);

        // FastDFS服务器返回，上传信息 # info=group1/M00/00/00/wKhkkVed4NqAcoDuAADCiIhwGH4627.jpg
        System.out.println("FastDFS服务器返回，上传信息 # info=" + info);



        // C:\Users\HeroCao\Downloads\HeroCao\Code\GitLab\2017-03-22\Ms.Onion-YT\msyt-commons\msyt-commons-common\target\test-classes\config

    }



}
