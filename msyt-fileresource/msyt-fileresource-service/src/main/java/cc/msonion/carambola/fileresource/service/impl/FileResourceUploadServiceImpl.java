
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



package cc.msonion.carambola.fileresource.service.impl;

/**
 * @Title: FileResourceUploadServiceImpl.java
 * @Package: cc.msonion.carambola.fileresource.service.impl
 * @Description: 文件资源上传 实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月10日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月10日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.parent.ext.constants.MsOnionZookeeperConstants;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionDistributedAtomicLongUtils;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.fileresource.common.viewobject.AlbumViewObject;
import cc.msonion.carambola.fileresource.interfaces.FileResourceUploadService;
import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import cc.msonion.carambola.fileresource.pojo.FileResourceUploadExample;
import cc.msonion.carambola.fileresource.service.ext.AblumTask;
import cc.msonion.carambola.fileresource.service.redis.key.impl.FileResourceUploadRedisKeyGeneratorImpl;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.ext.service.MsOnionServiceExt;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.service.validator.MsOnionPojoValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @ClassName: FileResourceUploadServiceImpl
 * @Description: 文件资源上传 实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月10日
 *
 */
@Service
public class FileResourceUploadServiceImpl extends MsOnionServiceExt<FileResourceUpload, FileResourceUploadExample>
        implements FileResourceUploadService {


    private static final long serialVersionUID = 6108192230481237707L;

    /**
     * 文件资源中心 上传 模块Redis的Key生成器实现
     */
    @Autowired
    private FileResourceUploadRedisKeyGeneratorImpl fileResourceUploadRedisKeyGeneratorImpl;

    /**
     * 保存
     *
     * @param apiVersion         Api版本
     * @param fileResourceUpload 对象
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult saveFileResourceDownload(MsOnionApiVersion apiVersion, FileResourceUpload fileResourceUpload)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            if (null == fileResourceUpload) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, FileResourceConstants.POJO_NOT_NULL);
            }

            final String valiateMsg = MsOnionPojoValidatorUtils.validatePojo(fileResourceUpload);
            if (StringUtils.isNotEmpty(valiateMsg)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, valiateMsg);
            }


            fileResourceUpload.setStatus(MsOnionTableRecordStatus.NORMAL.getValue());

            // 生成Idx
            long idx = MsOnionDistributedAtomicLongUtils.getIdx(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_FILERESOURCE_UPLOAD_IDX_PATH);
            fileResourceUpload.setIdx(idx);


            Long version = MsOnionDistributedAtomicLongUtils.getVersion(this.getMsOnionDomain(), this.getMsOnionCuratorZookeeperClient(),
                    this.getRetryPolicy(), MsOnionZookeeperConstants.ZK_COUNTER_FILERESOURCE_UPLOAD_VERSION_IDX_PATH);
            fileResourceUpload.setVersion(version);

            int result = this.save(apiVersion, fileResourceUpload);
            if (result > 0) {
                this.getMsOnionLogger().info(this.getClass().getName(), "## 文件资源上传记录新增成功, idx = " + idx);
                return MsOnionResult.ok(fileResourceUpload);
            }
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, FileResourceConstants.FAIL);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }


    }

    /**
     * 通过消息id获取唯一文件资源
     *
     * @param apiVersion Api版本
     * @param messageId  消息id
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public FileResourceUpload getFileMessageId(MsOnionApiVersion apiVersion, String messageId)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(messageId) && !MsOnionRegexUtils.checkDigit(messageId)) {
            return null;
        }

        FileResourceUploadExample example = new FileResourceUploadExample();
        FileResourceUploadExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andMessageIdEqualTo(Long.valueOf(messageId));
        List<FileResourceUpload> fileResourceUploads = queryByExample(apiVersion, example);
        if (MsOnionCollectionUtils.isEmpty(fileResourceUploads)) {
            return  null;
        }

        return fileResourceUploads.get(0);
    }

    /**
     * 将大文件分片上传信息 存入redis中
     *
     * @param key    key
     * @param value  值
     * @param expire 生效时间
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public void setFileUploadCache(String key, String value, int expire) throws MsOnionException {
        String redisKey = fileResourceUploadRedisKeyGeneratorImpl.getFileUploadKey(key);
        this.getMsOnionLogger().info(getClass().getName(), "## 文件分片上传redis key:" + redisKey);
        setForRedis(redisKey,  MsOnionSecurityUtils.encodeForRedis(value), expire);
    }

    /**
     * 获取redis中大文件分片上传信息
     *
     * @param key key
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public String getFileUploadCache(String key) throws MsOnionException {
        String redisKey = fileResourceUploadRedisKeyGeneratorImpl.getFileUploadKey(key);
        return MsOnionSecurityUtils.decodeForRedis(getFromRedis(redisKey));
    }


    /**
     * 通过相册类型获取 相册图片集合
     *
     * @param apiVersion Api版本
     * @param ablumType  相册类型
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getFileListAblumType(MsOnionApiVersion apiVersion,
                                              Short ablumType) throws MsOnionIllegalArgumentException, MsOnionException {
        if (ablumType <= 0) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "### 相册类型为空");
        }

        FileResourceUploadExample example = new FileResourceUploadExample();
        FileResourceUploadExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
        criteria.andAlbumTypeEqualTo(ablumType);
        List<FileResourceUpload> fileResourceUploads = queryByExample(apiVersion, example);


        return MsOnionResult.ok(fileResourceUploads);
    }

    /**
     * 通过相册类型获取 相册图片集合,并组成相册视图对象
     *
     * @param apiVersion Api版本
     * @param ablumType  相册类型
     * @return MsOnionResult 实例
     * @throws MsOnionIllegalArgumentException 异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getAblumViewObjectByType(MsOnionApiVersion apiVersion,
                                                  Short ablumType, String uploadRootDir) throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionResult result = getFileListAblumType(apiVersion, ablumType);
        if (result.getStatus() != MsOnionStatusConstants.STATUS_200) {
            return result;
        }

        Object data = result.getData();
        if (data == null) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, "### 相册为空");
        }
        List<FileResourceUpload> list = (List<FileResourceUpload>) data;
        AblumTask task = new AblumTask(list, uploadRootDir);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.execute(task);
        pool.shutdown();
        ArrayList<AlbumViewObject> dataList = null;
        try {
            dataList = task.get();
        } catch (InterruptedException | ExecutionException e) {
           throw new MsOnionException(e);
        }
        return MsOnionResult.ok(dataList);
    }



}
