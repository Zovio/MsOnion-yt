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
 * @Title: ItemLogServiceImpl
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: 商品日志服务实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月24日 17:19:04
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月24日 17:19:04
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.collector.pojo.CollectorItemLog;
import cc.msonion.carambola.collector.pojo.CollectorItemLogExample;
import cc.msonion.carambola.collector.service.ItemLogService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ItemLogServiceImpl
 * @Description: 商品日志服务实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月24日 17:19:04
 */
@Service
public class ItemLogServiceImpl extends MsOnionServiceExt<CollectorItemLog, CollectorItemLogExample> implements ItemLogService {


    /**
     * 保存商品日志
     *
     * @param apiVersion       api版本
     * @param collectorItemLog 商品日志对象
     * @param operateId        当前操作者ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title saveItemLog
     * @Description 保存商品日志
     * ItemModifyLocationUtils 修改位置字段常量类
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月26日 10:15:51
     */
    @Override
    public MsOnionResult saveItemLog(MsOnionApiVersion apiVersion, CollectorItemLog collectorItemLog, Long operateId)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (collectorItemLog == null || operateId == null || operateId <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        // 参数校验
        String validateMsg = MsOnionPojoValidatorUtils.validatePojo(collectorItemLog);
        if (MsOnionStringUtils.isNotEmpty(validateMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateMsg);
        }
        // 保存
        int saveResult = save(apiVersion, collectorItemLog);
        if (saveResult <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
        return MsOnionResult.ok();
    }

    /**
     * 设置商品日志新增字段值
     * @param collectorItemLog 商品日志对象
     * @param operateId 当前操作者ID
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    private void setItemLog(CollectorItemLog collectorItemLog, Long operateId) throws MsOnionIllegalArgumentException, MsOnionException {

        // 生成idx
        long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_ITEM_LOG_IDX_PATH);
        collectorItemLog.setIdx(idx);
        // 生成version
        long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_ITEM_LOG_VERSION_IDX_PATH);
        collectorItemLog.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
        collectorItemLog.setVersion(version);
        collectorItemLog.setCreateByMemberIdx(operateId);
        collectorItemLog.setCreateTime(new Date());
        collectorItemLog.setUpdateByMemberIdx(operateId);
        collectorItemLog.setUpdateTime(new Date());
    }

    /**
     * 批量保存商品日志
     *
     * @param apiVersion           api版本
     * @param collectorItemLogList 保存商品对象集合
     * @param operateId            当前操作者ID
     * @return MsOnionResult<cc.msonion.carambola.parent.pojo.MsOnionBatchReport<CollectorItemLog>>
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveItemLog
     * @Description 批量保存商品日志, 返回MsOnionResult<cc.msonion.carambola.parent.pojo.MsOnionBatchReport<CollectorItemLog>>
     * ItemModifyLocationUtils 修改位置字段常量类
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月26日 10:14:40
     */
    @Override
    public MsOnionResult batchSaveItemLog(MsOnionApiVersion apiVersion, List<CollectorItemLog> collectorItemLogList, Long operateId)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(collectorItemLogList) || operateId == null || operateId <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        Map<Integer, CollectorItemLog> saveCollectorItemLogList = new HashMap<Integer, CollectorItemLog>();
        Map<Integer, CollectorItemLog> errorCollectorItemLogList = new HashMap<Integer, CollectorItemLog>();
        for (int i = 0; i < collectorItemLogList.size(); i++) {
            CollectorItemLog collectorItemLog = collectorItemLogList.get(i);
            // 参数校验
            String validateMsg = MsOnionPojoValidatorUtils.validatePojo(collectorItemLog);
            if (MsOnionStringUtils.isNotEmpty(validateMsg)) {
                errorCollectorItemLogList.put(i, collectorItemLog);
            } else {
                setItemLog(collectorItemLog, operateId);
                saveCollectorItemLogList.put(i, collectorItemLog);
            }
        }
        MsOnionBatchReport<CollectorItemLog> msOnionBatchReport = new MsOnionBatchReport<CollectorItemLog>();
        if (MsOnionMapUtils.isEmpty(saveCollectorItemLogList)) {
            msOnionBatchReport.setFailureRecords(errorCollectorItemLogList);
            return MsOnionResult.ok();
        }
        msOnionBatchReport = this.saveWithBatch(apiVersion, saveCollectorItemLogList);
        msOnionBatchReport.getFailureRecords().putAll(errorCollectorItemLogList);
        return MsOnionResult.ok(msOnionBatchReport);
    }
}
