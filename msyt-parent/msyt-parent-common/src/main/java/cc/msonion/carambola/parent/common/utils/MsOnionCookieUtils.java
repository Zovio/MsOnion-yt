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

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.SimpleCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie工具类
 *
 * @Title: MsOnionCookieUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: Cookie工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:29:39
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:29:39
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * @ClassName: MsOnionCookieUtils
 * @Description: Cookie工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:29:39
 */
public final class MsOnionCookieUtils {

    /**
     * 默认Cookie周期为-1，也就是清除Cookie
     */
    private static final int DEFAULT_COOKIE_MAXAGE = -1;  // -1就是清除Cookie
    /**
     * 编码为UTF-8
     */
    private static final String ENC = "UTF-8";  // UTF-8
    /**
     * 本地localhost
     */
    private static final String LOCAL_HOST = "localhost";

    private MsOnionCookieUtils() {
    }

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request    HttpServletRequest实例对象
     * @param cookieName Cookie名称
     * @return 返回Cookie的值
     * @throws MsOnionException 异常
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) throws MsOnionException {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值, 用户Token专用的，没有加密Token，因为Ajax需要获取Cookie
     *
     * @param request    HttpServletRequest实例对象
     * @param cookieName Cookie名称
     * @return 返回Cookie的值
     * @throws MsOnionException 异常
     */
    public static String getCookieValueForUserToken(HttpServletRequest request, String cookieName) throws MsOnionException {
        return getCookieValue(request, cookieName, true, false);
    }

    /**
     * 得到Cookie的值
     *
     * @param request    HttpServletRequest实例对象
     * @param cookieName Cookie名称
     * @param isDecoder  是否解码
     * @return 返回Cookie中数据
     * @throws MsOnionException 异常
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) throws MsOnionException {
        return getCookieValue(request, cookieName, isDecoder, true);
    }

    /**
     * 获取Cookie中数据
     *
     * @param request    HttpServletRequest实例对象
     * @param cookieName Cookie名称
     * @param isDecoder  是否解码
     * @param is3DES     是否3DES
     * @return 返回Cookie中数据
     * @throws MsOnionException 异常
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder, boolean is3DES) throws MsOnionException {
        try {
            Cookie[] cookieList = request.getCookies();
            if (null == cookieList || null == cookieName) {
                return null;
            }
            String retValue = null;

            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = cookieList[i].getValue();

                    // 必须注意顺序， 添加Cookie的时候，是最后解密 3DES，
                    // 获取的时候，必须先解密 3DES
                    // 3DES 解密
                    if (is3DES && StringUtils.isNotBlank(retValue)) {
                        retValue = MsOnionSecurityUtils.decodeForCookie(retValue);
                    }

                    if (isDecoder) {
                        retValue = URLDecoder.decode(retValue, ENC);
                    }
                    break;
                }
            }
            return retValue;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     *
     * @param request     HttpServletRequest实例对象
     * @param response    HttpServletResponse实例对象
     * @param cookieName  Cookie名称
     * @param cookieValue Cookie值（数据）
     * @throws MsOnionException 异常
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) throws MsOnionException {
        setCookie(request, response, cookieName, cookieValue, DEFAULT_COOKIE_MAXAGE); // -1
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     *
     * @param request      HttpServletRequest实例对象
     * @param response     HttpServletResponse实例对象
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效周期（单位：秒）
     * @throws MsOnionException 异常
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage) throws MsOnionException {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 在指定时间内生效，这是User Token使用的，由于需要通过Ajax获取Cookie，因此不能加密
     *
     * @param request      HttpServletRequest实例对象
     * @param response     HttpServletResponse实例对象
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效周期（单位：秒）
     * @throws MsOnionException 异常
     */
    public static void setCookieForUserToken(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                             String cookieValue, int cookieMaxage) throws MsOnionException {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, true, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     *
     * @param request     HttpServletRequest实例对象
     * @param response    HttpServletResponse实例对象
     * @param cookieName  Cookie名称
     * @param cookieValue Cookie值
     * @param isEncode    是否编码
     * @throws MsOnionException 异常
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) throws MsOnionException {
        setCookie(request, response, cookieName, cookieValue, DEFAULT_COOKIE_MAXAGE, isEncode);  // -1
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     *
     * @param request      HttpServletRequest实例对象
     * @param response     HttpServletResponse实例对象
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效周期（单位：秒）
     * @param isEncode     是否编码
     * @throws MsOnionException 异常
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, boolean isEncode) throws MsOnionException {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 删除Cookie带cookie域名
     *
     * @param request    HttpServletRequest实例对象
     * @param response   HttpServletResponse实例对象
     * @param cookieName Cookie名称
     * @throws MsOnionException 异常
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName) throws MsOnionException {
        doSetCookie(request, response, cookieName, "", DEFAULT_COOKIE_MAXAGE, false);
    }

    /**
     * 删除Cookie带cookie域名
     *
     * @param request    HttpServletRequest实例对象
     * @param response   HttpServletResponse实例对象
     * @param cookieName Cookie名称
     * @throws MsOnionException 异常
     */
    public static void deleteCookieForUserToken(HttpServletRequest request, HttpServletResponse response,
                                                String cookieName) throws MsOnionException {
        doSetCookie(request, response, cookieName, "", DEFAULT_COOKIE_MAXAGE, true, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    /**
     * @param request      HttpServletRequest实例对象
     * @param response     HttpServletResponse实例对象
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值（数据）
     * @param cookieMaxage Cookie生效周期（单位：秒）
     * @param isEncode     是否编码
     * @throws MsOnionException 异常
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) throws MsOnionException {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode, true);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param request      HttpServletRequest实例对象
     * @param response     HttpServletResponse实例对象
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值（数据）
     * @param cookieMaxage Cookie生效周期（单位：秒）
     * @param isEncode     是否编码
     * @param is3DES       是否3DES加密
     * @throws MsOnionException 异常
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieValue, int cookieMaxage, boolean isEncode, boolean is3DES) throws MsOnionException {
        try {
            if (StringUtils.isBlank(cookieName)) {
                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_COOKIE_NAME_ILLEGAL);
            }

            cookieName = cookieName.trim();

            if (StringUtils.isBlank(cookieValue)) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = cookieValue.trim();
                cookieValue = URLEncoder.encode(cookieValue, ENC);  // "utf-8"
            }

            // 3DES 加密
            if (is3DES && StringUtils.isNotBlank(cookieValue)) {
                cookieValue = MsOnionSecurityUtils.encodeForCookie(cookieValue);
            }

            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            // 设置demain有问题，用IP访问客户端没有cookie，360浏览器localhost和符合格式的域名访问也没有cookie
//            if (null != request) { // 设置域名的cookie
//                String domainName = getDomainName(request);
//
////                if (!LOCAL_HOST.equalsIgnoreCase(domainName)) {
////                    cookie.setDomain(domainName);
////                }
//
//                if (MsOnionStringUtils.isNotBlank(domainName)) {
//                    cookie.setDomain(domainName);
//                } else {
//                    // 获取客户端IP
//                    String ip = MsOnionIPUtils.getIp(request);
//                    // 把IP当做 host
//                    cookie.setDomain(ip);
//                }
//            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }



//	/**
//	 * 设置Cookie的值，并使其在指定时间内生效
//	 *
//	 * @param cookieMaxage cookie生效的最大秒数
//	 */
//	private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
//			String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
//		try {
//			if (cookieValue == null) {
//				cookieValue = "";
//			} else {
//				cookieValue = URLEncoder.encode(cookieValue, encodeString);
//			}
//			Cookie cookie = new Cookie(cookieName, cookieValue);
//			if (cookieMaxage > 0)
//				cookie.setMaxAge(cookieMaxage);
//			if (null != request) {// 设置域名的cookie
//				String domainName = getDomainName(request);
//				System.out.println(domainName);
//				if (!"localhost".equals(domainName)) {
//					cookie.setDomain(domainName);
//				}
//			}
//			cookie.setPath("/");
//			response.addCookie(cookie);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

    /**
     * 获取Cookie的域名
     *
     * @param request HttpServletRequest实例对象
     * @return 返回Cookie的域名
     * @throws MsOnionException 异常
     */
    private static String getDomainName(HttpServletRequest request) throws MsOnionException {
        try {
            String domainName = null;

            String serverName = request.getRequestURL().toString();

            if (StringUtils.isBlank(serverName)) {
                return domainName = "";
            }

            serverName = serverName.toLowerCase().trim();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }

            if (StringUtils.isNotBlank(domainName) && domainName.indexOf(":") > 0) {
                String[] ary = domainName.split("\\:");
                domainName = ary[0];
            }
            return domainName;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }
}
