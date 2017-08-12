package cc.msonion.carambola.content.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class ContentImage implements MsOnionBasePojoAdapter {
    /**
     * 内容图片主键idx，分布式架构，全局唯一递增
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
     * 内容主键idx，不可以为null0
     */
    @NotNull(message = "内容主键不能为空")
    private Long contentIdx;

    /**
     * 图片名称，最多50个字符，不可以为null
     */
    @NotNull(message = "图片名称不能为空")
    @Length(max = 50, message = "图片名称最多50个字符")
    private String name;

    /**
     * 图片标题，最多50个字符，不可以为null
     */
    @NotNull(message = "图片标题不能为空")
    @Length(max = 50, message = "图片标题最多50个字符")
    private String title;

    /**
     * 图片链接地址，最多200个字符
     */
    @Length(max = 200, message = "图片链接地址最多200个字符")
    private String linkUrl;

    /**
     * 排序号，升序排列，不可以为null，必须手动设置默认值为0
     */
    @NotNull(message = "排序号不能为空")
    @Range(min = 0, max = 10, message = "排序号")
    private Integer orderNumber;

    /**
     * 小图，最多255个字符
     */
    @Length(max = 255, message = "小图最多255个字符")
    private String imageSmall;

    /**
     * 中图，最多255个字符
     */
    @Length(max = 255, message = "中图最多255个字符")
    private String imageMiddle;

    /**
     * 大图，最多255个字符
     */
    @Length(max = 255, message = "大图最多255个字符")
    private String imageBig;

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
     * 备注
     */
    @NotNull(message = "备注不能为空")
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
     * 内容图片主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 内容图片主键idx，分布式架构，全局唯一递增
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
     * 内容主键idx，不可以为null0
     */
    public Long getContentIdx() {
        return contentIdx;
    }

    /**
     * 内容主键idx，不可以为null0
     */
    public void setContentIdx(Long contentIdx) {
        this.contentIdx = contentIdx;
    }

    /**
     * 图片名称，最多50个字符，不可以为null
     */
    public String getName() {
        return name;
    }

    /**
     * 图片名称，最多50个字符，不可以为null
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 图片标题，最多50个字符，不可以为null
     */
    public String getTitle() {
        return title;
    }

    /**
     * 图片标题，最多50个字符，不可以为null
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 图片链接地址，最多200个字符
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 图片链接地址，最多200个字符
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 排序号，升序排列，不可以为null，必须手动设置默认值为0
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * 排序号，升序排列，不可以为null，必须手动设置默认值为0
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 小图，最多255个字符
     */
    public String getImageSmall() {
        return imageSmall;
    }

    /**
     * 小图，最多255个字符
     */
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall == null ? null : imageSmall.trim();
    }

    /**
     * 中图，最多255个字符
     */
    public String getImageMiddle() {
        return imageMiddle;
    }

    /**
     * 中图，最多255个字符
     */
    public void setImageMiddle(String imageMiddle) {
        this.imageMiddle = imageMiddle == null ? null : imageMiddle.trim();
    }

    /**
     * 大图，最多255个字符
     */
    public String getImageBig() {
        return imageBig;
    }

    /**
     * 大图，最多255个字符
     */
    public void setImageBig(String imageBig) {
        this.imageBig = imageBig == null ? null : imageBig.trim();
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
        sb.append(", contentIdx=").append(contentIdx);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", imageSmall=").append(imageSmall);
        sb.append(", imageMiddle=").append(imageMiddle);
        sb.append(", imageBig=").append(imageBig);
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