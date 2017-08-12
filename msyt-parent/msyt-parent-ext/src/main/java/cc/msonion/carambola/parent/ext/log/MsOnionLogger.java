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

package cc.msonion.carambola.parent.ext.log;

import cc.msonion.carambola.parent.common.enums.MsOnionLogLevel;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.interfaces.log.MsOnionLog;
import cc.msonion.carambola.parent.interfaces.log.adapter.MsOnionLoggerAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import cc.msonion.carambola.parent.pojo.zk.retrypolicy.MsOnionRetryNTimes;
import com.cyfonly.flogger.FLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Title: MsOnionLogger.java
 * @Package: cc.msonion.carambola.parent.ext.log
 * @Description: 洋葱小姐分布式架构日志器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月7日 下午1:54:36
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月7日 下午1:54:36
 * @Modify-version: V2.0.0
 * @Modify-description: 实现info、debug、warn、error
 */

/**
 * 洋葱小姐分布式架构日志器
 *
 * @ClassName: MsOnionLogger
 * @Description: 洋葱小姐分布式架构日志器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月7日 下午1:54:36
 */
@Component
public class MsOnionLogger implements MsOnionLoggerAdapter {

    /**
     * 默认日志打印控制台的值：2
     */
    private static final long DEFAULT_PRINT_TO_CONSOLE_VALUE = 2L;

    /**
     * 是否打印日志到控制台，上线要关闭：false
     */
    private static final boolean IS_PRINT_TO_CONSOLE = true;  // true , false

    /**
     * 域名：生产环境、预览（预发布）环境、稳定环境、测试环境、开发环境域名
     */
    @Autowired
    private MsOnionDomain msOnionDomain;

    /**
     * 日志
     */
    @Autowired
    private MsOnionLog msOnionLog;

    /**
     * ZK客户端
     */
    @Autowired
    private MsOnionCuratorZookeeperClient curatorZookeeperClient;

    /**
     * 重试策略
     */
    @Autowired
    private MsOnionRetryNTimes retryNTimes;

//	/**
//	 * 是否打印日志到控制台，测试环境、预览环境、生产环境，这3个环境必须设置为false，关闭在控制台打印日志，
//	 * 只有开发环境才设置为true
//	 */
//	@Value("${msOnion.isPrintToConsole}")
//	private boolean isPrintToConsole;

//	/**
//	 * FLogger实例对象
//	 */
//	private static final FLogger LOGGER = FLogger.getInstance();

    /**
     * @Fields serialVersionUID : 自动生成
     */
    private static final long serialVersionUID = -4632966489663333388L;

    /**
     * 日志级别：Info
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    public void info(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("info#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.INFO.getValue()) {
                    return;
                }
                msOnionLog.info(className, ex, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void error(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.err.println(String.format("error#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.ERROR.getValue()) {
                    return;
                }
                msOnionLog.error(className, ex, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void warn(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.err.println(String.format("warn#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.WARN.getValue()) {
                    return;
                }
                msOnionLog.warn(className, ex, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void debug(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("debug#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.DEBUG.getValue()) {
                    return;
                }
                msOnionLog.debug(className, ex, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月19日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void debug(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("debug#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.INFO.getValue()) {
                    return;
                }
                msOnionLog.debug(className, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Info
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void info(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("info#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.INFO.getValue()) {
                    return;
                }
                msOnionLog.info(className, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void error(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("error#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.ERROR.getValue()) {
                    return;
                }
                msOnionLog.error(className, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void warn(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("warn#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.WARN.getValue()) {
                    return;
                }
                msOnionLog.warn(className, logMsg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void error(String className, Exception ex) {
        try {
            String msg = getMsg(className, ex, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("error#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.ERROR.getValue()) {
                    return;
                }
                msOnionLog.error(className, ex);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void warn(String className, Exception ex) {
        try {
            String msg = getMsg(className, ex, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("warn#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.WARN.getValue()) {
                    return;
                }
                msOnionLog.warn(className, ex);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void debug(String className, Exception ex) {
        try {
            String msg = getMsg(className, ex, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("debug#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.DEBUG.getValue()) {
                    return;
                }
                msOnionLog.debug(className, ex);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.debug(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    //////////////// Throwable cause，Begin /////////////////////////////////////

    /**
     * 日志级别：Info
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void info(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("info#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.INFO.getValue()) {
                    return;
                }
                msOnionLog.info(className, cause, logMsg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void error(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("error#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.ERROR.getValue()) {
                    return;
                }
                msOnionLog.error(className, cause, logMsg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void warn(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("warn#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.WARN.getValue()) {
                    return;
                }
                msOnionLog.warn(className, cause, logMsg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void debug(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("debug#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.DEBUG.getValue()) {
                    return;
                }
                msOnionLog.debug(className, cause, logMsg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.debug(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void error(String className, Throwable cause) {
        try {
            String msg = getMsg(className, cause, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("error#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.ERROR.getValue()) {
                    return;
                }
                msOnionLog.error(className, cause);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void warn(String className, Throwable cause) {
        try {
            String msg = getMsg(className, cause, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("warn#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.WARN.getValue()) {
                    return;
                }
                msOnionLog.warn(className, cause);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.error(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public void debug(String className, Throwable cause) {
        try {
            String msg = getMsg(className, cause, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            // 如果打印
            if (MsOnionZookeeperUtils.getPrintToConsole(msOnionDomain, curatorZookeeperClient, retryNTimes)
                    >= DEFAULT_PRINT_TO_CONSOLE_VALUE) {
                System.out.println(String.format("debug#%s", msg));
            } else if (null != msOnionLog) {
                // 判断是否打印这个级别的日志
                long logLevel = MsOnionZookeeperUtils.getLogLevel(msOnionDomain, curatorZookeeperClient, retryNTimes);
                if (logLevel > MsOnionLogLevel.DEBUG.getValue()) {
                    return;
                }
                msOnionLog.debug(className, cause);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
//			e.printStackTrace();
            if (null != msOnionLog) {
                msOnionLog.debug(className, e);
            } else {
                e.printStackTrace();
            }
        }
    }

    //////////////// Throwable cause，End /////////////////////////////////////

    /**
     * 获取日志信息
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @return 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    private String getMsg(String className, Exception ex, String logMsg) {

        if (null == this.msOnionDomain || StringUtils.isBlank(this.msOnionDomain.getEnvironment())) {
            if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg) && null != ex) {
                return String.format("environment: %s, %s, %s, %s", this.msOnionDomain.getEnvironment(),
                        className, ExceptionUtils.getStackTrace(ex), logMsg);
            } else if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg)) {
                return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
                        className, logMsg);
            } else if (StringUtils.isNotBlank(className) && null != ex) {
                return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
                        className, ExceptionUtils.getStackTrace(ex));
            } else if (StringUtils.isNotBlank(logMsg)) {
                return logMsg;
            }
            return null;
        }

        if (null != className && StringUtils.isNotBlank(logMsg) && null != ex) {
            return String.format("%s, %s, %s", className, ExceptionUtils.getStackTrace(ex), logMsg);
        } else if (null != className && StringUtils.isNotBlank(logMsg)) {
            return String.format("%s, %s", className, logMsg);
        } else if (null != className && null != ex) {
            return String.format("%s, %s", className, ExceptionUtils.getStackTrace(ex));
        } else if (StringUtils.isNotBlank(logMsg)) {
            return logMsg;
        }
        return null;
    }

    /**
     * 获取日志信息
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @return 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    private String getMsg(String className, Throwable ex, String logMsg) {

        if (null == this.msOnionDomain || StringUtils.isBlank(this.msOnionDomain.getEnvironment())) {
            if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg) && null != ex) {
                return String.format("environment: %s, %s, %s, %s", this.msOnionDomain.getEnvironment(),
                        className, ExceptionUtils.getStackTrace(ex), logMsg);
            } else if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg)) {
                return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
                        className, logMsg);
            } else if (StringUtils.isNotBlank(className) && null != ex) {
                return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
                        className, ExceptionUtils.getStackTrace(ex));
            } else if (StringUtils.isNotBlank(logMsg)) {
                return logMsg;
            }
            return null;
        }

        if (null != className && StringUtils.isNotBlank(logMsg) && null != ex) {
            return String.format("%s, %s, %s", className, ExceptionUtils.getStackTrace(ex), logMsg);
        } else if (null != className && StringUtils.isNotBlank(logMsg)) {
            return String.format("%s, %s", className, logMsg);
        } else if (null != className && null != ex) {
            return String.format("%s, %s", className, ExceptionUtils.getStackTrace(ex));
        } else if (StringUtils.isNotBlank(logMsg)) {
            return logMsg;
        }
        return null;
    }

    ///////////////////  静态日志 ############ Begin ////////////////////////////

    /**
     * 获取日志信息
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    public static String doGetMsg(String className, Exception ex, String logMsg) {
        if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg) && null != ex) {
            return String.format("%s, %s, %s", className, ex, logMsg);
        } else if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg)) {
            return String.format("%s, %s", className, logMsg);
        } else if (StringUtils.isNotBlank(className) && null != ex) {
            return String.format("%s, %s", className, ex);
        } else if (StringUtils.isNotBlank(logMsg)) {
            return logMsg;
        }
        return null;
    }

    /**
     * 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
     * 其他非单元测试，必须使用成员方法！！！
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Title: _info
     * @Description: 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
     * 其他非单元测试，必须使用成员方法！！！
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月7日 下午2:19:35
     */
    public static void doInfo(String className, Exception ex, String logMsg) {
        String msg = doGetMsg(className, ex, logMsg);
        if (StringUtils.isBlank(msg)) {
            return;
        }
        if (IS_PRINT_TO_CONSOLE) {
            System.out.println(msg);
        } /*else {
            FLogger.getInstance().info(String.format("doInfo#className=%s,msg=%s", className, msg));
        }*/
    }

    /**
     * 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
     * 其他非单元测试，必须使用成员方法！！！
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Title: _info
     * @Description: 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
     * 其他非单元测试，必须使用成员方法！！！
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月7日 下午2:19:35
     */
    public static void doInfo(String className, String logMsg) {
        doInfo(className, null, logMsg);
    }

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    public static void doWarn(String className, String logMsg) {
        try {
            String msg = doGetMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (IS_PRINT_TO_CONSOLE) {
                System.err.println(msg);
            } else {
                FLogger.getInstance().warn(String.format("doWarn#className=%s,msg=%s", className, msg));
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    public static void doError(String className, String logMsg) {
        try {
            String msg = doGetMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (IS_PRINT_TO_CONSOLE) {
                System.err.println(msg);
            } else {
                FLogger.getInstance().error(String.format("doError#className=%s,msg=%s", className, msg));
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    ///////////////////  静态日志 ############ End ////////////////////////////

}
