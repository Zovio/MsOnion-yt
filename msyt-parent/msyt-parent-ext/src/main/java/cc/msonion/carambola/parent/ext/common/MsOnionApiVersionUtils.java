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

package cc.msonion.carambola.parent.ext.common;

import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;

/**
 * @Title: MsOnionApiVersionUtils.java
 * @Package: cc.msonion.carambola.parent.ext.common
 * @Description: API版本工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月22日 下午4:02:11
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月22日 下午4:02:11
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建获取Api版本实例方法
 */

/**
 * @ClassName: MsOnionApiVersionUtils
 * @Description: API版本工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月22日 下午4:02:11
 *
 */
public final class MsOnionApiVersionUtils {

    /**
     * Api版本
     */
    private static final MsOnionApiVersion INSTANCE = new MsOnionApiVersion();

    /**
     * 本地线程的Api版本变量
     */
    private static final ThreadLocal<MsOnionApiVersion> LOCAL_API_VERSION = new ThreadLocal<MsOnionApiVersion>();

    private MsOnionApiVersionUtils() { }

    /**
     * 获取Api版本（不是单例的，每一次都创建一个实例）
     * @return 返回Api版本实例对象
     */
    public static MsOnionApiVersion getApiVersion() {
        return new MsOnionApiVersion();
    }

    /**
     * 获取Api版本（线程安全的）
     * @return 返回本地线程Api版本
     */
    public static MsOnionApiVersion getLocalApiVersion() {
        return LOCAL_API_VERSION.get();
    }

    /**
     * 设置Api版本实例对象（线程安全的）
     * @param apiVersion api版本
     */
    public static void setLocalApiVersion(MsOnionApiVersion apiVersion) {
        LOCAL_API_VERSION.set(apiVersion);
    }

    /**
     * 移除本地变量
     */
    public static void clearLocalApiVersion() {
        LOCAL_API_VERSION.remove();
    }
}
