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


package cc.msonion.carambola.fileresource.pojo;

/**
 * @Title: FileResourceInfo.java
 * @Package: cc.msonion.carambola.fileresource.api.netty.upload.pojo
 * @Description: 文件上传消息 实体类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月03日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月03日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import java.io.Serializable;

/**
 * @ClassName: FileResourceInfo
 * @Description: 文件上传消息 实体类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月03日
 *
 */
public class FileResourceInfo implements Serializable {

    private static final long serialVersionUID = 3663553526419011506L;

    /**
     * messageId
     */
    private String messageId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long totalSize;

    /**
     * 文件已经上传大小
     */
    private Long uploadSize;

    /**
     * 生成后的文件名
     */
    private String md5Value;

    /**
     * 文件类型
     */
    private Short fileType;

    /**
     * 上传状态 uploading(上传中)：2 ， uploaded （上传完成） : 1  , 上传失败：0
     */
    private Short uploadStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * cos 下载，展示url
     */
    private String  sourceUrl;


    public FileResourceInfo() {

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
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }


    /**
     *
     * @return md5Value
     */
    public String getMd5Value() {
        return md5Value;
    }

    /**
     *
     * @param md5Value md5Value
     */
    public void setMd5Value(String md5Value) {
        this.md5Value = md5Value;
    }

    /**
     *
     * @return fileType
     */
    public Short getFileType() {
        return fileType;
    }

    /**
     *
     * @param fileType fileType
     */
    public void setFileType(Short fileType) {
        this.fileType = fileType;
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
     * @return uploadStatus
     */
    public Short getUploadStatus() {
        return uploadStatus;
    }

    /**
     *
     * @param uploadStatus uploadStatus
     */
    public void setUploadStatus(Short uploadStatus) {
        this.uploadStatus = uploadStatus;
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
     * @return uploadSize
     */
    public Long getUploadSize() {
        return uploadSize;
    }

    /**
     *
     * @param uploadSize uploadSize
     */
    public void setUploadSize(Long uploadSize) {
        this.uploadSize = uploadSize;
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
}
