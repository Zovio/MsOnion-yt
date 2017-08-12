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

/**
 * Zookeeper工具类
 *
 * @Title: MsOnionZookeeperUtils
 * @Package: cc.msonion.carambola.parent.ext.utils.zookeeper
 * @Description: Zookeeper工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月29日 下午3:36:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月29日 下午3:36:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

import cc.msonion.carambola.parent.common.enums.MsOnionLogLevel;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFilenameUtils;
import cc.msonion.carambola.parent.interfaces.log.adapter.MsOnionLoggerAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.atomic.MsOnionDistributedAtomicInteger;
import cc.msonion.carambola.parent.pojo.zk.atomic.MsOnionDistributedAtomicLong;
import cc.msonion.carambola.parent.pojo.zk.children.MsOnionZookeeperPathChild;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.recipes.atomic.AtomicValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Zookeeper工具类
 *
 * @ClassName: MsOnionZookeeperUtils
 * @Description: Zookeeper工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月29日 下午3:36:42
 */
public final class MsOnionZookeeperUtils {

    /////////////////// 生成 idx_code ########## Begin /////////////////////////////////

//    /**
//     * idx_code 9位初始值：000001000，必须是9位数 ，不采取5,4两种集合的原因，4位（千）一天最多只能有9999值，超过不行
//     * <p>2017070810000200098，就是：100002000</p>
//     */
//    private static final long IDX_CODE_9_DIGITS_INITIALIZE_VALUE = 1; //1000;

    /**
     * idx_code 9位初始值：000001000，必须是9位数 ，不采取5,4两种集合的原因，4位（千）一天最多只能有9999值，超过不行
     * <p>2017070810000200098，就是：100002000</p>
     */
//    private static final long[] IDX_CODE_9_DIGITS_INITIALIZE_VALUES = {10000, 20000, 30000, 40000, 50000, 60000}; // 不包括60000;
    private static final long[] IDX_CODE_9_DIGITS_INITIALIZE_VALUES = {1000, 2000, 3000, 4000, 5000, 6000}; // 不包括6000;

    /**
     * idx_code 17位初始值：000001000，必须是17位数 ，不采取5,4两种集合的原因，4位（千）一天最多只能有9999值，超过不行
     * <p>2017070810000200098，就是：100002000</p>
     */
//    private static final long[] IDX_CODE_9_DIGITS_INITIALIZE_VALUES = {10000, 20000, 30000, 40000, 50000, 60000}; // 不包括60000;
//    private static final long[] IDX_CODE_17_DIGITS_INITIALIZE_VALUES = {1000, 2000, 3000, 4000, 5000};
    private static final long[] IDX_CODE_17_DIGITS_INITIALIZE_VALUES = {1000, 2000, 3000};

//    /**
//     * idx_code 5位初始值：01000，必须是5位数，不可以是01000
//     * <p>2017070810000200098，就是：10000</p>
//     */
//    private static final long IDX_CODE_5_DIGITS_INITIALIZE_VALUE = 1; //1000;
//
//    /**
//     * idx_code 4位初始值：01000，必须是4位数，不可以是0100
//     * <p>2017070810000200098，就是：2000</p>
//     */
//    private static final long IDX_CODE_4_DIGITS_INITIALIZE_VALUE = 1; //100;

//    /**
//     * idx_code 5位数的step，默认是1
//     */
//    private static final long IDX_CODE_5_DIGITS_STEP = 1;
//
//    /**
//     * idx_code 4位数的step，默认是2
//     */
//    private static final long IDX_CODE_4_DIGITS_STEP = 2;

    /**
     * idx_code 9位数的step数组，不会有使用6，因为随机，因此不包括最大值，加上6容易控制而已，
     * <p>由于所有的模块都使用，因此step不要太大，如果只是一个模块，例如订单使用，可以大一些</p>
     */
    private static final int[] IDX_CODE_9_DIGITS_STEPS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 不包括10
//    private static final int[] IDX_CODE_9_DIGITS_STEPS = {1, 2, 3, 4}; // 不包括4

    /**
     * idx_code 9位数的step数组，不会有使用6，因为随机，因此不包括最大值，加上6容易控制而已，
     * <p>由于所有的模块都使用，因此step不要太大，如果只是一个模块，例如订单使用，可以大一些</p>
     */
    private static final int[] IDX_CODE_17_DIGITS_STEPS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 不包括10
//    private static final int[] IDX_CODE_9_DIGITS_STEPS = {1, 2, 3, 4}; // 不包括4

    /**
     * idx_code 2位数的随机，最小值为：0
     */
    private static final int IDX_CODE_2_DIGITS_RANDOM_MIN_VALUE = 0;

    /**
     * idx_code 2位数的随机，最大值为：100
     */
    private static final int IDX_CODE_2_DIGITS_RANDOM_MAX_VALUE = 100;

    /**
     * idx_code 9位数的随机，固定长度：9
     */
    private static final int IDX_CODE_9_DIGITS_LENGTH = 9;

//    /**
//     * idx_code 5位数的随机，固定长度：5
//     */
//    private static final int IDX_CODE_5_DIGITS_LENGTH = 5;
//
//    /**
//     * idx_code 4位数的随机，固定长度：4
//     */
//    private static final int IDX_CODE_4_DIGITS_LENGTH = 4;

    /**
     * idx_code 2位数的随机，固定长度：2
     */
    private static final int IDX_CODE_2_DIGITS_RANDOM_LENGTH = 2;

    /**
     * 生成idxCode的yyyyMMdd非法
     */
    private static final String IDX_CODE_YYYY_MM_DD_ILLEGAL = "生成idxCode的yyyyMMdd非法";

//    /**
//     * 生成idxCode的5位数非法
//     */
//    private static final String IDX_CODE_5_DIGITS_ILLEGAL = "生成idxCode的5位数非法";
//
//    /**
//     * 生成idxCode的4位数非法
//     */
//    private static final String IDX_CODE_4_DIGITS_ILLEGAL = "生成idxCode的4位数非法";

    /**
     * 生成idxCode的9位数非法
     */
    private static final String IDX_CODE_9_DIGITS_ILLEGAL = "生成idxCode的9位数非法";

    /**
     * 生成idxCode的17位数非法
     */
    private static final String IDX_CODE_17_DIGITS_ILLEGAL = "生成idxCode的17位数非法";

    /**
     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode
     */
    public static final String ZK_ROOT_COUNTER_IDX_CODE_PATH = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_IDX_CODE_PATH; // "/www.msyc.cc/carambola/atomic/longIdxCode";

//    /**
//     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/5Digits
//     */
//    public static final String ZK_ROOT_COUNTER_IDX_CODE_5_DIGITS_PATH =
// MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_IDX_CODE_5_DIGITS_PATH; // "/www.msyc.cc/carambola/atomic/longIdxCode/5Digits";
//
//    /**
//     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/4Digits
//     */
//    public static final String ZK_ROOT_COUNTER_IDX_CODE_4_DIGITS_PATH =
// MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_IDX_CODE_4_DIGITS_PATH; // "/www.msyc.cc/carambola/atomic/longIdxCode/4Digits";

//    /**
//     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/9Digits
//     */
//    private static final String ZK_ROOT_COUNTER_IDX_CODE_9_DIGITS_PATH
// = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_IDX_CODE_9_DIGITS_PATH; // "/www.msyc.cc/carambola/atomic/longIdxCode/4Digits";

    /**
     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/dev/9Digits
     */
    private static final String ZK_ROOT_COUNTER_IDX_CODE_9_DIGITS_PATH = "9Digits";

    /**
     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longIdxCode/dev/17Digits
     */
    private static final String ZK_ROOT_COUNTER_IDX_CODE_17_DIGITS_PATH = "17Digits";


    /////////////////// 生成 idx_code ########## End /////////////////////////////////

    /**
     * Zookeeper 根Base目录，例如：/www.msyc.cc/carambola/atomic
     */
    public static final String ZK_ROOT_COUNTER_BASE_PATH = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;

    /**
     * 反斜线：/
     */
    private static final String ZK_ROOT_PATH_BACKSLASH = "/";

    /**
     * default watch
     */
    private static final boolean DEFAULT_WATCH = true;

    /**
     * default recursively：默认不是递归，为：false
     */
    private static final boolean DEFAULT_RECURSIVELY = false;

    ///////////////////// 日志级别 ### Begin #### //////////////////////////////

    /**
     * Zookeeper idxCode目录，例如：/www.msyc.cc/carambola/atomic/longLog
     */
    public static final String ZK_ROOT_COUNTER_LOG_PATH = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_LOG_PATH; // "/www.msyc.cc/carambola/atomic/longLog";

    /**
     * 日志级别：logLevel
     */
    private static final String ZK_COUNTER_LOG_LEVEL_PATH = "logLevel";

    /**
     * 日志是否打印到控制台路径：print2Console
     */
    private static final String ZK_COUNTER_PRINT_TO_CONSOLE_PATH = "print2Console";

    /**
     * 默认日志打印到控制台的值为：1L
     */
    private static final long DEFAULT_PRINT_TO_CONSOLE_VALUE = 1L;

    /**
     * 分析SQL执行的耗时级别，打印日志，例如：耗时超过100毫秒才打印日志
     */
    private static final String ZK_COUNTER_SQL_COST_TIME_LEVEL_PATH = "sqlCostTimeLevel";

    /**
     * SQL耗时时间，100毫秒
     */
    private static final long DEFAULT_SQL_COST_TIME_LEVEL = 100L;

    //////////////////// 日志级别 ### End #### //////////////////////////////

    //////////////////// Redis缓存配置 ### Begin #### //////////////////////////////

    /**
     * Redis全局查询缓存是否禁用，默认值：1L（不禁用缓存，也就是开启）
     */
    public static final long DEFAULT_REDIS_CACHE_GLOBAL_QUERY_DISABLED = 1L;

    /**
     * Redis全局缓存周期，默认值：1天（24小时）
     * <p># Redis Global Expire : 60 * 60 * 24 => 86400
     * ## 60 * 60 * 24 * 1 = 1 day</p>
     */
    public static final long DEFAULT_REDIS_CACHE_GLOBAL_EXPIRE = 86400L;

    /**
     * Redis全局查询缓存是否禁用路径
     */
    private static final String ZK_COUNTER_REDIS_CACHE_GLOBAL_QUERY_DISABLED_PATH = "cache/globalQueryDisabled";

    /**
     * Redis全局缓存周期的路径
     */
    private static final String ZK_COUNTER_REDIS_CACHE_GLOBAL_EXPIRE_PATH = "cache/globalExpire";

    /**
     * Zookeeper redis目录，例如：/www.msyc.cc/carambola/atomic/longRedis
     */
    private static final String ZK_COUNTER_REDIS_PATH = MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_REDIS_PATH;

    //////////////////// Redis缓存配置 ### End #### //////////////////////////////

    //////////////////// 验证码，随机查询数据库的数量，配置 ### End #### //////////////////////////////

//    /**
//     * 验证码，随机查询数据库图片的数量，默认为：10
//     */
//    public static final int DEFAULT_VERIFICATION_CODE_RANDOM_QUERY_IMAGE_COUNT = 10;

    /**
     * 默认验证码自动的周期，也就是多少时间内，有1次不需要验证码，单位：秒
     * <p>例如：30秒内，只能1次不需要验证码，也就是无论验证码是否正常，都不校验验证码，</p>
     * <p>包括所有的账号，super、admin，解决验证码出现问题，无法登陆后台，所有人都卡住，提供的容灾方案</p>
     */
    public static final int DEFAULT_VERIFY_CODE_AUTO_EXPIRE = 600;

    /**
     * 默认开启验证码，如果为1就是开启验证码，其他值都是不开启验证码
     */
    public static final long DEFAULT_ENABLE_VERIFY_CODE = 1;

    /**
     * 开启验证码的路径
     */
    private static final String ZK_COUNTER_REDIS_ENABLE_VERIFY_CODE_PATH = "verifyCode/enable";

    /**
     * 验证码自动周期路径
     */
    private static final String ZK_COUNTER_REDIS_VERIFY_CODE_AUTO_EXPIRE_PATH = "verifyCode/autoExpire";

    //////////////////// 验证码，随机查询数据库的数量， ### End #### //////////////////////////////

    private MsOnionZookeeperUtils() {

    }

    /**
     * Zookeeper 根Base目录，例如：/www.msyc.cc/carambola/atomic
     *
     * @return Zookeeper 根Base目录，例如：/www.msyc.cc/carambola/atomic
     */
    public static String getZookeeperCounterRootBasePath() {
        return MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH;
    }

    /**
     * 获取 Zookeeper的 Counter的根路径的所有子路径
     *
     * @param zkClient ZK客户端
     * @return Zookeeper的 Counter的根路径的所有子路径
     * @throws MsOnionException 异常
     */
    public static List<String> getCounterRootBasePathChildren(MsOnionCuratorZookeeperClient zkClient)
            throws MsOnionException {
        return getCounterRootBasePathChildren(null, zkClient);
    }

    /**
     * 获取 Zookeeper的 Counter的根路径的所有子目录
     *
     * @param log      日志
     * @param zkClient Zookeeper客户端
     * @return 返回Zookeeper的 Counter的根路径的所有子路径
     * @throws MsOnionException 异常
     */
    public static List<String> getCounterRootBasePathChildren(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient)
            throws MsOnionException {
        try {
//            return zkClient.getCuratorFramework().getZookeeperClient().getZooKeeper()
//                    .getChildren(getZookeeperCounterRootBasePath(), DEFAULT_WATCH);
            return zkClient.getCuratorFramework().getZookeeperClient().getZooKeeper()
                    .getChildren(ZK_ROOT_COUNTER_BASE_PATH, DEFAULT_WATCH);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取ZK 根路径下，所有子目录，
     * <p>一次性加载所有子目录</p>
     *
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterRootPathChildren(MsOnionCuratorZookeeperClient zkClient
            , String modulePath) throws MsOnionException {
        return getCounterRootPathChildren(null, zkClient);
    }

    /**
     * 获取ZK 根路径下，所有子目录，
     * <p>一次性加载所有子目录，使用children 获取子目录集合</p>
     *
     * @param log      日志
     * @param zkClient ZK客户端
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterRootPathChildren(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient)
            throws MsOnionException {
        return getCounterPathChildren(log, zkClient, null);
    }

    /**
     * 获取ZK 指定路径下，所有子目录，
     * <p>一次性加载所有子目录，使用children 获取子目录集合</p>
     *
     * @param log        日志
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterPathChildren(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient
            , String modulePath) throws MsOnionException {

        String counterPath = null;
        if (MsOnionStringUtils.isBlank(modulePath) || ZK_ROOT_PATH_BACKSLASH.equals(modulePath)) {
            counterPath = ZK_ROOT_COUNTER_BASE_PATH;
            modulePath = ZK_ROOT_PATH_BACKSLASH;
        } else {
            counterPath = generateZKCounterPath(modulePath);
            modulePath = modulePath.trim();
        }
        try {
            // 获取 指定路径下的目录
            List<String> paths = null;
            if (MsOnionStringUtils.isBlank(modulePath)) {
                paths = getCounterRootBasePathChildren(zkClient);
            } else {
                paths = zkClient.getCuratorFramework().getZookeeperClient().getZooKeeper()
                        .getChildren(counterPath, DEFAULT_WATCH);
            }

            MsOnionZookeeperPathChild pathChild = new MsOnionZookeeperPathChild();

            // 不包括前面"/"
            String parentPath = MsOnionFilenameUtils.getPath(counterPath);
            // 如果最前面没有 "/"，加上 "/"
            if (!parentPath.startsWith(ZK_ROOT_PATH_BACKSLASH)) {
                parentPath = String.format("%s%s", ZK_ROOT_PATH_BACKSLASH, parentPath);
            }

            // 去掉后面的 "/"
            if (parentPath.endsWith(ZK_ROOT_PATH_BACKSLASH)) {
                parentPath = parentPath.substring(0, parentPath.length() - 1);
            }

            pathChild.setParentPath(parentPath);
            pathChild.setModulePath(modulePath);

            if (MsOnionStringUtils.isNotBlank(modulePath)) {
                pathChild.setPath(modulePath);
            } else {
                pathChild.setPath(ZK_ROOT_PATH_BACKSLASH);
            }

            // 有BUG，存在重复 modulePath
//            if (modulePath.startsWith(ZK_ROOT_PATH_BACKSLASH) || parentPath.endsWith(ZK_ROOT_PATH_BACKSLASH)) {
//                pathChild.setFullPath(String.format("%s%s", counterPath, modulePath));
//            } else {
//                pathChild.setFullPath(String.format("%s/%s", counterPath, modulePath));
//            }

            pathChild.setFullPath(counterPath);

            List<MsOnionZookeeperPathChild> children = new ArrayList<>();
            pathChild.setChildren(children);

            // 没有 Children
            if (MsOnionCollectionUtils.isEmpty(paths)) {
                return pathChild;
            }

            String modulePath2 = null;

            for (String path : paths) {
                if (modulePath.startsWith(ZK_ROOT_PATH_BACKSLASH) && modulePath.length() == 1) {
                    modulePath2 = String.format("%s%s", modulePath, path);
                } else {
                    modulePath2 = String.format("%s/%s", modulePath, path);
                }

                // 递归调用
                MsOnionZookeeperPathChild zkPathChild = getCounterPathChildren(log, zkClient, modulePath2);
                // 添加到集合中
                children.add(zkPathChild);
            }
            return pathChild;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取Zookeeper Counter路径下的子目录
     * <p>一次性加载所有子目录，使用children 获取子目录集合</p>
     *
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径，例如：dev、pro、test
     * @return 获取Zookeeper Counter路径下的子目录
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterPathChildren(MsOnionCuratorZookeeperClient zkClient,
                                                                   String modulePath) throws MsOnionException {
        return getCounterPathChildren(null, zkClient, modulePath);
    }

    /**
     * 获取Zookeeper Counter 根路径下的子目录
     * <p>一次性加载所有子目录，使用children 获取子目录集合</p>
     *
     * @param zkClient ZK客户端
     * @return 获取Zookeeper Counter路径下的子目录
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterRootPathChildren(MsOnionCuratorZookeeperClient zkClient)
            throws MsOnionException {
        return getCounterPathChildren(null, zkClient, null);
    }

    /**
     * 获取ZK 指定路径下，当前子目录，
     * <p>不是一次性加载所有子目录，不是递归，使用childList 获取子目录集合</p>
     *
     * @param log        日志
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterPathCurrentChildren(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                                          String modulePath) throws MsOnionException {

        String counterPath = null;
        if (MsOnionStringUtils.isBlank(modulePath) || ZK_ROOT_PATH_BACKSLASH.equals(modulePath)) {
            counterPath = ZK_ROOT_COUNTER_BASE_PATH;
            modulePath = ZK_ROOT_PATH_BACKSLASH;
        } else {
            counterPath = generateZKCounterPath(modulePath);
            modulePath = modulePath.trim();
        }
        try {
            // 获取 指定路径下的目录
            List<String> paths = null;
            if (MsOnionStringUtils.isBlank(modulePath)) {
                paths = getCounterRootBasePathChildren(zkClient);
            } else {
                paths = zkClient.getCuratorFramework().getZookeeperClient().getZooKeeper()
                        .getChildren(counterPath, DEFAULT_WATCH);
            }

            MsOnionZookeeperPathChild pathChild = new MsOnionZookeeperPathChild();

            // 不包括前面"/"
            String parentPath = MsOnionFilenameUtils.getPath(counterPath);
            // 如果最前面没有 "/"，加上 "/"
            if (!parentPath.startsWith(ZK_ROOT_PATH_BACKSLASH)) {
                parentPath = String.format("%s%s", ZK_ROOT_PATH_BACKSLASH, parentPath);
            }

            // 去掉后面的 "/"
            if (parentPath.endsWith(ZK_ROOT_PATH_BACKSLASH)) {
                parentPath = parentPath.substring(0, parentPath.length() - 1);
            }

            pathChild.setParentPath(parentPath);
            pathChild.setModulePath(modulePath);

            if (MsOnionStringUtils.isNotBlank(modulePath)) {
                pathChild.setPath(modulePath);
            } else {
                pathChild.setPath(ZK_ROOT_PATH_BACKSLASH);
            }

            // 有BUG，出现重复路径 modulePath
//            if (modulePath.startsWith(ZK_ROOT_PATH_BACKSLASH) || parentPath.endsWith(ZK_ROOT_PATH_BACKSLASH)) {
//            if (modulePath.equals(ZK_ROOT_PATH_BACKSLASH)) {
//                pathChild.setFullPath(String.format("%s%s", counterPath, modulePath));
//            } else {
//                pathChild.setFullPath(String.format("%s/%s", counterPath, modulePath));
//            }

            pathChild.setFullPath(counterPath);

            // 不是递归，只是获取当前目录的子目录
            pathChild.setChildList(paths);

            return pathChild;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取ZK 指定路径下，当前子目录，
     * <p>不是一次性加载所有子目录，不是递归，使用childList 获取子目录集合</p>
     *
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterPathCurrentChildren(MsOnionCuratorZookeeperClient zkClient
            , String modulePath) throws MsOnionException {
        return getCounterPathCurrentChildren(null, zkClient, modulePath);
    }

    /**
     * 获取ZK 根路径下，当前子目录，
     * <p>不是一次性加载所有子目录，不是递归，使用childList 获取子目录集合</p>
     *
     * @param log      日志
     * @param zkClient ZK客户端
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterRootPathCurrentChildren(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient)
            throws MsOnionException {
        return getCounterPathCurrentChildren(log, zkClient, null);
    }

    /**
     * 获取ZK 根路径下，当前子目录，
     * <p>不是一次性加载所有子目录，不是递归，使用childList 获取子目录集合</p>
     *
     * @param zkClient ZK客户端
     * @return 返回 MsOnionZookeeperPathChild
     * @throws MsOnionException 异常
     */
    public static MsOnionZookeeperPathChild getCounterRootPathCurrentChildren(MsOnionCuratorZookeeperClient zkClient)
            throws MsOnionException {
        return getCounterRootPathCurrentChildren(null, zkClient);
    }

    /**
     * 获取Zookeeper Counter路径下的子目录
     *
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径，例如：dev、pro、test
     * @return 获取Zookeeper Counter路径下的子目录
     * @throws MsOnionException 异常
     */
    public static List<String> getCounterPathChildrenForList(MsOnionCuratorZookeeperClient zkClient,
                                                             String modulePath) throws MsOnionException {
        return getCounterPathChildrenForList(null, zkClient, modulePath);
    }

    /**
     * 获取Zookeeper Counter路径下的子目录
     *
     * @param log        日志
     * @param zkClient   ZK客户端
     * @param modulePath 模块路径，例如：dev、pro、test
     * @return 获取Zookeeper Counter路径下的子目录
     * @throws MsOnionException 异常
     */
    public static List<String> getCounterPathChildrenForList(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                             String modulePath) throws MsOnionException {
        // 生成路径
        String path = generateZKCounterPath(log, modulePath);
        try {
            return zkClient.getCuratorFramework().getZookeeperClient().getZooKeeper()
                    .getChildren(path, DEFAULT_WATCH);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 生成Zookeeper的CounterPath
     *
     * @param modulePath 模块路径，例如：/item/code/8081
     * @return 生成Zookeeper的CounterPath
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Title: generateZKCounterPath
     * @Description: 生成Zookeeper的CounterPath
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月29日 下午3:57:43
     */
    public static String generateZKCounterPath(String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {
        return generateZKCounterPath(null, modulePath);
    }

    /**
     * 生成Zookeeper的CounterPath
     *
     * @param log        日志
     * @param modulePath 模块路径，例如：/item/code/8081
     * @return 生成Zookeeper的CounterPath
     * @throws MsOnionIllegalArgumentException 自定义非法参数异常，不是RuntimeException
     * @throws MsOnionException                MsOnion异常类
     * @Title: generateZKCounterPath
     * @Description: 生成Zookeeper的CounterPath
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月29日 下午3:57:43
     */
    public static String generateZKCounterPath(MsOnionLoggerAdapter log, String modulePath)
            throws MsOnionIllegalArgumentException, MsOnionException {

        if (MsOnionStringUtils.isEmpty(modulePath)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # generateZKCounterPath方法， modulePath参数不可以为null",
                    MsOnionDistributedAtomicLongUtils.class.getName()));
        }

        modulePath = modulePath.trim();
        modulePath = modulePath.replace(" ", "");

        if (modulePath.length() == 1 && modulePath.startsWith("/")
                || modulePath.contains(MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH)) {
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
        return String.format("%s%s", MsOnionDistributedAtomicLongUtils.ZK_ROOT_COUNTER_BASE_PATH, modulePath);
    }

    ////////////////////////////////////  AtomicValue Long ## Begin /////////////////////////////////////////

    /**
     * <p>原始值最初设置为数据库中相当于NULL。 使用此方法初始化该值。 当且仅当该节点不存在时，该值将被设置。</p>
     * <p>如果设置了该值，则为true，如果该节点已存在则为false</p>
     *
     * @param zkClient        ZK客户端
     * @param retryPolicy     重试策略
     * @param zkCounterPath   zkCounterPath
     * @param initializeValue 初始化值
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    public static boolean initialize(MsOnionCuratorZookeeperClient zkClient,
                                     RetryPolicy retryPolicy, String zkCounterPath,
                                     long initializeValue) throws MsOnionException {
        return initialize(null, zkClient, retryPolicy, zkCounterPath, initializeValue);
    }

    /**
     * <p>原始值最初设置为数据库中相当于NULL。 使用此方法初始化该值。 当且仅当该节点不存在时，该值将被设置。</p>
     * <p>如果设置了该值，则为true，如果该节点已存在则为false</p>
     *
     * @param log             日志
     * @param zkClient        ZK客户端
     * @param retryPolicy     重试策略
     * @param zkCounterPath   zkCounterPath
     * @param initializeValue 初始化值
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    public static boolean initialize(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                     RetryPolicy retryPolicy, String zkCounterPath,
                                     long initializeValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.initialize(initializeValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>将1添加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> increment(MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        return increment(null, zkClient, retryPolicy, zkCounterPath);
    }

    /**
     * <p>将1添加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> increment(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.increment();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>从当前值中减去1并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> decrement(MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        return decrement(null, zkClient, retryPolicy, zkCounterPath);
    }

    /**
     * <p>从当前值中减去1并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> decrement(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.decrement();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>强制设置计数器的值，而不保证原子性。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @throws MsOnionException 异常
     */
    public static void forceSet(MsOnionCuratorZookeeperClient zkClient,
                                RetryPolicy retryPolicy, String zkCounterPath,
                                Long newValue) throws MsOnionException {
        forceSet(null, zkClient, retryPolicy, zkCounterPath, newValue);
    }

    /**
     * <p>强制设置计数器的值，而不保证原子性。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @throws MsOnionException 异常
     */
    public static void forceSet(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                RetryPolicy retryPolicy, String zkCounterPath,
                                long newValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            distributedAtomicLong.forceSet(newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>尝试将值原子地设置为给定值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> trySet(MsOnionCuratorZookeeperClient zkClient,
                                           RetryPolicy retryPolicy, String zkCounterPath,
                                           long newValue) throws MsOnionException {
        return trySet(null, zkClient, retryPolicy, zkCounterPath, newValue);
    }

    /**
     * <p>尝试将值原子地设置为给定值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> trySet(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                           RetryPolicy retryPolicy, String zkCounterPath,
                                           long newValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.trySet(newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>返回计数器的当前值。 注意：如果该值未设置，则返回0。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> get(MsOnionCuratorZookeeperClient zkClient,
                                        RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        return get(null, zkClient, retryPolicy, zkCounterPath);
    }

    /**
     * <p>返回计数器的当前值。 注意：如果该值未设置，则返回0。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> get(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                        RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>从当前值减去delta并返回新的值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         减去的值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> subtract(MsOnionCuratorZookeeperClient zkClient,
                                             RetryPolicy retryPolicy, String zkCounterPath,
                                             long delta) throws MsOnionException {
        return subtract(null, zkClient, retryPolicy, zkCounterPath, delta);
    }

    /**
     * <p>从当前值减去delta并返回新的值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         减去的值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> subtract(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                             RetryPolicy retryPolicy, String zkCounterPath,
                                             long delta) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.subtract(delta);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>将delta增加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         增加的值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> add(MsOnionCuratorZookeeperClient zkClient,
                                        RetryPolicy retryPolicy, String zkCounterPath,
                                        long delta) throws MsOnionException {
        return add(null, zkClient, retryPolicy, zkCounterPath, delta);
    }

    /**
     * <p>将delta增加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         增加的值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> add(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                        RetryPolicy retryPolicy, String zkCounterPath,
                                        long delta) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.add(delta);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>如果当前值==为期望值，则将值设置为给定的更新值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param expectedValue 期望值
     * @param newValue      新值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> compareAndSet(MsOnionCuratorZookeeperClient zkClient,
                                                  RetryPolicy retryPolicy, String zkCounterPath,
                                                  long expectedValue, long newValue) throws MsOnionException {
        return compareAndSet(null, zkClient, retryPolicy, zkCounterPath, expectedValue, newValue);
    }

    /**
     * <p>如果当前值==为期望值，则将值设置为给定的更新值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param expectedValue 期望值
     * @param newValue      新值
     * @return AtomicValue<Long>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Long> compareAndSet(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                  RetryPolicy retryPolicy, String zkCounterPath,
                                                  long expectedValue, long newValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicLong distributedAtomicLong = new MsOnionDistributedAtomicLong(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicLong.compareAndSet(expectedValue, newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////////////////////  AtomicValue Long ## End /////////////////////////////////////////

    ////////////////////////////////////  AtomicValue Integer ## End /////////////////////////////////////////

    /**
     * <p>原始值最初设置为数据库中相当于NULL。 使用此方法初始化该值。 当且仅当该节点不存在时，该值将被设置。</p>
     * <p>如果设置了该值，则为true，如果该节点已存在则为false</p>
     *
     * @param zkClient        ZK客户端
     * @param retryPolicy     重试策略
     * @param zkCounterPath   zkCounterPath
     * @param initializeValue 初始化值
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    public static boolean initializeForInteger(MsOnionCuratorZookeeperClient zkClient,
                                               RetryPolicy retryPolicy, String zkCounterPath,
                                               int initializeValue) throws MsOnionException {
        return initializeForInteger(null, zkClient, retryPolicy, zkCounterPath, initializeValue);
    }

    /**
     * <p>原始值最初设置为数据库中相当于NULL。 使用此方法初始化该值。 当且仅当该节点不存在时，该值将被设置。</p>
     * <p>如果设置了该值，则为true，如果该节点已存在则为false</p>
     *
     * @param log             日志
     * @param zkClient        ZK客户端
     * @param retryPolicy     重试策略
     * @param zkCounterPath   zkCounterPath
     * @param initializeValue 初始化值
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    public static boolean initializeForInteger(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                               RetryPolicy retryPolicy, String zkCounterPath,
                                               int initializeValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.initialize(initializeValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>将1添加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> incrementForInteger(MsOnionCuratorZookeeperClient zkClient,
                                                           RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        return incrementForInteger(null, zkClient, retryPolicy, zkCounterPath);
    }

    /**
     * <p>将1添加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> incrementForInteger(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                           RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.increment();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>从当前值中减去1并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> decrementForInteger(MsOnionCuratorZookeeperClient zkClient,
                                                           RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        return decrementForInteger(null, zkClient, retryPolicy, zkCounterPath);
    }

    /**
     * <p>从当前值中减去1并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> decrementForInteger(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                           RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.decrement();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>强制设置计数器的值，而不保证原子性。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @throws MsOnionException 异常
     */
    public static void forceSet(MsOnionCuratorZookeeperClient zkClient,
                                RetryPolicy retryPolicy, String zkCounterPath,
                                int newValue) throws MsOnionException {
        forceSet(null, zkClient, retryPolicy, zkCounterPath, newValue);
    }

    /**
     * <p>强制设置计数器的值，而不保证原子性。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @throws MsOnionException 异常
     */
    public static void forceSet(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                RetryPolicy retryPolicy, String zkCounterPath,
                                int newValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            distributedAtomicInteger.forceSet(newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>尝试将值原子地设置为给定值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> trySet(MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy, String zkCounterPath,
                                              int newValue) throws MsOnionException {
        return trySet(null, zkClient, retryPolicy, zkCounterPath, newValue);
    }

    /**
     * <p>尝试将值原子地设置为给定值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param newValue      新值
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> trySet(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy, String zkCounterPath,
                                              int newValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.trySet(newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>返回计数器的当前值。 注意：如果该值未设置，则返回0。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> getForInteger(MsOnionCuratorZookeeperClient zkClient,
                                                     RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        return getForInteger(null, zkClient, retryPolicy, zkCounterPath);
    }

    /**
     * <p>返回计数器的当前值。 注意：如果该值未设置，则返回0。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> getForInteger(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                     RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.get();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>从当前值减去delta并返回新的值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         减去的值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> subtract(MsOnionCuratorZookeeperClient zkClient,
                                                RetryPolicy retryPolicy, String zkCounterPath,
                                                int delta) throws MsOnionException {
        return subtract(null, zkClient, retryPolicy, zkCounterPath, delta);
    }

    /**
     * <p>从当前值减去delta并返回新的值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         减去的值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> subtract(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                RetryPolicy retryPolicy, String zkCounterPath,
                                                int delta) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.subtract(delta);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>将delta增加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         增加的值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> add(MsOnionCuratorZookeeperClient zkClient,
                                           RetryPolicy retryPolicy, String zkCounterPath,
                                           int delta) throws MsOnionException {
        return add(null, zkClient, retryPolicy, zkCounterPath, delta);
    }

    /**
     * <p>将delta增加到当前值并返回新值信息。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param delta         增加的值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> add(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                           RetryPolicy retryPolicy, String zkCounterPath,
                                           int delta) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.add(delta);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>如果当前值==为期望值，则将值设置为给定的更新值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param expectedValue 期望值
     * @param newValue      新值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> compareAndSet(MsOnionCuratorZookeeperClient zkClient,
                                                     RetryPolicy retryPolicy, String zkCounterPath,
                                                     int expectedValue, int newValue) throws MsOnionException {
        return compareAndSet(null, zkClient, retryPolicy, zkCounterPath, expectedValue, newValue);
    }

    /**
     * <p>如果当前值==为期望值，则将值设置为给定的更新值。 记住要始终检查AtomicValue.succeeded()。</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @param expectedValue 期望值
     * @param newValue      新值
     * @return AtomicValue<Integer>
     * @throws MsOnionException 异常
     */
    public static AtomicValue<Integer> compareAndSet(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                     RetryPolicy retryPolicy, String zkCounterPath,
                                                     int expectedValue, int newValue) throws MsOnionException {
        try {
            MsOnionDistributedAtomicInteger distributedAtomicInteger = new MsOnionDistributedAtomicInteger(zkClient.getCuratorFramework(),
                    zkCounterPath, retryPolicy);
            return distributedAtomicInteger.compareAndSet(expectedValue, newValue);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////////////////////  AtomicValue Integer ## End /////////////////////////////////////////


    /////////////////// 生成 idxCode ########## Begin /////////////////////////////////

    /**
     * 获取IdxCode，整个分布式架构底层，所有的IdxCode都是调用这个方法实现，
     * <ul>规则：
     * <li>1、所有的IdxCode 都是统一调用当前方法，根据当天日期进行拆分，例如：2017070810000200098、2017070710008200218</li>
     * <li>2、每一天都一个目录，例如：20170707、20170708</li>
     * <li>3、总长度19位，long（bigint) , 日期(yyyyMMdd) + 5位(ZK递增1) + 4位(ZK递增2) + 2位随机</li>
     * <li>4、5位(ZK递增1) + 4位(ZK递增2) + 2位随机 中，如果位数不够使用0补齐</li>
     * <li>5、内部有1次重试机制，外部使用也可以再重试机制，使用try-catch 中重试</li></ul>
     *
     * @param domain      封装各种环境
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试机制
     * @return 返回2017070810000200098
     * @throws MsOnionException 异常
     */
    public static long getIdxCode(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                  RetryPolicy retryPolicy) throws MsOnionException {
        return getIdxCode(null, domain, zkClient, retryPolicy);
    }

    /**
     * 获取IdxCode，整个分布式架构底层，所有的IdxCode都是调用这个方法实现，
     * <ul>规则：
     * <li>1、所有的IdxCode 都是统一调用当前方法，根据当天日期进行拆分，例如：19698</li>
     * <li>2、每一天都一个目录，例如：20170707、20170708</li>
     * <li>3、总长度19位，long（bigint) , 日期(yyyyMMdd) + 5位(ZK递增1) + 4位(ZK递增2) + 2位随机</li>
     * <li>4、ZK递增（随机递增） + 2位随机 中</li>
     * <li>5、内部有1次重试机制，外部使用也可以再重试机制，使用try-catch 中重试</li>
     * <li>6、如果平台发展到每天的订单量超过亿+级别，需要扩展binint(long)为字符串，采取yyyyMMdd+19位数(long)</li>
     * </ul>
     *
     * @param domain      封装各种环境
     * @param log         日志
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试机制
     * @return 返回2017070810000200098
     * @throws MsOnionException 异常
     */
    public static long getIdxCode(MsOnionLoggerAdapter log, MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                  RetryPolicy retryPolicy) throws MsOnionException {
        /*
         规则：
         1、所有的IdxCode 都是统一调用当前方法，根据当天日期进行拆分，例如：2017070810000200098、2017070710008200218
         2、每一天都一个目录，例如：20170707、20170708
         3、总长度19位，long（bigint) , 日期(yyyyMMdd) + 5位(ZK递增1) + 4位(ZK递增2) + 2位随机
         4、5位(ZK递增1) + 4位(ZK递增2) + 2位随机 中，如果位数不够使用0补齐
         5、内部有1次重试机制，外部使用也可以再重试机制，使用try-catch 中重试
         */

        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getIdxCode，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }

        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getIdxCode方法， 环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }

        env = env.trim();

//        String yyyyMMdd = null;
//        try {
//            // 日期，年月日，例如：20170708、20171218
//            yyyyMMdd = MsOnionDateUtils.formatYyyyMMddUnity(new Date());
//        } catch (Exception ex) {
//            // 重试1次
//            yyyyMMdd = MsOnionDateUtils.formatYyyyMMddUnity(new Date());
//        }
//
//        if (MsOnionStringUtils.isBlank(yyyyMMdd)) {
//            throw new MsOnionException(IDX_CODE_YYYY_MM_DD_ILLEGAL);
//        }
//
//        // 去掉左右空格
//        yyyyMMdd = yyyyMMdd.trim();
//
//        // ZK目录按照年、月来分类
//        // 年，2017
//        String yyyy = yyyyMMdd.substring(0, 4);
//        // 月,07
//        String mm = yyyyMMdd.substring(4, 6);
//        // 日，08
//        String dd = yyyyMMdd.substring(6, 8);

//        // 采取5、4存在一个问题，1天最多只能有9999（4位）值，否则出现！！！
//        // 5位数
//        String digits5CounterPath = String.format("%s/%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_5_DIGITS_PATH, yyyy, mm, dd);
////        MsOnionLoggerAdapterger.doInfo(MsOnionZookeeperUtils.class.getName(), "5位数路径 # digits5CounterPath=" + digits5CounterPath);
//        AtomicValue<Long> digits5AtomicValue = add(log, zkClient, retryPolicy, digits5CounterPath, IDX_CODE_5_DIGITS_STEP);
//        if (null == digits5AtomicValue || !digits5AtomicValue.succeeded()) {
//            // 重试1次
//            digits5AtomicValue = add(log, zkClient, retryPolicy, digits5CounterPath, IDX_CODE_5_DIGITS_STEP);
//        }
//
//        if (null == digits5AtomicValue || !digits5AtomicValue.succeeded()) {
//            throw new MsOnionException(IDX_CODE_5_DIGITS_ILLEGAL);
//        }
//
//        // 4位数
//        String digits4CounterPath = String.format("%s/%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_4_DIGITS_PATH, yyyy, mm, dd);
////        MsOnionLoggerAdapterger.doInfo(MsOnionZookeeperUtils.class.getName(), "4位数路径 # digits4CounterPath=" + digits4CounterPath);
//        // 随机生成 step，包括最小值，不包括最大值
//        long digits4Step = MsOnionRandomUtils.getRandom(1, IDX_CODE_4_DIGITS_STEPS.length);
//        AtomicValue<Long> digits4AtomicValue = add(log, zkClient, retryPolicy, digits4CounterPath, digits4Step);
//        if (null == digits4AtomicValue || !digits4AtomicValue.succeeded()) {
//            // 重试1次
//            digits4AtomicValue = add(log, zkClient, retryPolicy, digits4CounterPath, digits4Step);
//        }
//
//        if (null == digits4AtomicValue || !digits4AtomicValue.succeeded()) {
//            throw new MsOnionException(IDX_CODE_4_DIGITS_ILLEGAL);
//        }
//
//        String digits5Value = MsOnionFixedLengthUtils.fixLength(digits5AtomicValue.postValue(), IDX_CODE_5_DIGITS_LENGTH);
//
//        String digits4Value = MsOnionFixedLengthUtils.fixLength(digits4AtomicValue.postValue(), IDX_CODE_4_DIGITS_LENGTH);

//        // 9位数
//        String digits9CounterPath = String.format("%s/%s/%s/%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_PATH, env,
//                ZK_ROOT_COUNTER_IDX_CODE_9_DIGITS_PATH, yyyy, mm, dd);

        // 17位数，全局的，就不能使用年、月、日来生成，会导致重复！！！
        String digits17CounterPath = String.format("%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_PATH, env,
                ZK_ROOT_COUNTER_IDX_CODE_17_DIGITS_PATH);

        // 首先判断是否存在，没有存在使用随机初始值
        initializeForIdxCode(log, zkClient, retryPolicy, digits17CounterPath);

//        MsOnionLoggerAdapterger.doInfo(MsOnionZookeeperUtils.class.getName(), "17位数路径 # digits17CounterPath=" + digits17CounterPath);
        // 随机生成 step，包括最小值，不包括最大值
        long digits9Step = MsOnionRandomUtils.getRandom(1, IDX_CODE_17_DIGITS_STEPS.length);
        AtomicValue<Long> digits17AtomicValue = add(log, zkClient, retryPolicy, digits17CounterPath, digits9Step);
        if (null == digits17AtomicValue || !digits17AtomicValue.succeeded()) {
            // 重试1次
            digits17AtomicValue = add(log, zkClient, retryPolicy, digits17CounterPath, digits9Step);
        }

        if (null == digits17AtomicValue || !digits17AtomicValue.succeeded()) {
            throw new MsOnionException(IDX_CODE_17_DIGITS_ILLEGAL);
        }

        // 不需要固定长度
//        String digits9Value = MsOnionFixedLengthUtils.fixLength(digits9AtomicValue.postValue(), IDX_CODE_9_DIGITS_LENGTH);

        // 随机2位
        int random = MsOnionRandomUtils.getRandom(IDX_CODE_2_DIGITS_RANDOM_MIN_VALUE, IDX_CODE_2_DIGITS_RANDOM_MAX_VALUE);

        // 固定2位长度，不够补齐0
        String randomValue = MsOnionFixedLengthUtils.fixLength(random, IDX_CODE_2_DIGITS_RANDOM_LENGTH);

        // 拼接成字符串，不需要包括年月日，因为商品IdxCode太长不友好，订单号比较长，可以
//        String idxCode = String.format("%s%s%s", yyyyMMdd, digits9AtomicValue.postValue(), randomValue);

        // ZK 递增（随机，最大9位，亿级数据） + 2位随机
        String idxCode = String.format("%s%s", digits17AtomicValue.postValue(), randomValue);

        try {
            // 将字符串转换成long
            return Long.parseLong(idxCode);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取OrderNo（订单编号），整个分布式架构底层，所有的IdxCode都是调用这个方法实现，
     * <ul>规则：
     * <li>1、所有的orderNo 都是统一调用当前方法，根据当天日期进行拆分，例如：2017070810000200098、2017070710008200218</li>
     * <li>2、每一天都一个目录，例如：20170707、20170708</li>
     * <li>3、总长度19位，long（bigint) , 日期(yyyyMMdd) + 5位(ZK递增1) + 4位(ZK递增2) + 2位随机</li>
     * <li>4、5位(ZK递增1) + 4位(ZK递增2) + 2位随机 中，如果位数不够使用0补齐</li>
     * <li>5、内部有1次重试机制，外部使用也可以再重试机制，使用try-catch 中重试</li>
     * <li>6、如果平台发展到每天的订单量超过亿+级别，需要扩展binint(long)为字符串，采取yyyyMMdd+19位数(long)</li>
     * </ul>
     *
     * @param domain      封装各种环境
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试机制
     * @return 返回2017070810000200098
     * @throws MsOnionException 异常
     */
    public static long getOrderNo(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                  RetryPolicy retryPolicy) throws MsOnionException {
        return getOrderNo(null, domain, zkClient, retryPolicy);
    }

    /**
     * 获取OrderNo（订单编号），整个分布式架构底层，所有的IdxCode都是调用这个方法实现，
     * <ul>规则：
     * <li>1、所有的orderNo 都是统一调用当前方法，根据当天日期进行拆分，例如：2017070810000200098、2017070710008200218</li>
     * <li>2、每一天都一个目录，例如：20170707、20170708</li>
     * <li>3、总长度19位，long（bigint) , 日期(yyyyMMdd) + 5位(ZK递增1) + 4位(ZK递增2) + 2位随机</li>
     * <li>4、5位(ZK递增1) + 4位(ZK递增2) + 2位随机 中，如果位数不够使用0补齐</li>
     * <li>5、内部有1次重试机制，外部使用也可以再重试机制，使用try-catch 中重试</li>
     * <li>6、如果平台发展到每天的订单量超过亿+级别，需要扩展binint(long)为字符串，采取yyyyMMdd+19位数(long)</li>
     * </ul>
     *
     * @param domain      封装各种环境
     * @param log         日志
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试机制
     * @return 返回2017070810000200098
     * @throws MsOnionException 异常
     */
    public static long getOrderNo(MsOnionLoggerAdapter log, MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                  RetryPolicy retryPolicy) throws MsOnionException {
        /*
         规则：
         1、所有的IdxCode 都是统一调用当前方法，根据当天日期进行拆分，例如：2017070810000200098、2017070710008200218
         2、每一天都一个目录，例如：20170707、20170708
         3、总长度19位，long（bigint) , 日期(yyyyMMdd) + 5位(ZK递增1) + 4位(ZK递增2) + 2位随机
         4、5位(ZK递增1) + 4位(ZK递增2) + 2位随机 中，如果位数不够使用0补齐
         5、内部有1次重试机制，外部使用也可以再重试机制，使用try-catch 中重试
         */

        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getIdxCode，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }

        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getIdxCode方法， 环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }

        env = env.trim();

        String yyyyMMdd = null;
        try {
            // 日期，年月日，例如：20170708、20171218
            yyyyMMdd = MsOnionDateUtils.formatYyyyMMddUnity(new Date());
        } catch (Exception ex) {
            // 重试1次
            yyyyMMdd = MsOnionDateUtils.formatYyyyMMddUnity(new Date());
        }

        if (MsOnionStringUtils.isBlank(yyyyMMdd)) {
            throw new MsOnionException(IDX_CODE_YYYY_MM_DD_ILLEGAL);
        }

        // 去掉左右空格
        yyyyMMdd = yyyyMMdd.trim();

        // ZK目录按照年、月来分类
        // 年，2017
        String yyyy = yyyyMMdd.substring(0, 4);
        // 月,07
        String mm = yyyyMMdd.substring(4, 6);
        // 日，08
        String dd = yyyyMMdd.substring(6, 8);

//        // 采取5、4存在一个问题，1天最多只能有9999（4位）值，否则出现！！！
//        // 5位数
//        String digits5CounterPath = String.format("%s/%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_5_DIGITS_PATH, yyyy, mm, dd);
////        MsOnionLoggerAdapterger.doInfo(MsOnionZookeeperUtils.class.getName(), "5位数路径 # digits5CounterPath=" + digits5CounterPath);
//        AtomicValue<Long> digits5AtomicValue = add(log, zkClient, retryPolicy, digits5CounterPath, IDX_CODE_5_DIGITS_STEP);
//        if (null == digits5AtomicValue || !digits5AtomicValue.succeeded()) {
//            // 重试1次
//            digits5AtomicValue = add(log, zkClient, retryPolicy, digits5CounterPath, IDX_CODE_5_DIGITS_STEP);
//        }
//
//        if (null == digits5AtomicValue || !digits5AtomicValue.succeeded()) {
//            throw new MsOnionException(IDX_CODE_5_DIGITS_ILLEGAL);
//        }
//
//        // 4位数
//        String digits4CounterPath = String.format("%s/%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_4_DIGITS_PATH, yyyy, mm, dd);
////        MsOnionLoggerAdapterger.doInfo(MsOnionZookeeperUtils.class.getName(), "4位数路径 # digits4CounterPath=" + digits4CounterPath);
//        // 随机生成 step，包括最小值，不包括最大值
//        long digits4Step = MsOnionRandomUtils.getRandom(1, IDX_CODE_4_DIGITS_STEPS.length);
//        AtomicValue<Long> digits4AtomicValue = add(log, zkClient, retryPolicy, digits4CounterPath, digits4Step);
//        if (null == digits4AtomicValue || !digits4AtomicValue.succeeded()) {
//            // 重试1次
//            digits4AtomicValue = add(log, zkClient, retryPolicy, digits4CounterPath, digits4Step);
//        }
//
//        if (null == digits4AtomicValue || !digits4AtomicValue.succeeded()) {
//            throw new MsOnionException(IDX_CODE_4_DIGITS_ILLEGAL);
//        }
//
//        String digits5Value = MsOnionFixedLengthUtils.fixLength(digits5AtomicValue.postValue(), IDX_CODE_5_DIGITS_LENGTH);
//
//        String digits4Value = MsOnionFixedLengthUtils.fixLength(digits4AtomicValue.postValue(), IDX_CODE_4_DIGITS_LENGTH);

        // 9位数
        String digits9CounterPath = String.format("%s/%s/%s/%s/%s/%s", ZK_ROOT_COUNTER_IDX_CODE_PATH, env,
                ZK_ROOT_COUNTER_IDX_CODE_9_DIGITS_PATH, yyyy, mm, dd);

        // 首先判断是否存在，没有存在使用随机初始值
        initializeForOrderNo(log, zkClient, retryPolicy, digits9CounterPath);

//        MsOnionLoggerAdapterger.doInfo(MsOnionZookeeperUtils.class.getName(), "9位数路径 # digits9CounterPath=" + digits9CounterPath);
        // 随机生成 step，包括最小值，不包括最大值
        long digits9Step = MsOnionRandomUtils.getRandom(1, IDX_CODE_9_DIGITS_STEPS.length);
        AtomicValue<Long> digits9AtomicValue = add(log, zkClient, retryPolicy, digits9CounterPath, digits9Step);
        if (null == digits9AtomicValue || !digits9AtomicValue.succeeded()) {
            // 重试1次
            digits9AtomicValue = add(log, zkClient, retryPolicy, digits9CounterPath, digits9Step);
        }

        if (null == digits9AtomicValue || !digits9AtomicValue.succeeded()) {
            throw new MsOnionException(IDX_CODE_9_DIGITS_ILLEGAL);
        }

        String digits9Value = MsOnionFixedLengthUtils.fixLength(digits9AtomicValue.postValue(), IDX_CODE_9_DIGITS_LENGTH);

        // 随机2位
        int random = MsOnionRandomUtils.getRandom(IDX_CODE_2_DIGITS_RANDOM_MIN_VALUE, IDX_CODE_2_DIGITS_RANDOM_MAX_VALUE);

        // 固定2位长度，不够补齐0
        String randomValue = MsOnionFixedLengthUtils.fixLength(random, IDX_CODE_2_DIGITS_RANDOM_LENGTH);

        // 拼接成字符串
        String idxCode = String.format("%s%s%s", yyyyMMdd, digits9Value, randomValue);

        try {
            // 将字符串转换成long
            return Long.parseLong(idxCode);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * <p>原始值最初设置为数据库中相当于NULL。 使用此方法初始化该值。 当且仅当该节点不存在时，该值将被设置。</p>
     * <p>如果设置了该值，则为true，如果该节点已存在则为false</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    private static boolean initializeForIdxCode(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {

        // 获取当前路径的值
        AtomicValue<Long> atomicValue = get(log, zkClient, retryPolicy, zkCounterPath);
        if (null == atomicValue || (atomicValue.postValue() <= 0 && atomicValue.preValue() <= 0)) {
            // 当前路径还不存在
            // 随机生成初始化值
            int random = MsOnionRandomUtils.getRandom(0, IDX_CODE_17_DIGITS_INITIALIZE_VALUES.length);
            long value = IDX_CODE_17_DIGITS_INITIALIZE_VALUES[random];
            return initialize(log, zkClient, retryPolicy, zkCounterPath, value);
        }

        return false;
    }

    /**
     * <p>原始值最初设置为数据库中相当于NULL。 使用此方法初始化该值。 当且仅当该节点不存在时，该值将被设置。</p>
     * <p>如果设置了该值，则为true，如果该节点已存在则为false</p>
     *
     * @param log           日志
     * @param zkClient      ZK客户端
     * @param retryPolicy   重试策略
     * @param zkCounterPath zkCounterPath
     * @return 如果设置了该值，则为true，如果该节点已存在则为false
     * @throws MsOnionException 异常
     */
    private static boolean initializeForOrderNo(MsOnionLoggerAdapter log, MsOnionCuratorZookeeperClient zkClient,
                                                RetryPolicy retryPolicy, String zkCounterPath) throws MsOnionException {

        // 获取当前路径的值
        AtomicValue<Long> atomicValue = get(log, zkClient, retryPolicy, zkCounterPath);
        if (null == atomicValue || (atomicValue.postValue() <= 0 && atomicValue.preValue() <= 0)) {
            // 当前路径还不存在
            // 随机生成初始化值
            int random = MsOnionRandomUtils.getRandom(0, IDX_CODE_9_DIGITS_INITIALIZE_VALUES.length);
            long value = IDX_CODE_9_DIGITS_INITIALIZE_VALUES[random];
            return initialize(log, zkClient, retryPolicy, zkCounterPath, value);
        }

        return false;
    }

    /////////////////// 生成 idxCode ########## End /////////////////////////////////


    /////////////////// 日志级别 ########## Begin /////////////////////////////////

    /**
     * 获取日志级别的路径，
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 返回日志级别的路径
     * @throws MsOnionException 异常
     */
    public static String getLogLevelPath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                         RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getLogLevelPath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getLogLevelPath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 日志级别的路径
        return String.format("%s/%s/%s", ZK_ROOT_COUNTER_LOG_PATH, env.trim(), ZK_COUNTER_LOG_LEVEL_PATH);
    }

    /**
     * 获取日志级别，
     * <p>日志级别   0:调试信息  1:普通信息   2:警告信息  3:错误信息  4:严重错误信息</p>
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 返回日志级别   0:调试信息  1:普通信息   2:警告信息  3:错误信息  4:严重错误信息
     * @throws MsOnionException 异常
     */
    public static long getLogLevel(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                   RetryPolicy retryPolicy) throws MsOnionException {
//        if (null == domain) {
//            throw new MsOnionIllegalArgumentException(String.format("%s # getLogLevel，domain参数不可以为null",
//                    MsOnionZookeeperUtils.class.getName()));
//        }
//        // 获取环境：开发、测试、稳定、预发布、生产
//        String env = domain.getEnvironment();
//        if (MsOnionStringUtils.isEmpty(env)) {
//            throw new MsOnionIllegalArgumentException(String.format("%s # getLogLevel，环境配置为null",
//                    MsOnionZookeeperUtils.class.getName()));
//        }
//        env = env.trim();
        try {
            // 日志级别的路径
            String logLevelCounterPath = getLogLevelPath(domain, zkClient, retryPolicy);
            // 获取日志级别：0、1、2、3、4
            // 日志级别   0:调试信息  1:普通信息   2:警告信息  3:错误信息  4:严重错误信息
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, logLevelCounterPath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, logLevelCounterPath);
            }
            // 设置默认为DEBUG
            if (atomicValue.postValue() == 0) {
                // 初始化
                initialize(zkClient, retryPolicy, logLevelCounterPath, MsOnionLogLevel.DEBUG.getValue());
            }
            return atomicValue.postValue();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取是否打印日志到控制台的值，的路径
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 返回获取日志打印到控制台的值，0:不打印到控制台，大于等于1(>=1)：打印到控制台
     * @throws MsOnionException 异常
     */
    public static String getPrintToConsolePath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                               RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getPrintToConsolePath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s # getPrintToConsolePath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 是否打印日志到控制台的值，的路径
        return String.format("%s/%s/%s", ZK_ROOT_COUNTER_LOG_PATH, env.trim(), ZK_COUNTER_PRINT_TO_CONSOLE_PATH);
    }

    /**
     * 获取日志打印到控制台的值，
     * <p>0-1:不打印到控制台，大于等于2(>=2)：打印到控制台</p>
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 返回获取日志打印到控制台的值，0-1:不打印到控制台，大于等于2(>=2)：打印到控制台
     * @throws MsOnionException 异常
     */
    public static long getPrintToConsole(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                         RetryPolicy retryPolicy) throws MsOnionException {
//        if (null == domain) {
//            throw new MsOnionIllegalArgumentException(String.format("%s # getLogLevel，domain参数不可以为null",
//                    MsOnionZookeeperUtils.class.getName()));
//        }
//        // 获取环境：开发、测试、稳定、预发布、生产
//        String env = domain.getEnvironment();
//        if (MsOnionStringUtils.isEmpty(env)) {
//            throw new MsOnionIllegalArgumentException(String.format("%s # getLogLevel，环境配置为null",
//                    MsOnionZookeeperUtils.class.getName()));
//        }
//        env = env.trim();
        try {
            // 日志级别的路径
            String logLevelCounterPath = getPrintToConsolePath(domain, zkClient, retryPolicy);
            // 获取日志级别： 1、2、3、4
            // 日志级别   1:调试信息  2:普通信息   3:警告信息  4:错误信息  5:严重错误信息
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, logLevelCounterPath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, logLevelCounterPath);
            }
            // 初始化值，如果为0
            if (atomicValue.postValue() == 0) {
                // 如果没有值，就是初始化为1，也就是打印日志
                initialize(zkClient, retryPolicy, logLevelCounterPath, MsOnionLogLevel.DEBUG.getValue());
            }
            return atomicValue.postValue();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取SQL执行耗时的值，是否打印日志，的路径
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 返回获取SQL执行耗时的值，是否打印日志，的路径
     * @throws MsOnionException 异常
     */
    public static String getSqlCostTimeLevelPath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                 RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getSqlCostTimeLevelPath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getSqlCostTimeLevelPath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 是否打印日志到控制台的值，的路径
        return String.format("%s/%s/%s", ZK_ROOT_COUNTER_LOG_PATH, env.trim(), ZK_COUNTER_SQL_COST_TIME_LEVEL_PATH);
    }

    /**
     * SQL执行，耗时超过多少毫秒，打印日志，单元：毫秒
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 返回SQL执行，耗时超过多少毫秒，打印日志，单元：毫秒
     * @throws MsOnionException 异常
     */
    public static long getSqlCostTimeLevel(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                           RetryPolicy retryPolicy) throws MsOnionException {
        try {
            // SQL耗时时间，日志级别的路径
            String sqlCostTimeLevelCounterPath = getSqlCostTimeLevelPath(domain, zkClient, retryPolicy);
            // SQL执行，耗时超过多少毫秒，打印日志
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, sqlCostTimeLevelCounterPath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, sqlCostTimeLevelCounterPath);
            }
            // 初始值
            if (atomicValue.postValue() == 0) {
                // 默认的初始值，为100毫秒
                initialize(zkClient, retryPolicy, sqlCostTimeLevelCounterPath, DEFAULT_SQL_COST_TIME_LEVEL);
            }
            return atomicValue.postValue();
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }


    // /////////////// 日志级别 ########## End /////////////////////////////////


    // /////////////// Redis配置 ########## Begin /////////////////////////////////

    /**
     * 获取Redis全局查询缓存是否禁用路径
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 获取Redis全局查询缓存是否禁用路径
     * @throws MsOnionException 异常
     */
    public static String getRedisCacheGlobalQueryDisabledPath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                              RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getRedisCacheGlobalQueryDisabledPath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getRedisCacheGlobalQueryDisabledPath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 是否打印日志到控制台的值，的路径
        return String.format("%s/%s/%s", ZK_COUNTER_REDIS_PATH, env.trim(), ZK_COUNTER_REDIS_CACHE_GLOBAL_QUERY_DISABLED_PATH);
    }

    /**
     * Redis全局查询缓存是否禁用，默认值：2L， 2L:不禁用缓存（启用缓存），1L：禁用缓存（不使用Redis缓存）
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 重试策略
     * @return Redis全局查询缓存是否禁用，默认值：2L， 2L:不禁用缓存（启用缓存），1L：禁用缓存（不使用Redis缓存）
     * @throws MsOnionException 异常
     */
    public static boolean getRedisCacheGlobalQueryDisabled(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                        RetryPolicy retryPolicy) throws MsOnionException {
        try {
            // Redis全局查询缓存是否禁用的路径
            String redisCacheGlobalQueryDisabledPath = getRedisCacheGlobalQueryDisabledPath(domain, zkClient, retryPolicy);
            // Redis全局查询缓存是否禁用
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, redisCacheGlobalQueryDisabledPath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, redisCacheGlobalQueryDisabledPath);
            }
            // 初始值
            if (atomicValue.postValue() == 0) {
                // 默认的初始值，为2L，不禁用Redis缓存，也就是启用Redis缓存
                initialize(zkClient, retryPolicy, redisCacheGlobalQueryDisabledPath, DEFAULT_REDIS_CACHE_GLOBAL_QUERY_DISABLED);
            }
            long value = atomicValue.postValue();
            return value != DEFAULT_REDIS_CACHE_GLOBAL_QUERY_DISABLED;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * Redis全局缓存周期路径
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return Redis全局缓存周期路径
     * @throws MsOnionException 异常
     */
    public static String getRedisGlobalCacheExpirePath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                       RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getRedisGlobalCacheExpirePath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getRedisGlobalCacheExpirePath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 是否打印日志到控制台的值，的路径
        return String.format("%s/%s/%s", ZK_COUNTER_REDIS_PATH, env.trim(), ZK_COUNTER_REDIS_CACHE_GLOBAL_EXPIRE_PATH);
    }

    /**
     * Redis全局缓存周期，默认值：1天（24小时）
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return Redis全局缓存周期，默认值：1天（24小时）
     * @throws MsOnionException 异常
     */
    public static int getRedisGlobalCacheExpire(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                 RetryPolicy retryPolicy) throws MsOnionException {
        try {
            // Redis全局缓存周期路径
            String redisGlobalCacheExpirePath = getRedisGlobalCacheExpirePath(domain, zkClient, retryPolicy);
            // Redis全局缓存周期
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, redisGlobalCacheExpirePath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, redisGlobalCacheExpirePath);
            }
            // 初始值
            if (atomicValue.postValue() == 0) {
                // Redis全局缓存周期，默认值：1天（24小时）
                initialize(zkClient, retryPolicy, redisGlobalCacheExpirePath, DEFAULT_REDIS_CACHE_GLOBAL_EXPIRE);
            }
            long value = atomicValue.postValue();
            return (int) value;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ///////////////// Redis配置 ########## End /////////////////////////////////

    ///////////////// 验证码配置 ## Begin ########## /////////////////////////////////

    /**
     * 是否开启认证码的路径
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return Redis全局缓存周期路径
     * @throws MsOnionException 异常
     */
    public static String getEnableVerifyCodePath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                 RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getEnableVerifyCodePath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getEnableVerifyCodePath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 是否开启认证码的路径
        return String.format("%s/%s/%s", ZK_COUNTER_REDIS_PATH, env.trim(), ZK_COUNTER_REDIS_ENABLE_VERIFY_CODE_PATH);
    }

    /**
     * 是否获取开启验证码，1：开启认证码，其他值：都是不开启认证码，也就是不进行验证码校验
     * <p>true：开启验证码，  false：不开启验证码</p>
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 是否获取开启验证码，默认值：1
     * @throws MsOnionException 异常
     */
    public static boolean getEnableVerifyCode(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy) throws MsOnionException {
        try {
            // 获取开启验证码路径
            String enableVerifyCodePath = getEnableVerifyCodePath(domain, zkClient, retryPolicy);
            // 获取开启验证码
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, enableVerifyCodePath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, enableVerifyCodePath);
            }
            // 初始值
            if (atomicValue.postValue() == 0) {
                // 获取开启验证码，默认为：1，也就是开启验证码，其他值：不开启验证码
                initialize(zkClient, retryPolicy, enableVerifyCodePath, DEFAULT_ENABLE_VERIFY_CODE);
            }
            // 1，也就是开启验证码，其他值：不开启验证码
            long value = atomicValue.postValue();
            return value == DEFAULT_ENABLE_VERIFY_CODE;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 验证码自动周期路径
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return Redis全局缓存周期路径
     * @throws MsOnionException 异常
     */
    public static String getVerifyCodeAutoExpirePath(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                                     RetryPolicy retryPolicy) throws MsOnionException {
        if (null == domain) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getVerifyCodeAutoExpirePath，domain参数不可以为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 获取环境：开发、测试、稳定、预发布、生产
        String env = domain.getEnvironment();
        if (MsOnionStringUtils.isEmpty(env)) {
            throw new MsOnionIllegalArgumentException(String.format("%s#getVerifyCodeAutoExpirePath，环境配置为null",
                    MsOnionZookeeperUtils.class.getName()));
        }
        // 是否开启认证码的路径
        return String.format("%s/%s/%s", ZK_COUNTER_REDIS_PATH, env.trim(), ZK_COUNTER_REDIS_VERIFY_CODE_AUTO_EXPIRE_PATH);
    }

    /**
     * 获取验证码自动周期，作为Redis的周期，单位为：秒
     *
     * @param domain      域名，各个环境封装
     * @param zkClient    ZK客户端
     * @param retryPolicy 测试策略
     * @return 获取验证码自动周期，作为Redis的周期，单位为：秒，默认为：300秒（5分钟）
     * @throws MsOnionException 异常
     */
    public static int getVerifyCodeAutoExpire(MsOnionDomain domain, MsOnionCuratorZookeeperClient zkClient,
                                              RetryPolicy retryPolicy) throws MsOnionException {
        try {
            // 获取验证码自动周期，作为Redis的周期路径
            String verifyCodeAutoExpirePath = getVerifyCodeAutoExpirePath(domain, zkClient, retryPolicy);
            // 获取验证码自动周期，作为Redis的周期
            AtomicValue<Long> atomicValue = get(zkClient, retryPolicy, verifyCodeAutoExpirePath);
            // 重试
            if (null == atomicValue || !atomicValue.succeeded()) {
                atomicValue = get(zkClient, retryPolicy, verifyCodeAutoExpirePath);
            }
            // 初始值
            if (atomicValue.postValue() == 0) {
                // 获取验证码自动周期，作为Redis的周期，默认为300秒
                initialize(zkClient, retryPolicy, verifyCodeAutoExpirePath, DEFAULT_VERIFY_CODE_AUTO_EXPIRE);
            }
            // 获取验证码自动周期，作为Redis的周期，默认为300秒
            long value = atomicValue.postValue();
            return (int) value;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    // /////////////// 验证码配置 ## End ########## /////////////////////////////////

}
