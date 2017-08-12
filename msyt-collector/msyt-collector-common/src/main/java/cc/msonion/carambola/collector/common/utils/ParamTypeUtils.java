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

package cc.msonion.carambola.collector.common.utils;

/**
 * @Title: ParamTypeUtils.java
 * @Package: cc.msonion.carambola.collector.common.utils
 * @Description: 参数类型常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/3/31 10:24
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017/3/31 10:24
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：条形码参数类型
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ParamTypeUtils
 * @Description: 参数类型常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/3/31 10:24
 */
public final class ParamTypeUtils {

    private ParamTypeUtils() {
    }

    /**
     * 定义Map
     */
    private static Map<Integer, String> map = new LinkedHashMap<>();

    /**
     * 商品条形码
     */
    public static final int TYPE_ITEM_BARCODE = 1;

    /**
     * 品牌中文名称
     */
    public static final int TYPE_BRAND_CN_NAME = 2;

    /**
     * 品牌英文名称
     */
    public static final int TYPE_BRAND_EN_NAME = 3;

    /**
     * 类目编码
     */
    public static final int TYPE_CATEGORY_CODE = 4;

    /**
     * 属性名称
     */
    public static final int TYPE_ATTRIBUTE_NAME = 5;

    /**
     * 属性组名称
     */
    public static final int TYPE_ATTRIBUTE_GROUP_NAME = 6;

    /**
     * 产地编码
     */
    public static final int TYPE_ORIGIN_CODE = 7;

    /**
     * 产地中文名称
     */
    public static final int TYPE_ORIGIN_CN_NAME = 8;

    /**
     * 产地英文名称
     */
    public static final int TYPE_ORIGIN_EN_NAME = 9;

    /**
     * 品牌名称
     */
    public static final int TYPE_BRAND_NAME = 10;

    static {
        map = new LinkedHashMap<Integer, String>();
        map.put(TYPE_ITEM_BARCODE, "商品条形码");
        map.put(TYPE_BRAND_CN_NAME, "品牌中文名称");
        map.put(TYPE_BRAND_EN_NAME, "品牌英文名称");
        map.put(TYPE_CATEGORY_CODE, "类目编码");
        map.put(TYPE_ATTRIBUTE_NAME, "属性名称");
        map.put(TYPE_ATTRIBUTE_GROUP_NAME, "属性组名称");
        map.put(TYPE_ORIGIN_CODE, "产地编码");
        map.put(TYPE_ORIGIN_CN_NAME, "产地中文名称");
        map.put(TYPE_ORIGIN_EN_NAME, "产地英文名称");
        map.put(TYPE_BRAND_NAME, "品牌名称");
    }

    /**
     * 得到描述
     *
     * @param id 传入id
     * @return String
     */
    public static String getDescription(Integer id) {
        return map.get(id);
    }

    /**
     * @return String
     */
    public static String toJson() {
        try {
            return MsOnionJsonUtils.objectToJson(map);
        } catch (MsOnionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param ids 传入ids
     * @return String
     */
    public static String toJson(Integer... ids) {
        Map<Integer, String> newMap = getMap(ids);
        try {
            return MsOnionJsonUtils.objectToJson(newMap);
        } catch (MsOnionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return Map
     */
    public static Map getMap() {
        return map;
    }

    /**
     * @param ids 传入ids
     * @return Map
     */
    public static Map getMap(Integer... ids) {
        Map<Integer, String> newMap = new LinkedHashMap<>(map);
        List<Integer> keys = Arrays.asList(ids);
        newMap.keySet().retainAll(keys);

        return newMap;
    }
}
