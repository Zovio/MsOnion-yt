/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营属性洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际类目直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.manager.web.controller.collector;

/**
 * @Title: AttributeController.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 属性Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月13日 下午9:32:42
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo 3028554324@qq.com
 * @Modify-date: 2017年4月13日 下午9:32:42
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.collector.common.constants.ItemConstants;
import cc.msonion.carambola.collector.pojo.*;
import cc.msonion.carambola.collector.service.AttributeGroupAttributeService;
import cc.msonion.carambola.collector.service.AttributeGroupService;
import cc.msonion.carambola.collector.service.AttributeService;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AttributeController
 * @Description: 属性Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月13日 下午9:32:42
 */
@Controller
public class AttributeController extends MsOnionBaseAppController {
    /**
     * 属性服务
     */
    @Autowired
    private AttributeService attributeService;
    /**
     * 属性组服务
     */
    @Autowired
    private AttributeGroupService attributeGroupService;
    /**
     * 属性组属性服务
     */
    @Autowired
    private AttributeGroupAttributeService attributeGroupAttributeService;

    /**
     * 属性列表
     *
     * @param request 请求
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/list")
    public String listAttribute(HttpServletRequest request) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        return "/collector/attribute/listAttribute";
    }

    /**
     * 获取属性列表
     *
     * @param request   请求
     * @param attribute 查询条件
     * @return 返回属性列表
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping(value = "/collect/attribute/grid")
    public List<Map<String, Object>> gridAttribute(HttpServletRequest request, CollectorAttribute attribute) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        List<Map<String, Object>> list = attributeService.getAttributeTree(apiVersion, attribute);
        return list;
    }

    /**
     * 动态获取属性列表
     *
     * @param attribute 查询条件
     * @return 属性列表
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/attribute/getAttributeGridByParentId")
    @ResponseBody
    public List<Map<String, Object>> getAttributeGridByParentId(CollectorAttribute attribute, String id) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        long idx;
        if (!MsOnionRegexUtils.checkDigit(id)) {
            idx = ItemConstants.DEFAULT_PRESENT_IDX;
        } else {
            idx = MsOnionNumberUtils.toLong(id, ItemConstants.DEFAULT_PRESENT_IDX);
        }

        attribute.setIdx(idx);

        List<Map<String, Object>> datas = attributeService.getAttributeByTopId(apiVersion, attribute);
        return datas;
    }

    /**
     * 获取所有属性值，不包括属性选项
     *
     * @param request 请求
     * @return 返回结果
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping("/collect/attribute/all")
    public Map<String, Object> getAllAttributeValue(HttpServletRequest request) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        CollectorAttributeExample example = new CollectorAttributeExample();
        CollectorAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andPidxEqualTo(0L);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        String orderBy;
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
        } else {
            orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
        }

        example.setOrderByClause(orderBy);
        List<CollectorAttribute> attributeList = attributeService.queryByExample(apiVersion, example);
        long count = attributeService.countByExample(apiVersion, example);

        Map<String, Object> map = new HashMap<>();
        map.put("rows", attributeList);
        map.put("total", count);
        return map;
    }

    /**
     * 属性组列表
     *
     * @return String
     */
    @RequestMapping("/collect/attribute/gp/list")
    public String listAttributeGroup() {
        return "/collector/attribute/listAttributeGroup";
    }

    /**
     * 获取属性组列表
     *
     * @param request   请求
     * @param groupName 属性组的名称
     * @param page      页数
     * @param rows      每页显示数量
     * @return 返回属性组列表
     * @throws MsOnionException 自定义异常
     */
    @ResponseBody
    @RequestMapping(value = "/collect/attribute/gp/grid", method = RequestMethod.POST)
    public Map<String, Object> gridAttributeGroup(HttpServletRequest request, String groupName, String page,
                                                  String rows) throws MsOnionException {
        // 如果是非法参数， 可以默认第1页，10条记录
        if (!MsOnionRegexUtils.checkDigit(page)) {
            page = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
        }

        if (!MsOnionRegexUtils.checkDigit(rows)) {
            rows = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE + "";
        }

        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            CollectorAttributeGroupExample example = new CollectorAttributeGroupExample();
            CollectorAttributeGroupExample.Criteria criteria = example.createCriteria();
            criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());

            if (StringUtils.isNotBlank(groupName)) {
                criteria.andNameLike(ManagerConstants.PERCENT + groupName.trim() + ManagerConstants.PERCENT);
            }

            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);

            String orderBy;
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + ManagerConstants.SPACE + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            Map map = new HashMap<>();
            MsOnionResultAdapter resultAdapter = attributeGroupService.queryListByPageForResult(apiVersion,
                    example, pageNum, pageSize, orderBy);
            if (null != resultAdapter) {
                MsOnionPagingResult msOnionPagingResult = (MsOnionPagingResult) resultAdapter;

                map.put("rows", msOnionPagingResult.getData());
                map.put("total", msOnionPagingResult.getTotalCounts());
            }

            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑属性
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/edit/{idxStr}")
    public String editAttribute(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = attributeService.getAttributeByIdx(apiVersion, idx);

                CollectorAttribute attribute = (CollectorAttribute) result.getData();
                request.setAttribute("attribute", attribute);
            }

            return "/collector/attribute/editAttribute";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存属性
     *
     * @param attribute 属性
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/save")
    @ResponseBody
    public MsOnionResult saveAttribute(CollectorAttribute attribute) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            MsOnionResult result;
            Long idx = attribute.getIdx();

            Member currentUser = CurrentUserUtils.getCurrentUser();

            if (null == idx) {
                attribute.setCreateByMemberIdx(currentUser.getIdx());
                attribute.setUpdateByMemberIdx(currentUser.getIdx());
                result = attributeService.addAttribute(apiVersion, attribute);
            } else {
                attribute.setUpdateByMemberIdx(currentUser.getIdx());
                result = attributeService.updateAttribute(apiVersion, attribute);
            }
            return result;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 删除属性
     *
     * @param idxStr 属性主键idx
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping("/collect/attribute/del")
    public MsOnionResult deleteAttribute(String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        Long idx = Long.parseLong(idxStr);

        try {
            MsOnionResult result = attributeService.deleteAttribute(apiVersion, idx);
            return result;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑属性组
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/gp/edit/{idxStr}")
    public String editAttributeGroup(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = attributeGroupService.getAttributeGroupByIdx(apiVersion, idx);

                if (null != result.getData()) {
                    CollectorAttributeGroup attributeGroup = (CollectorAttributeGroup) result.getData();
                    request.setAttribute("attributeGroup", attributeGroup);

                    CollectorAttributeGroupAttributeExample example = new CollectorAttributeGroupAttributeExample();
                    CollectorAttributeGroupAttributeExample.Criteria criteria = example.createCriteria();
                    criteria.andAttributeGroupIdxEqualTo(idx);
                    criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

                    StringBuffer attributeIds = new StringBuffer();
                    StringBuffer attributeValues = new StringBuffer();

                    List<CollectorAttributeGroupAttribute> list = attributeGroupAttributeService.queryByExample(apiVersion, example);

                    for (CollectorAttributeGroupAttribute attributeGroupAttribute : list) {
                        if (null != attributeGroupAttribute) {
                            Long attributeIdx = attributeGroupAttribute.getAttributeIdx();
                            attributeIds.append(attributeIdx.toString());
                            attributeIds.append(",");

                            CollectorAttribute attribute = attributeService.queryByPrimaryKey(apiVersion, attributeIdx);
                            if (attribute != null) {
                                String name = attribute.getName();
                                attributeValues.append(name).append("，");
                            }
                        }
                    }

                    if (attributeIds.length() > 0 && attributeValues.length() > 0) {
                        request.setAttribute("attributeIds", attributeIds.deleteCharAt(attributeIds.length() - 1).toString());
                        request.setAttribute("attributeValues", attributeValues.deleteCharAt(attributeValues.length() - 1).toString());
                    }
                }
            }

            return "/collector/attribute/editAttributeGroup";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 编辑属性组中属性值
     *
     * @param request 请求
     * @param idxStr  属性组主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/gp/sel/edit/{idxStr}")
    public String editAttributeGroupSel(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isNotBlank(idxStr)) {
            Long idx = Long.valueOf(idxStr);

            CollectorAttributeGroupAttributeExample example = new CollectorAttributeGroupAttributeExample();
            CollectorAttributeGroupAttributeExample.Criteria criteria = example.createCriteria();
            criteria.andAttributeGroupIdxEqualTo(idx);
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<Long> attributeIdxList = new ArrayList<>();
            List<CollectorAttributeGroupAttribute> attributeGroupAttributeList = attributeGroupAttributeService.queryByExample(apiVersion, example);
            for (CollectorAttributeGroupAttribute attributeGroupAttribute : attributeGroupAttributeList) {
                if (null != attributeGroupAttribute) {
                    Long attributeIdx = attributeGroupAttribute.getAttributeIdx();
                    attributeIdxList.add(attributeIdx);
                }
            }

            String attributeIdxListJson = MsOnionJsonUtils.objectToJson(attributeIdxList);
            request.setAttribute("attributeIdxListJson", attributeIdxListJson);
        }

        return "/collector/attribute/editAttributeGroupSelect";
    }

    /**
     * 保存属性组
     *
     * @param attributeGroup 属性组
     * @param attributes     属性
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/gp/save")
    @ResponseBody
    public MsOnionResult saveAttributeGroup(CollectorAttributeGroup attributeGroup, String attributes) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            MsOnionResult result;
            Long idx = attributeGroup.getIdx();

            Member currentUser = CurrentUserUtils.getCurrentUser();

            if (null == idx) {
                attributeGroup.setCreateByMemberIdx(currentUser.getIdx());
                attributeGroup.setUpdateByMemberIdx(currentUser.getIdx());
                result = attributeGroupService.addAttributeGroupAndAttribute(apiVersion, attributeGroup, attributes);
            } else {
                attributeGroup.setUpdateByMemberIdx(currentUser.getIdx());
                result = attributeGroupService.updateAttributeGroupAndAttribute(apiVersion, attributeGroup, attributes);
            }
            return result;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 删除属性组
     *
     * @param ids 属性组主键idx集合
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping("/collect/attribute/gp/del")
    public MsOnionResult deleteAttributeGroup(@RequestParam(value = "ids[]", required = false, defaultValue = "") String[] ids)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        MsOnionResult result = attributeGroupService.batchDeleteAttributeGroup(apiVersion, ids);
        return result;
    }

    /**
     * 编辑属性选项
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return String
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/sel/edit/{idxStr}")
    public String editAttributeSel(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = attributeService.getAttributeByIdx(apiVersion, idx);

                CollectorAttribute attribute = (CollectorAttribute) result.getData();
                request.setAttribute("attribute", attribute);

                CollectorAttributeExample example = new CollectorAttributeExample();
                CollectorAttributeExample.Criteria criteria = example.createCriteria();
                criteria.andPidxEqualTo(idx);
                criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);

                List<CollectorAttribute> attributeList = attributeService.queryByExample(apiVersion, example);
                request.setAttribute("attributeSelectList", attributeList);
            }

            return "/collector/attribute/editAttributeSelect";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 保存属性选项
     *
     * @param idxStr              属性主键
     * @param attributeSelectList 属性选项名称集合
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/attribute/sel/save")
    @ResponseBody
    public MsOnionResult saveAttributeSel(String idxStr, @RequestParam(value = "attributeSelectList[]", required = false, defaultValue = "")
            String[] attributeSelectList) throws MsOnionException {
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法 , idxStr=" + idxStr);
            }

            Long idx = Long.parseLong(idxStr);

            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            List<CollectorAttribute> attributeList = new ArrayList<>();
            Member currentUser = CurrentUserUtils.getCurrentUser();

            for (String attributeSelect : attributeSelectList) {
                if (null != attributeSelect) {
                    CollectorAttribute attribute = new CollectorAttribute();

                    attribute.setPidx(idx);
                    attribute.setName(attributeSelect);
                    attribute.setIsSelect(false);
                    attribute.setIsShow(true);
                    attribute.setCreateByMemberIdx(currentUser.getIdx());
                    attribute.setUpdateByMemberIdx(currentUser.getIdx());
                    attributeList.add(attribute);
                }
            }

            MsOnionResult result = attributeService.addAttributeSelect(apiVersion, idx, attributeList);
            return result;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }
}
