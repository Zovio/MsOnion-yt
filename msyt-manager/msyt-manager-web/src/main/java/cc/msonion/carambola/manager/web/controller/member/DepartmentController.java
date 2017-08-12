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
 * @Title: DepartmentController.java
 * @Package: cc.msonion.carambola.manager.web.controller.member
 * @Description: 部门 controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月06日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月06日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Department;
import cc.msonion.carambola.member.pojo.DepartmentExample;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.DepartmentService;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName: DepartmentController
 * @Description: 部门 controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月06日
 *
 */
@Controller
public class DepartmentController extends MsOnionBaseAppController {

    /**
     *  departmentService
     */
    @Autowired
    private DepartmentService departmentService;


    /**
     *   菜单 新增、编辑、查询页面
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param type 操作类型：add，edit，view
     * @param idx  菜单主键idx
     * @param model  Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/dept/{type}/{idx}")
    public String toSaveOrEditOrViewdept(HttpServletRequest req, HttpServletResponse res,
                                         @PathVariable String type, @PathVariable Long idx, Model model) {
        try {
            if (ManagerConstants.ADD.equals(type)) {
                model.addAttribute("type", type);

            } else if (ManagerConstants.EDIT.equals(type) || ManagerConstants.VIEW.equals(type)) {
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
                Department department = this.departmentService.queryByPrimaryKey(msOnionApiVersion, idx);
                model.addAttribute("dept", department);
                model.addAttribute("type", type);
            }
        } catch (MsOnionException e) {
            e.printStackTrace();
        }
        return "/member/dept/saveOrEditOrViewDept";
    }

    /**
     *
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model Model实例对象
     * @param dept dept 对象
     * @return MsOnionResult对象
     */
    @RequestMapping(value = "/dept/do-saveOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model, Department dept) {
        try {
            Member currentUser = CurrentUserUtils.getCurrentUser();
            dept.setCreateByMemberIdx(currentUser.getIdx());
            dept.setUpdateByMemberIdx(currentUser.getIdx());

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = departmentService.saveOrUpdate(msOnionApiVersion, dept);
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
    @RequestMapping("/dept/paging/{pageNumStr}/{pageSizeStr}/{orderby}")
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

            DepartmentExample example = new DepartmentExample();
            DepartmentExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            return this.departmentService.queryListByPageForResult(msOnionApiVersion, example, pageNum, pageSize, orderby);

        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, e.getMessage(), null);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_500,  MessageConstants.MESSAGE_SERVER_ERROR, null);
        }
    }

    /**
     * 根据主键 禁用菜单
     *
     * @param req    HttpServletRequest实例对象
     * @param res    HttpServletResponse实例对象
     * @param model  Model实例对象
     * @param idxStr 主键idx
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/dept/delete/{idxStr}")
    @ResponseBody
    public MsOnionResult deletedept(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        long idx = 0;
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法, idxStr=" + idxStr);
            }
            idx = Long.parseLong(idxStr);
            Department department = this.departmentService.queryByPrimaryKey(msOnionApiVersion, idx);
            if (null == department) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.DATA_NONE_MEMBER);
            }

            if (MsOnionTableRecordStatus.DELETED.toString().equals(department.getStatus().toString())) {
                return MsOnionResult.ok();
            }

            //更新状态
            department.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
            department.setUpdateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());
            department.setUpdateTime(new Date());
            int result = this.departmentService.updateByPrimaryKey(msOnionApiVersion, department);
            if (result > 0) {
                return MsOnionResult.ok();
            }

            this.getMsOnionLogger().info(getClass().getName(), "# 禁用菜单！" + department.getUpdateByMemberIdx());

            return new MsOnionResult(MsOnionStatusConstants.STATUS_400,  MessageConstants.MESSAGE_SERVER_ERROR, null);

        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }


    /**
     *   菜单树页面
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param model  Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/dept/tree")
    public String deptTree(HttpServletRequest req, HttpServletResponse res, Model model) {

        return "/member/dept/deptTree";
    }


    /**
     * 查询树菜单
     *
     * @param req    HttpServletRequest实例对象
     * @param res    HttpServletResponse实例对象
     * @param model  Model实例对象
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/dept/treeData")
    @ResponseBody
    public MsOnionResult treeData(HttpServletRequest req, HttpServletResponse res, Model model) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {
            DepartmentExample example = new DepartmentExample();
            DepartmentExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            example.setOrderByClause("zindex asc,idx asc");

            List<Department> deptList = this.departmentService.queryByExample(msOnionApiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(deptList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_DATA_NON_EXISTENT);
            }

            List<Map<String, Object>> treeVOList = new ArrayList<Map<String, Object>>(deptList.size());
            Map<String, Object> m = null;
            for (Department dept : deptList) {
                m = new HashMap<String, Object>();
                m.put("id", dept.getId());
                m.put("pId", dept.getPid());
                m.put("name", dept.getName());
                m.put("code", dept.getCode());
                m.put("open", Boolean.FALSE);
                treeVOList.add(m);
            }

            return MsOnionResult.ok(treeVOList);

        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }
}
