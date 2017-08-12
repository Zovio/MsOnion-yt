package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseCatePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class CollectorAttribute implements MsOnionBaseCatePojoAdapter {
    /**
     * 属性主键idx，分布式架构，全局唯一递增
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
     * 父级属性主键idx，不可以为null，必须手动设置为0
     */
    private Long pidx;

    /**
     pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String pidxCodeS;

    /**
     * pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题
     */
    private Long pidxCode;

    /**
     * 属性名称，最多10个字符，不可重复，不可以为null
     */
    @NotNull(message = "属性不能为空")
    @Size(min = 2, max = 10, message = "属性名称，最少2个字符，最多10个字符")
    private String name;

    /**
     * 属性别名，最多60个字符
     */
    @Length(max = 60, message = "属性别名最多60个字符")
    private String attributeAlias;

    /**
     * 属性序号，输入的序号必须为大于0的整数，前端商城显示顺序按此排列。序号越大排序越靠前，相同序号的按照修改时间排序，即排序规则为“顺序+时间”。
     */
    @Range(min = 1, max = 2147483647, message = "属性序号需要设置1-2147483647")
    private Integer attributeSequence;

    /**
     * 是否选项框，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    @NotNull(message = "请选择是否选项框")
    @Range(min = 0, max = 9, message = "是否选项框取值区间0-9")
    private Boolean isSelect;

    /**
     * 是否显示，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    @NotNull(message = "请选择是否显示")
    @Range(min = 0, max = 9, message = "是否显示取值区间0-9")
    private Boolean isShow;

    /**
     * 是否关键属性，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    @NotNull(message = "请选择是否关键属性")
    @Range(min = 0, max = 9, message = "是否关键属性取值区间0-9")
    private Boolean isKey;

    /**
     * 备注，最多50个字符
     */
    @Length(max = 50, message = "备注最大50个字符")
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
     * 这个pid是字符串的，和数据库中的pidx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用pidx（Long）
     * 唯一标识，pidx, JavaScript  对 18位数字pidx支持不好，导致最后一位丢失，所以使用字符串pid
     */
    private String pid;

    /**
     * 属性主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 属性主键idx，分布式架构，全局唯一递增
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
     * 父级属性主键idx，不可以为null，必须手动设置为0
     */
    public Long getPidx() {
        return pidx;
    }

    /**
     * 父级属性主键idx，不可以为null，必须手动设置为0
     */
    public void setPidx(Long pidx) {
        this.pidx = pidx;
    }

    /**
     pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getPidxCodeS() {
        return String.valueOf(this.pidxCode);
    }

    /**
     * pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题
     */
    public Long getPidxCode() {
        return pidxCode;
    }

    /**
     * pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题
     */
    public void setPidxCode(Long pidxCode) {
        this.pidxCode = pidxCode;
    }

    /**
     * 属性名称，最多10个字符，不可重复，不可以为null
     */
    public String getName() {
        return name;
    }

    /**
     * 属性名称，最多10个字符，不可重复，不可以为null
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 属性别名，最多60个字符
     */
    public String getAttributeAlias() {
        return attributeAlias;
    }

    /**
     * 属性别名，最多60个字符
     */
    public void setAttributeAlias(String attributeAlias) {
        this.attributeAlias = attributeAlias == null ? null : attributeAlias.trim();
    }

    /**
     * 属性序号，输入的序号必须为大于0的整数，前端商城显示顺序按此排列。序号越大排序越靠前，相同序号的按照修改时间排序，即排序规则为“顺序+时间”。
     */
    public Integer getAttributeSequence() {
        return attributeSequence;
    }

    /**
     * 属性序号，输入的序号必须为大于0的整数，前端商城显示顺序按此排列。序号越大排序越靠前，相同序号的按照修改时间排序，即排序规则为“顺序+时间”。
     */
    public void setAttributeSequence(Integer attributeSequence) {
        this.attributeSequence = attributeSequence;
    }

    /**
     * 是否选项框，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    public Boolean getIsSelect() {
        return isSelect;
    }

    /**
     * 是否选项框，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    /**
     * 是否显示，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * 是否显示，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * 是否关键属性，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    public Boolean getIsKey() {
        return isKey;
    }

    /**
     * 是否关键属性，1：是，0：否，取值范围为0-9，不可以为null，必须手动设置默认值为0
     */
    public void setIsKey(Boolean isKey) {
        this.isKey = isKey;
    }

    /**
     * 备注，最多50个字符
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，最多50个字符
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

    /**
     * 这个pid是字符串的，和数据库中的pidx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用pid，而是使用pidx（Long）
     * 唯一标识，pidx, JavaScript  对 18位数字pidx支持不好，导致最后一位丢失，所以使用字符串pid
     */
    @Override
    public String getPid() {
        return this.pidx + "";
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
        sb.append(", pidx=").append(pidx);
        sb.append(", pidxCodeS=").append(pidxCodeS);
        sb.append(", pidxCode=").append(pidxCode);
        sb.append(", name=").append(name);
        sb.append(", attributeAlias=").append(attributeAlias);
        sb.append(", attributeSequence=").append(attributeSequence);
        sb.append(", isSelect=").append(isSelect);
        sb.append(", isShow=").append(isShow);
        sb.append(", isKey=").append(isKey);
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
        sb.append(", pid=").append(pid);
        sb.append("]");
        return sb.toString();
    }
}