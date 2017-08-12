package cc.msonion.carambola.item.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class ItemOfficial implements MsOnionBasePojoAdapter {
    /**
     * 正式商品主键idx，分布式架构，全局唯一递增
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
     * 采集商品主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "商品不能为空")
    private Long itemIdx;

    /**
     * 平台主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "所属平台不能为空")
    private Long platformIdx;

    /**
     * 初次上架人，商品第一次上架的操作者名称
     */
    private Long firstOnlineByMemberIdx;

    /**
    初次上架时间，商品第一次上架的时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String firstOnlineTimeEnYyyyMMddHHmmss;

    /**
     * 初次上架时间，商品第一次上架的时间
     */
    private Date firstOnlineTime;

    /**
     * 上架人，最近上架人的名称
     */
    private Long onlineByMemberIdx;

    /**
    上架时间，最近的上架时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String onlineTimeEnYyyyMMddHHmmss;

    /**
     * 上架时间，最近的上架时间
     */
    private Date onlineTime;

    /**
     * 下架人，最近的下架人名称
     */
    private Long offlineByMemberIdx;

    /**
    下架时间，最近的下架时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String offlineTimeEnYyyyMMddHHmmss;

    /**
     * 下架时间，最近的下架时间
     */
    private Date offlineTime;

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
     * 上下架状态，1：上架，2：下架，3：待上架，0：删除，取值范围为0-9，不可以为null
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
     * 正式商品主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 正式商品主键idx，分布式架构，全局唯一递增
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
     * 采集商品主键idx，不可以为null，必须手动设置为0
     */
    public Long getItemIdx() {
        return itemIdx;
    }

    /**
     * 采集商品主键idx，不可以为null，必须手动设置为0
     */
    public void setItemIdx(Long itemIdx) {
        this.itemIdx = itemIdx;
    }

    /**
     * 平台主键idx，不可以为null，必须手动设置为0
     */
    public Long getPlatformIdx() {
        return platformIdx;
    }

    /**
     * 平台主键idx，不可以为null，必须手动设置为0
     */
    public void setPlatformIdx(Long platformIdx) {
        this.platformIdx = platformIdx;
    }

    /**
     * 初次上架人，商品第一次上架的操作者名称
     */
    public Long getFirstOnlineByMemberIdx() {
        return firstOnlineByMemberIdx;
    }

    /**
     * 初次上架人，商品第一次上架的操作者名称
     */
    public void setFirstOnlineByMemberIdx(Long firstOnlineByMemberIdx) {
        this.firstOnlineByMemberIdx = firstOnlineByMemberIdx;
    }

    /**
    初次上架时间，商品第一次上架的时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getFirstOnlineTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.firstOnlineTime) ;
    }

    /**
     * 初次上架时间，商品第一次上架的时间
     */
    public Date getFirstOnlineTime() {
        return firstOnlineTime;
    }

    /**
     * 初次上架时间，商品第一次上架的时间
     */
    public void setFirstOnlineTime(Date firstOnlineTime) {
        this.firstOnlineTime = firstOnlineTime;
    }

    /**
     * 上架人，最近上架人的名称
     */
    public Long getOnlineByMemberIdx() {
        return onlineByMemberIdx;
    }

    /**
     * 上架人，最近上架人的名称
     */
    public void setOnlineByMemberIdx(Long onlineByMemberIdx) {
        this.onlineByMemberIdx = onlineByMemberIdx;
    }

    /**
    上架时间，最近的上架时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getOnlineTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.onlineTime) ;
    }

    /**
     * 上架时间，最近的上架时间
     */
    public Date getOnlineTime() {
        return onlineTime;
    }

    /**
     * 上架时间，最近的上架时间
     */
    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    /**
     * 下架人，最近的下架人名称
     */
    public Long getOfflineByMemberIdx() {
        return offlineByMemberIdx;
    }

    /**
     * 下架人，最近的下架人名称
     */
    public void setOfflineByMemberIdx(Long offlineByMemberIdx) {
        this.offlineByMemberIdx = offlineByMemberIdx;
    }

    /**
    下架时间，最近的下架时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getOfflineTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.offlineTime) ;
    }

    /**
     * 下架时间，最近的下架时间
     */
    public Date getOfflineTime() {
        return offlineTime;
    }

    /**
     * 下架时间，最近的下架时间
     */
    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
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
     * 上下架状态，1：上架，2：下架，3：待上架，0：删除，取值范围为0-9，不可以为null
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 上下架状态，1：上架，2：下架，3：待上架，0：删除，取值范围为0-9，不可以为null
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
        sb.append(", platformIdx=").append(platformIdx);
        sb.append(", firstOnlineByMemberIdx=").append(firstOnlineByMemberIdx);
        sb.append(", firstOnlineTimeEnYyyyMMddHHmmss=").append(firstOnlineTimeEnYyyyMMddHHmmss);
        sb.append(", firstOnlineTime=").append(firstOnlineTime);
        sb.append(", onlineByMemberIdx=").append(onlineByMemberIdx);
        sb.append(", onlineTimeEnYyyyMMddHHmmss=").append(onlineTimeEnYyyyMMddHHmmss);
        sb.append(", onlineTime=").append(onlineTime);
        sb.append(", offlineByMemberIdx=").append(offlineByMemberIdx);
        sb.append(", offlineTimeEnYyyyMMddHHmmss=").append(offlineTimeEnYyyyMMddHHmmss);
        sb.append(", offlineTime=").append(offlineTime);
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