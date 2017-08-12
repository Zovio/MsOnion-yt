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
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRedisUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import org.apache.commons.lang3.StringUtils;


/**
 * Redis的Key生成器
 *
 * @Title: MsOnionRedisKeyGenerator.java
 * @Package: cc.msonion.carambola.parent.interfaces.redis.key
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午2:28:17
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月17日 下午2:28:17
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * Redis的Key生成器
 *
 * @param <T> Target --》目标Pojo，例如：Item
 * @param <E> Example --》目标Pojo的Example，例如：ItemExample
 * @ClassName: MsOnionRedisKeyGenerator
 * @Description: Redis的Key生成器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午2:28:17
 */
public abstract class MsOnionRedisKeyGenerator<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> implements MsOnionRedisKeyGeneratorAdapter<T, E> {

    /**
     * 默认最大int 值
     */
    private static final int DEFAULT_MAX_INT_VALUE = Integer.MAX_VALUE / 10;

    /**
     * Redis的KEYS，框架自定义的
     */
    private static final String DEFAULT_KEY_4_KEYS = "KEYS";

    /**
     * 默认分页：PAGE
     */
    private static final String DEFAULT_PAGE = "PAGE";

    /**
     * 默认 EXAMPLE 名称
     */
    private static final String DEFAULT_SINGLE_EXAMPLE = "EXAMPLE";

    /**
     * 默认Session
     */
    private static final String DEFAULT_SESSION = "SESSION";

    /**
     * one : Query One 使用
     */
    private static final String DEFAULT_ONE = "ONE";

    /**
     * 默认Count
     */
    private static final String DEFAULT_COUNT = "COUNT";

    /**
     * random : Query Random （随机查询）使用
     */
    private static final String DEFAULT_RANDOM = "RANDOM";

    /**
     * 目标POJO名称
     */
    private String targetName;

    /**
     * 目标POJO的标识
     */
    private String targetIdentification;

    /**
     * Session标识
     */
    private String sessionIdentification;

    /**
     * Example名称
     */
    private String exampleName;

    /**
     * Example标识
     */
    private String exampleIdentification;

    /**
     * 简单的Example名称
     */
    private String singleExampleName;

    /**
     * 简单Example标识
     */
    private String singleExampleIdentification;

    /**
     * keys的名称
     */
    private String keysName;

    /**
     * keys的标识
     */
    private String keysIdentification;

    /**
     * 分页的名称
     */
    private String pageName;

    /**
     * 分页标识
     */
    private String pageIdentification;

    /**
     * 数量count名称
     */
    private String countName;

    /**
     * 数量count的标识
     */
    private String countIdentification;

    /**
     * query one 使用: ONE
     */
    private String oneName;

    /**
     * query one 使用，例如： :ONE:
     */
    private String oneIdentification;

    ////////////// 随机查询， Begin ### /////////////

    /**
     * query random （随机查询）使用: RANDOM
     */
    private String randomName;

    /**
     * query random（随机查询） 使用，例如： :RANDOM:
     */
    private String randomIdentification;

    ///////////// 随机查询 ， End ### ////////////

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
    @Override
    public String getPrefix() throws MsOnionIllegalArgumentException {
        return MsOnionRedisUtils.getRedisKeyPrefix();
    }

    /**
     * @return 返回Keys的名称
     * @Title: getKeysName
     * @Description: 获取KEYS的名称，例如：KEYS
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:56:24
     */
    public String getKeysName() throws MsOnionException {
        if (StringUtils.isNotBlank(this.keysName)) {
            return this.keysName;
        }
        return this.keysName = MsOnionRedisUtils.getRedisKeyStyle(this.DEFAULT_KEY_4_KEYS);
    }

    /**
     * :KEYS
     *
     * @return 返回 :KEYS
     * @Title: getKeysIdentification
     * @Description: 获取KEYS的标识，例如：:KEYS
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:56:41
     */
    public String getKeysIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.keysIdentification)) {
            return this.keysIdentification;
        }
//        return this.keysIdentification = String.format(":%s:", getKeysName());
        return this.keysIdentification = String.format(":%s", getKeysName());
    }

    /**
     * @return
     * @throws MsOnionException
     * @Title: getTargetName
     * @Description: 获取目标名称，其实就是目标POJO名称，不包括包名，例如：ITEM
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:42:52
     */
    public String getTargetName() throws MsOnionException {
        if (StringUtils.isNotBlank(this.targetName)) {
            return this.targetName;
        }
        return this.targetName = MsOnionRedisUtils.getRedisKeyStyle(MsOnionReflectionUtils.getSuperclassParameterizedTypeSimpleName(this.getClass(), 0));
    }

    /**
     * @return
     * @throws MsOnionException
     * @Title: getTargetIdentification
     * @Description: 获取目标标识，其实就是目标POJO的名称的标识，例如：:ITEM:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:43:41
     */
    public String getTargetIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.targetIdentification)) {
            return this.targetIdentification;
        }
        return this.targetIdentification = String.format(":%s:", getTargetName());
    }

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
    @Override
    public String getSessionIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.sessionIdentification)) {
            return this.sessionIdentification;
        }
        // 没有前面的“:”
        this.sessionIdentification = String.format("%s:", this.DEFAULT_SESSION);
        return this.sessionIdentification;
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
    @Override
    public String getCountIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.countIdentification)) {
            return this.countIdentification;
        }
        // 没有前面的“:”
        this.countIdentification = String.format("%s:", this.DEFAULT_COUNT);
        return this.countIdentification;
    }

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
    @Override
    public String getCountName() throws MsOnionException {
        if (StringUtils.isNotBlank(this.countName)) {
            return this.countName;
        }
        this.countName = MsOnionRedisUtils.getRedisKeyStyle(DEFAULT_COUNT);
        return this.countName;
    }

    /**
     * @return
     * @throws MsOnionException 异常
     * @Title: getExampleName
     * @Description: 获取Example名称，例如：ITEMEXAMPLE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:47:49
     */
    public String getExampleName() throws MsOnionException {
        if (StringUtils.isNotBlank(this.exampleName)) {
            return this.exampleName;
        }
        return this.exampleName = MsOnionRedisUtils.getRedisKeyStyle(MsOnionReflectionUtils.getSuperclassParameterizedTypeSimpleName(this.getClass(), 1));
    }

    /**
     * @return
     * @throws MsOnionException
     * @Title: getExampleIdentification
     * @Description: 获取Example标识，例如：:ITEMEXAMPLE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:48:13
     */
    public String getExampleIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.exampleIdentification)) {
            return this.exampleIdentification;
        }
        return this.exampleIdentification = String.format(":%s:", getExampleName());
    }

    /**
     * @return
     * @Title: getSingleExampleName
     * @Description: 获取单一Example名称，例如：EXAMPLE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:41:55
     */
    public String getSingleExampleName() throws MsOnionException {
        if (StringUtils.isNotBlank(this.singleExampleName)) {
            return this.singleExampleName;
        }
        return this.singleExampleName = MsOnionRedisUtils.getRedisKeyStyle(this.DEFAULT_SINGLE_EXAMPLE);
    }

    /**
     * @return
     * @Title: getSingleExampleIdentification
     * @Description: 获取单一Example标识，例如：:EXAMPLE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:43:32
     */
    public String getSingleExampleIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.singleExampleIdentification)) {
            return this.singleExampleIdentification;
        }
        return this.singleExampleIdentification = String.format(":%s:", getSingleExampleName());
    }

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
    @Override
    public String getOneIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.oneIdentification)) {
            return this.oneIdentification;
        }
        return this.oneIdentification = String.format(":%s:", getOneName());
    }

    /**
     * Query One 的名称： ONE
     *
     * @return 返回 Query One 的名称： ONE
     * @throws MsOnionException 异常
     * @Title: getOneName
     * @Description: 获取 Query One 的名称： ONE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月28日 上午10:50:34
     */
    @Override
    public String getOneName() throws MsOnionException {
        if (MsOnionStringUtils.isNotBlank(this.oneName)) {
            return this.oneName;
        }
        return this.oneName = MsOnionRedisUtils.getRedisKeyStyle(DEFAULT_ONE);
    }

    /**
     * @return
     * @throws MsOnionIllegalArgumentException
     * @Title: getPageName
     * @Description: 获取PAGE名称，例如：PAGE
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:50:34
     */
    @Override
    public String getPageName() throws MsOnionException {
        if (StringUtils.isNotBlank(this.pageName)) {
            return this.pageName;
        }
        return this.pageName = MsOnionRedisUtils.getRedisKeyStyle(this.DEFAULT_PAGE);
    }

    /**
     * @return
     * @throws MsOnionException
     * @Title: getPageIdentification
     * @Description: 获取PAGE的标识，例如：:PAGE:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:51:55
     */
    @Override
    public String getPageIdentification() throws MsOnionException {
        if (StringUtils.isNotBlank(this.pageIdentification)) {
            return this.pageIdentification;
        }
        return this.pageIdentification = String.format(":%s:", getPageName());
    }

    /**
     * @return
     * @throws MsOnionException
     * @Title: getKey
     * @Description: 获取Redis中Key的Keys，更新Redis缓存需要使用到
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午2:01:47
     */
    public String getKey() throws MsOnionException {
        try {
//            String targetName = getTargetName();
            // 因为 targetIdentification 包括左右两边的 : (冒号) , 这里后面不需要有 : (冒号)
            //			String targetIdentification = getTargetIdentification();
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s%s%s", targetName, getPrefix(), getKeysIdentification(), targetName);
            return String.format("%s%s", getTargetName(), getKeysIdentification());
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param idx 主键idx
     * @return
     * @Title: getKey
     * @Description: 获取Redis中的Key，例如：ITEM_INFO:DESCRIPTION:3333
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午10:02:51
     */
    public String getKey(long idx) throws MsOnionException {
        try {
            if (idx <= 0 || idx >= Long.MAX_VALUE) {
                return null;
            }
//            String targetName = getTargetName();
//            String targetIdentification = getTargetIdentification();
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s%s%s", targetName, getPrefix(), targetIdentification, idx);

            return String.format("%s:%s", getTargetName(), idx);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param record 目标POJO是对象
     * @return
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午12:41:56
     */
    public String getKey(T record) throws MsOnionException {
        try {
            if (null == record) {
                return null;
            }
            if (StringUtils.isNotBlank(record.getId()) && StringUtils.isNumeric(record.getId())) {
                return getKey(Long.parseLong(record.getId()));
            }
//            String targetName = getTargetName();
//            String targetIdentification = getTargetIdentification();
            String json = MsOnionJsonUtils.objectToJson(record);
            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json);  // 32位
            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json); // 40位
            // item_info:contic:ex:md5
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s%s%s", targetName, getPrefix(), targetIdentification, md5);

            return String.format("%s:%s", getTargetName(), md5);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param example
     * @return
     * @throws MsOnionException
     * @Title: getKey
     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:23:22
     */
    @Override
    public String getKey(E example) throws MsOnionException {
        try {
            if (null == example) {
                return null;
            }
//            String targetName = getTargetName();
            // 不使用targetIdentification，因为 singleExampleIdentification 中已经包括左右两边:(冒号)
            //			String targetIdentification = getTargetIdentification();
//            String exampleName = getExampleName();
//            String singleExampleIdentification = getSingleExampleIdentification();
            String json = MsOnionJsonUtils.objectToJson(example);
            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json);  // 32位
            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json); // 40位
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s:%s%s%s:%s", targetName, getPrefix(), targetName,
// singleExampleIdentification, exampleName, md5);
//            return String.format("%s%s%s:%s", targetName, singleExampleIdentification, exampleName, md5);
            return String.format("%s%s%s", getTargetName(), getSingleExampleIdentification(), md5);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * getKeyForQueryOne，Query One 使用
     *
     * @param example
     * @return
     * @throws MsOnionException
     * @Title: getKey
     * @Description: 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，例如：ITEM_INFO:BASIC:0ffab0253b59f6d8adec7ff0e61740a7
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:23:22
     */
    @Override
    public String getKeyForQueryOne(E example) throws MsOnionException {
        try {
            if (null == example) {
                return null;
            }
//            String targetName = getTargetName();
            // 不使用targetIdentification，因为 singleExampleIdentification 中已经包括左右两边:(冒号)
            //			String targetIdentification = getTargetIdentification();
//            String exampleName = getExampleName();
//            String singleExampleIdentification = getSingleExampleIdentification();
//            String oneName = getOneName();
            String json = MsOnionJsonUtils.objectToJson(example);
            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json);  // 32位
            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json); // 40位
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s:%s%s%s:%s:%s", targetName, getPrefix(),
//                    targetName, singleExampleIdentification, exampleName, getOneName(), md5);

            return String.format("%s%s%s:%s",
                    getTargetName(), getSingleExampleIdentification(), getOneName(), md5);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取Redis的Key
     *
     * @param pageNum  分页，页码
     * @param pageSize 分页，每页数量
     * @return 返回Redis的Key
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key ，通过分页，例如：ITEM_INFO:DESCRIPTION:CONDITION:PAGE:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 上午11:27:22
     */
    public String getKey(int pageNum, int pageSize) throws MsOnionException {
        try {
            if (pageNum <= 0 || pageNum >= DEFAULT_MAX_INT_VALUE || pageSize <= 0 || pageSize >= DEFAULT_MAX_INT_VALUE) {
                return null;
            }
//            String targetName = getTargetName();
//            String pageIdentification = getPageIdentification();
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s:%s%s%s-%s", targetName, getPrefix(), targetName, pageIdentification, pageNum, pageSize);

            return String.format("%s%s%s-%s", getTargetName(), getPageIdentification(), pageNum, pageSize);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 生成（得到）Redis缓存的Key
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     * @throws MsOnionException 异常
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午1:45:32
     */
    public String getKey(E example, int pageNum, int pageSize) throws MsOnionException {
//		try {
//			if (null == example || pageNum <= 0 || pageNum >= DEFAULT_MAX_INT_VALUE
// || pageSize <= 0 || pageSize >= DEFAULT_MAX_INT_VALUE) {
//				return null;
//			}
//			String targetName = getTargetName();
//			String exampleName = getExampleName();
//			String pageIdentification = getPageIdentification();
//			String json = MsOnionJsonUtils.objectToJson(example);
//			String md5 = MsOnionSecurityUtils.md5ForRedisKey(json);
//			//			String sha1 = MsOnionSecuritySHAUtils.sha1(json);
//			// item_info:item_cat:contic:ex:md5:1-20
//			return String.format("%s%s:%s%s%s:%s:%s-%s", targetName, getPrefix(),
// targetName, pageIdentification, exampleName, md5, pageNum, pageSize);
//		} catch (Exception e) {
//			throw new MsOnionException(e);
//		}
        return this.getKey(example, pageNum, pageSize, null);
    }

    /**
     * 生成（得到）Redis缓存的Key
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param orderBy  排序
     * @return
     * @throws MsOnionException
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午1:45:32
     */
    @Override
    public String getKey(E example, int pageNum, int pageSize, String orderBy) throws MsOnionException {
        return this.getKey(example, pageNum, pageSize, orderBy, null);
    }

    /**
     * 生成（得到）Redis缓存的Key
     *
     * @param example  目标POJO的Example实例对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param orderBy  排序
     * @param groupBy  分组
     * @return
     * @throws MsOnionException
     * @Title: getKey
     * @Description: 获取Redis中的Key，有分页查询条件，例如：ITEM_INFO:BASIC:CONDITION:PAGE:EXAMPLE:c9c7a5dee80926f410937bde763d3520:1-20
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午1:45:32
     */
    @Override
    public String getKey(E example, int pageNum, int pageSize, String orderBy, String groupBy) throws MsOnionException {
        try {
            if (null == example || pageNum <= 0 || pageNum >= DEFAULT_MAX_INT_VALUE || pageSize <= 0 || pageSize >= DEFAULT_MAX_INT_VALUE) {
                return null;
            }
//            String targetName = getTargetName();
//            String exampleName = getExampleName();
//            String pageIdentification = getPageIdentification();
            String json = MsOnionJsonUtils.objectToJson(example);
            // 去掉JSON的左右空格
//			String md5 = MsOnionSecurityUtils.md5ForRedisKey(json.trim());
            String newOrderBy = "";
            String newGroupBy = "";
            if (StringUtils.isNotBlank(orderBy)) {
                // 全部转换成小写
                newOrderBy = orderBy.trim().toLowerCase();
            }
            if (StringUtils.isNotBlank(groupBy)) {
                // 全部转换成小写
                newGroupBy = groupBy.trim().toLowerCase();
            }

            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json.trim() + newOrderBy + newGroupBy);

            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json);
            // item_info:item_cat:contic:ex:md5:1-20
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s:%s%s%s:%s:%s-%s", targetName, getPrefix(), targetName,
// pageIdentification, exampleName, md5, pageNum, pageSize);

            return String.format("%s%s%s:%s-%s", getTargetName(), getPageIdentification(), md5, pageNum, pageSize);

        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @return 默认返回true，购物车需要重写，返回false，手动更新Redis缓存
     * @Title: isGlobalInfluenceRedisCache
     * @Description: 是否全局影响的缓存，例如：购物车和用户关联，不是全局影响的；商品是所有用户关注的，因此商品是全局影响的
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:12:16
     */
    public boolean isGlobalInfluenceRedisCache() {
        return true;
    }

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
    @Override
    public String getCountKey(long idx) throws MsOnionException {
        try {
            if (idx <= 0 || idx >= Long.MAX_VALUE) {
                return null;
            }
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s%s%s%s",
//                    getTargetName(), getPrefix(), getTargetIdentification(),
//                    getCountIdentification(), idx);
            return String.format("%s:%s%s",
                    getTargetName(),
                    getCountIdentification(), idx);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

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
    @Override
    public String getCountKey(T record) throws MsOnionException {
        try {
            if (null == record) {
                return null;
            }
            if (StringUtils.isNotBlank(record.getId()) && StringUtils.isNumeric(record.getId())) {
                return getKey(Long.parseLong(record.getId()));
            }

            String json = MsOnionJsonUtils.objectToJson(record);
            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json);  // 32位
            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json); // 40位
            // item_info:contic:ex:count:md5
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s%s%s%s",
//                    getTargetName(), getPrefix(), getTargetIdentification(),
//                    getCountIdentification(), md5);
            return String.format("%s:%s%s",
                    getTargetName(),
                    getCountIdentification(), md5);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取Redis中的Key，都是使用Jackson转换成Json，不需要考虑Json排序，
     * 例如：ITEM:COUNT:0ffab0253b59f6d8adec7ff0e61740a7
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
    @Override
    public String getCountKey(E example) throws MsOnionException {
        try {
            if (null == example) {
                return null;
            }
//            String targetName1 = getTargetName();
            // 不使用targetIdentification，因为 singleExampleIdentification 中已经包括左右两边:(冒号)
            //			String targetIdentification = getTargetIdentification();
            String json = MsOnionJsonUtils.objectToJson(example);
            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json);  // 32位
            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json); // 40位
            // MEMBER_INFO:MEMBER:COUNT:EXAMPLE:MEMBEREXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s:%s:%s%s%s:%s",
//                    targetName1, getPrefix(), targetName1, getCountName(),
//                    getSingleExampleIdentification(), getExampleName(), md5);
            return String.format("%s:%s%s%s",
                    getTargetName(), getCountName(),
                    getSingleExampleIdentification(), md5);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    ///////////////////////// 获取Count查询的Key， End //////////////////////////


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
    @Override
    public String getKeyForQueryRandom(E example, int pageNum, int pageSize, String orderBy) throws MsOnionException {
        try {
            if (null == example || pageNum <= 0 || pageNum >= DEFAULT_MAX_INT_VALUE || pageSize <= 0 || pageSize >= DEFAULT_MAX_INT_VALUE) {
                return null;
            }
            String json = MsOnionJsonUtils.objectToJson(example);
            // 去掉JSON的左右空格
//			String md5 = MsOnionSecurityUtils.md5ForRedisKey(json.trim());
            String newOrderBy = "";
//            String newGroupBy = "";
            if (StringUtils.isNotBlank(orderBy)) {
                // 全部转换成小写
                newOrderBy = orderBy.trim().toLowerCase();
            }
//            if (StringUtils.isNotBlank(groupBy)) {
//                // 全部转换成小写
//                newGroupBy = groupBy.trim().toLowerCase();
//            }

//            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json.trim() + newOrderBy + newGroupBy);

            // 不支持 分组 groupBy
            String md5 = MsOnionSecurityUtils.md5ForRedisKey(json.trim() + newOrderBy);

            //			String sha1 = MsOnionSecuritySHAUtils.sha1(json);
            // item_info:item_cat:contic:ex:md5:1-20
            // 优化Redis的Key的长度 ， modify by @ 2017-07-04 22:45
//            return String.format("%s%s:%s%s%s:%s:%s-%s", targetName, getPrefix(), targetName,
// pageIdentification, exampleName, md5, pageNum, pageSize);

//            return String.format("%s%s%s:%s-%s", getTargetName(), getRandomIdetification(), md5, pageNum, pageSize);

            return String.format("%s%s%s:%s:%s-%s",
                    getTargetName(), getSingleExampleIdentification(), getRandomName(), md5, pageNum, pageSize);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

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
    @Override
    public String getRandomIdetification() throws MsOnionException {
        if (MsOnionStringUtils.isNotBlank(this.randomIdentification)) {
            return this.randomIdentification;
        }
        this.randomIdentification = MsOnionRedisUtils.getRedisKeyIdentificationStyle(DEFAULT_RANDOM);
        return this.randomIdentification;
    }

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
    @Override
    public String getRandomName() throws MsOnionException {
        if (MsOnionStringUtils.isNotBlank(this.randomName)) {
            return this.randomName;
        }
        this.randomName = MsOnionRedisUtils.getRedisKeyStyle(DEFAULT_RANDOM);
        return this.randomName;
    }

    //////////////////////// 获取 Query Random 随机查询的 Key , End #######//////////////////


}
