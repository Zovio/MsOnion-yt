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


package cc.msonion.carambola.fileresource.api.netty.upload.utils;

/**
 * @Title: MultipartFileUtils.java
 * @Package: cc.msonion.carambola.fileresource.api.netty.upload.utils
 * @Description: NormalFileUtils上传
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月04日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月04日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.fileresource.pojo.FileResourceInfo;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionPropertiesUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionURLEncodingUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.ext.utils.netty.MsOnionNettyUtils;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 正常10MB 文件上传，包括springmvc from表单提交、以及直接传file byte[]过来
 *
 * @ClassName: NormalFileUtils
 * @Description: 正常10MB 文件上传，包括springmvc from表单提交、已经直接传file byte[]过来
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月04日
 *
 */
public final class NormalFileUtils {

    private NormalFileUtils() {

    }

    /**
     * Properties name
     */
    private static final String PROPERTIES_NAME = FileResourceConstants.NETTY_PROPERTIES_NAME;

    /**
     * Properties
     */
    private static  Properties properties = null;

    /**
     * 上传根目录
     */
    private static String uploadRootDir = null;

    /**
     * 每次上传的大小 10mb
     */
    private static int bufferSize = 0;


    /**
     *  初始化netty属性
     * @param msOnionDomain msOnionDomain
     */
    public static void initProperties(MsOnionDomain msOnionDomain) {
        try {
            properties = MsOnionPropertiesUtils.loadProperties(msOnionDomain.getEnvironment(), PROPERTIES_NAME);
            uploadRootDir = (String) properties.get("netty.fileresource.upload.root.directory");
            String bufferSizeStr = (String) properties.get("netty.server.buffer.size");
            bufferSize = Integer.valueOf(bufferSizeStr);
        } catch (MsOnionException ex) {
            ex.printStackTrace();
        }
    }




    /**
     *
     * @return 获取上传目录
     */
    public static String getUploadRootDir() {
        return uploadRootDir;
    }

    /**
     *  获取配置文件中 bufferSize
     * @return bufferSize
     */
    public static int getBufferSize() {
        return bufferSize;
    }

    /**
     * 上传文件（springmvc支持多个，最大可配置）
     *
     * @param file     全路径名称文件
     * @param filePath 文件路径
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    public static FileResourceInfo uploadMultipartFile(MultipartFile file, String filePath) throws MsOnionException {
        try {
            if (file.isEmpty()) {
                throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
            }

            filePath = genRelativePath(filePath);
            File destFile = genDestFile(file.getOriginalFilename(), filePath, uploadRootDir);

            file.transferTo(destFile);

            // 构造文件上传信息
            FileResourceInfo fileResourceInfo = new FileResourceInfo();
            fileResourceInfo.setMessageId(MsOnionNettyUtils.getMessageIdForUpload());
            fileResourceInfo.setName(file.getOriginalFilename());
            fileResourceInfo.setTotalSize(file.getSize());
            fileResourceInfo.setMd5Value(destFile.getName());
            // window 默认 \需要替换成/
            String p = destFile.getAbsolutePath().substring(destFile.getAbsolutePath().indexOf(uploadRootDir) + uploadRootDir.length());
            fileResourceInfo.setPath(MsOnionFileUtils.transitionPath(p));
            fileResourceInfo.setUploadSize(file.getSize());
            // TODO 通过后缀名区分文件类型
            if (MsOnionFilenameUtils.isImage(file.getOriginalFilename())) {
                fileResourceInfo.setFileType(FileResourceConstants.FILE_PIC);
            } else {
                fileResourceInfo.setFileType(FileResourceConstants.FILE_RESOURCE);
            }
            fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_SUC);
            return fileResourceInfo;
        } catch (IOException e) {
            throw new MsOnionException(e);
        }

    }


    /**
     *  上传文件
     * @param fileName 文件名称
     * @param uploadPath 上传模块
     * @param totalSize 文件大小
     * @param buffer byte[]
     * @return FileResourceInfo 对象
     * @throws MsOnionException 异常
     */
    public static FileResourceInfo uploadNormalFile(String fileName, String uploadPath, Long totalSize, byte[] buffer) throws MsOnionException {
        try {
            if (StringUtils.isBlank(fileName) || StringUtils.isBlank(uploadPath)) {
                throw new MsOnionException("文件参数非法[fileName：" + fileName + "，uploadPath：" + uploadPath + "]", MsOnionStatusConstants.STATUS_400);
            }

            uploadPath = genRelativePath(uploadPath);
            File destFile = genDestFile(fileName, uploadPath, uploadRootDir);

            RandomAccessFile randomAccessFile = RandomAccessFileUtils.initRandomAccessFile(destFile, 0L);
            randomAccessFile.write(buffer);

            // 构造文件上传信息
            FileResourceInfo fileResourceInfo = new FileResourceInfo();
//            fileResourceInfo.setMessageId(MsOnionUUIDUtils.randomUUID());
            fileResourceInfo.setName(fileName);
            fileResourceInfo.setTotalSize(totalSize);
            fileResourceInfo.setMd5Value(destFile.getName());
            fileResourceInfo.setPath(destFile.getAbsolutePath());
            if (MsOnionFilenameUtils.isImage(fileName)) {
                fileResourceInfo.setFileType(FileResourceConstants.FILE_PIC);
            } else {
                fileResourceInfo.setFileType(FileResourceConstants.FILE_RESOURCE);
            }
            fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_SUC);
            return fileResourceInfo;
        } catch (IOException e) {
            throw new MsOnionException(e);
        } finally {
            RandomAccessFileUtils.close();
        }

    }


    /**
     *  创建上传的目标文件
     * @param fileName 文件名称
     * @param uploadPath 上传目录 ，自己会在该目录下生成年月日子目录
     * @param uploadRootDir 文件根目录
     * @return 目标文件
     * @throws MsOnionException 异常
     */
    public static File genDestFile(String fileName, String uploadPath, String uploadRootDir) throws MsOnionException {

        try {
            String destFilename = MsOnionFilenameUtils.generateFilename(fileName);
            File rootFile = MsOnionFileUtils.getFileResourceUploadRootDir(uploadRootDir);
            String absolutePath = rootFile.getAbsolutePath();
            File curFile = new File(absolutePath + uploadPath);
            MsOnionFileUtils.forceMkdir(curFile);
            File destFile = new File(curFile, destFilename);
            return destFile;
        } catch (IOException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     *  生成前端传过的模块路径 + 默认年月日
     * @param uploadPath 模块路径
     * @return 路径
     * @throws MsOnionException 异常
     */
    public static String genRelativePath(String uploadPath) throws MsOnionException {
        // 模块 + 年月日
        String separator = MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR;
        if (StringUtils.isNotBlank(uploadPath)) {
            if (!uploadPath.startsWith(separator)) {
                uploadPath = separator + uploadPath.trim();
            }
            if (!uploadPath.endsWith(separator)) {
                uploadPath = uploadPath.trim() + separator;
            }
        } else {
            uploadPath = uploadPath + separator;
        }
        uploadPath += MsOnionDateUtils.toYyyyMMddUnity(System.currentTimeMillis());
        return uploadPath;
    }

    /**
     * 文件下载时设置response头信息，
     * @param request 请求对象
     * @param response 响应对象
     * @param fileName 文件名称
     * @return 响应对象
     */
    public static HttpServletResponse setDownLoadResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType("application/x-www-form-urlencoded");
        String outfilename = null;
        // IE浏览器 终极解决文件名乱码
        String headInfo = request.getHeader("User-Agent").toUpperCase();
        if (headInfo.indexOf("MSIE") > 0 || headInfo.indexOf("RV:11") >0 || headInfo.indexOf("EDGE") > 0) {
            outfilename = MsOnionURLEncodingUtils.encodeURIComponent(fileName);
        } else {
            // firefox浏览器
            try {
                outfilename = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        response.setHeader("Content-disposition", "attachment;filename=" + outfilename);
        return response;
    }
}
