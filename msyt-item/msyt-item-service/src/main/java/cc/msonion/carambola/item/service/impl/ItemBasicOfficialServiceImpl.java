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
 * @Title: ItemBasicOfficialServiceImpl.java
 * @Package: cc.msonion.carambola.item.service.impl
 * @Description: 商品模块商品服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017年6月22日 上午11:10:21
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017年6月22日 上午11:10:21
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：增，删，改，查方法
 */

import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.item.pojo.ItemBasicOfficial;
import cc.msonion.carambola.item.pojo.ItemBasicOfficialExample;
import cc.msonion.carambola.item.service.ItemBasicOfficialService;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: ItemBasicOfficialServiceImpl
 * @Description: 商品模块商品服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017年6月22日 上午11:10:21
 */
@Service
public class ItemBasicOfficialServiceImpl extends MsOnionServiceExt<ItemBasicOfficial, ItemBasicOfficialExample> implements ItemBasicOfficialService {

    /**
     * 新增商品
     *
     * @param apiVersion        Api版本
     * @param itemBasicOfficial 商品
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年6月22日 上午11:10:21
     */
    @Override
    public MsOnionResult addItemItem(MsOnionApiVersion apiVersion, ItemBasicOfficial itemBasicOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_IDX_PATH);
        itemBasicOfficial.setIdx(idx);

        // 新增时默认有效
        itemBasicOfficial.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_ITEM_ITEM_VERSION_PATH);
        itemBasicOfficial.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，itemBasicOfficial=" + itemBasicOfficial);

        int result = this.save(apiVersion, itemBasicOfficial);
        if (result > 0) {
            return MsOnionResult.ok(itemBasicOfficial);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 修改商品
     *
     * @param apiVersion        Api版本
     * @param itemBasicOfficial 商品
     * @return 返回修改结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     * @Author JohnnyWoo johnny-woo@msyc.cc
     * @Date 2017年6月22日 上午11:10:21
     */
    @Override
    public MsOnionResult updateItemItem(MsOnionApiVersion apiVersion, ItemBasicOfficial itemBasicOfficial)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = itemBasicOfficial.getIdx();

        ItemBasicOfficial oldData = this.queryByPrimaryKey(apiVersion, idx);
        oldData.setItemNo(itemBasicOfficial.getItemNo());
        oldData.setBarcode(itemBasicOfficial.getBarcode());
        oldData.setCnName(itemBasicOfficial.getCnName());
        oldData.setEnName(itemBasicOfficial.getEnName());
        oldData.setBrandIdx(itemBasicOfficial.getBrandIdx());
        oldData.setOriginIdx(itemBasicOfficial.getOriginIdx());
        oldData.setCategoryIdx(itemBasicOfficial.getCategoryIdx());
        oldData.setSpecification(itemBasicOfficial.getSpecification());
        oldData.setBatch(itemBasicOfficial.getBatch());
        oldData.setWeight(itemBasicOfficial.getWeight());
        oldData.setItemStateIdx(itemBasicOfficial.getItemStateIdx());
        oldData.setCollectionStatus(itemBasicOfficial.getCollectionStatus());
        oldData.setCollectionRemark(itemBasicOfficial.getCollectionRemark());
        oldData.setUpdateTime(new Date());
        oldData.setUpdateByMemberIdx(itemBasicOfficial.getUpdateByMemberIdx());

        int result = this.updateByPrimaryKey(apiVersion, oldData);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, oldData);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }

    /**
     * 获取商品
     *
     * @param apiVersion Api版本
     * @param sourceIdx  源主键idx
     * @return 返回商品
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public ItemBasicOfficial queryOneBySourceIdx(MsOnionApiVersion apiVersion, Long sourceIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        ItemBasicOfficialExample example = new ItemBasicOfficialExample();
        ItemBasicOfficialExample.Criteria criteria = example.createCriteria();
        criteria.andItemOfficialIdxEqualTo(sourceIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        return this.queryOne(apiVersion, example);
    }
}
