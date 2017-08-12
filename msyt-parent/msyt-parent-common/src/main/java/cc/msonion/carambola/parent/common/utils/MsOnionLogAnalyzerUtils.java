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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.SortingParams;

/**
 * @Title: MsOnionLogAnalyzerUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: 日志分析工具
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午6:59:53
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午6:59:53
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * @ClassName: MsOnionLogAnalyzerUtils
 * @Description: 日志分析工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午6:59:53
 */
public final class MsOnionLogAnalyzerUtils {

    /**
     * @Fields PARAMETER_SEPARATOR : 参数分隔符
     */
    public static final String PARAMETER_SEPARATOR = "|";

    /**
     * @Fields MAP_PARAMETER_SEPARATOR : Map参数之间分隔符
     */
    public static final String MAP_PARAMETER_SEPARATOR = "||";

    /**
     * @Fields MAP_KEY_VALUE_PARAMETER_SEPARATOR : Map元素中Key和Value的分隔符号
     */
    public static final String MAP_KEY_VALUE_PARAMETER_SEPARATOR = "@";


    private MsOnionLogAnalyzerUtils() {
    }

    /**
     * 获取参数
     * @param params 参数
     * @return
     * @Title: getParameter
     * @Description: 获取参数
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 上午11:26:54
     */
    public static String getParameter(String... params) {
        try {
            if (null != params && params.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (String param : params) {
                    sb.append(param);
                    sb.append(MsOnionLogAnalyzerUtils.PARAMETER_SEPARATOR);
                }
                String str = sb.toString();
                return str = str.substring(0, str.length() - 2);
            }
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
        }
        return null;
    }

    // 应该转换成 Json

    /**
     * @return
     * @Title: getHashMap
     * @Description: 获取HashMap实例对象
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:34:26
     */
    public static HashMap<String, String> getHashMap() {
        return new HashMap<String, String>();
    }

    //	/**
    //	* @Title: getMap
    //	* @Description: 得到日志分析器Map
    //	* @Author: HeroCao hero-cao@msyc.cc
    //	* @Date: 2017年2月16日 下午3:09:38
    //	*
    //	* @param key
    //	* @param value
    //	* @return
    //	*/
    //	public static Map<String, String> getLogAnalyzerMap(String key, String value) {
    //		try {
    //			Map<String, String> map = new HashMap<String, String>();
    //			map.put("key", key);
    //			map.put("value", value);
    //			return map;
    //		} catch (Exception e) {
    //			MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
    //			return null;
    //		}
    //	}

    //	/**
    //	* @Title: getLogAnalyzerJson
    //	* @Description: 得到日志分析器Json
    //	* @Author: HeroCao hero-cao@msyc.cc
    //	* @Date: 2017年2月16日 下午3:18:01
    //	*
    //	* @param key
    //	* @param value
    //	* @return
    //	*/
    //	public static String getLogAnalyzerJson(String key, String value) {
    //		try {
    //			Map<String, String> map = getLogAnalyzerMap(key, value);
    //			return MsOnionJsonUtils.objectToJson(map);
    //		} catch (Exception e) {
    //			MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
    //			return null;
    //		}
    //	}

    /**
     * @param key
     * @param value
     * @param nxxx
     * @param expx
     * @param time
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:30:02
     */
    public static Map<String, String> getLogAnalyzerMap(String key, String value, String nxxx, String expx, long time) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", key);
            map.put("value", value);
            map.put("nxxx", nxxx);
            map.put("expx", expx);
            map.put("time", time + "");
            return map;
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param value
     * @param nxxx
     * @param expx
     * @param time
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:30:21
     */
    public static String getLogAnalyzerJson(String key, String value, String nxxx, String expx, long time) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key, value, nxxx, expx, time));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:38:24
     */
    public static Map<String, String> getLogAnalyzerMap(String key) {
        try {
            HashMap<String, String> map = getHashMap();
            map.put("key", key);
            return map;
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午3:38:21
     */
    public static String getLogAnalyzerJson(String key) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param seconds
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:36
     */
    public static Map<String, String> getLogAnalyzerMap(String key, int seconds) {
        HashMap<String, String> map = getHashMap();
        map.put("key", key);
        map.put("seconds", seconds + "");
        return map;
    }

    /**
     * @param key
     * @param seconds
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:40
     */
    public static String getLogAnalyzerJson(String key, int seconds) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key, seconds));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param milliseconds
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:36
     */
    public static Map<String, String> getLogAnalyzerMap(String key, long milliseconds) {
        HashMap<String, String> map = getHashMap();
        map.put("key", key);
        map.put("milliseconds", milliseconds + "");
        return map;
    }

    /**
     * @param key
     * @param seconds
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:40
     */
    public static String getLogAnalyzerJson(String key, long milliseconds) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key, milliseconds));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }


    /**
     * @param key
     * @param unixTime
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:36
     */
    public static Map<String, String> getLogAnalyzerMapByUnixTime(String key, long unixTime) {
        HashMap<String, String> map = getHashMap();
        map.put("key", key);
        map.put("unixTime", unixTime + "");
        return map;
    }

    /**
     * @param key
     * @param seconds
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:40
     */
    public static String getLogAnalyzerJsonByUnixTime(String key, long unixTime) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMapByUnixTime(key, unixTime));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param value
     * @param key2
     * @param value2
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午5:42:12
     */
    public static Map<String, String> getLogAnalyzerMapByArray(String value, String key2, String... value2) {
        try {
            HashMap<String, String> map = getHashMap();
            if (StringUtils.isNotEmpty(value)) {
                map.put("key", value);
            }
            if (StringUtils.isNotEmpty(key2) && null != value2 && value2.length > 0) {
                String json = MsOnionJsonUtils.objectToJson(value2);
                map.put(key2, json);
            }
            return map;
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param value
     * @param key2
     * @param value2
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午5:42:32
     */
    public static String getLogAnalyzerJsonByArray(String value, String key2, String... value2) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMapByArray(value, key2, value2));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param value
     * @param key2
     * @param scoreMembers
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:42:54
     */
    public static Map<String, String> getLogAnalyzerMap(String key, String value, String key2, Map<String, Double> scoreMembers) {
        try {
            HashMap<String, String> map = getHashMap();
            if (StringUtils.isNotEmpty(value)) {
                map.put(key, value);
            }
            if (StringUtils.isNotEmpty(key2) && null != scoreMembers && !scoreMembers.isEmpty()) {
                String json = MsOnionJsonUtils.objectToJson(scoreMembers);
                map.put(key2, json);
            }
            return map;
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param value
     * @param key2
     * @param scoreMembers
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午7:44:48
     */
    public static String getLogAnalyzerJson(String key, String value, String key2, Map<String, Double> scoreMembers) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key, value, key2, scoreMembers));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param value
     * @param key2
     * @param hash
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午5:34:14
     */
    public static Map<String, String> getLogAnalyzerMap(String value, String key2, Map<String, String> hash) {
        try {
            HashMap<String, String> map = getHashMap();
            if (StringUtils.isNotEmpty(value)) {
                map.put("key", value);
            }
            if (StringUtils.isNotEmpty(key2) && null != hash && !hash.isEmpty()) {
                String json = MsOnionJsonUtils.objectToJson(hash);
                map.put(key2, json);
            }
            return map;
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param value
     * @param key2
     * @param hash
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午5:38:57
     */
    public static String getLogAnalyzerJson(String value, String key2, Map<String, String> hash) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(value, key2, hash));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param value
     * @param key2
     * @param value2
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月9日 上午7:43:01
     */
    public static String getLogAnalyzerJson(String value, String key2, String value2) {
        try {
            return getLogAnalyzerJson("key", value, key2, value2);
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param otherKey1
     * @param otherValue1
     * @return
     * @Title: getLogAnalyzerJsonByOthers
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月9日 上午7:39:11
     */
    public static String getLogAnalyzerJsonByOthers(String otherKey1, String otherValue1) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(otherKey1, otherValue1, null, null, null, null, null, null, null, null));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param value
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:40
     */
    public static String getLogAnalyzerJson(String key, String value) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap("key", key, "value", value, null, null, null, null, null, null));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param seconds
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午4:39:40
     */
    public static String getLogAnalyzerJson(String key1, String value1, String key2, String value2) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key1, value1, key2, value2, null, null, null, null, null, null));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     * @param key3
     * @param value3
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午8:31:08
     */
    public static String getLogAnalyzerJson(String key1, String value1, String key2, String value2, String key3, String value3) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key1, value1, key2, value2, key3, value3, null, null, null, null));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     * @param key3
     * @param value3
     * @param key4
     * @param value4
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午8:31:26
     */
    public static String getLogAnalyzerJson(String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key1, value1, key2, value2, key3, value3, key4, value4, null, null));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     * @param key3
     * @param value3
     * @param key4
     * @param value4
     * @param key5
     * @param value5
     * @return
     * @Title: getLogAnalyzerMap
     * @Description: 得到日志分析器Map
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午8:31:45
     */
    public static Map<String, String> getLogAnalyzerMap(String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4, String key5, String value5) {
        HashMap<String, String> map = getHashMap();
        if (StringUtils.isNotEmpty(key1) && StringUtils.isNotEmpty(value1)) {
            map.put(key1, value1);
        }
        if (StringUtils.isNotEmpty(key2) && StringUtils.isNotEmpty(value2)) {
            map.put(key2, value2);
        }
        if (StringUtils.isNotEmpty(key3) && StringUtils.isNotEmpty(value3)) {
            map.put(key3, value3);
        }
        if (StringUtils.isNotEmpty(key4) && StringUtils.isNotEmpty(value4)) {
            map.put(key4, value4);
        }
        if (StringUtils.isNotEmpty(key5) && StringUtils.isNotEmpty(value5)) {
            map.put(key5, value5);
        }
        return map;
    }

    /**
     * @param key1
     * @param value1
     * @param key2
     * @param value2
     * @param key3
     * @param value3
     * @param key4
     * @param value4
     * @param key5
     * @param value5
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午8:32:13
     */
    public static String getLogAnalyzerJson(String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4, String key5, String value5) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerMap(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param sortingParameters
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午8:19:35
     */
    public static String getLogAnalyzerJson(String key, SortingParams sortingParameters) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerJson("key", key, "sortingParameters", MsOnionJsonUtils.objectToJson(sortingParameters)));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }

    /**
     * @param key
     * @param where
     * @param pivot
     * @param value
     * @return
     * @Title: getLogAnalyzerJson
     * @Description: 得到日志分析器Json
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:08:19
     */
    public static String getLogAnalyzerJson(String key, LIST_POSITION where, String pivot, String value) {
        try {
            return MsOnionJsonUtils.objectToJson(getLogAnalyzerJson("key", key, "where", MsOnionJsonUtils.objectToJson(where), "pivot", pivot, "value", value));
        } catch (Exception e) {
            MsOnionLogger.error(MsOnionLogAnalyzerUtils.class, e);
            return null;
        }
    }
}
