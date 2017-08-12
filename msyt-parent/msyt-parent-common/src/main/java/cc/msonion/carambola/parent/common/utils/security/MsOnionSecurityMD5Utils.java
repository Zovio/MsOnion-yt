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

import java.security.MessageDigest;

/**
 * @Title: MsOnionSecurityMD5Utils.java
 * @Package: cc.msonion.carambola.parent.common.utils.security
 * @Description: 安全MD5工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:53:25
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:53:25
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * 安全MD5工具类
 *
 * @ClassName: MsOnionSecurityMD5Utils
 * @Description: 安全MD5工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:53:25
 */
public final class MsOnionSecurityMD5Utils {

    /**
     * 密钥
     */
    private static final String KEY = "1234567890@$@@*()^%#!QWERTYUIOP!@#$%^&*()ASDFGHJKL&@ZXCVBNM<>";

    private MsOnionSecurityMD5Utils() {
    }

    //  16位MD5加密JAVA版 , 32位 MD5

//	private String md5_32;
//    private String md5_16;
//public Md5(String sourceStr){
//    String result = "";
//    try {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(sourceStr.getBytes());
//        byte b[] = md.digest();
//        int i;
//        StringBuffer buf = new StringBuffer("");
//        for (int offset = 0; offset < b.length; offset++) {
//            i = b[offset];
//            if (i < 0)
//                i += 256;
//            if (i < 16)
//                buf.append("0");
//            buf.append(Integer.toHexString(i));
//        }
//        result = buf.toString();
//        md5_32=result;
//        md5_16= buf.toString().substring(8, 24);
//    } catch (NoSuchAlgorithmException e) {
//        System.out.println(e);
//    }
//}
//    public String get16(){
//        return md5_16;
//    }
//    public String get32(){
//        return md5_32;
//    }

    /**
     * 字符串MD5
     *
     * @param data 需要MD5的数据
     * @return 返回MD5之后数据
     * @throws MsOnionException 异常
     * @Title: md5
     * @Description: 字符串MD5
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月17日 上午11:11:58
     */
    public static String md5(String data) throws MsOnionException {
        try {
            byte[] array = MessageDigest.getInstance("MD5").digest(data.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * MD5作为Redis的Key
     *
     * @param data 需要MD5的数据
     * @return 返回MD5之后的数据
     * @throws MsOnionException 异常
     * @Title: md5ForRedisKey
     * @Description: MD5作为Redis的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 上午9:26:41
     */
    public static String md5ForRedisKey(String data) throws MsOnionException {
        return md5(data);
    }

//    /**
//     * MD5作为密码
//     *
//     * @param password 密码（明文）
//     * @return 返回MD5之后的密码
//     * @throws MsOnionException 异常
//     * @Title: md5ForPassword
//     * @Description: MD5作为密码
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月16日 上午9:35:54
//     */
//    public static String md5ForPassword(String password) throws MsOnionException {
//        try {
//            String sha1ForPassword = MsOnionSecuritySHAUtils.sha1ForPassword(password);
//            String md5 = md5(sha1ForPassword);
//            return md5(md5 + KEY + sha1ForPassword);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
}
