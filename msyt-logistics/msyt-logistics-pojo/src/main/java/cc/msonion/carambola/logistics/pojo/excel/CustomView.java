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
package cc.msonion.carambola.logistics.pojo.excel;

/**
 * @Title: CustomView
 * @Package: cc.msonion.carambola.manager.pojo.view
 * @Description: 商品报关参数对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月24日 20:19:26
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月24日 20:19:26
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */


import cc.msonion.carambola.parent.ext.excel.view.MsOnionBaseReaderView;

import java.io.Serializable;

/**
 * @ClassName: CustomView
 * @Description: 商品报关参数对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月24日 20:19:26
 */
public class CustomView extends MsOnionBaseReaderView implements Serializable {


    /**
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    private Long itemIdx;

    /**
     * 报关品名，最多50个字符
     */
    private String customName;

    /**
     * 第一数量，取值范围为0-2147483647
     */
    private Integer firstQuantity;

    /**
     * 第二数量，取值范围为0-2147483647
     */
    private Integer secondQuantity;

    /**
     * 第一单位，最多5个字符
     */
    private Long firstUnit;

    /**
     * 第二单位，最多5个字符
     */
    private Long secondUnit;

    /**
     * 行邮税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    private Double postalArticlesTaxRate;

    /**
     * 跨境税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    private Double crossBorderTaxRate;

    /**
     * BC价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    private Double bcPrice;

    /**
     * HS编码，取值范围0-9999999999，不可以为null
     */
    private Long hsCode;

    /**
     * 毛重，最多20个字符
     */
    private String grossWeight;

    /**
     * 行邮税号，取值范围0-9999999999，不可以为null
     */
    private Long postalArticlesTaxNumber;

    /**
     * 是否含有消费税，1：是，0：否，取值范围为0-9，不可以为null
     */
    private Short isContainExcise;

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
     * @return customName
     */
    public String getCustomName() {
        return customName;
    }

    /**
     * @param customName customName
     */
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    /**
     * @return firstQuantity
     */
    public Integer getFirstQuantity() {
        return firstQuantity;
    }

    /**
     * @param firstQuantity firstQuantity
     */
    public void setFirstQuantity(Integer firstQuantity) {
        this.firstQuantity = firstQuantity;
    }

    /**
     * @return secondQuantity
     */
    public Integer getSecondQuantity() {
        return secondQuantity;
    }

    /**
     * @param secondQuantity secondQuantity
     */
    public void setSecondQuantity(Integer secondQuantity) {
        this.secondQuantity = secondQuantity;
    }

    public Long getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(Long firstUnit) {
        this.firstUnit = firstUnit;
    }

    public Long getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(Long secondUnit) {
        this.secondUnit = secondUnit;
    }

    /**
     * @return postalArticlesTaxRate
     */
    public Double getPostalArticlesTaxRate() {
        return postalArticlesTaxRate;
    }

    /**
     * @param postalArticlesTaxRate postalArticlesTaxRate
     */
    public void setPostalArticlesTaxRate(Double postalArticlesTaxRate) {
        this.postalArticlesTaxRate = postalArticlesTaxRate;
    }

    /**
     * @return crossBorderTaxRate
     */
    public Double getCrossBorderTaxRate() {
        return crossBorderTaxRate;
    }

    /**
     * @param crossBorderTaxRate crossBorderTaxRate
     */
    public void setCrossBorderTaxRate(Double crossBorderTaxRate) {
        this.crossBorderTaxRate = crossBorderTaxRate;
    }

    /**
     * @return bcPrice
     */
    public Double getBcPrice() {
        return bcPrice;
    }

    /**
     * @param bcPrice bcPrice
     */
    public void setBcPrice(Double bcPrice) {
        this.bcPrice = bcPrice;
    }

    /**
     * @return hsCode
     */
    public Long getHsCode() {
        return hsCode;
    }

    /**
     * @param hsCode hsCode
     */
    public void setHsCode(Long hsCode) {
        this.hsCode = hsCode;
    }

    /**
     * @return grossWeight
     */
    public String getGrossWeight() {
        return grossWeight;
    }

    /**
     * @param grossWeight grossWeight
     */
    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * @return postalArticlesTaxNumber
     */
    public Long getPostalArticlesTaxNumber() {
        return postalArticlesTaxNumber;
    }

    /**
     * @param postalArticlesTaxNumber postalArticlesTaxNumber
     */
    public void setPostalArticlesTaxNumber(Long postalArticlesTaxNumber) {
        this.postalArticlesTaxNumber = postalArticlesTaxNumber;
    }

    /**
     * @return isContainExcise
     */
    public Short getIsContainExcise() {
        return isContainExcise;
    }

    /**
     * @param isContainExcise isContainExcise
     */
    public void setIsContainExcise(Short isContainExcise) {
        this.isContainExcise = isContainExcise;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomView{");
        sb.append("itemIdx=").append(itemIdx);
        sb.append(", customName='").append(customName).append('\'');
        sb.append(", firstQuantity=").append(firstQuantity);
        sb.append(", secondQuantity=").append(secondQuantity);
        sb.append(", firstUnit='").append(firstUnit).append('\'');
        sb.append(", secondUnit='").append(secondUnit).append('\'');
        sb.append(", postalArticlesTaxRate=").append(postalArticlesTaxRate);
        sb.append(", crossBorderTaxRate=").append(crossBorderTaxRate);
        sb.append(", bcPrice=").append(bcPrice);
        sb.append(", hsCode=").append(hsCode);
        sb.append(", grossWeight='").append(grossWeight).append('\'');
        sb.append(", postalArticlesTaxNumber=").append(postalArticlesTaxNumber);
        sb.append(", isContainExcise=").append(isContainExcise);
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
