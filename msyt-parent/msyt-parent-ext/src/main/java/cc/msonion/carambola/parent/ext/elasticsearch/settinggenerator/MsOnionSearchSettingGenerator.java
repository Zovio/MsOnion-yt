package cc.msonion.carambola.parent.ext.elasticsearch.settinggenerator;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.elasticsearch.adapter.MsOnionSearchSettingGeneratorAdapter;
import cc.msonion.carambola.parent.ext.elasticsearch.constants.MsOnionElasticsearchConstants;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.annotation.MsOnionSearch;

/**
 * Created by HeroCao on 2017/6/9.
 */
public class MsOnionSearchSettingGenerator<T extends MsOnionBasePojoAdapter> implements MsOnionSearchSettingGeneratorAdapter {

    /**
     * 目标POJO的Class实例对象
     */
    private Class<T> targetClazz;

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
    @Override
    public String getTargetName() throws MsOnionException {
        return getTargetClazz().getName();
    }

    /**
     * 获取Elasticsearch 索引名称，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引名称，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    @Override
    public String getIndexName() throws MsOnionException {
        String module = null;
        try {
            // 通过 Annotation获取 module
            MsOnionSearch searchAnnotation = getTargetClazz().getAnnotation(MsOnionSearch.class);
            // 获取模块，可能searchAnnotation为 null，但是，这里就是专门用于搜索的，因此，直接报空指针异常！！！
            module = searchAnnotation.module();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        // 如果为null 、 空
        if (MsOnionStringUtils.isBlank(module)) {
            throw new MsOnionException(MsOnionElasticsearchConstants.ES_SEARCH_ANNOTATION_MODULE_ILLEGAL);
        }
        // 去掉左右空格
        return module.trim();
    }

    /**
     * 获取Elasticsearch 索引Type，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引Type，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    @Override
    public String getTypeName() throws MsOnionException {
        return getTargetClazz().getName();
    }

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
    public Class<T> getTargetClazz() throws MsOnionException {
        if (null != this.targetClazz) {
            return this.targetClazz;
        }
        return this.targetClazz = (Class<T>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass());
    }
}
