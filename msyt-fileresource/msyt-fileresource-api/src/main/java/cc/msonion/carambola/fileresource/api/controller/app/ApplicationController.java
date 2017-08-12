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
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.fileresource.api.controller.app;

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseFileResourceAppController;
import cc.msonion.carambola.fileresource.api.ext.FileResourcePersistUtils;
import cc.msonion.carambola.fileresource.pojo.FileResourceInfo;
import cc.msonion.carambola.fileresource.api.netty.upload.utils.FileResourceNettyUtils;
import cc.msonion.carambola.fileresource.api.netty.upload.utils.NormalFileUtils;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.fileresource.interfaces.AblumLibService;
import cc.msonion.carambola.fileresource.interfaces.FileResourceDownloadService;
import cc.msonion.carambola.fileresource.interfaces.FileResourceUploadService;
import cc.msonion.carambola.fileresource.pojo.AblumLib;
import cc.msonion.carambola.fileresource.pojo.FileResourceDownload;
import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.utils.netty.MsOnionNettyUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Title: ApplicationController.java
 * @Package: cc.msonion.carambola.fileresource.api.controller.app
 * @Description: 文件上传
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月24日 上午11:06:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月24日 上午11:06:16
 */

/**
 * @ClassName: ApplicationController
 * @Description: 文件上传 (改用腾讯云cos存储)
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月24日 上午11:06:16
 */
@Deprecated
@Controller
public class ApplicationController extends MsOnionBaseFileResourceAppController {

    /**
     * 上传文件缓冲大小
     */
    @Value("${netty.server.buffer.size}")
    private int bufferSize;

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
     * 上传文件 、下载文件，用来记录操作 线程池（10）
     */
    //private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(MsOnionConstants.POOL_SIZE);
    private static ExecutorService fixedThreadPool = null;


    /**
     * 启动 Netty Server
     */
    /*@PostConstruct
    public void init() {
        try {
            FileResourceNettyUtils.startNettyServer(getMsOnionLogger(), getMsOnionDomain(),
                    getMsOnionNettyServerAdapter(), getMsOnionNettyClientAdapter());
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
        }
        this.getMsOnionLogger().info(getClass().getName(), " ### 开启 Netty Server 成功 ......");
        // 初始化配置文件
        NormalFileUtils.initProperties(getMsOnionDomain());

    }
*/

    /**
     * 测试页面
     *
     * @param req req
     * @param res res
     * @return 页面
     */
   /* @RequestMapping("/test")
    public String test(HttpServletRequest req, HttpServletResponse res) {
        System.out.println(getMsOnionDomain().getFileResourceApi());
        System.out.println(getMsOnionDomain().getEnvironment());
        String path = MsOnionConstants.ALBUM_COLLECT_PATH_START + File.separator + "test";
        req.setAttribute("path", path);
        return "index";
    }
*/



    /**
     * 测试 Netty 上传 Client
     *
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @return MsOnionResultAdapter实例对象
     */
    @Deprecated
    @RequestMapping("/test-upload-c")
    @ResponseBody
    public MsOnionResultAdapter testUploadClient(HttpServletRequest req, HttpServletResponse res) {
        try {
            // 上传文件
            String filename = "D:\\testFile\\旺店通2.0开放接口文档V2_0_5.rar";

            String path = "/test";

            MsOnionNettyResponse msOnionNettyResponse = FileResourceNettyUtils.uploadFile(getMsOnionLogger(), getMsOnionDomain(),
                    getMsOnionNettyClientAdapter(), filename, path);


            return MsOnionResult.ok(msOnionNettyResponse.getReturnValue());

        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "## 上传图片失败......");
        }


    }


    /**
     * 支持多文件上传图片，最多支持2GB (目前还是同步，集成MQ后可以改成异步)
     *
     * @param request  request
     * @param response response
     * @return 结果
     */
    @RequestMapping(value = "/fr/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResultAdapter uploadMultipartFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            String referer = request.getHeader("Referer");
            getMsOnionLogger().info(getClass().getName(), " #### uploadMultipartFile <-- " + referer);

            // 获取页面传递过来文件路径
            String filePath = request.getParameter("filePath");
           /* if (StringUtils.isBlank(filePath)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "### 文件所属路径不能为空");
            }*/
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
                    if (!file.isEmpty()) {
                        // 获取文件总长度
                        long fileSize = file.getSize();
                        String fileName = file.getOriginalFilename();
                        // System.out.println("文件名：" + file.getName() + "," + file.getOriginalFilename());
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

                        if (bufferSize < fileSize) {
                            byte[] bytes = file.getBytes();
                            long num = fileSize % bufferSize == 0
                                    ? fileSize / bufferSize : fileSize / bufferSize + 1;
                            long start = 0L;
                            // 分片上传、它们的uuid必须一样
                            String messageId = MsOnionNettyUtils.getMessageIdForUpload();
                            for (int i = 0; i < num; i++) {
                                start = i * bufferSize;
                                long end = (i + 1) * bufferSize > fileSize ? fileSize : (i + 1) * bufferSize;
                                byte[] bytes1 = Arrays.copyOfRange(bytes, (int) start, (int) end);

                                // 从redis查找是否之前已经上传过，续传
                                String redisValue = fileResourceUploadService.getFileUploadCache(messageId);
                                if (StringUtils.isNotBlank(redisValue)) {
                                    FileResourceInfo fir = MsOnionJsonUtils.jsonToPojo(redisValue, FileResourceInfo.class);
                                    filePath = NormalFileUtils.getUploadRootDir() + fir.getPath();

                                }

                                MsOnionNettyResponse msOnionNettyResponse = null;
                                try {
                                    msOnionNettyResponse = FileResourceNettyUtils.uploadLargeFile(getMsOnionLogger(), getMsOnionDomain(),
                                            getMsOnionNettyClientAdapter(), fileName, filePath, fileSize, start, bytes1);
                                    fileResourceInfo = (FileResourceInfo) msOnionNettyResponse.getReturnValue();
                                    fileResourceInfo.setMessageId(messageId);
                                    fileResourceInfo.setRemark(remark);
                                } catch (MsOnionException e) {
                                    this.getMsOnionLogger().error(getClass().getName(), e);
                                    fileResourceInfo = new FileResourceInfo();
                                    fileResourceInfo.setMessageId(messageId);
                                    fileResourceInfo.setName(fileName);
                                    fileResourceInfo.setTotalSize(fileSize);
                                    fileResourceInfo.setRemark(e.getMessage());
                                    fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_FAIL);
                                    break;
                                } finally {
                                    // 存入redis中
                                    String value = MsOnionJsonUtils.objectToJson(fileResourceInfo);
                                    fileResourceUploadService.setFileUploadCache(messageId, value, FileResourceConstants.ONE_HOUR);
                                }

                            }

                            List<FileResourceInfo> fileResourceInfos = Collections.singletonList(fileResourceInfo);
                            fixedThreadPool.execute(() -> {
                                try {
                                    FileResourcePersistUtils.persistUpload(msOnionApiVersion, fileResourceInfos,
                                            fileResourceUploadService, getMsOnionLogger(), ablumLibService);
                                } catch (MsOnionException e) {
                                    this.getMsOnionLogger().error(getClass().getName(), e, "### 文件上传异步记录上传结果失败...");
                                }
                            });
                        } else {
                            try {
                                fileResourceInfo = NormalFileUtils.uploadMultipartFile(file, filePath);
                                fileResourceInfo.setRemark(remark);
                            } catch (MsOnionException e) {
                                this.getMsOnionLogger().error(getClass().getName(), e);
                                fileResourceInfo = new FileResourceInfo();
                                fileResourceInfo.setName(file.getOriginalFilename());
                                fileResourceInfo.setRemark(e.getMessage());
                                fileResourceInfo.setUploadStatus(FileResourceConstants.UPLOAD_FAIL);
                            } finally {
                                //  上传成功持久化
                                List<FileResourceInfo> fileResourceInfos = Collections.singletonList(fileResourceInfo);
                                fixedThreadPool.execute(() -> {
                                    try {
                                        FileResourcePersistUtils.persistUpload(msOnionApiVersion, fileResourceInfos,
                                                fileResourceUploadService, getMsOnionLogger(), ablumLibService);
                                    } catch (MsOnionException e) {
                                        this.getMsOnionLogger().error(getClass().getName(), e, "### 文件上传异步记录上传结果失败...");
                                    }
                                });

                            }
                        }
                        list.add(fileResourceInfo);
                    }
                }
            }
            return MsOnionResult.ok(list);
        } catch (MaxUploadSizeExceededException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "### 上传文件失败,文件太大,最大支持 " + ex.getMaxUploadSize() + " B");
        } catch (IOException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "### 上传文件失败...");
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "### 上传文件失败...");
        }

    }


    /**
     * 单个文件下载
     *
     * @param request  request
     * @param response response
     * @throws MsOnionException 异常
     */
    @RequestMapping(value = "/fr/downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response) throws MsOnionException {
        String messageId = request.getParameter("messageId");
        if (StringUtils.isBlank(messageId)) {
            throw new MsOnionException("### 文件messageId不能为空");
        }

        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);
        FileResourceUpload fileResourceUpload = null;
        try {
            fileResourceUpload = fileResourceUploadService.getFileMessageId(msOnionApiVersion, messageId.trim());
            if (fileResourceUpload == null) {
                throw new MsOnionException("### 文件不存在..." + messageId);
            }
            String filePath = NormalFileUtils.getUploadRootDir() + fileResourceUpload.getPath();

            File file = new File(filePath);
            if (!file.exists() || !file.canRead() || !file.isFile()) {
                throw new MsOnionException("文件不存在..." + messageId);
               /* response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write("<script type=\"text/javascript\"> alert('文件不存在！') </script>");
                out.close();
                return;*/
            }

            response = NormalFileUtils.setDownLoadResponse(request, response, fileResourceUpload.getName());

            try (
                    FileInputStream is = new FileInputStream(file);
                    ServletOutputStream out = response.getOutputStream()
            ) {
                IOUtils.copyLarge(is, out);
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            throw new MsOnionException("### 下载失败...");
        } finally {
            if (fileResourceUpload != null) {
                final FileResourceDownload download = new FileResourceDownload();
                download.setMessageId(fileResourceUpload.getMessageId());
                download.setFileIdx(fileResourceUpload.getIdx());
                download.setDownloadStatus(FileResourceConstants.UPLOAD_SUC);
                download.setTotalSize(fileResourceUpload.getTotalSize());
                download.setDownloadSize(fileResourceUpload.getTotalSize());
                fixedThreadPool.execute(() -> {
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
    @RequestMapping(value = "/fr/downFileZip")
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
                    //throw new MsOnionException("### 文件不存在..." + messageIds[i]);
                }
                String filePath = NormalFileUtils.getUploadRootDir() + fileResourceUpload.getPath();

                File file = new File(filePath);
                if (!file.exists() || !file.canRead() || !file.isFile()) {
                    break;
                    //throw new MsOnionException("文件不存在..." + messageIds[i]);
                }
                try (FileInputStream fis = new FileInputStream(file)) {
                    out.putNextEntry(new ZipEntry(fileResourceUpload.getPath()));
                    int len;
                    // 读入需要下载的文件的内容，打包到zip文件
                    while ((len = fis.read(buffer)) > 0) {
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
                fixedThreadPool.execute(() -> {
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
    @RequestMapping(value = "/fr/downAblumZip")
    public void downAblumZip(HttpServletRequest request, HttpServletResponse response) throws MsOnionException {
        // 文件idx
        String idxs = request.getParameter("idxs");
        if (StringUtils.isBlank(idxs)) {
            throw new MsOnionException("### 文件idx不能为空");
        }

        idxs = MsOnionSecurityBase64Utils.decode(idxs);
        String[] idxArr = idxs.split(",");

        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);
        AblumLib ablumLib = null;

        // 生成的ZIP文件名为年月日时分秒.zip
        String zipName = MsOnionDateUtils.formatYyyyMMddHHmmssUnity(new Date()) + ".zip";
        response = NormalFileUtils.setDownLoadResponse(request, response, zipName);
        try (ServletOutputStream outputStream = response.getOutputStream();
             ZipOutputStream out = new ZipOutputStream(outputStream)) {

//            byte[] buffer = new byte[FileResourceConstants.DEFAULT_BUFFER_SIZE];
            for (int i = 0; i < idxArr.length; i++) {
                String idx = idxArr[i].trim();
                if (!MsOnionRegexUtils.checkDigit(idx)) {
                    continue;
                }

                ablumLib = ablumLibService.queryByPrimaryKey(msOnionApiVersion, Long.valueOf(idx));
                if (ablumLib == null) {
                    continue;
                }

                String name = ablumLib.getName();
                // 文件全路径
                String filePath = NormalFileUtils.getUploadRootDir() + ablumLib.getRelativePath();

                // 遍历文件夹中有多少文件
                Path path = Paths.get(filePath);
                Path walkFileTree = Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                        File file = path.toFile();
                        if (file.exists() && file.canRead() && file.isFile()) {
                            try (FileInputStream fis = new FileInputStream(file)) {
                                String zipPath = MsOnionFileUtils.transitionPath(path.toString().substring(path.toString().indexOf(name)));
                                out.putNextEntry(new ZipEntry(zipPath));
                                // 读入需要下载的文件的内容，打包到zip文件 (NIO)
                                FileChannel channel = fis.getChannel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(FileResourceConstants.DEFAULT_BUFFER_SIZE);
                                while (true) {
                                    // clear方法重设缓冲区，使它可以接受读入的数据
                                    byteBuffer.clear();
                                    // 从输入通道中将数据读到缓冲区
                                    int r = channel.read(byteBuffer);
                                    if (r < 0) {
                                        break;
                                    }
                                    // flip方法让缓冲区可以将新读入的数据写入另一个通道
                                    byteBuffer.flip();
                                    out.write(byteBuffer.array());
                                    out.flush();
                                }

                               /* int len;
                                while ((len = fis.read(buffer)) > 0) {
                                    out.write(buffer, 0, len);
                                }*/
                                out.closeEntry();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            throw new MsOnionException("### 相册下载失败...");
        }
    }


    /**
     * 单个文件删除
     *
     * @param request  request
     * @param response response
     * @throws MsOnionException 异常
     * @return  MsOnionResult
     */
    @RequestMapping(value = "/fr/delFile")
    @ResponseBody
    public MsOnionResult delFile(HttpServletRequest request, HttpServletResponse response) throws MsOnionException {
        String messageId = request.getParameter("messageId");
        if (StringUtils.isBlank(messageId)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法, messageId=" + messageId);
        }
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(FileResourceConstants.API_VERSION);
        FileResourceUpload fileResourceUpload = null;
        try {
            fileResourceUpload = fileResourceUploadService.getFileMessageId(msOnionApiVersion, messageId.trim());
            if (fileResourceUpload == null) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "文件不存在, messageId=" + messageId);
            }
            String filePath = NormalFileUtils.getUploadRootDir() + fileResourceUpload.getPath();

            File file = new File(filePath);
            if (!file.exists() || !file.canRead() || !file.isFile()) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "文件无法删除, messageId=" + messageId);
            }
            file.delete();
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "文件删除失败, messageId=" + messageId);
        } finally {
            if (fileResourceUpload != null) {
                fileResourceUpload.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
                fileResourceUploadService.updateByPrimaryKey(msOnionApiVersion, fileResourceUpload);
                // 如果是相册中图片也需要删除
                MsOnionResult result = ablumLibService.getAblumLibByFileIdx(msOnionApiVersion, fileResourceUpload.getIdx());
                if (result.getStatus() == MsOnionStatusConstants.STATUS_200) {
                    AblumLib ablumLib = (AblumLib) result.getData();
                    ablumLibService.deleteByPrimaryKey(msOnionApiVersion, ablumLib.getIdx());
                }
            }
        }
        return MsOnionResult.ok();
    }

}
