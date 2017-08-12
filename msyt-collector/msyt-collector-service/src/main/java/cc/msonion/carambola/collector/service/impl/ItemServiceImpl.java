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
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: ItemServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: Item业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/3/30 11:41
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017/3/30 11:41
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.ext.utils.ExcelItemFillData;
import cc.msonion.carambola.collector.ext.view.ExcelItemView;
import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemBidding;
import cc.msonion.carambola.collector.pojo.CollectorItemCategory;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionCategoryLevelUtils;
import cc.msonion.carambola.commons.common.utils.MsOnionCollectionStatusUtils;
import cc.msonion.carambola.commons.common.utils.MsOnionPurchaseStatusUtils;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionBeanUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicCodeUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import cc.msonion.carambola.thirdplatform.erp.pojo.response.Stock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ItemServiceImpl
 * @Description: 商品Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017/3/30 11:41
 */
@Service
public class ItemServiceImpl extends MsOnionServiceExt<CollectorItem, CollectorItemExample> implements ItemService {
    /**
     * 商品竞价服务
     */
    @Autowired
    private ItemBiddingService itemBiddingService;

    /**
     * 商品类目服务
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 商品历史记录服务
     */
    @Autowired
    private ItemHistoryService itemHistoryService;

    /**
     * itemBarcodeService
     */
    @Autowired
    private ItemBarcodeService itemBarcodeService;

//    @Autowired
//    private DefaultMQProducer mqProducer;

//    @Value("#{rocketmqProperties[rocketmq_item_topic]}")
//    private String topic;

//    @Value("#{rocketmqProperties[rocketmq_tag]}")
//    private String tag;

    /**
     * 检查条形码是否已存在 （不要在这里扩展其他了）
     * 先判断商品条形码是否存在，在判断商品仓库条形码是否存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数
     * @return 返回检查结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        CollectorItemExample example = new CollectorItemExample();
        CollectorItemExample.Criteria criteria = example.createCriteria();

        if (ParamTypeUtils.TYPE_ITEM_BARCODE == type) {
            criteria.andBarcodeEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        CollectorItem collectorItem2 = this.queryOne(apiVersion, example);
        if (collectorItem2 == null) {
            // 判断商品仓库是否存在
            MsOnionResult result2 = itemBarcodeService.checkUnique(apiVersion, param.trim());
            if (result2.getStatus() == MsOnionStatusConstants.STATUS_200) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, ParamTypeUtils.getDescription(type) + "不存在，可以使用", param);
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ParamTypeUtils.getDescription(type) + "已存在，不可使用", param);
    }

    /**
     * 新增商品
     *
     * @param apiVersion Api版本
     * @param item
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addItem(MsOnionApiVersion apiVersion, CollectorItem item) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == item) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_INFO_NULL);
        }

        // 检查条形码是否已存在
        String barcode = item.getBarcode();
        MsOnionResult msOnionResult = this.inspectParameter(apiVersion, barcode, ParamTypeUtils.TYPE_ITEM_BARCODE);
        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return msOnionResult;
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_IDX_PATH);
        item.setIdx(idx);

        item.setPurchaseStatus(MsOnionPurchaseStatusUtils.PURCHASE_NOT_CONFIRMED);
        item.setCollectionStatus(MsOnionCollectionStatusUtils.COLLECTION_NOT);
        item.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        item.setImageSmall(item.getImageBig());
        item.setImageMiddle(item.getImageBig());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_VERSION_PATH);
        item.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，item=" + item);

        int result = this.save(apiVersion, item);
        if (result > 0) {
            return MsOnionResult.ok(item);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 新增商品及商品竞价信息
     *
     * @param apiVersion  Api版本
     * @param item        商品
     * @param itemBidding 商品竞价
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemAndBidding(MsOnionApiVersion apiVersion, CollectorItem item, CollectorItemBidding itemBidding)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = this.addItem(apiVersion, item);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        CollectorItem i = (CollectorItem) result.getData();
        Long itemIdx = i.getIdx();
        itemBidding.setItemIdx(itemIdx);

        result = itemBiddingService.addItemBidding(apiVersion, itemBidding);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

//        this.dataTransition(i, (ItemBidding) result.getData(), true);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteItem
     * @Description 删除商品
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年3月30日 下午7:47:16
     */
    @Override
    public MsOnionResult deleteItem(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItem item = this.queryByPrimaryKey(apiVersion, idx);
        if (null == item) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param item
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItem
     * @Description 更新商品
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年3月30日 下午7:47:16
     */
    @Override
    public MsOnionResult updateItem(MsOnionApiVersion apiVersion, CollectorItem item) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == item) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_INFO_NULL);
        }

        Long idx = item.getIdx();
        String barcode = item.getBarcode();
        CollectorItem oldData = this.queryByPrimaryKey(apiVersion, idx);

        // 检查条形码是否已存在
        if (!oldData.getBarcode().equals(barcode) && StringUtils.isNotBlank(barcode)) {
            MsOnionResult msOnionResult = this.inspectParameter(apiVersion, barcode, ParamTypeUtils.TYPE_ITEM_BARCODE);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }
            // 条形码和之前不同，不能为空，且不存在，那么修改
            oldData.setBarcode(barcode);
        }

        // 对比数据有无改变
        /*boolean hasModified = MsOnionModifiedFieldUtils.hasModified(oldData, item);
        if (hasModified) {
            // 商品历史记录
            MsOnionResult rs = itemHistoryService.addItemHistory(apiVersion, item);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
        }*/

        oldData.setCnName(item.getCnName());
        oldData.setEnName(item.getEnName());
        oldData.setBrandIdx(item.getBrandIdx());
        oldData.setOriginIdx(item.getOriginIdx());
        oldData.setSpecification(item.getSpecification());
        oldData.setBatch(item.getBatch());
        if (null != item.getWeight()) {
            oldData.setWeight(item.getWeight());
        }
        if (null != item.getItemStateIdx()) {
            oldData.setItemStateIdx(item.getItemStateIdx());
        }
        if (null != item.getCollectionStatus()) {
            oldData.setCollectionStatus(item.getCollectionStatus());
        }
        oldData.setCollectionRemark(item.getCollectionRemark());
        if (null != item.getPublishStatus()) {
            oldData.setPublishStatus(item.getPublishStatus());
        }
        oldData.setUpdateTime(new Date());
        oldData.setUpdateByMemberIdx(item.getUpdateByMemberIdx());


        oldData.setImageBig(item.getImageBig());
        oldData.setImageSmall(item.getImageBig());
        oldData.setImageMiddle(item.getImageBig());

        int result = this.updateByPrimaryKey(apiVersion, oldData);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, oldData);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 更新商品及商品竞价信息
     *
     * @param apiVersion  Api版本
     * @param item        商品
     * @param itemBidding 商品竞价
     * @return 返回更新结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateItemAndBidding(MsOnionApiVersion apiVersion, CollectorItem item, CollectorItemBidding itemBidding)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = updateItem(apiVersion, item);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        result = itemBiddingService.updateItemBidding(apiVersion, itemBidding);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the item by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemByIdx
     * @Description 通过主键idx得到商品
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年3月30日 下午7:47:16
     */
    @Override
    public MsOnionResult getItemByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItem item = this.queryByPrimaryKey(apiVersion, idx);
        if (null == item) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, item);
    }

    /**
     * 确认采购
     *
     * @param apiVersion Api版本
     * @param item       商品
     * @param mantissa   货号尾数
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult confirmPurchase(MsOnionApiVersion apiVersion, CollectorItem item, Short mantissa)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = item.getIdx();
        Long categoryIdx = item.getCategoryIdx();
        CollectorItemCategory itemCategory = itemCategoryService.queryByPrimaryKey(apiVersion, categoryIdx);
        if (!MsOnionCategoryLevelUtils.LEVEL_THREE.equals(itemCategory.getLevelNum())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_PLEASE_SELECT_THREE_CATEGORY);
        }
        Long warehouseTypeIdx = item.getWarehouseTypeIdx();

        CollectorItem i = this.queryByPrimaryKey(apiVersion, idx);
        if (null == i) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        Short purchaseStatus = i.getPurchaseStatus();
        if (MsOnionPurchaseStatusUtils.PURCHASE_CONFIRMED.equals(purchaseStatus)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_PURCHASE_CONFIRMED);
        }

        // 生成货号
        MsOnionResult result = this.generateItemNo(apiVersion, categoryIdx, warehouseTypeIdx, mantissa);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        String itemNo = (String) result.getData();

        // 设置商品货号、商品类目、仓库类型、采购状态
        i.setItemNo(itemNo);
        i.setCategoryIdx(categoryIdx);
        i.setWarehouseTypeIdx(warehouseTypeIdx);
        i.setPurchaseStatus(MsOnionPurchaseStatusUtils.PURCHASE_CONFIRMED);
        i.setUpdateTime(new Date());
        i.setUpdateByMemberIdx(item.getUpdateByMemberIdx());

        int rs = this.updateByPrimaryKey(apiVersion, i);
        if (rs > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, CollectorMessageConstants.MESSAGE_CONFIRM_PURCHASE_SUCCESS, i);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, CollectorMessageConstants.MESSAGE_CONFIRM_PURCHASE_FAIL);
    }

    /**
     * 生成货号
     *
     * @param apiVersion       Api版本
     * @param categoryIdx      类目主键idx
     * @param warehouseTypeIdx 仓库主键idx
     * @param mantissa         仓库货号尾数
     * @return 返回货号
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult generateItemNo(MsOnionApiVersion apiVersion, Long categoryIdx, Long warehouseTypeIdx, Short mantissa)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = itemCategoryService.getItemCategoryByIdx(apiVersion, categoryIdx);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        CollectorItemCategory itemCategory = (CollectorItemCategory) result.getData();
        Short levelNum = itemCategory.getLevelNum();
        if (MsOnionCategoryLevelUtils.LEVEL_THREE.equals(levelNum)) {
            MsOnionResult rs = itemCategoryService.getParent(apiVersion, categoryIdx);
            if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return rs;
            }
            itemCategory = (CollectorItemCategory) rs.getData();
        }

        String itemCategoryCode = itemCategory.getCode();

        String itemNo = MsOnionDistributedAtomicCodeUtils.getItemNo(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy());
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, CollectorMessageConstants.MESSAGE_GENERATE_ITEM_NO_SUCCESS,
                itemCategoryCode + itemNo + mantissa);
    }

    /**
     * 修改类目
     *
     * @param apiVersion Api版本
     * @param item       商品
     * @param mantissa   仓库货号尾数
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateItemCategory(MsOnionApiVersion apiVersion, CollectorItem item, Short mantissa)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = item.getIdx();
        Long categoryIdx = item.getCategoryIdx();
        CollectorItemCategory ic = itemCategoryService.queryByPrimaryKey(apiVersion, categoryIdx);
        if (!MsOnionCategoryLevelUtils.LEVEL_THREE.equals(ic.getLevelNum())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_PLEASE_SELECT_THREE_CATEGORY);
        }

        CollectorItem i = this.queryByPrimaryKey(apiVersion, idx);
        if (null == i) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        Short purchaseStatus = i.getPurchaseStatus();
        if (MsOnionPurchaseStatusUtils.PURCHASE_NOT_CONFIRMED.equals(purchaseStatus)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_FIRST_CONFIRM_PURCHASE);
        }

        Long warehouseTypeIdx = i.getWarehouseTypeIdx();
        Long oldCategoryIdx = i.getCategoryIdx();
        if (!categoryIdx.equals(oldCategoryIdx)) {
            MsOnionResult result = itemCategoryService.getParent(apiVersion, categoryIdx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }

            CollectorItemCategory itemCategory = (CollectorItemCategory) result.getData();
            String parentCode = itemCategory.getCode();

            result = itemCategoryService.getParent(apiVersion, oldCategoryIdx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }

            itemCategory = (CollectorItemCategory) result.getData();
            String oldParentCode = itemCategory.getCode();

            if (!parentCode.equals(oldParentCode)) {
                result = this.generateItemNo(apiVersion, categoryIdx, warehouseTypeIdx, mantissa);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }

                String itemNo = (String) result.getData();
                i.setItemNo(itemNo);
            }
        }

        i.setCategoryIdx(categoryIdx);
        i.setPublishStatus(item.getPublishStatus());
        i.setUpdateTime(new Date());
        i.setUpdateByMemberIdx(item.getUpdateByMemberIdx());

        int rs = this.updateByPrimaryKey(apiVersion, i);
        if (rs > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, i);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

//    /**
//     * 发起MQ数据同步
//     * @param item  采集中心商品信息
//     * @param itemBidding 采集中心竞价信息
//     * @param isAdd true:新增；false:修改
//     * @throws MsOnionException    异常
//     */
//    private void dataTransition(Item item, ItemBidding itemBidding, Boolean isAdd) throws MsOnionException {
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("item", item);
//        data.put("itemBidding", itemBidding);
//        data.put("isAdd", isAdd);
//        try {
//            Message msg = new Message(topic, tag, MsOnionJsonUtils.objectToJson(data).getBytes("UTF-8"));
//            mqProducer.send(msg);
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }
//    }


    /**
     * 更新商品忽略属性为空的字段
     *
     * @param apiVersion  Api版本
     * @param item        商品
     * @param itemBidding 商品价格
     * @param operateId   后台操作用户的ID
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateItemAndBiddingIgnoreNull(MsOnionApiVersion apiVersion, CollectorItem item,
                                                        CollectorItemBidding itemBidding, Long operateId)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == item) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_INFO_NULL);
        }

        Long idx = item.getIdx();
        CollectorItem i = this.queryByPrimaryKey(apiVersion, idx);
        if (i == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
        }

        MsOnionBeanUtils.copyPropertiesIgnoreNull(item, i, true);

        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_VERSION_PATH);
        i.setVersion(version);
        i.setUpdateTime(new Date());
        i.setUpdateByMemberIdx(item.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, i);

        if (result <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
        }
        // 商品历史记录
        /*MsOnionResult rs = itemHistoryService.addItemHistory(apiVersion, i);
        if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return rs;
        }*/
        itemBidding.setItemIdx(i.getIdx());
        MsOnionResult rs = itemBiddingService.updateItemBiddingIgnoreNull(apiVersion, itemBidding, operateId);
        if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return rs;
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * 批量处理新增商品Excel导入问题
     *
     * @param apiVersion      Api版本
     * @param newItemViewList 新增商品数据模版集合
     * @param operateId       后台操作用户的ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月09日 11:33:35
     */
    @Override
    public MsOnionResult batchItemView(MsOnionApiVersion apiVersion, List<ExcelItemView> newItemViewList, Long operateId) {
        if (MsOnionCollectionUtils.isEmpty(newItemViewList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        // 新增的map
        Map<Integer, CollectorItem> saveCollectorItemMap = new HashMap<Integer, CollectorItem>();
        Map<Integer, CollectorItemBidding> saveCollectorItemBiddingMap = new HashMap<Integer, CollectorItemBidding>();
        // 更新的map
        Map<Integer, CollectorItem> updateCollectorItemMap = new HashMap<Integer, CollectorItem>();
        Map<Integer, CollectorItemBidding> updateCollectorItemBiddingMap = new HashMap<Integer, CollectorItemBidding>();
        for (int i = 0; i < newItemViewList.size(); i++) {
            ExcelItemView newItemView = newItemViewList.get(i);
            try {
                if (newItemView == null) {
                    continue;
                }
                Map<String, Object> dataMap = ExcelItemFillData.getItmeMap(newItemView, operateId);
                CollectorItem item = (CollectorItem) dataMap.get("collectorItem");
                CollectorItemBidding itemBidding = (CollectorItemBidding) dataMap.get("collectorItemBidding");

                if (item.getIdx() != null && item.getIdx() > 0) {
                    // 更新商品
                    CollectorItem collectorItem = this.queryByPrimaryKey(apiVersion, item.getIdx());
                    if (collectorItem == null) {
                        newItemView.setOk(false);
                        newItemView.setErrorMsg(CollectorMessageConstants.MESSAGE_ITEM_NOT_EXIST);
                        continue;
                    }
                    // 查询竞价数据
                    MsOnionResult itemBiddingResult = itemBiddingService.getItemBiddingByItemIdx(apiVersion, item.getIdx());
                    if (itemBiddingResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        newItemView.setOk(false);
                        newItemView.setErrorMsg(CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_BIDDING);
                        continue;
                    }
                    CollectorItemBidding collectorItemBidding = (CollectorItemBidding) itemBiddingResult.getData();
                    collectorItemBidding.setIdx(null);
                    MsOnionBeanUtils.copyPropertiesIgnoreNull(item, collectorItem, true);
                    MsOnionBeanUtils.copyPropertiesIgnoreNull(itemBidding, collectorItemBidding, true);
                    // 字段校验
                    MsOnionResult msOnionResult = checkField(apiVersion, collectorItem, collectorItemBidding);
                    if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        newItemView.setOk(false);
                        newItemView.setErrorMsg(msOnionResult.getMsg());
                        continue;
                    }
                    updateCollectorItemMap.put(i, collectorItem);
                    updateCollectorItemBiddingMap.put(i, collectorItemBidding);
                } else {
                    // 新增商品
                    // 字段校验
                    MsOnionResult msOnionResult = checkField(apiVersion, item, itemBidding);
                    if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        newItemView.setOk(false);
                        newItemView.setErrorMsg(msOnionResult.getMsg());
                        continue;
                    }
                    saveCollectorItemMap.put(i, item);
                    saveCollectorItemBiddingMap.put(i, itemBidding);
                }
                newItemView.setOk(true);
                newItemView.setErrorMsg(MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
            } catch (MsOnionException e) {
                newItemView.setOk(false);
                newItemView.setErrorMsg(MsOnionExcelConstants.MESSAGE_EXCEL_SAVE_DATA_ERROR);
                continue;
            }
        }
        // 新增的结果返回
        // 存放商品操作异常的数据
        Map<Integer, CollectorItem> itemErrorMap = new HashMap<Integer, CollectorItem>();
        // 存放商品竞价操作异常的数据
        Map<Integer, CollectorItemBidding> itemBiddingMap = new HashMap<Integer, CollectorItemBidding>();
        MsOnionResult batchSaveResult = batchSaveItem(apiVersion, saveCollectorItemMap, saveCollectorItemBiddingMap, operateId);
        if (batchSaveResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
            Map<String, Object> returnMap = (Map<String, Object>) batchSaveResult.getData();
            itemErrorMap = (Map<Integer, CollectorItem>) returnMap.get("itemFail");
            itemBiddingMap = (Map<Integer, CollectorItemBidding>) returnMap.get("itemBiddingFail");
        } else {
            itemBiddingMap.putAll(saveCollectorItemBiddingMap);
        }

        // 更新的结果返回
        MsOnionResult batchUpdateResult = batchUpdateItem(apiVersion, updateCollectorItemMap, updateCollectorItemBiddingMap, operateId);
        if (batchUpdateResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
            Map<String, Object> returnMap = (Map<String, Object>) batchUpdateResult.getData();
            itemErrorMap.putAll((Map<Integer, CollectorItem>) returnMap.get("itemFail"));
            itemBiddingMap.putAll((Map<Integer, CollectorItemBidding>) returnMap.get("itemBiddingFail"));
        } else {
            itemBiddingMap.putAll(updateCollectorItemBiddingMap);
        }
        if (MsOnionMapUtils.isNotEmpty(itemErrorMap)) {
            for (Integer i : itemErrorMap.keySet()) {
                newItemViewList.get(i).setOk(false);
                newItemViewList.get(i).setErrorMsg(CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_FAIL);
            }
        }
        if (MsOnionMapUtils.isNotEmpty(itemBiddingMap)) {
            for (Integer i : itemBiddingMap.keySet()) {
                newItemViewList.get(i).setOk(false);
                newItemViewList.get(i).setErrorMsg(CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_BIDDING_FAIL);
            }
        }
        return MsOnionResult.ok(newItemViewList);
    }

    /**
     * 批量保存商品
     *
     * @param apiVersion                  Api版本
     * @param saveCollectorItemMap        批量导入Excel商品
     * @param saveCollectorItemBiddingMap 批量导入Excel商品竞价
     * @param operateId                   后台操作用户的ID
     * @return MsOnionResult MsOnionResult
     */
    private MsOnionResult batchSaveItem(MsOnionApiVersion apiVersion, Map<Integer, CollectorItem> saveCollectorItemMap,
                                        Map<Integer, CollectorItemBidding> saveCollectorItemBiddingMap, Long operateId) {
        if (saveCollectorItemMap == null || saveCollectorItemMap.isEmpty()
                || saveCollectorItemBiddingMap == null || saveCollectorItemBiddingMap.isEmpty()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        try {
            // 新增商品赋值
            for (Integer i : saveCollectorItemMap.keySet()) {
                CollectorItem item = saveCollectorItemMap.get(i);
                Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                        this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_IDX_PATH);
                item.setIdx(idx);
                item.setPurchaseStatus(MsOnionPurchaseStatusUtils.PURCHASE_NOT_CONFIRMED);
                item.setCollectionStatus(MsOnionCollectionStatusUtils.COLLECTION_NOT);
                item.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                        this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_VERSION_PATH);
                item.setVersion(version);
            }
            // 存放商品操作异常的数据
            Map<Integer, CollectorItem> itemErrorMap = new HashMap<Integer, CollectorItem>();
            // 存放商品竞价操作异常的数据
            Map<Integer, CollectorItemBidding> itemBiddingErrorMap = new HashMap<Integer, CollectorItemBidding>();
            Map<Integer, CollectorItem> msOnionBatchFailMap = new HashMap<Integer, CollectorItem>();
            Map<Integer, CollectorItem> msOnionBatchSuccessMap = new HashMap<Integer, CollectorItem>();
            // 操作商品数据保存
            MsOnionBatchReport msOnionBatchReport = this.saveWithBatch(apiVersion, saveCollectorItemMap);
            if (msOnionBatchReport != null) {
                msOnionBatchFailMap = msOnionBatchReport.getFailureRecords();
                msOnionBatchSuccessMap = msOnionBatchReport.getSuccessRecords();
            }

            // 如果有失败的，竞价map需要去掉这个对象
            if (msOnionBatchFailMap != null && !msOnionBatchFailMap.isEmpty()) {
                for (Integer idx : msOnionBatchFailMap.keySet()) {
                    saveCollectorItemBiddingMap.remove(idx);
                }
                itemErrorMap.putAll(msOnionBatchFailMap);
            }
            // 如果有成功的，竞价map设置每个对象的商品ID
            if (msOnionBatchSuccessMap != null && !msOnionBatchSuccessMap.isEmpty()) {
                for (Integer i : msOnionBatchSuccessMap.keySet()) {
                    saveCollectorItemBiddingMap.get(i).setItemIdx(msOnionBatchSuccessMap.get(i).getIdx());
                }
            }
            // 保存商品竞价信息，这里有个问题，竞价数据没有保存成功，商品数据已经保存成功，人工处理
            MsOnionBatchReport batchBiddindReport = itemBiddingService.batchSaveItemBidding(apiVersion, saveCollectorItemBiddingMap);
            // 如果有失败的，竞价map需要去掉这个对象
            if (batchBiddindReport != null) {
                Map<Integer, CollectorItemBidding> failureRecords = batchBiddindReport.getFailureRecords();
                for (Integer i : failureRecords.keySet()) {
                    itemBiddingErrorMap.put(i, failureRecords.get(i));
                }
            } else {
                itemBiddingErrorMap.putAll(saveCollectorItemBiddingMap);
            }
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("itemFail", itemErrorMap);
            returnMap.put("itemBiddingFail", itemBiddingErrorMap);
            return MsOnionResult.ok(returnMap);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_FAIL);
        }
    }

    /**
     * 批量更新商品
     *
     * @param apiVersion                    Api版本
     * @param updateCollectorItemMap        批量导入Excel商品
     * @param updateCollectorItemBiddingMap 批量导入Excel商品竞价
     * @param operateId                     后台操作用户的ID
     * @return MsOnionResult MsOnionResult
     */
    private MsOnionResult batchUpdateItem(MsOnionApiVersion apiVersion, Map<Integer, CollectorItem> updateCollectorItemMap,
                                          Map<Integer, CollectorItemBidding> updateCollectorItemBiddingMap, Long operateId) {
        if (updateCollectorItemMap == null || updateCollectorItemMap.isEmpty()
                || updateCollectorItemBiddingMap == null || updateCollectorItemBiddingMap.isEmpty()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        try {
            // 新增商品赋值
            for (Integer i : updateCollectorItemMap.keySet()) {
                CollectorItem item = updateCollectorItemMap.get(i);
                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                        this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_VERSION_PATH);
                item.setVersion(version);
            }
            // 存放商品操作异常的数据
            Map<Integer, CollectorItem> itemErrorMap = new HashMap<Integer, CollectorItem>();
            // 存放商品竞价操作异常的数据
            Map<Integer, CollectorItemBidding> itemBiddingErrorMap = new HashMap<Integer, CollectorItemBidding>();
            Map<Integer, CollectorItem> msOnionBatchFailMap = new HashMap<Integer, CollectorItem>();
            Map<Integer, CollectorItem> msOnionBatchSuccessMap = new HashMap<Integer, CollectorItem>();
            // 操作商品数据保存
            MsOnionBatchReport msOnionBatchReport = this.updateWithBatch(apiVersion, updateCollectorItemMap);
            if (msOnionBatchReport != null) {
                msOnionBatchFailMap = msOnionBatchReport.getFailureRecords();
                msOnionBatchSuccessMap = msOnionBatchReport.getSuccessRecords();
            }

            // 如果有失败的，竞价map需要去掉这个对象
            if (msOnionBatchFailMap != null && !msOnionBatchFailMap.isEmpty()) {
                for (Integer idx : msOnionBatchFailMap.keySet()) {
                    updateCollectorItemBiddingMap.remove(idx);
                }
                itemErrorMap.putAll(msOnionBatchFailMap);
            }
            // 如果有成功的，竞价map设置每个对象的商品ID
            if (msOnionBatchSuccessMap != null && !msOnionBatchSuccessMap.isEmpty()) {
                for (Integer idx : msOnionBatchSuccessMap.keySet()) {
                    updateCollectorItemBiddingMap.get(idx).setItemIdx(msOnionBatchSuccessMap.get(idx).getIdx());
                }
            }
            // 保存商品竞价信息
            MsOnionBatchReport batchBiddindReport = itemBiddingService.batchUpdateItemBidding(apiVersion, updateCollectorItemBiddingMap);
            if (batchBiddindReport != null) {
                Map<Integer, CollectorItemBidding> failureRecords = batchBiddindReport.getFailureRecords();
                for (Integer i : failureRecords.keySet()) {
                    itemBiddingErrorMap.put(i, failureRecords.get(i));
                }
            } else {
                itemBiddingErrorMap.putAll(updateCollectorItemBiddingMap);
            }
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("itemFail", itemErrorMap);
            returnMap.put("itemBiddingFail", itemBiddingErrorMap);
            return MsOnionResult.ok(returnMap);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_BATCH_SAVE_FAILURE);
        }

    }


    /**
     * 字段校验
     *
     * @param apiVersion  apiVersion
     * @param item        商品
     * @param itemBidding 价格
     * @return MsOnionResult
     * @throws MsOnionException the ms onion exception
     */
    private MsOnionResult checkField(MsOnionApiVersion apiVersion, CollectorItem item, CollectorItemBidding itemBidding) throws MsOnionException {
        // 条形码不能为空
        if (MsOnionStringUtils.isEmpty(item.getBarcode())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_BARCODE_NULL);
        }
        // 条形码不能重复
        if (item.getIdx() != null && item.getIdx() > 0) {
            // 检查条形码是否已存在
            CollectorItemExample example = new CollectorItemExample();
            CollectorItemExample.Criteria criteria = example.createCriteria();
            criteria.andBarcodeEqualTo(item.getBarcode().trim());
            criteria.andIdxNotEqualTo(item.getIdx());
            criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            List<CollectorItem> list = this.queryByExample(apiVersion, example);
            if (list != null && list.size() > 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BARCODE_EXIST);
            }
        } else {
            // 检查条形码是否已存在
            CollectorItemExample example = new CollectorItemExample();
            CollectorItemExample.Criteria criteria = example.createCriteria();
            criteria.andBarcodeEqualTo(item.getBarcode().trim());
            criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            List<CollectorItem> list = this.queryByExample(apiVersion, example);
            if (list != null && list.size() > 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BARCODE_EXIST);
            }
        }
        // 中文名称不能为空
        if (MsOnionStringUtils.isEmpty(item.getCnName())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_CN_NAME_NULL);
        }
        // 中文名称长度不能超限
        if (item.getCnName().length() > ItemConstants.ITEM_CN_NAME_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_CN_NAME_LENGTH_ILLEGAL);
        }
        // 英文名称不能为空
        if (MsOnionStringUtils.isEmpty(item.getEnName())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_EN_NAME_NULL);
        }
        // 英文名称长度不能超限
        if (item.getEnName().length() > ItemConstants.ITEM_EN_NAME_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_EN_NAME_LENGTH_ILLEGAL);
        }
        // 品牌ID 判断
        if (item.getBrandIdx() == null || item.getBrandIdx() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_BRAND_NULL);
        }
        // 产地ID 判断
        if (item.getOriginIdx() == null || item.getOriginIdx() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_ORIGIN_NULL);
        }
        // 竞价
        String validateStr = MsOnionPojoValidatorUtils.validatePojo(itemBidding);
        if (MsOnionStringUtils.isNotEmpty(validateStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
        }
        // 批次
        if (item.getBatch() == null || item.getBatch() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_BATCH_NULL);
        }
        // 权重
        if (item.getWeight() == null || item.getWeight() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_WEIGHT_NULL);
        }
        // 销售状态
        if (item.getItemStateIdx() == null || item.getItemStateIdx() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_STATUS_NULL);
        }
        // 销售状态取值范围
        if (item.getItemStateIdx() < ItemConstants.ITEM_STATE_MIN_VALUE
                || item.getItemStateIdx() > ItemConstants.ITEM_STATE_MAX_VALUE) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_STATUS_NULL);
        }
        // 采集备注长度
        if (item.getCollectionRemark() != null && item.getCollectionRemark().length()
                > ItemConstants.ITEM_COLLECTION_REMARK_MAX_LENGTH) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_STATUS_NULL);
        }

        return MsOnionResult.ok();
    }

    /**
     * ERP同步商品库存
     *
     * @param apiVersion      api版本
     * @param erpStockDTOList 实体集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description ERP同步商品库存, 查询商品ID
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月08日 11:13:41
     */
    @Override
    public MsOnionResult queryItemStock(MsOnionApiVersion apiVersion, List<Stock> erpStockDTOList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionCollectionUtils.isNotEmpty(erpStockDTOList)) {
            for (Stock stock : erpStockDTOList) {
                CollectorItemExample collectorItemExample = new CollectorItemExample();
                CollectorItemExample.Criteria criteria = collectorItemExample.createCriteria();
                criteria.andBarcodeEqualTo(stock.getBarcode().trim());
                criteria.andStatusNotEqualTo(MsOnionStatusConstants.SQL_STATUS_MIN_VALUE);
                List<CollectorItem> collectorItemIdxList = this.queryByExample(apiVersion, collectorItemExample);
                if (MsOnionCollectionUtils.isNotEmpty(collectorItemIdxList)) {
                    CollectorItem item = collectorItemIdxList.get(0);
                    stock.setGoodsId(item.getIdx());
                }
            }
        }
        return MsOnionResult.ok(erpStockDTOList);
    }
}
