package test.msonion.carambola.member.service.menu.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseCatePojoAdapter;
import cc.msonion.carambola.parent.pojo.base.MsOnionBaseCatePojo;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TestMenu extends MsOnionBaseCatePojo implements MsOnionBaseCatePojoAdapter {
    /**
     * 菜单主键idx，必须是分布式全局唯一递增
     */
    private Long idx;

    /**
     * 父级菜单主键pidx，必须是分布式全局唯一递增,不可以为null，必须默认为0，需要手动设置
     */
    private Long pidx;

    /**
     * 菜单码，最多30个字符，只能包括小写字母和_(下划线)，不可以为null，必须手动设置为空字符串
     */
    @NotNull(message = "菜单编码不能为空")
    private String code;

    /**
     * 菜单名称，最多20个字符（包括中英文），不可以为null或者空字符串，长度最少2个字符
     */
    @NotNull(message = "菜单名称不能为空")
    private String name;

    /**
     * 菜单Url，最多100个字符，不可以包括中文，父级菜单不可以为null，必须手动初始化为空字符串
     */
    private String url;

    /**
     * 菜单url权限映射，不可以为null。
     */
    private String urlMapping;

    /**
     * 菜单备注，最多20个字符，不可以为null，必须手动设置为空字符串
     */
    private String remark;

    /**
     * 菜单顺序，按照从小到大顺序，取值范围0-766，不可以为null，必须手动设置默认值为0## @Range(min = 0, max = 766, message = "菜单顺序需要设置0-766")##
     */
    @Range(min = 0, max = 766, message = "菜单顺序需要设置0-766")
    private Short zindex;

    /**
     * 菜单是否在顶部显示，1:显示，0:不显示；手动设置默认值0
     */
    private Short topShow;

    /**
     * 菜单级别，1:一级菜单，2：二级菜单，3：三级菜单，不可以为null，必须手动设置默认值为0
     */
    private Short level;

    /**
     * 菜单Icon的Url，最多200个字符，不可以为null，必须手动设置为空字符串
     */
    private String iconUrl;

    /**
     * 菜单类别主键idx，暂时不使用，后续扩展使用，不可以设置为null，必须通过手动设置为0
     */
    private Long menuCateIdx;

    /**
     * 菜单状态，1：可用，0：删除，取值范围为0-9，不可以为null，必须手动设置为0或者1
     */
    private Short status;

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值
     */
    private Long version;

    /**
     * 菜单被哪位成员（member_idx）创建的，不可以为null
     */
    private Long createByMemberIdx;

    /**
     * 菜单被哪位成员（member_idx）修改的，不可以为null
     */
    private Long updateByMemberIdx;

    /**
     * 创建时间，后续不可以再更新时间
     */
    private Date createTime;

    /**
     * 更新时间，每一次都要更新
     */
    private Date updateTime;

    /**
     * 扩展字段，不可以为null，默认为空字符串
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
     * 菜单主键idx，必须是分布式全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 菜单主键idx，必须是分布式全局唯一递增
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * 父级菜单主键pidx，必须是分布式全局唯一递增,不可以为null，必须默认为0，需要手动设置
     */
    public Long getPidx() {
        return pidx;
    }

    /**
     * 父级菜单主键pidx，必须是分布式全局唯一递增,不可以为null，必须默认为0，需要手动设置
     */
    public void setPidx(Long pidx) {
        this.pidx = pidx;
    }

    /**
     * 菜单码，最多30个字符，只能包括小写字母和_(下划线)，不可以为null，必须手动设置为空字符串
     */
    public String getCode() {
        return code;
    }

    /**
     * 菜单码，最多30个字符，只能包括小写字母和_(下划线)，不可以为null，必须手动设置为空字符串
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 菜单名称，最多20个字符（包括中英文），不可以为null或者空字符串，长度最少2个字符
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称，最多20个字符（包括中英文），不可以为null或者空字符串，长度最少2个字符
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 菜单Url，最多100个字符，不可以包括中文，父级菜单不可以为null，必须手动初始化为空字符串
     */
    public String getUrl() {
        return url;
    }

    /**
     * 菜单Url，最多100个字符，不可以包括中文，父级菜单不可以为null，必须手动初始化为空字符串
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 菜单url权限映射，不可以为null。
     */
    public String getUrlMapping() {
        return urlMapping;
    }

    /**
     * 菜单url权限映射，不可以为null。
     */
    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping == null ? null : urlMapping.trim();
    }

    /**
     * 菜单备注，最多20个字符，不可以为null，必须手动设置为空字符串
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 菜单备注，最多20个字符，不可以为null，必须手动设置为空字符串
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 菜单顺序，按照从小到大顺序，取值范围0-766，不可以为null，必须手动设置默认值为0## @Range(min = 0, max = 766, message = "菜单顺序需要设置0-766")##
     */
    public Short getZindex() {
        return zindex;
    }

    /**
     * 菜单顺序，按照从小到大顺序，取值范围0-766，不可以为null，必须手动设置默认值为0## @Range(min = 0, max = 766, message = "菜单顺序需要设置0-766")##
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
    }

    /**
     * 菜单是否在顶部显示，1:显示，0:不显示；手动设置默认值0
     */
    public Short getTopShow() {
        return topShow;
    }

    /**
     * 菜单是否在顶部显示，1:显示，0:不显示；手动设置默认值0
     */
    public void setTopShow(Short topShow) {
        this.topShow = topShow;
    }

    /**
     * 菜单级别，1:一级菜单，2：二级菜单，3：三级菜单，不可以为null，必须手动设置默认值为0
     */
    public Short getLevel() {
        return level;
    }

    /**
     * 菜单级别，1:一级菜单，2：二级菜单，3：三级菜单，不可以为null，必须手动设置默认值为0
     */
    public void setLevel(Short level) {
        this.level = level;
    }

    /**
     * 菜单Icon的Url，最多200个字符，不可以为null，必须手动设置为空字符串
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 菜单Icon的Url，最多200个字符，不可以为null，必须手动设置为空字符串
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    /**
     * 菜单类别主键idx，暂时不使用，后续扩展使用，不可以设置为null，必须通过手动设置为0
     */
    public Long getMenuCateIdx() {
        return menuCateIdx;
    }

    /**
     * 菜单类别主键idx，暂时不使用，后续扩展使用，不可以设置为null，必须通过手动设置为0
     */
    public void setMenuCateIdx(Long menuCateIdx) {
        this.menuCateIdx = menuCateIdx;
    }

    /**
     * 菜单状态，1：可用，0：删除，取值范围为0-9，不可以为null，必须手动设置为0或者1
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 菜单状态，1：可用，0：删除，取值范围为0-9，不可以为null，必须手动设置为0或者1
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 菜单被哪位成员（member_idx）创建的，不可以为null
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 菜单被哪位成员（member_idx）创建的，不可以为null
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * 菜单被哪位成员（member_idx）修改的，不可以为null
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 菜单被哪位成员（member_idx）修改的，不可以为null
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
     * 创建时间，后续不可以再更新时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间，后续不可以再更新时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，每一次都要更新
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，每一次都要更新
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 扩展字段，不可以为null，默认为空字符串
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展字段，不可以为null，默认为空字符串
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
        sb.append(", pidx=").append(pidx);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", urlMapping=").append(urlMapping);
        sb.append(", remark=").append(remark);
        sb.append(", zindex=").append(zindex);
        sb.append(", topShow=").append(topShow);
        sb.append(", level=").append(level);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", menuCateIdx=").append(menuCateIdx);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}