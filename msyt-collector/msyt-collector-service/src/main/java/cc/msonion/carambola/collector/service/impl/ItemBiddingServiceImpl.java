/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营竞价洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际竞价直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: ItemBiddingServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemBidding业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月5日 下午4:06:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月5日 下午4:06:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.CollectorItemBidding;
import cc.msonion.carambola.collector.pojo.CollectorItemBiddingExample;
import cc.msonion.carambola.collector.service.ItemBiddingService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
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
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ItemBiddingServiceImpl
 * @Description: 商品竞价Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月5日 下午4:06:21
 */
@Service
public class ItemBiddingServiceImpl extends MsOnionServiceExt<CollectorItemBidding, CollectorItemBiddingExample> implements ItemBiddingService {
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
     * 新增商品竞价
     *
     * @param apiVersion  Api版本
     * @param itemBidding
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addItemBidding(MsOnionApiVersion apiVersion, CollectorItemBidding itemBidding)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemBidding) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BIDDING_INFO_NULL);
        }


        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_IDX_PATH);
        itemBidding.setIdx(idx);
        itemBidding.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_VERSION_IDX_PATH);
        itemBidding.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemBidding=" + itemBidding);

        int result = this.save(apiVersion, itemBidding);
        if (result > 0) {
            return MsOnionResult.ok(itemBidding);
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
     * @Title deleteItemBidding
     * @Description 删除商品竞价
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月5日 下午4:06:21
     */
    @Override
    public MsOnionResult deleteItemBidding(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemBidding itemBidding = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemBidding) {
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
     * @param itemBidding
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemBidding
     * @Description 更新商品竞价
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月5日 下午4:06:21
     */
    @Override
    public MsOnionResult updateItemBidding(MsOnionApiVersion apiVersion, CollectorItemBidding itemBidding)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemBidding) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BIDDING_INFO_NULL);
        }

        Long itemIdx = itemBidding.getItemIdx();
        MsOnionResult result = this.getItemBiddingByItemIdx(apiVersion, itemIdx);
        if (null != result.getData()) {
            CollectorItemBidding oldData = (CollectorItemBidding) result.getData();
            if (null != oldData) {
                boolean noChange = !MsOnionModifiedFieldUtils.hasModified(oldData, itemBidding);
                if (noChange) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS);
                }

                Long idx = oldData.getIdx();
                MsOnionResult rs = this.deleteItemBidding(apiVersion, idx);
                if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return rs;
                }
            }
        }

        return this.addItemBidding(apiVersion, itemBidding);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemBidding by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemBiddingByIdx
     * @Description 通过主键idx得到商品竞价
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月5日 下午4:06:21
     */
    @Override
    public MsOnionResult getItemBiddingByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * 获取商品竞价信息
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回竞价信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemBiddingByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemBiddingExample example = new CollectorItemBiddingExample();
        CollectorItemBiddingExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemBidding> itemBiddingList = this.queryByExample(apiVersion, example);
        if (null == itemBiddingList || itemBiddingList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_BIDDING);
        }

        CollectorItemBidding itemBidding = itemBiddingList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemBidding);
    }


    /**
     * 更新商品价格信息，NULL属性不更新
     *
     * @param apiVersion  api版本
     * @param itemBidding CollectorItemBidding对象
     * @param operateId   指定需要删除的缓存key对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemBiddingIgnoreNull
     * @Description 更新商品价格信息，NULL属性不更新
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月12日 17:27:35
     */
    @Override
    public MsOnionResult updateItemBiddingIgnoreNull(MsOnionApiVersion apiVersion, CollectorItemBidding itemBidding,
                                                     Long operateId) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemBidding) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_BIDDING_INFO_NULL);
        }

        // 查询所有旧的可用的数据
        CollectorItemBiddingExample example = new CollectorItemBiddingExample();
        CollectorItemBiddingExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemBidding.getItemIdx());
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemBidding> collectorItemBiddingList = this.queryByExample(apiVersion, example);

        if (MsOnionCollectionUtils.isEmpty(collectorItemBiddingList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_BIDDING_INFO_NULL);
        }
        // 旧数据对象
        CollectorItemBidding collectorItemBidding = collectorItemBiddingList.get(0);

        MsOnionBeanUtils.copyPropertiesIgnoreNull(itemBidding, collectorItemBidding, true); // 实体这里有个BUG

        //ExcelItemFillData.copyItemBidding(itemBidding, collectorItemBidding);

        // 校验参数
        String validateStr = MsOnionPojoValidatorUtils.validatePojo(collectorItemBidding);
        if (MsOnionStringUtils.isNotEmpty(validateStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
        }
        MsOnionResult msOnionResult = this.deleteItemBidding(apiVersion, collectorItemBidding.getIdx());
        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return msOnionResult;
        }

        // 新增
        collectorItemBidding.setIdx(null);
        collectorItemBidding.setIdxCode(null);
        Date curDate = new Date();
        collectorItemBidding.setUpdateTime(curDate);
        collectorItemBidding.setUpdateByMemberIdx(operateId);
        collectorItemBidding.setCreateByMemberIdx(operateId);
        collectorItemBidding.setCreateTime(curDate);

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_VERSION_IDX_PATH);
        collectorItemBidding.setVersion(version);

        return this.addItemBidding(apiVersion, collectorItemBidding);
    }


    /**
     * 批量保存商品竞价数据
     *
     * @param apiVersion              api版本
     * @param collectorItemBiddingMap 商品竞价数据
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveItemBidding
     * @Description 批量保存商品竞价数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月20日 14:38:46
     */
    @Override
    public MsOnionBatchReport<CollectorItemBidding> batchSaveItemBidding(MsOnionApiVersion apiVersion,
           Map<Integer, CollectorItemBidding> collectorItemBiddingMap) throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            if (null == collectorItemBiddingMap || collectorItemBiddingMap.isEmpty()) {
                return null;
            }
            // 保存的竞价数据集合赋值
            for (Integer i : collectorItemBiddingMap.keySet()) {
                CollectorItemBidding itemBidding = collectorItemBiddingMap.get(i);
                Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_IDX_PATH);
                itemBidding.setIdx(idx);
                itemBidding.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_VERSION_IDX_PATH);
                itemBidding.setVersion(version);
            }

            return this.saveWithBatch(apiVersion, collectorItemBiddingMap);
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_BIDDING_FAIL);
            return null;
        }

    }


    /**
     * 批量软删除商品竞价数据
     *
     * @param apiVersion              api版本
     * @param collectorItemBiddingMap 商品竞价数据
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchDeleteItemBidding
     * @Description 批量软删除商品竞价数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月20日 14:38:46
     */
    @Override
    public MsOnionBatchReport<CollectorItemBidding> batchDeleteItemBidding(MsOnionApiVersion apiVersion,
            Map<Integer, CollectorItemBidding> collectorItemBiddingMap) throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            if (null == collectorItemBiddingMap || collectorItemBiddingMap.isEmpty()) {
                return null;
            }
            for (Integer i : collectorItemBiddingMap.keySet()) {
                CollectorItemBidding itemBidding = collectorItemBiddingMap.get(i);

                itemBidding.setStatus(MsOnionTableRecordStatus.DELETED.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                        this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                        MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_BIDDING_VERSION_IDX_PATH);
                itemBidding.setVersion(version);
            }

            return this.updateWithBatch(apiVersion, collectorItemBiddingMap);
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_BIDDING_FAIL);
            return null;
        }
    }

    /**
     * 批量更新商品竞价数据
     *
     * @param apiVersion              api版本
     * @param collectorItemBiddingMap 商品竞价数据
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveItemBidding
     * @Description 批量更新商品竞价数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月20日 14:38:46
     */
    @Override
    public MsOnionBatchReport<CollectorItemBidding> batchUpdateItemBidding(MsOnionApiVersion apiVersion,
        Map<Integer, CollectorItemBidding> collectorItemBiddingMap) throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            if (null == collectorItemBiddingMap || collectorItemBiddingMap.isEmpty()) {
                return null;
            }
            Map<Integer, CollectorItemBidding> delMap = new HashMap<Integer, CollectorItemBidding>();
            // 错误参数实体的map
            Map<Integer, CollectorItemBidding> paramsErrorMap = new HashMap<Integer, CollectorItemBidding>();
            for (Integer i : collectorItemBiddingMap.keySet()) {
                CollectorItemBidding itemBidding = collectorItemBiddingMap.get(i);
                CollectorItemBiddingExample example = new CollectorItemBiddingExample();
                CollectorItemBiddingExample.Criteria criteria = example.createCriteria();
                criteria.andItemIdxEqualTo(itemBidding.getItemIdx());
                criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

                List<CollectorItemBidding> itemBiddingList = this.queryByExample(apiVersion, example);
                if (MsOnionCollectionUtils.isEmpty(itemBiddingList)) {
                    paramsErrorMap.put(i, itemBidding);
                    continue;
                }
                itemBidding = itemBiddingList.get(0);
                delMap.put(i, itemBidding);
            }
            // 删除出异常的实体
            MsOnionBatchReport delReport = batchDeleteItemBidding(apiVersion, delMap);
            if (delReport != null) {
                delReport.getFailureRecords().putAll(paramsErrorMap);
                Set<Integer> keys = delReport.getFailureRecords().keySet();
                for (Integer i : keys) {
                    collectorItemBiddingMap.remove(i);
                }
            } else {
                return null;
            }
            MsOnionBatchReport saveReport = batchSaveItemBidding(apiVersion, collectorItemBiddingMap);
            if (saveReport != null) {
                saveReport.getFailureRecords().putAll(delReport.getFailureRecords());
                return  saveReport;
            } else {
                delReport.getFailureRecords().putAll(collectorItemBiddingMap);
                return  delReport;
            }
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e,
                    CollectorMessageConstants.MESSAGE_ITEM_SAVE_ITEM_BIDDING_FAIL);
            return null;
        }

    }
}
