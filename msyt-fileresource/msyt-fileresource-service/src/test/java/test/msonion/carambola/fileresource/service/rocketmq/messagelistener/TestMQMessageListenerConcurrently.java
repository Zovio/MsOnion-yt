package test.msonion.carambola.fileresource.service.rocketmq.messagelistener;

import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by HeroCao on 2017/5/10.
 */
public class TestMQMessageListenerConcurrently implements MsOnionRocketMQMessageListenerConcurrently {

    @Autowired
    private MsOnionLogger msOnionLog;

    /**
     * It is not recommend to throw exception,rather than returning ConsumeConcurrentlyStatus.RECONSUME_LATER if consumption failure
     *
     * @param msgs    msgs.size() >= 1<br>
     *                DefaultMQPushConsumer.consumeMessageBatchMaxSize=1，you can modify here
     * @param context
     * @return
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {


        System.out.printf(Thread.currentThread().getName() + " 消费者# 接收消息 # TestMQMessageListenerConcurrently #  Receive New Messages: " + msgs + "%n");

        // 模拟接收失败！！！
        // TODO: RocketMQ 可能存在重复消息，需要处理！！！
        // TODO: 特别是和金额有关系，必须注意！！！ 需要处理重复信息！！！
        if (System.currentTimeMillis() % 2 == 0) {
            System.out.println("TestMQMessageListenerConcurrently # 模拟 ## 接收消息失败 # 需要重试 ！！！");
            // 失败，需要重试，RocketMQ 底层自动重试，必须处理重复！！！
            // 查看当前信息，是否重新发送 ！！！
            // 有重新发送 ！！！
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        // 成功接收消息

        // 遍历消息，进行处理
        for (MessageExt messageExt : msgs) {
//            byte[] bytes = messageExt.getBody();

            try {

                String topic = messageExt.getTopic();

                // 必须注意，编码必须使用 RemotingHelper.DEFAULT_CHARSET
                String msgBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                String tags = messageExt.getTags();

                System.out.println("收到消息：" + "  topic :" + topic + "  ,tags : " + tags + " ,msg : " + msgBody);
                System.out.println("-------------------------------");

            } catch (Exception ex) {
                // 测试，时间开发
//                ex.printStackTrace();
                this.msOnionLog.error(getClass().getName(), ex, "接收消息错误，RECONSUME_LATER，messageExt=" + messageExt);

                // Failure consumption,later try to consume
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

    }
}
