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

package cc.msonion.carambola.parent.ext.elasticsearch.pojo;

import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionQuerySchemeAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionSearchConditionAdapter;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.List;

/**
 * Elasticsearch 搜索条件
 *
 * @Title: MsOnionSearchCondition.java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.pojo
 * @Description: Elasticsearch 搜索条件
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月6日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月6日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * Elasticsearch 搜索条件
 *
 * @ClassName: MsOnionSearchCondition
 * @Description: Elasticsearch 搜索条件
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月6日 下午1:48:16
 */
public class MsOnionSearchCondition implements MsOnionSearchConditionAdapter {

    /**
     * 分页，从哪条记录开始，从0开始，而不是1开始，0：就是第1页
     */
    private int from;

    /**
     * 分页，每页数量
     */
    private int size;

    /**
     * 是否高亮显示
     */
    private boolean isHighlight;

    /**
     * 高亮字段
     */
    private List<HighlightBuilder.Field> highlightFields;

    /**
     * 排序
     */
    private List<SortBuilder> sortBuilders;

    /**
     * 高亮显示：前缀，可以多个
     */
    private List<String> preTags;

    /**
     * 高亮显示：后缀，可以多个
     */
    private List<String> postTags;

    /**
     * 是否必要字段匹配
     */
    private boolean requireFieldMatch;

    /**
     * 是否按查询匹配度排序
     */
    private boolean explain;

    /**
     * 设置搜索类型
     */
    private SearchType searchType = SearchType.DEFAULT;

    /**
     * 查询方案 集合
     */
    private List<MsOnionQuerySchemeAdapter> querySchemes;

    /////////////////////////  Getters 、 Setters  ### Begin ///////////////////////////////////////////

    /**
     * 分页，from是从0开始，而不是1开始
     * @return 分页，from是从0开始，而不是1开始
     */
    public int getFrom() {
        return from;
    }

    /**
     * 分页，from是从0开始，而不是1开始
     * @param from 从0开始
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * 获取分页，每页记录数
     * @return 每页记录数
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置分页，每页记录数
     * @param size 每页记录数
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 是否高亮
     * @return 是否高亮
     */
    public boolean isHighlight() {
        return isHighlight;
    }

    /**
     * 设置高亮
     * @param highlight 是否高亮
     */
    public void setHighlight(boolean highlight) {
        isHighlight = highlight;
    }

    /**
     * 高亮字段集合
     * @return 高亮字段集合
     */
    public List<HighlightBuilder.Field> getHighlightFields() {
        return highlightFields;
    }

    public void setHighlightFields(List<HighlightBuilder.Field> highlightFields) {
        this.highlightFields = highlightFields;
    }

    public List<SortBuilder> getSortBuilders() {
        return sortBuilders;
    }

    public void setSortBuilders(List<SortBuilder> sortBuilders) {
        this.sortBuilders = sortBuilders;
    }

    public List<String> getPreTags() {
        return preTags;
    }

    public void setPreTags(List<String> preTags) {
        this.preTags = preTags;
    }

    public List<String> getPostTags() {
        return postTags;
    }

    public void setPostTags(List<String> postTags) {
        this.postTags = postTags;
    }

    public boolean isRequireFieldMatch() {
        return requireFieldMatch;
    }

    public void setRequireFieldMatch(boolean requireFieldMatch) {
        this.requireFieldMatch = requireFieldMatch;
    }

    public boolean isExplain() {
        return explain;
    }

    public void setExplain(boolean explain) {
        this.explain = explain;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public List<MsOnionQuerySchemeAdapter> getQuerySchemes() {
        return querySchemes;
    }

    public void setQuerySchemes(List<MsOnionQuerySchemeAdapter> querySchemes) {
        this.querySchemes = querySchemes;
    }


    ////////////////////////  Getters 、 Setters  ### End ///////////////////////////////////////////

}
