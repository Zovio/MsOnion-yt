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
 * @Title: ResourceDownloadControlller.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 资源下载controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月25日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月25日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.utils.MsOnionSaleStatusUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.item.common.utils.ItemOfficialStatusUtils;
import cc.msonion.carambola.item.ext.pojo.ItemOfficialExt;
import cc.msonion.carambola.item.pojo.*;
import cc.msonion.carambola.item.service.*;
import cc.msonion.carambola.item.service.ext.ItemOfficialExtService;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareOfficialService;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.controller.collector.common.ItemCommonQuery;
import cc.msonion.carambola.manager.web.controller.ext.ResourceExporterUtils;
import cc.msonion.carambola.manager.web.controller.ext.ResponseUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.common.utils.concurrent.MsOnionThreadPoolUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficial;
import cc.msonion.carambola.warehouse.service.WarehouseOfficialService;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @ClassName: ResourceDownloadControlller
 * @Description: 资源下载controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月25日
 */
@Controller
public class ResourceDownloadControlller extends MsOnionBaseAppController {

    /**
     * 类目服务
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 正式商品拓展服务
     */
    @Autowired
    private ItemOfficialExtService itemOfficialExtService;


    /**
     * 商品品牌服务
     */
    @Autowired
    private ItemBrandService itemBrandService;

    /**
     * 商品属性服务
     */
    @Autowired
    private ItemAttributeService itemAttributeService;

    /**
     * 正式商品属性值服务
     */
    @Autowired
    private ItemAttributeOfficialService itemAttributeOfficialService;

    /**
     * 正式商品关键属性图片
     */
    @Autowired
    private ItemAttributeImageOfficialService itemAttributeImageOfficialService;


    /**
     * memberService
     */
    @Autowired
    private MemberService memberService;

    /**
     * 产地 service
     */
    @Autowired
    private ItemOriginService itemOriginService;

    /**
     * 正式商品服务
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * 正式商品基本信息服务
     */
    @Autowired
    private ItemBasicOfficialService itemBasicOfficialService;

    /**
     * 正式商品采集信息服务
     */
    @Autowired
    private ItemCollectionOfficialService itemCollectionOfficialService;

    /**
     * 正式商品条形码服务
     */
    @Autowired
    private ItemBarcodeOfficialService itemBarcodeOfficialService;

    /**
     * editorService
     */
    @Autowired
    private EditorService editorService;

    /**
     * logisticsCustomsDeclareOfficialService
     */
    @Autowired
    private LogisticsCustomsDeclareOfficialService logisticsCustomsDeclareOfficialService;

    /**
     * warehouseOfficialService
     */
    @Autowired
    private WarehouseOfficialService warehouseOfficialService;

    /**
     * itemPriceOfficialService
     */
    @Autowired
    private ItemPriceOfficialService itemPriceOfficialService;

    /**
     * 属性服务
     */
    @Autowired
    private AttributeService attributeService;

    /**
     * 资源下载
     *
     * @param req   请求对象
     * @param res   响应对象
     * @param model model实体
     * @return 页面
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/download/home")
    public String home(HttpServletRequest req, HttpServletResponse res, Model model) throws MsOnionException {
        String confStr = MsOnionPropertiesUtils.loadConf(getMsOnionDomain().getEnvironment(), ManagerConstants.REASOURCE_DOWNLOAD_FILE);
        JSONArray jSONArray = MsOnionJsonUtils.jsonToPojo(confStr, JSONArray.class);
        model.addAttribute("JSONArray", jSONArray);

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


        return "/collector/download/home";
    }


    /**
     * 导出数据
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws MsOnionException 异常
     */
    @RequestMapping(value = "/collect/download/exportDate", method = RequestMethod.POST)
    public void exportDate(HttpServletRequest request, HttpServletResponse response) throws MsOnionException {
        String keyArr = request.getParameter("keyArr");
        String nameArr = request.getParameter("nameArr");

        // 是否勾选了属性值
        String itemAttribute = request.getParameter("itemAttribute");

        keyArr = MsOnionSecurityBase64Utils.decode(keyArr);
        nameArr = MsOnionSecurityBase64Utils.decode(nameArr);
        keyArr = MessageConstants.DEFAULT_ATTR_STR + (keyArr == null ? "" : keyArr);
        nameArr = MessageConstants.DEFAULT_ATTR_NAME + (nameArr == null ? "" : nameArr);

        List<String> keyList = Arrays.asList(keyArr.split(","));

        // 文件名
        String fileName = "资源文件" + MsOnionDateUtils.formatYyyyMMddHHmmssUnity(new Date()) + ".xls";
        ResponseUtils.setDownLoadResponse(request, response, fileName);
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            // 获取查询参数
            ItemOfficialExt itemOfficialExt = this.getSearchParams(request);

            boolean isNeedAttribute = MsOnionConstants.YES.equals(itemAttribute);
            // 如果勾选了属性值复选框，就组装属性表头
            if (isNeedAttribute) {
                String attrName = this.getAttributeName(apiVersion, itemOfficialExt.getCategoryIdxList().get(0));
                if (nameArr.endsWith(",")) {
                    nameArr = nameArr + attrName;
                } else {
                    nameArr = nameArr + ',' + attrName;
                }
            }

            osw = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
            bw = new BufferedWriter(osw);

            bw.write(ResourceExporterUtils.getReportHead(Arrays.asList(nameArr.split(",")), fileName.substring(0, fileName.indexOf("."))));
            bw.flush();
            // 获取查询所有的数据总数
            Long totalCount = itemOfficialExtService.getCount(apiVersion, itemOfficialExt);

            int pageNo = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE;
            String pageSizeStr = getSysValueBySetKey(MsOnionSysSetCodeConstants.DOWN_PAGESIZE);
            int pageSize =  StringUtils.isNotBlank(pageSizeStr) ? Integer.valueOf(pageSizeStr)
                    : MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE;

            Long totalPage = this.getTotalPage(pageSize, totalCount);

            if (null != totalPage && totalPage > 0) {
                for (int i = 1; i <= totalPage; i++) {
                    itemOfficialExt.setPageNum(i);
                    itemOfficialExt.setPageSize(pageSize);

                    String row = this.getExcelRow(apiVersion, keyList, itemOfficialExt, isNeedAttribute);
                    bw.write(row);
                }
            }

            bw.write(ResourceExporterUtils.getReportTail());
            bw.flush();
            response.flushBuffer();
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e, "## 资源下载失败....");
        } finally {
            IOUtils.closeQuietly(bw);
            IOUtils.closeQuietly(osw);
        }

    }

    /**
     * 筛选按钮查询商品数量
     *
     * @param request 请求
     * @return 商品数量
     */
    @RequestMapping("/collect/download/getItemCount")
    @ResponseBody
    public MsOnionResult getItemCount(HttpServletRequest request) {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 获取查询参数
            ItemOfficialExt itemOfficialExt = this.getSearchParams(request);

            Map<String, Object> data = new HashMap<>();
            Long totalCount = itemOfficialExtService.getCount(apiVersion, itemOfficialExt);

            data.put("totalCount", totalCount);

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, data);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, e.getMessage());
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_QUERY_FAILURE);
        }
    }

    /**
     * 获取商品基本信息
     *
     * @param apiVersion                    版本号
     * @param list                          页面要导出的字段对象属性list
     * @param itemOfficialIdx               正式商品idx
     * @param itemBasicOfficialService      正式商品基础服务
     * @param itemBrandService              商品品牌服务
     * @param itemOriginService             商品产地服务
     * @param itemCategoryService           商品分类服务
     * @param itemCollectionOfficialService 正式商品采集服务
     * @param memberService                 成员服务
     * @param itemBarcodeOfficialService    正式商品条形码
     * @return List<String>
     */
    private List<String> getItemBasicOfficial(List<String> list, MsOnionApiVersion apiVersion, Long itemOfficialIdx,
                                              ItemBasicOfficialService itemBasicOfficialService, ItemBrandService itemBrandService,
                                              ItemOriginService itemOriginService,
                                              ItemCategoryService itemCategoryService,
                                              ItemCollectionOfficialService itemCollectionOfficialService, MemberService memberService,
                                              ItemBarcodeOfficialService itemBarcodeOfficialService) {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            try {
                String key = list.get(i);
                String[] keyArr = key.split("\\|");
                String pojoAttr = keyArr[0];
                String pojoClazz = keyArr[1];
                if (pojoClazz.equals(ItemBasicOfficial.class.getName())) {
                    // 条形码集合
                    List<String> barcodeList = new ArrayList<>();
                    // 查询 正式商品基础信息 （中文品名、外文品名、产地、品牌、类目、批次、商品规格、权重、销售状态、条形码、销售状态）
                    ItemBasicOfficial itemBasicOfficial = itemBasicOfficialService.queryOneBySourceIdx(apiVersion, itemOfficialIdx);
                    if (itemBasicOfficial == null) {
                        dataList.add("");
                        continue;
                    }
                    if ("barcode".equals(pojoAttr)) {
                        // 查询条形码
                        barcodeList.add(itemBasicOfficial.getBarcode());
                        MsOnionResult r1 = itemBarcodeOfficialService.getItemBarcodeOfficialByItemOfficalIdx(apiVersion, itemOfficialIdx);
                        if (r1.getStatus() == MsOnionStatusConstants.STATUS_200) {
                            List<ItemBarcodeOfficial> temList = (List<ItemBarcodeOfficial>) r1.getData();
                            List<String> barcodes = temList.stream().map(ItemBarcodeOfficial::getBarcode).collect(Collectors.toList());
                            barcodeList.addAll(barcodes);
                        }
                        dataList.add(StringUtils.join(barcodeList, ","));
                        continue;
                    }
                    // 品牌
                    if ("brandIdx".equals(pojoAttr)) {
                        CollectorItemBrand brand = itemBrandService.queryByPrimaryKey(apiVersion, itemBasicOfficial.getBrandIdx());
                        dataList.add(brand == null ? "" : brand.getBrandName());
                        continue;
                    }
                    // 产地
                    if ("originIdx".equals(pojoAttr)) {
                        CollectorItemOrigin origin = itemOriginService.queryByPrimaryKey(apiVersion, itemBasicOfficial.getOriginIdx());
                        dataList.add(origin == null ? "" : origin.getCnName());
                        continue;
                    }
                    // 类目
                    if ("categoryIdx".equals(pojoAttr)) {
                        CollectorItemCategory category = itemCategoryService.queryByPrimaryKey(apiVersion, itemBasicOfficial.getCategoryIdx());
                        dataList.add(category == null ? "" : category.getName());
                        continue;
                    }

                    // 销售状态
                    if ("itemStateIdx".equals(pojoAttr)) {
                        String description = MsOnionSaleStatusUtils.getDescription(itemBasicOfficial.getItemStateIdx());
                        dataList.add(description == null ? "" : description);
                        continue;
                    }

                    Field field = ReflectionUtils.findField(itemBasicOfficial.getClass(), pojoAttr);
                    ReflectionUtils.makeAccessible(field);
                    dataList.add(field.get(itemBasicOfficial) == null ? "" : field.get(itemBasicOfficial).toString());
                }
                if (pojoClazz.equals(ItemOfficial.class.getName())) {
                    // 查询正式商品信息 （商品状态、初次上架人、初次上架时间、上架人、上架时间、下架人、下架时间）
                    ItemOfficial itemOfficial = itemOfficialService.queryByPrimaryKey(apiVersion, itemOfficialIdx);
                    if (itemOfficial == null) {
                        dataList.add("");
                        continue;
                    }
                    // 初次上架人
                    if ("firstOnlineByMemberIdx".equals(pojoAttr)) {
                        if (itemOfficial.getFirstOnlineByMemberIdx() == 0L) {
                            dataList.add("");
                            continue;
                        }
                        Member member = memberService.queryByPrimaryKey(apiVersion, itemOfficial.getFirstOnlineByMemberIdx());
                        dataList.add(member == null ? "" : member.getName());
                        continue;
                    }
                    // 初次上架时间
                    if ("firstOnlineTimeEnYyyyMMddHHmmss".equals(pojoAttr)) {
                        dataList.add(itemOfficial.getFirstOnlineByMemberIdx() == 0L ? "" : itemOfficial.getFirstOnlineTimeEnYyyyMMddHHmmss());
                        continue;
                    }

                    // 上架人
                    if ("onlineByMemberIdx".equals(pojoAttr)) {
                        if (itemOfficial.getOnlineByMemberIdx() == 0L) {
                            dataList.add("");
                            continue;
                        }
                        Member member = memberService.queryByPrimaryKey(apiVersion, itemOfficial.getOnlineByMemberIdx());
                        dataList.add(member == null ? "" : member.getName());
                        continue;
                    }
                    // 初次上架时间
                    if ("onlineTimeEnYyyyMMddHHmmss".equals(pojoAttr)) {
                        dataList.add(itemOfficial.getOnlineByMemberIdx() == 0L ? "" : itemOfficial.getOnlineTimeEnYyyyMMddHHmmss());
                        continue;
                    }
                    // 下架人
                    if ("offlineByMemberIdx".equals(pojoAttr)) {
                        if (itemOfficial.getOfflineByMemberIdx() == 0L) {
                            dataList.add("");
                            continue;
                        }
                        Member member = memberService.queryByPrimaryKey(apiVersion, itemOfficial.getOfflineByMemberIdx());
                        dataList.add(member == null ? "" : member.getName());
                        continue;
                    }
                    // 下架时间
                    if ("offlineTimeEnYyyyMMddHHmmss".equals(pojoAttr)) {
                        dataList.add(itemOfficial.getOfflineByMemberIdx() == 0L ? "" : itemOfficial.getOfflineTimeEnYyyyMMddHHmmss());
                        continue;
                    }
                    // 商品状态
                    if ("status".equals(pojoAttr)) {
                        String description = ItemOfficialStatusUtils.getDescription(itemOfficial.getStatus());
                        dataList.add(description);
                        continue;
                    }
                    Field field = ReflectionUtils.findField(itemOfficial.getClass(), pojoAttr);
                    ReflectionUtils.makeAccessible(field);
                    dataList.add(field.get(itemOfficial) == null ? "" : field.get(itemOfficial).toString());
                }

                if (pojoClazz.equals(ItemCollectionOfficial.class.getName())) {
                    // 查询正式商品采集信息 （视频链接）
                    if ("videoLink".equals(pojoAttr)) {
                        ItemCollectionOfficial itemCollectionOfficial = itemCollectionOfficialService.queryOneBySourceIdx(apiVersion,
                                itemOfficialIdx);
                        dataList.add(itemCollectionOfficial == null ? "" : itemCollectionOfficial.getVideoLink());
                        continue;
                    }
                }
            } catch (MsOnionException | IllegalArgumentException | IllegalAccessException e) {
                getMsOnionLogger().error(getClass().getName(), e, " ### 组装商品基础信息失败，商品idx= " + itemOfficialIdx);
                dataList.add("");
            }
        }
        return dataList;
    }


    /**
     * 获取商品采集采编信息
     *
     * @param apiVersion                    版本号
     * @param list                          页面要导出的字段对象属性list
     * @param itemOfficialIdx               正式商品idx
     * @param itemCollectionOfficialService 正式商品采集服务
     * @param editorService                 采编service
     * @return List<String>
     */
    private List<String> getItemCollectOfficial(List<String> list, MsOnionApiVersion apiVersion, Long itemOfficialIdx,
                                                EditorService editorService, ItemCollectionOfficialService itemCollectionOfficialService) {

        List<String> dataList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            try {
                String key = list.get(i);
                String[] keyArr = key.split("\\|");
                String pojoAttr = keyArr[0];
                String pojoClazz = keyArr[1];

                if (pojoClazz.equals(ItemCollectionOfficial.class.getName())) {
                    ItemCollectionOfficial itemCollectionOfficial = itemCollectionOfficialService.queryOneBySourceIdx(apiVersion, itemOfficialIdx);
                    if (itemCollectionOfficial == null) {
                        dataList.add("");
                        continue;
                    }
                    // 查询正式商品采集信息 （搜索关键词、广告语、卖点、导购语）
                    if ("searchKeywords".equals(pojoAttr)) {
                        dataList.add(itemCollectionOfficial == null ? "" : itemCollectionOfficial.getSearchKeywords());
                        continue;
                    }
                    if ("sellingPoint".equals(pojoAttr)) {
                        dataList.add(itemCollectionOfficial == null ? "" : itemCollectionOfficial.getSellingPoint());
                        continue;
                    }
                    if ("slogan".equals(pojoAttr)) {
                        dataList.add(itemCollectionOfficial == null ? "" : itemCollectionOfficial.getSlogan());
                        continue;
                    }
                    if ("shoppingGuide".equals(pojoAttr)) {
                        dataList.add(itemCollectionOfficial == null ? "" : itemCollectionOfficial.getShoppingGuide());
                        continue;
                    }
                    if ("editorIdx".equals(pojoAttr)) {
                        CollectorItemEditor editor = editorService.queryByPrimaryKey(apiVersion, itemCollectionOfficial.getEditorIdx());
                        dataList.add(editor == null ? "" : editor.getName());
                        continue;
                    }
                }

            } catch (MsOnionException e) {
                getMsOnionLogger().error(getClass().getName(), e, " ### 组装商品采集信息失败，商品idx= " + itemOfficialIdx);
                dataList.add("");
            }
        }
        return dataList;
    }

    /**
     * 获取商品报关信息
     *
     * @param apiVersion                             版本号
     * @param list                                   页面要导出的字段对象属性list
     * @param itemOfficialIdx                        正式商品idx
     * @param logisticsCustomsDeclareOfficialService 正式商品报关服务
     * @return List<String>
     */
    private List<String> getItemCustomsOfficial(List<String> list, MsOnionApiVersion apiVersion, Long itemOfficialIdx,
                                                LogisticsCustomsDeclareOfficialService logisticsCustomsDeclareOfficialService) {
        List<String> dataList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            try {
                String key = list.get(i);
                String[] keyArr = key.split("\\|");
                String pojoAttr = keyArr[0];
                String pojoClazz = keyArr[1];

                if (pojoClazz.equals(LogisticsCustomsDeclareOfficial.class.getName())) {
                    MsOnionResult ret = logisticsCustomsDeclareOfficialService.getLogisticsCustomsDeclareOfficialByItemOfficialIdx(apiVersion,
                            itemOfficialIdx);

                    if (ret.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        dataList.add("");
                        continue;
                    }

                    LogisticsCustomsDeclareOfficial logisticsCustomsDeclareOfficial = (LogisticsCustomsDeclareOfficial) ret.getData();
                    if (logisticsCustomsDeclareOfficial == null) {
                        dataList.add("");
                        continue;
                    }
                    if ("isContainExcise".equals(pojoAttr)) {
                        dataList.add(logisticsCustomsDeclareOfficial == null ? ""
                                : (logisticsCustomsDeclareOfficial.getIsContainExcise() == 1 ? "是" : "否"));
                        continue;
                    }
                    Field field = ReflectionUtils.findField(logisticsCustomsDeclareOfficial.getClass(), pojoAttr);
                    ReflectionUtils.makeAccessible(field);
                    dataList.add(field.get(logisticsCustomsDeclareOfficial) == null ? "" : field.get(logisticsCustomsDeclareOfficial).toString());
                }

            } catch (MsOnionException | IllegalArgumentException | IllegalAccessException e) {
                getMsOnionLogger().error(getClass().getName(), e, " ### 组装商品报关信息失败，商品idx= " + itemOfficialIdx);
                dataList.add("");
            }
        }
        return dataList;
    }

    /**
     * 获取商品库存信息
     *
     * @param apiVersion               版本号
     * @param list                     页面要导出的字段对象属性list
     * @param itemOfficialIdx          正式商品idx
     * @param warehouseOfficialService 正式商品库存服务
     * @return List<String>
     */
    private List<String> getItemWarehouseOfficial(List<String> list, MsOnionApiVersion apiVersion, Long itemOfficialIdx,
                                                  WarehouseOfficialService warehouseOfficialService) {
        List<String> dataList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            try {
                String key = list.get(i);
                String[] keyArr = key.split("\\|");
                String pojoAttr = keyArr[0];
                String pojoClazz = keyArr[1];

                if (pojoClazz.equals(WarehouseOfficial.class.getName())) {
                    MsOnionResult ret = warehouseOfficialService.getWarehouseOfficialByItemOfficialIdx(apiVersion, itemOfficialIdx);

                    if (ret.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        dataList.add("");
                        continue;
                    }

                    WarehouseOfficial warehouseOfficial = (WarehouseOfficial) ret.getData();
                    if (warehouseOfficial == null) {
                        dataList.add("");
                        continue;
                    }
                    if ("isKeyOrder".equals(pojoAttr)) {
                        dataList.add(warehouseOfficial == null ? "" : (warehouseOfficial.getIsKeyOrder() == 1 ? "是" : "否"));
                        continue;
                    }
                    if ("isFreeShipping".equals(pojoAttr)) {
                        dataList.add(warehouseOfficial == null ? "" : (warehouseOfficial.getIsFreeShipping() == 1 ? "是" : "否"));
                        continue;
                    }
                    Field field = ReflectionUtils.findField(warehouseOfficial.getClass(), pojoAttr);
                    ReflectionUtils.makeAccessible(field);
                    dataList.add(field.get(warehouseOfficial) == null ? "" : field.get(warehouseOfficial).toString());
                }

            } catch (MsOnionException | IllegalArgumentException | IllegalAccessException e) {
                getMsOnionLogger().error(getClass().getName(), e, " ### 组装商品库存信息失败，商品idx= " + itemOfficialIdx);
                dataList.add("");
            }
        }
        return dataList;
    }

    /**
     * 获取商品价格信息
     *
     * @param apiVersion               版本号
     * @param list                     页面要导出的字段对象属性list
     * @param itemOfficialIdx          正式商品idx
     * @param itemPriceOfficialService 正式商品库存服务
     * @return List<String>
     */
    private List<String> getItemPriceOfficial(List<String> list, MsOnionApiVersion apiVersion, Long itemOfficialIdx,
                                              ItemPriceOfficialService itemPriceOfficialService) {
        List<String> dataList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            try {
                String key = list.get(i);
                String[] keyArr = key.split("\\|");
                String pojoAttr = keyArr[0];
                String pojoClazz = keyArr[1];

                if (pojoClazz.equals(ItemPriceOfficial.class.getName())) {
                    ItemPriceOfficial itemPriceOfficial = itemPriceOfficialService.queryOneByItemOfficialIdx(apiVersion, itemOfficialIdx);
                    if (itemPriceOfficial == null) {
                        dataList.add("");
                        continue;
                    }

                    if ("marketPriceDots2".equals(pojoAttr)) {
                        dataList.add(itemPriceOfficial.getMarketPriceDots2());
                        continue;
                    }
                    if ("sellingPriceDots2".equals(pojoAttr)) {
                        dataList.add(itemPriceOfficial.getSellingPriceDots2());
                        continue;
                    }
                    if ("supplyPriceDots2".equals(pojoAttr)) {
                        dataList.add(itemPriceOfficial.getSupplyPriceDots2());
                        continue;
                    }

//                    Field field = ReflectionUtils.findField(itemPriceOfficial.getClass(), pojoAttr);
//                    ReflectionUtils.makeAccessible(field);
//                    dataList.add(field.get(itemPriceOfficial) == null ? "" : field.get(itemPriceOfficial).toString());
                }

            } catch (MsOnionException | IllegalArgumentException  e) {
                getMsOnionLogger().error(getClass().getName(), e, " ### 组装商品价格信息失败，商品idx= " + itemOfficialIdx);
                dataList.add("");
            }
        }
        return dataList;
    }

    /**
     * 获取属性和关键属性图片
     *
     * @param apiVersion      版本
     * @param itemOfficialIdx 正式商品idx
     * @param categoryIdx     类目idx
     * @return 属性列表
     * @throws MsOnionException 自定义异常
     */
    private List<String> getItemAttribute(MsOnionApiVersion apiVersion, Long itemOfficialIdx, Long categoryIdx)
            throws MsOnionException {

        if (null == categoryIdx) {
            return null;
        }

        // 获取属性
        MsOnionResult result = itemAttributeService.getItemAttributeByCategoryIdx(apiVersion, categoryIdx);
        if (null == result || MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
            return null;
        }

        List<String> attributeStrs = new ArrayList<>();

        List<Map<String, Object>> attributes = (List<Map<String, Object>>) result.getData();
        if (attributes != null && attributes.size() > 0) {
            for (Map<String, Object> attribute : attributes) {
                StringBuffer sbAttr = new StringBuffer();

                Long attributeIdx = (Long) attribute.get("attributeIdx");

                // 获取属性值
                result = itemAttributeOfficialService.getItemAttributeValue(apiVersion, itemOfficialIdx, attributeIdx);

                ItemAttributeOfficial attributeOfficial = (ItemAttributeOfficial) result.getData();
                if (null == attributeOfficial || StringUtils.isBlank(attributeOfficial.getAttributeValue())) {
                    sbAttr.append("");
                } else {
                    Boolean isSelect = (Boolean) attribute.get("isSelect");
                    if (null != isSelect && isSelect && MsOnionNumberUtils.isDigits(attributeOfficial.getAttributeValue())) {
                        result = attributeService.getAttributeByIdx(apiVersion, Long.valueOf(attributeOfficial.getAttributeValue()));
                        if (null != result.getData()) {
                            CollectorAttribute selectedAttributeValue = (CollectorAttribute) result.getData();
                            sbAttr.append(selectedAttributeValue.getName());
                        }
                    } else {
                        sbAttr.append(attributeOfficial.getAttributeValue());
                    }
                }

                // 获取关键属性图片
                List<ItemAttributeImageOfficial> attributeImageOfficialList =
                        itemAttributeImageOfficialService.getItemAttributeImage(apiVersion, itemOfficialIdx, attributeIdx);

                if (null != attributeImageOfficialList && attributeImageOfficialList.size() > 0) {
                    StringBuffer sbImage = new StringBuffer();
                    for (ItemAttributeImageOfficial attributeImageOfficial : attributeImageOfficialList) {
                        sbImage.append(attributeImageOfficial.getImageMiddle()); // 取中等图的路径
                        sbImage.append(",");  // 多个图片用英文逗号拼接
                    }

                    if (sbImage.length() > 0) {
                        sbImage.deleteCharAt(sbImage.length() - 1);
                    }

                    // 拼接关键属性图片
                    if (sbAttr.length() > 0) {
                        sbAttr.append(", ");
                        sbAttr.append(sbImage);
                    } else {
                        sbAttr.append(sbImage);
                    }
                }
                attributeStrs.add(sbAttr.toString());
            }
        }
        return attributeStrs;
    }

    /**
     * 获取属性表头
     *
     * @param apiVersion  版本
     * @param categoryIdx 第三分类
     * @return 属性表头
     * @throws MsOnionException 自定义异常
     */
    private String getAttributeName(MsOnionApiVersion apiVersion, Long categoryIdx) throws MsOnionException {
        // 获取属性
        MsOnionResult result = itemAttributeService.getItemAttributeByCategoryIdx(apiVersion, categoryIdx);
        if (null == result || MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
            return null;
        }

        List<Map<String, Object>> attributes = (List<Map<String, Object>>) result.getData();

        this.getMsOnionLogger().info("fkk", "attributes=" + JSONArray.toJSONString(attributes));

        if (attributes != null && attributes.size() > 0) {
            StringBuffer sbAttr = new StringBuffer();
            for (Map<String, Object> attribute : attributes) {
                sbAttr.append(attribute.get("attributeName"));
                sbAttr.append(',');
            }

            if (sbAttr.length() > 0) {
                sbAttr.deleteCharAt(sbAttr.length() - 1);
            }

            return sbAttr.toString();
        }
        return "";
    }

    /**
     * 验证并组装查询参数
     *
     * @param request 请求
     * @return 查询参数
     * @throws Exception 自定义异常
     */
    private ItemOfficialExt getSearchParams(HttpServletRequest request) throws Exception {
        String searchId = request.getParameter("s_id");
        String cnName = request.getParameter("s_cnName");
        String itemNo = request.getParameter("s_itemNo");
        String searchBrandId = request.getParameter("s_brandId");
        String searchBatch = request.getParameter("s_batch");
        String searchStatus = request.getParameter("s_status");

        // 第一品类、第二品类、第三品类搜索处理
        String searchCategory1 = request.getParameter("s_category1");
        String searchCategory2 = request.getParameter("s_category2");
        String searchCategory3 = request.getParameter("s_category3");

        Long itemIdx = null;
        Short status = null;
        Long brandIdx = null;
        Integer batch = null;

        try {
            if (StringUtils.isNotBlank(searchId)) {
                itemIdx = Long.valueOf(searchId);
            }

            if (StringUtils.isNotBlank(searchStatus)) {
                status = Short.valueOf(searchStatus);
            }

            if (StringUtils.isNotBlank(searchBrandId)) {
                brandIdx = Long.valueOf(searchBrandId);
            }

            if (StringUtils.isNotBlank(searchBatch)) {
                batch = Integer.valueOf(searchBatch);
            }

            if (StringUtils.isNotBlank(searchCategory1)) {
                Long.valueOf(searchCategory1);
            }

            if (StringUtils.isNotBlank(searchCategory2)) {
                Long.valueOf(searchCategory2);
            }

            if (StringUtils.isNotBlank(searchCategory3)) {
                Long.valueOf(searchCategory3);
            }
        } catch (Exception e) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_PARAMETER_CONDITION_ILLEGAL);
        }

        ItemOfficialExt itemOfficialExt = new ItemOfficialExt();

        if (MsOnionStringUtils.isNotBlank(searchCategory1) || MsOnionStringUtils.isNotBlank(searchCategory2)
                || MsOnionStringUtils.isNotBlank(searchCategory3)) {
            ItemCommonQuery itemCommonQuery = new ItemCommonQuery(itemCategoryService);
            List<Long> categoryIdxList = itemCommonQuery.getCategoryIdxList(searchCategory1, searchCategory2, searchCategory3);
            if (MsOnionCollectionUtils.isNotEmpty(categoryIdxList)) {
                itemOfficialExt.setCategoryIdxList(categoryIdxList);
            }
        }

        // 品名 url 解码
        if (StringUtils.isNotBlank(cnName)) {
            cnName = URLDecoder.decode(cnName, "UTF-8");
        }

        this.getMsOnionLogger().info("fkk", "cnName=" + cnName);

        ItemBasicOfficial itemBasicOfficial = new ItemBasicOfficial();
        ItemOfficial itemOfficial = new ItemOfficial();
        itemOfficial.setItemIdx(itemIdx);
        itemOfficial.setStatus(status);
        itemBasicOfficial.setCnName(cnName);
        itemBasicOfficial.setItemNo(itemNo);
        itemBasicOfficial.setBrandIdx(brandIdx);
        itemBasicOfficial.setBatch(batch);
        itemOfficialExt.setItemBasicOfficial(itemBasicOfficial);
        itemOfficialExt.setItemOfficial(itemOfficial);

        return itemOfficialExt;
    }

    /**
     * 查询并组装导出数据
     *
     * @param apiVersion      版本
     * @param keyList         列名
     * @param itemOfficialIdx 正式商品idx
     * @param categoryIdx     第三分类idx
     * @param isNeedAttribute 是否导出属性值
     * @return 数据
     * @throws InterruptedException 终止异常
     * @throws ExecutionException   多线程执行异常
     */
    private String getExcelRow(MsOnionApiVersion apiVersion, List<String> keyList, Long itemOfficialIdx, Long categoryIdx, boolean isNeedAttribute)
            throws InterruptedException, ExecutionException {
        // 查询商品基础信息
        Callable<List<String>> c1 = () -> getItemBasicOfficial(keyList, apiVersion, itemOfficialIdx,
                itemBasicOfficialService, itemBrandService,
                itemOriginService, itemCategoryService,
                itemCollectionOfficialService, memberService, itemBarcodeOfficialService);

        // 查询商品采编采集
        Callable<List<String>> c2 = () -> getItemCollectOfficial(keyList, apiVersion, itemOfficialIdx,
                editorService, itemCollectionOfficialService);

        // 查询报关
        Callable<List<String>> c3 = () -> getItemCustomsOfficial(keyList, apiVersion, itemOfficialIdx,
                logisticsCustomsDeclareOfficialService);

        // 库存
        Callable<List<String>> c4 = () -> getItemWarehouseOfficial(keyList, apiVersion, itemOfficialIdx,
                warehouseOfficialService);

        // 价格
        Callable<List<String>> c5 = () -> getItemPriceOfficial(keyList, apiVersion, itemOfficialIdx,
                itemPriceOfficialService);


        Future<List<String>> f1 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c1);
        Future<List<String>> f2 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c2);
        Future<List<String>> f3 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c3);
        Future<List<String>> f4 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c4);
        Future<List<String>> f5 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c5);

        List<String> dataList = f1.get();
        List<String> list2 = f2.get();
        List<String> list3 = f3.get();
        List<String> list4 = f4.get();
        List<String> list5 = f5.get();

        dataList.addAll(list2);
        dataList.addAll(list3);
        dataList.addAll(list4);
        dataList.addAll(list5);

        // 是否需要属性
        if (isNeedAttribute) {
            // 属性、属性值、关键属性图片
            Callable<List<String>> c6 = () -> getItemAttribute(apiVersion, itemOfficialIdx, categoryIdx);
            Future<List<String>> f6 = MsOnionThreadPoolUtils.getFixedThreadPool().submit(c6);
            List<String> list6 = f6.get();
            dataList.addAll(list6);
        }

        StringBuffer row = new StringBuffer();
        row.append("<tr height=\"20\">");
        for (String data : dataList) {
            row.append("<td x:str>");
            row.append(data);
            row.append("</td>");

        }
        row.append("</tr>");

        return row.toString();
    }

    /**
     * 获取数据
     *
     * @param apiVersion      版本
     * @param keyList         列名
     * @param itemOfficialExt 查询条件
     * @param isNeedAttribute 是否导出属性值
     * @return 数据
     * @throws InterruptedException 终止异常
     * @throws ExecutionException   多线程执行异常
     */
    private String getExcelRow(MsOnionApiVersion apiVersion, List<String> keyList,
                               ItemOfficialExt itemOfficialExt, boolean isNeedAttribute) throws InterruptedException, ExecutionException {
        // 获取数据
        List<ItemOfficialExt> itemOfficialExtList = itemOfficialExtService.selectByPage(apiVersion, itemOfficialExt);
        StringBuffer sbRow = new StringBuffer();
        if (null != itemOfficialExtList && itemOfficialExtList.size() > 0) {
            for (ItemOfficialExt data : itemOfficialExtList) {
                ItemOfficial itemOfficial = data.getItemOfficial();
                if (null != itemOfficial) {
                    Long categoryIdx = null == data.getItemBasicOfficial() ? null : data.getItemBasicOfficial().getCategoryIdx();
                    String excelRow = this.getExcelRow(apiVersion, keyList, itemOfficial.getIdx(),
                            categoryIdx, isNeedAttribute);
                    sbRow.append(excelRow);
                }
            }
        }
        return sbRow.toString();
    }

    /**
     * 获取总页数
     *
     * @param pageSize   每页多少条
     * @param totalCount 总条数
     * @return 总页数
     */
    private Long getTotalPage(int pageSize, Long totalCount) {
        return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    }

}
