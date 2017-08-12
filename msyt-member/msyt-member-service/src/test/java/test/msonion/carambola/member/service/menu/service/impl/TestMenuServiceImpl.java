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


package test.msonion.carambola.member.service.menu.service.impl;

/**
 * @Title: MenuServiceImpl.java
 * @Package: cc.msonion.carambola.member.service.impl
 * @Description: 菜单service服务类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月31日
 * @Version: V2.0.0
 * @Modify-by:
 * @Modify-date:
 * @Modify-version:
 * @Modify-description:
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.member.common.constants.MessageConstants;
import cc.msonion.carambola.member.common.constants.ParamTypeConstants;
import cc.msonion.carambola.member.common.constants.PropertiesConstants;
import cc.msonion.carambola.member.ext.utils.MenuToViewObjectUtil;
import cc.msonion.carambola.member.ext.view.MenuViewObject;
import cc.msonion.carambola.member.pojo.Menu;
import cc.msonion.carambola.member.pojo.MenuExample;
import cc.msonion.carambola.member.service.MenuService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionUrlMappingUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import test.msonion.carambola.member.service.menu.service.TestMenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单service服务类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月31日
 */
@Service
public class TestMenuServiceImpl extends MsOnionServiceExt<Menu, MenuExample> implements TestMenuService {


    private static final long serialVersionUID = -4839487595395966662L;


    @Override
    public MsOnionResult inspectParameter(MsOnionApiVersion apiVersion,
                                          String param, Integer type) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (StringUtils.isBlank(param)) {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL, param);
            }
            // 去掉左右空格
            param = param.trim();
            // 创建 UserExample
            MenuExample example = new MenuExample();
            MenuExample.Criteria criteria = example.createCriteria();

            String msg = null;
            if (ParamTypeConstants.MENU_TYPE_CODE == type) {
                criteria.andCodeEqualTo(param);
                msg = ParamTypeConstants.MENU_TYPE_CODE_MSG;
            } else {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400,
                        MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL, type);
            }
            // 查询
            List<Menu> list = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(list)) {
                return MsOnionResult.ok();
            }

            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, msg + "已经存在，不可以使用", param);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public MsOnionResult saveOrUpdate(MsOnionApiVersion apiVersion, Menu menu) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == menu) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_NULL);
            }

            if (menu.getIdx() == null) {
                // add
                menu.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(menu);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }
                // 查询数据库，校验菜单编码
                MsOnionResult moResult = this.inspectParameter(apiVersion, menu.getCode(), ParamTypeConstants.MENU_TYPE_CODE);
                if (moResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return moResult;
                }

                //生成 菜单url 映射
                String urlMapping = MsOnionUrlMappingUtils.genUrlMapping(menu.getUrl());
                menu.setUrlMapping(urlMapping);

                // 生成Idx
                long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MENU_IDX_PATH);
                // 设置 Idx ， 存在一个问题，如果新增失败，但是ZK中已经递增1L，需要处理，但是必须要注意线程安全问题！！！
                menu.setIdx(idx);

                Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MENU_VERSION_IDX_PATH);
                menu.setVersion(version);

                int result = this.save(apiVersion, menu);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 菜单新增成功, result = " + result);
                    return MsOnionResult.ok(menu);
                }
            } else {
                // update
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(menu);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }
                Menu mn = this.queryByPrimaryKey(apiVersion, menu.getIdx());
                // 这些属性排除不需要copy
                String[] excludeArr = new String[]{PropertiesConstants.CODE, PropertiesConstants.IDX, PropertiesConstants.CREATE_BY_MEMBER_IDX,
                        PropertiesConstants.CREATE_TIME, PropertiesConstants.VERSION, PropertiesConstants.STATUS};
                BeanUtils.copyProperties(menu, mn, excludeArr);

                //生成 菜单url 映射
                String urlMapping = MsOnionUrlMappingUtils.genUrlMapping(mn.getUrl());
                mn.setUrlMapping(urlMapping);


                int result = this.updateByPrimaryKey(apiVersion, mn);
                if (result > 0) {
                    this.getMsOnionLogger().info(this.getClass().getName(), "## 菜单更新成功, result = " + result);
                    return MsOnionResult.ok(menu);
                }
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public MsOnionResult getTopMenu(MsOnionApiVersion apiVersion, Integer pageSize) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (pageSize == null || pageSize <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL);
            }
            MenuExample example = new MenuExample();
            MenuExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            criteria.andPidxEqualTo(ParamTypeConstants.TOP_MENU_IDX);
            example.setOrderByClause("zindex asc,idx asc");
            List<Menu> menus = queryListByPage(apiVersion, 1, pageSize);
            return MsOnionResult.ok(menus);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "## 查询顶级菜单失败");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "## 查询顶级菜单失败");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_INFO_ERROR);
        }
    }

    @Override
    public List<Menu> getChildMenusByPidx(MsOnionApiVersion apiVersion, Long pidx) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (pidx == null || pidx < 0) {
                return null;
            }
            MenuExample example = new MenuExample();
            MenuExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            criteria.andPidxEqualTo(pidx);
            example.setOrderByClause("zindex asc,idx asc");

            return queryByExample(apiVersion, example);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "## 通过父级idx，查询子类菜单失败");
            return null;
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "## 查询顶级菜单失败");
            return null;
        }
    }



    @Override
    public MsOnionResult getMenuViewObject(MsOnionApiVersion apiVersion,
                                           List<Long> idxList) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 没有菜单idx直接返回空的，idx == null -> 超级管理员
            if (idxList != null && idxList.size() == 0) {
                return MsOnionResult.ok(new MenuViewObject());
            }
            MenuViewObject menuViewObject = genTree(apiVersion, idxList, ParamTypeConstants.TOP_MENU_IDX);
            return MsOnionResult.ok(menuViewObject);
        } catch (MsOnionIllegalArgumentException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "## 查询菜单树失败");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_MEMBER_PARAMETER_TYPE_ILLEGAL);
        } catch (MsOnionException e) {
            this.getMsOnionLogger().error(getClass().getName(), e, "## 查询菜单树失败");
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_500, MessageConstants.MESSAGE_INFO_ERROR);
        }
    }


    /**
     * 构造菜单树对象
     *
     * @param apiVersion apiVersion
     * @param idxList    idx集合
     * @param rootId     顶级idx
     * @return MenuViewObject对象
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    private MenuViewObject genTree(MsOnionApiVersion apiVersion,
                                   List<Long> idxList, Long rootId) throws MsOnionIllegalArgumentException, MsOnionException {
        Menu menu1 = null;
        MenuViewObject mvo = new MenuViewObject();
        if (rootId == ParamTypeConstants.TOP_MENU_IDX) {
            // 虚拟构造顶级菜单
            menu1 = new Menu();
            menu1.setIdx(rootId);
        } else {
            menu1 = this.queryByPrimaryKey(apiVersion, rootId);
        }
        MenuToViewObjectUtil.menuToVo(menu1, mvo);

        // 查询最顶级菜单
        List<Menu> topMenuList = getChildMenusByPidx(apiVersion, rootId);
        if (MsOnionCollectionUtils.isNotEmpty(topMenuList)) {
            for (Menu menu : topMenuList) {
                if (MsOnionCollectionUtils.isEmpty(idxList) || idxList.contains(menu.getIdx())) {
                    MenuViewObject t = genTree(apiVersion, idxList, menu.getIdx());
                    mvo.getChildren().add(t);
                }
            }
        } else {
            Map map = new HashMap();
            map.put("url", menu1.getUrl());
            map.put("code", menu1.getCode());
            mvo.getAttributes().add(map);
            mvo.setChildren(null);
        }
        return mvo;
    }
}
