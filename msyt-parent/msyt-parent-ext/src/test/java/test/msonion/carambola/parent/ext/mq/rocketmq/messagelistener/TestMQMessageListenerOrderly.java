package test.msonion.carambola.parent.ext.mq.rocketmq.messagelistener;

import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerOrderly;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by HeroCao on 2017/5/10.
 */
public class TestMQMessageListenerOrderly implements MsOnionRocketMQMessageListenerOrderly {

    private AtomicLong consumeTimes = new AtomicLong(0);

    /**
     * It is not recommend to throw exception,rather than returning ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT if consumption failure
     *
     * @param msgs    msgs.size() >= 1<br>
     *                DefaultMQPushConsumer.consumeMessageBatchMaxSize=1，you can modify here
     * @param context
     * @return
     */
    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {


        // TODO: 不是自动提交 ！！！
        context.setAutoCommit(false);
        System.out.printf(Thread.currentThread().getName() + " # 接收有序消息 ## Orderly ## Receive New Messages: " + msgs + "%n");

        this.consumeTimes.incrementAndGet();
        if ((this.consumeTimes.get() % 2) == 0) {
            return ConsumeOrderlyStatus.SUCCESS;
        } else if ((this.consumeTimes.get() % 3) == 0) {
            return ConsumeOrderlyStatus.ROLLBACK; // 已经废弃
        } else if ((this.consumeTimes.get() % 4) == 0) {
            return ConsumeOrderlyStatus.COMMIT; // 已经废弃
        } else if ((this.consumeTimes.get() % 5) == 0) {
            context.setSuspendCurrentQueueTimeMillis(3000);
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }

        return ConsumeOrderlyStatus.SUCCESS;
    }
}
