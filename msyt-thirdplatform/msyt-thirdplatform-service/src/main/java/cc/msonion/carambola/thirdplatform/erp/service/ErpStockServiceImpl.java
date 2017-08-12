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
 * @Title: ErpStockServiceImpl
 * @Package: cc.msonion.carambola.thirdplatform.erp.service
 * @Description: ERP库存服务实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:25:40
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月06日 20:25:40
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpConstants;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpPagingConstants;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpPathConstants;
import cc.msonion.carambola.thirdplatform.erp.common.utils.ParseUtil;
import cc.msonion.carambola.thirdplatform.erp.common.utils.SignUtil;
import cc.msonion.carambola.thirdplatform.erp.pojo.ErpParame;
import cc.msonion.carambola.thirdplatform.erp.pojo.request.StockQueryRequest;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.StockQueryResponse;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionDynamicRedisKeyConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionOkHttp3Utils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.interfaces.service.MsOnionErpService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName: ErpStockServiceImpl
 * @Description: ERP库存服务实现
 *
 * warehouseNo 这个字段ERP 测试环境是必须要填的，到时候对接erp 开发环境，这个字段可以为空，即查询全部
 *
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:25:40
 */
@Service
public class ErpStockServiceImpl extends MsOnionErpService implements ErpStockService {

    /**
     * ERP相关参数
     */
    @Autowired
    private ErpParame erpParame;


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
    @Override
    public MsOnionResult getStockCount(MsOnionApiVersion apiVersion, String startTime, String endTime, String warehouseNo)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            inspectParameters(apiVersion);

            StockQueryRequest stockQueryRequest = new StockQueryRequest();
            stockQueryRequest.setStartTime(MsOnionStringUtils.defaultString(startTime, ""));
            stockQueryRequest.setEndTime(MsOnionStringUtils.defaultString(endTime, ""));
            stockQueryRequest.setWarehouseNo(warehouseNo);
            stockQueryRequest.setPageNo(ErpPagingConstants.PAGING_PAGENUM_MIN_VALUE);
            stockQueryRequest.setPageSize(ErpPagingConstants.PAGING_PAGESIZE_MIN_VALUE);

            String errorMsg = MsOnionPojoValidatorUtils.validatePojo(stockQueryRequest);

            if (MsOnionStringUtils.isNotBlank(errorMsg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, errorMsg);
            }

            StockQueryResponse stockQueryResponse = (StockQueryResponse) ParseUtil.
                    parseMapToPojo(postRequest(stockQueryRequest), StockQueryResponse.class);
            if (stockQueryResponse == null || stockQueryResponse.getCode() != 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, stockQueryResponse.getMessage());
            }
            return MsOnionResult.ok(stockQueryResponse.getTotalCount());
        } catch (MsOnionException e) {
            throw  e;
        }
    }

    /**
     * @param apiVersion        api版本
     * @param stockQueryRequest 库存查询请求
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult queryStock(MsOnionApiVersion apiVersion, StockQueryRequest stockQueryRequest)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            inspectParameters(apiVersion);

            String errorMsg = MsOnionPojoValidatorUtils.validatePojo(stockQueryRequest);

            if (MsOnionStringUtils.isNotBlank(errorMsg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, errorMsg);
            }


            StockQueryResponse stockQueryResponse = (StockQueryResponse) ParseUtil.
                    parseMapToPojo(postRequest(stockQueryRequest), StockQueryResponse.class);
            if (stockQueryResponse == null || stockQueryResponse.getCode() != 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, stockQueryResponse.getMessage());
            }
            return MsOnionResult.ok(stockQueryResponse.getStocks());
        } catch (MsOnionException e) {
            throw  e;
        }
    }

    /**
     * 获取库存同步开始时间
     *
     * @param apiVersion api版本
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getStockSyncBeginTime
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月10日 16:47:43
     */
    @Override
    public MsOnionResult getStockSyncBeginTime(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {

        inspectParameters(apiVersion);
        Date curDate = new Date();
        String syncTime = getForRedis(MsOnionDynamicRedisKeyConstants.ERP_STOCK_SYNC_TIME_REDIS_KEY);
        String syncEndTime = MsOnionDateUtils.formatYyyyMMddHHmmss(curDate);
        if (MsOnionStringUtils.isEmpty(syncTime)) {
            syncTime = MsOnionDateUtils.formatYyyyMMddHHmmss(
                    MsOnionDateUtils.addMinutes(curDate, -ErpConstants.ERP_STOCK_SYNC_TIME_INTERVAL));
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime", syncTime);
        map.put("endTime", syncEndTime);
        return MsOnionResult.ok(map);
    }

    /**
     * 设置库存同步结束时间
     *
     * @param apiVersion api版本
     * @param endTime    结束时间
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title setStockSyncEndTime
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月10日 16:47:43
     */
    @Override
    public MsOnionResult setStockSyncEndTime(MsOnionApiVersion apiVersion, String endTime) throws MsOnionIllegalArgumentException, MsOnionException {
        setForRedis(MsOnionDynamicRedisKeyConstants.ERP_STOCK_SYNC_TIME_REDIS_KEY, endTime, ErpConstants.SYNC_TIME_EXPIRE_TIME);
        return MsOnionResult.ok();
    }

    /**
     * 请求接口
     * @param stockQueryRequest 库存查询
     * @return json对象
     * @throws MsOnionException                the ms onion exception
     */
    private JSONObject postRequest(StockQueryRequest stockQueryRequest) throws MsOnionException {
        JSONObject returnObject = null;
        int retryTimes = 0;
        try {
            returnObject = retryRequest(retryTimes, stockQueryRequest);
        } catch (MsOnionException e) {
            try {
                returnObject = retryRequest(retryTimes, stockQueryRequest);
            } catch (MsOnionException e1) {
                this.getMsOnionLogger().error(this.getClass().getName(), e1, "#### 调用ERP接口重试出错 #####");
//                System.out.println("#### 调用ERP接口重试出错 #####");
            }
        }
        return returnObject;
    }

    /**
     * 调用失败重试
     * @param retryTimes 重试次数
     * @param stockQueryRequest 请求参数
     * @return json对象
     * @throws MsOnionException 系统异常
     */
    private JSONObject retryRequest(int retryTimes, StockQueryRequest stockQueryRequest) throws MsOnionException {
        TreeMap<String, Object> paramMap =  ParseUtil.parsePojoToMap(stockQueryRequest);
        String postUrl = erpParame.getErpDomainUrl() + ErpPathConstants.STOCK_QUERY_URL;
        paramMap = SignUtil.genBaseErpMap(paramMap, erpParame.getAppKey(),
                erpParame.getAppSecret(), erpParame.getSid());
        JSONObject returnObject = MsOnionOkHttp3Utils.post(postUrl, paramMap);
        if (retryTimes >= ErpConstants.MAX_RETRY_TIMES) {
            return returnObject;
        }
        if (returnObject == null
                || returnObject.getLong("code") == ErpConstants.SIGN_ERROR_CODE) {
            retryTimes++;
            try {
                Thread.sleep(ErpConstants.RETRY_TIME_INTERVAL);
            } catch (InterruptedException e) {
            }
            this.getMsOnionLogger().info(this.getClass().getName(), "#### 调用ERP接口失败重试 #####");
//            System.out.println("#### 调用ERP接口失败重试," + retryTimes + " #####");
            returnObject = retryRequest(retryTimes, stockQueryRequest);
        }
        return returnObject;
    }

    /**
     * 查询商品的ERP库存
     *
     * @param apiVersion       api版本
     * @param collectorItemMap 商品对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title queryItemListStock
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月28日 19:35:52
     */
    @Override
    public MsOnionResult queryItemListStock(MsOnionApiVersion apiVersion, Map<Integer, CollectorItem> collectorItemMap) {

        if (MsOnionMapUtils.isEmpty(collectorItemMap)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        Map<Integer, Stock> itemStockMap = new HashMap<Integer, Stock>();
        Map<Integer, Stock> errorItemStockMap = new HashMap<Integer, Stock>();
        MsOnionBatchReport<Stock> msOnionBatchReport = new MsOnionBatchReport<Stock>();
        for (Integer i : collectorItemMap.keySet()) {
            CollectorItem collectorItem = null;
            try {
                collectorItem = collectorItemMap.get(i);
                if (MsOnionStringUtils.isEmpty(collectorItem.getBarcode())) {
                    errorItemStockMap.put(i, null);
                    continue;
                }
                StockQueryRequest stockQueryRequest = new StockQueryRequest();
                stockQueryRequest.setStartTime(null);
                stockQueryRequest.setEndTime(null);
                stockQueryRequest.setPageNo(0L);
                stockQueryRequest.setSpecNo(collectorItem.getItemNo());
                stockQueryRequest.setWarehouseNo(ErpConstants.ERP_DEFAULT_ALL_WZREHOSE_NO);
                stockQueryRequest.setPageSize(ErpPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE);

                StockQueryResponse stockQueryResponse = (StockQueryResponse) ParseUtil.
                        parseMapToPojo(postRequest(stockQueryRequest), StockQueryResponse.class);
                if (stockQueryResponse == null || stockQueryResponse.getCode() != 0
                        || MsOnionCollectionUtils.isEmpty(stockQueryResponse.getStocks())) {
                    errorItemStockMap.put(i, null);
                   continue;
                }
                this.getMsOnionLogger().info(this.getClass().getName(),
                        "####### 查询商品条形码为 " + collectorItem.getBarcode()
                                + ",返回库存集合大小 " + stockQueryResponse.getStocks().size() + " ##############");
                Stock stock = stockQueryResponse.getStocks().get(0);
                stock.setGoodsId(collectorItem.getIdx());

                if (MsOnionCollectionUtils.isNotEmpty(stockQueryResponse.getStocks())
                        && stockQueryResponse.getStocks().size() > 1) {

                    double stockNum = 0d;

                    for (Stock s : stockQueryResponse.getStocks()) {
                        stockNum += MsOnionNumberUtils.toDouble(s.getStockNum(), 0);
                    }
                    stock.setStockNum(stockNum + "");
                } else {
                    stock.setStockNum("0");
                }

                itemStockMap.put(i, stock);
            } catch (MsOnionException e) {
                errorItemStockMap.put(i, null);
                this.getMsOnionLogger().error(this.getClass().getName(), e,
                        " ####### 查询商品条形码为 " + collectorItem.getBarcode() + " 的库存出错 ######## ");
            }
        }
        msOnionBatchReport.setSuccessRecords(itemStockMap);
        msOnionBatchReport.setFailureRecords(errorItemStockMap);
        return MsOnionResult.ok(msOnionBatchReport);
    }
}
