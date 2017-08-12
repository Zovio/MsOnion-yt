package test.msonion.carambola.search.service.collector;

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.parent.ext.elasticsearch.service.base.MsOnionElasticsearchBaseService;
import test.msonion.carambola.pojo.TestCollectorItem;

/**
 * Created by HeroCao on 2017/6/10.
 * 搜索中心的所有 Service，命名规则： xxxSearchService ， 必须包括 SearchService ！！！
 */
public interface TestCollectorItemSearchService extends MsOnionElasticsearchBaseService<TestCollectorItem> {


}
