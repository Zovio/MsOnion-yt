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
package cc.msonion.carambola.parent.interfaces.service;

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseErpService;
/**
 * @Title: MsOnionErpService
 * @Package: cc.msonion.carambola.parent.interfaces.service
 * @Description: 提供ERP操作相关接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:28:01
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年06月06日 20:28:01
 * @Modify-version: 2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: MsOnionErpService
 * @Description: 提供ERP操作相关接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年06月06日 20:28:01
 */
public abstract class MsOnionErpService extends MsOnionCacheService implements MsOnionBaseErpService {
//    /**
//     * erp keys set集合的键
//     */
//    private static final String DEFAULT_KEY_4_KEYS = "ERP_INFO:KEYS:ERP";

    /**
     * erp keys set集合的键
     */
    private static final String DEFAULT_KEY_4_KEYS = "ERP:KEYS";

    /**
     * erp 需要添加的键值对的前缀，默认添加
     */
    private static final String DEFAULT_KEY_PREFIX = "ERP:";


    /**
     * 添加键值对到redis
     * @param key key
     * @param value value
     * @param redisCacheExpire 失效时间
     * @return 返回操作结果
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException MsOnionException
     */
    @Deprecated
    protected String setForRedis(String key, String value, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException  {
        inspectParametersForRedisKey(key);
        key = DEFAULT_KEY_PREFIX + key;
        // 设置缓存
        String result = getMsOnionJedisAdapter().set(key, value);
        // 不设置缓存周期
        // 保存当前Key，为了以后更新缓存使用的，这里没有缓存周期参数，只能使用全局周期
        this.addKey(key, redisCacheExpire);
        return result;
    }

    /**
     * 从redis中获取值
     * @param key key
     * @return 值
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException MsOnionException
     */
    @Deprecated
    protected String getForRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException  {
        inspectParametersForRedisKey(key);
        key = DEFAULT_KEY_PREFIX + key;
        return getMsOnionJedisAdapter().get(key);
    }

    /**
     * 参数校验
     * @param key key
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException MsOnionException
     */
    @Deprecated
    protected  void inspectParametersForRedisKey(String key) throws MsOnionIllegalArgumentException, MsOnionException {

        if (MsOnionStringUtils.isBlank(key)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 添加键到keys
     * @param key key
     * @param expire expire
     * @return 操作结果
     * @throws MsOnionIllegalArgumentException MsOnionIllegalArgumentException
     * @throws MsOnionException MsOnionException
     */
    @Deprecated
    private Long addKey(String key, int expire) throws MsOnionIllegalArgumentException, MsOnionException {
        // 加密
        key =  MsOnionSecurityUtils.encodeForRedis(key);
        // 添加 Set集合Key
        Long result = getMsOnionJedisAdapter().sadd(DEFAULT_KEY_4_KEYS, key);
        // 设置过期时间
        getMsOnionJedisAdapter().expire(DEFAULT_KEY_4_KEYS, expire);
        return result;
    }



}
