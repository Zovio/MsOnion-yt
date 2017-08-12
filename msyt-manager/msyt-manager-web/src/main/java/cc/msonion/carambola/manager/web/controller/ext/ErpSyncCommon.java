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
package cc.msonion.carambola.manager.web.controller.ext;
/**
 * @Title: ErpSyncUtils
 * @Package: cc.msonion.carambola.manager.web.controller.ext
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月28日 13:57:05
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月28日 13:57:05
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpConstants;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpPagingConstants;
import cc.msonion.carambola.thirdplatform.erp.pojo.request.StockQueryRequest;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import cc.msonion.carambola.thirdplatform.erp.service.ErpStockService;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.warehouse.pojo.Warehouse;
import cc.msonion.carambola.warehouse.service.WarehouseService;
import cc.msonion.carambola.warehouse.service.WarehouseTypeService;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ErpSyncUtils
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月28日 13:57:05
 */
public class ErpSyncCommon {

    /**
     * ErpStockService
     */
    private ErpStockService erpStockService;
    /**
     * msOnionLogger
     */
    private MsOnionLogger msOnionLogger;

    /**
     * 商品服务
     */
    private ItemService itemService;

    /**
     * 库存服务
     */
    private WarehouseService warehouseService;
    /**
     * 仓库服务
     */
    private WarehouseTypeService warehouseTypeService;

    public ErpSyncCommon(ErpStockService erpStockService, MsOnionLogger msOnionLogger, ItemService itemService,
                         WarehouseService warehouseService, WarehouseTypeService warehouseTypeService) {
        this.erpStockService = erpStockService;
        this.msOnionLogger = msOnionLogger;
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.warehouseTypeService = warehouseTypeService;
    }

    /**
     * 循环查询和修改商品库存
     * @param msOnionApiVersion 版本号
     * @param currentPage 当前页
     * @param endPage 结束页
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    public void syncUpdateItem(MsOnionApiVersion msOnionApiVersion, long currentPage, long endPage,
                                String startTime, String endTime) {
        try {
            StockQueryRequest stockQueryRequest = new StockQueryRequest();
            stockQueryRequest.setStartTime(startTime);
            stockQueryRequest.setEndTime(endTime);
            stockQueryRequest.setPageNo(currentPage);
            stockQueryRequest.setWarehouseNo(ErpConstants.ERP_DEFAULT_ALL_WZREHOSE_NO);
            stockQueryRequest.setPageSize(ErpPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE);
            MsOnionResult msOnionResult = erpStockService.queryStock(msOnionApiVersion, stockQueryRequest);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                msOnionLogger.error(getClass().getName(),
                        "#### 循环查询和修改商品库存出错，" + msOnionResult.getMsg() + " ####");
            } else {
                List<Stock> itemStockList = (List<Stock>) msOnionResult.getData();
                // 查询并设置商品ID
                List<Stock> erpStockDTOList = queryItemStock(msOnionApiVersion, itemStockList);
                syncItemStock(msOnionApiVersion, erpStockDTOList);
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(getClass().getName(), e,
                    "#### 循环查询和修改商品库存出错，当前页 " + currentPage + " 总页数 " + endPage + " ####");
        } finally {
            currentPage++;
            if (currentPage <= endPage) {
                syncUpdateItem(msOnionApiVersion, currentPage, endPage, startTime, endTime);
            }
        }
    }

    /**
     * 查询设置商品ID
     * @param msOnionApiVersion 版本号
     * @param erpStockList ERP库存对象集合
     * @return 商品库存对象集合
     */
    private List<Stock> queryItemStock(MsOnionApiVersion msOnionApiVersion, List<Stock> erpStockList) {
        try {
            final int defaultInt = -1;
            if (MsOnionCollectionUtils.isEmpty(erpStockList)) {
                return null;
            }
            List<Stock> erpStockListR = new ArrayList<Stock>();
            for (Stock stock : erpStockList) {
                CollectorItemExample collectorItemExample = new CollectorItemExample();
                CollectorItemExample.Criteria criteria = collectorItemExample.createCriteria();
                criteria.andItemNoEqualTo(stock.getGoodsNo().trim());
                criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                List<CollectorItem> collectorItemIdxList = itemService.queryByExample(msOnionApiVersion, collectorItemExample);
                if (MsOnionCollectionUtils.isNotEmpty(collectorItemIdxList)) {
                    CollectorItem item = collectorItemIdxList.get(0);
                    stock.setGoodsId(item.getIdx());
                    erpStockListR.add(stock);
                }
            }
            return erpStockListR;
        } catch (MsOnionException e) {
            msOnionLogger.error(getClass().getName(), e,
                    "#### 处理ERP库存同步出错，设置商品ID出错 ####");
            return null;
        }

    }

    /**
     *  同步库存
     * @param msOnionApiVersion 版本号
     * @param erpStockList ERP库存对象集合
     */
    private void syncItemStock(MsOnionApiVersion msOnionApiVersion, List<Stock> erpStockList) {

        if (CollectionUtils.isEmpty(erpStockList)) {
            return;
        }
        MsOnionResult syncWarehouseStockResult = warehouseService.batchSyncStock(msOnionApiVersion, erpStockList);
        if (syncWarehouseStockResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
            MsOnionBatchReport<Warehouse> msOnionBatchReport = (MsOnionBatchReport<Warehouse>) syncWarehouseStockResult.getData();
            msOnionLogger.info(getClass().getName(),
                    "#### 处理ERP库存同步出错，设置商品ID，成功 " + msOnionBatchReport.getSuccessRecords().size()
                            + "，失败 " + msOnionBatchReport.getFailureRecords().size() + " ####");
        } else {
            msOnionLogger.error(getClass().getName(),
                    "#### 处理ERP库存同步出错，更新商品库存出错，" + syncWarehouseStockResult.getMsg() + " ####");
        }
    }

}
