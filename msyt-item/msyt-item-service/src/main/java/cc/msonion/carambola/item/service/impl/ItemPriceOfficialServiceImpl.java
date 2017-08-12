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
package cc.msonion.carambola.item.service.impl;


import cc.msonion.carambola.collector.pojo.CollectorItemPrice;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.pojo.ItemPriceOfficial;
import cc.msonion.carambola.item.pojo.ItemPriceOfficialExample;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.item.service.ItemPriceOfficialService;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionBeanUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Title: ItemPriceOfficialServiceImpl
 * @Package: cc.msonion.carambola.item.service.impl
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月27日 13:50:16
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月27日 13:50:16
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: ItemPriceOfficialServiceImpl
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月27日 13:50:16
 */
@Service
public class ItemPriceOfficialServiceImpl extends MsOnionServiceExt<ItemPriceOfficial, ItemPriceOfficialExample>
        implements ItemPriceOfficialService {

    /**
     * 正式商品服务
     */
    @Autowired
    private ItemOfficialService itemOfficialService;

    /**
     * 批量同步采集商品价格数据到正式商品价格
     *
     * @param apiVersion             api版本
     * @param collectorItemPriceMap 采集的商品价格对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveOrUpdate
     * @Description 返回MsOnionResult<Map<Integer, CollectorItemPrice>>
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月27日 14:07:05
     */
    @Override
    public MsOnionResult batchSaveOrUpdate(MsOnionApiVersion apiVersion, Map<Integer, CollectorItemPrice> collectorItemPriceMap)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (MsOnionMapUtils.isEmpty(collectorItemPriceMap)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                            MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        Map<Integer, ItemPriceOfficial> saveItemPriceOfficialMap= new HashMap<Integer, ItemPriceOfficial>();
        Map<Integer, ItemPriceOfficial> updateItemPriceOfficialMap= new HashMap<Integer, ItemPriceOfficial>();
        Map<Integer, ItemPriceOfficial> deleteItemPriceOfficialMap= new HashMap<Integer, ItemPriceOfficial>();
        Map<Integer, CollectorItemPrice> errorMap= new HashMap<Integer, CollectorItemPrice>();
        for (Integer i : collectorItemPriceMap.keySet()) {
            CollectorItemPrice collectorItemPrice = collectorItemPriceMap.get(i);
            MsOnionResult itemOfficialByItemIdxResult = itemOfficialService.
                    getItemOfficialByItemIdx(apiVersion, collectorItemPrice.getItemIdx());
            if (itemOfficialByItemIdxResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                errorMap.put(i, collectorItemPrice);
            } else {
                ItemOfficial itemOfficial= (ItemOfficial) itemOfficialByItemIdxResult.getData();
                ItemPriceOfficial itemPriceOfficial = new ItemPriceOfficial();
                MsOnionBeanUtils.copyProperties(collectorItemPrice, itemPriceOfficial);
                itemPriceOfficial.setItemOfficialIdx(itemOfficial.getIdx());

                ItemPriceOfficial itemPriceOfficial1 = this.queryByPrimaryKey(apiVersion, collectorItemPrice.getIdx());
                if (itemPriceOfficial1 == null) {
                    // 查询是否有旧数据
                    ItemPriceOfficialExample itemPriceOfficialExample = new ItemPriceOfficialExample();
                    ItemPriceOfficialExample.Criteria criteria = itemPriceOfficialExample.createCriteria();
                    criteria.andItemOfficialIdxEqualTo(itemOfficial.getIdx());
                    criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                    ItemPriceOfficial oldItemPriceOfficial = this.queryOne(apiVersion, itemPriceOfficialExample);
                    if (oldItemPriceOfficial != null) {
                        // 版本号 , 不使用 idx 避免理解歧义
                        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_PRICE_VERSION_PATH);
                        oldItemPriceOfficial.setVersion(version);
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
        MsOnionBatchReport<ItemPriceOfficial> msOnionBatchReport = null;
        // 删除旧数据
        if (MsOnionMapUtils.isNotEmpty(deleteItemPriceOfficialMap)) {
            msOnionBatchReport = this.updateWithBatch(apiVersion, deleteItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, collectorItemPriceMap.get(i));
                }
            }
        }
        // 新增新数据
        if (MsOnionMapUtils.isNotEmpty(saveItemPriceOfficialMap)) {

            msOnionBatchReport = this.saveWithBatch(apiVersion, saveItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, collectorItemPriceMap.get(i));
                }
            }
        }
        // 更新数据
        if (MsOnionMapUtils.isNotEmpty(updateItemPriceOfficialMap)) {
            msOnionBatchReport = this.updateWithBatch(apiVersion, updateItemPriceOfficialMap);
            if (MsOnionMapUtils.isNotEmpty(msOnionBatchReport.getFailureRecords())) {
                for (Integer i : msOnionBatchReport.getFailureRecords().keySet()) {
                    errorMap.put(i, collectorItemPriceMap.get(i));
                }
            }
        }

        return MsOnionResult.ok(errorMap);
    }

    /**
     * 新增商品价格
     *
     * @param apiVersion        Api版本
     * @param itemPriceOfficial 商品价格信息
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年06月30日 19:54:16
     */
    @Override
    public MsOnionResult addItemPriceOfficial(MsOnionApiVersion apiVersion, ItemPriceOfficial itemPriceOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_PRICE_IDX_PATH);
        itemPriceOfficial.setIdx(idx);

        // 新增时默认有效
        itemPriceOfficial.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_PRICE_VERSION_PATH);
        itemPriceOfficial.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemPriceOfficial=" + itemPriceOfficial);

        int result = this.save(apiVersion, itemPriceOfficial);
        if (result > 0) {
            return MsOnionResult.ok(itemPriceOfficial);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 修改商品价格
     *
     * @param apiVersion        Api版本
     * @param itemPriceOfficial 商品价格信息
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年06月30日 19:54:16
     */
    @Override
    public MsOnionResult updateItemPriceOfficial(MsOnionApiVersion apiVersion, ItemPriceOfficial itemPriceOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = itemPriceOfficial.getIdx();

        ItemPriceOfficial oldData = this.queryByPrimaryKey(apiVersion, idx);
        oldData.setMarketPrice(itemPriceOfficial.getMarketPrice());
        oldData.setSellingPrice(itemPriceOfficial.getSellingPrice());
        oldData.setSupplyPrice(itemPriceOfficial.getSupplyPrice());
        oldData.setUpdateTime(new Date());
        oldData.setUpdateByMemberIdx(itemPriceOfficial.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, oldData);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, oldData);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 获取商品价格
     *
     * @param apiVersion      Api版本
     * @param itemOfficialIdx 正式商品主键idx
     * @return 返回商品价格
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年06月30日 19:54:16
     */
    @Override
    public ItemPriceOfficial queryOneByItemOfficialIdx(MsOnionApiVersion apiVersion, Long itemOfficialIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        ItemPriceOfficialExample example = new ItemPriceOfficialExample();
        ItemPriceOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(itemOfficialIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        example.setOrderByClause("update_time desc");

        List<ItemPriceOfficial> itemPriceOfficials = this.queryByExample(apiVersion, example);

        return null != itemPriceOfficials && itemPriceOfficials.size() > 0 ? itemPriceOfficials.get(0) : null;
    }
}
