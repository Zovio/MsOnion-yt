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

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @Title: MsOnionIPUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: IP工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午7:09:40
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月26日 下午7:09:40
 * @Modify-version: V2.0.0
 * @Modify-description: 实现相关方法
 */

/**
 * @ClassName: MsOnionIPUtils
 * @Description: IPv4工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午7:09:40
 */
public final class MsOnionIPUtils {

    private MsOnionIPUtils() {
    }

    /**
     * INADDRSZ
     */
    private static final int INADDRSZ = 4;

    /**
     * 默认长度：15
     */
    private static final int DEFAULT_LENGTH_15 = 15;

    // ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")

    /**
     * 127.0.0.1
     */
    private static final String LOCALHOST_127 = "127.0.0.1";

    /**
     * 0:0:0:0:0:0:0:1
     */
    private static final String LOCALHOST_0_1 = "0:0:0:0:0:0:0:1";

    /**
     * 把IP地址转化为字节数组
     *
     * @param ipAddr IP地址
     * @return 返回byte[]
     * @throws MsOnionException 异常
     */
    public static byte[] ipToBytesByInet(String ipAddr) throws MsOnionException {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new MsOnionException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 把IP地址转化为int
     *
     * @param ipAddr IP地址
     * @return int 返回IP地址转化为int
     * @throws MsOnionException 异常
     */
    public static byte[] ipToBytesByReg(String ipAddr) throws MsOnionException {
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } catch (Exception e) {
            throw new MsOnionException(ipAddr + " is invalid IP");
        }

    }

    /**
     * 字节数组转化为IP
     *
     * @param bytes IP的bytes
     * @return int 返回字节数组转化为IP
     */
    public static String bytesToIp(byte[] bytes) {
        return new StringBuffer().append(bytes[0] & 0xFF).append('.').append(
                bytes[1] & 0xFF).append('.').append(bytes[2] & 0xFF)
                .append('.').append(bytes[3] & 0xFF).toString();
    }

    /**
     * 根据位运算把 byte[] -> int
     *
     * @param bytes IP的bytes
     * @return int 返回字节数组转化为IP
     */
    public static int bytesToInt(byte[] bytes) {
        int addr = bytes[3] & 0xFF;
        addr |= ((bytes[2] << 8) & 0xFF00);
        addr |= ((bytes[1] << 16) & 0xFF0000);
        addr |= ((bytes[0] << 24) & 0xFF000000);
        return addr;
    }

    /**
     * 把IP地址转化为int
     *
     * @param ipAddr IP地址
     * @return int 返回字节数组转化为IP
     * @throws MsOnionException 异常
     */
    public static int ipToInt(String ipAddr) throws MsOnionException {
        try {
            return bytesToInt(ipToBytesByInet(ipAddr));
        } catch (Exception e) {
            throw new MsOnionException(ipAddr + " is invalid IP");
        }
    }

    /**
     * ipInt -> byte[]
     *
     * @param ipInt IP的Int类型值
     * @return byte[] bytes
     */
    public static byte[] intToBytes(int ipInt) {
        byte[] ipAddr = new byte[INADDRSZ];
        ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
        ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
        ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
        ipAddr[3] = (byte) (ipInt & 0xFF);
        return ipAddr;
    }

    /**
     * 把int->ip地址
     *
     * @param ipInt ip对应的Int值
     * @return String 返回IP
     */
    public static String intToIp(int ipInt) {
        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
                .append((ipInt >> 16) & 0xff).append('.').append(
                        (ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
                .toString();
    }

    /**
     * 把192.168.1.1/24 转化为int数组范围
     *
     * @param ipAndMask ip和网关
     * @return int[] int数组值
     * @throws MsOnionException 异常
     */
    public static int[] getIPIntScope(String ipAndMask) throws MsOnionException {

        try {
            String[] ipArr = ipAndMask.split("/");
            if (ipArr.length != 2) {
                throw new MsOnionIllegalArgumentException("invalid ipAndMask with: "
                        + ipAndMask);
            }
            int netMask = Integer.valueOf(ipArr[1].trim());
            if (netMask < 0 || netMask > 31) {
                throw new IllegalArgumentException("invalid ipAndMask with: "
                        + ipAndMask);
            }
            int ipInt = ipToInt(ipArr[0]);
            int netIP = ipInt & (0xFFFFFFFF << (32 - netMask));
            int hostScope = (0xFFFFFFFF >>> netMask);
            return new int[]{netIP, netIP + hostScope};
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 把192.168.1.1/24 转化为IP数组范围
     *
     * @param ipAndMask IP和网关
     * @return String[] 字符串数组
     * @throws MsOnionException 异常
     */
    public static String[] getIPAddrScope(String ipAndMask) throws MsOnionException {
        try {
            int[] ipIntArr = getIPIntScope(ipAndMask);
            return new String[]{intToIp(ipIntArr[0]),
                    intToIp(ipIntArr[0])};
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     *
     * @param ipAddr ipAddr
     * @param mask   mask
     * @return int[] int数组
     * @throws MsOnionException 异常
     */
    public static int[] getIPIntScope(String ipAddr, String mask) throws MsOnionException {

        int ipInt;
        int netMaskInt = 0;
        int ipcount = 0;
        try {
            ipInt = ipToInt(ipAddr);
            if (null == mask || "".equals(mask)) {
                return new int[]{ipInt, ipInt};
            }
            netMaskInt = ipToInt(mask);
            ipcount = ipToInt("255.255.255.255") - netMaskInt;
            int netIP = ipInt & netMaskInt;
            int hostScope = netIP + ipcount;
            return new int[]{netIP, hostScope};
        } catch (Exception e) {
            throw new MsOnionException("invalid ip scope express  ip:"
                    + ipAddr + "  mask:" + mask);
        }

    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     *
     * @param ipAddr IP地址
     * @param mask   mask
     * @return String[] 字符串数组
     * @throws MsOnionException 异常
     */
    public static String[] getIPStrScope(String ipAddr, String mask) throws MsOnionException {
        try {
            int[] ipIntArr = getIPIntScope(ipAddr, mask);
            return new String[]{intToIp(ipIntArr[0]),
                    intToIp(ipIntArr[0])};
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

//	/**
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		String ipAddr = "192.168.8.1";
//
//		byte[] bytearr = ipToBytesByInet(ipAddr);
//
//		StringBuffer byteStr = new StringBuffer();
//
//		for (byte b : bytearr) {
//			if (byteStr.length() == 0) {
//				byteStr.append(b);
//			} else {
//				byteStr.append("," + b);
//			}
//		}
//		MsOnionLogger.info(MsOnionIPUtils.class, "IP: " + ipAddr + " ByInet --> byte[]: [ " + byteStr+ " ]");
//
//		bytearr = ipToBytesByReg(ipAddr);
//		byteStr = new StringBuffer();
//
//		for (byte b : bytearr) {
//			if (byteStr.length() == 0) {
//				byteStr.append(b);
//			} else {
//				byteStr.append("," + b);
//			}
//		}
//		MsOnionLogger.info(MsOnionIPUtils.class, "IP: " + ipAddr + " ByReg  --> byte[]: [ " + byteStr
//				+ " ]");
//
//		MsOnionLogger.info(MsOnionIPUtils.class, "byte[]: " + byteStr + " --> IP: "
//				+ bytesToIp(bytearr));
//
//		int ipInt = ipToInt(ipAddr);
//
//		MsOnionLogger.info(MsOnionIPUtils.class, "IP: " + ipAddr + "  --> int: " + ipInt);
//
//		MsOnionLogger.info(MsOnionIPUtils.class, "int: " + ipInt + " --> IP: "
//				+ intToIp(ipInt));
//
//		String ipAndMask = "192.168.1.1/24";
//
//		int[] ipscope = getIPIntScope(ipAndMask);
//		MsOnionLogger.info(MsOnionIPUtils.class, ipAndMask + " --> int地址段：[ " + ipscope[0] + ","
//				+ ipscope[1] + " ]");
//
//		MsOnionLogger.info(MsOnionIPUtils.class, ipAndMask + " --> IP 地址段：[ "
//				+ intToIp(ipscope[0]) + ","
//				+ intToIp(ipscope[1]) + " ]");
//
//		String ipAddr1 = "192.168.1.1", ipMask1 = "255.255.255.0";
//
//		int[] ipscope1 = getIPIntScope(ipAddr1, ipMask1);
//		MsOnionLogger.info(MsOnionIPUtils.class, ipAddr1 + " , " + ipMask1 + "  --> int地址段 ：[ "
//				+ ipscope1[0] + "," + ipscope1[1] + " ]");
//
//		MsOnionLogger.info(MsOnionIPUtils.class, ipAddr1 + " , " + ipMask1 + "  --> IP地址段 ：[ "
//				+ intToIp(ipscope1[0]) + ","
//				+ intToIp(ipscope1[1]) + " ]");
//
//	}


    /**
     * 获取IP地址
     *
     * @param request HttpServletRequest请求实例对象
     * @return 返回IP地址
     * @throws MsOnionException 异常
     */
    public static String getIp(HttpServletRequest request) throws MsOnionException {
        String ip = null;
        try {
            request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();

                if (LOCALHOST_127.equals(ip) || LOCALHOST_0_1.equals(ip)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (StringUtils.isNotBlank(ip) && ip.trim().length() > DEFAULT_LENGTH_15) {  // "***.***.***.***".length() = 15
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }

            if (StringUtils.isBlank(ip)) {
                ip = InetAddress.getLocalHost().getHostAddress();
            }
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        return ip;
    }

}
