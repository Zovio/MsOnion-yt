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

/**
 * @Title: MsOnionSecuritySHAUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils.security
 * @Description: SHA工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:54:18
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:54:18
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * SHA工具类
 *
 * @ClassName: MsOnionSecuritySHAUtils
 * @Description: SHA工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:54:18
 */
public final class MsOnionSecuritySHAUtils {

//    /**
//     * 密钥
//     */
//    private static final String KEY = "1234567890QWERTYUIOP!@#$%^&*()ASDFGHJKL&@ZXCVBNM<>";
//
//    private MsOnionSecuritySHAUtils() {
//    }
//
//    /**
//     * SHA1
//     * @param decript 需要SHA1的字符串
//     * @return 返回SHA1之后的字符串
//     * @throws MsOnionException 异常
//     */
//    public static String sha1(String decript) throws MsOnionException {
//        try {
//            MessageDigest digest = java.security.MessageDigest
//                    .getInstance("SHA-1");
//            digest.update(decript.getBytes());
//            byte messageDigest[] = digest.digest();
//            // Create Hex String
//            StringBuffer hexString = new StringBuffer();
//            // 字节数组转换为 十六进制 数
//            for (int i = 0; i < messageDigest.length; i++) {
//                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
//                if (shaHex.length() < 2) {
//                    hexString.append(0);
//                }
//                hexString.append(shaHex);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            throw new MsOnionException(e);
//        }
//    }
//
//    public static String sha(String decript) {
//        try {
//            MessageDigest digest = java.security.MessageDigest
//                    .getInstance("SHA");
//            digest.update(decript.getBytes());
//            byte messageDigest[] = digest.digest();
//            // Create Hex String
//            StringBuffer hexString = new StringBuffer();
//            // 字节数组转换为 十六进制 数
//            for (int i = 0; i < messageDigest.length; i++) {
//                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
//                if (shaHex.length() < 2) {
//                    hexString.append(0);
//                }
//                hexString.append(shaHex);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

//	public static String MD5(String input) {
//		try {
//			// 获得MD5摘要算法的 MessageDigest 对象
//			MessageDigest mdInst = MessageDigest.getInstance("MD5");
//			// 使用指定的字节更新摘要
//			mdInst.update(input.getBytes());
//			// 获得密文
//			byte[] md = mdInst.digest();
//			// 把密文转换成十六进制的字符串形式
//			StringBuffer hexString = new StringBuffer();
//			// 字节数组转换为 十六进制 数
//			for (int i = 0; i < md.length; i++) {
//				String shaHex = Integer.toHexString(md[i] & 0xFF);
//				if (shaHex.length() < 2) {
//					hexString.append(0);
//				}
//				hexString.append(shaHex);
//			}
//			return hexString.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

//    /**
//     * 加密
//     *
//     * @param content
//     *            需要加密的内容
//     * @param password
//     *            加密密码
//     * @return
//     */
//    public static byte[] encryptAES(String content, String password) {
//        try {
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            kgen.init(128, new SecureRandom(password.getBytes()));
//            SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
//            byte[] byteContent = content.getBytes("utf-8");
//            cipher.init(Cipher.ENCRYPT_MODE, key); // 初始化
//            byte[] result = cipher.doFinal(byteContent);
//            return result; // 加密
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 解密
//     *
//     * @param content
//     *            待解密内容
//     * @param password
//     *            解密密钥
//     * @return
//     */
//    public static byte[] decryptAES(byte[] content, String password) {
//        try {
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            kgen.init(128, new SecureRandom(password.getBytes()));
//            SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
//            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
//            byte[] result = cipher.doFinal(content);
//            return result; // 加密
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @Title: sha1ForPassword
//     * @Description: 对密码进行SHA1
//     * @author HeroCao herobigdatacao@126.com
//     * @date 2016年8月21日 上午9:14:13
//     *
//     * @param password
//     * @return
//     * @throws NoSuchAlgorithmException
//     */
//    public static String sha1ForPassword(String password) throws NoSuchAlgorithmException {
//        String md5 = MsOnionSecurityMD5Utils.md5(password);
//        md5 = MsOnionSecurityMD5Utils.md5(password + md5);
//        String sha1 = sha1(password + md5);
//        return sha1(sha1 + md5 + KEY);
//    }

}
