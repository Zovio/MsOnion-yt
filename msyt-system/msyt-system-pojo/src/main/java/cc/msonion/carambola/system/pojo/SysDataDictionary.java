package cc.msonion.carambola.system.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class SysDataDictionary implements MsOnionBasePojoAdapter {
    /**
     * 系统数据字典主键idx，必须是分布式架构，全局唯一递增的
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
     * 数据字典编码，不可以为null，必须手动赋值为空字符串## @NotNull(message = "字典编码不能为空")##
     */
    @NotNull(message = "字典编码不能为空")
    private String dictCode;

    /**
     * 数据字典值，不可以为null，必须手动赋值为空字符## @NotNull(message = "字典值不能为空")@Length(min = 1, max = 50, message = "字典值最小1个字符，最大50个字符")##
     */
    @NotNull(message = "字典值不能为空")
    @Length(min = 1, max = 50, message = "字典值最小1个字符，最大50个字符")
    private String dictKey;

    /**
     * 数据字典名称，不可以为null，必须手动赋值为空字符串##  @NotNull(message = "字典名称不能为空")@Length(min = 1, max = 50, message = "字典名称最小1个字符，最大50个字符")##
     */
    @NotNull(message = "字典名称不能为空")
    @Length(min = 1, max = 50, message = "字典名称最小1个字符，最大50个字符")
    private String dictValue;

    /**
     * 备注，最多100个字符，不可以为null，必须手动设置为空字符串
     */
    @Length(max = 100, message = "备注最大100个字符")
    private String remark;

    /**
     * 顺序，必须手动设置为0 ## @Range(max = 100, message = "顺序最大为100")##
     */
    @Range(max = 100, message = "顺序最大为100")
    private Short zindex;

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    private Short status;

    /**
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    private Long version;

    /**
     * 被哪个成员创建
     */
    private Long createByMemberIdx;

    /**
     * 被哪个成员修改
     */
    private Long updateByMemberIdx;

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    private Date createTime;

    /**
     * 更新时间，每次修改，都要更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    /**
     * 系统数据字典主键idx，必须是分布式架构，全局唯一递增的
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 系统数据字典主键idx，必须是分布式架构，全局唯一递增的
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
     * 数据字典编码，不可以为null，必须手动赋值为空字符串## @NotNull(message = "字典编码不能为空")##
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 数据字典编码，不可以为null，必须手动赋值为空字符串## @NotNull(message = "字典编码不能为空")##
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     * 数据字典值，不可以为null，必须手动赋值为空字符## @NotNull(message = "字典值不能为空")@Length(min = 1, max = 50, message = "字典值最小1个字符，最大50个字符")##
     */
    public String getDictKey() {
        return dictKey;
    }

    /**
     * 数据字典值，不可以为null，必须手动赋值为空字符## @NotNull(message = "字典值不能为空")@Length(min = 1, max = 50, message = "字典值最小1个字符，最大50个字符")##
     */
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    /**
     * 数据字典名称，不可以为null，必须手动赋值为空字符串##  @NotNull(message = "字典名称不能为空")@Length(min = 1, max = 50, message = "字典名称最小1个字符，最大50个字符")##
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 数据字典名称，不可以为null，必须手动赋值为空字符串##  @NotNull(message = "字典名称不能为空")@Length(min = 1, max = 50, message = "字典名称最小1个字符，最大50个字符")##
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    /**
     * 备注，最多100个字符，不可以为null，必须手动设置为空字符串
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，最多100个字符，不可以为null，必须手动设置为空字符串
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 顺序，必须手动设置为0 ## @Range(max = 100, message = "顺序最大为100")##
     */
    public Short getZindex() {
        return zindex;
    }

    /**
     * 顺序，必须手动设置为0 ## @Range(max = 100, message = "顺序最大为100")##
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
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
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 被哪个成员创建
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 被哪个成员创建
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * 被哪个成员修改
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 被哪个成员修改
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
     * 更新时间，每次修改，都要更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，每次修改，都要更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", dictCode=").append(dictCode);
        sb.append(", dictKey=").append(dictKey);
        sb.append(", dictValue=").append(dictValue);
        sb.append(", remark=").append(remark);
        sb.append(", zindex=").append(zindex);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}