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


package cc.msonion.carambola.fileresource.api.controller.app;

/**
 * @Title: FileResourceController.java
 * @Package: cc.msonion.carambola.fileresource.api.controller.app
 * @Description: 文件资源中心controller，采用腾讯云 对象存储
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月13日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月13日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseCOSAppController;
import cc.msonion.carambola.fileresource.api.ext.FileResourcePersistUtils;
import cc.msonion.carambola.fileresource.api.netty.upload.utils.NormalFileUtils;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.fileresource.interfaces.AblumLibService;
import cc.msonion.carambola.fileresource.interfaces.FileResourceDownloadService;
import cc.msonion.carambola.fileresource.interfaces.FileResourceUploadService;
import cc.msonion.carambola.fileresource.pojo.*;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.concurrent.MsOnionThreadPoolUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.utils.netty.MsOnionNettyUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.http.ResponseBodyKey;
import com.qcloud.cos.request.GetFileInputStreamRequest;
import com.qcloud.cos.request.UploadFileRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: FileResourceController
 * @Description: 文件资源中心controller，采用腾讯云 对象存储
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月13日
 *
 */
@Controller
public class FileResourceController extends MsOnionBaseCOSAppController {

    /**
     * fileResourceUploadService
     */
    @Autowired
    private FileResourceUploadService fileResourceUploadService;

    /**
     * fileResourceDownloadService
     */
    @Autowired
    private FileResourceDownloadService fileResourceDownloadService;

    /**
     * ablumLibService
     */
    @Autowired
    private AblumLibService ablumLibService;

    /**
     *  大文件上传线程池（10）
     */
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(MsOnionConstants.POOL_SIZE);

    /**
     * 拦截所有匹配不到的路径，"/**" 拦截所有请求 , 包括 JS ， CSS，所有的请求，都会执行这里 ！！！
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 返回路径
     */
    @RequestMapping("/**")
    @ResponseBody
    public MsOnionResultAdapter showAll(HttpServletRequest req, HttpServletResponse res, Model model) {

        this.getMsOnionLogger().info(getClass().getName(), "showAll #  所有非法请求 ， 域名信息，this.domain=" + this.getMsOnionDomain());

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "非法请求！");
    }

    /**
     * 测试页面
     * @param req 请求对象
     * @param res 响应对象
     * @throws  MsOnionException 异常
     * @return 页面
     */
   /*@RequestMapping("/test2")
    public String test(HttpServletRequest req, HttpServletResponse res) throws MsOnionException {
        System.out.println(getBucketName());
        System.out.println(MsOnionFilenameUtils.generateCOSFilePath("test/aa/", "aa.txt"));
        return "index2";
    }*/

    /**
     * 支持多文件上传图片，最多支持2GB
     *
     * @param request  request
     * @param response response
     * @return 结果
     */
    @RequestMapping(value = "/cos/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResultAdapter uploadMultipartFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 支持跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            String referer = request.getHeader("Referer");
            getMsOnionLogger().debug(getClass().getName(), " #### uploadMultipartFile <-- " + referer);

            // 获取页面传递过来文件路径
            String filePath = request.getParameter("filePath");

            String remark = request.getParameter("remark");
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);

            List list = new ArrayList();
            // 创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            // 判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file.isEmpty()) {
                        continue;
                    }
                    // 获取文件总长度
                    long fileSize = file.getSize();
                    String fileName = file.getOriginalFilename();
                    getMsOnionLogger().info(getClass().getName(), " ## 文件名：" + fileName + ", 大小" + fileSize);
                    FileResourceInfo fileResourceInfo = null;
                    // 拦截没有权限的文件资源
                    if (!MsOnionFilenameUtils.isFileResource(fileName)) {
                        fileResourceInfo = new FileResourceInfo();
                        fileResourceInfo.setName(fileName);
                        fileResourceInfo.setRemark(FileResourceConstants.INVALID_FILE_FORMAT);
                        fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_FAIL);
                        list.add(fileResourceInfo);
                        continue;
                    }

                    // 要上传到 cos 的路径
                    String cosFilePath = MsOnionFilenameUtils.generateCOSFilePath(filePath, fileName);
                    byte[] bytes = file.getBytes();
                    JSONObject jsonObject = null;
                     // 大于20MB 的文件异步上传，通过日志查看结果、先返回成功
                    if (bytes.length > MsOnionFileUtils.DEFALUT_COS_FILESIZE) {
                        fixedThreadPool.execute(() -> {
                            getMsOnionLogger().info(FileResourceController.class.getName(), " ## 大于20M的大文件异步上传 start .... ");
                            UploadFileRequest uploadFileRequest =
                                    new UploadFileRequest(getBucketName(), cosFilePath, bytes);
                            uploadFileRequest.setEnableShaDigest(false);
                            String uploadFileRet = getCOSClient().uploadFile(uploadFileRequest);
                            getMsOnionLogger().info(FileResourceController.class.getName(), " ## 大于20M的大文件异步上传结果: " + uploadFileRet);
                            try {
                                JSONObject ret = MsOnionJsonUtils.jsonToPojo(uploadFileRet, JSONObject.class);
                                // 上传失败需要将 文件上传记录 状态改成 已删除
                                if (0 != ret.getIntValue(ResponseBodyKey.CODE)) {
                                    FileResourceUploadExample example = new FileResourceUploadExample();
                                    FileResourceUploadExample.Criteria criteria = example.createCriteria();
                                    criteria.andPathEqualTo(cosFilePath);
                                    List<FileResourceUpload>  tmplist = fileResourceUploadService.queryByExample(msOnionApiVersion, example);
                                    if (MsOnionCollectionUtils.isNotEmpty(tmplist)) {
                                        fileResourceUploadService.updateStatus(msOnionApiVersion,
                                                tmplist.get(0).getIdx(), MsOnionTableRecordStatus.DELETED.getValue());
                                    }
                                }
                            } catch (MsOnionException e) {
                                this.getMsOnionLogger().error(getClass().getName(), e, "### 大文件上传失败，处理失败结果异常...");
                            }
                        });
                        // 假装上传成功了....
                        jsonObject = new JSONObject();
                        jsonObject.put(ResponseBodyKey.CODE, 0);
                    } else {
                        UploadFileRequest uploadFileRequest =
                                new UploadFileRequest(getBucketName(), cosFilePath, bytes);
                        uploadFileRequest.setEnableShaDigest(false);
                        String uploadFileRet = getCOSClient().uploadFile(uploadFileRequest);
                        getMsOnionLogger().info(FileResourceController.class.getName(), " ## upload file ret: " + uploadFileRet);
                        jsonObject = MsOnionJsonUtils.jsonToPojo(uploadFileRet, JSONObject.class);
                    }

                   // 上传成功
                    if (0 == jsonObject.getIntValue(ResponseBodyKey.CODE)) {
                        fileResourceInfo = new FileResourceInfo();
                        fileResourceInfo.setMessageId(MsOnionNettyUtils.getMessageIdForUpload());
                        fileResourceInfo.setName(fileName);
                        fileResourceInfo.setMd5Value(cosFilePath.substring(cosFilePath
                                .lastIndexOf(MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR) + 1));
                        fileResourceInfo.setTotalSize(file.getSize());
                        fileResourceInfo.setPath(cosFilePath);
                        fileResourceInfo.setRemark(remark);
                        if (MsOnionFilenameUtils.isImage(file.getOriginalFilename())) {
                            fileResourceInfo.setFileType(FileResourceConstants.FILE_PIC);
                        } else {
                            fileResourceInfo.setFileType(FileResourceConstants.FILE_RESOURCE);
                        }
                        fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_SUC);

                        List<FileResourceInfo> fileResourceInfos = Collections.singletonList(fileResourceInfo);
                        MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                            try {
                                FileResourcePersistUtils.persistUpload(msOnionApiVersion, fileResourceInfos,
                                        fileResourceUploadService, getMsOnionLogger(), ablumLibService);
                            } catch (MsOnionException e) {
                                this.getMsOnionLogger().error(getClass().getName(), e, "### 文件上传异步记录上传结果失败...");
                            }
                         });
                    } else {
                        fileResourceInfo = new FileResourceInfo();
                        fileResourceInfo.setName(fileName);
                        fileResourceInfo.setRemark(jsonObject.getString(ResponseBodyKey.MESSAGE));
                        fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_FAIL);
                    }

                    list.add(fileResourceInfo);
                }
            }
            return MsOnionResult.ok(list);
        } catch (MaxUploadSizeExceededException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "### 上传文件失败,文件太大,最大支持 " + ex.getMaxUploadSize() + " B");
        } catch (IOException | MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "### 上传文件失败...");
        }
    }


    /**
     * 单个文件下载
     *
     * @param request  request
     * @param response response
     * @param messageIdStr 文件消息id
     * @throws MsOnionException 异常
     */
    @Deprecated
    @RequestMapping(value = "/cos/download/{messageIdStr}")
    public void downFile(HttpServletRequest request, HttpServletResponse response, @PathVariable String messageIdStr) throws MsOnionException {
        if (!MsOnionRegexUtils.checkDigit(messageIdStr)) {
            throw new MsOnionException("### 文件messageId不能为空");
        }
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);
        FileResourceUpload fileResourceUpload = null;
        try {
            fileResourceUpload = fileResourceUploadService.getFileMessageId(msOnionApiVersion, messageIdStr.trim());
            if (fileResourceUpload == null) {
                throw new MsOnionException("### 文件不存在..." + messageIdStr);
            }
            response = NormalFileUtils.setDownLoadResponse(request, response, fileResourceUpload.getName());
            String cosFilePath = fileResourceUpload.getPath();
            GetFileInputStreamRequest getFileInputStreamRequest = new GetFileInputStreamRequest(getBucketName(), cosFilePath);
            getFileInputStreamRequest.setUseCDN(false);
            try (
                    InputStream input = getCOSClient().getFileInputStream(getFileInputStreamRequest);
                    ServletOutputStream out = response.getOutputStream()
            ) {
                IOUtils.copyLarge(input, out);
            }
        } catch (Exception e) {
            fileResourceUpload = null;
            this.getMsOnionLogger().error(getClass().getName(), e);
            throw new MsOnionException("### 下载失败...");
        } finally {
            if (fileResourceUpload != null) {
                final FileResourceDownload download = new FileResourceDownload();
                download.setMessageId(fileResourceUpload.getMessageId());
                download.setFileIdx(fileResourceUpload.getIdx());
                download.setDownloadStatus(FileResourceConstants.UPLOAD_SUC);
                download.setTotalSize(fileResourceUpload.getTotalSize());
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    try {
                        fileResourceDownloadService.saveFileResourceDownload(msOnionApiVersion, download);
                    } catch (MsOnionException e) {
                        this.getMsOnionLogger().error(getClass().getName(), e, "### 文件资源中心下载单个文件保存下载记录失败...");
                    }
                });
            }
        }
    }

    /**
     * 打包多个下载
     *
     * @param request  request
     * @param response response
     * @throws MsOnionException 异常
     */
    @RequestMapping(value = "/cos/downFileZip")
    public void downFileZip(HttpServletRequest request, HttpServletResponse response) throws MsOnionException {
        String[] messageIds = request.getParameterValues("messageId");
        if (messageIds.length == 0) {
            throw new MsOnionException("### 文件messageId不能为空");
        }
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);
        FileResourceUpload fileResourceUpload = null;

        // 生成的ZIP文件名为年月日时分秒.zip
        String fileName = MsOnionDateUtils.formatYyyyMMddHHmmssUnity(new Date()) + ".zip";
        response = NormalFileUtils.setDownLoadResponse(request, response, fileName);
        try (ServletOutputStream outputStream = response.getOutputStream();
             ZipOutputStream out = new ZipOutputStream(outputStream)) {

            byte[] buffer = new byte[FileResourceConstants.DEFAULT_BUFFER_SIZE];
            for (int i = 0; i < messageIds.length; i++) {
                fileResourceUpload = fileResourceUploadService.getFileMessageId(msOnionApiVersion, messageIds[i].trim());
                if (fileResourceUpload == null) {
                    break;
                }
                String cosFilePath = fileResourceUpload.getPath();

                GetFileInputStreamRequest getFileInputStreamRequest = new GetFileInputStreamRequest(getBucketName(), cosFilePath);
                getFileInputStreamRequest.setUseCDN(false);

                try (InputStream input = getCOSClient().getFileInputStream(getFileInputStreamRequest)) {
                    out.putNextEntry(new ZipEntry(MsOnionDateUtils.formatYyyyMMddHHmmssUnity(new Date()) + "/" + fileResourceUpload.getName()));
                    int len;
                    // 读入需要下载的文件的内容，打包到zip文件
                    while ((len = input.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.closeEntry();
                } catch (Exception e) {
                    this.getMsOnionLogger().error(getClass().getName(), "#### 下载文件失败..." + fileResourceUpload.getMessageId());
                }
                // 保存下载记录
                FileResourceDownload download = new FileResourceDownload();
                download.setMessageId(fileResourceUpload.getMessageId());
                download.setFileIdx(fileResourceUpload.getIdx());
                download.setDownloadStatus(FileResourceConstants.UPLOAD_SUC);
                download.setTotalSize(fileResourceUpload.getTotalSize());
                download.setDownloadSize(fileResourceUpload.getTotalSize());
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    try {
                        fileResourceDownloadService.saveFileResourceDownload(msOnionApiVersion, download);
                    } catch (MsOnionException e) {
                        this.getMsOnionLogger().error(getClass().getName(), e, "### 文件资源中心下载多个文件保存下载记录失败...");
                    }
                });
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            throw new MsOnionException("### 下载失败...");
        }
    }


    /**
     * 商品相册 打包多个下载
     * (前端多个用文件用，隔开 在js加密)
     *
     * @param request  request
     * @param response response
     * @throws MsOnionException 异常
     */
    @RequestMapping(value = "/cos/downAblumZip")
    public void downAblumZip(HttpServletRequest request, HttpServletResponse response) throws MsOnionException {
        // 文件idx
        String idxs = request.getParameter("idxs");
        if (StringUtils.isBlank(idxs)) {
            throw new MsOnionException("### 文件idx不能为空");
        }

        String ablumName = request.getParameter("ablumName");

        idxs = MsOnionSecurityBase64Utils.decode(idxs);
        String[] idxArr = idxs.split(",");

        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);
        AblumLib ablumLib = null;

        // 生成的ZIP文件名为年月日时分秒.zip
        String zipName = MsOnionDateUtils.formatYyyyMMddHHmmssUnity(new Date()) + ".zip";
        if (StringUtils.isNotBlank(ablumName)) {
            zipName = ablumName + "_" + zipName;
        }
        response = NormalFileUtils.setDownLoadResponse(request, response, zipName);
        List<String> cosList = new ArrayList<>();
        try (ServletOutputStream outputStream = response.getOutputStream();
             ZipOutputStream out = new ZipOutputStream(outputStream)) {
            for (int i = 0; i < idxArr.length; i++) {
                String idx = idxArr[i].trim();
                if (!MsOnionRegexUtils.checkDigit(idx)) {
                    continue;
                }

                //
                ablumLib = ablumLibService.queryByPrimaryKey(msOnionApiVersion, Long.valueOf(idx));
                if (ablumLib == null) {
                    continue;
                }
                // 如果不是文件，则需要查询到目录底下的文件，反之不需要遍历
                if (ablumLib.getDirType() == MsOnionConstants.ONE) {
                    cosList.add(ablumLib.getRelativePath());
                    continue;
                }
                long filePix = ablumLib.getIdx();
                short ablumType = ablumLib.getAblumType();
                MsOnionResult result = ablumLibService.getAblumLibListByPidx(msOnionApiVersion, filePix, ablumType);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    continue;
                }
                List<AblumLib> ablumLibList = (List<AblumLib>) result.getData();
                while (MsOnionCollectionUtils.isNotEmpty(ablumLibList)) {
                    List<AblumLib> tempChilds = new ArrayList<>();
                    for (AblumLib obj : ablumLibList) {
                        if (obj.getDirType() == MsOnionConstants.ONE) {
                            cosList.add(obj.getRelativePath());
                            continue;
                        }
                        MsOnionResult result2 = ablumLibService.getAblumLibListByPidx(msOnionApiVersion, obj.getIdx(), obj.getAblumType());
                        if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
                            continue;
                        }
                        List<AblumLib> ablumLibList2 = (List<AblumLib>) result2.getData();
                        if (MsOnionCollectionUtils.isNotEmpty(ablumLibList2)) {
                            for (AblumLib obj2 : ablumLibList2) {
                                tempChilds.add(obj2);
                            }
                        }
                    }
                    ablumLibList = tempChilds;
                }
            }

            byte[] buffer = new byte[FileResourceConstants.DEFAULT_BUFFER_SIZE];
            getMsOnionLogger().info(this.getClass().getName(), " ##本次要下载文件数量:" + cosList.size());
            for (int i = 0; i < cosList.size(); i++) {
                String cospath = cosList.get(i);
                GetFileInputStreamRequest getFileInputStreamRequest = new GetFileInputStreamRequest(getBucketName(), cospath);
                getFileInputStreamRequest.setUseCDN(false);
                try (InputStream input = getCOSClient().getFileInputStream(getFileInputStreamRequest)) {
                    String[] ss = Stream.of(cospath.split(MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR))
                            .skip(MsOnionConstants.TWO).toArray(String[]::new);
                    cospath = StringUtils.join(ss, MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR);
                    if (StringUtils.isNotBlank(ablumName)) {
                        cospath = ablumName + MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR + cospath;
                    }
                    out.putNextEntry(new ZipEntry(cospath));
                    int len = 0;
                    // 读入需要下载的文件的内容，打包到zip文件
                    while ((len = input.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.closeEntry();
                } catch (Exception e) {
                    this.getMsOnionLogger().error(getClass().getName(), "#### 下载相册失败..." + cospath);
                }
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            throw new MsOnionException("### 相册下载失败...");
        }
    }
}
