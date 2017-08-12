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
package cc.msonion.carambola.manager.ext.utils;

/**
 * @Title: ItemOfficialPublishUtils.java
 * @Package: cc.msonion.carambola.manager.ext.utils
 * @Description: 正式商品发布工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/6/24 14:41
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/6/24 14:41
 * @Modify-version: V2.0.0
 * @Modify-description: 保存并发布后，存储正式商品数据
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemAttribute;
import cc.msonion.carambola.collector.pojo.CollectorItemAttributeImage;
import cc.msonion.carambola.collector.pojo.CollectorItemCollection;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionEditTypeUtils;
import cc.msonion.carambola.item.pojo.*;
import cc.msonion.carambola.item.service.*;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ItemOfficialPublishUtils
 * @Description: 正式商品发布工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/6/24 14:41
 */
public final class ItemOfficialPublishUtils {
    private ItemOfficialPublishUtils() {
    }

    /**
     * 发布商品信息
     *
     * @param apiVersion                        Api版本
     * @param itemOfficialService               正式商品服务
     * @param itemBasicOfficialService          正式商品基本信息服务
     * @param itemCollectionOfficialService     正式商品采集信息服务
     * @param itemAttributeImageOfficialService 正式商品属性图片服务
     * @param itemAttributeOfficialService      正式商品属性值服务
     * @param item                              商品信息
     * @param itemCollection                    商品采集信息
     * @param attributeImages                   关键属性图片
     * @param attributes                        属性值
     * @return 返回发布结果
     * @throws MsOnionException 自定义异常
     */
    public static MsOnionResult publishItemCollectionInfo(MsOnionApiVersion apiVersion,
                                                          ItemOfficialService itemOfficialService,
                                                          ItemBasicOfficialService itemBasicOfficialService,
                                                          ItemCollectionOfficialService itemCollectionOfficialService,
                                                          ItemAttributeImageOfficialService itemAttributeImageOfficialService,
                                                          ItemAttributeOfficialService itemAttributeOfficialService,
                                                          CollectorItem item,
                                                          CollectorItemCollection itemCollection,
                                                          List<CollectorItemAttributeImage> attributeImages,
                                                          List<CollectorItemAttribute> attributes)
            throws MsOnionException {
        Long itemIdx = item.getIdx();

        MsOnionResult result = itemOfficialService.getItemOfficialByItemIdx(apiVersion, itemIdx);
        if (null == result.getData()) {
            ItemOfficial itemOfficial = new ItemOfficial();
            itemOfficial.setItemIdx(itemIdx);
            itemOfficial.setCreateByMemberIdx(itemCollection.getCreateByMemberIdx());
            itemOfficial.setUpdateByMemberIdx(itemCollection.getUpdateByMemberIdx());

            result = itemOfficialService.addItemOfficial(apiVersion, itemOfficial);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        ItemOfficial itemOfficial = (ItemOfficial) result.getData();
        Long itemOfficialIdx = itemOfficial.getIdx();

        // 发布商品信息
        result = publishItem(apiVersion, itemBasicOfficialService, itemOfficialIdx, item);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        // 发布商品采集信息
        result = publishItemCollection(apiVersion, itemCollectionOfficialService, itemOfficialIdx, itemCollection);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        // 发布商品属性值信息
        if (attributes != null && attributes.size() > 0) {
            List<ItemAttributeOfficial> itemAttributeOfficials = new ArrayList<>();
            for (CollectorItemAttribute collectorItemAttribute : attributes) {
                ItemAttributeOfficial itemAttributeOfficial = new ItemAttributeOfficial();
                itemAttributeOfficial.setAttributeIdx(collectorItemAttribute.getAttributeIdx());
                itemAttributeOfficial.setAttributeValue(collectorItemAttribute.getAttributeValue());
                itemAttributeOfficial.setCreateByMemberIdx(itemCollection.getCreateByMemberIdx());
                itemAttributeOfficial.setUpdateByMemberIdx(itemCollection.getUpdateByMemberIdx());
                itemAttributeOfficial.setItemOfficialIdx(itemOfficialIdx);
                itemAttributeOfficials.add(itemAttributeOfficial);
            }

            result = itemAttributeOfficialService.saveItemAttributeOfficial(apiVersion, itemOfficialIdx, itemAttributeOfficials);
            if (MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                return result;
            }
        }

        // 发布关键属性图片信息
        if (attributeImages != null && attributeImages.size() > 0) {
            List<ItemAttributeImageOfficial> itemAttributeImageOfficialList = new ArrayList<>();
            for (CollectorItemAttributeImage attributeImage : attributeImages) {
                ItemAttributeImageOfficial itemAttributeImage = new ItemAttributeImageOfficial();
                itemAttributeImage.setAttributeIdx(attributeImage.getAttributeIdx());
                itemAttributeImage.setCreateByMemberIdx(itemCollection.getCreateByMemberIdx());
                itemAttributeImage.setUpdateByMemberIdx(itemCollection.getUpdateByMemberIdx());
                itemAttributeImage.setEditType(MsOnionEditTypeUtils.EDIT_SECOND);
                itemAttributeImage.setImageBig(attributeImage.getImageBig());
                itemAttributeImage.setImageMiddle(attributeImage.getImageMiddle());
                itemAttributeImage.setImageSmall(attributeImage.getImageSmall());
                itemAttributeImage.setItemOfficialIdx(itemOfficialIdx);
                itemAttributeImage.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                itemAttributeImageOfficialList.add(itemAttributeImage);
            }

            result = itemAttributeImageOfficialService.addItemAttributeImageOfficial(apiVersion, itemAttributeImageOfficialList);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
    }

    /**
     * 发布商品基本信息
     *
     * @param apiVersion               Api版本
     * @param itemBasicOfficialService 正式商品基本信息服务
     * @param itemOfficialIdx          正式商品主键idx
     * @param item                     商品信息
     * @return 返回发布结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    public static MsOnionResult publishItem(MsOnionApiVersion apiVersion, ItemBasicOfficialService itemBasicOfficialService,
                                            Long itemOfficialIdx, CollectorItem item)
            throws MsOnionIllegalArgumentException, MsOnionException {
        boolean addItemItem = false;
        ItemBasicOfficial itemBasicOfficial = itemBasicOfficialService.queryOneBySourceIdx(apiVersion, itemOfficialIdx);
        if (null == itemBasicOfficial) {
            itemBasicOfficial = new ItemBasicOfficial();
            itemBasicOfficial.setItemOfficialIdx(itemOfficialIdx);

            addItemItem = true;
        }

        itemBasicOfficial.setItemNo(item.getItemNo());
        itemBasicOfficial.setBarcode(item.getBarcode());
        itemBasicOfficial.setCnName(item.getCnName());
        itemBasicOfficial.setEnName(item.getEnName());
        itemBasicOfficial.setBrandIdx(item.getBrandIdx());
        itemBasicOfficial.setOriginIdx(item.getOriginIdx());
        itemBasicOfficial.setCategoryIdx(item.getCategoryIdx());
        itemBasicOfficial.setSpecification(item.getSpecification());
        itemBasicOfficial.setWarehouseTypeIdx(item.getWarehouseTypeIdx());
        itemBasicOfficial.setBatch(item.getBatch());
        itemBasicOfficial.setWeight(item.getWeight());
        itemBasicOfficial.setItemStateIdx(item.getItemStateIdx());
        itemBasicOfficial.setCollectionStatus(item.getCollectionStatus());
        itemBasicOfficial.setCollectionRemark(item.getCollectionRemark());
        itemBasicOfficial.setCreateByMemberIdx(item.getCreateByMemberIdx());
        itemBasicOfficial.setUpdateByMemberIdx(item.getUpdateByMemberIdx());

        return addItemItem ? itemBasicOfficialService.addItemItem(apiVersion, itemBasicOfficial)
                : itemBasicOfficialService.updateItemItem(apiVersion, itemBasicOfficial);
    }

    /**
     * 发布正式商品采集信息
     *
     * @param apiVersion                    Api版本
     * @param itemCollectionOfficialService 正式商品采集信息服务
     * @param itemOfficialIdx               正式商品主键idx
     * @param itemCollection                商品采集信息
     * @return 返回发布结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    public static MsOnionResult publishItemCollection(MsOnionApiVersion apiVersion, ItemCollectionOfficialService itemCollectionOfficialService,
                                                      Long itemOfficialIdx, CollectorItemCollection itemCollection)
            throws MsOnionIllegalArgumentException, MsOnionException {
        boolean addItemItemCollection = false;
        ItemCollectionOfficial itemCollectionOfficial = itemCollectionOfficialService.queryOneBySourceIdx(apiVersion, itemOfficialIdx);
        if (null == itemCollectionOfficial) {
            itemCollectionOfficial = new ItemCollectionOfficial();
            itemCollectionOfficial.setItemOfficialIdx(itemOfficialIdx);

            addItemItemCollection = true;
        }

        itemCollectionOfficial.setSellingPoint(itemCollection.getSellingPoint());
        itemCollectionOfficial.setSlogan(itemCollection.getSlogan());
        itemCollectionOfficial.setShoppingGuide(itemCollection.getShoppingGuide());
        itemCollectionOfficial.setEditorIdx(itemCollection.getEditorIdx());
        itemCollectionOfficial.setSearchKeywords(itemCollection.getSearchKeywords());
        itemCollectionOfficial.setVideoLink(itemCollection.getVideoLink());
        itemCollectionOfficial.setEditType(itemCollection.getEditType());
        itemCollectionOfficial.setCreateByMemberIdx(itemCollection.getCreateByMemberIdx());
        itemCollectionOfficial.setUpdateByMemberIdx(itemCollection.getUpdateByMemberIdx());

        return addItemItemCollection ? itemCollectionOfficialService.addItemItemCollection(apiVersion, itemCollectionOfficial)
                : itemCollectionOfficialService.updateItemItemCollection(apiVersion, itemCollectionOfficial);
    }
}
