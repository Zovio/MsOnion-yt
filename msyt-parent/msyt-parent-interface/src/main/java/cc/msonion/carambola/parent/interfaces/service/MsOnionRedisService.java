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

/**
 * @Title: MsOnionRedisService.java
 * @Package: cc.msonion.carambola.parent.interfaces.service.base
 * @Description: MsOnionRedisService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月14日 下午3:34:54
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月14日 下午3:34:54
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：ApiVersion参数、MSOnionParameter参数，以及调整相关方法
 */

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.interfaces.log.adapter.MsOnionLoggerAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.interfaces.redis.clients.jedis.MsOnionJedisAdapter;
import cc.msonion.carambola.parent.interfaces.redis.key.MsOnionRedisKeyGeneratorAdapter;
import cc.msonion.carambola.parent.interfaces.service.base.MsOnionBaseRedisService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.Set;

/**
 * Redis缓存Service，提供Redis缓存操作相关接口
 *
 * @param <T> 目标Pojo，例如：Item
 * @param <E> 目标Pojo的 Example，例如：ItemExample
 * @ClassName: MsOnionRedisService
 * @Description: MsOnionRedisService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月14日 下午3:34:54
 */
public abstract class MsOnionRedisService<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter>
        implements MsOnionBaseRedisService<T, E> {

    /**
     * 默认返回值
     */
    public static final long DEFAULT_RETUAN_LONG_VALUE = -1L;

    /**
     * Redis的Jedis适配器
     */
    @Autowired
    private MsOnionJedisAdapter msOnionJedisAdapter;

    /**
     * MsOnion日志
     */
    @Autowired
    private MsOnionLoggerAdapter msOnionLogger;

    /**
     * 开发、测试、稳定、预发布、生产（线上）环境的域名
     */
    @Autowired
    private MsOnionDomain msOnionDomain;

    /**
     * @Fields redis_cache_expire : Redis全局缓存周期
     */
    @Value("${msonion_global_redis_cache_expire}")
    private int redisCacheExpire;

    /**
     * Redis的Key生成器适配器
     */
    @Autowired
    private MsOnionRedisKeyGeneratorAdapter<T, E> redisKeyGeneratorAdapter;

    ///////////////// copy from MsOnionService ### Begin //////////////////////

    /**
     * MsOnionRedisService 当前实现类的实例对象，方便于多线程中获取当前this
     */
    private MsOnionRedisService msOnionRedisService = this;

    /**
     * 目标POJO的Class实例对象
     */
    private Class<T> targetClazz;

    /**
     * 目标POJO的EXample的Class实例对象
     */
    private Class<E> exampleClazz;

    ///////////////// copy from MsOnionService ### End //////////////////////

    //////////////////////// Setters、Getters ##### Begin /////////////////////////

    /**
     * 日志
     *
     * @return 日志
     */
    public MsOnionLoggerAdapter getMsOnionLogger() {
        return msOnionLogger;
    }

    /**
     * 开发、测试、稳定、预发布、生产（线上）环境的域名
     *
     * @return 开发、测试、稳定、预发布、生产（线上）环境的域名
     */
    public MsOnionDomain getMsOnionDomain() {
        return msOnionDomain;
    }

    /**
     * Redis全局缓存周期
     *
     * @return Redis全局缓存周期
     */
    public int getRedisCacheExpire() {
        return redisCacheExpire;
    }

    /**
     * Redis的Key生成器适配器
     *
     * @return Redis的Key生成器适配器
     */
    public MsOnionRedisKeyGeneratorAdapter<T, E> getRedisKeyGeneratorAdapter() {
        return redisKeyGeneratorAdapter;
    }

    //////////////////////// Setters、Getters ##### End  /////////////////////////

    //////////////////////// Redis缓存操作 ##### Begin  /////////////////////////

    ////////////////////// ############## copy from MsOnionServive ### Begin ////////////

    /**
     * 获取目标POJO的Class
     *
     * @return 返回目标POJO的Class
     * @throws MsOnionException 异常
     * @Title: getTargetClazz
     * @Description: 获取目标POJO的Class
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:31:10
     */
    @SuppressWarnings("unchecked")
    public Class<T> getTargetClazz() throws MsOnionException {
        if (null != this.targetClazz) {
            return this.targetClazz;
        }
        return this.targetClazz = (Class<T>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass());
    }

    /**
     * 获取目标POJO的Example的Class
     *
     * @return 返回目标POJO的Example的Class
     * @throws MsOnionException 异常
     * @Title: getExampleClazz
     * @Description: 获取目标POJO的Example的Class
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:33:02
     */
    @SuppressWarnings("unchecked")
    public Class<E> getExampleClazz() throws MsOnionException {
        if (null != this.exampleClazz) {
            return this.exampleClazz;
        }
        return this.exampleClazz = (Class<E>) MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), 1);
    }

    /**
     * 获取KEYS的名称，例如：KEYS
     *
     * @return 返回KEYS的名称，例如：KEYS
     * @throws MsOnionException 异常
     * @Title: getKeysName
     * @Description: 获取KEYS的名称，例如：KEYS
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:56:24
     */
    public String getKeysName() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getKeysName();
    }

    /**
     * 返回KEYS的标识，例如：:KEYS
     *
     * @return 返回KEYS的标识，例如：:KEYS
     * @throws MsOnionException 异常
     * @Title: getKeysIdentification
     * @Description: 获取KEYS的标识，例如：:KEYS
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:56:41
     */
    public String getKeysIdentification() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getKeysIdentification();
    }

    /**
     * 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     *
     * @return 目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     * @throws MsOnionException 异常
     * @Title: getTargetName
     * @Description: 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:42:52
     */
    public String getTargetName() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getTargetName();
    }

    /**
     * 取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     *
     * @return 目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     * @throws MsOnionException 异常
     * @Title: getTargetIdentification
     * @Description: 获取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:43:41
     */
    public String getTargetIdentification() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getTargetIdentification();
    }

    /**
     * 获取前缀，例如：_INFO
     *
     * @return 返回前缀，例如：_INFO
     * @throws MsOnionException 异常
     * @Title: getPrefix
     * @Description: 获取前缀，例如：_INFO
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月14日 下午8:33:02
     */
    public String getPrefix() throws MsOnionException {
        return this.redisKeyGeneratorAdapter.getPrefix();
    }

    /**
     * 获取Example名称，例如：ITEMEXAMPLE
     *
     * @return 返回Example名称，例如：ITEMEXAMPLE
     * @throws MsOnionException 异常
     * @Title: getExampleName
     * @Description: 获取Example名称，例如：ITEMEXAMPLE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:47:49
     */
    public String getExampleName() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getExampleName();
    }

    /**
     * 获取Example标识，例如：:ITEMEXAMPLE:
     *
     * @return 返回Example标识，例如：:ITEMEXAMPLE:
     * @throws MsOnionException 异常
     * @Title: getExampleIdentification
     * @Description: 获取Example标识，例如：:ITEMEXAMPLE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:48:13
     */
    public String getExampleIdentification() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getExampleIdentification();
    }

    /**
     * 获取单一Example名称，例如：EXAMPLE
     *
     * @return 返回单一Example名称，例如：EXAMPLE
     * @throws MsOnionException 异常
     * @Title: getSingleExampleName
     * @Description: 获取单一Example名称，例如：EXAMPLE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:41:55
     */
    public String getSingleExampleName() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getSingleExampleName();
    }

    /**
     * 获取单一Example标识，例如：:EXAMPLE:
     *
     * @return 返回单一Example标识，例如：:EXAMPLE:
     * @throws MsOnionException 异常
     * @Title: getSingleExampleIdentification
     * @Description: 获取单一Example标识，例如：:EXAMPLE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:43:32
     */
    public String getSingleExampleIdentification() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getSingleExampleIdentification();
    }

    /**
     * query one 的标识， 例如：  :ONE:
     *
     * @return 返回 query one 的标识， 例如：  :ONE:
     * @throws MsOnionException 异常
     * @Title: getOneIdentification
     * @Description: 获取One标识，query one使用，例如：:ONE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年07月05日 上午11:43:32
     */
    public String getOneIdentification() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getOneIdentification();
    }

    /**
     * 获取PAGE名称，例如：PAGE（分页）
     *
     * @return 返回PAGE名称，例如：PAGE（分页）
     * @throws MsOnionException 异常
     * @Title: getPageName
     * @Description: 获取PAGE名称，例如：PAGE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:50:34
     */
    public String getPageName() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getPageName();
    }

    /**
     * 获取PAGE的标识，例如：:PAGE: （分页）
     *
     * @return 获取PAGE的标识，例如：:PAGE:
     * @throws MsOnionException 异常
     * @Title: getPageIdentification
     * @Description: 获取PAGE的标识，例如：:PAGE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:51:55
     */
    public String getPageIdentification() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getPageIdentification();
    }

    /**
     * 获取Session标识，例如：SESSION:
     *
     * @return 返回Session标识，例如：SESSION:
     * @throws MsOnionException 异常
     * @Title: getSessionIdentification
     * @Description: 获取Session标识，例如：SESSION:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月14日 上午10:43:41
     */
    public String getSessionIdentification() throws MsOnionException {
        return this.redisKeyGeneratorAdapter.getSessionIdentification();
    }

    /**
     * 获取Count标识，例如：COUNT:
     *
     * @return 返回Count标识，例如：COUNT:
     * @throws MsOnionException 异常
     * @Title: getCountIdentification
     * @Description: 获取Count标识，例如：COUNT:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月15日 上午10:43:41
     */
    public String getCountIdentification() throws MsOnionException {
        // 没有前面的“:”
        return this.redisKeyGeneratorAdapter.getCountIdentification();
    }

    /**
     * 加密目标POJO
     *
     * @param record 目标POJO实例对象
     * @return 返回加密之后数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: encodeValue
     * @Description:加密目标POJO
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午3:37:16
     */
    protected String encodeValue(T record) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == record) {
            return null;
        }
        String json = MsOnionJsonUtils.objectToJson(record);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return MsOnionSecurityUtils.encodeForRedis(json);
    }

    /**
     * 加密Value
     *
     * @param value 需要加密数据
     * @return 返回加密之后数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: encodeValue 加密
     * @Description: 加密Value
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午3:48:20
     */
    protected String encodeValue(String value) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return MsOnionSecurityUtils.encodeForRedis(value);
    }

    /**
     * 解密
     *
     * @param encryptText 加密Text
     * @return
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: decodeValue
     * @Description: 解密Value
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午3:41:17
     */
    protected String decodeValue(String encryptText) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        }
        return MsOnionSecurityUtils.decodeForRedis(encryptText);
    }

    /**
     * @return 返回Redis中Keys的Key，更新Redis缓存需要使用到，Set集合
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中Keys的Key，更新Redis缓存需要使用到，Set集合
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午2:01:47
     */
    protected String getKey() throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getKey();
    }

    /**
     * 获取Redis中当前所有Key对应的Keys，更新Redis缓存需要使用到
     *
     * @return 返回Redis中当前所有Key对应的Keys，更新Redis缓存需要使用到
     * @throws MsOnionException 异常
     * @Title: getSetKeysValues
     * @Description: 获取Redis中当前所有Key对应的Keys，更新Redis缓存需要使用到
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午4:07:07
     */
    protected Set<String> getSetKeysValues() throws MsOnionException {
        String key4Keys = getKey();
        if (StringUtils.isBlank(key4Keys)) {
            return null;
        }
        return this.msOnionJedisAdapter.smembers(key4Keys);
    }

    /**
     * 获取Example的Redis中的Key
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回Example的Redis中的Key
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Example的Redis中的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:08:59
     */
    protected String getKey(E example) throws MsOnionException {
        return this.getRedisKeyGeneratorAdapter().getKey(example);
    }

    /**
     * 添加Redis中的Keys的Key，Set集合
     *
     * @param key    Redis中的Key
     * @param expire Redis缓存周期，单位：秒
     * @return 返回Redis添加操作结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: addKey
     * @Description: 添加Redis中的Keys的Key，Set集合
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午2:44:51
     */
    protected Long addKey(String key, int expire) throws MsOnionIllegalArgumentException, MsOnionException {
        if (StringUtils.isBlank(key)) {
            return DEFAULT_RETUAN_LONG_VALUE;
        }
        String key4Keys = getKey();
        if (StringUtils.isBlank(key4Keys)) {
            return DEFAULT_RETUAN_LONG_VALUE;
        }
        // 加密
        key = encodeValue(key);
        // 添加 Set集合Key
        Long result = this.msOnionJedisAdapter.sadd(key4Keys, key);
        // 设置过期时间
        this.msOnionJedisAdapter.expire(key4Keys, expire);
        return result;
    }

    /**
     * 删除Redis中的Keys的Key，Set集合
     *
     * @param apiVersion 版本号
     * @param key        Redis缓存中的Key
     * @return 返回Redis删除操作结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: deleteKey
     * @Description: 删除Redis中的Keys的Key，Set集合
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年2月18日 下午3:10:37
     */
    protected Long deleteKey(MsOnionApiVersion apiVersion, String key) throws MsOnionIllegalArgumentException, MsOnionException {
        String key4Keys = getKey();
        // 打印日志
        this.msOnionLogger.debug(getClass().getName(),
                String.format("deleteKey # 删除Set集合中成员(key)，明文key=%s，key4Keys=%s",
                        key, key4Keys));
        // 加密
        key = encodeValue(key);
        if (StringUtils.isBlank(key) || StringUtils.isBlank(key4Keys)) {
            return DEFAULT_RETUAN_LONG_VALUE;
        }

        // 打印日志
        this.msOnionLogger.debug(getClass().getName(),
                String.format("deleteKey # 删除Set集合中成员(key)，加密key=%s，key4Keys=%s",
                        key, key4Keys));
        return this.msOnionJedisAdapter.srem(key4Keys, key);
    }


    ////////////////////// ############## copy from MsOnionServive ### End ////////////



    //////////////////////////////// 提供简单Redis缓存操作，Begin /////////////////////////////////////////////////////

    ////////////////// String字符串类型操作，Begin ///////////

    /**
     * 将数据缓存到Redis中
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key     Redis缓存的Key
     * @param value   Redis缓存中的数据
     * @param seconds Redis缓存周期，单位：秒
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedis(String key, String value, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return setForRedis(null, key, value, seconds);
    }

    /**
     * 将数据缓存到Redis中
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis缓存的Key
     * @param value     Redis缓存中的数据
     * @param seconds   Redis缓存周期，单位：秒
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedis(MsOnionParameterAdapter parameter, String key, String value,
                              int seconds) throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);

        if (StringUtils.isBlank(value)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_VALUE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        if (seconds <= MsOnionConstants.REDIS_EXPIRE_MIN_VALUE || seconds >= MsOnionConstants.REDIS_EXPIRE_MAX_VALUE) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_SECONDS_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        try {
            value = value.trim();
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("setForRedis # 设置Redis缓存 # key=%s，value=%s，seconds=%s",
                            key, value, seconds));

            // 设置缓存
            String result = this.msOnionJedisAdapter.set(key, value);
            // 设置缓存周期
            this.msOnionJedisAdapter.expire(key, seconds);
            // 保存当前Key，为了以后更新缓存使用的
            this.addKey(key, seconds);
            return result;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将数据缓存到Redis中
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key   Redis缓存的Key
     * @param value Redis缓存中的数据
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedis(String key, String value) throws MsOnionIllegalArgumentException, MsOnionException {
        return setForRedis(null, key, value);
    }

    /**
     * 将数据缓存到Redis中
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis缓存的Key
     * @param value     Redis缓存中的数据
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedis(MsOnionParameterAdapter parameter, String key, String value)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        try {
            if (StringUtils.isBlank(value)) {
                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_VALUE_ILLEGAL,
                        MsOnionStatusConstants.STATUS_400);
            }

            value = value.trim();
            this.msOnionLogger.debug(getClass().getName(),
                    String.format("setForRedis # 设置Redis缓存 # key=%s，value=%s",
                            key, value));

            // 设置缓存
            String result = this.msOnionJedisAdapter.set(key, value);
            // 不设置缓存周期
            // 保存当前Key，为了以后更新缓存使用的，这里没有缓存周期参数，只能使用全局周期
            this.addKey(key, this.redisCacheExpire);
            return result;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>设置key的过期时间，超过时间后，将会自动删除该key。
     * 在Redis的术语中一个key的相关超时是不确定的。
     * 超时后只有对key执行DEL命令或者SET命令或者GETSET时才会清除。
     * 这意味着，从概念上讲所有改变key的值的操作都会使他清除。
     * 例如，INCR递增key的值，执行LPUSH操作，或者用HSET改变hash的field所有这些操作都会触发删除动作。
     * 使用PERSIST命令可以清除超时，使其变成一个永久的key。
     * 如果key被RENAME命令修改，相关的超时时间会转移到新key上面。
     * 如果key被RENAME命令修改，比如原来就存在Key_A,然后调用RENAME Key_B Key_A命令，
     * 这时不管原来Key_A是永久的还是设置为超时的，都会由Key_B的有效期状态覆盖。
     * 刷新过期时间
     * 对已经有过期时间的key执行EXPIRE操作，将会更新它的过期时间。
     * 有很多应用有这种业务场景，例如记录会话的session。</p>
     *
     * @param key     Redis的Key
     * @param seconds Redis缓存周期，单位：秒
     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Long expireForRedis(String key, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return expireForRedis(null, key, seconds);
    }

    /**
     * <p>设置key的过期时间，超过时间后，将会自动删除该key。
     * 在Redis的术语中一个key的相关超时是不确定的。
     * 超时后只有对key执行DEL命令或者SET命令或者GETSET时才会清除。
     * 这意味着，从概念上讲所有改变key的值的操作都会使他清除。
     * 例如，INCR递增key的值，执行LPUSH操作，或者用HSET改变hash的field所有这些操作都会触发删除动作。
     * 使用PERSIST命令可以清除超时，使其变成一个永久的key。
     * 如果key被RENAME命令修改，相关的超时时间会转移到新key上面。
     * 如果key被RENAME命令修改，比如原来就存在Key_A,然后调用RENAME Key_B Key_A命令，
     * 这时不管原来Key_A是永久的还是设置为超时的，都会由Key_B的有效期状态覆盖。
     * 刷新过期时间
     * 对已经有过期时间的key执行EXPIRE操作，将会更新它的过期时间。
     * 有很多应用有这种业务场景，例如记录会话的session。</p>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @param seconds   Redis缓存周期，单位：秒
     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Long expireForRedis(MsOnionParameterAdapter parameter, String key, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        if (seconds <= MsOnionConstants.REDIS_EXPIRE_MIN_VALUE || seconds >= MsOnionConstants.REDIS_EXPIRE_MAX_VALUE) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_SECONDS_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }

        try {
            return this.msOnionJedisAdapter.expire(key, seconds);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 删除Redis中的缓存
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key Redis缓存的Key
     * @return 返回删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public Long delKeyForRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return delKeyForRedis(null, key);
    }

    /**
     * 删除Redis中的缓存
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis缓存的Key
     * @return 返回删除结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public Long delKeyForRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);

        try {
            return this.msOnionJedisAdapter.del(key);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取Redis缓存中数据
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key Redis的Key
     * @return 返回Redis缓存中数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    public String getFromRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException {
        return getFromRedis(null, key);
    }

    /**
     * 获取Redis缓存中数据
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @return 返回Redis缓存中数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String getFromRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        try {
            return this.msOnionJedisAdapter.get(key);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////// String字符串类型操作，End ///////////


    ////////////////// Set类型操作，Begin ///////////
    ////// Redis set对外提供功能与list类似，是一个列表的功能，特殊之处在于set是可以自动排重的，不会出现重复的数据！！！

    /**
     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
     * 如果key 的类型不是集合则返回错误。</p>
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key    Redis的Key
     * @param member 添加到Set集合中的成员数据
     * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素。
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Long saddForRedis(String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return saddForRedis(null, key, member);
    }

    /**
     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
     * 如果key 的类型不是集合则返回错误。</p>
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @param member    添加到Set集合中的成员数据
     * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素。
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Long saddForRedis(MsOnionParameterAdapter parameter, String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        inspectParameterForRedisMember(member);
        try {
            return this.msOnionJedisAdapter.sadd(key);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 删除并获取Redis中一个集合里面的元素
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key Redis的Key
     * @return 返回删除之后集合中原始
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public String spopForRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return spopForRedis(null, key);
    }

    /**
     * 删除并获取Redis中一个集合里面的元素
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @return 返回删除之后集合中原始
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public String spopForRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        try {
            return this.msOnionJedisAdapter.spop(key);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取key的Set集合所有的元素
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key Redis的Key
     * @return 返回key的Set集合所有的元素
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Set<String> smembersForRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return smembersForRedis(null, key);
    }

    /**
     * 获取key的Set集合所有的元素
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @return 返回key的Set集合所有的元素
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Set<String> smembersForRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        try {
            return this.msOnionJedisAdapter.smembers(key);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
     * 如果member元素是集合key的成员，则返回1
     * 如果member元素不是key的成员，或者集合key不存在，则返回0
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key    Redis的Key
     * @param member 成员
     * @return 返回Set集合中成员 member 是否是存储的集合 key的成员
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Boolean sismemberForRedis(String key, String member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return sismemberForRedis(null, key, member);
    }

    /**
     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
     * 如果member元素是集合key的成员，则返回1
     * 如果member元素不是key的成员，或者集合key不存在，则返回0
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @param member    成员
     * @return 返回Set集合中成员 member 是否是存储的集合 key的成员
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Boolean sismemberForRedis(MsOnionParameterAdapter parameter, String key, String member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        if (StringUtils.isBlank(member)) {
            return false;
        }
        member = member.trim();
        try {
            return this.msOnionJedisAdapter.sismember(key, member);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////// Set类型操作，End ///////////

    /**
     * Redis缓存中key是否存在
     * <p>1：key存在</p>
     * <p>0：key不存在</p>
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key Redis的Key
     * @return 返回1 如果key存在，0 如果key不存在
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Boolean existsKeyFromRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return existsKeyFromRedis(null, key);
    }

    /**
     * Redis缓存中key是否存在
     * <p>1：key存在</p>
     * <p>0：key不存在</p>
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @return 返回1 如果key存在，0 如果key不存在
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Boolean existsKeyFromRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        inspectParametersForRedisKey(parameter, key);
        try {
            return this.msOnionJedisAdapter.exists(key);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>在key集合中移除指定的元素，
     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
     * 如果key的类型不是一个集合,则返回错误。</p>
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key    Redis中的Key
     * @param member 需要从Redis的Set集合中删除的成员
     * @return 从集合中移除元素的个数，不包括不存在的成员
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Long sremForRedis(String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return sremForRedis(null, key, member);
    }

    /**
     * <p>在key集合中移除指定的元素，
     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
     * 如果key的类型不是一个集合,则返回错误。</p>
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis中的Key
     * @param member    需要从Redis的Set集合中删除的成员
     * @return 从集合中移除元素的个数，不包括不存在的成员
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    @Override
    public Long sremForRedis(MsOnionParameterAdapter parameter, String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        inspectParameterForRedisMember(member);
        try {
            return this.msOnionJedisAdapter.srem(key, member);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    //    /**
//     * <p>从 key 指定的哈希集中移除指定的域。在哈希集中不存在的域将被忽略。</p>
//     * <p>如果 key 指定的哈希集不存在，它将被认为是一个空的哈希集，该命令将返回0。</p>
//     *
//     * @param key   Redis的Key
//     * @param field 哈希集中的域
//     * @return 返回删除结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    public Long hdelRedisKey(String key, String... field)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return hdelRedisKey(null, key, field);
//    }
//
//    /**
//     * <p>从 key 指定的哈希集中移除指定的域。在哈希集中不存在的域将被忽略。</p>
//     * <p>如果 key 指定的哈希集不存在，它将被认为是一个空的哈希集，该命令将返回0。</p>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param field     哈希集中的域
//     * @return 返回删除结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    public Long hdelRedisKey(MsOnionParameterAdapter parameter, String key, String... field)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key, field);
//        try {
//            return this.msOnionJedisAdapter.hdel(key, field);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }
//
//    /**
//     * <p>设置 key 指定的哈希集中指定字段的值。该命令将重写所有在哈希集中存在的字段。</p>
//     * <p>如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联</p>
//     *
//     * @param key  Redis中Key
//     * @param hash Map数据
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    public String hmset(String key, Map<String, String> hash)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        return hmset(null, key, hash);
//    }
//
//    /**
//     * <p>设置 key 指定的哈希集中指定字段的值。该命令将重写所有在哈希集中存在的字段。</p>
//     * <p>如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联</p>
//     *
//     * @param parameter 参数
//     * @param key       Redis中Key
//     * @param hash      Map数据
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    public String hmset(MsOnionParameterAdapter parameter, String key, Map<String, String> hash)
//            throws MsOnionIllegalArgumentException, MsOnionException {
//        // 检查参数
//        inspectParametersForRedisKey(parameter, key);
//        inspectParameterForRedisField(hash);
//
//        try {
//            return this.msOnionJedisAdapter.hmset(key, hash);
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }
//    }

    //////////////// 提供简单Redis缓存操作，检查参数，Begin /////////////////////

    /**
     * 检查Redis的Key参数
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 分页参数
     * @param key       Redis的Key
     * @param field     Redis中数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParametersForRedisKey(MsOnionParameterAdapter parameter, String key, String... field)
            throws MsOnionIllegalArgumentException, MsOnionException {
        inspectParametersForRedisKey(parameter, key);
        inspectParameterForRedisField(field);
    }

    /**
     * 检查Redis的Key参数
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 分页参数
     * @param key       Redis的Key
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    private void inspectParametersForRedisKey(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), "inspectParametersForRedisKey # 检查参数 # key=" + key);

            if (StringUtils.isBlank(key)) {
                throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_ILLEGAL,
                        MsOnionStatusConstants.STATUS_400);
            }

            // Redis 的Key规则类似
            // String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());

            key = key.trim();
            // 判断Key规则是否符合  getTargetName()
            if (!key.contains(getTargetName())) {
                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL, getTargetName()),
                        MsOnionStatusConstants.STATUS_400);
            }

            // 判断Key规则是否符合  getPrefix()
//            if (!key.contains(getPrefix())) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL,
//                        getPrefix()),
//                        MsOnionStatusConstants.STATUS_400);
//            }

            // 判断Key规则是否符合  getTargetIdentification()
//            if (!key.contains(getTargetIdentification())) {
//                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
//                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL,
//                        getTargetIdentification()),
//                        MsOnionStatusConstants.STATUS_400);
//            }

            // 判断Key规则是否包括 ::
            if (key.contains(MsOnionConstants.REDIS_KEY_ILLEGAL_MULTI_COLON)) {
                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_MULTI_COLON,
                        MsOnionConstants.REDIS_KEY_ILLEGAL_MULTI_COLON),
                        MsOnionStatusConstants.STATUS_400);
            }

            // Redis的Key不可以":"开头
            if (key.startsWith(MsOnionConstants.REDIS_KEY_COLON)) {
                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_STARTS_WITH,
                        MsOnionConstants.REDIS_KEY_COLON),
                        MsOnionStatusConstants.STATUS_400);
            }

            // Redis的Key不可以":"结尾
            if (key.endsWith(MsOnionConstants.REDIS_KEY_COLON)) {
                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_ENDS_WITH,
                        MsOnionConstants.REDIS_KEY_COLON),
                        MsOnionStatusConstants.STATUS_400);
            }

            // 规则的Key，按照规则生成的Key
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            String regulationKey = String.format("%s%s%s", getTargetName(),
//                    getPrefix(), getTargetIdentification());

            String regulationKey = String.format("%s:", getTargetName());

            // 打印日志
            this.msOnionLogger.debug(getClass().getName(), "inspectParametersForRedisKey # 检查参数 # Redis的Key规则：regulationKey=" + regulationKey);

            // 判断Key规则是否符合  regulationKey
            if (!key.contains(regulationKey)) {
                throw new MsOnionIllegalArgumentException(String.format("%s，%s",
                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL,
                        regulationKey),
                        MsOnionStatusConstants.STATUS_400);
            }

            // 校验正则表达式
            if (!MsOnionRegexUtils.isRedisKey(key)) {
                throw new MsOnionIllegalArgumentException(String.format("%s，key=%s",
                        MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_KEY_REGULATION_ILLEGAL_REGEX,
                        key),
                        MsOnionStatusConstants.STATUS_400);
            }
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 检查field参数
     *
     * @param field Redis中数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameterForRedisField(String... field)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == field || field.length <= 0) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_FIELD_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查member参数
     *
     * @param member Redis中Set集合中的数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameterForRedisMember(String... member)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == member || member.length <= 0) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_MEMBER_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    /**
     * 检查hash参数
     *
     * @param hash Map数据
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameterForRedisHash(Map<String, String> hash)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == hash || hash.isEmpty()) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_HASH_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }

    //////////////////// 提供简单Redis缓存操作，检查参数，End //////////////////////////



    //////////////////////// Redis缓存操作 ##### End  /////////////////////////


    //////////////////////// Redis缓存操作，加密的 ##### Begin  /////////////////////////

    /**
     * 将数据缓存到Redis中，采取安全的方式
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key     Redis缓存的Key
     * @param value   Redis缓存中的数据（不需要加密，底层会自动会加密）
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedisWithSecurity(String key, String value)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return setForRedisWithSecurity(key, value, redisCacheExpire);
    }

    /**
     * 将数据缓存到Redis中，采取安全的方式
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key     Redis缓存的Key
     * @param value   Redis缓存中的数据（不需要加密，底层会自动会加密）
     * @param seconds Redis缓存周期，单位：秒
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedisWithSecurity(String key, String value, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return setForRedisWithSecurity(null, key, value, seconds);
    }

    /**
     * 将数据缓存到Redis中，采取安全的方式
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key     Redis缓存的Key
     * @param value   Redis缓存中的数据（不需要加密，底层会自动会加密）
     * @param seconds Redis缓存周期，单位：秒
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String setForRedisWithSecurity(MsOnionParameterAdapter parameter, String key, String value, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 校验 value
        inspectParameterForRedisValue(value);
        try {
            // 加密
            value = MsOnionSecurityUtils.encodeForRedis(value);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        return setForRedis(null, key, value, seconds);
    }

    /**
     * 获取Redis缓存中数据，采取安全的方式
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @return 返回Redis缓存中数据（已经是解密之后的数据）
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String getFromRedisWithSecurity(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 检查参数
        inspectParametersForRedisKey(parameter, key);
        try {
            // 加密的
            String value = this.msOnionJedisAdapter.get(key);
            // 解密
            return MsOnionSecurityUtils.decodeForRedis(value);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取Redis缓存中数据，采取安全的方式
     *
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     *
     * @param key       Redis的Key
     * @return 返回Redis缓存中数据（已经是解密之后的数据）
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    @Override
    public String getFromRedisWithSecurity(String key)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return getFromRedisWithSecurity(null, key);
    }

    /**
     * 检查value参数
     *
     * @param value Redis中数据（不需要加密）
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月14日 下午6:18:09
     */
    protected void inspectParameterForRedisValue(String value)
            throws MsOnionIllegalArgumentException, MsOnionException {
        if (MsOnionStringUtils.isBlank(value)) {
            throw new MsOnionIllegalArgumentException(MsOnionMessageConstants.MESSAGE_PARAMETER_REDIS_VALUE_ILLEGAL,
                    MsOnionStatusConstants.STATUS_400);
        }
    }



    //////////////////////// Redis缓存操作，加密的 ##### End  /////////////////////////




}
