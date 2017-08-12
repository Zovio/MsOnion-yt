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


package cc.msonion.carambola.system.service.impl;

/**
 * @Title: SysSettingService.java
 * @Package: cc.msonion.carambola.system.service.impl
 * @Description: 系统设置service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月07日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月07日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import cc.msonion.carambola.system.common.SystemMessageConstants;
import cc.msonion.carambola.system.pojo.SysSetting;
import cc.msonion.carambola.system.pojo.SysSettingExample;
import cc.msonion.carambola.system.service.SysSettingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: SysSettingService
 * @Description: 系统设置service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月07日
 */
@Service
public class SysSettingServiceImpl extends MsOnionServiceExt<SysSetting, SysSettingExample> implements SysSettingService {


    private static final long serialVersionUID = 543541127530155326L;

    /**
     * @param apiVersion Api版本
     * @param param      参数
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     * @Description: 检查参数
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "系统设置编码为空");
        }
        SysSettingExample example = new SysSettingExample();
        SysSettingExample.Criteria criteria = example.createCriteria();
        criteria.andSettingKeyEqualTo(param.trim());
        List<SysSetting> list = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "系统设置编码已存在");
        }
        return MsOnionResult.ok();
    }

    /**
     * 保存 系统设置
     *
     * @param apiVersion Api版本
     * @param sysSetting 登录日志
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult saveOrUpdateSysSetting(MsOnionApiVersion apiVersion,
                                                SysSetting sysSetting) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == sysSetting) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.POJO_NOT_NULL);
            }

            if (sysSetting.getIdx() == null) {
                // add
                sysSetting.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(sysSetting);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }

                // 校验编码是否唯一
                MsOnionResult result1 = inspectParameter(apiVersion, sysSetting.getSettingKey());
                if (result1.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result1.getMsg());
                }


                // 生成Idx
                long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_SYSTEM_SYSSETTING_IDX_PATH);

                sysSetting.setIdx(idx);

                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_SYSTEM_SYSSETTING_VERSION_IDX_PATH);
                sysSetting.setVersion(version);

                int result = this.save(apiVersion, sysSetting);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 系统设置新增成功, idx = " + idx);
                    return MsOnionResult.ok();
                }
            } else {
                // update
                SysSetting ss = this.queryByPrimaryKey(apiVersion, sysSetting.getIdx());
                //  ss.setSettingKey(sysSetting.getSettingKey());
                String valiateMsg1 = MsOnionPojoValidatorUtils.validatePojoProperty(sysSetting, "settingValue");
                if (StringUtils.isNotEmpty(valiateMsg1)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg1);
                }
                String valiateMsg2 = MsOnionPojoValidatorUtils.validatePojoProperty(sysSetting, "remark");
                if (StringUtils.isNotEmpty(valiateMsg2)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg2);
                }
                ss.setSettingValue(sysSetting.getSettingValue());
                ss.setRemark(sysSetting.getRemark());
                if (sysSetting.getUpdateByMemberIdx() > 0L) {
                    ss.setUpdateByMemberIdx(sysSetting.getUpdateByMemberIdx());
                }
                ss.setUpdateTime(new Date());

                int result = this.updateByPrimaryKey(apiVersion, ss);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 系统设置更新成功, idx = " + sysSetting.getIdx());
                    return MsOnionResult.ok();
                }
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.FAIL);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 通过设置编码获取设置值
     *
     * @param apiVersion Api版本
     * @param setKey     设置编码
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getValueBySetKey(MsOnionApiVersion apiVersion, String setKey) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(setKey)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "系统设置编码为空");
        }
        SysSettingExample example = new SysSettingExample();
        SysSettingExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andSettingKeyEqualTo(setKey.trim());
        List<SysSetting> list = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            return MsOnionResult.ok(list.get(0));
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.POJO_NOT_NULL);
    }
}
