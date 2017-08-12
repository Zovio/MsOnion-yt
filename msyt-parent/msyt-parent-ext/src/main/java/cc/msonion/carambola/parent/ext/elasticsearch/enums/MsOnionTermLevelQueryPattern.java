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

package cc.msonion.carambola.parent.ext.elasticsearch.enums;

/**
 * Elasticsearch Term level 查询模式
 * @Title: .java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.enums
 * @Description: Elasticsearch 
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
 * Elasticsearch Term level 查询模式
 * @ClassName: 
 * @Description: Elasticsearch 
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月6日 下午1:48:16
 */
public enum MsOnionTermLevelQueryPattern {

    /**
     * term query ：期限查询
     * <p>查找包含指定字段中指定的具体术语的文档。</p>
     */
    TERM_QUERY(0),

    /**
     * terms query: 期限查询
     * <p>查找包含指定字段中指定的任何确切术语的文档。</p>
     */
    TERMS_QUERY(1),

    /**
     * range query：范围查询
     * <p>找到指定的字段包含指定范围内的值（日期，数字或字符串）的文档。</p>
     */
    RANGE_QUERY(2),

    /**
     * exists query：存在查询
     * <p>查找指定的字段包含任何非空值的文档。</p>
     */
    EXISTS_QUERY(3),

    /**
     * prefix query：前缀查询
     * <p>查找指定字段的文档包含具有指定的确切前缀的术语。</p>
     */
    PREFIX_QUERY(4),

    /**
     * Wildcard Query：通配符查询
     * <p>查找指定字段的文档包含与指定的模式匹配的字词，其中模式支持单字符通配符（?）和多字符通配符（*）</p>
     */
    WILDCARD_QUERY(5),

    /**
     * regexp query：正则表达式匹配查询
     * <p>查找指定字段的文档包含与指定的正则表达式匹配的术语。</p>
     */
    REGEXP_QUERY(6),

    /**
     * fuzzy query：模糊查询
     * <p>查找指定字段的文档包含与指定术语模糊相似的术语。 模糊性测量为Levenshtein编辑距离为1或2。</p>
     */
    FUZZY_QUERY(7),

    /**
     * type query：type查询
     * <p>查找指定类型的文档。</p>
     */
    TYPE_QUERY(8),

    /**
     * ids query：IDs 查询
     * <p>查找具有指定类型和ID的文档。</p>
     */
    IDS_QUERY(9);

    /**
     * 值
     */
    private int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    MsOnionTermLevelQueryPattern(int value) {
        this.value = value;
    }

    /**
     * 获取值
     *
     * @return 返回值
     */
    public int getValue() {
        return value;
    }

}
