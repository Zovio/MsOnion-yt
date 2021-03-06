package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CollectorItemEditor implements MsOnionBasePojoAdapter {
    /**
     * 商品采编主键idx，分布式架构，全局唯一递增
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
     * 采编名称，最多20个字符，不可以为null
     */
    @NotNull(message = "采编名称不能为空")
    @Length(max = 20, message = "采编名称最多20个字符")
    private String name;

    /**
     头像小图，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String headImgSmallDynamicS;

    /**
     * 头像小图
     */
    private String headImgSmall;

    /**
     头像中图，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String headImgMiddleDynamicS;

    /**
     * 头像中图
     */
    private String headImgMiddle;

    /**
     头像大图，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String headImgBigDynamicS;

    /**
     * 头像大图
     */
    private String headImgBig;

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
     * 商品采编主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品采编主键idx，分布式架构，全局唯一递增
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
     * 采编名称，最多20个字符，不可以为null
     */
    public String getName() {
        return name;
    }

    /**
     * 采编名称，最多20个字符，不可以为null
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     头像小图，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getHeadImgSmallDynamicS() {
        return this.headImgSmallDynamicS;
    }

    /**
     头像小图，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setHeadImgSmallDynamicS(String headImgSmallDynamicS) {
         this.headImgSmallDynamicS = headImgSmallDynamicS;
    }

    /**
     * 头像小图
     */
    public String getHeadImgSmall() {
        return headImgSmall;
    }

    /**
     * 头像小图
     */
    public void setHeadImgSmall(String headImgSmall) {
        this.headImgSmall = headImgSmall == null ? null : headImgSmall.trim();
    }

    /**
     头像中图，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getHeadImgMiddleDynamicS() {
        return this.headImgMiddleDynamicS;
    }

    /**
     头像中图，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setHeadImgMiddleDynamicS(String headImgMiddleDynamicS) {
         this.headImgMiddleDynamicS = headImgMiddleDynamicS;
    }

    /**
     * 头像中图
     */
    public String getHeadImgMiddle() {
        return headImgMiddle;
    }

    /**
     * 头像中图
     */
    public void setHeadImgMiddle(String headImgMiddle) {
        this.headImgMiddle = headImgMiddle == null ? null : headImgMiddle.trim();
    }

    /**
     头像大图，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getHeadImgBigDynamicS() {
        return this.headImgBigDynamicS;
    }

    /**
     头像大图，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setHeadImgBigDynamicS(String headImgBigDynamicS) {
         this.headImgBigDynamicS = headImgBigDynamicS;
    }

    /**
     * 头像大图
     */
    public String getHeadImgBig() {
        return headImgBig;
    }

    /**
     * 头像大图
     */
    public void setHeadImgBig(String headImgBig) {
        this.headImgBig = headImgBig == null ? null : headImgBig.trim();
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
        sb.append(", name=").append(name);
        sb.append(", headImgSmallDynamicS=").append(headImgSmallDynamicS);
        sb.append(", headImgSmall=").append(headImgSmall);
        sb.append(", headImgMiddleDynamicS=").append(headImgMiddleDynamicS);
        sb.append(", headImgMiddle=").append(headImgMiddle);
        sb.append(", headImgBigDynamicS=").append(headImgBigDynamicS);
        sb.append(", headImgBig=").append(headImgBig);
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