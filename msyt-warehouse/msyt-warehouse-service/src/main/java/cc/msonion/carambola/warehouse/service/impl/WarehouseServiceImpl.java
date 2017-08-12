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


package cc.msonion.carambola.warehouse.service.impl;

/**
 * @Title: WarehouseServiceImpl.java
 * @Package: cc.msonion.carambola.warehouse.service.impl
 * @Description: 商品仓库service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月23日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import cc.msonion.carambola.warehouse.common.constans.WarehouseConstants;
import cc.msonion.carambola.warehouse.pojo.Warehouse;
import cc.msonion.carambola.warehouse.pojo.WarehouseExample;
import cc.msonion.carambola.warehouse.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WarehouseServiceImpl
 * @Description: 商品仓库service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 */
@Service
public class WarehouseServiceImpl extends MsOnionServiceExt<Warehouse, WarehouseExample>
        implements WarehouseService {


    private static final long serialVersionUID = 9175500348818025886L;

    /**
     * 新增商品仓库
     *
     * @param apiVersion Api版本
     * @param warehouse  商品仓库
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult addWarehouse(MsOnionApiVersion apiVersion, Warehouse warehouse) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == warehouse) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MESSAGE_WAREHOUSE_INFO_NULL);
        }

        String validatorMsg = MsOnionPojoValidatorUtils.validatePojo(warehouse);
        if (StringUtils.isNotBlank(validatorMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validatorMsg);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_IDX_PATH);
        warehouse.setIdx(idx);
        warehouse.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
        warehouse.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，warehouse=" + warehouse);

        int result = this.save(apiVersion, warehouse);
        if (result > 0) {
            return MsOnionResult.ok(warehouse);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 删除商品仓库
     *
     * @param apiVersion Api版本
     * @param idx        商品报关主键idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult deleteWarehouse(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }


    /**
     * 更新商品仓库
     *
     * @param apiVersion Api版本
     * @param warehouse  商品仓库
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult updateWarehouse(MsOnionApiVersion apiVersion, Warehouse warehouse) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == warehouse) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MESSAGE_WAREHOUSE_INFO_NULL);
        }

        String validateMsg = MsOnionPojoValidatorUtils.validatePojo(warehouse);
        if (MsOnionStringUtils.isNotBlank(validateMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateMsg);
        }

        Long itemIdx = warehouse.getItemIdx();

        WarehouseExample example = new WarehouseExample();
        WarehouseExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<Warehouse> itemCustomList = this.queryByExample(apiVersion, example);
        if (null != itemCustomList && itemCustomList.size() > 0) {
            for (Warehouse p : itemCustomList) {
                this.deleteWarehouse(apiVersion, p.getIdx());
            }
        }

        return this.addWarehouse(apiVersion, warehouse);

    }

    /**
     * 通过idx获取商品仓库
     *
     * @param apiVersion Api版本
     * @param idx        商品报关主键idx
     * @return the LogisticsCustomsDeclare by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult getWarehouseByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * 通过商品获取仓库
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回报关信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getWarehouseByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionIllegalArgumentException, MsOnionException {
        WarehouseExample example = new WarehouseExample();
        WarehouseExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<Warehouse> itemCustomList = this.queryByExample(apiVersion, example);
        if (null == itemCustomList || itemCustomList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MESSAGE_WAREHOUSE_INFO_NULL);
        } else if (itemCustomList.size() != 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MESSAGE_WAREHOUSE_EXCEPTION);
        }

        Warehouse itemCustom = itemCustomList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemCustom);
    }


    /**
     * 新增或更新商品库存
     *
     * @param apiVersion    Api版本
     * @param warehouseList 商品库存对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    @Deprecated
    @Override
    public MsOnionResult syncWarehouseStock(MsOnionApiVersion apiVersion, List<Warehouse> warehouseList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        int success = 0;
        int fail = 0;
        if (MsOnionCollectionUtils.isNotEmpty(warehouseList)) {
            for (Warehouse warehouse : warehouseList) {
                try {
                    if (warehouse.getItemIdx() == null
                            || warehouse.getItemIdx() <= 0) {
                        fail++;
                        continue;
                    }
                    saveOrupdateWarehouseStock(apiVersion, warehouse);
                    success++;
                } catch (MsOnionException e) {
                    fail++;
                    this.getMsOnionLogger().error(this.getClass().getName(), e,
                            " ########## 新增或更新商品库存 ########## ");
                }
            }
        }

        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        resultMap.put("success", success);
        resultMap.put("fail", fail);
        return MsOnionResult.ok(resultMap);
    }


    /**
     * 新增或更新库存
     *
     * @param apiVersion Api版本
     * @param warehouse  库存对象
     * @return MsOnionResult  the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    @Deprecated
    @Override
    public MsOnionResult saveOrupdateWarehouseStock(MsOnionApiVersion apiVersion, Warehouse warehouse)
            throws MsOnionIllegalArgumentException, MsOnionException {

        WarehouseExample example = new WarehouseExample();
        WarehouseExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(warehouse.getItemIdx());
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<Warehouse> itemCustomList = this.queryByExample(apiVersion, example);
        int operateIndex = 0;
        if (MsOnionCollectionUtils.isNotEmpty(itemCustomList)) {
            Warehouse itemCustom = itemCustomList.get(0);
            // 版本号 , 不使用 idx 避免理解歧义
            Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
            itemCustom.setVersion(version);
            if (warehouse.getRealTimeInventory() > WarehouseConstants.DEFAULT_ZERO_STOCK_NUM) {
                itemCustom.setRealTimeInventory(warehouse.getRealTimeInventory() - warehouse.getSafeInventory());
            } else {
                itemCustom.setRealTimeInventory(WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
            }
            operateIndex = this.updateByPrimaryKey(apiVersion, itemCustom);
        } else {
            Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_IDX_PATH);
            warehouse.setIdx(idx);
            warehouse.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

            // 版本号 , 不使用 idx 避免理解歧义
            Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
            warehouse.setVersion(version);
            if (warehouse.getRealTimeInventory() > WarehouseConstants.DEFAULT_ZERO_STOCK_NUM) {
                warehouse.setRealTimeInventory(warehouse.getRealTimeInventory() - warehouse.getSafeInventory());
            } else {
                warehouse.setRealTimeInventory(WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
            }
            operateIndex = this.save(apiVersion, warehouse);
        }

        if (operateIndex > 0) {
            return MsOnionResult.ok();
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }


    /**
     * 批量同步商品库存
     *
     * @param apiVersion   Api版本
     * @param erpStockList ERP库存对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    @Override
    public MsOnionResult batchSyncStock(MsOnionApiVersion apiVersion, List<Stock> erpStockList) {
        if (MsOnionCollectionUtils.isEmpty(erpStockList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
        }
        Map<Integer, Warehouse> saveWarehouseMap = new HashMap<Integer, Warehouse>();
        Map<Integer, Warehouse> updateWarehouseMap = new HashMap<Integer, Warehouse>();
        for (int i = 0; i < erpStockList.size(); i++) {
            Stock stock = erpStockList.get(i);
            try {
                WarehouseExample example = new WarehouseExample();
                WarehouseExample.Criteria criteria = example.createCriteria();
                criteria.andItemIdxEqualTo(stock.getGoodsId());
                criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                List<Warehouse> itemCustomList = this.queryByExample(apiVersion, example);

                if (MsOnionCollectionUtils.isNotEmpty(itemCustomList)) {
                    Warehouse warehouse = itemCustomList.get(0);
                    Double d = MsOnionNumberUtils.toDouble(stock.getStockNum(), WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    if ((d.intValue() - warehouse.getSafeInventory()) > WarehouseConstants.DEFAULT_ZERO_STOCK_NUM) {
                        warehouse.setRealTimeInventory(d.intValue() - warehouse.getSafeInventory());
                    } else {
                        warehouse.setRealTimeInventory(WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    }

                    // 版本号 , 不使用 idx 避免理解歧义
                    Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                            this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
                    warehouse.setVersion(version);
                    updateWarehouseMap.put(i, warehouse);
                } else {
                    Warehouse warehouse = new Warehouse();
                    Double d = MsOnionNumberUtils.toDouble(stock.getStockNum(), WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    if ((d.intValue() - WarehouseConstants.DEFAULT_SAFE_INVENTORY) > WarehouseConstants.DEFAULT_ZERO_STOCK_NUM) {
                        warehouse.setRealTimeInventory(d.intValue() - WarehouseConstants.DEFAULT_SAFE_INVENTORY);
                    } else {
                        warehouse.setRealTimeInventory(WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    }
                    warehouse.setSafeInventory(WarehouseConstants.DEFAULT_SAFE_INVENTORY);
                    Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                            this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_IDX_PATH);
                    warehouse.setIdx(idx);
                    warehouse.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                    warehouse.setItemIdx(stock.getGoodsId());
                    // 版本号 , 不使用 idx 避免理解歧义
                    Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                            this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
                    warehouse.setVersion(version);
                    saveWarehouseMap.put(i, warehouse);
                }

            } catch (MsOnionException e) {
                this.getMsOnionLogger().error(getClass().getName(),
                        "#### 处理ERP库存同步出错，商品ID " + stock.getGoodsId() + " ####");
            }
        }
        MsOnionBatchReport<Warehouse> msOnionBatchReport = new MsOnionBatchReport<Warehouse>();
        try {
            if (MsOnionMapUtils.isNotEmpty(saveWarehouseMap)) {
                MsOnionBatchReport<Warehouse> saveMsOnionBatchReport = this.saveWithBatch(apiVersion, saveWarehouseMap);
                if (saveMsOnionBatchReport.getSuccessRecords() != null) {
                    msOnionBatchReport.setSuccessRecords(saveMsOnionBatchReport.getSuccessRecords());
                }
                if (saveMsOnionBatchReport.getFailureRecords() != null) {
                    msOnionBatchReport.setFailureRecords(saveMsOnionBatchReport.getFailureRecords());
                }
            }

            if (MsOnionMapUtils.isNotEmpty(updateWarehouseMap)) {
                MsOnionBatchReport<Warehouse> updateMsOnionBatchReport = this.updateWithBatch(apiVersion, updateWarehouseMap);
                if (msOnionBatchReport.getSuccessRecords() != null) {
                    msOnionBatchReport.getSuccessRecords().putAll(updateMsOnionBatchReport.getSuccessRecords());
                } else {
                    msOnionBatchReport.setSuccessRecords(updateMsOnionBatchReport.getSuccessRecords());
                }
                if (msOnionBatchReport.getFailureRecords() != null) {
                    msOnionBatchReport.getFailureRecords().putAll(updateMsOnionBatchReport.getFailureRecords());
                } else {
                    msOnionBatchReport.setFailureRecords(updateMsOnionBatchReport.getFailureRecords());
                }
            }
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(),
                    "#### 处理ERP库存同步出错 ####");
        }

        return MsOnionResult.ok(msOnionBatchReport);
    }


    /**
     * 批量同步商品库存
     *
     * @param apiVersion   Api版本
     * @param erpStockList ERP库存对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 18:06:44
     */
    @Override
    public MsOnionResult batchSyncStock(MsOnionApiVersion apiVersion, Map<Integer, Stock> erpStockList) {
        if (MsOnionMapUtils.isEmpty(erpStockList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
        }
        Map<Integer, Warehouse> saveWarehouseMap = new HashMap<Integer, Warehouse>();
        Map<Integer, Warehouse> updateWarehouseMap = new HashMap<Integer, Warehouse>();
        for (Integer i : erpStockList.keySet()) {
            Stock stock = erpStockList.get(i);
            try {
                WarehouseExample example = new WarehouseExample();
                WarehouseExample.Criteria criteria = example.createCriteria();
                criteria.andItemIdxEqualTo(stock.getGoodsId());
                criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                List<Warehouse> itemCustomList = this.queryByExample(apiVersion, example);

                if (MsOnionCollectionUtils.isNotEmpty(itemCustomList)) {
                    Warehouse warehouse = itemCustomList.get(0);
                    Double d = MsOnionNumberUtils.toDouble(stock.getStockNum(), WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    if ((d.intValue() - warehouse.getSafeInventory()) > WarehouseConstants.DEFAULT_ZERO_STOCK_NUM) {
                        warehouse.setRealTimeInventory(d.intValue() - warehouse.getSafeInventory());
                    } else {
                        warehouse.setRealTimeInventory(WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    }
                    // 版本号 , 不使用 idx 避免理解歧义
                    Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                            this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
                    warehouse.setVersion(version);
                    updateWarehouseMap.put(i, warehouse);
                } else {
                    Warehouse warehouse = new Warehouse();
                    Double d = MsOnionNumberUtils.toDouble(stock.getStockNum(), WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    if ((d.intValue() - WarehouseConstants.DEFAULT_SAFE_INVENTORY) > WarehouseConstants.DEFAULT_ZERO_STOCK_NUM) {
                        warehouse.setRealTimeInventory(d.intValue() - WarehouseConstants.DEFAULT_SAFE_INVENTORY);
                    } else {
                        warehouse.setRealTimeInventory(WarehouseConstants.DEFAULT_ZERO_STOCK_NUM);
                    }
                    warehouse.setItemIdx(stock.getGoodsId());
                    warehouse.setSafeInventory(WarehouseConstants.DEFAULT_SAFE_INVENTORY);
                    Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                            this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_IDX_PATH);
                    warehouse.setIdx(idx);
                    warehouse.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                    // 版本号 , 不使用 idx 避免理解歧义
                    Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                            this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSE_VERSION_PATH);
                    warehouse.setVersion(version);
                    saveWarehouseMap.put(i, warehouse);
                }

            } catch (MsOnionException e) {
                this.getMsOnionLogger().error(getClass().getName(),
                        "#### 处理ERP库存同步出错，商品ID " + stock.getGoodsId() + " ####");
            }
        }
        MsOnionBatchReport<Warehouse> msOnionBatchReport = new MsOnionBatchReport<Warehouse>();
        try {
            if (MsOnionMapUtils.isNotEmpty(saveWarehouseMap)) {
                MsOnionBatchReport<Warehouse> saveMsOnionBatchReport = this.saveWithBatch(apiVersion, saveWarehouseMap);
                if (saveMsOnionBatchReport.getSuccessRecords() != null) {
                    msOnionBatchReport.setSuccessRecords(saveMsOnionBatchReport.getSuccessRecords());
                }
                if (saveMsOnionBatchReport.getFailureRecords() != null) {
                    msOnionBatchReport.setFailureRecords(saveMsOnionBatchReport.getFailureRecords());
                }
            }

            if (MsOnionMapUtils.isNotEmpty(updateWarehouseMap)) {
                MsOnionBatchReport<Warehouse> updateMsOnionBatchReport = this.updateWithBatch(apiVersion, updateWarehouseMap);
                if (msOnionBatchReport.getSuccessRecords() != null) {
                    msOnionBatchReport.getSuccessRecords().putAll(updateMsOnionBatchReport.getSuccessRecords());
                } else {
                    msOnionBatchReport.setSuccessRecords(updateMsOnionBatchReport.getSuccessRecords());
                }
                if (msOnionBatchReport.getFailureRecords() != null) {
                    msOnionBatchReport.getFailureRecords().putAll(updateMsOnionBatchReport.getFailureRecords());
                } else {
                    msOnionBatchReport.setFailureRecords(updateMsOnionBatchReport.getFailureRecords());
                }
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(),
                    "#### 处理ERP库存同步出错 ####");
        }

        return MsOnionResult.ok(msOnionBatchReport);
    }
}
