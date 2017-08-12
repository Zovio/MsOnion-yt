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

package cc.msonion.carambola.parent.ext.elasticsearch.service.base;

/**
 * Elasticsearch BaseService
 *
 * @Title: MsOnionElasticsearchBaseService.java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.service.base
 * @Description: Elasticsearch BaseService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月5日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.ext.elasticsearch.action.MsOnionActionListener;
import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionSearchConditionAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.client.MsOnionTransportClient;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.MsOnionElasticsearchItem;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.paging.MsOnionESSearchPagingResult;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Elasticsearch BaseService
 *
 * @param <T> 目标Pojo，例如：Item
 * @ClassName: MsOnionElasticsearchBaseService
 * @Description: Elasticsearch BaseService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 */
public interface MsOnionElasticsearchBaseService<T extends MsOnionBasePojoAdapter> extends Serializable {

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
    String getTargetName() throws MsOnionException;

    /**
     * 获取Elasticsearch 索引名称，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引名称，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    String getIndexName() throws MsOnionException;

    /**
     * 获取Elasticsearch 索引Type，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引Type，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    String getTypeName() throws MsOnionException;

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
    Class<T> getTargetClazz() throws MsOnionException;

    ///////////////////////////////// 添加  ######## Begin //////////////////////////////////////////

    /**
     * 添加 Elasticsearch 索引
     *
     * @param source     资源、数据源
     * @param idx        主键idx
     * @param apiVersion API版本
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    IndexResponse add(MsOnionApiVersion apiVersion, long idx, String source) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 添加 Elasticsearch 索引
     *
     * @param source     资源、数据源
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    IndexResponse add(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 添加 Elasticsearch 索引
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源、数据源
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionResult addForResult(MsOnionApiVersion apiVersion, long idx, String source) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 添加 Elasticsearch 索引
     *
     * @param source     资源、数据源
     * @param apiVersion API版本
     * @param parameter  参数
     * @param idx 主键参数
     * @return 返回 IndexResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionResult addForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

    ///////////////////////////////// 添加  ######## End //////////////////////////////////////////

    ///////////////////////////////// 删除  ######## End //////////////////////////////////////////

    /**
     * 删除
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    DeleteResponse delete(MsOnionApiVersion apiVersion, long idx) throws MsOnionIllegalArgumentException, MsOnionException;

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
    DeleteResponse delete(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionResult deleteForResult(MsOnionApiVersion apiVersion, long idx) throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionResult deleteForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    void asyncDelete(MsOnionApiVersion apiVersion, String name, String text, String source,
                     MsOnionActionListener actionListener) throws MsOnionIllegalArgumentException, MsOnionException;

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
    void asyncDelete(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, String name,
                     String text, String source, MsOnionActionListener actionListener)
            throws MsOnionIllegalArgumentException, MsOnionException;


    ///////////////////////////////// 删除  ######## End //////////////////////////////////////////

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
    UpdateResponse update(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    UpdateResponse update(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionResult updateForResult(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionResult updateForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    UpdateResponse upsert(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    UpdateResponse upsert(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @param source     资源
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionResult upsertForResult(MsOnionApiVersion apiVersion, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionResult upsertForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, String source)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 GetResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    GetResponse get(MsOnionApiVersion apiVersion, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    GetResponse get(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取
     *
     * @param apiVersion API版本
     * @param idx        主键idx
     * @return 返回 GetResponse 实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionResult getForResult(MsOnionApiVersion apiVersion, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionResult getForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param items      MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    List<String> get(MsOnionApiVersion apiVersion, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    List<String> get(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param items      MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionResult getForResult(MsOnionApiVersion apiVersion, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionResult getForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<MsOnionElasticsearchItem> items)
            throws MsOnionIllegalArgumentException, MsOnionException;

//    /**
//     * multi get API允许根据其索引，类型和ID获取文档列表
//     *
//     * @param idx    主键idx
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException, MsOnionException 异常
//     */
//    List<String> getMulti(long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    List<String> getMulti(MsOnionApiVersion apiVersion, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    List<String> getMulti(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    List<String> getMulti(MsOnionApiVersion apiVersion, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    List<String> getMulti(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    List<String> getMultiForResult(MsOnionApiVersion apiVersion, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    List<String> getMultiForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long... idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param apiVersion API版本
     * @param idxes      主键idx
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    List<String> getMultiForResult(MsOnionApiVersion apiVersion, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    List<String> getMultiForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException;

    ///////////////////////////////// 搜索  ######## Begin //////////////////////////////////////////

    /**
     * 搜索
     *
     * @param apiVersion api版本
     * @param condition  搜索条件
     * @return 返回搜索结果集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    MsOnionESSearchPagingResult search(MsOnionApiVersion apiVersion, MsOnionSearchConditionAdapter condition)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
    MsOnionESSearchPagingResult search(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                       MsOnionSearchConditionAdapter condition) throws MsOnionIllegalArgumentException, MsOnionException;

//    /**
//     * 搜索
//     *
//     * @param apiVersion api版本
//     * @param condition  搜索条件
//     * @return 返回搜索结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    MsOnionResult searchForResult(MsOnionApiVersion apiVersion, MsOnionSearchConditionAdapter condition)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
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
//    MsOnionResult searchForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
//                                  MsOnionSearchConditionAdapter condition)
//            throws MsOnionIllegalArgumentException, MsOnionException;

    ///////////////////////////////// 搜索  ######## End //////////////////////////////////////////

    /**
     * 获取 Elasticsearch 客户端
     * @return 返回 Elasticsearch 客户端
     */
    MsOnionTransportClient getMsOnionTransportClient();

}
