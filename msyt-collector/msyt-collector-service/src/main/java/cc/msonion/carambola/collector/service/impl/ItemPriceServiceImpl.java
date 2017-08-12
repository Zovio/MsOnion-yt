/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营价格洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际价格直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: ItemPriceServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemPrice业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月7日 下午3:41:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月7日 下午3:41:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.common.constants.OrderByConstants;
import cc.msonion.carambola.collector.common.enums.ItemPricePublishEnum;
import cc.msonion.carambola.collector.ext.utils.ItemPriceFillData;
import cc.msonion.carambola.collector.ext.view.ItemPriceView;
import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemPrice;
import cc.msonion.carambola.collector.pojo.CollectorItemPriceExample;
import cc.msonion.carambola.collector.service.ItemPriceService;
import cc.msonion.carambola.collector.service.ItemService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionExcelConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionBeanUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.common.MsOnionModifiedFieldUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ItemPriceServiceImpl
 * @Description: 商品价格Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月7日 下午3:41:21
 */
@Service
public class ItemPriceServiceImpl extends MsOnionServiceExt<CollectorItemPrice, CollectorItemPriceExample> implements ItemPriceService {

    /**
     * itemService
     */
    @Autowired
    private ItemService itemService;

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
     * 新增商品价格
     *
     * @param apiVersion Api版本
     * @param itemPrice
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addItemPrice(MsOnionApiVersion apiVersion, CollectorItemPrice itemPrice)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long itemIdx = itemPrice.getItemIdx();
        // 检查商品是否存在
        MsOnionResult rs = itemService.getItemByIdx(apiVersion, itemIdx);
        if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return rs;
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_PRICE_IDX_PATH);
        itemPrice.setIdx(idx);
        itemPrice.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_PRICE_VERSION_IDX_PATH);
        itemPrice.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemPrice=" + itemPrice);

        int result = this.save(apiVersion, itemPrice);
        if (result > 0) {
            return MsOnionResult.ok(itemPrice);
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
     * @Title deleteItemPrice
     * @Description 删除商品价格
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月7日 下午3:41:21null
     */
    @Override
    public MsOnionResult deleteItemPrice(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemPrice itemPrice = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemPrice) {
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
     * @param itemPrice
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemPrice
     * @Description 更新商品价格
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月7日 下午3:41:21
     */
    @Override
    public MsOnionResult updateItemPrice(MsOnionApiVersion apiVersion, CollectorItemPrice itemPrice)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemPrice) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_PRICE_INFO_NULL);
        }

        // 检查商品是否存在
        Long itemIdx = itemPrice.getItemIdx();
        MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        result = this.getItemPriceByItemIdx(apiVersion, itemIdx);
        if (null != result.getData()) {
            CollectorItemPrice oldPrice = (CollectorItemPrice) result.getData();

            boolean noChange = !MsOnionModifiedFieldUtils.hasModified(oldPrice, itemPrice);
            if (noChange && itemPrice.getPublishStatus() != null && ItemPricePublishEnum.PUBLISHED.getValue() != itemPrice.getPublishStatus()) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, oldPrice);
            }

            Long idx = oldPrice.getIdx();
            result = this.deleteItemPrice(apiVersion, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        return this.addItemPrice(apiVersion, itemPrice);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemPrice by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemPriceByIdx
     * @Description 通过主键idx得到商品价格
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月7日 下午3:41:21
     */
    @Override
    public MsOnionResult getItemPriceByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * 获取商品价格
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回商品价格
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemPriceByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemPriceExample example = new CollectorItemPriceExample();
        CollectorItemPriceExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        example.setOrderByClause("update_time desc");

        List<CollectorItemPrice> itemPriceList = this.queryByExample(apiVersion, example);
        if (null == itemPriceList || itemPriceList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_PRICE);
        }

        CollectorItemPrice itemPrice = itemPriceList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemPrice);
    }


    /**
     * 批量处理商品价格Excel导入问题
     *
     * @param apiVersion        Api版本
     * @param itemPriceViewList 商品价格数据模版集合
     * @param operateId         后台操作用户的ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月09日 11:33:35
     */
    @Override
    public MsOnionResult batchItemPriceView(MsOnionApiVersion apiVersion, List<ItemPriceView> itemPriceViewList, Long operateId) {
        try {
            if (MsOnionCollectionUtils.isEmpty(itemPriceViewList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
            }
            Map<Integer, CollectorItemPrice> saveCollectorItemMap = new HashMap<Integer, CollectorItemPrice>();
            Map<Integer, CollectorItemPrice> updateCollectorItemMap = new HashMap<Integer, CollectorItemPrice>();
            List<Long> itemIdx = new ArrayList<Long>();
            for (int i = 0; i < itemPriceViewList.size(); i++) {
                ItemPriceView itemPriceView = itemPriceViewList.get(i);
                try {
                    if (itemPriceView == null) {
                        continue;
                    }
                    CollectorItemPrice itemPrice = ItemPriceFillData.convertToDataObject(itemPriceView, operateId);
                    if (itemIdx.contains(itemPriceView.getItemIdx())) {
                        itemPriceView.setOk(false);
                        itemPriceView.setErrorMsg(CollectorMessageConstants.ITEM_IDX_REPEAT);
                        continue;
                    } else {
                        itemIdx.add(itemPriceView.getItemIdx());
                    }
                    // 查询所有旧的可用的数据
                    CollectorItemPriceExample example = new CollectorItemPriceExample();
                    CollectorItemPriceExample.Criteria criteria = example.createCriteria();
                    criteria.andItemIdxEqualTo(itemPrice.getItemIdx());
                    criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                    List<CollectorItemPrice> itemPriceList = this.queryByExample(apiVersion, example);

                    if (MsOnionCollectionUtils.isNotEmpty(itemPriceList)) {
                        CollectorItemPrice collectorItemPrice = itemPriceList.get(0);
                        MsOnionBeanUtils.copyPropertiesIgnoreNull(itemPrice, collectorItemPrice, true);
                        // 字段校验
                        MsOnionResult msOnionResult = checkField(apiVersion, collectorItemPrice, itemPriceView);
                        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                            itemPriceView.setOk(false);
                            itemPriceView.setErrorMsg(msOnionResult.getMsg());
                            continue;
                        }
                        updateCollectorItemMap.put(i, collectorItemPrice);
                    } else {
                        // 字段校验
                        MsOnionResult msOnionResult = checkField(apiVersion, itemPrice, itemPriceView);
                        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                            itemPriceView.setOk(false);
                            itemPriceView.setErrorMsg(msOnionResult.getMsg());
                            continue;
                        }
                        saveCollectorItemMap.put(i, itemPrice);
                    }
                    itemPriceView.setOk(true);
                    itemPriceView.setErrorMsg(MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
                } catch (MsOnionException e) {
                    itemPriceView.setOk(false);
                    itemPriceView.setErrorMsg(MsOnionExcelConstants.MESSAGE_EXCEL_SAVE_DATA_ERROR);
                    continue;
                }
            }

            MsOnionBatchReport msOnionBatchReport = null;
            Map<Integer, CollectorItemPrice> msOnionBatchFailMap = new HashMap<Integer, CollectorItemPrice>();
            // 新增的商品价格集合
            msOnionBatchReport = batchSaveItemItemPrice(apiVersion, saveCollectorItemMap);
            if (msOnionBatchReport != null) {
                msOnionBatchFailMap.putAll(msOnionBatchReport.getFailureRecords());
            }
            // 更新的商品价格集合
            msOnionBatchReport = batchUpdateCollectorItemPriceR(apiVersion, updateCollectorItemMap);
            if (msOnionBatchReport != null) {
                // 合并错误集合
                msOnionBatchFailMap.putAll(msOnionBatchReport.getFailureRecords());
            }
            // 如果有失败的，竞价map需要去掉这个对象
            if (msOnionBatchFailMap != null && !msOnionBatchFailMap.isEmpty()) {
                for (Integer idx : msOnionBatchFailMap.keySet()) {
                    // 更新错误结果
                    itemPriceViewList.get(idx).setOk(false);
                    itemPriceViewList.get(idx).setErrorMsg(CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_PRICE_FAIL);
                }
            }
            return MsOnionResult.ok(itemPriceViewList);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                            MsOnionMessageConstants.MESSAGE_BATCH_SAVE_FAILURE);
        }
    }
    /**
     * 批量保存商品价格信息
     * @Title batchSaveItemItemPrice
     * @Description 批量保存商品价格信息
     * @param apiVersion api版本
     * @param collectorItemPriceMap 商品价格信息
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 14:01:25
     */
    public MsOnionBatchReport<CollectorItemPrice> batchSaveItemItemPrice(MsOnionApiVersion apiVersion,
        Map<Integer, CollectorItemPrice> collectorItemPriceMap) throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            if (null == collectorItemPriceMap || collectorItemPriceMap.isEmpty()) {
                return null;
            }

            for (Integer i : collectorItemPriceMap.keySet()) {
                CollectorItemPrice itemPrice = collectorItemPriceMap.get(i);
                Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_PRICE_IDX_PATH);
                itemPrice.setIdx(idx);
                itemPrice.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_PRICE_IDX_PATH);
                itemPrice.setVersion(version);
            }

            return this.saveWithBatch(apiVersion, collectorItemPriceMap);
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_PRICE_FAIL);
            return null;
        }

    }

    /**
     * 批量软删除商品价格
     * @Title batchDeleteCollectorItemPrice
     * @Description 软删除商品价格
     * @param apiVersion api版本
     * @param collectorItemPriceMap 指定需要删除的商品价格
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 13:46:35
     */
    private MsOnionBatchReport<CollectorItemPrice> batchDeleteCollectorItemPrice(MsOnionApiVersion apiVersion,
        Map<Integer, CollectorItemPrice> collectorItemPriceMap) throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            if (null == collectorItemPriceMap || collectorItemPriceMap.isEmpty()) {
                return null;
            }
            for (Integer i : collectorItemPriceMap.keySet()) {
                CollectorItemPrice itemPrice = collectorItemPriceMap.get(i);

                itemPrice.setStatus(MsOnionTableRecordStatus.DELETED.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_VERSION_IDX_PATH);
                itemPrice.setVersion(version);
            }

            return this.updateWithBatch(apiVersion, collectorItemPriceMap);
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_PRICE_FAIL);
            return null;
        }
    }

    /**
     * 批量修改collectorItemPrice
     *
     * @param apiVersion            api版本
     * @param collectorItemPriceMap collectorItemPrice集合
     * @return MsOnionResult对象
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemPriceRecordByItemIdx
     * @Description List<Long> 商品ID集合
     * 返回MsOnionResult data值为MsOnionBatchReport<CollectorItemPrice>
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月27日 11:05:19
     */
    @Override
    public MsOnionResult batchUpdateCollectorItemPrice(MsOnionApiVersion apiVersion, Map<Integer, CollectorItemPrice> collectorItemPriceMap)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return MsOnionResult.ok(batchUpdateCollectorItemPriceR(apiVersion, collectorItemPriceMap));
    }

    /**
     * 批量更新商品价格
     * @Title batchUpdateCollectorItemPrice
     * @Description 描述信息
     * @param apiVersion api版本
     * @param collectorItemPriceMap 商品价格对象集合
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 14:02:09
     */
    public MsOnionBatchReport<CollectorItemPrice> batchUpdateCollectorItemPriceR(MsOnionApiVersion apiVersion,
             Map<Integer, CollectorItemPrice> collectorItemPriceMap) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == collectorItemPriceMap || collectorItemPriceMap.isEmpty()) {
            return null;
        }
        try {
            Map<Integer, CollectorItemPrice> delMap = new HashMap<Integer, CollectorItemPrice>();
            // 错误参数实体的map
            Map<Integer, CollectorItemPrice> paramsErrorMap = new HashMap<Integer, CollectorItemPrice>();
            for (Integer i : collectorItemPriceMap.keySet()) {
                CollectorItemPrice itemPrice = collectorItemPriceMap.get(i);
                CollectorItemPriceExample example = new CollectorItemPriceExample();
                CollectorItemPriceExample.Criteria criteria = example.createCriteria();
                criteria.andItemIdxEqualTo(itemPrice.getItemIdx());
                criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

                List<CollectorItemPrice> itemPriceList = this.queryByExample(apiVersion, example);
                if (MsOnionCollectionUtils.isEmpty(itemPriceList)) {
                    paramsErrorMap.put(i, itemPrice);
                    continue;
                }
                itemPrice = itemPriceList.get(0);
                delMap.put(i, itemPrice);
            }
            // 删除出异常的实体
            MsOnionBatchReport delReport = batchDeleteCollectorItemPrice(apiVersion, delMap);
            if (delReport != null) {
                delReport.getFailureRecords().putAll(paramsErrorMap);
                Set<Integer> keys = delReport.getFailureRecords().keySet();
                for (Integer i : keys) {
                    collectorItemPriceMap.remove(i);
                }
            } else {
                return null;
            }
            MsOnionBatchReport saveReport = batchSaveItemItemPrice(apiVersion, collectorItemPriceMap);
            if (saveReport != null) {
                saveReport.getFailureRecords().putAll(delReport.getFailureRecords());
                return  saveReport;
            } else {
                delReport.getFailureRecords().putAll(collectorItemPriceMap);
                return  delReport;
            }
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_PRICE_FAIL);
            return null;
        }
    }

    /**
     * 参数校验
     * @param apiVersion 版本
     * @param itemPrice 价格
     * @param itemPriceView Excel价格实体
     * @return MsOnionResult MsOnionResult
     * @throws MsOnionException MsOnionException
     */
    private MsOnionResult checkField(MsOnionApiVersion apiVersion, CollectorItemPrice itemPrice,
                                     ItemPriceView itemPriceView) throws MsOnionException {
        if (null == itemPrice) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_PRICE_INFO_NULL);
        }
        Long itemIdx = itemPrice.getItemIdx();
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


        // 市场价不能为空
        if (itemPrice.getMarketPrice() == null || itemPrice.getMarketPrice() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_MARKET_PRICE_NULL);
        }
        // 市场价超过最大值
        if (itemPrice.getMarketPrice() > ItemConstants.MAX_ITEM_PRICE) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_MARKET_PRICE_EXCEED_MAX);
        }

        // 售价不能为空
        if (itemPrice.getSellingPrice() == null || itemPrice.getSellingPrice() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_SELLING_PRICE_NULL);
        }
        // 售价超过最大值
        if (itemPrice.getSellingPrice() > ItemConstants.MAX_ITEM_PRICE) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_SELLING_PRICE_EXCEED_MAX);
        }

        // 供货价不能为空
        if (itemPrice.getSupplyPrice() == null || itemPrice.getSupplyPrice() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_SUPPLY_PRICE_NULL);
        }

        // 供货价超过最大值
        if (itemPrice.getSupplyPrice() > ItemConstants.MAX_ITEM_PRICE) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_SUPPLY_PRICE_EXCEED_MAX);
        }
        CollectorItem collectorItem = (CollectorItem) result.getData();
        itemPriceView.setItemNo(collectorItem.getItemNo());
        return MsOnionResult.ok();
    }

    /**
     * EXCEL导入修改商品价格
     *
     * @param apiVersion Api版本
     * @param itemPrice  商品价格数据
     * @param operateId  后台操作用户的ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月09日 11:33:35
     */
    @Deprecated
    private MsOnionResult updateItemPriceIgoreNull(MsOnionApiVersion apiVersion, CollectorItemPrice itemPrice, Long operateId)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemPrice) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_PRICE_INFO_NULL);
        }

        Long itemIdx = itemPrice.getItemIdx();
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
        // 查询所有旧的可用的数据
        CollectorItemPriceExample example = new CollectorItemPriceExample();
        CollectorItemPriceExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemPrice> itemPriceList = this.queryByExample(apiVersion, example);

        if (MsOnionCollectionUtils.isEmpty(itemPriceList)) {
            // 校验参数
            String validateStr = MsOnionPojoValidatorUtils.validatePojo(itemPrice);
            if (MsOnionStringUtils.isNotEmpty(validateStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
            }
            return this.addItemPrice(apiVersion, itemPrice);
        } else {
            // 旧数据对象
            CollectorItemPrice collectorItemPrice = itemPriceList.get(0);

            MsOnionBeanUtils.copyPropertiesIgnoreNull(itemPrice, collectorItemPrice, true);

            // 校验参数
            String validateStr = MsOnionPojoValidatorUtils.validatePojo(collectorItemPrice);
            if (MsOnionStringUtils.isNotEmpty(validateStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
            }
            MsOnionResult msOnionResult = this.deleteItemPrice(apiVersion, collectorItemPrice.getIdx());
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }

            // 新增
            collectorItemPrice.setIdx(null);
            collectorItemPrice.setIdxCode(null);
            return this.addItemPrice(apiVersion, collectorItemPrice);
        }

    }

    /**
     * 根据商品ID 查询商品价格的最新的对象和上次修改的对象
     *
     * @param apiVersion  api版本
     * @param itemIdxList 商品ID集合
     * @return MsOnionResult对象
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemPriceRecordByItemIdx
     * @Description List<Long> 商品ID集合
     * 返回MsOnionResult data值为List<Map<String,Object>>
     * Map<String,Object>包含{key new:Object 最新对象,key old:Object 上次修改的对象}
     * 如果有一个商品ID没有找到，则返回错误的MsOnionResult
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月27日 11:05:19
     */
    @Override
    public MsOnionResult getItemPriceRecordByItemIdx(MsOnionApiVersion apiVersion, List<Long> itemIdxList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(itemIdxList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        List<Map<String, Object>> mapList = new ArrayList<>();

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
            CollectorItemPriceExample example = new CollectorItemPriceExample();
            CollectorItemPriceExample.Criteria criteria = example.createCriteria();
            criteria.andItemIdxEqualTo(itemIdx);
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<CollectorItemPrice> itemPriceList = this.queryByExample(apiVersion, example);
            if (null == itemPriceList || itemPriceList.size() <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_PRICE);
            } else if (itemPriceList.size() != 1) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        CollectorMessageConstants.MESSAGE_ITEM_PRICE_EXCEPTION);
            }
            CollectorItemPrice itemPrice = itemPriceList.get(0);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("new", itemPrice);
            example.clear();
            CollectorItemPriceExample.Criteria criteria2 = example.createCriteria();
            criteria2.andItemIdxEqualTo(itemIdx);
            criteria2.andStatusEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
            List<CollectorItemPrice> delItemPriceList = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(delItemPriceList)) {
                map.put("old", null);
            } else {
                map.put("old", delItemPriceList.get(0));
            }
            mapList.add(map);
        }

        return MsOnionResult.ok(mapList);
    }



}
