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
package cc.msonion.carambola.manager.ext.utils;

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.*;
import cc.msonion.carambola.commons.common.utils.MsOnionEditTypeUtils;
import cc.msonion.carambola.item.pojo.ItemAttributeOfficial;
import cc.msonion.carambola.item.service.ItemAttributeOfficialService;
import cc.msonion.carambola.item.service.ItemOfficialService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.*;

/**
 * @ClassName: ItemOfficialAttributeUtils
 * @Description: 正式商品属性工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Mark 3563249160@qq.com
 * @Date: 2017/6/27
 */
public final class ItemOfficialAttributeUtils {
    /**
     * 获取正式商品的属性和属性值
     * @param apiVersion 版本
     * @param itemService 属性
     * @param categoryAttributeGroupService 类目与属性组服务
     * @param itemAttributeOfficialService 正式商品属性服务
     * @param attributeGroupAttributeService 属性组属性服务
     * @param attributeService 属性服务
     * @param itemIdx 采集中心商品idx
     * @param itemOfficialIdx 正式商品idx
     * @return 查询结果
     * @throws MsOnionException 自定义异常
     */
    public static MsOnionResult getItemAttributeAndValue(MsOnionApiVersion apiVersion,
                                                         ItemService itemService,
                                                         CategoryAttributeGroupService categoryAttributeGroupService,
                                                         ItemAttributeOfficialService itemAttributeOfficialService,
                                                         AttributeGroupAttributeService attributeGroupAttributeService,
                                                         AttributeService attributeService,
                                                         Long itemIdx, Long itemOfficialIdx) throws MsOnionException {
        MsOnionResult result = itemService.getItemByIdx(apiVersion, itemIdx);
        if (null == result.getData()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_NOT_FOUND_ITEM);
        }

        CollectorItem item = (CollectorItem) result.getData();
        Long categoryIdx = item.getCategoryIdx();

        List<CollectorAttribute> attributeList = new ArrayList<>();

        List<CollectorCategoryAttributeGroup> categoryAttributeGroups =
                categoryAttributeGroupService.getCategoryAttributeGroup(apiVersion, categoryIdx);
        if (null != categoryAttributeGroups && categoryAttributeGroups.size() > 0) {
            for (CollectorCategoryAttributeGroup categoryAttributeGroup : categoryAttributeGroups) {
                if (null != categoryAttributeGroup) {
                    Long attributeGroupIdx = categoryAttributeGroup.getAttributeGroupIdx();
                    result = attributeGroupAttributeService.getAttributeByAttributeGroupIdx(apiVersion, attributeGroupIdx);
                    if (null == result.getData()) {
                        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                                        CollectorMessageConstants.MESSAGE_NOT_FOUND_ATTRIBUTE_GROUP_ATTRIBUTE);
                    }
                    List<CollectorAttribute> attributes = (List<CollectorAttribute>) result.getData();
                    attributeList.addAll(attributes);
                }
            }
        }

        Set tempAttributeIdxSet = new HashSet(); // 用于去重
        List<Map<String, Object>> itemAttributeList = new ArrayList<>();
        if (null != attributeList && attributeList.size() > 0) {
            for (CollectorAttribute attribute : attributeList) {
                if (null == attribute || !attribute.getIsShow() || tempAttributeIdxSet.contains(attribute.getIdx())) {
                    continue;
                }

                Long attributeIdx = attribute.getIdx();
                String attributeName = attribute.getName();

                tempAttributeIdxSet.add(attributeIdx);

                Map<String, Object> map = new HashMap<>();
                map.put("attributeIdx", attributeIdx);
                map.put("attributeName", attributeName);
                map.put("isKey", attribute.getIsKey());

                result = itemAttributeOfficialService.getItemAttributeValue(apiVersion, itemOfficialIdx, attributeIdx);
                if (null == result.getData()) {
                    map.put("attributeValue", "");
                    itemAttributeList.add(map);
                } else {
                    ItemAttributeOfficial itemAttribute = (ItemAttributeOfficial) result.getData();
                    String attributeValue = itemAttribute.getAttributeValue();
                    map.put("attributeValue", attributeValue);

                    if (null != attribute.getIsSelect() && attribute.getIsSelect() && MsOnionNumberUtils.isDigits(attributeValue)) {
                        result = attributeService.getAttributeByIdx(apiVersion, Long.valueOf(attributeValue));
                        if (null != result.getData()) {
                            CollectorAttribute selectedAttributeValue = (CollectorAttribute) result.getData();
                            map.put("selectedAttributeValue", selectedAttributeValue.getName());
                        }
                    }
                    itemAttributeList.add(map);
                }
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemAttributeList);
    }
}
