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
package cc.msonion.carambola.collector.ext.utils;

/**
 * @Title: NewItemFillData
 * @Package: cc.msonion.carambola.manager.common.transform
 * @Description: 新增商品 Excel数据模型转换为pojo对象模型
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 20:44:00
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月23日 20:44:00
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemBidding;
import cc.msonion.carambola.collector.ext.view.ExcelItemView;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: NewItemFillData
 * @Description: 新增商品 Excel数据模型转换为pojo对象模型
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 20:44:00
 */
public final class ExcelItemFillData {


    private ExcelItemFillData() {

    }
    /**
     * NewItemView 转换为Map
     * Map:{"collectorItem":collectorItem,"collectorItemBidding":ItemBidding}
     * @Title getItmeMap
     * @Description NewItemView 转换为Map
     * @param itemView api版本
     * @param operateIdx 操作这ID
     * @throws MsOnionException   the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月23日 20:48:23
     */
    public static Map<String, Object> getItmeMap(ExcelItemView itemView, Long operateIdx) throws MsOnionException {
        Date curDate = new Date();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        CollectorItem collectorItem = new CollectorItem();
        CollectorItemBidding collectorItemBidding = new CollectorItemBidding();
        collectorItem.setIdx(itemView.getItemIdx());
        collectorItem.setBarcode(itemView.getBarcode());
        collectorItem.setCnName(itemView.getCnName());
        collectorItem.setEnName(itemView.getEnName());
        collectorItem.setBrandIdx(itemView.getBrandIdx());
        collectorItem.setOriginIdx(itemView.getOriginIdx());
        collectorItem.setBatch(itemView.getBatch());
        collectorItem.setWeight(itemView.getWeight());
        collectorItem.setItemStateIdx(itemView.getItemStateIdx());
        collectorItem.setCreateByMemberIdx(operateIdx);
        collectorItem.setUpdateByMemberIdx(operateIdx);
        collectorItem.setCreateTime(curDate);
        collectorItem.setUpdateTime(curDate);
        collectorItem.setCollectionRemark(itemView.getCollectionRemark());
        if (itemView.getAmazonPrice() != null && itemView.getAmazonPrice() > 0) {
            collectorItemBidding.setAmazonPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getAmazonPrice())));
        }
        if (itemView.getTmallPrice() != null && itemView.getTmallPrice() > 0) {
            collectorItemBidding.setTmallPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getTmallPrice())));
        }
        if (itemView.getJdPrice() != null && itemView.getJdPrice() > 0) {
            collectorItemBidding.setJdPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getJdPrice())));
        }
        if (itemView.getRedPrice() != null && itemView.getRedPrice() > 0) {
            collectorItemBidding.setRedPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getRedPrice())));
        }
        if (itemView.getOriginCountryPrice() != null && itemView.getOriginCountryPrice() > 0) {
            collectorItemBidding.setOriginCountryPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getOriginCountryPrice())));
        }
        if (itemView.getDomesticPrice() != null && itemView.getDomesticPrice() > 0) {
            collectorItemBidding.setDomesticPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getDomesticPrice())));
        }
        if (itemView.getAbroadPrice() != null && itemView.getAbroadPrice() > 0) {
            collectorItemBidding.setAbroadPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getAbroadPrice())));
        }
        if (itemView.getKoalaPrice() != null && itemView.getKoalaPrice() > 0) {
            collectorItemBidding.setKoalaPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getKoalaPrice())));
        }
        if (itemView.getJumeiPrice() != null && itemView.getJumeiPrice() > 0) {
            collectorItemBidding.setJumeiPrice(MsOnionNumberUtils.toIntForPrice(String.valueOf(itemView.getJumeiPrice())));
        }

        collectorItemBidding.setCreateByMemberIdx(operateIdx);
        collectorItemBidding.setUpdateByMemberIdx(operateIdx);
        collectorItemBidding.setCreateTime(curDate);
        collectorItemBidding.setUpdateTime(curDate);

        returnMap.put("collectorItem", collectorItem);
        returnMap.put("collectorItemBidding", collectorItemBidding);
        return returnMap;
    }

    /**
     * 竞价属性拷贝
     * @param source 源
     * @param target 目标
     * @throws MsOnionException MsOnionException
     */
    public static  void copyItemBidding(CollectorItemBidding source, CollectorItemBidding target) throws MsOnionException {

        if (source.getAmazonPrice() != null && source.getAmazonPrice() > 0) {
            target.setAmazonPrice(source.getAmazonPrice());
        } else {
            target.setAmazonPrice(null);
        }
        if (source.getTmallPrice() != null && source.getTmallPrice() > 0) {
            target.setTmallPrice(source.getTmallPrice());
        } else {
            target.setTmallPrice(null);
        }
        if (source.getJdPrice() != null && source.getJdPrice() > 0) {
            target.setJdPrice(source.getJdPrice());
        } else {
            target.setJdPrice(null);
        }
        if (source.getRedPrice() != null && source.getRedPrice() > 0) {
            target.setRedPrice(source.getRedPrice());
        } else {
            target.setRedPrice(null);
        }
        if (source.getOriginCountryPrice() != null && source.getOriginCountryPrice() > 0) {
            target.setOriginCountryPrice(source.getOriginCountryPrice());
        } else {
            target.setOriginCountryPrice(null);
        }
        if (source.getDomesticPrice() != null && source.getDomesticPrice() > 0) {
            target.setDomesticPrice(source.getDomesticPrice());
        } else {
            target.setDomesticPrice(null);
        }
        if (source.getAbroadPrice() != null && source.getAbroadPrice() > 0) {
            target.setAbroadPrice(source.getAbroadPrice());
        } else {
            target.setAbroadPrice(null);
        }
        if (source.getKoalaPrice() != null && source.getKoalaPrice() > 0) {
            target.setKoalaPrice(source.getKoalaPrice());
        } else {
            target.setKoalaPrice(null);
        }
        if (source.getJumeiPrice() != null && source.getJumeiPrice() > 0) {
            target.setJumeiPrice(source.getJumeiPrice());
        } else {
            target.setJumeiPrice(null);
        }

    }

}
