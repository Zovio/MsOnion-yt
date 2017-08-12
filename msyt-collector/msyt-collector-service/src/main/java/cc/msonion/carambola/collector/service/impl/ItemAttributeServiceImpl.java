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
 * @Title: ItemAttributeServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: 商品属性值服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/22 21:20
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/22 21:20
 * @Modify-version: V2.0.0
 * @Modify-description: 新增商品属性CRUD相关操作
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.common.utils.MsOnionEditTypeUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ItemAttributeServiceImpl
 * @Description: 商品属性值服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/22 21:20
 */
@Service
public class ItemAttributeServiceImpl extends MsOnionServiceExt<CollectorItemAttribute, CollectorItemAttributeExample>
        implements ItemAttributeService {

    /**
     * 商品服务
     */
    @Autowired
    private ItemService itemService;

    /**
     * 商品类目服务
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 属性组属性服务
     */
    @Autowired
    private AttributeGroupAttributeService attributeGroupAttributeService;

    /**
     * 类目与属性组关联服务
     */
    @Autowired
    private CategoryAttributeGroupService categoryAttributeGroupService;

    /**
     * 属性服务
     */
    @Autowired
    private AttributeService attributeService;

    /**
     * 新增商品属性值
     *
     * @param apiVersion    Api版本
     * @param itemAttribute 商品属性值信息
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemAttribute(MsOnionApiVersion apiVersion, CollectorItemAttribute itemAttribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_IDX_PATH);
        itemAttribute.setIdx(idx);
        itemAttribute.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_VERSION_PATH);
        itemAttribute.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemAttribute=" + itemAttribute);

        int result = this.save(apiVersion, itemAttribute);
        if (result > 0) {
            return MsOnionResult.ok(itemAttribute);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 删除商品属性值
     *
     * @param apiVersion Api版本
     * @param idx        商品属性主键idx
     * @return 返回删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult deleteItemAttribute(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemAttribute itemAttribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemAttribute) {
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
     * 修改商品属性值
     *
     * @param apiVersion    Api版本
     * @param itemAttribute 商品属性值信息
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateItemAttribute(MsOnionApiVersion apiVersion, CollectorItemAttribute itemAttribute)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemAttribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_EDITOR_NULL);
        }

        Long idx = itemAttribute.getIdx();

        CollectorItemAttribute temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setItemIdx(itemAttribute.getItemIdx());
        temp.setAttributeIdx(itemAttribute.getAttributeIdx());
        temp.setAttributeValue(itemAttribute.getAttributeValue());
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(itemAttribute.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 获取商品属性值
     *
     * @param apiVersion Api版本
     * @param idx        商品属性主键idx
     * @return 返回商品属性值信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemAttributeByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemAttribute itemAttribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemAttribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemAttribute);
    }

    /**
     * 获取商品属性值
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @param editType   编辑类型
     * @return 返回商品属性值信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemAttributeByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx, Short editType)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemAttributeExample example = new CollectorItemAttributeExample();
        CollectorItemAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andEditTypeEqualTo(editType);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemAttribute> collectorItemAttributeList = this.queryByExample(apiVersion, example);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, collectorItemAttributeList);
    }

    /**
     * 更新商品属性值
     *
     * @param apiVersion        Api版本
     * @param itemIdx           商品主键idx
     * @param editType          编辑类型
     * @param itemAttributeList 商品属性集合
     * @return 返回更新结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateItemAttributeByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx, Short editType,
                                                      List<CollectorItemAttribute> itemAttributeList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = this.getItemAttributeByItemIdx(apiVersion, itemIdx, editType);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        // 删除旧属性
        List<CollectorItemAttribute> list = (List<CollectorItemAttribute>) result.getData();
        for (CollectorItemAttribute itemAttribute : list) {
            if (null != itemAttribute) {
                Long itemAttributeIdx = itemAttribute.getIdx();
                MsOnionResult rs = this.deleteItemAttribute(apiVersion, itemAttributeIdx);
                if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return rs;
                }
            }
        }

        // 新增新属性
        for (CollectorItemAttribute itemAttribute : itemAttributeList) {
            if (null != itemAttribute) {
                itemAttribute.setItemIdx(itemIdx);
                itemAttribute.setEditType(editType);
                MsOnionResult rs = this.addItemAttribute(apiVersion, itemAttribute);
                if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return rs;
                }
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * 获取商品属性值
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品主键idx
     * @param editType   编辑类型
     * @return 返回商品属性值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemAttributeByItemIdxAndEditType(MsOnionApiVersion apiVersion, Long itemIdx, Short editType)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);
        if (null == result.getData()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ITEM);
        }

        CollectorItem item = (CollectorItem) result.getData();
        Long categoryIdx = item.getCategoryIdx();

        List<CollectorAttribute> attributeList = new ArrayList<>();
        List<CollectorCategoryAttributeGroup> categoryAttributeGroups = categoryAttributeGroupService.getCategoryAttributeGroup(apiVersion, categoryIdx);
        if (null != categoryAttributeGroups && categoryAttributeGroups.size() > 0) {
            for (CollectorCategoryAttributeGroup collectorAttributeGroup : categoryAttributeGroups) {
                if (null != collectorAttributeGroup) {
                    Long attributeGroupIdx = collectorAttributeGroup.getAttributeGroupIdx();
                    result = attributeGroupAttributeService.getAttributeByAttributeGroupIdx(apiVersion, attributeGroupIdx);
                    if (null == result.getData()) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ATTRIBUTE_GROUP_ATTRIBUTE);
                    }
                    List<CollectorAttribute> attributes = (List<CollectorAttribute>) result.getData();
                    attributeList.addAll(attributes);
                }
            }
        }

        Set tempAttributeIdxSet = new HashSet(); //用于去重
        List<Map<String, Object>> itemAttributeList = new ArrayList<>();
        if (null != attributeList && attributeList.size() > 0) {
            for (CollectorAttribute attribute : attributeList) {
                if (null == attribute || (MsOnionEditTypeUtils.EDIT_SECOND.equals(editType) && !attribute.getIsShow())
                        || tempAttributeIdxSet.contains(attribute.getIdx())) {
                    continue;
                }

                Long attributeIdx = attribute.getIdx();
                String attributeName = attribute.getName();

                tempAttributeIdxSet.add(attributeIdx);

                Map<String, Object> map = new HashMap<>();
                map.put("attributeIdx", attributeIdx);
                map.put("attributeName", attributeName);
                map.put("isKey", attribute.getIsKey());

                if (null != attribute.getIsSelect() && attribute.getIsSelect()) {
                    List<CollectorAttribute> attributeOptions = attributeService.getAttributeSelects(apiVersion, attributeIdx);
                    map.put("attributeOptions", attributeOptions);
                }

                result = this.getItemAttributeValueByEditType(apiVersion, itemIdx, attributeIdx, editType);
                if (null == result.getData()) {
                    // 如果二次编辑没有录入属性值，那么填充采集信息做参考
                    if (MsOnionEditTypeUtils.EDIT_SECOND.equals(editType)) {
                        result = this.getItemAttributeValueByEditType(apiVersion, itemIdx, attributeIdx, MsOnionEditTypeUtils.EDIT_FIRST);
                        if (null == result.getData()) {
                            map.put("attributeValue", "");
                            itemAttributeList.add(map);
                        } else {
                            map = this.getAttributeValue(apiVersion, result, map);
                            itemAttributeList.add(map);
                        }
                    } else {
                        map.put("attributeValue", "");
                        itemAttributeList.add(map);
                    }
                } else {
                    map = this.getAttributeValue(apiVersion, result, map);
                    itemAttributeList.add(map);
                }
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemAttributeList);
    }

    /**
     * 获取商品属性值
     *
     * @param apiVersion   Api版本
     * @param itemIdx      商品主键idx
     * @param attributeIdx 属性主键idx
     * @param editType     编辑类型
     * @return 返回商品属性值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemAttributeValueByEditType(MsOnionApiVersion apiVersion, Long itemIdx, Long attributeIdx, Short editType)
            throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemAttributeExample example = new CollectorItemAttributeExample();
        CollectorItemAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andAttributeIdxEqualTo(attributeIdx);
        criteria.andEditTypeEqualTo(editType);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemAttribute> itemAttributeList = this.queryByExample(apiVersion, example);
        if (null == itemAttributeList || itemAttributeList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ITEM_ATTRIBUTE);
        }

        CollectorItemAttribute itemAttribute = itemAttributeList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemAttribute);
    }

    /**
     * 按分类id获取属性列表
     * @param apiVersion 版本
     * @param categoryIdx 分类id
     * @return 属性列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult getItemAttributeByCategoryIdx(MsOnionApiVersion apiVersion, Long categoryIdx) throws MsOnionException {
        MsOnionResult result = itemCategoryService.getItemCategoryByIdx(apiVersion, categoryIdx);
        if (null == result.getData()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ITEM_CATEGORY);
        }

        List<CollectorCategoryAttributeGroup> categoryAttributeGroups =
                categoryAttributeGroupService.getCategoryAttributeGroup(apiVersion, categoryIdx);

        List<Map<String, Object>> itemAttributeList = new ArrayList<>();
        Set tempAttributeIdxSet = new HashSet(); //用于去重
        if (null != categoryAttributeGroups && categoryAttributeGroups.size() > 0) {
            for (CollectorCategoryAttributeGroup categoryAttributeGroup : categoryAttributeGroups) {
                if (null != categoryAttributeGroup) {
                    Long attributeGroupIdx = categoryAttributeGroup.getAttributeGroupIdx();
                    result = attributeGroupAttributeService.getAttributeByAttributeGroupIdx(apiVersion, attributeGroupIdx);
                    if (null == result.getData()) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ATTRIBUTE_GROUP_ATTRIBUTE);
                    }

                    List<CollectorAttribute> attributeList = (List<CollectorAttribute>) result.getData();
                    for (CollectorAttribute attribute : attributeList) {
                        if (null == attribute || !attribute.getIsShow() || tempAttributeIdxSet.contains(attribute.getIdx())) {
                            continue;
                        }

                        Long attributeIdx = attribute.getIdx();

                        tempAttributeIdxSet.add(attributeIdx);

                        String attributeName = attribute.getName();

                        Map<String, Object> map = new HashMap<>();
                        map.put("attributeIdx", attributeIdx);
                        map.put("attributeName", attributeName);
                        map.put("isSelect", attribute.getIsSelect());
                        itemAttributeList.add(map);
                    }
                }
            }
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemAttributeList);
    }

    /**
     * 获取属性值
     * @param apiVersion 版本
     * @param result 服务返回结果
     * @param map 数据集
     * @return 属性值
     * @throws MsOnionException 自定义异常
     */
    private Map<String, Object> getAttributeValue(MsOnionApiVersion apiVersion, MsOnionResult result,
                                                  Map<String, Object> map) throws MsOnionException {
        CollectorItemAttribute itemAttribute = (CollectorItemAttribute) result.getData();
        String attributeValue = itemAttribute.getAttributeValue();
        map.put("attributeValue", attributeValue);
        if (MsOnionNumberUtils.isDigits(attributeValue)) {
            result = attributeService.getAttributeByIdx(apiVersion, Long.valueOf(attributeValue));
            if (null != result.getData()) {
                CollectorAttribute selectedAttributeValue = (CollectorAttribute) result.getData();
                map.put("selectedAttributeValue", selectedAttributeValue.getName());
            }
        }

        return map;
    }
}
