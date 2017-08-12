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


package cc.msonion.carambola.manager.web.security;

/**
 * @Title: CurrentUserUtil.java
 * @Package: cc.msonion.carambola.manager.common.utils
 * @Description: 获取当前用户的一些操作
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月05日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月05日
 * @Modify-version: V2.0.0
 * @Modify-description:  获取当前用户
 *
 */

import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.member.common.constants.MemberConstants;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.MemberService;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionIPUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.system.pojo.SysMemberLoginLog;
import cc.msonion.carambola.system.service.SysMemberLoginLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * @ClassName: CurrentUserUtil
 * @Description: 获取当前用户的一些操作
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月05日
 *
 */
public final class CurrentUserUtils {

    /**
     * 登录成功
     */
    public static final short LOGIN_SUC = 1;

    /**
     * 登录失败
     */
    public static final short LOGIN_FAIL = 2;

    /**
     *  私用构造方法
     */
    private CurrentUserUtils() {

    }

    /**
     *  获取session中当前用户
     * @return 当前用户
     */
    public static Member getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        Member member = (Member) currentUser.getSession().getAttribute(ManagerConstants.SESSION_MEMBER);
        return member;
    }

    /**
     * 记录用户登录日志
     * @param msOnionLogger 日志服务
     * @param memberService 成员服务
     * @param sysMemberLoginLogService 登录日志服务
     * @param req 请求对象
     * @param username 登录名
     * @throws MsOnionException 异常
     */
    public static void saveLoginLog(MsOnionLogger msOnionLogger, MemberService memberService, SysMemberLoginLogService sysMemberLoginLogService,
                                    HttpServletRequest req, String username) {
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(MemberConstants.API_VERSION);
        Long memberIdx = getCurrentUser() == null ? 0L : getCurrentUser().getIdx();
        short loginStatus = LOGIN_SUC;
        try {
            if (memberIdx != null && memberIdx > 0) {
                Member member = memberService.queryByPrimaryKey(msOnionApiVersion, memberIdx);
                member.setLastLoginIp(MsOnionIPUtils.getIp(req));
                member.setLastLoginTime(new Date());
                memberService.updateByPrimaryKey(msOnionApiVersion, member);
            } else {
                loginStatus = LOGIN_FAIL;
            }
            SysMemberLoginLog loginLog = new SysMemberLoginLog();
            loginLog.setLoginName(username);
            loginLog.setIpAddress(MsOnionIPUtils.getIp(req));
            loginLog.setLoginStatus(loginStatus);
            loginLog.setCreateByMemberIdx(memberIdx);
            loginLog.setUpdateByMemberIdx(memberIdx);
            sysMemberLoginLogService.saveLoginLog(msOnionApiVersion, loginLog);
            msOnionLogger.info(CurrentUserUtils.class.getName(), "### 用户 " + username + " 登录记录用户日志成功...");
        } catch (MsOnionException e) {
            msOnionLogger.error(CurrentUserUtils.class.getName(), e, "### 用户 " + username + " 登录记录用户日志失败...");
        }
    }


}
