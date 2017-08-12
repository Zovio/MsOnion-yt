/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营类目洋葱小姐。
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
 * @Title: CategoryController.java
 * @Package: cc.msonion.carambola.manager.web.controller.collector
 * @Description: 类目Controller
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
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroup;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupExample;
import cc.msonion.carambola.collector.pojo.CollectorCategoryAttributeGroup;
import cc.msonion.carambola.collector.pojo.CollectorItemCategory;
import cc.msonion.carambola.collector.service.AttributeGroupService;
import cc.msonion.carambola.collector.service.CategoryAttributeGroupService;
import cc.msonion.carambola.collector.service.ItemCategoryService;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.image.MsOnionImageUtils;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CategoryController
 * @Description: 类目Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo 3028554324@qq.com
 * @Date: 2017年4月13日 下午9:32:42
 */
@Controller
public class CategoryController extends MsOnionBaseAppController {
    /**
     * 商品类目服务
     */
    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 属性组服务
     */
    @Autowired
    private AttributeGroupService attributeGroupService;

    /**
     * 字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    @Autowired
    private CategoryAttributeGroupService categoryAttributeGroupService;

    /**
     * 类目列表
     *
     * @return String
     */
    @RequestMapping("/collect/categy/list")
    public String listCategory() {
        return "/collector/category/listCategory";
    }

    /**
     * 获取类目列表
     *
     * @param request 请求
     * @param code    类目编码
     * @param name    类目名称
     * @return String
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping(value = "/collect/categy/grid", method = RequestMethod.POST)
    public List<Map<String, Object>> gridCategory(HttpServletRequest request, String code, String name)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (MsOnionStringUtils.isNotEmpty(code)) {
            code = ManagerConstants.PERCENT + code.trim() + ManagerConstants.PERCENT;
        }
        if (MsOnionStringUtils.isNotEmpty(name)) {
            name = ManagerConstants.PERCENT + name.trim() + ManagerConstants.PERCENT;
        }
        List<Map<String, Object>> categoryTree = itemCategoryService.getCategoryTree(apiVersion, code, name);
        return categoryTree;
    }

    /**
     * 获取类目列表
     *
     * @param request 请求
     * @param id  父类ID
     * @return String
     * @throws MsOnionException 异常
     */
    @ResponseBody
    @RequestMapping(value = "/collect/categy/gridByParentId", method = RequestMethod.POST)
    public List<Map<String, Object>> gridByParentId(HttpServletRequest request, String id, String name, String code)
            throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        long parentLong;
        if (!MsOnionRegexUtils.checkDigit(id)) {
            parentLong = ItemConstants.DEFAULT_PRESENT_IDX;
        } else {
            parentLong = MsOnionNumberUtils.toLong(id, ItemConstants.DEFAULT_PRESENT_IDX);
        }

        List<Map<String, Object>> categoryTree = itemCategoryService.getCategoryByParentId(apiVersion,
                parentLong, name, code);
        return categoryTree;
    }

    /**
     * 编辑类目
     *
     * @param request 请求
     * @param idxStr  主键idx
     * @return 返回页面
     * @throws MsOnionException 自定义异常
     */
    @RequestMapping("/collect/categy/edit/{idxStr}")
    public String editCategory(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            if (StringUtils.isNotBlank(idxStr)) {
                Long idx = Long.parseLong(idxStr);
                MsOnionResult result = itemCategoryService.getItemCategoryByIdx(apiVersion, idx);
                if (null != result.getData()) {
                    CollectorItemCategory itemCategory = (CollectorItemCategory) result.getData();

                    // 查询图片
                    itemCategory.setImageBigDynamicS(MsOnionImageUtils.splitImgFiled(getImgurl(), itemCategory.getImageBig()));
                    request.setAttribute("itemCategory", itemCategory);

                    result = itemCategoryService.getParent(apiVersion, idx);
                    if (null != result.getData()) {
                        CollectorItemCategory p = (CollectorItemCategory) result.getData();
                        String parentName = p.getName();
                        request.setAttribute("parentName", parentName);
                    }
                }

                // 获取已保存的类目与属性组关联
                List<CollectorCategoryAttributeGroup> categoryAttributeGroups =
                        categoryAttributeGroupService.getCategoryAttributeGroup(apiVersion, idx);

                if (null != categoryAttributeGroups && categoryAttributeGroups.size() > 0) {
                    List<Map<String, Object>> attributeGroupList = new ArrayList<>();
                    for (CollectorCategoryAttributeGroup categoryAttributeGroup : categoryAttributeGroups) {
                        CollectorAttributeGroup attributeGroup =
                                attributeGroupService.queryByPrimaryKey(apiVersion, categoryAttributeGroup.getAttributeGroupIdx());

                        Map<String, Object> attributeGroupMap = new HashMap<>();
                        attributeGroupMap.put("id", attributeGroup.getIdx());
                        attributeGroupMap.put("name", attributeGroup.getName());
                        attributeGroupList.add(attributeGroupMap);
                        request.setAttribute("attributeGroupList", attributeGroupList);
                    }
                }
            }

            Map publishChannelMap = sysDataDictionaryService.getDictMap(apiVersion, MsOnionDictCodeConstants.PUBLISH_CHANNEL);
            request.setAttribute("publishChannelMap", publishChannelMap);

            return "/collector/category/editCategory";
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        }
    }


    /**
     * 编辑类目中上级目录
     *
     * @param request 请求
     * @param idxStr  属性组主键idx
     * @return 返回上级目录
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/categy/sel/edit/{idxStr}")
    public String editCategorySel(HttpServletRequest request, @PathVariable String idxStr) throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        if (StringUtils.isNotBlank(idxStr)) {
            Long idx = Long.valueOf(idxStr);

            MsOnionResult result = itemCategoryService.getItemCategoryByIdx(apiVersion, idx);
            CollectorItemCategory itemCategory = (CollectorItemCategory) result.getData();
            if (null != itemCategory) {
                Long selectedCategoryIdx = itemCategory.getPidx();
                request.setAttribute("selectedCategoryIdx", selectedCategoryIdx);

                CollectorItemCategory selectedCategoryThree = null;
                CollectorItemCategory selectedCategoryTwo = null;
                CollectorItemCategory selectedCategoryOne = null;
                if (selectedCategoryIdx != null && selectedCategoryIdx > 0) {
                    selectedCategoryThree = itemCategoryService.queryByPrimaryKey(apiVersion, selectedCategoryIdx);
                    request.setAttribute("selectedCategoryThree", selectedCategoryThree);
                }
                if (selectedCategoryThree != null && selectedCategoryThree.getPidx() != null && selectedCategoryThree.getPidx() > 0) {
                    selectedCategoryTwo = itemCategoryService.queryByPrimaryKey(apiVersion, selectedCategoryThree.getPidx());
                    request.setAttribute("selectedCategoryTwo", selectedCategoryTwo);
                }

            }
        }

        return "/collector/category/editCategorySelect";
    }

    /**
     * 保存类目
     *
     * @param itemCategory 商品类目
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    @RequestMapping("/collect/categy/save")
    @ResponseBody
    public MsOnionResult saveCategory(CollectorItemCategory itemCategory, String attributeGroupIds)
                                                                                        throws MsOnionException {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        try {
            MsOnionResult result;
            Long idx = itemCategory.getIdx();
            Member currentUser = CurrentUserUtils.getCurrentUser();

            if (null == idx) {
                itemCategory.setCreateByMemberIdx(currentUser.getIdx());
                itemCategory.setUpdateByMemberIdx(currentUser.getIdx());
                result = itemCategoryService.addItemCategory(apiVersion, itemCategory);
            } else {
                itemCategory.setUpdateByMemberIdx(currentUser.getIdx());
                result = itemCategoryService.updateItemCategory(apiVersion, itemCategory);
            }

            if (MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                return result;
            }

            if (StringUtils.isNotBlank(attributeGroupIds)) {
                String[] attributeGroupIdArr = attributeGroupIds.split(",");
                if (null != attributeGroupIdArr && attributeGroupIdArr.length > 0) {
                    CollectorItemCategory collectorItemCategory = (CollectorItemCategory) result.getData();
                    Long categoryIdx = collectorItemCategory.getIdx();
                    List<CollectorCategoryAttributeGroup> categoryAttributeGroups = new ArrayList<>();
                    for (String attributeGroupId : attributeGroupIdArr) {
                        if (StringUtils.isNotBlank(attributeGroupId)) {
                            CollectorCategoryAttributeGroup attributeGroup = new CollectorCategoryAttributeGroup();
                            Long attributeGroupIdx = Long.valueOf(attributeGroupId);
                            attributeGroup.setAttributeGroupIdx(attributeGroupIdx);
                            attributeGroup.setCategoryIdx(categoryIdx);
                            attributeGroup.setCreateByMemberIdx(currentUser.getIdx());
                            attributeGroup.setUpdateByMemberIdx(currentUser.getIdx());

                            categoryAttributeGroups.add(attributeGroup);
                        }
                    }
                    MsOnionResult msOnionResult = categoryAttributeGroupService.batchSaveCategoryAttributeGroup(
                                                                            apiVersion, categoryAttributeGroups, idx);
                    return msOnionResult;
                }
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
            }

            return result;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }
    }

    /**
     * 选择属性组页面
     */
    @RequestMapping("/collect/categy/chooseAttributeGroup/{attributeGroupIds}")
    public String chooseAttributeGroup(HttpServletRequest request, @PathVariable String attributeGroupIds) {
        request.setAttribute("attributeGroupIds", attributeGroupIds);
        return "/collector/category/chooseAttrGrouplist";
    }

    /**
     * 获取属性组列表
     * @param request 请求
     * @param name 属性组名称
     * @param page 第几页
     * @param rows 每页多少条
     * @return 页面
     */
    @RequestMapping("/collect/categy/getAttributeGroupGrid")
    @ResponseBody
    public Map<String, Object> getAttributeGroupGrid(HttpServletRequest request, String name, String page, String rows) {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        Integer pageIndex = 1;
        Integer pageSize = 10;

        Map<String, Object> resultMap = new HashMap<>();

        try {
            if (StringUtils.isNotBlank(page)) {
                pageIndex = Integer.valueOf(page);
            }

            if (StringUtils.isNotBlank(rows)) {
                pageSize = Integer.valueOf(rows);
            }

            MsOnionResultAdapter msOnionResultAdapter =
                    attributeGroupService.getAttributeGroup(apiVersion, name, pageIndex, pageSize);

            MsOnionPagingResult pagingResult = (MsOnionPagingResult) msOnionResultAdapter;

            resultMap.put("total", pagingResult.getTotalCounts());
            resultMap.put("rows", pagingResult.getData());

        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }
        return resultMap;
    }

    /**
     * 获取某个类目的属性组
     * @param categoryId 类目idx
     * @return 处理结果
     */
    @RequestMapping("/collect/categy/getCategoryAttributeGroups")
    @ResponseBody
    public MsOnionResult getCategoryAttributeGroups(Long categoryId) {
        MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
        apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            List<CollectorCategoryAttributeGroup> categoryAttributeGroups =
                    categoryAttributeGroupService.getCategoryAttributeGroup(apiVersion, categoryId);
            return MsOnionResult.ok(categoryAttributeGroups);
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_SEARCH_FAILURE);
        }
    }
}
