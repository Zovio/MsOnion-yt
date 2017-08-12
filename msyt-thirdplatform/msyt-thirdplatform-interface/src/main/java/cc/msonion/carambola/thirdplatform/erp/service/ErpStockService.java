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
package cc.msonion.carambola.thirdplatform.erp.service;
/**
 * @Title: ErpStockService
 * @Package: cc.msonion.carambola.thirdplatform.erp.service
 * @Description: ERP库存服务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 19:12:09
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月06日 19:12:09
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.thirdplatform.erp.pojo.request.StockQueryRequest;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseErpService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.Map;

/**
 * @ClassName: ErpStockService
 * @Description: ERP库存服务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 19:12:09
 */
public interface ErpStockService extends MsOnionBaseErpService {




    /**
     * @param apiVersion api版本
     * @param startTime 开始时间 <格式：YYYY-MM-DD HH:mm:ss/>
     * @param endTime 结束时间
     * @param warehouseNo 仓库编号
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getStockCount
     * @Description 获取ERP货品数量
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    MsOnionResult getStockCount(MsOnionApiVersion apiVersion, String startTime, String endTime, String warehouseNo)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 查询库存集合
     * @param apiVersion api版本
     * @param stockQueryRequest 库存查询请求
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException  the ms onion exception
     */
    MsOnionResult queryStock(MsOnionApiVersion apiVersion, StockQueryRequest stockQueryRequest)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取库存同步开始时间
     * @Title getStockSyncBeginTime
     * @Description 描述信息
     * @param apiVersion api版本
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月10日 16:47:43
     */
    MsOnionResult getStockSyncBeginTime(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException;;
    /**
     * 设置库存同步结束时间
     * @Title setStockSyncEndTime
     * @Description 描述信息
     * @param apiVersion api版本
     * @param endTime 结束时间
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月10日 16:47:43
     */
    MsOnionResult setStockSyncEndTime(MsOnionApiVersion apiVersion, String endTime)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 查询商品的ERP库存
     * @Title queryItemListStock
     * @Description 描述信息
     * @param apiVersion api版本
     * @param collectorItemMap 商品对象集合
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月28日 19:35:52
     */
    MsOnionResult queryItemListStock(MsOnionApiVersion apiVersion, Map<Integer, CollectorItem> collectorItemMap);
}
