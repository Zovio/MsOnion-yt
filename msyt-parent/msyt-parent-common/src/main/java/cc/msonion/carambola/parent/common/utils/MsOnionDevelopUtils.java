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


package cc.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import org.apache.commons.lang3.StringUtils;

/**
 * 开发工具类，主要提供切换环境（生产，测试，预览，开发）相关方法
 *
 * @Title: MsOnionDevelopUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 开发工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午3:10:40
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月15日 下午3:10:40
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：生产、测试、预览、开发环境相关配置
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月05日 上午10:15:40
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：稳定环境相关配置，修改：加入稳定环境的判断
 */

/**
 * 开发工具类，主要提供切换环境（生产，测试，预览，开发）相关方法
 *
 * @ClassName: MsOnionDevelopUtils
 * @Description: 开发工具类，主要提供切换环境（生产，测试，预览，开发）相关方法
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午3:10:40
 */
public class MsOnionDevelopUtils {

    /**
     * 默认长度
     */
    private static final int DEFAULT_LENGTH = 2;

    protected MsOnionDevelopUtils() {
    }

    // # Environment : pro（production） , pre（preview） , dev（develop） , test

    /**
     * @Fields ENVIRONMENT_PRO : 生产环境（production）
     */
    public static final String ENVIRONMENT_PRO = "pro";

    /**
     * @Fields ENVIRONMENT_PRE : preview（预览）环境
     */
    public static final String ENVIRONMENT_PRE = "pre";

    /**
     * @Fields ENVIRONMENT_STA : stable（稳定）环境
     */
    public static final String ENVIRONMENT_STA = "sta";

    /**
     * @Fields ENVIRONMENT_DEV : 开发环境
     */
    public static final String ENVIRONMENT_DEV = "dev";

    /**
     * @Fields ENVIRONMENT_TEST : 测试环境
     */
    public static final String ENVIRONMENT_TEST = "test";

    /**
     * 根据环境获取协议
     *
     * @param environment  环境
     * @param proProtocol  生产环境协议
     * @param preProtocol  预览（预发布）环境协议
     * @param staProtocol  稳定环境协议
     * @param testProtocol 测试环境协议
     * @param devProtocol  开发环境协议
     * @return 返回环境对应的协议
     * @throws MsOnionIllegalArgumentException 异常
     * @Title: getProtocol
     * @Description: 根据环境获取协议
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月15日 下午3:30:48
     */
    public static String getProtocol(String environment, String proProtocol, String preProtocol, String staProtocol, String testProtocol, String devProtocol) throws MsOnionIllegalArgumentException {

        if (StringUtils.isBlank(environment)) {
            throw new MsOnionIllegalArgumentException("环境不可以为空，environment =" + environment);
        }
        if (StringUtils.isBlank(proProtocol)) {
            throw new MsOnionIllegalArgumentException("生产环境协议，不可以为空，proProtocol = " + proProtocol);
        }
        if (StringUtils.isBlank(preProtocol)) {
            throw new MsOnionIllegalArgumentException("预览环境协议，不可以为空，preProtocol = " + preProtocol);
        }
        if (StringUtils.isBlank(staProtocol)) {
            throw new MsOnionIllegalArgumentException("稳定环境协议，不可以为空，staProtocol =" + staProtocol);
        }
        if (StringUtils.isBlank(testProtocol)) {
            throw new MsOnionIllegalArgumentException("测试环境协议，不可以为空，testProtocol = " + testProtocol);
        }

        if (StringUtils.isBlank(devProtocol)) {
            throw new MsOnionIllegalArgumentException("开发环境协议，不可以为空，devProtocol = " + devProtocol);
        }

        environment = environment.trim();   // 环境
        proProtocol = proProtocol.trim();  // 生产环境协议
        preProtocol = preProtocol.trim();   // 预览（预发布）环境协议
        staProtocol = staProtocol.trim();   // 稳定环境协议
        testProtocol = testProtocol.trim();   // 测试环境协议
        devProtocol = devProtocol.trim();   // 开发环境协议

        // http://www.jd.com/
        // https://www.taobao.com/

        // https://
        if (proProtocol.endsWith(":")) {
            proProtocol += "//";
        } else if (!proProtocol.endsWith("//")) {
            proProtocol += "://";
        }

        if (preProtocol.endsWith(":")) {
            preProtocol += "//";
        } else if (!preProtocol.endsWith("//")) {
            preProtocol += "://";
        }

        if (staProtocol.endsWith(":")) {
            staProtocol += "//";
        } else if (!staProtocol.endsWith("//")) {
            staProtocol += "://";
        }

        if (testProtocol.endsWith(":")) {
            testProtocol += "//";
        } else if (!testProtocol.endsWith("//")) {
            testProtocol += "://";
        }

        if (devProtocol.endsWith(":")) {
            devProtocol += "//";
        } else if (!devProtocol.endsWith("//")) {
            devProtocol += "://";
        }

        // 测试环境
        if (ENVIRONMENT_TEST.equalsIgnoreCase(environment)) {
            return testProtocol;
        } else if (ENVIRONMENT_PRE.equalsIgnoreCase(environment)) {
            // 预览环境
            return preProtocol;
        } else if (ENVIRONMENT_STA.equalsIgnoreCase(environment)) {
            // 稳定环境
            return preProtocol;
        } else if (ENVIRONMENT_DEV.equalsIgnoreCase(environment)) {
            // 开发环境
            return devProtocol;
        } else {
            // 默认是生产环境
            return proProtocol;
        }
    }

    /**
     * 根据环境，获取域名
     *
     * @param environment 环境
     * @param proDomain   生产环境域名
     * @param preDomain   预览（预发布）环境域名
     * @param staDomain   稳定环境域名
     * @param testDomain  测试环境域名
     * @param devDomain   开发环境域名
     * @return 返回具体环境对应的域名
     * @throws MsOnionIllegalArgumentException 异常
     * @Title: getDomain
     * @Description: 根据环境，获取域名
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2017年2月15日 下午5:19:36
     */
    public static String getDomain(String environment, String proDomain, String preDomain,
                                   String staDomain, String testDomain, String devDomain) throws MsOnionIllegalArgumentException {

        if (StringUtils.isBlank(environment)) {
            throw new MsOnionIllegalArgumentException("环境不可以为空，environment = " + environment);
        }
        if (StringUtils.isBlank(proDomain)) {
            throw new MsOnionIllegalArgumentException("生产环境域名，不可以为空，proDomain = " + proDomain);
        }
        if (StringUtils.isBlank(preDomain)) {
            throw new MsOnionIllegalArgumentException("预览环境域名，不可以为空，preDomain=" + preDomain);
        }
        if (StringUtils.isBlank(staDomain)) {
            throw new MsOnionIllegalArgumentException("稳定环境域名，不可以为空，staDomain=" + staDomain);
        }
        if (StringUtils.isBlank(testDomain)) {
            throw new MsOnionIllegalArgumentException("测试环境域名，不可以为空，testDomain=" + testDomain);
        }
        if (StringUtils.isBlank(devDomain)) {
            throw new MsOnionIllegalArgumentException("开发环境域名，不可以为空，devDomain=" + devDomain);
        }

        environment = environment.trim();
        proDomain = proDomain.trim();
        preDomain = preDomain.trim();
        staDomain = staDomain.trim();
        testDomain = testDomain.trim();
        devDomain = devDomain.trim();

        // https://www.taobao.com/  ==>  https://www.taobao.com
        if (proDomain.contains("/")) {
            proDomain = proDomain.replace("/", "");
        }
        if (preDomain.contains("/")) {
            preDomain = preDomain.replace("/", "");
        }
        if (staDomain.contains("/")) {
            staDomain = staDomain.replace("/", "");
        }
        if (testDomain.contains("/")) {
            testDomain = testDomain.replace("/", "");
        }
        if (devDomain.contains("/")) {
            devDomain = devDomain.replace("/", "");
        }

        if (StringUtils.isBlank(environment) || environment.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("环境参数非法，environment=" + environment);
        }
        if (StringUtils.isBlank(proDomain) || proDomain.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("生产环境域名参数非法，proDomain=" + proDomain);
        }
        if (StringUtils.isBlank(preDomain) || preDomain.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("预览环境域名非法，preDomain=" + preDomain);
        }
        if (StringUtils.isBlank(staDomain) || staDomain.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("稳定环境域名非法，staDomain=" + staDomain);
        }
        if (StringUtils.isBlank(testDomain) || testDomain.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("测试环境域名参数非法，testDomain=" + testDomain);
        }
        if (StringUtils.isBlank(devDomain) || devDomain.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("开发环境域名参数非法，devDomain=" + devDomain);
        }

        // 测试环境
        if (ENVIRONMENT_TEST.equalsIgnoreCase(environment)) {
            return testDomain;
        } else if (ENVIRONMENT_PRE.equalsIgnoreCase(environment)) {
            // 预览环境
            return preDomain;
        } else if (ENVIRONMENT_STA.equalsIgnoreCase(environment)) {
            // 稳定环境
            return staDomain;
        } else if (ENVIRONMENT_DEV.equalsIgnoreCase(environment)) {
            // 开发环境
            return devDomain;
        } else {
            // 默认是生产环境
            return proDomain;
        }
    }

    /**
     * 根据环境，获取协议和域名，域名最后没有包括“/”，例如： https://www.taobao.com
     *
     * @param environment  环境，例如：pro、pre、sta、test、dev
     * @param proProtocol  生产环境协议
     * @param preProtocol  预览环境协议
     * @param staProtocol  稳定环境协议
     * @param testProtocol 测试环境协议
     * @param devProtocol  开发环境协议
     * @param proDomain    生产环境域名
     * @param preDomain    预览环境域名
     * @param staDomain    稳定环境域名
     * @param testDomain   测试环境域名
     * @param devDomain    开发环境域名
     * @return 返回根据环境，获取协议和域名，域名最后没有包括“/”，例如： https://www.taobao.com
     * @throws MsOnionIllegalArgumentException 非法参数异常，自定义的，非RuntimeException
     * @Title: getProtocolAndDomain
     * @Description: 根据环境，获取协议和域名，域名最后没有包括“/”，例如： https://www.taobao.com
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2017年2月15日 下午5:23:01
     */
    public static String getProtocolAndDomain(String environment, String proProtocol, String preProtocol, String staProtocol, String testProtocol, String devProtocol,
                                              String proDomain, String preDomain, String staDomain, String testDomain, String devDomain) throws MsOnionIllegalArgumentException {
        String protocol = getProtocol(environment, proProtocol, preProtocol, staProtocol, testProtocol, devProtocol);
        String domain = getDomain(environment, proDomain, preDomain, staDomain, testDomain, devDomain);
        return protocol + domain;
    }

    /**
     * 获取图片Url
     *
     * @param environment       环境，例如：pro、pre、sta、test、dev
     * @param protocolAndDomain 协议和域名
     * @param proUri            生产环境URI
     * @param preUri            预览环境URI
     * @param staUri            稳定环境URI
     * @param testUri           测试环境URI
     * @param devUri            开发环境URI
     * @return 返回图片URL
     * @throws MsOnionIllegalArgumentException 异常
     */
    public static String getImageUrl(String environment, String protocolAndDomain,
                                     String proUri, String preUri, String staUri, String testUri, String devUri) throws MsOnionIllegalArgumentException {

        if (StringUtils.isBlank(environment)) {
            throw new MsOnionIllegalArgumentException("环境不可以为空，environment=" + environment);
        }

        if (StringUtils.isBlank(protocolAndDomain)) {
            throw new MsOnionIllegalArgumentException("环境，协议和域名不可以为空，protocolAndDomain=" + protocolAndDomain);
        }

        if (StringUtils.isBlank(proUri)) {
            throw new MsOnionIllegalArgumentException("生产环境图片Uri，不可以为空，proUri=" + proUri);
        }
        if (StringUtils.isBlank(preUri)) {
            throw new MsOnionIllegalArgumentException("预览环境图片Uri，不可以为空，preUri=" + preUri);
        }
        if (StringUtils.isBlank(staUri)) {
            throw new MsOnionIllegalArgumentException("稳定环境图片Uri，不可以为空，staUri=" + staUri);
        }
        if (StringUtils.isBlank(testUri)) {
            throw new MsOnionIllegalArgumentException("测试环境图片Uri，不可以为空，testUri=" + testUri);
        }
        if (StringUtils.isBlank(devUri)) {
            throw new MsOnionIllegalArgumentException("开发环境图片Uri，不可以为空，devUri=" + devUri);
        }

        environment = environment.trim();
        protocolAndDomain = protocolAndDomain.trim();
        proUri = proUri.trim();
        preUri = preUri.trim();
        staUri = staUri.trim();
        testUri = testUri.trim();
        devUri = devUri.trim();

        if (StringUtils.isBlank(environment) || environment.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("环境参数非法，environment=" + environment);
        }
        if (StringUtils.isBlank(protocolAndDomain) || protocolAndDomain.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("协议和域名参数非法，protocolAndDomain=" + protocolAndDomain);
        }
        if (StringUtils.isBlank(proUri) || proUri.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("生产环境图片Uri参数非法，productionUri=" + proUri);
        }
        if (StringUtils.isBlank(preUri) || preUri.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("预览环境图片Uri参数非法，preUri=" + preUri);
        }
        if (StringUtils.isBlank(staUri) || staUri.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("稳定环境图片Uri参数非法，staUri=" + staUri);
        }
        if (StringUtils.isBlank(testUri) || testUri.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("测试环境图片Uri参数非法，testUri=" + testUri);
        }
        if (StringUtils.isBlank(devUri) || devUri.length() <= DEFAULT_LENGTH) {
            throw new MsOnionIllegalArgumentException("开发环境图片Uri参数非法，devUri=" + devUri);
        }

        // 判断是否包括 http 或者 https

        if (proUri.startsWith("http")) {
            throw new MsOnionIllegalArgumentException("生产环境图片Uri参数非法，不可以包括http、https、http://、https://，productionUri=" + proUri);
        }
        if (preUri.startsWith("http")) {
            throw new MsOnionIllegalArgumentException("预览环境图片Uri参数非法，不可以包括http、https、http://、https://，preUri=" + preUri);
        }
        if (staUri.startsWith("http")) {
            throw new MsOnionIllegalArgumentException("稳定环境图片Uri参数非法，不可以包括http、https、http://、https://，staUri=" + staUri);
        }
        if (testUri.startsWith("http")) {
            throw new MsOnionIllegalArgumentException("测试环境图片Uri参数非法，不可以包括http、https、http://、https://，testUri=" + testUri);
        }
        if (devUri.startsWith("http")) {
            throw new MsOnionIllegalArgumentException("测试环境图片Uri参数非法，不可以包括http、https、http://、https://，devUri=" + devUri);
        }

        // http://www.jd.com
        // https://www.taobao.com

        if (protocolAndDomain.endsWith("/")) {
            protocolAndDomain = protocolAndDomain.substring(0, protocolAndDomain.length() - 1);
        }

        if (!proUri.startsWith("/")) {
            proUri = "/" + proUri;
        }
        if (!preUri.startsWith("/")) {
            preUri = "/" + preUri;
        }
        if (!staUri.startsWith("/")) {
            staUri = "/" + staUri;
        }
        if (!testUri.startsWith("/")) {
            testUri = "/" + testUri;
        }
        if (!devUri.startsWith("/")) {
            devUri = "/" + devUri;
        }

        // 测试环境
        if (ENVIRONMENT_TEST.equalsIgnoreCase(environment)) {
            return protocolAndDomain + testUri;
        } else if (ENVIRONMENT_PRE.equalsIgnoreCase(environment)) {
            // 预览环境
            return protocolAndDomain + preUri;
        } else if (ENVIRONMENT_STA.equalsIgnoreCase(environment)) {
            // 稳定环境
            return protocolAndDomain + staUri;
        } else if (ENVIRONMENT_DEV.equalsIgnoreCase(environment)) {
            // 开发环境
            return protocolAndDomain + devUri;
        } else {
            // 默认是生产环境
            return protocolAndDomain + proUri;
        }
    }
}
