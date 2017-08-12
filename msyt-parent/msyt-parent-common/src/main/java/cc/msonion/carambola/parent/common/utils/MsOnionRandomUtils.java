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

/**
 * 随机工具类
 *
 * @Title: MsOnionRandomUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 随机工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月09日 下午8:58:36
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:58:36
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * @ClassName: MsOnionRandomUtils
 * @Description: 随机工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月09日 下午8:58:36
 */
public final class MsOnionRandomUtils extends RandomUtils {

    /**
     * 数字和字母（包括大小写字母）
     */
    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 数字
     */
    public static final String NUMBERS = "0123456789";

    /**
     * 字母（包括大小写字母）
     */
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 大写字母
     */
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 小写字母
     */
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private MsOnionRandomUtils() {
        throw new AssertionError();
    }

    /**
     * 得到一个固定长度的随机字符串，它是大写字母、小写字母和数字的混合
     *
     * @param length 长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，它是大写字母、小写字母和数字的混合
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是一个混合的数字
     *
     * @param length 长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，它是一个混合的数字
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是大写字母和小写字母的混合
     *
     * @param length 长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，它是大写字母和小写字母的混合
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是大写字母的混合
     *
     * @param length 长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，它是大写字母的混合
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是小写字母的混合
     *
     * @param length 长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，它是小写字母的混合
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是源中的字符混合
     *
     * @param source 字符串源（字符串）
     * @param length 长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，它是源中的字符混合
     */
    public static String getRandom(String source, int length) {
        return StringUtils.isBlank(source) ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * 得到一个固定长度的随机字符串，其混合焦在sourcechar
     *
     * @param sourceChar char数组
     * @param length     长度，例如：5,8,10 ...
     * @return 返回一个固定长度的随机字符串，其混合焦在sourcechar
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (null == sourceChar || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * 获取随机整数在0和max之间，不包括最大值，包括最小值
     *
     * @param max 最大值，if max <= 0, return 0
     * @return 返回随机整数在0和max之间
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * 获取最小值和最大值之间的随机int，不包括最大值，包括最小值
     *
     * @param min 最小值，if min > max, return 0
     * @param max 最大值，if min == max, return min
     * @return 返回最小值和最大值之间的随机int
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * 洗牌算法，随机置换指定的数组使用的默认源的随机性
     *
     * @param objArray Object数组
     * @return 返回洗牌算法，随机置换指定的数组使用的默认源的随机性
     */
    public static boolean shuffle(Object[] objArray) {
        if (null == objArray || objArray.length <= 0) {
            return false;
        }
        return shuffle(objArray, getRandom(objArray.length));
    }

    /**
     * 洗牌算法，随机置换指定的数组
     *
     * @param objArray     Object数组
     * @param shuffleCount 洗牌数量
     * @return 返回洗牌算法，随机置换指定的数组
     */
    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        if (objArray == null || objArray.length <= 0 || shuffleCount < 0) {
            return false;
        }

        int length = objArray.length;
        if (length < shuffleCount) {
            return false;
        }

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            Object temp = objArray[length - i];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;
        }
        return true;
    }

    /**
     * 洗牌算法，随机置换指定数组的使用随机的默认源
     *
     * @param intArray Int数组
     * @return 返回洗牌算法，随机置换指定数组的使用随机的默认源
     */
    public static int[] shuffle(int[] intArray) {
        if (null == intArray || intArray.length <= 0) {
            return null;
        }

        return shuffle(intArray, getRandom(intArray.length));
    }

    /**
     * 洗牌算法，随机置换指定的数组
     *
     * @param intArray     Int数组
     * @param shuffleCount 洗牌数量
     * @return 返回洗牌算法，随机置换指定的数组
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {

        if (null == intArray || intArray.length <= 0 || shuffleCount < 0) {
            return null;
        }

        int length = intArray.length;
        if (length < shuffleCount) {
            return null;
        }

        int[] out = new int[shuffleCount];
        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }

    /**
     * 随机一个范围的long值
     *
     * @param begin 开始
     * @param end   结束
     * @return 随机一个范围的long值
     */
    public static long random(long begin, long end) {
        long result = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始和结束，则递归调用本函数查找随机值
        if (result == begin || result == end) {
            return random(begin, end);
        }
        return result;
    }



    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     * @return 证书数组
     */
    public static int[] randomNoRepeat(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

}
