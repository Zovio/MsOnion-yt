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
package cc.msonion.carambola.search.service.impl;

/**
 * @Title: CollectorSearchServiceImpl.java
 * @Package: cc.msonion.carambola.service.impl
 * @Description: 收集器搜索服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/4 14:01
 * @Version: V2.0.0
 * @Modify-by: JohnnyWoo johnny-woo@msyc.cc
 * @Modify-date: 2017/5/4 14:01
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.search.common.utils.ElasticsearchUtils;
import cc.msonion.carambola.search.service.CollectorSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: CollectorSearchServiceImpl
 * @Description: 收集器搜索服务实现类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: JohnnyWoo johnny-woo@msyc.cc
 * @Date: 2017/5/4 14:01
 */
@Service
public class CollectorSearchServiceImpl implements CollectorSearchService {
    /**
     * 查询索引
     *
     * @param typeName    类型名称
     * @param inputFields 录入条件
     * @param orderBy     排序字段
     * @param pageIndex   当前所在页
     * @param pageSize    每页显示大小
     * @return 返回搜索结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public Map<String, Object> performIndexGridSearch(String typeName, Map inputFields, String orderBy, int pageIndex, int pageSize)
            throws MsOnionIllegalArgumentException, MsOnionException {
        List list = null;
        int totalSize = 0;
        try {
            String indexName = ElasticsearchUtils.getIndexName(typeName);
            BoolQueryBuilder queryBuilder = ElasticsearchUtils.createConditionFromInputFields(inputFields);
            orderBy = ElasticsearchUtils.prepareOrderByParameters(inputFields, orderBy);

            int indexFrom = pageIndex * pageSize;
            list = new ArrayList<>();

            TransportClient client = ElasticsearchUtils.getClient();
            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName)
                    .setTypes(typeName)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(queryBuilder)
                    .setFrom(indexFrom)
                    .setSize(pageSize);

            if (StringUtils.isNotBlank(orderBy)) {
                if (orderBy.contains("|")) {
                    String[] orderByList = orderBy.split("\\|");

                    for (String tempOrderBy : orderByList) {
                        SortOrder order = tempOrderBy.startsWith("-") ? SortOrder.DESC : SortOrder.ASC;
                        tempOrderBy = tempOrderBy.startsWith("-") ? tempOrderBy.substring(1) : tempOrderBy;

                        searchRequestBuilder.addSort(tempOrderBy, order);
                    }
                } else {
                    SortOrder order = orderBy.startsWith("-") ? SortOrder.DESC : SortOrder.ASC;
                    orderBy = orderBy.startsWith("-") ? orderBy.substring(1) : orderBy;

                    searchRequestBuilder.addSort(orderBy, order);
                }
            }

            SearchResponse response = searchRequestBuilder.execute().actionGet();
            SearchHits hits = response.getHits();
            totalSize = Integer.parseInt(String.valueOf(hits.totalHits()));

            Iterator<SearchHit> hitsItr = hits.iterator();
            while (hitsItr.hasNext()) {
                SearchHit hit = hitsItr.next();
                Map<String, Object> source = hit.getSource();

                list.add(source);
            }
        } catch (MsOnionException e) {
            throw new MsOnionException(e);
        } catch (NumberFormatException e) {
            throw new MsOnionException(e);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        result.put("list", list);
        result.put("listSize", totalSize);
        return result;
    }

    /**
     * 新增索引
     *
     * @param typeName 类型名称
     * @param id       ID
     * @param source   资源
     * @return 返回新增结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult addIndex(String typeName, String id, Map source) throws MsOnionIllegalArgumentException, MsOnionException {
        boolean result = ElasticsearchUtils.prepareIndexSetIdAndSource(typeName, id, source);
        if (result) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_SAVE_SUCCESS);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_SAVE_FAILURE);
    }

    /**
     * 删除索引
     *
     * @param typeName 类型名称
     * @param id       ID
     * @return 返回删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult deleteIndex(String typeName, String id) throws MsOnionIllegalArgumentException, MsOnionException {
        boolean result = ElasticsearchUtils.prepareDeleteIndexTypeId(typeName, id);
        if (result) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_DELETE_SUCCESS);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_DELETE_FAILURE);
    }

    /**
     * 更新索引
     *
     * @param typeName 类型名称
     * @param id       ID
     * @param source   资源
     * @return 返回更新结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                自定义异常
     */
    @Override
    public MsOnionResult updateIndex(String typeName, String id, Map source) throws MsOnionIllegalArgumentException, MsOnionException {
        boolean result = ElasticsearchUtils.prepareIndexSetIdAndSource(typeName, id, source);
        if (result) {
            return MsOnionResult.build(MsOnionStatusConstants.STATUS_200, MsOnionMessageConstants.MESSAGE_UPDATE_SUCCESS);
        }
        return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, MsOnionMessageConstants.MESSAGE_UPDATE_FAILURE);
    }
}
