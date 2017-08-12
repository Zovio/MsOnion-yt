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

package cc.msonion.carambola.parent.interfaces.redis.key;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;

/**
 * @Title: MsOnionRedisKeyGeneratorAdapter.java
 * @Package: cc.msonion.carambola.parent.interfaces.redis.key
 * @Description: Redis的Key生成器的适配器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午2:06:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月17日 下午2:06:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新建：定义相关方法
 */

/**
 * @ClassName: MsOnionRedisKeyGeneratorAdapter
 * @Description: Redis的Key生成器的适配器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午2:06:16
 */
public interface MsOnionRedisKeyGeneratorAdapter<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> {

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
    String getKeysName() throws MsOnionException;

    /**
     * 获取KEYS的标识，例如：:KEYS:
     *
     * @return 返回KEYS的标识，例如：:KEYS:
     * @throws MsOnionException 异常
     * @Title: getKeysIdentification
     * @Description: 获取KEYS的标识，例如：:KEYS:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:56:41
     */
    String getKeysIdentification() throws MsOnionException;

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
     * 获取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     *
     * @return 返回目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     * @throws MsOnionException 异常
     * @Title: getTargetIdentification
     * @Description: 获取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:43:41
     */
    String getTargetIdentification() throws MsOnionException;

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
    String getExampleName() throws MsOnionException;

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
    String getExampleIdentification() throws MsOnionException;

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
    String getSingleExampleName() throws MsOnionException;

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
    String getSingleExampleIdentification() throws MsOnionException;

    /**
     * 获取PAGE名称，例如：PAGE
     *
     * @return 返回PAGE名称，例如：PAGE
     * @throws MsOnionException 异常
     * @Title: getPageName
     * @Description: 获取PAGE名称，例如：PAGE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:50:34
     */
    String getPageName() throws MsOnionException;

    /**
     * 获取PAGE的标识，例如：:PAGE:
     *
     * @return 返回PAGE的标识，例如：:PAGE:
     * @throws MsOnionException 异常
     * @Title: getPageIdentification
     * @Description: 获取PAGE的标识，例如：:PAGE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:51:55
     */
    String getPageIdentification() throws MsOnionException;

    /**
     * 获取Redis中Keys的Key，更新Redis缓存需要使用到
     *
     * @return 返回Redis中Keys的Key，更新Redis缓存需要使用到
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中Keys的Key，更新Redis缓存需要使用到
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:01:47
     */
    String getKey() throws MsOnionException;

    /**
     * 根据主键Idx获取Redis中Key
     *
     * @param idx 主键idx
     * @return 返回主键Idx获取Redis中Key
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 根据主键Idx获取Redis中Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:01:23
     */
    String getKey(long idx) throws MsOnionException;

    /**
     * 获取Redis中的Key
     *
     * @param record 目标POJO的实例对象
     * @return 返回Redis中的Key
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月24日 下午12:41:56
     */
    String getKey(T record) throws MsOnionException;

    /**
     * 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:23:22
     */
    String getKey(E example) throws MsOnionException;

    /**
     * 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:BASIC:ONE:0ffab0253b59f6d8adec7ff0e61740a7
     * <p>query one 使用</p>
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:23:22
     */
    String getKeyForQueryOne(E example) throws MsOnionException;

    /**
     * 获取Redis中的Key，有分页查询条件，
     * 例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 返回Key 返回Redis中的Key，有分页查询条件
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:45:32
     */
    String getKey(int pageNum, int pageSize) throws MsOnionException;

    /**
     * 获取Redis中的Key，有分页查询条件，
     * 例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 返回Redis中的Key，有分页查询条件
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:45:32
     */
    String getKey(E example, int pageNum, int pageSize) throws MsOnionException;

    /**
     * 获取Redis中的Key，有分页查询条件，
     * 例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param orderBy  排序
     * @return 返回Redis中的Key，有分页查询条件
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:45:32
     */
    String getKey(E example, int pageNum, int pageSize, String orderBy) throws MsOnionException;

    /**
     * 获取Redis中的Key，有分页查询条件，
     * 例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param orderBy  排序
     * @param groupBy  分组
     * @return 返回获取Redis中的Key，有分页查询条件
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午1:45:32
     */
    String getKey(E example, int pageNum, int pageSize, String orderBy, String groupBy) throws MsOnionException;

    /**
     * @return 默认返回true，购物车需要重写，返回false，手动更新Redis缓存
     * @Title: isGlobalInfluenceRedisCache
     * @Description: 是否全局影响的缓存，例如：购物车和用户关联，不是全局影响的；商品是所有用户关注的，因此商品是全局影响的
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:12:16
     */
    boolean isGlobalInfluenceRedisCache();

    ////////////////////////// 2017-04-13 , Begin ///////////////////////////////////

    /**
     * 获取Session标识，例如：SESSION:
     *
     * @return 返回Session标识，例如：SESSION:
     * @throws MsOnionException 异常
     * @Title: getSessionIdentification
     * @Description: 获取Session标识，例如：SESSION:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:43:41
     */
    String getSessionIdentification() throws MsOnionException;

    /**
     * 获取前缀，例如：_INFO
     *
     * @return 返回前缀，例如：_INFO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Title: getPrefix
     * @Description: 获取前缀，例如：_INFO
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午8:33:02
     */
    String getPrefix() throws MsOnionIllegalArgumentException;

    ////////////////////////// 2017-04-13 , End ///////////////////////////////////

    ///////////////////////// 获取Count查询的Key， Begin //////////////////////////

    /**
     * 根据主键Idx获取Redis中Key
     * <p>
     * <p>Count专用</p>
     *
     * @param idx 主键idx
     * @return 返回主键Idx获取Redis中Key
     * @throws MsOnionException 异常
     * @Title: getCountKey
     * @Description: 根据主键Idx获取Redis中Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:01:23
     */
    String getCountKey(long idx) throws MsOnionException;

    /**
     * 获取Redis中的Key
     * <p>
     * <p>Count专用</p>
     *
     * @param record 目标POJO的实例对象
     * @return 返回Redis中的Key
     * @throws MsOnionException 异常
     * @Title: getCountKey
     * @Description: 获取Redis中的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月24日 下午12:41:56
     */
    String getCountKey(T record) throws MsOnionException;

    /**
     * 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:COUNT:0ffab0253b59f6d8adec7ff0e61740a7
     * <p>
     * <p>Count专用</p>
     *
     * @param example 目标POJO的Example实例对象
     * @return 返回Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序
     * @throws MsOnionException 异常
     * @Title: getCountKey
     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:ITEM:COUNT:0ffab0253b59f6d8adec7ff0e61740a7
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月15日 上午11:23:22
     */
    String getCountKey(E example) throws MsOnionException;

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
    String getCountIdentification() throws MsOnionException;

    /**
     * 获取Count名称，例如：COUNT
     *
     * @return 返回Count标识，例如：COUNT
     * @throws MsOnionException 异常
     * @Title: getCountIdentification
     * @Description: 获取Count标识，例如：COUNT
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月15日 上午10:43:41
     */
    String getCountName() throws MsOnionException;

    ///////////////////////// 获取Count查询的Key， End //////////////////////////


    ///////////////////////// 获取Query One查询的Key， Begin //////////////////////////

    /**
     * query one 的标识， 例如：  :ONE:
     *
     * @return 返回 query one 的标识， 例如：  :ONE:
     * @throws MsOnionException 异常
     * @Title: getOneIdentification
     * @Description: 获取One标识，query one使用，例如：:ONE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月28日 上午11:43:32
     */
    String getOneIdentification() throws MsOnionException;

    /**
     * Query One 的名称： ONE
     *
     * @return 返回 Query One 的名称： ONE
     * @throws MsOnionException 异常
     * @Title: getOneName
     * @Description: Query One 的名称： ONE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月28日 上午10:50:34
     */
    String getOneName() throws MsOnionException;


    ///////////////////////// 获取Query One 查询的Key， End //////////////////////////

    //////////////////////// 获取 Query Random 随机查询的 Key , Begin #######//////////////////

//    /**
//     * 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
//     * 例如：ITEM_INFO:BASIC:ONE:0ffab0253b59f6d8adec7ff0e61740a7
//     * <p>query one 使用</p>
//     *
//     * @param example 目标POJO的Example实例对象
//     * @param orderBy 排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
//     * @return 返回Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序
//     * @throws MsOnionException 异常
//     * @Title: getKey
//     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
//     * 例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年2月17日 上午11:23:22
//     */
//    String getKeyForQueryRandom(E example, String orderBy) throws MsOnionException;

    /**
     * query random（随机查询） 的标识， 例如：  :RANDOM:
     *
     * @return 返回 query random（随机查询） 的标识， 例如：  :RANDOM:
     * @throws MsOnionException 异常
     * @Title: getOneIdentification
     * @Description: query random（随机查询） 的标识， 例如：  :RANDOM:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 上午11:43:32
     */
    String getRandomIdetification() throws MsOnionException;

    /**
     * Query Random 的名称（随机查询）： RANDOM
     *
     * @return 返回 Query Random （随机查询）的名称： RANDOM
     * @throws MsOnionException 异常
     * @Title: getOneName
     * @Description: Query Random （随机查询）的名称： RANDOM
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 上午10:50:34
     */
    String getRandomName() throws MsOnionException;

    //////////////////////// 获取 Query Random 随机查询的 Key , Begin #######//////////////////

    /**
     * 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:BASIC:RANDOM:0ffab0253b59f6d8adec7ff0e61740a7
     * <p>query one 使用</p>
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  分页，页码
     * @param pageSize 分页，每页容量（数量）
     * @param orderBy  排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:23:22
     */
    String getKeyForQueryRandom(E example, int pageNum, int pageSize, String orderBy) throws MsOnionException;

    //////////////////////// 获取 Query Random 随机查询的 Key , End #######//////////////////

}
