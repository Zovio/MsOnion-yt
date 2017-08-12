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
package cc.msonion.carambola.system.api.controller.app;
/**
 * @Title: VerifyCodeController
 * @Package: cc.msonion.carambola.system.api.controller.app
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年07月21日 15:19:00
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年07月21日 15:19:00
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.commons.web.common.utils.MsOnionVerifyCodeUtils;
import cc.msonion.carambola.commons.web.controller.base.MsOnionBaseAppController;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.ext.utils.MsOnionSysSetUtils;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionSysSetCodeConstants;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionUUIDUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.system.common.SystemMessageConstants;
import cc.msonion.carambola.system.pojo.SysVerifyCodePicture;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: VerifyCodeController
 * @Description: 描述信息
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年07月21日 15:19:00
 */
@Controller
public class VerifyCodeController extends MsOnionBaseAppController {

    /**
     * dynamicCacheService
     */
    @Autowired
    private DynamicCacheService dynamicCacheService;

    /**
     * memberService
     */
    @Autowired
    private MemberService memberService;

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
     * 显示验证码
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param model Model实例对象
     * @return MsOnionResult MsOnionResult对象json
     */
    @RequestMapping("/getValidateAccessCode")
    @ResponseBody
    public MsOnionResult getValidateAccessCode(HttpServletRequest req, HttpServletResponse res, Model model) {
        this.getMsOnionLogger().debug(getClass().getName(), "getValidateAccessCode # 获取验证码访问码");
        try {
            // 支持跨域
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            // 生成随机字符串
            String encodeKeyValue = MsOnionUUIDUtils.randomUUID();

            // 把key和value存入到redis
            dynamicCacheService.setForRedisWithSecurity(memberService.getUserVefiyCache(encodeKeyValue),
                    MsOnionConstants.PERCENT, ManagerConstants.VERIFYCODE_EXPIRE_TIME);

            return MsOnionResult.ok(MsOnionSecurityBase64Utils.encode(encodeKeyValue));
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e, "getValidateAccessCode # 获取验证码出错");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, SystemMessageConstants.VERIFY_CODE_ACCESS_CODE_ERROR);
        }
    }



    /**
     * 显示验证码
     *
     * @param req   HttpServletRequest实例对象
     * @param res   HttpServletResponse实例对象
     * @param accessCode 访问码
     * @param model Model实例对象
     */
    @RequestMapping("/validateCode")
    public void validateCode(HttpServletRequest req, HttpServletResponse res, Model model, String accessCode) {

        this.getMsOnionLogger().debug(getClass().getName(), "validateCode # 显示验证码");

        try {
            if (MsOnionStringUtils.isEmpty(accessCode)) {
                this.getMsOnionLogger().debug(getClass().getName(), "accessCode # 访问码为空");
                return;
            }

            accessCode = MsOnionSecurityBase64Utils.decode(accessCode);

            String value = dynamicCacheService.getFromRedisWithSecurity(memberService.getUserVefiyCache(accessCode));
            if (MsOnionStringUtils.isEmpty(value)
                    || !value.equals(MsOnionConstants.PERCENT)) {
                this.getMsOnionLogger().debug(getClass().getName(), "accessCode # 访问码不存在");
                return;
            }

            // 生成验证码并返回值
            MsOnionResult msOnionResult = genVerifyCodePicture(req, res);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                this.getMsOnionLogger().error(this.getClass().getName(), "######生成的验证码异常");
                return;
            }
            String showVerifyCode = (String) msOnionResult.getData();

            // 把key和value存入到redis
            dynamicCacheService.setForRedisWithSecurity(memberService.getUserVefiyCache(accessCode),
                    showVerifyCode, ManagerConstants.VERIFYCODE_EXPIRE_TIME);

            this.getMsOnionLogger().debug(getClass().getName(), "showValidateCode # 生成的验证码 33, showVerifyCode=" + showVerifyCode);

        } catch (Exception e) {

            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }
    }

    /**
     * 生成验证码
     * @param req HttpServletRequest实例对象
     * @param res HttpServletResponse实例对象
     * @return MsOnionResult
     * @throws Exception Exception
     */
    private MsOnionResult genVerifyCodePicture(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 版本对象
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        int verifyCodePictureNum = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_NUMBER));
        // 查询图片集合
        MsOnionResult msOnionResult = sysVerifyCodePictureService.
                queryRandomVerifyCodePicture(msOnionApiVersion, verifyCodePictureNum);
        if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return msOnionResult;
        }
        List<SysVerifyCodePicture> sysVerifyCodePictureList = (List<SysVerifyCodePicture>) msOnionResult.getData();
        List<BufferedImage> fileList = new ArrayList<BufferedImage>();
        String frImgurl = getSysValueBySetKey(MsOnionSysSetCodeConstants.FR_IMGUTL);
        for (SysVerifyCodePicture sysVerifyCodePicture : sysVerifyCodePictureList) {
            String urlString = frImgurl + sysVerifyCodePicture.getImagePath();
            URL url = new URL(urlString);
            BufferedImage bufferedImage = ImageIO.read(url);
            fileList.add(bufferedImage);
        }
        // 图片的尺寸
        int verifyCodePictureSize = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_SIZE));
        // 图片的尺寸
        int ring = Integer.parseInt(MsOnionSysSetUtils.getSysValueBySetKey(sysSettingService, msOnionApiVersion,
                MsOnionSysSetCodeConstants.VERIFYCODE_PICTURE_RING));
        // 生成验证码图片并返回值
        String codeValue = MsOnionVerifyCodeUtils.generateVerifyCodePicture(req, res, verifyCodePictureSize, ring, fileList);

        return msOnionResult.ok(codeValue);
    }
}
