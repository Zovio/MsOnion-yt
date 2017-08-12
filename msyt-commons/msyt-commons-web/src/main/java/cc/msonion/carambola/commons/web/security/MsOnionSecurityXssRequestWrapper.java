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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 装饰器模式加强Request处理，Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 *
 * @Title: MsOnionSecurityXssRequestWrapper.java
 * @Package: cc.msonion.carambola.commons.web.security
 * @Description: 装饰器模式加强Request处理，Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月26日 下午10:02:28
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月26日 下午10:02:28
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * @ClassName: MsOnionSecurityXssRequestWrapper
 * @Description: 装饰器模式加强Request处理，Web安全，防XSS、CSRF攻击、SQL注入、上传文件后缀名漏洞（要采取白名单）、数据敏感词过滤等
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月26日 下午10:02:28
 */
public class MsOnionSecurityXssRequestWrapper extends HttpServletRequestWrapper {

    // Spring加载过滤器的时候，Beans还没有创建，因此这里不能使用
    /**
     * 日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * Policy
     */
    private Policy policy;

    /**
     * @param request HttpServletRequest实例对象
     */
    public MsOnionSecurityXssRequestWrapper(HttpServletRequest request) {
        super(request);
        // 打印日志
        if (null == msOnionLogger) {
            MsOnionLogger.doInfo(getClass().getName(), "MsOnionSecurityXssRequestWrapper  # Web安全，防XSS ## 装饰器模式加强request处理 # ...");
        } else {
            msOnionLogger.info(getClass().getName(), "MsOnionSecurityXssRequestWrapper  # Web安全，防XSS ## 装饰器模式加强request处理 # ...");
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> requestMap = super.getParameterMap();
        // 打印日志
        if (null == msOnionLogger) {
            MsOnionLogger.doInfo(getClass().getName(), "getParameterMap # requestMap=" + requestMap);
        } else {
            msOnionLogger.info(getClass().getName(), "getParameterMap # requestMap=" + requestMap);
        }
        Iterator iterator = requestMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            String[] values = (String[]) me.getValue();
            for (int i = 0; i < values.length; i++) {
                values[i] = handleXss(values[i]);
            }
        }
        return requestMap;
    }

    @Override
    public String[] getParameterValues(String paramString) {
        String[] arrayOfString1 = super.getParameterValues(paramString);
        // 打印日志
        if (null == msOnionLogger) {
            MsOnionLogger.doInfo(getClass().getName(), String.format("getParameterValues # paramString=%s", paramString));
        } else {
            msOnionLogger.info(getClass().getName(), String.format("getParameterValues # paramString=%s", paramString));
        }
        if (ArrayUtils.isEmpty(arrayOfString1)) {
            return null;
        }
        // 打印日志
        MsOnionLogger.doInfo(getClass().getName(), String.format("getParameterValues # paramString=%s，arrayOfString1=%s",
                paramString, Arrays.asList(arrayOfString1)));
        int i = arrayOfString1.length;
        String[] arrayOfString2 = new String[i];
        for (int j = 0; j < i; j++) {
            arrayOfString2[j] = handleXss(arrayOfString1[j]);
        }
        return arrayOfString2;
    }

    @Override
    public String getParameter(String paramString) {
        String str = super.getParameter(paramString);
        // 打印日志
        if (null == msOnionLogger) {
            MsOnionLogger.doInfo(getClass().getName(), String.format("getParameter # paramString=%s，参数值str=%s",
                    paramString, str));
        } else {
            msOnionLogger.info(getClass().getName(), String.format("getParameter # paramString=%s，参数值str=%s",
                    paramString, str));
        }
        return handleXss(str);
    }

    @Override
    public String getHeader(String paramString) {
        String str = super.getHeader(paramString);
        // 打印日志
        if (null == msOnionLogger) {
            MsOnionLogger.doInfo(getClass().getName(), String.format("getHeader # paramString=%s，参数值str=%s",
                    paramString, str));
        } else {
            msOnionLogger.info(getClass().getName(), String.format("getHeader # paramString=%s，参数值str=%s",
                    paramString, str));
        }
        return handleXss(str);
    }

    /**
     * 处理XSS攻击
     *
     * @param value 输入value
     * @return 返回处理XSS攻击之后的value
     */
    private String handleXss(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        AntiSamy antiSamy = new AntiSamy();
        try {
            // CleanResults cr = antiSamy.scan(dirtyInput, policyFilePath);
            // 解决中文乱码问题，GET、POST请求都拦截，POST中文乱码，已经在 web.xml 中配了，
            // 在全部拦截 GET、POST请求进行参数处理
//            value = MsOnionStringUtils.getNormalGetRequestParameter(value);
            // 打印日志
            if (null == msOnionLogger) {
                MsOnionLogger.doInfo(getClass().getName(), String.format("handleXss#参数值value=%s", value));
            } else {
                msOnionLogger.info(getClass().getName(), String.format("handleXss#参数值value=%s", value));
            }
            final CleanResults cr = antiSamy.scan(value, getPolicy());
            // 安全的HTML输出
            return cr.getCleanHTML();
        } catch (Exception ex) {
            MsOnionLogger.doInfo(getClass().getName(), ex, null);
        }
        return value;
    }

    ////////////////  Getters 、Setters # Begin ///////////////////////////

    /**
     * 获取策略
     *
     * @return 返回策略
     */
    public Policy getPolicy() {
        if (null == this.policy) {
            try {
                // antisamy-anythinggoes.xml 是在 jar包中，这里直接写死了！！！
                this.policy = Policy.getInstance(MsOnionSecurityXssRequestWrapper.class.getClassLoader()
                        .getResourceAsStream("antisamy-anythinggoes.xml"));
            } catch (Exception ex) {
                // 异常日志
                if (null == msOnionLogger) {
                    MsOnionLogger.doInfo(getClass().getName(), ex, null);
                } else {
                    msOnionLogger.info(getClass().getName(), ex, null);
                }
            }
        }
        return this.policy;
    }

    ////////////////  Getters 、Setters # End ///////////////////////////

}
