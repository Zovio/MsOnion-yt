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
 * @Title: MenuController.java
 * @Package: cc.msonion.carambola.manager.web.controller.member
 * @Description: 后台菜单控制类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月31日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月01日
 * @Modify-version: V2.0.0
 * @Modify-description: c u r d基本方法
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.controller.ext.AuthExtUtils;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.ext.view.MenuViewObject;
import cc.msonion.carambola.member.ext.view.TreeViewObject;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.Menu;
import cc.msonion.carambola.member.pojo.MenuExample;
import cc.msonion.carambola.member.service.ButtonService;
import cc.msonion.carambola.member.service.MemberRoleService;
import cc.msonion.carambola.member.service.MenuService;
import cc.msonion.carambola.member.service.RoleService;
import cc.msonion.carambola.parent.common.constants.MsOnionDictCodeConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MenuController
 * @Description: 后台菜单控制类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月31日
 */
@Controller
public class MenuController extends MsOnionBaseAppController {

    /**
     * menuService
     */
    @Autowired
    private MenuService menuService;

    /**
     * sysDataDictionaryService
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * buttonService
     */
    @Autowired
    private ButtonService buttonService;

    /**
     * memberRoleService
     */
    @Autowired
    private MemberRoleService memberRoleService;

    /**
     * roleService
     */
    @Autowired
    private RoleService roleService;

    /**
     * 需要隐藏的菜单idx
     */
    @Value("${hide_menu_idxs}")
    private String hideMenuIdxs;


    /**
     * 超级管理员
     */
    @Value("${shiro.member.super}")
    private String shiroSuper;

    /**
     * 菜单 新增、编辑、查询页面
     *
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param type  操作类型：add，edit，view (新增时idx =1 )
     * @param idx   菜单主键idx
     * @param model Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/menu/{type}/{idx}")
    public String toSaveOrEditOrViewMenu(HttpServletRequest req, HttpServletResponse res,
                                         @PathVariable String type, @PathVariable String idx, Model model) {
        try {
            if (!MsOnionRegexUtils.checkDigit(idx)) {
                this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idx=" + idx);
                return getError();
            }
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            if (ManagerConstants.ADD.equals(type)) {
                model.addAttribute("type", type);

            } else if (ManagerConstants.EDIT.equals(type) || ManagerConstants.VIEW.equals(type)) {
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
                Menu menu = this.menuService.queryByPrimaryKey(msOnionApiVersion, Long.valueOf(idx));
                model.addAttribute("menu", menu);
                model.addAttribute("type", type);
            } else {
                return getError();
            }
        } catch (MsOnionException e) {
            this.getMsOnionLogger().info(getClass().getName(), e, "### 进入菜单操作页失败。");
            return getError();
        }
        return "/member/menu/saveOrEditOrViewMenu";
    }

    /**
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param model Model实例对象
     * @param menu  Menu 对象
     * @return MsOnionResult对象
     */
    @RequestMapping(value = "/menu/do-saveOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model, Menu menu) {
        try {
            Member currentUser = CurrentUserUtils.getCurrentUser();
            menu.setCreateByMemberIdx(currentUser.getIdx());
            menu.setUpdateByMemberIdx(currentUser.getIdx());

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = menuService.saveOrUpdate(msOnionApiVersion, menu);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }
            if (ManagerConstants.YES.equals(menu.getMarkButton().toString())) {
                Menu menu1 = (Menu) msOnionResult.getData();
                MsOnionResult msResult = null;
                try {
                    msResult = buttonService.saveDefaultButtons(msOnionApiVersion, menu1);
                } catch (MsOnionException e) {
                    getMsOnionLogger().error(getClass().getName(), e, "### 菜单生成默认按钮失败！");
                }
                getMsOnionLogger().info(getClass().getName(), "#### 默认生成按钮结果：" + msResult.getMsg() + " , menuIdx=" + menu1.getIdx());
            }
            return msOnionResult;
        } catch (MsOnionException e) {
            e.printStackTrace();
            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), "服务器忙，请稍后再试 ...");
        }
    }


    /**
     * 排序、分页查询
     *
     * @param req         HttpServletRequest实例对象
     * @param res         HttpServletResponse实例对象
     * @param model       Model实例对象
     * @param pageNumStr  页码
     * @param pageSizeStr 每页数量
     * @param orderby     排序
     * @return 返回MsOnionResultAdapter
     */
    @RequestMapping("/menu/paging/{pageNumStr}/{pageSizeStr}/{orderby}")
    @ResponseBody
    public MsOnionResultAdapter pagingByOrder(HttpServletRequest req, HttpServletResponse res, Model model,
                                              @PathVariable String pageNumStr, @PathVariable String pageSizeStr, @PathVariable String orderby) {

        // 如果是非法参数， 可以默认第1页，20条记录
        if (!MsOnionRegexUtils.checkDigit(pageNumStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法, pageNumStr=" + pageNumStr);
        }

        if (!MsOnionRegexUtils.checkDigit(pageSizeStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法, pageSizeStr=" + pageSizeStr);
        }

        try {

            int pageNum = Integer.parseInt(pageNumStr);
            int pageSize = Integer.parseInt(pageSizeStr);

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            MenuExample example = new MenuExample();
            MenuExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            return this.menuService.queryListByPageForResult(msOnionApiVersion, example, pageNum, pageSize, orderby);

        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, e.getMessage(), null);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR, null);
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
    @RequestMapping(value = "/menu/updateStatus/{idxStr}/{statusStr}", method = {RequestMethod.POST})
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

            int result = menuService.updateStatus(msOnionApiVersion, idx, status);
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
     * 菜单页面 (easyui-tree)
     *
     * @param req   HttpServletRequest 对象
     * @param res   HttpServletResponse  对象
     * @param model Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/menu/list")
    public String toList(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
            model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            model.addAttribute("statusMap", statusMap);

            // 非SUPER角色组需要隐藏的菜单
            Member member = CurrentUserUtils.getCurrentUser();
            List roleList = AuthExtUtils.getRoleList(apiVersion, member, memberRoleService, roleService);
            if (!roleList.contains(shiroSuper)) {
                model.addAttribute("hideMenuIdxs", hideMenuIdxs);
            }
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ### 菜单列表页获取菜单树失败...");
            return getError();
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, " ### 菜单列表页获取菜单树失败...");
            return getError();
        }

        return "/member/menu/menuList";
    }


    /**
     * 查询树菜单 (z-tree 测试)
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/menu/treeData")
    @ResponseBody
    public MsOnionResult treeData(HttpServletRequest req, HttpServletResponse res, Model model) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            MenuExample example = new MenuExample();
            MenuExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            example.setOrderByClause(ManagerConstants.ORDER_BY_ZINDEX_ASC);

            List<Menu> menuList = this.menuService.queryByExample(msOnionApiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(menuList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_DATA_NON_EXISTENT);
            }

            List<TreeViewObject> treeVOList = new ArrayList<TreeViewObject>(menuList.size());
            TreeViewObject vo = null;
            for (Menu menu : menuList) {
                vo = new TreeViewObject();
                vo.setId(menu.getId());
                vo.setpId(menu.getPid());
                vo.setName(menu.getName());
                vo.setCode(menu.getCode());
                vo.setOpen(Boolean.FALSE);
                treeVOList.add(vo);
            }

            return MsOnionResult.ok(treeVOList);

        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     * 首页查询树菜单(easyui-tree)
     *
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/menu/getMenuData")
    @ResponseBody
    public List<MenuViewObject> getMenuData(HttpServletRequest req, HttpServletResponse res) {
        try {
            MenuViewObject menuViewObject = (MenuViewObject) WebUtils.getSessionAttribute(req, ManagerConstants.SESSION_MENU);
            // if (menuViewObject == null) {
            MsOnionApiVersion apiVersion = MsOnionApiVersionUtils.getApiVersion();
            apiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = menuService.getMenuViewObject(apiVersion, null);
            menuViewObject = (MenuViewObject) msOnionResult.getData();
            List<MenuViewObject> children = menuViewObject.getChildren();
            // }
            return children;
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_SERVER_ERROR);
        }
        return null;

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
    @RequestMapping("/menu/paging")
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
            String pid = req.getParameter("pid");

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            MenuExample example = new MenuExample();
            String orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;

            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + " " + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            MenuExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike("%" + name.trim() + "%");
            }

            if (StringUtils.isNotBlank(pid) && MsOnionRegexUtils.checkDigit(pid)) {
                criteria.andPidxEqualTo(Long.valueOf(pid));
            }

            if (StringUtils.isNotBlank(status) && MsOnionRegexUtils.isIntegerNotNegative(status)) {
                criteria.andStatusEqualTo(Short.valueOf(status));
            } else {
                criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            }

            // 不查询指定的菜单idx
            Member member = CurrentUserUtils.getCurrentUser();
            List roleList = AuthExtUtils.getRoleList(msOnionApiVersion, member, memberRoleService, roleService);
            if (!roleList.contains(shiroSuper) && StringUtils.isNotBlank(hideMenuIdxs)) {
                List<Long> excludIdx = new ArrayList<>();
                String[] arr = hideMenuIdxs.split(",");
                for (int i = 0; i < arr.length; i++) {
                    excludIdx.addAll(menuService.getChildIdxsByPidx(msOnionApiVersion, Long.valueOf(arr[i])));
                }
                criteria.andIdxNotIn(excludIdx);
            }

            MsOnionResultAdapter msOnionResultAdapter = this.menuService.queryListByPageForResult(msOnionApiVersion, example,
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
     * 通过菜单菜单级别查询菜单集合
     *
     * @param req         HttpServletRequest实例对象
     * @param res         HttpServletResponse实例对象
     * @param model       Model实例对象
     * @param levelStr  页码
     * @return 返回MsOnionResult
     */
    @RequestMapping("/menu/getMenusByLevel/{levelStr}")
    @ResponseBody
    public MsOnionResult getMenusByLevel(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String levelStr) {
        if (!MsOnionRegexUtils.checkDigit(levelStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法, levelStr=" + levelStr);
        }
        try {
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            return this.menuService.getMenusByLevel(msOnionApiVersion, Short.valueOf(levelStr));
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, e.getMessage(), null);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR, null);
        }
    }
}
