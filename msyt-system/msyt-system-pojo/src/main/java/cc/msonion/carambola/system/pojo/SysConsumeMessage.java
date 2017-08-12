package cc.msonion.carambola.system.pojo;

import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;

public class SysConsumeMessage implements MsOnionBasePojoAdapter {
    /**
     * MQ消费消息主键idx，分布式架构，全局唯一递增
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
     * MQ消息Id，固定是32位MD5值，根据msgId可以解决消息重复消费问题
     */
    private String msgId;

    /**
     * MQ的Topic，最大长度50字符
     */
    private String topic;

    /**
     * MQ的Tags，最大长度50字符
     */
    private String tags;

    /**
     * MQ的Keys，最大长度200字符
     */
    private String keys;

    /**
     * MQ消息内容，最大长度2000字符
     */
    private String message;

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
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    private Date createTime;

    /**
     * 更新时间，每次修改，都要更新时间
     */
    private Date updateTime;

    /**
     * 扩展，最大长度100字符
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
     * MQ消费消息主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * MQ消费消息主键idx，分布式架构，全局唯一递增
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
     * MQ消息Id，固定是32位MD5值，根据msgId可以解决消息重复消费问题
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * MQ消息Id，固定是32位MD5值，根据msgId可以解决消息重复消费问题
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    /**
     * MQ的Topic，最大长度50字符
     */
    public String getTopic() {
        return topic;
    }

    /**
     * MQ的Topic，最大长度50字符
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * MQ的Tags，最大长度50字符
     */
    public String getTags() {
        return tags;
    }

    /**
     * MQ的Tags，最大长度50字符
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * MQ的Keys，最大长度200字符
     */
    public String getKeys() {
        return keys;
    }

    /**
     * MQ的Keys，最大长度200字符
     */
    public void setKeys(String keys) {
        this.keys = keys == null ? null : keys.trim();
    }

    /**
     * MQ消息内容，最大长度2000字符
     */
    public String getMessage() {
        return message;
    }

    /**
     * MQ消息内容，最大长度2000字符
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
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
     * 扩展，最大长度100字符
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展，最大长度100字符
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
        sb.append(", msgId=").append(msgId);
        sb.append(", topic=").append(topic);
        sb.append(", tags=").append(tags);
        sb.append(", keys=").append(keys);
        sb.append(", message=").append(message);
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