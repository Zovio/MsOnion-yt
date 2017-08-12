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
package cc.msonion.carambola.item.service.impl;

/**
 * @Title: ItemAttributeOfficialServiceImpl.java
 * @Package: cc.msonion.carambola.item.service.impl
 * @Description: 商品模块商品属性值服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017年6月24日 下午16:42:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017年6月24日 下午16:42:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.item.pojo.ItemAttributeOfficial;
import cc.msonion.carambola.item.pojo.ItemAttributeOfficialExample;
import cc.msonion.carambola.item.service.ItemAttributeOfficialService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemAttributeOfficialServiceImpl
 * @Description: 商品模块商品属性值服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017年6月24日 下午16:42:21
 */
@Service
public class ItemAttributeOfficialServiceImpl extends MsOnionServiceExt<ItemAttributeOfficial, ItemAttributeOfficialExample>
        implements ItemAttributeOfficialService {
    /**
     * 新增商品属性值
     *
     * @param apiVersion            Api版本
     * @param itemAttributeOfficial 商品属性值
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年6月24日 下午16:42:21
     */
    @Override
    public MsOnionResult addItemAttributeOfficial(MsOnionApiVersion apiVersion, ItemAttributeOfficial itemAttributeOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_ATTRIBUTE_IDX_PATH);
        itemAttributeOfficial.setIdx(idx);

        // 新增时默认有效
        itemAttributeOfficial.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_ATTRIBUTE_VERSION_PATH);
        itemAttributeOfficial.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemAttributeOfficial=" + itemAttributeOfficial);

        int result = this.save(apiVersion, itemAttributeOfficial);
        if (result > 0) {
            return MsOnionResult.ok(itemAttributeOfficial);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 保存正式商品属性值
     * @param apiVersion            Api版本
     * @param itemIdx 商品idx
     * @param itemAttributeOfficials 商品属性值
     * @return 处理结果
     * @throws MsOnionException  自定义异常
     */
    @Override
    public MsOnionResult saveItemAttributeOfficial(MsOnionApiVersion apiVersion, Long itemIdx, List<ItemAttributeOfficial> itemAttributeOfficials)
            throws MsOnionException {
        if (itemAttributeOfficials == null || itemAttributeOfficials.size() < 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        // 清除正式商品属性值
        this.clearAttribute(apiVersion, itemIdx);

        // 保存属性值
        for (ItemAttributeOfficial itemAttributeOfficial : itemAttributeOfficials) {
            MsOnionResult result = this.addItemAttributeOfficial(apiVersion, itemAttributeOfficial);
            if (MsOnionStatusConstants.STATUS_200 != result.getStatus()) {
                return result;
            }
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
    }

    /**
     * 修改商品属性值
     *
     * @param apiVersion            Api版本
     * @param itemAttributeOfficial 商品属性值
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年6月24日 下午16:42:21
     */
    @Override
    public MsOnionResult updateItemAttributeOfficial(MsOnionApiVersion apiVersion, ItemAttributeOfficial itemAttributeOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = itemAttributeOfficial.getIdx();

        ItemAttributeOfficial oldData = this.queryByPrimaryKey(apiVersion, idx);
        oldData.setAttributeIdx(itemAttributeOfficial.getAttributeIdx());
        oldData.setAttributeValue(itemAttributeOfficial.getAttributeValue());
        oldData.setUpdateTime(new Date());
        oldData.setUpdateByMemberIdx(itemAttributeOfficial.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, oldData);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, oldData);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 获取商品属性值
     *
     * @param apiVersion      Api版本
     * @param itemOfficialIdx 正式商品主键idx
     * @return 返回商品属性值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public ItemAttributeOfficial queryOneByItemOfficialIdx(MsOnionApiVersion apiVersion, Long itemOfficialIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        ItemAttributeOfficialExample example = new ItemAttributeOfficialExample();
        ItemAttributeOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(itemOfficialIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        return this.queryOne(apiVersion, example);
    }

    /**
     * 查询某个商品的属性列表
     * @param apiVersion 版本
     * @param itemOfficalIdx 商品idx
     * @return 属性列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<ItemAttributeOfficial> getAttributeByItemOfficialIdx(MsOnionApiVersion apiVersion, Long itemOfficalIdx) throws MsOnionException {
        ItemAttributeOfficialExample example = new ItemAttributeOfficialExample();
        ItemAttributeOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andItemOfficialIdxEqualTo(itemOfficalIdx);
        return this.queryByExample(apiVersion, example);
    }

    /**
     * 删除商品属性值
     *
     * @param apiVersion Api版本
     * @param idx 商品属性主键idx
     * @return 返回删除结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult deleteItemAttribute(MsOnionApiVersion apiVersion, Long idx) throws MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        ItemAttributeOfficial itemAttribute = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemAttribute) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 获取商品属性值
     *
     * @param apiVersion   Api版本
     * @param itemOfficialIdx      商品主键idx
     * @param attributeIdx 属性主键idx
     * @return 返回商品属性值
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemAttributeValue(MsOnionApiVersion apiVersion, Long itemOfficialIdx, Long attributeIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        ItemAttributeOfficialExample example = new ItemAttributeOfficialExample();
        ItemAttributeOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(itemOfficialIdx);
        criteria.andAttributeIdxEqualTo(attributeIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<ItemAttributeOfficial> itemAttributeList = this.queryByExample(apiVersion, example);
        if (null == itemAttributeList || itemAttributeList.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        ItemAttributeOfficial itemAttribute = itemAttributeList.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemAttribute);
    }

    /**
     * 清除旧属性
     * @param apiVersion 版本
     * @param itemIdx 商品idx
     * @throws MsOnionException 自定义异常
     */
    private MsOnionResult clearAttribute(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionException {
        List<ItemAttributeOfficial> attributeOfficials = this.getAttributeByItemOfficialIdx(apiVersion, itemIdx);
        for (ItemAttributeOfficial itemAttribute : attributeOfficials) {
            if (null != itemAttribute) {
                Long itemAttributeIdx = itemAttribute.getIdx();
                MsOnionResult rs = this.deleteItemAttribute(apiVersion, itemAttributeIdx);
                if (rs.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return rs;
                }
            }
        }
        return MsOnionResult.ok();
    }
}
