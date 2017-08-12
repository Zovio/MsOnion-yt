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

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * @Title: MsOnionSecurity3DESUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 安全3DES工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:33:19
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:33:19
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * @ClassName: MsOnionSecurity3DESUtils
 * @Description: 安全3DES工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:33:19
 */
public final class MsOnionSecurity3DESUtils {

    private MsOnionSecurity3DESUtils() {
    }

    /**
     * 密钥
     */
    private static final String SECRET_KEY = "MGAC+ZDdTn/h9O2Nu8JPf9F55VQke1hzqrIhAD3xbGY4jim SQf9AX1HWiuP/7MxVf+ZIrDLxeFaNN6IOI6z5YMiLqAlWBM6 3tVYWesYMVD9 RaXyPRTHYEqpng1W2iqewTvMMQJxSqMJoCJWIKeYtdUE/0A6C76F58 +qcUAnw8oTrTcwUhXOYlYUlZLeI4 +UxzeEYrSb/OgUVF+wDXbicUtjV/Rrwlj0O pa0y2QW2 Of81YV 8uyvm+wPUFJ+8IlV+";

    // 必须8位，否则报错 : Wrong IV length: must be 8 bytes long
    /**
     * 向量
     */
    private static final String IV = "@msyc.cc";

    /**
     * 加解密统一使用的编码方式
     */
    private static final String ENCODING = "utf-8";

    /**
     * 默认字符串编码集
     */
    private static final String DEFAULT_CHARSET = "UTF8";

    /**
     * 默认长度
     */
    private static final int DEFAULT_LENGTH = 8;

    /**
     * @param plainText 普通文本
     * @return 返回3DES加密字符串
     * @throws MsOnionException MsOnionException异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:26:19
     */
    public static String encode(String plainText) throws MsOnionException {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(SECRET_KEY.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
            return MsOnionSecurityBase64Utils.encode(encryptData);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return 返回3DES解密字符串
     * @throws MsOnionException MsOnionException异常
     */
    public static String decode(String encryptText) throws MsOnionException {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(SECRET_KEY.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            byte[] decryptData = cipher.doFinal(MsOnionSecurityBase64Utils.decodeForBytes(encryptText));

            return new String(decryptData, ENCODING);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 填充
     *
     * @param str 字符串
     * @return 返回填充之后字符串
     * @throws MsOnionException 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午1:25:31
     */
    public static String padding(String str) throws MsOnionException {
        byte[] oldByteArray;
        try {
            oldByteArray = str.getBytes(DEFAULT_CHARSET);
            int numberToPad = DEFAULT_LENGTH - oldByteArray.length % DEFAULT_LENGTH;
            byte[] newByteArray = new byte[oldByteArray.length + numberToPad];
            System.arraycopy(oldByteArray, 0, newByteArray, 0,
                    oldByteArray.length);
            for (int i = oldByteArray.length; i < newByteArray.length; ++i) {
                newByteArray[i] = 0;
            }
            return new String(newByteArray, DEFAULT_CHARSET);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

}
