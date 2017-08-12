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


package cc.msonion.carambola.manager.web.controller.logistics;

/**
 * @Title: CustomsDeclarController.java
 * @Package: cc.msonion.carambola.manager.web.controller.logistics
 * @Description: 商品报关controller
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
import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemBrand;
import cc.msonion.carambola.collector.pojo.CollectorItemCategory;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.collector.service.ItemBrandService;
import cc.msonion.carambola.collector.service.ItemCategoryService;
import cc.msonion.carambola.collector.service.ItemLogService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionPublishStatusUtils;
import cc.msonion.carambola.commons.common.utils.MsOnionPurchaseStatusUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.fileresource.interfaces.FileResourceTemplateService;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplate;
import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.logistics.pojo.*;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareOfficialService;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.logistics.service.LogisticsUnitService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.controller.collector.common.ItemCommonQuery;
import cc.msonion.carambola.manager.web.controller.ext.SyncToItemOfficialUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.common.utils.concurrent.MsOnionThreadPoolUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CustomsDeclarController
 * @Description: 商品报关controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月23日
 */
@Controller
public class CustomsDeclarController extends MsOnionBaseAppController {

    /**
     * 商品service
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品报关service
     */
    @Autowired
    private LogisticsCustomsDeclareService logisticsCustomsDeclareService;

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * 商品品牌服务
     */
    @Autowired
    private ItemBrandService itemBrandService;


    /**
     * 正式商品报关service
     */
    @Autowired
    private LogisticsCustomsDeclareOfficialService logisticsCustomsDeclareOfficialService;

    /**
     * 正式商品service
     */
    @Autowired
    private ItemOfficialService itemOfficialService;
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
     * 计量单位服务
     */
    @Autowired
    private LogisticsUnitService logisticsUnitService;

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
    @RequestMapping("/logistics/customs/list")
    public String listLogisticsCustomsDeclare(Model model) throws MsOnionException {
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


            Map isContainExciseMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.ISCONTAINEXCISE_STATUS);
            model.addAttribute("isContainExciseMap", isContainExciseMap);
            model.addAttribute("isContainExciseJson", MsOnionJsonUtils.objectToJson(isContainExciseMap));

            // 查询报关模板
            MsOnionResult msOnionResult = fileResourceTemplateService.queryOneTemplate(apiVersion, ManagerConstants.FILE_TEMPLATE_LOGISTICS,
                    MsOnionAppPlatformConstants.YT_MANAGER_SYSTEM);
            if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                FileResourceTemplate template = (FileResourceTemplate) msOnionResult.getData();
                String frDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_DOMAIN);
                model.addAttribute("templateApi", frDomin + "/cos/download/" + template.getFileMessageId());
            }
            return "/logistics/customsdeclare/listCustom";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取商品报关列表
     *
     * @param request           请求
     * @param page              当前页码
     * @param rows              每页记录数量
     * @param collectorItem     商品搜索参数
     * @param firstCategoryIdx  第一品类
     * @param secondCategoryIdx 第二品类
     * @param threeCategoryIdx  第三品类
     * @param isContainExcise   是否含有消费税
     * @param publishStatus     发布状态
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/logistics/customs/grid")
    @ResponseBody
    public MsOnionResult gridItem(HttpServletRequest request, String page, String rows, CollectorItem collectorItem,
                                  String firstCategoryIdx, String secondCategoryIdx, String threeCategoryIdx,
                                  String isContainExcise, String publishStatus) throws MsOnionException {
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
                    // 报关信息
                    MsOnionResult result = logisticsCustomsDeclareService.getLogisticsCustomsDeclareByItemIdx(apiVersion, item.getIdx());
                    if (null != result.getData()) {
                        LogisticsCustomsDeclare logisticsCustomsDeclare = (LogisticsCustomsDeclare) result.getData();
                        Long firstUnitIdx = logisticsCustomsDeclare.getFirstUnitIdx();
                        if (null != firstUnitIdx && firstUnitIdx > 0) {
                            LogisticsUnit firstLogisticsUnit = logisticsUnitService.queryByPrimaryKey(apiVersion, firstUnitIdx);
                            if (null != firstLogisticsUnit) {
                                logisticsCustomsDeclare.setFirstUnitIdxDynamicS(firstLogisticsUnit.getName());
                            }
                        }

                        Long secondUnitIdx = logisticsCustomsDeclare.getSecondUnitIdx();
                        if (null != secondUnitIdx && secondUnitIdx > 0) {
                            LogisticsUnit secondLogisticsUnit = logisticsUnitService.queryByPrimaryKey(apiVersion, secondUnitIdx);
                            if (null != secondLogisticsUnit) {
                                logisticsCustomsDeclare.setSecondUnitIdxDynamicS(secondLogisticsUnit.getName());
                            }
                        }
                        itemMap.put("logisticsCustomsDeclare", logisticsCustomsDeclare);
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
     * 更新报关信息
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return 返回更新结果
     * @throws MsOnionException 异常
     */
    @RequestMapping("/logistics/customs/edit/{idxStr}")
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

                    result = logisticsCustomsDeclareService.getLogisticsCustomsDeclareByItemIdx(apiVersion, idx);
                    if (null != result.getData()) {
                        LogisticsCustomsDeclare logisticsCustomsDeclare = (LogisticsCustomsDeclare) result.getData();
                        Long firstUnitIdx = logisticsCustomsDeclare.getFirstUnitIdx();
                        if (null != firstUnitIdx && firstUnitIdx > 0) {
                            LogisticsUnit firstLogisticsUnit = logisticsUnitService.queryByPrimaryKey(apiVersion, firstUnitIdx);
                            logisticsCustomsDeclare.setFirstUnitIdxDynamicS((null == firstLogisticsUnit) ? "" : firstLogisticsUnit.getName());

                        }

                        Long secondUnitIdx = logisticsCustomsDeclare.getSecondUnitIdx();
                        if (null != secondUnitIdx && secondUnitIdx > 0) {
                            LogisticsUnit secondLogisticsUnit = logisticsUnitService.queryByPrimaryKey(apiVersion, secondUnitIdx);
                            logisticsCustomsDeclare.setSecondUnitIdxDynamicS((null == secondLogisticsUnit) ? "" : secondLogisticsUnit.getName());
                        }
                        request.setAttribute("itemCustom", logisticsCustomsDeclare);
                    }
                }
            }

            String type = request.getParameter("type");
            if (StringUtils.isNotBlank(type) && ManagerConstants.VIEW.equals(type.trim())) {
                return "/logistics/customsdeclare/viewCustom";
            }

            List<LogisticsUnit> logisticsUnitList = logisticsUnitService.getAllLogisticsUnit(apiVersion);
            request.setAttribute("logisticsUnitList", logisticsUnitList);
            return "/logistics/customsdeclare/editCustom";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存商品报关信息
     *
     * @param request                 请求
     * @param logisticsCustomsDeclare 报关信息
     * @param syncFlag                并同步到正式商品报关信息
     * @return 返回保存结果
     * @throws MsOnionException 异常
     */
    @RequestMapping(value = "/logistics/customs/save", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult saveLogisticsCustomsDeclare(HttpServletRequest request, LogisticsCustomsDeclare logisticsCustomsDeclare, String syncFlag)
            throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            String postalArticlesTaxRateStr = request.getParameter("postalArticlesTaxRateStr");
            if (StringUtils.isNotBlank(postalArticlesTaxRateStr)) {
                int postalArticlesTaxRate = MsOnionNumberUtils.toIntForCurrency(postalArticlesTaxRateStr,
                        MsOnionNumberUtils.CURRENCY_UNIT_STEP_100000);
                logisticsCustomsDeclare.setPostalArticlesTaxRate(postalArticlesTaxRate);
            }

            String crossBorderTaxRateStr = request.getParameter("crossBorderTaxRateStr");
            if (StringUtils.isNotBlank(crossBorderTaxRateStr)) {
                int crossBorderTaxRate = MsOnionNumberUtils.toIntForCurrency(crossBorderTaxRateStr,
                        MsOnionNumberUtils.CURRENCY_UNIT_STEP_100000);
                logisticsCustomsDeclare.setCrossBorderTaxRate(crossBorderTaxRate);
            }

            String bcPriceStr = request.getParameter("bcPriceStr");
            if (StringUtils.isNotBlank(bcPriceStr)) {
                int bcPrice = MsOnionNumberUtils.toIntForPrice(bcPriceStr);
                logisticsCustomsDeclare.setBcPrice(bcPrice);
            }

            // 查询正式商品
            if (MsOnionConstants.YES.equals(syncFlag)) {
                MsOnionResult reulst = itemOfficialService.getItemOfficialByItemIdx(apiVersion, logisticsCustomsDeclare.getItemIdx());
                if (reulst.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ManagerConstants.PUBLISH_ITEMOFFICIAL_FAIL);
                }
            }

            Member currentUser = CurrentUserUtils.getCurrentUser();
            logisticsCustomsDeclare.setCreateByMemberIdx(currentUser.getIdx());
            logisticsCustomsDeclare.setUpdateByMemberIdx(currentUser.getIdx());

            MsOnionResult result2 = itemService.getItemByIdx(apiVersion, logisticsCustomsDeclare.getItemIdx());
            if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
            }
            MsOnionResult result = logisticsCustomsDeclareService.updateLogisticsCustomsDeclare(apiVersion, logisticsCustomsDeclare);
            if (result.getStatus() == MsOnionStatusConstants.STATUS_200 && MsOnionConstants.YES.equals(syncFlag)) {

                /*
                getMsOnionLogger().info(CustomsDeclarController.class.getName(), " ## 开始同步正式报关信息...");
                LogisticsCustomsDeclare customsDeclare = (LogisticsCustomsDeclare) result.getData();
                Callable<MsOnionResult> c = () -> SyncToItemOfficialUtils.syncToLogisticsCustomsDeclareOfficial(getMsOnionLogger(), customsDeclare,
                        itemOfficialService, logisticsCustomsDeclareService, logisticsCustomsDeclareOfficialService);
                Future<MsOnionResult> f1 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c);
                getMsOnionLogger().info(CustomsDeclarController.class.getName(), " ## 同步正式报关信息结束，返回结果： " + f1.get().getStatus());
                */
                // 线程异步更新
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    getMsOnionLogger().info(CustomsDeclarController.class.getName(), " ## 开始同步正式报关信息...");
                    LogisticsCustomsDeclare logistics = (LogisticsCustomsDeclare) result.getData();
                    MsOnionResult r = SyncToItemOfficialUtils.syncToLogisticsCustomsDeclareOfficial(getMsOnionLogger(), logistics,
                            itemOfficialService, logisticsCustomsDeclareService, logisticsCustomsDeclareOfficialService);
                    getMsOnionLogger().info(CustomsDeclarController.class.getName(), " ## 同步正式报关信息结束，返回结果： " + r.getStatus());
                });
            }
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
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
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月27日 16:33:45
     */
    @RequestMapping("/logistics/customs/saveAndPublish")
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
            MsOnionResult getCustomsRecordResult = getCustomsRecordByItemIdx(apiVersion, itemIdxList);
            if (getCustomsRecordResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return getCustomsRecordResult;
            }
            List<Map<String, Object>> customsMapList = (List<Map<String, Object>>) getCustomsRecordResult.getData();
            Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap = new HashMap<Integer, LogisticsCustomsDeclare>();
            // 最新的值用作发布到商品服务
            if (MsOnionCollectionUtils.isNotEmpty(customsMapList)) {
                for (int i = 0; i < customsMapList.size(); i++) {
                    Map<String, Object> map = customsMapList.get(i);
                    LogisticsCustomsDeclare collectorItemPrice = (LogisticsCustomsDeclare) map.get("new");
                    collectorItemPrice.setPublishStatus(MsOnionPublishStatusUtils.PUBLISH_PUBLISHED);
                    logisticsCustomsDeclareMap.put(i, collectorItemPrice);
                }
            }
            // 发布出错的对象
            Map<Integer, LogisticsCustomsDeclare> failMap = new HashMap<Integer, LogisticsCustomsDeclare>();

            // 修改正式商品库数据
            MsOnionResult msOnionResult = batchSaveOrUpdate(apiVersion, logisticsCustomsDeclareMap);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }

            Map<Integer, LogisticsCustomsDeclare> batchSaveOrUpdateMap = (Map<Integer, LogisticsCustomsDeclare>) msOnionResult.getData();
            if (MsOnionMapUtils.isNotEmpty(batchSaveOrUpdateMap)) {
                for (Integer i : batchSaveOrUpdateMap.keySet()) {
                    logisticsCustomsDeclareMap.remove(i);
                }
                failMap.putAll(batchSaveOrUpdateMap);
            }

            // 修改发布状态
            MsOnionResult updateMsOnionResult = logisticsCustomsDeclareService.
                    batchUpdateCollectorLogisticsCustoms(apiVersion, logisticsCustomsDeclareMap);
            if (updateMsOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return updateMsOnionResult;
            }
            MsOnionBatchReport<LogisticsCustomsDeclare> batchUpdateReport =
                    (MsOnionBatchReport<LogisticsCustomsDeclare>) updateMsOnionResult.getData();
            Map<Integer, LogisticsCustomsDeclare> batchUpdateMap = batchUpdateReport.getFailureRecords();
            if (MsOnionMapUtils.isNotEmpty(batchUpdateMap)) {
                for (Integer i : batchUpdateMap.keySet()) {
                    logisticsCustomsDeclareMap.remove(i);
                }
                failMap.putAll(batchUpdateMap);
            }
            // 发布出错信息返回给客户端
            List<String> errorMsg = new ArrayList<String>();
            if (MsOnionMapUtils.isNotEmpty(failMap)) {
                for (Integer i : failMap.keySet()) {
                    LogisticsCustomsDeclare ip = failMap.get(i);
                    errorMsg.add(String.format(MessageConstants.MESSAGE_ITEM_SYNC_ERROR, ip.getItemIdx()));
                }
            }
            return MsOnionResult.ok(errorMsg);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e,
                    "######### 保存并发布报关数据失败 ############");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

    }

    /**
     * 获取新对象和旧对象集合
     *
     * @param apiVersion  API版本
     * @param itemIdxList 商品ID集合
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException                MsOnionException
     */
    private MsOnionResult getCustomsRecordByItemIdx(MsOnionApiVersion apiVersion, List<Long> itemIdxList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(itemIdxList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        for (Long itemIdx : itemIdxList) {
            if (itemIdx == null || itemIdx <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
            }
            // 检查商品是否存在
            MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
            }
            // 查询最新的对象
            LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
            LogisticsCustomsDeclareExample.Criteria criteria = example.createCriteria();
            criteria.andItemIdxEqualTo(itemIdx);
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<LogisticsCustomsDeclare> logisticsCustomsDeclareList = logisticsCustomsDeclareService.
                    queryByExample(apiVersion, example);
            if (null == logisticsCustomsDeclareList || logisticsCustomsDeclareList.size() <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_CUSTOM);
            } else if (logisticsCustomsDeclareList.size() != 1) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_CUSTOM_EXCEPTION);
            }
            LogisticsCustomsDeclare itemPrice = logisticsCustomsDeclareList.get(0);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("new", itemPrice);
            example.clear();
            LogisticsCustomsDeclareExample.Criteria criteria2 = example.createCriteria();
            criteria2.andItemIdxEqualTo(itemIdx);
            criteria2.andStatusEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
            List<LogisticsCustomsDeclare> dellogisticsCustomsDeclareList = logisticsCustomsDeclareService.
                    queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(dellogisticsCustomsDeclareList)) {
                map.put("old", null);
            } else {
                map.put("old", dellogisticsCustomsDeclareList.get(0));
            }
            mapList.add(map);
        }

        return MsOnionResult.ok(mapList);
    }

    /**
     * 批量保存或修改
     *
     * @param apiVersion                 API
     * @param logisticsCustomsDeclareMap logisticsCustomsDeclare对象Map
     * @return MsOnionResult MsOnionResult
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException                MsOnionException
     */
    public MsOnionResult batchSaveOrUpdate(MsOnionApiVersion apiVersion, Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionMapUtils.isEmpty(logisticsCustomsDeclareMap)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        Map<Integer, LogisticsCustomsDeclareOfficial> saveItemPriceOfficialMap = new HashMap<Integer, LogisticsCustomsDeclareOfficial>();
        Map<Integer, LogisticsCustomsDeclareOfficial> updateItemPriceOfficialMap = new HashMap<Integer, LogisticsCustomsDeclareOfficial>();
        Map<Integer, LogisticsCustomsDeclareOfficial> deleteItemPriceOfficialMap = new HashMap<Integer, LogisticsCustomsDeclareOfficial>();
        Map<Integer, LogisticsCustomsDeclare> errorMap = new HashMap<Integer, LogisticsCustomsDeclare>();
        for (Integer i : logisticsCustomsDeclareMap.keySet()) {
            LogisticsCustomsDeclare collectorItemPrice = logisticsCustomsDeclareMap.get(i);
            MsOnionResult itemOfficialByItemIdxResult = itemOfficialService.
                    getItemOfficialByItemIdx(apiVersion, collectorItemPrice.getItemIdx());
            if (itemOfficialByItemIdxResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                errorMap.put(i, collectorItemPrice);
            } else {
                ItemOfficial itemOfficial = (ItemOfficial) itemOfficialByItemIdxResult.getData();
                LogisticsCustomsDeclareOfficial itemPriceOfficial = new LogisticsCustomsDeclareOfficial();
                MsOnionBeanUtils.copyProperties(collectorItemPrice, itemPriceOfficial);
                itemPriceOfficial.setItemOfficialIdx(itemOfficial.getIdx());

                LogisticsCustomsDeclareOfficial itemPriceOfficial1 = logisticsCustomsDeclareOfficialService.
                        queryByPrimaryKey(apiVersion, collectorItemPrice.getIdx());
                if (itemPriceOfficial1 == null) {

                    // 查询是否有旧数据
                    LogisticsCustomsDeclareOfficialExample itemPriceOfficialExample = new LogisticsCustomsDeclareOfficialExample();
                    LogisticsCustomsDeclareOfficialExample.Criteria criteria = itemPriceOfficialExample.createCriteria();
                    criteria.andItemOfficialIdxEqualTo(itemOfficial.getIdx());
                    criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                    LogisticsCustomsDeclareOfficial oldItemPriceOfficial = logisticsCustomsDeclareOfficialService.queryOne(apiVersion,
                            itemPriceOfficialExample);
                    if (oldItemPriceOfficial != null) {
                        oldItemPriceOfficial.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
                        // 加入旧数据到删除列表
                        deleteItemPriceOfficialMap.put(i, oldItemPriceOfficial);
                    }
                    saveItemPriceOfficialMap.put(i, itemPriceOfficial);
                } else {
                    updateItemPriceOfficialMap.put(i, itemPriceOfficial);
                }
            }
        }
        MsOnionBatchReport<LogisticsCustomsDeclareOfficial> msOnionBatchReport = null;
        if (MsOnionMapUtils.isNotEmpty(deleteItemPriceOfficialMap)) {
            msOnionBatchReport = logisticsCustomsDeclareOfficialService.updateWithBatch(apiVersion, deleteItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, logisticsCustomsDeclareMap.get(i));
                }
            }
        }

        if (MsOnionMapUtils.isNotEmpty(saveItemPriceOfficialMap)) {
            msOnionBatchReport = logisticsCustomsDeclareOfficialService.saveWithBatch(apiVersion, saveItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, logisticsCustomsDeclareMap.get(i));
                }
            }
        }

        if (MsOnionMapUtils.isNotEmpty(updateItemPriceOfficialMap)) {
            msOnionBatchReport = logisticsCustomsDeclareOfficialService.updateWithBatch(apiVersion, updateItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, logisticsCustomsDeclareMap.get(i));
                }
            }
        }

        return MsOnionResult.ok(errorMap);
    }
}
