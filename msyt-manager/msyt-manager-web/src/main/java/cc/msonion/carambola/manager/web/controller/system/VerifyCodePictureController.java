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
package cc.msonion.carambola.manager.web.controller.system;
/**
 * @Title: VerifyCodePictureController
 * @Package: cc.msonion.carambola.manager.web.controller.system
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年07月19日 14:29:06
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年07月19日 14:29:06
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.content.common.constants.ContentConstants;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.MemberExample;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.*;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.common.SystemMessageConstants;
import cc.msonion.carambola.system.pojo.SysVerifyCodePicture;
import cc.msonion.carambola.system.service.SysDataDictionaryService;
import cc.msonion.carambola.system.service.SysSettingService;
import cc.msonion.carambola.system.service.SysVerifyCodePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @ClassName: VerifyCodePictureController
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年07月19日 14:29:06
 */
@Controller
public class VerifyCodePictureController extends MsOnionBaseAppController {

    /**
     * 数据字典服务
     */
    @Autowired
    private SysDataDictionaryService sysDataDictionaryService;

    /**
     * 验证码图片service
     */
    @Autowired
    private SysVerifyCodePictureService sysVerifyCodePictureService;

    /**
     * sysSettingService
     */
    @Autowired
    private SysSettingService sysSettingService;

    /**
     * memberService
     */
    @Autowired
    private MemberService memberService;
    /**
     * 验证码图片列表页面
     * @param model 模型
     * @return 验证码图片列表页面
     */
    @RequestMapping("/system/verifyCode/list")
    public String list(Model model) {
        try {
            Map verifyCodePictureTypeMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.VERIFY_CODE_PICTURE_TYPE);
            model.addAttribute("verifyCodePictureTypeMap", verifyCodePictureTypeMap);
            model.addAttribute("verifyCodePictureTypeJson", MsOnionJsonUtils.objectToJson(verifyCodePictureTypeMap));
            Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                    ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
            model.addAttribute("statusMapJson", MsOnionJsonUtils.objectToJson(statusMap));
            model.addAttribute("statusMap", statusMap);
            return "/system/verifyCode/verifyCodePictureList";
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 查看验证码图片列表页面失败..");
            return getError();
        }

    }
    /**
     * 查询分页列表
     * @param req  HttpServletRequest实例对象
     * @param res  HttpServletResponse实例对象
     * @param page 当前页
     * @param rows 页大小
     * @param sort 排序字段
     * @param order 排序方式
     * @param remark 备注
     * @param type 类型查询
     * @param status 状态
     * @return 返回MsOnionResult
     */
    @RequestMapping("/system/verifyCode/list/getPage")
    @ResponseBody
    public MsOnionResult getPage(HttpServletRequest req, HttpServletResponse res, String page, String rows,
                                 String sort, String order, String remark, String type, String status) {
        try {
            this.getMsOnionLogger().debug(getClass().getName(),
                    "### 查询验证码对象分页数据...page=" + page + "...rows=" + rows + "...sort=" + sort
                            + "...order=" + order + "...remark=" + remark + "...type=" + type + "...status=" + status);
            // 处理参数
            if (!MsOnionRegexUtils.checkDigit(page)) {
                page = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
            }
            if (!MsOnionRegexUtils.checkDigit(rows)) {
                rows = MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_DEFAULT_VALUE + "";
            }
            if (!MsOnionRegexUtils.isInteger(type)) {
                type = ContentConstants.CONTENT_DEFAULT_TYPE + "";
            }
            if (!MsOnionRegexUtils.isInteger(status)) {
                status = MsOnionStatusConstants.SQL_STATUS_ONE_VALUE + "";
            }
            int pageNum = Integer.parseInt(page);
            int pageSize = Integer.parseInt(rows);
            short typeInt = Short.parseShort(type);
            short statusInt = Short.parseShort(status);
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 分页查询
            MsOnionResult pageResult = sysVerifyCodePictureService.selectVerifyCodePictureage(msOnionApiVersion, pageNum, pageSize, remark,
                    typeInt, statusInt, sort, order);
            // 返回对象给客户端
            return pageResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 查询验证码图片数据分页列表失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 查询验证码图片数据分页列表失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     * 验证码图片保存页面
     * @param model 模型
     * @return 验证码图片保存页面
     */
    @RequestMapping("/system/verifyCode/list/save")
    public String save(Model model) {

        try {
            Map verifyCodePictureTypeMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                    MsOnionDictCodeConstants.VERIFY_CODE_PICTURE_TYPE);
            model.addAttribute("verifyCodePictureTypeMap", verifyCodePictureTypeMap);
            model.addAttribute("verifyCodePictureTypeJson", MsOnionJsonUtils.objectToJson(verifyCodePictureTypeMap));

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            String verifyCodePictureSize = MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                    MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE);
            model.addAttribute("verifyCodePictureSize", verifyCodePictureSize);
            return "/system/verifyCode/verifyCodeSave";
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 验证码图片保存页面失败..");
            return getError();
        }
    }


    /**
     * 保存对象数据
     *
     * @param req     HttpServletRequest 对象
     * @param res     HttpServletResponse  对象
     * @param type 类型
     * @param imagePath 图片路径
     * @param remark 备注
     * @return 页面视图ave
     */
    @RequestMapping("/system/verifyCode/list/doSave")
    @ResponseBody
    public MsOnionResult editContent(HttpServletRequest req, HttpServletResponse res, String type, String imagePath, String remark) {
        try {
            this.getMsOnionLogger().debug(getClass().getName(),
                    "### 保存验证码对象数据...type=" + type + "...imagePath=" + imagePath + "...remark=" + remark);
            if (MsOnionStringUtils.isEmpty(type)
                        || MsOnionStringUtils.isEmpty(imagePath)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }

            // 处理参数
            if (!MsOnionRegexUtils.checkDigit(type)) {
                type = MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE + "";
            }
            if (MsOnionStringUtils.isNotEmpty(remark)) {
                remark = remark.trim();
            }
            short typeInt = Short.parseShort(type);

            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 判断图片格式是否符合规则
            if (MsOnionStringUtils.isEmpty(imagePath)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.VERIFY_CODE_IMAGE_PATH_ERROR);
            }
            try {
                int verifyCodePictureSize = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                        MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE));
                String frImgurl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);
                String urlString = frImgurl + imagePath;
                URL url = new URL(urlString);
                BufferedImage bufferedImage = ImageIO.read(url);
                if (bufferedImage == null
                        || bufferedImage.getWidth() != verifyCodePictureSize
                        || bufferedImage.getHeight() != verifyCodePictureSize) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.VERIFY_CODE_IMAGE_SIZE_ERROR);
                }
            } catch (IOException e) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.VERIFY_CODE_IMAGE_PATH_ERROR);
            }

            // 当前操作者ID
            long operateId = CurrentUserUtils.getCurrentUser().getIdx();

            // 保存数据
            MsOnionResult saveResult = sysVerifyCodePictureService.
                    saveVerifyCodePicture(msOnionApiVersion, imagePath, remark, typeInt, operateId);
            return saveResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 保存验证码图片对象数据失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 保存验证码图片对象数据失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }

    }

    /**
     * 详情页面
     *
     * @param req     HttpServletRequest 对象
     * @param res     HttpServletResponse  对象
     * @param model   Model实例对象
     * @param idxStr     内容对象的ID
     * @return 页面视图
     */
    @RequestMapping("/system/verifyCode/list/detail")
    public String detail(HttpServletRequest req, HttpServletResponse res, Model model, String idxStr) {
        try {
            Long idxLong = 0L;
            if (MsOnionStringUtils.isNotBlank(idxStr)) {
                if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                    this.getMsOnionLogger().error(getClass().getName(),
                            MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                    return getError();
                }
                idxLong = Long.parseLong(idxStr);
            }

            if (idxLong != null && idxLong > 0) {
                // 版本对象
                MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
                msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

                // 查询详情信息
                SysVerifyCodePicture sysVerifyCodePicture = sysVerifyCodePictureService.
                        queryByPrimaryKey(msOnionApiVersion, idxLong);
                model.addAttribute("sysVerifyCodePicture", sysVerifyCodePicture);
                Map verifyCodePictureTypeMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService, ManagerConstants.API_VERSION,
                        MsOnionDictCodeConstants.VERIFY_CODE_PICTURE_TYPE);
                model.addAttribute("verifyCodePictureTypeName", verifyCodePictureTypeMap.get(sysVerifyCodePicture.getType().toString()));
                Map statusMap = MsOnionSysSetUtils.getDictMap(sysDataDictionaryService,
                        ManagerConstants.API_VERSION, MsOnionDictCodeConstants.MSYT_STATUS);
                model.addAttribute("statusName", statusMap.get(sysVerifyCodePicture.getStatus().toString()));

                MemberExample memberExample = new MemberExample();
                MemberExample.Criteria criteria = memberExample.createCriteria();
                criteria.andIdxEqualTo(sysVerifyCodePicture.getCreateByMemberIdx());
                Member member = memberService.queryOne(msOnionApiVersion, memberExample);
                model.addAttribute("createMember", member);
                model.addAttribute("createTime", MsOnionDateUtils.formatYyyyMMddHHmmssSSS(sysVerifyCodePicture.getCreateTime()));
            }
            return "/system/verifyCode/verifyCodePictureDetail";
        } catch (Exception e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "### 查看验证码图片详情页面失败..");
            return getError();
        }
    }


    /**
     * 删除对象
     * @param req HttpServletRequest 对象
     * @param res HttpServletResponse  对象
     * @param idxStr 内容对象的ID
     * @return 页面视图
     */
    @RequestMapping("/system/verifyCode/list/delete")
    @ResponseBody
    public MsOnionResult delete(HttpServletRequest req, HttpServletResponse res, String idxStr) {

        try {
            if (!MsOnionRegexUtils.checkDigit(idxStr)) {
                this.getMsOnionLogger().error(getClass().getName(),
                        MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", idxStr=" + idxStr);
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
            }
            Long idxLong = Long.parseLong(idxStr);

            if (idxLong == null || idxLong <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.DEL_CONTENT_PARAM_ERROR);
            }
            // 版本对象
            MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
            msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

            // 当前操作者ID
            long operateId = CurrentUserUtils.getCurrentUser().getIdx();

            MsOnionResult contentReomveResult = sysVerifyCodePictureService.deleteVerifyCodePicture(msOnionApiVersion, idxLong, operateId);

            return contentReomveResult;
        } catch (MsOnionIllegalArgumentException el) {
            this.getMsOnionLogger().error(getClass().getName(), el, "### 删除验证码图片对象失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL);
        } catch (MsOnionException ex) {
            this.getMsOnionLogger().error(getClass().getName(), ex, "### 删除验证码图片对象失败..");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_SERVER_ERROR);
        }
    }

}



