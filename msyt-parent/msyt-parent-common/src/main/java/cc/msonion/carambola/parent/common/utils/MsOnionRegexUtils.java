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

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: MsOnionRegexUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 正则表达式工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午9:06:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午9:06:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：常用正则表达式判断
 */

/**
 * @ClassName: MsOnionRegexUtils
 * @Description: 正则表达式工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午9:06:58
 */
public final class MsOnionRegexUtils {

    private MsOnionRegexUtils() {
    }

    /*
     * 京东的正则表达式：
     *
     * https://reg.jd.com/misc/js/companyJdValidate.js?t=20130108
     *
     * var validateRegExp = {
    decmal: "^([+-]?)\\d*\\.\\d+$", //浮点数
    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
    intege: "^-?[1-9]\\d*$", //整数
    intege1: "^[1-9]\\d*$", //正整数
    intege2: "^-[1-9]\\d*$", //负整数
    num: "^([+-]?)\\d*\\.?\\d+$", //数字
    num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
    num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
    color: "^[a-fA-F0-9]{6}$", //颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter: "^[A-Za-z]+$", //字母
    letter_l: "^[a-z]+$", //小写字母
    letter_u: "^[A-Z]+$", //大写字母
    mobile: "^0?(13|15|17|18|14)[0-9]{9}$", //手机
    notempty: "^\\S+$", //非空
    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
    fullNumber: "^[0-9]+$", //数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //户名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
    zipcode: "^\\d{6}$", //邮编
    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};


     */

//    /**
//     * 验证Email
//     *
//     * @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkEmail(String email) {
//        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
//        return Pattern.matches(regex, email);
//    }

//    /**
//     * 验证身份证号码
//     *
//     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkIdCard(String idCard) {
//        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
//        return Pattern.matches(regex, idCard);
//    }

//    /**
//     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
//     *
//     * @param mobile 移动、联通、电信运营商的号码段
//     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
//     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
//     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
//     *               <p>电信的号段：133、153、180（未启用）、189</p>
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkMobile(String mobile) {
//        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
//        return Pattern.matches(regex, mobile);
//    }

//    /**
//     * 验证固定电话号码
//     *
//     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
//     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
//     *              数字之后是空格分隔的国家（地区）代码。</p>
//     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
//     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
//     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkPhone(String phone) {
//        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
//        return Pattern.matches(regex, phone);
//    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        /**
         * 验证非零的正整数：^\+?[1-9][0-9]*$

         验证非零的负整数：^\-[1-9][0-9]*$

         验证非负整数（正整数 + 0） ^\d+$

         验证非正整数（负整数 + 0） ^((-\d+)|(0+))$

         String regex = "^\\+?[1-9][0-9]*$";

         */
        if (StringUtils.isBlank(digit)) {
            return false;
        }
        // Error
//        String regex = "\\-?[1-9]\\d+";
        String regex = "^\\+?[1-9][0-9]*$";
        return Pattern.matches(regex, digit);
    }

//    /**
//     * 验证整数和浮点数（正负整数和正负浮点数）
//     *
//     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkDecimals(String decimals) {
//        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
//        return Pattern.matches(regex, decimals);
//    }

//    /**
//     * 验证空白字符
//     *
//     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkBlankSpace(String blankSpace) {
//        String regex = "\\s+";
//        return Pattern.matches(regex, blankSpace);
//    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isChinese(String chinese) {
        if (StringUtils.isBlank(chinese)) {
            return false;
        }
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isBirthday(String birthday) {
        if (StringUtils.isBlank(birthday)) {
            return false;
        }
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

//    /**
//     * 验证URL地址
//     *
//     * @param url 格式：http://www.msyc.cc , https://www.msyc.cc
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean isURL(String url) {
//        if (StringUtils.isBlank(url)) {
//            return false;
//        }
//        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
//        return Pattern.matches(regex, url);
//    }

    /**
     * 获取网址URL的一级域名
     *
     * @param url URL
     * @return 返回网址URL的一级域名
     */
    public static String getDomain(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv|hk)", Pattern.CASE_INSENSITIVE);
        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

//    /**
//     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
//     *
//     * @param ipAddress IPv4标准地址
//     * @return 验证成功返回true，验证失败返回false
//     */
//    public static boolean checkIpAddress(String ipAddress) {
//        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
//        return Pattern.matches(regex, ipAddress);
//    }

    ////////// ----------------- 常量定义 ，Begin ////////////////////////

    /**
     * Email正则表达式="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
     */
    // private static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";;

    // private static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
    private static final String EMAIL = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";


    /**
     * 电话号码正则表达式= (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)
     */
    private static final String TELEPHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

    /**
     * 手机号码正则表达式=^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$
     */
    private static final String MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

    /**
     * Integer正则表达式 ^-?(([1-9]\d*$)|0)
     */
    public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";

    /**
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$
     */
    private static final String INTEGER_NEGATIVE = "^[1-9]\\d*|0$";

    /**
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$
     */
    public static final String INTEGER_POSITIVE = "^-[1-9]\\d*|0$";

    /**
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
     */
    private static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

    /**
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$
     */
    private static final String DOUBLE_NEGATIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";

    /**
     * 负Double正则表达式 <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
     */
    private static final String DOUBLE_POSITIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";

    /**
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
     */
    private static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";

    /**
     * 邮编正则表达式  [0-9]\d{5}(?!\d) 国内6位邮编
     */
    public static final String CODE = "[0-9]\\d{5}(?!\\d)";

    /**
     * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$
     */
    private static final String STR_ENG_NUM_DOWN_LINE = "^\\w+$";

    /**
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$
     */
    public static final String STR_ENG_NUM = "^[A-Za-z0-9]+";

    /**
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$
     */
    private static final String STR_ENG = "^[A-Za-z]+$";

    /**
     * 过滤特殊字符串正则
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
     */
    private static final String STR_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    /**
     * 日期正则 支持：
     * YYYY-MM-DD
     * YYYY/MM/DD
     * YYYY_MM_DD
     * YYYYMMDD
     * YYYY.MM.DD的形式
     */
    private static final String DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +
            "(0?2)([-\\/\\._]?)(29)$)" +
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";

    /***
     * 日期正则 支持：
     *  YYYY-MM-DD
     */
    private static final String DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    /**
     * URL正则表达式
     * 匹配 http www ftp
     */
    private static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +
            "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +
            "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +
            "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

    /**
     * 身份证正则表达式
     */
    private static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
            "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

    /**
     * 机构代码
     */
    private static final String JIGOU_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";

    /**
     * 匹配数字组成的字符串  ^[0-9]+$
     */
    private static final String STR_NUM = "^[0-9]+$";

    /**
     * Redis缓存的Key正则表达式，只能是 : 数字 + 大写字母 + "_" + "-"
     * 例如：ITEMBRAND_INFO:ITEMBRAND:PAGE:ITEMBRANDEXAMPLE:C7F97D24CD4077E4CBAFEF23CA64944E:1-10
     */
    private static final String REGEX_REDIS_KEY = "^[0-9A-Z:_-]+$";

    /*
    var pattern_1 = /^.*([\W_])+.*$/i;
    var pattern_2 = /^.*([a-zA-Z])+.*$/i;
    var pattern_3 = /^.*([0-9])+.*$/i;
     */

//    private static final String regex_password_level_1 = "[A-Z]+";
//
//    private static final String regex_password_level_2 = "[a-z]+";
//
//    private static final String regex_password_level_3 = "[0-9]+";
//
//    private static final String regex_password_level_3 = "[0-9]+";

    // String regex = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*+?]+)$)^[\\w~!@#$%\\^&*+?]+$";

    /**
     * 密码必须是字母或特殊符号和数字结合，
     * <p>注意正则表达式还有BUG，需要参考开源中国的JS正则表达式实现</p>
     */
    private static final String REGEX_PASSWORD_LEVEL = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*+?]+)$)^[\\w~!@#$%\\^&*+?]+$";


    //////////////  ------------------验证方法

    /**
     * 判断字段是否为Email 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回true:是Email格式，false：不是Email格式
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isEmail(String str) {
        return regular(str, EMAIL);
    }

    /**
     * 判断是否为电话号码 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回true:是电话号码， false：不是电话号码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isTelephone(String str) {
        return regular(str, TELEPHONE);
    }

    /**
     * 判断是否为手机号码 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回true:是手机号码，false：不是手机号码
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isMobilephone(String str) {
        return regular(str, MOBILE);
    }

    /**
     * 判断是否为Url 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回true:是URL格式，false:不是URL格式
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isUrl(String str) {
        return regular(str, URL);
    }

    /**
     * 判断字段是否为数字 正负整数 正负浮点数 符合返回ture
     *
     * @param str 字符串
     * @return boolean 是否为数字 正负整数 正负浮点数 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isNumber(String str) {
        return regular(str, DOUBLE);
    }

    /**
     * 判断字段是否为Integer，符合返回ture
     *
     * @param str 字符串
     * @return boolean 是否为Integer,符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isInteger(String str) {
        return regular(str, INTEGER);
    }

    /**
     * 判断字段是否为正整数正则表达式 >=0 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为正整数正则表达式 >=0 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isIntegerNotNegative(String str) {
        return regular(str, INTEGER_NEGATIVE);
    }

    /**
     * 判断字段是否为负整数正则表达式 <=0 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为负整数正则表达式 <=0 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isIntegerNotPositive(String str) {
        return regular(str, INTEGER_POSITIVE);
    }

    /**
     * 判断字段是否为DOUBLE 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为DOUBLE 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isDouble(String str) {
        return regular(str, DOUBLE);
    }

    /**
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为正浮点数正则表达式 >=0 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isDoubleNotNegative(String str) {
        return regular(str, DOUBLE_NEGATIVE);
    }

    /**
     * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为负浮点数正则表达式 <=0 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isDoubleNotPositive(String str) {
        return regular(str, DOUBLE_POSITIVE);
    }

    /**
     * 判断字段是否为日期 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回段是否为日期 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isDate(String str) {
        return regular(str, DATE_ALL);
    }

    /**
     * 验证2010-12-10
     *
     * @param str 字符串
     * @return 返回true:是日期格式，false：不是日期格式
     */
    public static boolean isDateFormat(String str) {
        return regular(str, DATE_FORMAT1);
    }

    /**
     * 判断字段是否为年龄 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为年龄 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isAge(String str) {
        return regular(str, AGE);
    }

    /**
     * 判断字段是否超长，按照去掉左右两边空格之后的长度计算，
     * 字串为空返回fasle, 超过长度返回ture，反之返回false
     *
     * @param str    字符串
     * @param length 长度
     * @return boolean 超过长度返回ture，反之返回false
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isLengthOut(String str, int length) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (str.trim().length() > length) {
            return true;
        }
        return false;
    }

    /**
     * 判断字段是否为身份证 符合返回ture
     *
     * @param str 字符串
     * @return boolean 返回是否为身份证 符合返回ture
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isIdCard(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        str = str.trim();

        if (str.length() == 15 || str.length() == 18) {
            return regular(str, IDCARD);
        }
        return false;
    }

    /**
     * 判断字段是否为邮编 符合返回ture
     *
     * @param str 字符串
     * @return boolean
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isPostcode(String str) {
        return regular(str, CODE);
    }

    /**
     * 判断字符串是不是全部是英文字母
     *
     * @param str 字符串
     * @return boolean 返回字符串是不是全部是英文字母
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isEnglish(String str) {
        return regular(str, STR_ENG);
    }

    /**
     * 判断字符串是不是全部是英文字母+数字
     *
     * @param str 字符串
     * @return boolean 返回字符串是不是全部是英文字母+数字
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isEnglishNumber(String str) {
        return regular(str, STR_ENG_NUM);
    }

    /**
     * 判断字符串是不是全部是英文字母+数字+下划线
     *
     * @param str 字符串
     * @return boolean 返回字符串是不是全部是英文字母+数字+下划线
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isEnglishNumberLine(String str) {
        return regular(str, STR_ENG_NUM_DOWN_LINE);
    }

    /**
     * 过滤特殊字符串，返回过滤后的字符串
     *
     * @param str 字符串
     * @return 返回过滤后的字符串
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static String filterStr(String str) {
        Pattern p = Pattern.compile(STR_SPECIAL);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 校验机构代码格式
     *
     * @param str 字符串
     * @return 返回是否为校验机构代码格式
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isJigouCode(String str) {
        return regular(str, JIGOU_CODE);
    }

    /**
     * 判断字符串是不是数字组成 <br/>
     * <p>
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = false
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * StringUtils.isNumeric("-123") = false
     * StringUtils.isNumeric("+123") = false
     * </pre>
     *
     * @param str 字符串
     * @return boolean 返回字符串是不是数字组成
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isNumeric(String str) {
        return StringUtils.isNumeric(str);
    }

    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     *
     * @param str     匹配的字符串
     * @param pattern 匹配模式
     * @return boolean 是否符合正则表达式pattern 匹配返回true
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean regular(String str, String pattern) {
        if (null == str || str.trim().length() <= 0)
            return false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断字符串是不是Redis的Key规则，是大写英文字母 + 数字 + "_" + "-" + ":"
     *
     * @param key Redis的Key字符串
     * @return boolean 判断字符串是不是Redis的Key规则，是大写英文字母 + 数字 + "_" + "-" + ":"
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:01:37
     */
    public static boolean isRedisKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        return regular(key, REGEX_REDIS_KEY);
    }

    /**
     * 密码必须是字母或特殊符号和数字结合
     *
     * @param password 非加密的密码（明文）
     * @return 是否符合规则
     */
    public static boolean isPasswordLevel(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        return regular(password, REGEX_PASSWORD_LEVEL);
    }


    /**
     * 密码必须是字母或特殊符号和数字结合(仿开源中国)
     *
     * @param password 非加密的密码（明文）
     * @return 是否符合规则
     */
    public static boolean isPasswordCompose(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        if (regular(password, ".*[0-9].*") && (regular(password, ".*[a-zA-Z].*") || regular(password, ".*\\W.*"))){
            return true;
        }
        return false;
    }

}
