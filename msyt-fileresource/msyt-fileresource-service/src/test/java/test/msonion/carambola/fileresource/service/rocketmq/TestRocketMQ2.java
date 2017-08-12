package test.msonion.carambola.fileresource.service.rocketmq;

import org.junit.Test;
import test.msonion.carambola.fileresource.service.rocketmq.messagelistener.TestMQMessageListenerConcurrently;

/**
 * Created by HeroCao on 2017/5/12.
 */
public class TestRocketMQ2  {

    @Test
    public void test01() {



    }

    @Test
    public void testMultiWithSameConsumerGroup() {

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 ...");

        String consumerGroup = "consumerGroup-01";

        // String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere

        String namesrvAddr = "rocketmq01-dev:9876;rocketmq02-dev:9876";

//        String topic = "topic-01";

        String topic = "msyt-mq-topic-dev-02";

        String subExpression = "*";


        TestMQMessageListenerConcurrently listenerConcurrently = new TestMQMessageListenerConcurrently();

        // 报错：
        // MsOnionRuntimeException: The consumer group[consumerGroup-01] has been created before, specify another name please.

        TestRocketMQPushConsumerExt consumerExt1 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently);


        TestRocketMQPushConsumerExt consumerExt2 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently);


        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt1=" + consumerExt1);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt2=" + consumerExt2);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , End !!!");
    }


    @Test
    public void testMultiWithSameConsumerGroup2() {

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 ...");

        String consumerGroup = "consumerGroup-01";

        // String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere

        String namesrvAddr = "rocketmq01-dev:9876;rocketmq02-dev:9876";

        String topic = "topic-01";

        String subExpression = "*";


        TestMQMessageListenerConcurrently listenerConcurrently = new  TestMQMessageListenerConcurrently();

        // 报错：
        // MsOnionRuntimeException: The consumer group[consumerGroup-01] has been created before, specify another name please.

        TestRocketMQPushConsumerExt consumerExt1 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently);


//        TestRocketMQPushConsumerExt consumerExt2 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently);


        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt1=" + consumerExt1);

//        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt2=" + consumerExt2);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , End !!!");
    }

    public static void main(String[] args) {


        // 同时一起创建是不可以的！！！ 报错
        // 报错： main" cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException: The consumer group[consumerGroup-01] has been created before, specify another name please.
        //

        // 运行之后，再运行 main 就可以了！！！
        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 ...");

        String consumerGroup = "consumerGroup-01";

        // String consumerGroup, String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere

        String namesrvAddr = "172.16.50.242:9876;172.16.50.243:9876";

//        String topic = "topic-01";

        String topic = "mq-topic-dev-02";

        String subExpression = "*";


        TestMQMessageListenerConcurrently listenerConcurrently1 = new  TestMQMessageListenerConcurrently();

        TestMQMessageListenerConcurrently listenerConcurrently2 = new  TestMQMessageListenerConcurrently();

        // 报错：
        // MsOnionRuntimeException: The consumer group[consumerGroup-01] has been created before, specify another name please.

        TestRocketMQPushConsumerExt consumerExt1 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently1);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt1=" + consumerExt1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        TestRocketMQPushConsumerExt consumerExt2 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently2);




//        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt2=" + consumerExt2);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , End !!!");


    }

}
