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
package cc.msonion.carambola.manager.web.scheduler.erp;
/**
 * @Title: ErpStockScheduler
 * @Package: cc.msonion.carambola.manager.web.scheduler.erp
 * @Description: erp库存定时同步
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月07日 18:12:11
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月07日 18:12:11
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.parent.common.constants.MsOnionDynamicRedisKeyConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpConstants;
import cc.msonion.carambola.thirdplatform.erp.common.constants.ErpPagingConstants;
import cc.msonion.carambola.thirdplatform.erp.service.ErpStockService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.web.controller.ext.ErpSyncCommon;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.warehouse.service.WarehouseService;
import cc.msonion.carambola.warehouse.service.WarehouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ErpStockScheduler
 * @Description: erp库存定时同步
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月07日 18:12:11
 */
public class ErpStockScheduler extends MsOnionBaseAppController {

    /**
     * ERP 库存服务
     */
    @Autowired
    private ErpStockService erpStockService;

    /**
     * 商品服务
     */
    @Autowired
    private ItemService itemService;

    /**
     * 库存服务
     */
    @Autowired
    private WarehouseService warehouseService;

    /**
     * 库存服务
     */
    @Autowired
    private WarehouseTypeService warehouseTypeService;

    /**
     * dynamicCacheService
     */
    @Autowired
    private DynamicCacheService dynamicCacheService;

    /**
     * 定时同步商品库存
     */
    public void erpStockSyncToItem() {
        try {

            this.getMsOnionLogger().info(getClass().getName(), "#### 定时同步商品库存 ####");

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            MsOnionResult stockSyncBeginTimeResult = getStockSyncBeginTime();
            if (stockSyncBeginTimeResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                this.getMsOnionLogger().info(getClass().getName(), "#### 查询同步开始时间 "
                        + stockSyncBeginTimeResult.getMsg() + " ####");
                return;
            }
            Map<String, String> timeMap = (Map<String, String>) stockSyncBeginTimeResult.getData();
            String startTime = timeMap.get("startTime");
            String endTime = timeMap.get("endTime");
            // 查询ERP商品总数量
            MsOnionResult totalCountResult = erpStockService.getStockCount(msOnionApiVersion, startTime,
                    endTime, ErpConstants.ERP_DEFAULT_ALL_WZREHOSE_NO);
            if (totalCountResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                this.getMsOnionLogger().info(getClass().getName(), "#### 查询ERP商品总数量 "
                        + totalCountResult.getMsg() + " ####");
                return;
            }

            // 商品总数量
            long totalCount = (long) totalCountResult.getData();
            if (totalCount <= 0) {
                this.getMsOnionLogger().info(getClass().getName(), "#### 查询ERP商品总数量 "
                        + totalCount + " ####");
                return;
            }

            // 获取总页数
            long pageSize = ErpPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE;
            long totalPage = 0;
            if (totalCount % pageSize == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = (totalCount / pageSize) + 1;
            }

            long beginPage = ErpPagingConstants.PAGING_PAGENUM_MIN_VALUE;
            long endPage = totalPage - 1;

            // 分批查询，一次100条，循环更新商品库存
            ErpSyncCommon erpSyncCommon = new ErpSyncCommon(erpStockService, this.getMsOnionLogger(),
                    itemService, warehouseService, warehouseTypeService);
            erpSyncCommon.syncUpdateItem(msOnionApiVersion, beginPage, endPage, startTime, endTime);

            dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.ERP_STOCK_SYNC_TIME_REDIS_KEY,
                    endTime, ErpConstants.SYNC_TIME_EXPIRE_TIME);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "#### 处理ERP库存同步出错 ####");
        }
    }

    /**
     * 获取ERP设置时间
     * @return  MsOnionResult MsOnionResult
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException MsOnionException
     */
    private  MsOnionResult getStockSyncBeginTime() throws MsOnionIllegalArgumentException, MsOnionException {
        Date curDate = new Date();

        String syncTime =  dynamicCacheService.getFromRedisWithSecurity(
                MsOnionDynamicRedisKeyConstants.ERP_STOCK_SYNC_TIME_REDIS_KEY);
        String syncEndTime = MsOnionDateUtils.formatYyyyMMddHHmmss(curDate);
        if (MsOnionStringUtils.isEmpty(syncTime)) {
            syncTime = MsOnionDateUtils.formatYyyyMMddHHmmss(
                    MsOnionDateUtils.addMinutes(curDate, -ErpConstants.ERP_STOCK_SYNC_TIME_INTERVAL));
            dynamicCacheService.setForRedisWithSecurity(MsOnionDynamicRedisKeyConstants.ERP_STOCK_SYNC_TIME_REDIS_KEY,
                    syncTime, ErpConstants.SYNC_TIME_EXPIRE_TIME);
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime", syncTime);
        map.put("endTime", syncEndTime);

        return MsOnionResult.ok(map);
    }


}
