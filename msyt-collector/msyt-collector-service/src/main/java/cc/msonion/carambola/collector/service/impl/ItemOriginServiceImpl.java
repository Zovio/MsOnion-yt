/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营产地洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际产地直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: ItemOriginServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: ItemOrigin业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月1日 下午2:26:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.collector.pojo.CollectorItemOrigin;
import cc.msonion.carambola.collector.pojo.CollectorItemOriginExample;
import cc.msonion.carambola.collector.service.ItemOriginService;
import cc.msonion.carambola.collector.service.ItemService;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemOriginServiceImpl
 * @Description: 商品产地Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 */
@Service
public class ItemOriginServiceImpl extends MsOnionServiceExt<CollectorItemOrigin, CollectorItemOriginExample> implements ItemOriginService {
    /**
     * 商品 Service
     */
    @Autowired
    private ItemService itemService;

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

        CollectorItemOriginExample example = new CollectorItemOriginExample();
        CollectorItemOriginExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (ParamTypeUtils.TYPE_ORIGIN_CODE == type) {
            criteria.andOriginCodeEqualTo(param);
        } else if (ParamTypeUtils.TYPE_ORIGIN_CN_NAME == type) {
            criteria.andCnNameEqualTo(param);
        } else if (ParamTypeUtils.TYPE_ORIGIN_EN_NAME == type) {
            criteria.andEnNameEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        if (null != exclude) {
            criteria.andIdxNotEqualTo(exclude);
        }

        List<CollectorItemOrigin> list = this.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "不存在，可以使用" + ParamTypeUtils.getDescription(type), param);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "已存在，不可使用" + ParamTypeUtils.getDescription(type), param);
    }

    /**
     * 新增商品产地
     *
     * @param apiVersion Api版本
     * @param itemOrigin 商品产地
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemOrigin(MsOnionApiVersion apiVersion, CollectorItemOrigin itemOrigin)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查产地编码唯一性
        String originCode = itemOrigin.getOriginCode();
        if (StringUtils.isNotBlank(originCode)) {
            MsOnionResult result = inspectParameter(apiVersion, originCode, ParamTypeUtils.TYPE_ORIGIN_CODE);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 检查产地中文名称唯一性
        String originCnName = itemOrigin.getCnName();
        if (StringUtils.isNotBlank(originCnName)) {
            MsOnionResult result = inspectParameter(apiVersion, originCnName, ParamTypeUtils.TYPE_ORIGIN_CN_NAME);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 检查产地英文名称唯一性
        String originEnName = itemOrigin.getEnName();
        if (StringUtils.isNotBlank(originEnName)) {
            MsOnionResult result = inspectParameter(apiVersion, originEnName, ParamTypeUtils.TYPE_ORIGIN_EN_NAME);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 产地图片
        itemOrigin.setImageMiddle(itemOrigin.getImageBig());
        itemOrigin.setImageSmall(itemOrigin.getImageBig());

        // 国旗图片
        itemOrigin.setNationalFlagImageSmall(itemOrigin.getNationalFlagImageBig());
        itemOrigin.setNationalFlagImageMiddle(itemOrigin.getNationalFlagImageBig());


        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ORIGIN_IDX_PATH);
        itemOrigin.setIdx(idx);

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ORIGIN_VERSION_PATH);
        itemOrigin.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemOrigin=" + itemOrigin);

        int result = this.save(apiVersion, itemOrigin);
        if (result > 0) {
            return MsOnionResult.ok(itemOrigin);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 批量删除产地信息
     *
     * @param apiVersion Api版本
     * @param ids        产地主键idx集合
     * @return 返回批量删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult batchDeleteItemOrigin(MsOnionApiVersion apiVersion, String[] ids) throws MsOnionIllegalArgumentException, MsOnionException {
        for (String idxStr : ids) {
            if (StringUtils.isBlank(idxStr)) {
                continue;
            }

            Long idx = Long.parseLong(idxStr);

            MsOnionResult result = this.deleteItemOrigin(apiVersion, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteItemOrigin
     * @Description 删除商品产地
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult deleteItemOrigin(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemOrigin itemOrigin = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemOrigin) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        Short status = itemOrigin.getStatus();
        if (MsOnionTableRecordStatus.DELETED.getValue() == status) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_ILLEGAL_OPERATION, itemOrigin);
        }

        // 若产地有商品，则不能删除
        CollectorItemExample example = new CollectorItemExample();
        CollectorItemExample.Criteria criteria = example.createCriteria();
        criteria.andOriginIdxEqualTo(idx);
        criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());

        List<CollectorItem> itemList = itemService.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "删除失败，该产地有商品", itemOrigin);
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
     * @param itemOrigin
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemOrigin
     * @Description 更新商品产地
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult updateItemOrigin(MsOnionApiVersion apiVersion, CollectorItemOrigin itemOrigin)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemOrigin) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_ORIGIN_NULL);
        }

        // 检查产地编码唯一性
        Long idx = itemOrigin.getIdx();
        String originCode = itemOrigin.getOriginCode();
        if (StringUtils.isNotBlank(originCode)) {
            MsOnionResult result = inspectParameter(apiVersion, originCode, ParamTypeUtils.TYPE_ORIGIN_CODE, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 检查产地中文名称唯一性
        String originCnName = itemOrigin.getCnName();
        if (StringUtils.isNotBlank(originCnName)) {
            MsOnionResult result = inspectParameter(apiVersion, originCnName, ParamTypeUtils.TYPE_ORIGIN_CN_NAME, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 检查产地英文名称唯一性
        String originEnName = itemOrigin.getEnName();
        if (StringUtils.isNotBlank(originEnName)) {
            MsOnionResult result = inspectParameter(apiVersion, originEnName, ParamTypeUtils.TYPE_ORIGIN_EN_NAME, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        // 当状态为禁用时，若产地有商品，则不能禁用
        Short status = itemOrigin.getStatus();
        if (MsOnionTableRecordStatus.DISABLE.getValue() == status) {
            CollectorItemExample example = new CollectorItemExample();
            CollectorItemExample.Criteria criteria = example.createCriteria();
            criteria.andOriginIdxEqualTo(idx);
            criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());

            List<CollectorItem> itemList = itemService.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "不能禁用，该产地有商品", itemOrigin);
            }
        }

        CollectorItemOrigin itemOrigin1 = this.queryByPrimaryKey(apiVersion, idx);
        itemOrigin1.setOriginCode(originCode);
        itemOrigin1.setCustomsCode(itemOrigin.getCustomsCode());
        itemOrigin1.setCnName(itemOrigin.getCnName());
        itemOrigin1.setEnName(itemOrigin.getEnName());
        itemOrigin1.setStatus(status);
        itemOrigin1.setUpdateTime(new Date());
        itemOrigin1.setUpdateByMemberIdx(itemOrigin.getUpdateByMemberIdx());

        // 产地图片
        itemOrigin1.setImageBig(itemOrigin.getImageBig());
        itemOrigin1.setImageMiddle(itemOrigin.getImageBig());
        itemOrigin1.setImageSmall(itemOrigin.getImageBig());

        // 国旗图片
        itemOrigin1.setNationalFlagImageSmall(itemOrigin.getNationalFlagImageBig());
        itemOrigin1.setNationalFlagImageMiddle(itemOrigin.getNationalFlagImageBig());
        itemOrigin1.setNationalFlagImageBig(itemOrigin.getNationalFlagImageBig());


        int result = this.updateByPrimaryKey(apiVersion, itemOrigin1);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, itemOrigin);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the itemOrigin by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getItemOriginByIdx
     * @Description 通过主键idx得到商品产地
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult getItemOriginByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemOrigin itemOrigin = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemOrigin) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemOrigin);
    }

    /**
     * 获取所有商品产地
     *
     * @param apiVersion Api版本
     * @return 返回所有商品产地
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public List<CollectorItemOrigin> getAllItemOrigin(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemOriginExample example = new CollectorItemOriginExample();
        CollectorItemOriginExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemOrigin> itemOriginList = this.queryByExample(apiVersion, example);
        return itemOriginList;
    }
}
