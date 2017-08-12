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


package cc.msonion.carambola.warehouse.service.impl;

/**
 * @Title: WarehouseTypeServiceImpl.java
 * @Package: cc.msonion.carambola.warehouse.service.impl
 * @Description: 商品仓库类型service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月21日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月21日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import cc.msonion.carambola.warehouse.common.constans.WarehouseConstants;
import cc.msonion.carambola.warehouse.pojo.WarehouseType;
import cc.msonion.carambola.warehouse.pojo.WarehouseTypeExample;
import cc.msonion.carambola.warehouse.service.WarehouseTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: WarehouseTypeServiceImpl
 * @Description: 商品仓库类型service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月21日
 *
 */
@Service
public class WarehouseTypeServiceImpl extends MsOnionServiceExt<WarehouseType, WarehouseTypeExample> implements WarehouseTypeService {


    /**
     * @param apiVersion Api版本
     * @param type       类型
     * @param param      参数
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     * @Description: 检查参数
     */
    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion, Integer type,
                                          String param) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(param) || type <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }
        if (WarehouseConstants.NAME_TYPE == type) {
            WarehouseTypeExample example = new WarehouseTypeExample();
            WarehouseTypeExample.Criteria criteria = example.createCriteria();
            criteria.andWarehouseNameEqualTo(param.trim());
            List<WarehouseType> list = queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(list)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.NAME_EXITE_MESSAGE);
            }
        } else if (WarehouseConstants.CODE_TYPE == type) {
            WarehouseTypeExample example = new WarehouseTypeExample();
            WarehouseTypeExample.Criteria criteria = example.createCriteria();
            criteria.andWarehouseCodeEqualTo(Short.valueOf(param.trim()));
            List<WarehouseType> list = queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(list)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.CODE_EXITE_MESSAGE);
            }
        } else if (WarehouseConstants.MANTISSA_TYPE == type) {
            WarehouseTypeExample example = new WarehouseTypeExample();
            WarehouseTypeExample.Criteria criteria = example.createCriteria();
            criteria.andMantissaEqualTo(Short.valueOf(param.trim()));
            List<WarehouseType> list = queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(list)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MANTISSA_EXITE_MESSAGE);
            }
        }
        return MsOnionResult.ok();
    }

    /**
     * 新增或者更新商品仓库类型
     *
     * @param apiVersion    Api版本
     * @param warehouseType 商品仓库类型
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Date 2017年4月11日 下午8:21:21
     */
    @Override
    public MsOnionResult addOrUpdateWarehouseType(MsOnionApiVersion apiVersion, WarehouseType warehouseType)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == warehouseType) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, WarehouseConstants.MESSAGE_WAREHOUSETYPE_INFO_NULL);
            }

            if (warehouseType.getIdx() == null) {
                // add
                warehouseType.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(warehouseType);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }

                // 校验名称是否唯一
                MsOnionResult result1 = inspectParameter(apiVersion, WarehouseConstants.NAME_TYPE, warehouseType.getWarehouseName());
                if (result1.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result1.getMsg());
                }
                // 校验编码是否唯一
                MsOnionResult result2 = inspectParameter(apiVersion, WarehouseConstants.CODE_TYPE, warehouseType.getWarehouseCode().toString());
                if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result2.getMsg());
                }
                // 校验货号尾数是否唯一
                MsOnionResult result3 = inspectParameter(apiVersion, WarehouseConstants.MANTISSA_TYPE, warehouseType.getMantissa().toString());
                if (result3.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result3.getMsg());
                }


                // 生成Idx
                long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSETYPE_IDX_PATH);

                warehouseType.setIdx(idx);

                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_WAREHOUSE_WAREHOUSETYPE_VERSION_PATH);
                warehouseType.setVersion(version);

                int result = this.save(apiVersion, warehouseType);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 商品仓库类型新增成功, idx = " + idx);
                    return MsOnionResult.ok();
                }
            } else {
                // update
                WarehouseType wht = this.queryByPrimaryKey(apiVersion, warehouseType.getIdx());
                warehouseType.setMantissa(wht.getMantissa());
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(warehouseType);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }

                if (!wht.getWarehouseName().equals(warehouseType.getWarehouseName())) {
                    // 校验名称是否唯一
                    MsOnionResult result1 = inspectParameter(apiVersion, WarehouseConstants.NAME_TYPE, warehouseType.getWarehouseName());
                    if (result1.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result1.getMsg());
                    }
                }

                if (!wht.getWarehouseCode().equals(warehouseType.getWarehouseCode())) {
                    // 校验编码是否唯一
                    MsOnionResult result2 = inspectParameter(apiVersion, WarehouseConstants.CODE_TYPE, warehouseType.getWarehouseCode().toString());
                    if (result2.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, result2.getMsg());
                    }
                }
                wht.setZindex(warehouseType.getZindex());
                wht.setWarehouseName(warehouseType.getWarehouseName());
                wht.setWarehouseCode(warehouseType.getWarehouseCode());
                wht.setRemark(warehouseType.getRemark());
                wht.setUpdateByMemberIdx(warehouseType.getUpdateByMemberIdx());
                wht.setUpdateTime(new Date());
                int result = this.updateByPrimaryKey(apiVersion, wht);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 商品仓库类型更新成功, idx = " + wht.getIdx());
                    return MsOnionResult.ok();
                }
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取所有有效的仓库
     *
     * @param apiVersion 版本号
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getWarehouseList(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {
        WarehouseTypeExample example = new WarehouseTypeExample();
        WarehouseTypeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        example.setOrderByClause(WarehouseConstants.ORDER_BY_ZINDEX_DESC);
        List<WarehouseType> list = queryByExample(apiVersion, example);
        return MsOnionResult.ok(list);
    }
}
