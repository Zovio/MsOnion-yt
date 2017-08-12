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


package cc.msonion.carambola.manager.web.controller.warehouse;

/**
 * @Title: WarehouseController.java
 * @Package: cc.msonion.carambola.manager.web.controller.warehouse
 * @Description: 仓库controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月23日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.OrderByConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionPublishStatusUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import cc.msonion.carambola.thirdplatform.erp.service.ErpStockService;
import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.service.ItemBarcodeOfficialService;
import cc.msonion.carambola.item.service.ItemBasicOfficialService;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.WarehousePublishUtil;
import cc.msonion.carambola.manager.web.controller.collector.common.ItemCommonQuery;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.controller.ext.SyncToItemOfficialUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.common.utils.concurrent.MsOnionThreadPoolUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import cc.msonion.carambola.warehouse.pojo.Warehouse;
import cc.msonion.carambola.warehouse.pojo.WarehouseExample;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficial;
import cc.msonion.carambola.warehouse.service.WarehouseOfficialService;
import cc.msonion.carambola.warehouse.service.WarehouseService;
import cc.msonion.carambola.warehouse.service.WarehouseTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: WarehouseController
 * @Description: 仓库controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 */
@Controller
public class WarehouseController extends MsOnionBaseAppController {

    /**
     * 商品service
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品品牌服务
     */
    @Autowired
    private ItemBrandService itemBrandService;

    /**
     * 商品仓库service
     */
    @Autowired
    private WarehouseService warehouseService;

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * 商品条形码服务
     */
    @Autowired
    private ItemBarcodeService itemBarcodeService;


    /**
     * warehouseOfficialService
     */
    @Autowired
    private WarehouseOfficialService warehouseOfficialService;

    /**
     * itemBarcodeOfficialService
     */
    @Autowired
    private ItemBarcodeOfficialService itemBarcodeOfficialService;

    /**
     * 正式商品基础信息服务
     */
    @Autowired
    private ItemBasicOfficialService itemBasicOfficialService;


    /**
     * 正式商品服务
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * 商品类目service
     */
    @Autowired
    private ItemCategoryService itemCategoryService;


    /**
     * 商品日志service
     */
    @Autowired
    private ItemLogService itemLogService;

    /**
     * warehouseTypeService
     */
    @Autowired
    private WarehouseTypeService warehouseTypeService;


    /**
     * erpStockService
     */
    @Autowired
    private ErpStockService erpStockService;

    /**
     * 商品仓库列表
     *
     * @param model 模型
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/wareho/wareho/list")
    public String listLogisticsCustomsDeclare(Model model) throws MsOnionException {
        Map purchaseStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.PURCHASE_STATUS);
        model.addAttribute("purchaseStatusMap", purchaseStatusMap);
        model.addAttribute("purchaseStatusMapJson", MsOnionJsonUtils.objectToJson(purchaseStatusMap));

        Map collectionStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.COLLECTION_STATUS);
        model.addAttribute("collectionStatusMapJson", MsOnionJsonUtils.objectToJson(collectionStatusMap));

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        List<CollectorItemBrand> brandList = itemBrandService.getAllItemBrand(apiVersion);
        model.addAttribute("brandList", brandList);

        Map publishStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.PUBLISH_STATUS);
        model.addAttribute("publishStatusMap", publishStatusMap);
        model.addAttribute("publishStatusJson", MsOnionJsonUtils.objectToJson(publishStatusMap));


        Map isFreeShippingStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.ISFREESHIPPING_STATUS);
        model.addAttribute("isFreeShippingMap", isFreeShippingStatusMap);
        model.addAttribute("isFreeShippingJson", MsOnionJsonUtils.objectToJson(isFreeShippingStatusMap));


        Map isKeyOrderMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                MsOnionDictCodeConstants.ISKEYORDER_STATUS);
        model.addAttribute("isKeyOrderMap", isKeyOrderMap);
        model.addAttribute("isKeyOrderJson", MsOnionJsonUtils.objectToJson(isKeyOrderMap));
        return "warehouse/warehouse/listWarehouse";

    }


    /**
     * 获取商品仓库列表
     *
     * @param request        请求
     * @param page           当前页码
     * @param rows           每页记录数量
     * @param collectorItem  商品搜索参数
     * @param isKeyOrder     是否一键下单
     * @param isFreeShipping 是否包邮
     * @param publishStatus  发布状态
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/wareho/wareho/grid")
    @ResponseBody
    public MsOnionResult gridItem(HttpServletRequest request, String page, String rows, CollectorItem collectorItem,
                                  String isKeyOrder, String isFreeShipping, String publishStatus) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            CollectorItemExample example = new CollectorItemExample();
            ItemCommonQuery itemCommonQuery = new ItemCommonQuery(itemCategoryService);
            itemCommonQuery.setGoodsQueryParams(example, collectorItem, null, null, null, null);

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = itemService.queryListByPageForResult(apiVersion, example, pageNum, pageSize, orderBy);
            if (null == resultAdapter) {
                return MsOnionResult.ok();
            }

            MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

            long total = pagingResult.getTotalCounts();
            List<CollectorItem> itemList = (List<CollectorItem>) pagingResult.getData();
            List<Map<String, Object>> mapList = new ArrayList<>();
            if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
                for (CollectorItem item : itemList) {
                    Map<String, Object> itemMap = new HashMap<String, Object>();
                    itemMap.put("collectorItem", item);
                    // 价格
                    MsOnionResult result = warehouseService.getWarehouseByItemIdx(apiVersion, item.getIdx());
                    if (null != result.getData()) {
                        Warehouse warehouse = (Warehouse) result.getData();
                        itemMap.put("warehouse", warehouse);
                    }
                    mapList.add(itemMap);
                }
            }
            map.put("total", total);
            map.put("rows", mapList);
            return MsOnionResult.ok(map);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

    /**
     * 更新商品仓库信息
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return 返回更新结果
     * @throws MsOnionException 异常
     */
    @RequestMapping("/wareho/wareho/edit/{idxStr}")
    public String updateLogisticsCustomsDeclare(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr) && MsOnionRegexUtils.checkDigit(idxStr.trim())) {

                Long idx = Long.parseLong(idxStr);

                MsOnionResult result = itemService.getItemByIdx(apiVersion, idx);
                if (null != result.getData()) {
                    CollectorItem item = (CollectorItem) result.getData();
                    request.setAttribute("item", item);

                    result = warehouseService.getWarehouseByItemIdx(apiVersion, idx);
                    if (null != result.getData()) {
                        Warehouse warehouse = (Warehouse) result.getData();
                        request.setAttribute("warehouse", warehouse);
                    }
                }
                List<CollectorItemBarcode> itemBarcodeList = itemBarcodeService.getItemBarcodeList(apiVersion, idx);
                request.setAttribute("itemBarcodeList", itemBarcodeList);
            }
            String type = request.getParameter("type");
            if (StringUtils.isNotBlank(type) && ManagerConstants.VIEW.equals(type.trim())) {
                return "warehouse/warehouse/viewWarehouse";
            }
            return "warehouse/warehouse/editWarehouse";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存商品仓库信息
     *
     * @param warehouse   报关信息
     * @param barcode     新增商品时候的条形码
     * @param barcodeStrs 新增的条形码
     * @param syncFlag    保存并发布
     * @return 返回保存结果
     * @throws MsOnionException 异常
     */
    @RequestMapping(value = "/wareho/wareho/save", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult saveLogisticsCustomsDeclare(Warehouse warehouse, String barcode,
                                                     String barcodeStrs, String syncFlag) throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            Member currentUser = CurrentUserUtils.getCurrentUser();
            warehouse.setCreateByMemberIdx(currentUser.getIdx());
            warehouse.setUpdateByMemberIdx(currentUser.getIdx());

            // 查询正式商品
            if (MsOnionConstants.YES.equals(syncFlag)) {
                MsOnionResult reulst = itemOfficialService.getItemOfficialByItemIdx(apiVersion, warehouse.getItemIdx());
                if (reulst.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ManagerConstants.PUBLISH_ITEMOFFICIAL_FAIL);
                }
            }

            MsOnionResult result2 = itemService.getItemByIdx(apiVersion, warehouse.getItemIdx());
            if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
            }
            // 查询此条形码是否存在
            MsOnionResult barcodeResult = itemService.inspectParameter(apiVersion, barcode, ParamTypeUtils.TYPE_ITEM_BARCODE);
            // 判断批量录入的条形码是否正确
            if (StringUtils.isNotBlank(barcodeStrs)) {
                // 查询当前的条形码
                List<CollectorItemBarcode> itemBarcodeList = itemBarcodeService.getItemBarcodeList(apiVersion, warehouse.getItemIdx());
                List<String> list1 = itemBarcodeList.stream().map(CollectorItemBarcode::getBarcode).collect(Collectors.toList());
                String[] arr = barcodeStrs.trim().split(",");
                List<String> list2 = Arrays.asList(arr);
                // 取页面与数据库的差集
                Collection<String> list3 = CollectionUtils.subtract(list2, list1);
                Iterator<String> iterator = list3.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    MsOnionResult r1 = itemService.inspectParameter(apiVersion, next, ParamTypeUtils.TYPE_ITEM_BARCODE);
                    if (r1.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                                String.format(CollectorMessageConstants.MESSAGE_ITEM_BARCODE_EXIST_FORMT, next));
                    }
                }
            }

            // 判断是否改了条形码
            CollectorItem collectorItem = (CollectorItem) result2.getData();
            if (!collectorItem.getBarcode().equals(barcode)) {
                if (barcodeResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                    collectorItem.setBarcode(barcode);
                    itemService.updateByPrimaryKey(apiVersion, collectorItem);
                } else {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                            String.format(CollectorMessageConstants.MESSAGE_ITEM_BARCODE_EXIST_FORMT, barcode));
                }
            }
            // 同步正式商品条形码
            if (MsOnionConstants.YES.equals(syncFlag)) {
                CollectorItem collectorItem2 = new CollectorItem();
                collectorItem2.setBarcode(barcode);
                collectorItem2.setIdx(collectorItem.getIdx());
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    getMsOnionLogger().info(WarehouseController.class.getName(), " ## 开始同步正式商品条形码...");
                    MsOnionResult result = SyncToItemOfficialUtils.syncToItemOfficial(getMsOnionLogger(), collectorItem2,
                            itemOfficialService, itemBasicOfficialService, itemLogService, currentUser.getIdx(),
                            warehouseTypeService, itemCategoryService);
                    getMsOnionLogger().info(WarehouseController.class.getName(), " ## 同步正式商品条形码结束，返回结果：" + result.getStatus());
                });
            }

            // 保存仓库
            MsOnionResult result = warehouseService.updateWarehouse(apiVersion, warehouse);
            // 添加条形码 （ 去重）
            List list = Collections.emptyList();
            if (result.getStatus() == MsOnionStatusConstants.STATUS_200) {
                if (StringUtils.isNotBlank(barcodeStrs)) {
                    String[] arr = barcodeStrs.trim().split(",");
                    Set set = new LinkedHashSet();
                    Stream.of(arr).forEach(s -> set.add(s.trim()));
                    // 删除和商品相同的条形码
                    set.removeIf(s -> s.equals(barcode));
                    list = new ArrayList(set);
                }
                MsOnionResult mr = itemBarcodeService.addItemBarcode(apiVersion, list, warehouse.getItemIdx());
                this.getMsOnionLogger().info(getClass().getName(), " ## 添加条形码结果：" + mr.getMsg());
            }

            if (result.getStatus() == MsOnionStatusConstants.STATUS_200 && MsOnionConstants.YES.equals(syncFlag)) {
                // 线程异步更新正式商品仓库
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    getMsOnionLogger().info(WarehouseController.class.getName(), " ## 开始同步正式商品仓库...");
                    Warehouse warehouse1 = (Warehouse) result.getData();
                    MsOnionResult r = SyncToItemOfficialUtils.syncToWarehouseOfficial(getMsOnionLogger(), warehouse1, itemOfficialService,
                            warehouseService, warehouseOfficialService, itemLogService, currentUser.getIdx());
                    getMsOnionLogger().info(WarehouseController.class.getName(), " ## 同步正式商品仓库结束，返回结果：" + r.getStatus());

                });
                // 线程异步更新商品条形码，只会新增和删除
                List list1 = list;
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    getMsOnionLogger().info(WarehouseController.class.getName(), " ## 开始同步正式商品仓库条形码...");
                    Warehouse warehouse1 = (Warehouse) result.getData();
                    MsOnionResult r = SyncToItemOfficialUtils.syncToItemBarcodeOfficials(getMsOnionLogger(), warehouse1.getItemIdx(),
                            list1, itemOfficialService, itemBarcodeOfficialService, itemLogService, currentUser.getIdx());
                    getMsOnionLogger().info(WarehouseController.class.getName(), " ## 同步正式商品仓库条形码结束，返回结果： " + r.getStatus());
                });
            }
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
    }


    /**
     * 描述信息
     *
     * @param request    HttpServletRequest 对象
     * @param response   HttpServletResponse  对象
     * @param model      Model实例对象
     * @param itemIdxStr 商品ID集合
     * @return 页面视图
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping(value = "/wareho/wareho/syncErpStock", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult syncErpStock(HttpServletRequest request, HttpServletRequest response, Model model,
                                      String itemIdxStr) throws MsOnionException {
        if (MsOnionStringUtils.isEmpty(itemIdxStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        try {
            // 存放失败的商品集合
            List<CollectorItem> failCollectorItemList = new ArrayList<CollectorItem>();
            // 根据商品ID查询商品对象的集合
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            String[] strArr = MsOnionStringUtils.split(itemIdxStr, ",");
            Map<Integer, CollectorItem> collectorItemList = new HashMap<Integer, CollectorItem>();
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                long itemIdx = Long.parseLong(str);
                MsOnionResult msOnionResult = itemService.getItemByIdx(apiVersion, itemIdx);
                if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return msOnionResult;
                }
                CollectorItem collectorItem = (CollectorItem) msOnionResult.getData();
                collectorItemList.put(i, collectorItem);
            }

            // 查询商品ERP库存的集合
            MsOnionResult getItemPriceResult = erpStockService.queryItemListStock(apiVersion, collectorItemList);
            if (getItemPriceResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return getItemPriceResult;
            }

            MsOnionBatchReport<Stock> stockMsOnionBatchReport = (MsOnionBatchReport<Stock>) getItemPriceResult.getData();
            Map<Integer, Stock> stockMap = stockMsOnionBatchReport.getSuccessRecords();
            Map<Integer, Stock> stockFailMap = stockMsOnionBatchReport.getFailureRecords();
            if (MsOnionMapUtils.isNotEmpty(stockFailMap)) {
                for (Integer i : stockFailMap.keySet()) {
                    failCollectorItemList.add(collectorItemList.get(i));
                }
            }

            // 保存到采集的商品库存表
            List<CollectorItem> successCollectorItemList = new ArrayList<>();
            MsOnionResult batchSyncStockResult = warehouseService.batchSyncStock(apiVersion, stockMap);
            if (batchSyncStockResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                MsOnionBatchReport<Warehouse> msOnionBatchReport = (MsOnionBatchReport<Warehouse>) batchSyncStockResult.getData();
                if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                    for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                        failCollectorItemList.add(collectorItemList.get(i));
                    }
                }
                if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getSuccessRecords())) {
                    for (Integer i : msOnionBatchReport.getSuccessRecords().keySet()) {
                        successCollectorItemList.add(collectorItemList.get(i));
                    }
                }
            }
            // 获取新对象和旧对象
            MsOnionResult recordResult = getWarehouseRecordByItemIdx(apiVersion, successCollectorItemList);
            List<Map<String, Object>> recordResultList = (List<Map<String, Object>>) recordResult.getData();
            Map<Integer, Warehouse> successWarehouseList = new HashMap<Integer, Warehouse>();
            if (MsOnionCollectionUtils.isNotEmpty(recordResultList)) {
                for (int i = 0; i < recordResultList.size(); i++) {
                    Map<String, Object> map = recordResultList.get(i);
                    Warehouse warehouse = (Warehouse) map.get("new");
                    // 如果是已发布状态的才需要同步到正式商品库
                    if (warehouse != null && warehouse.getPublishStatus() == MsOnionPublishStatusUtils.PUBLISH_PUBLISHED) {
                        successWarehouseList.put(i, warehouse);
                    }
                }
            }
            List<String> errorMsg = new ArrayList<String>();
            if (MsOnionCollectionUtils.isNotEmpty(failCollectorItemList)) {
                for (CollectorItem collectorItem : failCollectorItemList) {
                    errorMsg.add(String.format(MessageConstants.MESSAGE_ITEM_SYNC_ERROR, collectorItem.getIdx()));
                }
            }

            // 同步到正式商品区的商品库存表
            MsOnionResult batchSaveOrUpdate = batchSaveOrUpdate(apiVersion, successWarehouseList);
            if (batchSaveOrUpdate.getStatus() == MsOnionStatusConstants.STATUS_200) {
                // 同步出错的返回
                Map<Integer, Warehouse> failMap = (Map<Integer, Warehouse>) batchSaveOrUpdate.getData();
                if (MsOnionMapUtils.isNotEmpty(failMap)) {
                    for (Integer i : failMap.keySet()) {
                        Warehouse ip = failMap.get(i);
                        errorMsg.add(String.format(MessageConstants.MESSAGE_ITEM_SYNC_ERP_ERROR, ip.getItemIdx()));
                        recordResultList.remove(i);
                    }
                }
            }


            Member currentUser = CurrentUserUtils.getCurrentUser();
            // 最后记录操作日志

            List<CollectorItemLog> collectorItemLogList = new ArrayList<CollectorItemLog>();
            if (MsOnionCollectionUtils.isNotEmpty(recordResultList)) {
                for (int i = 0; i < recordResultList.size(); i++) {
                    Map<String, Object> map = recordResultList.get(i);
                    Warehouse warehouseOld = (Warehouse) map.get("old");
                    Warehouse warehouseNew = (Warehouse) map.get("new");
                    List<CollectorItemLog> genPubListLogs = WarehousePublishUtil.
                            genPubListLogs(warehouseOld, warehouseNew, currentUser.getIdx());
                    collectorItemLogList.addAll(genPubListLogs);
                }
            }
            itemLogService.batchSaveItemLog(apiVersion, collectorItemLogList, currentUser.getIdx());
            return MsOnionResult.ok(errorMsg);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e,
                    "######### 同步ERP库存数据失败 ############");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }


    }

    /**
     * 查询库存对象的新对象和旧对象
     *
     * @param apiVersion               api版本
     * @param successCollectorItemList 商品ID集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getWarehouseRecordByItemIdx
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月28日 21:03:50
     */
    public MsOnionResult getWarehouseRecordByItemIdx(MsOnionApiVersion apiVersion, List<CollectorItem> successCollectorItemList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(successCollectorItemList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        List<Map<String, Object>> mapList = new ArrayList<>();

        for (CollectorItem collectorItem : successCollectorItemList) {
            if (collectorItem == null || collectorItem.getIdx() == null || collectorItem.getIdx() <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
            }
            // 检查商品是否存在
            MsOnionResult result = itemService.getItemByIdx(apiVersion, collectorItem.getIdx());
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
            }
            // 查询最新的对象
            WarehouseExample example = new WarehouseExample();
            WarehouseExample.Criteria criteria = example.createCriteria();
            criteria.andItemIdxEqualTo(collectorItem.getIdx());
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<Warehouse> warehouseList = warehouseService.queryByExample(apiVersion, example);
            if (null == warehouseList || warehouseList.size() <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_PRICE);
            } else if (warehouseList.size() != 1) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_PRICE_EXCEPTION);
            }
            Warehouse warehouse = warehouseList.get(0);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("new", warehouse);
            example.clear();
            WarehouseExample.Criteria criteria2 = example.createCriteria();
            criteria2.andItemIdxEqualTo(collectorItem.getIdx());
            criteria2.andStatusEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
            List<Warehouse> delItemPriceList = warehouseService.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(delItemPriceList)) {
                map.put("old", null);
            } else {
                map.put("old", delItemPriceList.get(0));
            }
            mapList.add(map);
        }

        return MsOnionResult.ok(mapList);
    }

    /**
     * 批量保存
     *
     * @param apiVersion   api版本
     * @param warehouseMap warehouse对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveOrUpdate
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月28日 21:20:41
     */
    public MsOnionResult batchSaveOrUpdate(MsOnionApiVersion apiVersion, Map<Integer, Warehouse> warehouseMap)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionMapUtils.isEmpty(warehouseMap)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        Map<Integer, WarehouseOfficial> saveItemPriceOfficialMap = new HashMap<Integer, WarehouseOfficial>();
        Map<Integer, WarehouseOfficial> updateItemPriceOfficialMap = new HashMap<Integer, WarehouseOfficial>();
        Map<Integer, Warehouse> errorMap = new HashMap<Integer, Warehouse>();
        for (Integer i : warehouseMap.keySet()) {
            Warehouse warehouse = warehouseMap.get(i);
            MsOnionResult itemOfficialByItemIdxResult = itemOfficialService.
                    getItemOfficialByItemIdx(apiVersion, warehouse.getItemIdx());
            if (itemOfficialByItemIdxResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                errorMap.put(i, warehouse);
            } else {
                ItemOfficial itemOfficial = (ItemOfficial) itemOfficialByItemIdxResult.getData();
                WarehouseOfficial warehouseOfficial = new WarehouseOfficial();
                MsOnionBeanUtils.copyProperties(warehouse, warehouseOfficial);
                warehouseOfficial.setItemOfficialIdx(itemOfficial.getIdx());

                WarehouseOfficial warehouseOfficial1 = warehouseOfficialService.queryByPrimaryKey(apiVersion, warehouse.getIdx());
                if (warehouseOfficial1 == null) {
                    saveItemPriceOfficialMap.put(i, warehouseOfficial);
                } else {
                    updateItemPriceOfficialMap.put(i, warehouseOfficial);
                }
            }
        }
        MsOnionBatchReport<WarehouseOfficial> msOnionBatchReport = null;
        if (MsOnionMapUtils.isNotEmpty(saveItemPriceOfficialMap)) {
            msOnionBatchReport = warehouseOfficialService.saveWithBatch(apiVersion, saveItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, warehouseMap.get(i));
                }
            }
        }

        if (MsOnionMapUtils.isNotEmpty(updateItemPriceOfficialMap)) {
            msOnionBatchReport = warehouseOfficialService.updateWithBatch(apiVersion, updateItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, warehouseMap.get(i));
                }
            }
        }

        return MsOnionResult.ok(errorMap);
    }
}
