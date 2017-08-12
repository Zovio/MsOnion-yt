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


package cc.msonion.carambola.manager.web.security;

/**
 *  
 *
 * @Title: AnyRolesFilter.java
 * @Package: cc.msonion.carambola.manager.web.security
 * @Description: shiro 自定义过滤器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月30日
 * @Version:
 * @Modify-by:
 * @Modify-date:
 * @Modify-version:
 * @Modify-description:
 */

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.web.controller.ext.AuthExtUtils;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.controller.ext.StaticResourcesVersionUtils;
import cc.msonion.carambola.member.common.constants.MemberConstants;
import cc.msonion.carambola.member.ext.view.MenuViewObject;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.*;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionSysSetCodeConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.service.SysSettingService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: AnyRolesFilter
 * @Description: shiro 自定义过滤器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月30日
 */
public class AnyRolesFilter extends AccessControlFilter {


    /**
     * 系统url。需要添加进去
     */
    @Value("${shiro_sysUrls}")
    private String sysUrls;

    /**
     * 登录页url
     */
    @Value("${shiro_loginUrl}")
    private String loginUrl;
    /**
     * 成功页url
     */
    @Value("${shiro_successUrl}")
    private String successUrl;

    /**
     * 未授权 url
     */
    @Value("${shiro_unauthorizedUrl}")
    private String unauthorizedUrl;


    /**
     * 超级管理员
     */
    @Value("${shiro.member.super}")
    private String shiroSuper;


    /**
     * MemberService
     */
    @Autowired
    private MemberService memberService;

    /**
     * menuService
     */
    @Autowired
    private MenuService menuService;

    /**
     * memberRoleService
     */
    @Autowired
    private MemberRoleService memberRoleService;

    /**
     * roleResourceGroupService
     */
    @Autowired
    private RoleResourceGroupService roleResourceGroupService;

    /**
     * resourceService
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * msOnionLogger
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * sysSettingService
     */
    @Autowired
    private SysSettingService sysSettingService;

    /**
     * dynamicCacheService
     */
    @Autowired
    private DynamicCacheService dynamicCacheService;



    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request, response);
        HttpServletRequest hrequest = WebUtils.toHttp(request);

        // 初始化静态资源版本号
        StaticResourcesVersionUtils.initStaticVersionToSession(dynamicCacheService, hrequest, WebUtils.toHttp(response));

        // 设置静态资源域名
        this.setStaticDomain2Application(hrequest);

        // 表示没有登录，重定向到登录页面
        if (subject.getPrincipal() == null) {
            return false;
        }

        //是否记住我 subject.isAuthenticated() 表示用户是否进行了身份验证登录的
        if (!subject.isAuthenticated() && subject.isRemembered()) {
            MsOnionApiVersion msOnionApiVersion = new MsOnionApiVersion();
            msOnionApiVersion.setRequestApiVersion(MemberConstants.API_VERSION);
            String principal = subject.getPrincipal().toString();
            Long userId = Long.valueOf(principal.split(ManagerConstants.DOLLAR_ESCAPE)[0]);
            Member member = this.memberService.queryByPrimaryKey(msOnionApiVersion, userId);
            // 成员为空，状态不正常的话，都跳去登录页面
            if (null == member || member.getStatus() != MsOnionStatusConstants.SQL_STATUS_ONE_VALUE) {
                subject.logout();
                return false;
            }
            // 修改过密码,跳去登录页面
            if (!member.getPassword().equals(principal.split(ManagerConstants.DOLLAR_ESCAPE)[1])) {
                subject.logout();
                return false;
            }
            subject.login(new UsernamePasswordToken(
                    member.getName(), MsOnionSecurityUtils.md5ForPassword(member.getPassword()), true));
            Session session = subject.getSession(false);
            session.setAttribute(ManagerConstants.SESSION_MEMBER, member);
            String frDomin = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion, MsOnionSysSetCodeConstants.FR_DOMAIN);
            String frImgurl = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion, MsOnionSysSetCodeConstants.FR_IMGUTL);
            session.setAttribute("fileResourceApi", frDomin);
            session.setAttribute("picimgurl", frImgurl);
        }

        Session session = subject.getSession(false);
        Member member = (Member) session.getAttribute(ManagerConstants.SESSION_MEMBER);
        if (null == member) {

            return false;
        }

        // 查询授权菜单存入session中
        MenuViewObject menuViewObject = (MenuViewObject) session.getAttribute(ManagerConstants.SESSION_MENU);
        if (menuViewObject == null) {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 如果是 SUPER 角色，则拥有所有的菜单
            List<Long> menuIdxList = null;

            if (!subject.hasRole(shiroSuper)) {
                menuIdxList = AuthExtUtils.getMenuIdxList(apiVersion, member, memberRoleService, roleResourceGroupService, resourceService);
                msOnionLogger.info(getClass().getName(), "当前用户：" + member.getName() + ", menuIdxList:" + menuIdxList);
            }

            MsOnionResult msOnionResult = menuService.getMenuViewObject(apiVersion, menuIdxList);
            menuViewObject = (MenuViewObject) msOnionResult.getData();

            // 处理首页顶部显示菜单
            String numStr = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, apiVersion, MsOnionSysSetCodeConstants.MSYT_TOPSHOW_NUM);
            numStr = Optional.ofNullable(numStr).orElse(ManagerConstants.TOP_SHOW_NUM);

            List<MenuViewObject> childrens = menuViewObject.getChildren().parallelStream().filter(s -> s.getTopShow() == 1)
                    .limit(Integer.valueOf(numStr)).collect(Collectors.toList());
            session.setAttribute(ManagerConstants.SESSION_TOP_SHOW_MENU, childrens);

            session.setAttribute(ManagerConstants.SESSION_MENU, menuViewObject);
            session.setAttribute(ManagerConstants.SESSION_MENU_JSON, MsOnionJsonUtils.objectToJson(menuViewObject.getChildren()));
        }

        // 判断当前url访问权限
        String requestURI = getPathWithinApplication(request);
        // System.out.println("requestURI： " + requestURI);

        // 登录成功页直接放过
        if (requestURI.equals(successUrl)) {
            return true;
        }

        // 从 menuViewObject 获取 urlmapping List 集合判断
        List<String> urlMappingList = null;
        urlMappingList = (List<String>) session.getAttribute(ManagerConstants.SESSION_URL_MAPPING);
        if (urlMappingList == null) {
            urlMappingList = new ArrayList<>();
            urlMappingList.add(sysUrls);
            urlMappingList = getUrlMappingList2(menuViewObject, urlMappingList);
            session.setAttribute(ManagerConstants.SESSION_URL_MAPPING, urlMappingList);
        }

        for (int i = 0; i < urlMappingList.size(); i++) {
            if (pathMatcher.matches(urlMappingList.get(i), requestURI)) {
                return true;
            }
        }
        // onAccessDenied 方法
        return false;

    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            if (isAjax(request)) {
                out(response, msOnionLogger, MsOnionStatusConstants.STATUS_1, "当前用户已失效，请重新登录");
            } else {
                // 表示没有登录，重定向到登录页面
                saveRequest(request);
                WebUtils.issueRedirect(request, response, loginUrl);
            }
        } else {
            if (StringUtils.hasText(unauthorizedUrl)) {
                if (isAjax(request)) {
                    out(response, msOnionLogger, MsOnionStatusConstants.STATUS_2, "您没有该请求的权限，请联系管理员");
                } else {
                    // 如果有未授权页面跳转过去
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                }
            } else {
                // 否则返回401未授权状态码
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }

    /**
     * 获取所有urlMapping 集合 （dfs）
     * 数据量过大会导致 java.lang.StackOverflowError
     * @param mvo     MenuViewObject
     * @param urlList urlList
     * @return urlMapping 集合
     */
    private List<String> getUrlMappingList(MenuViewObject mvo, List<String> urlList) {
        if (mvo == null) {
            return urlList;
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(mvo.getUrlMapping())) {
            urlList.add(mvo.getUrlMapping());
        }

        if (MsOnionCollectionUtils.isNotEmpty(mvo.getChildren())) {
            List<MenuViewObject> childrens = mvo.getChildren();
            for (MenuViewObject obj : childrens) {
                getUrlMappingList(obj, urlList);
            }
        }
        return urlList;
    }

    /**
     * 建议使用
     * 获取所有urlMapping 集合 （bfs）
     *
     * @param mvo     MenuViewObject
     * @param urlList urlList
     * @return urlMapping 集合
     */
    private List<String> getUrlMappingList2(MenuViewObject mvo, List<String> urlList) {
        if (mvo == null) {
            return urlList;
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(mvo.getUrlMapping())) {
            urlList.add(mvo.getUrlMapping());
        }

        List<MenuViewObject> childrens = mvo.getChildren();
        if (MsOnionCollectionUtils.isNotEmpty(childrens)) {
            while (MsOnionCollectionUtils.isNotEmpty(childrens)) {
                List<MenuViewObject> tempChilds = new ArrayList<>();
                for (MenuViewObject obj : childrens) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(obj.getUrlMapping())) {
                        urlList.add(obj.getUrlMapping());
                    }
                    if (MsOnionCollectionUtils.isNotEmpty(obj.getChildren())) {
                        for (MenuViewObject obj2 : obj.getChildren()) {
                            tempChilds.add(obj2);
                        }
                    }
                }
                childrens = tempChilds;
            }
        }
        return urlList;
    }

    /**
     * 是否是Ajax请求
     *
     * @param request 请求对象
     * @return 结果
     */
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     *   使用	response 输出JSON
     * @param response 响应对象
     * @param msOnionLogger 日志
     * @param status 状态
     * @param msg 提示语
     */
    public static void out(ServletResponse response, MsOnionLogger msOnionLogger, int status, String msg) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write(MsOnionJsonUtils.objectToJson(MsOnionResult.build(status, msg)));
            out.flush();
        } catch (Exception e) {
            msOnionLogger.error(AnyRolesFilter.class.getName(), e);
        }
    }

    /**
     * 设置静态资源变量
     * @param request 请求
     * @throws MsOnionException 自定义异常
     */
    private void setStaticDomain2Application(HttpServletRequest request) throws MsOnionException {
        MsOnionApiVersion msOnionApiVersion = new MsOnionApiVersion();
        msOnionApiVersion.setRequestApiVersion(MemberConstants.API_VERSION);

        String staticDomain = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.STATIC_DOMAIN);

        ServletContext application = request.getServletContext();
        application.setAttribute(ManagerConstants.SESSION_STATIC_DOMAIN, staticDomain);
    }
}
