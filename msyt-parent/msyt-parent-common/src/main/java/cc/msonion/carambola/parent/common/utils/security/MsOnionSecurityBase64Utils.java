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

package cc.msonion.carambola.parent.common.utils.security;

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Title: MsOnionSecurityBase64Utils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 安全Base64工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:34:53
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:34:53
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。通常是52个大小字母和10个数字，以及+，/两个字符，还有个=用于补缺。
 *
 * @ClassName: MsOnionSecurityBase64Utils
 * @Description: 安全Base64工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:34:53
 */
public final class MsOnionSecurityBase64Utils {

    private MsOnionSecurityBase64Utils() {
    }

    /**
     * LEGAL_CHARS
     */
    private static final char[] LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    /**
     * Base64 加密
     * @param data 需要Base64加密的数据
     * @return 返回Base64加密字符串
     * @throws MsOnionException 异常
     * @Title: encode
     * @Description: 加密
     * @author HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:22:33
     */
    public static String encode(String data) throws MsOnionException {
        if (StringUtils.isBlank(data)) {
            return data;
        }
        return encode(data.trim().getBytes());
    }

    /**
     * Base64 加密
     * @param data 数据
     * @return 返回Base64加密字符串
     * @throws MsOnionException 异常
     * @Title: encode
     * @Description: 加密
     * @author HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:22:33
     */
    public static String encode(byte[] data) throws MsOnionException {
        try {
            int start = 0;
            int len = data.length;
            StringBuffer buf = new StringBuffer(data.length * 3 / 2);

            int end = len - 3;
            int i = start;
            int n = 0;

            while (i <= end) {
                int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8) | (((int) data[i + 2]) & 0x0ff);

                buf.append(LEGAL_CHARS[(d >> 18) & 63]);
                buf.append(LEGAL_CHARS[(d >> 12) & 63]);
                buf.append(LEGAL_CHARS[(d >> 6) & 63]);
                buf.append(LEGAL_CHARS[d & 63]);

                i += 3;

                if (n++ >= 14) {
                    n = 0;
                    buf.append(" ");
                }
            }

            if (i == start + len - 2) {
                int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);

                buf.append(LEGAL_CHARS[(d >> 18) & 63]);
                buf.append(LEGAL_CHARS[(d >> 12) & 63]);
                buf.append(LEGAL_CHARS[(d >> 6) & 63]);
                buf.append("=");
            } else if (i == start + len - 1) {
                int d = (((int) data[i]) & 0x0ff) << 16;

                buf.append(LEGAL_CHARS[(d >> 18) & 63]);
                buf.append(LEGAL_CHARS[(d >> 12) & 63]);
                buf.append("==");
            }

            return buf.toString();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * @param c
     * @return
     * @Title: decode
     * @Description: 解密
     * @author HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:22:48
     */
    private static int decode(char c) {
        if (c >= 'A' && c <= 'Z')
            return ((int) c) - 65;
        else if (c >= 'a' && c <= 'z')
            return ((int) c) - 97 + 26;
        else if (c >= '0' && c <= '9')
            return ((int) c) - 48 + 26 + 26;
        else
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException("unexpected code: " + c);
            }
    }

    /**
     * Base64解密
     *
     * @param data 已经使用Base64加密的字符串
     * @return 返回Base64解密之后字符串
     * @throws MsOnionException 异常
     * @Description: 解密
     * @author HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:23:14
     */
    public static String decode(String data) throws MsOnionException {
        byte[] bytes = decodeForBytes(data);
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        // 原先的，存在乱码问题
//        return new String(bytes);
        try {
            // 统一指定编码为：UTF-8
            return new String(bytes, MsOnionConstants.CHARSET_UTF_8);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * Base64解密
     *
     * @param data 已经使用Base64加密的字符串
     * @return 返回Base64解密之后字符串
     * @throws MsOnionException 异常
     * @Description: 解密
     * @author HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:23:14
     */
    public static byte[] decodeForBytes(String data) throws MsOnionException {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        data = data.trim();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            decode(data, bos);
        } catch (IOException e) {
            throw new MsOnionException(e);
        }
        byte[] decodedBytes = bos.toByteArray();
        try {
            bos.close();
            bos = null;
        } catch (IOException ex) {
            throw new MsOnionException(ex);
        }
        return decodedBytes;
    }

    /**
     * 解密
     *
     * @param s  字符串
     * @param os 输出流
     * @throws IOException 异常
     */
    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;

        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= ' ')
                i++;

            if (i == len)
                break;

            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6) + (decode(s.charAt(i + 3)));

            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=')
                break;
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=')
                break;
            os.write(tri & 255);

            i += 4;
        }
    }

}
