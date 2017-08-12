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
 * @Title: ItemModifyLocationUtils
 * @Package: cc.msonion.carambola.collector.common.utils
 * @Description: 商品日志修改位置
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月26日 10:22:15
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月26日 10:22:15
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;

import java.util.*;

/**
 * @ClassName: ItemModifyLocationUtils
 * @Description: 商品日志修改位置
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月26日 10:22:15
 */
public final class ItemModifyLocationUtils {


    private ItemModifyLocationUtils() {

    }

    /**
     * 定义Map
     */
    private static Map<Short, String> map = new LinkedHashMap<>();


    /**
     * 定义 属性Map
     */
    private static Map<String, Short> attrMap = new LinkedHashMap<>();


    /**
     * @Fields TYPE_ITEM_CATEGORY : 修改分类，
     * 新增商品页面的修改分类功能，修改前后都需显示一二三级，
     * 以美妆-面部护理-化妆水方式显示
     */
    public static final short TYPE_ITEM_CATEGORY = 1;
    /**
     * @Fields TYPE_ITEM_WAREHOUSE : 修改仓库，新增商品页面的修改仓库功能
     */
    public static final short TYPE_ITEM_WAREHOUSE = 2;

    /**
     * @Fields TYPE_ITEM_NO : 货号，修改了分类的一二级跟仓库都会涉及到货号的修改，
     * 所以修改分类跟仓库时如果影响到货号也需多一条货号修改的操作日志。
     */
    public static final short TYPE_ITEM_NO = 3;

    /**
     * @Fields TYPE_ITEM_MARKETPRICE : 市场价，设置价格页面的市场价
     */
    public static final short TYPE_ITEM_MARKETPRICE = 4;

    /**
     * @Fields TYPE_ITEM_SELLINGPRICE : 售价，设置价格页面的售价
     */
    public static final short TYPE_ITEM_SELLINGPRICE = 5;

    /**
     * @Fields TYPE_ITEM_MARKETPRICE : 供货价，设置价格页面的供货价
     */
    public static final short TYPE_ITEM_SUPPLYPRICE = 6;

    /**
     * @Fields TYPE_ITEM_CNNAME : 中文品名，设置价格页面的供货价
     */
    public static final short TYPE_ITEM_CNNAME = 7;

    /**
     * @Fields TYPE_ITEM_BARCODE : 条形码，仓库管理中心的条形码
     */
    public static final short TYPE_ITEM_BARCODE = 8;

    /**
     * @Fields TYPE_ITEM_REALTIMEINVENTORY : 实时库存，仓库管理中心的实时库存
     */
    public static final short TYPE_ITEM_REALTIMEINVENTORY = 9;

    /**
     * @Fields TYPE_ITEM_SAFEINVENTORY : 安全库存，仓库管理中心的安全库存
     */
    public static final short TYPE_ITEM_SAFEINVENTORY = 10;

    /**
     * @Fields TYPE_ITEM_DELIVERYCOEFFICIENT : 发货系数，仓库管理中心的发货系数
     */
    public static final short TYPE_ITEM_DELIVERYCOEFFICIENT = 11;

    /**
     * @Fields TYPE_ITEM_ISKEYORDER : 是否一键下单，仓库管理中心的是否一键下单
     */
    public static final short TYPE_ITEM_ISKEYORDER = 12;

    /**
     * @Fields TYPE_ITEM_ISFREESHIPPING : 是否免邮，仓库管理中心的是否免邮
     */
    public static final short TYPE_ITEM_ISFREESHIPPING = 13;

    /**
     * 是否一键下单
     */
    public static final String ATTR_ITEM_ISKEYORDER = "isKeyOrder";
    /**
     * 是否免邮
     */
    public static final String ATTR_ITEM_ISFREESHIPPING = "isFreeShipping";


    /**
     * 实时库存
     */
    public static final String ATTR_ITEM_REALTIMEINVENTORY = "realTimeInventory";

    /**
     * 安全库存
     */
    public static final String ATTR_ITEM_SAFEINVENTORY = "safeInventory";

    /**
     * 发货系数
     */
    public static final String ATTR_ITEM_DELIVERYCOEFFICIENT = "deliveryCoefficient";

    static {
        map = new LinkedHashMap<>();
        map.put(TYPE_ITEM_CATEGORY, "商品分类");
        map.put(TYPE_ITEM_WAREHOUSE, "仓库");
        map.put(TYPE_ITEM_NO, "货号");
        map.put(TYPE_ITEM_MARKETPRICE, "市场价");
        map.put(TYPE_ITEM_SELLINGPRICE, "售价");
        map.put(TYPE_ITEM_SUPPLYPRICE, "供货价");
        map.put(TYPE_ITEM_CNNAME, "中文品名");
        map.put(TYPE_ITEM_BARCODE, "商品条形码");
        map.put(TYPE_ITEM_REALTIMEINVENTORY, "实时库存");
        map.put(TYPE_ITEM_SAFEINVENTORY, "安全库存");
        map.put(TYPE_ITEM_DELIVERYCOEFFICIENT, "发货系数");
        map.put(TYPE_ITEM_ISKEYORDER, "是否一键下单");
        map.put(TYPE_ITEM_ISFREESHIPPING, "是否免邮");


        attrMap.put(ATTR_ITEM_REALTIMEINVENTORY, TYPE_ITEM_REALTIMEINVENTORY);
        attrMap.put(ATTR_ITEM_SAFEINVENTORY, TYPE_ITEM_SAFEINVENTORY);
        attrMap.put(ATTR_ITEM_DELIVERYCOEFFICIENT, TYPE_ITEM_DELIVERYCOEFFICIENT);
        attrMap.put(ATTR_ITEM_ISKEYORDER, TYPE_ITEM_ISKEYORDER);
        attrMap.put(ATTR_ITEM_ISFREESHIPPING, TYPE_ITEM_ISFREESHIPPING);
    }

    /**
     * 得到描述
     *
     * @param id 传入id
     * @return String
     */
    public static String getDescription(Short id) {
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
    public static String toJson(Short... ids) {
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
     * @return attrMap
     */
    public static Map getAttrMap() {
        return attrMap;
    }


    /**
     * @param ids 传入ids
     * @return Map
     */
    public static Map getMap(Short... ids) {
        Map<Short, String> newMap = new LinkedHashMap<Short, String>(map);
        List<Short> keys = Arrays.asList(ids);
        newMap.keySet().retainAll(keys);

        return newMap;
    }

    /**
     * 字典值变成中文描叙
     * @param key 字典key
     * @param dict 字典值
     * @return 描叙
     */
    public static String getDescriptionByDict(String key, Object dict) {
        if (null == dict) {
            return "";
        }
        return Optional.ofNullable(dict.toString()).map(s -> {
            if (key.equals(ATTR_ITEM_ISKEYORDER) || key.equals(ATTR_ITEM_ISFREESHIPPING)) {
                if (MsOnionConstants.YES.equals(dict.toString())) {
                    return "是";
                } else if (MsOnionConstants.NO.equals(dict.toString())) {
                    return "否";
                }
            }
            return dict.toString();
        }).orElse("");
    }
}
