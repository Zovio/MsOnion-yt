package cc.msonion.carambola.content.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class Content implements MsOnionBasePojoAdapter {
    /**
     * 内容主键idx，分布式架构，全局唯一递增
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
     * 唯一标识码，最多20个字符，不可以为null
     */
    @NotNull(message = "唯一标识码不能为空")
    @Length(max = 20, message = "唯一标识码最多20个字符")
    private String code;

    /**
     * 名称，最多50个字符，不可以为null
     */
    @NotNull(message = "名称不能为空")
    @Length(max = 50, message = "名称最多50个字符")
    private String name;

    /**
     * 类型，不可以为null，1：html，2：多图
     */
    @NotNull(message = "类型不能为空")
    @Range(min = 1, max = 2, message = "类型取值区间1-2")
    private Short type;

    /**
     * HTML内容，最多12000个字符，不可以为null
     */
    @NotNull(message = "HTML内容不能为空")
    @Length(max = 12000, message = "HTML内容最多12000个字符")
    private String content;

    /**
    有效开始时间，不可以为null，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String startTimeEnYyyyMMddHHmmss;

    /**
     * 有效开始时间，不可以为null
     */
    @NotNull(message = "有效开始时间不能为空")
    private Date startTime;

    /**
    有效结束时间，不可以为null，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String endTimeEnYyyyMMddHHmmss;

    /**
     * 有效结束时间，不可以为null
     */
    @NotNull(message = "有效结束时间不能为空")
    private Date endTime;

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
     * 备注
     */
    @Length(max = 50, message = "唯一标识码最多50个字符")
    private String remark;

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
     * 内容主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 内容主键idx，分布式架构，全局唯一递增
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
     * 唯一标识码，最多20个字符，不可以为null
     */
    public String getCode() {
        return code;
    }

    /**
     * 唯一标识码，最多20个字符，不可以为null
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称，最多50个字符，不可以为null
     */
    public String getName() {
        return name;
    }

    /**
     * 名称，最多50个字符，不可以为null
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 类型，不可以为null，1：html，2：多图
     */
    public Short getType() {
        return type;
    }

    /**
     * 类型，不可以为null，1：html，2：多图
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * HTML内容，最多12000个字符，不可以为null
     */
    public String getContent() {
        return content;
    }

    /**
     * HTML内容，最多12000个字符，不可以为null
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
    有效开始时间，不可以为null，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getStartTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.startTime) ;
    }

    /**
     * 有效开始时间，不可以为null
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 有效开始时间，不可以为null
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
    有效结束时间，不可以为null，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getEndTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.endTime) ;
    }

    /**
     * 有效结束时间，不可以为null
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 有效结束时间，不可以为null
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", startTimeEnYyyyMMddHHmmss=").append(startTimeEnYyyyMMddHHmmss);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTimeEnYyyyMMddHHmmss=").append(endTimeEnYyyyMMddHHmmss);
        sb.append(", endTime=").append(endTime);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", remark=").append(remark);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}