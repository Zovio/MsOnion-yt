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

package cc.msonion.carambola.parent.ext.elasticsearch.constants;

/**
 * Elasticsearch 常量
 *
 * @Title: MsOnionElasticsearchConstants.java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.constants
 * @Description: Elasticsearch 常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月5日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

import java.util.Map;

/**
 * Elasticsearch 常量
 *
 * @ClassName: MsOnionElasticsearchConstants
 * @Description: Elasticsearch 常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 */
public final class MsOnionElasticsearchConstants {

    private MsOnionElasticsearchConstants() {

    }

    /**
     * Elasticsearch Settings配置的Key，集群名称：cluster.name
     */
    public static final String ES_SETTINGS_KEY_CLUSTER_NAME = "cluster.name";

    //////////////////  参数校验 ## Begin  ////////////////////////////////

    /**
     * 参数TransportClient非法
     */
    public static final String ES_PARAMETER_TRANSPORTCLIENT_ILLEGAL = "参数TransportClient非法";

    /**
     * 参数index非法
     */
    public static final String ES_PARAMETER_INDEX_ILLEGAL = "参数index非法";

    /**
     * 参数type非法
     */
    public static final String ES_PARAMETER_TYPE_ILLEGAL = "参数type非法";

    /**
     * 参数json非法
     */
    public static final String ES_PARAMETER_JSON_ILLEGAL = "参数json非法";

    /**
     * 参数idx非法
     */
    public static final String ES_PARAMETER_IDX_ILLEGAL = "参数idx非法";

    /**
     * 参数source非法
     */
    public static final String ES_PARAMETER_SOURCE_ILLEGAL = "参数source非法";

    /**
     * 参数name非法
     */
    public static final String ES_PARAMETER_NAME_ILLEGAL = "参数name非法";

    /**
     * 参数text非法
     */
    public static final String ES_PARAMETER_TEXT_ILLEGAL = "参数text非法";

    /**
     * 参数listener非法
     */
    public static final String ES_PARAMETER_LISTENER_ILLEGAL = "参数listener非法";

    /**
     * 参数items非法
     */
    public static final String ES_PARAMETER_ITEMS_ILLEGAL = "参数items非法";

    /**
     * 参数idxes非法
     */
    public static final String ES_PARAMETER_IDXES_ILLEGAL = "参数idxes非法";

    /**
     * 搜索注解module非法
     */
    public static final String ES_SEARCH_ANNOTATION_MODULE_ILLEGAL = "搜索注解module非法";


    //////////////////  参数校验 ## End  ////////////////////////////////

    ////////////////////  分页 ### Begin ////////////////////////////////

    /**
     * 分页，最小 from：10
     */
    public static final int ES_MIN_FROM = 0;

    /**
     * 分页，最大 from：Integer.MAX_VALUE
     */
    public static final int ES_MAX_FROM = Integer.MAX_VALUE / 1000;

    /**
     * 分页，最小 size：5
     */
    public static final int ES_MIN_SIZE = 5;

    /**
     * 分页，最大 size：50
     */
    public static final int ES_MAX_SIZE = 50;

//    /**
//     * 最小 to：10
//     */
//    public static final int ES_MIN_TO = 10;
//
//    /**
//     * 最大 to：Integer.MAX_VALUE
//     */
//    public static final int ES_MAX_TO = Integer.MAX_VALUE / 1000;

    /**
     * ElasticSearch 搜索失败，默认总记录数：0
     */
    public static final int ES_FAILURE_DEFAULT_TOTAL_COUNT = 0;

    /**
     * ElasticSearch 搜索失败，默认 from : 1
     */
    public static final int ES_FAILURE_DEFAULT_FROM = 1;

    /**
     * ElasticSearch 搜索失败，默认 to：5
     */
    public static final int ES_FAILURE_DEFAULT_TO = 1;

    /**
     * ElasticSearch 搜索失败，默认 size : 5
     */
    public static final int ES_FAILURE_DEFAULT_SIZE = 5;

    //////////////////  分页 ### End ////////////////////////////////


    /////////////////////////////// ES 的 index 、type ############## Begin /////////////////////

    /**
     * 添加ES索引失败
     */
    public static final String ES_INDEX_FAILURE = "添加ES索引失败";

    /**
     * 更新ES索引失败
     */
    public static final String ES_UPDATE_FAILURE = "更新ES索引失败";

    /**
     * 删除ES索引失败
     */
    public static final String ES_DELETE_FAILURE = "删除ES索引失败";

    /**
     * 综合操作ES索引失败
     */
    public static final String ES_UPSERT_FAILURE = "更新ES索引失败";

    /**
     * terms query 值的分隔符：,
     */
    public static final String ES_TERMS_QUERY_VALUE_SEPARATOR = ",";

    /**
     * Elasticsearch 搜索失败
     */
    public static final String ES_SEARCH_FAILURE = "搜索失败";

    /////////////////////////////// ES 的 index 、type ############## End /////////////////////


}
