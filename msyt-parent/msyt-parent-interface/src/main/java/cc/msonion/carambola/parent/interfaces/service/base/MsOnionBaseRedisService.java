/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(ApiVersion apiVersion, Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(ApiVersion apiVersion, MDPP)
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

package cc.msonion.carambola.parent.interfaces.service.base;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;

import java.util.Set;

/**
 * @Title: MsOnionBaseRedisService.java
 * @Package: cc.msonion.carambola.parent.interfaces.service.base
 * @Description: MsOnionBaseRedisService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午3:34:54
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月14日 下午3:34:54
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：ApiVersion参数、MSOnionParameter参数，以及调整相关方法
 */

/**
 * BaseRedis缓存Service，提供Redis缓存操作相关接口
 *
 * @param <T> 目标Pojo，例如：Item
 * @param <E> 目标Pojo的 Example，例如：ItemExample
 * @ClassName: MsOnionBaseRedisService
 * @Description: MsOnionBaseRedisService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月14日 下午3:34:54
 */
public interface MsOnionBaseRedisService<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> {

    //////////////////////////////// 提供简单Redis缓存操作，Begin /////////////////////////////////////////////////////

    ////////////////// String字符串类型操作，Begin ///////////

    /**
     * 将数据缓存到Redis中
     * <p>
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
    @Deprecated
    // 使用 setForRedisWithSecurity
    String setForRedis(String key, String value, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 将数据缓存到Redis中
     * <p>
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
    @Deprecated
    // 使用 setForRedisWithSecurity
    String setForRedis(MsOnionParameterAdapter parameter, String key, String value,
                       int seconds) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 将数据缓存到Redis中
     * <p>
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
    @Deprecated
    // 使用 setForRedisWithSecurity
    String setForRedis(String key, String value)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 将数据缓存到Redis中
     * <p>
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
    @Deprecated
    // 使用 setForRedisWithSecurity
    String setForRedis(MsOnionParameterAdapter parameter, String key, String value)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
     * <p>
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key     Redis的Key
     * @param seconds Redis缓存周期，单位：秒
     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    Long expireForRedis(String key, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException;

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
     * <p>
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
     * @param seconds   Redis缓存周期，单位：秒
     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    Long expireForRedis(MsOnionParameterAdapter parameter, String key, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除Redis中的缓存
     * <p>
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
    Long delKeyForRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除Redis中的缓存
     * <p>
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
    Long delKeyForRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取Redis缓存中数据
     * <p>
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
    @Deprecated
    // 使用getFromRedisWithSecurity
    String getFromRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取Redis缓存中数据
     * <p>
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
    @Deprecated
    // 使用 getFromRedisWithSecurity
    String getFromRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    ////////////////// String字符串类型操作，End ///////////


    ////////////////// Set类型操作，Begin ///////////
    ////// Redis set对外提供功能与list类似，是一个列表的功能，特殊之处在于set是可以自动排重的，不会出现重复的数据！！！

    /**
     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
     * 如果key 的类型不是集合则返回错误。</p>
     * <p>
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
    Long saddForRedis(String key, String... member) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
     * 如果key 的类型不是集合则返回错误。</p>
     * <p>
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
    Long saddForRedis(MsOnionParameterAdapter parameter, String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除并获取Redis中一个集合里面的元素
     * <p>
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
    String spopForRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 删除并获取Redis中一个集合里面的元素
     * <p>
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
    String spopForRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取key的Set集合所有的元素
     * <p>
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
    Set<String> smembersForRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取key的Set集合所有的元素
     *
     * @param parameter 参数
     * @param key       Redis的Key
     * @return 返回key的Set集合所有的元素
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    Set<String> smembersForRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
     * 如果member元素是集合key的成员，则返回1
     * 如果member元素不是key的成员，或者集合key不存在，则返回0
     * <p>
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
    Boolean sismemberForRedis(String key, String member)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
     * 如果member元素是集合key的成员，则返回1
     * 如果member元素不是key的成员，或者集合key不存在，则返回0
     * <p>
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
    Boolean sismemberForRedis(MsOnionParameterAdapter parameter, String key, String member)
            throws MsOnionIllegalArgumentException, MsOnionException;

    ////////////////// Set类型操作，End ///////////

    /**
     * Redis缓存中key是否存在
     * <p>1：key存在</p>
     * <p>0：key不存在</p>
     * <p>
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
    Boolean existsKeyFromRedis(String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * Redis缓存中key是否存在
     * <p>1：key存在</p>
     * <p>0：key不存在</p>
     * <p>
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
    Boolean existsKeyFromRedis(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * <p>在key集合中移除指定的元素，
     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
     * 如果key的类型不是一个集合,则返回错误。</p>
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
    Long sremForRedis(String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * <p>在key集合中移除指定的元素，
     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
     * 如果key的类型不是一个集合,则返回错误。</p>
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
    Long sremForRedis(MsOnionParameterAdapter parameter, String key, String... member)
            throws MsOnionIllegalArgumentException, MsOnionException;

    //////////////////////////////// 提供简单Redis缓存操作，End /////////////////////////////////////////////////////


    //////////////////////// Redis缓存操作，加密的 ##### Begin  /////////////////////////

    /**
     * 将数据缓存到Redis中，采取安全的方式
     * <p>
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key   Redis缓存的Key
     * @param value Redis缓存中的数据（不需要加密，底层会自动会加密）
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    String setForRedisWithSecurity(String key, String value)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 将数据缓存到Redis中，采取安全的方式
     * <p>
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
    String setForRedisWithSecurity(String key, String value, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 将数据缓存到Redis中，采取安全的方式
     * <p>
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param parameter 参数
     * @param key       Redis缓存的Key
     * @param value     Redis缓存中的数据（不需要加密，底层会自动会加密）
     * @param seconds   Redis缓存周期，单位：秒
     * @return 返回结果
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    String setForRedisWithSecurity(MsOnionParameterAdapter parameter, String key, String value, int seconds)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取Redis缓存中数据，采取安全的方式
     * <p>
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
    String getFromRedisWithSecurity(MsOnionParameterAdapter parameter, String key)
            throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 获取Redis缓存中数据，采取安全的方式
     * <p>
     * <ul>
     * <li>Key的生成规则：String.format("%s%s%s", getTargetName(), getSessionIdentification(), token.toUpperCase());</li>
     * <li>其中：getTargetName() 这个必须的，</li>
     * <li>例如：ITEMBRAND:PAGE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
     * <li>不能以":"开头，不能以":"结束</li>
     * </ul>
     *
     * @param key Redis的Key
     * @return 返回Redis缓存中数据（已经是解密之后的数据）
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月13日 下午6:18:09
     */
    String getFromRedisWithSecurity(String key)
            throws MsOnionIllegalArgumentException, MsOnionException;


    //////////////////////// Redis缓存操作，加密的 ##### End  /////////////////////////


}
