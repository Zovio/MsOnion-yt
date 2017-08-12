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
package cc.msonion.carambola.collector.ext.dto;

/**
 * @Title: CollectorItemDTO.java
 * @Package: cc.msonion.carambola.collector.ext.dto
 * @Description: 商品数据传输对象类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/15 19:40
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/15 19:40
 * @Modify-version: V2.0.0
 * @Modify-description: 继承商品类
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemHistory;

/**
 * @ClassName: CollectorItemDTO
 * @Description: 商品数据传输对象类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/15 19:40
 */
public class CollectorItemDTO extends CollectorItem {

    public CollectorItemDTO(CollectorItem item) {
        this.setIdx(item.getIdx());
        this.setItemStateIdx(item.getItemStateIdx());
        this.setItemNo(item.getItemNo());
        this.setBarcode(item.getBarcode());
        this.setCnName(item.getCnName());
        this.setEnName(item.getEnName());
        this.setBrandIdx(item.getBrandIdx());
        this.setOriginIdx(item.getOriginIdx());
        this.setCategoryIdx(item.getCategoryIdx());
        this.setSpecification(item.getSpecification());
        this.setWarehouseTypeIdx(item.getWarehouseTypeIdx());
        this.setBatch(item.getBatch());
        this.setCollectionRemark(item.getCollectionRemark());
        this.setPurchaseStatus(item.getPurchaseStatus());
        this.setCollectionStatus(item.getCollectionStatus());
        this.setWeight(item.getWeight());
        this.setCreateByMemberIdx(item.getCreateByMemberIdx());
        this.setUpdateByMemberIdx(item.getUpdateByMemberIdx());
        this.setCreateTime(item.getCreateTime());
        this.setUpdateTime(item.getUpdateTime());
        this.setStatus(item.getStatus());
        this.setVersion(item.getVersion());
        this.setExt(item.getExt());
    }

    public CollectorItemDTO(CollectorItemHistory itemHistory) {
        this.setIdx(itemHistory.getIdx());
        this.setItemStateIdx(itemHistory.getItemStateIdx());
        this.setCnName(itemHistory.getCnName());
        this.setEnName(itemHistory.getEnName());
        this.setBrandIdx(itemHistory.getBrandIdx());
        this.setOriginIdx(itemHistory.getOriginIdx());
        this.setCategoryIdx(itemHistory.getCategoryIdx());
        this.setSpecification(itemHistory.getSpecification());
        this.setWarehouseTypeIdx(itemHistory.getWarehouseTypeIdx());
        this.setBatch(itemHistory.getBatch());
        this.setCollectionRemark(itemHistory.getCollectionRemark());
        this.setPurchaseStatus(itemHistory.getPurchaseStatus());
        this.setCollectionStatus(itemHistory.getCollectionStatus());
        this.setWeight(itemHistory.getWeight());
        this.setCreateByMemberIdx(itemHistory.getCreateByMemberIdx());
        this.setUpdateByMemberIdx(itemHistory.getUpdateByMemberIdx());
        this.setCreateTime(itemHistory.getCreateTime());
        this.setUpdateTime(itemHistory.getUpdateTime());
        this.setStatus(itemHistory.getStatus());
        this.setVersion(itemHistory.getVersion());
        this.setExt(itemHistory.getExt());
    }

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 品牌中文名称
     */
    private String brandCnName;

    /**
     * 品牌英文名称
     */
    private String brandEnName;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 产地中文名称
     */
    private String originCnName;

    /**
     * 操作人
     */
    private String createByMemberName;

    /**
     * 获取类目名称
     *
     * @return 返回类目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类目名称
     *
     * @param categoryName 类目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取品牌名称
     *
     * @return 返回品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置品牌名称
     *
     * @param brandName 品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取品牌中文名称
     *
     * @return 返回品牌中文名称
     */
    public String getBrandCnName() {
        return brandCnName;
    }

    /**
     * 设置品牌中文名称
     *
     * @param brandCnName 品牌中文名称
     */
    public void setBrandCnName(String brandCnName) {
        this.brandCnName = brandCnName;
    }


    /**
     * 获取品牌英文名称
     *
     * @return 返回品牌英文名称
     */
    public String getBrandEnName() {
        return brandEnName;
    }

    /**
     * 设置品牌英文名称
     *
     * @param brandEnName 品牌英文名称
     */
    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }

    /**
     * 获取产地中文名称
     *
     * @return 返回产地中文名称
     */
    public String getOriginCnName() {
        return originCnName;
    }

    /**
     * 设置产地中文名称
     *
     * @param originCnName 产地中文名称
     */
    public void setOriginCnName(String originCnName) {
        this.originCnName = originCnName;
    }

    /**
     * 获取操作人
     *
     * @return 返回操作人
     */
    public String getCreateByMemberName() {
        return createByMemberName;
    }

    /**
     * 设置操作人
     *
     * @param createByMemberName 操作人
     */
    public void setCreateByMemberName(String createByMemberName) {
        this.createByMemberName = createByMemberName;
    }
}
