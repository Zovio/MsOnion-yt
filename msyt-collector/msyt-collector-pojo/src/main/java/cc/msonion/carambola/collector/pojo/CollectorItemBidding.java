package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionModifiedField;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class CollectorItemBidding implements MsOnionBasePojoAdapter {
    /**
     * 商品竞价主键idx，分布式架构，全局唯一递增
     */
    private Long idx;

    /**
     idx code 全局唯一性，解决idx可以通过递增1猜测出来，潜在安全问题，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String idxCodeS;

    /**
     * idx code 全局唯一性，解决idx可以通过递增1猜测出来，潜在安全问题
     */
    private Long idxCode;

    /**
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    private Long itemIdx;

    /**
     亚马逊售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String amazonPriceDots2;

    /**
     * 亚马逊售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "亚马逊售价不能为空")
    @Range(min = 0, max = 2147483647, message = "亚马逊售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer amazonPrice;

    /**
     天猫售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String tmallPriceDots2;

    /**
     * 天猫售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "天猫售价不能为空")
    @Range(min = 0, max = 2147483647, message = "天猫售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer tmallPrice;

    /**
     京东售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String jdPriceDots2;

    /**
     * 京东售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "京东售价不能为空")
    @Range(min = 0, max = 2147483647, message = "京东售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer jdPrice;

    /**
     小红书售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String redPriceDots2;

    /**
     * 小红书售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "小红书售价不能为空")
    @Range(min = 0, max = 2147483647, message = "小红书售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer redPrice;

    /**
     国外官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String abroadPriceDots2;

    /**
     * 国外官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "国外官网售价不能为空")
    @Range(min = 0, max = 2147483647, message = "国外官网售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer abroadPrice;

    /**
     国内官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String domesticPriceDots2;

    /**
     * 国内官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "国内官网售价不能为空")
    @Range(min = 0, max = 2147483647, message = "国内官网售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer domesticPrice;

    /**
     原产国售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String originCountryPriceDots2;

    /**
     * 原产国售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "原产国售价不能为空")
    @Range(min = 0, max = 2147483647, message = "原产国售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer originCountryPrice;

    /**
     考拉售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String koalaPriceDots2;

    /**
     * 考拉售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "考拉售价不能为空")
    @Range(min = 0, max = 2147483647, message = "考拉售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer koalaPrice;

    /**
     聚美售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String jumeiPriceDots2;

    /**
     * 聚美售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "聚美售价不能为空")
    @Range(min = 0, max = 2147483647, message = "聚美售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer jumeiPrice;

    /**
     创建成员idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String createByMemberIdxDynamicS;

    /**
     * 创建成员idx，不可以为null
     */
    private Long createByMemberIdx;

    /**
     * 修改成员idx，不可以为null
     */
    private Long updateByMemberIdx;

    /**
    创建时间，第一次创建时间，后续不可以修改时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String createTimeEnYyyyMMddHHmmss;

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
     * 商品竞价主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品竞价主键idx，分布式架构，全局唯一递增
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     idx code 全局唯一性，解决idx可以通过递增1猜测出来，潜在安全问题，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getIdxCodeS() {
        return String.valueOf(this.idxCode);
    }

    /**
     * idx code 全局唯一性，解决idx可以通过递增1猜测出来，潜在安全问题
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * idx code 全局唯一性，解决idx可以通过递增1猜测出来，潜在安全问题
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    public Long getItemIdx() {
        return itemIdx;
    }

    /**
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    public void setItemIdx(Long itemIdx) {
        this.itemIdx = itemIdx;
    }

    /**
     亚马逊售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getAmazonPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(amazonPrice, 2);
    }

    /**
     * 亚马逊售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getAmazonPrice() {
        return amazonPrice;
    }

    /**
     * 亚马逊售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setAmazonPrice(Integer amazonPrice) {
        this.amazonPrice = amazonPrice;
    }

    /**
     天猫售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getTmallPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(tmallPrice, 2);
    }

    /**
     * 天猫售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getTmallPrice() {
        return tmallPrice;
    }

    /**
     * 天猫售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setTmallPrice(Integer tmallPrice) {
        this.tmallPrice = tmallPrice;
    }

    /**
     京东售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getJdPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(jdPrice, 2);
    }

    /**
     * 京东售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getJdPrice() {
        return jdPrice;
    }

    /**
     * 京东售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setJdPrice(Integer jdPrice) {
        this.jdPrice = jdPrice;
    }

    /**
     小红书售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getRedPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(redPrice, 2);
    }

    /**
     * 小红书售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getRedPrice() {
        return redPrice;
    }

    /**
     * 小红书售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setRedPrice(Integer redPrice) {
        this.redPrice = redPrice;
    }

    /**
     国外官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getAbroadPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(abroadPrice, 2);
    }

    /**
     * 国外官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getAbroadPrice() {
        return abroadPrice;
    }

    /**
     * 国外官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setAbroadPrice(Integer abroadPrice) {
        this.abroadPrice = abroadPrice;
    }

    /**
     国内官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getDomesticPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(domesticPrice, 2);
    }

    /**
     * 国内官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getDomesticPrice() {
        return domesticPrice;
    }

    /**
     * 国内官网售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setDomesticPrice(Integer domesticPrice) {
        this.domesticPrice = domesticPrice;
    }

    /**
     原产国售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getOriginCountryPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(originCountryPrice, 2);
    }

    /**
     * 原产国售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getOriginCountryPrice() {
        return originCountryPrice;
    }

    /**
     * 原产国售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setOriginCountryPrice(Integer originCountryPrice) {
        this.originCountryPrice = originCountryPrice;
    }

    /**
     考拉售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getKoalaPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(koalaPrice, 2);
    }

    /**
     * 考拉售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getKoalaPrice() {
        return koalaPrice;
    }

    /**
     * 考拉售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setKoalaPrice(Integer koalaPrice) {
        this.koalaPrice = koalaPrice;
    }

    /**
     聚美售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getJumeiPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(jumeiPrice, 2);
    }

    /**
     * 聚美售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getJumeiPrice() {
        return jumeiPrice;
    }

    /**
     * 聚美售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setJumeiPrice(Integer jumeiPrice) {
        this.jumeiPrice = jumeiPrice;
    }

    /**
     创建成员idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getCreateByMemberIdxDynamicS() {
        return this.createByMemberIdxDynamicS;
    }

    /**
     创建成员idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setCreateByMemberIdxDynamicS(String createByMemberIdxDynamicS) {
         this.createByMemberIdxDynamicS = createByMemberIdxDynamicS;
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
    创建时间，第一次创建时间，后续不可以修改时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getCreateTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.createTime) ;
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
        sb.append(", itemIdx=").append(itemIdx);
        sb.append(", amazonPriceDots2=").append(amazonPriceDots2);
        sb.append(", amazonPrice=").append(amazonPrice);
        sb.append(", tmallPriceDots2=").append(tmallPriceDots2);
        sb.append(", tmallPrice=").append(tmallPrice);
        sb.append(", jdPriceDots2=").append(jdPriceDots2);
        sb.append(", jdPrice=").append(jdPrice);
        sb.append(", redPriceDots2=").append(redPriceDots2);
        sb.append(", redPrice=").append(redPrice);
        sb.append(", abroadPriceDots2=").append(abroadPriceDots2);
        sb.append(", abroadPrice=").append(abroadPrice);
        sb.append(", domesticPriceDots2=").append(domesticPriceDots2);
        sb.append(", domesticPrice=").append(domesticPrice);
        sb.append(", originCountryPriceDots2=").append(originCountryPriceDots2);
        sb.append(", originCountryPrice=").append(originCountryPrice);
        sb.append(", koalaPriceDots2=").append(koalaPriceDots2);
        sb.append(", koalaPrice=").append(koalaPrice);
        sb.append(", jumeiPriceDots2=").append(jumeiPriceDots2);
        sb.append(", jumeiPrice=").append(jumeiPrice);
        sb.append(", createByMemberIdxDynamicS=").append(createByMemberIdxDynamicS);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTimeEnYyyyMMddHHmmss=").append(createTimeEnYyyyMMddHHmmss);
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