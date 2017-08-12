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
package cc.msonion.carambola.thirdplatform.erp.pojo.response;
/**
 * @Title: Stock
 * @Package: cc.msonion.carambola.thirdplatform.erp.pojo.response
 * @Description: ERP库存实体
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 21:10:07
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月06日 21:10:07
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @ClassName: Stock
 * @Description: ERP库存实体
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 21:10:07
 */
public class Stock implements Serializable {


    /**
     * 主键,用于logistics_sync_ack回写状态
     */
    @JSONField(name = "spec_id")
    private Long specId;

    /**
     * 货品编号
     */
    @JSONField(name = "goods_no")
    private String goodsNo;

    /**
     * 货品名称
     */
    @JSONField(name = "goods_name")
    private String goodsName;

    /**
     * 商家编码
     */
    @JSONField(name = "spec_no")
    private String specNo;

    /**
     * 规格名称
     */
    @JSONField(name = "spec_name")
    private String specName;

    /**
     * 规格码
     */
    @JSONField(name = "spec_code")
    private String specCode;

    /**
     * 条形码
     */
    @JSONField(name = "barcode")
    private String barcode;

    /**
     * 仓库主键id
     */
    @JSONField(name = "warehouse_id")
    private String warehouseId;

    /**
     * 仓库编号
     */
    @JSONField(name = "warehouse_no")
    private String warehouseNo;

    /**
     * 仓库名称
     */
    @JSONField(name = "warehouse_name")
    private String warehouseName;

    /**
     * 仓库类型0为普通,非0为外部WMS
     */
    @JSONField(name = "warehouse_type")
    private String warehouseType;

    /**
     * 库存量
     */
    @JSONField(name = "stock_num")
    private String stockNum;

    /**
     * 未付款量
     */
    @JSONField(name = "unpay_num")
    private String unpayNum;

    /**
     * 预订单量
     */
    @JSONField(name = "subscribe_num")
    private String subscribeNum;

    /**
     * 待审核量
     */
    @JSONField(name = "order_num")
    private String orderNum;

    /**
     * 待发货量
     */
    @JSONField(name = "sending_num")
    private String sendingNum;

    /**
     * 采购在途量
     */
    @JSONField(name = "purchase_num")
    private String purchaseNum;

    /**
     * 调拨在途量
     */
    @JSONField(name = "transfer_num")
    private String transferNum;

    /**
     * 待采购量
     */
    @JSONField(name = "to_purchase_num")
    private String toPurchaseNum;

    /**
     * 采购到货量
     */
    @JSONField(name = "purchase_arrive_num")
    private String purchaseArriveNum;

    /**
     * 单品在外部WMS中的编码，如在物流宝仓库中主键
     */
    @JSONField(name = "spec_wh_no")
    private String specWhNo;

    /**
     * 外部WMS同步时库存
     */
    @JSONField(name = "wms_sync_stock")
    private String wmsSyncStock;

    /**
     * 外部WMS同步时占用库存
     */
    @JSONField(name = "wms_preempty_stock")
    private String wmsPreemptyStock;

    /**
     * 外部WMS同步时,与系统库存的差
     */
    @JSONField(name = "wms_stock_diff")
    private String wmsStockDiff;


    /**
     * 与外部WMS同步时间wms_sync_stock-stock_num
     */
    @JSONField(name = "wms_sync_time")
    private String wmsSyncTime;

    /**
     * 成本价
     */
    @JSONField(name = "cost_price")
    private String costPrice;

    /**
     * 成本价
     */
    @JSONField(name = "weight")
    private String weight;

    /**
     * 商品图片url
     */
    @JSONField(name = "img_url")
    private String imgUrl;

    /**
     * 商品ID
     */
    @JSONField(serialize = false)
    private Long goodsId;

    /**
     * @return specId
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * @param specId specId
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * @return goodsNo
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * @param goodsNo goodsNo
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    /**
     * @return goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * @return specNo
     */
    public String getSpecNo() {
        return specNo;
    }

    /**
     * @param specNo specNo
     */
    public void setSpecNo(String specNo) {
        this.specNo = specNo;
    }

    /**
     * @return specName
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * @param specName specName
     */
    public void setSpecName(String specName) {
        this.specName = specName;
    }

    /**
     * @return specCode
     */
    public String getSpecCode() {
        return specCode;
    }

    /**
     * @param specCode specCode
     */
    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    /**
     * @return barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @return warehouseId
     */
    public String getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId warehouseId
     */
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return warehouseNo
     */
    public String getWarehouseNo() {
        return warehouseNo;
    }

    /**
     * @param warehouseNo warehouseNo
     */
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    /**
     * @return warehouseName
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * @param warehouseName warehouseName
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * @return warehouseType
     */
    public String getWarehouseType() {
        return warehouseType;
    }

    /**
     * @param warehouseType warehouseType
     */
    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }

    /**
     * @return stockNum
     */
    public String getStockNum() {
        return stockNum;
    }

    /**
     * @param stockNum stockNum
     */
    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * @return unpayNum
     */
    public String getUnpayNum() {
        return unpayNum;
    }

    /**
     * @param unpayNum unpayNum
     */
    public void setUnpayNum(String unpayNum) {
        this.unpayNum = unpayNum;
    }

    /**
     * @return subscribeNum
     */
    public String getSubscribeNum() {
        return subscribeNum;
    }

    /**
     * @param subscribeNum subscribeNum
     */
    public void setSubscribeNum(String subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    /**
     * @return orderNum
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum orderNum
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return sendingNum
     */
    public String getSendingNum() {
        return sendingNum;
    }

    /**
     * @param sendingNum sendingNum
     */
    public void setSendingNum(String sendingNum) {
        this.sendingNum = sendingNum;
    }

    /**
     * @return purchaseNum
     */
    public String getPurchaseNum() {
        return purchaseNum;
    }

    /**
     * @param purchaseNum purchaseNum
     */
    public void setPurchaseNum(String purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    /**
     * @return transferNum
     */
    public String getTransferNum() {
        return transferNum;
    }

    /**
     * @param transferNum transferNum
     */
    public void setTransferNum(String transferNum) {
        this.transferNum = transferNum;
    }

    /**
     * @return toPurchaseNum
     */
    public String getToPurchaseNum() {
        return toPurchaseNum;
    }

    /**
     * @param toPurchaseNum toPurchaseNum
     */
    public void setToPurchaseNum(String toPurchaseNum) {
        this.toPurchaseNum = toPurchaseNum;
    }

    /**
     * @return purchaseArriveNum
     */
    public String getPurchaseArriveNum() {
        return purchaseArriveNum;
    }

    /**
     * @param purchaseArriveNum purchaseArriveNum
     */
    public void setPurchaseArriveNum(String purchaseArriveNum) {
        this.purchaseArriveNum = purchaseArriveNum;
    }

    /**
     * @return specWhNo
     */
    public String getSpecWhNo() {
        return specWhNo;
    }

    /**
     * @param specWhNo specWhNo
     */
    public void setSpecWhNo(String specWhNo) {
        this.specWhNo = specWhNo;
    }

    /**
     * @return wmsSyncStock
     */
    public String getWmsSyncStock() {
        return wmsSyncStock;
    }

    /**
     * @param wmsSyncStock wmsSyncStock
     */
    public void setWmsSyncStock(String wmsSyncStock) {
        this.wmsSyncStock = wmsSyncStock;
    }

    /**
     * @return wmsPreemptyStock
     */
    public String getWmsPreemptyStock() {
        return wmsPreemptyStock;
    }

    /**
     * @param wmsPreemptyStock wmsPreemptyStock
     */
    public void setWmsPreemptyStock(String wmsPreemptyStock) {
        this.wmsPreemptyStock = wmsPreemptyStock;
    }

    /**
     * @return wmsStockDiff
     */
    public String getWmsStockDiff() {
        return wmsStockDiff;
    }

    /**
     * @param wmsStockDiff wmsStockDiff
     */
    public void setWmsStockDiff(String wmsStockDiff) {
        this.wmsStockDiff = wmsStockDiff;
    }

    /**
     * @return wmsSyncTime
     */
    public String getWmsSyncTime() {
        return wmsSyncTime;
    }

    /**
     * @param wmsSyncTime wmsSyncTime
     */
    public void setWmsSyncTime(String wmsSyncTime) {
        this.wmsSyncTime = wmsSyncTime;
    }

    /**
     * @return costPrice
     */
    public String getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice costPrice
     */
    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return goodsId
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
