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


package cc.msonion.carambola.manager.web.controller.ext;

/**
 * @Title: AuthExtUtil.java
 * @Package: cc.msonion.carambola.manager.web.controller.ext
 * @Description: 权限管理Util
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月19日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月19日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.manager.common.constants.ManagerConstants;
import cc.msonion.carambola.manager.common.constants.MessageConstants;
import cc.msonion.carambola.manager.web.security.CurrentUserUtils;
import cc.msonion.carambola.member.pojo.*;
import cc.msonion.carambola.member.service.*;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionApiVersionUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @ClassName: AuthExtUtil
 * @Description: 权限管理Util
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月19日
 */
public final class AuthExtUtils {

    private AuthExtUtils() {

    }

    /**
     * 处理权限选择的资源
     *
     * @param msOnionLogger      日志service
     * @param resourceService 资源service
     * @param menuService     菜单service
     * @param buttonService   按钮service
     * @param buttonsStr      按钮
     * @param menuStr         菜单
     * @param resourceGroup   资源组对象
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    public static MsOnionResult processResources(MsOnionLogger msOnionLogger, ResourceService resourceService, MenuService menuService,
                                                 ButtonService buttonService, String buttonsStr, String menuStr,
                                                 ResourceGroup resourceGroup) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(menuStr)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_PARAMETER_ILLEGAL + ", menuStr=" + menuStr);
        }
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);

        Resource tmp = new Resource();
        tmp.setResourceGroupIdx(resourceGroup.getIdx());
        List<Resource> resourcesList = resourceService.getListByResource(msOnionApiVersion, tmp);

        List<Long> deleteIdxList = new ArrayList<>();
        if (MsOnionCollectionUtils.isNotEmpty(resourcesList)) {
            for (Resource resource : resourcesList) {
                // 禁用资源组对应的资源
                deleteIdxList.add(resource.getIdx());
            }
            resourceService.deleteByIdxes(msOnionApiVersion, deleteIdxList);
        }

        // 处理菜单
        String[] menuArr = menuStr.trim().split(",");
        List<String> menuIdxList = Arrays.asList(menuArr);
        // 新增菜单资源
        // saveResource(resourceService, menuService, buttonService, ManagerConstants.MENU_TYPE, menuIdxList, resourceGroup.getIdx());

        // 处理按钮（格式：菜单idx_按钮idx）
        long a3 = System.currentTimeMillis();
        List<String> buttonIdxList = new ArrayList<>();
        if (StringUtils.isNotBlank(buttonsStr)) {
            String[] buttonArr = buttonsStr.trim().split(",");
            for (int i = 0; i < buttonArr.length; i++) {
                String[] buttonIdArr = buttonArr[i].split("_");
                if (buttonIdArr.length > 1 && menuIdxList.contains(buttonIdArr[0])) {
                    buttonIdxList.add(buttonIdArr[1]);
                }
            }
        }
        // 新增按钮资源
        //saveResource(resourceService, menuService, buttonService, ManagerConstants.BUTTON_TYPE, buttonIdxList, resourceGroup.getIdx());
        long a4 = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ManagerConstants.THREAD_SIZE);
        try {
            // 新增菜单资源线程
            fixedThreadPool.execute(() -> {
                try {
                    saveResource(resourceService, menuService, buttonService, ManagerConstants.MENU_TYPE, menuIdxList, resourceGroup.getIdx());
                } catch (MsOnionException e) {
                    msOnionLogger.error(AuthExtUtils.class.getName(), e, "##保存菜单资源失败....");
                }

            });
            // 新增按钮资源线程
            fixedThreadPool.execute(() -> {
                try {
                    saveResource(resourceService, menuService,
                            buttonService, ManagerConstants.BUTTON_TYPE, buttonIdxList, resourceGroup.getIdx());
                } catch (MsOnionException e) {
                    msOnionLogger.error(AuthExtUtils.class.getName(), e, "##保存按钮资源失败....");
                }
            });

        } catch (Exception e) {
            msOnionLogger.error(AuthExtUtils.class.getName(), e, "++++线程池新增资源失败。。。");
        } finally {
            fixedThreadPool.shutdown();
            msOnionLogger.info(AuthExtUtils.class.getName(), "++++线程池新增资源运行时间： " + (System.currentTimeMillis() - a4) + "ms");

        }
        return MsOnionResult.ok();

    }

    /**
     * 保存资源
     *
     * @param resourceService  资源service
     * @param menuService      菜单servcie
     * @param buttonService    按钮service
     * @param resourceType     资源类型 1：菜单 2：按钮
     * @param resourceList     资源idx字符串集合
     * @param resourceGroupIdx 资源组idx
     * @return MsOnionResult
     * @throws MsOnionException 异常
     */
    static MsOnionResult saveResource(ResourceService resourceService, MenuService menuService,
                                      ButtonService buttonService, Short resourceType,
                                      List<String> resourceList, Long resourceGroupIdx) throws MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(resourceList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_EXAMPLE_ILLEGAL);
        }
        MsOnionApiVersion msOnionApiVersion = MsOnionApiVersionUtils.getApiVersion();
        msOnionApiVersion.setRequestApiVersion(ManagerConstants.API_VERSION);
        List<Resource> list = new ArrayList<>();
        for (int i = 0; i < resourceList.size(); i++) {
            String resourceIdx = resourceList.get(i);
            if (MsOnionRegexUtils.checkDigit(resourceIdx)) {
                Resource resource = new Resource();
                resource.setResourceGroupIdx(resourceGroupIdx);
                long menuButtonIdx = Long.valueOf(resourceIdx);
                resource.setMenuButtonIdx(menuButtonIdx);
                resource.setResourceType(resourceType);
                if (resourceType.shortValue() == ManagerConstants.BUTTON_TYPE.shortValue()) {
                    Button button = buttonService.queryByPrimaryKey(msOnionApiVersion, menuButtonIdx);
                    resource.setCode(button.getCode());
                    // 按钮资源时候需要保存 菜单idx --> ext 前端需要
                    resource.setExt(button.getMenuIdx() + "");
                } else if (resourceType.shortValue() == ManagerConstants.MENU_TYPE.shortValue()) {
                    Menu menu = menuService.queryByPrimaryKey(msOnionApiVersion, menuButtonIdx);
                    resource.setCode(menu.getCode());
                }
                resource.setCreateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());
                resource.setUpdateByMemberIdx(CurrentUserUtils.getCurrentUser().getIdx());
                list.add(resource);
                //resourceService.saveResource(msOnionApiVersion, resource);
            }
        }
        return resourceService.saveBatchResource(msOnionApiVersion, list);
    }


    /**
     * 获取 当前用户拥有的角色 (shiro)
     *
     * @param msOnionApiVersion 版本号
     * @param member            成员
     * @param memberRoleService 成员角色servcie
     * @param roleService       角色servcie
     * @return 角色编码集合
     * @throws MsOnionException 异常
     */
    public static List<String> getRoleList(MsOnionApiVersion msOnionApiVersion, Member member,
                                           MemberRoleService memberRoleService, RoleService roleService) throws MsOnionException {
        MemberRole memberRole = new MemberRole();
        memberRole.setMemberIdx(member.getIdx());
        List<MemberRole> list = memberRoleService.getMemberRoleList(msOnionApiVersion, memberRole);
        List roleList = new ArrayList();
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            for (MemberRole mr : list) {
                Role role = roleService.queryByPrimaryKey(msOnionApiVersion, mr.getRoleIdx());
                roleList.add(role.getCode());
            }
        }
        return roleList;
    }

    /**
     * 获取 当前用户拥有的权限 (shiro)
     *
     * @param msOnionApiVersion        版本号
     * @param member                   成员对象
     * @param memberRoleService        成员角色service
     * @param roleResourceGroupService 角色资源组service
     * @param resourceService          资源service
     * @return 权限编码集合
     * @throws MsOnionException 异常
     */
    public static Set<String> getPermissions(MsOnionApiVersion msOnionApiVersion, Member member,
                                             MemberRoleService memberRoleService, RoleResourceGroupService roleResourceGroupService,
                                             ResourceService resourceService) throws MsOnionException {
        Set<String> permissions = new HashSet();
        List<Resource> resourceList = getAllResourceByResourceType(msOnionApiVersion, member,
                memberRoleService, roleResourceGroupService, resourceService, ManagerConstants.BUTTON_TYPE);
        for (Resource r : resourceList) {
            permissions.add(r.getCode());
        }
        return permissions;
    }

    /**
     * 获取 SUPER拥有的所有按钮权限 (shiro)
     *
     * @param msOnionApiVersion 版本号
     * @param buttonService     按钮service
     * @return 权限编码集合
     * @throws MsOnionException 异常
     */
    public static Set<String> getAllPermissions(MsOnionApiVersion msOnionApiVersion, ButtonService buttonService) throws MsOnionException {
        ButtonExample example = new ButtonExample();
        ButtonExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        List<Button> buttonList = buttonService.queryByExample(msOnionApiVersion, example);

        Set<String> permissions = buttonList.stream().map(Button::getCode).collect(Collectors.toSet());
        return permissions;
    }

    /**
     * 获取 当前用户拥有菜单idx集合 (shiro)
     *
     * @param msOnionApiVersion        版本号
     * @param member                   成员对象
     * @param memberRoleService        成员角色service
     * @param roleResourceGroupService 角色资源组service
     * @param resourceService          资源service
     * @return 菜单idx集合
     * @throws MsOnionException 异常
     */
    public static List<Long> getMenuIdxList(MsOnionApiVersion msOnionApiVersion, Member member,
                                            MemberRoleService memberRoleService, RoleResourceGroupService roleResourceGroupService,
                                            ResourceService resourceService) throws MsOnionException {

        List<Resource> resourceList = getAllResourceByResourceType(msOnionApiVersion, member,
                memberRoleService, roleResourceGroupService, resourceService, ManagerConstants.MENU_TYPE);
//        Set<Long> set = new HashSet();
//        resourceList.parallelStream().forEach(s -> set.add(s.getMenuButtonIdx()));
        Set<Long> set = resourceList.parallelStream().map(Resource::getMenuButtonIdx).collect(Collectors.toSet());
        return new ArrayList(set);
    }


    /**
     * 通过资源类型 获取当前用户拥有的权限 (shiro)
     *
     * @param msOnionApiVersion        版本号
     * @param member                   成员对象
     * @param memberRoleService        成员角色service
     * @param roleResourceGroupService 角色资源组service
     * @param resourceService          资源service
     * @param resourceType             资源类型(1:菜单 2：按钮)
     * @return 资源集合
     * @throws MsOnionException 异常
     */
    public static List<Resource> getAllResourceByResourceType(MsOnionApiVersion msOnionApiVersion, Member member,
                                                              MemberRoleService memberRoleService, RoleResourceGroupService roleResourceGroupService,
                                                              ResourceService resourceService, Short resourceType) throws MsOnionException {
        List<Resource> allResource = new ArrayList<>();

        MemberRole memberRole = new MemberRole();
        memberRole.setMemberIdx(member.getIdx());
        List<MemberRole> list = memberRoleService.getMemberRoleList(msOnionApiVersion, memberRole);
        // 查询用户 --> 角色集合
        List<Long> roleIdxList = new ArrayList<>();
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            for (MemberRole mr : list) {
                roleIdxList.add(mr.getRoleIdx());
            }
            //  角色 --> 资源组 --> 资源
            RoleResourceGroupExample example = new RoleResourceGroupExample();
            RoleResourceGroupExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            criteria.andRoleIdxIn(roleIdxList);
            List<RoleResourceGroup> rrgList = roleResourceGroupService.queryByExample(msOnionApiVersion, example);
            List<Long> roleResourceGroupIdxList = new ArrayList<>();
            if (MsOnionCollectionUtils.isNotEmpty(rrgList)) {
                for (RoleResourceGroup roleResourceGroup : rrgList) {
                    roleResourceGroupIdxList.add(roleResourceGroup.getResourceGroupIdx());
                }
                ResourceExample example1 = new ResourceExample();
                ResourceExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                criteria1.andResourceTypeEqualTo(resourceType);
                criteria1.andResourceGroupIdxIn(roleResourceGroupIdxList);
                List<Resource> resourceList = resourceService.queryByExample(msOnionApiVersion, example1);
                if (MsOnionCollectionUtils.isNotEmpty(resourceList)) {
                    allResource.addAll(resourceList);
                }
            }
        }
        return allResource;
    }

}
