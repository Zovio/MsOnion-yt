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
 * @Title: AttributeGroupServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: AttributeGroup业务逻辑的实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午4:40:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月11日 下午4:40:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.AttributeGroupAttributeService;
import cc.msonion.carambola.collector.service.AttributeGroupService;
import cc.msonion.carambola.collector.service.ItemCategoryService;
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
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: AttributeGroupServiceImpl
 * @Description: 属性组Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午4:40:21
 */
@Service
public class AttributeGroupServiceImpl extends MsOnionServiceExt<CollectorAttributeGroup, CollectorAttributeGroupExample>
        implements AttributeGroupService {
    /**
     * 逗号分隔符
     */
    public static final String SEPARATOR_COMMA = ",";


    /**
     * 商品类目 Service
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 属性组属性服务
     */
    @Autowired
    private AttributeGroupAttributeService attributeGroupAttributeService;

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

        CollectorAttributeGroupExample example = new CollectorAttributeGroupExample();
        CollectorAttributeGroupExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (ParamTypeUtils.TYPE_ATTRIBUTE_GROUP_NAME == type) {
            criteria.andNameEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        if (null != exclude && ItemConstants.DEFAULT_PRESENT_IDX < exclude) {
            criteria.andIdxNotEqualTo(exclude);
        }

        List<CollectorAttributeGroup> list = this.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "不存在，可以使用" + ParamTypeUtils.getDescription(type), param);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "已存在，不可使用" + ParamTypeUtils.getDescription(type), param);
    }

    /**
     * 新增属性组
     *
     * @param apiVersion     Api版本
     * @param attributeGroup
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addAttributeGroup(MsOnionApiVersion apiVersion, CollectorAttributeGroup attributeGroup)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == attributeGroup) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ATTRIBUTE_GROUP_INFO_NULL);
        }

        // 属性组名称唯一性判断
        String name = attributeGroup.getName();
        if (StringUtils.isNotBlank(name)) {
            MsOnionResult result = inspectParameter(apiVersion, name, ParamTypeUtils.TYPE_ATTRIBUTE_GROUP_NAME);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_IDX_PATH);
        attributeGroup.setIdx(idx);
        attributeGroup.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_VERSION_PATH);
        attributeGroup.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，attributeGroup=" + attributeGroup);

        int result = this.save(apiVersion, attributeGroup);
        if (result > 0) {
            return MsOnionResult.ok(attributeGroup);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 新增属性组及组属性
     *
     * @param apiVersion     Api版本
     * @param attributeGroup 属性组
     * @param attributes     属性
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addAttributeGroupAndAttribute(MsOnionApiVersion apiVersion, CollectorAttributeGroup attributeGroup, String attributes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = addAttributeGroup(apiVersion, attributeGroup);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        CollectorAttributeGroup attributeGroup1 = (CollectorAttributeGroup) result.getData();
        result = attributeGroupAttributeService.batchAddAttributeGroupAttribute(apiVersion, attributeGroup1, attributes);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteAttributeGroup
     * @Description 删除属性组
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午4:40:21
     */
    @Override
    public MsOnionResult deleteAttributeGroup(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorAttributeGroup attributeGroup = this.queryByPrimaryKey(apiVersion, idx);
        if (null == attributeGroup) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        // 若有商品正在应用此属性，则提示删除失败，该属性组有商品。
        CollectorItemCategoryExample example = new CollectorItemCategoryExample();
        CollectorItemCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andAttributeGroupIdxEqualTo(idx);
        criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);

        List<CollectorItemCategory> itemList = itemCategoryService.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(itemList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "删除失败，有类目正在应用此属性组", attributeGroup);
        }

        // 删除属性组属性关联
        MsOnionResult rs = attributeGroupAttributeService.getAttributeGroupAttributeByAttributeGroupIdx(apiVersion, idx);
        if (null != rs.getData()) {
            List<CollectorAttributeGroupAttribute> attributeGroupAttributeList = (List<CollectorAttributeGroupAttribute>) rs.getData();
            for (CollectorAttributeGroupAttribute attributeGroupAttribute : attributeGroupAttributeList) {
                if (null != attributeGroupAttribute) {
                    Long attributeGroupAttributeIdx = attributeGroupAttribute.getIdx();
                    MsOnionResult result = attributeGroupAttributeService.deleteAttributeGroupAttribute(apiVersion, attributeGroupAttributeIdx);
                    if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                        return result;
                    }
                }
            }
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 批量删除属性组
     *
     * @param apiVersion Api版本
     * @param ids        属性组主键idx集合
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult batchDeleteAttributeGroup(MsOnionApiVersion apiVersion, String[] ids)
            throws MsOnionIllegalArgumentException, MsOnionException {
        for (String idxStr : ids) {
            if (StringUtils.isBlank(idxStr)) {
                continue;
            }

            Long idx = Long.parseLong(idxStr);

            MsOnionResult result = deleteAttributeGroup(apiVersion, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param attributeGroup
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateAttributeGroup
     * @Description 更新属性组
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午4:40:21
     */
    @Override
    public MsOnionResult updateAttributeGroup(MsOnionApiVersion apiVersion, CollectorAttributeGroup attributeGroup)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == attributeGroup) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ATTRIBUTE_GROUP_INFO_NULL);
        }

        Long idx = attributeGroup.getIdx();
        // 属性组名称唯一性判断
        String name = attributeGroup.getName();
        if (StringUtils.isNotBlank(name)) {
            MsOnionResult result = inspectParameter(apiVersion, name, ParamTypeUtils.TYPE_ATTRIBUTE_GROUP_NAME, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        CollectorAttributeGroup attributeGroup1 = this.queryByPrimaryKey(apiVersion, idx);
        attributeGroup1.setName(attributeGroup.getName());
        attributeGroup1.setUpdateTime(new Date());
        attributeGroup1.setUpdateByMemberIdx(attributeGroup.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, attributeGroup1);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, attributeGroup);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 更新属性组及组属性
     *
     * @param apiVersion     Api版本
     * @param attributeGroup 属性组
     * @param attributes     属性
     * @return 返回更新结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateAttributeGroupAndAttribute(MsOnionApiVersion apiVersion, CollectorAttributeGroup attributeGroup, String attributes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = updateAttributeGroup(apiVersion, attributeGroup);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        Long idx = attributeGroup.getIdx();
        Long updateByMemberIdx = attributeGroup.getUpdateByMemberIdx();

        CollectorAttributeGroupAttributeExample example = new CollectorAttributeGroupAttributeExample();
        CollectorAttributeGroupAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andAttributeGroupIdxEqualTo(idx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorAttributeGroupAttribute> list = attributeGroupAttributeService.queryByExample(apiVersion, example);
        for (CollectorAttributeGroupAttribute attributeGroupAttribute : list) {
            if (null != attributeGroupAttribute) {
                attributeGroupAttribute.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
                attributeGroupAttribute.setUpdateByMemberIdx(updateByMemberIdx);
                attributeGroupAttribute.setUpdateTime(new Date());

                result = attributeGroupAttributeService.updateAttributeGroupAttribute(apiVersion, attributeGroupAttribute);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }
            }
        }

        result = attributeGroupAttributeService.batchAddAttributeGroupAttribute(apiVersion, attributeGroup, attributes);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the attributeGroup by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getAttributeGroupByIdx
     * @Description 通过主键idx得到属性组
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午4:40:21
     */
    @Override
    public MsOnionResult getAttributeGroupByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorAttributeGroup attributeGroup = this.queryByPrimaryKey(apiVersion, idx);
        if (null == attributeGroup) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, attributeGroup);
    }

    /**
     * 分页查询属性组列表
     * @param apiVersion 版本
     * @param groupName 属性组名称
     * @param pageIndex 第几页
     * @param pageSize 每页多少条
     * @return 属性组列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResultAdapter getAttributeGroup(MsOnionApiVersion apiVersion, String groupName,
                                                  Integer pageIndex, Integer pageSize) throws MsOnionException {
        CollectorAttributeGroupExample example = new CollectorAttributeGroupExample();
        CollectorAttributeGroupExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (StringUtils.isNotBlank(groupName)) {
            criteria.andNameLike("%" + groupName + "%");
        }

        return this.queryListByPageForResult(apiVersion, example, pageIndex, pageSize);

    }
}
