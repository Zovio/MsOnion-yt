package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CollectorItemOrigin implements MsOnionBasePojoAdapter {
    /**
     * 商品产地主键idx，分布式架构，全局唯一递增
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
     * 产地编码，2-4位字符，不可以为null，必须手动设置为空字符串
     */
    @NotNull(message = "产地编码不能为空")
    @Length(min = 2, max = 4, message = "产地编码最少2位字符，最多4位字符")
    private String originCode;

    /**
     * 海关编码，3位字符，不可以为null，必须手动设置为空字符串
     */
    @NotNull(message = "海关编码不能为空")
    @Length(min = 3, max = 3, message = "海关编码为3个字符")
    private String customsCode;

    /**
     * 中文名称，最多10个字符，不可以为null，必须手动设置为空字符串
     */
    @NotNull(message = "中文名称不能为空")
    @Length(max = 10, message = "中文名称最多10个字符")
    private String cnName;

    /**
     * 英文名称，最多30个字符，不可以为null，必须手动设置为空字符串
     */
    @NotNull(message = "英文名称不能为空")
    @Length(max = 30, message = "英文名称最多30个字符")
    private String enName;

    /**
     国旗小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String nationalFlagImageSmallDynamicS;

    /**
     * 国旗小图文件
     */
    private String nationalFlagImageSmall;

    /**
     国旗中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String nationalFlagImageMiddleDynamicS;

    /**
     * 国旗中图文件
     */
    private String nationalFlagImageMiddle;

    /**
     国旗大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String nationalFlagImageBigDynamicS;

    /**
     * 国旗大图文件
     */
    private String nationalFlagImageBig;

    /**
     产地小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageSmallDynamicS;

    /**
     * 产地小图文件
     */
    private String imageSmall;

    /**
     产地中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageMiddleDynamicS;

    /**
     * 产地中图文件
     */
    private String imageMiddle;

    /**
     产地大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageBigDynamicS;

    /**
     * 产地大图文件
     */
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
     * 商品产地主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品产地主键idx，分布式架构，全局唯一递增
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
     * 产地编码，2-4位字符，不可以为null，必须手动设置为空字符串
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * 产地编码，2-4位字符，不可以为null，必须手动设置为空字符串
     */
    public void setOriginCode(String originCode) {
        this.originCode = originCode == null ? null : originCode.trim();
    }

    /**
     * 海关编码，3位字符，不可以为null，必须手动设置为空字符串
     */
    public String getCustomsCode() {
        return customsCode;
    }

    /**
     * 海关编码，3位字符，不可以为null，必须手动设置为空字符串
     */
    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode == null ? null : customsCode.trim();
    }

    /**
     * 中文名称，最多10个字符，不可以为null，必须手动设置为空字符串
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 中文名称，最多10个字符，不可以为null，必须手动设置为空字符串
     */
    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    /**
     * 英文名称，最多30个字符，不可以为null，必须手动设置为空字符串
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 英文名称，最多30个字符，不可以为null，必须手动设置为空字符串
     */
    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    /**
     国旗小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getNationalFlagImageSmallDynamicS() {
        return this.nationalFlagImageSmallDynamicS;
    }

    /**
     国旗小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setNationalFlagImageSmallDynamicS(String nationalFlagImageSmallDynamicS) {
         this.nationalFlagImageSmallDynamicS = nationalFlagImageSmallDynamicS;
    }

    /**
     * 国旗小图文件
     */
    public String getNationalFlagImageSmall() {
        return nationalFlagImageSmall;
    }

    /**
     * 国旗小图文件
     */
    public void setNationalFlagImageSmall(String nationalFlagImageSmall) {
        this.nationalFlagImageSmall = nationalFlagImageSmall == null ? null : nationalFlagImageSmall.trim();
    }

    /**
     国旗中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getNationalFlagImageMiddleDynamicS() {
        return this.nationalFlagImageMiddleDynamicS;
    }

    /**
     国旗中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setNationalFlagImageMiddleDynamicS(String nationalFlagImageMiddleDynamicS) {
         this.nationalFlagImageMiddleDynamicS = nationalFlagImageMiddleDynamicS;
    }

    /**
     * 国旗中图文件
     */
    public String getNationalFlagImageMiddle() {
        return nationalFlagImageMiddle;
    }

    /**
     * 国旗中图文件
     */
    public void setNationalFlagImageMiddle(String nationalFlagImageMiddle) {
        this.nationalFlagImageMiddle = nationalFlagImageMiddle == null ? null : nationalFlagImageMiddle.trim();
    }

    /**
     国旗大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getNationalFlagImageBigDynamicS() {
        return this.nationalFlagImageBigDynamicS;
    }

    /**
     国旗大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setNationalFlagImageBigDynamicS(String nationalFlagImageBigDynamicS) {
         this.nationalFlagImageBigDynamicS = nationalFlagImageBigDynamicS;
    }

    /**
     * 国旗大图文件
     */
    public String getNationalFlagImageBig() {
        return nationalFlagImageBig;
    }

    /**
     * 国旗大图文件
     */
    public void setNationalFlagImageBig(String nationalFlagImageBig) {
        this.nationalFlagImageBig = nationalFlagImageBig == null ? null : nationalFlagImageBig.trim();
    }

    /**
     产地小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageSmallDynamicS() {
        return this.imageSmallDynamicS;
    }

    /**
     产地小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageSmallDynamicS(String imageSmallDynamicS) {
         this.imageSmallDynamicS = imageSmallDynamicS;
    }

    /**
     * 产地小图文件
     */
    public String getImageSmall() {
        return imageSmall;
    }

    /**
     * 产地小图文件
     */
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall == null ? null : imageSmall.trim();
    }

    /**
     产地中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageMiddleDynamicS() {
        return this.imageMiddleDynamicS;
    }

    /**
     产地中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageMiddleDynamicS(String imageMiddleDynamicS) {
         this.imageMiddleDynamicS = imageMiddleDynamicS;
    }

    /**
     * 产地中图文件
     */
    public String getImageMiddle() {
        return imageMiddle;
    }

    /**
     * 产地中图文件
     */
    public void setImageMiddle(String imageMiddle) {
        this.imageMiddle = imageMiddle == null ? null : imageMiddle.trim();
    }

    /**
     产地大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageBigDynamicS() {
        return this.imageBigDynamicS;
    }

    /**
     产地大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageBigDynamicS(String imageBigDynamicS) {
         this.imageBigDynamicS = imageBigDynamicS;
    }

    /**
     * 产地大图文件
     */
    public String getImageBig() {
        return imageBig;
    }

    /**
     * 产地大图文件
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
        sb.append(", originCode=").append(originCode);
        sb.append(", customsCode=").append(customsCode);
        sb.append(", cnName=").append(cnName);
        sb.append(", enName=").append(enName);
        sb.append(", nationalFlagImageSmallDynamicS=").append(nationalFlagImageSmallDynamicS);
        sb.append(", nationalFlagImageSmall=").append(nationalFlagImageSmall);
        sb.append(", nationalFlagImageMiddleDynamicS=").append(nationalFlagImageMiddleDynamicS);
        sb.append(", nationalFlagImageMiddle=").append(nationalFlagImageMiddle);
        sb.append(", nationalFlagImageBigDynamicS=").append(nationalFlagImageBigDynamicS);
        sb.append(", nationalFlagImageBig=").append(nationalFlagImageBig);
        sb.append(", imageSmallDynamicS=").append(imageSmallDynamicS);
        sb.append(", imageSmall=").append(imageSmall);
        sb.append(", imageMiddleDynamicS=").append(imageMiddleDynamicS);
        sb.append(", imageMiddle=").append(imageMiddle);
        sb.append(", imageBigDynamicS=").append(imageBigDynamicS);
        sb.append(", imageBig=").append(imageBig);
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