package test.msonion.carambola.parent.ext.mq.rocketmq;

import cc.msonion.carambola.parent.ext.mq.rocketmq.MsOnionRocketMQPushConsumerExt;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerConcurrently;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerOrderly;
import com.alibaba.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import com.alibaba.rocketmq.remoting.RPCHook;

/**
 * Created by HeroCao on 2017/5/10.
 */
public class TestRocketMQPushConsumerExt extends MsOnionRocketMQPushConsumerExt {


    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup                Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook                      RPCHook实例对象
     * @param allocateMessageQueueStrategy AllocateMessageQueueStrategy实例对象
     * @param namesrvAddr                  Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                        Topic
     * @param subExpression                订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerConcurrently         MsOnionRocketMQMessageListenerConcurrently实例对象
     * @param consumeFromWhere             ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently, String consumeFromWhere) {
        super(consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression, listenerConcurrently, consumeFromWhere);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup                Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook                      RPCHook实例对象
     * @param allocateMessageQueueStrategy AllocateMessageQueueStrategy实例对象
     * @param namesrvAddr                  Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                        Topic
     * @param subExpression                订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerConcurrently         MsOnionRocketMQMessageListenerConcurrently实例对象
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently) {
        super(consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression, listenerConcurrently);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup        Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook              RPCHook实例对象
     * @param namesrvAddr          Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                Topic
     * @param subExpression        订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerConcurrently MsOnionRocketMQMessageListenerConcurrently实例对象
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently) {
        super(consumerGroup, rpcHook, namesrvAddr, topic, subExpression, listenerConcurrently);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup        Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param namesrvAddr          Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                Topic
     * @param subExpression        订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerConcurrently MsOnionRocketMQMessageListenerConcurrently实例对象
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently) {
        super(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup        Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook              RPCHook实例对象
     * @param namesrvAddr          Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                Topic
     * @param subExpression        订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerConcurrently MsOnionRocketMQMessageListenerConcurrently实例对象
     * @param consumeFromWhere     ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently, String consumeFromWhere) {
        super(consumerGroup, rpcHook, namesrvAddr, topic, subExpression, listenerConcurrently, consumeFromWhere);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup        Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param namesrvAddr          Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                Topic
     * @param subExpression        订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerConcurrently MsOnionRocketMQMessageListenerConcurrently实例对象
     * @param consumeFromWhere     ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently, String consumeFromWhere) {
        super(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently, consumeFromWhere);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup                Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook                      RPCHook实例对象
     * @param allocateMessageQueueStrategy AllocateMessageQueueStrategy实例对象
     * @param namesrvAddr                  Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                        Topic
     * @param subExpression                订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerOrderly              MsOnionRocketMQMessageListenerOrderly 实例对象
     * @param consumeFromWhere             ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere) {
        super(consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression, listenerOrderly, consumeFromWhere);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup                Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook                      RPCHook实例对象
     * @param allocateMessageQueueStrategy AllocateMessageQueueStrategy实例对象
     * @param namesrvAddr                  Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic                        Topic
     * @param subExpression                订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerOrderly              MsOnionRocketMQMessageListenerOrderly 实例对象
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly) {
        super(consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression, listenerOrderly);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup   Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook         RPCHook实例对象
     * @param namesrvAddr     Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic           Topic
     * @param subExpression   订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerOrderly MsOnionRocketMQMessageListenerOrderly 实例对象
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly) {
        super(consumerGroup, rpcHook, namesrvAddr, topic, subExpression, listenerOrderly);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup   Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param namesrvAddr     Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic           Topic
     * @param subExpression   订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerOrderly MsOnionRocketMQMessageListenerOrderly 实例对象
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly) {
        super(consumerGroup, namesrvAddr, topic, subExpression, listenerOrderly);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup    Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param rpcHook          RPCHook实例对象
     * @param namesrvAddr      Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic            Topic
     * @param subExpression    订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerOrderly  MsOnionRocketMQMessageListenerOrderly 实例对象
     * @param consumeFromWhere ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere) {
        super(consumerGroup, rpcHook, namesrvAddr, topic, subExpression, listenerOrderly, consumeFromWhere);
    }

    /**
     * RocketMQ Push消费者扩展
     *
     * @param consumerGroup    Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param namesrvAddr      Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic            Topic
     * @param subExpression    订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     * @param listenerOrderly  MsOnionRocketMQMessageListenerOrderly 实例对象
     * @param consumeFromWhere ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    public TestRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere) {
        super(consumerGroup, namesrvAddr, topic, subExpression, listenerOrderly, consumeFromWhere);
    }
}
