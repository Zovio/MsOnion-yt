package cc.msonion.carambola.warehouse.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionModifiedField;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class Warehouse implements MsOnionBasePojoAdapter {
    /**
     * 商品仓库主键idx，分布式架构，全局唯一递增
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
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "商品不能为空")
    private Long itemIdx;

    /**
     * 实时库存，取值范围为0-2147483647，不可以为null
     */
    @NotNull(message = "实时库存不能为空")
    @Range(min = 0, max = 2147483647, message = "实时库存需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer realTimeInventory;

    /**
     * 安全库存，取值范围为0-2147483647，不可以为null，必须手动设置默认值为3
     */
    @NotNull(message = "安全库存不能为空")
    @Range(min = 0, max = 2147483647, message = "安全库存需要设置0-2147483647")
    @MsOnionModifiedField
    private Integer safeInventory;

    /**
     * 发货系数，取值范围为0-99，不可以为null
     */
    @NotNull(message = "发货系数不能为空")
    @Range(min = 0, max = 99, message = "发货系数取值区间1-99")
    @MsOnionModifiedField
    private Short deliveryCoefficient;

    /**
     * 是否一键下单，1：是，0：否，取值范围为0-9，不可以为null
     */
    @NotNull(message = "是否一键下单不能为空")
    @Range(min = 0, max = 9, message = "是否一键下单取值区间0-9")
    @MsOnionModifiedField
    private Short isKeyOrder;

    /**
     * 是否免邮，1：是，0：否，取值范围为0-9，不可以为null
     */
    @NotNull(message = "是否免邮不能为空")
    @Range(min = 0, max = 9, message = "是否免邮取值区间0-9")
    @MsOnionModifiedField
    private Short isFreeShipping;

    /**
     * 发布状态，0-未发布 1-已发布
     */
    private Short publishStatus;

    /**
     * 备注，0-100字符，不可以为null，必须手动设置为空字符串## @Length(max = 100, message = "备注最大100个字符")##
     */
    @Length(max = 100, message = "备注最大100个字符")
    private String remark;

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
     * 商品仓库主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品仓库主键idx，分布式架构，全局唯一递增
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
     * 实时库存，取值范围为0-2147483647，不可以为null
     */
    public Integer getRealTimeInventory() {
        return realTimeInventory;
    }

    /**
     * 实时库存，取值范围为0-2147483647，不可以为null
     */
    public void setRealTimeInventory(Integer realTimeInventory) {
        this.realTimeInventory = realTimeInventory;
    }

    /**
     * 安全库存，取值范围为0-2147483647，不可以为null，必须手动设置默认值为3
     */
    public Integer getSafeInventory() {
        return safeInventory;
    }

    /**
     * 安全库存，取值范围为0-2147483647，不可以为null，必须手动设置默认值为3
     */
    public void setSafeInventory(Integer safeInventory) {
        this.safeInventory = safeInventory;
    }

    /**
     * 发货系数，取值范围为0-99，不可以为null
     */
    public Short getDeliveryCoefficient() {
        return deliveryCoefficient;
    }

    /**
     * 发货系数，取值范围为0-99，不可以为null
     */
    public void setDeliveryCoefficient(Short deliveryCoefficient) {
        this.deliveryCoefficient = deliveryCoefficient;
    }

    /**
     * 是否一键下单，1：是，0：否，取值范围为0-9，不可以为null
     */
    public Short getIsKeyOrder() {
        return isKeyOrder;
    }

    /**
     * 是否一键下单，1：是，0：否，取值范围为0-9，不可以为null
     */
    public void setIsKeyOrder(Short isKeyOrder) {
        this.isKeyOrder = isKeyOrder;
    }

    /**
     * 是否免邮，1：是，0：否，取值范围为0-9，不可以为null
     */
    public Short getIsFreeShipping() {
        return isFreeShipping;
    }

    /**
     * 是否免邮，1：是，0：否，取值范围为0-9，不可以为null
     */
    public void setIsFreeShipping(Short isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    /**
     * 发布状态，0-未发布 1-已发布
     */
    public Short getPublishStatus() {
        return publishStatus;
    }

    /**
     * 发布状态，0-未发布 1-已发布
     */
    public void setPublishStatus(Short publishStatus) {
        this.publishStatus = publishStatus;
    }

    /**
     * 备注，0-100字符，不可以为null，必须手动设置为空字符串## @Length(max = 100, message = "备注最大100个字符")##
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，0-100字符，不可以为null，必须手动设置为空字符串## @Length(max = 100, message = "备注最大100个字符")##
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", itemIdx=").append(itemIdx);
        sb.append(", realTimeInventory=").append(realTimeInventory);
        sb.append(", safeInventory=").append(safeInventory);
        sb.append(", deliveryCoefficient=").append(deliveryCoefficient);
        sb.append(", isKeyOrder=").append(isKeyOrder);
        sb.append(", isFreeShipping=").append(isFreeShipping);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", remark=").append(remark);
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