package test.msonion.carambola.parent.ext.rocketmq.simple;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by HeroCao on 2017/5/7.
 */
public class TestPushConsumer {


    private static final String CONSUMER_GROUP = "CID_JODIE_1";

//    private static final String NAMESRV_ADDR = "172.16.50.242:9876;172.16.50.243:9876";

    private static final String TOPIC = "msyc-mq-topic-dev";

//    private static final String NAMESRV_ADDR = "172.16.50.242:9876;172.16.50.243:9876";

    private static final String NAMESRV_ADDR = "rocketmq01-dev:9876;rocketmq02-dev:9876";

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);

        consumer.setNamesrvAddr(NAMESRV_ADDR);

        consumer.subscribe(TOPIC, "*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            /**

             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");

                if (System.currentTimeMillis() % 2 == 0) {
                    System.out.println("接收信息失败 # 需要重试 ！！！");
                    // 失败，需要重试，RocketMQ 底层自动重试，必须处理重复！！！
                    // 查看当前信息，是否重新发送 ！！！
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;


            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");

//        consumer.shutdown();
    }
}
