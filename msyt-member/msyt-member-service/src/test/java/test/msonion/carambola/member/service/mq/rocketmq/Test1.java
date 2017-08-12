package test.msonion.carambola.member.service.mq.rocketmq;

import org.junit.Test;

/**
 * Created by HeroCao on 2017/5/29.
 */
public class Test1 {


    @Test
    public void testRocketMQMessageId() {

        String msgId = "AC100A81397C14DAD5DC916D05B10000";

        /**
         * msgId=AC100A81397C14DAD5DC916D05B10000，msgId.length()=32
         */
        System.out.println("msgId=" + msgId + "，msgId.length()=" + msgId.length());

    }
}
