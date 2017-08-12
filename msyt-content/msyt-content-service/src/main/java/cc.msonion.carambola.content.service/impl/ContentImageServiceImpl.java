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
package cc.msonion.carambola.content.service.impl;


import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.content.common.constants.ContentConstants;
import cc.msonion.carambola.content.pojo.ContentImage;
import cc.msonion.carambola.content.pojo.ContentImageExample;
import cc.msonion.carambola.content.pojo.view.ContentImageView;
import cc.msonion.carambola.content.service.ContentImageService;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionPojoStringFieldUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @Title: ContentImageService.java
 * @Package: cc.msonion.carambola.content.service
 * @Description: 内容图片Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月16日 下午13:50:21
 * @Modify-version: 2.0.0
 * @Modify-description: 新增Service
 */

/**
 * @ClassName: ContentImageService
 * @Description: 内容图片Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 */
@Service
public class ContentImageServiceImpl extends MsOnionServiceExt<ContentImage, ContentImageExample>
        implements ContentImageService {


    /**
     * 查询内容的图片数据
     *
     * @param apiVersion api版本
     * @param contentIdx 内容的主键
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title queryContentImageList
     * @Description 查询内容的图片数据
     */
    @Override
    public MsOnionResult queryContentImageList(MsOnionApiVersion apiVersion, Long contentIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            ContentImageExample contentImageExample = new ContentImageExample();
            ContentImageExample.Criteria criteria = contentImageExample.createCriteria();
            criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
            List<ContentImage> imageList = queryListByPage(apiVersion, contentImageExample,
                    MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE,
                    MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE,
                    ContentConstants.IMAGE_ORDER_BY_DEFAULT);
            return MsOnionResult.ok(imageList);
        } catch (MsOnionException e) {
            throw e;
        }
    }

    /**
     * 添加内容图片集合
     *
     * @param apiVersion       api版本
     * @param contentIdx     content实体Id
     * @param contentImageList contentImage集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title saveContentImageList
     * @Description 添加内容图片集合
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月18日 14:06:16
     */
    @Override
    public MsOnionResult saveContentImageList(MsOnionApiVersion apiVersion, Long contentIdx,  Long operateIdx,
           List<ContentImageView> contentImageList)throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 参数校验
            if (contentIdx == null
                    || contentIdx <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }
            if (MsOnionCollectionUtils.isEmpty(contentImageList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }
            // 保存新数据
            return saveList(apiVersion, contentIdx, operateIdx, contentImageList);
        } catch (Exception e) {
           throw new MsOnionException(e);
        }
    }

    /**
     * 更新内容图片集合
     *
     * @param apiVersion       api版本
     * @param contentIdx       content实体的ID
     * @param contentImageList contentImage集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title updateContentImageList
     * @Description 更新内容图片集合
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月18日 14:09:00
     */
    @Override
    public MsOnionResult updateContentImageList(MsOnionApiVersion apiVersion, Long contentIdx, Long operateIdx,
              List<ContentImageView> contentImageList)throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 参数校验
            if (contentIdx == null
                    || contentIdx <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }
            if (MsOnionCollectionUtils.isEmpty(contentImageList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }

            // 查询之前旧数据
            ContentImageExample contentImageExample = new ContentImageExample();
            ContentImageExample.Criteria criteria = contentImageExample.createCriteria();
            criteria.andContentIdxEqualTo(contentIdx);
            List<ContentImage> oldContentImageList = this.queryListByPage(apiVersion, contentImageExample,
                    MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE,
                    MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE);

            // 循环软删除
            for (ContentImage image : oldContentImageList) {
                image.setUpdateByMemberIdx(operateIdx);
                image.setUpdateTime(new Date());
                this.updateByPrimaryKey(apiVersion, image);
            }

            // 保存新数据
            return saveList(apiVersion, contentIdx, operateIdx, contentImageList);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 描述信息
     * @Title deleteCacheByKeys
     * @Description 描述信息
     * @param apiVersion api版本
     * @param contentIdx content idx
     * @param operateIdx 操作者ID
     * @param contentImageVOList contentImageVO集合
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月18日 16:00:20
     */
    private MsOnionResult saveList(MsOnionApiVersion apiVersion, Long contentIdx, Long operateIdx,
                         List<ContentImageView> contentImageVOList)throws MsOnionIllegalArgumentException, MsOnionException {

        for (ContentImageView contentImageView : contentImageVOList) {
            ContentImage contentImage = new ContentImage();
            BeanUtils.copyProperties(contentImageView, contentImage);
            MsOnionPojoStringFieldUtils.inspectPojoStringFieldValue(contentImage);
            String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(contentImage);
            if (MsOnionStringUtils.isNotBlank(valiateMsg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
            }
            // 生成idx,version
            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                    this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                    MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_IMAGE_IDX_PATH);
            contentImage.setIdx(idx);
            long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                    this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                    MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_IMAGE_VERSION_IDX_PATH);
            contentImage.setVersion(version);

            contentImage.setCreateByMemberIdx(operateIdx);
            contentImage.setUpdateByMemberIdx(operateIdx);
            this.save(apiVersion, contentImage);
        }
        return MsOnionResult.ok();
    }
}
