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
package cc.msonion.carambola.cache.service;

import cc.msonion.carambola.cache.pojo.CacheKey;
import cc.msonion.carambola.cache.pojo.CacheObject;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseCacheService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;

import java.util.List;
/**
 * @Title: CacheService.java
 * @Package: cc.msonion.carambola.cache.service
 * @Description: 缓存中心接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月10日 15:38
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月10日 15:38
 * @Modify-version: V2.0.0
 * @Modify-description: 缓存中心接口
 */
/**
 * @ClassName: CacheService
 * @Description: 缓存中心接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月10日 15:38
 *
 */
public interface CacheService extends MsOnionBaseCacheService {

    /**
     * @param apiVersion api版本
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getAllCacheKeys
     * @Description 查询缓存中心获取所有缓存Key的对象集合
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    MsOnionResult getAllCacheKeys(MsOnionApiVersion apiVersion)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @param apiVersion api版本
     * @param cacheKey 缓存key对象
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 根据Key的路径查找所有KEY的集合
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月12日 下午13:44:20
     */
    MsOnionResult getCacheKeyList(MsOnionApiVersion apiVersion, CacheKey cacheKey)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * @param apiVersion api版本
     * @param cacheObject 缓存对象
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 根据Key查询缓存对象详情
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月12日 下午13:44:20
     */
    MsOnionResult getCacheObjectDetail(MsOnionApiVersion apiVersion, CacheObject cacheObject)
            throws MsOnionIllegalArgumentException, MsOnionException;
    /**
     * @param apiVersion api版本
     * @param cacheKeyList 指定需要删除的缓存key对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 根据键删除缓存对象
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    MsOnionResult deleteCacheByKeys(MsOnionApiVersion apiVersion, List<CacheKey> cacheKeyList)
            throws MsOnionIllegalArgumentException, MsOnionException;



    /**
     * @param apiVersion api版本
     * @param cacheKeyList 指定需要删除的缓存树的节点对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByNodes
     * @Description 根据键删除缓存对象
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    MsOnionResult deleteCacheByNodes(MsOnionApiVersion apiVersion, List<CacheKey> cacheKeyList)
            throws MsOnionIllegalArgumentException, MsOnionException;
}
