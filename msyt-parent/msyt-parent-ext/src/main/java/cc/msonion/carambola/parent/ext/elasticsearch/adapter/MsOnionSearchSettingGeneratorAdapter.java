package cc.msonion.carambola.parent.ext.elasticsearch.adapter;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;

import java.io.Serializable;

/**
 * Created by HeroCao on 2017/6/9.
 */
public interface MsOnionSearchSettingGeneratorAdapter<T extends MsOnionBasePojoAdapter> extends Serializable {

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
    String getTargetName() throws MsOnionException;

    /**
     * 获取Elasticsearch 索引名称，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引名称，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    String getIndexName() throws MsOnionException;

    /**
     * 获取Elasticsearch 索引Type，根据当前目标POJO获取
     *
     * @return 返回Elasticsearch 索引Type，根据当前目标POJO获取
     * @throws MsOnionException 异常
     */
    String getTypeName() throws MsOnionException;

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
    Class<T> getTargetClazz() throws MsOnionException;

}
