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

import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.utils.*;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.fileresource.interfaces.FileResourceUploadService;
import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import cc.msonion.carambola.item.service.*;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.ext.utils.ItemOfficialPublishUtils;
import cc.msonion.carambola.manager.ext.utils.ItemPrePublishUtil;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import com.alibaba.fastjson.JSON;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName: CollectorController
 * @Description: 收集器Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月12日 下午9:24:42
 */
@Controller
public class ItemCollectionController extends MsOnionBaseAppController {
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
     * 商品采集service
     */
    @Autowired
    private ItemCollectionService itemCollectionService;

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
     * 产地服务
     */
    @Autowired
    private ItemOriginService itemOriginService;

    /**
     * 成员服务
     */
    @Autowired
    private MemberService memberService;

    /**
     * 采编服务
     */
    @Autowired
    private EditorService editorService;

    /**
     * 类目服务
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 属性组服务
     */
    @Autowired
    private AttributeGroupService attributeGroupService;

    /**
     * 属性组属性服务
     */
    @Autowired
    private AttributeGroupAttributeService attributeGroupAttributeService;

    /**
     * 商品属性服务
     */
    @Autowired
    private ItemAttributeService itemAttributeService;

    /**
     * 商品内容服务
     */
    @Autowired
    private ItemContentService itemContentService;

    /**
     * fileResourceUploadService
     */
    @Autowired
    private FileResourceUploadService fileResourceUploadService;

    /**
     * itemFormalImageLibService
     */
    @Autowired
    private ItemFormalImageLibService itemFormalImageLibService;

    /**
     * 属性服务
     */
    @Autowired
    private AttributeService attributeService;

    /**
     * 属性图片服务
     */
    @Autowired
    private ItemAttributeImageService itemAttributeImageService;

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
     * 正式商品服务
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * 正式商品关键属性图片服务
     */
    @Autowired
    private ItemAttributeImageOfficialService itemAttributeImageOfficialService;

    /**
     * 正式商品属性值服务
     */
    @Autowired
    private ItemAttributeOfficialService itemAttributeOfficialService;

    /**
     * 条形码服务
     */
    @Autowired
    private ItemBarcodeService itemBarcodeService;


    /**
     * 商品日志服务
     */
    @Autowired
    private ItemLogService itemLogService;

    /**
     * 采集信息
     *
     * @param model 模型
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/collection/list")
    public String listItem(Model model) throws MsOnionException {
        try {
            Map purchaseStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.PURCHASE_STATUS);
            model.addAttribute("purchaseStatusMap", purchaseStatusMap);
            model.addAttribute("purchaseStatusMapJson", MsOnionJsonUtils.objectToJson(purchaseStatusMap));

            Map collectionStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.COLLECTION_STATUS);
            model.addAttribute("collectionStatusMap", collectionStatusMap);
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

            return "/collector/item/collection/listCollection";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 二次编辑
     *
     * @param model 模型
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/collection/list2")
    public String listItem2(Model model) throws MsOnionException {
        try {
            Map purchaseStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.PURCHASE_STATUS);
            model.addAttribute("purchaseStatusMap", purchaseStatusMap);
            model.addAttribute("purchaseStatusMapJson", MsOnionJsonUtils.objectToJson(purchaseStatusMap));

            Map collectionStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.COLLECTION_STATUS);
            model.addAttribute("collectionStatusMap", collectionStatusMap);
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

            return "/collector/item/collection/listTwiceEdit";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑采编信息
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/item/collection/edit/{idxStr}")
    public String editItem(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isBlank(idxStr)) {
            return "/collector/item/collection/editCollection";
        }

        try {
            Long itemIdx = Long.parseLong(idxStr);
            MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);

            if (null != result.getData()) {
                CollectorItem item = (CollectorItem) result.getData();
                // 查询图片
                item.setImageBigDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), item.getImageBig()));

                request.setAttribute("item", item);

                // 采集相册图片
                request.setAttribute("imgurl", MsOnionConstants.ALBUM_FORMAL_PATH_START + MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR
                        + item.getIdx()
                        + MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR + item.getItemNo()
                        + MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR);


                // 属性值
                MsOnionResult rs = itemAttributeService.getItemAttributeByItemIdxAndEditType(apiVersion, itemIdx, MsOnionEditTypeUtils.EDIT_FIRST);
                if (null != rs.getData()) {
                    List<Map<String, Object>> itemAttributeList = (List<Map<String, Object>>) rs.getData();
                    itemAttributeList = this.getAttributeImage(apiVersion, itemIdx, itemAttributeList);
                    request.setAttribute("itemAttributeList", itemAttributeList);
                }

                // 条形码列表
                List<CollectorItemBarcode> itemBarcodeList = this.getCollectorBarcodes(apiVersion, item.getBarcode(), itemIdx);
                request.setAttribute("itemBarcodeList", itemBarcodeList);

                this.getMsOnionLogger().info(this.getClass().getName(), "\n\nitemBarcodeList" + JSON.toJSONString(itemBarcodeList));

            }

            // 品牌列表
            List<CollectorItemBrand> itemBrandList = itemBrandService.getAllItemBrand(apiVersion);
            request.setAttribute("itemBrandList", itemBrandList);

            // 产地列表
            List<CollectorItemOrigin> itemOriginList = itemOriginService.getAllItemOrigin(apiVersion);
            request.setAttribute("itemOriginList", itemOriginList);

            // 采集状态
            Map collectionStatusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.COLLECTION_STATUS);
            request.setAttribute("collectionStatusMap", collectionStatusMap);

            // 销售状态
            Map saleStatusMap = MsOnionSaleStatusUtils.getMap();
            request.setAttribute("saleStatusMap", saleStatusMap);

            // 竞价信息
            result = itemBiddingService.getItemBiddingByItemIdx(apiVersion, itemIdx);
            if (null != result.getData()) {
                CollectorItemBidding itemBidding = (CollectorItemBidding) result.getData();
                request.setAttribute("itemBidding", itemBidding);
            }

            // 采集信息
            result = itemCollectionService.getItemCollectionByItemIdx(apiVersion, itemIdx);
            if (null != result.getData()) {
                CollectorItemCollection itemCollection = (CollectorItemCollection) result.getData();
                if (itemCollection.getAttachmentId() > 0L) {
                    FileResourceUpload fr = fileResourceUploadService.getFileMessageId(apiVersion, itemCollection.getAttachmentId() + "");
                    itemCollection.setAttachmentIdDynamicS(Optional.ofNullable(fr)
                            .map(u -> u.getMd5Value()).orElse(null));
                    request.setAttribute("downAttachUrl", fr == null ? "" : getImgurl() + fr.getPath());
                }
                request.setAttribute("itemCollection", itemCollection);
            }

            // 内容信息
            result = itemContentService.getItemContentByItemIdx(apiVersion, itemIdx);
            if (null != result.getData()) {
                CollectorItemContent itemContent = (CollectorItemContent) result.getData();
                request.setAttribute("itemContent", itemContent);
            }

            // 采编列表
            result = editorService.getAllItemEditor(apiVersion);
            if (null != result.getData()) {
                List<CollectorItemEditor> itemEditorList = (List<CollectorItemEditor>) result.getData();
                request.setAttribute("itemEditorList", itemEditorList);
            }

            // 获取系统设置附件大小
            String maxAttachmentSize = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_ATTACHMENT_SIZE);
            request.setAttribute("maxAttachmentSize", maxAttachmentSize);
            return "/collector/item/collection/editCollection";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 二次编辑信息
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/item/collection/edit2/{idxStr}")
    public String editItem2(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            Long itemIdx = Long.parseLong(idxStr);
            MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);
            if (null != result.getData()) {
                CollectorItem item = (CollectorItem) result.getData();
                request.setAttribute("item", item);

                // 正式相册图片
                request.setAttribute("imgurl", MsOnionConstants.ALBUM_FORMAL_PATH_START
                        + MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR + item.getIdx()
                        + MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR + item.getItemNo());
                // 查询正式图片
                MsOnionResult formalImagsResult = itemFormalImageLibService.getItemFormalImagsByItemIdx(apiVersion, itemIdx);
                if (null != formalImagsResult.getData()) {
                    CollectorItemFormalImageLib itemFormalImageLib = (CollectorItemFormalImageLib) formalImagsResult.getData();
                    // 查询图片
                    String imgurl = getImgurl();

                    itemFormalImageLib.setWhiteBackgroundImageBigDynamicS(MsOnionImageUtils.splitImgFiled(imgurl,
                            itemFormalImageLib.getWhiteBackgroundImageBig()));

                    itemFormalImageLib.setMainPushImageBigDynamicS(MsOnionImageUtils.splitImgFiled(imgurl, itemFormalImageLib.getMainPushImageBig()));

                    itemFormalImageLib.setDetailPageMainImageBigDynamicS(MsOnionImageUtils.splitImgFiled(imgurl,
                            itemFormalImageLib.getDetailPageMainImageBig()));

                    request.setAttribute("itemFormalImages", itemFormalImageLib);
                }

                // 属性值
                MsOnionResult rs = itemAttributeService.getItemAttributeByItemIdxAndEditType(apiVersion, itemIdx, MsOnionEditTypeUtils.EDIT_SECOND);
                if (null != rs.getData()) {
                    List<Map<String, Object>> itemAttributeList = (List<Map<String, Object>>) rs.getData();
                    itemAttributeList = this.getAttributeImage(apiVersion, itemIdx, itemAttributeList);
                    request.setAttribute("itemAttributeList", itemAttributeList);
                }


                // 条形码列表
                List<CollectorItemBarcode> itemBarcodeList = this.getCollectorBarcodes(apiVersion, item.getBarcode(), itemIdx);
                request.setAttribute("itemBarcodeList", itemBarcodeList);
            }

            // 品牌列表
            List<CollectorItemBrand> itemBrandList = itemBrandService.getAllItemBrand(apiVersion);
            request.setAttribute("itemBrandList", itemBrandList);

            // 产地列表
            List<CollectorItemOrigin> itemOriginList = itemOriginService.getAllItemOrigin(apiVersion);
            request.setAttribute("itemOriginList", itemOriginList);

            // 二次采集信息
            result = itemCollectionService.getItemTwiceCollectionByItemIdx(apiVersion, itemIdx);
            if (null != result.getData()) {
                CollectorItemCollection itemCollection = (CollectorItemCollection) result.getData();
                request.setAttribute("itemCollection", itemCollection);
            } else {
                MsOnionResult rs = itemCollectionService.getItemCollectionByItemIdx(apiVersion, itemIdx);
                if (null != rs.getData()) {
                    CollectorItemCollection itemCollection = (CollectorItemCollection) rs.getData();
                    request.setAttribute("itemCollection", itemCollection);
                }
            }

            // 采编列表
            result = editorService.getAllItemEditor(apiVersion);
            if (null != result.getData()) {
                List<CollectorItemEditor> itemEditorList = (List<CollectorItemEditor>) result.getData();
                request.setAttribute("itemEditorList", itemEditorList);
            }

            // 销售状态
            Map saleStatusMap = MsOnionSaleStatusUtils.getMap();
            request.setAttribute("saleStatusMap", saleStatusMap);

            return "/collector/item/collection/editTwiceEdit";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 查看采集信息
     *
     * @param request 请求
     * @param idxStr  商品主键idx
     * @return 返回查看页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/item/collection/detail/{idxStr}")
    public String detailCollection(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isBlank(idxStr)) {
            return "/collector/item/collection/detailCollection";
        }

        try {
            Long itemIdx = Long.parseLong(idxStr);
            MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);

            if (null != result.getData()) {
                CollectorItem item = (CollectorItem) result.getData();
                request.setAttribute("item", item);

                // 属性值
                MsOnionResult rs = itemAttributeService.getItemAttributeByItemIdxAndEditType(apiVersion, itemIdx,
                        MsOnionEditTypeUtils.EDIT_SECOND);
                if (null != rs.getData()) {
                    List<Map<String, Object>> itemAttributeList = (List<Map<String, Object>>) rs.getData();
                    if (itemAttributeList.size() < 1) {
                        rs = itemAttributeService.getItemAttributeByItemIdxAndEditType(apiVersion, itemIdx,
                                MsOnionEditTypeUtils.EDIT_FIRST);
                        if (null != rs) {
                            itemAttributeList = (List<Map<String, Object>>) rs.getData();
                        }
                    }
                    itemAttributeList = this.getAttributeImage(apiVersion, itemIdx, itemAttributeList);
                    request.setAttribute("itemAttributeList", itemAttributeList);
                }

                // 条形码列表
                List<CollectorItemBarcode> itemBarcodeList = this.getCollectorBarcodes(apiVersion, item.getBarcode(), itemIdx);
                request.setAttribute("itemBarcodeList", itemBarcodeList);

                // 品牌名称
                Long brandIdx = item.getBrandIdx();
                rs = itemBrandService.getItemBrandByIdx(apiVersion, brandIdx);
                if (null != rs.getData()) {
                    CollectorItemBrand itemBrand = (CollectorItemBrand) rs.getData();
                    request.setAttribute("brandName", itemBrand.getBrandName());
                }

                // 产地名称
                Long originIdx = item.getOriginIdx();
                rs = itemOriginService.getItemOriginByIdx(apiVersion, originIdx);
                if (null != rs.getData()) {
                    CollectorItemOrigin itemOrigin = (CollectorItemOrigin) rs.getData();
                    request.setAttribute("originCnName", itemOrigin.getCnName());
                }

                // 采集状态
                Short collectionStatus = item.getCollectionStatus();
                request.setAttribute("collectionStatus", MsOnionCollectionStatusUtils.getDescription(collectionStatus));

                // 竞价信息
                rs = itemBiddingService.getItemBiddingByItemIdx(apiVersion, itemIdx);
                if (null != rs.getData()) {
                    CollectorItemBidding itemBidding = (CollectorItemBidding) rs.getData();
                    request.setAttribute("itemBidding", itemBidding);
                }

                // 采集信息
                rs = itemCollectionService.getItemCollectionByItemIdx(apiVersion, itemIdx);
                if (null != rs.getData()) {
                    CollectorItemCollection itemCollection = (CollectorItemCollection) rs.getData();
                    request.setAttribute("itemCollection", itemCollection);

                    // 采编信息
                    Long editorIdx = itemCollection.getEditorIdx();
                    rs = editorService.getItemEditorByIdx(apiVersion, editorIdx);
                    if (null != rs.getData()) {
                        CollectorItemEditor itemEditor = (CollectorItemEditor) rs.getData();
                        request.setAttribute("editorName", itemEditor.getName());
                    }

                    // 附件
                    if (itemCollection.getAttachmentId() > 0L) {
                        FileResourceUpload fr = fileResourceUploadService.getFileMessageId(apiVersion, itemCollection.getAttachmentId() + "");
                        itemCollection.setAttachmentIdDynamicS(Optional.ofNullable(fr)
                                .map(u -> u.getMd5Value()).orElse(null));
                        request.setAttribute("downAttachUrl", getImgurl() + (fr == null ? "" : fr.getPath()));
                    }
                }

                // 内容信息
                result = itemContentService.getItemContentByItemIdx(apiVersion, itemIdx);
                if (null != result.getData()) {
                    CollectorItemContent itemContent = (CollectorItemContent) result.getData();
                    request.setAttribute("itemContent", itemContent);
                }


            }

            return "/collector/item/collection/detailCollection";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存采集信息
     *
     * @param request             请求
     * @param biddingIdx          商品竞价主键idx
     * @param collectionIdx       商品采集主键idx
     * @param contentIdx          商品内容主键idx
     * @param item                商品信息
     * @param itemBidding         商品竞价信息
     * @param itemCollection      商品采集信息
     * @param itemContent         商品内容信息
     * @param itemAttributeValues 属性值集合
     * @param itemAttributeImages 关键属性图片信息
     * @return 返回保存结果
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping(value = "/collect/item/collection/save", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult saveItemCollection(HttpServletRequest request, String biddingIdx, String collectionIdx, String contentIdx,
                                            CollectorItem item, CollectorItemBidding itemBidding, CollectorItemCollection itemCollection,
                                            CollectorItemContent itemContent, String itemAttributeValues, String itemAttributeImages)
            throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            if (StringUtils.isNotBlank(biddingIdx)) {
                // 取名biddingIdx，避免与idx冲突
                Long bIdx = Long.valueOf(biddingIdx);
                itemBidding.setIdx(bIdx);
            }

            if (StringUtils.isNotBlank(collectionIdx)) {
                // 取名collectionIdx，避免与idx冲突
                Long cIdx = Long.valueOf(collectionIdx);
                itemCollection.setIdx(cIdx);
            }

            if (StringUtils.isNotBlank(contentIdx)) {
                // 取名contentIdx，避免与idx冲突
                Long cIdx = Long.valueOf(contentIdx);
                itemContent.setIdx(cIdx);
            }

            // 商品竞价价格信息
            String amazonPriceStr = request.getParameter("amazonPriceStr");
            if (StringUtils.isNotBlank(amazonPriceStr)) {
                int amazonPrice = MsOnionNumberUtils.toIntForPrice(amazonPriceStr);
                itemBidding.setAmazonPrice(amazonPrice);
            }

            String tmallPriceStr = request.getParameter("tmallPriceStr");
            if (StringUtils.isNotBlank(tmallPriceStr)) {
                int tmallPrice = MsOnionNumberUtils.toIntForPrice(tmallPriceStr);
                itemBidding.setTmallPrice(tmallPrice);
            }

            String jdPriceStr = request.getParameter("jdPriceStr");
            if (StringUtils.isNotBlank(jdPriceStr)) {
                int jdPrice = MsOnionNumberUtils.toIntForPrice(jdPriceStr);
                itemBidding.setJdPrice(jdPrice);
            }

            String redPriceStr = request.getParameter("redPriceStr");
            if (StringUtils.isNotBlank(redPriceStr)) {
                int redPrice = MsOnionNumberUtils.toIntForPrice(redPriceStr);
                itemBidding.setRedPrice(redPrice);
            }

            String abroadPriceStr = request.getParameter("abroadPriceStr");
            if (StringUtils.isNotBlank(abroadPriceStr)) {
                int abroadPrice = MsOnionNumberUtils.toIntForPrice(abroadPriceStr);
                itemBidding.setAbroadPrice(abroadPrice);
            }

            String domesticPriceStr = request.getParameter("domesticPriceStr");
            if (StringUtils.isNotBlank(domesticPriceStr)) {
                int domesticPrice = MsOnionNumberUtils.toIntForPrice(domesticPriceStr);
                itemBidding.setDomesticPrice(domesticPrice);
            }

            String originCountryPriceStr = request.getParameter("originCountryPriceStr");
            if (StringUtils.isNotBlank(originCountryPriceStr)) {
                int originCountryPrice = MsOnionNumberUtils.toIntForPrice(originCountryPriceStr);
                itemBidding.setOriginCountryPrice(originCountryPrice);
            }

            String koalaPriceStr = request.getParameter("koalaPriceStr");
            if (StringUtils.isNotBlank(koalaPriceStr)) {
                int koalaPrice = MsOnionNumberUtils.toIntForPrice(koalaPriceStr);
                itemBidding.setKoalaPrice(koalaPrice);
            }

            String jumeiPriceStr = request.getParameter("jumeiPriceStr");
            if (StringUtils.isNotBlank(jumeiPriceStr)) {
                int jumeiPrice = MsOnionNumberUtils.toIntForPrice(jumeiPriceStr);
                itemBidding.setJumeiPrice(jumeiPrice);
            }

            Member currentUser = CurrentUserUtils.getCurrentUser();

            // 商品设置修改成员
            item.setUpdateByMemberIdx(currentUser.getIdx());

            // 商品竞价设置修改成员
            itemBidding.setUpdateByMemberIdx(currentUser.getIdx());

            // 商品采集设置编辑类型，创建成员，修改成员
            itemCollection.setEditType(MsOnionEditTypeUtils.EDIT_FIRST);
            itemCollection.setCreateByMemberIdx(currentUser.getCreateByMemberIdx());
            itemCollection.setUpdateByMemberIdx(currentUser.getUpdateByMemberIdx());

            // 商品内容设置创建成员，修改成员
            itemContent.setCreateByMemberIdx(currentUser.getCreateByMemberIdx());
            itemContent.setUpdateByMemberIdx(currentUser.getUpdateByMemberIdx());

            // 属性值集合解密
            List<CollectorItemAttribute> itemAttributeList = new ArrayList<>();
            if (MsOnionStringUtils.isNotBlank(itemAttributeValues)) {
                String json = MsOnionSecurityBase64Utils.decode(itemAttributeValues);
                itemAttributeList = MsOnionJsonUtils.jsonToList(json, CollectorItemAttribute.class);
            }

            // 关键属性图片信息处理
            List<CollectorItemAttributeImage> itemAttributeImageList = new ArrayList<>();
            if (StringUtils.isNotBlank(itemAttributeImages)) {
                String json = MsOnionSecurityBase64Utils.decode(itemAttributeImages);
                itemAttributeImageList = MsOnionJsonUtils.jsonToList(json, CollectorItemAttributeImage.class);
                if (itemAttributeImageList != null && itemAttributeImageList.size() > 0) {
                    for (CollectorItemAttributeImage attributeImage : itemAttributeImageList) {
                        attributeImage.setImageSmall(attributeImage.getImageBig());
                        attributeImage.setImageMiddle(attributeImage.getImageBig());
                        attributeImage.setCreateByMemberIdx(currentUser.getIdx());
                        attributeImage.setUpdateByMemberIdx(currentUser.getIdx());
                        attributeImage.setItemIdx(Long.valueOf(request.getParameter("itemIdx")));
                        attributeImage.setEditType(MsOnionEditTypeUtils.EDIT_SECOND);
                    }
                }
            }

            MsOnionResult result = itemCollectionService.saveItemCollection(apiVersion, item, itemBidding,
                    itemCollection, itemContent, itemAttributeList, itemAttributeImageList);
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
    }

    /**
     * 保存二次编辑信息
     *
     * @param request                     请求
     * @param collectionIdx               商品采集主键idx
     * @param item                        商品信息
     * @param itemCollection              商品采集信息
     * @param itemAttributeValues         属性值集合信息
     * @param collectorItemFormalImageLib 正式图片
     * @param itemAttributeImages         关键属性图片信息
     * @return 返回保存结果
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collect/item/collection/save2")
    public MsOnionResult secondCollection(HttpServletRequest request, String collectionIdx, CollectorItem item,
                                          CollectorItemCollection itemCollection, String itemAttributeValues,
                                          CollectorItemFormalImageLib collectorItemFormalImageLib,
                                          String itemAttributeImages) throws MsOnionException {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            if (StringUtils.isNotBlank(collectionIdx)) {
                // 取名collectionIdx，避免与idx冲突
                Long cIdx = Long.valueOf(collectionIdx);
                itemCollection.setIdx(cIdx);
            }

            Member currentUser = CurrentUserUtils.getCurrentUser();
            item.setCreateByMemberIdx(currentUser.getIdx());
            item.setUpdateByMemberIdx(currentUser.getIdx());
            itemCollection.setEditType(MsOnionEditTypeUtils.EDIT_SECOND);
            itemCollection.setCreateByMemberIdx(currentUser.getCreateByMemberIdx());
            itemCollection.setUpdateByMemberIdx(currentUser.getUpdateByMemberIdx());

            // 属性值集合解密
            List<CollectorItemAttribute> itemAttributeList = new ArrayList<>();
            if (MsOnionStringUtils.isNotBlank(itemAttributeValues)) {
                String json = MsOnionSecurityBase64Utils.decode(itemAttributeValues);
                itemAttributeList = MsOnionJsonUtils.jsonToList(json, CollectorItemAttribute.class);
            }

            collectorItemFormalImageLib.setCreateByMemberIdx(currentUser.getCreateByMemberIdx());
            collectorItemFormalImageLib.setUpdateByMemberIdx(currentUser.getCreateByMemberIdx());

            // 保存类型，保存：1，保存并发布：2
            String saveType = request.getParameter("saveType");

            List<CollectorItemAttributeImage> itemAttributeImageList = new ArrayList<>();
            if (StringUtils.isNotBlank(itemAttributeImages)) {
                String json = MsOnionSecurityBase64Utils.decode(itemAttributeImages);
                itemAttributeImageList = MsOnionJsonUtils.jsonToList(json, CollectorItemAttributeImage.class);
                if (itemAttributeImageList != null && itemAttributeImageList.size() > 0) {
                    for (CollectorItemAttributeImage attributeImage : itemAttributeImageList) {
                        attributeImage.setImageSmall(attributeImage.getImageBig());
                        attributeImage.setImageMiddle(attributeImage.getImageBig());
                        attributeImage.setCreateByMemberIdx(currentUser.getIdx());
                        attributeImage.setUpdateByMemberIdx(currentUser.getIdx());
                        attributeImage.setItemIdx(Long.valueOf(request.getParameter("itemIdx")));
                        attributeImage.setEditType(MsOnionEditTypeUtils.EDIT_SECOND);
                    }
                }
            }
            CollectorItem originObject = itemService.queryByPrimaryKey(apiVersion, item.getIdx());
            itemCollection.setPublishStatus("2".equals(saveType) ? MsOnionPublishStatusUtils.PUBLISH_PUBLISHED
                    : MsOnionPublishStatusUtils.PUBLISH_NOT_PUBLISH);
            MsOnionResult result = itemCollectionService.saveItemTwiceCollection(apiVersion, item, itemCollection,
                    itemAttributeList, collectorItemFormalImageLib, itemAttributeImageList);

            if ("2".equals(saveType)) {
                if (result.getStatus() == MsOnionStatusConstants.STATUS_200) {
                    // 预发布商品信息补充
                    Long idx = item.getIdx();
                    CollectorItem ci = itemService.queryByPrimaryKey(apiVersion, idx);
                    item.setItemNo(ci.getItemNo());
                    item.setBarcode(ci.getBarcode());
                    item.setCategoryIdx(ci.getCategoryIdx());
                    item.setWarehouseTypeIdx(ci.getWarehouseTypeIdx());
                    item.setCollectionStatus(ci.getCollectionStatus());

                    // 保存商品发布操作日志
                    List<CollectorItemLog> collectorItemLogList = ItemPrePublishUtil.genPubListLogs(originObject, item, currentUser.getIdx());
                    itemLogService.batchSaveItemLog(apiVersion, collectorItemLogList, currentUser.getIdx());

                    // 发布商品
                    result = ItemOfficialPublishUtils.publishItemCollectionInfo(
                            apiVersion, itemOfficialService,
                            itemBasicOfficialService, itemCollectionOfficialService,
                            itemAttributeImageOfficialService, itemAttributeOfficialService,
                            item, itemCollection, itemAttributeImageList, itemAttributeList);
                }
            }
            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
    }

    /**
     * 获取条形码
     *
     * @param apiVersion 版本
     * @param barcode    商品表自带的条形码
     * @param itemIdx    商品idx
     * @return 条形码列表
     * @throws MsOnionException 自定义异常
     */
    private List<CollectorItemBarcode> getCollectorBarcodes(MsOnionApiVersion apiVersion, String barcode,
                                                            Long itemIdx) throws MsOnionException {
        List<CollectorItemBarcode> itemBarcodeList = new ArrayList<>();
        if (StringUtils.isNotBlank(barcode)) {
            CollectorItemBarcode itemBarcode = new CollectorItemBarcode();
            itemBarcode.setBarcode(barcode);
            itemBarcodeList.add(itemBarcode);
        }
        // 从条形码表查询
        List<CollectorItemBarcode> barcodes = itemBarcodeService.getItemBarcodeList(apiVersion, itemIdx);
        itemBarcodeList.addAll(barcodes);
        return itemBarcodeList;
    }

    /**
     * 获取关键属性图片
     *
     * @param apiVersion        版本
     * @param itemIdx           商品idx
     * @param itemAttributeList 属性列表
     * @return 属性列表
     * @throws MsOnionException 自定义异常
     */
    private List<Map<String, Object>> getAttributeImage(MsOnionApiVersion apiVersion, Long itemIdx,
                                                        List<Map<String, Object>> itemAttributeList) throws MsOnionException {
        if (null != itemAttributeList && itemAttributeList.size() > 0) {
            for (Map<String, Object> itemAttribute : itemAttributeList) {
                // 关键属性图片
                List<CollectorItemAttributeImage> keyAttributeImages = itemAttributeImageService.getItemAttributeImage(apiVersion,
                        itemIdx, (Long) itemAttribute.get("attributeIdx"));
                if (keyAttributeImages != null && keyAttributeImages.size() > 0) {
                    for (CollectorItemAttributeImage keyAttributeImage : keyAttributeImages) {
                        String imagePath = getImgurl() + keyAttributeImage.getImageMiddle();
                        keyAttributeImage.setImageMiddleDynamicS(imagePath);
                    }
                    itemAttribute.put("attributeImages", keyAttributeImages);
                }
            }
        }
        return itemAttributeList;
    }
}
