package test.msonion.carambola.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionSearch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

// @Exclusion
// @MsOnionSearchExclusion
@MsOnionSearch(module = "test-msyt-collector3")
public class TestCollectorItem implements MsOnionBasePojoAdapter {
    /**
     * 商品主键idx，分布式架构，全局唯一递增
     */
    private Long idx;

    /**
     * idx code 全局唯一性，解决idx可以通过递增1猜测出来，潜在安全问题
     */
    private Long idxCode;

    /**
     * 平台主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "所属平台不能为空")
    private Long platformIdx;

    /**
     * 状态主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "商品状态不能为空")
    private Long itemStateIdx;

    /**
     * 商品扩展主键idx，不可以为null，全局唯一递增
     */
    private Long itemExtIdx;

    /**
     * 货号，14个字符，根据类目与仓库类型来自动生成货号，不可重复，不可以为null
     */
    @NotNull(message = "货号不能为空")
    @Length(min = 14, max = 14, message = "货号为14个字符")
    private String itemNo;

    /**
     * 条形码，最多20个字符，不可以为null
     */
    @NotNull(message = "条形码不能为空")
    @Length(max = 20, message = "条形码最多20个字符")
    private String barcode;

    /**
     * 中文名称，最多50个字符，不可以为null
     */
    @NotNull(message = "中文名称不能为空")
    @Length(max = 50, message = "中文名称最多50个字符")
    private String cnName;

    /**
     * 英文名称，最多200个字符，不可以为null
     */
    @NotNull(message = "英文名称不能为空")
    @Length(max = 200, message = "英文名称最多200个字符")
    private String enName;

    /**
     * 品牌主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "品牌不能为空")
    private Long brandIdx;

    /**
     * 产地主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "产地不能为空")
    private Long originIdx;

    /**
     * 类目主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "类目不能为空")
    private Long categoryIdx;

    /**
     * 商品规格，最多100个字符
     */
    @Length(max = 100, message = "商品规格最多100个字符")
    private String specification;

    /**
     * 仓库类型主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "仓库类型不能为空")
    private Long warehouseTypeIdx;

    /**
     * 批次，取值范围0-99999，不可以为null，必须手动设置为0
     */
    @NotNull(message = "批次不能为空")
    @Range(min = 0, max = 99999, message = "批次取值区间0-99999")
    private Integer batch;

    /**
     * 采集备注，最多500个字符
     */
    @Length(max = 500, message = "采集备注最多500个字符")
    private String collectionRemark;

    /**
     商品小图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageSmallIdDynamicS;

    /**
     * 商品小图文件messageid
     */
    private Long imageSmallId;

    /**
     商品中图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageMiddleIdDynamicS;

    /**
     * 商品中图文件messageid
     */
    private Long imageMiddleId;

    /**
     商品大图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageBigIdDynamicS;

    /**
     * 商品大图文件messageid
     */
    private Long imageBigId;

    /**
     * 采购状态，1：未确认采购，2：确认采购，取值范围0-9，不可以为null，必须手动设置默认值为1
     */
    @NotNull(message = "采购状态不能为空")
    @Range(min = 0, max = 9, message = "采购状态取值区间0-9")
    private Short purchaseStatus;

    /**
     * 采集状态，1：未采集，2：采集完成，3：编辑完成，4：文案通过，取值范围0-9，不可以为null，必须手动设置默认值为1
     */
    @NotNull(message = "采集状态不能为空")
    @Range(min = 0, max = 9, message = "采集状态取值区间0-9")
    private Short collectionStatus;

    /**
     * 权重，决定前端商品的排列顺序，不可以为null，必须手动设置为0
     */
    @NotNull(message = "权重不能为空")
    private Long weight;

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
     * 上下架状态，1：上架，2：下架，0：删除，取值范围为0-9，不可以为null
     */
    private Short status;

    /**
     * 版本号，高并发，乐观锁的解决方案
     */
    private Long version;

    /**
     * 扩展，0-100字符，不可以为null，必须手动设置为空字符串
     */
    // 使用 @JsonIgnore 注解，在需要忽略的 field 字段上，即可忽略该字段。
//    @JsonIgnore
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
     * 商品主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品主键idx，分布式架构，全局唯一递增
     */
    public void setIdx(Long idx) {
        this.idx = idx;
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
     * 平台主键idx，不可以为null，必须手动设置为0
     */
    public Long getPlatformIdx() {
        return platformIdx;
    }

    /**
     * 平台主键idx，不可以为null，必须手动设置为0
     */
    public void setPlatformIdx(Long platformIdx) {
        this.platformIdx = platformIdx;
    }

    /**
     * 状态主键idx，不可以为null，必须手动设置为0
     */
    public Long getItemStateIdx() {
        return itemStateIdx;
    }

    /**
     * 状态主键idx，不可以为null，必须手动设置为0
     */
    public void setItemStateIdx(Long itemStateIdx) {
        this.itemStateIdx = itemStateIdx;
    }

    /**
     * 商品扩展主键idx，不可以为null，全局唯一递增
     */
    public Long getItemExtIdx() {
        return itemExtIdx;
    }

    /**
     * 商品扩展主键idx，不可以为null，全局唯一递增
     */
    public void setItemExtIdx(Long itemExtIdx) {
        this.itemExtIdx = itemExtIdx;
    }

    /**
     * 货号，14个字符，根据类目与仓库类型来自动生成货号，不可重复，不可以为null
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * 货号，14个字符，根据类目与仓库类型来自动生成货号，不可重复，不可以为null
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    /**
     * 条形码，最多20个字符，不可以为null
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 条形码，最多20个字符，不可以为null
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 中文名称，最多50个字符，不可以为null
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 中文名称，最多50个字符，不可以为null
     */
    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    /**
     * 英文名称，最多200个字符，不可以为null
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 英文名称，最多200个字符，不可以为null
     */
    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    /**
     * 品牌主键idx，不可以为null，必须手动设置为0
     */
    public Long getBrandIdx() {
        return brandIdx;
    }

    /**
     * 品牌主键idx，不可以为null，必须手动设置为0
     */
    public void setBrandIdx(Long brandIdx) {
        this.brandIdx = brandIdx;
    }

    /**
     * 产地主键idx，不可以为null，必须手动设置为0
     */
    public Long getOriginIdx() {
        return originIdx;
    }

    /**
     * 产地主键idx，不可以为null，必须手动设置为0
     */
    public void setOriginIdx(Long originIdx) {
        this.originIdx = originIdx;
    }

    /**
     * 类目主键idx，不可以为null，必须手动设置为0
     */
    public Long getCategoryIdx() {
        return categoryIdx;
    }

    /**
     * 类目主键idx，不可以为null，必须手动设置为0
     */
    public void setCategoryIdx(Long categoryIdx) {
        this.categoryIdx = categoryIdx;
    }

    /**
     * 商品规格，最多100个字符
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 商品规格，最多100个字符
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * 仓库类型主键idx，不可以为null，必须手动设置为0
     */
    public Long getWarehouseTypeIdx() {
        return warehouseTypeIdx;
    }

    /**
     * 仓库类型主键idx，不可以为null，必须手动设置为0
     */
    public void setWarehouseTypeIdx(Long warehouseTypeIdx) {
        this.warehouseTypeIdx = warehouseTypeIdx;
    }

    /**
     * 批次，取值范围0-99999，不可以为null，必须手动设置为0
     */
    public Integer getBatch() {
        return batch;
    }

    /**
     * 批次，取值范围0-99999，不可以为null，必须手动设置为0
     */
    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    /**
     * 采集备注，最多500个字符
     */
    public String getCollectionRemark() {
        return collectionRemark;
    }

    /**
     * 采集备注，最多500个字符
     */
    public void setCollectionRemark(String collectionRemark) {
        this.collectionRemark = collectionRemark == null ? null : collectionRemark.trim();
    }

    /**
     商品小图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageSmallIdDynamicS() {
        return this.imageSmallIdDynamicS;
    }

    /**
     商品小图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageSmallIdDynamicS(String imageSmallIdDynamicS) {
         this.imageSmallIdDynamicS = imageSmallIdDynamicS;
    }

    /**
     * 商品小图文件messageid
     */
    public Long getImageSmallId() {
        return imageSmallId;
    }

    /**
     * 商品小图文件messageid
     */
    public void setImageSmallId(Long imageSmallId) {
        this.imageSmallId = imageSmallId;
    }

    /**
     商品中图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageMiddleIdDynamicS() {
        return this.imageMiddleIdDynamicS;
    }

    /**
     商品中图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageMiddleIdDynamicS(String imageMiddleIdDynamicS) {
         this.imageMiddleIdDynamicS = imageMiddleIdDynamicS;
    }

    /**
     * 商品中图文件messageid
     */
    public Long getImageMiddleId() {
        return imageMiddleId;
    }

    /**
     * 商品中图文件messageid
     */
    public void setImageMiddleId(Long imageMiddleId) {
        this.imageMiddleId = imageMiddleId;
    }

    /**
     商品大图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageBigIdDynamicS() {
        return this.imageBigIdDynamicS;
    }

    /**
     商品大图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageBigIdDynamicS(String imageBigIdDynamicS) {
         this.imageBigIdDynamicS = imageBigIdDynamicS;
    }

    /**
     * 商品大图文件messageid
     */
    public Long getImageBigId() {
        return imageBigId;
    }

    /**
     * 商品大图文件messageid
     */
    public void setImageBigId(Long imageBigId) {
        this.imageBigId = imageBigId;
    }

    /**
     * 采购状态，1：未确认采购，2：确认采购，取值范围0-9，不可以为null，必须手动设置默认值为1
     */
    public Short getPurchaseStatus() {
        return purchaseStatus;
    }

    /**
     * 采购状态，1：未确认采购，2：确认采购，取值范围0-9，不可以为null，必须手动设置默认值为1
     */
    public void setPurchaseStatus(Short purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    /**
     * 采集状态，1：未采集，2：采集完成，3：编辑完成，4：文案通过，取值范围0-9，不可以为null，必须手动设置默认值为1
     */
    public Short getCollectionStatus() {
        return collectionStatus;
    }

    /**
     * 采集状态，1：未采集，2：采集完成，3：编辑完成，4：文案通过，取值范围0-9，不可以为null，必须手动设置默认值为1
     */
    public void setCollectionStatus(Short collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    /**
     * 权重，决定前端商品的排列顺序，不可以为null，必须手动设置为0
     */
    public Long getWeight() {
        return weight;
    }

    /**
     * 权重，决定前端商品的排列顺序，不可以为null，必须手动设置为0
     */
    public void setWeight(Long weight) {
        this.weight = weight;
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
     * 上下架状态，1：上架，2：下架，0：删除，取值范围为0-9，不可以为null
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 上下架状态，1：上架，2：下架，0：删除，取值范围为0-9，不可以为null
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
        sb.append(", idxCode=").append(idxCode);
        sb.append(", platformIdx=").append(platformIdx);
        sb.append(", itemStateIdx=").append(itemStateIdx);
        sb.append(", itemExtIdx=").append(itemExtIdx);
        sb.append(", itemNo=").append(itemNo);
        sb.append(", barcode=").append(barcode);
        sb.append(", cnName=").append(cnName);
        sb.append(", enName=").append(enName);
        sb.append(", brandIdx=").append(brandIdx);
        sb.append(", originIdx=").append(originIdx);
        sb.append(", categoryIdx=").append(categoryIdx);
        sb.append(", specification=").append(specification);
        sb.append(", warehouseTypeIdx=").append(warehouseTypeIdx);
        sb.append(", batch=").append(batch);
        sb.append(", collectionRemark=").append(collectionRemark);
        sb.append(", imageSmallIdDynamicS=").append(imageSmallIdDynamicS);
        sb.append(", imageSmallId=").append(imageSmallId);
        sb.append(", imageMiddleIdDynamicS=").append(imageMiddleIdDynamicS);
        sb.append(", imageMiddleId=").append(imageMiddleId);
        sb.append(", imageBigIdDynamicS=").append(imageBigIdDynamicS);
        sb.append(", imageBigId=").append(imageBigId);
        sb.append(", purchaseStatus=").append(purchaseStatus);
        sb.append(", collectionStatus=").append(collectionStatus);
        sb.append(", weight=").append(weight);
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