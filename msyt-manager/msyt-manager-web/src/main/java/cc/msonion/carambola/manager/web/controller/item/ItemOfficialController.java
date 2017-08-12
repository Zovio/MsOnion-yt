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
package cc.msonion.carambola.manager.web.controller.item;

/**
 * @Title: ItemOfficialController.java
 * @Package: cc.msonion.carambola.manager.web.controller.item
 * @Description: 正式商品Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/6/19 11:02
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/6/19 11:02
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：正式商品列表
 */

import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.utils.MsOnionSaleStatusUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.item.common.constants.ItemMessageConstants;
import cc.msonion.carambola.item.common.utils.ItemOfficialStatusUtils;
import cc.msonion.carambola.item.ext.pojo.ItemOfficialExt;
import cc.msonion.carambola.item.pojo.*;
import cc.msonion.carambola.item.service.*;
import cc.msonion.carambola.item.service.ext.ItemOfficialExtService;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial;
import cc.msonion.carambola.logistics.pojo.LogisticsUnit;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareOfficialService;
import cc.msonion.carambola.logistics.service.LogisticsUnitService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.ext.utils.ItemOfficialAttributeUtils;
import cc.msonion.carambola.manager.ext.utils.ItemOfficialOnlineUtils;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.controller.collector.common.ItemCommonQuery;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficial;
import cc.msonion.carambola.warehouse.service.WarehouseOfficialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName: ItemOfficialController
 * @Description: 正式商品Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/6/19 11:02
 */
@Controller
public class ItemOfficialController extends MsOnionBaseAppController {
    /**
     * 商品service
     */
    @Autowired
    private ItemService itemService;

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
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * 商品模块 - 正式商品服务
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * 商品模块 - 商品服务
     */
    @Autowired
    private ItemBasicOfficialService itemBasicOfficialService;

    /**
     * 商品模块 - 商品采集服务
     */
    @Autowired
    private ItemCollectionOfficialService itemCollectionOfficialService;

    /**
     * 正式商品属性图片服务
     */
    @Autowired
    private ItemAttributeImageOfficialService itemAttributeImageOfficialService;

    /**
     * 成员服务
     */
    @Autowired
    private MemberService memberService;

    /**
     * 属性服务
     */
    @Autowired
    private ItemAttributeService itemAttributeService;

    /**
     * 正式商品属性服务
     */
    @Autowired
    private ItemAttributeOfficialService itemAttributeOfficialService;

    /**
     * 属性组属性服务
     */
    @Autowired
    private AttributeGroupAttributeService attributeGroupAttributeService;

    /**
     * 正式商品扩展服务
     */
    @Autowired
    private ItemOfficialExtService itemOfficialExtService;
    /**
     * 正式商品仓库服务
     */
    @Autowired
    private WarehouseOfficialService warehouseOfficialService;

    /**
     * 正式商品报关服务
     */
    @Autowired
    private LogisticsCustomsDeclareOfficialService logisticsCustomsDeclareOfficialService;

    /**
     * 正式商品条形码服务
     */
    @Autowired
    private ItemBarcodeOfficialService itemBarcodeOfficialService;

    /**
     * 商品图片服务
     */
    @Autowired
    private ItemFormalImageLibService itemFormalImageLibService;

    /**
     * 正式商品价格
     */
    @Autowired
    private ItemPriceOfficialService itemPriceOfficialService;

    /**
     * 采编服务
     */
    @Autowired
    private EditorService editorService;

    /**
     * 商品产地服务
     */
    @Autowired
    private ItemOriginService itemOriginService;

    /**
     * 计量单位服务
     */
    @Autowired
    private LogisticsUnitService logisticsUnitService;

    /**
     * 类目与属性组关联服务
     */
    @Autowired
    private CategoryAttributeGroupService categoryAttributeGroupService;

    /**
     * 属性服务
     */
    @Autowired
    private AttributeService attributeService;

    /**
     * 正式商品列表
     *
     * @param model 模型
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/item/official/list")
    public String listItemOfficial(Model model) throws MsOnionException {
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

            // 上下架状态
            model.addAttribute("itemOfficialStatusMap", ItemOfficialStatusUtils.getMap(ItemOfficialStatusUtils.ITEM_OFFICIAL_WAIT_ONLINE,
                    ItemOfficialStatusUtils.ITEM_OFFICIAL_ONLINE, ItemOfficialStatusUtils.ITEM_OFFICIAL_OFFLINE));

            // 销售状态
            model.addAttribute("itemSaleStatusMap", MsOnionSaleStatusUtils.getMap());

            // 品牌集合
            List<CollectorItemBrand> collectorItemBrandList = itemBrandService.getAllItemBrand(apiVersion);
            model.addAttribute("collectorItemBrandList", collectorItemBrandList);
            return "/item/official/listItemOfficial";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取正式商品列表
     *
     * @param request 请求
     * @param page    当前页码
     * @param rows    每页记录数量
     * @return 列表、总数
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/item/official/grid")
    public Map<String, Object> gridItemOfficial(HttpServletRequest request, String page, String rows) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            int pageNum = MsOnionRegexUtils.checkDigit(page) ? Integer.parseInt(page) : MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            int pageSize = MsOnionRegexUtils.checkDigit(rows) ? Integer.parseInt(rows) : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE;

            ItemOfficialExt itemOfficialExt = new ItemOfficialExt();
            ItemOfficial itemOfficial = new ItemOfficial();
            ItemBasicOfficial itemBasicOfficial = new ItemBasicOfficial();

            // 搜索条件 Begin
            String itemOfficialIdx = request.getParameter("itemOfficialIdx");
            if (MsOnionStringUtils.isNotBlank(itemOfficialIdx)) {
                Long idx = Long.valueOf(itemOfficialIdx);
                itemOfficial.setIdx(idx);
            }

            String itemCollectorIdx = request.getParameter("itemCollectorIdx");
            if (MsOnionStringUtils.isNotBlank(itemCollectorIdx)) {
                Long itemIdx = Long.valueOf(itemCollectorIdx);
                itemOfficial.setItemIdx(itemIdx);
            }

            String itemOfficialCnName = request.getParameter("itemOfficialCnName");
            if (MsOnionStringUtils.isNotBlank(itemOfficialCnName)) {
                itemBasicOfficial.setCnName(itemOfficialCnName);
            }

            String itemOfficialItemNo = request.getParameter("itemOfficialItemNo");
            if (MsOnionStringUtils.isNotBlank(itemOfficialItemNo)) {
                itemBasicOfficial.setItemNo(itemOfficialItemNo);
            }

            String itemOfficialBrandIdx = request.getParameter("itemOfficialBrandIdx");
            if (MsOnionStringUtils.isNotBlank(itemOfficialBrandIdx)) {
                Long brandIdx = Long.valueOf(itemOfficialBrandIdx);
                itemBasicOfficial.setBrandIdx(brandIdx);
            }

            String itemOfficialBatch = request.getParameter("itemOfficialBatch");
            if (MsOnionStringUtils.isNotBlank(itemOfficialBatch)) {
                Integer batch = Integer.valueOf(itemOfficialBatch);
                itemBasicOfficial.setBatch(batch);
            }

            String itemOfficialWeight = request.getParameter("itemOfficialWeight");
            if (MsOnionStringUtils.isNotBlank(itemOfficialWeight)) {
                Long weight = Long.valueOf(itemOfficialWeight);
                itemBasicOfficial.setWeight(weight);
            }

            // 第一品类、第二品类、第三品类搜索处理
            String itemOfficialFirstCategoryIdx = request.getParameter("itemOfficialFirstCategoryIdx");
            String itemOfficialSecondCategoryIdx = request.getParameter("itemOfficialSecondCategoryIdx");
            String itemOfficialThreeCategoryIdx = request.getParameter("itemOfficialThreeCategoryIdx");
            if (MsOnionStringUtils.isNotBlank(itemOfficialFirstCategoryIdx)
                    || MsOnionStringUtils.isNotBlank(itemOfficialSecondCategoryIdx)
                    || MsOnionStringUtils.isNotBlank(itemOfficialThreeCategoryIdx)) {
                ItemCommonQuery itemCommonQuery = new ItemCommonQuery(itemCategoryService);
                List<Long> categoryIdxList = itemCommonQuery.getCategoryIdxList(itemOfficialFirstCategoryIdx,
                        itemOfficialSecondCategoryIdx, itemOfficialThreeCategoryIdx);
                if (MsOnionCollectionUtils.isNotEmpty(categoryIdxList)) {
                    itemOfficialExt.setCategoryIdxList(categoryIdxList);
                }
            }

            String itemOfficialOnlineStatus = request.getParameter("itemOfficialOnlineStatus");
            if (MsOnionStringUtils.isNotBlank(itemOfficialOnlineStatus)) {
                Short status = Short.valueOf(itemOfficialOnlineStatus);
                itemOfficial.setStatus(status);
            }

            String itemOfficialItemStateIdx = request.getParameter("itemOfficialItemStateIdx");
            if (MsOnionStringUtils.isNotBlank(itemOfficialItemStateIdx)) {
                Long itemStateIdx = Long.valueOf(itemOfficialItemStateIdx);
                itemBasicOfficial.setItemStateIdx(itemStateIdx);
            }
            // 搜索条件 End

            // 排序
            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                if (ManagerConstants.ITEM_IDX.equals(sort)) {
                    orderBy = ManagerConstants.ITEM_OFFICIAL_TABLE_ALIAS + sort.trim() + ManagerConstants.SPACE + order.trim();
                } else {
                    orderBy = ManagerConstants.ITEM_BASIC_OFFICIAL_TABLE_ALIAS + sort.trim() + ManagerConstants.SPACE + order.trim();
                }
            } else {
                orderBy = ManagerConstants.ITEM_OFFICIAL_TABLE_DEFAULT_ORDER;
            }

            // 设置搜索条件
            itemOfficialExt.setItemOfficial(itemOfficial);
            itemOfficialExt.setItemBasicOfficial(itemBasicOfficial);
            itemOfficialExt.setOrderByClause(MsOnionStringUtils.getCorrectOrderByForDB(orderBy));
            itemOfficialExt.setPageNum(pageNum);
            itemOfficialExt.setPageSize(pageSize);

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = itemOfficialExtService.queryListByPageForResult(apiVersion, itemOfficialExt);
            if (null != resultAdapter) {
                MsOnionPagingResult pagingResult = (MsOnionPagingResult) resultAdapter;

                long total = pagingResult.getTotalCounts();
                List<ItemOfficialExt> itemOfficialExtList = (List<ItemOfficialExt>) pagingResult.getData();

                List<Map<String, Object>> list = new ArrayList<>();
                Iterator<ItemOfficialExt> it = itemOfficialExtList.iterator();
                while (it.hasNext()) {
                    ItemOfficialExt next = it.next();
                    Map<String, Object> temp = getItemOfficialShowMap(apiVersion, next);
                    list.add(temp);
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
     * 获取正式商品显示Map
     *
     * @param apiVersion Api版本
     * @param next       正式商品扩展信息
     * @return 返回正式商品显示Map
     * @throws MsOnionException 自定义异常
     */
    private Map<String, Object> getItemOfficialShowMap(MsOnionApiVersion apiVersion, ItemOfficialExt next) throws MsOnionException {
        try {
            ItemOfficial nextItemOfficial = next.getItemOfficial();
            Long idx = nextItemOfficial.getIdx();

            Map<String, Object> temp = new HashMap<>();
            temp.put("idx", idx);
            temp.put("itemIdx", nextItemOfficial.getItemIdx());
            temp.put("onlineStatus", ItemOfficialStatusUtils.getDescription(nextItemOfficial.getStatus()));

            ItemBasicOfficial nextItemBasicOfficial = itemBasicOfficialService.queryOneBySourceIdx(apiVersion, idx);
            if (null != nextItemBasicOfficial) {
                temp.put("cnName", nextItemBasicOfficial.getCnName());
                temp.put("itemNo", nextItemBasicOfficial.getItemNo());
                temp.put("batch", nextItemBasicOfficial.getBatch());
                temp.put("weight", nextItemBasicOfficial.getWeight());
                temp.put("saleStatus", MsOnionSaleStatusUtils.getDescription(nextItemBasicOfficial.getItemStateIdx()));

                // 类目
                Long categoryIdx = nextItemBasicOfficial.getCategoryIdx();
                if (null != categoryIdx && categoryIdx != 0) {
                    CollectorItemCategory itemCategory = itemCategoryService.queryByPrimaryKey(apiVersion, categoryIdx);
                    if (null != itemCategory) {
                        temp.put("categoryName", itemCategory.getName());
                    }
                }

                // 品牌
                Long brandIdx = nextItemBasicOfficial.getBrandIdx();
                if (null != brandIdx && brandIdx != 0) {
                    CollectorItemBrand itemBrand = itemBrandService.queryByPrimaryKey(apiVersion, brandIdx);
                    if (null != itemBrand) {
                        temp.put("brandName", itemBrand.getBrandName());
                    }
                }
            }

            // 初次上架人、初次上架时间
            Long firstOnlineByMemberIdx = nextItemOfficial.getFirstOnlineByMemberIdx();
            if (null != firstOnlineByMemberIdx && firstOnlineByMemberIdx != 0) {
                Member member = memberService.queryByPrimaryKey(apiVersion, firstOnlineByMemberIdx);
                if (null != member) {
                    temp.put("firstOnlineByMemberName", member.getName());
                    temp.put("firstOnlineTime", nextItemOfficial.getFirstOnlineTimeEnYyyyMMddHHmmss());
                }
            }

            // 上架人、上架时间
            Long onlineByMemberIdx = nextItemOfficial.getOnlineByMemberIdx();
            if (null != onlineByMemberIdx && onlineByMemberIdx != 0) {
                Member member = memberService.queryByPrimaryKey(apiVersion, onlineByMemberIdx);
                if (null != member) {
                    temp.put("onlineByMemberName", member.getName());
                    temp.put("onlineTime", nextItemOfficial.getOnlineTimeEnYyyyMMddHHmmss());
                }
            }

            // 下架人、下架时间
            Long offlineByMemberIdx = nextItemOfficial.getOfflineByMemberIdx();
            if (null != offlineByMemberIdx && offlineByMemberIdx != 0) {
                Member member = memberService.queryByPrimaryKey(apiVersion, offlineByMemberIdx);
                if (null != member) {
                    temp.put("offlineByMemberName", member.getName());
                    temp.put("offlineTime", nextItemOfficial.getOfflineTimeEnYyyyMMddHHmmss());
                }
            }

            return temp;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 正式商品详情
     *
     * @param idxStr 正式商品主键idx
     * @param model  模型
     * @return 返回详情页
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/item/official/detail/{idxStr}")
    public String detailItemOfficial(@PathVariable String idxStr, Model model) throws MsOnionException {
        if (StringUtils.isBlank(idxStr)) {
            return null;
        }

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        Long idx = Long.valueOf(idxStr);
        ItemBasicOfficial itemBasicOfficial = itemBasicOfficialService.queryOneBySourceIdx(apiVersion, idx);
        model.addAttribute("itemBasicOfficial", itemBasicOfficial);

        if (null != itemBasicOfficial) {
            Long brandIdx = itemBasicOfficial.getBrandIdx();
            CollectorItemBrand itemBrand = itemBrandService.queryByPrimaryKey(apiVersion, brandIdx);
            if (null != itemBrand) {
                model.addAttribute("brandName", itemBrand.getBrandName());
            }

            Long originIdx = itemBasicOfficial.getOriginIdx();
            CollectorItemOrigin itemOrigin = itemOriginService.queryByPrimaryKey(apiVersion, originIdx);
            if (null != itemOrigin) {
                model.addAttribute("originCnName", itemOrigin.getCnName());
            }

            Long itemStateIdx = itemBasicOfficial.getItemStateIdx();
            if (null != itemStateIdx && itemStateIdx != 0) {
                String saleStatus = MsOnionSaleStatusUtils.getDescription(itemStateIdx);
                model.addAttribute("saleStatus", saleStatus);
            }
        }

        ItemCollectionOfficial itemCollectionOfficial = itemCollectionOfficialService.queryOneBySourceIdx(apiVersion, idx);
        model.addAttribute("itemCollectionOfficial", itemCollectionOfficial);

        if (null != itemCollectionOfficial) {
            Long editorIdx = itemCollectionOfficial.getEditorIdx();
            if (null != editorIdx && editorIdx != 0) {
                CollectorItemEditor itemEditor = editorService.queryByPrimaryKey(apiVersion, editorIdx);
                if (null != itemEditor) {
                    model.addAttribute("editorName", itemEditor.getName());
                }
            }
        }

        ItemOfficial itemOfficial = itemOfficialService.queryByPrimaryKey(apiVersion, idx);

        // 属性
        MsOnionResult result = ItemOfficialAttributeUtils.getItemAttributeAndValue(apiVersion, itemService,
                categoryAttributeGroupService, itemAttributeOfficialService,
                attributeGroupAttributeService, attributeService, itemOfficial.getItemIdx(), idx);
        if (MsOnionStatusConstants.STATUS_200 == result.getStatus()) {
            List<Map<String, Object>> attributeList = (List<Map<String, Object>>) result.getData();
            if (null != attributeList && attributeList.size() > 0) {
                for (Map<String, Object> itemAttribute : attributeList) {
                    // 关键属性图片
                    List<ItemAttributeImageOfficial> keyAttributeImages = itemAttributeImageOfficialService.getItemAttributeImage(apiVersion,
                            itemOfficial.getIdx(), (Long) itemAttribute.get("attributeIdx"));
                    if (keyAttributeImages != null && keyAttributeImages.size() > 0) {
                        for (ItemAttributeImageOfficial keyAttributeImage : keyAttributeImages) {
                            String imagePath = getImgurl() + keyAttributeImage.getImageMiddle();
                            keyAttributeImage.setImageMiddleDynamicS(imagePath);
                        }
                        itemAttribute.put("attributeImages", keyAttributeImages);
                    }
                }
            }
            model.addAttribute("itemAttributeList", attributeList);
        }

        // 报关
        MsOnionResult r1 = logisticsCustomsDeclareOfficialService.getLogisticsCustomsDeclareOfficialByItemOfficialIdx(apiVersion, idx);
        if (MsOnionStatusConstants.STATUS_200 == r1.getStatus()) {
            LogisticsCustomsDeclareOfficial logisticsCustomsDeclareOfficial = (LogisticsCustomsDeclareOfficial) r1.getData();
            Long firstUnitIdx = logisticsCustomsDeclareOfficial.getFirstUnitIdx();
            if (null != firstUnitIdx && firstUnitIdx > 0) {
                LogisticsUnit firstLogisticsUnit = logisticsUnitService.queryByPrimaryKey(apiVersion, firstUnitIdx);
                if (null != firstLogisticsUnit) {
                    logisticsCustomsDeclareOfficial.setFirstUnitIdxDynamicS(firstLogisticsUnit.getName());
                }
            }

            Long secondUnitIdx = logisticsCustomsDeclareOfficial.getSecondUnitIdx();
            if (null != secondUnitIdx && secondUnitIdx > 0) {
                LogisticsUnit secondLogisticsUnit = logisticsUnitService.queryByPrimaryKey(apiVersion, secondUnitIdx);
                if (null != secondLogisticsUnit) {
                    logisticsCustomsDeclareOfficial.setSecondUnitIdxDynamicS(secondLogisticsUnit.getName());
                }
            }
            model.addAttribute("logisticsCustomsDeclareOfficial", logisticsCustomsDeclareOfficial);
        }

        // 仓库
        MsOnionResult r2 = warehouseOfficialService.getWarehouseOfficialByItemOfficialIdx(apiVersion, idx);
        if (MsOnionStatusConstants.STATUS_200 == r2.getStatus()) {
            WarehouseOfficial warehouseOfficial = (WarehouseOfficial) r2.getData();
            model.addAttribute("warehouseOfficial", warehouseOfficial);
        }

        // 商品图片
        MsOnionResult r3 = itemFormalImageLibService.getItemFormalImagsByItemIdx(apiVersion, itemOfficial.getItemIdx());
        if (MsOnionStatusConstants.STATUS_200 == r3.getStatus()) {
            CollectorItemFormalImageLib itemFormalImageLib = (CollectorItemFormalImageLib) r3.getData();
            // 查询图片
            String imgurl = getImgurl();

            itemFormalImageLib.setWhiteBackgroundImageBigDynamicS(MsOnionImageUtils.splitImgFiled(imgurl,
                    itemFormalImageLib.getWhiteBackgroundImageBig()));

            itemFormalImageLib.setMainPushImageBigDynamicS(MsOnionImageUtils.splitImgFiled(imgurl, itemFormalImageLib.getMainPushImageBig()));

            itemFormalImageLib.setDetailPageMainImageBigDynamicS(MsOnionImageUtils.splitImgFiled(imgurl,
                    itemFormalImageLib.getDetailPageMainImageBig()));
            model.addAttribute("itemFormalImageLib", itemFormalImageLib);
        }

        // 条形码
        MsOnionResult r4 = itemBarcodeOfficialService.getItemBarcodeOfficialByItemOfficalIdx(apiVersion, idx);
        if (MsOnionStatusConstants.STATUS_200 == r4.getStatus()) {
            List<ItemBarcodeOfficial> barcodeList = (List<ItemBarcodeOfficial>) r4.getData();
            model.addAttribute("barcodeList", barcodeList);
        }

        ItemPriceOfficial itemPriceOfficial = itemPriceOfficialService.queryOneByItemOfficialIdx(apiVersion, idx);
        model.addAttribute("itemPriceOfficial", itemPriceOfficial);

        return "/item/official/detailItemOfficial";
    }

    /**
     * 上架正式商品
     *
     * @param ids 正式商品主键idx集合
     * @return 返回上架结果
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/item/official/online")
    public MsOnionResult onlineItemOfficial(@RequestParam(value = "ids[]", required = false, defaultValue = "") String[] ids)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        Member member = CurrentUserUtils.getCurrentUser();
        Long memberIdx = member.getIdx();

        // 检查选中上架的商品中，有无信息未录入完整的
        boolean b = false;
        for (String idxStr : ids) {
            if (StringUtils.isBlank(idxStr)) {
                continue;
            }

            Long idx = Long.parseLong(idxStr);
            boolean isCompleteItemInfo = ItemOfficialOnlineUtils.isCompleteItemInfo(apiVersion, itemCollectionOfficialService,
                    itemPriceOfficialService, logisticsCustomsDeclareOfficialService, warehouseOfficialService, idx);
            if (!isCompleteItemInfo) {
                b = true;
            }
        }
        if (b) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ItemMessageConstants.MESSAGE_ITEM_OFFICIAL_NOT_COMPLETE);
        }

        MsOnionResult result = itemOfficialService.saveBatchOnlineItemOfficial(apiVersion, ids, memberIdx);
        return result;
    }

    /**
     * 下架正式商品
     *
     * @param ids 正式商品主键idx集合
     * @return 返回下架结果
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/item/official/offline")
    public MsOnionResult offlineItemOfficial(@RequestParam(value = "ids[]", required = false, defaultValue = "") String[] ids)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        Member member = CurrentUserUtils.getCurrentUser();
        Long memberIdx = member.getIdx();

        MsOnionResult result = itemOfficialService.saveBatchOfflineItemOfficial(apiVersion, ids, memberIdx);
        return result;
    }
}
