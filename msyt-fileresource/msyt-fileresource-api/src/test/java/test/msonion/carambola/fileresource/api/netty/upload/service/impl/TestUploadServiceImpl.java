package test.msonion.carambola.fileresource.api.netty.upload.service.impl;

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.msonion.carambola.fileresource.api.netty.upload.pojo.TestUploadInfo;
import test.msonion.carambola.fileresource.api.netty.upload.service.TestUploadService;


import java.io.File;

/**
 * Created by HeroCao on 2017/4/30.
 */
@Service
public class TestUploadServiceImpl implements TestUploadService {
 
    /**
     * 上传根目录
     */
    @Value("${netty.fileresource.upload.root.directory}")
    private String uploadRootDir;

    @Override
    public void update(TestUploadInfo uploadInfo) {

    }

    @Override
    public TestUploadInfo query(String filename) throws InterruptedException {

        System.out.println("TestUploadServiceImpl ## query ## 执行了 ## filename=" + filename);

        if ("slow".equals(filename)) {
            Thread.sleep(300L);
        } else if ("500".equalsIgnoreCase(filename.trim())) {

            System.out.println("TestUploadServiceImpl ## query ## 执行了 ## 休眠500毫秒 ## filename=" + filename);


            Thread.sleep(100L);
        } else if ("1000".equalsIgnoreCase(filename.trim())) {

            System.out.println("TestUploadServiceImpl ## query ## 执行了 ## 休眠1000毫秒 ## filename=" + filename);


            Thread.sleep(200L);
        }
        return new TestUploadInfo(filename);

    }

    @Override
    public TestUploadInfo queryWithSystemException(String filename) {
        return null;
    }

    /**
     * 上传文件
     *
     * @param filename 文件全路径名称
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    @Override
    public MsOnionResult uploadFile(String filename) throws MsOnionException {


        // 将文件保存到服务器

        if (StringUtils.isBlank(filename)) {
            throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
        }

        filename = filename.trim();

//        FileUtils.


        return uploadFile(new File(filename));
    }

    /**
     * 上传文件
     *
     * @param file 全路径名称文件
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    @Override
    public MsOnionResult uploadFile(File file) throws MsOnionException {

        if (null == file || !file.exists() || !file.canRead() || !file.isFile()) {
            throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
        }

//        MsOnionIOUtils.s


//        FileUtils.convertFileCollectionToFileArray()

        // 保存上传文件的URL到数据库的时候，不要加上 域名 和 协议
        // 只保存 path 就可以了


        // TODO: 相册中，收集器，路径是有规则的 ！！！
        // TODO: 这里只是测试 ！！！

        String extension = MsOnionFilenameUtils.getExtension(file.getName());

        long time = System.currentTimeMillis();


        String destFilename = String.format("%s/%s.%s", MsOnionRandomUtils.getRandom(1,100), time, extension);


        File destFile = new File(MsOnionFileUtils.getFileResourceUploadRootDir(uploadRootDir), destFilename);

        System.out.println("上传文件 # destFilename=" + destFilename);

        System.out.println("上传文件 # destFile=" + destFile);


//        MsOnionPropertiesUtils.loadProperties()

        try {
            // TODO: 如果是大文件 , 512MB , 1G , 2G ，直接这样整个文件拷贝，是不行的！！！
            // TODO: 服务器的内存会爆掉的！！！
            // TODO: 优化 使用 读取一部分 ，写入部分
            // 这里是测试！！！
            // copyFile 有BUG，没有关闭 父目录IO，无法删除 ！！！
//            MsOnionFileUtils.copyFile(file, destFile);

            // 没有关闭 Stream : The source stream is left open
//            MsOnionFileUtils.copyToFile();

//            MsOnionFileUtils.copyToFile(file, destFile);

            // 关闭 Stream ： The source stream is closed.
//            MsOnionFileUtils.copyInputStreamToFile();

//            MsOnionFileUtils.copyFile();

            MsOnionFileUtils.uploadFile(file, destFile);

//            RandomAccessFile



            // 返回结果
            return MsOnionResult.ok(destFile);
        } catch (Exception ex) {
           throw new MsOnionException(ex);
        }

    }
}
