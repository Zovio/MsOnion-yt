package cc.msonion.carambola.fileresource.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseCatePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AblumLib implements MsOnionBaseCatePojoAdapter {
    /**
     * 相册库主键idx，必须是分布式架构，全局唯一递增的
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
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;

    /**
     * 父级idx
     */
    private Long pidx;

    /**
     * 父级名称，冗余一下
     */
    private String parentName;

    /**
     * 相对路径## @Length(max = 100, message = "父级名称最大100个字符")##
     */
    @Length(max = 100, message = "父级名称最大100个字符")
    private String relativePath;

    /**
     * 相册类型 1:采集相册；2:正式相册
     */
    private Short ablumType;

    /**
     * 文件总数量
     */
    private Integer totalNum;

    /**
     * 文件目录级别 1:一级目录,2：二级目录 ...，以此类推
     */
    private Short category;

    /**
     * 文件总大小
     */
    private Long totalSize;

    /**
     * 文件idx，如果是文件夹就不需要保存，文件时需要保存
     */
    private Long fileIdx;

    /**
     * 目录类型 0:目录 1:文件
     */
    private Short dirType;

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
     * 被哪个成员修改
     */
    private Long updateByMemberIdx;

    /**
    创建时间，第一次创建时间，后续不可以修改时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String createTimeEnYyyyMMddHHmmss;

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    private Date createTime;

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
     * 这个pid是字符串的，和数据库中的pidx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用pidx（Long）
     * 唯一标识，pidx, JavaScript  对 18位数字pidx支持不好，导致最后一位丢失，所以使用字符串pid
     */
    private String pid;

    /**
     * 相册库主键idx，必须是分布式架构，全局唯一递增的
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 相册库主键idx，必须是分布式架构，全局唯一递增的
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
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父级idx
     */
    public Long getPidx() {
        return pidx;
    }

    /**
     * 父级idx
     */
    public void setPidx(Long pidx) {
        this.pidx = pidx;
    }

    /**
     * 父级名称，冗余一下
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 父级名称，冗余一下
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    /**
     * 相对路径## @Length(max = 100, message = "父级名称最大100个字符")##
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * 相对路径## @Length(max = 100, message = "父级名称最大100个字符")##
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath == null ? null : relativePath.trim();
    }

    /**
     * 相册类型 1:采集相册；2:正式相册
     */
    public Short getAblumType() {
        return ablumType;
    }

    /**
     * 相册类型 1:采集相册；2:正式相册
     */
    public void setAblumType(Short ablumType) {
        this.ablumType = ablumType;
    }

    /**
     * 文件总数量
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 文件总数量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 文件目录级别 1:一级目录,2：二级目录 ...，以此类推
     */
    public Short getCategory() {
        return category;
    }

    /**
     * 文件目录级别 1:一级目录,2：二级目录 ...，以此类推
     */
    public void setCategory(Short category) {
        this.category = category;
    }

    /**
     * 文件总大小
     */
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * 文件总大小
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * 文件idx，如果是文件夹就不需要保存，文件时需要保存
     */
    public Long getFileIdx() {
        return fileIdx;
    }

    /**
     * 文件idx，如果是文件夹就不需要保存，文件时需要保存
     */
    public void setFileIdx(Long fileIdx) {
        this.fileIdx = fileIdx;
    }

    /**
     * 目录类型 0:目录 1:文件
     */
    public Short getDirType() {
        return dirType;
    }

    /**
     * 目录类型 0:目录 1:文件
     */
    public void setDirType(Short dirType) {
        this.dirType = dirType;
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
    创建时间，第一次创建时间，后续不可以修改时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getCreateTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.createTime) ;
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
        sb.append(", name=").append(name);
        sb.append(", pidx=").append(pidx);
        sb.append(", parentName=").append(parentName);
        sb.append(", relativePath=").append(relativePath);
        sb.append(", ablumType=").append(ablumType);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", category=").append(category);
        sb.append(", totalSize=").append(totalSize);
        sb.append(", fileIdx=").append(fileIdx);
        sb.append(", dirType=").append(dirType);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTimeEnYyyyMMddHHmmss=").append(createTimeEnYyyyMMddHHmmss);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append("]");
        return sb.toString();
    }
}