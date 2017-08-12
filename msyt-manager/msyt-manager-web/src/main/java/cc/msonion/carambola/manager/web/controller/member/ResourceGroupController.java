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


package cc.msonion.carambola.manager.web.controller.member;

/**
 * @Title: ResourceGroupController.java
 * @Package: cc.msonion.carambola.manager.web.controller.member
 * @Description: 资源组controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月18日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月18日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.controller.ext.AuthExtUtils;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.*;
import cc.msonion.carambola.member.service.ButtonService;
import cc.msonion.carambola.member.service.MenuService;
import cc.msonion.carambola.member.service.ResourceGroupService;
import cc.msonion.carambola.member.service.ResourceService;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ResourceGroupController
 * @Description: 资源组controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月18日
 *
 */
@Controller
public class ResourceGroupController extends MsOnionBaseAppController {

    /**
     * resourceGroupService
     */
    @Autowired
    private ResourceGroupService resourceGroupService;

    /**
     * resourceService
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * menuService
     */
    @Autowired
    private MenuService menuService;


    /**
     * buttonService
     */
    @Autowired
    private ButtonService buttonService;
    /**
     * sysDataDictionaryService
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     *  需要隐藏的菜单idx
     */
    @Value("${hide_menu_idxs}")
    private String hideMenuIdxs;


    /**
     *   新增、编辑、查询页面
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param type 操作类型：add，edit，view
     * @param idxStr  主键idx
     * @param model  Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/authManage/{type}/{idxStr}")
    public String toSaveOrEditOrViewMenu(HttpServletRequest req, HttpServletResponse res,
                                         @PathVariable String type, @PathVariable String idxStr, Model model) {
        try {
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                return getError();
            }

            model.addAttribute("hideMenuIdxs", hideMenuIdxs);
            if (ManagerConstants.ADD.equals(type)) {
                model.addAttribute("type", type);
                model.addAttribute("menuResourcesJson", "{}");
                model.addAttribute("buttonResourcesJson", "{}");

            } else if (ManagerConstants.EDIT.equals(type) || ManagerConstants.VIEW.equals(type)) {
                long resourceGroupIdx = Long.valueOf(idxStr);
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
                ResourceGroup resourceGroup = this.resourceGroupService.queryByPrimaryKey(msOnionApiVersion, resourceGroupIdx);
                model.addAttribute("resourceGroup", resourceGroup);
                model.addAttribute("type", type);
                // 查询当前资源组的资源
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

                Resource resource1 = new Resource();
                resource1.setResourceType(ManagerConstants.MENU_TYPE);
                resource1.setResourceGroupIdx(resourceGroupIdx);
                List<Resource> menuResources = resourceService.getListByResource(msOnionApiVersion, resource1);
                model.addAttribute("menuResourcesJson", MsOnionJsonUtils.objectToJson(menuResources));

                Resource resource2 = new Resource();
                resource2.setResourceType(ManagerConstants.BUTTON_TYPE);
                resource2.setResourceGroupIdx(resourceGroupIdx);
                List<Resource> buttonResources = resourceService.getListByResource(msOnionApiVersion, resource2);
                model.addAttribute("buttonResourcesJson", MsOnionJsonUtils.objectToJson(buttonResources));
            } else {
                this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", type=" + type);
                return getError();
            }
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 进入资源组页面失败..");
            return getError();
        }
        return "/member/resourceGroup/saveOrEditOrViewResourceGroup";
    }

    /**
     * 保存和编辑
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model Model实例对象
     * @param resourceGroup resourceGroup 对象
     * @param buttonsStr 按钮字符串
     * @param menuStr 菜单字符串
     * @return MsOnionResult对象
     */
    @RequestMapping(value = "/authManage/do-saveOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model,
                                ResourceGroup resourceGroup, String buttonsStr, String menuStr) {
        try {
            Member currentUser = CurrentUserUtils.getCurrentUser();
            resourceGroup.setCreateByMemberIdx(currentUser.getIdx());
            resourceGroup.setUpdateByMemberIdx(currentUser.getIdx());

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = resourceGroupService.saveOrUpdate(msOnionApiVersion, resourceGroup);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }
            ResourceGroup rg = (ResourceGroup) msOnionResult.getData();
            return AuthExtUtils.processResources(getMsOnionLogger(), resourceService, menuService, buttonService, buttonsStr, menuStr, rg);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }


    /**
     * 查询分页列表 （easyui-datagird）
     *
     * @param req  HttpServletRequest实例对象
     * @param res  HttpServletResponse实例对象
     * @param page 页码
     * @param rows 每页数量
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = {"/authManage/paging", "/role/chooseResourceGroup"})
    @ResponseBody
    public Map<String, Object> paging(HttpServletRequest req, HttpServletResponse res, String page, String rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果是非法参数， 可以默认第1页，10条记录
        if (!MsOnionRegexUtils.checkDigit(page)) {
            page = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
        }
        if (!MsOnionRegexUtils.checkDigit(rows)) {
            rows = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MIN_VALUE + "";
        }
        try {
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);

            String name = req.getParameter("name");
            String status = req.getParameter("status");


            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            ResourceGroupExample example = new ResourceGroupExample();
            String orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;

            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + " " + order.trim();
            }

            ResourceGroupExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike("%" + name.trim() + "%");
            }

            if (StringUtils.isNotBlank(status) && MsOnionRegexUtils.isIntegerNotNegative(status)) {
                criteria.andStatusEqualTo(Short.valueOf(status));
            } else {
                criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            }

            MsOnionResultAdapter msOnionResultAdapter = this.resourceGroupService.queryListByPageForResult(msOnionApiVersion, example,
                    pageNum, pageSize, orderBy);

            if (msOnionResultAdapter == null) {
                map.put("total", 0);
                map.put("rows", null);
                map.put("info", MessageConstants.DATA_NONE_MEMBER);
            } else {
                MsOnionPagingResult msOnionPagingResult = (MsOnionPagingResult) msOnionResultAdapter;
                map.put("total", msOnionPagingResult.getTotalCounts());
                map.put("rows", msOnionPagingResult.getData());
            }
            return map;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            map.put("total", 0);
            map.put("rows", null);
            map.put("info", MessageConstants.MESSAGE_SERVER_ERROR);
            return map;
        }
    }


    /**
     * 根据主键 修改状态
     *
     * @param req       HttpServletRequest实例对象
     * @param res       HttpServletResponse实例对象
     * @param idxStr    主键idx
     * @param statusStr 状态
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/authManage/updateStatus/{idxStr}/{statusStr}", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult updateStatus(HttpServletRequest req, HttpServletResponse res,
                                      @PathVariable String idxStr, @PathVariable String statusStr) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        long idx = 0;
        short status = 0;
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
            }
            if (!MsOnionRegexUtils.isNumeric(statusStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", statusStr=" + statusStr);
            }
            idx = Long.parseLong(idxStr);
            status = Short.parseShort(statusStr);

            int result = resourceGroupService.updateStatus(msOnionApiVersion, idx, status);
            if (result > 0) {
                return MsOnionResult.ok();
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    cc.msonion.carambola.member.common.constants.MessageConstants.MESSAGE_INFO_ERROR);

        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     *   资源组列表
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param model  Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/authManage/list")
    public String toList(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
            model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            model.addAttribute("statusMap", statusMap);
        } catch (MsOnionException e) {
            return getError();
        }
        return "/member/resourceGroup/resourceGrouplist";
    }

    /**
     * 保存当前菜单下的按钮
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model Model实例对象
     * @param buttonsStr 按钮字符串
     * @param menuIdx 菜单idx
     * @param idx idx 当前资源组
     * @return MsOnionResult对象
     */
    @RequestMapping(value = "/authManage/saveCurButtons", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult saveCurButtons(HttpServletRequest req, HttpServletResponse res, Model model,
                                        String buttonsStr, String menuIdx, String idx) {
        try {
            if (StringUtils.isBlank(menuIdx) || StringUtils.isBlank(idx)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            if (!MsOnionRegexUtils.checkDigit(idx) || !MsOnionRegexUtils.checkDigit(menuIdx)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            // 组装当前按钮idx
            String[] buttonArr = {};
            if (StringUtils.isNotBlank(buttonsStr)) {
                buttonArr = buttonsStr.trim().split(",");
            }
            List<Long> buttonIdxList = new ArrayList();
            for (int i = 0; i < buttonArr.length; i++) {
                if (MsOnionRegexUtils.checkDigit(buttonArr[i])) {
                    buttonIdxList.add(Long.valueOf(buttonArr[i]));
                }
            }

            // 查询当前资源组、菜单idx 下面的按钮，如果多了则保存，少了则禁用之前的

            Member currentUser = CurrentUserUtils.getCurrentUser();

            Resource resource = new Resource();
            resource.setResourceType(ManagerConstants.BUTTON_TYPE);
            resource.setResourceGroupIdx(Long.valueOf(idx));
            resource.setExt(menuIdx.trim());

            List<Resource> resourceList = resourceService.getListByResource(msOnionApiVersion, resource);
            for (Resource r : resourceList) {
                if (buttonIdxList.contains(r.getMenuButtonIdx())) {
                    // 从集合中移除
                    buttonIdxList.remove(r.getMenuButtonIdx());
                } else {
                    // 禁用
                    resourceService.deleteByPrimaryKey(msOnionApiVersion, r.getIdx());
                }
            }
            if (buttonIdxList.size() > 0) {
                List<Resource> dataList = new ArrayList<>();
                for (int i = 0; i < buttonIdxList.size(); i++) {
                    Resource resource2 = new Resource();
                    resource2.setCreateByMemberIdx(currentUser.getIdx());
                    resource2.setUpdateByMemberIdx(currentUser.getIdx());

                    resource2.setResourceType(ManagerConstants.BUTTON_TYPE);
                    resource2.setResourceGroupIdx(Long.valueOf(idx));
                    resource2.setExt(menuIdx.trim());
                    Button button = buttonService.queryByPrimaryKey(msOnionApiVersion, buttonIdxList.get(i));
                    resource2.setCode(button.getCode());
                    resource2.setMenuButtonIdx(button.getIdx());
                    dataList.add(resource2);
                }
                resourceService.saveBatchResource(msOnionApiVersion, dataList);
            }
            return MsOnionResult.ok();
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

}
