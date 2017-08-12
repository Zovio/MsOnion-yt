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

package cc.msonion.carambola.commons.web.security;

import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 *
 * @Title: MsOnionSecurityXssFilter.java
 * @Package: cc.msonion.carambola.commons.web.security
 * @Description: Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月26日 下午10:32:28
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月26日 下午10:32:28
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 *
 * @ClassName: MsOnionSecurityXssFilter
 * @Description: Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月26日 下午10:32:28
 */
public class MsOnionSecurityXssFilter implements Filter {

    // Spring 加载过滤器的时候，MsOnionLog 还是为null，Beans还没有创建
    /**
     * 日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * 需要排除的页面，不包括在内的页面
     */
    private String excludedPages;

    /**
     * 需要排除的页面，不包括在内的页面
     */
    private String[] excludedPageArray;

    /**
     * 过滤器配置
     */
    private FilterConfig filterConfig;

    /**
     * excludedPages，需要排除的页面，不包括在内的页面的参数名称，在web.xml中配置的
     */
    private static final String PARAMETER_NAME_EXCLUDED_PAGES = "excludedPages";

    /**
     * 自定义过滤规则
     *
     * @param filterConfig 过滤器配置
     * @throws ServletException 异常
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        excludedPages = filterConfig.getInitParameter(PARAMETER_NAME_EXCLUDED_PAGES);
        excludedPageArray = new String[]{};
        // 打印日志
        if (null == msOnionLogger) {
            MsOnionLogger.doInfo(getClass().getName(), String.format("init # excludedPages=%s，excludedPageArray=%s",
                    excludedPages, excludedPageArray));
        } else {
            msOnionLogger.info(getClass().getName(), String.format("init # excludedPages=%s，excludedPageArray=%s",
                    excludedPages, excludedPageArray));
        }

        if (StringUtils.isNotEmpty(excludedPages)) {
            // 替换所有的空格
            excludedPageArray = excludedPages.replaceAll("[\\s]", "")
                    .split(",");
        }
    }

    /**
     * 执行过滤
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        boolean isExcludedPage = false;
        HttpServletRequest req = (HttpServletRequest) request;
        // 判断是否需要XSS攻击防护
        isExcludedPage = isMatchUrl(excludedPageArray, req);

        if (isExcludedPage) {
            // 打印日志
            if (null == msOnionLogger) {
                MsOnionLogger.doInfo(getClass().getName(), String.format("doFilter # 过滤 # 不需要处理XSS # isExcludedPage=%s", isExcludedPage));
            } else {
                msOnionLogger.info(getClass().getName(), String.format("doFilter # 过滤 # 不需要处理XSS # isExcludedPage=%s", isExcludedPage));
            }
            chain.doFilter(request, response);
        } else {
            // 打印日志
            if (null == msOnionLogger) {
                MsOnionLogger.doInfo(getClass().getName(), String.format("doFilter # 过滤 # 需要处理XSS # isExcludedPage=%s", isExcludedPage));
            } else {
                msOnionLogger.info(getClass().getName(), String.format("doFilter # 过滤 # 需要处理XSS # isExcludedPage=%s", isExcludedPage));
            }
            chain.doFilter(new MsOnionSecurityXssRequestWrapper(req), response);
        }
    }

    /**
     * URL是否符合规则列表
     *
     * @param patterns 规则
     * @param request  HttpServletRequest实例对象
     * @return 返回是否符合规则列表
     */
    public boolean isMatchUrl(String[] patterns, HttpServletRequest request) {
        //获取项目名称
        String contextPath = request.getContextPath();
        // 获取请求URL
        String requestURI = request.getRequestURI();
        // 将Url中得//转换为/
        String action = requestURI.substring(contextPath.length()).replaceAll("//", "/");
        // 打印日志
        MsOnionLogger.doInfo(getClass().getName(), String.format("isMatchUrl # contextPath=%s，requestURI=%s，action=%s",
                contextPath, requestURI, action));
        return PatternMatchUtils.simpleMatch(patterns, action);
    }

    /**
     * 释放、回收资源
     */
    @Override
    public void destroy() {
        this.filterConfig = null;
        // 打印日志
        MsOnionLogger.doInfo(getClass().getName(), String.format("destroy # 释放回收资源 ，End !!!"));
    }

}
