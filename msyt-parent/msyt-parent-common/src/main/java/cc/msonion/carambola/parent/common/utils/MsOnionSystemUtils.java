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

package cc.msonion.carambola.parent.common.utils;

import org.apache.commons.lang3.SystemUtils;

import java.util.Properties;

/**
 * 系统工具类
 * @Title: MsOnionSystemUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 日期工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午7:01:37
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月1日 下午7:01:37
 * @Modify-version: V2.0.0
 * @Modify-description: 创建
 */

/**
 * 系统工具类
 * @ClassName: MsOnionSystemUtils
 * @Description: 系统工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午7:01:37
 */
public final class MsOnionSystemUtils extends SystemUtils {

    /**
     * 操作系统：Windows
     */
    public static final String OS_WINDOWS = "Windows";

    /**
     * os.name
     */
    public static final String PROPS_KEY_OS_NAME = "os.name";

    /**
     * user.name
     */
    public static final String PROPS_KEY_USER_NAME = "user.name";

    /**
     * user.dir
     */
    public static final String PROPS_KEY_USER_DIR = "user.dir";

    /**
     * user.home
     */
    public static final String PROPS_KEY_USER_HOME = "user.home";

    /**
     * java.home
     */
    public static final String PROPS_KEY_JAVA_HOME = "java.home";

    /**
     * 系统 Properties
     */
    private static final Properties SYS_PROPS = System.getProperties();

    private MsOnionSystemUtils() {}

    /**
     * 获取操作系统名称，例如：Windows 10
     * @return 返回操作系统名称
     */
    public static String getOsName() {
        return System.getProperty(PROPS_KEY_OS_NAME);
    }

    /**
     * 获取系统当前用户的账户名称，例如：HeroCao
     * @return 返回系统当前用户的账户名称
     */
    public static String getUserName() {
        return System.getProperty(PROPS_KEY_USER_NAME);
    }

//    /**
//     * 用户的当前工作目录，例如：C:\Users\HeroCao\Downloads\HeroCao\Code\GitLab\2017-03-22\Ms.Onion-YT\msyt-parent\msyt-parent-common
//     * @return 返回用户的当前工作目录
//     */
//    public static String getUserDir() {
//        return System.getProperty(PROPS_KEY_USER_DIR);
//    }

//    /**
//     * 用户的主目录，例如：C:\Users\HeroCao
//     * @return 返回用户的主目录
//     */
//    public static String getUserHome() {
//        return System.getProperty(PROPS_KEY_USER_HOME);
//    }

//    /**
//     * Java安装目录，例如：C:\Java\jdk1.8.0_65\jre
//     * @return 返回Java 安装目录
//     */
//    public static String getJavaHome() {
//        return System.getProperty(PROPS_KEY_JAVA_HOME);
//    }

    /**
     * 判断当前系统是不是 windows
     * @return
     */
    public static Boolean isWindows(){
        String osName = MsOnionSystemUtils.getOsName();
        if (osName.toLowerCase().contains(MsOnionSystemUtils.OS_WINDOWS.toLowerCase())) {
            return true;
        }
        return false;
    }

}
