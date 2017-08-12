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

/**
 * @Title: MsOnionSqlValidateUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: SQL验证工具类，包括处理SQL注入
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午2:01:25
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月26日 下午2:01:25
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建，实现相关方法
 */

/**
 * @ClassName: MsOnionSqlValidateUtils
 * @Description: SQL验证工具类，包括处理SQL注入
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月26日 下午2:01:25
 */
public final class MsOnionSqlValidateUtils {

    /**
     * SQL注入关键词
     */
    private static final String[] KEYWORDS;

    /**
     * 正则表达式
     */
    private static final String REGEX;

    static {
        StringBuilder sb = new StringBuilder();
        // 过滤掉的SQL关键词，可以手动添加 ，不可包括 * ，因为正则表达式报错,
        // /|#|;  菜单url需要使用/ 描述可能使用到# ;

        // 原来的
        String str1 = "'|%|--|and|or|not|use|insert|delete|update|select|count|group|union|net user|table|database|sitename|char";
        String str2 = "|create|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|source|sql|where|from|idx|name|password|username";  // |*";  包括 * 正则表达式报错
        String str3 = "|like|//||group_concat|column_name|information_schema.columns|table_schema|order|by|chr|mid|master|column|useradd|passwd";

//        String str1 = "'|%|--";
//        String str2 = "|create";  // |*";  包括 * 正则表达式报错
//        String str3 = "|like";

        sb.append(str1);
        sb.append(str2);
        sb.append(str3);

        REGEX = sb.toString().trim().toLowerCase();

		/*
        WORDS='|%|--|and|or|not|use|insert|delete|update|select|count|group|union|net user|table|database|sitename|char|create|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|source|sql|where|from|idx|name|password|username|like|//|/|#|;|group_concat|column_name|information_schema.columns|table_schema|order|by|chr|mid|master|column|useradd|passwd
		 */
//		MSLogger.info(MsOnionSqlValidateUtils.class, "WORDS=" + REGEX);

        KEYWORDS = REGEX.split("\\|");
    }

    /**
     * 包括SQL注入关键词被替换成空字符串
     */
    private static final String WORD = "";

    private MsOnionSqlValidateUtils() {
    }

    /**
     * SQL校验，是否包含SQL注入关键词
     *
     * @param str 字符串
     * @return 返回true：包括SQL注入关键词，false：正常的，不包括SQL注入关键词
     * @Title: sqlValidate
     * @Description: SQL校验，是否包含SQL注入关键词
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午2:16:52
     */
    public static boolean sqlValidate(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        str = str.toLowerCase(); // 统一转为小写

        String[] strs = str.split(" ");

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < KEYWORDS.length; j++) {
                if (KEYWORDS[j].toLowerCase().equals(strs[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param str 需要过滤的字符串
     * @return 返回过滤SQL注入关键词之后的字符串
     * @Title: sqlFilter
     * @Description: 过滤SQL注入关键词为空字符串
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午2:22:24
     */
    public static String sqlFilter2(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        // 不可以转换为小写，因为会把原先的数据都转换了
        //		str = str.toLowerCase(); // 统一转为小写

        str = sqlFilter(str, " ");

        str = sqlFilter(str, ";");
        str = sqlFilter(str, "=");

        return str;
    }

    /**
     * SQL过滤器
     *
     * @param str        字符串
     * @param splitRegex 正则表达式
     * @return 返回过滤掉SQL关键词的字符串
     */
    private static String sqlFilter(String str, String splitRegex) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        // 不可以转换为小写，因为会把原先的数据都转换了
        //		str = str.toLowerCase(); // 统一转为小写

        String[] strs = str.split(splitRegex);

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < KEYWORDS.length; j++) {
                if (KEYWORDS[j].toLowerCase().equals(strs[i])) {

                    // 替换SQL注入关键词为空字符串
                    str = str.replace(KEYWORDS[j].toLowerCase(), WORD);
                }

            }
        }

        return str;
    }

    /**
     * @param str 需要过滤的字符串
     * @return 返回过滤SQL注入关键词之后的字符串
     * @Title: sqlFilter
     * @Description: 过滤SQL注入关键词为空字符串
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月26日 下午2:22:24
     */
    public static String sqlFilter(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return str.trim();

        // 暂时不进行处理，由于MyBatis的逆向工程中，已经使用 ${} （已经解决SQL注入），
        // 只有 发布商品、资源下载的搜索，是使用 #{ } (存在SQL注入），但是，不影响
//        return str.replaceAll("(?i)" + REGEX, "");
    }
}
