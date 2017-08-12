package cc.msonion.carambola.item.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionModifiedField;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class ItemPriceOfficial implements MsOnionBasePojoAdapter {
    /**
     * 正式商品价格主键idx，分布式架构，全局唯一递增
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
     * 正式商品主键idx，不可以为null，必须手动设置默认值为0
     */
    private Long itemOfficialIdx;

    /**
     市场价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String marketPriceDots2;

    /**
     * 市场价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "市场价不能为空")
    @Range(min = 0, max = 2147483647, message = "市场价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer marketPrice;

    /**
     售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String sellingPriceDots2;

    /**
     * 售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "售价不能为空")
    @Range(min = 0, max = 2147483647, message = "售价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer sellingPrice;

    /**
     供货价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    private String supplyPriceDots2;

    /**
     * 供货价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    @NotNull(message = "供货价不能为空")
    @Range(min = 0, max = 2147483647, message = "供货价需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer supplyPrice;

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
     * 正式商品价格主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 正式商品价格主键idx，分布式架构，全局唯一递增
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
     * 正式商品主键idx，不可以为null，必须手动设置默认值为0
     */
    public Long getItemOfficialIdx() {
        return itemOfficialIdx;
    }

    /**
     * 正式商品主键idx，不可以为null，必须手动设置默认值为0
     */
    public void setItemOfficialIdx(Long itemOfficialIdx) {
        this.itemOfficialIdx = itemOfficialIdx;
    }

    /**
     市场价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getMarketPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(marketPrice, 2);
    }

    /**
     * 市场价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getMarketPrice() {
        return marketPrice;
    }

    /**
     * 市场价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getSellingPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(sellingPrice, 2);
    }

    /**
     * 售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getSellingPrice() {
        return sellingPrice;
    }

    /**
     * 售价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     供货价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null，
     货币格式：188.12、0.12
     */
    public String getSupplyPriceDots2() throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionNumberUtils.formatCurrency(supplyPrice, 2);
    }

    /**
     * 供货价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public Integer getSupplyPrice() {
        return supplyPrice;
    }

    /**
     * 供货价，以分为单位，而不是以元为单位，取值范围0-2147483647，不可以为null
     */
    public void setSupplyPrice(Integer supplyPrice) {
        this.supplyPrice = supplyPrice;
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
        sb.append(", itemOfficialIdx=").append(itemOfficialIdx);
        sb.append(", marketPriceDots2=").append(marketPriceDots2);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", sellingPriceDots2=").append(sellingPriceDots2);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", supplyPriceDots2=").append(supplyPriceDots2);
        sb.append(", supplyPrice=").append(supplyPrice);
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