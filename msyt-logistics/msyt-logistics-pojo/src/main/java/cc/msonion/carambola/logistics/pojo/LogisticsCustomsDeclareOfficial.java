package cc.msonion.carambola.logistics.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class LogisticsCustomsDeclareOfficial implements MsOnionBasePojoAdapter {
    /**
     * 正式商品报关主键idx，分布式架构，全局唯一递增
     */
    private Long idx;

    /**
     主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String idxCodeS;

    /**
     * 主键idx生成的code
     */
    private Long idxCode;

    /**
     * 正式商品主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "正式商品不能为空")
    private Long itemOfficialIdx;

    /**
     * 报关品名，最多50个字符
     */
    @Length(max = 50, message = "报关品名最多50个字符")
    private String customName;

    /**
     * 第一数量，取值范围为0-2147483647
     */
    @Range(min = 0, max = 2147483647, message = "第一数量需要设置0-2147483647")
    private Integer firstQuantity;

    /**
     * 第二数量，取值范围为0-2147483647
     */
    @Range(min = 0, max = 2147483647, message = "第二数量需要设置0-2147483647")
    private Integer secondQuantity;

    /**
     第一单位主键idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String firstUnitIdxDynamicS;

    /**
     * 第一单位主键idx，不可以为null
     */
    private Long firstUnitIdx;

    /**
     第二单位主键idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String secondUnitIdxDynamicS;

    /**
     * 第二单位主键idx，不可以为null
     */
    private Long secondUnitIdx;

    /**
     行邮税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null，
     货币格式：188.12345、0.12345
     */
    private String postalArticlesTaxRateDots5;

    /**
     * 行邮税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    @NotNull(message = "行邮税率不能为空")
    @Range(min = 0, max = 100000, message = "行邮税率需要设置0-1")
    private Integer postalArticlesTaxRate;

    /**
     跨境税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null，
     货币格式：188.12345、0.12345
     */
    private String crossBorderTaxRateDots5;

    /**
     * 跨境税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    @NotNull(message = "跨境税率不能为空")
    @Range(min = 0, max = 100000, message = "跨境税率需要设置0-1")
    private Integer crossBorderTaxRate;

    /**
     BC价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String bcPriceDots2;

    /**
     * BC价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "BC价不能为空")
    @Range(min = 0, max = 2147483647, message = "BC价需要设置0-2147483647")
    private Integer bcPrice;

    /**
     * HS编码，取值范围0-9999999999，不可以为null
     */
    @NotNull(message = "HS编码不能为空")
    @Range(min = 0, max = 9999999999L, message = "HS编码需要设置0-9999999999")
    private Long hsCode;

    /**
     * 毛重，最多20个字符
     */
    @Length(max = 20, message = "毛重最多20个字符")
    private String grossWeight;

    /**
     * 行邮税号，取值范围0-9999999999，不可以为null
     */
    @NotNull(message = "行邮税号不能为空")
    @Range(min = 0, max = 9999999999L, message = "行邮税号需要设置0-9999999999")
    private Long postalArticlesTaxNumber;

    /**
     * 是否含有消费税，1：是，0：否，取值范围为0-9，不可以为null
     */
    @NotNull(message = "是否含有消费税不能为空")
    private Short isContainExcise;

    /**
     * 创建成员idx，不可以为null
     */
    private Long createByMemberIdx;

    /**
     * 修改成员idx，不可以为null
     */
    private Long updateByMemberIdx;

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    private Date createTime;

    /**
     * 更新时间，每一次修改都要更新时间
     */
    private Date updateTime;

    /**
     * 状态，1：可用的、激活的，2：未激活，0：删除，取值范围为0-9，不可以为null
     */
    private Short status;

    /**
     * 版本号，高并发，乐观锁的解决方案
     */
    private Long version;

    /**
     * 扩展，0-100字符，不可以为null，必须手动设置为空字符串
     */
    private String ext;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    /**
     * 正式商品报关主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 正式商品报关主键idx，分布式架构，全局唯一递增
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getIdxCodeS() {
        return String.valueOf(this.idxCode);
    }

    /**
     * 主键idx生成的code
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * 主键idx生成的code
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 正式商品主键idx，不可以为null，必须手动设置为0
     */
    public Long getItemOfficialIdx() {
        return itemOfficialIdx;
    }

    /**
     * 正式商品主键idx，不可以为null，必须手动设置为0
     */
    public void setItemOfficialIdx(Long itemOfficialIdx) {
        this.itemOfficialIdx = itemOfficialIdx;
    }

    /**
     * 报关品名，最多50个字符
     */
    public String getCustomName() {
        return customName;
    }

    /**
     * 报关品名，最多50个字符
     */
    public void setCustomName(String customName) {
        this.customName = customName == null ? null : customName.trim();
    }

    /**
     * 第一数量，取值范围为0-2147483647
     */
    public Integer getFirstQuantity() {
        return firstQuantity;
    }

    /**
     * 第一数量，取值范围为0-2147483647
     */
    public void setFirstQuantity(Integer firstQuantity) {
        this.firstQuantity = firstQuantity;
    }

    /**
     * 第二数量，取值范围为0-2147483647
     */
    public Integer getSecondQuantity() {
        return secondQuantity;
    }

    /**
     * 第二数量，取值范围为0-2147483647
     */
    public void setSecondQuantity(Integer secondQuantity) {
        this.secondQuantity = secondQuantity;
    }

    /**
     第一单位主键idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getFirstUnitIdxDynamicS() {
        return this.firstUnitIdxDynamicS;
    }

    /**
     第一单位主键idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setFirstUnitIdxDynamicS(String firstUnitIdxDynamicS) {
         this.firstUnitIdxDynamicS = firstUnitIdxDynamicS;
    }

    /**
     * 第一单位主键idx，不可以为null
     */
    public Long getFirstUnitIdx() {
        return firstUnitIdx;
    }

    /**
     * 第一单位主键idx，不可以为null
     */
    public void setFirstUnitIdx(Long firstUnitIdx) {
        this.firstUnitIdx = firstUnitIdx;
    }

    /**
     第二单位主键idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getSecondUnitIdxDynamicS() {
        return this.secondUnitIdxDynamicS;
    }

    /**
     第二单位主键idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setSecondUnitIdxDynamicS(String secondUnitIdxDynamicS) {
         this.secondUnitIdxDynamicS = secondUnitIdxDynamicS;
    }

    /**
     * 第二单位主键idx，不可以为null
     */
    public Long getSecondUnitIdx() {
        return secondUnitIdx;
    }

    /**
     * 第二单位主键idx，不可以为null
     */
    public void setSecondUnitIdx(Long secondUnitIdx) {
        this.secondUnitIdx = secondUnitIdx;
    }

    /**
     行邮税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null，
     货币格式：188.12345、0.12345
     */
    public String getPostalArticlesTaxRateDots5() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(postalArticlesTaxRate, 5);
    }

    /**
     * 行邮税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    public Integer getPostalArticlesTaxRate() {
        return postalArticlesTaxRate;
    }

    /**
     * 行邮税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    public void setPostalArticlesTaxRate(Integer postalArticlesTaxRate) {
        this.postalArticlesTaxRate = postalArticlesTaxRate;
    }

    /**
     跨境税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null，
     货币格式：188.12345、0.12345
     */
    public String getCrossBorderTaxRateDots5() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(crossBorderTaxRate, 5);
    }

    /**
     * 跨境税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    public Integer getCrossBorderTaxRate() {
        return crossBorderTaxRate;
    }

    /**
     * 跨境税率，以十万分位为单位，而不是以元为单位，取值范围为0-100000，不可以为null
     */
    public void setCrossBorderTaxRate(Integer crossBorderTaxRate) {
        this.crossBorderTaxRate = crossBorderTaxRate;
    }

    /**
     BC价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getBcPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(bcPrice, 2);
    }

    /**
     * BC价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getBcPrice() {
        return bcPrice;
    }

    /**
     * BC价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setBcPrice(Integer bcPrice) {
        this.bcPrice = bcPrice;
    }

    /**
     * HS编码，取值范围0-9999999999，不可以为null
     */
    public Long getHsCode() {
        return hsCode;
    }

    /**
     * HS编码，取值范围0-9999999999，不可以为null
     */
    public void setHsCode(Long hsCode) {
        this.hsCode = hsCode;
    }

    /**
     * 毛重，最多20个字符
     */
    public String getGrossWeight() {
        return grossWeight;
    }

    /**
     * 毛重，最多20个字符
     */
    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight == null ? null : grossWeight.trim();
    }

    /**
     * 行邮税号，取值范围0-9999999999，不可以为null
     */
    public Long getPostalArticlesTaxNumber() {
        return postalArticlesTaxNumber;
    }

    /**
     * 行邮税号，取值范围0-9999999999，不可以为null
     */
    public void setPostalArticlesTaxNumber(Long postalArticlesTaxNumber) {
        this.postalArticlesTaxNumber = postalArticlesTaxNumber;
    }

    /**
     * 是否含有消费税，1：是，0：否，取值范围为0-9，不可以为null
     */
    public Short getIsContainExcise() {
        return isContainExcise;
    }

    /**
     * 是否含有消费税，1：是，0：否，取值范围为0-9，不可以为null
     */
    public void setIsContainExcise(Short isContainExcise) {
        this.isContainExcise = isContainExcise;
    }

    /**
     * 创建成员idx，不可以为null
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 创建成员idx，不可以为null
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * 修改成员idx，不可以为null
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 修改成员idx，不可以为null
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，每一次修改都要更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，每一次修改都要更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 状态，1：可用的、激活的，2：未激活，0：删除，取值范围为0-9，不可以为null
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态，1：可用的、激活的，2：未激活，0：删除，取值范围为0-9，不可以为null
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 扩展，0-100字符，不可以为null，必须手动设置为空字符串
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展，0-100字符，不可以为null，必须手动设置为空字符串
     */
    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    @Override
    public String getId() {
        return this.idx + "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idx=").append(idx);
        sb.append(", idxCodeS=").append(idxCodeS);
        sb.append(", idxCode=").append(idxCode);
        sb.append(", itemOfficialIdx=").append(itemOfficialIdx);
        sb.append(", customName=").append(customName);
        sb.append(", firstQuantity=").append(firstQuantity);
        sb.append(", secondQuantity=").append(secondQuantity);
        sb.append(", firstUnitIdxDynamicS=").append(firstUnitIdxDynamicS);
        sb.append(", firstUnitIdx=").append(firstUnitIdx);
        sb.append(", secondUnitIdxDynamicS=").append(secondUnitIdxDynamicS);
        sb.append(", secondUnitIdx=").append(secondUnitIdx);
        sb.append(", postalArticlesTaxRateDots5=").append(postalArticlesTaxRateDots5);
        sb.append(", postalArticlesTaxRate=").append(postalArticlesTaxRate);
        sb.append(", crossBorderTaxRateDots5=").append(crossBorderTaxRateDots5);
        sb.append(", crossBorderTaxRate=").append(crossBorderTaxRate);
        sb.append(", bcPriceDots2=").append(bcPriceDots2);
        sb.append(", bcPrice=").append(bcPrice);
        sb.append(", hsCode=").append(hsCode);
        sb.append(", grossWeight=").append(grossWeight);
        sb.append(", postalArticlesTaxNumber=").append(postalArticlesTaxNumber);
        sb.append(", isContainExcise=").append(isContainExcise);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}