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
package cc.msonion.carambola.commons.common.utils;

/**
 * @Title: MsOnionSaleStatusUtils.java
 * @Package: cc.msonion.carambola.commons.common.utils
 * @Description: 销售状态工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/20 10:02
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/20 10:02
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：销售状态，（1）在线-常规（2）在线-季节（3）在线-售完即止（4）在线-促销（5）今日补货（6）异常库存（7）暂时下架（8）停止售卖
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsOnionSaleStatusUtils
 * @Description: 销售状态工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/20 10:02
 */
public final class MsOnionSaleStatusUtils {
    private MsOnionSaleStatusUtils() {
    }

    /**
     * 定义Map
     */
    private static Map<Long, String> map = new LinkedHashMap<>();

    /**
     * 在线-常规
     */
    public static final Long ONLINE_ROUTINE = 1L;

    /**
     * 在线-季节
     */
    public static final Long ONLINE_SEASON = 2L;

    /**
     * 在线-售完即止
     */
    public static final Long ONLINE_SELL_OUT = 3L;

    /**
     * 在线-促销
     */
    public static final Long ONLINE_PROMOTION = 4L;

    /**
     * 今日补货
     */
    public static final Long TODAY_REPLENISHMENT = 5L;

    /**
     * 异常库存
     */
    public static final Long ABNORMAL_INVENTORY = 6L;

    /**
     * 暂时下架
     */
    public static final Long TEMPORARY_SHELF = 7L;

    /**
     * 停止售卖
     */
    public static final Long STOP_SELL = 8L;

    static {
        map = new LinkedHashMap<>();
        map.put(ONLINE_ROUTINE, "在线-常规");
        map.put(ONLINE_SEASON, "在线-季节");
        map.put(ONLINE_SELL_OUT, "在线-售完即止");
        map.put(ONLINE_PROMOTION, "在线-促销");
        map.put(TODAY_REPLENISHMENT, "今日补货");
        map.put(ABNORMAL_INVENTORY, "异常库存");
        map.put(TEMPORARY_SHELF, "暂时下架");
        map.put(STOP_SELL, "停止售卖");
    }

    /**
     * 得到描述
     *
     * @param id 传入id
     * @return String
     */
    public static String getDescription(Long id) {
        return map.get(id);
    }

    /**
     * toJson
     *
     * @return String
     * @throws MsOnionException MsOnion异常类
     */
    public static String toJson() throws MsOnionException {
        try {
            return MsOnionJsonUtils.objectToJson(map);
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 得到Map
     *
     * @return Map
     */
    public static Map getMap() {
        return map;
    }

    /**
     * 得到Map
     *
     * @param ids 传入ids
     * @return Map
     */
    public static Map getMap(Long... ids) {
        Map<Long, String> newMap = new LinkedHashMap<>(map);
        List<Long> keys = Arrays.asList(ids);
        newMap.keySet().retainAll(keys);

        return newMap;
    }

    /**
     * toJson
     *
     * @param ids 传入ids
     * @return String
     * @throws MsOnionException MsOnion异常类
     */
    public static String toJson(Long... ids) throws MsOnionException {
        Map<Long, String> newMap = getMap(ids);

        try {
            return MsOnionJsonUtils.objectToJson(newMap);
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }
}
