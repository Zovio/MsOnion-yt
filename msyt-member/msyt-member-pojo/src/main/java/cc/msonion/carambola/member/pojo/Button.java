package cc.msonion.carambola.member.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class Button implements MsOnionBasePojoAdapter {
    /**
     * 按钮主键idx，必须是分布式架构，全局唯一递增的
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
     * 按钮名称，最多10个字符，最少2个字符
     */
    @NotNull(message = "按钮名称不能为空")
    @Size(min = 2, max = 10, message = "按钮名称，最少2个字符,最多10个字符")
    private String name;

    /**
     * 按钮码，暂时不使用，后续扩展使用，不可以为null，必须手动赋值为空字符串
     */
    @Length(min = 2, max = 30, message = "按钮编码最少2个字符，最大30个字符")
    private String code;

    /**
     * 菜单主键idx，也就是当前按钮属于哪个菜单的，如果为0表示当前按钮属于后台首页，不可以为null，必须设置为0
     */
    @Range(min = 1, message = "所属菜单不能为空")
    private Long menuIdx;

    /**
     * 按钮的Icon图片的Url，最多200个字符串，不可以为null，必须手动设置为空字符串
     */
    private String iconUrl;

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

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    private Short status;

    /**
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    private Long version;

    /**
     * 顺序，暂时不使用，后续扩展使用，取值范围0-760，不可以为null，必须手动设置为0
     */
    private Short zindex;

    /**
     * 备注，最多20个字符，不可以为null，必须手动设置为空字符串
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    /**
     * 按钮主键idx，必须是分布式架构，全局唯一递增的
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 按钮主键idx，必须是分布式架构，全局唯一递增的
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
     * 按钮名称，最多10个字符，最少2个字符
     */
    public String getName() {
        return name;
    }

    /**
     * 按钮名称，最多10个字符，最少2个字符
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 按钮码，暂时不使用，后续扩展使用，不可以为null，必须手动赋值为空字符串
     */
    public String getCode() {
        return code;
    }

    /**
     * 按钮码，暂时不使用，后续扩展使用，不可以为null，必须手动赋值为空字符串
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 菜单主键idx，也就是当前按钮属于哪个菜单的，如果为0表示当前按钮属于后台首页，不可以为null，必须设置为0
     */
    public Long getMenuIdx() {
        return menuIdx;
    }

    /**
     * 菜单主键idx，也就是当前按钮属于哪个菜单的，如果为0表示当前按钮属于后台首页，不可以为null，必须设置为0
     */
    public void setMenuIdx(Long menuIdx) {
        this.menuIdx = menuIdx;
    }

    /**
     * 按钮的Icon图片的Url，最多200个字符串，不可以为null，必须手动设置为空字符串
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 按钮的Icon图片的Url，最多200个字符串，不可以为null，必须手动设置为空字符串
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
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
     * 顺序，暂时不使用，后续扩展使用，取值范围0-760，不可以为null，必须手动设置为0
     */
    public Short getZindex() {
        return zindex;
    }

    /**
     * 顺序，暂时不使用，后续扩展使用，取值范围0-760，不可以为null，必须手动设置为0
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
    }

    /**
     * 备注，最多20个字符，不可以为null，必须手动设置为空字符串
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，最多20个字符，不可以为null，必须手动设置为空字符串
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", menuIdx=").append(menuIdx);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", zindex=").append(zindex);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}