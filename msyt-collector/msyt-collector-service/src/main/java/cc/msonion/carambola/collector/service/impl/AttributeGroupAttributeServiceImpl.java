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
 * @Title: AttributeGroupAttributeServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: AttributeGroupAttribute业务逻辑的实现
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
import cc.msonion.carambola.collector.pojo.CollectorAttribute;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroup;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupAttribute;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupAttributeExample;
import cc.msonion.carambola.collector.service.AttributeGroupAttributeService;
import cc.msonion.carambola.collector.service.AttributeService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: AttributeGroupAttributeServiceImpl
 * @Description: 属性组属性Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月11日 下午4:40:21
 */
@Service
public class AttributeGroupAttributeServiceImpl extends MsOnionServiceExt<CollectorAttributeGroupAttribute, CollectorAttributeGroupAttributeExample>
        implements AttributeGroupAttributeService {
    /**
     * 逗号分隔符
     */
    public static final String SEPARATOR_COMMA = ",";

    /**
     * 属性服务
     */
    @Autowired
    private AttributeService attributeService;

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
     * 新增属性组属性
     *
     * @param apiVersion              Api版本
     * @param attributeGroupAttribute
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addAttributeGroupAttribute(MsOnionApiVersion apiVersion, CollectorAttributeGroupAttribute attributeGroupAttribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == attributeGroupAttribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ATTRIBUTE_GROUP_ATTRIBUTE_INFO_NULL);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_ATTRIBUTE_IDX_PATH);
        attributeGroupAttribute.setIdx(idx);
        attributeGroupAttribute.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_GROUP_ATTRIBUTE_VERSION_PATH);
        attributeGroupAttribute.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，attributeGroupAttribute=" + attributeGroupAttribute);

        int result = this.save(apiVersion, attributeGroupAttribute);
        if (result > 0) {
            return MsOnionResult.ok(attributeGroupAttribute);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 批量新增属性组属性
     *
     * @param apiVersion     Api版本
     * @param attributeGroup 属性组
     * @param attributes     属性
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult batchAddAttributeGroupAttribute(MsOnionApiVersion apiVersion, CollectorAttributeGroup attributeGroup, String attributes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(attributes)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_SELECT_ATTRIBUTE_FIRST);
        }

        String[] attrs = attributes.split(SEPARATOR_COMMA);
        for (String attr : attrs) {
            if (StringUtils.isNotBlank(attr)) {
                Long attributeIdx = Long.valueOf(attr);

                CollectorAttributeGroupAttribute attributeGroupAttribute = new CollectorAttributeGroupAttribute();
                attributeGroupAttribute.setAttributeGroupIdx(attributeGroup.getIdx());
                attributeGroupAttribute.setAttributeIdx(attributeIdx);
                attributeGroupAttribute.setCreateByMemberIdx(attributeGroup.getCreateByMemberIdx());
                attributeGroupAttribute.setUpdateByMemberIdx(attributeGroup.getUpdateByMemberIdx());

                MsOnionResult result = addAttributeGroupAttribute(apiVersion, attributeGroupAttribute);
                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }
            }
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteAttributeGroupAttribute
     * @Description 删除属性组属性
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午4:40:21
     */
    @Override
    public MsOnionResult deleteAttributeGroupAttribute(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorAttributeGroupAttribute attributeGroupAttribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == attributeGroupAttribute) {
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
     * @param attributeGroupAttribute
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateAttributeGroupAttribute
     * @Description 更新属性组属性
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午4:40:21
     */
    @Override
    public MsOnionResult updateAttributeGroupAttribute(MsOnionApiVersion apiVersion, CollectorAttributeGroupAttribute attributeGroupAttribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == attributeGroupAttribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ATTRIBUTE_GROUP_ATTRIBUTE_INFO_NULL);
        }

        attributeGroupAttribute.setUpdateTime(new Date());
        //设置修改成员idx
//        attributeGroupAttribute.setUpdateByMemberIdx();

        int result = this.updateByPrimaryKey(apiVersion, attributeGroupAttribute);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, attributeGroupAttribute);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the attribute by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getAttributeGroupAttributeByIdx
     * @Description 通过主键idx得到属性组属性
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月11日 下午4:40:21
     */
    @Override
    public MsOnionResult getAttributeGroupAttributeByIdx(MsOnionApiVersion apiVersion, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorAttributeGroupAttribute attributeGroupAttribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == attributeGroupAttribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, attributeGroupAttribute);
    }

    /**
     * 获取属性
     *
     * @param apiVersion        Api版本
     * @param attributeGroupIdx 属性组主键idx
     * @return 返回属性
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getAttributeByAttributeGroupIdx(MsOnionApiVersion apiVersion, Long attributeGroupIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult rs = this.getAttributeGroupAttributeByAttributeGroupIdx(apiVersion, attributeGroupIdx);
        if (null == rs.getData()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ATTRIBUTE);
        }

        List<CollectorAttribute> attributeList = new ArrayList<>();
        List<CollectorAttributeGroupAttribute> attributeGroupAttributeList = (List<CollectorAttributeGroupAttribute>) rs.getData();
        for (CollectorAttributeGroupAttribute attributeGroupAttribute : attributeGroupAttributeList) {
            Long attributeIdx = attributeGroupAttribute.getAttributeIdx();

            MsOnionResult result = attributeService.getAttributeByIdx(apiVersion, attributeIdx);
            if (null != result.getData()) {
                CollectorAttribute attribute = (CollectorAttribute) result.getData();
                if (attribute.getStatus() == MsOnionTableRecordStatus.NORMAL.getValue()) {
                    attributeList.add(attribute);
                }
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, attributeList);
    }

    /**
     * 获取属性组属性
     *
     * @param apiVersion        Api版本
     * @param attributeGroupIdx 属性组主键idx
     * @return 返回属性组属性
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getAttributeGroupAttributeByAttributeGroupIdx(MsOnionApiVersion apiVersion, Long attributeGroupIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorAttributeGroupAttributeExample example = new CollectorAttributeGroupAttributeExample();
        CollectorAttributeGroupAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andAttributeGroupIdxEqualTo(attributeGroupIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorAttributeGroupAttribute> attributeGroupAttributeList = this.queryByExample(apiVersion, example);
        if (null == attributeGroupAttributeList || attributeGroupAttributeList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ATTRIBUTE);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, attributeGroupAttributeList);
    }
}
