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

package cc.msonion.carambola.parent.ext.service;

import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionPagingConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.interfaces.service.MsOnionService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 分布式底层架批量操作公共Service
 *
 * @Title: MsOnionService.java
 * @Package: cc.msonion.carambola.parent.interfaces.service
 * @Description: 对Service封装
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月17日 下午5:11:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月17日 下午5:11:38
 * @Modify-version: V2.0.0
 * @Modify-description: 修改：日志，使用MsOnionLog，throws MsOnionException
 */

/**
 * 分布式底层架批量操作公共Service
 *
 * @param <T> 目标POJO
 * @param <E> 目标POJO的Example
 * @ClassName: MsOnionBatchService
 * @Description: MsOnionBatchService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月17日 下午5:11:38
 */
public abstract class MsOnionBatchService<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> extends MsOnionService<T, E> {

    /**
     * 默认批量新增（插入）结果：1
     */
    private static final int DEFAULT_BATCH_SAVE_RESULT = 1;

    /**
     * MsOnionBaseMapper
     */
    @Autowired
    private MsOnionBaseMapper<T, E> baseMapper;  // 不可以把 private 修改成 protected，不可以直接调用

    /**
     * msOnionBatchService
     */
    private MsOnionBatchService msOnionBatchService = this;

    //////////////////////////////////// 提供查询，一个记录的方法 ### Begin ## ///////////////

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public T queryOne(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOne(apiVersion, example, isDisabledRedisQueryCache());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOneForResult(apiVersion, null, example, isDisabledRedisQueryCache());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public T queryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOne(apiVersion, null, example, isDisabledRedisQueryCache());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOneForResult(apiVersion, parameter, example, isDisabledRedisQueryCache());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public T queryOne(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOne(apiVersion, null, example, isDisabledRedisQueryCache);
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOneForResult(apiVersion, null, example, isDisabledRedisQueryCache, getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public T queryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOne(apiVersion, parameter, example, isDisabledRedisQueryCache, getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                                           boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOneForResult(apiVersion, null, example, isDisabledRedisQueryCache, getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public T queryOne(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOne(apiVersion, null, example, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, E example,
                                           boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return queryOneForResult(apiVersion, null, example, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public T queryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                      boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {

        // 查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上
        return queryOneByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, redisCacheExpire);
    }

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    @Override
    public MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example,
                                           boolean isDisabledRedisQueryCache, int redisCacheExpire)
            throws MsOnionIllegalArgumentException, MsOnionException {
        T t = queryOne(apiVersion, parameter, example, isDisabledRedisQueryCache, redisCacheExpire);

        return resultForQueryOne(apiVersion, parameter, t);
    }

    /**
     * resultForQueryOne
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param pojo       目标POJO实例对象
     * @return 返回MsOnionResult
     */
    private MsOnionResult resultForQueryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T pojo) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_QUERY_FAILURE;
        if (null != pojo) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_QUERY_SUCCESS;
        }
        return MsOnionResult.build(status, msg, pojo);
    }


    //////////////////////////////////// 提供查询，一个记录的方法 ### End ## ///////////////


    //////////////////////////////////// 批量修改、更新 ### Begin  ## ///////////////

    /**
     * 批量更新、修改目标POJO的记录
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionBatchReport updateWithBatch(MsOnionApiVersion apiVersion, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return updateWithBatch(apiVersion, null, map);
    }

    /**
     * 批量更新、修改目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionResultAdapter updateWithBatchForResult(MsOnionApiVersion apiVersion, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return updateWithBatchForResult(apiVersion, null, map);
    }

    /**
     * 批量更新、修改目标POJO的记录，返回结果：MsOnionResult
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionResultAdapter updateWithBatchForResult(MsOnionApiVersion apiVersion,
                                                         MsOnionParameterAdapter parameter, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {

        MsOnionBatchReport batchReport = updateWithBatch(apiVersion, parameter, map);
        if (MsOnionMapUtils.isEmpty(batchReport.getSuccessRecords())) {
            // 所有批量都失败，也就是没有成功的
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_BATCH_UPDATE_FAILURE, batchReport);
        }
        return MsOnionResult.ok(batchReport);
    }

    /**
     * 批量保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 返回批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionBatchReport updateWithBatch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion);
        inspectParameter(map);

        // 创建 MsOnionBatchReport
        MsOnionBatchReport batchReport = new MsOnionBatchReport();
        batchReport.setTotalCount(map.size());
        batchReport.setSourceRecords(map);
        // 失败和成功记录
        Map<Integer, T> successRecords = new HashedMap();
        Map<Integer, T> failureRecords = new HashedMap();
        Map<Integer, Exception> exceptions = new HashedMap();
        batchReport.setSuccessRecords(successRecords);
        batchReport.setFailureRecords(failureRecords);
        batchReport.setExceptions(exceptions);

        // 当前操作第一记录
        T firstRecord = null;

        // 默认是所有都操作成功
        batchReport.setAllSuccess(true);
        // 遍历
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            Integer key = entry.getKey();
            T value = entry.getValue();
            if (null == firstRecord) {
                firstRecord = value;
            }
            try {
                // 修改、更新，不需要缓存
                updateWithBatchForOne(apiVersion, parameter, value);
                // 保存修改、更新成功记录
                successRecords.put(key, value);
            } catch (Exception ex) {
                // 异常
                exceptions.put(key, ex);
                // 保存修改、更新失败记录
                failureRecords.put(key, value);
                // 不是所有都操作成功
                batchReport.setAllSuccess(false);
                // 收集错误日志
                getMsOnionLogger().error(getClass().getName(), ex);
            }
        }
        // 批量操作之后，同步缓存
        addAndSyncRedisCacheForBatch(apiVersion, parameter, firstRecord);
        // 返回
        return batchReport;
    }

    /**
     * 批量更新、修改，一条记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO实例对象
     * @return 返回操作结果，1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                异常
     */
    private int updateWithBatchForOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                      T record) throws MsOnionIllegalArgumentException, MsOnionException {

        // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
        inspectPojoFieldValue(apiVersion, parameter, record);

        // 打印日志
        this.getMsOnionLogger().debug(getClass().getName(),
                String.format("updateWithBatchForOne # 批量更新一条记录，前 # record=%s", record));

        int result = this.baseMapper.updateByPrimaryKey(record);

        // 打印日志
        this.getMsOnionLogger().debug(getClass().getName(),
                String.format("updateWithBatchForOne # 批量更新一条记录，后# result=%s,record=%s", result, record));

        // TODO: MQ ！！！

        return result;
    }

    //////////////////////////////////// 批量修改、更新 ### End  ## ///////////////

    //////////////////////////////////// 批量新增、插入 ### Begin  ## ///////////////

    /**
     * 批量保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionBatchReport saveWithBatch(MsOnionApiVersion apiVersion, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return saveWithBatch(apiVersion, null, map);
    }

    /**
     * 批量保存（新增）目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionResultAdapter saveWithBatchForResult(MsOnionApiVersion apiVersion, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return saveWithBatchForResult(apiVersion, null, map);
    }

//    /**
//     * 批量保存（新增）目标POJO的记录
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param map       源数据记录集合，也就是需要批量操作的原始数据集合
//     *            <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
//     * @return 返回批量成功的记录条数
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//    @Override
//    public MsOnionBatchReport saveWithBatch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParameters(apiVersion);
//        inspectParameter(map);
//        // 创建 MsOnionBatchReport
//        MsOnionBatchReport batchReport = new MsOnionBatchReport();
//        batchReport.setTotalCount(map.size());
//        batchReport.setSourceRecords(map);
//        // 失败和成功记录
//        Map<Integer, T> successRecords = new HashedMap();
//        Map<Integer, T> failureRecords = new HashedMap();
//        batchReport.setSuccessRecords(successRecords);
//        batchReport.setFailureRecords(failureRecords);
//        // 当前操作失败记录
//        T currentRecord = null;
//        try {
//            // 由于批量操作，只会在后台管理系统操作，因此还是采取遍历的方式，可以知道的哪一些操作失败
//            for (T pojo : list) {
//                // 当前pojo
//                currentRecord = pojo;
//                // 插入，不需要缓存
//                saveWithBatchForOne(apiVersion, parameter, pojo);
//                // 保存插入成功记录
//                successRecords.add(pojo);
//            }
//            // 全部批量操作完成之后，才统一更新缓存
//            batchReport.setAllSuccess(true);
//            // 设置成功操作集合
//            batchReport.setSuccessRecords(list);
//            // 批量操作成功，同步缓存
//            addAndSyncRedisCacheForBatch(apiVersion, parameter, successRecords.get);
//            // 返回
//            return batchReport;
//        } catch (Exception ex) {
//            // 注意，批量插入的过程中，一旦有一条记录操作失败，后续的其他记录都不会执行
//            batchReport.setException(ex);
//            // 不是所有都操作成功
//            batchReport.setAllSuccess(false);
//            // 当前失败记录
//            batchReport.setCurrentFailureRecord(currentRecord);
//            // 操作失败集合
//            List<T> tempList = new ArrayList<T>(list);
//            // 所有记录集合中，移除成功记录集合，就是失败记录集合
//            tempList.removeAll(successRecords);
//            // 失败记录集合
//            failureRecords.addAll(tempList);
//            // 收集错误日志
//            getMsOnionLogger().error(getClass().getName(), ex);
//            // 同步已经操作成功记录到缓存中
//            addAndSyncRedisCacheForBatch(apiVersion, parameter, successRecords);
//        }
//        // 返回
//        return batchReport;
//    }

    /**
     * 批量保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 返回批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionBatchReport saveWithBatch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion);
        inspectParameter(map);
        // 创建 MsOnionBatchReport
        MsOnionBatchReport batchReport = new MsOnionBatchReport();
        batchReport.setTotalCount(map.size());
        batchReport.setSourceRecords(map);
        // 失败和成功记录
        Map<Integer, T> successRecords = new HashedMap();
        Map<Integer, T> failureRecords = new HashedMap();
        Map<Integer, Exception> exceptions = new HashedMap();
        batchReport.setSuccessRecords(successRecords);
        batchReport.setFailureRecords(failureRecords);
        batchReport.setExceptions(exceptions);

        // 当前操作第一记录
        T firstRecord = null;

        // 默认是所有都操作成功
        batchReport.setAllSuccess(true);
        // 遍历
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            Integer key = entry.getKey();
            T value = entry.getValue();
            if (null == firstRecord) {
                firstRecord = value;
            }
            try {
                // 插入，不需要缓存
                saveWithBatchForOne(apiVersion, parameter, value);
                // 保存插入成功记录
                successRecords.put(key, value);
            } catch (Exception ex) {
                // 异常
                exceptions.put(key, ex);
                failureRecords.put(key, value);
                // 不是所有都操作成功
                batchReport.setAllSuccess(false);
                // 收集错误日志
                getMsOnionLogger().error(getClass().getName(), ex);
            }
        }

        // 批量操作之后，同步缓存
        addAndSyncRedisCacheForBatch(apiVersion, parameter, firstRecord);
        // 返回
        return batchReport;
    }

    /**
     * 批量保存（新增）目标POJO的记录，返回结果：MsOnionResult
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    @Override
    public MsOnionResultAdapter saveWithBatchForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException {
        MsOnionBatchReport batchReport = saveWithBatch(apiVersion, parameter, map);
        if (MsOnionMapUtils.isEmpty(batchReport.getSuccessRecords())) {
            // 所有批量都失败，也就是没有成功的
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_400,
                    MsOnionMessageConstants.MESSAGE_BATCH_SAVE_FAILURE, batchReport);
        }
        return MsOnionResult.ok(batchReport);
    }

    /**
     * 批量新增（插入），新增一条记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO实例对象
     * @return 返回操作结果，1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                异常
     */
    private int saveWithBatchForOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                    T record) throws MsOnionIllegalArgumentException, MsOnionException {

//        this.getMsOnionLogger().debug(getClass().getName(), "saveWithBatchForOne , 批量新增，检查和SQL注入过滤之前，record=" + record);

        // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
        inspectPojoFieldValue(apiVersion, parameter, record);

        try {
            // 设置idxCode
            Field idxCodeField = record.getClass().getDeclaredField("idxCode");
            // 允许访问
            idxCodeField.setAccessible(true);
            // 动态生成idxCode
            long idxCode = MsOnionZookeeperUtils.getIdxCode(getMsOnionLogger(), getMsOnionDomain(), getMsOnionCuratorZookeeperClient(), getRetryPolicy());
            if (idxCode <= 0) {
                // 再次动态生成idxCode
                idxCode = MsOnionZookeeperUtils.getIdxCode(getMsOnionLogger(), getMsOnionDomain(), getMsOnionCuratorZookeeperClient(), getRetryPolicy());
            }
            idxCodeField.set(record, idxCode);
            // 打印日志
            getMsOnionLogger().debug(msOnionBatchService.getClass().getName(), String.format("saveWithBatchForOne#动态生成idxCode=%s,record=%s", idxCode, record));
        } catch (Exception e) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_SET_IDX_CODE_ERROR, e, MsOnionStatusConstants.STATUS_400);
        }

        getMsOnionLogger().debug(getClass().getName(), "saveWithBatchForOne , 批量新增，检查和SQL注入过滤之后，record=" + record);

        // 设置新注册的所有用户默认是2 ？？ 不要最好通过代码来实现，因为有可能
        // 影响都其他模块的默认值，商品新增之后，默认就是1 ，成员新增之后，就是2（未激活）
        int result = this.baseMapper.insert(record);

        // 批量操作，不要遍历操单挑记录过程中，添加到缓存中，否则导致性能有问题！！！
        // 因为每一次添加一条新记录就会同步缓存，应该是最后操作完成之后，再一起批量添加到缓存中
        // 删除列表、分页、条件查询 Redis缓存，必须在添加缓存之前，否则到又删除当前的缓存

        // TODO: MQ ！！！

        return result;
    }

    /**
     * 检查参数
     *
     * @param map 源数据记录集合，也就是需要批量操作的原始数据集合
     *            <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameter(Map<Integer, T> map) throws MsOnionIllegalArgumentException {
        if (MsOnionMapUtils.isEmpty(map)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_LIST_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 添加和同步Redis缓存，批量操作
     *
     * @param apiVersion Api版本
     * @param parameter  参数
     * @param pojo       目标POJO对象
     */
    private void addAndSyncRedisCacheForBatch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T pojo) {

        if (null == pojo) {
            return;
        }
        // 由于每一次添加缓存，都需要清除之前的缓存，因此批量操作，就不添加缓存，清除缓存就可以了。
//        for (T pojo : list) {
//            // 删除列表、分页、条件查询 Redis缓存，必须在添加缓存之前，否则到又删除当前的缓存
//            this.syncPageAndExampleRedisCacheForAdd(apiVersion, parameter, pojo, DEFAULT_BATCH_SAVE_RESULT);
//
//            // 添加Redis缓存
//            this.addRedisCache(apiVersion, parameter, pojo, DEFAULT_BATCH_SAVE_RESULT);
//        }


        // 清除缓存，只需要一个POJO就可以了，因为有POJO才知道，是清除哪一个模块的缓存
        // 删除列表、分页、条件查询 Redis缓存，必须在添加缓存之前，否则到又删除当前的缓存
        this.syncPageAndExampleRedisCacheForAdd(apiVersion, parameter, pojo, DEFAULT_BATCH_SAVE_RESULT);

    }

    //////////////////////////////////// 批量新增、插入 ### End  ## ///////////////

    //////////////////////////////////// 批量随机查询 ### Begin  ## ///////////////

    /////////////////// 批量随机查询 ### Begin ////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion,
                                        E example, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, null, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                        E example, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, parameter, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                        E example, boolean isDisabledRedisQueryCache, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, getRedisCacheExpire(), count);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                        E example, boolean isDisabledRedisQueryCache,
                                        int redisCacheExpire, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, redisCacheExpire, count, null);
    }

    //////////////////  排序 orderBy  ## Begin /////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion,
                                        E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, null, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count, orderBy);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                        E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, parameter, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count, orderBy);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                        E example, boolean isDisabledRedisQueryCache, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, getRedisCacheExpire(), count, orderBy);
    }

//    /**
//     * 根据目标POJO的Example查询，随机查询记录
//     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
//     *
//     * @param apiVersion                api版本
//     * @param parameter                 参数
//     * @param example                   目标POJO的Example实例对象
//     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
//     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
//     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
//     * @param orderBy 排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
//     * @return 返回目标POJO的集合
//     * @throws MsOnionIllegalArgumentException 非法参数
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年7月21日 下午6:30:12
//     */
//    @Override
//    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                        E example, boolean isDisabledRedisQueryCache,
//                                        int redisCacheExpire, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
//        return queryRandomByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, redisCacheExpire, count, null);
//    }


    /////////////////  排序 orderBy  ## End /////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                        E example, boolean isDisabledRedisQueryCache,
                                        int redisCacheExpire, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameters(apiVersion, example);
        // 检查数量，相当于分页 pageSize
        inspectParameterForPageSize(count);
        // 检查缓存周期参数
        redisCacheExpire = inspectParameterForRedisCacheExpire(redisCacheExpire);

//        try {
//            // 必须加上状态条件， 0： 删除， 1： 正常， 2：未激活（用户，成员）
//            // 如果是0：删除的，不可以查询到
//            // 必须在 生成 Redis Key之前调用。
//            invokeAndStatusIn(example, getStatusAvailableValues());
//        } catch (Exception e) {
//            throw new MsOnionException(e);
//        }

        // 优先级：当前如果已经是true，那么全局的就不需要判断了，如果当前的为false，就需要判断全局
        // 当前true > 全局true | false
        if (!isDisabledRedisQueryCache) {  // 如果没有禁用Redis查询缓存
            try {
                // 随机查询的Redis的 Key
                String key = this.getRedisKeyGeneratorAdapter().getKeyForQueryRandom(example,
                        MsOnionPagingConstants.MS_ONION_QUERY_RANDOM_PAGENUM_VALUE, count, orderBy);
                // 打印日志
                this.getMsOnionLogger().debug(getClass().getName(),
                        String.format("queryRandomByExample#随机查询，可以使用缓存 # 根据example得到key=%s",
                                key));
                if (StringUtils.isNotBlank(key)) {
//                    String json = this.msOnionJedisAdapter.get(key);
                    String json = getFromRedis(key);
                    // 打印日志
                    this.getMsOnionLogger().debug(getClass().getName(),
                            String.format("queryRandomByExample#随机查询，可以使用缓存 # 根据example得到key=%s，根据Key查询缓存加密json=%s",
                                    key, json));
                    // 解密
                    json = decodeValue(json);
                    // 打印日志
                    this.getMsOnionLogger().debug(getClass().getName(),
                            String.format("queryRandomByExample#随机查询，可以使用缓存 # 根据example得到key=%s，解密之后的json=%s",
                                    key, json));
                    if (StringUtils.isNotBlank(json)) {
                        Class<T> clazz = getTargetClazz();
                        // 存储都是List，也要按照List来解析
                        List<T> list = MsOnionJsonUtils.jsonToList(json, clazz);
                        if (MsOnionCollectionUtils.isNotEmpty(list)) {
                            // 有多个元素
                            return list;
                        }
                    }
                }
            } catch (Exception e) {
                this.getMsOnionLogger().error(this.getClass().getName(), e);
            }
        }

        List<T> list = null;
        try {
            this.getMsOnionLogger().debug(getClass().getName(),
                    String.format("queryRandomByExample#随机查询，直接查询MySQL#分页查询(1,1)# getTargetName()=%s，",
                            getTargetName()));
            // 使用PageHelper设置分页信息
            // PageHelper 4.x
//            PageHelper.startPage(MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGENUM_VALUE, MsOnionPagingConstants.MS_ONION_QUERY_ONE_PAGESIZE_VALUE);
            // PageHelper 5.x
            PageHelper.startPage(MsOnionPagingConstants.MS_ONION_QUERY_RANDOM_PAGENUM_VALUE,
                    count,
                    MsOnionPagingConstants.MS_ONION_QUERY_RANDOM_PAGING_IS_COUNT);
            // PageHelper 5.x， 排序， OrderByHelper.orderBy("idx desc");
            if (MsOnionStringUtils.isNotBlank(orderBy)) {
                OrderByHelper.orderBy(orderBy.trim());
            }
            // 查询数据库
            list = this.baseMapper.selectByExample(example);
            // 打印日志
            this.getMsOnionLogger().debug(getClass().getName(),
                    String.format("queryRandomByExample#随机查询，直接查询MySQL # getTargetName()=%s，查询列表记录数list=%s",
                            getTargetName(), list));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }

        // 添加到Redis缓存中
        // 不管当前是否禁用缓存，查询结果都是存在到缓存中
        try {
            if (!MsOnionCollectionUtils.isEmpty(list)) {
                String json = MsOnionJsonUtils.objectToJson(list);
                // 随机查询的Redis的 Key
                String key = this.getRedisKeyGeneratorAdapter().getKeyForQueryRandom(example,
                        MsOnionPagingConstants.MS_ONION_QUERY_RANDOM_PAGENUM_VALUE, count, orderBy);
                // 打印日志
                this.getMsOnionLogger().debug(getClass().getName(),
                        String.format("queryRandomByExample#随机查询，直接查询MySQL # 准备添加到Redis缓存中 # 根据Example得到key=%s，查询列表json=%s",
                                key, json));
                if (StringUtils.isNotBlank(json) && StringUtils.isNotBlank(key)) {
                    // tNbbJ , nbbJ , Tnb , tnb
                    // 加密
                    json = encodeValue(json);
                    // 2个方法实现的
//                    this.msOnionJedisAdapter.set(key, json);
//                    // 设置周期
//                    this.msOnionJedisAdapter.expire(key, redisCacheExpire);
                    // 1个方法，添加到Redis缓存中
                    setForRedis(parameter, key, json, redisCacheExpire);
                    // 保存当前Key，为了以后更新缓存使用的
                    this.addKey(key, redisCacheExpire);
                    // 打印日志
                    this.getMsOnionLogger().debug(getClass().getName(),
                            String.format("queryRandomByExample#随机查询，直接查询MySQL # 已添加到Redis缓存中 # 根据Example得到key=%s，加密json=%s，缓存周期redisCacheExpire=%s",
                                    key, json, redisCacheExpire));
                }
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }
        return MsOnionCollectionUtils.isEmpty(list) ? null : list;
    }


    //////////////////  forResult ### Begin ///////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion,
                                                              E example, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExampleForResult(apiVersion, null, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                              E example, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExampleForResult(apiVersion, parameter, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                              E example, boolean isDisabledRedisQueryCache, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExampleForResult(apiVersion, parameter, example, isDisabledRedisQueryCache, getRedisCacheExpire(), count);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                              E example, boolean isDisabledRedisQueryCache,
                                                              int redisCacheExpire, int count) throws MsOnionIllegalArgumentException, MsOnionException {
        List<T> list = queryRandomByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, redisCacheExpire, count);
        return resultForQueryRandom(list);
    }

    //////////////////  排序 orderBy  ## Begin /////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion,
                                                              E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExampleForResult(apiVersion, null, example, count, orderBy);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                              E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        return queryRandomByExampleForResult(apiVersion, parameter, example, this.isDisabledRedisQueryCache(), getRedisCacheExpire(), count, orderBy);
    }

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    @Override
    public MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                              E example, boolean isDisabledRedisQueryCache,
                                                              int redisCacheExpire, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException {
        List<T> list = queryRandomByExample(apiVersion, parameter, example, isDisabledRedisQueryCache, redisCacheExpire, count, orderBy);
        return resultForQueryRandom(list);
    }

    ///////////////  排序 orderBy  ## End /////////////////////////////////////


    /**
     * 随机查询结果
     *
     * @param list 数据集合
     * @return 返回MsOnionResult
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年07月20日 下午6:18:09
     */
    protected MsOnionResultAdapter resultForQueryRandom(List<T> list) {
        int status = MsOnionStatusConstants.STATUS_400;
        String msg = MsOnionMessageConstants.MESSAGE_SAVE_FAILURE;
        if (MsOnionCollectionUtils.isNotEmpty(list)) {
            status = MsOnionStatusConstants.STATUS_200;
            msg = MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS;
        }
        return MsOnionResult.build(status, msg, list);
    }

    //////////////////  forResult ### End ///////////////////////////////

    /////////////////// 批量随机查询  ### End ////////////////////////////////////


    //////////////////////////////////// 批量随机查询 ### End  ## ///////////////

}
