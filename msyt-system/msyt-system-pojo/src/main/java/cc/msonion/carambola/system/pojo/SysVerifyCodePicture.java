package cc.msonion.carambola.system.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class SysVerifyCodePicture implements MsOnionBasePojoAdapter {
    /**
     * 验证码图片主键idx，必须是分布式架构，全局唯一递增的
     */
    private Long idx;

    /**
     验证码图片主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String idxCodeS;

    /**
     * 验证码图片主键idx生成的code
     */
    private Long idxCode;

    /**
     * 类型，不能为空，1、洋桃供应链后台 2、洋桃商城
     */
    @NotNull(message = "类型不能为空")
    @Range(min = 1, max = 9, message = "类型取值区间1-9")
    private Short type;

    /**
     验证码图片路径，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imagePathDynamicS;

    /**
     * 验证码图片路径
     */
    @NotNull(message = "验证码图片路径不能为空")
    @Length(max = 200, message = "验证码图片路径最大200个字符")
    private String imagePath;

    /**
     * 备注，最多100个字符，不可以为null，必须手动设置为空字符串
     */
    @Length(max = 100, message = "备注最大100个字符")
    private String remark;

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

    /**
     * 扩展字段
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
     * 验证码图片主键idx，必须是分布式架构，全局唯一递增的
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 验证码图片主键idx，必须是分布式架构，全局唯一递增的
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     验证码图片主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getIdxCodeS() {
        return String.valueOf(this.idxCode);
    }

    /**
     * 验证码图片主键idx生成的code
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * 验证码图片主键idx生成的code
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 类型，不能为空，1、洋桃供应链后台 2、洋桃商城
     */
    public Short getType() {
        return type;
    }

    /**
     * 类型，不能为空，1、洋桃供应链后台 2、洋桃商城
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     验证码图片路径，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImagePathDynamicS() {
        return this.imagePathDynamicS;
    }

    /**
     验证码图片路径，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImagePathDynamicS(String imagePathDynamicS) {
         this.imagePathDynamicS = imagePathDynamicS;
    }

    /**
     * 验证码图片路径
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 验证码图片路径
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
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
     * 扩展字段
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展字段
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
        sb.append(", type=").append(type);
        sb.append(", imagePathDynamicS=").append(imagePathDynamicS);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}