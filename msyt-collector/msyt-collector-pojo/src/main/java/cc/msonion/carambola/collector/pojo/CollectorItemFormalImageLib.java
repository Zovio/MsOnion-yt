package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CollectorItemFormalImageLib implements MsOnionBasePojoAdapter {
    /**
     * 商品正式图库主键idx，分布式架构，全局唯一递增
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
    @NotNull(message = "商品不能为空")
    private Long itemIdx;

    /**
     白底小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String whiteBackgroundImageSmallDynamicS;

    /**
     * 白底小图文件
     */
    private String whiteBackgroundImageSmall;

    /**
     白底中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String whiteBackgroundImageMiddleDynamicS;

    /**
     * 白底中图文件
     */
    private String whiteBackgroundImageMiddle;

    /**
     白底大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String whiteBackgroundImageBigDynamicS;

    /**
     * 白底大图文件
     */
    private String whiteBackgroundImageBig;

    /**
     主推小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String mainPushImageSmallDynamicS;

    /**
     * 主推小图文件
     */
    private String mainPushImageSmall;

    /**
     主推中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String mainPushImageMiddleDynamicS;

    /**
     * 主推中图文件
     */
    private String mainPushImageMiddle;

    /**
     主推大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String mainPushImageBigDynamicS;

    /**
     * 主推大图文件
     */
    private String mainPushImageBig;

    /**
     详情页主小图文件详情页主小图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String detailPageMainImageSmallDynamicS;

    /**
     * 详情页主小图文件详情页主小图文件messageid
     */
    private String detailPageMainImageSmall;

    /**
     详情页主中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String detailPageMainImageMiddleDynamicS;

    /**
     * 详情页主中图文件
     */
    private String detailPageMainImageMiddle;

    /**
     详情页主大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String detailPageMainImageBigDynamicS;

    /**
     * 详情页主大图文件
     */
    private String detailPageMainImageBig;

    /**
     * 详情
     */
    @Length(max = 12000, message = "详情最多12000个字符")
    private String contentInfo;

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
     * 商品正式图库主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品正式图库主键idx，分布式架构，全局唯一递增
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
     白底小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getWhiteBackgroundImageSmallDynamicS() {
        return this.whiteBackgroundImageSmallDynamicS;
    }

    /**
     白底小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setWhiteBackgroundImageSmallDynamicS(String whiteBackgroundImageSmallDynamicS) {
         this.whiteBackgroundImageSmallDynamicS = whiteBackgroundImageSmallDynamicS;
    }

    /**
     * 白底小图文件
     */
    public String getWhiteBackgroundImageSmall() {
        return whiteBackgroundImageSmall;
    }

    /**
     * 白底小图文件
     */
    public void setWhiteBackgroundImageSmall(String whiteBackgroundImageSmall) {
        this.whiteBackgroundImageSmall = whiteBackgroundImageSmall == null ? null : whiteBackgroundImageSmall.trim();
    }

    /**
     白底中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getWhiteBackgroundImageMiddleDynamicS() {
        return this.whiteBackgroundImageMiddleDynamicS;
    }

    /**
     白底中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setWhiteBackgroundImageMiddleDynamicS(String whiteBackgroundImageMiddleDynamicS) {
         this.whiteBackgroundImageMiddleDynamicS = whiteBackgroundImageMiddleDynamicS;
    }

    /**
     * 白底中图文件
     */
    public String getWhiteBackgroundImageMiddle() {
        return whiteBackgroundImageMiddle;
    }

    /**
     * 白底中图文件
     */
    public void setWhiteBackgroundImageMiddle(String whiteBackgroundImageMiddle) {
        this.whiteBackgroundImageMiddle = whiteBackgroundImageMiddle == null ? null : whiteBackgroundImageMiddle.trim();
    }

    /**
     白底大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getWhiteBackgroundImageBigDynamicS() {
        return this.whiteBackgroundImageBigDynamicS;
    }

    /**
     白底大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setWhiteBackgroundImageBigDynamicS(String whiteBackgroundImageBigDynamicS) {
         this.whiteBackgroundImageBigDynamicS = whiteBackgroundImageBigDynamicS;
    }

    /**
     * 白底大图文件
     */
    public String getWhiteBackgroundImageBig() {
        return whiteBackgroundImageBig;
    }

    /**
     * 白底大图文件
     */
    public void setWhiteBackgroundImageBig(String whiteBackgroundImageBig) {
        this.whiteBackgroundImageBig = whiteBackgroundImageBig == null ? null : whiteBackgroundImageBig.trim();
    }

    /**
     主推小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getMainPushImageSmallDynamicS() {
        return this.mainPushImageSmallDynamicS;
    }

    /**
     主推小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setMainPushImageSmallDynamicS(String mainPushImageSmallDynamicS) {
         this.mainPushImageSmallDynamicS = mainPushImageSmallDynamicS;
    }

    /**
     * 主推小图文件
     */
    public String getMainPushImageSmall() {
        return mainPushImageSmall;
    }

    /**
     * 主推小图文件
     */
    public void setMainPushImageSmall(String mainPushImageSmall) {
        this.mainPushImageSmall = mainPushImageSmall == null ? null : mainPushImageSmall.trim();
    }

    /**
     主推中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getMainPushImageMiddleDynamicS() {
        return this.mainPushImageMiddleDynamicS;
    }

    /**
     主推中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setMainPushImageMiddleDynamicS(String mainPushImageMiddleDynamicS) {
         this.mainPushImageMiddleDynamicS = mainPushImageMiddleDynamicS;
    }

    /**
     * 主推中图文件
     */
    public String getMainPushImageMiddle() {
        return mainPushImageMiddle;
    }

    /**
     * 主推中图文件
     */
    public void setMainPushImageMiddle(String mainPushImageMiddle) {
        this.mainPushImageMiddle = mainPushImageMiddle == null ? null : mainPushImageMiddle.trim();
    }

    /**
     主推大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getMainPushImageBigDynamicS() {
        return this.mainPushImageBigDynamicS;
    }

    /**
     主推大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setMainPushImageBigDynamicS(String mainPushImageBigDynamicS) {
         this.mainPushImageBigDynamicS = mainPushImageBigDynamicS;
    }

    /**
     * 主推大图文件
     */
    public String getMainPushImageBig() {
        return mainPushImageBig;
    }

    /**
     * 主推大图文件
     */
    public void setMainPushImageBig(String mainPushImageBig) {
        this.mainPushImageBig = mainPushImageBig == null ? null : mainPushImageBig.trim();
    }

    /**
     详情页主小图文件详情页主小图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getDetailPageMainImageSmallDynamicS() {
        return this.detailPageMainImageSmallDynamicS;
    }

    /**
     详情页主小图文件详情页主小图文件messageid，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setDetailPageMainImageSmallDynamicS(String detailPageMainImageSmallDynamicS) {
         this.detailPageMainImageSmallDynamicS = detailPageMainImageSmallDynamicS;
    }

    /**
     * 详情页主小图文件详情页主小图文件messageid
     */
    public String getDetailPageMainImageSmall() {
        return detailPageMainImageSmall;
    }

    /**
     * 详情页主小图文件详情页主小图文件messageid
     */
    public void setDetailPageMainImageSmall(String detailPageMainImageSmall) {
        this.detailPageMainImageSmall = detailPageMainImageSmall == null ? null : detailPageMainImageSmall.trim();
    }

    /**
     详情页主中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getDetailPageMainImageMiddleDynamicS() {
        return this.detailPageMainImageMiddleDynamicS;
    }

    /**
     详情页主中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setDetailPageMainImageMiddleDynamicS(String detailPageMainImageMiddleDynamicS) {
         this.detailPageMainImageMiddleDynamicS = detailPageMainImageMiddleDynamicS;
    }

    /**
     * 详情页主中图文件
     */
    public String getDetailPageMainImageMiddle() {
        return detailPageMainImageMiddle;
    }

    /**
     * 详情页主中图文件
     */
    public void setDetailPageMainImageMiddle(String detailPageMainImageMiddle) {
        this.detailPageMainImageMiddle = detailPageMainImageMiddle == null ? null : detailPageMainImageMiddle.trim();
    }

    /**
     详情页主大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getDetailPageMainImageBigDynamicS() {
        return this.detailPageMainImageBigDynamicS;
    }

    /**
     详情页主大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setDetailPageMainImageBigDynamicS(String detailPageMainImageBigDynamicS) {
         this.detailPageMainImageBigDynamicS = detailPageMainImageBigDynamicS;
    }

    /**
     * 详情页主大图文件
     */
    public String getDetailPageMainImageBig() {
        return detailPageMainImageBig;
    }

    /**
     * 详情页主大图文件
     */
    public void setDetailPageMainImageBig(String detailPageMainImageBig) {
        this.detailPageMainImageBig = detailPageMainImageBig == null ? null : detailPageMainImageBig.trim();
    }

    /**
     * 详情
     */
    public String getContentInfo() {
        return contentInfo;
    }

    /**
     * 详情
     */
    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo == null ? null : contentInfo.trim();
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
        sb.append(", itemIdx=").append(itemIdx);
        sb.append(", whiteBackgroundImageSmallDynamicS=").append(whiteBackgroundImageSmallDynamicS);
        sb.append(", whiteBackgroundImageSmall=").append(whiteBackgroundImageSmall);
        sb.append(", whiteBackgroundImageMiddleDynamicS=").append(whiteBackgroundImageMiddleDynamicS);
        sb.append(", whiteBackgroundImageMiddle=").append(whiteBackgroundImageMiddle);
        sb.append(", whiteBackgroundImageBigDynamicS=").append(whiteBackgroundImageBigDynamicS);
        sb.append(", whiteBackgroundImageBig=").append(whiteBackgroundImageBig);
        sb.append(", mainPushImageSmallDynamicS=").append(mainPushImageSmallDynamicS);
        sb.append(", mainPushImageSmall=").append(mainPushImageSmall);
        sb.append(", mainPushImageMiddleDynamicS=").append(mainPushImageMiddleDynamicS);
        sb.append(", mainPushImageMiddle=").append(mainPushImageMiddle);
        sb.append(", mainPushImageBigDynamicS=").append(mainPushImageBigDynamicS);
        sb.append(", mainPushImageBig=").append(mainPushImageBig);
        sb.append(", detailPageMainImageSmallDynamicS=").append(detailPageMainImageSmallDynamicS);
        sb.append(", detailPageMainImageSmall=").append(detailPageMainImageSmall);
        sb.append(", detailPageMainImageMiddleDynamicS=").append(detailPageMainImageMiddleDynamicS);
        sb.append(", detailPageMainImageMiddle=").append(detailPageMainImageMiddle);
        sb.append(", detailPageMainImageBigDynamicS=").append(detailPageMainImageBigDynamicS);
        sb.append(", detailPageMainImageBig=").append(detailPageMainImageBig);
        sb.append(", contentInfo=").append(contentInfo);
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