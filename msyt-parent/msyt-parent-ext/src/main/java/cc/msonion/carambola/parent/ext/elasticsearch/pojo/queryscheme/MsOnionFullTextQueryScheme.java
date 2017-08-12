package cc.msonion.carambola.parent.ext.elasticsearch.pojo.queryscheme;

import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionQuerySchemeAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.enums.MsOnionFullTextQueryPattern;

/**
 * Created by HeroCao on 2017/6/6.
 */
public class MsOnionFullTextQueryScheme implements MsOnionQuerySchemeAdapter {

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private Object value;

    /**
     * 开始
     */
    private Object from;

    /**
     * 结束
     */
    private Object to;

    /**
     * 查询模式
     */
    private MsOnionFullTextQueryPattern queryPattern;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public MsOnionFullTextQueryPattern getQueryPattern() {
        return queryPattern;
    }

    public void setQueryPattern(MsOnionFullTextQueryPattern queryPattern) {
        this.queryPattern = queryPattern;
    }
}
