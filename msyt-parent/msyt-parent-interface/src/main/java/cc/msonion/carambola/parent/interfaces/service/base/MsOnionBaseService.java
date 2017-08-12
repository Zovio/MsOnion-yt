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
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionBatchReport;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionResultAdapter;

import java.util.List;
import java.util.Map;

/**
 * @Title: MsOnionBaseService.java
 * @Package: cc.msonion.carambola.parent.interfaces.service.base
 * @Description: MsOnionBaseService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月17日下午3:34:54
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月17日下午3:34:54
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：ApiVersion参数、MSOnionParameter参数，以及调整相关方法
 */

/**
 * @param <T> 目标Pojo，例如：Item
 * @param <E> 目标Pojo的 Example，例如：ItemExample
 * @ClassName: MsOnionBaseService
 * @Description: MsOnionBaseService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月17日下午3:34:54
 */
public interface MsOnionBaseService<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> extends MsOnionBaseRedisService<T, E> {

//	public PageInfo<T> queryByPage(ApiVersion apiVersion, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

//	/**
//	 * @Title: queryByPage
//	 * @Description: 分页查询
//	 * @Author: HeroCao hero-cao@msyc.cc
//	 * @Date: 2017年6月17日下午5:15:48
//	 *
//	 * @param example
//	 * @param pageNum 页数，从1开始
//	 * @param pageSize 每页数量
//	 * @return
//	 */
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public PageInfo<T> queryByPage(ApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    public List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    public MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param pageNum    当前页数
     * @param pageSize   每页数量
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    List<T> queryListByPage(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 分页查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param pageNum                   当前页数
     * @param pageSize                  每页数量
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回查询目标POJO集合
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter queryListByPageForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, int pageNum, int pageSize, String orderBy, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx集合进行批量删除（软删除）
     *
     * @param apiVersion api版本
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    int deleteByIdxes(MsOnionApiVersion apiVersion, List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx集合进行批量删除（软删除），返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter deleteByIdxesForResult(MsOnionApiVersion apiVersion, List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx集合进行批量删除（软删除）
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    int deleteByIdxes(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx集合进行批量删除（软删除），返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    MsOnionResultAdapter deleteByIdxesForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来获取记录数
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    long countByExample(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来获取记录数，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter countByExampleForResult(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来获取记录数
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    long countByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来获取记录数，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回记录数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter countByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    ////////////////////// 删除，Begin ////////////////////////////////////

//    /**
//     * 根据目标POJO的Example删除
//     *
//     * @param apiVersion api版本
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException 异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//     int deleteByExample(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

//    /**
//     * 根据目标POJO的Example删除，返回结果：MsOnionResultAdapter
//     *
//     * @param apiVersion api版本
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException 异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//     MsOnionResultAdapter deleteByExampleForResult(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

//    /**
//     * 根据目标POJO的Example删除
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//     int deleteByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

//    /**
//     * 根据目标POJO的Example删除，返回结果：MsOnionResultAdapter , 不允许提供直接删除数据功能
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param example           目标POJO的Example实例对象
//     * @return 返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException 非法参数
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//     MsOnionResultAdapter deleteByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 通过主键Idx删除（软删除，其实就是更新状态）
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Title: deleteByPrimaryKey
     * @Description: 通过主键Idx删除
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int deleteByPrimaryKey(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 通过主键Idx删除，返回结果：MsOnionResultAdapter ， 不允许提供直接删除数据功能
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    ///////////////////////// 直接删除，不允许直接删除，禁止，必须通过软删除（更新状态），Begin ///////////////////////////////////

//    /**
//     * 根据主键idx删除
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param idx               主键idx
//     * @return 如果返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//     int deleteByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

//    /**
//     * 根据主键idx删除，返回结果：MsOnionResultAdapter , 不允许提供直接删除数据功能
//     *
//     * @param apiVersion api版本
//     * @param parameter         参数
//     * @param idx               主键idx
//     * @return 如果返回1：成功，0：失败
//     * @throws MsOnionIllegalArgumentException
//     * @throws MsOnionException
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年6月17日下午6:30:12
//     */
//     MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

//	/**
//	 *  根据主键idx集合进行删除，也就是批量删除，其实是软删除，
//	 *  <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
//	 * @param example 目标POJO的Example实例对象
//	 * @return 返回删除成功记录条数
//	 *@throws MsOnionIllegalArgumentException
//	 * @throws MsOnionException
//	 * @Author: HeroCao hero-cao@msyc.cc
//	 * @Date: 2017年6月17日下午6:30:12
//	 */
//	public int deleteByIdxesWithExample(E example) throws MsOnionIllegalArgumentException, MsOnionException ;

//	/**
//	 * 根据主键idx集合进行删除，也就是批量删除，其实是软删除，
//	 *  <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
//	 *
//	 * @param apiVersion
//	 * @param example 目标POJO的Example实例对象
//	 * @return 返回删除成功记录条数
//	 * @throws MsOnionIllegalArgumentException
//	 * @throws MsOnionException
//	 * @Author: HeroCao hero-cao@msyc.cc
//	 * @Date: 2017年6月17日下午6:30:12
//	 */
//	public int deleteByIdxesWithExample(ApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException ;

//	/**
//	 * 根据主键idx集合进行删除，也就是批量删除，其实是软删除，
//	 *  <p> 使用目标POJO的Example的Criteria andIdxIn(List<Long> values) </p>
//	 *
//	 * @param apiVersion api版本
//	 * @param parameter 参数
//	 * @param example 目标POJO的Example实例对象
//	 * @return 返回删除成功记录条数
//	 * @throws MsOnionIllegalArgumentException
//	 * @throws MsOnionException
//	 * @Author: HeroCao hero-cao@msyc.cc
//	 * @Date: 2017年6月17日下午6:30:12
//	 */
//	public int deleteByIdxesWithExample(ApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException ;

    ///////////////////////// 直接删除，不允许直接删除，禁止，必须通过软删除（更新状态），End  ///////////////////////////////////


    ///////////////////////// 必须通过软删除（更新状态），Begin ///////////////////////////////////

    /**
     * 根据主键idx删除
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int deleteByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx删除，返回结果：MsOnionResultAdapter , 不允许提供直接删除数据功能
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter deleteByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    ///////////////////////// 软删除（更新状态），End ///////////////////////////////////

    /**
     * 保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int save(MsOnionApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 保存（新增）目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter saveForResult(MsOnionApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int save(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 保存（新增）目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter saveForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException;

//	public int saveSelective(ApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public int saveSelective(ApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    //	public List<T> queryByExampleWithBLOBs(ApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    List<T> queryByExample(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    List<T> queryByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    List<T> queryByExample(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    List<T> queryByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    List<T> queryByExample(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    List<T> queryByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx查询
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    T queryByPrimaryKey(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx查询
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    T queryByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 返回目标POJO的实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    T queryByPrimaryKey(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    T queryByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @param redisCacheExpire          Redis的缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    T queryByPrimaryKey(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存
     * @param redisCacheExpire          Redis的缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, Long idx, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis的缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    T queryByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键idx进行查询，返回：MsOnionResultAdapter
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param idx                       主键idx
     * @param isDisabledRedisQueryCache 是否禁用Redis的查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis的缓存周期，单位：毫秒
     * @return 返回目标POJO实例对象
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter queryByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

//	public int updateByExampleSelective(ApiVersion apiVersion, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;
//
//	public int updateByExampleSelective(ApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    //	public int updateByExampleWithBLOBs(ApiVersion apiVersion, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来更新
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int updateByExample(MsOnionApiVersion apiVersion, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter updateByExampleForResult(MsOnionApiVersion apiVersion, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来更新
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int updateByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example来更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter updateByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example) throws MsOnionIllegalArgumentException, MsOnionException;

//	public int updateByPrimaryKeySelective(ApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;

//	/**
//	 *  根据主键Idx进行更新 ，由于底层使用反射，对null值都设置默认值，因此这个方法不可以使用！！！
//	 * @param apiVersion
//	 * @param parameter
//	 * @param record
//	 * @return
//	 * @throws MsOnionIllegalArgumentException
//	 * @throws MsOnionException
//	 */
//	public int updateByPrimaryKeySelective(ApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    //	public int updateByPrimaryKeyWithBLOBs(ApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键Idx进行更新
     *
     * @param apiVersion api版本
     * @param record     目标POJO
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    int updateByPrimaryKey(MsOnionApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键Idx进行更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param record     目标POJO
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter updateByPrimaryKeyForResult(MsOnionApiVersion apiVersion, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键Idx进行更新
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午20:50:12
     */
    int updateByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据主键Idx进行更新，返回：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @return 如果更新成功返回1，否则返回0
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午20:50:12
     */
    MsOnionResultAdapter updateByPrimaryKeyForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException;


    //////////////////////////////// 更新状态，Begin /////////////////////////////////////////////////////

    /**
     * 更新状态status
     *
     * @param apiVersion api版本
     * @param idx        主键idx
     * @param status     目标更新状态值，例如：0（删除）
     * @return 返回数据库更新操作记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    int updateStatus(MsOnionApiVersion apiVersion, long idx, short status) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 更新状态status
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param status     目标更新状态值，例如：0（删除）
     * @return 返回数据库更新操作记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     */
    int updateStatus(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, long idx, short status) throws MsOnionIllegalArgumentException, MsOnionException;

    //////////////////////////////// 更新状态，End /////////////////////////////////////////////////////


//    //////////////////////////////// 提供简单Redis缓存操作，Begin /////////////////////////////////////////////////////
//
//    ////////////////// String字符串类型操作，Begin ///////////
//
//    /**
//     * 将数据缓存到Redis中
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key     Redis缓存的Key
//     * @param value   Redis缓存中的数据
//     * @param seconds Redis缓存周期，单位：秒
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    String setForRedis(String key, String value, int seconds)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 将数据缓存到Redis中
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis缓存的Key
//     * @param value     Redis缓存中的数据
//     * @param seconds   Redis缓存周期，单位：秒
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    String setForRedis(MsOnionParameterAdapter parameter, String key, String value,
//                       int seconds) throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 将数据缓存到Redis中
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key   Redis缓存的Key
//     * @param value Redis缓存中的数据
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    String setForRedis(String key, String value)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 将数据缓存到Redis中
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis缓存的Key
//     * @param value     Redis缓存中的数据
//     * @return 返回结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    String setForRedis(MsOnionParameterAdapter parameter, String key, String value)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * <p>设置key的过期时间，超过时间后，将会自动删除该key。
//     * 在Redis的术语中一个key的相关超时是不确定的。
//     * 超时后只有对key执行DEL命令或者SET命令或者GETSET时才会清除。
//     * 这意味着，从概念上讲所有改变key的值的操作都会使他清除。
//     * 例如，INCR递增key的值，执行LPUSH操作，或者用HSET改变hash的field所有这些操作都会触发删除动作。
//     * 使用PERSIST命令可以清除超时，使其变成一个永久的key。
//     * 如果key被RENAME命令修改，相关的超时时间会转移到新key上面。
//     * 如果key被RENAME命令修改，比如原来就存在Key_A,然后调用RENAME Key_B Key_A命令，
//     * 这时不管原来Key_A是永久的还是设置为超时的，都会由Key_B的有效期状态覆盖。
//     * 刷新过期时间
//     * 对已经有过期时间的key执行EXPIRE操作，将会更新它的过期时间。
//     * 有很多应用有这种业务场景，例如记录会话的session。</p>
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key     Redis的Key
//     * @param seconds Redis缓存周期，单位：秒
//     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Long expireForRedis(String key, int seconds)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * <p>设置key的过期时间，超过时间后，将会自动删除该key。
//     * 在Redis的术语中一个key的相关超时是不确定的。
//     * 超时后只有对key执行DEL命令或者SET命令或者GETSET时才会清除。
//     * 这意味着，从概念上讲所有改变key的值的操作都会使他清除。
//     * 例如，INCR递增key的值，执行LPUSH操作，或者用HSET改变hash的field所有这些操作都会触发删除动作。
//     * 使用PERSIST命令可以清除超时，使其变成一个永久的key。
//     * 如果key被RENAME命令修改，相关的超时时间会转移到新key上面。
//     * 如果key被RENAME命令修改，比如原来就存在Key_A,然后调用RENAME Key_B Key_A命令，
//     * 这时不管原来Key_A是永久的还是设置为超时的，都会由Key_B的有效期状态覆盖。
//     * 刷新过期时间
//     * 对已经有过期时间的key执行EXPIRE操作，将会更新它的过期时间。
//     * 有很多应用有这种业务场景，例如记录会话的session。</p>
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param seconds   Redis缓存周期，单位：秒
//     * @return 返回:1 如果成功设置过期时间，0 如果key不存在或者不能设置过期时间。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Long expireForRedis(MsOnionParameterAdapter parameter, String key, int seconds)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 删除Redis中的缓存
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis缓存的Key
//     * @return 返回删除结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    Long delKeyForRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 删除Redis中的缓存
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis缓存的Key
//     * @return 返回删除结果
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    Long delKeyForRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 获取Redis缓存中数据
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回Redis缓存中数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    String getFromRedis(String key) throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 获取Redis缓存中数据
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回Redis缓存中数据
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年4月13日 下午6:18:09
//     */
//    String getFromRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    ////////////////// String字符串类型操作，End ///////////
//
//
//    ////////////////// Set类型操作，Begin ///////////
//    ////// Redis set对外提供功能与list类似，是一个列表的功能，特殊之处在于set是可以自动排重的，不会出现重复的数据！！！
//
//    /**
//     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
//     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
//     * 如果key 的类型不是集合则返回错误。</p>
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key    Redis的Key
//     * @param member 添加到Set集合中的成员数据
//     * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Long saddForRedis(String key, String... member) throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * <p>添加一个或多个指定的member元素到集合的 key中.指定的一个或者多个元素member 如果已经在集合key中存在则忽略。
//     * 如果集合key 不存在，则新建集合key，并添加member元素到集合key中。
//     * 如果key 的类型不是集合则返回错误。</p>
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param member    添加到Set集合中的成员数据
//     * @return 返回新成功添加到集合里元素的数量，不包括已经存在于集合中的元素。
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Long saddForRedis(MsOnionParameterAdapter parameter, String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 删除并获取Redis中一个集合里面的元素
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回删除之后集合中原始
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    String spopForRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 删除并获取Redis中一个集合里面的元素
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回删除之后集合中原始
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    String spopForRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 获取key的Set集合所有的元素
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回key的Set集合所有的元素
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Set<String> smembersForRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 获取key的Set集合所有的元素
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回key的Set集合所有的元素
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Set<String> smembersForRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
//     * 如果member元素是集合key的成员，则返回1
//     * 如果member元素不是key的成员，或者集合key不存在，则返回0
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key    Redis的Key
//     * @param member 成员
//     * @return 返回Set集合中成员 member 是否是存储的集合 key的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Boolean sismemberForRedis(String key, String member)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * 获取Set集合中成员 member 是否是存储的集合 key的成员，
//     * 如果member元素是集合key的成员，则返回1
//     * 如果member元素不是key的成员，或者集合key不存在，则返回0
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @param member    成员
//     * @return 返回Set集合中成员 member 是否是存储的集合 key的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Boolean sismemberForRedis(MsOnionParameterAdapter parameter, String key, String member)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    ////////////////// Set类型操作，End ///////////
//
//    /**
//     * Redis缓存中key是否存在
//     * <p>1：key存在</p>
//     * <p>0：key不存在</p>
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key Redis的Key
//     * @return 返回1 如果key存在，0 如果key不存在
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Boolean existsKeyFromRedis(String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * Redis缓存中key是否存在
//     * <p>1：key存在</p>
//     * <p>0：key不存在</p>
//     * <p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis的Key
//     * @return 返回1 如果key存在，0 如果key不存在
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Boolean existsKeyFromRedis(MsOnionParameterAdapter parameter, String key)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * <p>在key集合中移除指定的元素，
//     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
//     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
//     * 如果key的类型不是一个集合,则返回错误。</p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param key    Redis中的Key
//     * @param member 需要从Redis的Set集合中删除的成员
//     * @return 从集合中移除元素的个数，不包括不存在的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Long sremForRedis(String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    /**
//     * <p>在key集合中移除指定的元素，
//     * 如果指定的元素不是keyd的Set集合中的元素则忽略，
//     * 如果key的Set集合不存在则被视为一个空的集合，该命令返回0。
//     * 如果key的类型不是一个集合,则返回错误。</p>
//     * <ul>
//     * <li>Key的生成规则：String.format("%s%s%s%s%s", getTargetName(), getPrefix(), getTargetIdentification(), getSessionIdentification(), token.toUpperCase());</li>
//     * <li>其中：getTargetName(), getPrefix(), getTargetIdentification() 这个必须的，</li>
//     * <li>例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10，</li>
//     * <li>Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"</li>
//     * <li>不能以":"开头，不能以":"结束</li>
//     * </ul>
//     *
//     * @param parameter 参数
//     * @param key       Redis中的Key
//     * @param member    需要从Redis的Set集合中删除的成员
//     * @return 从集合中移除元素的个数，不包括不存在的成员
//     * @throws MsOnionIllegalArgumentException 非法参数异常
//     * @throws MsOnionException                异常
//     */
//    Long sremForRedis(MsOnionParameterAdapter parameter, String key, String... member)
//            throws MsOnionIllegalArgumentException, MsOnionException;
//
//    //////////////////////////////// 提供简单Redis缓存操作，End /////////////////////////////////////////////////////
//

    //////////////////////////////////// 提供查询，一个记录的方法 ### Begin ## ///////////////

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    T queryOne(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    T queryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @return MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    T queryOne(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    T queryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    T queryOne(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回目标POJO
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    T queryOne(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，一条目标POJO记录，返回结果：MsOnionResultAdapter，
     * <p>查询最多1条记录：queryOne 比 queryByExample 性能高500倍以上</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @return 返回MsOnionResult
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日 下午6:30:12
     */
    MsOnionResult queryOneForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, E example, boolean isDisabledRedisQueryCache, int redisCacheExpire) throws MsOnionIllegalArgumentException, MsOnionException;


    //////////////////////////////////// 提供查询，一个记录的方法 ### End  ## ///////////////

    //////////////////////////////////// 批量更新、修改 ### Begin  ## ///////////////

    /**
     * 批量更新、修改目标POJO的记录
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionBatchReport updateWithBatch(MsOnionApiVersion apiVersion, Map<Integer, T> map) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量更新、修改目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter updateWithBatchForResult(MsOnionApiVersion apiVersion, Map<Integer, T> map) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 返回批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionBatchReport updateWithBatch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量更新、修改目标POJO的记录，返回结果：MsOnionResult
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter updateWithBatchForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException;


    //////////////////////////////////// 批量更新、修改 ### End  ## ///////////////

    //////////////////////////////////// 批量新增、插入 ### Begin  ## //////////////////////////////////

    /**
     * 批量保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionBatchReport saveWithBatch(MsOnionApiVersion apiVersion, Map<Integer, T> map) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量保存（新增）目标POJO的记录，返回结果：MsOnionResultAdapter
     *
     * @param apiVersion api版本
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter saveWithBatchForResult(MsOnionApiVersion apiVersion, Map<Integer, T> map) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 返回批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionBatchReport saveWithBatch(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 批量保存（新增）目标POJO的记录，返回结果：MsOnionResult
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param map        源数据记录集合，也就是需要批量操作的原始数据集合
     *                   <p>Key ：行号（从0开始） ， Value ： 目标POJO对象</p>
     * @return 批量成功的记录条数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月17日下午6:30:12
     */
    MsOnionResultAdapter saveWithBatchForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Map<Integer, T> map)
            throws MsOnionIllegalArgumentException, MsOnionException;


    //////////////////////////////////// 批量添加、插入 ### End  ## ///////////////

    /////////////////// 批量随机查询 ### Begin ////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion,
                                 E example, int count) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                 E example, int count) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                 E example, boolean isDisabledRedisQueryCache, int count) throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                 E example, boolean isDisabledRedisQueryCache,
                                 int redisCacheExpire, int count) throws MsOnionIllegalArgumentException, MsOnionException;


    //////////////////  forResult ### Begin ///////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion,
                                                       E example, int count) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                       E example, int count) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                       E example, boolean isDisabledRedisQueryCache, int count) throws MsOnionIllegalArgumentException, MsOnionException;


    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                       E example, boolean isDisabledRedisQueryCache,
                                                       int redisCacheExpire, int count) throws MsOnionIllegalArgumentException, MsOnionException;

    //////////////////  forResult ### End ///////////////////////////////

    //////////////////  排序 orderBy  ## Begin /////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion,
                                 E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                 E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                 E example, boolean isDisabledRedisQueryCache, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    List<T> queryRandomByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                 E example, boolean isDisabledRedisQueryCache,
                                 int redisCacheExpire, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /////////////////  排序 orderBy  ## End /////////////////////////////////////

    //////////////////  排序 orderBy  ## Begin /////////////////////////////////////

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion,
                                                       E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param example    目标POJO的Example实例对象
     * @param count      随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy    排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                       E example, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    /**
     * 根据目标POJO的Example查询，随机查询记录
     * <p>随机查询数量，查询结果数量不一定相同，可能数据没有，也可能数量不够</p>
     *
     * @param apiVersion                api版本
     * @param parameter                 参数
     * @param example                   目标POJO的Example实例对象
     * @param isDisabledRedisQueryCache 是否禁止Redis查询缓存，true：禁止缓存，false：使用缓存
     * @param redisCacheExpire          Redis缓存周期，单位：毫秒
     * @param count                     随机查询数量，例如：4、5、6、8、10 ，不能超过50，分页最大值为：50
     * @param orderBy                   排序，多个排序条件使用,(英文输入法逗号)隔开，DESC 倒序（从大到小）ASC：顺序（从小到大），排序字段名（列名）必须与数据库表列名一致，例如：idx 、create_time 、update_time 、create_time desc、create_time ASC、create_time DESC,idx ASC、create_time DESC,update_time DESC,idx ASC
     * @return 返回目标POJO的集合
     * @throws MsOnionIllegalArgumentException 非法参数
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月21日 下午6:30:12
     */
    MsOnionResultAdapter queryRandomByExampleForResult(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter,
                                                       E example, boolean isDisabledRedisQueryCache,
                                                       int redisCacheExpire, int count, String orderBy) throws MsOnionIllegalArgumentException, MsOnionException;

    ///////////////  排序 orderBy  ## End /////////////////////////////////////


    /////////////////// 批量随机查询  ### End ////////////////////////////////////


}
