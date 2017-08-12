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

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.CollectorCategoryAttributeGroup;
import cc.msonion.carambola.collector.pojo.CollectorCategoryAttributeGroupExample;
import cc.msonion.carambola.collector.service.CategoryAttributeGroupService;
import cc.msonion.carambola.collector.service.redis.key.impl.CategoryAttributeGroupRedisKeyGeneratorImpl;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CategoryAttributeGroupService
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Mark 3563249160@qq.com
 * @Date: 2017/7/19
 */
@Service
public class CategoryAttributeGroupServiceImpl extends MsOnionServiceExt<CollectorCategoryAttributeGroup,
                                    CollectorCategoryAttributeGroupExample> implements CategoryAttributeGroupService{

    /**
     * 获取某个类目与属性组的关联关系
     * @param apiVersion 版本
     * @param categoryIdx 类目idx
     * @return 列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<CollectorCategoryAttributeGroup> getCategoryAttributeGroup(MsOnionApiVersion apiVersion,
                                                               Long categoryIdx) throws MsOnionException {
        CollectorCategoryAttributeGroupExample example = new CollectorCategoryAttributeGroupExample();
        CollectorCategoryAttributeGroupExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andCategoryIdxEqualTo(categoryIdx);

        return this.queryByExample(apiVersion, example);
    }

    /**
     * 关联类目与属性组
     * @param apiVersion 版本
     * @param categoryAttributeGroup 类目与属性组
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult addCategoryAttributeGroup(MsOnionApiVersion apiVersion ,
                                   CollectorCategoryAttributeGroup categoryAttributeGroup) throws MsOnionException {
        if (null == categoryAttributeGroup) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    CollectorMessageConstants.MESSAGE_ITEM_INFO_NULL);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_CATEGORY_ATTRIBUTE_GROUP_IDX_PATH);

        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_CATEGORY_ATTRIBUTE_GROUP_VERSION_IDX_PATH);

        categoryAttributeGroup.setIdx(idx);
        categoryAttributeGroup.setVersion(version);
        categoryAttributeGroup.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        int result = this.save(apiVersion, categoryAttributeGroup);
        if (result > 0) {
            return MsOnionResult.ok(categoryAttributeGroup);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 批量关联类目与属性组
     * @param apiVersion 版本
     * @param categoryAttributeGroups 类目与属性组
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult batchSaveCategoryAttributeGroup(MsOnionApiVersion apiVersion,
            List<CollectorCategoryAttributeGroup> categoryAttributeGroups, Long categoryIdx) throws MsOnionException {

        if (null != categoryIdx) {
            this.clearCategoryAttributeGroup(apiVersion, categoryIdx);
        }

        Map<Integer, CollectorCategoryAttributeGroup> map = new HashMap<>();
        if (null != categoryAttributeGroups && categoryAttributeGroups.size() > 0) {
            for (int index = 0; index < categoryAttributeGroups.size(); index++) {
                CollectorCategoryAttributeGroup categoryAttributeGroup = categoryAttributeGroups.get(index);
                Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                        this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                        MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_CATEGORY_ATTRIBUTE_GROUP_IDX_PATH);

                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                        this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                        MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_CATEGORY_ATTRIBUTE_GROUP_VERSION_IDX_PATH);

                categoryAttributeGroup.setIdx(idx);
                categoryAttributeGroup.setVersion(version);
                categoryAttributeGroup.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                map.put(index, categoryAttributeGroup);
            }
        }

        if (map.size() > 0) {
            MsOnionBatchReport batchReport = this.saveWithBatch(apiVersion, map);
            if (null != batchReport && batchReport.isAllSuccess()) {
                return MsOnionResult.ok();
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 清除已关联的类目与属性组
     * @param apiVersion 版本
     * @param categoryIdx 类目idx
     * @throws MsOnionException 自定义异常
     */
    private void clearCategoryAttributeGroup(MsOnionApiVersion apiVersion, Long categoryIdx) throws MsOnionException {
        List<CollectorCategoryAttributeGroup> categoryAttributeGroups =
                                                            this.getCategoryAttributeGroup(apiVersion, categoryIdx);

        if (null != categoryAttributeGroups && categoryAttributeGroups.size() > 0) {
            List<Long> idxes = new ArrayList<>();
            for (CollectorCategoryAttributeGroup categoryAttributeGroup : categoryAttributeGroups) {
                idxes.add(categoryAttributeGroup.getIdx());
            }
            this.deleteByIdxes(apiVersion, idxes);
        }
    }
}
