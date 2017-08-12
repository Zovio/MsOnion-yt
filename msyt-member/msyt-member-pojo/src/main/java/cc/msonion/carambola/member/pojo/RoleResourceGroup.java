package cc.msonion.carambola.member.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;

public class RoleResourceGroup implements MsOnionBasePojoAdapter {
    /**
     * 角色资源主键idx，必须分布式架构，全局唯一递增
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
     * 角色主键idx
     */
    private Long roleIdx;

    /**
     * 资源主键idx
     */
    private Long resourceGroupIdx;

    /**
     * 创建的成员idx
     */
    private Long createByMemberIdx;

    /**
     * 修改的成员idx
     */
    private Long updateByMemberIdx;

    /**
     * 创建时间，第一次创建的时间，后续不可以修改时间
     */
    private Date createTime;

    /**
     * 更新时间，后续修改必须更新时间
     */
    private Date updateTime;

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null，必须手动设置为0
     */
    private Short status;

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null
     */
    private Long version;

    /**
     * 扩展，字符个数0-100，不可以为null，必须手动设置空字符串
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
     * 角色资源主键idx，必须分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 角色资源主键idx，必须分布式架构，全局唯一递增
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
     * 角色主键idx
     */
    public Long getRoleIdx() {
        return roleIdx;
    }

    /**
     * 角色主键idx
     */
    public void setRoleIdx(Long roleIdx) {
        this.roleIdx = roleIdx;
    }

    /**
     * 资源主键idx
     */
    public Long getResourceGroupIdx() {
        return resourceGroupIdx;
    }

    /**
     * 资源主键idx
     */
    public void setResourceGroupIdx(Long resourceGroupIdx) {
        this.resourceGroupIdx = resourceGroupIdx;
    }

    /**
     * 创建的成员idx
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 创建的成员idx
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * 修改的成员idx
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 修改的成员idx
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
     * 创建时间，第一次创建的时间，后续不可以修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间，第一次创建的时间，后续不可以修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，后续修改必须更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，后续修改必须更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null，必须手动设置为0
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null，必须手动设置为0
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 扩展，字符个数0-100，不可以为null，必须手动设置空字符串
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展，字符个数0-100，不可以为null，必须手动设置空字符串
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
        sb.append(", roleIdx=").append(roleIdx);
        sb.append(", resourceGroupIdx=").append(resourceGroupIdx);
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