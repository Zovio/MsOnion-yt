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
 *  
 */

package cc.msonion.carambola.thirdplatform.erp.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * @Title: SignUtil
 * @Package: cc.msonion.carambola.thirdplatform.erp.common.utils
 * @Description: erp接口 签名工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:16:30
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月06日 20:16:30
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: SignUtil
 * @Description: erp接口 签名工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:16:30
 */
public final class SignUtil {

    private SignUtil() {

    }

    /**
     * 组装接口需要的系统固定变量
     *
     * @param map    参数map
     * @param appkey appkey
     * @param appsecret appsecret
     * @param sid    sid
     * @return TreeMap<String, Object> 参数map
     * @throws MsOnionException the ms onion exception
     */
    public static TreeMap<String, Object> genBaseErpMap(TreeMap<String, Object> map, String appkey, String appsecret,
                                                        String sid) throws MsOnionException  {
        map.put("timestamp", SignUtil.getTimeStamp());
        map.put("appkey", appkey);
        map.put("sid", sid);
        String sign = SignUtil.packData(map, appsecret);
        map.put("sign", sign);
        return map;
    }


    /**
     * 通过请求参数获取接口签名
     *
     * @param map       请求参数
     * @param appsecret appsecret
     * @return 签名字符串
     * @throws MsOnionException the ms onion exception
     */
    private static String packData(TreeMap<String, Object> map, String appsecret) throws MsOnionException {
        Map<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.putAll(map);
        Iterator<String> iter = treeMap.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        // 格式化2位数字，不足前面补0
        DecimalFormat df1 = new DecimalFormat("00");
        // 格式化4位数字，不足前面补0
        DecimalFormat df2 = new DecimalFormat("0000");
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String val = map.get(key).toString();
            if ("sign".equals(key)) {
                continue;
            }
            sb.append(df1.format(key.length())).append("-").append(key).append(":").
                    append(df2.format(val.length())).append("-").append(val);
            if (iter.hasNext()) {
                sb.append(";");
            }
        }
        String str = sb.append(appsecret).toString();
        // 进行MD5加密
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            final int maxLength = 16;
            return new BigInteger(1, md5.digest()).toString(maxLength);
        } catch (NoSuchAlgorithmException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取接口所需要的10位时间戳
     *
     * @return 时间字符串
     */
    public static String getTimeStamp() {
        final int begin = 0;
        final int end = 10;
        return String.valueOf(System.currentTimeMillis()).substring(begin, end);
    }


}
