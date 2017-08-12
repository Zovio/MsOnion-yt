package test.msonion.carambola.parent.ext.mq.rocketmq;

import cc.msonion.carambola.parent.ext.mq.rocketmq.MsOnionRocketMQProducerExt;
import com.alibaba.rocketmq.remoting.RPCHook;

/**
 * Created by HeroCao on 2017/5/10.
 */
public class TestRocketMQProducerExt extends MsOnionRocketMQProducerExt {
    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param rpcHook       rpcHook实例对象
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic         topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     */
    public TestRocketMQProducerExt(String producerGroup, RPCHook rpcHook, String namesrvAddr, String topic) {
        super(producerGroup, rpcHook, namesrvAddr, topic);
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup  Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param rpcHook        rpcHook实例对象
     * @param namesrvAddr    Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic          topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     * @param delayTimeLevel RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
     *                       level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。
     */
    public TestRocketMQProducerExt(String producerGroup, RPCHook rpcHook, String namesrvAddr, String topic, int delayTimeLevel) {
        super(producerGroup, rpcHook, namesrvAddr, topic, delayTimeLevel);
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup  Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param namesrvAddr    Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic          topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     * @param delayTimeLevel RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
     *                       level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。
     */
    public TestRocketMQProducerExt(String producerGroup, String namesrvAddr, String topic, int delayTimeLevel) {
        super(producerGroup, namesrvAddr, topic, delayTimeLevel);
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @param producerGroup Producer 组名，多个 Producer 如果属于一 个应用，发送同样的消息，则应该将它们 归为同一组
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic         topic， RocketMQ问题汇总，一个consumerGroup只对应一个topic
     */
    public TestRocketMQProducerExt(String producerGroup, String namesrvAddr, String topic) {
        super(producerGroup, namesrvAddr, topic);
    }

//    public TestRocketMQProducerExt(String producerGroup, RPCHook rpcHook, String namesrvAddr) {
//        super(producerGroup, rpcHook, namesrvAddr);
//    }
//
//    public TestRocketMQProducerExt(String producerGroup, String namesrvAddr) {
//        super(producerGroup, namesrvAddr);
//    }


}
