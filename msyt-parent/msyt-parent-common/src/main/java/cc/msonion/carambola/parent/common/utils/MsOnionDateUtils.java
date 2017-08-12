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

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @Title: MsOnionDateUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 日期工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午7:01:37
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午7:01:37
 * @Modify-version: V2.0.0
 * @Modify-description: 创建
 */

/**
 * @ClassName: MsOnionDateUtils
 * @Description: 日期工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午7:01:37
 */
public final class MsOnionDateUtils extends org.apache.commons.lang3.time.DateUtils {

    //  // Slash(/)：斜线 ，line(-):线 ， Dot(.)：点 ，中文(CHINESE)

    /**
     * 默认认证码日期，由于是2017-07-20之后才上线的，因此可以设置这个时间。
     */
    public static final String DEFAULT_VERIFICATION_CODE_DATE = "2017-06-20";  // 2017-07-20

    /**
     * 东8区时区，北京时间
     */
    public static final TimeZone TIME_ZONE_GMT_8 = TimeZone.getTimeZone("GMT+8");

    /**
     * 一年365天
     */
    public static final int YEAR_OF_DAYS_365 = 365;

    /**
     * 一年366天
     */
    public static final int YEAR_OF_DAYS_366 = 366;

    /**
     * 一周的星期
     */
    public static final String[] WEEKDAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /////////////////// MyBatis 逆向工程支持的日期 ## Begin /////////////////////

    /////////////////// 中文日期格式 ## Begin  ///////////////////

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日 22:12:28:998
     */
    public static final String AT_CN_YYYY_MM_DD_HH_MM_SS_SSS = "@CnYyyyMMddHHmmssSSS";

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日 22:12:28
     */
    public static final String AT_CN_YYYY_MM_DD_HH_MM_SS = "@CnYyyyMMddHHmmss";

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日 22:12
     */
    public static final String AT_CN_YYYY_MM_DD_HH_MM = "@CnYyyyMMddHHmm";

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日 22
     */
    public static final String AT_CN_YYYY_MM_DD_HH = "@CnYyyyMMddHH";

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日
     */
    public static final String AT_CN_YYYY_MM_DD = "@CnYyyyMMdd";

    /**
     * MyBatis逆向工程生成日期格式：2017年05月
     */
    public static final String AT_CN_YYYY_MM = "@CnYyyyMM";

    /**
     * MyBatis逆向工程生成日期格式：2017年
     */
    public static final String AT_CN_YYYY = "@CnYyyy";

    /////////////////// 中文日期格式 ## End  ///////////////////

    /////////////////// 英文日期格式 ## Begin  ///////////////////

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日 22:12:28:998
     */
    public static final String AT_EN_YYYY_MM_DD_HH_MM_SS_SSS = "@EnYyyyMMddHHmmssSSS";

    /**
     * MyBatis逆向工程生成日期格式：2017-05-02 22:12:28
     */
    public static final String AT_EN_YYYY_MM_DD_HH_MM_SS = "@EnYyyyMMddHHmmss";

    /**
     * MyBatis逆向工程生成日期格式：2017-05-02 22:12
     */
    public static final String AT_EN_YYYY_MM_DD_HH_MM = "@EnYyyyMMddHHmm";

    /**
     * MyBatis逆向工程生成日期格式：2017-05-02 22
     */
    public static final String AT_EN_YYYY_MM_DD_HH = "@EnYyyyMMddHH";

    /**
     * MyBatis逆向工程生成日期格式：2017年05月02日
     */
    public static final String AT_EN_YYYY_MM_DD = "@EnYyyyMMdd";

    /**
     * MyBatis逆向工程生成日期格式：2017-05
     */
    public static final String AT_EN_YYYY_MM = "@EnYyyyMM";

    /**
     * MyBatis逆向工程生成日期格式：2017
     */
    public static final String AT_EN_YYYY = "@EnYyyy";

    /**
     * MyBatis逆向工程生成日期格式：22:12:28:998
     */
    public static final String AT_EN_HH_MM_SS_SSS = "@EnHHmmssSSS";

    /**
     * MyBatis逆向工程生成日期格式：22:12:28，小时:分钟:秒
     */
    public static final String AT_EN_HH_MM_SS = "@EnHHmmss";

    /**
     * MyBatis逆向工程生成日期格式：22:12，小时:分钟
     */
    public static final String AT_EN_HH_MM = "@EnHHmm";

    /**
     * MyBatis逆向工程生成日期格式：22，小时
     */
    public static final String AT_EN_HH = "@EnHH";

    //    /**
    //     * MyBatis逆向工程生成日期格式：12，月份
    //     */
    //    public static final String AT_EN_MONTH = "@EnMonth";

    //    /**
    //	 * MyBatis逆向工程生成日期格式：03，日
    //	 */
    //	public static final String AT_EN_DD = "@EnDD";

    //    /**
    //     * MyBatis逆向工程生成日期格式：38，分钟
    //     */
    //    public static final String AT_EN_MINUTE = "@EnMinute";

    /**
     * MyBatis逆向工程生成日期格式：38，分钟
     */
    public static final String AT_EN_MM = "@EnMM";


    /////////////////// 英文日期格式 ## End  ///////////////////


    /////////////////// MyBatis 逆向工程支持的日期 ## End /////////////////////

    private MsOnionDateUtils() {
        throw new AssertionError();
    }

    /////////////////////// 英文-日期格式，Begin  /////////////////////////////

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:SSS，例如：2017-04-24 15:35:18:998
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS_SSS_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-04-24 15:35:18
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy-MM-dd HH:mm，例如：2017-04-24 15:35
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy-MM-dd HH，例如：2017-04-24 15
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy-MM-dd HH");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy-MM-dd，例如：2017-04-24
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy-MM-dd");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy-MM，例如：2017-04
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy-MM");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy，例如：2017
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：HH:mm:ss:SSS，例如：15:37:18:998
     */
    private static final ThreadLocal<SimpleDateFormat> HH_MM_SS_SSS_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("HH:mm:ss:SSS");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：HH:mm:ss，例如：15:37:18
     */
    private static final ThreadLocal<SimpleDateFormat> HH_MM_SS_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("HH:mm:ss");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：HH:mm，例如：15:37
     */
    private static final ThreadLocal<SimpleDateFormat> HH_MM_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("HH:mm");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：HH，例如：15
     */
    private static final ThreadLocal<SimpleDateFormat> HH_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("HH");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：mm，例如：08（分钟）
     */
    private static final ThreadLocal<SimpleDateFormat> MM_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("mm");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("mm");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /////////////////////// 英文-日期格式，End  /////////////////////////////

    ////////////////////////// Unity 格式 ## Begin ////////////////////////

    /**
     * 日期格式：yyyyMMddHHmmssSSS，例如：20170501195808988
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS_SSS_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyyMMddHHmmssSSS");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyyMMddHHmmss，例如：20170501195808
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyyMMddHHmmss");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyyMMddHHmm，例如：201705011958
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyyMMddHHmm");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyyMMddHH，例如：2017050119
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyyMMddHH");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyyMMdd，例如：20170501
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyyMMdd");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyyMM，例如：201705
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyyMM");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy，例如：2017
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：HHmmss，例如：18:28:38
     */
    private static final ThreadLocal<SimpleDateFormat> HH_MM_SS_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("HHmmss");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：HHmm，例如：1538
     */
    private static final ThreadLocal<SimpleDateFormat> HH_MM_UNITY = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("HHmm");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    ////////////////////////// Unity 格式 ## End ////////////////////////

    /////////////////////// 中文日期格式，Begin /////////////////////////////

    /**
     * 日期格式：yyyy年
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy年MM月
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年MM月");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy年MM月dd日
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年MM月dd日");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy年MM月dd日 HH
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年MM月dd日 HH");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm:ss
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm:ss:SSS
     */
    private static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HH_MM_SS_SSS_CHINESE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SSS");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SSS");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /////////////////////// 中文日期格式，End  /////////////////////////////

    /**
     * 日期格式：M.d
     */
    private static final ThreadLocal<SimpleDateFormat> M_D = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // Docker容器中，存在时区问题
//            return new SimpleDateFormat("M.d");
            // 默认设置东8区的时区，如果是国际化，就存在问题！！！
            SimpleDateFormat sdf = new SimpleDateFormat("M.d");
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            return sdf;
        }
    };

    /**
     * yyyy-MM-dd HH:mm
     *
     * @param dateStr 日期字符串
     * @return 返回日期格式：yyyy-MM-dd HH:mm
     * @throws MsOnionException 异常
     */
    public static Date parseDate(String dateStr) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
        // new SimpleDateFormat().parse(String )
        //        new SimpleDateFormat().format(new Date())
    }


    /**
     * 日期字符串格式：yyyy-MM，转换成日期，
     *
     * @param dateStr 日期字符串，格式：yyyy-MM，否则报错
     * @return 返回日期
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMLine(String dateStr) throws MsOnionException {
        try {
            return YYYY_MM_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    //    /**
    //     * yyyy-MM-dd HH:mm
    //     *
    //     * @param date 日期
    //     * @return
    //     */
    //    @Deprecated
    //    public static String formatMM(Date date) {
    //        return YYYY_MM_DD_HH_MM_FORMAT.get().format(date);
    //    }

    ////////////////////// 格式化格式 ， Begin ///////////////////////////

    ////////////////////// 格式化日期格式：中文格式 ， Begin ///////////////////////////

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm:ss:SSS，例如：2017年04月24日 13:58:18:998
     *
     * @param date 日期
     * @return 返回日期格式：yyyy年MM月dd日 HH:mm:ss:SSS，例如：2017年04月24日 13:58:18:998
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmssSSSChinese(Date date) throws MsOnionException {
        return YYYY_MM_DD_HH_MM_SS_SSS_CHINESE.get().format(date);
    }

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm:ss，例如：2017年04月24日 13:58:18
     *
     * @param date 日期
     * @return 返回日期格式：yyyy年MM月dd日 HH:mm:ss，例如：2017年04月24日 13:58:18
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmssChinese(Date date) throws MsOnionException {
        return YYYY_MM_DD_HH_MM_SS_CHINESE.get().format(date);
    }

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm，例如：2017年04月24日 13:58
     *
     * @param date 日期
     * @return 返回日期格式：yyyy年MM月dd日 HH:mm，例如：2017年04月24日 13:58
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmChinese(Date date) throws MsOnionException {
        return YYYY_MM_DD_HH_MM_CHINESE.get().format(date);
    }

    /**
     * 日期格式：yyyy年MM月dd日 HH，例如：2017年04月24日 13
     *
     * @param date 日期
     * @return 返回日期格式：yyyy年MM月dd日 HH，例如：2017年04月24日 13
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHChinese(Date date) throws MsOnionException {
        return YYYY_MM_DD_HH_CHINESE.get().format(date);
    }

    /**
     * 日期格式：yyyy年MM月dd日，例如：2017年04月24日
     *
     * @param date 日期
     * @return 返回日期格式：yyyy年MM月dd日，例如：2017年04月24日
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddChinese(Date date) throws MsOnionException {
        return YYYY_MM_DD_CHINESE.get().format(date);
    }

    /**
     * 格式化日期为：yyyy年MM月，例如：2017年04月
     *
     * @param date 日期
     * @return 返回日期为：yyyy年MM月，例如：2017年04月
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMChinese(Date date) throws MsOnionException {
        return YYYY_MM_CHINESE.get().format(date);
    }

    /**
     * 格式化日期为：yyyy年，例如：2017年
     *
     * @param date 日期
     * @return 返回日期为：yyyy年，例如：2017年
     * @throws MsOnionException 异常
     */
    public static String formatYyyyChinese(Date date) throws MsOnionException {
        return YYYY_CHINESE.get().format(date);
    }

    ////////////////////// 格式化日期格式：中文格式 ， Begin ///////////////////////////

    ////////////////////// 格式化日期格式：英文-格式 ， Begin ///////////////////////////

    /**
     * 期格式：yyyy-MM-dd HH:mm:ss:SSS，例如：2017-04-24 13:58:18:998
     *
     * @param date 期
     * @return 返回期格式：yyyy-MM-dd HH:mm:ss:SSS，例如：2017-04-24 13:58:18:998
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmssSSS(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_SS_SSS_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyy-MM-dd HH:mm:ss，例如：2017-04-24 13:58:18
     *
     * @param date 期
     * @return 返回期格式：yyyy-MM-dd HH:mm:ss，例如：2017-04-24 13:58:18
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmss(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_SS_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyy-MM-dd HH:mm，例如：2017-04-24 13:58
     *
     * @param date 期
     * @return 返回期格式：yyyy-MM-dd HH:mm，例如：2017-04-24 13:58
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmm(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyy-MM-dd HH，例如：2017-04-24 13
     *
     * @param date 期
     * @return 返回期格式：yyyy-MM-dd HH，例如：2017-04-24 13
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHH(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyy-MM-dd，例如：2017-04-24
     *
     * @param date 期
     * @return 返回期格式：yyyy-MM-dd，例如：2017-04-24
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMdd(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：yyyy-MM，例如：2017-04
     *
     * @param date 期
     * @return 返回期为：yyyy-MM，例如：2017-04
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMM(Date date) throws MsOnionException {
        try {
            return YYYY_MM_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：yyyy，例如：2017
     *
     * @param date 期
     * @return 返回期为：yyyy，例如：2017
     * @throws MsOnionException 异常
     */
    public static String formatYyyy(Date date) throws MsOnionException {
        try {
            return YYYY_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HH:mm:ss:SSS，例如：15:48:18:998
     *
     * @param date 期
     * @return 返回期为：HH:mm:ss:SSS，例如：15:48:18:998
     * @throws MsOnionException 异常
     */
    public static String formatHHmmssSSS(Date date) throws MsOnionException {
        try {
            return HH_MM_SS_SSS_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HH:mm:ss，例如：15:48:18
     *
     * @param date 期
     * @return 返回期为：HH:mm:ss，例如：15:48:18
     * @throws MsOnionException 异常
     */
    public static String formatHHmmss(Date date) throws MsOnionException {
        try {
            return HH_MM_SS_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HH:mm，例如：15:48
     *
     * @param date 期
     * @return 返回期为：HH:mm，例如：15:48
     * @throws MsOnionException 异常
     */
    public static String formatHHmm(Date date) throws MsOnionException {
        try {
            return HH_MM_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HH ，例如：15 (小时）
     *
     * @param date 期
     * @return 返回期为：HH ，例如：15 (小时）
     * @throws MsOnionException 异常
     */
    public static String formatHH(Date date) throws MsOnionException {
        try {
            return HH_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HH:mm，例如：15（分钟）
     *
     * @param date 期
     * @return 返回期为：HH:mm，例如：15（分钟）
     * @throws MsOnionException 异常
     */
    public static String formatMM(Date date) throws MsOnionException {
        try {
            return MM_FORMAT.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////// 格式化日期格式：英文-格式 ， Begin ///////////////////////////

    //////////////////////// Unity ### Begin ////////////////////////////////////

    /**
     * 期格式：yyyyMMddHHmmssSSS，例如：20170501283848988
     *
     * @param date 期
     * @return 返回期格式：yyyyMMddHHmmssSSS，例如：20170501283848988
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmssSSSUnity(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_SS_SSS_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyyMMddHHmmss，例如：20170501283848
     *
     * @param date 期
     * @return 返回期格式：yyyyMMddHHmmss，例如：20170501283848
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmssUnity(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_SS_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyyMMddHHmm，例如：201705012006
     *
     * @param date 期
     * @return 返回期格式：yyyyMMddHHmm，例如：201705012006
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHmmUnity(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyyMMddHH，例如：2017050120
     *
     * @param date 期
     * @return 返回期格式：yyyyMMddHH，例如：2017050120
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddHHUnity(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 期格式：yyyyMMdd，例如：20170501
     *
     * @param date 期
     * @return 返回期格式：yyyyMMdd，例如：20170501
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMddUnity(Date date) throws MsOnionException {
        try {
            return YYYY_MM_DD_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：yyyyMM，例如：201705
     *
     * @param date 期
     * @return 返回期为：yyyyMM，例如：201705
     * @throws MsOnionException 异常
     */
    public static String formatYyyyMMUnity(Date date) throws MsOnionException {
        try {
            return YYYY_MM_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：yyyy，例如：2017
     *
     * @param date 期
     * @return 返回期为：yyyy，例如：2017
     * @throws MsOnionException 异常
     */
    public static String formatYyyyUnity(Date date) throws MsOnionException {
        try {
            return YYYY_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HHmmss，例如：154818
     *
     * @param date 期
     * @return 返回期为：HHmmss，例如：154818
     * @throws MsOnionException 异常
     */
    public static String formatHHmmssUnity(Date date) throws MsOnionException {
        try {
            return HH_MM_SS_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化期为：HHmm，例如：1548
     *
     * @param date 期
     * @return 返回期为：HHmm，例如：1548
     * @throws MsOnionException 异常
     */
    public static String formatHHmmUnity(Date date) throws MsOnionException {
        try {
            return HH_MM_UNITY.get().format(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    //////////////////////// Unity ### End ////////////////////////////////////


    ////////////////////// 格式化格式 ， End  ///////////////////////////


    ////////////////////// 转换为日期Date ， Begin  ///////////////////////////

    ////////////////////// 中文日期，转换为日期 Date ， Begin  ///////////////////////////

    /**
     * 将中文日期格式：2017年04月24日 15:05:02 转换成 Date
     *
     * @param dateStr 日期格式字符串，例如：2017年04月24日 15:05:02
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddHHmmssChinese(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_HH_MM_SS_CHINESE.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将中文日期格式：2017年04月24日 15:05 转换成 Date
     *
     * @param dateStr 日期格式字符串，例如：2017年04月24日 15:05
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddHHmmChinese(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_HH_MM_CHINESE.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将中文日期格式：2017年04月24日 15 转换成 Date
     *
     * @param dateStr 日期格式字符串，例如：2017年04月24日 15
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddHHChinese(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_HH_CHINESE.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将中文日期格式：2017年04月24日 转换成 Date
     *
     * @param dateStr 日期格式字符串，例如：2017年04月24日
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddChinese(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_CHINESE.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将中文日期格式：2017年04月 转换成 Date
     *
     * @param dateStr 日期格式字符串，例如：2017年04月
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMChinese(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_CHINESE.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将中文日期格式：2017年 转换成 Date
     *
     * @param dateStr 日期格式字符串，例如：2017年
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyChinese(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_CHINESE.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////// 中文日期，转换为日期 Date ， End  ///////////////////////////


    ////////////////////// 英文-日期，转换为日期 Date ， Begin  ///////////////////////////

    /**
     * 将英文日期格式 转换成 Date
     *
     * @param dateStr 期格式字符串，例如：2017-04-24 15:05:02
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddHHmmss(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_HH_MM_SS_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将英文日期格式 转换成 Date
     *
     * @param dateStr 英文日期格式字符串，例如：2017-04-24 15:05
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddHHmm(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_HH_MM_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将英文日期格式 转换成 Date
     *
     * @param dateStr 英文日期格式字符串，例如：2017-04-24 15
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMddHH(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_HH_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将英文日期格式 转换成 Date
     *
     * @param dateStr 期格式字符串，例如：2017-04-24
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMMdd(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_DD_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 将英文日期格式 转换成 Date
     *
     * @param dateStr 英文日期格式字符串，例如：2017-04
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyyMM(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_MM_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 年 转换成 Date
     *
     * @param dateStr 年字符串，例如：2017
     * @return
     * @throws MsOnionException 异常
     */
    public static Date parseWithYyyy(String dateStr) throws MsOnionException {
        try {
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            dateStr = dateStr.trim();
            return YYYY_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////// 英文-日期，转换为日期 Date ， End  ///////////////////////////

    ////////////////////// 转换为日期Date ， End  ///////////////////////////

    ////////////////////// 将日期的 long值转换成中文字符串日期 ， Begin  ///////////////////////////

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm:ss，例如：2017年04月24日 13:58:18
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy年MM月dd日 HH:mm:ss，例如：2017年04月24日 13:58:18
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmmssChinese(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmmssChinese(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyy年MM月dd日 HH:mm，例如：2017年04月24日 13:58
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy年MM月dd日 HH:mm，例如：2017年04月24日 13:58
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmmChinese(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmmChinese(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyy年MM月dd日 HH，例如：2017年04月24日 13
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy年MM月dd日 HH，例如：2017年04月24日 13
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHChinese(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHChinese(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyy年MM月dd日，例如：2017年04月24日
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy年MM月dd日，例如：2017年04月24日
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddChinese(long time) throws MsOnionException {
        try {
            return formatYyyyMMddChinese(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化日期为：yyyy年MM月，例如：2017年04月
     *
     * @param time 日期时间
     * @return 返回日期为：yyyy年MM月，例如：2017年04月
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMChinese(long time) throws MsOnionException {
        try {
            return formatYyyyMMChinese(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化日期为：yyyy年，例如：2017年
     *
     * @param time 日期时间
     * @return 返回日期为：yyyy年，例如：2017年
     * @throws MsOnionException 异常
     */
    public static String toYyyyChinese(long time) throws MsOnionException {
        try {
            return formatYyyyChinese(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ////////////////////// 将日期的 long值转换成中文字符串日期 ， End  ///////////////////////////

    ////////////////////// 将日期的 long值转换成字英文符串日期 ， End  ///////////////////////////

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-04-24 13:58:18
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-04-24 13:58:18
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmmss(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmmss(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyy-MM-dd HH:mm，例如：2017-04-24 13:58
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy-MM-dd HH:mm，例如：2017-04-24 13:58
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmm(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmm(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyy-MM-dd HH，例如：2017-04-24 13
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy-MM-dd HH，例如：2017-04-24 13
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHH(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHH(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyy-MM-dd，例如：2017-04-24
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyy-MM-dd，例如：2017-04-24
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMdd(long time) throws MsOnionException {
        try {
            return formatYyyyMMdd(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化日期为：yyyy-MM，例如：2017-04
     *
     * @param time 日期时间
     * @return 返回日期为：yyyy-MM，例如：2017-04
     * @throws MsOnionException 异常
     */
    public static String toYyyyMM(long time) throws MsOnionException {
        try {
            return formatYyyyMM(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化日期为：yyyy，例如：2017
     *
     * @param time 日期时间
     * @return 返回日期为：yyyy，例如：2017
     * @throws MsOnionException 异常
     */
    public static String toYyyy(long time) throws MsOnionException {
        try {
            return formatYyyy(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    ///////////////////// Unity ## Begin ///////////////////

    /**
     * 日期格式：yyyyMMddHHmmssSSS，例如：20170501201128988
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyyMMddHHmmssSSS，例如：20170501201128988
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmmssSSSUnity(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmmssSSSUnity(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyyMMddHHmmss，例如：20170501201128
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyyMMddHHmmss，例如：20170501201128
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmmssUnity(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmmssUnity(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyyMMddHHmm，例如：201705011358
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyyMMddHHmm，例如：201705011358
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHmmUnity(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHmmUnity(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyyMMddHH，例如：2017050113
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyyMMddHH，例如：2017050113
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddHHUnity(long time) throws MsOnionException {
        try {
            return formatYyyyMMddHHUnity(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 日期格式：yyyyMMdd，例如：20170501
     *
     * @param time 日期时间
     * @return 返回日期格式：yyyyMMdd，例如：20170501
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMddUnity(long time) throws MsOnionException {
        try {
            return formatYyyyMMddUnity(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 格式化日期为：yyyyMM，例如：201705
     *
     * @param time 日期时间
     * @return 返回日期为：yyyyMM，例如：201705
     * @throws MsOnionException 异常
     */
    public static String toYyyyMMUnity(long time) throws MsOnionException {
        try {
            return formatYyyyMMUnity(new Date(time));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    //    /**
    //     * 格式化日期为：yyyy，例如：2017
    //     *
    //     * @param time 日期时间
    //     * @return 返回日期为：yyyy，例如：2017
    //     * @throws MsOnionException 异常
    //     */
    //    public static String toYyyyForUnity(long time) throws MsOnionException {
    //        try {
    //            return formatYyyy(new Date(time));
    //        } catch (Exception ex) {
    //            throw new MsOnionException(ex);
    //        }
    //    }

    ///////////////////// Unity ## End  ///////////////////

    ////////////////////// 将日期的 long值转换成英文字符串日期 ， End  ///////////////////////////

    ////////////////////// 解决Docker容器中，时间存在时区问题 ， Begin  ///////////////////////////


    /**
     * 获取东8区的时区，当前时间（北京时间、上海时间）
     *
     * @return 返回东8区的时区，当前时间（北京时间、上海时间）
     * @throws MsOnionException 异常
     */
    public static Date getNowDate() throws MsOnionException {
        try {
            SimpleDateFormat sdf = YYYY_MM_DD_HH_MM_SS_FORMAT.get();
            sdf.setTimeZone(TIME_ZONE_GMT_8);
            String dateStr = sdf.format(new Date());
            return parseWithYyyyMMddHHmmss(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }


    ////////////////////// 解决Docker容器中，时间存在时区问题 ， End  ///////////////////////////

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     * @throws MsOnionException
     */
    @Deprecated
    public static Date parseSS(String dateStr) throws MsOnionException {
        try {
            return YYYY_MM_DD_HH_MM_SS_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    @Deprecated
    public static Date parseSimpleSS(String dateStr) throws MsOnionException {
        try {
            return HH_MM_SS_FORMAT.get().parse(dateStr);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    @Deprecated
    public static String formatSS(Date date) {
        if (date == null) {
            return null;
        }
        return YYYY_MM_DD_HH_MM_SS_FORMAT.get().format(date);
    }

    @Deprecated
    public static String formatSimpleSS(Date date) {
        return HH_MM_SS_FORMAT.get().format(date);
    }

    /**
     * yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws MsOnionException
     */
    @Deprecated
    public static Date parseDD(String dateStr) {
        try {
            return YYYY_MM_DD_FORMAT.get().parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    @Deprecated
    public static String formatDD(Date date) {
        return YYYY_MM_DD_FORMAT.get().format(date);
    }

    /**
     * @param offsetDays 当前时间的偏移：-2前天，-1昨天，0今天，1明天，2后天
     * @return yyyy-MM-dd
     * @author yangxuehua
     */
    @Deprecated
    public static String getYYYY_MM_DD(int offsetDays) {
        Calendar calendar = Calendar.getInstance();
        if (offsetDays != 0)
            calendar.add(Calendar.DATE, offsetDays);
        Date date = calendar.getTime();
        return formatDD(date);
    }

    /**
     * @param offsetDays 当前时间的偏移：-2前天，-1昨天，0今天，1明天，2后天
     * @return M.d or 明天(M.d)
     */
    public static String getDisplayName(int offsetDays) {
        Calendar calendar = Calendar.getInstance();
        if (offsetDays != 0) {
            calendar.add(Calendar.DATE, offsetDays);
        }
        Date date = calendar.getTime();
        String md = M_D.get().format(date); // e.g：5.2
        switch (offsetDays) {
            case -2:
                return "前天(" + md + ")";
            case -1:
                return "昨天(" + md + ")";
            case 0:
                return "今天(" + md + ")";
            case 1:
                return "明天(" + md + ")";
            case 2:
                return "后天(" + md + ")";
            default:
                return md;
        }
    }

    @Deprecated
    public static String getDisplayForMovieShow(String yyyy_MM_dd) throws MsOnionException {
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = parse.parse(yyyy_MM_dd);

            SimpleDateFormat monthAndDay = new SimpleDateFormat("M月dd日");
            String monthAndDayString = monthAndDay.format(date);

            String today = MsOnionDateUtils.getYYYY_MM_DD(0);
            if (today.equals(yyyy_MM_dd)) {
                return "今天 " + monthAndDayString;
            }
            String tomorrow = MsOnionDateUtils.getYYYY_MM_DD(1);
            if (tomorrow.equals(yyyy_MM_dd)) {
                return "明天 " + monthAndDayString;
            }
            String dayAfterTomorrow = MsOnionDateUtils.getYYYY_MM_DD(2);
            if (dayAfterTomorrow.equals(yyyy_MM_dd)) {
                return "后天 " + monthAndDayString;
            }

            SimpleDateFormat format = new SimpleDateFormat("E M月dd日");
            String ret = format.format(date);
            ret = ret.replaceAll("星期", "周");
            return ret;

        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 到第二天0点的时间间隔
     *
     * @param date
     * @return
     */
    @Deprecated
    public static long getMilliSecondToTomorrow(Date date) {
        String today = formatDD(date);
        Date today0 = parseDD(today);
        return (86400000 - (date.getTime() - today0.getTime()));
    }

    /**
     * 今年多少天，闰年（366天），平年（365天）
     *
     * @return 返回：闰年（366天），平年（365天）
     */
    public static int getYearDays() {
        //        GregorianCalendar calendar = new GregorianCalendar();
        //        int year = calendar.get(Calendar.YEAR);
        // 判断今年是闰年还是平年
        if (isLeapYear()) {
            return YEAR_OF_DAYS_366;
        } else {
            return YEAR_OF_DAYS_365;
        }
    }

    /**
     * 判断今年是闰年还是平年
     *
     * @return 返回true：是闰年（366天），false：平年（365天）
     */
    public static boolean isLeapYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        // 判断今年是闰年还是平年
        return calendar.isLeapYear(year);
    }

    /**
     * 获取随机日期
     *
     * @param beginDate 起始日期，日期格式：yyyy-MM-dd，例如：2017-07-20
     * @param endDate   结束日期，日期格式：yyyy-MM-dd，例如：2017-07-20
     * @return 返回随机日期，日期格式：yyyy-MM-dd，例如：2017-07-20
     * @throws MsOnionException 异常
     */
    public static String randomDate(String beginDate, String endDate) throws MsOnionException {
        try {
            // 可以考虑，从 2017-07-28 （上线）到 当前时间（服务器时间），这时间进行随机
//            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date start = YYYY_MM_DD_FORMAT.get().parse(beginDate);
            Date end = YYYY_MM_DD_FORMAT.get().parse(endDate);
//            Date start = format.parse(beginDate); // 构造开始日期
//            Date end = format.parse(endDate); // 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return beginDate;
            }
            long date = MsOnionRandomUtils.random(start.getTime(), end.getTime());
            return YYYY_MM_DD_FORMAT.get().format(new Date(date));
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取随机日期，验证码专用
     *
     * @return 返回随机日期
     * @throws MsOnionException 异常
     */
    public static Date randomDateForVerificationCode() throws MsOnionException {
        try {
            // 随机时间段为，上线前期（也可以存储到ZK中），结束为服务器当前时间
            String date = randomDate(DEFAULT_VERIFICATION_CODE_DATE, YYYY_MM_DD_FORMAT.get().format(new Date()));
            return YYYY_MM_DD_FORMAT.get().parse(date);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

}
