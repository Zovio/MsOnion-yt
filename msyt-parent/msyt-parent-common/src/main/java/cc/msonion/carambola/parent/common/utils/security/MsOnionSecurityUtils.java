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

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @Title: MsOnionSecurityUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 安全工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:32:04
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:32:04
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：所有安全相关方法
 */

/**
 * @ClassName: MsOnionSecurityUtils
 * @Description: 安全工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:32:04
 */
public final class MsOnionSecurityUtils extends DigestUtils {

    /**
     * 密钥
     */
    private static final String KEY = "iOzMR6t/1FMXZuYUQPPhRPJ6j52 8JTZ4sThczk9cpG6NcqVfpPePvHGKxXKFKF +WExT2dQX7R12Vkm8J7+4hDN46Ef38Bxr13EOkv3svz6zhd/dwl2H0UESGWt /Kj46a2j/KuJfuq nTAuwBIn/byubYM0hcZyVAeoJ W//Gjt3OPeVq+M0pkCr8HAi8Vw6zYpd3hv12C0 M0M/pbvqHjRMfHuBmFt";

    /**
     * 干扰1
     */
    private static final String INTERFERENCE_3DES_1 = "gOGYAW3PUoQchiKwjXYCItrcp8 FXXRkfUQaVYWKG81THvTZwnlqXfVj ZQk5H3b84gi+88oXi2a1USF3MNwlb3YzNn261FZlQql7cPrI3i+SzNpcinR3 S0vQWY4dZxwzDfgItPOJcveLF3Q0Eed2pEnuLeQh9lSTvhs3n31n2Pw4EbbZ t9Lw8pXkRmhnGKx1WHury0a1";

    /**
     * 干扰2
     */
    private static final String INTERFERENCE_3DES_2 = "Qcujboo4YYGFUqFKXENNru69SuVe K2/eC4hI/kQd58EaKkMMeZcOQnm xWNHhBzW9IJxFhimVOs51w83Vxj52vTnEj ju45h7zSz/tvhpqrwHX1 0I4pf0Sv01TAXsVAYjR61 NTiIoJnCiy3Gl+G9 RId+nh+MfEnR3/h1OOgr0NXnBWsi 8T0lm82U5JKS2e1eKN63Gxsd";

    /**
     * 干扰3
     */
    private static final String INTERFERENCE_3DES_3 = "MXx4cIFArnSN87vawleeEyWrhIgPNmCQLcr 0chWf8DjetuCHICq6eIv9zaM58h5N8GyPfsOeY0VNafzFi5EkPaXm3v61FKm JoNUY4exXdZVuetjy9uVpt5OHHOn7F1CttCOZWGOJeyMVTy7UCjjG3j9CHTg /x7GWcEVWpifQ LWgICvphMx6Ow cwi3FQOPf8ucHSHV8YXTZN";

    /**
     * 干扰4
     */
    private static final String INTERFERENCE_3DES_4 = "pMEa1S3lp1Zn0lNmqOQe2vrG VgVwSuqPe3lvp891BwRWTXK+xAs0UvEcbSjqDCay+83VejY8dqMSkUL3HUHwA apcl87fZ4tFYqH+qxe1FvjvZsh2whK8qhhHPzzWujCFTJ/DZ/LUwz2AjzGhS tIX28c55m0VVb9Fs1J9A Uv+veK7osxBfgOQLoB80kK8ztjnnML2w";

    /**
     * 干扰第一阶段长度
     */
    private static final int INTERFERENCE_3DES_1_LENGTH = 8;
    /**
     * 干扰第二阶段长度
     */
    private static final int INTERFERENCE_3DES_2_LENGTH = 58;

    /**
     * 空格
     */
    private static final String BLANK = " ";

    /**
     * + 号
     */
    private static final String PLUS = "+";

    /**
     * 空格替换的目标
     */
    private static final String BLANK_REPLACE_TARGET = "_msyc_";

    /**
     * +替换的目标
     */
    private static final String PLUS_REPLACE_TARGET = "_yt_";

    /**
     * 4
     */
    private static final String FOUR = "4";

    /**
     * 7
     */
    private static final String SEVEN = "7";

    /**
     * 4替换的目标
     */
    private static final String FOUR_REPLACE_TARGET = "_Four_";

    /**
     * 7替换的目标
     */
    private static final String SEVEN_REPLACE_TARGET = "_Seven_";

    private MsOnionSecurityUtils() {

    }

    /**
     * MD5作为密码
     *
     * @param password 密码（明文）
     * @return 返回MD5之后的密码
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Description: MD5作为密码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 上午11:01:09
     */
    public static String md5ForPassword(String password) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            String shaHex = shaHex(password + KEY);
            String sha256Hex = sha256Hex(shaHex + KEY);
            String sha384Hex = sha384Hex(sha256Hex + shaHex);
            String sha512Hex = sha512Hex(shaHex + sha256Hex + sha384Hex);
            String md5Hex = md5Hex(sha512Hex + shaHex + sha384Hex);
            return md5Hex(sha256Hex + sha384Hex + md5Hex + shaHex + sha512Hex).toUpperCase();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取Token
     *
     * @param username 用户名
     * @param password 加密的密码
     * @return 返回Token
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: getToken
     * @Description: 获取Token
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:14:06
     */
    public static String getToken(String username, String password) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            String md5 = md5Hex(username + KEY + password).toUpperCase();
            String sha256Hex = sha256Hex(username + md5 + KEY);
            String sha384Hex = sha384Hex(KEY + sha256Hex + md5);
            String sha512Hex = sha512Hex(sha384Hex + sha256Hex + md5);
            return md5Hex(sha512Hex + md5 + sha384Hex + sha256Hex).toUpperCase();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * Cookie数据加密
     *
     * @param plainText 普通文本
     * @return 返回加密之后Cookie数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: encodeForCookie
     * @Description: Cookie数据加密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:13:12
     */
    public static String encodeForCookie(String plainText) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 加强复杂加密
            return encodeByInterferenceForStrengthen(plainText);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * Cookie数据解密
     *
     * @param encryptText 加密的文本
     * @return 返回解密的数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: decodeForCookie
     * @Description: Cookie数据解密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:13:49
     */
    public static String decodeForCookie(String encryptText) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 加强复杂解密
            return decodeByInterferenceForStrengthen(encryptText);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 通过干扰加强复杂度的加密
     *
     * @param plainText 普通文本
     * @return 返回加密数据
     * @throws MsOnionException 异常
     * @Title: encodeByInterferenceForStrengthen
     * @Description: 通过干扰加强复杂度的加密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月27日 下午4:29:33
     */
    private static String encodeByInterferenceForStrengthen(String plainText) throws MsOnionException {
        try {
            String encode = MsOnionSecurity3DESUtils.encode(plainText);

            // 插入干扰数据: 1
            if (StringUtils.isNotBlank(encode) && encode.length() > INTERFERENCE_3DES_1_LENGTH) {
                String subStr1 = encode.substring(0, INTERFERENCE_3DES_1_LENGTH);
                String subStr2 = encode.substring(INTERFERENCE_3DES_1_LENGTH, encode.length());
                StringBuffer sb = new StringBuffer();
                sb.append(subStr1);
                sb.append(INTERFERENCE_3DES_1);
                sb.append(subStr2);
                sb.append(INTERFERENCE_3DES_2);
                encode = MsOnionSecurity3DESUtils.encode(sb.toString());
            }

            // 插入干扰数据: 2
            if (StringUtils.isNotBlank(encode) && encode.length() > INTERFERENCE_3DES_2_LENGTH) {
                String subStr1 = encode.substring(0, INTERFERENCE_3DES_2_LENGTH);
                String subStr2 = encode.substring(INTERFERENCE_3DES_2_LENGTH, encode.length());
                StringBuffer sb = new StringBuffer();
                sb.append(subStr1);
                sb.append(INTERFERENCE_3DES_3);
                sb.append(subStr2);
                sb.append(INTERFERENCE_3DES_4);
                encode = MsOnionSecurity3DESUtils.encode(sb.toString());
            }
            return encode;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 通过干扰加强复杂度的解密
     *
     * @param encryptText 加密文本
     * @return 返回解密之后数据
     * @throws MsOnionException 异常
     * @Title: decodeByInterferenceForStrengthen
     * @Description: 通过干扰加强复杂度的解密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月27日 下午4:32:35
     */
    private static String decodeByInterferenceForStrengthen(String encryptText) throws MsOnionException {
        try {
            // 3DES 解密
            String decode = MsOnionSecurity3DESUtils.decode(encryptText);

            // 插入干扰数据: 2
            if (StringUtils.isNotBlank(decode) && decode.length() > INTERFERENCE_3DES_2_LENGTH) {
                String subStr1 = decode.substring(0, INTERFERENCE_3DES_2_LENGTH);
                String subStr2 = decode.substring(INTERFERENCE_3DES_2_LENGTH + INTERFERENCE_3DES_3.length(),
                        decode.length() - INTERFERENCE_3DES_4.length());
                StringBuffer sb = new StringBuffer();
                sb.append(subStr1);
                sb.append(subStr2);
                decode = MsOnionSecurity3DESUtils.decode(sb.toString());
            }

            // 插入干扰数据: 1
            if (StringUtils.isNotBlank(decode) && decode.length() > INTERFERENCE_3DES_1_LENGTH) {
                String subStr1 = decode.substring(0, INTERFERENCE_3DES_1_LENGTH);
                String subStr2 = decode.substring(INTERFERENCE_3DES_1_LENGTH + INTERFERENCE_3DES_1.length(),
                        decode.length() - INTERFERENCE_3DES_2.length());
                StringBuffer sb = new StringBuffer();
                sb.append(subStr1);
                sb.append(subStr2);
                decode = MsOnionSecurity3DESUtils.decode(sb.toString());
            }

            return decode;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 解密Redis中的数据
     *
     * @param encryptText 加密Text
     * @return 返回解密之后数据
     * @throws MsOnionException 异常
     * @Description: 解密Redis中的数据
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:16:19
     */
    public static String decodeForRedis(String encryptText) throws MsOnionException {
        try {
            if (StringUtils.isBlank(encryptText)) {
                return null;
            }
            // 高强度、复杂解密
            return decodeByInterferenceForStrengthen(encryptText);
            // 直接明文
            // return encryptText;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 加密Redis中的数据
     *
     * @param plainText 明文Text
     * @return 返回加密的数据
     * @throws MsOnionException 异常
     * @Description: 加密Redis中的数据
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:17:53
     */
    public static String encodeForRedis(String plainText) throws MsOnionException {
        try {
            if (StringUtils.isBlank(plainText)) {
                return null;
            }
            // 高强度、复杂加密
            return encodeByInterferenceForStrengthen(plainText);
            // 直接明文
            // return plainText;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * MD5作为Redis的Key
     *
     * @param data 需要MD5的字符串
     * @return 返回MD5之后字符串
     * @throws MsOnionException 异常
     * @Title: md5ForRedisKey
     * @Description: MD5作为Redis的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 上午9:26:41
     */
    public static String md5ForRedisKey(String data) throws MsOnionException {
        try {
            String md5 = MsOnionSecurityMD5Utils.md5ForRedisKey(data + KEY).toUpperCase();
            String sha384Hex = sha384Hex(md5 + KEY);
            String sha512Hex = sha512Hex(sha384Hex + md5);
            return MsOnionSecurityMD5Utils.md5ForRedisKey(sha512Hex + sha384Hex).toUpperCase();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 对用户Session进行加密
     *
     * @param plainText 普通Text
     * @return 返回加密数据
     * @throws MsOnionException 异常
     * @Description: 对用户Session进行加密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月2日 下午9:15:02
     */
    public static String encodeUserSession(String plainText) throws MsOnionException {
        try {
            if (StringUtils.isBlank(plainText)) {
                return null;
            }
            return encodeByInterferenceForStrengthen(plainText);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 对用户Session进行解密
     *
     * @param encryptJson 加密的Json数据
     * @return 返回对用户Session进行解密的数据
     * @throws MsOnionException 异常
     * @Description: 对用户Session进行解密
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月2日 下午9:18:36
     */
    public static String decodeUserSession(String encryptJson) throws MsOnionException {
        try {
            return decodeByInterferenceForStrengthen(encryptJson);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }


    /**
     * 找回密码 token加密
     *
     * @param plainText 普通Text
     * @return 返回加密数据
     * @throws MsOnionException 异常
     */
    public static String encodeEmailToken(String plainText) throws MsOnionException {
        try {
            return base64EncodeWithReplace(plainText);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 找回密码 token解密
     *
     * @param encryptJson 加密的Json数据
     * @return 返回对用户Session进行解密的数据
     * @throws MsOnionException 异常
     */
    public static String decodeEmailToken(String encryptJson) throws MsOnionException {
        try {
            return base64DecodeWithReplace(encryptJson);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    //////////////////////////// Base64，特殊处理（替换空格、+），Begin ///////////////////////

    /**
     * Base64加密，采取替换空格、+号 方式，因为URL对空格和+ 转义
     *
     * @param plainText 普通Text
     * @return 返回加密数据
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月12日 下午9:15:02
     */
    public static String base64EncodeWithReplace(String plainText) throws MsOnionException {
        try {
            if (StringUtils.isBlank(plainText)) {
                return null;
            }
            return MsOnionSecurityBase64Utils.encode(plainText)
                    .replace(BLANK, BLANK_REPLACE_TARGET)
                    .replace(PLUS, PLUS_REPLACE_TARGET)
                    .replace(FOUR, FOUR_REPLACE_TARGET)
                    .replace(SEVEN, SEVEN_REPLACE_TARGET);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * Base64解密，采取替换替换空格、+号 方式
     *
     * @param encrypt 加密的Base64数据，采取替换空格、+号 方式
     * @return 返回解密的数据
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月12日 下午9:18:36
     */
    public static String base64DecodeWithReplace(String encrypt) throws MsOnionException {
        if (StringUtils.isBlank(encrypt)) {
            return null;
        }
        try {
            encrypt = encrypt.trim()
                    .replace(BLANK_REPLACE_TARGET, BLANK)
                    .replace(PLUS_REPLACE_TARGET, PLUS)
                    .replace(FOUR_REPLACE_TARGET, FOUR)
                    .replace(SEVEN_REPLACE_TARGET, SEVEN);
            return MsOnionSecurityBase64Utils.decode(encrypt);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    //////////////////////////// Base64，特殊处理，End ///////////////////////

}
