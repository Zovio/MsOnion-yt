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

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.common.utils.MemberStatusUtils;
import cc.msonion.carambola.member.pojo.*;
import cc.msonion.carambola.member.service.MemberCateService;
import cc.msonion.carambola.member.service.MemberRoleService;
import cc.msonion.carambola.member.service.MemberService;
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
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
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
import java.util.*;


/**
 * @Title: MemberController.java
 * @Package: cc.msonion.carambola.manager.web.controller.member
 * @Description: 成员（公司内部）Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月27日 下午9:23:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月27日 下午9:23:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增、删、改、查方法
 */

/**
 * @ClassName: MemberController
 * @Description: 成员（公司内部）Controller
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月27日 下午9:23:42
 */
@Controller
public class MemberController extends MsOnionBaseAppController {

    /**
     * 超级管理员
     */
    @Value("${shiro.member.super}")
    private String shiroSuper;

    /**
     * MemberService
     */
    @Autowired
    private MemberService memberService;


    /**
     * memberCateService
     */
    @Autowired
    private MemberCateService memberCateService;


    /**
     * memberRoleService
     */
    @Autowired
    private MemberRoleService memberRoleService;

    /**
     * memberRoleService
     */
    @Autowired
    private RoleService roleService;

    /**
     * sysDataDictionaryService
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;


    /**
     * 列出所有成员
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/member/list")
    public String listAllMemeber(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
            model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            model.addAttribute("statusMap", statusMap);

            Map sexMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_SEX);
            model.addAttribute("sexMapJson", MsOnionJsonUtils.objectToJson(sexMap));
        } catch (MsOnionException e) {
            return getError();
        }
        return "/member/member/list";

    }

    /**
     * 根据主键 修改成员状态
     *
     * @param req       HttpServletRequest实例对象
     * @param res       HttpServletResponse实例对象
     * @param idxStr    主键idx
     * @param statusStr 状态
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/member/updateStatus/{idxStr}/{statusStr}")
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
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", statusStr=" + statusStr);
            }
            idx = Long.parseLong(idxStr);
            status = Short.parseShort(statusStr);

            int result = memberService.updateStatus(msOnionApiVersion, idx, status);
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
     * 分页列表 (easyui-datagird)
     *
     * @param req  HttpServletRequest实例对象
     * @param res  HttpServletResponse实例对象
     * @param page 页码
     * @param rows 每页数量
     * @return 返回MsOnionResult
     */
    @RequestMapping("/member/paging")
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
            String telphone = req.getParameter("telphone");
            String status = req.getParameter("status");
            String userCode = req.getParameter("userCode");
            String email = req.getParameter("email");
            String userFullName = req.getParameter("userFullName");

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            String orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;

            String sort = req.getParameter("sort");
            String order = req.getParameter("order");
            if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
                orderBy = sort.trim() + " " + order.trim();
            } else {
                orderBy = ManagerConstants.ORDER_BY_UPDATE_TIME;
            }

            MemberExample example = new MemberExample();

            MemberExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(name)) {
                criteria.andNameLike("%" + name.trim() + "%");
            }
            if (StringUtils.isNotBlank(telphone)) {
                criteria.andPhoneLike("%" + telphone.trim() + "%");
            }

            if (StringUtils.isNotBlank(userCode)) {
                criteria.andCodeLike("%" + userCode.trim() + "%");
            }
            if (StringUtils.isNotBlank(email)) {
                criteria.andEmailLike("%" + email.trim() + "%");
            }
            if (StringUtils.isNotBlank(userFullName)) {
                criteria.andFullNameLike("%" + userFullName.trim() + "%");
            }

            if (StringUtils.isNotBlank(status) && MsOnionRegexUtils.isIntegerNotNegative(status)) {
                criteria.andStatusEqualTo(Short.valueOf(status));
            } else {
                // not in 性能低
//                criteria.andStatusNotEqualTo(MsOnionTableRecordStatus.DELETED.getValue());
                criteria.andStatusIn(MemberStatusUtils.getGeneralMemberStatuses());
            }
            // 不要采取这种方式，过滤超级管理员，性能比较低，采取 status 不同值的方式过滤
//            criteria.andNameNotEqualTo(shiroSuper.toLowerCase());
            MsOnionResultAdapter msOnionResultAdapter = this.memberService.queryListByPageForResult(msOnionApiVersion, example,
                    pageNum, pageSize, orderBy);

            if (msOnionResultAdapter != null) {
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
     * 显示编辑
     *
     * @param req    HttpServletRequest实例对象
     * @param res    HttpServletResponse实例对象
     * @param model  Model实例对象
     * @param idxStr 主键
     * @return 返回路径
     */
    @RequestMapping(value = "/member/edit/{idxStr}")
    public String showEdit(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {
        try {
            long idx = 0;
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(), "##　请求参数非法, idxStr=");
                return getError();
            }

            idx = Long.parseLong(idxStr);
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            Member member = this.memberService.queryByPrimaryKey(msOnionApiVersion, idx);
            if (null != member) {
                // 考虑到安全，必须屏蔽密码
                member.setPassword(null);
            }
            model.addAttribute("member", member);
            String type = req.getParameter("_type");
            model.addAttribute("_type", type);

            // 查询用户类型
            MemberCateExample example = new MemberCateExample();
            MemberCateExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<MemberCate> memberCateList = this.memberCateService.queryByExample(msOnionApiVersion, example);
            model.addAttribute("memberCateList", memberCateList);

            // 查询用户角色集合
            MemberRole memberRole = new MemberRole();
            memberRole.setMemberIdx(member.getIdx());
            List<Role> roleList = null;
            List<MemberRole> memberRoleList = memberRoleService.getMemberRoleList(msOnionApiVersion, memberRole);
            if (MsOnionCollectionUtils.isNotEmpty(memberRoleList)) {
                roleList = new ArrayList<>(memberRoleList.size());
                for (MemberRole mr : memberRoleList) {
                    Role role = roleService.queryByPrimaryKey(msOnionApiVersion, mr.getRoleIdx());
                    roleList.add(role);
                }
            }

            model.addAttribute("roleList", roleList);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　查询member失败");
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　查询member失败");
        }
        return "/member/member/memberInfoEdit";
    }

    /**
     * 进行编辑（更新）
     *
     * @param req        HttpServletRequest实例对象
     * @param res        HttpServletResponse实例对象
     * @param model      Model实例对象
     * @param member     成员POJO实例对象
     * @param roleIdxStr 角色idx字符串 (加密)
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/member/do-edit", method = {RequestMethod.POST})
    @ResponseBody
    public MsOnionResult doEdit(HttpServletRequest req, HttpServletResponse res, Model model, Member member, String roleIdxStr) {
        try {
            this.getMsOnionLogger().info(getClass().getName(), "doEdit # 执行编辑用户（成员）信息 , member=" + member);
            member.setUpdateTime(new Date());
            member.setUpdateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());

            // 处理前端js加密
            if (member != null) {
                member.setFullName(MsOnionSecurityBase64Utils.decode(member.getFullName()));
                member.setCode(MsOnionSecurityBase64Utils.decode(member.getCode()));
                member.setPhone(MsOnionSecurityBase64Utils.decode(member.getPhone()));
                member.setTel(MsOnionSecurityBase64Utils.decode(member.getTel()));
                member.setEmail(MsOnionSecurityBase64Utils.decode(member.getEmail()));
                member.setRemark(MsOnionSecurityBase64Utils.decode(member.getRemark()));
            }

            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            MsOnionResult msOnionResult = this.memberService.update(msOnionApiVersion, member);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }

            // 处理角色和资源组的关系

            MemberRole memberRole = new MemberRole();
            memberRole.setMemberIdx(member.getIdx());
            memberRole.setCreateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());
            memberRole.setUpdateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());

            List<String> idxList = null;
            if (StringUtils.isNotBlank(roleIdxStr)) {
                String[] idxArr = MsOnionSecurityBase64Utils.decode(roleIdxStr).split(",");
                idxList = Arrays.asList(idxArr);
            }
            msOnionResult = memberRoleService.addMemberRole(msOnionApiVersion, idxList, memberRole);

            return msOnionResult;
        } catch (Exception e) {
            return MsOnionResult.build(MsOnionExecuteResultStatus.ERROR.getValue(), MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }


    /**
     * 查询列表
     *
     * @param req    HttpServletRequest 对象
     * @param res    HttpServletResponse  对象
     * @param model  Model实例对象
     * @param idxStr 角色主键idx
     * @return 访问路径
     */
    @RequestMapping(value = "/member/chooseRole/{idxStr}")
    public String chooseRole(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {
        try {
            List<String> list = new ArrayList<>();
            String roleIdx = req.getParameter("roleIdx");
            list = Optional.ofNullable(roleIdx).map(s -> Arrays.asList(roleIdx.split(","))).orElse(list);
            req.setAttribute("roleIdxListJson", MsOnionJsonUtils.objectToJson(list));
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 查询用户和角色的关系失败。 roleIdx=" + idxStr);
            return getError();
        }
        return "/member/role/chooseRolelist";
    }

    /**
     * 列表修改用户密码
     *
     * @param req    HttpServletRequest实例对象
     * @param res    HttpServletResponse实例对象
     * @param model  Model实例对象
     * @param idxStr 主键
     * @return 返回路径
     */
    @RequestMapping(value = "/member/edit/memberPwd/{idxStr}")
    public String editMemberPwd(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable String idxStr) {
        try {
            long idx = 0;
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(), "##　请求参数非法, idxStr=");
                return getError();
            }

            idx = Long.parseLong(idxStr);
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            Member member = this.memberService.queryByPrimaryKey(msOnionApiVersion, idx);
            if (null != member) {
                // 考虑到安全，必须屏蔽密码
                member.setPassword(null);
            }
            model.addAttribute("member", member);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　查询member失败");
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　查询member失败");
        }
        return "/member/member/memberPwdEdit";
    }

    /**
     * 修改用户密码
     *
     * @param req         HttpServletRequest实例对象
     * @param res         HttpServletResponse实例对象
     * @param model       Model实例对象
     * @param idxStr      成员主键idx
     * @param curPassword 当前用户密码
     * @param newPassword 新密码
     * @return 返回MsOnionResult
     */
    @RequestMapping(value = "/member/edit/do-password", method = RequestMethod.POST)
    @ResponseBody
    public MsOnionResult doUpdatePassword(HttpServletRequest req, HttpServletResponse res, Model model,
                                          String idxStr, String curPassword, String newPassword) {
        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ",idx=" + idxStr);
            }
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isPermitted(ManagerConstants.MEMBER_EDIT_PWD)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "您无权修改别人的密码！");
            }

            // 判断当前用户密码是否正确
            Long curIdx = CurrentUserUtils.getCurrentUser().getIdx();
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            curPassword = MsOnionSecurityBase64Utils.decode(curPassword);
            newPassword = MsOnionSecurityBase64Utils.decode(newPassword);

            return memberService.editMemberPassword(msOnionApiVersion, curIdx, curPassword, Long.valueOf(idxStr), newPassword);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e);
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);

        }
    }

    /**
     * 新增成员
     *
     * @param req   请求对象
     * @param res   响应对象
     * @param model 实体
     * @return 视图
     */
    @RequestMapping(value = "/member/toAddMember")
    public String toAddMember(HttpServletRequest req, HttpServletResponse res, Model model) {
        try {
            // 查询用户类型
            MemberCateExample example = new MemberCateExample();
            MemberCateExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            List<MemberCate> memberCateList = this.memberCateService.queryByExample(msOnionApiVersion, example);
            model.addAttribute("memberCateList", memberCateList);

        } catch (MsOnionException e) {
            return getError();
        }
        return "/member/member/addMemberInfo";
    }

    /**
     * 新增成员
     *
     * @param req        HttpServletRequest实例对象
     * @param res        HttpServletResponse实例对象
     * @param member     成员POJO实例
     * @param roleIdxStr 角色idx
     * @return 返回MsOnionResult
     * @Author: liaoxf
     * @Date: 2017年5月26日
     */
    @RequestMapping(value = "/member/do-save", method = RequestMethod.POST)  // POST 请求
    @ResponseBody
    public MsOnionResult doSave(HttpServletRequest req, HttpServletResponse res, Member member, String roleIdxStr) {
        try {
            this.getMsOnionLogger().info(getClass().getName(), "##doSave#POST请求#新增成员#前端JS加密#member=" + member);
            // 处理前端js加密
            if (member != null) {
                member.setName(MsOnionSecurityBase64Utils.decode(member.getName()));
                member.setFullName(MsOnionSecurityBase64Utils.decode(member.getFullName()));
                member.setPassword(MsOnionSecurityBase64Utils.decode(member.getPassword()));
                member.setPhone(MsOnionSecurityBase64Utils.decode(member.getPhone()));
                member.setTel(MsOnionSecurityBase64Utils.decode(member.getTel()));
                member.setEmail(MsOnionSecurityBase64Utils.decode(member.getEmail()));
                member.setRemark(MsOnionSecurityBase64Utils.decode(member.getRemark()));
            }

            this.getMsOnionLogger().info(getClass().getName(), "##doSave#POST请求#新增成员#解密前端JS的加密#member=" + member);


            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
            MsOnionResult msOnionResult = memberService.register(msOnionApiVersion, member);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return msOnionResult;
            }

            // 处理角色和资源组的关系
            Member newMember = (Member) msOnionResult.getData();
            MemberRole memberRole = new MemberRole();
            memberRole.setMemberIdx(newMember.getIdx());
            memberRole.setCreateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());
            memberRole.setUpdateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());

            if (StringUtils.isNotBlank(roleIdxStr)) {
                String[] idxArr = MsOnionSecurityBase64Utils.decode(roleIdxStr).split(",");
                List<MemberRole> dataList = new ArrayList<>();
                for (int i = 0; i < idxArr.length; i++) {
                    if (MsOnionRegexUtils.checkDigit(idxArr[i])) {
                        MemberRole memberRole1 = new MemberRole();
                        BeanUtils.copyProperties(memberRole, memberRole1);
                        memberRole1.setRoleIdx(Long.valueOf(idxArr[i]));
                        dataList.add(memberRole1);
                    }
                }
                memberRoleService.saveBatchMemberRole(msOnionApiVersion, dataList);
            }
            return msOnionResult;
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　新增成员");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, e.getMessage());
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "##　新增成员");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_MEMBER_REGISTER_FAILURE);
        }
    }

}
