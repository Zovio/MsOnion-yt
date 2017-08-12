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


package cc.msonion.carambola.fileresource.common.viewobject;

/**
 * @Title: AlbumViewObject.java
 * @Package: cc.msonion.carambola.fileresource.common.viewObject
 * @Description: 相册视图对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月13日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月13日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AlbumViewObject
 * @Description: 相册视图对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月13日
 *
 */
public class AlbumViewObject implements Serializable {


    private static final long serialVersionUID = 7070754948594503793L;

    /**
     *  文件idx
     */
    private Long fileIdx;

    /**
     * 文件uuid
     */
    private String messageId;

    /**
     * 名称
     */
    private String name;

    /**
     * 相册类型
     */
    private Short  albumType;

    /**
     * 文件总数量
     */
    private Integer totalNum = 0;

    /**
     * 文件总大小
     */
    private Long totalSize = 0L;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间 （也可用来排序）
     */
    private Date createTime;

    /**
     * 相对路径
     */
    private String relativePath;


    /**
     * 父级名称，通过name关联父级
     */
    private String parentName;


    public AlbumViewObject() {
    }

    /**
     *
     * @return fileIdx
     */
    public Long getFileIdx() {
        return fileIdx;
    }

    /**
     *
     * @param fileIdx fileIdx
     */
    public void setFileIdx(Long fileIdx) {
        this.fileIdx = fileIdx;
    }

    /**
     *
     * @return messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     *
     * @param messageId messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return albumType
     */
    public Short getAlbumType() {
        return albumType;
    }

    /**
     *
     * @param albumType albumType
     */
    public void setAlbumType(Short albumType) {
        this.albumType = albumType;
    }

    /**
     *
     * @return totalNum
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     *
     * @param totalNum totalNum
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     *
     * @return totalSize
     */
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     *
     * @param totalSize totalSize
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     * @return parentName
     */
    public String getParentName() {
        return parentName;
    }

    /**
     *
     * @param parentName parentName
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     *
     * @return relativePath
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     *
     * @param relativePath relativePath
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        AlbumViewObject that = (AlbumViewObject) o;
        return name != null ? name.equals(that.name) : false;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
    }
}
