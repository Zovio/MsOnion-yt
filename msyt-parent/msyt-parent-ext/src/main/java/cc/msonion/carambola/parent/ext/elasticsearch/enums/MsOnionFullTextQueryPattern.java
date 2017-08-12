package cc.msonion.carambola.parent.ext.elasticsearch.enums;

/**
 * Created by HeroCao on 2017/6/6.
 */
public enum MsOnionFullTextQueryPattern {

    /**
     * match query：匹配查询
     * <p>用于执行全文查询的标准查询，包括模糊匹配和短语或邻近查询。</p>
     */
    MATCH_QUERY(0),

    /**
     * multi_match query：多匹配查询
     * <p>多字段版本的匹配查询。</p>
     */
    MULTI_MATCH_QUERY(1),

    /**
     * common_terms query：常用术语查询
     * <p>一个更专业的查询，更多的偏好不常见的单词。</p>
     */
    COMMON_TERMS_QUERY(2),

    /**
     * query_string query：查询字符串查询
     * <p>支持紧凑的Lucene查询字符串语法，允许您在单个查询字符串中指定AND | OR | NOT条件和多字段搜索。 仅适用于专家用户。</p>
     */
    QUERY_STRING_QUERY(3),

    /**
     * simple_query_string：简单查询字符串
     * <p>一个更简单，更健壮的查询字符串语法版本，适合直接暴露给用户。</p>
     */
    SIMPLE_QUERY_STRING(4);

    /**
     * 值
     */
    private int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    MsOnionFullTextQueryPattern(int value) {
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
