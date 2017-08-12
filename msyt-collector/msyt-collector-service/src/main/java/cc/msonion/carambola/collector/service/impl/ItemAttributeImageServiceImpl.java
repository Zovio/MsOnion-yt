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

import cc.msonion.carambola.collector.pojo.CollectorItemAttributeImage;
import cc.msonion.carambola.collector.pojo.CollectorItemAttributeImageExample;
import cc.msonion.carambola.collector.service.ItemAttributeImageService;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ItemAttributeImageServiceImpl
 * @Description: 关键属性图片服务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: Mark 3563249160@qq.com
 * @Date: 2017/6/22
 */
@Service
public class ItemAttributeImageServiceImpl extends MsOnionServiceExt<CollectorItemAttributeImage, CollectorItemAttributeImageExample>
        implements ItemAttributeImageService {
    /**
     * 添加商品关键属性图片
     *
     * @param apiVersion     版本
     * @param attributeImage 关键属性图片参数
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult addItemAttributeImage(MsOnionApiVersion apiVersion, CollectorItemAttributeImage attributeImage)
            throws MsOnionException {

        MsOnionResult msOnionResult = this.initItemAttributeImage(attributeImage);
        if (MsOnionStatusConstants.STATUS_200 != msOnionResult.getStatus()) {
            return msOnionResult;
        }

        attributeImage = (CollectorItemAttributeImage) msOnionResult.getData();

        int result = this.save(apiVersion, attributeImage);
        if (result > 0) {
            return MsOnionResult.ok(attributeImage);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 批量增加商品关键属性图片
     *
     * @param apiVersion      版本
     * @param attributeImages 关键属性图片参数
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    @Override
    public MsOnionResult addItemAttributeImage(MsOnionApiVersion apiVersion, List<CollectorItemAttributeImage> attributeImages)
            throws MsOnionException {
        if (attributeImages == null || attributeImages.size() < 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        this.clearAttributeImageByItemIdx(apiVersion, attributeImages.get(0).getItemIdx());

        Map<Integer, CollectorItemAttributeImage> itemAttributeImageMap = new HashMap<>();
        for (int index = 0; index < attributeImages.size(); index++) {

            CollectorItemAttributeImage attributeImage = attributeImages.get(index);

            MsOnionResult msOnionResult = this.initItemAttributeImage(attributeImage);
            if (MsOnionStatusConstants.STATUS_200 != msOnionResult.getStatus()) {
                return msOnionResult;
            }

            attributeImage = (CollectorItemAttributeImage) msOnionResult.getData();
            itemAttributeImageMap.put(index, attributeImage);
        }

        // 改为底层批量增加
        MsOnionBatchReport batchReport = this.saveWithBatch(apiVersion, itemAttributeImageMap);
        if (null == batchReport || !batchReport.isAllSuccess()) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, "批量增加成功");
    }

    /**
     * 获取某个商品的关键属性图片列表
     *
     * @param apiVersion 版本
     * @param itemIdx    商品idx
     * @return 图片实体列表
     * @throws MsOnionException 自定义异常
     */
    @Override
    public List<CollectorItemAttributeImage> getItemAttributeImage(MsOnionApiVersion apiVersion, Long itemIdx,
                                                                   Long attributeIdx) throws MsOnionException {
        CollectorItemAttributeImageExample example = new CollectorItemAttributeImageExample();
        CollectorItemAttributeImageExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdxEqualTo(itemIdx);
        criteria.andAttributeIdxEqualTo(attributeIdx);
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());

        return this.queryByExample(apiVersion, example);
    }

    /**
     * 删除商品关键属性图片
     *
     * @param apiVersion 版本
     * @param idx        主键idx
     * @return 处理结果
     */
    @Override
    public MsOnionResult deleteItemAttributeImage(MsOnionApiVersion apiVersion, Long idx) throws MsOnionException {
        if (idx == null || idx < 1) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        int result = this.deleteByPrimaryKey(apiVersion, idx);
        if (result > 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 清除该商品已存在的关键属性图片
     *
     * @param apiVersion 版本
     * @param itemIdx    商品idx
     * @throws MsOnionException 自定义异常
     */
    private void clearAttributeImageByItemIdx(MsOnionApiVersion apiVersion, Long itemIdx) throws MsOnionException {
        if (itemIdx != null && itemIdx > 0) {
            CollectorItemAttributeImageExample example = new CollectorItemAttributeImageExample();
            CollectorItemAttributeImageExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
            criteria.andItemIdxEqualTo(itemIdx);
            List<CollectorItemAttributeImage> images = this.queryByExample(apiVersion, example);
            if (images != null && images.size() > 0) {
                List<Long> attributeImageIdxes = new ArrayList<>();
                for (CollectorItemAttributeImage image : images) {
                    attributeImageIdxes.add(image.getIdx());
                }
                this.deleteByIdxes(apiVersion, attributeImageIdxes);
            }
        }
    }

    /**
     * 初始化关键属性图片
     *
     * @param attributeImage 关键属性图片信息
     * @return 处理结果
     * @throws MsOnionException 自定义异常
     */
    private MsOnionResult initItemAttributeImage(CollectorItemAttributeImage attributeImage) throws MsOnionException {
        if (attributeImage == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_PARAMETER_TYPE_ILLEGAL);
        }

        String msg = MsOnionPojoValidatorUtils.validatePojo(attributeImage);
        if (StringUtils.isNotBlank(msg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, msg);
        }

        Long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_IMAGE_IDX_PATH);

        attributeImage.setIdx(idx);

        Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_COLLECTOR_ITEM_ATTRIBUTE_IMAGE_VERSION_IDX_PATH);

        attributeImage.setVersion(version);
        attributeImage.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

        return MsOnionResult.build(MsOnionStatusConstants.STATUS_200,
                MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS, attributeImage);
    }
}
