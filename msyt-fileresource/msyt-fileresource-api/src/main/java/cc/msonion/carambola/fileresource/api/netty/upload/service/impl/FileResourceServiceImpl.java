/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */


package cc.msonion.carambola.fileresource.api.netty.upload.service.impl;

/**
 * @Title: FileResourceServiceImpl.java
 * @Package: cc.msonion.carambola.fileresource.api.netty.upload.service.impl
 * @Description: 文件中心 服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月03日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月03日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.fileresource.pojo.FileResourceInfo;
import cc.msonion.carambola.fileresource.api.netty.upload.service.FileResourceService;
import cc.msonion.carambola.fileresource.api.netty.upload.utils.NormalFileUtils;
import cc.msonion.carambola.fileresource.api.netty.upload.utils.RandomAccessFileUtils;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @ClassName: FileResourceServiceImpl
 * @Description: 文件中心 服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月03日
 */
@Service
public class FileResourceServiceImpl implements FileResourceService {

    /**
     * 上传根目录
     */
    @Value("${netty.fileresource.upload.root.directory}")
    private String uploadRootDir;

    /**
     * 上传文件
     *
     * @param filename 文件名称
     * @param filePath 文件路径
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    @Override
    public FileResourceInfo uploadFile(String filename, String filePath) throws MsOnionException {
        // 将文件保存到服务器

        if (StringUtils.isBlank(filename)) {
            throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
        }

        filename = filename.trim();

        return uploadFile(new File(filename), filePath);
    }

    /**
     * 上传文件
     *
     * @param file 全路径名称文件
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    @Override
    public FileResourceInfo uploadFile(File file, String filePath) throws MsOnionException {
        if (null == file || !file.exists() || !file.canRead() || !file.isFile()) {
            throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
        }

        try {
            // 模块 + 年月日
            if (StringUtils.isNotBlank(filePath)) {
                filePath += MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR + MsOnionDateUtils.toYyyyMMddUnity(System.currentTimeMillis());
            }


            String destFilename = MsOnionFilenameUtils.generateFilename(file.getPath());

            File rootFile = MsOnionFileUtils.getFileResourceUploadRootDir(uploadRootDir);
            String absolutePath = rootFile.getAbsolutePath();

            File curFile = new File(absolutePath + filePath);
            MsOnionFileUtils.forceMkdir(curFile);

            File destFile = new File(curFile, destFilename);

            System.out.println("##### 源文件大小：" + file.length() + "B , 源文件名称：" + file.getName());
            System.out.println("上传文件 # destFilename=" + destFilename);
            System.out.println("上传文件 # destFile=" + destFile);


            MsOnionFileUtils.uploadLargeFile(file, destFile);

            // 构造文件上传信息
            FileResourceInfo fileResourceInfo = new FileResourceInfo();
            fileResourceInfo.setName(file.getName());
            fileResourceInfo.setTotalSize(file.length());
            fileResourceInfo.setMd5Value(destFilename);
            fileResourceInfo.setPath(destFile.getAbsolutePath());

//            RandomAccessFile

            // 返回结果
            // TODO: 上传成功，需要保存 上传原始文件名称，服务器保存的路径， 生成文件名称
            // 保存文件名称： 时间戳 + 随机数
            // TODO： 上传文件总大小 byte 为单位， total_size , upload_size
            // TODO: 上传状态：uploading：2 ， uploaded （上传完成） : 1  , 上传失败：0
            // TODO： 文件类型，  视频、Excel、Word 、图片、文件
            // TODO: 如果上传的是图片类型，必须 有图片 指纹ID，后续实现以图搜图 ！！！
            // TODO: 下载， 下载进度， 下载状态，
            // TODO: 断点下载 、断点上传 ！！！
            /*
            京东的图片路径：
             https://img13.360buyimg.com/n5/s54x54_jfs/t4642/110/753072126/121222/5556881f/58d484a0N1d9d2ebf.jpg
             阿里云： OSS
             http://msyc-img.oss-cn-shenzhen.aliyuncs.com/product/image/20151228/20151228204706997_400.jpg

             */

            // TODO: 所有 和域名、协议相关都需要使用 MsOnionDomain
            return fileResourceInfo;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param filename   文件名称
     * @param filePath   文件路径
     * @param totalSize  文件总大小
     * @param uploadStart 文件上传的位置
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    @Override
    public FileResourceInfo uploadLargeFile(String filename, String filePath, Long totalSize, Long uploadStart, byte[] bytes) throws MsOnionException {
        if (StringUtils.isBlank(filename)) {
            throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
        }
        if (totalSize < 0L || uploadStart < 0L || totalSize < uploadStart) {
            throw new MsOnionException("上传文件大小非法", MsOnionStatusConstants.STATUS_400);
        }
        File destFile = null;
        // filePath 为绝对路径时，直接找到文件、续传
        destFile = new File(filePath);
        if (!destFile.exists()) {
            filePath = NormalFileUtils.genRelativePath(filePath);
            destFile = NormalFileUtils.genDestFile(filename, filePath, uploadRootDir);
        }
        try {
            RandomAccessFile randomAccessFile = RandomAccessFileUtils.initRandomAccessFile(destFile, uploadStart);
            randomAccessFile.write(bytes);

            FileResourceInfo fileResourceInfo = new FileResourceInfo();
            fileResourceInfo.setName(filename);
            fileResourceInfo.setTotalSize(totalSize);
            fileResourceInfo.setMd5Value(destFile.getName());
            fileResourceInfo.setUploadSize(uploadStart + bytes.length);
            fileResourceInfo.setPath(MsOnionFileUtils.transitionPath(destFile.getAbsolutePath().substring(destFile.getAbsolutePath()
                    .indexOf(uploadRootDir) + uploadRootDir.length())));
            // 图片类型
            if (MsOnionFilenameUtils.isImage(filename)) {
                fileResourceInfo.setFileType(FileResourceConstants.FILE_PIC);
            } else {
                // 其他类型
                fileResourceInfo.setFileType(FileResourceConstants.FILE_RESOURCE);
            }

            // 文件上传大小 < 文件总大小，上传中
            if (fileResourceInfo.getUploadSize() < totalSize){
                fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_ING);
            } else {
                fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_SUC);
            }
            return fileResourceInfo;
        } catch (IOException e) {
            throw new MsOnionException(e);
        } finally {
            RandomAccessFileUtils.close();
        }

    }


}
