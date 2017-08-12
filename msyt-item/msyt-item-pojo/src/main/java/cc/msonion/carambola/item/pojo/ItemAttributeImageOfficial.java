package cc.msonion.carambola.item.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class ItemAttributeImageOfficial implements MsOnionBasePojoAdapter {
    /**
     * 正式商品属性图片主键idx，分布式架构，全局唯一递增
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
     * 正式商品主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "商品不能为空")
    private Long itemOfficialIdx;

    /**
     * 属性主键idx， 不可以为null，必须手动设置默认值为0
     */
    private Long attributeIdx;

    /**
     商品属性小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageSmallDynamicS;

    /**
     * 商品属性小图文件
     */
    private String imageSmall;

    /**
     商品属性中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageMiddleDynamicS;

    /**
     * 商品属性中图文件
     */
    private String imageMiddle;

    /**
     商品属性大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageBigDynamicS;

    /**
     * 商品属性大图文件
     */
    private String imageBig;

    /**
     * 备注，最多200个字符
     */
    private String remark;

    /**
     * 编辑类型，1：一次编辑，2：二次编辑，取值范围1-9，不可以为null
     */
    private Short editType;

    /**
     * 创建成员，第一次新增时创建，后期不可以更改
     */
    private Long createByMemberIdx;

    /**
     * 修改成员，每一次修改都要记录
     */
    private Long updateByMemberIdx;

    /**
     * 创建时间，新增时赋值当前时间，不可以更改
     */
    private Date createTime;

    /**
     * 修改时间
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
     * 正式商品属性图片主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 正式商品属性图片主键idx，分布式架构，全局唯一递增
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
     * 正式商品主键idx，不可以为null，必须手动设置为0
     */
    public Long getItemOfficialIdx() {
        return itemOfficialIdx;
    }

    /**
     * 正式商品主键idx，不可以为null，必须手动设置为0
     */
    public void setItemOfficialIdx(Long itemOfficialIdx) {
        this.itemOfficialIdx = itemOfficialIdx;
    }

    /**
     * 属性主键idx， 不可以为null，必须手动设置默认值为0
     */
    public Long getAttributeIdx() {
        return attributeIdx;
    }

    /**
     * 属性主键idx， 不可以为null，必须手动设置默认值为0
     */
    public void setAttributeIdx(Long attributeIdx) {
        this.attributeIdx = attributeIdx;
    }

    /**
     商品属性小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageSmallDynamicS() {
        return this.imageSmallDynamicS;
    }

    /**
     商品属性小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageSmallDynamicS(String imageSmallDynamicS) {
         this.imageSmallDynamicS = imageSmallDynamicS;
    }

    /**
     * 商品属性小图文件
     */
    public String getImageSmall() {
        return imageSmall;
    }

    /**
     * 商品属性小图文件
     */
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall == null ? null : imageSmall.trim();
    }

    /**
     商品属性中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageMiddleDynamicS() {
        return this.imageMiddleDynamicS;
    }

    /**
     商品属性中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageMiddleDynamicS(String imageMiddleDynamicS) {
         this.imageMiddleDynamicS = imageMiddleDynamicS;
    }

    /**
     * 商品属性中图文件
     */
    public String getImageMiddle() {
        return imageMiddle;
    }

    /**
     * 商品属性中图文件
     */
    public void setImageMiddle(String imageMiddle) {
        this.imageMiddle = imageMiddle == null ? null : imageMiddle.trim();
    }

    /**
     商品属性大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageBigDynamicS() {
        return this.imageBigDynamicS;
    }

    /**
     商品属性大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageBigDynamicS(String imageBigDynamicS) {
         this.imageBigDynamicS = imageBigDynamicS;
    }

    /**
     * 商品属性大图文件
     */
    public String getImageBig() {
        return imageBig;
    }

    /**
     * 商品属性大图文件
     */
    public void setImageBig(String imageBig) {
        this.imageBig = imageBig == null ? null : imageBig.trim();
    }

    /**
     * 备注，最多200个字符
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，最多200个字符
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 编辑类型，1：一次编辑，2：二次编辑，取值范围1-9，不可以为null
     */
    public Short getEditType() {
        return editType;
    }

    /**
     * 编辑类型，1：一次编辑，2：二次编辑，取值范围1-9，不可以为null
     */
    public void setEditType(Short editType) {
        this.editType = editType;
    }

    /**
     * 创建成员，第一次新增时创建，后期不可以更改
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 创建成员，第一次新增时创建，后期不可以更改
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * 修改成员，每一次修改都要记录
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 修改成员，每一次修改都要记录
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
     * 创建时间，新增时赋值当前时间，不可以更改
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间，新增时赋值当前时间，不可以更改
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
        sb.append(", itemOfficialIdx=").append(itemOfficialIdx);
        sb.append(", attributeIdx=").append(attributeIdx);
        sb.append(", imageSmallDynamicS=").append(imageSmallDynamicS);
        sb.append(", imageSmall=").append(imageSmall);
        sb.append(", imageMiddleDynamicS=").append(imageMiddleDynamicS);
        sb.append(", imageMiddle=").append(imageMiddle);
        sb.append(", imageBigDynamicS=").append(imageBigDynamicS);
        sb.append(", imageBig=").append(imageBig);
        sb.append(", remark=").append(remark);
        sb.append(", editType=").append(editType);
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