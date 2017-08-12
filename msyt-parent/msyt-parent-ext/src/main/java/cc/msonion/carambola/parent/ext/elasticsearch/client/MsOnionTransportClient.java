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

package cc.msonion.carambola.parent.ext.elasticsearch.client;

import cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.ext.elasticsearch.constants.MsOnionElasticsearchConstants;
import cc.msonion.carambola.parent.ext.elasticsearch.utils.MsOnionElasticsearchUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Elasticsearch 客户端
 * @Title: MsOnionTransportClient.java
 * @Package: cc.msonion.carambola.parent.ext.elasticsearch.action
 * @Description: Elasticsearch 客户端
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年6月5日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * Elasticsearch 客户端
 * @ClassName: MsOnionTransportClient
 * @Description: Elasticsearch 客户端
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年6月5日 下午1:48:16
 */
public class MsOnionTransportClient extends PreBuiltTransportClient {

    /**
     * MsOnionTransportClient 构造方法
     *
     * @param clusterName      ES集群名称，例如：es.msyc.dev
     * @param transportAddress 例如：es01-dev:9300;es02-dev:9300;es03-dev:9300;es04-dev:9300;es05-dev:9300
     */
    public MsOnionTransportClient(String clusterName, String transportAddress) {
        this(clusterName, MsOnionElasticsearchUtils.getTransportAddresses(transportAddress));

        MsOnionLogger.doInfo(MsOnionTransportClient.class.getName(), String.format("构造方法#clusterName=%s，transportAddress=%s",
                clusterName, transportAddress));
    }

    public MsOnionTransportClient(String clusterName, List<TransportAddress> transportAddresses) {
//        if (MsOnionStringUtils.isBlank(clusterName)) {
//            throw new MsOnionRuntimeException("clusterName is null");
//        }
//        Settings settings = Settings.builder().put(MsOnionElasticsearchConstants.ES_SETTINGS_KEY_CLUSTER_NAME, clusterName).build();

        this(Settings.builder().put(MsOnionElasticsearchConstants.ES_SETTINGS_KEY_CLUSTER_NAME, clusterName).build());

        if (MsOnionCollectionUtils.isEmpty(transportAddresses)) {
            throw new MsOnionRuntimeException("transportAddresses is empty or null");
        }

        // 将List 转换成 数组
        TransportAddress[] transportAddressArray = transportAddresses.toArray(new TransportAddress[transportAddresses.size()]);
        // 添加
//        addTransportAddresses(transportAddressArray);

        addTransportAddress(transportAddressArray[0]);

        MsOnionLogger.doInfo(MsOnionTransportClient.class.getName(), String.format("构造方法#clusterName=%s，transportAddresses=%s",
                clusterName, transportAddresses));
    }

    /**
     * Creates a new transport client with pre-installed plugins.
     *
     * @param settings the settings passed to this transport client
     * @param plugins  an optional array of additional plugins to run with this client
     */
    public MsOnionTransportClient(Settings settings, Class<? extends Plugin>[] plugins) {
        super(settings, plugins);
    }

    /**
     * Creates a new transport client with pre-installed plugins.
     *
     * @param settings the settings passed to this transport client
     * @param plugins  a collection of additional plugins to run with this client
     */
    public MsOnionTransportClient(Settings settings, Collection<Class<? extends Plugin>> plugins) {
        super(settings, plugins);
    }

    /**
     * Creates a new transport client with pre-installed plugins.
     *
     * @param settings the settings passed to this transport client
     */
    public MsOnionTransportClient(Settings settings) {
        super(settings);
    }

    /**
     * Creates a new transport client with pre-installed plugins.
     *
     * @param settings            the settings passed to this transport client
     * @param plugins             a collection of additional plugins to run with this client
     * @param hostFailureListener a failure listener that is invoked if a node is disconnected; this can be <code>null</code>
     */
    public MsOnionTransportClient(Settings settings, Collection<Class<? extends Plugin>> plugins, HostFailureListener hostFailureListener) {
        super(settings, plugins, hostFailureListener);
    }

}
