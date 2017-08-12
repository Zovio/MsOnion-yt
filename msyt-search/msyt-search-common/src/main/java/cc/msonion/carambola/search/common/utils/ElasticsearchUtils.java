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
package cc.msonion.carambola.search.common.utils;

/**
 * @Title: ElasticsearchUtils.java
 * @Package: cc.msonion.carambola.search.common.utils
 * @Description: 索引工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/4 14:15
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/4 14:15
 * @Modify-version:
 * @Modify-description:
 */

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: ElasticsearchUtils
 * @Description: 索引工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/4 14:15
 */
public final class ElasticsearchUtils {
    private ElasticsearchUtils() {
    }

    /**
     * 客户端
     */
    private static TransportClient client = null;

    /**
     * 端口
     */
    private static final int PORT = 9300;

    /**
     * 索引名称前缀
     */
    public static final String INDEX_NAME = "msyt";

    /**
     * 分隔符
     */
    public static final String SEPARATOR = "_";

    /**
     * 获取客户端
     *
     * @return 返回客户端
     * @throws MsOnionException 自定义异常
     */
    public static TransportClient getClient() throws MsOnionException {
        try {
            Settings settings = Settings.builder().put("cluster.name", "es.msyc.dev").build();
            client = new PreBuiltTransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("es01-dev"), PORT));
        } catch (UnknownHostException e) {
            throw new MsOnionException(e);
        }
        return client;
    }

    /**
     * 关闭客户端
     */
    public static void closeClient() {
        if (client != null) {
            client.close();
        }
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名称
     * @return true/false
     * @throws MsOnionException 自定义异常
     */
    public static boolean isIndexExists(String indexName) throws MsOnionException {
        try {
            IndicesAdminClient adminClient = getClient().admin().indices();
            IndicesExistsResponse indicesExistsResponse = adminClient.prepareExists(indexName).execute().actionGet();
            return indicesExistsResponse.isExists();
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 判断类型是否存在
     *
     * @param indexName 索引名称
     * @param typeName  类型名称
     * @return true/false
     * @throws MsOnionException 自定义异常
     */
    public static boolean isTypeExists(String indexName, String typeName) throws MsOnionException {
        try {
            if (isIndexExists(indexName)) {
                TypesExistsResponse typesExistsResponse = getClient().admin().indices().prepareTypesExists(indexName).
                        setTypes(typeName).execute().actionGet();
                return typesExistsResponse.isExists();
            }
            return false;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 获取索引名称
     *
     * @param typeName 类型名称
     * @return 返回索引名称
     */
    public static String getIndexName(String typeName) {
        return INDEX_NAME + SEPARATOR + typeName;
    }

    /**
     * 删除索引
     *
     * @param typeName 类型名称
     * @return 返回删除结果
     * @throws MsOnionException 自定义异常
     */
    public static boolean deleteIndex(String typeName) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);

            if (isIndexExists(indexName)) {
                DeleteIndexResponse response = getClient().admin().indices().prepareDelete(indexName).execute().actionGet();
                return response.isAcknowledged();
            }
            return false;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 新增数据
     *
     * @param typeName 类型名称
     * @param id       ID
     * @param source   资源
     * @return 返回新增结果
     * @throws MsOnionException 自定义异常
     */
    public static boolean prepareIndexSetIdAndSource(String typeName, String id, Map source) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);

            IndexResponse response = getClient().prepareIndex(indexName, typeName).setId(id).setSource(source).execute().actionGet();
            int status = response.status().getStatus();

            return (status == MsOnionStatusConstants.STATUS_200);
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 删除数据
     *
     * @param typeName 类型名称
     * @param id       ID
     * @return 返回删除结果
     * @throws MsOnionException 自定义异常
     */
    public static boolean prepareDeleteIndexTypeId(String typeName, String id) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);

            DeleteResponse response = getClient().prepareDelete(indexName, typeName, id).execute().actionGet();
            int status = response.status().getStatus();

            return (status == MsOnionStatusConstants.STATUS_200);
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 创建查询条件
     *
     * @param inputFields 录入条件
     * @return 返回条件构造器
     * @throws MsOnionException 自定义异常
     */
    public static BoolQueryBuilder createConditionFromInputFields(Map<String, Object> inputFields) throws MsOnionException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (inputFields == null) {
            return null;
        }

        Iterator<Map.Entry<String, Object>> iterator = inputFields.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();

            //固定参数不处理
            if ("pageIndex".equals(key) || "start".equals(key) || "limit".equals(key) || "direction".equals(key) || "field".equals(key)) {
                continue;
            }

            //key没有前缀不处理，如eq、ge、in
            if (!key.contains("_")) {
                System.out.println("ElasticsearchUtils.createConditionFromInputFields时，key没有前缀不处理，key：" + key);
                continue;
            }

            Object value = entry.getValue();
            //key前缀不为isNull并且未传值的不处理
            String prefix = key.substring(0, key.indexOf("_"));
            if (!"isNull".equals(prefix) && value == null) {
                continue;
            }

            String suffix = key.substring(key.indexOf("_") + 1);

            if ("eq".equals(prefix)) {
                queryBuilder.must(QueryBuilders.termsQuery(suffix, value));
            }
            if ("in".equals(prefix)) {
                queryBuilder.must(QueryBuilders.termsQuery(suffix, value.toString().split(",")));
            }
            if ("notin".equals(prefix)) {
                queryBuilder.mustNot(QueryBuilders.termsQuery(suffix, value.toString().split(",")));
            }
            if ("like".equals(prefix)) {
                queryBuilder.must(QueryBuilders.wildcardQuery(suffix, "*" + value.toString() + "*"));
            }
            if ("not".equals(prefix)) {
                queryBuilder.mustNot(QueryBuilders.termsQuery(suffix, value));
            }
        }

        return queryBuilder;
    }

    /**
     * 排序
     *
     * @param inputFields 录入条件
     * @param orderBy     排序字段
     * @return 返回排序内容，比如 "id|-name" 按id顺序name倒序进行排序
     */
    public static String prepareOrderByParameters(Map inputFields, String orderBy) {
        Object direction = inputFields.get("direction");
        Object field = inputFields.get("field");

        String tempOrderBy = "";
        if ((null != field) && (null != direction)) {
            if (!"ASC".equals(direction)) {
                tempOrderBy += "-";
            }
            tempOrderBy += field;
        }

        if (StringUtils.isNotBlank(orderBy)) {
            if (StringUtils.isNotBlank(tempOrderBy)) {
                tempOrderBy += "|";
            }
            tempOrderBy += orderBy;
        }

        return tempOrderBy;
    }

    /**
     * 数量
     *
     * @param typeName     类型名称
     * @param queryBuilder 条件构造器
     * @return 返回数量
     * @throws MsOnionException 自定义异常
     */
    public static long findCountByCondition(String typeName, BoolQueryBuilder queryBuilder) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);

            SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(indexName)
                    .setTypes(typeName)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(queryBuilder)
                    .setFrom(0)
                    .setSize(0);

            SearchResponse response = searchRequestBuilder.execute().actionGet();
            SearchHits hits = response.getHits();
            long count = hits.getTotalHits();

            return count;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 求和
     *
     * @param typeName     类型名称
     * @param field        求和字段
     * @param queryBuilder 条件构造器
     * @return 返回总和
     * @throws MsOnionException 自定义异常
     */
    public static double findSumByCondition(String typeName, String field, BoolQueryBuilder queryBuilder) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);
            SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_" + field).field(field);

            SearchRequestBuilder searchRequestBuilder = getClient().prepareSearch(indexName)
                    .setTypes(typeName)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(queryBuilder)
                    .addAggregation(sumBuilder);

            SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
            Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();
            InternalSum internalSum = (InternalSum) aggregationMap.get("sum_" + field);

            return internalSum.getValue();
        } catch (IndexNotFoundException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 获取类型属性
     *
     * @param typeName 类型名称
     * @return 返回属性Map
     * @throws MsOnionException 自定义异常
     */
    public static Map getIndexTypeProperties(String typeName) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);

            GetMappingsResponse getMappingsResponse = getClient().admin().indices().prepareGetMappings(indexName).setTypes(typeName).get();
            ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> mappings = getMappingsResponse.getMappings();
            MappingMetaData mappingMetaData = mappings.get(indexName).get(typeName);

            Map<String, Object> sourceAsMap = mappingMetaData.getSourceAsMap();
            Map<String, Map<String, String>> properties = (Map<String, Map<String, String>>) sourceAsMap.get("properties");
            return properties;
        } catch (IOException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }

    /**
     * 获取资源
     *
     * @param typeName 类型名称
     * @param id       ID
     * @return 返回资源
     * @throws MsOnionException 自定义异常
     */
    public static Map getIndexTypeIdSource(String typeName, String id) throws MsOnionException {
        try {
            String indexName = getIndexName(typeName);
            GetResponse getResponse = getClient().prepareGet(indexName, typeName, id).execute().actionGet();
            Map<String, Object> map = getResponse.getSource();
            return map;
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } finally {
            closeClient();
        }
    }
}
