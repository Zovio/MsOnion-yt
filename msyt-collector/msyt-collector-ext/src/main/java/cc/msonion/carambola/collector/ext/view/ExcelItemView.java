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
package cc.msonion.carambola.collector.ext.view;



import cc.msonion.carambola.parent.ext.excel.view.MsOnionBaseReaderView;

import java.io.Serializable;

/**
 * @Title: NewItemView
 * @Package: cc.msonion.carambola.manager.common.utils
 * @Description: NewItemView Excel 新增商品数据模版
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 19:59:43
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月23日 19:59:43
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: NewItemView
 * @Description: NewItemView Excel 新增商品数据模版
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月23日 19:59:43
 */
public class ExcelItemView extends MsOnionBaseReaderView implements Serializable {


    /**
     * 商品ID
     */
    private Long itemIdx;  //  idx
    /**
     * 条形码，最多20个字符，不可以为null
     */
    private String barcode;

    /**
     * 中文名称，最多50个字符，不可以为null
     */
    private String cnName;

    /**
     * 英文名称，最多200个字符，不可以为null
     */
    private String enName;

    /**
     * 品牌，不可以为null，必须手动设置为0
     */
    private Long brandIdx;

    /**
     * 产地，不可以为null，必须手动设置为0
     */
    private Long originIdx;

    /**
     * 亚马逊售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double amazonPrice;

    /**
     * 天猫售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double tmallPrice;

    /**
     * 京东售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double jdPrice;

    /**
     * 小红书售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double redPrice;

    /**
     * 国外官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double abroadPrice;

    /**
     * 国内官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double domesticPrice;

    /**
     * 原产国售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double originCountryPrice;

    /**
     * 考拉售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double koalaPrice;

    /**
     * 聚美售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double jumeiPrice;

    /**
     * 批次，取值范围0-99999，不可以为null，必须手动设置为0
     */
    private Integer batch;
    /**
     * 权重，决定前端商品的排列顺序，不可以为null，必须手动设置为0
     */
    private Long weight;
    /**tr
     * 销售状态
     * （1）在线-常规（2）在线-季节（3）在线-售完即止（4）在线-促销（5）今日补货（6）异常库存（7）暂时下架（8）停止售卖
     */
    private Long itemStateIdx;
    /**
     * 采集备注，最多500个字符
     */
    private String collectionRemark;


    /**
     * @return itemIdx
     */
    public Long getItemIdx() {
        return itemIdx;
    }

    /**
     * @param itemIdx itemIdx
     */
    public void setItemIdx(Long itemIdx) {
        this.itemIdx = itemIdx;
    }

    /**
     * @return barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @return cnName
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * @param cnName cnName
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * @return enName
     */
    public String getEnName() {
        return enName;
    }

    /**
     * @param enName enName
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }


    /**
     * @return amazonPrice
     */
    public Double getAmazonPrice() {
        return amazonPrice;
    }

    /**
     * @param amazonPrice amazonPrice
     */
    public void setAmazonPrice(Double amazonPrice) {
        this.amazonPrice = amazonPrice;
    }

    /**
     * @return tmallPrice
     */
    public Double getTmallPrice() {
        return tmallPrice;
    }

    /**
     * @param tmallPrice tmallPrice
     */
    public void setTmallPrice(Double tmallPrice) {
        this.tmallPrice = tmallPrice;
    }

    /**
     * @return jdPrice
     */
    public Double getJdPrice() {
        return jdPrice;
    }

    /**
     * @param jdPrice jdPrice
     */
    public void setJdPrice(Double jdPrice) {
        this.jdPrice = jdPrice;
    }

    /**
     * @return redPrice
     */
    public Double getRedPrice() {
        return redPrice;
    }

    /**
     * @param redPrice redPrice
     */
    public void setRedPrice(Double redPrice) {
        this.redPrice = redPrice;
    }

    /**
     * @return abroadPrice
     */
    public Double getAbroadPrice() {
        return abroadPrice;
    }

    /**
     * @param abroadPrice abroadPrice
     */
    public void setAbroadPrice(Double abroadPrice) {
        this.abroadPrice = abroadPrice;
    }

    /**
     * @return domesticPrice
     */
    public Double getDomesticPrice() {
        return domesticPrice;
    }

    /**
     * @param domesticPrice domesticPrice
     */
    public void setDomesticPrice(Double domesticPrice) {
        this.domesticPrice = domesticPrice;
    }

    /**
     * @return originCountryPrice
     */
    public Double getOriginCountryPrice() {
        return originCountryPrice;
    }

    /**
     * @param originCountryPrice originCountryPrice
     */
    public void setOriginCountryPrice(Double originCountryPrice) {
        this.originCountryPrice = originCountryPrice;
    }

    /**
     * @return koalaPrice
     */
    public Double getKoalaPrice() {
        return koalaPrice;
    }

    /**
     * @param koalaPrice koalaPrice
     */
    public void setKoalaPrice(Double koalaPrice) {
        this.koalaPrice = koalaPrice;
    }

    /**
     * @return jumeiPrice
     */
    public Double getJumeiPrice() {
        return jumeiPrice;
    }

    /**
     * @param jumeiPrice jumeiPrice
     */
    public void setJumeiPrice(Double jumeiPrice) {
        this.jumeiPrice = jumeiPrice;
    }

    /**
     * @return batch
     */
    public Integer getBatch() {
        return batch;
    }

    /**
     * @param batch batch
     */
    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    /**
     * @return weight
     */
    public Long getWeight() {
        return weight;
    }

    /**
     * @param weight weight
     */
    public void setWeight(Long weight) {
        this.weight = weight;
    }


    /**
     * @return collectionRemark
     */
    public String getCollectionRemark() {
        return collectionRemark;
    }

    /**
     * @param collectionRemark collectionRemark
     */
    public void setCollectionRemark(String collectionRemark) {
        this.collectionRemark = collectionRemark;
    }

    /**
     * @return Long
     */
    public Long getBrandIdx() {
        return brandIdx;
    }

    /**
     * @param brandIdx brandIdx
     */
    public void setBrandIdx(Long brandIdx) {
        this.brandIdx = brandIdx;
    }

    /**
     * @return originIdx
     */
    public Long getOriginIdx() {
        return originIdx;
    }

    /**
     * @param originIdx originIdx
     */
    public void setOriginIdx(Long originIdx) {
        this.originIdx = originIdx;
    }

    /**
     * @return itemStateIdx
     */
    public Long getItemStateIdx() {
        return itemStateIdx;
    }

    /**
     * @param itemStateIdx itemStateIdx
     */
    public void setItemStateIdx(Long itemStateIdx) {
        this.itemStateIdx = itemStateIdx;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NewItemView{");
        sb.append("[barcode='").append(barcode).append('\'');
        sb.append(", cnName='").append(cnName).append('\'');
        sb.append(", enName='").append(enName).append('\'');
        sb.append(", brandIdx=").append(brandIdx);
        sb.append(", originIdx=").append(originIdx);
        sb.append(", amazonPrice=").append(amazonPrice);
        sb.append(", tmallPrice=").append(tmallPrice);
        sb.append(", jdPrice=").append(jdPrice);
        sb.append(", redPrice=").append(redPrice);
        sb.append(", abroadPrice=").append(abroadPrice);
        sb.append(", domesticPrice=").append(domesticPrice);
        sb.append(", originCountryPrice=").append(originCountryPrice);
        sb.append(", koalaPrice=").append(koalaPrice);
        sb.append(", jumeiPrice=").append(jumeiPrice);
        sb.append(", batch=").append(batch);
        sb.append(", weight=").append(weight);
        sb.append(", itemStateIdx=").append(itemStateIdx);
        sb.append(", collectionRemark='").append(collectionRemark).append('\'');
        sb.append('}');
        sb.append("BaseReaderView{");
        sb.append("curSheetNum=").append(this.getCurSheetNum());
        sb.append(", asHead=").append(this.getAsHead());
        sb.append(", asTail=").append(this.getAsTail());
        sb.append(", curLine=").append(this.getCurLine());
        sb.append(", isOk=").append(this.getOk());
        sb.append(", errorMsg='").append(this.getErrorMsg()).append('\'');
        sb.append("}]");
        return sb.toString();
    }
}
