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

import java.io.Serializable;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * @Title: MsOnionRedisUtils.java
 * @Package: cc.msonion.carambola.parent.common.utils
 * @Description: Redis工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:58:36
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:58:36
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * @ClassName: MsOnionRedisUtils
 * @Description: Redis工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:58:36
 */
public final class MsOnionRedisUtils {

    private MsOnionRedisUtils() {
    }

    /**
     * 后缀
     */
    private static final String PREFIX = "_INFO";

    /**
     * @Fields USER_NONE_IDENTIFICATION : 用户未登陆标识，购物车，用户信息的Key
     */
    public static final String USER_NONE_IDENTIFICATION = ":NONE:";

    /**
     * @param plainJson 普通Json（明文Json）
     * @return
     * @throws MsOnionException
     * @Title: getEncodeUserSession
     * @Description: 获取加密用户Session
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月21日 下午1:59:51
     */
    public static String getEncodeUserSession(String plainJson) throws MsOnionException {
        // 加强复杂加密
        return MsOnionSecurityUtils.encodeUserSession(plainJson);
        // 直接明文
    }

    /**
     * @param encryptJson 加密Json字符串
     * @return
     * @throws MsOnionException
     * @Title: getDecodeUserSession
     * @Description: 获取解密用户Session
     * @Author: HeroCao hero-cao@msyc.cc
     * @date 2016年8月21日 下午2:04:44
     */
    public static String getDecodeUserSession(String encryptJson) throws MsOnionException {
        // 加强复杂解密
        return MsOnionSecurityUtils.decodeUserSession(encryptJson);
        // 直接明文
    }

//	/** 
//	* @Title: getNoneLoginCartTokenKey 
//	* @Description: 获取没有登录时候，购物车中Token的Key
//	* @Author: HeroCao hero-cao@msyc.cc
//	* @date 2016年8月24日 下午7:52:40
//	*
//	* @param token
//	* @return     
//	*/
//	public static String getNoneLoginCartTokenKey(String token) {
//		return String.format("%sCart:Token:%s", DEFAULT_CART_PREFIX,token);
//	}

    /**
     * @param clazz
     * @return
     * @throws MsOnionIllegalArgumentException 参数非法
     * @Title: getRedisKeyStyle
     * @Description: 获取Redis中Key的样式
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午2:15:51
     */
    public static String getRedisKeyStyle(Class<Serializable> clazz) throws MsOnionIllegalArgumentException {
        if (null == clazz) {
            throw new MsOnionIllegalArgumentException("非法参数，clazz不能为空，clazz=" + clazz);
        }

        return getRedisKeyStyle(clazz.getSimpleName());
    }

    /**
     * @param name 名称
     * @return
     * @throws MsOnionIllegalArgumentException 非法参数
     * @Title: getRedisKeyStyle
     * @Description: 获取Redis中Key的样式
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午2:15:32
     */
    public static String getRedisKeyStyle(String name) throws MsOnionIllegalArgumentException {
        if (StringUtils.isBlank(name)) {
            throw new MsOnionIllegalArgumentException("非法参数，name不能为空，name=" + name);
        }

        return name.toUpperCase();
    }

    /**
     * 获取Redis的Key标识，例如： :PAGE:
     * @param name 名称，例如：PAGE
     * @return 返回Redis的Key标识，例如： :PAGE:
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Title: getRedisKeyIdentificationStyle
     * @Description: 获取Redis中Key的标识样式，例如：:ITEM:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:26:10
     */
    public static String getRedisKeyIdentificationStyle(String name) throws MsOnionIllegalArgumentException {
        return String.format(":%s:", getRedisKeyStyle(name));
    }

    /**
     * @param clazz
     * @return
     * @throws MsOnionIllegalArgumentException
     * @Title: getRedisKeyIdentificationStyle
     * @Description: 获取Redis中Key的标识样式，例如：:ITEM:
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午9:26:32
     */
    public static String getRedisKeyIdentificationStyle(Class<Serializable> clazz) throws MsOnionIllegalArgumentException {
        return String.format(":%s:", getRedisKeyStyle(clazz));
    }

    /**
     * @return
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @Title: getRedisKeyPrefix
     * @Description: 获取Redis中Key的前缀，例如： _INFO
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午2:38:06
     */
    public static String getRedisKeyPrefix() throws MsOnionIllegalArgumentException {
        return getRedisKeyStyle(PREFIX);
    }

    /**
     * @param clazz
     * @return
     * @throws MsOnionIllegalArgumentException
     * @Title: getRedisKeyPrefix
     * @Description: 获取Redis中Key的前缀，例如： USER_INFO
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午2:44:01
     */
    public static String getRedisKeyPrefix(Class<Serializable> clazz) throws MsOnionIllegalArgumentException {
        return String.format("%s%s", getRedisKeyStyle(clazz), getRedisKeyPrefix());
    }

    /**
     * @param name
     * @return
     * @throws MsOnionIllegalArgumentException
     * @Title: getRedisKeyPrefix
     * @Description: 获取Redis中Key的前缀，例如： USER_INFO
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午2:44:23
     */
    public static String getRedisKeyPrefix(String name) throws MsOnionIllegalArgumentException {
        return String.format("%s%s", getRedisKeyStyle(name), getRedisKeyPrefix());
    }

    /**
     * @param userTokenOrKey
     * @return
     * @Title: isLoginUserInfo
     * @Description: 是否登录用户信息
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月16日 下午2:56:48
     */
    public static boolean isLoginUserInfo(String userTokenOrKey) {
        if (StringUtils.isBlank(userTokenOrKey)) {
            return false;
        }
        return !userTokenOrKey.contains(USER_NONE_IDENTIFICATION);
    }

}
