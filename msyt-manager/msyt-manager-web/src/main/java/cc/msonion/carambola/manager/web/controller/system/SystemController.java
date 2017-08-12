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


package cc.msonion.carambola.manager.web.controller.system;

/**
 * @Title: SystermContoller.java
 * @Package: cc.msonion.carambola.manager.web.controller.system
 * @Description: 系统不需要拦截url请求
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月15日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月15日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.common.utils.MsOnionVerifyCodeUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.controller.ext.StaticResourcesVersionUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.common.constants.MemberConstants;
import cc.msonion.carambola.member.common.constants.ParamTypeConstants;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.MemberExample;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionDynamicRedisKeyConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionSysSetCodeConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionSendEmailUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.service.SysMemberLoginLogService;
import cc.msonion.carambola.system.service.SysSettingService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 系统一些公共的url请求，后续好做权限控制
 *
 * @ClassName: SystermContoller
 * @Description: 系统不需要拦截url请求
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月15日
 *
 */
@Controller
public class SystemController extends MsOnionBaseAppController {

    /**
     * MemberService
     */
    @Autowired
    private MemberService memberService;

    /**
     * sysDataDictionaryService
     */
    @Autowired
    private SysMemberLoginLogService sysMemberLoginLogService;

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
     * Super名称
     */
    @Value("${shiro.member.super}")
    private String superName;

    /**
     * 初始化 js、css、img版本号存入redis
     */
    @PostConstruct
    public void init() {
        try {
            dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_JS_VERSION_KEY,
                    getMsOnionDomain().getJsVersion());
            dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_CSS_VERSION_KEY,
                    getMsOnionDomain().getCssVersion());
            dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.REDIS_IMG_VERSION_KEY,
                    getMsOnionDomain().getImgVersion());
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ### 系统启动初始化js、css、img版本号存入redis中失败...");
        }
        this.getMsOnionLogger().info(getClass().getName(), " ### 系统启动初始化js、css、img版本号存入redis中成功...");
    }


    /**
     * 显示登录
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 返回路径
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月28日 下午7:00:27
     */
    @RequestMapping("/sys/to-login")
    public String showLogin(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            StaticResourcesVersionUtils.initStaticVersionToSession(dynamicCacheService, req, res);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ###系统启动初始化js、css、img版本号存入redis中失败...");
            return getError();
        }
        // 验证码域名
        String verifyCodeDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.VERIFY_CODE_DOMAIN);
        ServletContext application = req.getServletContext();
        application.setAttribute("verifyCodeDomin", verifyCodeDomin);

        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Session session = subject.getSession(false);
            if (session != null && session.getAttribute(ManagerConstants.SESSION_MEMBER) != null) {
                return "redirect:/";  // /member/index
            }
        }
        try {
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            String verifyCodePictureSize = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE);
            model.addAttribute("verifyCodePictureSize", verifyCodePictureSize);
            String verifyCodePictureNum = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER);
            model.addAttribute("verifyCodePictureNum", verifyCodePictureNum);
            String verifyCodePictureRing = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING);
            model.addAttribute("verifyCodePictureRing", verifyCodePictureRing);
            return "/member/member/login";
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ###加载验证码失败...");
            return getError();
        }
    }

    /**
     * 进行登录
     *
     * @param req      HttpServletRequest实例对象
     * @param res      HttpServletResponse实例对象
     * @param model    Model实例对象
     * @param username 用户名
     * @param password 密码（明文，用户传递参数）
     * @param vcode    验证码
     * @param accessCode  验证码访问码
     * @throws MsOnionException 异常
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/sys/do-login", method = RequestMethod.POST)  // POST 请求
    @ResponseBody
    public MsOnionResult doLogin(HttpServletRequest req, HttpServletResponse res, Model model, String username,
                                 String password, String vcode, String accessCode) throws MsOnionException {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        username = MsOnionSecurityBase64Utils.decode(username);
        try {
            msOnionApiVersion.setRequestApiVersion(MemberConstants.API_VERSION);
            // Base64解密
            password = MsOnionSecurityBase64Utils.decode(password);
            vcode = MsOnionSecurityBase64Utils.decode(vcode);
            this.getMsOnionLogger().info(getClass().getName(), "进行登录， username=" + username + ", 验证码=" + vcode);
//            String encodeKeyValue = MsOnionCookieUtils.getCookieValue(req, ManagerConstants.VERIFYCODE_COOKIE_NAME);
            // 验证码 ## Begin
            String superKey = MsOnionDynamicRedisKeyConstants.REDIS_VERIFY_CODE_UNSECURITY_SUPER;
            String unsecuritySuper = dynamicCacheService.getFromRedisWithSecurity(superKey);
            // 如果是所有成员都有绿色通道的，Redis的Key是动态的
            String allKey = MsOnionDynamicRedisKeyConstants.REDIS_VERIFY_CODE_UNSECURITY_ALL;
            allKey = String.format("%s:%s", allKey, MsOnionSecurityUtils.md5ForRedisKey(username));
            String unsecurityAll = dynamicCacheService.getFromRedisWithSecurity(allKey);
            // 还没有Redis的数据：1、过期了， 2、还没有
            // 所有的成员的绿色通道，某一个时间内只能有一次，不需要认证码
            // 禁用验证码，同时还没有使用免验证码登录 ， 或者超过验证周期（Redis）
            if (!MsOnionZookeeperUtils.getEnableVerifyCode(getMsOnionDomain(),
                    getMsOnionCuratorZookeeperClient(), getMsOnionRetryNTimes()) && MsOnionStringUtils.isBlank(unsecurityAll)) {
                // 所有的绿色通道，也就是不需要关闭验证码
                int allExpire = MsOnionZookeeperUtils.getVerifyCodeAutoExpire(getMsOnionDomain(), getMsOnionCuratorZookeeperClient(),
                        getMsOnionRetryNTimes());
                // 添加到Redis中
                dynamicCacheService.setForRedisWithSecurity(allKey,
                        MsOnionDynamicRedisKeyConstants.REDIS_VERIFY_CODE_ALL, allExpire);
                // 打印日志
                getMsOnionLogger().debug(getClass().getName(),
                        String.format("所有成员绿色通道，allExpire=%s，unsecurityAll=%s", allExpire, unsecurityAll));
            } else if (MsOnionStringUtils.isBlank(unsecuritySuper) && MsOnionStringUtils.isNotBlank(superName)
                    && superName.equalsIgnoreCase(username)) {
                // super 绿色通道，1、过期了，  2、还没有 ，不需要验证码，直接登录。
                // 添加到Redis缓存中
                int superExpire = MsOnionDynamicRedisKeyConstants.REDIS_VERIFY_CODE_UNSECURITY_SUPER_EXPIRE;
                dynamicCacheService.setForRedisWithSecurity(superKey,
                        MsOnionDynamicRedisKeyConstants.REDIS_VERIFY_CODE_SUPER, superExpire);
                // 打印日志
                getMsOnionLogger().debug(getClass().getName(),
                        String.format("super绿色通道，superExpire=%s，unsecuritySuper=%s", superExpire, unsecuritySuper));
            } else {
                String verifyCode = null;
                if (MsOnionStringUtils.isNotEmpty(accessCode)) {
                    // 判断如果有验证码对应的cookies 的key，比对解密后的字符串和redis
                    accessCode = MsOnionSecurityBase64Utils.decode(accessCode);
                    verifyCode = dynamicCacheService.getFromRedisWithSecurity(memberService.getUserVefiyCache(accessCode));
                } else {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_VERIFICATION_CODE_ERROR);
                }
                this.getMsOnionLogger().info(getClass().getName(), "进行登录， ==》Session中验证码，verifyCodeFromSession=" + verifyCode);
                // 图片旋转次数
                int verifyCodePictureRing = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                        MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING));
                // 图片数量
                int verifyCodePictureNum = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                        MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER));
                if (MsOnionStringUtils.isEmpty(vcode) || !MsOnionVerifyCodeUtils.checkCode(verifyCode, vcode.trim(),
                        verifyCodePictureNum, verifyCodePictureRing)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_VERIFICATION_CODE_ERROR);
                }
            }
            // 验证码 ## End
//            memberService.delUserVefiyCache(req.getSession().getId());
            MsOnionResult msOnionResult = this.memberService.login(msOnionApiVersion, username, password);
            this.getMsOnionLogger().info(getClass().getName(), "进行登录， 验证码，msOnionResult.getMsg()=" + msOnionResult.getMsg());

            String rememberme = req.getParameter("rememberme");
            rememberme = MsOnionSecurityBase64Utils.decode(rememberme);

            // 登录成功，把用户信息写入到Cookie中

            if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username,
                        MsOnionSecurityUtils.md5ForPassword(password));
                if (MsOnionConstants.YES.equals(rememberme)) {
                    token.setRememberMe(true);
                }
                subject.login(token);

                Session session = subject.getSession(false);
                Member m = (Member) msOnionResult.getData();
                m.setPassword(MsOnionSecurityUtils.md5ForPassword(password));
                session.setAttribute(ManagerConstants.SESSION_MEMBER, m);
            }
            // 记录登录日志
            CurrentUserUtils.saveLoginLog(getMsOnionLogger(), memberService, sysMemberLoginLogService, req, username);
            return msOnionResult;
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            // 记录登录日志
            try {
                CurrentUserUtils.saveLoginLog(getMsOnionLogger(), memberService, sysMemberLoginLogService, req, username);
            } catch (Exception e1) {
                this.getMsOnionLogger().error(getClass().getName(), e1);
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

    /**
     * 显示注册
     * @param req 请求对象
     * @param res 响应对象
     * @param model model对象
     * @return 显示注册页面
     */
    @RequestMapping(value = "/sys/to-register")
    public String showRegister(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            StaticResourcesVersionUtils.initStaticVersionToSession(dynamicCacheService, req, res);
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            String verifyCodePictureSize = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE);
            model.addAttribute("verifyCodePictureSize", verifyCodePictureSize);
            String verifyCodePictureNum = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER);
            model.addAttribute("verifyCodePictureNum", verifyCodePictureNum);
            String verifyCodePictureRing = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING);
            model.addAttribute("verifyCodePictureRing", verifyCodePictureRing);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ###系统启动初始化js、css、img版本号存入redis中失败...");
            return getError();
        }
        this.getMsOnionLogger().info(getClass().getName(), "showRegister # 显示注册  ...");
        return "/member/member/register";
    }

    /**
     * 注册
     *
     * @param req    HttpServletRequest实例对象
     * @param res    HttpServletResponse实例对象
     * @param member 成员POJO实例
     * @param vcode  验证码
     * @param accessCode  验证码访问码
     * @return 返回MsOnionResult
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月28日 下午7:00:27
     */
    @RequestMapping(value = "/sys/do-register", method = RequestMethod.POST)  // POST 请求
    @ResponseBody
    public MsOnionResult doRegister(HttpServletRequest req, HttpServletResponse res, Member member, String vcode, String accessCode) {
        try {
            // 处理前端js加密
            if (member != null) {
                member.setName(MsOnionSecurityBase64Utils.decode(member.getName()));
                member.setFullName(MsOnionSecurityBase64Utils.decode(member.getFullName()));
                member.setPassword(MsOnionSecurityBase64Utils.decode(member.getPassword()));
                member.setPhone(MsOnionSecurityBase64Utils.decode(member.getPhone()));
                member.setTel(MsOnionSecurityBase64Utils.decode(member.getTel()));
                member.setEmail(MsOnionSecurityBase64Utils.decode(member.getEmail()));
                member.setRemark(MsOnionSecurityBase64Utils.decode(member.getRemark()));
            }

            this.getMsOnionLogger().info(getClass().getName(), "## 注册  # member=" + member + "，vcode=" + vcode);
            // 验证码 ## Begin
//            String encodeKeyValue = MsOnionCookieUtils.getCookieValue(req, ManagerConstants.VERIFYCODE_COOKIE_NAME);
            String verifyCode = null;
            if (MsOnionStringUtils.isNotEmpty(accessCode)) {
                // 判断如果有验证码对应的cookies 的key，比对解密后的字符串和redis
                accessCode = MsOnionSecurityBase64Utils.decode(accessCode);
                verifyCode = dynamicCacheService.getFromRedisWithSecurity(memberService.getUserVefiyCache(accessCode));
            } else {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_VERIFICATION_CODE_ERROR);
            }
            this.getMsOnionLogger().info(getClass().getName(), "## 注册  # 存储在Session的验证码， verifyCode=" + verifyCode);

            vcode = MsOnionSecurityBase64Utils.decode(vcode);
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 图片旋转次数
            int verifyCodePictureRing = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING));
            // 图片数量
            int verifyCodePictureNum = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER));
            if (MsOnionStringUtils.isEmpty(vcode) || !MsOnionVerifyCodeUtils.checkCode(verifyCode, vcode.trim(),
                    verifyCodePictureNum, verifyCodePictureRing)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_VERIFICATION_CODE_ERROR);
            }
//            memberService.delUserVefiyCache(req.getSession().getId());
            // 验证码 ## End
            MsOnionResult msOnionResult = memberService.register(msOnionApiVersion, member);
            return msOnionResult;
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　注册失败");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, e.getMessage());
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　注册失败");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_MEMBER_REGISTER_FAILURE);
        }
    }


    /**
     * 更新密码
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @param idxStr   成员主键
     * @return 返回路径
     */
    @RequestMapping(value = "/sys/updatePassword/{idxStr}")
    public String updatePassword(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {

        if (!MsOnionRegexUtils.checkDigit(idxStr)) {
            this.getMsOnionLogger().error(getClass().getName(), "##　请求参数非法, idxStr=" + idxStr);
            return getError();
        }

        req.setAttribute("idx", idxStr);
        return "/member/member/updatePassword";
    }


    /**
     * 基于Email，修改密码
     *
     * @param req         HttpServletRequest实例对象
     * @param res         HttpServletResponse实例对象
     * @param model       Model实例对象
     * @param idx       成员主键idx
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/sys/update/do-password", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doUpdatePassword(HttpServletRequest req, HttpServletResponse res, Model model,
                                          Long idx, String oldPassword, String newPassword) {
        try {
            if (idx == null || idx <= 0L) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ",idx=" + idx);
            }

            Long idx1 = CurrentUserUtils.getCurrentUser().getIdx();
            if (idx1.longValue() != idx.longValue()) {
                this.getMsOnionLogger().error(getClass().getName(), "## 禁止越权修改密码 [" + idx1 + "," + idx);
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "您无权修改别人的密码！");
            }

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            oldPassword = MsOnionSecurityBase64Utils.decode(oldPassword);
            newPassword = MsOnionSecurityBase64Utils.decode(newPassword);

            return memberService.updatePassword(msOnionApiVersion, idx, oldPassword, newPassword);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);

        }
    }


    /**
     * 退出系统
     *
     * @param request 请求
     * @param response 响应
     * @return
     */
    @RequestMapping("/sys/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try {
                subject.logout();
            } catch (Exception ex) {
                this.getMsOnionLogger().error(getClass().getName(), ex);
            }
        }
        try {
            response.sendRedirect(request.getContextPath() + "/member/to-login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param request 请求
     * @param response 响应
     * @return 页面
     */
    @RequestMapping("/sys/unauthorized")
    public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
        return "/error/unauthorized";
    }


    /**
     * 展示忘记密码页面
     * @param req 请求对象
     * @param res 响应对象
     * @param model model对象
     * @return 忘记密码
     */
    @RequestMapping(value = "/sys/to-forget")
    public String showForget(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            StaticResourcesVersionUtils.initStaticVersionToSession(dynamicCacheService, req, res);
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            String verifyCodePictureSize = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE);
            model.addAttribute("verifyCodePictureSize", verifyCodePictureSize);
            String verifyCodePictureNum = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER);
            model.addAttribute("verifyCodePictureNum", verifyCodePictureNum);
            String verifyCodePictureRing = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING);
            model.addAttribute("verifyCodePictureRing", verifyCodePictureRing);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ###系统启动初始化js、css、img版本号存入redis中失败...");
            return getError();
        }

        return "/member/member/forget";
    }


    /**
     * 忘记密码申请操作
     * @param req 请求对象
     * @param res 响应对象
     * @param accessCode  验证码访问码
     * @return 忘记密码
     */
    @RequestMapping(value = "/sys/apply-forget", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult applyForget(HttpServletRequest req, HttpServletResponse res, String accessCode) {
        try {
            String email = req.getParameter("email");
            String vCode = req.getParameter("vCode");
            String passWord = req.getParameter("password");

            if (StringUtils.isBlank(email) || StringUtils.isBlank(vCode) || StringUtils.isBlank(passWord)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }

//            String encodeKeyValue = MsOnionCookieUtils.getCookieValue(req, ManagerConstants.VERIFYCODE_COOKIE_NAME);
            // 验证码 ## Begin
            String verifyCode = null;
            if (MsOnionStringUtils.isNotEmpty(accessCode)) {
                // 判断如果有验证码对应的cookies 的key，比对解密后的字符串和redis
                accessCode = MsOnionSecurityBase64Utils.decode(accessCode);
                verifyCode = dynamicCacheService.getFromRedisWithSecurity(memberService.getUserVefiyCache(accessCode));
            } else {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_VERIFICATION_CODE_ERROR);
            }

            vCode = MsOnionSecurityBase64Utils.decode(vCode);
            if (verifyCode == null || !verifyCode.equalsIgnoreCase(vCode.trim())) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_VERIFICATION_CODE_ERROR);
            }
            // 验证码 ## End

            //memberService.delUserVefiyCache(req.getSession().getId());
            email = MsOnionSecurityBase64Utils.decode(email);
            passWord = MsOnionSecurityBase64Utils.decode(passWord);

            // 邮箱正则表达式
            boolean checkEmail = MsOnionRegexUtils.isEmail(email);
            if (!checkEmail) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        cc.msonion.carambola.member.common.constants.MessageConstants.MESSAGE_MEMBER_EMAIL_FORMAT_ILLEGAL);
            }

            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 要判断是否存在此邮箱
            MemberExample example = new MemberExample();
            MemberExample.Criteria criteria = example.createCriteria();
            List<Short> statuses = new ArrayList<>();
            statuses.add(MsOnionTableRecordStatus.NORMAL.getValue());
            statuses.add(MsOnionTableRecordStatus.DISABLE.getValue());
            criteria.andStatusIn(statuses);
            criteria.andEmailEqualTo(email.trim());
            // 查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上
            Member member = memberService.queryOne(apiVersion, example);
            if (null == member) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, "邮箱不存在！");
            }

            final String subject = "洋桃跨境供应链后台管理系统找回密码";
            final long curTime = System.currentTimeMillis();
            // 构造加密token
            Map m = new HashMap();
            m.put("time", curTime);
            m.put("email", email);
            m.put("password", passWord.trim());

            String json = MsOnionJsonUtils.objectToJson(m);

            String token = MsOnionSecurityUtils.encodeEmailToken(json);
            this.getMsOnionLogger().info(getClass().getName(), "## 发送加密token：" + token);

            // 组装邮件内容 文字+url
            String content = "请点击如下链接进行密码修改";


            String forgetUrl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FORGET_PASSWORD_DOMAIN);
            String url = forgetUrl + "/sys/verify-forget?token=";

            StringBuilder sb = new StringBuilder();
            sb.append(content).append(url).append(token);


            this.getMsOnionLogger().info(getClass().getName(), "## 发送内容 :" + sb.toString());
            //发送邮件
            MsOnionSendEmailUtils.sendEmail(email, subject, sb.toString());

            return MsOnionResult.ok();
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, e.getMessage());
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

    /**
     * 忘记密码确认操作
     * @param req 请求对象
     * @param res 响应对象
     * @return 忘记密码
     */
    @RequestMapping(value = "/sys/verify-forget")
    public String verifyForget(HttpServletRequest req, HttpServletResponse res) {
        MsOnionResult msOnionResult = null;
        String url = "/member/member/forgetFinsh";
        try {
            String token = req.getParameter("token");
            if (StringUtils.isBlank(token)) {
                msOnionResult = MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
                req.setAttribute("msOnionResult", msOnionResult);
                return url;
            }

            String data = MsOnionSecurityUtils.decodeEmailToken(token);
            this.getMsOnionLogger().info(getClass().getName(), "## 解密token：" + data);

            JSONObject dataJson = MsOnionJsonUtils.jsonToPojo(data, JSONObject.class);
            //JSONObject dataJson = JSONObject.parseObject(data);

            long replyTime = (long) dataJson.get("time");
            String email = (String) dataJson.get("email");
            String password = (String) dataJson.get("password");

            // 判断token是否已经在缓存中了，说明已经验证过了
            String redisKeyToke = MsOnionDynamicRedisKeyConstants.REDIS_EMAIL_FOUND_KEY + replyTime;
            String cache = dynamicCacheService.getFromRedisWithSecurity(redisKeyToke);
            if (token.equals(cache)) {
                msOnionResult = MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_EMAIL_TOKEN_USED);
                req.setAttribute("msOnionResult", msOnionResult);
                return url;
            }

            // 判断时间 30分钟内有效
            long currentTime = System.currentTimeMillis();
            if (currentTime - replyTime > ManagerConstants.FIFTEEN_MIN) {
                msOnionResult = MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_EMAIL_TOKEN_ERROR);
                req.setAttribute("msOnionResult", msOnionResult);
                return url;
            }

            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 更新密码
            msOnionResult = memberService.updatePassword(apiVersion, email, password);

            // 更新标识存入redis中
            dynamicCacheService.setForRedisWithSecurity(redisKeyToke, token, ManagerConstants.FIFTEEN_MIN_SECOND);


        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            msOnionResult = MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            msOnionResult = MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
        req.setAttribute("msOnionResult", msOnionResult);
        return url;
    }


    /**
     * 注册时 校验唯一性
     * @param req 请求对象
     * @param res 响应
     * @param type 校验类型
     * @param val 值
     * @param targetIdx 目标idx
     * @return MsOnionResult
     */
    @RequestMapping(value = "/sys/check-register", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult checkUnique(HttpServletRequest req, HttpServletResponse res, String type, String val, Long targetIdx) {
        try {
            if (!MsOnionRegexUtils.checkDigit(type)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + " , type=" + type);
            }

            if (StringUtils.isBlank(val)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + " , val=" + val);
            }

            // 如果是名称类型，需要判断长度
            if (ParamTypeConstants.TYPE_NAME == Integer.valueOf(type).intValue()) {
                if (MsOnionStringUtils.length(val.trim()) < MemberConstants.MEMBER_NAME_MIN_LENGTH
                        || MsOnionStringUtils.length(val.trim()) > MemberConstants.MEMBER_NAME_MAX_LENGTH) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                            cc.msonion.carambola.member.common.constants.MessageConstants.MESSAGE_MEMBER_NAME_LENGTH_ILLEGAL);
                }
            }

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            return memberService.inspectParameter(msOnionApiVersion, val, Integer.valueOf(type), targetIdx);
        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }


    /**
     * 显示编辑
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @param idxStr   主键
     * @return 返回路径
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月28日 下午6:59:32
     */
    @RequestMapping(value = "/sys/edit/{idxStr}")
    public String showEdit(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {
        try {
            long idx = 0;

            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(), "##　请求参数非法, idxStr=");
                return getError();
            }

            idx = Long.parseLong(idxStr);
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            Member member = this.memberService.queryByPrimaryKey(msOnionApiVersion, idx);
            if (null != member) {
                // 考虑到安全，必须屏蔽密码
                member.setPassword(null);
            }
            model.addAttribute("member", member);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　查询member失败");
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　查询member失败");
        }
        // return "/manager/edit";
        return "/member/member/editMember";
    }

    /**
     * 进行编辑（更新）
     *
     * @param req    HttpServletRequest实例对象
     * @param res    HttpServletResponse实例对象
     * @param model  Model实例对象
     * @param member 成员POJO实例对象
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/sys/do-edit", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model, Member member) {
        try {
            // 处理前端js加密
            if (member != null) {
                member.setFullName(MsOnionSecurityBase64Utils.decode(member.getFullName()));
                member.setCode(MsOnionSecurityBase64Utils.decode(member.getCode()));
                member.setPhone(MsOnionSecurityBase64Utils.decode(member.getPhone()));
                member.setTel(MsOnionSecurityBase64Utils.decode(member.getTel()));
                member.setEmail(MsOnionSecurityBase64Utils.decode(member.getEmail()));
                member.setRemark(MsOnionSecurityBase64Utils.decode(member.getRemark()));
            }

            this.getMsOnionLogger().info(getClass().getName(), "doEdit # 执行编辑用户（成员）信息 , member=" + member);

            member.setUpdateTime(new Date());
            member.setUpdateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            return this.memberService.update(msOnionApiVersion, member);
        } catch (Exception e) {

            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), "服务器忙，请稍后再试 ...");
        }
    }


    /**
     * 初始化文件资源中心相关地址和回现路径
     *
     * @param request request
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping(value = "/sys/initFileresource", method = RequestMethod.POST)
    public MsOnionResult initWarehouse(HttpServletRequest request) throws MsOnionException {
        String frDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_DOMAIN);
        String frImgurl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);
        return MsOnionResult.ok(frDomin + "," + frImgurl);
    }


}
