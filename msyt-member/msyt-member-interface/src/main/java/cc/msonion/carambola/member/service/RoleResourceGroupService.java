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

package cc.msonion.carambola.member.service;
/**
 * @Title: RoleResourceGroupService.java
 * @Package: cc.msonion.carambola.member.service
 * @Description: 角色与资源组 Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月07日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月07日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.member.pojo.RoleResourceGroup;
import cc.msonion.carambola.member.pojo.RoleResourceGroupExample;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.List;

/**
 * @ClassName: RoleResourceGroupService
 * @Description: 角色与资源组 Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月07日
 *
 */
public interface RoleResourceGroupService extends MsOnionBaseService<RoleResourceGroup, RoleResourceGroupExample> {

    /**
     * 新增 角色与资源组关系
     *
     * @param apiVersion Api版本
     * @param roleResourceGroup 角色与资源组实例
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException 异常
     */
    MsOnionResult saveRoleResourceGroup(MsOnionApiVersion apiVersion,
                                        RoleResourceGroup roleResourceGroup) throws MsOnionIllegalArgumentException, MsOnionException;



    /**
     * 新增 角色与资源组关系
     *
     * @param apiVersion Api版本
     * @param roleResourceGroups 角色与资源组实例集合
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException 异常
     */
    MsOnionResult saveBatchRoleResourceGroup(MsOnionApiVersion apiVersion,
                                        List<RoleResourceGroup> roleResourceGroups) throws MsOnionIllegalArgumentException, MsOnionException;



    /**
     *  通过条件查询角色与资源的关系列表 (支持条件resourceGroupIdx/roleIdx)
     * @param apiVersion 版本号
     * @param roleResourceGroup 角色与资源的关系
     * @return 资源集合
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException 异常
     */
    List<RoleResourceGroup> getListByResourceRoleResourceGroup(
            MsOnionApiVersion apiVersion, RoleResourceGroup roleResourceGroup) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     *  添加 角色与资源组关系 （先删除旧关系再新增）
     * @param msOnionApiVersion 版本号
     * @param idxList 资源组idx
     * @param roleResourceGroup  角色与资源组关系对象
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException 异常
     */
    MsOnionResult addRoleResourceGroup(MsOnionApiVersion msOnionApiVersion, List<String> idxList,
                                       RoleResourceGroup roleResourceGroup) throws MsOnionIllegalArgumentException, MsOnionException;
}
