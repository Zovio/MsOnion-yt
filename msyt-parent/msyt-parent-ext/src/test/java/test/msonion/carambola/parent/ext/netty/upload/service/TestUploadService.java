package test.msonion.carambola.parent.ext.netty.upload.service;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import test.msonion.carambola.parent.ext.netty.upload.pojo.TestUploadInfo;

import java.io.File;
import java.io.Serializable;

/**
 * Created by HeroCao on 2017/4/30.
 */
public interface TestUploadService extends Serializable {


//    void upload() throws MsOnionException;

    void update(TestUploadInfo uploadInfo);
    TestUploadInfo query(String filename) throws InterruptedException;
    TestUploadInfo queryWithSystemException(String filename);


    // 断点续传 ，断点续传

    /**
     * 上传文件
     * @param filename 文件全路径名称
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    MsOnionResult uploadFile(String filename) throws MsOnionException;

    /**
     * 上传文件
     * @param file 全路径名称文件
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    MsOnionResult uploadFile(File file) throws MsOnionException;

}
