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
package cc.msonion.carambola.collector.service.impl;

/**
 * @Title: EditorServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: 采编服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/22 15:02
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/22 15:02
 * @Modify-version: V2.0.0
 * @Modify-description: 针对采编的CRUD操作
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.CollectorItemCollection;
import cc.msonion.carambola.collector.pojo.CollectorItemCollectionExample;
import cc.msonion.carambola.collector.pojo.CollectorItemEditor;
import cc.msonion.carambola.collector.pojo.CollectorItemEditorExample;
import cc.msonion.carambola.collector.service.EditorService;
import cc.msonion.carambola.collector.service.ItemCollectionService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: EditorServiceImpl
 * @Description: 采编服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/22 15:02
 */
@Service
public class EditorServiceImpl extends MsOnionServiceExt<CollectorItemEditor, CollectorItemEditorExample> implements EditorService {
    /**
     * 商品采集服务
     */
    @Autowired
    private ItemCollectionService itemCollectionService;

    /**
     * 新增采编信息
     *
     * @param apiVersion Api版本
     * @param itemEditor 采编信息
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemEditor(MsOnionApiVersion apiVersion, CollectorItemEditor itemEditor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_EDITOR_IDX_PATH);
        itemEditor.setIdx(idx);

        itemEditor.setHeadImgMiddle(itemEditor.getHeadImgBig());
        itemEditor.setHeadImgSmall(itemEditor.getHeadImgBig());
        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_EDITOR_VERSION_PATH);
        itemEditor.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemEditor=" + itemEditor);

        int result = this.save(apiVersion, itemEditor);
        if (result > 0) {
            return MsOnionResult.ok(itemEditor);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 批量删除采编信息
     *
     * @param apiVersion Api版本
     * @param ids        采编主键idx集合
     * @return 返回批量删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult batchDeleteItemEditor(MsOnionApiVersion apiVersion, String[] ids) throws MsOnionIllegalArgumentException, MsOnionException {
        for (String idxStr : ids) {
            if (StringUtils.isBlank(idxStr)) {
                continue;
            }

            Long idx = Long.parseLong(idxStr);

            MsOnionResult result = this.deleteItemEditor(apiVersion, idx);
            if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return result;
            }
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * 删除采编信息
     *
     * @param apiVersion Api版本
     * @param idx        采编主键idx
     * @return 返回删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult deleteItemEditor(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemEditor itemEditor = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemEditor) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        Short status = itemEditor.getStatus();
        if (MsOnionTableRecordStatus.DELETED.getValue() == status) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_ILLEGAL_OPERATION, itemEditor);
        }

        // 如果采编有采集信息，那么不能删除
        CollectorItemCollectionExample example = new CollectorItemCollectionExample();
        CollectorItemCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andEditorIdxEqualTo(idx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemCollection> itemCollectionList = itemCollectionService.queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isNotEmpty(itemCollectionList)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "删除失败，该采编有采集信息", itemEditor);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 修改采编信息
     *
     * @param apiVersion Api版本
     * @param itemEditor 采编信息
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateItemEditor(MsOnionApiVersion apiVersion, CollectorItemEditor itemEditor)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == itemEditor) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_EDITOR_NULL);
        }

        Long idx = itemEditor.getIdx();

        // 当状态为禁用时，如果采编有采集信息，那么不能禁用
        Short status = itemEditor.getStatus();
        if (MsOnionTableRecordStatus.DISABLE.getValue() == status) {
            CollectorItemCollectionExample example = new CollectorItemCollectionExample();
            CollectorItemCollectionExample.Criteria criteria = example.createCriteria();
            criteria.andEditorIdxEqualTo(idx);
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

            List<CollectorItemCollection> itemCollectionList = itemCollectionService.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isNotEmpty(itemCollectionList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "不能禁用，该采编有采集信息", itemEditor);
            }
        }

        CollectorItemEditor temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setName(itemEditor.getName());
        temp.setStatus(itemEditor.getStatus());
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(itemEditor.getUpdateByMemberIdx());


        temp.setHeadImgBig(itemEditor.getHeadImgBig());
        temp.setHeadImgMiddle(itemEditor.getHeadImgBig());
        temp.setHeadImgSmall(itemEditor.getHeadImgBig());

        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 获取采编信息
     *
     * @param apiVersion Api版本
     * @param idx        采编主键idx
     * @return 返回采编信息
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getItemEditorByIdx(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == idx || idx <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_IDX_ILLEGAL);
        }

        CollectorItemEditor itemEditor = this.queryByPrimaryKey(apiVersion, idx);
        if (null == itemEditor) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_POJO_OR_IDX_ILLEGAL);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemEditor);
    }

    /**
     * 获取所有采编
     *
     * @param apiVersion Api版本
     * @return 返回所有采编
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult getAllItemEditor(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {
        CollectorItemEditorExample example = new CollectorItemEditorExample();
        CollectorItemEditorExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemEditor> itemEditorList = this.queryByExample(apiVersion, example);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, itemEditorList);
    }
}
