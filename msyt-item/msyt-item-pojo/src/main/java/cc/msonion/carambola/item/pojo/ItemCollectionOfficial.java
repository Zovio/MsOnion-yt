package cc.msonion.carambola.item.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ItemCollectionOfficial implements MsOnionBasePojoAdapter {
    /**
     * 正式商品采集主键idx，分布式架构，全局唯一递增
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
     * 卖点，最多20个字符
     */
    @Length(max = 20, message = "卖点最多20个字符")
    private String sellingPoint;

    /**
     * 广告语，最多50个字符
     */
    @Length(max = 50, message = "广告语最多50个字符")
    private String slogan;

    /**
     * 导购语，最多300个字符
     */
    @Length(max = 300, message = "导购语最多300个字符")
    private String shoppingGuide;

    /**
     * 采编主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "采编不能为空")
    private Long editorIdx;

    /**
     * 搜索关键词，最多100个字符
     */
    @Length(max = 100, message = "搜索关键词最多100个字符")
    private String searchKeywords;

    /**
     附件文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String attachmentIdDynamicS;

    /**
     * 附件文件messageid
     */
    private Long attachmentId;

    /**
     * 视频链接，最多200个字符
     */
    private String videoLink;

    /**
     * 编辑类型，1：一次编辑，2：二次编辑，取值范围1-9，不可以为null
     */
    private Short editType;

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
     * 正式商品采集主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 正式商品采集主键idx，分布式架构，全局唯一递增
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
     * 卖点，最多20个字符
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * 卖点，最多20个字符
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint == null ? null : sellingPoint.trim();
    }

    /**
     * 广告语，最多50个字符
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * 广告语，最多50个字符
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    /**
     * 导购语，最多300个字符
     */
    public String getShoppingGuide() {
        return shoppingGuide;
    }

    /**
     * 导购语，最多300个字符
     */
    public void setShoppingGuide(String shoppingGuide) {
        this.shoppingGuide = shoppingGuide == null ? null : shoppingGuide.trim();
    }

    /**
     * 采编主键idx，不可以为null，必须手动设置为0
     */
    public Long getEditorIdx() {
        return editorIdx;
    }

    /**
     * 采编主键idx，不可以为null，必须手动设置为0
     */
    public void setEditorIdx(Long editorIdx) {
        this.editorIdx = editorIdx;
    }

    /**
     * 搜索关键词，最多100个字符
     */
    public String getSearchKeywords() {
        return searchKeywords;
    }

    /**
     * 搜索关键词，最多100个字符
     */
    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords == null ? null : searchKeywords.trim();
    }

    /**
     附件文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getAttachmentIdDynamicS() {
        return this.attachmentIdDynamicS;
    }

    /**
     附件文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setAttachmentIdDynamicS(String attachmentIdDynamicS) {
         this.attachmentIdDynamicS = attachmentIdDynamicS;
    }

    /**
     * 附件文件messageid
     */
    public Long getAttachmentId() {
        return attachmentId;
    }

    /**
     * 附件文件messageid
     */
    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * 视频链接，最多200个字符
     */
    public String getVideoLink() {
        return videoLink;
    }

    /**
     * 视频链接，最多200个字符
     */
    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink == null ? null : videoLink.trim();
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
        sb.append(", sellingPoint=").append(sellingPoint);
        sb.append(", slogan=").append(slogan);
        sb.append(", shoppingGuide=").append(shoppingGuide);
        sb.append(", editorIdx=").append(editorIdx);
        sb.append(", searchKeywords=").append(searchKeywords);
        sb.append(", attachmentIdDynamicS=").append(attachmentIdDynamicS);
        sb.append(", attachmentId=").append(attachmentId);
        sb.append(", videoLink=").append(videoLink);
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