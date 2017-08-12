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

package cc.msonion.carambola.parent.common.utils.math;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @Title: MsOnionNumberUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils.math
 * @Description: 数字工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月28日 下午8:33:19
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月28日 下午8:33:19
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * @ClassName: MsOnionNumberUtils
 * @Description: 数字工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月28日 下午8:33:19
 */
public final class MsOnionNumberUtils extends NumberUtils {

    /**
     * 价格：元转换成分，100
     */
    public static final int PRICE_UNIT_STEP_VALUE = 100;

    /**
     * 货币单位Step：10
     */
    public static final int CURRENCY_UNIT_STEP_10 = 10;

    /**
     * 货币单位Step：100
     */
    public static final int CURRENCY_UNIT_STEP_100 = 100;

    /**
     * 货币单位Step：1000
     */
    public static final int CURRENCY_UNIT_STEP_1000 = 1000;

    /**
     * 货币单位Step：10000
     */
    public static final int CURRENCY_UNIT_STEP_10000 = 10000;

    /**
     * 货币单位Step：100000
     */
    public static final int CURRENCY_UNIT_STEP_100000 = 100000;

    private MsOnionNumberUtils() {
    }

    /**
     * 转换成价格，单位为：分，转换失败返回0
     *
     * @param str 单位为元的价格字符串，例如：100.00、99、188.85
     * @return 返回单位为：分，价格，转换失败返回0
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static int toIntForPrice(String str) {
        return toIntForPrice(str, INTEGER_ZERO);
    }

    /**
     * 转换成价格，单位为：分
     *
     * @param str          单位为元的价格字符串，例如：100.00、99、188.85
     * @param defaultValue 默认值，如果转换失败就返回默认值
     * @return 返回单位为：分，价格
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static int toIntForPrice(String str, final int defaultValue) {
        if (MsOnionStringUtils.isBlank(str)) {
            return defaultValue;
        }
        str = str.trim();
//        float value = toFloat(str, defaultValue);
//        return (int) (value * PRICE_UNIT_STEP_VALUE);
        return toIntForCurrency(str, CURRENCY_UNIT_STEP_100);
    }

    /**
     * 转换成价格，单位为：分，转换失败返回0
     *
     * @param str 单位为元的价格字符串，例如：100.00、99、188.85
     * @return 返回单位为：分，价格，转换失败返回0
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static long toLongForPrice(String str) {
        return toLongForPrice(str, LONG_ZERO);
    }

    /**
     * 转换成价格，单位为：分
     *
     * @param str          单位为元的价格字符串，例如：100.00、99、188.85
     * @param defaultValue 默认值，如果转换失败就返回默认值
     * @return 返回单位为：分，价格
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static long toLongForPrice(String str, final long defaultValue) {
        if (MsOnionStringUtils.isBlank(str)) {
            return defaultValue;
        }
        str = str.trim();
        float value = toFloat(str, defaultValue);
        return (long) (value * PRICE_UNIT_STEP_VALUE);
//        return toLongForCurrency(str, U);
    }

    /**
     * 转换成货币格式
     *
     * @param str          字符串，例如：0.12345、0.00065、0.00008
     * @param unitStep 当前step，例如：10、100、1000、10000、100000
     * @return 返回货币格式值
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static int toIntForCurrency(String str, final int unitStep) {
        return (int) toLongForCurrency(str, INTEGER_ZERO, unitStep);
    }

    /**
     * 转换成货币格式
     *
     * @param str          字符串，例如：0.12345、0.00065、0.00008
     * @param defaultValue 默认值，如果转换失败就返回默认值
     * @param unitStep 当前step，例如：10、100、1000、10000、100000 ## CURRENCY_UNIT_STEP_100
     * @return 返回货币格式值
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static int toIntForCurrency(String str, final int defaultValue, final int unitStep) {
        return (int) toLongForCurrency(str, defaultValue, unitStep);
    }

    /**
     * 转换成货币格式
     *
     * @param str          字符串，例如：0.12345、0.00065、0.00008
     * @param unitStep 当前step，例如：10、100、1000、10000、100000
     * @return 返回货币格式值
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static long toLongForCurrency(String str, final int unitStep) {
        return toLongForCurrency(str, LONG_ZERO, unitStep);
    }

    /**
     * 转换成货币格式
     *
     * @param str          字符串，例如：0.12345、0.00065、0.00008
     * @param defaultValue 默认值，如果转换失败就返回默认值
     * @param unitStep 当前step，例如：10、100、1000、10000、100000 ## CURRENCY_UNIT_STEP_100
     * @return 返回货币格式值
     * @Modify-by: HeroCao hero-cao@msyc.cc
     * @Modify-date: 2017年5月28日 下午8:33:19
     */
    public static long toLongForCurrency(String str, final long defaultValue, final int unitStep) {
        if (MsOnionStringUtils.isBlank(str)) {
            return defaultValue;
        }
        str = str.trim();
        float value = toFloat(str, defaultValue);
        return (long) (value * unitStep);
    }

    /**
     * 格式化货币，例如：188.123456、0.00008、188.12345
     * @Title: formatCurrency
     * @Description: 格式化货币，例如：188.123456、0.00008、188.12345
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月2日 下午9:58:37
     *
     * @param currency 货币，例如：1、9、188、19800
     * @param length 长度，长度多少就是有多少位小数点，例如：1、2、3、5
     * @return 返回格式化之后的货币格式
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException 异常
     */
    public static String formatCurrency(Long currency, int length) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == currency) {
            return null;
        }
    if (currency <= 0 || length <= 0) {
            return String.valueOf(currency);
        }
        String value = null;
        value = String.valueOf(currency);
        if (length >= value.length()) {
            value = MsOnionFixedLengthUtils.fixLength(currency, length + 1);
        }
        // 截取小数点的值
        String dot = value.substring(value.length() - length, value.length());
        // 整数位
        value = value.substring(0, value.length() - length);
        return String.format("%s.%s", value, dot);
    }

    /**
     * 格式化货币，例如：188.123456、0.00008、188.12345
     * @Title: formatCurrency
     * @Description: 格式化货币，例如：188.123456、0.00008、188.12345
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年6月2日 下午9:58:37
     *
     * @param currency 货币，例如：1、9、188、19800
     * @param length 长度，长度多少就是有多少位小数点，例如：1、2、3、5
     * @return 返回格式化之后的货币格式
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException 异常
     */
    public static String formatCurrency(Integer currency, int length) throws MsOnionIllegalArgumentException, MsOnionException {
        if (null == currency) {
            return null;
        }
        if (currency <= 0 || length <= 0) {
            return String.valueOf(currency);
        }
        String value = null;
        value = String.valueOf(currency);
        if (length >= value.length()) {
            value = MsOnionFixedLengthUtils.fixLength(currency, length + 1);
        }
        // 截取小数点的值
        String dot = value.substring(value.length() - length, value.length());
        // 整数位
        value = value.substring(0, value.length() - length);
        return String.format("%s.%s", value, dot);
    }

//	/**
//	 * 格式化货币，例如：188.123456、0.00008、188.12345
//	 * @Title: formatCurrency
//	 * @Description: 格式化货币，例如：188.123456、0.00008、188.12345
//	 * @Author: HeroCao hero-cao@msyc.cc
//	 * @Date: 2017年6月2日 下午9:58:37
//	 *
//	 * @param currency 货币，例如：1、9、188、19800
//	 * @param length 长度，例如：1、2、3、5
//	 * @return 返回格式化之后的货币格式
//	 * @throws MsOnionIllegalArgumentException 非法参数异常
//	 * @throws MsOnionException 异常
//	 */
//	public static String formatCurrency(int currency, int length) throws MsOnionIllegalArgumentException, MsOnionException {
//		return formatCurrency(currency, length);
//	}

}
