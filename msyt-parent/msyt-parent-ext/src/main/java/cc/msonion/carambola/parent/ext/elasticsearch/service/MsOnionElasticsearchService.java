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

package cc.msonion.carambola.parent.ext.elasticsearch.service;

import cc.msonion.carambola.parent.common.collections.MsOnionMapUtils;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionExecuteResultStatus;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.ext.elasticsearch.action.MsOnionActionListener;
import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionQuerySchemeAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionSearchConditionAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.client.MsOnionTransportClient;
import cc.msonion.carambola.parent.ext.elasticsearch.constants.MsOnionElasticsearchConstants;
import cc.msonion.carambola.parent.ext.elasticsearch.enums.MsOnionFullTextQueryPattern;
import cc.msonion.carambola.parent.ext.elasticsearch.enums.MsOnionTermLevelQueryPattern;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.MsOnionElasticsearchItem;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.MsOnionSearchCondition;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.queryscheme.MsOnionFullTextQueryScheme;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.queryscheme.MsOnionTermLevelQueryScheme;
import cc.msonion.carambola.parent.ext.elasticsearch.service.base.MsOnionElasticsearchBaseService;
import cc.msonion.carambola.parent.ext.elasticsearch.utils.MsOnionElasticsearchUtils;
import cc.msonion.carambola.parent.ext.log.*;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.paging.MsOnionESSearchPagingResult;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionSearch;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch Service
 *
 * @Title: MsOnionElasticsearchService.java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.service.base
 * @Description: Elasticsearch Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月5日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * Elasticsearch Service
 *
 * @param <T> 目标Pojo，例如：Item
 * @ClassName: MsOnionElasticsearchService
 * @Description: Elasticsearch Service
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 */
public abstract class MsOnionElasticsearchService<T extends MsOnionBasePojoAdapter> implements MsOnionElasticsearchBaseService<T> {

    /**
     * 日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * MsOnionService 当前实现类的实例对象，方便于多线程中获取当前this
     */
    private MsOnionElasticsearchService msOnionElasticsearchService = this;

    /**
     * 目标POJO的Class实例对象
     */
    private Class<T> targetClazz;

    /**
     * Elasticsearch 客户端
     */
    @Autowired
    private MsOnionTransportClient msOnionTransportClient;

    /**
     * include lower value means that from is gt when false or gte when true
     */
    private static final boolean ES_EXISTS_INCLUDE_LOWER = true;

    /**
     * include upper value means that to is lt when false or lte when true
     */
    private static final boolean ES_EXISTS_INCLUDE_UPPER = true;

    /**
     * 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     *
     * @return 返回目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     * @throws MsOnionException 异常
     * @Title: getTargetName
     * @Description: 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:42:52
     */
    @Override
    public String getTargetName() throws MsOnionException {
        return getTargetClazz().getName();
    }

    /**
     * 获取Elasticsearch 索引名称，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引名称，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    @Override
    public String getIndexName() throws MsOnionException {
        String index = null;
        try {
            // 通过 Annotation获取 module
            MsOnionSearch searchAnnotation = getTargetClazz().getAnnotation(MsOnionSearch.class);
            // 获取模块，可能searchAnnotation为 null，但是，这里就是专门用于搜索的，因此，直接报空指针异常！！！
//            module = searchAnnotation.module();
            // 获取index名称
            index = searchAnnotation.index();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        // 如果为null 、 空
        if (MsOnionStringUtils.isBlank(index)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_SEARCH_ANNOTATION_MODULE_ILLEGAL);
        }
        // 去掉左右空格
        return index.trim();
    }

    /**
     * 获取Elasticsearch 索引Type，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引Type，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    @Override
    public String getTypeName() throws MsOnionException {
//        return getTargetClazz().getSimpleName();
        String type = null;
        try {
            // 通过 Annotation获取 module
            MsOnionSearch searchAnnotation = getTargetClazz().getAnnotation(MsOnionSearch.class);
            // 获取模块，可能searchAnnotation为 null，但是，这里就是专门用于搜索的，因此，直接报空指针异常！！！
//            module = searchAnnotation.module();
            // 获取index名称
            type = searchAnnotation.type();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        // 如果为null 、 空
        if (MsOnionStringUtils.isBlank(type)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_SEARCH_ANNOTATION_MODULE_ILLEGAL);
        }
        // 去掉左右空格
        return type.trim();
    }

    /**
     * 获取目标POJO的Class
     *
     * @return 返回目标POJO的Class
     * @throws MsOnionException 异常
     * @Title: getTargetClazz
     * @Description: 获取目标POJO的Class
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月8日 下午23:31:10
     */
    @Override
    public Class<T> getTargetClazz() throws MsOnionException {
        if (null != this.targetClazz) {
            return this.targetClazz;
        }
        this.targetClazz = (Class<T>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass());
        return this.targetClazz;
    }


    /////////////////// 添加 ### Begin #####  //////////////////////////////////////

    /**
     * 添加 Elasticsearch 索引
     *
     * @param apiVersion API版本
     * @param source     资源、数据源
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public IndexResponse add(MsOnionApiVersion apiVersion, long idx, String source) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);
        return add(apiVersion, null, idx, source);
    }

    /**
     * 添加 Elasticsearch 索引
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx 主键idx
     * @param source     资源、数据源
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public IndexResponse add(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);
        return MsOnionElasticsearchUtils.add(this.msOnionTransportClient, getIndexName(), getTypeName(), idx, source);
    }

    /**
     * 添加 Elasticsearch 索引
     *
     * @param apiVersion API版本
     * @param source     资源、数据源
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult addForResult(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);
        return addForResult(apiVersion, null, idx, source);
    }

    /**
     * 添加 Elasticsearch 索引
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param source     资源、数据源
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult addForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        IndexResponse indexResponse = add(apiVersion, parameter, idx, source);
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            return MsOnionResult.ok(indexResponse);
        }
        return MsOnionResult.build(MsOnionExecuteResultStatus.FAILURE, MsOnionElasticsearchConstants.ES_INDEX_FAILURE);
    }

    /////////////////// 添加 ### End #####  //////////////////////////////////////

    /////////////////// 删除 ### Begin #####  //////////////////////////////////////

    /**
     * 删除
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public DeleteResponse delete(MsOnionApiVersion apiVersion, long idx) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        return delete(apiVersion, null, idx);
    }

    /**
     * 删除
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public DeleteResponse delete(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        String index = getIndexName();
        String type = getTypeName();
        // 打印日志
        this.msOnionLogger.debug(getClass().getName(), String.format("delete#删除#index=%s，type=%s，idx=%s，apiVersion=%s",
                index, type, idx, apiVersion));
        return MsOnionElasticsearchUtils.delete(this.msOnionTransportClient, index, type, idx);
    }

    /**
     * 删除
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult deleteForResult(MsOnionApiVersion apiVersion, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return deleteForResult(apiVersion, null, idx);
    }

    /**
     * 删除
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult deleteForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        DeleteResponse deleteResponse = delete(apiVersion, parameter, idx);

        if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED) {
            return MsOnionResult.ok(deleteResponse);
        }
        return MsOnionResult.build(MsOnionExecuteResultStatus.FAILURE, MsOnionElasticsearchConstants.ES_DELETE_FAILURE, deleteResponse);
    }

    /**
     * 异步删除
     *
     * @param apiVersion     API版本
     * @param name           名称
     * @param text           文本
     * @param source         资源
     * @param actionListener MsOnionActionListener实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public void asyncDelete(MsOnionApiVersion apiVersion, String name, String text, String source,
                            MsOnionActionListener actionListener)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        asyncDelete(apiVersion, null, name, text, source, actionListener);
    }

    /**
     * 异步删除
     *
     * @param apiVersion     API版本
     * @param parameter      参数
     * @param name           名称
     * @param text           文本
     * @param source         资源
     * @param actionListener MsOnionActionListener实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public void asyncDelete(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, String name, String text,
                            String source, MsOnionActionListener actionListener)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        String index = getIndexName();
        String type = getTypeName();
        // 打印日志
        this.msOnionLogger.debug(getClass().getName(), String.format("asyncDelete#异步删除#index=%s，type=%s，apiVersion=%s，source=%s",
                index, type, apiVersion, source));

        MsOnionElasticsearchUtils.asyncDelete(this.msOnionTransportClient, index,
                type, source, actionListener);
    }

    /////////////////// 删除 ### End  #####  //////////////////////////////////////

    /////////////////// 更新 ### Begin #####  //////////////////////////////////////

//    /**
//     * 更新
//     *
//     * @param idx    主键idx
//     * @param source 资源
//     * @return 返回 UpdateResponse 实例对象
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public UpdateResponse update(long idx, String source) throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParameterForAppVersion(apiVersion);
//        return null;
//    }

    /**
     * 更新
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源
     * @return 返回 UpdateResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public UpdateResponse update(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        return update(apiVersion, null, idx, source);
    }

    /**
     * 更新
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param source     资源
     * @return 返回 UpdateResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public UpdateResponse update(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        String index = getIndexName();
        String type = getTypeName();
        // 打印日志
        this.msOnionLogger.debug(getClass().getName(), String.format("update#更新#index=%s，type=%s，idx=%s，apiVersion=%s，source=%s",
                index, type, idx, apiVersion, source));

        return MsOnionElasticsearchUtils.update(this.msOnionTransportClient, index, type, idx, source);
    }

    /**
     * 更新
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源
     * @return 返回 UpdateResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult updateForResult(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        return updateForResult(apiVersion, null, idx, source);
    }

    /**
     * 更新
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param source     资源
     * @return 返回 UpdateResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult updateForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        UpdateResponse updateResponse = update(apiVersion, parameter, idx, source);

        // TODO: 需要判断
        if (updateResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            return MsOnionResult.build(MsOnionExecuteResultStatus.FAILURE, MsOnionElasticsearchConstants.ES_UPDATE_FAILURE, updateResponse);
        }
        return MsOnionResult.ok(updateResponse);
    }


    /////////////////// 更新 ### End #####  //////////////////////////////////////

    /////////////////// 综合操作 ### Begin #####  //////////////////////////////////////

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public UpdateResponse upsert(MsOnionApiVersion apiVersion, long idx, String source) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        return upsert(apiVersion, null, idx, source);
    }

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param source     资源
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public UpdateResponse upsert(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        return upsert(apiVersion, null, idx, source);
    }

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult upsertForResult(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        return upsertForResult(apiVersion, null, idx, source);
    }

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param source     资源
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult upsertForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx,
                                         String source) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        UpdateResponse updateResponse = upsert(apiVersion, parameter, idx, source);

        // TODO: 需要判断
        if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            return MsOnionResult.build(MsOnionExecuteResultStatus.FAILURE, MsOnionElasticsearchConstants.ES_UPSERT_FAILURE);
        }
        return MsOnionResult.ok(updateResponse);
    }


    /////////////////// 综合操作 ### End #####  //////////////////////////////////////


    /////////////////// get操作 ### Begin #####  //////////////////////////////////////


    /////////////////// get单个值操作 ### Begin #####  //////////////////////////////////////

    /**
     * 获取
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 GetResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public GetResponse get(MsOnionApiVersion apiVersion, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return get(apiVersion, null, idx);
    }

    /**
     * 获取
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回 GetResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public GetResponse get(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);

        String index = getIndexName();
        String type = getTypeName();
        // 打印日志
        this.msOnionLogger.debug(getClass().getName(), String.format("get#获取#index=%s，type=%s，idx=%s，apiVersion=%s", index, type, idx, apiVersion));
        return MsOnionElasticsearchUtils.get(this.msOnionTransportClient, index, type, idx);
    }

    /**
     * 获取
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 GetResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getForResult(MsOnionApiVersion apiVersion, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return getForResult(apiVersion, null, idx);
    }

    /**
     * 获取
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回 GetResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        GetResponse getResponse = get(apiVersion, parameter, idx);
        // TODO: 需要考虑查询失败！！！
        /*
        getResponse=Error building toString out of XContent: com.fasterxml.jackson.core.JsonGenerationException: Can not start an object, expecting field name (context: Object)
	at com.fasterxml.jackson.core.JsonGenerator._reportError(JsonGenerator.java:1897)
         */

        return MsOnionResult.ok(getResponse);
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param items      MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> get(MsOnionApiVersion apiVersion, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param items      MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> get(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param items      MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getForResult(MsOnionApiVersion apiVersion, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param items      MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionResult getForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }


    /////////////////// get单个值操作 ### End #####  //////////////////////////////////////


    /////////////////// get多个操作 ### Begin #####  //////////////////////////////////////

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMulti(MsOnionApiVersion apiVersion, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMulti(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMulti(MsOnionApiVersion apiVersion, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMulti(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMultiForResult(MsOnionApiVersion apiVersion, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMultiForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMultiForResult(MsOnionApiVersion apiVersion, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public List<String> getMultiForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return null;
    }


    /////////////////// get多个操作 ### End #####  //////////////////////////////////////


    /////////////////// get操作 ### End #####  //////////////////////////////////////


    /////////////////// 搜索 ### Begin #####  //////////////////////////////////////

    /**
     * 搜索
     *
     * @param apiVersion api版本
     * @param condition  搜索条件
     * @return 返回搜索结果集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionESSearchPagingResult search(MsOnionApiVersion apiVersion, MsOnionSearchConditionAdapter condition)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return search(apiVersion, null, condition);
    }

    /**
     * 搜索
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param condition  搜索条件
     * @return 返回搜索结果集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public MsOnionESSearchPagingResult search(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                              MsOnionSearchConditionAdapter condition)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParameterForAppVersion(apiVersion);
        inspectParameterForCondition(condition);

        // 获取 index
        String index = getIndexName();
        String type = getTypeName();

        // 打印日志
        this.msOnionLogger.debug(getClass().getName(), String.format("search#搜索#index=%s,type=%s", index, type));

        SearchRequestBuilder searchRequestBuilder = this.msOnionTransportClient.prepareSearch(index);
        // 只对当前目标POJO的 type进行搜索
        searchRequestBuilder.setTypes(type);

        // 判断 MsOnionSearchCondition
        if (condition instanceof MsOnionSearchCondition) {
            MsOnionSearchCondition msOnionSearchCondition = (MsOnionSearchCondition) condition;

            // 设置搜索类型
            if (null != msOnionSearchCondition.getSearchType()) {
                searchRequestBuilder.setSearchType(msOnionSearchCondition.getSearchType());
            }

            int from = msOnionSearchCondition.getFrom();
            from = MsOnionPagingUtils.getFrom(from);
            int size = msOnionSearchCondition.getSize();
            size = MsOnionPagingUtils.getPageSize(size);
            // 设置 from
            searchRequestBuilder.setFrom(from);
            // 设置 size
            searchRequestBuilder.setSize(size);
            // 设置 Explain
            searchRequestBuilder.setExplain(msOnionSearchCondition.isExplain());
            // 添加排序
            for (SortBuilder sortBuilder : msOnionSearchCondition.getSortBuilders()) {
                searchRequestBuilder.addSort(sortBuilder);
            }

            // boolQueryBuilder，组合查询， Boolean ：must、must_not、should
            BoolQueryBuilder boolQueryBuilder = getBoolQueryBuilder(apiVersion, parameter, msOnionSearchCondition);

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("search#搜索#获取boolQueryBuilder=%s", boolQueryBuilder));

            // 设置 QueryBuilder
            searchRequestBuilder.setQuery(boolQueryBuilder);

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("search#搜索#searchRequestBuilder=%s", searchRequestBuilder));

            // 是否高亮
            if (msOnionSearchCondition.isHighlight() && MsOnionCollectionUtils.isNotEmpty(msOnionSearchCondition.getHighlightFields())) {
                // 设置高亮
                setHighlighters(searchRequestBuilder, msOnionSearchCondition);
            }
            return doSearch(searchRequestBuilder, msOnionSearchCondition);
        }
        // 没有查询到结果
        return MsOnionESSearchPagingResult.failure();
    }

//    /**
//     * 搜索
//     *
//     * @param apiVersion api版本
//     * @param condition  搜索条件
//     * @return 返回搜索结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public MsOnionResult searchForResult(MsOnionApiVersion apiVersion, MsOnionSearchConditionAdapter condition)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return null;
//    }

//    /**
//     * 搜索
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param condition  搜索条件
//     * @return 返回搜索结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    @Override
//    public MsOnionResult searchForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                         MsOnionSearchConditionAdapter condition)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return null;
//    }

//    /**
//     * 搜索
//     *
//     * @param apiVersion api版本
//     * @param parameter  参数
//     * @param condition  搜索条件
//     * @return 返回搜索结果集合
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    private List<T> doSearch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, MsOnionSearchCondition condition)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//
//
//        return null;
//    }

    /**
     * 获取 组合查询Builder实例对象
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param condition  条件
     * @return 返回 组合查询Builder实例对象
     * @throws MsOnionIllegalArgumentException 参数异常
     * @throws MsOnionException                异常
     */
    private BoolQueryBuilder getBoolQueryBuilder(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, MsOnionSearchCondition condition)
            throws MsOnionIllegalArgumentException, MsOnionException {

        // BoolQueryBuilder 组合查询，可以使用 must、mustNot、should
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (MsOnionCollectionUtils.isEmpty(condition.getQuerySchemes())) {
            return boolQueryBuilder;
        }
        // 需要判断
        List<MsOnionQuerySchemeAdapter> querySchemes = condition.getQuerySchemes();

        Object value = null;
        String name = null;
        String strValue = null;
        String[] values = null;
        List<String> valueList = null;

        for (MsOnionQuerySchemeAdapter schemeAdapter : querySchemes) {
            if (schemeAdapter instanceof MsOnionTermLevelQueryScheme) {
                MsOnionTermLevelQueryScheme termLevelQueryScheme = (MsOnionTermLevelQueryScheme) schemeAdapter;
                executeTermLevelQueryScheme(boolQueryBuilder, name, value, strValue, values, valueList, termLevelQueryScheme);
            } else if (schemeAdapter instanceof MsOnionFullTextQueryScheme) {
                MsOnionFullTextQueryScheme fullTextQueryScheme = (MsOnionFullTextQueryScheme) schemeAdapter;
                executeFullTextQueryScheme(boolQueryBuilder, name, value, strValue, values, valueList, fullTextQueryScheme);
            }
        }
        return boolQueryBuilder;
    }

    /**
     * 执行 FullTextQueryScheme
     *
     * @param boolQueryBuilder    BoolQueryBuilder实例对象
     * @param name                名称
     * @param value               值
     * @param strValue            字符串的值
     * @param values              数组的值
     * @param valueList           集合的值
     * @param fullTextQueryScheme MsOnionFullTextQueryScheme枚举类型
     */
    private void executeFullTextQueryScheme(BoolQueryBuilder boolQueryBuilder, String name, Object value, String strValue, String[] values,
                                            List<String> valueList, MsOnionFullTextQueryScheme fullTextQueryScheme) {
        MsOnionFullTextQueryPattern queryPattern = fullTextQueryScheme.getQueryPattern();
        name = fullTextQueryScheme.getName();
        value = fullTextQueryScheme.getValue();

        switch (queryPattern) {
            case MATCH_QUERY:
                boolQueryBuilder.must(QueryBuilders.matchQuery(name, value));
                break;
            case MULTI_MATCH_QUERY:
                // multiMatchQuery(Object text, String... fieldNames)
                // 由于 name 规定是 String类型，因此只能交换， name 和 value 交换
//                boolQueryBuilder.must(QueryBuilders.multiMatchQuery(value))
                if (value instanceof String) {
                    strValue = String.valueOf(value);
                    // 由于 name 规定是 String类型，因此只能交换， name 和 value 交换
                    boolQueryBuilder.must(QueryBuilders.multiMatchQuery(name, strValue));
                } else if (value instanceof List) {
                    valueList = (List<String>) value;
                    // 由于 name 规定是 String类型，因此只能交换， name 和 value 交换
                    boolQueryBuilder.must(QueryBuilders.multiMatchQuery(name, valueList.toArray(new String[valueList.size()])));
                } else if (value instanceof String[]) {
                    values = (String[]) value;
                    // 由于 name 规定是 String类型，因此只能交换， name 和 value 交换
                    boolQueryBuilder.must(QueryBuilders.multiMatchQuery(name, values));
                }
                break;
            case COMMON_TERMS_QUERY:
                boolQueryBuilder.must(QueryBuilders.commonTermsQuery(name, String.valueOf(value)));
                break;
            case QUERY_STRING_QUERY:
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(String.valueOf(value)));
                break;
            case SIMPLE_QUERY_STRING:
                boolQueryBuilder.must(QueryBuilders.simpleQueryStringQuery(String.valueOf(value)));
                break;
            default:
                // 默认
                boolQueryBuilder.must(QueryBuilders.matchQuery(name, value));
                break;
        }
    }

    /**
     * 执行 TermLevelQueryScheme
     *
     * @param boolQueryBuilder     组合查询Builder
     * @param name                 名称
     * @param value                值
     * @param strValue             字符串的值
     * @param values               数组的值
     * @param valueList            集合的值
     * @param termLevelQueryScheme MsOnionTermLevelQueryScheme枚举类型
     */
    private void executeTermLevelQueryScheme(BoolQueryBuilder boolQueryBuilder, String name, Object value, String strValue, String[] values,
                                             List<String> valueList, MsOnionTermLevelQueryScheme termLevelQueryScheme) {
        MsOnionTermLevelQueryPattern queryPattern = termLevelQueryScheme.getQueryPattern();
        name = termLevelQueryScheme.getName();
        value = termLevelQueryScheme.getValue();

        // 遍历搜索方案
        switch (queryPattern) {
            case TERM_QUERY:
                // value 的所有类型，都是调用 Object类型
//                        QueryBuilders.termQuery("", null);
                boolQueryBuilder.must(QueryBuilders.termQuery(termLevelQueryScheme.getName(), termLevelQueryScheme.getValue()));
                break;
            case TERMS_QUERY:
                if (value instanceof String) {
                    // 如果是字符串，采取逗号(,)逗号分割
                    strValue = String.valueOf(value);
                    // 多个值
                    values = strValue.split(MsOnionElasticsearchConstants.ES_TERMS_QUERY_VALUE_SEPARATOR);
                    boolQueryBuilder.must(QueryBuilders.termsQuery(name, values));
                } else if (value instanceof List) {
                    // 集合，值都是字符串
                    valueList = (List<String>) value;
                    boolQueryBuilder.must(QueryBuilders.termsQuery(name, valueList));
                }
                break;
            case RANGE_QUERY:
                // 范围查询
                boolQueryBuilder.must(QueryBuilders.rangeQuery(name).from(termLevelQueryScheme.getFrom())
                        .to(termLevelQueryScheme.getTo()).includeLower(ES_EXISTS_INCLUDE_LOWER)
                        .includeUpper(ES_EXISTS_INCLUDE_UPPER));
                break;
            case EXISTS_QUERY:
                // 存在查询，意思就是只要存在字段名称
                boolQueryBuilder.must(QueryBuilders.existsQuery(name));
                break;
            case PREFIX_QUERY:
                // 前缀查询
                boolQueryBuilder.must(QueryBuilders.prefixQuery(name, String.valueOf(value)));
                break;
            case WILDCARD_QUERY:
                // 通配符查询
                strValue = String.valueOf(value);
                if (MsOnionStringUtils.isBlank(strValue)) {
                    // 遍历下一个
//                    continue;
//                    return;
                    break;
                }
                strValue = strValue.trim();
//                            if (strValue.contains(MsOnionElasticsearchConstants.ES_TERMS_QUERY_VALUE_SEPARATOR)) {
//                                values = strValue.split(MsOnionElasticsearchConstants.ES_TERMS_QUERY_VALUE_SEPARATOR);
//                                boolQueryBuilder.must(QueryBuilders.wildcardQuery(name, values));
//                            } else {
//                                boolQueryBuilder.must(QueryBuilders.wildcardQuery(name, strValue));
//                            }
                boolQueryBuilder.must(QueryBuilders.wildcardQuery(name, strValue));
                break;
            case REGEXP_QUERY:
                // 正则表达式匹配查询
                strValue = String.valueOf(value);
                if (MsOnionStringUtils.isBlank(strValue)) {
                    // 遍历下一个
//                    continue;
                    break;
                }
                strValue = strValue.trim();
                boolQueryBuilder.must(QueryBuilders.regexpQuery(name, strValue));
                break;
            case FUZZY_QUERY:
                // 模糊查询
                strValue = String.valueOf(value);
                if (MsOnionStringUtils.isBlank(strValue)) {
                    // 遍历下一个
//                    continue;
//                    return;
                    break;
                }
                strValue = strValue.trim();
                boolQueryBuilder.must(QueryBuilders.fuzzyQuery(name, strValue));
                break;
            case TYPE_QUERY:
                // type 查询
                boolQueryBuilder.must(QueryBuilders.typeQuery(name));
                break;
            case IDS_QUERY:
                if (value instanceof String) {
                    strValue = String.valueOf(value);
                    boolQueryBuilder.must(QueryBuilders.idsQuery(name).addIds(strValue));
                } else if (value instanceof List) {
                    valueList = (List<String>) value;
                    boolQueryBuilder.must(QueryBuilders.idsQuery(name).addIds(valueList.toArray(new String[valueList.size()])));
                } else if (value instanceof String[]) {
                    values = (String[]) value;
                    boolQueryBuilder.must(QueryBuilders.idsQuery(name).addIds(values));
                }
                break;
            default:
                // 默认
                boolQueryBuilder.must(QueryBuilders.termQuery(termLevelQueryScheme.getName(), termLevelQueryScheme.getValue()));
                break;
        }

    }

    /**
     * 设置 highlighter s
     *
     * @param searchRequestBuilder SearchRequestBuilder实例对象
     * @param condition            搜索条件实例对象
     */
    private void setHighlighters(final SearchRequestBuilder searchRequestBuilder, MsOnionSearchCondition condition) {

        // 创建 highlighter
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 设置高亮样式
        highlightBuilder.preTags(condition.getPreTags().toArray(new String[condition.getPreTags().size()]));
        highlightBuilder.postTags(condition.getPostTags().toArray(new String[condition.getPostTags().size()]));
        for (HighlightBuilder.Field highlightField : condition.getHighlightFields()) {
            // 打印日志
            msOnionLogger.debug(getClass().getName(), String.format("setHighlighters #添加高亮字段#highlightField=%s", highlightField));
            // 添加 highlightField
            highlightBuilder.fields().add(highlightField);
        }
        // 设置 highlighter
        searchRequestBuilder.highlighter(highlightBuilder);
        // 打印日志
        msOnionLogger.debug(getClass().getName(), String.format("setHighlighters # highlightBuilder=%s", highlightBuilder));
        msOnionLogger.debug(getClass().getName(), String.format("setHighlighters # highlightBuilder.fields()=%s", highlightBuilder.fields()));
    }

    /**
     * 进行搜索
     *
     * @param searchRequestBuilder SearchRequestBuilder实例对象
     * @param condition            搜索条件实例对象
     * @return 返回搜索结果
     * @throws MsOnionException 异常
     */
    public MsOnionESSearchPagingResult doSearch(SearchRequestBuilder searchRequestBuilder, MsOnionSearchCondition condition) throws MsOnionException {
        try {
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#搜索条件condition=%s", condition));

            List<T> list = new ArrayList<T>();

            // 执行搜索
            SearchResponse searchResponse = searchRequestBuilder.get();
//            SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#搜索结果searchResponse=%s", searchResponse));

            // 获取搜索的文档结果
            SearchHits hits = searchResponse.getHits();
            // 总记录数
            long totalHits = hits.getTotalHits();
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#搜索结果#hits=%s，totalHits=%s", hits, totalHits));
            // Elasticsearch 搜索分页结果，注意：将 long 强行转换成 int ，int 最大值 21.+ 亿
            MsOnionESSearchPagingResult esSearchPagingResult =
                    new MsOnionESSearchPagingResult((int) totalHits, condition.getFrom(), condition.getSize());

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#总记录totalHits=%s", totalHits));

//            esSearchPagingResult.setData()

//        float maxScore = hits.getMaxScore();

//            hits.getTotalHits();
//            hits.totalHits();

//            SearchHit[] searchHits = hits.getHits();

//            this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#数组searchHits=%s", searchHits));

//            this.msOnionLogger.debug(getClass().getName(), "doSearch#搜索#数组searchHits=" + searchHits + "，searchHits.length=" + searchHits.length);

            Iterator<SearchHit> iterator = hits.iterator();
            List<Map<String, Object>> sourceList = new ArrayList<>();
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#迭代器iterator=%s", iterator));
            // 迭代
            while (iterator.hasNext()) {
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#迭代器内部1111"));
                // next
                SearchHit searchHit = iterator.next();
                // 将文档中的每一个对象转换Json字符串
//               String json = searchHit.getSourceAsString();
//               // 将Json字符串转换成对应的目标POJO实例对象
//               T t = MsOnionJsonUtils.jsonToPojo(json, getTargetClazz());
                // 搜索结果文档资源
                Map<String, Object> source = searchHit.getSource();
                // 打印日志
                this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代#搜索结果文档资源#source=%s", source));
                // 获取对应的高亮域
                Map<String, HighlightField> highlightFields = searchHit.highlightFields();
                // 没有高亮域
                if (!condition.isHighlight() || MsOnionMapUtils.isEmpty(highlightFields)
                        || MsOnionCollectionUtils.isEmpty(condition.getHighlightFields())) {
                    this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 没有高亮 ## highlightFields=%s", highlightFields));
                    // 没有高亮，直接是原始数据
                    sourceList.add(source);
                    // 下一个
                    continue;
                }
                // 有高亮域
                // 遍历高亮字段集合，从搜索条件中获取
                for (HighlightBuilder.Field field : condition.getHighlightFields()) {
                    // 从搜索结果中获取
                    HighlightField highlightField = highlightFields.get(field.name());
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 高亮字段highlightField=%s", highlightField));
                    // 搜索结果中没有当前高亮
                    if (null == highlightField) {
                        // 下一个循环
                        continue;
                    }
                    // 搜索结果中，有高亮字段
                    Text[] fragments = highlightField.fragments();
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 高亮字段#fragments=%s", fragments));

                    if (MsOnionArrayUtils.isEmpty(fragments)) {
                        // 下一个
                        continue;
                    }
                    // 不为空
                    StringBuilder sb = new StringBuilder();
                    for (Text text : fragments) {
                        // 打印日志
                        this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 高亮字段#text=%s", text));
                        sb.append(text);
                    }
                    // http://blog.csdn.net/u014698348/article/details/62884320
//                   source.put(needHighLightField, name.toString());
//                   highLights.add(needHighLightField + ":" + name.toString());

                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 高亮字段之后#field.name()=%s,sb=%s",
                            field.name(), sb.toString()));
                    // 如果有高亮，将原始字段替换成高亮
                    source.put(field.name(), sb.toString());
                    // 需要保存高亮吗？？？
                    sourceList.add(source);
                    // 打印日志
                    this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 高亮字段之后#source=%s", source));

                    this.msOnionLogger.debug(getClass().getName(), String.format("doSearch#搜索#结果迭代# 高亮字段之后#集合#sourceList=%s", sourceList));
                }
            }
            // 添加到集合中
            esSearchPagingResult.setData(sourceList);
            esSearchPagingResult.setStatus(MsOnionStatusConstants.STATUS_200);
            esSearchPagingResult.setMsg(MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
            // 返回结果
            return esSearchPagingResult;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /////////////////// 搜索 ### End #####  //////////////////////////////////////

    /////////////////// 检查参数 ### Begin #####  //////////////////////////////////////

    /**
     * 检查参数
     *
     * @param apiVersion api版本
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameterForAppVersion(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException {
        if (null == apiVersion) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_APIVERSION_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionStringUtils.isBlank(apiVersion.getRequestApiVersion())) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_APIVERSION_REQUEST_APIVERSION_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查参数，搜索条件
     *
     * @param condition 搜索条件的实例对象
     * @throws MsOnionIllegalArgumentException 参数异常
     */
    protected void inspectParameterForCondition(MsOnionSearchConditionAdapter condition) throws MsOnionIllegalArgumentException {
        if (null == condition) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_CONDITION_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /////////////////// 检查参数 ### End #####  //////////////////////////////////////

    /////////////////// Mapping ### Begin #####  //////////////////////////////////////



    /////////////////// Mapping ### End #####  //////////////////////////////////////

    /////////////////////////// Setters、 Getters ##  Begin //////////////////////

//    /**
//     * 获取目标POJO的Class
//     *
//     * @return 返回目标POJO的Class
//     * @throws MsOnionException 异常
//     * @Title: getTargetClazz
//     * @Description: 获取目标POJO的Class
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月8日 下午23:31:10
//     */
//    public Class<T> getTargetClazz() throws MsOnionException {
//        if (null != this.targetClazz) {
//            return this.targetClazz;
//        }
//        return this.targetClazz = (Class<T>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass());
//    }

    /**
     * 获取 Elasticsearch 客户端
     * @return 返回 Elasticsearch 客户端
     */
    public MsOnionTransportClient getMsOnionTransportClient() {
        return msOnionTransportClient;
    }


    /////////////////////////// Setters、 Getters ##  End //////////////////////


}
