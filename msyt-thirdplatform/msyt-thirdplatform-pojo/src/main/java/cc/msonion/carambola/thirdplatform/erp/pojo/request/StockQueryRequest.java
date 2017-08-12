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
package cc.msonion.carambola.thirdplatform.erp.pojo.request;
/**
 * @Title: StockQueryReq
 * @Package: cc.msonion.carambola.thirdplatform.erp.pojo.request
 * @Description: 库存查询请求参数
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:41:31
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月06日 20:41:31
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: StockQueryReq
 * @Description: 库存查询请求参数
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:41:31
 */
public class StockQueryRequest implements Serializable {

    /**
     * 仓库编号(不指定则返回所有仓库)
     */
    @JSONField(name = "warehouse_no")
    private String warehouseNo;

    /**
     * 货品的商家编码(不指定则返回所有商品)
     */
    @JSONField(name = "spec_no")
    private String specNo;

    /**
     * 条形码
     */
    @JSONField(name = "barcode")
    private String barcode;

    /**
     * 开始时间（最后更新时间）
     */
    @JSONField(name = "start_time")
    private String startTime;

    /**
     * 结束时间（最后更新时间）
     */
    @JSONField(name = "end_time")
    private String endTime;


    /**
     * 页号,从0页开始,默认为0
     */
    @NotNull(message = "页号不能为空")
    @JSONField(name = "page_no")
    private Long pageNo;

    /**
     * 分页大小,默认40,最大100
     */
    @NotNull(message = "分页大小不能为空")
    @Min(value = 1, message = "分页最小值不能小于1")
    @Max(value = 100, message = "分页最大值不能大于100")
    @JSONField(name = "page_size")
    private Long pageSize;

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
     * @return pageNo
     */
    public Long getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo pageNo
     */
    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return pageSize
     */
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize pageSize
     */
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
