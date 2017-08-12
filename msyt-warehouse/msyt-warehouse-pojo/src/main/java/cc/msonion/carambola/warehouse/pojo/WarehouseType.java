package cc.msonion.carambola.warehouse.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class WarehouseType implements MsOnionBasePojoAdapter {
    /**
     * 商品仓库类型主键idx，分布式架构，全局唯一递增
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
     * 仓库名称
     */
    @Length(min = 2, max = 50, message = "仓库名称最小2个字符，最大50个字符")
    private String warehouseName;

    /**
     * 仓库编码
     */
    @Range(min = 1, max = 999, message = "仓库编码最小为1，最大为999")
    private Short warehouseCode;

    /**
     * 货号尾数,限制两位大于等于0的整数## @Range(min = 10, max = 99, message = "货号尾数最小为10，最大为99")##
     */
    @Range(min = 10, max = 99, message = "货号尾数最小为10，最大为99")
    private Short mantissa;

    /**
     * 排序
     */
    @Range(max = 100, message = "顺序最大为100")
    private Short zindex;

    /**
     * 备注，最大100个字符
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
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
     * 商品仓库类型主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品仓库类型主键idx，分布式架构，全局唯一递增
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
     * 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    /**
     * 仓库编码
     */
    public Short getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * 仓库编码
     */
    public void setWarehouseCode(Short warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    /**
     * 货号尾数,限制两位大于等于0的整数## @Range(min = 10, max = 99, message = "货号尾数最小为10，最大为99")##
     */
    public Short getMantissa() {
        return mantissa;
    }

    /**
     * 货号尾数,限制两位大于等于0的整数## @Range(min = 10, max = 99, message = "货号尾数最小为10，最大为99")##
     */
    public void setMantissa(Short mantissa) {
        this.mantissa = mantissa;
    }

    /**
     * 排序
     */
    public Short getZindex() {
        return zindex;
    }

    /**
     * 排序
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
    }

    /**
     * 备注，最大100个字符
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，最大100个字符
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
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
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
        sb.append(", warehouseName=").append(warehouseName);
        sb.append(", warehouseCode=").append(warehouseCode);
        sb.append(", mantissa=").append(mantissa);
        sb.append(", zindex=").append(zindex);
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