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
 * @Title: AttributeServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: Attribute业务逻辑的实现
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
import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.common.constants.OrderByConstants;
import cc.msonion.carambola.collector.common.utils.ParamTypeUtils;
import cc.msonion.carambola.collector.pojo.CollectorAttribute;
import cc.msonion.carambola.collector.pojo.CollectorAttributeExample;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupAttribute;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupAttributeExample;
import cc.msonion.carambola.collector.service.AttributeGroupAttributeService;
import cc.msonion.carambola.collector.service.AttributeService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: AttributeServiceImpl
 * @Description: 属性Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月1日 下午2:26:21
 */
@Service
public class AttributeServiceImpl extends MsOnionServiceExt<CollectorAttribute, CollectorAttributeExample> implements AttributeService {
    /**
     * 属性组属性 Service
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

        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (ParamTypeUtils.TYPE_ATTRIBUTE_NAME == type) {
            criteria.andNameEqualTo(param);
        } else {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        if (null != exclude && ItemConstants.DEFAULT_PRESENT_IDX < exclude) {
            criteria.andIdxNotEqualTo(exclude);
        }

        List<CollectorAttribute> list = this.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(list)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "不存在，可以使用" + ParamTypeUtils.getDescription(type), param);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "已存在，不可使用" + ParamTypeUtils.getDescription(type), param);
    }

    /**
     * 新增属性
     *
     * @param apiVersion Api版本
     * @param attribute
     * @return ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult addAttribute(MsOnionApiVersion apiVersion, CollectorAttribute attribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == attribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_INFO_NULL);
        }

        // 属性名称唯一性判断
        String name = attribute.getName();
        if (StringUtils.isNotBlank(name)) {
            MsOnionResult result = inspectParameter(apiVersion, name, ParamTypeUtils.TYPE_ATTRIBUTE_NAME);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_IDX_PATH);
        attribute.setIdx(idx);
        attribute.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ATTRIBUTE_VERSION_PATH);
        attribute.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，attribute=" + attribute);

        int result = this.save(apiVersion, attribute);
        if (result > 0) {
            return MsOnionResult.ok(attribute);
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
     * @Title deleteAttribute
     * @Description 删除属性
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult deleteAttribute(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorAttribute attribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == attribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        // 若有属性组正在应用此属性，则提示删除失败，该属性已被占用
        CollectorAttributeGroupAttributeExample example = new CollectorAttributeGroupAttributeExample();
        CollectorAttributeGroupAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andAttributeIdxEqualTo(idx);
        criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);

        List<CollectorAttributeGroupAttribute> attributeList = attributeGroupAttributeService.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(attributeList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "删除失败，有属性组正在应用此属性", attribute);
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
     * @param attribute
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateAttribute
     * @Description 更新属性
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult updateAttribute(MsOnionApiVersion apiVersion, CollectorAttribute attribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == attribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_BRAND_NULL);
        }

        Long idx = attribute.getIdx();
        // 属性名称唯一性判断
        String name = attribute.getName();
        if (StringUtils.isNotBlank(name)) {
            MsOnionResult result = inspectParameter(apiVersion, name, ParamTypeUtils.TYPE_ATTRIBUTE_NAME, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }

        CollectorAttribute attribute1 = this.queryByPrimaryKey(apiVersion, idx);
        attribute1.setName(attribute.getName());
        attribute1.setIsSelect(attribute.getIsSelect());
        attribute1.setIsShow(attribute.getIsShow());
        attribute1.setRemark(attribute.getRemark());
        attribute1.setUpdateTime(new Date());
        attribute1.setUpdateByMemberIdx(attribute.getUpdateByMemberIdx());
        attribute1.setIsKey(attribute.getIsKey());

        int result = this.updateByPrimaryKey(apiVersion, attribute1);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, attribute);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * @param apiVersion
     * @param idx
     * @return the attribute by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getAttributeByIdx
     * @Description 通过主键idx得到属性
     * @Author Johnny woo 3028554324@qq.com
     * @Date 2017年4月1日 下午2:26:21
     */
    @Override
    public MsOnionResult getAttributeByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorAttribute attribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == attribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, attribute);
    }

    /**
     * 获取属性树结构
     *
     * @param apiVersion Api版本
     * @param attribute  查询条件
     * @return 返回属性树结构
     * @throws MsOnionIllegalArgumentException 无效参数类型异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<Map<String, Object>> getAttributeTree(MsOnionApiVersion apiVersion, CollectorAttribute attribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andPidxEqualTo(ItemConstants.DEFAULT_PRESENT_IDX);
        criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);

        String name = attribute.getName();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(MsOnionConstants.PERCENT + name.trim() + MsOnionConstants.PERCENT);
        }

        if (null != attribute.getIsSelect()) {
            criteria.andIsSelectEqualTo(attribute.getIsSelect());
        }
        if (null != attribute.getIsShow()) {
            criteria.andIsShowEqualTo(attribute.getIsShow());
        }

        example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
        List<Map<String, Object>> list = new ArrayList<>();
        List<CollectorAttribute> attributeList = this.queryByExample(apiVersion, example);
        Iterator<CollectorAttribute> it = attributeList.iterator();
        while (it.hasNext()) {
            CollectorAttribute attribute1 = it.next();
            Long idx1 = attribute1.getIdx();

            Map<String, Object> map = generateTree(apiVersion, idx1);
            list.add(map);
        }

        return list;
    }

    /**
     * 按topid获取属性列表
     * @param apiVersion 版本
     * @param attribute 查询条件
     * @return 属性列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<Map<String, Object>> getAttributeByTopId(MsOnionApiVersion apiVersion, CollectorAttribute attribute)
            throws MsOnionException {
        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);

        String name = attribute.getName();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(MsOnionConstants.PERCENT + name.trim() + MsOnionConstants.PERCENT);
        }

        if (null != attribute.getIsSelect()) {
            criteria.andIsSelectEqualTo(attribute.getIsSelect());
        }
        if (null != attribute.getIsShow()) {
            criteria.andIsShowEqualTo(attribute.getIsShow());
        }

        if (null != attribute.getIdx()) {
            criteria.andPidxEqualTo(attribute.getIdx());
        } else {
            criteria.andPidxEqualTo(ItemConstants.DEFAULT_PRESENT_IDX);
        }

        example.setOrderByClause(OrderByConstants.ORDER_BY_UPDATE_TIME);
        List<Map<String, Object>> datas = new ArrayList<>();
        List<CollectorAttribute> attributeList = this.queryByExample(apiVersion, example);
        if (null != attributeList && attributeList.size() > 0) {
            for (CollectorAttribute collectorAttribute : attributeList) {
                Map<String, Object> map = new HashMap();
                map.put("id", collectorAttribute.getIdx());
                map.put("name", collectorAttribute.getName());
                map.put("isSelect", collectorAttribute.getIsSelect());
                map.put("isShow", collectorAttribute.getIsShow());
                map.put("isKey", collectorAttribute.getIsKey());

                Boolean hasChild = this.hasChild(apiVersion, collectorAttribute.getIdx());
                if (null == hasChild || !hasChild) {
                    map.put("children", new ArrayList());
                    map.put("state", "open");
                } else {
                    map.put("state", "closed");
                }

                datas.add(map);
            }
        }

        return datas;
    }

    /**
     * 获取属性树结构
     *
     * @param apiVersion Api版本
     * @param idx        商品类目主键idx
     * @return
     * @throws MsOnionIllegalArgumentException 无效参数类型异常
     * @throws MsOnionException                异常
     */
    @Override
    public String getAttributeTree(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        Map<String, Object> map = generateTree(apiVersion, idx);
        String json = MsOnionJsonUtils.objectToJson(map);

        return json;
    }

    /**
     * 生成树
     *
     * @param apiVersion Api版本
     * @param idx        属性主键idx
     * @return 父级底下树结构
     * @throws MsOnionIllegalArgumentException 无效参数类型异常
     * @throws MsOnionException                异常
     */
    Map<String, Object> generateTree(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorAttribute attribute = this.queryByPrimaryKey(apiVersion, idx);
        String id = attribute.getId();
        String name = attribute.getName();
        Boolean isSelect = attribute.getIsSelect();
        Boolean isShow = attribute.getIsShow();
        Boolean isKey = attribute.getIsKey();

        Map<String, Object> map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("isSelect", isSelect);
        map.put("isShow", isShow);
        map.put("isKey", isKey);

        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andPidxEqualTo(idx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<Map<String, Object>> childrenList = new ArrayList<>();
        List<CollectorAttribute> attributeList = this.queryByExample(apiVersion, example);
        Iterator<CollectorAttribute> it = attributeList.iterator();
        while (it.hasNext()) {
            CollectorAttribute attribute1 = it.next();
            Long idx1 = attribute1.getIdx();

            Map<String, Object> children = generateTree(apiVersion, idx1);
            childrenList.add(children);
        }

        map.put("children", childrenList);
        return map;
    }

    @Override
    public MsOnionResult addAttributeSelect(MsOnionApiVersion apiVersion, Long idx, List<CollectorAttribute> attributeList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            CollectorAttributeExample example = new CollectorAttributeExample();
            CollectorAttributeExample.Criteria criteria = example.createCriteria();
            criteria.andPidxEqualTo(idx);
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<CollectorAttribute> list = this.queryByExample(apiVersion, example);
            Iterator<CollectorAttribute> it = list.iterator();
            while (it.hasNext()) {
                CollectorAttribute attribute = it.next();
                attribute.setStatus(MsOnionTableRecordStatus.DELETED.getValue());

                int result = this.updateByPrimaryKey(apiVersion, attribute);
                if (result == 0) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "新增属性选项失败");
                }
            }

            it = attributeList.iterator();
            while (it.hasNext()) {
                CollectorAttribute attribute = it.next();
                MsOnionResult result = this.addAttribute(apiVersion, attribute);

                if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return result;
                }
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "新增属性选项成功");
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取该属性的选项值
     * @param apiVersion 版本
     * @param attributeIdx 属性idx
     * @return 选项值列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<CollectorAttribute> getAttributeSelects(MsOnionApiVersion apiVersion, Long attributeIdx)
            throws MsOnionException {
        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andPidxEqualTo(attributeIdx);

        return this.queryByExample(apiVersion, example);
    }

    /**
     * 是否有子节点
     * @param apiVersion 版本
     * @param attributeIdx 属性id
     * @return 是否有子节点
     * @throws MsOnionException 自定义异常
     */
    private Boolean hasChild(MsOnionApiVersion apiVersion, Long attributeIdx) throws MsOnionException {
        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();

        criteria.andPidxEqualTo(attributeIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        long count = this.countByExample(apiVersion, example);
        return count > 0 ? true : false;
    }
}
