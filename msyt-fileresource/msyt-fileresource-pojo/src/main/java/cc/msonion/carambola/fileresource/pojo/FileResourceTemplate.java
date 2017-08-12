package cc.msonion.carambola.fileresource.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

public class FileResourceTemplate implements MsOnionBasePojoAdapter {
    /**
     * 文件模板主键idx，必须是分布式架构，全局唯一递增的
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
     * 模板标题
     */
    @Length(min = 2, max = 50, message = "模板标题最小2个字符，最大50个字符")
    private String title;

    /**
     * 模板类别## @Range(min = 1, max = 100, message = "模板类别不能为空，最大为100")##
     */
    @Range(min = 1, max = 100, message = "模板类别不能为空，最大为100")
    private Short categoryId;

    /**
     * 应用平台,不能为空
     */
    @Range(min = 1, max = 100, message = "应用平台不能为空，最大为100")
    private Short appPlatform;

    /**
     文件messageid，不能为空，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String fileMessageIdDynamicS;

    /**
     * 文件messageid，不能为空
     */
    @Range(min = 1,  message = "文件不能为空")
    private Long fileMessageId;

    /**
     * 源文件名称
     */
    @Length(max = 150, message = "文件名称最大150个字符")
    private String fileName;

    /**
     * 备注，不能为null
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
     被哪个成员修改，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String updateByMemberIdxDynamicS;

    /**
     * 被哪个成员修改
     */
    private Long updateByMemberIdx;

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    private Date createTime;

    /**
    更新时间，每次修改，都要更新时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String updateTimeEnYyyyMMddHHmmss;

    /**
     * 更新时间，每次修改，都要更新时间
     */
    private Date updateTime;

    /**
     * 扩展字段，最多100个字符，不能为null
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
     * 文件模板主键idx，必须是分布式架构，全局唯一递增的
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 文件模板主键idx，必须是分布式架构，全局唯一递增的
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
     * 模板标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 模板标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 模板类别## @Range(min = 1, max = 100, message = "模板类别不能为空，最大为100")##
     */
    public Short getCategoryId() {
        return categoryId;
    }

    /**
     * 模板类别## @Range(min = 1, max = 100, message = "模板类别不能为空，最大为100")##
     */
    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 应用平台,不能为空
     */
    public Short getAppPlatform() {
        return appPlatform;
    }

    /**
     * 应用平台,不能为空
     */
    public void setAppPlatform(Short appPlatform) {
        this.appPlatform = appPlatform;
    }

    /**
     文件messageid，不能为空，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getFileMessageIdDynamicS() {
        return this.fileMessageIdDynamicS;
    }

    /**
     文件messageid，不能为空，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setFileMessageIdDynamicS(String fileMessageIdDynamicS) {
         this.fileMessageIdDynamicS = fileMessageIdDynamicS;
    }

    /**
     * 文件messageid，不能为空
     */
    public Long getFileMessageId() {
        return fileMessageId;
    }

    /**
     * 文件messageid，不能为空
     */
    public void setFileMessageId(Long fileMessageId) {
        this.fileMessageId = fileMessageId;
    }

    /**
     * 源文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 源文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 备注，不能为null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，不能为null
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
     被哪个成员修改，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getUpdateByMemberIdxDynamicS() {
        return this.updateByMemberIdxDynamicS;
    }

    /**
     被哪个成员修改，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setUpdateByMemberIdxDynamicS(String updateByMemberIdxDynamicS) {
         this.updateByMemberIdxDynamicS = updateByMemberIdxDynamicS;
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
    更新时间，每次修改，都要更新时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getUpdateTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.updateTime) ;
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
     * 扩展字段，最多100个字符，不能为null
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展字段，最多100个字符，不能为null
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
        sb.append(", title=").append(title);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", appPlatform=").append(appPlatform);
        sb.append(", fileMessageIdDynamicS=").append(fileMessageIdDynamicS);
        sb.append(", fileMessageId=").append(fileMessageId);
        sb.append(", fileName=").append(fileName);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdxDynamicS=").append(updateByMemberIdxDynamicS);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTimeEnYyyyMMddHHmmss=").append(updateTimeEnYyyyMMddHHmmss);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}