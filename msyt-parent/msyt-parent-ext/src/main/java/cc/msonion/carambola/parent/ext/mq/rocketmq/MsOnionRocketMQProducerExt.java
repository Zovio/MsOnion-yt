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

package cc.msonion.carambola.parent.ext.mq.rocketmq;

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import com.alibaba.rocketmq.remoting.RPCHook;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Title: MsOnionRocketMQProducerExt.java
 * @Package: cc.msonion.carambola.parent.ext.mq.rocketmq
 * @Description: MsOnionRocketMQProducerExt
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月10日 下午3:24:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月10日 下午3:24:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * RocketMQ 生产者扩展
 *
 * @ClassName: MsOnionRocketMQProducerExt
 * @Description: MsOnionRocketMQProducerExt
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月10日 下午3:24:42
 */
public abstract class MsOnionRocketMQProducerExt {

    /**
     * MsOnionRocketMQProducer
     */
    private MsOnionRocketMQProducer msOnionRocketMQProducer;

    /**
     * Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     */
    private String namesrvAddr;

    /**
     * Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     */
    private String producerGroup;

    /**
     * topic，RocketMQ问题汇总，一个consumerGroup只对应一个topic
     */
    private String topic;

    /**
     * RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
     level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。 <br/>

     如何配置
     在服务器端（rocketmq-broker端）的属性配置文件中加入以下行： <br/>

     messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     描述了各级别与延时时间的对应映射关系。  <br/>

     这个配置项配置了从1级开始，各级延时的时间，可以修改这个指定级别的延时时间；  <br/>
     时间单位支持：s、m、h、d，分别表示秒、分、时、天； <br/>
     默认值就是上面声明的，可手工调整；  <br/>
     默认值已够用，不建议修改这个值。  <br/>
     如何发送延时消息  <br/>
     发送延时消息只需要在客户端（rocketmq-client端）待发送的消息（ com.alibaba.rocketmq.common.message.Message ）中设置延时级别即可。

     “设置消息延时 10s 消费”的 Producer 端代码如下： <br/>

     Message msg = newMessage(topic, tags, keys, body);  <br/>
     msg.setDelayTimeLevel(3);  <br/>
     ...  <br/>
     SendResult sendResult = getMQProducer().send(msg);  <br/>
     */
    private int delayTimeLevel;

//    /**
//     * RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
//     level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。
//     */
//    public static final int DEFAULT_DELAY_TIME_LEVEL = 0;

    /**
     * producerGroup is null
     */
    private static final String PRODUCER_GROUP_NULL = "producerGroup is null";

    /**
     * namesrvAddr is null
     */
    private static final String NAMESRV_ADDR_NULL = "namesrvAddr is null";

    /**
     * topic is null
     */
    private static final String TOPIC_NULL = "topic is null";

//    /**
//     * RocketMQ 生产者扩展
//     *
//     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
//     * @param rpcHook       rpcHook实例对象
//     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
//     */
//    public MsOnionRocketMQProducerExt(String producerGroup, RPCHook rpcHook, String namesrvAddr) {
//
//    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param rpcHook       rpcHook实例对象
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     */
    public MsOnionRocketMQProducerExt(String producerGroup, RPCHook rpcHook, String namesrvAddr, String topic) {
        this(producerGroup, rpcHook, namesrvAddr, topic, MsOnionRocketMQConstants.DEFAULT_DELAY_TIME_LEVEL);
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param rpcHook       rpcHook实例对象
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     * @param delayTimeLevel RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
    level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。
     */
    public MsOnionRocketMQProducerExt(String producerGroup, RPCHook rpcHook, String namesrvAddr, String topic, int delayTimeLevel) {
        if (MsOnionStringUtils.isBlank(producerGroup)) {
            throw new MsOnionRuntimeException(PRODUCER_GROUP_NULL, MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionStringUtils.isBlank(namesrvAddr)) {
            throw new MsOnionRuntimeException(NAMESRV_ADDR_NULL, MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionStringUtils.isBlank(topic)) {
            throw new MsOnionRuntimeException(TOPIC_NULL, MsOnionStatusConstants.STATUS_400);
        }

        // 去掉左右空格
        producerGroup = producerGroup.trim();
        namesrvAddr = namesrvAddr.trim();
        topic = topic.trim();

        if (null != rpcHook) {
            msOnionRocketMQProducer = new MsOnionRocketMQProducer(producerGroup, rpcHook);
        } else {
            msOnionRocketMQProducer = new MsOnionRocketMQProducer(producerGroup);
        }
        msOnionRocketMQProducer.setNamesrvAddr(namesrvAddr);

        this.namesrvAddr = namesrvAddr;
        this.producerGroup = producerGroup;
        this.topic = topic;
        this.delayTimeLevel = delayTimeLevel;

        try {
            // 在构造方中启动
            msOnionRocketMQProducer.start();
        } catch (Exception ex) {
            throw new MsOnionRuntimeException(ex);
        }
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     * @param delayTimeLevel RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
    level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。
     */
    public MsOnionRocketMQProducerExt(String producerGroup, String namesrvAddr, String topic, int delayTimeLevel) {
        this(producerGroup, null, namesrvAddr, topic, delayTimeLevel);
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     */
    public MsOnionRocketMQProducerExt(String producerGroup, String namesrvAddr, String topic) {
        this(producerGroup, null, namesrvAddr, topic);
    }

//    /**
//     * RocketMQ 生产者扩展
//     *
//     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
//     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
//     */
//    public MsOnionRocketMQProducerExt(String producerGroup, String namesrvAddr) {
//        this(producerGroup, null, namesrvAddr);
//    }

    /**
     * 关闭 Producer
     */
    public void shutdown() {
        msOnionRocketMQProducer.shutdown();
    }

    //////////////////// Setters、Getters ## Begin  ///////////////////////////////

    /**
     * Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     *
     * @return Name Server 地址列表
     */
    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    /**
     * Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     *
     * @return Producer 组名
     */
    public String getProducerGroup() {
        return producerGroup;
    }

    /**
     * MsOnionRocketMQProducer
     *
     * @return MsOnionRocketMQProducer
     */
    public MsOnionRocketMQProducer getMsOnionRocketMQProducer() {
        return msOnionRocketMQProducer;
    }

    /**
     * 获取topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     * @return 返回topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
     level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。 <br/>

     如何配置
     在服务器端（rocketmq-broker端）的属性配置文件中加入以下行： <br/>

     messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     描述了各级别与延时时间的对应映射关系。  <br/>

     这个配置项配置了从1级开始，各级延时的时间，可以修改这个指定级别的延时时间；  <br/>
     时间单位支持：s、m、h、d，分别表示秒、分、时、天； <br/>
     默认值就是上面声明的，可手工调整；  <br/>
     默认值已够用，不建议修改这个值。  <br/>
     如何发送延时消息  <br/>
     发送延时消息只需要在客户端（rocketmq-client端）待发送的消息（ com.alibaba.rocketmq.common.message.Message ）中设置延时级别即可。

     “设置消息延时 10s 消费”的 Producer 端代码如下： <br/>

     Message msg = newMessage(topic, tags, keys, body);  <br/>
     msg.setDelayTimeLevel(3);  <br/>
     ...  <br/>
     SendResult sendResult = getMQProducer().send(msg);  <br/>
     */
    public int getDelayTimeLevel() {
        return delayTimeLevel;
    }

    //////////////////// Setters、Getters ## End ///////////////////////////////

}
