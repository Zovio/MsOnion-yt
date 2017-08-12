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
 * @Title: LogisticsUnitServiceImpl.java
 * @Package: cc.msonion.carambola.logistics.service.impl
 * @Description: 计量单位服务业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017年7月19日 下午2:26:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017年7月19日 下午2:26:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.logistics.common.utils.ParamTypeUtils;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclare;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareExample;
import cc.msonion.carambola.logistics.pojo.LogisticsUnit;
import cc.msonion.carambola.logistics.pojo.LogisticsUnitExample;
import cc.msonion.carambola.logistics.service.LogisticsCustomsDeclareService;
import cc.msonion.carambola.logistics.service.LogisticsUnitService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: LogisticsUnitServiceImpl
 * @Description: 计量单位服务业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017年7月19日 下午2:26:21
 */
@Service
public class LogisticsUnitServiceImpl extends MsOnionServiceExt<LogisticsUnit, LogisticsUnitExample> implements LogisticsUnitService {

    /**
     * 报关信息服务
     */
    @Autowired
    private LogisticsCustomsDeclareService logisticsCustomsDeclareService;

    /**
     * 检查是否已存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数类型 {@link ParamTypeUtils}
     * @return 返回检查结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return inspectParameter(apiVersion, param, type, null);
    }

    /**
     * 检查是否已存在
     *
     * @param apiVersion Api版本
     * @param param      参数值
     * @param type       参数类型 {@link ParamTypeUtils}
     * @param exclude    排除：主键idx
     * @return 返回检查结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, String param, Integer type, Long exclude)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        LogisticsUnitExample example = new LogisticsUnitExample();
        LogisticsUnitExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (ParamTypeUtils.TYPE_UNIT_CODE == type) {
            criteria.andCodeEqualTo(param);
        } else if (ParamTypeUtils.TYPE_UNIT_NAME == type) {
            criteria.andNameEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        if (null != exclude && 0 < exclude) {
            criteria.andIdxNotEqualTo(exclude);
        }

        List<LogisticsUnit> list = this.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "不存在，可以使用" + ParamTypeUtils.getDescription(type), param);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "已存在，不可使用" + ParamTypeUtils.getDescription(type), param);
    }

    /**
     * 新增计量单位
     *
     * @param apiVersion    Api版本
     * @param logisticsUnit 计量单位信息
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addLogisticsUnit(MsOnionApiVersion apiVersion, LogisticsUnit logisticsUnit)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 计量单位编码唯一性判断
        String code = logisticsUnit.getCode();
        if (StringUtils.isNotBlank(code)) {
            MsOnionResult result = inspectParameter(apiVersion, code, ParamTypeUtils.TYPE_UNIT_CODE);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 计量单位名称唯一性判断
        String name = logisticsUnit.getName();
        if (StringUtils.isNotBlank(name)) {
            MsOnionResult result = inspectParameter(apiVersion, name, ParamTypeUtils.TYPE_UNIT_NAME);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_UNIT_IDX_PATH);
        logisticsUnit.setIdx(idx);

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_LOGISTICS_UNIT_VERSION_PATH);
        logisticsUnit.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，logisticsUnit=" + logisticsUnit);

        int result = this.save(apiVersion, logisticsUnit);
        if (result > 0) {
            return MsOnionResult.ok(logisticsUnit);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 删除计量单位
     *
     * @param apiVersion Api版本
     * @param idx        计量单位主键idx
     * @return 返回删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author Johnny woo johnny-woo@msyc.cc
     * @Date 2017年7月19日 下午2:26:21
     */
    @Override
    public MsOnionResult deleteLogisticsUnit(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        LogisticsUnit logisticsUnit = this.queryByPrimaryKey(apiVersion, idx);
        if (null == logisticsUnit) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        Short status = logisticsUnit.getStatus();
        if (MsOnionTableRecordStatus.DELETED.getValue() == status) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_ILLEGAL_OPERATION, logisticsUnit);
        }

        // 若计量单位已使用，则不能删除
        LogisticsCustomsDeclareExample example = new LogisticsCustomsDeclareExample();
        example.or().andFirstUnitIdxEqualTo(idx).andSecondUnitIdxEqualTo(idx);

        List<LogisticsCustomsDeclare> logisticsCustomsDeclareList = logisticsCustomsDeclareService.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(logisticsCustomsDeclareList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "删除失败，该计量单位已使用", logisticsUnit);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 批量删除计量单位
     *
     * @param apiVersion Api版本
     * @param ids        计量单位主键idx集合
     * @return 返回批量删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult batchDeleteLogisticsUnit(MsOnionApiVersion apiVersion, String[] ids)
            throws MsOnionIllegalArgumentException, MsOnionException {
        for (String idxStr : ids) {
            if (StringUtils.isBlank(idxStr)) {
                continue;
            }

            Long idx = Long.parseLong(idxStr);

            MsOnionResult result = deleteLogisticsUnit(apiVersion, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * 更新计量单位
     *
     * @param apiVersion    Api版本
     * @param logisticsUnit 计量单位信息
     * @return 返回更新结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author Johnny woo johnny-woo@msyc.cc
     * @Date 2017年7月19日 下午2:26:21
     */
    @Override
    public MsOnionResult updateLogisticsUnit(MsOnionApiVersion apiVersion, LogisticsUnit logisticsUnit)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = logisticsUnit.getIdx();

        // 计量单位编码唯一性判断
        String code = logisticsUnit.getCode();
        if (StringUtils.isNotBlank(code)) {
            MsOnionResult result = inspectParameter(apiVersion, code, ParamTypeUtils.TYPE_UNIT_CODE, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 计量单位名称唯一性判断
        String name = logisticsUnit.getName();
        if (StringUtils.isNotBlank(name)) {
            MsOnionResult result = inspectParameter(apiVersion, name, ParamTypeUtils.TYPE_UNIT_NAME, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        LogisticsUnit temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setCode(code);
        temp.setName(name);
        temp.setStatus(logisticsUnit.getStatus());
        temp.setRemark(logisticsUnit.getRemark());
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(logisticsUnit.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 获取所有计量单位
     *
     * @param apiVersion Api版本
     * @return 返回所有计量单位
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public List<LogisticsUnit> getAllLogisticsUnit(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {
        LogisticsUnitExample example = new LogisticsUnitExample();
        LogisticsUnitExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<LogisticsUnit> logisticsUnitList = this.queryByExample(apiVersion, example);
        return logisticsUnitList;
    }
}
