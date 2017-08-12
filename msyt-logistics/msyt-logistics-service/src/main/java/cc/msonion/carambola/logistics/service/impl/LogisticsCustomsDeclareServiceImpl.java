/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营报关洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际报关直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.logistics.service.impl;

/**
 * @Title: ItemCustomServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemCustom业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午8:21:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月11日 下午8:21:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.logistics.common.constants.LogisticsConstants;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclare;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareExample;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionBeanUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ItemCustomServiceImpl
 * @Description: 商品报关Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午8:21:21
 */
@Service
public class LogisticsCustomsDeclareServiceImpl extends MsOnionServiceExt<LogisticsCustomsDeclare, LogisticsCustomsDeclareExample>
        implements LogisticsCustomsDeclareService {
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
     * 新增商品报关
     *
     * @param apiVersion              Api版本
     * @param logisticsCustomsDeclare
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addLogisticsCustomsDeclare(MsOnionApiVersion apiVersion,
                                                    LogisticsCustomsDeclare logisticsCustomsDeclare)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == logisticsCustomsDeclare) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_CUSTOM_INFO_NULL);
        }

        String validatorMsg = MsOnionPojoValidatorUtils.validatePojo(logisticsCustomsDeclare);
        if (StringUtils.isNotBlank(validatorMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validatorMsg);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_IDX_PATH);
        logisticsCustomsDeclare.setIdx(idx);
        logisticsCustomsDeclare.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_VERSION_PATH);
        logisticsCustomsDeclare.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，logisticsCustomsDeclare=" + logisticsCustomsDeclare);

        int result = this.save(apiVersion, logisticsCustomsDeclare);
        if (result > 0) {
            return MsOnionResult.ok(logisticsCustomsDeclare);
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
     * @Title deleteItemCustom
     * @Description 删除商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult deleteLogisticsCustomsDeclare(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        LogisticsCustomsDeclare logisticsCustomsDeclare = this.queryByPrimaryKey(apiVersion, idx);
        if (null == logisticsCustomsDeclare) {
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
     * @param
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemCustom
     * @Description 更新商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult updateLogisticsCustomsDeclare(MsOnionApiVersion apiVersion, LogisticsCustomsDeclare logisticsCustomsDeclare)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == logisticsCustomsDeclare) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_CUSTOM_INFO_NULL);
        }

        String validateMsg = MsOnionPojoValidatorUtils.validatePojo(logisticsCustomsDeclare);
        if (MsOnionStringUtils.isNotBlank(validateMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateMsg);
        }

        Long itemIdx = logisticsCustomsDeclare.getItemIdx();

        LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
        LogisticsCustomsDeclareExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<LogisticsCustomsDeclare> itemCustomList = this.queryByExample(apiVersion, example);
        if (null != itemCustomList && itemCustomList.size() > 0) {
            for (LogisticsCustomsDeclare p : itemCustomList) {
                this.deleteLogisticsCustomsDeclare(apiVersion, p.getIdx());
            }
        }

        return this.addLogisticsCustomsDeclare(apiVersion, logisticsCustomsDeclare);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemCustom by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemCustomByIdx
     * @Description 通过主键idx得到商品报关
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult getLogisticsCustomsDeclareByIdx(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * 获取报关信息
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @return 返回报关信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getLogisticsCustomsDeclareByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
        LogisticsCustomsDeclareExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<LogisticsCustomsDeclare> itemCustomList = this.queryByExample(apiVersion, example);
        if (null == itemCustomList || itemCustomList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_NOT_FOUND_CUSTOM);
        } else if (itemCustomList.size() != 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_CUSTOM_EXCEPTION);
        }

        LogisticsCustomsDeclare itemCustom = itemCustomList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemCustom);
    }

    /**
     * 商品报关Excel导入编辑
     *
     * @param apiVersion              api版本
     * @param logisticsCustomsDeclare 指定需要删除的缓存key对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateCustomerIgnoreNull
     * @Description 商品报关
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月13日 17:02:17
     */
    @Override
    public MsOnionResult updateCustomerIgnoreNull(MsOnionApiVersion apiVersion, LogisticsCustomsDeclare logisticsCustomsDeclare)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == logisticsCustomsDeclare) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    LogisticsConstants.MESSAGE_ITEM_CUSTOM_INFO_NULL);
        }

        Long itemIdx = logisticsCustomsDeclare.getItemIdx();

        // 查询所有旧的可用的数据
        LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
        LogisticsCustomsDeclareExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<LogisticsCustomsDeclare> logisticsCustomsDeclareList = this.queryByExample(apiVersion, example);

        if (MsOnionCollectionUtils.isEmpty(logisticsCustomsDeclareList)) {
            // 校验参数
            String validateStr = MsOnionPojoValidatorUtils.validatePojo(logisticsCustomsDeclare);
            if (MsOnionStringUtils.isNotEmpty(validateStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
            }
            return this.addLogisticsCustomsDeclare(apiVersion, logisticsCustomsDeclare);

        } else {
            // 旧数据对象
            LogisticsCustomsDeclare logisticsCustomsDeclare1 = logisticsCustomsDeclareList.get(0);

            MsOnionBeanUtils.copyPropertiesIgnoreNull(logisticsCustomsDeclare, logisticsCustomsDeclare1, true);

            // 校验参数
            String validateStr = MsOnionPojoValidatorUtils.validatePojo(logisticsCustomsDeclare1);
            if (MsOnionStringUtils.isNotEmpty(validateStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validateStr);
            }
            MsOnionResult msOnionResult = this.deleteLogisticsCustomsDeclare(apiVersion, logisticsCustomsDeclare1.getIdx());
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }

            // 新增
            logisticsCustomsDeclare1.setIdx(null);
            logisticsCustomsDeclare1.setIdxCode(null);

            return this.addLogisticsCustomsDeclare(apiVersion, logisticsCustomsDeclare1);
        }
    }


    /**
     * 批量保存商品报关信息
     *
     * @param apiVersion                 api版本
     * @param logisticsCustomsDeclareMap 商品报关信息
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchSaveItemBidding
     * @Description 批量保存商品报关信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 14:01:25
     */
    @Override
    public MsOnionResult batchSaveLogisticsCustomsDeclare(MsOnionApiVersion apiVersion,
                                                          Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap)
            throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            if (null == logisticsCustomsDeclareMap || logisticsCustomsDeclareMap.isEmpty()) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_BATCH_CUSTOM_ERROR);
            }

            for (Integer i : logisticsCustomsDeclareMap.keySet()) {
                LogisticsCustomsDeclare itemPrice = logisticsCustomsDeclareMap.get(i);
                Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_IDX_PATH);
                itemPrice.setIdx(idx);
                itemPrice.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

                // 版本号 , 不使用 idx 避免理解歧义
                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_VERSION_PATH);
                itemPrice.setVersion(version);
            }
            MsOnionBatchReport<LogisticsCustomsDeclare> msOnionBatchReport = this.saveWithBatch(apiVersion, logisticsCustomsDeclareMap);
            return MsOnionResult.ok(msOnionBatchReport);
        } catch (MsOnionException e) {
            getMsOnionLogger().error(this.getClass().getName(), e, LogisticsConstants.MESSAGE_BATCH_CUSTOM_ERROR);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_BATCH_CUSTOM_ERROR);
        }

    }

    /**
     * 批量软删除商品报关
     *
     * @param apiVersion                 api版本
     * @param logisticsCustomsDeclareMap 指定需要删除的商品报关数据
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchDeleteCollectorItemPrice
     * @Description 软删除商品价格
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 13:46:35
     */
    private MsOnionBatchReport<LogisticsCustomsDeclare> batchDeleteCollectorItemPrice(
            MsOnionApiVersion apiVersion, Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == logisticsCustomsDeclareMap || logisticsCustomsDeclareMap.isEmpty()) {
            return null;
        }
        for (Integer i : logisticsCustomsDeclareMap.keySet()) {
            LogisticsCustomsDeclare itemPrice = logisticsCustomsDeclareMap.get(i);
            itemPrice.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
            // 版本号 , 不使用 idx 避免理解歧义
            Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_VERSION_PATH);
            itemPrice.setVersion(version);
        }

        return this.updateWithBatch(apiVersion, logisticsCustomsDeclareMap);
    }

    /**
     * 批量更新商品报关
     *
     * @param apiVersion                 api版本
     * @param logisticsCustomsDeclareMap 商品报关对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title batchUpdateCollectorLogisticsCustoms
     * @Description 批量更新商品报关
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年06月21日 14:02:09
     */
    @Override
    public MsOnionResult batchUpdateCollectorLogisticsCustoms(MsOnionApiVersion apiVersion,
                                                              Map<Integer, LogisticsCustomsDeclare> logisticsCustomsDeclareMap)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == logisticsCustomsDeclareMap || logisticsCustomsDeclareMap.isEmpty()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_BATCH_CUSTOM_ERROR);
        }
        // 存放错误结果的map
        Map<Integer, LogisticsCustomsDeclare> paramsErrorMap = new HashMap<Integer, LogisticsCustomsDeclare>();
        // 组装软删除的数据
        Map<Integer, LogisticsCustomsDeclare> delMap = new HashMap<Integer, LogisticsCustomsDeclare>();
        for (Integer i : logisticsCustomsDeclareMap.keySet()) {
            LogisticsCustomsDeclare itemPrice = logisticsCustomsDeclareMap.get(i);
            LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
            LogisticsCustomsDeclareExample.Criteria criteria = example.createCriteria();
            criteria.andItemIdxEqualTo(itemPrice.getItemIdx());
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<LogisticsCustomsDeclare> itemPriceList = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(itemPriceList)) {
                paramsErrorMap.put(i, itemPrice);
                continue;
            }
            itemPrice = itemPriceList.get(0);

            delMap.put(i, itemPrice);
        }
        // 软删除实体集合
        MsOnionBatchReport delReport = batchDeleteCollectorItemPrice(apiVersion, delMap);
        if (delReport != null) {
            delReport.getFailureRecords().putAll(paramsErrorMap);
            Set<Integer> keys = delReport.getFailureRecords().keySet();
            for (Integer i : keys) {
                logisticsCustomsDeclareMap.remove(i);
            }
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_BATCH_CUSTOM_ERROR);
        }
        // 保存实体集合
        MsOnionBatchReport<LogisticsCustomsDeclare> msOnionBatchReport = null;
        MsOnionResult saveReportResult = batchSaveLogisticsCustomsDeclare(apiVersion, logisticsCustomsDeclareMap);
        if (saveReportResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
            msOnionBatchReport = (MsOnionBatchReport<LogisticsCustomsDeclare>) saveReportResult.getData();
            msOnionBatchReport.getFailureRecords().putAll(delReport.getFailureRecords());
            return MsOnionResult.ok(msOnionBatchReport);
        } else {
            delReport.getFailureRecords().putAll(logisticsCustomsDeclareMap);
            return MsOnionResult.ok(delReport);
        }
    }
}
