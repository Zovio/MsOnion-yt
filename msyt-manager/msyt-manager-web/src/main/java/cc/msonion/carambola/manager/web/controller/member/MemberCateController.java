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
 * @Title: MemberCateController.java
 * @Package: cc.msonion.carambola.manager.web.controller.member
 * @Description: 用户组controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月06日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月06日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.MemberCate;
import cc.msonion.carambola.member.pojo.MemberCateExample;
import cc.msonion.carambola.member.service.MemberCateService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MemberCateController
 * @Description: 用户组controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月06日
 *
 */
@Controller
public class MemberCateController extends MsOnionBaseAppController {

    /**
     *  memberCateService
     */
    @Autowired
    private MemberCateService memberCateService;

    /**
     *  sysDataDictionaryService
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;




    /**
     *   新增、编辑、查询页面
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param type 操作类型：add，edit，view
     * @param idxStr  主键idx
     * @param model  Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/memberCate/{type}/{idxStr}")
    public String toSaveOrEditOrViewMenu(HttpServletRequest req, HttpServletResponse res,
                                         @PathVariable String type, @PathVariable String idxStr, Model model) {
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                return getError();
            }

            if (ManagerConstants.ADD.equals(type)) {
                model.addAttribute("type", type);

            } else if (ManagerConstants.EDIT.equals(type) || ManagerConstants.VIEW.equals(type)) {
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
                MemberCate memberCate = this.memberCateService.queryByPrimaryKey(msOnionApiVersion, Long.valueOf(idxStr));
                model.addAttribute("memberCate", memberCate);
                model.addAttribute("type", type);
            } else {
                this.getMsOnionLogger().error(getClass().getName(), MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", type=" + type);
                return getError();
            }
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 进入成员类别页面失败..");
            return getError();
        }
        return "/member/memberCate/saveOrEditOrViewMemberCate";
    }

    /**
     *
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param model Model实例对象
     * @param memberCate memberCate 对象
     * @return MsOnionResult对象
     */
    @RequestMapping(value = "/memberCate/do-saveOrEdit", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model, MemberCate memberCate) {
        try {
            Member currentUser = CurrentUserUtils.getCurrentUser();
            memberCate.setCreateByMemberId(currentUser.getIdx());
            memberCate.setUpdateByMemberId(currentUser.getIdx());

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = memberCateService.saveOrUpdate(msOnionApiVersion, memberCate);
            return msOnionResult;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), MessageConstants.MESSAGE_SERVER_ERROR);
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
    @RequestMapping("/memberCate/paging/{pageNumStr}/{pageSizeStr}/{orderby}")
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

            MemberCateExample example = new MemberCateExample();
            MemberCateExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            return this.memberCateService.queryListByPageForResult(msOnionApiVersion, example, pageNum, pageSize, orderby);

        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, e.getMessage(), null);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return new MsOnionResult(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR, null);
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
    @RequestMapping(value = "/memberCate/delete/{idxStr}")
    @ResponseBody
    public MsOnionResult deletememberCate(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        long idx = 0;
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "请求参数非法, idxStr=" + idxStr);
            }
            idx = Long.parseLong(idxStr);
            MemberCate memberCate = this.memberCateService.queryByPrimaryKey(msOnionApiVersion, idx);
            if (null == memberCate) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.DATA_NONE_MEMBER);
            }

            if (MsOnionTableRecordStatus.DELETED.toString().equals(memberCate.getStatus().toString())) {
                return MsOnionResult.ok();
            }

            //更新状态
            memberCate.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
            memberCate.setUpdateByMemberId(CurrentUserUtils.getCurrentUser().getIdx());
            memberCate.setUpdateTime(new Date());
            int result = this.memberCateService.updateByPrimaryKey(msOnionApiVersion, memberCate);
            if (result > 0) {
                return MsOnionResult.ok();
            }

            this.getMsOnionLogger().info(getClass().getName(), "# 禁用按钮！" + memberCate.getUpdateByMemberId());

            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR, null);

        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     * 查询有效的 用户类别
     * @param req 请求对象
     * @param res 响应对象
     * @param model 实体
     * @return MsOnionResult
     */
    @RequestMapping(value = "/memberCate/listAll")
    @ResponseBody
    public MsOnionResult listAll(HttpServletRequest req, HttpServletResponse res, Model model) {
        MsOnionApiVersion msOnionApiVersion =  MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        try {

            MemberCateExample example = new MemberCateExample();
            MemberCateExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<MemberCate> list = this.memberCateService.queryByExample(msOnionApiVersion, example);

            if (MsOnionCollectionUtils.isNotEmpty(list)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MessageConstants.MESSAGE_OK, list);
            } else {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.DATA_NONE_MEMBER);
            }
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_SERVER_ERROR);
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
    @RequestMapping("/memberCate/paging")
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

            MemberCateExample example = new MemberCateExample();
            String  orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;

            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + " " + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            MemberCateExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike("%" + name.trim() + "%");
            }

            if (StringUtils.isNotBlank(status) && MsOnionRegexUtils.isIntegerNotNegative(status)) {
                criteria.andStatusEqualTo(Short.valueOf(status));
            } else {
                criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
            }

            MsOnionResultAdapter msOnionResultAdapter = this.memberCateService.queryListByPageForResult(msOnionApiVersion, example,
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
    @RequestMapping(value = "/memberCate/updateStatus/{idxStr}/{statusStr}", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult updateStatus(HttpServletRequest req, HttpServletResponse res,
                                      @PathVariable String idxStr, @PathVariable String statusStr) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        long idx = 0;
        short status = 0;
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
            }
            if (!MsOnionRegexUtils.isNumeric(statusStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", statusStr=" + statusStr);
            }
            idx = Long.parseLong(idxStr);
            status = Short.parseShort(statusStr);

            int result = memberCateService.updateStatus(msOnionApiVersion, idx, status);
            if (result > 0) {
                return MsOnionResult.ok();
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, cc.msonion.carambola.member.common.constants.MessageConstants.MESSAGE_INFO_ERROR);

        } catch (MsOnionIllegalArgumentException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     *   新增、编辑、查询页面
     * @param req  HttpServletRequest 对象
     * @param res  HttpServletResponse  对象
     * @param model  Model实例对象
     * @return 访问路径
     */
    @RequestMapping(value = "/memberCate/list")
    public String toList(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
            model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            model.addAttribute("statusMap", statusMap);
        } catch (MsOnionException e) {
            return getError();
        }
        return "/member/memberCate/memberCatelist";
    }

}
