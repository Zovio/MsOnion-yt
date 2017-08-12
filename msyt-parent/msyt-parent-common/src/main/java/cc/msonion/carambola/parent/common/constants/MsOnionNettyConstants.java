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
 * Netty常量类
 *
 * @Title: MsOnionNettyConstants.java
 * @Package: cc.msonion.carambola.parent.common.constants
 * @Description: Netty常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月23日 下午7:53:58
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月23日 下午7:53:58
 * @Modify-version: V2.0.0
 * @Modify-description: 新增
 */

/**
 * @ClassName: MsOnionNettyConstants
 * @Description: Netty常量类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月23日 下午7:53:58
 */
public final class MsOnionNettyConstants {

    private MsOnionNettyConstants() {
    }

    /*
    ## resource/properties/config-netty.properties
    ##
    ## enable : true , disable : false
netty.http.ssl.enable=false
## netty
netty.transport.protocol=http
## fres.yunyangtao.com
netty.http.host =localhost
## msyt-fileresource-api       # Tomcat # 8140
netty.http.port =8140

## form post
netty.transport.form.post=formpost
## form get
netty.transport.form.get=formget
## form post multipart
netty.transport.form.post.multipart=formpostmultipart

     */

    /**
     * Netty的Properties配置文件，是否开启SSL的Key：netty.http.ssl.enable
     */
    public static final String NETTY_PROPERTIES_KEY_HTTP_SSL_ENABLE = "netty.http.ssl.enable";

    /**
     * Netty的Properties配置文件，传输协议Key：netty.transport.protocol
     */
    public static final String NETTY_PROPERTIES_KEY_HTTP_TRANSPORT_PROTOCOL = "netty.transport.protocol";

    /**
     * Netty的Properties配置文件，主机Key：netty.http.host
     */
    public static final String NETTY_PROPERTIES_KEY_HTTP_HOST = "netty.http.host";

    /**
     * Netty的Properties配置文件，端口的Key：netty.http.port
     */
    public static final String NETTY_PROPERTIES_KEY_HTTP_PORT = "netty.http.port";

    /**
     * Netty的Properties配置文件，POST的Key：netty.transport.form.post
     */
    public static final String NETTY_PROPERTIES_KEY_TRANSPORT_FORM_POST = "netty.transport.form.post";

    /**
     * Netty的Properties配置文件，GET的Key：netty.transport.form.get
     */
    public static final String NETTY_PROPERTIES_KEY_TRANSPORT_FORM_GET = "netty.transport.form.get";

    /**
     * Netty的Properties配置文件，POST multipart的Key：netty.transport.form.post.multipart
     */
    public static final String NETTY_PROPERTIES_KEY_TRANSPORT_FORM_POST_MULTIPART = "netty.transport.form.post.multipart";

    ////////////////////////// Netty 集群模式 ## Begin /////////////////////////////

    /**
     * 参数messageId非法
     */
    public static final String NETTY_PARAMETER_MESSAGE_ID_ILLEGAL = "参数messageId非法";

    /**
     * Get remote address error, should be InetSocketAddress
     */
    public static final String NETTY_REMOTE_ADDRESS_ERROR = "Get remote address error, should be InetSocketAddress";

    /**
     * Channel非法
     */
    public static final String NETTY_CHANNEL_ILLEGAL = "Channel非法";

    /**
     * Netty的Properties配置文件，端口的Key：netty.server.port
     */
    public static final String NETTY_PROPERTIES_KEY_SERVER_PORT = "netty.server.port";

    /**
     * Netty的Properties配置文件，端口的Key：netty.server.host
     */
    public static final String NETTY_PROPERTIES_KEY_SERVER_HOST = "netty.server.host";

    ////////////////////////// Netty 集群模式 ## End /////////////////////////////
}
