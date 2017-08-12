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
 * @Title: ItemHistoryServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: 商品历史纪录服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/23 12:52
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/23 12:52
 * @Modify-version: V2.0.0
 * @Modify-description: 新增商品历史纪录相关CRUD操作
 */

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemHistory;
import cc.msonion.carambola.collector.pojo.CollectorItemHistoryExample;
import cc.msonion.carambola.collector.service.ItemHistoryService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ItemHistoryServiceImpl
 * @Description: 商品历史纪录服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/23 12:52
 */
@Service
public class ItemHistoryServiceImpl extends MsOnionServiceExt<CollectorItemHistory, CollectorItemHistoryExample> implements ItemHistoryService {
    /**
     * 新增商品历史纪录
     *
     * @param apiVersion  Api版本
     * @param itemHistory 商品历史纪录
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemHistory(MsOnionApiVersion apiVersion, CollectorItemHistory itemHistory)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_HISTORY_IDX_PATH);
        itemHistory.setIdx(idx);
        itemHistory.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_HISTORY_VERSION_PATH);
        itemHistory.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemHistory=" + itemHistory);

        int result = this.save(apiVersion, itemHistory);
        if (result > 0) {
            return MsOnionResult.ok(itemHistory);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 新增商品历史记录
     *
     * @param apiVersion Api版本
     * @param item       商品记录
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemHistory(MsOnionApiVersion apiVersion, CollectorItem item) throws MsOnionIllegalArgumentException, MsOnionException {
        Long itemIdx = item.getIdx();

        CollectorItemHistory itemHistory = new CollectorItemHistory();
        itemHistory.setItemIdx(itemIdx);

        if (null != item.getItemStateIdx()) {
            itemHistory.setItemStateIdx(item.getItemStateIdx());
        }
        if (StringUtils.isNotBlank(item.getCnName())) {
            itemHistory.setCnName(item.getCnName());
        }
        if (StringUtils.isNotBlank(item.getEnName())) {
            itemHistory.setEnName(item.getEnName());
        }
        if (null != item.getBrandIdx()) {
            itemHistory.setBrandIdx(item.getBrandIdx());
        }
        if (null != item.getOriginIdx()) {
            itemHistory.setOriginIdx(item.getOriginIdx());
        }
        if (null != item.getCategoryIdx()) {
            itemHistory.setCategoryIdx(item.getCategoryIdx());
        }
        if (StringUtils.isNotBlank(item.getSpecification())) {
            itemHistory.setSpecification(item.getSpecification());
        }
        if (null != item.getWarehouseTypeIdx()) {
            itemHistory.setWarehouseTypeIdx(item.getWarehouseTypeIdx());
        }
        if (null != item.getBatch()) {
            itemHistory.setBatch(item.getBatch());
        }
        if (StringUtils.isNotBlank(item.getCollectionRemark())) {
            itemHistory.setCollectionRemark(item.getCollectionRemark());
        }
        if (null != item.getPurchaseStatus()) {
            itemHistory.setPurchaseStatus(item.getPurchaseStatus());
        }
        if (null != item.getCollectionStatus()) {
            itemHistory.setCollectionStatus(item.getCollectionStatus());
        }
        if (null != item.getWeight()) {
            itemHistory.setWeight(item.getWeight());
        }
        itemHistory.setCreateByMemberIdx(item.getCreateByMemberIdx());
        itemHistory.setUpdateByMemberIdx(item.getUpdateByMemberIdx());

        return this.addItemHistory(apiVersion, itemHistory);
    }
}
