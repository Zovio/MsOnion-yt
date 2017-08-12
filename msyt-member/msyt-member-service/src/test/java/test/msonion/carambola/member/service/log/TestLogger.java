package test.msonion.carambola.member.service.log;

/**
 * Created by HeroCao on 2017/5/19.
 */

import cc.msonion.carambola.parent.interfaces.log.MsOnionLog;
import com.cyfonly.flogger.FLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName: MsOnionLogger
 * @Description: 洋葱小姐分布式架构日志器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月7日 下午1:54:36
 *
 */
@Component
public class TestLogger implements MsOnionLog, Serializable {

//	/**
//	 * 域名：生产环境、预览（预发布）环境、稳定环境、测试环境、开发环境域名
//	 */
//	@Autowired
//	private MsOnionDomain msOnionDomain;

    /**
     * 是否打印日志到控制台，测试环境、预览环境、生产环境，这3个环境必须设置为false，关闭在控制台打印日志，
     * 只有开发环境才设置为true
     */
//	@Value("${msOnion.isPrintToConsole}")
    private boolean isPrintToConsole;

    /**
     * FLogger实例对象
     */
    private static final FLogger LOGGER = FLogger.getInstance();

    /**
     * @Fields serialVersionUID : 自动生成
     */
    private static final long serialVersionUID = -4632966489663333388L;

    /**
     * 日志级别：Info
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void info(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.info(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Error
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void error(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.error(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Warn
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void warn(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.warn(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Debug
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void debug(String className, Exception ex, String logMsg) {
        try {
            String msg = getMsg(className, ex, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.debug(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月19日 下午12:19:23
     */
    @Override
    public void debug(String className, String logMsg) {

    }

    /**
     * 日志级别：Info
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void info(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.info(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Error
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void error(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.error(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Warn
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void warn(String className, String logMsg) {
        try {
            String msg = getMsg(className, null, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.warn(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Error
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void error(String className, Exception ex) {
        try {
            String msg = getMsg(className, ex, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.error(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Warn
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void warn(String className, Exception ex) {
        try {
            String msg = getMsg(className, ex, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.warn(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Debug
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void debug(String className, Exception ex) {
        try {
            String msg = getMsg(className, ex, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.debug(msg);
            }
        } catch (Exception e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    //////////////// Throwable cause，Begin /////////////////////////////////////

    /**
     * 日志级别：Info
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void info(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.info(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Error
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void error(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.error(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Warn
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void warn(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.warn(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Debug
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @param logMsg 日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void debug(String className, Throwable cause, String logMsg) {
        try {
            String msg = getMsg(className, cause, logMsg);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.debug(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Error
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void error(String className, Throwable cause) {
        try {
            String msg = getMsg(className, cause, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.error(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Warn
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void warn(String className, Throwable cause) {
        try {
            String msg = getMsg(className, cause, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.warn(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    /**
     * 日志级别：Debug
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param cause 异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void debug(String className, Throwable cause) {
        try {
            String msg = getMsg(className, cause, null);
            if (StringUtils.isBlank(msg)) {
                return;
            }
            if (isPrintToConsole) {
                System.out.println(msg);
            } else {
                LOGGER.debug(msg);
            }
        } catch (Throwable e) {
            // 当前已经是日志中心，因此只能打印异常堆栈了
            e.printStackTrace();
        }
    }

    //////////////// Throwable cause，End /////////////////////////////////////

    /**
     * 获取日志信息
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @param logMsg 日志信息
     * @return
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    private String getMsg(String className, Exception ex, String logMsg) {

//		if (null == this.msOnionDomain || StringUtils.isBlank(this.msOnionDomain.getEnvironment())) {
//			if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg) && null != ex) {
//				return String.format("environment: %s, %s, %s, %s", this.msOnionDomain.getEnvironment(),
//						className, ExceptionUtils.getStackTrace(ex), logMsg);
//			} else if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg)) {
//				return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
//						className, logMsg);
//			} else if (StringUtils.isNotBlank(className) && null != ex) {
//				return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
//						className, ExceptionUtils.getStackTrace(ex));
//			} else if (StringUtils.isNotBlank(logMsg)) {
//				return logMsg;
//			}
//			return null;
//		}

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
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
     * @param ex 异常
     * @param logMsg 日志信息
     * @return
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    private String getMsg(String className, Throwable ex, String logMsg) {

//		if (null == this.msOnionDomain || StringUtils.isBlank(this.msOnionDomain.getEnvironment())) {
//			if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg) && null != ex) {
//				return String.format("environment: %s, %s, %s, %s", this.msOnionDomain.getEnvironment(),
//						className, ExceptionUtils.getStackTrace(ex), logMsg);
//			} else if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg)) {
//				return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
//						className, logMsg);
//			} else if (StringUtils.isNotBlank(className) && null != ex) {
//				return String.format("environment: %s, %s, %s", this.msOnionDomain.getEnvironment(),
//						className, ExceptionUtils.getStackTrace(ex));
//			} else if (StringUtils.isNotBlank(logMsg)) {
//				return logMsg;
//			}
//			return null;
//		}

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
}
