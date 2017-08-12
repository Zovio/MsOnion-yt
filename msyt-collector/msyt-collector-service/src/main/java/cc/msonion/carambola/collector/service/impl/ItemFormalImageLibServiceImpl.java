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
 * @Title: ItemFormalImageLibServiceImpl.java
 * @Package: cc.msonion.carambola.collector.service.impl
 * @Description: 商品正式图片实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月05日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年06月05日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.collector.common.constants.CollectorMessageConstants;
import cc.msonion.carambola.collector.pojo.CollectorItemFormalImageLib;
import cc.msonion.carambola.collector.pojo.CollectorItemFormalImageLibExample;
import cc.msonion.carambola.collector.service.ItemFormalImageLibService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
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
 * @ClassName: ItemFormalImageLibServiceImpl
 * @Description: 商品正式图片实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年06月05日
 *
 */
@Service
public class ItemFormalImageLibServiceImpl  extends MsOnionServiceExt<CollectorItemFormalImageLib, CollectorItemFormalImageLibExample>
        implements ItemFormalImageLibService {


    private static final long serialVersionUID = 1367918974785353322L;

    /**
     * 新增商品图片
     *
     * @param apiVersion                  Api版本
     * @param collectorItemFormalImageLib 商品图片
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addItemFormalImags(MsOnionApiVersion apiVersion,
                                            CollectorItemFormalImageLib collectorItemFormalImageLib)
            throws MsOnionIllegalArgumentException, MsOnionException {
        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_FORMAL_IMAGE_IDX_PATH);
        collectorItemFormalImageLib.setIdx(idx);
        collectorItemFormalImageLib.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        collectorItemFormalImageLib.setWhiteBackgroundImageSmall(collectorItemFormalImageLib.getWhiteBackgroundImageBig());
        collectorItemFormalImageLib.setWhiteBackgroundImageMiddle(collectorItemFormalImageLib.getWhiteBackgroundImageBig());

        collectorItemFormalImageLib.setMainPushImageMiddle(collectorItemFormalImageLib.getMainPushImageBig());
        collectorItemFormalImageLib.setMainPushImageSmall(collectorItemFormalImageLib.getMainPushImageBig());

        collectorItemFormalImageLib.setDetailPageMainImageSmall(collectorItemFormalImageLib.getDetailPageMainImageBig());
        collectorItemFormalImageLib.setDetailPageMainImageMiddle(collectorItemFormalImageLib.getDetailPageMainImageBig());
        collectorItemFormalImageLib.setContentInfo(collectorItemFormalImageLib.getContentInfo());
        // 版本号 , 不使用 idx 避免理解歧义
        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_FORMAL_IMAGE_VERSION_IDX_PATH);
        collectorItemFormalImageLib.setVersion(version);

        this.getMsOnionLogger().info(this.getClass().getName(), "准备新增，collectorItemFormalImageLib=" + collectorItemFormalImageLib);

        int result = this.save(apiVersion, collectorItemFormalImageLib);
        if (result > 0) {
            return MsOnionResult.ok(collectorItemFormalImageLib);
        }

        this.getMsOnionLogger().info(this.getClass().getName(), "新增结果，result=" + result);

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);

    }

    /**
     * 更新商品图片
     *
     * @param apiVersion                  Api版本
     * @param collectorItemFormalImageLib 商品图片
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateItemCategory
     * @Description 更新商品类目
     */
    @Override
    public MsOnionResult updateItemFormalImags(MsOnionApiVersion apiVersion,
                                               CollectorItemFormalImageLib collectorItemFormalImageLib)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == collectorItemFormalImageLib) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "### 对象为空");
        }

        Long idx = collectorItemFormalImageLib.getIdx();

        CollectorItemFormalImageLib temp = this.queryByPrimaryKey(apiVersion, idx);
        temp.setUpdateTime(new Date());
        temp.setUpdateByMemberIdx(collectorItemFormalImageLib.getUpdateByMemberIdx());

        temp.setWhiteBackgroundImageBig(collectorItemFormalImageLib.getWhiteBackgroundImageBig());
        temp.setWhiteBackgroundImageSmall(collectorItemFormalImageLib.getWhiteBackgroundImageBig());
        temp.setWhiteBackgroundImageMiddle(collectorItemFormalImageLib.getWhiteBackgroundImageBig());

        temp.setMainPushImageBig(collectorItemFormalImageLib.getMainPushImageBig());
        temp.setMainPushImageMiddle(collectorItemFormalImageLib.getMainPushImageBig());
        temp.setMainPushImageSmall(collectorItemFormalImageLib.getMainPushImageBig());

        temp.setDetailPageMainImageBig(collectorItemFormalImageLib.getDetailPageMainImageBig());
        temp.setDetailPageMainImageSmall(collectorItemFormalImageLib.getDetailPageMainImageBig());
        temp.setDetailPageMainImageMiddle(collectorItemFormalImageLib.getDetailPageMainImageBig());

        temp.setUpdateByMemberIdx(collectorItemFormalImageLib.getUpdateByMemberIdx());

        temp.setContentInfo(collectorItemFormalImageLib.getContentInfo());
        int result = this.updateByPrimaryKey(apiVersion, temp);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                    MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS, temp);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);

    }

    /**
     * 通过商品idx获取 正式图片
     *
     * @param apiVersion Api版本
     * @param itemIdx    商品idx
     * @return the itemCategory by idx
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     */
    @Override
    public MsOnionResult getItemFormalImagsByItemIdx(MsOnionApiVersion apiVersion,
                                                     Long itemIdx) throws MsOnionIllegalArgumentException, MsOnionException {
        if (itemIdx == null || itemIdx <= 0L) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_COLLECTION);
        }

        CollectorItemFormalImageLibExample example = new CollectorItemFormalImageLibExample();

        CollectorItemFormalImageLibExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        List<CollectorItemFormalImageLib> list = this.queryByExample(apiVersion, example);
        if (null == list || list.size() <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CollectorMessageConstants.MESSAGE_ITEM_NOT_FOUND_COLLECTION);
        }

        CollectorItemFormalImageLib lib = list.get(0);
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, lib);

    }
}
