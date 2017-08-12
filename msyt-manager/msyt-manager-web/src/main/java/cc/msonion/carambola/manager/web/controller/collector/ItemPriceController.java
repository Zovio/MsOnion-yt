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

package cc.msonion.carambola.manager.web.controller.collector;

/**
 * @Title: CollectorController.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 收集器Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月12日 下午9:24:42
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月12日 下午9:24:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：保存商品、确认采购、更新类目、更新价格、更新仓库类型
 */

import cc.msonion.carambola.collector.common.enums.ItemPricePublishEnum;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.utils.MsOnionPurchaseStatusUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.fileresource.interfaces.FileResourceTemplateService;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplate;
import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.item.service.ItemPriceOfficialService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.ItemPriceOfficialPublishUtil;
import cc.msonion.carambola.manager.web.controller.collector.common.ItemCommonQuery;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CollectorController
 * @Description: 收集器Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月12日 下午9:24:42
 */
@Controller
public class ItemPriceController extends MsOnionBaseAppController {
    /**
     * 商品service
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品价格service
     */
    @Autowired
    private ItemPriceService itemPriceService;

    /**
     * 正式商品价格服务
     */
    @Autowired
    private ItemPriceOfficialService itemPriceOfficialService;

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;
    /**
     * 品牌服务
     */
    @Autowired
    private ItemBrandService itemBrandService;

    /**
     * 商品类目service
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 商品日志服务
     */
    @Autowired
    private ItemLogService itemLogService;

    /**
     * 多条形码服务
     */
    @Autowired
    private ItemBarcodeService itemBarcodeService;

    /**
     * 正式商品服务
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * fileResourceTemplateService
     */
    @Autowired
    private FileResourceTemplateService fileResourceTemplateService;

    /**
     * 商品列表
     *
     * @param model 模型
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/price/list")
    public String listItem(Model model) throws MsOnionException {
        try {
            Map purchaseStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.PURCHASE_STATUS);
            model.addAttribute("purchaseStatusMap", purchaseStatusMap);
            model.addAttribute("purchaseStatusMapJson", MsOnionJsonUtils.objectToJson(purchaseStatusMap));

            Map collectionStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.COLLECTION_STATUS);
            model.addAttribute("collectionStatusMapJson", MsOnionJsonUtils.objectToJson(collectionStatusMap));

            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            model.addAttribute("warehouseTypeList", null);

            Short purchaseConfirmed = MsOnionPurchaseStatusUtils.PURCHASE_CONFIRMED;
            model.addAttribute("purchaseConfirmed", purchaseConfirmed);
            // 品牌集合
            List<CollectorItemBrand> collectorItemBrandList = itemBrandService.getAllItemBrand(apiVersion);
            model.addAttribute("collectorItemBrandList", collectorItemBrandList);

            Map publishStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.PUBLISH_STATUS);
            model.addAttribute("publishStatusMap", publishStatusMap);
            model.addAttribute("publishStatusJson", MsOnionJsonUtils.objectToJson(publishStatusMap));

            // 查询价格模板
            MsOnionResult msOnionResult = fileResourceTemplateService.queryOneTemplate(apiVersion, ManagerConstants.FILE_TEMPLATE_PRICE,
                    MsOnionAppPlatformConstants.YT_MANAGER_SYSTEM);
            if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                FileResourceTemplate template = (FileResourceTemplate) msOnionResult.getData();
                String frDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_DOMAIN);
                model.addAttribute("templateApi", frDomin + "/cos/download/" + template.getFileMessageId());
            }
            return "/collector/item/price/listSetPrice";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }


    /**
     * 获取商品价格列表
     *
     * @param request           请求
     * @param page              当前页码
     * @param rows              每页记录数量
     * @param collectorItem     商品搜索参数
     * @param firstCategoryIdx  第一品类
     * @param secondCategoryIdx 第二品类
     * @param threeCategoryIdx  第三品类
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/price/grid")
    @ResponseBody
    public MsOnionResult gridItem(HttpServletRequest request, String page, String rows, CollectorItem collectorItem,
                                  String firstCategoryIdx, String secondCategoryIdx, String threeCategoryIdx) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            CollectorItemExample example = new CollectorItemExample();
            ItemCommonQuery itemCommonQuery = new ItemCommonQuery(itemCategoryService);
            itemCommonQuery.setGoodsQueryParams(example, collectorItem, firstCategoryIdx, secondCategoryIdx, threeCategoryIdx, null);

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
                    // 类目
                    Long categoryIdx = item.getCategoryIdx();
                    if (null != categoryIdx) {
                        MsOnionResult result = itemCategoryService.getItemCategoryByIdx(apiVersion, categoryIdx);
                        if (null != result.getData()) {
                            CollectorItemCategory collectorItemCategory = (CollectorItemCategory) result.getData();
                            itemMap.put("collectorItemCategory", collectorItemCategory);
                        }
                    }
                    // 价格
                    MsOnionResult result = itemPriceService.getItemPriceByItemIdx(apiVersion, item.getIdx());
                    if (null != result.getData()) {
                        CollectorItemPrice collectorItemPrice = (CollectorItemPrice) result.getData();
                        itemMap.put("collectorItemPrice", collectorItemPrice);
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
     * 更新商品价格
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return 返回页面
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/item/price/edit/{idxStr}")
    public String updateItemPrice(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);

                CollectorItem item = itemService.queryByPrimaryKey(apiVersion, idx);
                request.setAttribute("item", item);

                MsOnionResult result = itemPriceService.getItemPriceByItemIdx(apiVersion, idx);
                if (null != result.getData()) {
                    CollectorItemPrice itemPrice = (CollectorItemPrice) result.getData();
                    request.setAttribute("itemPrice", itemPrice);
                }

                // 条形码列表
                List<CollectorItemBarcode> itemBarcodeList = new ArrayList<>();
                if (StringUtils.isNotBlank(item.getBarcode())) {
                    CollectorItemBarcode itemBarcode = new CollectorItemBarcode();
                    itemBarcode.setBarcode(item.getBarcode());
                    itemBarcodeList.add(itemBarcode);
                }

                List<CollectorItemBarcode> barcodes = itemBarcodeService.getItemBarcodeList(apiVersion, idx);
                itemBarcodeList.addAll(barcodes);
                request.setAttribute("itemBarcodeList", itemBarcodeList);
            }

            return "/collector/item/price/editSetPrice";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存商品价格
     *
     * @param request   请求
     * @param itemPrice 商品价格
     * @return msOnionResult
     */
    @RequestMapping("/collect/item/price/save")
    @ResponseBody
    public MsOnionResult saveItemPrice(HttpServletRequest request, CollectorItemPrice itemPrice) {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            MsOnionResult msOnionResult = itemService.getItemByIdx(apiVersion, itemPrice.getItemIdx());
            if (MsOnionStatusConstants.STATUS_200 != msOnionResult.getStatus()) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
            }

            CollectorItemPrice oldItemPrice = null;
            String saveType = request.getParameter("st");
            boolean isSaveAndPublish = StringUtils.isNotBlank(saveType) && "1".equals(saveType);
            if (isSaveAndPublish) {
                msOnionResult = itemPriceService.getItemPriceByItemIdx(apiVersion, itemPrice.getItemIdx());
                if (MsOnionStatusConstants.STATUS_200 == msOnionResult.getStatus()) {
                    oldItemPrice = (CollectorItemPrice) msOnionResult.getData();
                }
            }

            MsOnionResult result = this.saveItemPrices(request, itemPrice); // 保存商品价格
            if (MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                return result;
            }

            if (isSaveAndPublish) {
                CollectorItemPrice savedItemPrice = (CollectorItemPrice) result.getData();
                result = this.saveItemPriceOfficial(apiVersion, savedItemPrice); // 发布商品价格

                Member currentUser = CurrentUserUtils.getCurrentUser();
                List<CollectorItemLog> genPubListLogs = ItemPriceOfficialPublishUtil.
                        genPubListLogs(oldItemPrice, savedItemPrice, currentUser.getIdx());

                if (MsOnionCollectionUtils.isNotEmpty(genPubListLogs)) {
                    msOnionResult = itemLogService.batchSaveItemLog(apiVersion,
                            genPubListLogs, currentUser.getIdx()); // 保存日志
                    if (null == msOnionResult || MsOnionStatusConstants.STATUS_200 != msOnionResult.getStatus()) {
                        return msOnionResult;
                    }
                }
            }
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
        }
    }

    /**
     * 保存并发布
     *
     * @param request    HttpServletRequest 对象
     * @param response   HttpServletRequest  对象
     * @param model      Model实例对象
     * @param itemIdxStr 商品ID字符串
     * @return 页面视图
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/price/saveAndPublish")
    @ResponseBody
    public MsOnionResult saveAndPublish(HttpServletRequest request, HttpServletRequest response, Model model,
                                        String itemIdxStr) {
        if (MsOnionStringUtils.isEmpty(itemIdxStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        try {
            String[] strArr = MsOnionStringUtils.split(itemIdxStr, ",");
            List<Long> itemIdxList = new ArrayList<Long>();
            for (String str : strArr) {
                long itemIdx = Long.parseLong(str);
                itemIdxList.add(itemIdx);
            }
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 查询商品价格修改的最新两条数据
            MsOnionResult getItemPriceResult = itemPriceService.getItemPriceRecordByItemIdx(apiVersion, itemIdxList);
            if (getItemPriceResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return getItemPriceResult;
            }
            List<Map<String, Object>> itemPriceMapList = (List<Map<String, Object>>) getItemPriceResult.getData();
            Map<Integer, CollectorItemPrice> collectorItemPriceMap = new HashMap<Integer, CollectorItemPrice>();
            // 最新的值用作发布到商品服务
            if (MsOnionCollectionUtils.isNotEmpty(itemPriceMapList)) {
                for (int i = 0; i < itemPriceMapList.size(); i++) {
                    Map<String, Object> map = itemPriceMapList.get(i);
                    CollectorItemPrice collectorItemPrice = (CollectorItemPrice) map.get("new");

                    MsOnionResult msOnionResult = itemOfficialService.getItemOfficialByItemIdx(apiVersion, collectorItemPrice.getItemIdx());
                    if (null != msOnionResult && MsOnionStatusConstants.STATUS_200 == msOnionResult.getStatus()) {
                        // 若该商品已发布，则价格状态改为已发布，否则为未发布
                        ItemOfficial itemOfficial = (ItemOfficial) msOnionResult.getData();
                        if (null != itemOfficial) {
                            collectorItemPrice.setPublishStatus(ItemPricePublishEnum.PUBLISHED.getValue());
                        }
                    }

                    collectorItemPriceMap.put(i, collectorItemPrice);
                }
            }
            // 发布出错的对象
            Map<Integer, CollectorItemPrice> failMap = new HashMap<Integer, CollectorItemPrice>();
            Member currentUser = CurrentUserUtils.getCurrentUser();

            // 修改正式商品库数据
            MsOnionResult msOnionResult = itemPriceOfficialService.batchSaveOrUpdate(apiVersion, collectorItemPriceMap);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }

            Map<Integer, CollectorItemPrice> batchSaveOrUpdateMap = (Map<Integer, CollectorItemPrice>) msOnionResult.getData();
            if (MsOnionMapUtils.isNotEmpty(batchSaveOrUpdateMap)) {
                for (Integer i : batchSaveOrUpdateMap.keySet()) {
                    collectorItemPriceMap.remove(i);
                }
                failMap.putAll(batchSaveOrUpdateMap);
            }
            // 修改发布状态
            MsOnionResult updateItemPriceResult = itemPriceService.batchUpdateCollectorItemPrice(apiVersion, collectorItemPriceMap);
            if (updateItemPriceResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return updateItemPriceResult;
            }
            MsOnionBatchReport<CollectorItemPrice> updateItemPriceReport = (MsOnionBatchReport<CollectorItemPrice>) updateItemPriceResult.getData();
            if (updateItemPriceReport != null) {
                Map<Integer, CollectorItemPrice> updateItemPriceMap = updateItemPriceReport.getFailureRecords();
                for (Integer i : updateItemPriceMap.keySet()) {
                    collectorItemPriceMap.remove(i);
                }
                failMap.putAll(updateItemPriceMap);
            }
            // 返回错误的商品操作给客户端
            List<String> errorMsg = new ArrayList<String>();
            if (MsOnionMapUtils.isNotEmpty(failMap)) {
                for (Integer i : failMap.keySet()) {
                    CollectorItemPrice ip = failMap.get(i);
                    errorMsg.add(String.format(MessageConstants.MESSAGE_ITEM_SYNC_ERROR, ip.getItemIdx()));
                }
            }
            // 最后记录操作日志
            List<CollectorItemLog> collectorItemLogList = new ArrayList<CollectorItemLog>();
            if (MsOnionCollectionUtils.isNotEmpty(itemPriceMapList)) {
                for (int i = 0; i < itemPriceMapList.size(); i++) {
                    Map<String, Object> map = itemPriceMapList.get(i);
                    CollectorItemPrice collectorItemPriceOld = (CollectorItemPrice) map.get("old");
                    CollectorItemPrice collectorItemPriceNew = (CollectorItemPrice) map.get("new");
                    List<CollectorItemLog> genPubListLogs = ItemPriceOfficialPublishUtil.
                            genPubListLogs(collectorItemPriceOld, collectorItemPriceNew, currentUser.getIdx());
                    collectorItemLogList.addAll(genPubListLogs);
                }
            }
            itemLogService.batchSaveItemLog(apiVersion, collectorItemLogList, currentUser.getIdx());
            return MsOnionResult.ok(errorMsg);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e,
                    "######### 保存并发布商品价格数据失败 ############");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
    }

    /**
     * 保存商品价格
     *
     * @param request   请求
     * @param itemPrice 商品价格
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    private MsOnionResult saveItemPrices(HttpServletRequest request, CollectorItemPrice itemPrice)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        String marketPriceStr = request.getParameter("marketPriceStr");
        if (StringUtils.isNotBlank(marketPriceStr)) {
            int marketPrice = MsOnionNumberUtils.toIntForPrice(marketPriceStr);
            itemPrice.setMarketPrice(marketPrice);
        }

        String sellingPriceStr = request.getParameter("sellingPriceStr");
        if (StringUtils.isNotBlank(sellingPriceStr)) {
            int sellingPrice = MsOnionNumberUtils.toIntForPrice(sellingPriceStr);
            itemPrice.setSellingPrice(sellingPrice);
        }

        String supplyPriceStr = request.getParameter("supplyPriceStr");
        if (StringUtils.isNotBlank(supplyPriceStr)) {
            int supplyPrice = MsOnionNumberUtils.toIntForPrice(supplyPriceStr);
            itemPrice.setSupplyPrice(supplyPrice);
        }

        String saveType = request.getParameter("st");
        if (StringUtils.isNotBlank(saveType) && "1".equals(saveType)) {
            MsOnionResult msOnionResult = itemOfficialService.getItemOfficialByItemIdx(apiVersion, itemPrice.getItemIdx());
            if (null == msOnionResult || MsOnionStatusConstants.STATUS_200 != msOnionResult.getStatus()) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.SAVE_AND_PUBLISH_FAIL);
            }
            // 若该商品已发布，则价格状态改为已发布，否则为未发布
            ItemOfficial itemOfficial = (ItemOfficial) msOnionResult.getData();
            if (null != itemOfficial) {
                itemPrice.setPublishStatus(ItemPricePublishEnum.PUBLISHED.getValue());
            }
        }

        Member currentUser = CurrentUserUtils.getCurrentUser();
        itemPrice.setCreateByMemberIdx(currentUser.getIdx());
        itemPrice.setUpdateByMemberIdx(currentUser.getIdx());

        return itemPriceService.updateItemPrice(apiVersion, itemPrice);
    }

    /**
     * 保存到正式商品价格表中
     *
     * @param apiVersion 版本
     * @param itemPrice  价格
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    private MsOnionResult saveItemPriceOfficial(MsOnionApiVersion apiVersion, CollectorItemPrice itemPrice)
            throws MsOnionException {
        Map<Integer, CollectorItemPrice> collectorItemPriceMap = new HashMap<>();
        collectorItemPriceMap.put(0, itemPrice);
        return itemPriceOfficialService.batchSaveOrUpdate(apiVersion, collectorItemPriceMap);
    }
}
