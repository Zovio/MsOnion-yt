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
 * @Modify-by: Mark 3563249160@qq.com
 * @Modify-date: 2017年6月19日19:02:11
 * @Modify-version: V2.0.0
 * @Modify-description: editItem方法，品牌按品牌中英文首字母排序
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionPublishStatusUtils;
import cc.msonion.carambola.commons.common.utils.MsOnionPurchaseStatusUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.fileresource.interfaces.FileResourceTemplateService;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplate;
import cc.msonion.carambola.item.common.utils.ItemOfficialStatusUtils;
import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.service.ItemBasicOfficialService;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.controller.collector.common.ItemCommonQuery;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.controller.ext.SyncToItemOfficialUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.concurrent.MsOnionThreadPoolUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import cc.msonion.carambola.warehouse.pojo.WarehouseType;
import cc.msonion.carambola.warehouse.service.WarehouseTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName: CollectorController
 * @Description: 收集器Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月12日 下午9:24:42
 */
@Controller
public class ItemController extends MsOnionBaseAppController {
    /**
     * 商品service
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品竞价service
     */
    @Autowired
    private ItemBiddingService itemBiddingService;

    /**
     * 商品类目service
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 商品品牌服务
     */
    @Autowired
    private ItemBrandService itemBrandService;

    /**
     * 商品产地服务
     */
    @Autowired
    private ItemOriginService itemOriginService;

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * 商品历史服务
     */
    @Autowired
    private ItemHistoryService itemHistoryService;


    /**
     * 商品价格服务
     */
    @Autowired
    private ItemPriceService itemPriceService;

    /**
     * 成员服务
     */
    @Autowired
    private MemberService memberService;


    /**
     * warehouseTypeService
     */
    @Autowired
    private WarehouseTypeService warehouseTypeService;

    /**
     * ItemOfficialService
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * itemBasicOfficialService
     */
    @Autowired
    private ItemBasicOfficialService itemBasicOfficialService;

    /**
     * itemLogService
     */
    @Autowired
    private ItemLogService itemLogService;

    /**
     * fileResourceTemplateService
     */
    @Autowired
    private FileResourceTemplateService fileResourceTemplateService;

    /**
     * 商品采集服务
     */
    @Autowired
    private ItemCollectionService itemCollectionService;

    /**
     * 商品列表
     *
     * @param model 模型
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/list")
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
            MsOnionResult msOnionResult = fileResourceTemplateService.queryOneTemplate(apiVersion, ManagerConstants.FILE_TEMPLATE_ITEM,
                    MsOnionAppPlatformConstants.YT_MANAGER_SYSTEM);
            if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                FileResourceTemplate template = (FileResourceTemplate) msOnionResult.getData();
                String frDomin = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_DOMAIN);
                model.addAttribute("templateApi", frDomin + "/cos/download/" + template.getFileMessageId());
            }
            return "/collector/item/listItem";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑商品类目
     *
     * @param request 请求
     * @param idxStr  商品主键idx
     * @return 返回上级目录
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/item/categy/sel/edit/{idxStr}")
    public String editCategorySel(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isNotBlank(idxStr)) {
            Long idx = Long.valueOf(idxStr);

            CollectorItem item = itemService.queryByPrimaryKey(apiVersion, idx);
            if (null != item) {
                Long selectedCategoryIdx = item.getCategoryIdx();
                request.setAttribute("selectedCategoryIdx", selectedCategoryIdx);

                CollectorItemCategory selectedCategoryThree = null;
                CollectorItemCategory selectedCategoryTwo = null;
                CollectorItemCategory selectedCategoryOne = null;
                if (selectedCategoryIdx != null && selectedCategoryIdx > 0) {
                    selectedCategoryThree = itemCategoryService.queryByPrimaryKey(apiVersion, selectedCategoryIdx);
                    request.setAttribute("selectedCategoryThree", selectedCategoryThree);
                }
                if (selectedCategoryThree != null && selectedCategoryThree.getPidx() != null && selectedCategoryThree.getPidx() > 0) {
                    selectedCategoryTwo = itemCategoryService.queryByPrimaryKey(apiVersion, selectedCategoryThree.getPidx());
                    request.setAttribute("selectedCategoryTwo", selectedCategoryTwo);
                }
                if (selectedCategoryTwo != null && selectedCategoryTwo.getPidx() != null && selectedCategoryTwo.getPidx() > 0) {
                    selectedCategoryOne = itemCategoryService.queryByPrimaryKey(apiVersion, selectedCategoryTwo.getPidx());
                    request.setAttribute("selectedCategoryOne", selectedCategoryOne);
                }
            }
        }

        return "/collector/category/editCategorySelect";
    }

    /**
     * 获取商品列表
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
    @ResponseBody
    @RequestMapping("/collect/item/grid")
    public Map<String, Object> gridItem(HttpServletRequest request, String page, String rows, CollectorItem collectorItem,
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
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

                long total = pagingResult.getTotalCounts();
                List<CollectorItem> itemList = (List<CollectorItem>) pagingResult.getData();
                List<Map<String, Object>> mapList = new ArrayList<>();
                if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
                    for (CollectorItem item : itemList) {
                        Map<String, Object> itemMap = new HashMap<String, Object>();
                        if (item.getWarehouseTypeIdx() != null && item.getWarehouseTypeIdx() > 0L) {
                            WarehouseType warehouseType = warehouseTypeService.queryByPrimaryKey(apiVersion, item.getWarehouseTypeIdx());
                            item.setWarehouseTypeIdxDynamicS(Optional.ofNullable(warehouseType).map(t -> t.getWarehouseName()).orElse(""));
                        }
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
                        // 品牌
                        Long brandIdx = item.getBrandIdx();
                        if (null != brandIdx) {
                            MsOnionResult result = itemBrandService.getItemBrandByIdx(apiVersion, brandIdx);
                            if (null != result.getData()) {
                                CollectorItemBrand collectorItemBrand = (CollectorItemBrand) result.getData();
                                itemMap.put("collectorItemBrand", collectorItemBrand);
                            }
                        }

                        // 采集信息
                        MsOnionResult result = itemCollectionService.getItemTwiceCollectionByItemIdx(apiVersion, item.getIdx());
                        if (result.getData() != null) {
                            CollectorItemCollection itemCollection = (CollectorItemCollection) result.getData();
                            itemMap.put("itemCollection", itemCollection);
                        }

                        MsOnionResult itemOfficialResult = itemOfficialService.getItemOfficialByItemIdx(apiVersion, item.getIdx());
                        ItemOfficial itemOfficial = (ItemOfficial) itemOfficialResult.getData();
                        if (itemOfficial != null && (ItemOfficialStatusUtils.ITEM_OFFICIAL_ONLINE.equals(itemOfficial.getStatus())
                                || ItemOfficialStatusUtils.ITEM_OFFICIAL_OFFLINE.equals(itemOfficial.getStatus()))) {
                            itemMap.put("hasOnline", 1); // 上架过
                        } else {
                            itemMap.put("hasOnline", 0); // 没有上架记录
                        }

                        mapList.add(itemMap);
                    }
                }
                map.put("total", total);
                map.put("rows", mapList);
            }
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑商品
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/item/edit/{idxStr}")
    public String editItem(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                if (idx != 0) {
                    MsOnionResult result = itemService.getItemByIdx(apiVersion, idx);

                    CollectorItem item = (CollectorItem) result.getData();

                    // 查询图片
                    item.setImageBigDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), item.getImageBig()));

                    request.setAttribute("item", item);

                    // 该商品是否上架过
                    MsOnionResult itemOfficialResult = itemOfficialService.getItemOfficialByItemIdx(apiVersion, item.getIdx());
                    ItemOfficial itemOfficial = (ItemOfficial) itemOfficialResult.getData();
                    if (itemOfficial != null && (ItemOfficialStatusUtils.ITEM_OFFICIAL_ONLINE.equals(itemOfficial.getStatus())
                            || ItemOfficialStatusUtils.ITEM_OFFICIAL_OFFLINE.equals(itemOfficial.getStatus()))) {
                        request.setAttribute("hasOnline", 1); // 上架过
                    } else {
                        request.setAttribute("hasOnline", 0); // 没有上架记录
                    }

                    CollectorItemBiddingExample example = new CollectorItemBiddingExample();
                    CollectorItemBiddingExample.Criteria criteria = example.createCriteria();
                    criteria.andItemIdxEqualTo(idx);
                    criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

                    List<CollectorItemBidding> list = itemBiddingService.queryByExample(apiVersion, example);
                    if (null != list && list.size() > 0) {
                        CollectorItemBidding itemBidding = list.get(0);
                        request.setAttribute("itemBidding", itemBidding);
                    }
                }
            }

            request.setAttribute("collectImgurl", MsOnionConstants.ALBUM_COLLECT_PATH_START);

            List<CollectorItemBrand> itemBrandList = itemBrandService.getItemBrandOrderByName(apiVersion);

            request.setAttribute("itemBrandList", itemBrandList);

            List<CollectorItemOrigin> itemOriginList = itemOriginService.getAllItemOrigin(apiVersion);
            request.setAttribute("itemOriginList", itemOriginList);

            Short purchaseConfirmed = MsOnionPurchaseStatusUtils.PURCHASE_CONFIRMED;
            request.setAttribute("purchaseConfirmed", purchaseConfirmed);

            return "/collector/item/editItem";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存商品
     *
     * @param request     HttpServletRequest实例对象
     * @param response    HttpServletResponse实例对象
     * @param item        商品信息
     * @param itemBidding 商品竞价信息
     * @param biddingIdx  商品竞价主键idx
     * @return 返回保存结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping(value = "/collect/item/save", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult saveItem(HttpServletRequest request, HttpServletResponse response, CollectorItem item,
                                  CollectorItemBidding itemBidding, String biddingIdx)
            throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            MsOnionResult result;
            Long idx = item.getIdx();
            Member currentUser = CurrentUserUtils.getCurrentUser();

            // 设置商品竞价信息 start
            // 亚马逊售价
            String amazonPriceStr = request.getParameter("amazonPriceStr");
            if (StringUtils.isNotBlank(amazonPriceStr)) {
                int amazonPrice = MsOnionNumberUtils.toIntForPrice(amazonPriceStr);
                itemBidding.setAmazonPrice(amazonPrice);
            }
            // 天猫售价
            String tmallPriceStr = request.getParameter("tmallPriceStr");
            if (StringUtils.isNotBlank(tmallPriceStr)) {
                int tmallPrice = MsOnionNumberUtils.toIntForPrice(tmallPriceStr);
                itemBidding.setTmallPrice(tmallPrice);
            }
            // 京东售价
            String jdPriceStr = request.getParameter("jdPriceStr");
            if (StringUtils.isNotBlank(jdPriceStr)) {
                int jdPrice = MsOnionNumberUtils.toIntForPrice(jdPriceStr);
                itemBidding.setJdPrice(jdPrice);
            }
            // 小红书售价
            String redPriceStr = request.getParameter("redPriceStr");
            if (StringUtils.isNotBlank(redPriceStr)) {
                int redPrice = MsOnionNumberUtils.toIntForPrice(redPriceStr);
                itemBidding.setRedPrice(redPrice);
            }
            // 国外官网售价
            String abroadPriceStr = request.getParameter("abroadPriceStr");
            if (StringUtils.isNotBlank(abroadPriceStr)) {
                int abroadPrice = MsOnionNumberUtils.toIntForPrice(abroadPriceStr);
                itemBidding.setAbroadPrice(abroadPrice);
            }
            // 国内官网售价
            String domesticPriceStr = request.getParameter("domesticPriceStr");
            if (StringUtils.isNotBlank(domesticPriceStr)) {
                int domesticPrice = MsOnionNumberUtils.toIntForPrice(domesticPriceStr);
                itemBidding.setDomesticPrice(domesticPrice);
            }
            // 原产国售价
            String originCountryPriceStr = request.getParameter("originCountryPriceStr");
            if (StringUtils.isNotBlank(originCountryPriceStr)) {
                int originCountryPrice = MsOnionNumberUtils.toIntForPrice(originCountryPriceStr);
                itemBidding.setOriginCountryPrice(originCountryPrice);
            }
            // 考拉售价
            String koalaPriceStr = request.getParameter("koalaPriceStr");
            if (StringUtils.isNotBlank(koalaPriceStr)) {
                int koalaPrice = MsOnionNumberUtils.toIntForPrice(koalaPriceStr);
                itemBidding.setKoalaPrice(koalaPrice);
            }
            // 聚美售价
            String jumeiPriceStr = request.getParameter("jumeiPriceStr");
            if (StringUtils.isNotBlank(jumeiPriceStr)) {
                int jumeiPrice = MsOnionNumberUtils.toIntForPrice(jumeiPriceStr);
                itemBidding.setJumeiPrice(jumeiPrice);
            }
            // 设置商品竞价信息 end

            if (null == idx) {
                item.setCreateByMemberIdx(currentUser.getIdx());
                item.setUpdateByMemberIdx(currentUser.getIdx());

                itemBidding.setCreateByMemberIdx(currentUser.getIdx());
                itemBidding.setUpdateByMemberIdx(currentUser.getIdx());

                result = itemService.addItemAndBidding(apiVersion, item, itemBidding);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }
            } else {
                item.setCreateByMemberIdx(currentUser.getIdx());
                item.setUpdateByMemberIdx(currentUser.getIdx());

                itemBidding.setCreateByMemberIdx(currentUser.getIdx());
                itemBidding.setUpdateByMemberIdx(currentUser.getIdx());

                // 取名biddingIdx，避免与idx冲突
                Long bIdx = Long.valueOf(biddingIdx);
                itemBidding.setIdx(bIdx);
                itemBidding.setItemIdx(idx);

                result = itemService.updateItemAndBidding(apiVersion, item, itemBidding);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }
            }

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param idxStr 商品主键idx
     * @return msOnionResult
     */
    @RequestMapping("/collect/query/{idxStr}")
    @ResponseBody
    public MsOnionResult getItem(@PathVariable String idxStr) {
        this.getMsOnionLogger().info(getClass().getName(), "showItem # 显示商品信息 ,   idxStr=" + idxStr);

        if (!MsOnionRegexUtils.checkDigit(idxStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法 , idxStr=" + idxStr);
        }

        Long idx = Long.parseLong(idxStr);

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            MsOnionResult result = itemService.getItemByIdx(apiVersion, idx);

            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
        }
    }

    /**
     * 确认采购
     *
     * @param item 商品
     * @return 返回结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping(value = "/collect/item/purchase")
    @ResponseBody
    public MsOnionResult confirmPurchase(CollectorItem item) throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            Member currentUser = CurrentUserUtils.getCurrentUser();
            item.setUpdateByMemberIdx(currentUser.getIdx());

            // 查询仓库、获取货号尾数
            WarehouseType warehouseType = warehouseTypeService.queryByPrimaryKey(apiVersion, item.getWarehouseTypeIdx());
            // 货号尾数
            Short mantissa = 0;
            if (warehouseType != null) {
                mantissa = warehouseType.getMantissa();
            }

            MsOnionResult result = itemService.confirmPurchase(apiVersion, item, mantissa);
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500,
                    CollectorMessageConstants.MESSAGE_CONFIRM_PURCHASE_FAIL);
        }
    }


    /**
     * 修改仓库
     *
     * @param item     商品
     * @param syncFlag 保存并发布
     * @return 返回结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping(value = "/collect/item/editWarehouse")
    @ResponseBody
    public MsOnionResult editWarehouse(CollectorItem item, String syncFlag) throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 查询正式商品
            if (MsOnionConstants.YES.equals(syncFlag)) {
                MsOnionResult reulst = itemOfficialService.getItemOfficialByItemIdx(apiVersion, item.getIdx());
                if (reulst.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ManagerConstants.PUBLISH_ITEMOFFICIAL_FAIL);
                }
            }

            Member currentUser = CurrentUserUtils.getCurrentUser();
            MsOnionResult result = itemService.getItemByIdx(apiVersion, item.getIdx());
            if (result.getStatus() == MsOnionStatusConstants.STATUS_200) {
                CollectorItem collectorItem = (CollectorItem) result.getData();
                collectorItem.setWarehouseTypeIdx(item.getWarehouseTypeIdx());
                String itemNo = collectorItem.getItemNo();

                // 查询仓库、获取货号尾数
                WarehouseType warehouseType = warehouseTypeService.queryByPrimaryKey(apiVersion, item.getWarehouseTypeIdx());
                if (warehouseType != null) {
                    collectorItem.setItemNo(itemNo.substring(0, itemNo.length() - MsOnionConstants.TWO) + warehouseType.getMantissa());
                    collectorItem.setUpdateByMemberIdx(currentUser.getIdx());
                    collectorItem.setUpdateTime(new Date());

                    Short publishStatus = MsOnionConstants.YES.equals(syncFlag) ? MsOnionPublishStatusUtils.PUBLISH_PUBLISHED
                            : MsOnionPublishStatusUtils.PUBLISH_NOT_PUBLISH;
                    collectorItem.setPublishStatus(publishStatus);
                    itemService.updateByPrimaryKey(apiVersion, collectorItem);

                    if (MsOnionConstants.YES.equals(syncFlag)) {
                        CollectorItem collectorItem2 = new CollectorItem();
                        collectorItem2.setIdx(collectorItem.getIdx());
                        collectorItem2.setWarehouseTypeIdx(collectorItem.getWarehouseTypeIdx());
                        collectorItem2.setWarehouseTypeIdxDynamicS(warehouseType.getWarehouseName());
                        collectorItem2.setItemNo(collectorItem.getItemNo());
                        MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                            getMsOnionLogger().info(ItemController.class.getName(), " ## 开始同步正式商品仓库...");
                            MsOnionResult r = SyncToItemOfficialUtils.syncToItemOfficial(getMsOnionLogger(), collectorItem2,
                                    itemOfficialService, itemBasicOfficialService, itemLogService, currentUser.getIdx(),
                                    warehouseTypeService, itemCategoryService);
                            getMsOnionLogger().info(ItemController.class.getName(), " ## 同步正式商品仓库结束，返回结果： " + r.getStatus());
                        });
                    }
                    return MsOnionResult.ok();
                }

            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_EDIT_WAREHOUSE_FAIL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500,
                    CollectorMessageConstants.MESSAGE_EDIT_WAREHOUSE_FAIL);
        }
    }


    /**
     * 修改类目
     *
     * @param item     商品
     * @param syncFlag 保存并发布
     * @return 返回修改结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/category/save")
    @ResponseBody
    public MsOnionResult updateItemCategory(CollectorItem item, String syncFlag) throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 查询正式商品
            if (MsOnionConstants.YES.equals(syncFlag)) {
                MsOnionResult result = itemOfficialService.getItemOfficialByItemIdx(apiVersion, item.getIdx());
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ManagerConstants.PUBLISH_ITEMOFFICIAL_FAIL);
                }
                item.setPublishStatus(MsOnionPublishStatusUtils.PUBLISH_PUBLISHED);
            } else {
                item.setPublishStatus(MsOnionPublishStatusUtils.PUBLISH_NOT_PUBLISH);
            }

            Member currentUser = CurrentUserUtils.getCurrentUser();
            item.setUpdateByMemberIdx(currentUser.getIdx());
            CollectorItem collectorItem = itemService.queryByPrimaryKey(apiVersion, item.getIdx());
            WarehouseType warehouseType = warehouseTypeService.queryByPrimaryKey(apiVersion, collectorItem.getWarehouseTypeIdx());
            Short mantissa = 0;
            if (warehouseType != null) {
                mantissa = warehouseType.getMantissa();
            }

            MsOnionResult result = itemService.updateItemCategory(apiVersion, item, mantissa);
            if (result.getStatus() == MsOnionStatusConstants.STATUS_200 && MsOnionConstants.YES.equals(syncFlag)) {
                CollectorItem newCollectorItem = (CollectorItem) result.getData();
                CollectorItem collectorItem2 = new CollectorItem();
                collectorItem2.setIdx(newCollectorItem.getIdx());
                collectorItem2.setCategoryIdx(newCollectorItem.getCategoryIdx());
                collectorItem2.setItemNo(newCollectorItem.getItemNo());
                MsOnionThreadPoolUtils.getFixedThreadPool().execute(() -> {
                    getMsOnionLogger().info(ItemController.class.getName(), " ## 开始同步正式商品分类...");
                    MsOnionResult r = SyncToItemOfficialUtils.syncToItemOfficial(getMsOnionLogger(), collectorItem2,
                            itemOfficialService, itemBasicOfficialService, itemLogService, currentUser.getIdx(),
                            warehouseTypeService, itemCategoryService);
                    getMsOnionLogger().info(ItemController.class.getName(), " ## 同步正式商品分类结束，返回结果： " + r.getStatus());
                });
            }
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500,
                    CollectorMessageConstants.MESSAGE_UPDATE_CATEGORY_FAIL);
        }
    }

    /**
     * 商品历史纪录
     *
     * @param request 请求
     * @param idxStr  商品主键idx
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/history/{idxStr}")
    public String listItemHistory(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        request.setAttribute("idxStr", idxStr);
        return "/collector/item/history/listItemHistory";
    }

    /**
     * 商品基本信息历史
     *
     * @param request 请求
     * @param idxStr  商品主键idx
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/history/basic/{idxStr}")
    public String listItemHistoryBasic(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isNotBlank(idxStr)) {
            Long idx = Long.valueOf(idxStr);
            request.setAttribute("itemIdx", idx);

            MsOnionResult result = itemService.getItemByIdx(apiVersion, idx);
            if (null != result.getData()) {
                CollectorItem item = (CollectorItem) result.getData();
                request.setAttribute("item", item);

                Long brandIdx = item.getBrandIdx();
                MsOnionResult rs = itemBrandService.getItemBrandByIdx(apiVersion, brandIdx);
                if (null != rs.getData()) {
                    CollectorItemBrand itemBrand = (CollectorItemBrand) rs.getData();
                    request.setAttribute("brandName", itemBrand.getBrandName());
                }

                Long originIdx = item.getOriginIdx();
                rs = itemOriginService.getItemOriginByIdx(apiVersion, originIdx);
                if (null != rs.getData()) {
                    CollectorItemOrigin itemOrigin = (CollectorItemOrigin) rs.getData();
                    request.setAttribute("originCnName", itemOrigin.getCnName());
                }
            }
        }

        return "/collector/item/history/listItemHistoryBasic";
    }

    /**
     * 获取商品基本信息历史
     *
     * @param request 请求
     * @param page    当前页码
     * @param rows    每页记录数量
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collect/item/history/basic/grid")
    public Map<String, Object> getGridItemHistoryBasic(HttpServletRequest request, String page, String rows) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            CollectorItemHistoryExample example = new CollectorItemHistoryExample();
            CollectorItemHistoryExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            String itemIdxStr = request.getParameter("itemIdx");
            if (StringUtils.isNotBlank(itemIdxStr)) {
                Long itemIdx = Long.valueOf(itemIdxStr);
                criteria.andItemIdxEqualTo(itemIdx);
            }

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = itemHistoryService.queryListByPageForResult(apiVersion, example, pageNum, pageSize, orderBy);
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

                long total = pagingResult.getTotalCounts();
                List<CollectorItemHistory> itemHistoryList = (List<CollectorItemHistory>) pagingResult.getData();

                List<Map<String, Object>> list = new ArrayList<>();
                Iterator<CollectorItemHistory> it = itemHistoryList.iterator();
                while (it.hasNext()) {
                    CollectorItemHistory itemHistory = it.next();
                    Map<String, Object> itemDTO = new HashMap<>();
                    itemDTO.put("cnName", itemHistory.getCnName());
                    itemDTO.put("enName", itemHistory.getEnName());
                    itemDTO.put("specification", itemHistory.getSpecification());
                    itemDTO.put("batch", itemHistory.getBatch());
                    itemDTO.put("createTime", itemHistory.getCreateTimeEnYyyyMMddHHmmss());

                    Long brandIdx = itemHistory.getBrandIdx();
                    if (null != brandIdx) {
                        MsOnionResult result = itemBrandService.getItemBrandByIdx(apiVersion, brandIdx);
                        if (null != result.getData()) {
                            CollectorItemBrand itemBrand = (CollectorItemBrand) result.getData();

                            String brandName = itemBrand.getBrandName();
                            itemDTO.put("brandName", brandName);
                        }
                    }

                    Long originIdx = itemHistory.getOriginIdx();
                    if (null != originIdx) {
                        MsOnionResult result = itemOriginService.getItemOriginByIdx(apiVersion, originIdx);
                        if (null != result.getData()) {
                            CollectorItemOrigin itemOrigin = (CollectorItemOrigin) result.getData();
                            String originCnName = itemOrigin.getCnName();
                            itemDTO.put("originCnName", originCnName);
                        }
                    }

                    Long createByMemberIdx = itemHistory.getCreateByMemberIdx();
                    if (null != createByMemberIdx && createByMemberIdx != 0) {
                        Member member = memberService.queryByPrimaryKey(apiVersion, createByMemberIdx);
                        if (null != member) {
                            String createByMemberName = member.getName();
                            itemDTO.put("createByMemberName", createByMemberName);
                        }
                    }

                    list.add(itemDTO);
                }

                map.put("total", total);
                map.put("rows", list);
            }
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 商品竞价信息历史
     *
     * @param request 请求
     * @param idxStr  商品主键idx
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/history/bidding/{idxStr}")
    public String listItemHistoryBidding(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isNotBlank(idxStr)) {
            Long idx = Long.valueOf(idxStr);
            request.setAttribute("itemIdx", idx);

            MsOnionResult result = itemBiddingService.getItemBiddingByItemIdx(apiVersion, idx);
            if (null != result.getData()) {
                CollectorItemBidding itemBidding = (CollectorItemBidding) result.getData();
                request.setAttribute("itemBidding", itemBidding);
            }
        }

        return "/collector/item/history/listItemHistoryBidding";
    }

    /**
     * 获取商品竞价信息历史
     *
     * @param request 请求
     * @param page    当前页码
     * @param rows    每页记录数量
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collect/item/history/bidding/grid")
    public Map<String, Object> getGridItemHistoryBidding(HttpServletRequest request, String page, String rows) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            CollectorItemBiddingExample example = new CollectorItemBiddingExample();
            CollectorItemBiddingExample.Criteria criteria = example.createCriteria();

            String itemIdxStr = request.getParameter("itemIdx");
            if (StringUtils.isNotBlank(itemIdxStr)) {
                Long itemIdx = Long.valueOf(itemIdxStr);
                criteria.andItemIdxEqualTo(itemIdx);
            }

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = itemBiddingService.queryListByPageForResult(apiVersion, example, pageNum, pageSize, orderBy);
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

                long total = pagingResult.getTotalCounts();
                List<CollectorItemBidding> itemBiddingList = (List<CollectorItemBidding>) pagingResult.getData();

                Iterator<CollectorItemBidding> it = itemBiddingList.iterator();
                while (it.hasNext()) {
                    CollectorItemBidding itemBidding = it.next();
                    Long createByMemberIdx = itemBidding.getCreateByMemberIdx();

                    String createByMemberName = getCreateByMemberName(apiVersion, createByMemberIdx);
                    itemBidding.setCreateByMemberIdxDynamicS(createByMemberName);
                }

                map.put("total", total);
                map.put("rows", itemBiddingList);
            }
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 商品价格信息历史
     *
     * @param request 请求
     * @param idxStr  商品主键idx
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/history/price/{idxStr}")
    public String listItemHistoryPrice(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isNotBlank(idxStr)) {
            Long idx = Long.valueOf(idxStr);
            request.setAttribute("itemIdx", idx);

            MsOnionResult result = itemPriceService.getItemPriceByItemIdx(apiVersion, idx);
            if (null != result.getData()) {
                CollectorItemPrice itemPrice = (CollectorItemPrice) result.getData();
                request.setAttribute("itemPrice", itemPrice);
            }
        }

        return "/collector/item/history/listItemHistoryPrice";
    }

    /**
     * 获取商品价格信息历史
     *
     * @param request 请求
     * @param page    当前页码
     * @param rows    每页记录数量
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collect/item/history/price/grid")
    public Map<String, Object> getGridItemHistoryPrice(HttpServletRequest request, String page, String rows) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            CollectorItemPriceExample example = new CollectorItemPriceExample();
            CollectorItemPriceExample.Criteria criteria = example.createCriteria();

            String itemIdxStr = request.getParameter("itemIdx");
            if (StringUtils.isNotBlank(itemIdxStr)) {
                Long itemIdx = Long.valueOf(itemIdxStr);
                criteria.andItemIdxEqualTo(itemIdx);
            }

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = itemPriceService.queryListByPageForResult(apiVersion, example, pageNum, pageSize, orderBy);
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

                long total = pagingResult.getTotalCounts();
                List<CollectorItemPrice> itemPriceList = (List<CollectorItemPrice>) pagingResult.getData();

                Iterator<CollectorItemPrice> it = itemPriceList.iterator();
                while (it.hasNext()) {
                    CollectorItemPrice itemPrice = it.next();
                    Long createByMemberIdx = itemPrice.getCreateByMemberIdx();

                    String createByMemberName = getCreateByMemberName(apiVersion, createByMemberIdx);
                    itemPrice.setCreateByMemberIdxDynamicS(createByMemberName);
                }

                map.put("total", total);
                map.put("rows", itemPriceList);
            }
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取创建成员名称
     *
     * @param apiVersion        Api版本
     * @param createByMemberIdx 创建成员idx
     * @return 返回创建成员名称
     * @throws MsOnionException 自定义异常
     */
    String getCreateByMemberName(MsOnionApiVersion apiVersion, Long createByMemberIdx) throws MsOnionException {
        if (null == createByMemberIdx || createByMemberIdx == 0) {
            return null;
        }

        Member member = memberService.queryByPrimaryKey(apiVersion, createByMemberIdx);
        if (null == member) {
            return null;
        }

        String createByMemberName = member.getName();
        return createByMemberName;
    }

    /**
     * 根据父类目的ID 查询子类目的集合
     *
     * @param request     HttpServletRequest 对象
     * @param parentIdStr 父类目的ID
     * @return 子类目的集合
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collect/item/getSubList")
    public MsOnionResult getSubList(HttpServletRequest request, String parentIdStr) throws MsOnionException {
        try {
            long pidx = ItemConstants.DEFAULT_PRESENT_IDX;
            if (StringUtils.isNotBlank(parentIdStr)) {
                pidx = MsOnionNumberUtils.toLong(parentIdStr, ItemConstants.DEFAULT_PRESENT_IDX);
            }
            // 版本信息
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            CollectorItemCategoryExample example = new CollectorItemCategoryExample();
            CollectorItemCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
            criteria.andPidxEqualTo(pidx);
            List<CollectorItemCategory> collectorItemCategoryList = itemCategoryService.
                    queryByExample(apiVersion, example);

            return MsOnionResult.ok(collectorItemCategoryList);
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 根据父类目的ID查询子类目的集合失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 根据父类目的ID查询子类目的集合..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }


    /**
     * 初始化仓库
     *
     * @param request request
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping("/collect/item/initWarehouse")
    public MsOnionResult initWarehouse(HttpServletRequest request) throws MsOnionException {
        try {
            // 版本信息
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult result = warehouseTypeService.getWarehouseList(apiVersion);
            return result;
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 初始化仓库失败.");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }
}
