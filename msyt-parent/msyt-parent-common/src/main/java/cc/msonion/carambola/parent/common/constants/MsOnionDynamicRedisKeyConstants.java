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

package cc.msonion.carambola.parent.common.constants;


/**
 * 动态Redis的Key常量，只要动态的Redis Key才统一放到这里，不是动态的，统一使用 RedisKey生成器管理
 *
 * @Title: MsOnionDynamicRedisKeyConstants.java
 * @Package: cc.msonion.carambola.parent.common.constants
 * @Description: 动态Redis的Key常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年7月05日 下午7:53:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年7月05日 下午7:53:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */


/**
 * 动态Redis的Key常量，只要动态的Redis Key才统一放到这里，不是动态的，统一使用 RedisKey生成器管理
 *
 * @ClassName: MsOnionDynamicRedisKeyConstants
 * @Description: 动态Redis的Key常量
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年7月05日 下午7:53:58
 */
public final class MsOnionDynamicRedisKeyConstants {

    private MsOnionDynamicRedisKeyConstants() {

    }

    //////////////  ERP ##### Begin  /////////////////////////////

    /**
     * ERP库存同步时间的redis key
     */
    public static final String ERP_STOCK_SYNC_TIME_REDIS_KEY = "DYNAMICCACHE:ERP:STOCK:SYNCTIME";

    //////////////  ERP ##### End  /////////////////////////////


    //////////////  静态资源：CSS、JS、Image ##### Begin  /////////////////////////////

    /**
     * 管理后台系统 CSS版本号 存在redis缓存中key
     */
    public static final String REDIS_CSS_VERSION_KEY = "DYNAMICCACHE:STATIC:CSSVERSION";

    /**
     * 管理后台系统 JS版本号 存在redis缓存中key
     */
    public static final String REDIS_JS_VERSION_KEY = "DYNAMICCACHE:STATIC:JSVERSION";


    /**
     * 管理后台系统 IMG版本号 存在redis缓存中key
     */
    public static final String REDIS_IMG_VERSION_KEY = "DYNAMICCACHE:STATIC:IMGVERSION";


    /**
     * 管理后台系统 邮件找回密码 存在redis缓存中key
     */
    public static final String REDIS_EMAIL_FOUND_KEY = "DYNAMICCACHE:EMAIL:";

    /**
     * 管理后台系统 SESSION 存在redis缓存中key前缀
     */
    public static final String REDIS_SESSION_KEY_PREFIX = "DYNAMICCACHE:SESSION:";


    //////////////  静态资源：CSS、JS、Image ##### End  /////////////////////////////

    //////////////  验证码 ##### Begin  /////////////////////////////

    /**
     * VERIFYCODE 验证码
     */
    public static final String REDIS_VERIFYCODE = "DYNAMICCACHE:VERIFYCODE:";

    /**
     * 验证码不安性Super专用，防止后台由于验证码出现问题进不去，给Super预留“绿色通道”
     */
    public static final String REDIS_VERIFY_CODE_UNSECURITY_SUPER = "DYNAMICCACHE:VERIFYCODE:UNSECURITY:SUPER";

    /**
     * 验证码不安性所有专用，防止后台由于验证码出现问题进不去，给所有成员预留“绿色通道”
     */
    public static final String REDIS_VERIFY_CODE_UNSECURITY_ALL = "DYNAMICCACHE:VERIFYCODE:UNSECURITY:ALL";

    /**
     * 验证码不安性Super专用，防止后台由于验证码出现问题进不去，给Super预留“绿色通道”，Redis的周期：30分钟一次
     */
    public static final int REDIS_VERIFY_CODE_UNSECURITY_SUPER_EXPIRE = 180; // 方便测试先改成2秒钟，正式 ： 1800;

    /**
     * 验证码不安性Super专用
     */
    public static final String REDIS_VERIFY_CODE_SUPER = "1";

    /**
     * 验证码不安性，所有
     */
    public static final String REDIS_VERIFY_CODE_ALL = "2";

    //////////////  验证码 ##### End  /////////////////////////////
}
