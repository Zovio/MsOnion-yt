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


package cc.msonion.carambola.logistics.service.impl;

/**
 * @Title: LogisticsCustomsDeclareOfficialServiceImpl.java
 * @Package: cc.msonion.carambola.logistics.service.impl
 * @Description: 正式商品报关service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月22日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月22日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.logistics.common.constants.LogisticsConstants;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficialExample;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareOfficialService;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LogisticsCustomsDeclareOfficialServiceImpl
 * @Description: 正式商品报关service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月22日
 */
@Service
public class LogisticsCustomsDeclareOfficialServiceImpl
        extends MsOnionServiceExt<LogisticsCustomsDeclareOfficial, LogisticsCustomsDeclareOfficialExample>
        implements LogisticsCustomsDeclareOfficialService {

    /**
     * 采集物流信息
     */
    @Autowired
    private LogisticsCustomsDeclareService logisticsCustomsDeclareService;


    /**
     * 新增正式商品报关集合
     *
     * @param apiVersion                      Api版本
     * @param logisticsCustomsDeclareOfficial 正式商品报关
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addLogisticsCustomsDeclareOfficial(MsOnionApiVersion apiVersion,
                                                            LogisticsCustomsDeclareOfficial logisticsCustomsDeclareOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == logisticsCustomsDeclareOfficial) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_CUSTOM_INFO_NULL);
        }

        String validatorMsg = MsOnionPojoValidatorUtils.validatePojo(logisticsCustomsDeclareOfficial);
        if (StringUtils.isNotBlank(validatorMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, validatorMsg);
        }

       /* MsOnionResult ret = getLogisticsCustomsDeclareOfficialByItemOfficialIdx(apiVersion, logisticsCustomsDeclareOfficial.getItemOfficialIdx());
        if (ret.getStatus() == MsOnionStatusConstants.STATUS_200){
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, LogisticsConstants.MESSAGE_ITEM_CUSTOM_OFFICIAL_EXIST);
        }*/

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_OFFICIAL_IDX_PATH);
        logisticsCustomsDeclareOfficial.setIdx(idx);
        logisticsCustomsDeclareOfficial.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_CUSTOMS_DECLARE_OFFICIAL_VERSION_PATH);
        logisticsCustomsDeclareOfficial.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，logisticsCustomsDeclareOfficial=" + logisticsCustomsDeclareOfficial);

        int result = this.save(apiVersion, logisticsCustomsDeclareOfficial);
        if (result > 0) {
            return MsOnionResult.ok(logisticsCustomsDeclareOfficial);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);

    }


    /**
     * 通过正式商品idx获取报关信息
     *
     * @param apiVersion      Api版本
     * @param itemOfficialIdx 正式商品主键idx
     * @return 返回报关信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getLogisticsCustomsDeclareOfficialByItemOfficialIdx(MsOnionApiVersion apiVersion, Long itemOfficialIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (itemOfficialIdx == null || itemOfficialIdx <= 0L) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
        LogisticsCustomsDeclareOfficialExample example = new LogisticsCustomsDeclareOfficialExample();
        LogisticsCustomsDeclareOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(itemOfficialIdx);
        // criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        LogisticsCustomsDeclareOfficial logisticsCustomsDeclareOfficial = queryOne(apiVersion, example);
        if (null != logisticsCustomsDeclareOfficial) {
            return MsOnionResult.ok(logisticsCustomsDeclareOfficial);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }


}
