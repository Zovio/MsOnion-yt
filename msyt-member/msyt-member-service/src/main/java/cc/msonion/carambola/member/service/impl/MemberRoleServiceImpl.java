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


package cc.msonion.carambola.member.service.impl;

/**
 * @Title: MemberRoleServiceImpl.java
 * @Package: cc.msonion.carambola.member.service.impl
 * @Description: 用户与 角色 service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月06日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年04月06日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.member.common.constants.MessageConstants;
import cc.msonion.carambola.member.pojo.MemberRole;
import cc.msonion.carambola.member.pojo.MemberRoleExample;
import cc.msonion.carambola.member.service.MemberRoleService;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: MemberRoleServiceImpl
 * @Description: 用户与 角色 service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年04月06日
 */
@Service
public class MemberRoleServiceImpl extends MsOnionServiceExt<MemberRole, MemberRoleExample> implements MemberRoleService {


    private static final long serialVersionUID = 3164771220379609733L;


    @Override
    public MsOnionResult saveMemberRole(MsOnionApiVersion apiVersion,
                                        MemberRole memberRole) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == memberRole) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_NULL);
            }
            // add
            memberRole.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
            String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(memberRole);
            if (StringUtils.isNotEmpty(valiateMsg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
            }

            // 生成Idx
            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MEMBERROLE_IDX_PATH);
            memberRole.setIdx(idx);

            long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MEMBERROLE_VERSION_IDX_PATH);
            memberRole.setVersion(version);

            int result = this.save(apiVersion, memberRole);
            if (result > 0) {
                this.getMsOnionLogger().info(this.getClass().getName(), "## 用户与角色新增成功, result = " + result);
                return MsOnionResult.ok();
            }

            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 批量新增 用户与角色关系
     *
     * @param apiVersion  Api版本
     * @param memberRoles 用户与角色实例集合
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult saveBatchMemberRole(MsOnionApiVersion apiVersion,
                                             List<MemberRole> memberRoles) throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(memberRoles)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_ERROR);

        }

        try {
            Map<Integer, MemberRole> map = new HashMap();
            for (int i = 0; i < memberRoles.size(); i++) {
                MemberRole memberRole = memberRoles.get(i);
                if (null == memberRole) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_NULL);
                }
                // add
                memberRole.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());
                String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(memberRole);
                if (StringUtils.isNotEmpty(valiateMsg)) {
                    return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
                }

                // 生成Idx
                long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MEMBERROLE_IDX_PATH);
                memberRole.setIdx(idx);

                long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                        this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_MEMBER_MEMBERROLE_VERSION_IDX_PATH);
                memberRole.setVersion(version);

                map.put((i + 1), memberRole);
            }
            saveWithBatch(apiVersion, map);
            return MsOnionResult.ok();

        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public MsOnionResult delMemberRole(MsOnionApiVersion apiVersion, MemberRole memberRole) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == memberRole) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MessageConstants.MESSAGE_INFO_NULL);
            }

            MemberRoleExample example = new MemberRoleExample();
            MemberRoleExample.Criteria criteria = example.createCriteria();
            if (null != memberRole.getMemberIdx() && memberRole.getMemberIdx() != 0) {
                criteria.andMemberIdxEqualTo(memberRole.getMemberIdx());
            }

            if (null != memberRole.getRoleIdx() && memberRole.getRoleIdx() != 0) {
                criteria.andRoleIdxEqualTo(memberRole.getRoleIdx());
            }

            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            List<MemberRole> list = this.queryByExample(apiVersion, example);
            if (CollectionUtils.isNotEmpty(list)) {
                for (MemberRole mr : list) {
                    mr.setStatus(MsOnionTableRecordStatus.DELETED.getValue());
                    mr.setUpdateTime(new Date());
                    this.updateByPrimaryKeyForResult(apiVersion, mr);
                }
            }

            this.getMsOnionLogger().info(this.getClass().getName(), "## 用户与角色解除成功. " + memberRole.getMemberIdx() + "--" + memberRole.getRoleIdx());
            return MsOnionResult.ok();
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    @Override
    public List<MemberRole> getMemberRoleList(MsOnionApiVersion apiVersion,
                                              MemberRole memberRole) throws MsOnionIllegalArgumentException, MsOnionException {

        MemberRoleExample example = new MemberRoleExample();
        MemberRoleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        if (memberRole.getRoleIdx() != null && memberRole.getRoleIdx() > 0) {
            criteria.andRoleIdxEqualTo(memberRole.getRoleIdx());
        }

        if (memberRole.getMemberIdx() != null && memberRole.getMemberIdx() > 0) {
            criteria.andMemberIdxEqualTo(memberRole.getMemberIdx());
        }

        return queryByExample(apiVersion, example);

    }

    @Override
    public MsOnionResult addMemberRole(MsOnionApiVersion msOnionApiVersion,
                                       List<String> idxList, MemberRole memberRole) throws MsOnionIllegalArgumentException, MsOnionException {
        List<MemberRole> list = getMemberRoleList(msOnionApiVersion, memberRole);
        if (CollectionUtils.isNotEmpty(list)) {
            List<Long> list2 = list.stream().map(s -> s.getIdx()).collect(Collectors.toList());
           // deleteByIdxes(msOnionApiVersion, list2);
            for (int i = 0; i < list2.size(); i++) {
                updateStatus(msOnionApiVersion, list2.get(i), MsOnionTableRecordStatus.DELETED.getValue());
            }
        }

        if (CollectionUtils.isNotEmpty(idxList)) {
            List<MemberRole> dataList = new ArrayList<>();
            for (int i = 0; i < idxList.size(); i++) {
                String roleIdxStr = idxList.get(i);
                if (MsOnionRegexUtils.checkDigit(roleIdxStr)) {
                    MemberRole memberRole1 = new MemberRole();
                    BeanUtils.copyProperties(memberRole, memberRole1);
                    memberRole1.setRoleIdx(Long.valueOf(roleIdxStr));
                    dataList.add(memberRole1);
                }
            }
            saveBatchMemberRole(msOnionApiVersion, dataList);
        }

        return MsOnionResult.ok();
    }
}
