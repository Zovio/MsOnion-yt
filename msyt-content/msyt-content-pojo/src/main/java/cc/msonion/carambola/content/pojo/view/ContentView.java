/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.content.pojo.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @Title: ContentObject
 * @Package: cc.msonion.carambola.content.pojo
 * @Description: Content pojo 对象 包括图片
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月18日 10:31:31
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月18日 10:31:31
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: ContentObject
 * @Description: Content pojo 对象 包括图片
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月18日 10:31:31
 */
public class ContentView implements Serializable {

    /**
     * 主键idx，分布式架构，全局唯一递增
     */
    private Long idx;

    /**
     * 唯一标识码，最多20个字符，不可以为null
     */
    private String code;

    /**
     * 名称，最多50个字符，不可以为null
     */
    private String name;

    /**
     * 类型，不可以为null，1：html，2：多图
     */
    private Short type;

    /**
     * HTML内容，最多1000个字符，不可以为null
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     有效开始时间，不可以为null，
     中文日期格式：yyyy年MM月dd日 HH:mm:ss，例如：2017年05月02日 22:12:28
     */
    private String startTimeCnYyyyMMddHHmmss;

    /**
     * 有效开始时间，不可以为null
     */
    private Date startTime;

    /**
     有效结束时间，不可以为null，
     中文日期格式：yyyy年MM月dd日 HH:mm:ss，例如：2017年05月02日 22:12:28
     */
    private String endTimeCnYyyyMMddHHmmss;

    /**
     * 有效结束时间，不可以为null
     */
    private Date endTime;

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
     *  contentImageList
     */
    private List<ContentImageView> contentImageList;


    public ContentView() {
    }

    /**
     * @return idx
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * @param idx idx
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return type
     */
    public Short getType() {
        return type;
    }

    /**
     * @param type type
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return startTimeCnYyyyMMddHHmmss
     */
    public String getStartTimeCnYyyyMMddHHmmss() {
        return startTimeCnYyyyMMddHHmmss;
    }

    /**
     * @param startTimeCnYyyyMMddHHmmss startTimeCnYyyyMMddHHmmss
     */
    public void setStartTimeCnYyyyMMddHHmmss(String startTimeCnYyyyMMddHHmmss) {
        this.startTimeCnYyyyMMddHHmmss = startTimeCnYyyyMMddHHmmss;
    }

    /**
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return endTimeCnYyyyMMddHHmmss
     */
    public String getEndTimeCnYyyyMMddHHmmss() {
        return endTimeCnYyyyMMddHHmmss;
    }

    /**
     * @param endTimeCnYyyyMMddHHmmss endTimeCnYyyyMMddHHmmss
     */
    public void setEndTimeCnYyyyMMddHHmmss(String endTimeCnYyyyMMddHHmmss) {
        this.endTimeCnYyyyMMddHHmmss = endTimeCnYyyyMMddHHmmss;
    }

    /**
     *
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return createByMemberIdx
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * @param createByMemberIdx createByMemberIdx
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * @return updateByMemberIdx
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * @param updateByMemberIdx updateByMemberIdx
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return contentImageList
     */
    public List<ContentImageView> getContentImageList() {
        return contentImageList;
    }

    /**
     * @param contentImageList contentImageList
     */
    public void setContentImageList(List<ContentImageView> contentImageList) {
        this.contentImageList = contentImageList;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {

        return new StringBuffer("ContentView{")
                .append("idx=").append(idx)
                .append(", code='").append(code).append('\'')
                .append(", name=").append(name).append('\'')
                .append(", type=").append(type)
                .append(", content='").append(content).append('\'')
                .append(", startTimeCnYyyyMMddHHmmss='").append(startTimeCnYyyyMMddHHmmss).append('\'')
                .append(", startTime=").append(startTimeCnYyyyMMddHHmmss)
                .append(", endTimeCnYyyyMMddHHmmss='").append(endTimeCnYyyyMMddHHmmss).append('\'')
                .append(", endTime=").append(endTime)
                .append(", createByMemberIdx=").append(createByMemberIdx)
                .append(", updateByMemberIdx=").append(updateByMemberIdx)
                .append(", createTime=").append(createTime)
                .append(", updateTime=").append(updateTime)
                .append(", contentImageList=").append(contentImageList)
                .append('}').toString();
    }
}
