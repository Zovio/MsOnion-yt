/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营采集洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际采集直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: ItemCollectionServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemCollection业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午8:21:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月11日 下午8:21:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionCollectionStatusUtils;
import cc.msonion.carambola.commons.common.utils.MsOnionEditTypeUtils;
import cc.msonion.carambola.commons.common.utils.MsOnionPublishStatusUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemCollectionServiceImpl
 * @Description: 商品采集Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午8:21:21
 */
@Service
public class ItemCollectionServiceImpl extends MsOnionServiceExt<CollectorItemCollection, CollectorItemCollectionExample>
        implements ItemCollectionService {
    /**
     * 商品服务
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品历史纪录服务
     */
    @Autowired
    private ItemHistoryService itemHistoryService;

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
     * 商品图片
     */
    @Autowired
    private ItemFormalImageLibService itemFormalImageLibService;

    /**
     * 关键属性图片服务
     */
    @Autowired
    private ItemAttributeImageService itemAttributeImageService;

    /**
     * 检查是否已存在
     *
     * @param apiVersion
     * @param param      参数值
     * @param type       参数
     * @return
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * 新增商品采集
     *
     * @param apiVersion     Api版本
     * @param itemCollection
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addItemCollection(MsOnionApiVersion apiVersion, CollectorItemCollection itemCollection)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (itemCollection == null || itemCollection.getItemIdx() == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        // 校验参数
        String validateStr = MsOnionPojoValidatorUtils.validatePojo(itemCollection);
        if (MsOnionStringUtils.isNotEmpty(validateStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_COLLECTION_IDX_PATH);
        itemCollection.setIdx(idx);
        itemCollection.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        Short editType = itemCollection.getEditType();
        if (MsOnionEditTypeUtils.EDIT_SECOND.equals(editType)) {
            itemCollection.setPublishStatus(MsOnionPublishStatusUtils.PUBLISH_NOT_PUBLISH);
        }

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_COLLECTION_VERSION_PATH);
        itemCollection.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemCollection=" + itemCollection);

        int result = this.save(apiVersion, itemCollection);
        if (result > 0) {
            return MsOnionResult.ok(itemCollection);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteItemCollection
     * @Description 删除商品采集
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult deleteItemCollection(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * @param apiVersion
     * @param itemCollection
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemCollection
     * @Description 更新商品采集
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult updateItemCollection(MsOnionApiVersion apiVersion, CollectorItemCollection itemCollection)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemCollection) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BRAND_NULL);
        }

        // 校验参数
        String validateStr = MsOnionPojoValidatorUtils.validatePojo(itemCollection);
        if (MsOnionStringUtils.isNotEmpty(validateStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
        }

        Long idx = itemCollection.getIdx();

        CollectorItemCollection temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setSellingPoint(itemCollection.getSellingPoint()); // 卖点
        temp.setSlogan(itemCollection.getSlogan()); // 广告语
        temp.setShoppingGuide(itemCollection.getShoppingGuide()); // 导购语
        temp.setEditorIdx(itemCollection.getEditorIdx()); // 采编主键idx
        temp.setSearchKeywords(itemCollection.getSearchKeywords()); // 搜索关键词
        if (itemCollection.getAttachmentId() != null && itemCollection.getAttachmentId() >= 0) {
            temp.setAttachmentId(itemCollection.getAttachmentId()); //附件文件ID
        }
        if (StringUtils.isNotBlank(itemCollection.getVideoLink())) {
            temp.setVideoLink(itemCollection.getVideoLink()); // 视频链接
        }
        if (itemCollection.getPublishStatus() != null) {
            temp.setPublishStatus(itemCollection.getPublishStatus()); // 发布状态
        }
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(itemCollection.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemCollection by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemCollectionByIdx
     * @Description 通过主键idx得到商品采集信息
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult getItemCollectionByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * 获取商品采集信息
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回采集信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemCollectionByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemCollectionExample example = new CollectorItemCollectionExample();
        CollectorItemCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andEditTypeEqualTo(MsOnionEditTypeUtils.EDIT_FIRST);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemCollection> itemCollectionList = this.queryByExample(apiVersion, example);
        if (null == itemCollectionList || itemCollectionList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_COLLECTION);
        }

        CollectorItemCollection itemCollection = itemCollectionList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemCollection);
    }

    /**
     * 获取商品二次采集信息
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回二次采集信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemTwiceCollectionByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemCollectionExample example = new CollectorItemCollectionExample();
        CollectorItemCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andEditTypeEqualTo(MsOnionEditTypeUtils.EDIT_SECOND);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemCollection> itemCollectionList = this.queryByExample(apiVersion, example);
        if (null == itemCollectionList || itemCollectionList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_COLLECTION);
        }

        CollectorItemCollection itemCollection = itemCollectionList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemCollection);
    }

    /**
     * 保存商品采集信息
     *
     * @param apiVersion        Api版本
     * @param item              商品信息
     * @param itemBidding       商品竞价信息
     * @param itemCollection    商品采集信息
     * @param itemContent       商品内容信息
     * @param itemAttributeList 商品属性集合
     * @return 返回保存结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult saveItemCollection(MsOnionApiVersion apiVersion, CollectorItem item, CollectorItemBidding itemBidding,
                                            CollectorItemCollection itemCollection, CollectorItemContent itemContent,
                                            List<CollectorItemAttribute> itemAttributeList, List<CollectorItemAttributeImage> attributeImages)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = itemService.updateItemAndBidding(apiVersion, item, itemBidding);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        // 有则修改，无则新增商品采集信息
        Long itemIdx = item.getIdx();
        result = this.getItemCollectionByItemIdx(apiVersion, itemIdx);
        if (null != result.getData()) {
            // 解决打开页面多次点击保存，itemCollection信息中还没有idx
            CollectorItemCollection ic = (CollectorItemCollection) result.getData();
            Long itemCollectionIdx = ic.getIdx();
            itemCollection.setIdx(itemCollectionIdx);

            MsOnionResult rs = this.updateItemCollection(apiVersion, itemCollection);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        } else {
            MsOnionResult rs = this.addItemCollection(apiVersion, itemCollection);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }

        // 有则修改，无则新增商品内容信息
        result = itemContentService.getItemContentByItemIdx(apiVersion, itemIdx);
        if (null != result.getData()) {
            // 解决打开页面多次点击保存，itemContent信息中还没有idx
            CollectorItemContent ic = (CollectorItemContent) result.getData();
            Long itemContentIdx = ic.getIdx();
            itemContent.setIdx(itemContentIdx);

            MsOnionResult rs = itemContentService.updateItemContent(apiVersion, itemContent);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        } else {
            MsOnionResult rs = itemContentService.addItemContent(apiVersion, itemContent);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }

        // 更新商品属性值
        if (null != itemAttributeList && itemAttributeList.size() > 0) {
            MsOnionResult rs = itemAttributeService.updateItemAttributeByItemIdx(apiVersion, itemIdx,
                    MsOnionEditTypeUtils.EDIT_FIRST, itemAttributeList);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }

        // 保存关键属性图片信息
        if (null != attributeImages && attributeImages.size() > 0) {
            result = itemAttributeImageService.addItemAttributeImage(apiVersion, attributeImages);
            if (null != result && MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                return result;
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS, item);
    }

    /**
     * 保存商品二次采集信息
     *
     * @param apiVersion        Api版本
     * @param item              商品信息
     * @param itemCollection    商品采集信息
     * @param itemAttributeList 商品属性集合
     * @return 返回保存结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult saveItemTwiceCollection(MsOnionApiVersion apiVersion, CollectorItem item,
                                                 CollectorItemCollection itemCollection, List<CollectorItemAttribute> itemAttributeList,
                                                 CollectorItemFormalImageLib collectorItemFormalImageLib,
                                                 List<CollectorItemAttributeImage> attributeImages)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long itemIdx = item.getIdx();
        CollectorItem ci = itemService.queryByPrimaryKey(apiVersion, itemIdx);
        if (!MsOnionCollectionStatusUtils.COLLECTION_COMPLETED.equals(ci.getCollectionStatus())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_PLEASE_FIRST_COLLECTION_COMPLETED);
        }

        MsOnionResult result = itemService.updateItem(apiVersion, item);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        // 有则修改，无则新增商品二次采集信息
        result = this.getItemTwiceCollectionByItemIdx(apiVersion, itemIdx);
        if (null != result.getData()) {
            // 解决打开页面多次点击保存，itemCollection信息中还没有idx
            CollectorItemCollection ic = (CollectorItemCollection) result.getData();
            Long itemCollectionIdx = ic.getIdx();
            itemCollection.setIdx(itemCollectionIdx);

            MsOnionResult rs = this.updateItemCollection(apiVersion, itemCollection);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        } else {
            MsOnionResult rs = this.addItemCollection(apiVersion, itemCollection);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }

        // 更新商品属性值
        if (null != itemAttributeList && itemAttributeList.size() > 0) {
            MsOnionResult rs = itemAttributeService.updateItemAttributeByItemIdx(apiVersion, itemIdx,
                    MsOnionEditTypeUtils.EDIT_SECOND, itemAttributeList);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }

        // 更新图片
        MsOnionResult ms = itemFormalImageLibService.getItemFormalImagsByItemIdx(apiVersion, itemIdx);
        if (null != ms.getData()) {
            CollectorItemFormalImageLib cifi = (CollectorItemFormalImageLib) ms.getData();
            collectorItemFormalImageLib.setIdx(cifi.getIdx());
            MsOnionResult rs = itemFormalImageLibService.updateItemFormalImags(apiVersion, collectorItemFormalImageLib);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        } else {
            MsOnionResult rs = itemFormalImageLibService.addItemFormalImags(apiVersion, collectorItemFormalImageLib);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }

        // 保存关键属性图片
        if (attributeImages != null && attributeImages.size() > 0) {
            result = itemAttributeImageService.addItemAttributeImage(apiVersion, attributeImages);
            if (result == null || MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                return result;
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS, item);
    }
}
