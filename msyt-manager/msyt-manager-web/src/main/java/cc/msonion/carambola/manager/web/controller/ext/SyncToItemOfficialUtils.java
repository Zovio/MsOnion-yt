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
 * @Title: SyncToItemOfficialUtils.java
 * @Package: cc.msonion.carambola.manager.ext.util
 * @Description: 采集相关信息同步到正式商品信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月24日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月24日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.collector.common.utils.ItemModifyLocationUtils;
import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemCategory;
import cc.msonion.carambola.collector.pojo.CollectorItemLog;
import cc.msonion.carambola.collector.service.ItemCategoryService;
import cc.msonion.carambola.collector.service.ItemLogService;
import cc.msonion.carambola.item.pojo.ItemBarcodeOfficial;
import cc.msonion.carambola.item.pojo.ItemBasicOfficial;
import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.service.ItemBarcodeOfficialService;
import cc.msonion.carambola.item.service.ItemBasicOfficialService;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.logistics.common.constants.LogisticsConstants;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclare;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareOfficialService;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPropertiesConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.ext.utils.common.MsOnionModifiedFieldUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.warehouse.common.constans.WarehouseConstants;
import cc.msonion.carambola.warehouse.pojo.Warehouse;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficial;
import cc.msonion.carambola.warehouse.service.WarehouseOfficialService;
import cc.msonion.carambola.warehouse.service.WarehouseService;
import cc.msonion.carambola.warehouse.service.WarehouseTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SyncToItemOfficialUtils
 * @Description: 采集相关信息同步到正式商品信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月24日
 */
public final class SyncToItemOfficialUtils {


    private SyncToItemOfficialUtils() {

    }

    /**
     * 将采集报关信息，同步到 正式商品报关信息
     *
     * @param msOnionLogger                             日志service
     * @param logisticsCustomsDeclare                采集报关信息
     * @param itemOfficialService                    正式商品service
     * @param logisticsCustomsDeclareService         采集报关service
     * @param logisticsCustomsDeclareOfficialService 正式报关service
     * @return MsOnionResult
     */
    public static MsOnionResult syncToLogisticsCustomsDeclareOfficial(MsOnionLogger msOnionLogger, LogisticsCustomsDeclare logisticsCustomsDeclare,
                                                                      ItemOfficialService itemOfficialService,
                                                                      LogisticsCustomsDeclareService logisticsCustomsDeclareService,
                                                                      LogisticsCustomsDeclareOfficialService logisticsCustomsDeclareOfficialService) {
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集报关信息: " + logisticsCustomsDeclare);

        if (null == logisticsCustomsDeclare) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_CUSTOM_INFO_NULL);
        }

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            // 查询正式商品
            MsOnionResult reulst = itemOfficialService.getItemOfficialByItemIdx(apiVersion, logisticsCustomsDeclare.getItemIdx());

            if (reulst.getStatus() == MsOnionStatusConstants.STATUS_200) {
                // 将采报关信息，状态改为已发布
                LogisticsCustomsDeclare logisticsCustomsDeclare1 = logisticsCustomsDeclareService.queryByPrimaryKey(apiVersion,
                        logisticsCustomsDeclare.getIdx());
                logisticsCustomsDeclare1.setPublishStatus(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
                logisticsCustomsDeclareService.updateByPrimaryKey(apiVersion, logisticsCustomsDeclare1);
                ItemOfficial itemOfficial = (ItemOfficial) reulst.getData();
                MsOnionResult ret = logisticsCustomsDeclareOfficialService.getLogisticsCustomsDeclareOfficialByItemOfficialIdx(apiVersion,
                        itemOfficial.getIdx());
                // 查询正式商品对应的正式报关信息，不存在则新增
                if (ret.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    LogisticsCustomsDeclareOfficial logisticsCustomsDeclareOfficial = new LogisticsCustomsDeclareOfficial();
                    BeanUtils.copyProperties(logisticsCustomsDeclare1, logisticsCustomsDeclareOfficial);
                    logisticsCustomsDeclareOfficial.setItemOfficialIdx(itemOfficial.getIdx());
                    logisticsCustomsDeclareOfficial.setCreateTime(new Date());
                    logisticsCustomsDeclareOfficial.setUpdateTime(new Date());
                    MsOnionResult result = logisticsCustomsDeclareOfficialService.addLogisticsCustomsDeclareOfficial(apiVersion,
                            logisticsCustomsDeclareOfficial);
                    msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集报关信息  新增结果: " + result);
                    return result;
                }
                // 存在则更新，需要记录操作日志
                LogisticsCustomsDeclareOfficial logicsticsOfficial = (LogisticsCustomsDeclareOfficial) ret.getData();
                // update
                String[] excludeArr = new String[]{MsOnionPropertiesConstants.IDX_CODE, MsOnionPropertiesConstants.IDX,
                        MsOnionPropertiesConstants.CREATE_BY_MEMBER_IDX, MsOnionPropertiesConstants.CREATE_TIME,
                        MsOnionPropertiesConstants.VERSION, MsOnionPropertiesConstants.STATUS};
                BeanUtils.copyProperties(logisticsCustomsDeclare, logicsticsOfficial, excludeArr);
                int i = logisticsCustomsDeclareOfficialService.updateByPrimaryKey(apiVersion, logicsticsOfficial);
                msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集报关信息  更新结果: " + i);
                if (i == 0) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
                }
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(SyncToItemOfficialUtils.class.getName(), e, " ### 采集报关信息，同步到正式报关信息失败...");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync 商品采集报关信息 end. ");
        return MsOnionResult.ok();
    }


    /**
     * 将商品采集仓库信息，同步到 正式商品仓库信息
     *
     * @param msOnionLogger               日志service
     * @param warehouse                采集商品仓库
     * @param itemOfficialService      正式商品service
     * @param warehouseService         采集仓库service
     * @param warehouseOfficialService 正式仓库service
     * @param itemLogService           商品日志服务
     * @param operatorIdx              操作人
     * @return MsOnionResult
     */
    public static MsOnionResult syncToWarehouseOfficial(MsOnionLogger msOnionLogger, Warehouse warehouse,
                                                        ItemOfficialService itemOfficialService, WarehouseService warehouseService,
                                                        WarehouseOfficialService warehouseOfficialService,
                                                        ItemLogService itemLogService, Long operatorIdx) {
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集仓库信息: " + warehouse);
        if (null == warehouse) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MESSAGE_WAREHOUSE_INFO_NULL);
        }
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            // 查询正式商品
            MsOnionResult reulst = itemOfficialService.getItemOfficialByItemIdx(apiVersion, warehouse.getItemIdx());

            if (reulst.getStatus() == MsOnionStatusConstants.STATUS_200) {
                // 将仓库信息，状态改为已发布
                Warehouse warehouse1 = warehouseService.queryByPrimaryKey(apiVersion, warehouse.getIdx());
                warehouse1.setPublishStatus(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
                warehouseService.updateByPrimaryKey(apiVersion, warehouse1);
                ItemOfficial itemOfficial = (ItemOfficial) reulst.getData();
                MsOnionResult ret = warehouseOfficialService.getWarehouseOfficialByItemOfficialIdx(apiVersion, itemOfficial.getIdx());
                // 查询正式商品对应的正式报关信息，不存在则新增
                if (ret.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    WarehouseOfficial warehouseOfficial = new WarehouseOfficial();
                    BeanUtils.copyProperties(warehouse1, warehouseOfficial);
                    warehouseOfficial.setItemOfficialIdx(itemOfficial.getIdx());
                    warehouseOfficial.setCreateTime(new Date());
                    warehouseOfficial.setUpdateTime(new Date());
                    MsOnionResult result = warehouseOfficialService.addWarehouseOfficial(apiVersion, warehouseOfficial);
                    msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集仓库信息 新增结果: " + result);
                    // 添加操作日志
                   /* List<CollectorItemLog> logList = processWarehouseLogs(msOnionLog, warehouse1, null);
                    MsOnionResult logResult = itemLogService.batchSaveItemLog(apiVersion, logList, operatorIdx);
                    msOnionLog.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集仓库信息操作日志： " + logResult);*/
                    return result;
                }
                // 存在则更新，需要记录操作日志
                WarehouseOfficial warehouseOfficial = (WarehouseOfficial) ret.getData();
                // 判断是否要记录历史记录
                List<CollectorItemLog> logList = processWarehouseLogs(msOnionLogger, warehouse1, warehouseOfficial);

                // update
                String[] excludeArr = new String[]{MsOnionPropertiesConstants.IDX_CODE, MsOnionPropertiesConstants.IDX,
                        MsOnionPropertiesConstants.CREATE_BY_MEMBER_IDX, MsOnionPropertiesConstants.CREATE_TIME,
                        MsOnionPropertiesConstants.VERSION, MsOnionPropertiesConstants.STATUS};
                BeanUtils.copyProperties(warehouse1, warehouseOfficial, excludeArr);
                int i = warehouseOfficialService.updateByPrimaryKey(apiVersion, warehouseOfficial);
                msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集仓库信息 更新结果: " + i);
                if (i == 0) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
                }
                MsOnionResult result = itemLogService.batchSaveItemLog(apiVersion, logList, operatorIdx);
                msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集仓库信息操作日志： " + result);
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(SyncToItemOfficialUtils.class.getName(), e, " ### 商品采集仓库信息，同步到正式商品仓库信息失败...");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);

        }
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync商品采集仓库信息 end. ");

        return MsOnionResult.ok();
    }

    /**
     * 获取仓库操作记录
     *
     * @param msOnionLogger        日志
     * @param warehouse         采集商品仓库
     * @param warehouseOfficial 正式商品仓库
     * @return 日志集合
     * @throws MsOnionException 异常
     */
    public static List<CollectorItemLog> processWarehouseLogs(MsOnionLogger msOnionLogger,
                                                              Warehouse warehouse, WarehouseOfficial warehouseOfficial) throws MsOnionException {
        List<CollectorItemLog> logList = new ArrayList<>();
        Map<String, Object> modifiedMap = MsOnionModifiedFieldUtils.getModifiedFieldNameAndValue(msOnionLogger, warehouse);
       // System.out.println("-0---- " + modifiedMap);
        for (Iterator ite = modifiedMap.entrySet().iterator(); ite.hasNext(); ) {
            Map.Entry entry = (Map.Entry) ite.next();
            String key = (String) entry.getKey();
            Object newVal = entry.getValue();
            if (warehouseOfficial != null) {
                // 更新
                Field field = ReflectionUtils.findField(warehouseOfficial.getClass(), key);
                ReflectionUtils.makeAccessible(field);
                Object oldVal = null;
                try {
                    oldVal = field.get(warehouseOfficial);
                } catch (IllegalAccessException e) {
                    msOnionLogger.error(SyncToItemOfficialUtils.class.getName(), e);
                }
                // 判断值是否相等
                if (!newVal.equals(oldVal)) {
                    CollectorItemLog log = new CollectorItemLog();
                    log.setItemIdx(warehouse.getItemIdx());
                    log.setOriginValue(ItemModifyLocationUtils.getDescriptionByDict(key, oldVal));
                    log.setNewValue(ItemModifyLocationUtils.getDescriptionByDict(key, newVal));
                    log.setModifyLocation((Short) ItemModifyLocationUtils.getAttrMap().get(key));
                    logList.add(log);
                }
            } else {
                // 新增
                CollectorItemLog log = new CollectorItemLog();
                log.setItemIdx(warehouse.getItemIdx());
                log.setOriginValue("");
                log.setNewValue(ItemModifyLocationUtils.getDescriptionByDict(key, newVal));
                log.setModifyLocation((Short) ItemModifyLocationUtils.getAttrMap().get(key));
                logList.add(log);
            }
        }
        return logList;
    }

    /**
     * 将采集商品基础信息的条形码、选择仓库、分类、货号同步到正式商品基础信息
     *
     * @param msOnionLogger               日志service
     * @param collectorItem            采集商品基础信息
     * @param itemOfficialService      正式商品servie
     * @param itemBasicOfficialService 正式商品基础信息service
     * @param itemLogService           商品日志service
     * @param operatorIdx              操作人
     * @param warehouseTypeService     商品仓库类型service
     * @param itemCategoryService      商品类别service
     * @return MsOnionResult
     */
    public static MsOnionResult syncToItemOfficial(MsOnionLogger msOnionLogger, CollectorItem collectorItem,
                                                   ItemOfficialService itemOfficialService,
                                                   ItemBasicOfficialService itemBasicOfficialService,
                                                   ItemLogService itemLogService, Long operatorIdx,
                                                   WarehouseTypeService warehouseTypeService, ItemCategoryService itemCategoryService) {
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync采集商品基础信息: " + collectorItem);
        if (collectorItem == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.DATA_NONE_MEMBER);
        }

        if (collectorItem.getIdx() == null || collectorItem.getIdx() <= 0L) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        }
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            // 查询正式商品的条形码
            MsOnionResult result1 = itemOfficialService.getItemOfficialByItemIdx(apiVersion, collectorItem.getIdx());
            if (result1.getStatus() == MsOnionStatusConstants.STATUS_200) {
                ItemOfficial itemOfficial = (ItemOfficial) result1.getData();
                ItemBasicOfficial itemBasicOfficial = itemBasicOfficialService.queryOneBySourceIdx(apiVersion, itemOfficial.getIdx());
                if (itemBasicOfficial == null) {
                    // 正式商品基本信息为空
                    return MsOnionResult.ok();
                }

                // 商品条形码 （新）
                String barcode = collectorItem.getBarcode();
                // 仓库IDX （新）
                Long warehouseTypeIdx = collectorItem.getWarehouseTypeIdx();
                // 分类idx （新）
                Long categoryIdx = collectorItem.getCategoryIdx();
                // 货号 （新）
                String itemNo = collectorItem.getItemNo();
                boolean changeFlag = false;
                // 商品条形码 （旧）
                String oldBarcode = null;
                // 仓库IDX （旧）
                Long oldWarehouseTypeIdx = null;
                // 分类idx （旧）
                Long oldCategoryIdx = null;
                // 货号 （旧）
                String oldItemNo = null;
                List<CollectorItemLog> logList = new ArrayList<>();

                if (StringUtils.isNotBlank(barcode) && !barcode.equals(itemBasicOfficial.getBarcode())) {
                    changeFlag = true;
                    oldBarcode = itemBasicOfficial.getBarcode();
                    itemBasicOfficial.setBarcode(barcode);
                    // 保存操作记录 商品条形码
                    CollectorItemLog log = new CollectorItemLog();
                    log.setItemIdx(collectorItem.getIdx());
                    log.setOriginValue(oldBarcode);
                    log.setNewValue(barcode);
                    log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_BARCODE);
                    logList.add(log);
                }

                if (StringUtils.isNotBlank(itemNo) && !itemNo.equals(itemBasicOfficial.getItemNo())) {
                    changeFlag = true;
                    oldItemNo = itemBasicOfficial.getItemNo();
                    itemBasicOfficial.setItemNo(itemNo);
                    // 保存操作记录 货号
                    CollectorItemLog log = new CollectorItemLog();
                    log.setItemIdx(collectorItem.getIdx());
                    log.setOriginValue(oldItemNo);
                    log.setNewValue(itemNo);
                    log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_NO);
                    logList.add(log);
                }

                if (warehouseTypeIdx != null && warehouseTypeIdx > 0L && warehouseTypeIdx != itemBasicOfficial.getWarehouseTypeIdx()) {
                    changeFlag = true;
                    oldWarehouseTypeIdx = itemBasicOfficial.getWarehouseTypeIdx();
                    itemBasicOfficial.setWarehouseTypeIdx(warehouseTypeIdx);
                    // 保存操作记录 仓库
                    if (oldWarehouseTypeIdx != null && oldWarehouseTypeIdx > 0L) {
                        CollectorItemLog log = new CollectorItemLog();
                        log.setItemIdx(collectorItem.getIdx());
                        log.setOriginValue(warehouseTypeService.queryByPrimaryKey(apiVersion, oldWarehouseTypeIdx).getWarehouseName());
                        log.setNewValue(collectorItem.getWarehouseTypeIdxDynamicS());
                        log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_WAREHOUSE);
                        logList.add(log);
                    }
                }

                if (categoryIdx != null && categoryIdx > 0L && categoryIdx != itemBasicOfficial.getCategoryIdx()) {
                    changeFlag = true;
                    oldCategoryIdx = itemBasicOfficial.getCategoryIdx();
                    itemBasicOfficial.setCategoryIdx(categoryIdx);
                    // 保存操作记录 分类
                    MsOnionResult oldCategoryResult = itemCategoryService.getCurrentAndParents(apiVersion, oldCategoryIdx);
                    LinkedList<CollectorItemCategory> list1 = (LinkedList<CollectorItemCategory>) oldCategoryResult.getData();

                    MsOnionResult newCategoryResult = itemCategoryService.getCurrentAndParents(apiVersion, categoryIdx);
                    LinkedList<CollectorItemCategory> list2 = (LinkedList<CollectorItemCategory>) newCategoryResult.getData();


                    CollectorItemLog log = new CollectorItemLog();
                    log.setItemIdx(collectorItem.getIdx());
                    log.setOriginValue(StringUtils.join(list1.stream().map(s -> s.getName()).collect(Collectors.toList()), "-"));
                    log.setNewValue(StringUtils.join(list2.stream().map(s -> s.getName()).collect(Collectors.toList()), "-"));
                    log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_CATEGORY);
                    logList.add(log);
                }

                // 如果改变了这几项,更新
                if (changeFlag) {
                    int i = itemBasicOfficialService.updateByPrimaryKey(apiVersion, itemBasicOfficial);
                    msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync采集商品基础信息 更新结果：" + i);
                    if (i == 0) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
                    }
                    itemLogService.batchSaveItemLog(apiVersion, logList, operatorIdx);
                }
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(SyncToItemOfficialUtils.class.getName(), e, " ### 将采集商品基础信息的条形码、选择仓库、分类、货号同步到正式商品基础信息失败...");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync采集商品基础信息 end.");

        return MsOnionResult.ok();
    }

    /**
     * 同步正式商品条形码
     *
     * @param msOnionLogger                 日志service
     * @param itemIdx                    采集商品idx
     * @param list                       条形码集合
     * @param itemOfficialService        正式商品service
     * @param itemBarcodeOfficialService 正式商品条形码service
     * @param itemLogService             商品日志service
     * @param operatorIdx                操作人
     * @return MsOnionResult
     */
    public static MsOnionResult syncToItemBarcodeOfficials(MsOnionLogger msOnionLogger, Long itemIdx,
                                                           List<String> list, ItemOfficialService itemOfficialService,
                                                           ItemBarcodeOfficialService itemBarcodeOfficialService,
                                                           ItemLogService itemLogService, Long operatorIdx) {
        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync采集商品仓库条形码信息: itemIdx:" + itemIdx + "-->" + list);
        if (itemIdx == null || itemIdx <= 0L) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        }
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            // 查询正式商品的条形码
            List<CollectorItemLog> logList = new ArrayList<>();
            MsOnionResult result1 = itemOfficialService.getItemOfficialByItemIdx(apiVersion, itemIdx);
            if (result1.getStatus() == MsOnionStatusConstants.STATUS_200) {
                ItemOfficial itemOfficial = (ItemOfficial) result1.getData();
                MsOnionResult ret = itemBarcodeOfficialService.getItemBarcodeOfficialByItemOfficalIdx(apiVersion, itemOfficial.getIdx());

                if (ret.getStatus() == MsOnionStatusConstants.STATUS_200) {
                    List<ItemBarcodeOfficial> barcodeOfficials = (List<ItemBarcodeOfficial>) ret.getData();
                    List<String> list2 = barcodeOfficials.stream().map(ItemBarcodeOfficial::getBarcode).collect(Collectors.toList());
                    // 新增的集合
                    Collection<String> addList = CollectionUtils.subtract(list, list2);
                    // 删除的集合
                    Collection<String> delList = CollectionUtils.subtract(list2, list);
                    delList.stream().forEach(s -> {
                        CollectorItemLog log = new CollectorItemLog();
                        log.setItemIdx(itemIdx);
                        log.setOriginValue(s);
                        log.setNewValue("-");
                        log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_BARCODE);
                        logList.add(log);
                    });
                    addList.stream().forEach(s -> {
                        CollectorItemLog log = new CollectorItemLog();
                        log.setItemIdx(itemIdx);
                        log.setOriginValue("-");
                        log.setNewValue(s);
                        log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_BARCODE);
                        logList.add(log);
                    });
                    if (logList.size() > 0) {
                        // 操作历史记录
                        itemBarcodeOfficialService.deleteItemBarcodeOfficialByItemOfficalIdx(apiVersion, itemOfficial.getIdx());
                        MsOnionResult result = itemBarcodeOfficialService.addItemBarcodeOfficialList(apiVersion, list, itemOfficial.getIdx());
                        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
                        }
                        // 添加操作日志
                        itemLogService.batchSaveItemLog(apiVersion, logList, operatorIdx);
                    }
                } else {
                    // 批量新增
                    MsOnionResult result = itemBarcodeOfficialService.addItemBarcodeOfficialList(apiVersion, list, itemOfficial.getIdx());
                    if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
                    }
                    // 添加操作日志
                    list.stream().forEach(s -> {
                        CollectorItemLog log = new CollectorItemLog();
                        log.setItemIdx(itemIdx);
                        log.setOriginValue("-");
                        log.setNewValue(s);
                        log.setModifyLocation(ItemModifyLocationUtils.TYPE_ITEM_BARCODE);
                        logList.add(log);
                    });
                    itemLogService.batchSaveItemLog(apiVersion, logList, operatorIdx);

                }
            }
        } catch (MsOnionException e) {
            msOnionLogger.error(SyncToItemOfficialUtils.class.getName(), e, " ### 将采集商品仓库条形码同步到正式商品仓库条形码失败...");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }

        msOnionLogger.info(SyncToItemOfficialUtils.class.getName(), " ## sync采集商品仓库条形码信息end.");
        return MsOnionResult.ok();
    }

}
