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
/**
 * @Title: ContentService.java
 * @Package: cc.msonion.carambola.content.service
 * @Description: 内容Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月16日 下午13:50:21
 * @Modify-version: 2.0.0
 * @Modify-description: 新增Service
 */
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.content.common.constants.ContentConstants;
import cc.msonion.carambola.content.common.constants.PropertiesConstants;
import cc.msonion.carambola.content.ext.ContentBeanUtils;
import cc.msonion.carambola.content.pojo.Content;
import cc.msonion.carambola.content.pojo.ContentExample;
import cc.msonion.carambola.content.pojo.ContentImage;
import cc.msonion.carambola.content.pojo.view.ContentImageView;
import cc.msonion.carambola.content.pojo.view.ContentView;
import cc.msonion.carambola.content.service.ContentImageService;
import cc.msonion.carambola.content.service.ContentService;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.common.MsOnionPojoStringFieldUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ContentService
 * @Description: 内容Service实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月16日 下午13:50:21
 */
@Service
public class ContentServiceImpl  extends MsOnionServiceExt<Content, ContentExample> implements ContentService {

    /**
     * 内容图片服务
     */
    @Autowired
    private ContentImageService contentImageService;

    /**
     * 查询内容数据分页
     *
     * @param apiVersion 版本号
     * @param pageNum    当前页
     * @param pageSize   页大小
     * @param orderField 排序字段
     * @param orderType  排序方式
     * @param name       名称模糊查询
     * @param type       类型查询
     * @param isDelete  是否已删除 0查删除的 1或者NULL查未删除的
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     * @Title selectContentPage
     * @Description 查询内容数据分页
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 15:36:39
     */
    @Override
    public MsOnionResult selectContentPage(MsOnionApiVersion apiVersion, Integer pageNum, Integer pageSize,
                                           String orderField, String orderType, String name, Short type, Short isDelete)
                                            throws MsOnionIllegalArgumentException, MsOnionException {

        try {
            // 定义变量
            ContentExample contentExample = new ContentExample();
            ContentExample.Criteria criteria = contentExample.createCriteria();

            // 参数赋值
            if (MsOnionStringUtils.isNotBlank(name)) {
                criteria.andNameLike(MsOnionConstants.PERCENT + name.trim() + MsOnionConstants.PERCENT);
            }

            if (type != null && type > 0) {
                criteria.andTypeEqualTo(type.shortValue());
            }

            if (isDelete == null || isDelete == 1) {
                criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
            } else {
                criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_MIN_VALUE);
            }

            // 排序
            String orderBy = ContentConstants.ORDER_BY_DEFAULT;
            if (StringUtils.isNotBlank(orderField)
                    && StringUtils.isNotBlank(orderType)) {
                orderBy = orderField.trim() + " " + orderType.trim();
            }

            // 分页查询
            MsOnionResultAdapter msOnionResultAdapter = queryListByPageForResult(apiVersion, contentExample,
                                                        pageNum, pageSize, orderBy);
            // 组装数据
            MsOnionPagingResult msOnionPagingResult = (MsOnionPagingResult) msOnionResultAdapter;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("total", msOnionPagingResult.getTotalCounts());
            map.put("rows", msOnionPagingResult.getData());

            return MsOnionResult.ok(map);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

    }

    /**
     * 获取内容对象的详情数据
     *
     * @param apiVersion 版本号
     * @param idx        内容对象ID
     * @return 对象的详情数据
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                未知异常
     * @Title getContentDetail
     * @Description 获取内容对象的详情数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 15:36:39
     */
    @Override
    public MsOnionResult getContentDetail(MsOnionApiVersion apiVersion, Long idx)
                                          throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 参数校验
            if (idx == null || idx <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }

            // 查询对象
            Content content = this.queryByPrimaryKey(apiVersion, idx);
            if (content == null) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }

            // 值拷贝
            ContentView contentView = new ContentView();
            BeanUtils.copyProperties(content, contentView);

            // 查询图片集合
            if ((content != null)
                    && (content.getType() == ContentConstants.CONTENT_TYPE_IMAGE.shortValue())) {

                MsOnionResult imageListMsOnionResult = contentImageService.queryContentImageList(apiVersion, idx);
                if (imageListMsOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                    return imageListMsOnionResult;
                }
                List<ContentImage> imageList = (List<ContentImage>) imageListMsOnionResult.getData();
                List<ContentImageView> imageVoList = ContentBeanUtils.copyImageToVO(imageList);
                contentView.setContentImageList(imageVoList);
                return MsOnionResult.ok(contentView);
            }
            return MsOnionResult.ok(contentView);
        } catch (Exception e) {
           throw  new MsOnionException(e);
        }
    }

    /**
     * 保存内容对象数据
     * 新增/更新内容对象
     *
     * @param apiVersion   api版本
     * @param contentView      内容对象数据
     * @param  operateIdx 操作者ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title saveContentDetail
     * @Description 保存内容对象数据
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 15:36:39
     */
    @Override
    public MsOnionResult saveContentDetail(MsOnionApiVersion apiVersion, ContentView contentView, Long operateIdx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 参数校验
        if (contentView == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.ADD_CONTENT_PARAM_ERROR);
        }
        if (contentView.getType() == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.ADD_CONTENT_PARAM_ERROR);
        }
        if ((contentView.getType() == ContentConstants.CONTENT_TYPE_IMAGE.shortValue())
                && MsOnionCollectionUtils.isEmpty(contentView.getContentImageList())) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.ADD_CONTENT_PARAM_ERROR);
        }
        Content content = new Content();
        BeanUtils.copyProperties(contentView, content);
        // 去除字符串参数中的空格
        MsOnionPojoStringFieldUtils.inspectPojoStringFieldValue(content);
        String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(content);
        if (MsOnionStringUtils.isNotBlank(valiateMsg)) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
        }

        if (content.getIdx() != null && content.getIdx() > 0) {
            return updateContent(apiVersion, content, operateIdx, contentView.getContentImageList());
        } else {
            return saveContent(apiVersion, content, operateIdx, contentView.getContentImageList());
        }

    }

  /**
   * 更新Content
   * @Title deleteCacheByKeys
   * @Description 描述信息
   * @param apiVersion api版本
   * @param content content对象
   * @param  operateIdx 操作者ID
   * @param contentImageList content图片集合对象
   * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
   * @throws MsOnionException                the ms onion exception
   * @return the ms onion result
   * @Author DorenWu DorenWu@msyc.cc
   * @Date 2017年05月18日 14:22:51
   */
    private MsOnionResult updateContent(MsOnionApiVersion apiVersion, Content content, Long operateIdx,
               List<ContentImageView> contentImageList) throws MsOnionIllegalArgumentException, MsOnionException {
        // 更新
        // 生成version
        long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_IDX_PATH);
        // 设置默认值
        content.setVersion(version);
        content.setUpdateByMemberIdx(operateIdx);
        content.setUpdateTime(new Date());

        // 查询出来旧content,更新content
        Content oldContent = this.queryByPrimaryKey(apiVersion, content.getIdx());
        String[] excludeArr = new String[]{PropertiesConstants.CODE, PropertiesConstants.IDX,
                PropertiesConstants.CREATE_BY_MEMBER_IDX, PropertiesConstants.CREATE_TIME, PropertiesConstants.STATUS};
        BeanUtils.copyProperties(content, oldContent, excludeArr);
        int updateResult = this.updateByPrimaryKey(apiVersion, oldContent);
        if (updateResult <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.UPDATE_CONTENT_ERROR);
        }

        // 如果 是图片，设置旧数据为失效，新增新数据
        if (content.getType() == ContentConstants.CONTENT_TYPE_IMAGE.shortValue()) {
            MsOnionResult updateContentImageResult = contentImageService.
                    updateContentImageList(apiVersion, content.getIdx(), operateIdx, contentImageList);
            if (updateContentImageResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return updateContentImageResult;
            }
        }
        return MsOnionResult.ok();
    }
    /**
     * 保存Content
     * @Title saveContent
     * @Description 描述信息
     * @param apiVersion api版本
     * @param  content content对象
     * @param  operateIdx 操作者ID
     * @param  contentImageList 图片集合
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @return the ms onion result
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月18日 14:17:04
     */
    private MsOnionResult saveContent(MsOnionApiVersion apiVersion, Content content, Long operateIdx,
                                      List<ContentImageView> contentImageList)
                                        throws MsOnionIllegalArgumentException, MsOnionException {


        // 新增
        // 查询标识码是否存在
        MsOnionResult selectCodeResult = selectCodeIsUseable(apiVersion, content.getCode());
        if (selectCodeResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return selectCodeResult;
        }
        // 设置默认值
        content.setCreateByMemberIdx(operateIdx);
        content.setUpdateByMemberIdx(operateIdx);
        content.setStatus(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);

        // 生成idx,version
        long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_IDX_PATH);
        content.setIdx(idx);
        long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(),
                this.getMsOnionCuratorZookeeperClient(), this.getRetryPolicy(),
                MsOnionZookeeperConstants.ZK_COUNTER_CONTENT_VERSION_IDX_PATH);
        content.setVersion(version);

        // 保存 content
        int saveResult = save(apiVersion, content);
        if (saveResult <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.ADD_CONTENT_ERROR);
        }
        // 保存 image
        if (content.getType() == ContentConstants.CONTENT_TYPE_IMAGE.shortValue()) {
            MsOnionResult saveContentImageResult = contentImageService.
                    saveContentImageList(apiVersion, idx, operateIdx, contentImageList);
            if (saveContentImageResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                return saveContentImageResult;
            }
        }
        return MsOnionResult.ok();
    }


    /**
     * 查询标识码是否已经存在
     *
     * @param apiVersion api版本
     * @param code       标识码
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 查询标识码是否已经存在
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月18日 11:05:29
     */
    @Override
    public MsOnionResult selectCodeIsUseable(MsOnionApiVersion apiVersion, String code)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (MsOnionStringUtils.isBlank(code)) {
                return new MsOnionResult(MsOnionStatusConstants.STATUS_400, ContentConstants.CODE_REPETITIVE, code);
            }
            // 去掉左右空格
            code = code.trim();

            // 设置ContentExample对象
            ContentExample example = new ContentExample();
            ContentExample.Criteria criteria = example.createCriteria();
            criteria.andCodeEqualTo(code);
            criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
            // 查询
            List<Content> list = this.queryByExample(apiVersion, example);
            if (MsOnionCollectionUtils.isEmpty(list)) {
                return MsOnionResult.ok();
            }

            return new MsOnionResult(MsOnionStatusConstants.STATUS_400, ContentConstants.CODE_REPETITIVE, code);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 删除内容对象
     *
     * @param apiVersion api版本
     * @param operateIdx 操作者ID
     * @param idx        内容对象ID
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteContentByIdx
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月17日 14:55:24
     */
    @Override
    public MsOnionResult deleteContentByIdx(MsOnionApiVersion apiVersion, Long operateIdx, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 检查参数
            if (idx == null || idx <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.DEL_CONTENT_PARAM_ERROR);
            }
            if (operateIdx == null || operateIdx <= 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.DEL_CONTENT_PARAM_ERROR);
            }
            Content oldContent = this.queryByPrimaryKey(apiVersion, idx);
            oldContent.setUpdateTime(new Date());
            oldContent.setUpdateByMemberIdx(operateIdx);
            oldContent.setStatus(MsOnionStatusConstants.SQL_STATUS_MIN_VALUE);

            int delResult = this.updateByPrimaryKey(apiVersion, oldContent);
            if (delResult > 0) {
                return MsOnionResult.ok();
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.DEL_CONTENT_ERROR);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }


    /**
     * 根据编码查询内容
     *
     * @param apiVersion api版本
     * @param code       指定需要删除的缓存key对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getContentByCode
     * @Description 描述信息
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年05月22日 14:19:35
     */
    @Override
    public MsOnionResult getContentByCode(MsOnionApiVersion apiVersion, String code)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 参数校验
            if (MsOnionStringUtils.isEmpty(code)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }

            // 查询对象
            ContentExample contentExample = new ContentExample();
            ContentExample.Criteria criteria = contentExample.createCriteria();
            criteria.andCodeEqualTo(code);
            criteria.andStatusEqualTo(MsOnionStatusConstants.SQL_STATUS_ONE_VALUE);
            Date curDate = new Date();
            criteria.andStartTimeLessThan(curDate);
            criteria.andEndTimeGreaterThan(curDate);
            // 按修改时间倒序
            List<Content> contentList = this.queryListByPage(apiVersion, contentExample,
                    MsOnionPagingConstants.MS_ONION_PAGING_PAGENUM_MIN_VALUE,
                    MsOnionPagingConstants.MS_ONION_PAGING_PAGESIZE_MAX_VALUE,
                    ContentConstants.ORDER_BY_DEFAULT);

            if (MsOnionCollectionUtils.isEmpty(contentList)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, ContentConstants.PARAM_ERROR);
            }

            return MsOnionResult.ok(contentList.get(0));
        } catch (Exception e) {
            throw  new MsOnionException(e);
        }
    }
}
