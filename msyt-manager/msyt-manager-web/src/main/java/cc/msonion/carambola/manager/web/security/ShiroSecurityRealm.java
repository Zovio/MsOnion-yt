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
 *  
 *
 * @Title: ShiroSecurityRealm.java
 * @Package: cc.msonion.carambola.manager.web.security.ShiroSecurityRealm
 * @Description: shiro 自定义Realm
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月30日
 * @Version:
 * @Modify-by:
 * @Modify-date:
 * @Modify-version:
 * @Modify-description:
 */

import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.web.controller.ext.AuthExtUtils;
import cc.msonion.carambola.member.common.constants.MemberConstants;
import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.service.*;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: ShiroSecurityRealm
 * @Description: shiro 自定义Realm
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月30日
 */
public class ShiroSecurityRealm extends AuthorizingRealm {


    /**
     * 超级管理员
     */
    @Value("${shiro.member.super}")
    private String shiroSuper;

    /**
     * memberService
     */
    @Autowired
    private MemberService memberService;

    /**
     * memberRoleService
     */
    @Autowired
    private MemberRoleService memberRoleService;

    /**
     * roleResourceGroupService
     */
    @Autowired
    private RoleResourceGroupService roleResourceGroupService;

    /**
     * resourceService
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * roleService
     */
    @Autowired
    private RoleService roleService;

    /**
     * roleService
     */
    @Autowired
    private ButtonService buttonService;

    /**
     * msOnionLogger
     */
    @Autowired
    private MsOnionLogger msOnionLogger;


    /**
     * 认证方法
     *
     * @param authcToken authcToken
     * @return SimpleAuthenticationInfo对象
     * @throws AuthenticationException 异常
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //登录名(用户名、手机号码、email)
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(MemberConstants.API_VERSION);
        try {
            MsOnionResult msOnionResult = this.memberService.selectMemberByLoginName(msOnionApiVersion, userName);
            if (msOnionResult.getStatus() == MsOnionStatusConstants.STATUS_200) {
                Member member = (Member) msOnionResult.getData();
                return new SimpleAuthenticationInfo(member.getIdx() + ManagerConstants.DOLLAR + password, password, getName());
            }
        } catch (Exception e) {
            this.msOnionLogger.error(getClass().getName(), e);
        }
        return null;

    }


    /**
     * 授权
     *
     * @param principals principals
     * @return SimpleAuthorizationInfo对象
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String) principals.fromRealm(getName()).iterator().next();
        Long userId = Long.valueOf(principal.split(ManagerConstants.DOLLAR_ESCAPE)[0]);
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(MemberConstants.API_VERSION);
        try {
            Member member = this.memberService.queryByPrimaryKey(msOnionApiVersion, userId);
            if (null != member) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                // 获取当前用户的角色（list）
                List roleList = AuthExtUtils.getRoleList(msOnionApiVersion, member, memberRoleService, roleService);
                msOnionLogger.info(getClass().getName(), "当前用户：" + member.getName() + ", roleList: " + roleList);
                info.addRoles(roleList);
                // 获取当前用户 拥有的权限
                Set permissions = null;
                if (roleList.contains(shiroSuper)) {
                    permissions = AuthExtUtils.getAllPermissions(msOnionApiVersion, buttonService);
                } else {
                    permissions = AuthExtUtils.getPermissions(msOnionApiVersion, member, memberRoleService,
                            roleResourceGroupService, resourceService);
                }
                msOnionLogger.info(getClass().getName(), "当前用户：" + member.getName() + ", permissions: " + permissions);
                info.addStringPermissions(permissions);
                return info;
            }
        } catch (MsOnionException e) {
            this.msOnionLogger.error(getClass().getName(), e);
        }
        return null;
    }


}
