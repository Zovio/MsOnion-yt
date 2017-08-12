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

package cc.msonion.carambola.manager.web.controller.app;

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.commons.web.common.utils.MsOnionVerifyCodeUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.content.pojo.Content;
import cc.msonion.carambola.content.service.ContentService;
import cc.msonion.carambola.fileresource.pojo.FileResourceInfo;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionSysSetCodeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.pojo.SysVerifyCodePicture;
import cc.msonion.carambola.system.service.SysSettingService;
import cc.msonion.carambola.system.service.SysVerifyCodePictureService;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ApplicationController.java
 * @Package: cc.msonion.carambola.manager.web.controller.app
 * @Description: ApplicationController
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月12日 下午9:58:09
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月12日 下午9:58:09
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增、删、改、查方法
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月22日 14:48:18
 * @Modify-version: V2.0.0
 * @Modify-description: 添加内容展示：商城链接 和 版权信息
 */

/**
 * @ClassName: ApplicationController
 * @Description: ApplicationController
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月12日 下午9:58:09
 */
@Controller
public class ApplicationController extends MsOnionBaseAppController {

    /**
     * contentService
     */
    @Autowired
    private ContentService contentService;

    /**
     * memberService
     */
    @Autowired
    private MemberService memberService;

    /**
     * dynamicCacheService
     */
    @Autowired
    private DynamicCacheService dynamicCacheService;

    /**
     * sysSettingService
     */
    @Autowired
    private SysSettingService sysSettingService;


    /**
     * 验证码图片service
     */
    @Autowired
    private SysVerifyCodePictureService sysVerifyCodePictureService;


    /**
     * 进入首页
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 首页
     */
    @RequestMapping("/")
    public String index(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            this.getMsOnionLogger().info(getClass().getName(), "index , Member的 index  , /** 拦截，所有其他匹配不了的请求， ");
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 洋葱商城链接
            MsOnionResult ycIndexResult = contentService.getContentByCode(apiVersion, ManagerConstants.YC_INDEX_LINK);
            // 洋葱官网链接
            MsOnionResult ycWWWResult = contentService.getContentByCode(apiVersion, ManagerConstants.YC_WWW_LINK);
            // 洋桃商城链接
            MsOnionResult indexResult = contentService.getContentByCode(apiVersion, ManagerConstants.SHOP_INDEX_LINK);
            // 版权信息
            MsOnionResult copyrightResult = contentService.getContentByCode(apiVersion, ManagerConstants.MANAGE_COPYRIGHT);
            // 主页内容
            MsOnionResult systemIndexResult = contentService.getContentByCode(apiVersion, ManagerConstants.YT_SYSTEM_INDEX_CONTENT);

            if (systemIndexResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                Content content = (Content) systemIndexResult.getData();
                String contentHtml = StringEscapeUtils.unescapeHtml4(content.getContent());
                model.addAttribute("systemIndexContent", contentHtml);
            }
            if (ycWWWResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                Content content = (Content) ycWWWResult.getData();
                String contentHtml = StringEscapeUtils.unescapeHtml4(content.getContent());
                model.addAttribute("ycWWWResultContent", contentHtml);
            }
            if (indexResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                Content content = (Content) indexResult.getData();
                String contentHtml = StringEscapeUtils.unescapeHtml4(content.getContent());
                model.addAttribute("indexResultContent", contentHtml);
            }
            if (ycIndexResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                Content content = (Content) ycIndexResult.getData();
                String contentHtml = StringEscapeUtils.unescapeHtml4(content.getContent());
                model.addAttribute("ycIndexResultContent", contentHtml);
            }
            model.addAttribute("systemIndexResult", systemIndexResult);
            model.addAttribute("ycWWWResult", ycWWWResult);
            model.addAttribute("ycIndexResult", ycIndexResult);
            model.addAttribute("indexResult", indexResult);
            model.addAttribute("copyrightResult", copyrightResult);
            String frDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_DOMAIN);
            String frImgurl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);

            Session session = SecurityUtils.getSubject().getSession(false);
            session.setAttribute("fileResourceApi", frDomin);
            session.setAttribute("picimgurl", frImgurl);

            return "/member/member/index";
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return getError();
        }
    }

    /**
     * 主页展示
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 主页
     */
    @RequestMapping("/memberMain")
    public String memberIndex(HttpServletRequest req, HttpServletResponse res, Model model) {

        return "/member/index/memberMain";
    }

    /**
     * 拦截所有匹配不到的路径，"/**" 拦截所有请求 , 包括 JS ， CSS，所有的请求，都会执行这里 ！！！
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 返回路径
     */
    @RequestMapping("/**")
    public String showAll(HttpServletRequest req, HttpServletResponse res, Model model) {

        /*this.getMsOnionLogger().info(getClass().getName(), "showAll #  所有非法请求 ， 域名信息，this.domain=" + this.getMsOnionDomain());

        try {
            //  # 获取请求IP地址=172.16.20.203
            this.getMsOnionLogger().info(getClass().getName(), "showAll #  所有非法请求 ， # 获取请求IP地址=" + IPUtils.getIp(req));

        } catch (Exception ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex);
        }*/
        return "/error/404";
    }

    /**
     * 显示验证码
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     */
    @RequestMapping("/validatecode")
    public void showValidateCode(HttpServletRequest req, HttpServletResponse res, Model model) {

        this.getMsOnionLogger().debug(getClass().getName(), "showValidateCode # 显示验证码");

        try {
            // 生成随机字符串
            String encodeKeyValue = MsOnionUUIDUtils.randomUUID();

            // 写入Key到cookie
            final int maxAge = 180;
            MsOnionCookieUtils.setCookie(req, res, ManagerConstants.VERIFYCODE_COOKIE_NAME,
                    encodeKeyValue, maxAge);

            // 生成验证码并返回值
            MsOnionResult msOnionResult = genVerifyCodePicture(req, res);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                this.getMsOnionLogger().error(this.getClass().getName(), "######生成的验证码异常");
                return;
            }
            String showVerifyCode = (String) msOnionResult.getData();

            // 把key和value存入到redis
            dynamicCacheService.setForRedisWithSecurity(memberService.getUserVefiyCache(encodeKeyValue),
                    showVerifyCode, ManagerConstants.VERIFYCODE_EXPIRE_TIME);

            this.getMsOnionLogger().debug(getClass().getName(), "showValidateCode # 生成的验证码 33, showVerifyCode=" + showVerifyCode);

        } catch (Exception e) {

            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }
    }

    /**
     * 生成验证码
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @return MsOnionResult
     * @throws Exception Exception
     */
    private MsOnionResult genVerifyCodePicture(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 版本对象
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        int verifyCodePictureNum = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER));
        // 查询图片集合
        MsOnionResult msOnionResult = sysVerifyCodePictureService.
                queryRandomVerifyCodePicture(msOnionApiVersion, verifyCodePictureNum);
        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return msOnionResult;
        }
        List<SysVerifyCodePicture> sysVerifyCodePictureList = (List<SysVerifyCodePicture>) msOnionResult.getData();
        List<BufferedImage> fileList = new ArrayList<BufferedImage>();
        String frImgurl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);
        for (SysVerifyCodePicture sysVerifyCodePicture : sysVerifyCodePictureList) {
            String urlString = frImgurl + sysVerifyCodePicture.getImagePath();
            URL url = new URL(urlString);
            BufferedImage bufferedImage = ImageIO.read(url);
            fileList.add(bufferedImage);
        }
        // 图片的尺寸
        int verifyCodePictureSize = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE));
        // 图片的尺寸
        int ring = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING));
        // 生成验证码图片并返回值
        String codeValue = MsOnionVerifyCodeUtils.generateVerifyCodePicture(req, res, verifyCodePictureSize, ring, fileList);

        return msOnionResult.ok(codeValue);
    }


    /**
     * 文件上传根目录
     */
    @Value("${ueditor.tmp.dir}")
    private String ueditorTmpDir;
    /**
     * ueditor配置
     * @param req 请求
     * @param res 响应
     * @param model model
     * @param file file 文件
     * @param action 操作action 名称
     * @param filePath 文件存放的目录
     * @return String 操作结果
     * @Description  编写这个Ueditor核心处理器，是因为springmvc 中全局拦截，付文本编辑器不能直接访问默认的jsp页面,
     * 上传还是用springMVC的上传，因为spring已经拦截request 文件流，百度自带源码request获取不到
     */
    @RequestMapping("/ueditConfig")
    @ResponseBody
    public String importNewItemData(HttpServletRequest req, HttpServletResponse res, Model model,
            @RequestParam(value = "upfile", required = false) CommonsMultipartFile file, String action, String filePath) {

        try {
//            req.setCharacterEncoding("utf-8");
//            res.setHeader("Content-Type", "text/html");
            if (MsOnionStringUtils.isEmpty(action)) {
                return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
            }
            int actionCode = ActionMap.getType(action);
            if (actionCode == ActionMap.UPLOAD_IMAGE) {
                // 上传图片
                this.getMsOnionLogger().info(this.getClass().getName(),
                        " ####### ueditor上传图片 ######## ");
                // 临时文件
                File destFile = null;
                try {
                    if (file == null) {
                        return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA).toJSONString();
                    }
                    if (MsOnionStringUtils.isEmpty(filePath)) {
                        return new BaseState(false, MessageConstants.UEDITOR_UP_PATH_ERROR).toJSONString();
                    }
                    if (!MsOnionFilenameUtils.isImage(file.getOriginalFilename())) {
                        return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA).toJSONString();
                    }
                    if (file.getSize() > MsOnionConstants.FILE_PICTURE_MAX_SIZE) {
                        return new BaseState(false, AppInfo.MAX_SIZE).toJSONString();
                    }

                    // 上传文件目录相对路劲
                    String tmpfilePath = MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR
                            + MsOnionDateUtils.toYyyyMMddUnity(System.currentTimeMillis());
                    // 上传文件的根目录绝对路径
                    File rootFile = MsOnionFileUtils.getFileResourceUploadRootDir(ueditorTmpDir);
                    String absolutePath = rootFile.getAbsolutePath();
                    // 拼接全路径
                    File curFile = new File(absolutePath + tmpfilePath);
                    // 检查目录是否存在
                    MsOnionFileUtils.forceMkdir(curFile);
                    // 生成新文件
                    String destFileName = MsOnionFilenameUtils.generateFilename(file.getOriginalFilename());
                    destFile = new File(curFile, destFileName);
                    // 执行上传操作
                    file.transferTo(destFile);
                    // API地址
                    String frDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_DOMAIN);
                    final String url = frDomin + "/cos/uploadFiles";
                    Map map = new HashMap();
                    map.put("filePath", filePath);

                    JSONObject jsonObject = MsOnionOkHttp3Utils.postFiles(url, map, destFile);
                    if (jsonObject == null) {
                        return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR).toJSONString();
                    }
                    MsOnionResult msOnionResult = jsonObject.toJavaObject(MsOnionResult.class);
                    if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR).toJSONString();
                    }
                    List<FileResourceInfo> fileResourceInfoList =
                            JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("data")), FileResourceInfo.class);
                    if (MsOnionCollectionUtils.isEmpty(fileResourceInfoList)) {
                        return new BaseState(false, AppInfo.IO_ERROR).toJSONString();
                    }
                    FileResourceInfo fileResourceInfo = fileResourceInfoList.get(0);
                    if (fileResourceInfo.getUploadStatus() == 0) {
                        return new BaseState(false, fileResourceInfo.getRemark()).toJSONString();
                    }
                    State state = new BaseState(true);
                    state.putInfo("size", file.getSize());
                    state.putInfo("title", file.getOriginalFilename());
                    // 上传成功返回
                    String frImgurl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);
                    state.putInfo("url", frImgurl + fileResourceInfo.getPath());
                    state.putInfo("type", FileType.getSuffixByFilename(fileResourceInfo.getMd5Value()));
                    state.putInfo("original", fileResourceInfo.getMd5Value());
                    // 上传成功返回
                    return state.toJSONString();
                } catch (MsOnionException e) {
                    return new BaseState(false, AppInfo.IO_ERROR).toJSONString();
                } finally {
                    if (destFile != null) {
                        destFile.delete();
                    }
                }
            } else if (actionCode == ActionMap.UPLOAD_VIDEO) {
                // 上传视频
                this.getMsOnionLogger().info(this.getClass().getName(),
                        " ####### ueditor上传视频 ######## ");
                // TODO: 2017/7/1
            } else if (actionCode == ActionMap.CONFIG) {
                String rootPath = req.getServletContext().getRealPath("/");
                return new ActionEnter(req, rootPath).exec();
            }
            return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
        } catch (Exception e) {
            return new BaseState(false, AppInfo.IO_ERROR).toJSONString();
        }
    }

}
