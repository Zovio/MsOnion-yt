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

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapperWithBLOBs;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.interfaces.redis.clients.jedis.MsOnionJedisAdapter;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseServiceWithBLOBs;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Title: MsOnionServiceWithBLOBs.java
 * @Package: cc.msonion.carambola.parent.interfaces.service
 * @Description: MsOnionServiceWithBLOBs，数据库表中列包括Text、BLOB类型
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午5:46:12
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月17日 下午5:46:12
 * @Modify-version: V2.0.0
 * @Modify-description: 新建
 */

/**
 * @ClassName: MsOnionServiceWithBLOBs
 * @Description: MsOnionServiceWithBLOBs，数据库表中列包括Text、BLOB类型
 * @param <T> 目标POJO
 * @param <E> 目标POJO的Example
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午5:46:12
 */
public abstract class MsOnionServiceWithBLOBs<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter>
        extends MsOnionService<T, E> implements MsOnionBaseServiceWithBLOBs<T, E> {

    /**
     * 默认返回值
     */
    private static final long DEFAULT_RETUAN_LONG_VALUE = -1L;

    /**
     * 默认更新（软删除）的结果
     */
    private static final int DEFAULT_UPDATE_FOR_DELETE_RESULT = 1;

    /**
     * baseMapperWithBLOBs
     */
    @Autowired
    private MsOnionBaseMapperWithBLOBs<T, E> baseMapperWithBLOBs; // 不可以把 private 修改成 protected，不可以直接调用

    /**
     * Redis的Jedis适配器
     */
    @Autowired
    private MsOnionJedisAdapter msOnionJedisAdapter;  // 把 private 修改成 protected

    /**
     * @Fields serialVersionUID : 自动生成serialVersionUID
     */
    private static final long serialVersionUID = 7142547427686183033L;

    /**
     * 根据目标POJO的Example进行查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryByExampleWithBLOBs(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleWithBLOBs(apiVersion, example, this.isDisabledRedisQueryCache());
    }

    /**
     * 根据目标POJO的Example进行更新
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryByExampleWithBLOBs(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleWithBLOBs(apiVersion, example, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example进行查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> queryByExampleWithBLOBs(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleWithBLOBs(apiVersion, null, example, isDisabledRedisQueryCache);
    }

    /**
     * 根据目标POJO的Example进行查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryByExampleWithBLOBs(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleWithBLOBs(apiVersion, parameter, example, this.isDisabledRedisQueryCache());
    }

    /**
     * 根据目标POJO的Example进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryByExampleWithBLOBs(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.queryByExampleWithBLOBs(apiVersion, parameter, example, isDisabledRedisQueryCache, this.getRedisCacheExpire());
    }

    /**
     * 根据目标POJO的Example进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public List<T> queryByExampleWithBLOBs(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 从Redis缓存中查询
            if (!isDisabledRedisQueryCache) {  // 如果没有禁用Redis缓存
                // 获取Key
                String key = this.getRedisKeyGeneratorAdapter().getKey(example);
                if (StringUtils.isNotBlank(key)) {
                    // 从Redis中获取
                    String json = this.msOnionJedisAdapter.get(key);
                    // 解密
                    json = decodeValue(json);
                    Class<T> clazz = (Class<T>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass());
                    if (StringUtils.isNotBlank(json) && null != clazz) {
                        List<T> list = MsOnionJsonUtils.jsonToList(json, clazz);
                        if (MsOnionCollectionUtils.isNotEmpty(list)) {
                            return list; // 从Redis缓存中读取
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }

        // 从数据库中查询
        List<T> list = this.baseMapperWithBLOBs.selectByExampleWithBLOBs(example);

        try {
            if (MsOnionCollectionUtils.isEmpty(list)) {
                return list;
            }
            // 不管是否禁用Redis缓存，查询结果都存储到Redis中
            String json = MsOnionJsonUtils.objectToJson(list);
            // 获取Key
            String key = this.getRedisKeyGeneratorAdapter().getKey(example);
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(json)) {
                // 加密
                json = encodeValue(json);
                this.msOnionJedisAdapter.set(key, json);
                // 设置周期
                this.msOnionJedisAdapter.expire(key, redisCacheExpire);
                // 保存当前Key，为了以后更新缓存使用的
                this.addKey(key, redisCacheExpire);
            }
        } catch (Exception e) {
            this.getMsOnionLogger().error(this.getClass().getName(), e);
        }
        return list;
    }

    /**
     * 根据目标POJO的Example进行更新
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例对象
     * @param example    目标POJO的Example实例对象
     * @return 返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int updateByExampleWithBLOBs(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
        inspectPojoFieldValue(apiVersion, parameter, record);

        // 执行Mapper进行更新
        int result = this.baseMapperWithBLOBs.updateByExampleWithBLOBs(record, example);
        // 更新当前POJO列表，Redis中缓存
        this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, record, example, result);
        // 添加缓存
        // 优化：新增的数据，就不放到Redis缓存中，根据主键查询、条件查询 modify by 2017-07-04 22:30
//        this.addRedisCache(apiVersion, parameter, record, example, result);
        return result;
    }

    /**
     * 根据目标POJO的Example进行更新
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例对象
     * @param example    目标POJO的Example实例对象
     * @return 返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int updateByExampleWithBLOBs(MsOnionApiVersion apiVersion, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.updateByExampleWithBLOBs(apiVersion, null, record, example);
    }

    /**
     * 根据主键idx更新目标POJO
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     记录
     * @return 返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int updateByPrimaryKeyWithBLOBs(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查POJO的字段的值，如果为null，都设置对应类型的默认值
        inspectPojoFieldValue(apiVersion, parameter, record);

        // 执行Mapper进行更新
        int result = this.baseMapperWithBLOBs.updateByPrimaryKeyWithBLOBs(record);
        // 更新当前POJO列表，Redis中缓存
        this.syncPageAndExampleRedisCacheForUpdate(apiVersion, parameter, record, result);
        // 添加缓存
        // 优化：新增的数据，就不放到Redis缓存中，根据主键查询、条件查询 modify by 2017-07-04 22:30
//        this.addRedisCache(apiVersion, parameter, record, result);
        return result;
    }

    /**
     * 根据主键idx更新目标POJO
     *
     * @param apiVersion api版本
     * @param record     记录
     * @return 返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int updateByPrimaryKeyWithBLOBs(MsOnionApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException {
        return this.updateByPrimaryKeyWithBLOBs(apiVersion, null, record);
    }

    //////////////////////////// Getter , Begin //////////////////////////////

    /**
     * 获取BaseMapperWithBLOBs实现
     *
     * @return 返回BaseMapperWithBLOBs实现
     */
    public MsOnionBaseMapperWithBLOBs<T, E> getBaseMapperWithBLOBs() {
        return baseMapperWithBLOBs;
    }


    //////////////////////////// Getter , End //////////////////////////////

    //////////////////////// private addKey , Begin //////////////////////

//    /**
//     * 添加Redis中的Keys的Key，Set集合
//     *
//     * @param key    Redis中的Key
//     * @param expire Redis缓存周期，单位：秒
//     * @return 返回Redis添加操作结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Title: addKey
//     * @Description: 添加Redis中的Keys的Key，Set集合
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date 2017年2月18日 下午2:44:51
//     */
//    protected Long addKey(String key, int expire) throws MsOnionIllegalArgumentException, MsOnionException {
//        if (StringUtils.isBlank(key)) {
//            return DEFAULT_RETUAN_LONG_VALUE;
//        }
//        String key4Keys = getKey();
//        if (StringUtils.isBlank(key4Keys)) {
//            return DEFAULT_RETUAN_LONG_VALUE;
//        }
//        // 加密
//        key = encodeValue(key);
//        // 添加 Set集合Key
//        Long result = this.msOnionJedisAdapter.sadd(key4Keys, key);
//        // 设置过期时间
//        this.msOnionJedisAdapter.expire(key4Keys, expire);
//        return result;
//    }

    /////////////////////// private addKey , End //////////////////////

}
