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

import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * 字符串工具类
 *
 * @Title: MsOnionStringUtils.java
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
 * 字符串工具类
 *
 * @ClassName: MsOnionStringUtils
 * @Description: 字符串工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午7:01:37
 */
public final class MsOnionStringUtils extends StringUtils {

    /**
     * TimeEn
     */
    private static final String DEFAULT_DATE_FIELD_NAME_EN = "TimeEn";

    /**
     * TimeCn
     */
    private static final String DEFAULT_DATE_FIELD_NAME_CN = "TimeCn";

    /**
     * En
     */
    private static final String DEFAULT_DATE_EN = "En";

    /**
     * Cn
     */
    private static final String DEFAULT_DATE_CN = "Cn";

    /**
     * ASC
     */
    private static final String DEFAULT_EMPTY_ASC_UPPER = " ASC"; // 前面必须有空格

    /**
     * DESC
     */
    private static final String DEFAULT_EMPTY_DESC_UPPER = " DESC"; // 前面必须有空格

    /**
     * asc
     */
    private static final String DEFAULT_EMPTY_ASC_LOWER = " asc"; // 前面必须有空格

    /**
     * desc
     */
    private static final String DEFAULT_EMPTY_DESC_LOWER = " desc"; // 前面必须有空格

    /**
     * 逗号，（英文状态）
     */
    private static final String DEFAULT_COMMA = ",";

    /**
     * 敏感信息字段，日志中，过滤掉
     */
    private static final String[] SENSITIVE_INFO_FIELDS = {"phone", "password", "pwd", "email", "code", "tel", "name", "userName", "fullName", "nick", "nickName", "orderNo", "address"};

    /**
     * 敏感值，最小长度：3
     */
    private static final int SENSITIVE_VALUE_MIN_LENGTH = 3;

    /**
     * *
     */
    private static final String SENSITIVE_VALUE_STAR = "*";

    private MsOnionStringUtils() {

    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 返回首字母大写字符串
     */
    public static String captureName(String str) {
        if (isBlank(str)) {
            return null;
        }
        str = str.trim();
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 获取字符串中所有大写字母，例如：creaeByMebmerIdx ，返回：BMI
     *
     * @param str 字符串
     * @return 返回所有的大写字母
     */
    public static String getAllUpperCase(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        str = str.trim();
        StringBuffer buffer = new StringBuffer();
        // 转为char数组
        char[] ch = str.toCharArray();
        // 得到大写字母
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                buffer.append(ch[i]);
            }
        }
        // 倒序
//        buffer = buffer.reverse();
        return buffer.toString();
    }

    /**
     * 根据排序条件，得到数据库表的排序列名，例如: createByMemberIdx ，返回：create_by_member_idx，
     * <p>支持：createByMemberIdx asc,updateByByMemberIdx desc,lastLoginTime</p>
     * <p>转换成：create_by_member_idx asc,update_by_by_member_idx desc,last_login_time</p>
     * <p>注意：排序关键词 desc、asc不区分大小写，最终都统一转换成小写</p>
     * <p>同时支持：createTimeEnYyyyMMddHHmmssSSS 转换成：create_time</p>
     *
     * @param orderBy 排序条件，例如：createByMemberIdx DESC
     * @return 根据POJO的字段名，得到数据库表的列名
     */
    public static String getCorrectOrderByForDB(String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            return null;
        }
        orderBy = orderBy.trim();

        // 如果有包括 ASC、DESC（大写的），都替换成小写的 asc、desc
        if (orderBy.contains(DEFAULT_EMPTY_ASC_UPPER)) {
            orderBy = orderBy.replace(DEFAULT_EMPTY_ASC_UPPER, DEFAULT_EMPTY_ASC_LOWER);
        } else if (orderBy.contains(DEFAULT_EMPTY_DESC_UPPER)) {
            orderBy = orderBy.replace(DEFAULT_EMPTY_DESC_UPPER, DEFAULT_EMPTY_DESC_LOWER);
        }

        // 如果是日期格式
        // 简单实现，不支持多个条件
        String[] data = null;
        if (orderBy.contains(DEFAULT_DATE_FIELD_NAME_CN)) {
            orderBy = orderBy.split(DEFAULT_DATE_CN)[0];
        } else if (orderBy.contains(DEFAULT_DATE_FIELD_NAME_EN)) {
            orderBy = orderBy.split(DEFAULT_DATE_EN)[0];
        }

        // TODO: 需要完善，兼容多个条件的日期格式：
        // createTimeEnYyyyMMddHHmmssSSS asc,createTimeCnYyyyMMddHHmmssSSS desc,lastLoginTime
//        fieldName = getFileNames(fieldName);


        StringBuffer buffer = new StringBuffer();
        // 转为char数组
        char[] ch = orderBy.toCharArray();
        String separator = "-"; // 不可以是 .
        // 得到大写字母
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                buffer.append(ch[i]);
                if (i < ch.length - 2) {
                    buffer.append(separator);
                }
            }
        }
        // 如果是大写字母，就在前面加 "_" ， 例如: createByMemberIdx ，返回：create_by_member_idx
        // 同时都转换成小写，因为数据库中列名，都是小写，在 MySQL数据库军规中！！！

        if (buffer.length() <= 0) {
            return orderBy;
        }
        String[] strs = buffer.toString().split(separator);

        if (null == strs || strs.length <= 0) {
            return orderBy;
        }

        String line = "_";
        for (String str : strs) {
            orderBy = orderBy.replace(str, line + str.toLowerCase());
        }

        return orderBy;
    }

//    private static String getFileNames(String fieldName) {
//
//        // 是否有多个条件的
//        if (!fieldName.contains(DEFAULT_COMMA)) {
//            if (fieldName.contains(DEFAULT_DATE_FIELD_NAME_CN)) {
//                fieldName = fieldName.split(DEFAULT_DATE_CN)[0];
//            } else if (fieldName.contains(DEFAULT_DATE_FIELD_NAME_EN)) {
//                fieldName = fieldName.split(DEFAULT_DATE_EN)[0];
//            }
//            return fieldName;
//        }
//
//        // 包括多个条件，也就是有 ,
//        String[] data = null;
//        if (fieldName.contains(DEFAULT_DATE_FIELD_NAME_CN)) {
//            data = fieldName.split(DEFAULT_DATE_CN);
//
//        } else if (fieldName.contains(DEFAULT_DATE_FIELD_NAME_EN)) {
//            fieldName = fieldName.split(DEFAULT_DATE_EN)[0];
//        }
//        return fieldName;
//    }

//    private static String getFieldNamesEn(String fieldName, String[] data) {
//        if (data.length <= 1) {
//            return data[0];
//        }
//        // createTimeEnYyyyMMddHHmmssSSS asc,createTimeCnYyyyMMddHHmmssSSS desc,lastLoginTime
//        // YyyyMMddHHmmssSSS asc,createTimeCnYyyyMMddHHmmssSSS desc,lastLoginTime
//        // 以逗号分隔
//        String[] strs = data[1].split(DEFAULT_COMMA);
//
//        // 为 null 、不包括 asc、不包括 desc
//        if (null == strs || strs.length <= 0 || (!strs[0].contains(DEFAULT_EMPTY_ASC_LOWER) && !strs[0].contains(DEFAULT_EMPTY_DESC_LOWER))) {
//            return data[0];
//        }
//
//        String target = data[0];
//        if (strs[0].contains(DEFAULT_EMPTY_ASC_LOWER)) {
//            target = target + DEFAULT_EMPTY_ASC_LOWER;
//        } else if (strs[0].contains(DEFAULT_EMPTY_DESC_LOWER)) {
//            target = target + DEFAULT_EMPTY_DESC_LOWER;
//        }
//
//        // createTimeCnYyyyMMddHHmmssSSS desc
//        // 递归
//
//
//        return target;
//    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        return Optional.ofNullable(value).map(s -> s.length()).orElse(0);

        /* int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        *//* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 *//*
        for (int i = 0; i < value.length(); i++) {
            *//* 获取一个字符 *//*
            String temp = value.substring(i, i + 1);
            *//* 判断是否为中文字符 *//*
            if (temp.matches(chinese)) {
                *//* 中文字符长度为2 *//*
                valueLength += 2;
            } else {
                *//* 其他字符长度为1 *//*
                valueLength += 1;
            }
        }
        return valueLength;*/
    }


    /**
     * toString
     *
     * @param o            对象
     * @param defaultValue 默认值
     * @return 字符串
     */
    public static String toStringDefault(Object o, String defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            return String.valueOf(o);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    //////////////////// 解决 GET请求参数乱码问题 ### Begin ///////////////////////////

    /**
     * 解决 GET请求参数中文乱码问题
     *
     * @param parameter GET请求参数
     * @return 返回正常（非乱码）参数
     * @throws MsOnionException 异常
     */
    public static String getNormalGetRequestParameter(String parameter) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(parameter)) {
            return null;
        }
        parameter = parameter.trim();
        try {
            // 解决 GET请求参数，中文乱码问题
            return new String(parameter.getBytes(MsOnionConstants.CHARSET_ISO8859_1), MsOnionConstants.CHARSET_UTF_8);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    //////////////////// 解决 GET请求参数乱码问题 ### End ///////////////////////////


    //////////////////// 打印日志的时候，过滤掉敏感性 ### Begin ///////////////////////////

    /**
     * 过滤敏感词字段的值
     *
     * @param field 字段
     * @param value 字段的值
     * @return 返回过滤敏感词字段的值
     * @throws MsOnionException 异常
     */
    public static String filterSensitiveField(String field, String value) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(field) || MsOnionStringUtils.isBlank(value)) {
            return value;
        }

        // 去掉左右空格
        field = field.trim();
        value = value.trim();

        for (String name : SENSITIVE_INFO_FIELDS) {
            if (name.equalsIgnoreCase(field)) {
                return getFilterSensitiveValue(value);
            }
        }
        return value;
    }

    /**
     * 过滤敏感词值
     *
     * @param value 字符串
     * @return 返回过滤掉敏感信息
     * @throws MsOnionException 异常
     */
    public static String getFilterSensitiveValue(String value) throws MsOnionException {
        if (MsOnionStringUtils.isBlank(value)) {
            return "";
        }

        value = value.trim();

        if (value.length() > SENSITIVE_VALUE_MIN_LENGTH) {
            // 前面3位
            String str = value.substring(0, SENSITIVE_VALUE_MIN_LENGTH);
            // 后面都使用*替换了
            String stars = fixedLengthValue(value.length() - SENSITIVE_VALUE_MIN_LENGTH, SENSITIVE_VALUE_STAR);
            return String.format("%s%s", str, stars);
        } else {
            return fixedLengthValue(SENSITIVE_VALUE_MIN_LENGTH, SENSITIVE_VALUE_STAR);
        }
    }

    /**
     * 固定长度的值
     *
     * @param length 长度
     * @param value  字符串，没有去掉左右两边的空格
     * @return 返回固定长度的值
     * @throws MsOnionException 异常
     */
    public static String fixedLengthValue(int length, String value) throws MsOnionException {
        if (null == value || length <= 0) {
            return value;
        }
//        value = value.trim();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(value);
        }
        return sb.toString();
    }

    //////////////////// 打印日志的时候，过滤掉敏感性 ### End ///////////////////////////

}
