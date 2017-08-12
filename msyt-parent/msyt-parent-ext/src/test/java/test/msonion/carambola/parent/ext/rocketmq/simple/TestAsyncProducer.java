package test.msonion.carambola.parent.ext.rocketmq.simple;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * Created by HeroCao on 2017/5/7.
 */
public class TestAsyncProducer {

    private static final String PRODUCER_GROUP = "msyc-mq-producer-group-dev";

    private static final String TOPIC = "msyc-mq-topic-dev";

//    private static final String NAMESRV_ADDR = "172.16.50.242:9876;172.16.50.243:9876";

    private static final String NAMESRV_ADDR = "rocketmq01-dev:9876;rocketmq02-dev:9876";

    public static void main(String[] args) throws MQClientException, InterruptedException, UnsupportedEncodingException {

        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

//        for (int i = 0; i < 10000000; i++) {

        for (int i = 0; i < 100; i++) {
            try {
                final int index = i;
                Message msg = new Message(TOPIC,
                        "TagA-" + i,
                        "OrderID-" + i,
                        "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));

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

                SendResult sendResult = producer.send(msg);

                System.out.println("sendResult ## sendResult=" + sendResult);

                SendStatus sendStatus = sendResult.getSendStatus();

//                sendStatus.ordinal()



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }

}
