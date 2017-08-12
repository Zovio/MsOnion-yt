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
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.parent.ext.utils.zookeeper;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.pojo.MsOnionAtomicValueResult;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.atomic.MsOnionDistributedAtomicLong;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.recipes.atomic.AtomicValue;

/**
 * @Title: MsOnionDistributedAtomicLongUtils.java
 * @Package: cc.msonion.carambola.commons.common.utils.distributed
 * @Description: 分布式原子性Long类型值工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月24日 下午3:36:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月24日 下午3:36:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：获取分布式架构中，成员（公司内部）的主键idx
 */

/**
 * 分布式原子性Long类型值工具类
 *
 * @ClassName: MsOnionDistributedAtomicLongUtils
 * @Description: 分布式原子性Long类型值工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月24日 下午3:36:42
 */
public final class MsOnionDistributedAtomicLongUtils {

    private MsOnionDistributedAtomicLongUtils() {

    }

    /**
     * Zookeeper 根Base目录，例如：/www.msyc.cc/carambola/atomic
     */
    public static final String ZK_ROOT_COUNTER_BASE_PATH = "/www.msyc.cc/carambola/atomic";

    /**
     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode
     */
    public static final String ZK_ROOT_COUNTER_IDX_CODE_PATH = String.format("%s/longIdxCode", ZK_ROOT_COUNTER_BASE_PATH); // "/www.msyc.cc/carambola/atomic/longIdxCode";

//    /**
//     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/5Digits
//     */
//    public static final String ZK_ROOT_COUNTER_IDX_CODE_5_DIGITS_PATH
// = String.format("%s/5Digits", ZK_ROOT_COUNTER_IDX_CODE_PATH); // "/www.msyc.cc/carambola/atomic/longIdxCode";
//
//    /**
//     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/4Digits
//     */
//    public static final String ZK_ROOT_COUNTER_IDX_CODE_4_DIGITS_PATH
// = String.format("%s/4Digits", ZK_ROOT_COUNTER_IDX_CODE_PATH); // "/www.msyc.cc/carambola/atomic/longIdxCode";

//    /**
//     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/9Digits
//     */
//    public static final String ZK_ROOT_COUNTER_IDX_CODE_9_DIGITS_PATH
// = String.format("%s/9Digits", ZK_ROOT_COUNTER_IDX_CODE_PATH); // "/www.msyc.cc/carambola/atomic/longIdxCode";

    /**
     * Zookeeper 根目录，主要是主键idx专用，例如：/www.msyc.cc/carambola/atomic/longIdx
     */
    public static final String ZK_ROOT_COUNTER_PATH = String.format("%s/longIdx", ZK_ROOT_COUNTER_BASE_PATH); // "/www.msyc.cc/carambola/atomic/long";

    /**
     * Zookeeper 根目录，版本号专用的，例如：/www.msyc.cc/carambola/atomic/longVersion
     */
    public static final String ZK_ROOT_COUNTER_VERSION_PATH = String.format("%s/longVersion", ZK_ROOT_COUNTER_BASE_PATH); // "/www.msyc.cc/carambola/atomic/longVersion";

    /**
     * Zookeeper 根目录，商品货号编码、商品品牌编码 专用，例如：/www.msyc.cc/carambola/atomic/longItemCode
     */
    public static final String ZK_ROOT_COUNTER_ITEM_CODE_PATH = String.format("%s/longItemCode", ZK_ROOT_COUNTER_BASE_PATH); // "/www.msyc.cc/carambola/atomic/longItemCode";

//    /**
//     * Zookeeper 根目录，成员（Member）编码、用户（User）编码 专用
//     */
//    public static final String ZK_ROOT_COUNTER_USER_MEMBER_CODE_PATH = "/www.msyc.cc/carambola/atomic/longUMCode";

    /**
     * Zookeeper 根目录，Code编码 专用，/www.msyc.cc/carambola/atomic/longCode
     */
    public static final String ZK_ROOT_COUNTER_CODE_PATH = String.format("%s/longCode", ZK_ROOT_COUNTER_BASE_PATH);; // "/www.msyc.cc/carambola/atomic/longCode";

    /**
     * Zookeeper log日志目录，例如：/www.msyc.cc/carambola/atomic/longLog
     */
    public static final String ZK_ROOT_COUNTER_LOG_PATH = String.format("%s/longLog", ZK_ROOT_COUNTER_BASE_PATH); // "/www.msyc.cc/carambola/atomic/longLog";

    /**
     * Zookeeper redis目录，例如：/www.msyc.cc/carambola/atomic/longRedis
     */
    public static final String ZK_ROOT_COUNTER_REDIS_PATH = String.format("%s/longRedis", ZK_ROOT_COUNTER_BASE_PATH); // "/www.msyc.cc/carambola/atomic/longRedis";


    /**
     * 获取分布式架构，全局唯一Long类型的主键idx递增1之后的值
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item/code/8081
     * @return Long
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常
     * @throws MsOnionException                MsOnion异常类
     * @Title: getIdx
     * @Description: 获取分布式架构，全局唯一Long类型的主键idx递增1之后的值
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午4:56:37
     */
    public static Long getIdx(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient, RetryPolicy retryPolicy, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 得到递增1L之后的值
        return getIdxAtomicValue(msOnionDomain, zkClient, retryPolicy, modulePath).getAtomicValue().postValue();
    }

    /**
     * 获取分布式架构中，Long类型递增主键idx递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     *
     * @param msOnionDomain 域名
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Title: getIdxAtomicValue
     * @Description: 获取分布式架构中，Long类型递增主键idx递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午4:58:04
     */
    public static MsOnionAtomicValueResult<Long> getIdxAtomicValue(
            MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
            RetryPolicy retryPolicy, String modulePath) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
//            String counterPath = generateZKCounterPath(msOnionDomain, modulePath);
//            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
//                    counterPath, retryPolicy);
//            // 在原始值基础上递增1L
//            AtomicValue<Long> atomicValue = distributedAtomicLong.add(1L);
//
//            // 创建 MsOnionAtomicValueResult
//            MsOnionAtomicValueResult<Long> atomicValueResult = new MsOnionAtomicValueResult<Long>();
//            atomicValueResult.setAtomicValue(atomicValue);
//            atomicValueResult.setZkRootCounterPath(ZK_ROOT_COUNTER_PATH);
//            atomicValueResult.setZkCounterPath(counterPath);
//            // 返回 atomicValueResult
//            return atomicValueResult;

            return getAtomicValue(msOnionDomain, zkClient, retryPolicy, ZK_ROOT_COUNTER_PATH, modulePath);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取分布式架构，全局唯一Long类型的版本号Version递增1之后的值
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item/code/8081
     * @return Long
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常
     * @throws MsOnionException                MsOnion异常类
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午4:56:37
     */
    public static Long getVersion(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                  RetryPolicy retryPolicy, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 得到递增1L之后的值
        return getVersionAtomicValue(msOnionDomain, zkClient, retryPolicy, modulePath).getAtomicValue().postValue();
    }

    /**
     * 获取分布式架构中，Long类型递增版本号Version递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     *
     * @param msOnionDomain 域名
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月7日 下午4:58:04
     */
    public static MsOnionAtomicValueResult<Long> getVersionAtomicValue(
            MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
            RetryPolicy retryPolicy, String modulePath) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            return getAtomicValue(msOnionDomain, zkClient, retryPolicy, ZK_ROOT_COUNTER_VERSION_PATH, modulePath);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取分布式架构，全局唯一Long类型的商品货号编码递增1之后的值
     * <p>当前方法已经废弃，建议使用：getCode 方法</p>
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item/code/8081
     * @return Long
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常
     * @throws MsOnionException                MsOnion异常类
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午4:56:37
     */
    @Deprecated
    public static Long getItemCode(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                   RetryPolicy retryPolicy, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 得到递增1L之后的值
        return getItemCodeAtomicValue(msOnionDomain, zkClient, retryPolicy, modulePath).getAtomicValue().postValue();
    }

    /**
     * 获取分布式架构中，Long类型递增商品货号编码递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     * <p>当前方法已经废弃，建议使用：getCodeAtomicValue 方法</p>
     *
     * @param msOnionDomain 域名
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月7日 下午4:58:04
     */
    @Deprecated
    public static MsOnionAtomicValueResult<Long> getItemCodeAtomicValue(
            MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
            RetryPolicy retryPolicy, String modulePath) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            return getAtomicValue(msOnionDomain, zkClient, retryPolicy, ZK_ROOT_COUNTER_ITEM_CODE_PATH, modulePath);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取分布式架构，全局唯一Long类型的商品品牌编码递增1之后的值
     * <p>当前方法已经废弃，建议使用：getCode 方法</p>
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item/code/8081
     * @return Long
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常
     * @throws MsOnionException                MsOnion异常类
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午4:56:37
     */
    @Deprecated
    public static Long getBrandCode(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                    RetryPolicy retryPolicy, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 得到递增1L之后的值
        return getBrandCodeAtomicValue(msOnionDomain, zkClient, retryPolicy, modulePath).getAtomicValue().postValue();
    }

    /**
     * 获取分布式架构中，Long类型递增商品品牌编码递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     * <br/><p>当前方法已经废弃，建议使用：getCodeAtomicValue 方法</p>
     * @param msOnionDomain 域名
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月7日 下午4:58:04
     */
    @Deprecated
    public static MsOnionAtomicValueResult<Long> getBrandCodeAtomicValue(
            MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
            RetryPolicy retryPolicy, String modulePath) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            return getAtomicValue(msOnionDomain, zkClient, retryPolicy, ZK_ROOT_COUNTER_ITEM_CODE_PATH, modulePath);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取分布式架构，全局唯一Long类型的Code编码递增1之后的值
     *
     * @param msOnionDomain 域名
     * @param zkClient      Zookeeper客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item/code/8081
     * @return Long
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常
     * @throws MsOnionException                MsOnion异常类
     * @Title: getCode
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午4:56:37
     */
    public static Long getCode(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                    RetryPolicy retryPolicy, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        // 得到递增1L之后的值
        return getCodeAtomicValue(msOnionDomain, zkClient, retryPolicy, modulePath).getAtomicValue().postValue();
    }

    /**
     * 获取分布式架构中，Long类型递增Code编码递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     *
     * @param msOnionDomain 域名
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/item
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Title: getCodeAtomicValue
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月7日 下午4:58:04
     */
    public static MsOnionAtomicValueResult<Long> getCodeAtomicValue(
            MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
            RetryPolicy retryPolicy, String modulePath) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            return getAtomicValue(msOnionDomain, zkClient, retryPolicy, ZK_ROOT_COUNTER_CODE_PATH, modulePath);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取分布式架构中，Long类型递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     *
     * @param msOnionDomain 域名
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param rootPath      根路径，例如：/www.msyc.cc/carambola/atomic/long
     * @param modulePath    模块路径，例如：/item
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Title: getAtomicValue
     * @Description: 获取分布式架构中，Long类型递增主键idx递增1L的原子性值，是否成功：atomicValue.succeeded()，
     * 原始值：atomicValue.preValue()，递增之后最新值：atomicValue.postValue()
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年4月7日 下午4:58:04
     */
    private static MsOnionAtomicValueResult<Long> getAtomicValue(
            MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
            RetryPolicy retryPolicy, String rootPath, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            String counterPath = generateZKCounterPath(msOnionDomain, modulePath);
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    counterPath, retryPolicy);
            // 在原始值基础上递增1L , increment 也可以，相同！！！
            AtomicValue<Long> atomicValue = distributedAtomicLong.add(1L);

            // 创建 MsOnionAtomicValueResult
            MsOnionAtomicValueResult<Long> atomicValueResult = new MsOnionAtomicValueResult<Long>();
            atomicValueResult.setAtomicValue(atomicValue);
            atomicValueResult.setZkRootCounterPath(rootPath);
            atomicValueResult.setZkCounterPath(counterPath);
            // 返回 atomicValueResult
            return atomicValueResult;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 生成Zookeeper的CounterPath
     * @param msOnionDomain 域名POJO实例对象
     * @param modulePath    模块路径，例如：/item/code/8081
     * @return String
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Title: generateZKCounterPath
     * @Description: 生成Zookeeper的CounterPath
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月24日 下午3:57:43
     */
    public static String generateZKCounterPath(MsOnionDomain msOnionDomain, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (null == msOnionDomain) {
            throw new MsOnionIllegalArgumentException(String.format("%s # generateZKCounterPath方法，domain参数不可以为null",
                    MsOnionDistributedAtomicLongUtils.class.getName()));
        }

        if (StringUtils.isEmpty(modulePath)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # generateZKCounterPath方法， modulePath参数不可以为null",
                    MsOnionDistributedAtomicLongUtils.class.getName()));
        }

        String env = msOnionDomain.getEnvironment();
        if (StringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # generateZKCounterPath方法， 环境配置为null",
                    MsOnionDistributedAtomicLongUtils.class.getName()));
        }

        modulePath = modulePath.trim();
        modulePath = modulePath.replace(" ", "");

        if (modulePath.length() == 1 && modulePath.startsWith("/")) {
            throw new MsOnionIllegalArgumentException("MsOnionDistributedAtomicLongUtils # generateZKCounterPath方法， "
                    + "modulePath参数非法，modulePath=" + modulePath);
        }

        if (!modulePath.startsWith("/")) {
            modulePath = String.format("/%s", modulePath);
        }

        if (modulePath.endsWith("/")) {
            modulePath = modulePath.substring(0, modulePath.length() - 1);
        }

        // 开发环境
        /*if (MsOnionDevelopUtils.ENVIRONMENT_DEV.equalsIgnoreCase(env)) {
            counterPath = String.format("%s/%s/%s", counterPath, env, modulePath);
        } else if (MsOnionDevelopUtils.ENVIRONMENT_TEST.equalsIgnoreCase(env)) {
            counterPath += modulePath;
        }*/

        // 不是 "%s/%s/%s" ，而是 "%s/%s%s" ，因为 modulePath 已经包括 "/"
        return String.format("%s/%s%s", ZK_ROOT_COUNTER_PATH, env, modulePath);
    }

    /**
     * 不可以实现递减，因为分布式架构多线程，会出现Idx 重复 ！！！
     *
     * @param domain
     * @param zkClient
     * @param retryPolicy
     * @param modulePath
     * @return
     * @throws Exception
     * @Title: rollbackIdx
     * @Description: 回滚Idx的值，也就是递减1L
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月1日 下午11:12:58
     */
    /*public static Long rollbackIdx(Domain domain, MsOnionCuratorZookeeperClient zkClient, RetryPolicy retryPolicy, String modulePath)
            throws Exception {
        // 得到递减1L的原子性值
        return rollbackIdxAtomicValue(domain, zkClient, retryPolicy, modulePath).getAtomicValue().postValue();
    }*/

    /**
     * @param domain
     * @param zkClient
     * @param retryPolicy
     * @param modulePath
     * @return
     * @throws Exception
     */
    /*public static MsOnionAtomicValueResult<Long> rollbackIdxAtomicValue(Domain domain, MsOnionCuratorZookeeperClient zkClient,
                                                                        RetryPolicy retryPolicy, String modulePath) throws Exception {
        String counterPath = generateZKCounterPath(domain, modulePath);
        MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                counterPath, retryPolicy);
        // 在原始值基础上递增-1L（也就是递减1L）
        AtomicValue<Long> atomicValue = distributedAtomicLong.add(-1L);

        // 创建 MsOnionAtomicValueResult
        MsOnionAtomicValueResult<Long> atomicValueResult = new MsOnionAtomicValueResult<Long>();
        atomicValueResult.setAtomicValue(atomicValue);
        atomicValueResult.setZkRootCounterPath(ZK_ROOT_COUNTER_PATH);
        atomicValueResult.setZkCounterPath(counterPath);
        // 返回 atomicValueResult
        return atomicValueResult;

        return doIdxAtomicValue(domain, zkClient, retryPolicy, modulePath, -1L);
    }*/

    /**
     * @param msOnionDomain 域名POJO实例对象
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param modulePath    模块路径，例如：/msyt-collector/item/idx
     * @param value         递增值，如果为-1L那么就是递减1L
     * @return MsOnionAtomicValueResult<Long>
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     */
    /*public static MsOnionAtomicValueResult<Long> doIdxAtomicValue(MsOnionDomain msOnionDomain, MsOnionCuratorZookeeperClient zkClient,
                                                                  RetryPolicy retryPolicy, String modulePath, Long value)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            String counterPath = generateZKCounterPath(msOnionDomain, modulePath);
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    counterPath, retryPolicy);
            // 在原始值基础上递增-1L（也就是递减1L）
            AtomicValue<Long> atomicValue = distributedAtomicLong.add(value);  // -1L , 1L

            // 创建 MsOnionAtomicValueResult
            MsOnionAtomicValueResult<Long> atomicValueResult = new MsOnionAtomicValueResult<Long>();
            atomicValueResult.setAtomicValue(atomicValue);
            atomicValueResult.setZkRootCounterPath(ZK_ROOT_COUNTER_PATH);
            atomicValueResult.setZkCounterPath(counterPath);
            // 返回 atomicValueResult
            return atomicValueResult;
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }*/

    /**
     * @param suffix 后缀，例如：货号编码编码规则：1011****20，20可以作为后缀，没有可以为null
     * @return
     */
    /*public String test(String suffix) {
        return null;
    }*/
}
