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

package cc.msonion.carambola.parent.interfaces.log.adapter;

import com.cyfonly.flogger.FLogger;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * @Title: MsOnionLog.java
 * @Package: cc.msonion.carambola.parent.interfaces.log
 * @Description: 洋葱小姐框架日志接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月7日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年3月7日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：info、debug、warn、error
 */

/**
 * @ClassName: MsOnionLog
 * @Description: 洋葱小姐框架日志接口
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年3月7日 下午1:48:16
 */
public interface MsOnionLogAdapter extends Serializable {

//    /**
//     * FLogger实例对象
//     */
//    FLogger __LOGGER = FLogger.getInstance();

//    /**
//     * 是否打印日志到控制台，上线要关闭：false
//     */
//    boolean __isPrintToConsole = false;  // true

    /**
     * 日志级别：Info
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void info(String className, Exception ex, String logMsg);

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void error(String className, Exception ex, String logMsg);

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void warn(String className, Exception ex, String logMsg);

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void debug(String className, Exception ex, String logMsg);

    /**
     * 日志级别：debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年7月19日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void debug(String className, String logMsg);

    /**
     * 日志级别：Info
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void info(String className, String logMsg);

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void error(String className, String logMsg);

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void warn(String className, String logMsg);

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void error(String className, Exception ex);

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void warn(String className, Exception ex);

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param ex        异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void debug(String className, Exception ex);

    //////////////// Throwable cause，Begin /////////////////////////////////////

    /**
     * 日志级别：Info
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void info(String className, Throwable cause, String logMsg);

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void error(String className, Throwable cause, String logMsg);

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void warn(String className, Throwable cause, String logMsg);

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @param logMsg    日志信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void debug(String className, Throwable cause, String logMsg);

    /**
     * 日志级别：Error
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void error(String className, Throwable cause);

    /**
     * 日志级别：Warn
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void warn(String className, Throwable cause);

    /**
     * 日志级别：Debug
     *
     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
     * @param cause     异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月18日 下午12:19:23
     */
    @SuppressWarnings("rawtypes")
    void debug(String className, Throwable cause);

    /////////////////// Throwable cause，End //////////////////////////////////

//    /**
//     * 获取日志信息
//     *
//     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()的名称，必须使用： getClass().getName()
//     * @param ex        异常
//     * @param logMsg    日志信息
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月18日 下午12:19:23
//     */
//    @SuppressWarnings("rawtypes")
//    static String __getMsg(String className, Exception ex, String logMsg) {
//        if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg) && null != ex) {
//            return String.format("%s, %s, %s", className, ex, logMsg);
//        } else if (StringUtils.isNotBlank(className) && StringUtils.isNotBlank(logMsg)) {
//            return String.format("%s, %s", className, logMsg);
//        } else if (StringUtils.isNotBlank(className) && null != ex) {
//            return String.format("%s, %s", className, ex);
//        } else if (StringUtils.isNotBlank(logMsg)) {
//            return logMsg;
//        }
//        return null;
//    }
//
//    /**
//     * 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
//     * 其他非单元测试，必须使用成员方法！！！
//     *
//     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
//     * @param ex        异常
//     * @param logMsg    日志信息
//     * @Title: _info
//     * @Description: 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
//     * 其他非单元测试，必须使用成员方法！！！
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月7日 下午2:19:35
//     */
//    static void __info(String className, Exception ex, String logMsg) {
//        String msg = __getMsg(className, ex, logMsg);
//        if (StringUtils.isBlank(msg)) {
//            return;
//        }
//        if (__isPrintToConsole) {
//            System.out.println(msg);
//        } else {
//            FLogger.getInstance().warn(String.format("__info#className=%s,msg=%s", className, msg));
//        }
//    }
//
//    /**
//     * 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
//     * 其他非单元测试，必须使用成员方法！！！
//     *
//     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
//     * @param logMsg    日志信息
//     * @Title: _info
//     * @Description: 必须注意：当前方法只能在单元测试中使用，不可以在其他地方使用！！！
//     * 其他非单元测试，必须使用成员方法！！！
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月7日 下午2:19:35
//     */
//    static void __info(String className, String logMsg) {
//        __info(className, null, logMsg);
//    }
//
//    /**
//     * 日志级别：Warn
//     *
//     * @param className 目标Class实例对象的名称，必须使用： getClass().getName()
//     * @param logMsg    日志信息
//     * @Author: HeroCao hero-cao@msyc.cc
//     * @Date: 2017年3月18日 下午12:19:23
//     */
//    static void __warn(String className, String logMsg) {
//        try {
//            String msg = __getMsg(className, null, logMsg);
//            if (StringUtils.isBlank(msg)) {
//                return;
//            }
//            if (__isPrintToConsole) {
//                System.err.println(msg);
//            } else {
//                FLogger.getInstance().warn(String.format("__warn#className=%s,msg=%s", className, msg));
//            }
//        } catch (Exception e) {
//            // 当前已经是日志中心，因此只能打印异常堆栈了
//            e.printStackTrace();
//        }
//    }

//	public static void __setPrintToConsole(boolean isPrintToConsole) {
//		__isPrintToConsole = isPrintToConsole;
//	}

}
