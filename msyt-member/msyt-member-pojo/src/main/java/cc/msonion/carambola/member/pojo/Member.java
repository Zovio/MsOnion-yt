package cc.msonion.carambola.member.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;

public class Member implements MsOnionBasePojoAdapter {
    /**
     * 成员主键idx，分布式架构，全局唯一递增
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
     * 成员名称，最少2个字符，最多20个字符
     */
    private String name;

    /**
     * 全名，最多20个字符，不可以为null
     */
    private String fullName;

    /**
     * 成员编码，必须全集唯一，建议按照菜单_功能_模块命名，必须是小写字母和_（下划线），最少4字符，最多30个字符
     */
    private String code;

    /**
     * 密码，32位字符，必须是MD5（password + 盐）
     */
    private String password;

    /**
     * 手机号码，最多20位字符，不可以为null
     */
    private String phone;

    /**
     * 电话号码，最多15，不可以为null
     */
    private String tel;

    /**
     * 性别,1:男,2:女;不可以为null
     */
    private Short sex;

    /**
     * Email，最多30位字符，不可以为null
     */
    private String email;

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
     * 状态，1：可用的、激活的，2：未激活，0：删除，2：未激活，取值范围为0-9，不可以为null
     */
    private Short status;

    /**
     * 版本号，高并发，乐观锁解决方案
     */
    private Long version;

    /**
     * 备注，字符个数0-50，不可以为null，必须手动设置空字符串
     */
    private String remark;

    /**
     * 商店（店铺）主键idx，不可以为null，必须手动设置为0
     */
    private Long storeIdx;

    /**
     * 部门主键idx，不可以为null，必须手动设置为0
     */
    private Long departmentIdx;

    /**
     * VIP会员（成员）主键idx，不可以为null，必须手动设置为0
     */
    private Long vipMemberIdx;

    /**
     * 成员分类主键idx，不可以为null，必须手动设置为0
     */
    private Long memberCateIdx;

    /**
     * 最后登录IP，不可以为null，必须手动设置为空字符串
     */
    private String lastLoginIp;

    /**
    最后登录时间，不可以为null，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String lastLoginTimeEnYyyyMMddHHmmss;

    /**
     * 最后登录时间，不可以为null
     */
    private Date lastLoginTime;

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
     * 成员主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 成员主键idx，分布式架构，全局唯一递增
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
     * 成员名称，最少2个字符，最多20个字符
     */
    public String getName() {
        return name;
    }

    /**
     * 成员名称，最少2个字符，最多20个字符
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 全名，最多20个字符，不可以为null
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 全名，最多20个字符，不可以为null
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 成员编码，必须全集唯一，建议按照菜单_功能_模块命名，必须是小写字母和_（下划线），最少4字符，最多30个字符
     */
    public String getCode() {
        return code;
    }

    /**
     * 成员编码，必须全集唯一，建议按照菜单_功能_模块命名，必须是小写字母和_（下划线），最少4字符，最多30个字符
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 密码，32位字符，必须是MD5（password + 盐）
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码，32位字符，必须是MD5（password + 盐）
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 手机号码，最多20位字符，不可以为null
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码，最多20位字符，不可以为null
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 电话号码，最多15，不可以为null
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话号码，最多15，不可以为null
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 性别,1:男,2:女;不可以为null
     */
    public Short getSex() {
        return sex;
    }

    /**
     * 性别,1:男,2:女;不可以为null
     */
    public void setSex(Short sex) {
        this.sex = sex;
    }

    /**
     * Email，最多30位字符，不可以为null
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email，最多30位字符，不可以为null
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
     * 状态，1：可用的、激活的，2：未激活，0：删除，2：未激活，取值范围为0-9，不可以为null
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态，1：可用的、激活的，2：未激活，0：删除，2：未激活，取值范围为0-9，不可以为null
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 版本号，高并发，乐观锁解决方案
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观锁解决方案
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 备注，字符个数0-50，不可以为null，必须手动设置空字符串
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，字符个数0-50，不可以为null，必须手动设置空字符串
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 商店（店铺）主键idx，不可以为null，必须手动设置为0
     */
    public Long getStoreIdx() {
        return storeIdx;
    }

    /**
     * 商店（店铺）主键idx，不可以为null，必须手动设置为0
     */
    public void setStoreIdx(Long storeIdx) {
        this.storeIdx = storeIdx;
    }

    /**
     * 部门主键idx，不可以为null，必须手动设置为0
     */
    public Long getDepartmentIdx() {
        return departmentIdx;
    }

    /**
     * 部门主键idx，不可以为null，必须手动设置为0
     */
    public void setDepartmentIdx(Long departmentIdx) {
        this.departmentIdx = departmentIdx;
    }

    /**
     * VIP会员（成员）主键idx，不可以为null，必须手动设置为0
     */
    public Long getVipMemberIdx() {
        return vipMemberIdx;
    }

    /**
     * VIP会员（成员）主键idx，不可以为null，必须手动设置为0
     */
    public void setVipMemberIdx(Long vipMemberIdx) {
        this.vipMemberIdx = vipMemberIdx;
    }

    /**
     * 成员分类主键idx，不可以为null，必须手动设置为0
     */
    public Long getMemberCateIdx() {
        return memberCateIdx;
    }

    /**
     * 成员分类主键idx，不可以为null，必须手动设置为0
     */
    public void setMemberCateIdx(Long memberCateIdx) {
        this.memberCateIdx = memberCateIdx;
    }

    /**
     * 最后登录IP，不可以为null，必须手动设置为空字符串
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 最后登录IP，不可以为null，必须手动设置为空字符串
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
    最后登录时间，不可以为null，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getLastLoginTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.lastLoginTime) ;
    }

    /**
     * 最后登录时间，不可以为null
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 最后登录时间，不可以为null
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        sb.append(", fullName=").append(fullName);
        sb.append(", code=").append(code);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", tel=").append(tel);
        sb.append(", sex=").append(sex);
        sb.append(", email=").append(email);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", remark=").append(remark);
        sb.append(", storeIdx=").append(storeIdx);
        sb.append(", departmentIdx=").append(departmentIdx);
        sb.append(", vipMemberIdx=").append(vipMemberIdx);
        sb.append(", memberCateIdx=").append(memberCateIdx);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLoginTimeEnYyyyMMddHHmmss=").append(lastLoginTimeEnYyyyMMddHHmmss);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}