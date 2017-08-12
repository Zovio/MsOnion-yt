package cc.msonion.carambola.collector.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseCatePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class CollectorItemCategory implements MsOnionBaseCatePojoAdapter {
    /**
     * 商品类目主键idx，分布式架构，全局唯一递增
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
     * 父级类目主键idx，不可以为null，必须手动设置为0
     */
    private Long pidx;

    /**
     pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String pidxCodeS;

    /**
     * pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题
     */
    private Long pidxCode;

    /**
     * 顶级idx，记录最顶级的主键idx，如果自己是顶级那么这里录入自己的idx
     */
    private Long topIdx;

    /**
     * 类目编码，最多10个字符，不能重复，不可以为null
     */
    private String code;

    /**
     * 类目简称，最多10个字符，不可以为null
     */
    @NotNull(message = "简称不能为空")
    @Length(max = 10, message = "简称最大10个字符")
    private String name;

    /**
     * 类目全称，最多10个字符，不可以为null
     */
    @Length(max = 10, message = "全称最大10个字符")
    private String fullName;

    /**
     小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageSmallDynamicS;

    /**
     * 小图文件
     */
    private String imageSmall;

    /**
     中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageMiddleDynamicS;

    /**
     * 中图文件
     */
    private String imageMiddle;

    /**
     大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String imageBigDynamicS;

    /**
     * 大图文件
     */
    private String imageBig;

    /**
     * 属性组主键idx，不可以为null，必须手动设置为0
     */
    @NotNull(message = "权限组不能为空")
    private Long attributeGroupIdx;

    /**
     * 限购数，0：不限购，1-9表示具体限购数量，取值范围为0-9，不可以为null，必须手动设置默认值为3
     */
    @NotNull(message = "限购数量不能为空")
    @Range(min = 0, max = 9, message = "限购数量区间0-9")
    private Short purchaseLimit;

    /**
     * 类目级别，1：第一类目，2：第二类目，3：第三类目，取值范围1-9，不可以为null，必须手动设置默认值为1
     */
    @NotNull(message = "类目级别不能为空")
    @Range(min = 1, max = 9, message = "类目级别取值区间1-9")
    private Short levelNum;

    /**
     * 类目序号，输入的序号必须为大于0的整数，前端商城分类显示顺序按此排列。序号越大排序越靠前，相同序号的类目按照修改时间排序，即排序规则为“顺序+时间”。
     */
    @Range(min = 1, max = 2147483647, message = "类目序号需要设置1-2147483647")
    private Integer categorySequence;

    /**
     * 发布渠道，0：全部；1：洋桃；2：洋葱
     */
    private Short publishChannel;

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
     * 这个pid是字符串的，和数据库中的pidx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用pidx（Long）
     * 唯一标识，pidx, JavaScript  对 18位数字pidx支持不好，导致最后一位丢失，所以使用字符串pid
     */
    private String pid;

    /**
     * 商品类目主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 商品类目主键idx，分布式架构，全局唯一递增
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
     * 父级类目主键idx，不可以为null，必须手动设置为0
     */
    public Long getPidx() {
        return pidx;
    }

    /**
     * 父级类目主键idx，不可以为null，必须手动设置为0
     */
    public void setPidx(Long pidx) {
        this.pidx = pidx;
    }

    /**
     pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getPidxCodeS() {
        return String.valueOf(this.pidxCode);
    }

    /**
     * pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题
     */
    public Long getPidxCode() {
        return pidxCode;
    }

    /**
     * pidx code 全局唯一性，解决pidx可以通过递增1猜测出来，潜在安全问题
     */
    public void setPidxCode(Long pidxCode) {
        this.pidxCode = pidxCode;
    }

    /**
     * 顶级idx，记录最顶级的主键idx，如果自己是顶级那么这里录入自己的idx
     */
    public Long getTopIdx() {
        return topIdx;
    }

    /**
     * 顶级idx，记录最顶级的主键idx，如果自己是顶级那么这里录入自己的idx
     */
    public void setTopIdx(Long topIdx) {
        this.topIdx = topIdx;
    }

    /**
     * 类目编码，最多10个字符，不能重复，不可以为null
     */
    public String getCode() {
        return code;
    }

    /**
     * 类目编码，最多10个字符，不能重复，不可以为null
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 类目简称，最多10个字符，不可以为null
     */
    public String getName() {
        return name;
    }

    /**
     * 类目简称，最多10个字符，不可以为null
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 类目全称，最多10个字符，不可以为null
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 类目全称，最多10个字符，不可以为null
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageSmallDynamicS() {
        return this.imageSmallDynamicS;
    }

    /**
     小图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageSmallDynamicS(String imageSmallDynamicS) {
         this.imageSmallDynamicS = imageSmallDynamicS;
    }

    /**
     * 小图文件
     */
    public String getImageSmall() {
        return imageSmall;
    }

    /**
     * 小图文件
     */
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall == null ? null : imageSmall.trim();
    }

    /**
     中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageMiddleDynamicS() {
        return this.imageMiddleDynamicS;
    }

    /**
     中图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageMiddleDynamicS(String imageMiddleDynamicS) {
         this.imageMiddleDynamicS = imageMiddleDynamicS;
    }

    /**
     * 中图文件
     */
    public String getImageMiddle() {
        return imageMiddle;
    }

    /**
     * 中图文件
     */
    public void setImageMiddle(String imageMiddle) {
        this.imageMiddle = imageMiddle == null ? null : imageMiddle.trim();
    }

    /**
     大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getImageBigDynamicS() {
        return this.imageBigDynamicS;
    }

    /**
     大图文件，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setImageBigDynamicS(String imageBigDynamicS) {
         this.imageBigDynamicS = imageBigDynamicS;
    }

    /**
     * 大图文件
     */
    public String getImageBig() {
        return imageBig;
    }

    /**
     * 大图文件
     */
    public void setImageBig(String imageBig) {
        this.imageBig = imageBig == null ? null : imageBig.trim();
    }

    /**
     * 属性组主键idx，不可以为null，必须手动设置为0
     */
    public Long getAttributeGroupIdx() {
        return attributeGroupIdx;
    }

    /**
     * 属性组主键idx，不可以为null，必须手动设置为0
     */
    public void setAttributeGroupIdx(Long attributeGroupIdx) {
        this.attributeGroupIdx = attributeGroupIdx;
    }

    /**
     * 限购数，0：不限购，1-9表示具体限购数量，取值范围为0-9，不可以为null，必须手动设置默认值为3
     */
    public Short getPurchaseLimit() {
        return purchaseLimit;
    }

    /**
     * 限购数，0：不限购，1-9表示具体限购数量，取值范围为0-9，不可以为null，必须手动设置默认值为3
     */
    public void setPurchaseLimit(Short purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    /**
     * 类目级别，1：第一类目，2：第二类目，3：第三类目，取值范围1-9，不可以为null，必须手动设置默认值为1
     */
    public Short getLevelNum() {
        return levelNum;
    }

    /**
     * 类目级别，1：第一类目，2：第二类目，3：第三类目，取值范围1-9，不可以为null，必须手动设置默认值为1
     */
    public void setLevelNum(Short levelNum) {
        this.levelNum = levelNum;
    }

    /**
     * 类目序号，输入的序号必须为大于0的整数，前端商城分类显示顺序按此排列。序号越大排序越靠前，相同序号的类目按照修改时间排序，即排序规则为“顺序+时间”。
     */
    public Integer getCategorySequence() {
        return categorySequence;
    }

    /**
     * 类目序号，输入的序号必须为大于0的整数，前端商城分类显示顺序按此排列。序号越大排序越靠前，相同序号的类目按照修改时间排序，即排序规则为“顺序+时间”。
     */
    public void setCategorySequence(Integer categorySequence) {
        this.categorySequence = categorySequence;
    }

    /**
     * 发布渠道，0：全部；1：洋桃；2：洋葱
     */
    public Short getPublishChannel() {
        return publishChannel;
    }

    /**
     * 发布渠道，0：全部；1：洋桃；2：洋葱
     */
    public void setPublishChannel(Short publishChannel) {
        this.publishChannel = publishChannel;
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
        sb.append(", pidx=").append(pidx);
        sb.append(", pidxCodeS=").append(pidxCodeS);
        sb.append(", pidxCode=").append(pidxCode);
        sb.append(", topIdx=").append(topIdx);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", fullName=").append(fullName);
        sb.append(", imageSmallDynamicS=").append(imageSmallDynamicS);
        sb.append(", imageSmall=").append(imageSmall);
        sb.append(", imageMiddleDynamicS=").append(imageMiddleDynamicS);
        sb.append(", imageMiddle=").append(imageMiddle);
        sb.append(", imageBigDynamicS=").append(imageBigDynamicS);
        sb.append(", imageBig=").append(imageBig);
        sb.append(", attributeGroupIdx=").append(attributeGroupIdx);
        sb.append(", purchaseLimit=").append(purchaseLimit);
        sb.append(", levelNum=").append(levelNum);
        sb.append(", categorySequence=").append(categorySequence);
        sb.append(", publishChannel=").append(publishChannel);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append("]");
        return sb.toString();
    }
}