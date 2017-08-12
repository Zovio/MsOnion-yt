package test.msonion.carambola.commons.common.dfs.clients;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;


/**
 * Created by HeroCao on 2017/4/20.
 */
public class TestFastDFSClient2 {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient1 = null;
    private StorageClient storageClient = null;

    /**
     * @param conf
     * @throws Exception
     */
    public TestFastDFSClient2(String conf) throws Exception {
        this(conf, false);
    }

    /**
     * @param conf
     * @param isUse1 是否使用1的方法上传，也就是上传成功之后，返回String的方法
     * @throws Exception
     */
    public TestFastDFSClient2(String conf, boolean isUse1) throws Exception {
//		this.isUse1 = isUse1;
//        if (conf.contains("classpath:")) {
//            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
//        }

        if (conf.contains("classpath:")) {
            conf = this.getClass().getResource(conf.replace("classpath:", "")).getPath();
        } else {
            conf = this.getClass().getResource(conf).getPath();
        }

        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        if (isUse1) {
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } else {
            storageClient = new StorageClient(trackerServer, storageServer);
        }
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
    public String uploadFile1(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient1.upload_file1(fileName, extName, metas);
        return result;
    }

    /**
     * @Title: uploadFile
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午10:39:34
     *
     * @param fileName
     * @param extName
     * @param metas
     * @return
     * @throws Exception
     */
    public String[] uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String[] result = storageClient.upload_file(fileName, extName, metas);
        return result;
    }

    /**
     * @Title: uploadFile1
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:09:07
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public String uploadFile1(String fileName) throws Exception {
        return uploadFile1(fileName, null, null);
    }

    /**
     * @Title: uploadFile
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:08:59
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public String[] uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    /**
     * @Title: uploadFile1
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:08:51
     *
     * @param fileName
     * @param extName
     * @return
     * @throws Exception
     */
    public String uploadFile1(String fileName, String extName) throws Exception {
        return uploadFile1(fileName, extName, null);
    }

    /**
     * @Title: uploadFile
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:08:41
     *
     * @param fileName
     * @param extName
     * @return
     * @throws Exception
     */
    public String[] uploadFile(String fileName, String extName) throws Exception {
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
    public String uploadFile1(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient1.upload_file1(fileContent, extName, metas);
        return result;
    }

    /**
     * @Title: uploadFile
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:08:27
     *
     * @param fileContent
     * @param extName
     * @param metas
     * @return
     * @throws Exception
     */
    public String[] uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String[] result = storageClient.upload_file(fileContent, extName, metas);
        return result;
    }

    /**
     * @Title: uploadFile1
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:08:14
     *
     * @param fileContent
     * @return
     * @throws Exception
     */
    public String uploadFile1(byte[] fileContent) throws Exception {
        return uploadFile1(fileContent, null, null);
    }

    /**
     * @Title: uploadFile
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:08:05
     *
     * @param fileContent
     * @return
     * @throws Exception
     */
    public String[] uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    /**
     * @Title: uploadFile1
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:07:49
     *
     * @param fileContent
     * @param extName
     * @return
     * @throws Exception
     */
    public String uploadFile1(byte[] fileContent, String extName) throws Exception {
        return uploadFile1(fileContent, extName, null);
    }

    /**
     * @Title: uploadFile
     * @Description: 上传文件
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午11:05:11
     *
     * @param fileContent
     * @param extName
     * @return
     * @throws Exception
     */
    public String[] uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    /**
     * @Title: close
     * @Description: 关闭服务，释放资源占用
     * @author HeroCao herobigdatacao@126.com
     * @date 2016年7月31日 下午10:18:00
     *
     */
    public void close() {
        try {
            if (null != storageServer) {
                storageServer.close();
                storageServer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != trackerServer) {
                    trackerServer.close();
                    trackerServer = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {


//        System.out.println("/ getPath=" + this.getClass().getResource("/").getPath());


        // OK
        System.out.println("/ getPath1=" + TestFastDFS.class.getClassLoader().getResource("config/config-fastdfs.conf").getPath());

        // 报错：
//        System.out.println("/ getPath2=" + TestFastDFS.class.getClassLoader().getResource("/config/config-fastdfs.conf").getPath());

        // 测试上传
        testUpload2();

    }

    private static void testUpload2() throws Exception {

        String configFilename = "/config/config-fastdfs.conf";

//        TestFastDFSClient2 client = new TestFastDFSClient2(configFilename, true);

        TestFastDFSClient2 client = new TestFastDFSClient2(configFilename, false);

        String local_filename = "C:/Users/HeroCao/Desktop/yt.png";

        String file_ext_name = "jpg";  // 扩展名，不包含.(点）

        // 上传
        String[] infos = client.uploadFile(local_filename, file_ext_name);

        for (String info : infos) {

            System.out.println("上传图片 # FastDFS服务器返回 # info="+info);

            /**
             * 上传图片 # FastDFS服务器返回 # info=group1
             上传图片 # FastDFS服务器返回 # info=M00/00/00/rBAy8lj4sViALzRNAABjYaRbeZk171.jpg
             */
        }

        client.close();

        System.out.println("=========== 上传完成 ！！！===");

    }


}
