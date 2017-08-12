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
/**
 * @Title: ContentImageVO
 * @Package: cc.msonion.carambola.cache.pojo
 * @Description: 图片内容VO对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月19日 14:00:55
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年05月19日 14:00:55
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import java.io.Serializable;

/**
 * @ClassName: ContentImageVO
 * @Description: 图片内容VO对象
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年05月19日 14:00:55
 */
public class ContentImageView implements Serializable {


    /**
     * 图片名称，最多50个字符，不可以为null
     */
    private String name;
    /**
     * 图片标题，最多50个字符，不可以为null
     */
    private String title;
    /**
     * 图片链接地址，最多200个字符
     */
    private String linkUrl;
    /**
     * 排序号，升序排列，不可以为null，必须手动设置默认值为0
     */
    private Integer orderNumber;
    /**
     * 小图，最多255个字符
     */
    private String imageSmall;
    /**
     * 中图，最多255个字符
     */
    private String imageMiddle;
    /**
     * 大图，最多255个字符
     */
    private String imageBig;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * @return orderNumber
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @return imageSmall
     */
    public String getImageSmall() {
        return imageSmall;
    }

    /**
     * @return imageMiddle
     */
    public String getImageMiddle() {
        return imageMiddle;
    }

    /**
     * @return imageBig
     */
    public String getImageBig() {
        return imageBig;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @param linkUrl linkUrl
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    /**
     * @param orderNumber orderNumber
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    /**
     * @param imageSmall imageSmall
     */
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }
    /**
     * @param imageMiddle imageMiddle
     */
    public void setImageMiddle(String imageMiddle) {
        this.imageMiddle = imageMiddle;
    }
    /**
     * @param imageBig imageBig
     */
    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }


    @Override
    public String toString() {
        return new StringBuffer("ContentImageVO{" + "name='").append(name).append('\'')
                .append(", title='").append(title).append('\'')
                .append(", linkUrl='").append(linkUrl).append('\'')
                .append(", orderNumber=").append(orderNumber)
                .append(", imageSmall='").append(imageSmall).append('\'')
                .append(", imageMiddle='").append(imageMiddle).append('\'')
                .append(", imageBig='").append(imageBig).append('\'')
                .append('}').toString();
    }


}
