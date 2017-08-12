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

package cc.msonion.carambola.parent.ext.elasticsearch.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException;
import cc.msonion.carambola.parent.common.utils.MsOnionArrayUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.elasticsearch.action.MsOnionActionListener;
import cc.msonion.carambola.parent.ext.elasticsearch.constants.MsOnionElasticsearchConstants;
import cc.msonion.carambola.parent.ext.elasticsearch.pojo.MsOnionElasticsearchItem;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkIndexByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Elasticsearch工具类
 *
 * @Title: MsOnionArrayUtils.java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.utils
 * @Description: Elasticsearch工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月4日 下午8:26:31
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月4日 下午8:26:31
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * Elasticsearch工具类
 *
 * @ClassName: MsOnionElasticsearchUtils
 * @Description: Elasticsearch工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月4日 下午8:26:31
 */
public final class MsOnionElasticsearchUtils {

    /**
     * 多个 transportAddress 分隔符
     */
    private static final String MULTI_TRANSPORTADDRESS_SEPARATOR = ";";

    /**
     * transportAddress 端口分隔符
     */
    private static final String TRANSPORTADDRESS_PORT_SEPARATOR = ":";

    /**
     * 统配符：*
     */
    public static final String WILDCARD_ALL = "*";

    private MsOnionElasticsearchUtils() {

    }

    /**
     * 获取 TransportAddress 集合
     *
     * @param transportAddress 是以";"分隔，以":"分隔端口，例如：188.168.100.10:9300;188.168.100.11:9300;188.168.100.12:9300
     * @return 返回 TransportAddress 集合 List
     */
    public static List<TransportAddress> getTransportAddresses(String transportAddress) {
        if (MsOnionStringUtils.isBlank(transportAddress)) {
            throw new MsOnionRuntimeException("transportAddress is null");
        }

        try {
            // transportAddress 是以";"分隔，以":"分隔端口
            String[] transportAddresseses = transportAddress.split(MULTI_TRANSPORTADDRESS_SEPARATOR);

            List<TransportAddress> transportAddressList = new ArrayList<TransportAddress>();
            for (String trAddress : transportAddresseses) {

                if (MsOnionStringUtils.isBlank(trAddress)) {
                    continue;
                }
                String[] addressAndPort = trAddress.split(TRANSPORTADDRESS_PORT_SEPARATOR);
                String host = addressAndPort[0];
                String port = addressAndPort[1];

                // 创建 InetSocketTransportAddress
                InetSocketTransportAddress ista = new InetSocketTransportAddress(InetAddress.getByName(host.trim()),
                        Integer.parseInt(port.trim()));

                // 添加到集合中
                transportAddressList.add(ista);
            }

            return transportAddressList;
        } catch (Exception ex) {
            throw new MsOnionRuntimeException(ex);
        }
    }

    /**
     * 获取 TransportAddress 数组
     *
     * @param transportAddress 是以";"分隔，以":"分隔端口，例如：188.168.100.10:9300;188.168.100.11:9300;188.168.100.12:9300
     * @return 返回 TransportAddress 数组
     */
    public static TransportAddress[] getTransportAddressesForArray(String transportAddress) {
        List<TransportAddress> transportAddressList = getTransportAddresses(transportAddress);
        return (TransportAddress[]) transportAddressList.toArray();
    }

    ////////////////////////////////// 添加索引 ### Begin ///////////////////////////////////////////////

    /**
     * 新增索引
     *
     * @param client 客户端
     * @param index  索引名称
     * @param type   类型名称
     * @param idx    主键idx
     * @param json   数据源，Json格式字符串
     * @return 返回IndexResponse实例对象
     * @throws MsOnionException 异常
     */
    public static IndexResponse add(TransportClient client, String index, String type, long idx, String json) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdx(idx);
        inspectParameterForJson(json);

        index = index.trim();
        type = type.trim();
        json = json.trim();
        try {
            return client.prepareIndex(index, type, String.valueOf(idx)).setSource(json).get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ///////////////////////////////// 添加索引 ### End ///////////////////////////////////////////////

    ///////////////////////////////// 删除索引中记录 ### Begin ///////////////////////////////////////////////

    /**
     * 删除索引中记录，根据主键idx
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idx    主键idx
     * @return 返回 DeleteResponse 实例对象
     * @throws MsOnionException 异常
     */
    public static DeleteResponse delete(TransportClient client, String index, String type, long idx) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdx(idx);

        index = index.trim();
        type = type.trim();
        try {
            return client.prepareDelete(index, type, String.valueOf(idx)).get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 通过查询API删除，可以根据此删除一组给定的文档查询的结果，
     * <p>注意：同步操作</p>
     *
     * @param client 客户端
     * @param name   名称
     * @param text   文本
     * @param source 资源
     * @return 返回删除记录数
     * @throws MsOnionException 异常
     */
    public static long delete(TransportClient client, String name, String text, String source) throws MsOnionException {
        // 检查参数
        inspectParameterForClient(client);
        inspectParameterForName(name);
        inspectParameterForText(text);
        inspectParameterForSource(source);

        name = name.trim();
        text = text.trim();
        source = source.trim();
        BulkIndexByScrollResponse bulkIndexByScrollResponse = DeleteByQueryAction.INSTANCE.newRequestBuilder(client).
                filter(QueryBuilders.matchQuery(name, text)).source(source).get();

        return bulkIndexByScrollResponse.getDeleted();
    }

    /**
     * 异步删除，它可以是一个长时间运行的操作，想异步执行，可以调用`execute`而不是`get`，并提供一个 Listener
     *
     * @param client         客户端
     * @param name           名称
     * @param text           文本
     * @param source         资源
     * @param actionListener 行为监听器
     * @throws MsOnionException 异常
     */
    public static void asyncDelete(TransportClient client, String name, String text, String source,
                                   MsOnionActionListener actionListener) throws MsOnionException {
        // 检查参数
        inspectParameterForClient(client);
        inspectParameterForName(name);
        inspectParameterForText(text);
        inspectParameterForSource(source);
        inspectParameterForListener(actionListener);

        name = name.trim();
        text = text.trim();
        source = source.trim();
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery(name, text))
                .source(source).execute(actionListener);
    }

    ///////////////////////////////// 删除索引中记录 ### End ///////////////////////////////////////////////

    ///////////////////////////////// 更新索引中记录 ### Begin ///////////////////////////////////////////////

    /**
     * 根据idx 更新索引记录
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idx    主键idx
     * @param source 资源
     * @return 返回 UpdateResponse 实例对象
     * @throws MsOnionException 异常
     */
    public static UpdateResponse update(TransportClient client, String index, String type, long idx, String source) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdx(idx);
        inspectParameterForSource(source);

        index = index.trim();
        type = type.trim();
        source = source.trim();
        try {
            return client.prepareUpdate(index, type, String.valueOf(idx)).setDoc(source).get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 还支持upsert，如果文档不存在，则将使用upsert元素的内容对新文档进行索引
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idx    主键idx
     * @param source 资源
     * @throws MsOnionException 异常
     */
    public static UpdateResponse upsert(TransportClient client, String index, String type, long idx, String source) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdx(idx);
        inspectParameterForSource(source);
        String strIdx = String.valueOf(idx);

        index = index.trim();
        type = type.trim();
        source = source.trim();
        source = source.trim();
        try {
            // index
            IndexRequest indexRequest = new IndexRequest(index, type, strIdx).source(source);

            // update
            UpdateRequest updateRequest = new UpdateRequest(index, type, strIdx).doc(source).upsert(indexRequest);

            return client.update(updateRequest).get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }


    ///////////////////////////////// 更新索引中记录 ### End ///////////////////////////////////////////////


    ///////////////////////////////// 获取索引中记录 ### Begin ///////////////////////////////////////////////

    /**
     * 获取
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idx    主键idx
     * @return 返回GetResponse实例对象
     * @throws MsOnionException 异常
     */
    public static GetResponse get(TransportClient client, String index, String type, long idx) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdx(idx);

        index = index.trim();
        type = type.trim();
        try {
            return client.prepareGet(index, type, String.valueOf(idx)).get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param client 客户端
     * @param items  MsOnionElasticsearchItem集合List
     * @return 返回结果
     * @throws MsOnionException 异常
     */
    public static List<String> getMulti(TransportClient client, List<MsOnionElasticsearchItem> items) throws MsOnionException {
        // 检查参数
        inspectParameterForClient(client);
        inspectParameterForItems(items);

        try {
            MultiGetRequestBuilder multiGetRequestBuilder = client.prepareMultiGet();
            // 添加 item
            for (MsOnionElasticsearchItem item : items) {
                multiGetRequestBuilder.add(item);
            }
            return get(multiGetRequestBuilder);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idx    主键idx
     * @return 返回结果
     * @throws MsOnionException 异常
     */
    public static List<String> getMulti(TransportClient client, String index, String type, long idx) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdx(idx);

        index = index.trim();
        type = type.trim();
        try {
            MultiGetRequestBuilder multiGetRequestBuilder = client.prepareMultiGet();
            multiGetRequestBuilder.add(index, type, String.valueOf(idx));
            return get(multiGetRequestBuilder);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idxes  主键idx
     * @return 返回结果
     * @throws MsOnionException 异常
     */
    public static List<String> getMulti(TransportClient client, String index, String type, long... idxes) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdxes(idxes);

        index = index.trim();
        type = type.trim();
        try {
            MultiGetRequestBuilder multiGetRequestBuilder = client.prepareMultiGet();
            multiGetRequestBuilder.add(index, type, MsOnionArrayUtils.toArrayForString(idxes));
            return get(multiGetRequestBuilder);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * multi get API允许根据其索引，类型和ID获取文档列表
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @param idxes  主键idx
     * @return 返回结果
     * @throws MsOnionException 异常
     */
    public static List<String> getMulti(TransportClient client, String index, String type, List<Long> idxes) throws MsOnionException {
        // 检查参数
        inspectParameters(client, index, type);
        inspectParameterForIdxes(idxes);

        index = index.trim();
        type = type.trim();
        try {
            MultiGetRequestBuilder multiGetRequestBuilder = client.prepareMultiGet();
            multiGetRequestBuilder.add(index, type, MsOnionArrayUtils.toArrayForString(idxes));
            return get(multiGetRequestBuilder);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

//    public static <POJO extends MsOnionBasePojoAdapter> MsOnionResult getMutilForResult(TransportClient client, String index, String type, List<Long> idxes) throws MsOnionException {
//
//        List<String> list = getMulti(client, index, type, idxes);
//
//        for (String json : list) {
//            // TODO: 是 POJO 还是 List ？？？
//            MsOnionJsonUtils.jsonToPojo(json, POJO.clss);
////            MsOnionJsonUtils.jsonToList()
//        }
//    }

    /**
     * get multi
     *
     * @param multiGetRequestBuilder MultiGetRequestBuilder实例对象
     * @return 返回结果
     */
    private static List<String> get(MultiGetRequestBuilder multiGetRequestBuilder) {
        MultiGetResponse multiGetItemResponses = multiGetRequestBuilder.get();

        List<String> result = new ArrayList<String>();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                result.add(json);
            }
        }

        return result;
    }


    ///////////////////////////////// 获取索引中记录 ### End ///////////////////////////////////////////////

    ///////////////////////////////// bulk  ######## Begin //////////////////////////////////////////

    /**
     * 批量API允许在单个请求中对多个文档进行索引和更新
     *
     * @param client        客户端
     * @param index         索引
     * @param type          类型
     * @param list          目标POJO的集合
     * @param msOnionLogger    日志
     * @param eachBulkCount 每一个Bulk数量
     * @param <POJO>        目标POJO
     * @throws MsOnionException 异常
     */
    public static <POJO extends MsOnionBasePojoAdapter> void bulkForUpsert(TransportClient client, String index, String type, List<POJO> list, MsOnionLogger msOnionLogger, int eachBulkCount) throws MsOnionException {

        // 校验参数
        inspectParameterForClient(client);


        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        // either use client#prepare, or use Requests# to directly build index/delete requests
        // 遍历
        for (int i = 0; i < list.size(); i++) {
            try {
                POJO pojo = list.get(i);
                String json = MsOnionJsonUtils.objectToJson(pojo);
                // index
                IndexRequest indexRequest = new IndexRequest(index, type, pojo.getId()).source(json);
                // update
                UpdateRequest updateRequest = new UpdateRequest(index, type, pojo.getId()).doc(json).upsert(indexRequest);
                // add updateRequest
                bulkRequestBuilder.add(updateRequest);

                // 分批处理
                if (list.size() > eachBulkCount && i % eachBulkCount == 0) {
                    bulkRequestBuilder.get();
                }
            } catch (Exception ex) {
                msOnionLogger.error(MsOnionElasticsearchUtils.class.getName(), ex);
            }
        }

        BulkResponse bulkResponse = bulkRequestBuilder.get();
        // TODO: 失败的进行重新处理！！！
//        if (bulkResponse.hasFailures()) {
//            // process failures by iterating through each bulk response item
//            BulkItemResponse[] bulkItemResponses = bulkResponse.getItems();
//
//            for (BulkItemResponse bulkItemResponse : bulkItemResponses) {
//                if (bulkItemResponse.isFailed()) {
//                    String failureMessage = bulkItemResponse.getFailureMessage();
//                    String id = bulkItemResponse.getId();
//                    String index2 = bulkItemResponse.getIndex();
//                    String type2 = bulkItemResponse.getType();
//                }
//            }
//        }
    }

    ///////////////////////////////// bulk  ######## End //////////////////////////////////////////

    ///////////////////////////////// 搜索  ######## Begin //////////////////////////////////////////


    ///////////////////////////////// 搜索  ######## End //////////////////////////////////////////

    //////////////////////////// 检查参数  ### Begin ///////////////////////////////////////////////////////

    /**
     * 检查参数
     *
     * @param client 客户端
     * @param index  索引
     * @param type   类型
     * @throws MsOnionException 异常
     */
    public static void inspectParameters(TransportClient client, String index, String type) throws MsOnionException {
        if (null == client) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_TRANSPORTCLIENT_ILLEGAL);
        }
        if (MsOnionStringUtils.isBlank(index)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_INDEX_ILLEGAL);
        }
        if (MsOnionStringUtils.isBlank(type)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_TYPE_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param client 客户端
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForClient(TransportClient client) throws MsOnionException {
        if (null == client) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_TRANSPORTCLIENT_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param json 需要索引的Json字符串数据
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForJson(String json) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(json)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_JSON_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param source 需要索引的source字符串数据
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForSource(String source) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(source)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_JSON_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param name 名称
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForName(String name) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(name)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_NAME_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param text 文本
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForText(String text) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(text)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_TEXT_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param idx 主键idx
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForIdx(long idx) throws MsOnionException {
        if (idx <= 0 || idx >= Long.MAX_VALUE) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_IDX_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param idxes 主键idx集合
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForIdxes(List<Long> idxes) throws MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(idxes)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_IDXES_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param idxes 主键idx集合
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForIdxes(long... idxes) throws MsOnionException {
        if (MsOnionArrayUtils.isEmpty(idxes)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_IDXES_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param listener 行为监听器
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForListener(MsOnionActionListener listener) throws MsOnionException {
        if (null == listener) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_LISTENER_ILLEGAL);
        }
    }

    /**
     * 检查参数
     *
     * @param items MsOnionElasticsearchItem集合
     * @throws MsOnionException 异常
     */
    public static void inspectParameterForItems(List<MsOnionElasticsearchItem> items) throws MsOnionException {
        if (MsOnionCollectionUtils.isEmpty(items)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_PARAMETER_TRANSPORTCLIENT_ILLEGAL);
        }
    }

    //////////////////////////// 检查参数  ### End  ///////////////////////////////////////////////////////


    /////////////////////////////// ES 通配符 ############## Begin /////////////////////

    /**
     * 得到通配符查询值表达式，例如：*iPhone*
     * @param value 需要使用通配符查询的字符串，例如：iPhone
     * @return 返回通配符查询值表达式，例如：*iPhone*
     */
    public static String getWildcardQueryValueExpression(String value) {
        if (MsOnionStringUtils.isBlank(value)) {
            return WILDCARD_ALL;
        }
        return String.format("%s%s%s", WILDCARD_ALL, value.trim(), WILDCARD_ALL);
    }

    /////////////////////////////// ES 通配符 ############## End /////////////////////


    ////////////////////////  Mapping ### Begin ////////////////////////////

    public static void mapping(TransportClient client, String index, String type) throws MsOnionException {

       

        client.admin().indices().prepareCreate("producthuzhuindex").execute().actionGet();

     
//        XContentBuilder mapping = XContentFactory.jsonBuilder()
//                .startObject() .startObject("producthuzhuindex").startObject("properties")
//
//
//                .startObject("plan_intro")  //嵌套对象字段
//                .startObject("properties")
//                .startObject("item").field("type", "string").field("store", "yes").field("analyzer", "ik").field("search_analyzer", "ik").endObject()
//                .startObject("content").field("type", "string").field("store", "yes").field("analyzer", "ik").field("search_analyzer", "ik").endObject()
//                .endObject()
//                .endObject()
//
//                .startObject("today_member").field("type", "string").field("store", "yes").endObject();   //普通字段

    }



    ////////////////////////  Mapping ### End ////////////////////////////

}
