package cc.msonion.carambola.member.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseCatePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class MemberCate implements MsOnionBaseCatePojoAdapter {
    /**
     * 成员类别（分类、类型）主键idx
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
     * 成员父级类别pidx，暂时不使用，后续扩展使用，不可以为null，可以默认为0
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
     * 成员类别的名称，字符数0-10，不可以为null
     */
    @NotNull(message = "用户类别名称不能为空")
    @Length(max = 10, message = "用户类别名称最大为10")
    private String name;

    /**
     * 备注，字符数为0-100个（包括中英文），不可以为null，默认都要设置为空字符串
     */
    @Length(max = 100, message = "备注字符数最大为100")
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 版本号，高并发，乐观锁解决方案，不可以为null
     */
    private Long version;

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    private Short status;

    /**
     * 被哪位成员（member_idx）创建的，不可以为null
     */
    private Long createByMemberId;

    /**
     * 被哪位成员（member_idx）修改的，不可以为null
     */
    private Long updateByMemberId;

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
     * 成员类别（分类、类型）主键idx
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 成员类别（分类、类型）主键idx
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
     * 成员父级类别pidx，暂时不使用，后续扩展使用，不可以为null，可以默认为0
     */
    public Long getPidx() {
        return pidx;
    }

    /**
     * 成员父级类别pidx，暂时不使用，后续扩展使用，不可以为null，可以默认为0
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
     * 成员类别的名称，字符数0-10，不可以为null
     */
    public String getName() {
        return name;
    }

    /**
     * 成员类别的名称，字符数0-10，不可以为null
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 备注，字符数为0-100个（包括中英文），不可以为null，默认都要设置为空字符串
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，字符数为0-100个（包括中英文），不可以为null，默认都要设置为空字符串
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 版本号，高并发，乐观锁解决方案，不可以为null
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观锁解决方案，不可以为null
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 被哪位成员（member_idx）创建的，不可以为null
     */
    public Long getCreateByMemberId() {
        return createByMemberId;
    }

    /**
     * 被哪位成员（member_idx）创建的，不可以为null
     */
    public void setCreateByMemberId(Long createByMemberId) {
        this.createByMemberId = createByMemberId;
    }

    /**
     * 被哪位成员（member_idx）修改的，不可以为null
     */
    public Long getUpdateByMemberId() {
        return updateByMemberId;
    }

    /**
     * 被哪位成员（member_idx）修改的，不可以为null
     */
    public void setUpdateByMemberId(Long updateByMemberId) {
        this.updateByMemberId = updateByMemberId;
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
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append(", status=").append(status);
        sb.append(", createByMemberId=").append(createByMemberId);
        sb.append(", updateByMemberId=").append(updateByMemberId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append("]");
        return sb.toString();
    }
}