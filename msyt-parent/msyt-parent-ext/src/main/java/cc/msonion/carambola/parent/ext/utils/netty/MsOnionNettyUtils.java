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

package cc.msonion.carambola.parent.ext.utils.netty;

/**
 * @Title: MsOnionNettyUtils.java
 * @Package: cc.msonion.carambola.parent.ext.utils.netty
 * @Description: Netty工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午5:32:48
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月1日 下午5:32:48
 * @Modify-version: V2.0.0
 * @Modify-description: 新增
 */

import cc.msonion.carambola.parent.common.constants.MsOnionNettyConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.*;
import cc.msonion.carambola.parent.ext.log.*;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyServerAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionDomain;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: MsOnionNettyUtils
 * @Description: Netty工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月1日 下午5:32:48
 */
public final class MsOnionNettyUtils {

    /**
     * 上传文件使用线程池：10
     */
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    /**
     * Properties name
     */
    private static final String PROPERTIES_NAME = "properties/config-netty.properties";

    /**
     * Properties
     */
    private static volatile Properties properties = null;

    /**
     * 是否开启Netty服务，不要忘记了：volatile
     */
    private static volatile boolean serverStarted = false;

    /**
     * Netty Server 端口
     */
    private static volatile int serverPort = 0;

    /**
     * Netty Server 主机
     */
    private static volatile String serverHost = null;

    /**
     * 上传文件，MessageId最小随机值
     */
    private static final int MESSAGE_ID_RANDOM_MIN_VALUE = 0;

    /**
     * 上传文件，MessageId最大随机值
     */
    private static final int MESSAGE_ID_RANDOM_MAX_VALUE = 99;

    /**
     * MessageID随机位数：2
     */
    private static final int MESSAGE_ID_RANDOM_FIX_LENGTH = 2;

    private MsOnionNettyUtils() {
    }

    /**
     * 加载 Properties
     *
     * @param msOnionLogger    日志
     * @param msOnionDomain 域名相关封装
     * @return 返回 Properties
     */
    private static Properties loadProperties(MsOnionLogger msOnionLogger, MsOnionDomain msOnionDomain) {
        if (null == properties) {
            try {
                properties = MsOnionPropertiesUtils.loadProperties(msOnionDomain.getEnvironment(), PROPERTIES_NAME);
            } catch (MsOnionException ex) {
                // 错误堆栈
                msOnionLogger.error(MsOnionNettyUtils.class.getName(), ex);
            }
        }
        return properties;
    }

    /**
     * 获取 Netty Server的端口号，例如：8145
     *
     * @param msOnionLog    日志
     * @param msOnionDomain 域名相关封装
     * @return 返回Netty Server的端口号
     * @throws MsOnionException 异常
     */
    public static int getNettyServerPort(MsOnionLogger msOnionLog, MsOnionDomain msOnionDomain) throws MsOnionException {
        try {
            if (serverPort > 0 && serverPort < Integer.MAX_VALUE) {
                return serverPort;
            }
            String strPort = loadProperties(msOnionLog, msOnionDomain).getProperty(MsOnionNettyConstants.NETTY_PROPERTIES_KEY_SERVER_PORT);
            serverPort = Integer.parseInt(strPort);
            return serverPort;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 获取 Netty Server的主机，例如：localhost
     *
     * @param msOnionLog    日志
     * @param msOnionDomain 域名相关封装
     * @return 返回Netty Server的主机
     * @throws MsOnionException 异常
     */
    public static String getNettyServerHost(MsOnionLogger msOnionLog, MsOnionDomain msOnionDomain) throws MsOnionException {
        try {
            if (MsOnionStringUtils.isNotBlank(serverHost)) {
                return serverHost;
            }
            serverHost = loadProperties(msOnionLog, msOnionDomain).getProperty(MsOnionNettyConstants.NETTY_PROPERTIES_KEY_SERVER_HOST);
            return serverHost;
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 开启Netty Server
     *
     * @param msOnionLogger    日志
     * @param msOnionDomain 域名
     * @param nettyServer   Netty Server
     * @param nettyClient   Netty Client
     * @throws MsOnionException 异常
     */
    public static void startNettyServer(MsOnionLogger msOnionLogger, MsOnionDomain msOnionDomain, MsOnionNettyServerAdapter nettyServer,
                                        MsOnionNettyClientAdapter nettyClient) throws MsOnionException {
        try {
            int port = getNettyServerPort(msOnionLogger, msOnionDomain);
            String host = getNettyServerHost(msOnionLogger, msOnionDomain);
            if (!serverStarted) {
                nettyServer.start(port);
                serverStarted = true;
            }
            // 包括端口号，不行！！
//            String host = msOnionDomain.getFileResourceApi();
            // 打印日志
            msOnionLogger.info(MsOnionNettyUtils.class.getName(),
                    String.format("host=%s, port=%s，nettyServer=%s，nettyClient=%s",
                            host, port, nettyServer, nettyClient));
            nettyClient.connect(new InetSocketAddress(host, port));
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param msOnionLogger    日志
     * @param msOnionDomain 域名
     * @param nettyClient   Netty客户端
     * @param filename      上传的文件
     * @return 返回上传结果
     * @throws MsOnionException 异常
     */
    public static MsOnionNettyResponse uploadFile(MsOnionLogger msOnionLogger, MsOnionDomain msOnionDomain,
                                                  MsOnionNettyClientAdapter nettyClient, String filename)
            throws MsOnionException {
        // TODO: 实际开发，要换成实际的 Service相关代码 ,
        // TODO: 具体参考：cc.msonion.carambola.fileresource.api.netty.test.upload.utils.TestMsOnionNettyUtils
//        try {
//            // TODO: TestNettyUploadCallable 实际开发需要修改！！！
//            MsOnionNettyResponse response = EXECUTOR_SERVICE.submit(new TestNettyUploadCallable(nettyClient, "uploadFile", filename)).get();
//
//            return response;
//        } catch (Exception ex) {
//            throw new MsOnionException(ex);
//        }

        return null;
    }

    /**
     * 生成MessageId，可能出现重复，分布式架构中，不过后台管理系统，商品操作，出现同一个时间点并发概率很低
     *
     * @return 返回MessageId，例如：2017060321285928668
     * @throws MsOnionException 异常
     */
    public static long generateMessageId() throws MsOnionException {
        try {
            // 20170603212859298
            String time = MsOnionDateUtils.formatYyyyMMddHHmmssSSSUnity(new Date());
            // 随机
            int random = MsOnionRandomUtils.getRandom(MESSAGE_ID_RANDOM_MIN_VALUE, MESSAGE_ID_RANDOM_MAX_VALUE);
            String value = String.valueOf(random);
            // 固定2位
            if (value.length() < MESSAGE_ID_RANDOM_FIX_LENGTH) {
                value = MsOnionFixedLengthUtils.fixLength(random, MESSAGE_ID_RANDOM_FIX_LENGTH);
            }
            value = String.format("%s%s", time, value);
            return Long.parseLong(value);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 生成MessageId，可能出现重复，分布式架构中，不过后台管理系统，商品操作，出现同一个时间点并发概率很低
     *
     * @return 返回MessageId，例如：2017060321285928668
     * @throws MsOnionException 异常
     */
    public static String getMessageIdForUpload() throws MsOnionException {
        return String.valueOf(generateMessageId());
    }

}
