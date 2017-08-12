package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class CollectorItemLog implements MsOnionBasePojoAdapter {
    /**
     * 商品日志主键idx，分布式框架，全局唯一递增
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
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "关联ID不能为空")
    private Long itemIdx;

    /**
     修改位置，即修改了什么内容，比如：分类、货号、供货价、发货系数等等，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String modifyLocationDynamicS;

    /**
     * 修改位置，即修改了什么内容，比如：分类、货号、供货价、发货系数等等
     */
    @NotNull(message = "修改位置不能为空")
    @Range(min = 1, max = 50, message = "修改位置需要设置1-50")
    private Short modifyLocation;

    /**
     * 原始值，也就是修改前的值
     */
    @NotNull(message = "原始值不能为空")
    @Length(max = 100, message = "原始值最多100个字符")
    private String originValue;

    /**
     * 新值，也就是修改后的值
     */
    @NotNull(message = "新值不能为空")
    @Length(max = 100, message = "原始值最多100个字符")
    private String newValue;

    /**
     创建成员idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String createByMemberIdxDynamicS;

    /**
     * 创建成员idx，不可以为null
     */
    private Long createByMemberIdx;

    /**
     * 修改成员idx，不可以为null
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
     * 商品日志主键idx，分布式框架，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品日志主键idx，分布式框架，全局唯一递增
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
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    public Long getItemIdx() {
        return itemIdx;
    }

    /**
     * 商品主键idx，不可以为null，必须手动设置为0
     */
    public void setItemIdx(Long itemIdx) {
        this.itemIdx = itemIdx;
    }

    /**
     修改位置，即修改了什么内容，比如：分类、货号、供货价、发货系数等等，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getModifyLocationDynamicS() {
        return this.modifyLocationDynamicS;
    }

    /**
     修改位置，即修改了什么内容，比如：分类、货号、供货价、发货系数等等，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setModifyLocationDynamicS(String modifyLocationDynamicS) {
         this.modifyLocationDynamicS = modifyLocationDynamicS;
    }

    /**
     * 修改位置，即修改了什么内容，比如：分类、货号、供货价、发货系数等等
     */
    public Short getModifyLocation() {
        return modifyLocation;
    }

    /**
     * 修改位置，即修改了什么内容，比如：分类、货号、供货价、发货系数等等
     */
    public void setModifyLocation(Short modifyLocation) {
        this.modifyLocation = modifyLocation;
    }

    /**
     * 原始值，也就是修改前的值
     */
    public String getOriginValue() {
        return originValue;
    }

    /**
     * 原始值，也就是修改前的值
     */
    public void setOriginValue(String originValue) {
        this.originValue = originValue == null ? null : originValue.trim();
    }

    /**
     * 新值，也就是修改后的值
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * 新值，也就是修改后的值
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }

    /**
     创建成员idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getCreateByMemberIdxDynamicS() {
        return this.createByMemberIdxDynamicS;
    }

    /**
     创建成员idx，不可以为null，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setCreateByMemberIdxDynamicS(String createByMemberIdxDynamicS) {
         this.createByMemberIdxDynamicS = createByMemberIdxDynamicS;
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
        sb.append(", itemIdx=").append(itemIdx);
        sb.append(", modifyLocationDynamicS=").append(modifyLocationDynamicS);
        sb.append(", modifyLocation=").append(modifyLocation);
        sb.append(", originValue=").append(originValue);
        sb.append(", newValue=").append(newValue);
        sb.append(", createByMemberIdxDynamicS=").append(createByMemberIdxDynamicS);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTimeEnYyyyMMddHHmmss=").append(createTimeEnYyyyMMddHHmmss);
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