package test.msonion.carambola.fileresource.service.rocketmq;

import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.ext.mq.rocketmq.MsOnionRocketMQProducer;
import cc.msonion.carambola.parent.ext.spring.MsOnionSpringContainer;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import test.msonion.carambola.fileresource.service.rocketmq.messagelistener.TestMQMessageListenerConcurrently;
import test.msonion.carambola.fileresource.service.rocketmq.pojo.TestMember;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by HeroCao on 2017/5/10.
 */
@ContextConfiguration(locations = MsOnionSpringContainer.CONFIG_FILE)
public class TestRocketMQ extends AbstractJUnit4SpringContextTests {

//    @Autowired
//    private MsOnionRocketMQPushConsumerExt consumer;

//    @Resource
//    private MsOnionRocketMQPushConsumerExt consumerExt;
//
//    @Autowired
//    private MsOnionRocketMQProducerExt producerExt;

    @Autowired
    private TestRocketMQProducerExt testRocketMQProducerExt;

    @Autowired
    private TestRocketMQPushConsumerExt testRocketMQPushConsumerExt;


    @Autowired
    private TestRocketMQOrderlyPushConsumerExt orderlyPushConsumerExt;

    @Value("${rocketmq.topic}")
    private String topic;

    @Value("${rocketmq.tag}")
    private String tag;

    @Value("${rocketmq.consumer.topic.orderly}")
    private String orderlyTopic;



    // rocketmq.orderly.consumerGroup


    @Autowired
    private MsOnionLogger msOnionLog;

    @Before
    public void setUp() {

//        producer.setRetryTimesWhenSendAsyncFailed(0);

        // TODO:
//        producerExt.getMsOnionRocketMQProducer().setRetryTimesWhenSendAsyncFailed(0);
//
//        //
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

//        consumerExt.getMsOnionRocketMQPushConsumer().setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);


    }

    /**
     * 测试生产数据  ListenerConcurrently
     */
    @Test
    public void testProduceDataForListenerConcurrently() {

//        System.out.println("testProduceData ## testRocketMQProducerExt=" + testRocketMQProducerExt);
//
//        System.out.println("testProduceData ## testRocketMQPushConsumerExt=" + testRocketMQPushConsumerExt);

        /*
        testProduceData ## testRocketMQProducerExt=test.msonion.carambola.parent.ext.mq.rocketmq.TestRocketMQProducerExt@239105a8
testProduceData ## testRocketMQPushConsumerExt=test.msonion.carambola.parent.ext.mq.rocketmq.TestRocketMQPushConsumerExt@3fce8fd9
testProduceData ## consumerExt=test.msonion.carambola.parent.ext.mq.rocketmq.TestRocketMQPushConsumerExt@3fce8fd9
testProduceData ## producerExt=test.msonion.carambola.parent.ext.mq.rocketmq.TestRocketMQProducerExt@239105a8
         */

//        System.out.println("testProduceData ## consumerExt=" + consumerExt);
//
//        System.out.println("testProduceData ## producerExt=" + producerExt);

//        topic = "msyt-mq-topic-dev-02";
        topic = "mq-topic-dev-02";

        System.out.println("testProduceData ## topic=" + topic);

        System.out.println("testProduceData ## msOnionLog=" + msOnionLog);



//        consumer.sendMessageBack();

//        producer.send()

//        for (int i = 0; i < 10000000; i++) {

//        for (int i = 0; i < 10; i++) {
        for (int i = 0; i < 2; i++) {
            try {
                final int index = i;

                // 注意编码必须使用： RemotingHelper.DEFAULT_CHARSET
                // 有序 Topic
//                Message msg = new Message(orderlyTopic,

                Message msg = new Message(topic,
//                        "TagA-" + i,
                        tag,

                        "OrderID-" + i,
                        String.format("Hello RocketMQ，i=%d", i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                // 不要采取 SendCallback 方式，有BUG，消息堆积到几百就报错！！！
                // com.alibaba.rocketmq.client.exception.MQClientException: wait response timeout 3000ms


//                producer.send(msg, new SendCallback() {
//                    @Override
//                    public void onSuccess(SendResult sendResult) {
//                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
//                    }
//
//                    @Override
//                    public void onException(Throwable e) {
//                        System.out.printf("%-10d Exception %s %n", index, e);
//                        e.printStackTrace();
//                    }
//                });


                SendResult sendResult = testRocketMQProducerExt.getMsOnionRocketMQProducer().send(msg);

                System.out.println("sendResult ## sendResult=" + sendResult);

                SendStatus sendStatus = sendResult.getSendStatus();

//                sendStatus.ordinal()



            } catch (Exception e) {
//                e.printStackTrace();

                msOnionLog.error(getClass().getName(), e);
            }
        }



    }


    /**
     * 测试生产数据  ListenerOrderly
     */
    @Test
    public void testProduceDataForListenerOrderly() {

        /**
         * Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'testRocketMQOrderlyPushConsumerExt' defined in file [C:\Users\HeroCao\Downloads\HeroCao\Code\GitLab\2017-03-22\Ms.Onion-YT\msyt-parent\msyt-parent-ext\target\test-classes\META-INF\spring\external\baseContext.xml]: Bean instantiation via constructor failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [test.msonion.carambola.parent.ext.mq.rocketmq.TestRocketMQOrderlyPushConsumerExt]: Constructor threw exception; nested exception is cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException:
         *
         * The consumer group[msyt-mq-consumer-group-dev] has been created before, specify another name please.

         */

        System.out.println("testProduceData ## consumerExt=" + testRocketMQPushConsumerExt);

        System.out.println("testProduceData ## producerExt=" + testRocketMQProducerExt);

        System.out.println("testProduceData ## orderlyPushConsumerExt=" + orderlyPushConsumerExt);

//        System.out.println("testProduceData ## producerExt=" + order);

        System.out.println("testProduceData ## orderlyTopic=" + orderlyTopic);

        System.out.println("testProduceData ## msOnionLog=" + msOnionLog);



        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};

        MsOnionRocketMQProducer producer = testRocketMQProducerExt.getMsOnionRocketMQProducer();

//        producer.setNamesrvAddr();

        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;

            TestMember member = new TestMember();
            member.setCode(String.format("code-" + i));
            member.setCreateByMemberIdx(i + 1L);
            member.setCreateTime(new Date());
            member.setDepartmentIdx(i + 1L);
            member.setEmail(String.format("test-%s@msyc.cc", i));
            member.setExt("扩展 # " + i);
            member.setFullName("全名 # " + i);
            member.setIdx(i + 2L);
            member.setPassword("密码 ## " +i);
            member.setName("用户名 #" +i);



            try {
//                Message msg =
//                        new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i,
//                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

//                Message msg = new Message("msyt-mq-dev",
//                        "msyt-member" + i,
//                        "OrderID188-" + i,
//                        member.toString().getBytes(RemotingHelper.DEFAULT_CHARSET));


                Message msg = new Message(orderlyTopic,
                        tags[i % tags.length],
                        "OrderID188-" + i,
                        member.toString().getBytes(RemotingHelper.DEFAULT_CHARSET));

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();

                        System.out.println(String.format("mqs=%s, msg=%s, arg=%s", mqs, msg, arg));

                        return mqs.get(index);
                    }
                }, orderId);

                System.out.printf("%s%n", sendResult);

            } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException ex) {


//                ex.printStackTrace();

                msOnionLog.error(getClass().getName(), ex);
            } catch (UnsupportedEncodingException ex) {
//                e.printStackTrace();

                msOnionLog.error(getClass().getName(), ex);
            }


        }


    }


    @Test
    public void testMultiWithSameConsumerGroup() {

        // TODO： 同一个进程运行的消费者，使用通过一个 ConsumerGroup 报错
        // TODO: 如果是在 不同的进程就可以 ！！！
        // TODO: 不同的进程可以，也就是在 部署在 不同的Tomcat 节点中
        // TODO: 就可以实现 负载均衡 ， 负载均衡就是通过同一个 ConsumerGroup 实现的！！！



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


        TestRocketMQPushConsumerExt consumerExt2 = new TestRocketMQPushConsumerExt(consumerGroup, namesrvAddr, topic, subExpression, listenerConcurrently);


        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt1=" + consumerExt1);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , consumerExt2=" + consumerExt2);

        System.out.println("testMultiWithSameConsumerGroup # 测试多个相同的消费者组名 , End !!!");
    }



}
