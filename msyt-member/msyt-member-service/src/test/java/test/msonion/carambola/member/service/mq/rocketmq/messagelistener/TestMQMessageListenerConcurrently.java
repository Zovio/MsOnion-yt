package test.msonion.carambola.member.service.mq.rocketmq.messagelistener;

import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by HeroCao on 2017/5/29.
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
//        if (System.currentTimeMillis() % 2 == 0) {
//            System.out.println("TestMQMessageListenerConcurrently # 模拟 ## 接收消息失败 # 需要重试 ！！！");
//            // 失败，需要重试，RocketMQ 底层自动重试，必须处理重复！！！
//            // 查看当前信息，是否重新发送 ！！！
//            // 有重新发送 ！！！
//            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//        }

        // 成功接收消息

        // 遍历消息，进行处理
        for (MessageExt messageExt : msgs) {
//            byte[] bytes = messageExt.getBody();

            try {

                String topic = messageExt.getTopic();

                String keys = messageExt.getKeys();

                String msgId = messageExt.getMsgId();

                int queueId = messageExt.getQueueId();

                String buyerId = messageExt.getBuyerId();

                // 必须注意，编码必须使用 RemotingHelper.DEFAULT_CHARSET
                String msgBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                String tags = messageExt.getTags();

                System.out.println("收到消息：" + "  topic :" + topic + "  ,tags : " + tags + " ,msg : "
                        + msgBody + " ,keys=" + keys + "，msgId=" + msgId + "，queueId=" + queueId
                        + "，buyerId=" + buyerId);

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


        /*
        ConsumeMessageThread_10 消费者# 接收消息 # TestMQMessageListenerConcurrently #
         Receive New Messages: [MessageExt [queueId=3, storeSize=534, queueOffset=18, sysFlag=0,
         bornTimestamp=1496048420463, bornHost=/172.16.10.129:49835, storeTimestamp=1496048219766,
         storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A03BB1, commitLogOffset=27278257,
         bodyCRC=1001642186, reconsumeTimes=0, preparedTransactionOffset=0,
          toString()=Message [topic=msyt-mq-topic-dev, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=19, KEYS=123,
           CONSUME_START_TIME=1496048420467, UNIQ_KEY=AC100A8136C414DAD5DC9145086F0000, TAGS=Menu@Save}, body=360]]]


收到消息：  topic :msyt-mq-topic-dev  ,tags : Menu@Save ,
msg : {"idx":123,"pidx":0,"code":"test-menu-105",
"name":"测试菜单-105","url":"","urlMapping":"","remark":"备注：测试菜单-105",
"zindex":1,"topShow":1,"level":1,"iconUrl":"","menuCateIdx":0,
"markButton":0,"status":1,"version":23,"createByMemberIdx":0,"updateByMemberIdx":0,
"createTime":1496048420003,"updateTime":1496048420003,"ext":"","id":"123","pid":"0"}
,keys=123，msgId=AC100A8136C414DAD5DC9145086F0000，

queueId=3，buyerId=null

         */


        /*

        // 当前Topic 如果之前没有消费，依然会从 配置偏移位置开始消费， 关键是设置，消息保存在服务器的时间！！！


test.msonion.carambola.member.service.mq.rocketmq.TestRocketMQPushConsumerExt, 构造方法 # Concurrently # consumerGroup=msyt-mq-consumer-group-dev-01, rpcHook=null, allocateMessageQueueStrategy=null, namesrvAddr=rocketmq01-dev:9876;rocketmq02-dev:9876, topic=msyt-mq-topic-dev-01, subExpression=Menu@Save || Menu@Update, listenerConcurrently=test.msonion.carambola.member.service.mq.rocketmq.messagelistener.TestMQMessageListenerConcurrently@5f233b26
test.msonion.carambola.member.service.mq.rocketmq.TestRocketMQPushConsumerExt, 构造方法 # Concurrently # 准备开启 Consumer ...
test.msonion.carambola.member.service.mq.rocketmq.TestRocketMQPushConsumerExt, 构造方法 # Concurrently # 已经开启 Consumer ！！！
ConsumeMessageThread_1 消费者# 接收消息 # TestMQMessageListenerConcurrently #  Receive New Messages: [MessageExt [queueId=1, storeSize=558, queueOffset=1, sysFlag=0, bornTimestamp=1496050379953, bornHost=/172.16.10.129:50673, storeTimestamp=1496050179216, storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A92C26, commitLogOffset=27864102, bodyCRC=744545137, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=msyt-mq-topic-dev-01, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=2, KEYS=125, CONSUME_START_TIME=1496050696896, UNIQ_KEY=AC100A812DEC14DAD5DC9162EEB10000, TAGS=Menu@Update}, body=379]]]
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Update ,msg :
 {"idx":125,"pidx":0,"code":"test-menu-107","name":"测试菜单-107，修改1","url":"","urlMapping":"",
 "remark":"备注：测试菜单-107，修改1","zindex":2,"topShow":1,"level":1,"iconUrl":"","menuCateIdx":0,
 "markButton":0,"status":1,"version":0,"createByMemberIdx":0,"updateByMemberIdx":0,"createTime":1496050379000,
 "updateTime":1496050379000,"ext":"","id":"125","pid":"0"} ,keys=125，msgId=AC100A812DEC14DAD5DC9162EEB10000，queueId=1，buyerId=null
-------------------------------
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
 The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
result=1
休眠500 毫秒 。。。
ConsumeMessageThread_2 消费者# 接收消息 # TestMQMessageListenerConcurrently #
 Receive New Messages: [MessageExt [queueId=1, storeSize=558, queueOffset=2, sysFlag=0, bornTimestamp=1496050701007,
 bornHost=/172.16.10.129:50806, storeTimestamp=1496050500260, storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A92E54
 , commitLogOffset=27864660, bodyCRC=1369872904, reconsumeTimes=0, preparedTransactionOffset=0,
 toString()=Message [topic=msyt-mq-topic-dev-01, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=3, KEYS=125,
  CONSUME_START_TIME=1496050701012, UNIQ_KEY=AC100A81074C14DAD5DC9167D4CE0000, TAGS=Menu@Update}, body=379]]]
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Update ,msg : {"idx":125,"pidx":0,"code":"test-menu-107",
"name":"测试菜单-107，修改2","url":"","urlMapping":"","remark":"备注：测试菜单-107，修改2","zindex":2,"topShow":1,
"level":1,"iconUrl":"","menuCateIdx":0,"markButton":0,"status":1,"version":0,"createByMemberIdx":0,"updateByMemberIdx":0,
"createTime":1496050700143,"updateTime":1496050700143,"ext":"","id":"125","pid":"0"} ,keys=125，
msgId=AC100A81074C14DAD5DC9167D4CE0000，queueId=1，buyerId=null
-------------------------------

Process finished with exit code 0

// 数据库表 ，  idx , msgId (char(32))  , topic varchar(50), tags varchar(50), keys varchar(100) ,  message varchar(1000)


收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Update ,msg : {"idx":125,"pidx":0,"code":"test-menu-107",
"name":"测试菜单-107，修改3","url":"","urlMapping":"","remark":"备注：测试菜单-107，修改3",
"zindex":2,"topShow":1,"level":1,"iconUrl":"","menuCateIdx":0,"markButton":0,"status":1,"version":0,
"createByMemberIdx":0,"updateByMemberIdx":0,"createTime":1496051040290,"updateTime":1496051040290,"ext":"",
"id":"125","pid":"0"} ,keys=125，msgId=AC100A81397C14DAD5DC916D05B10000，queueId=1，buyerId=null

-------------------------------
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
 The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
ConsumeMessageThread_3 消费者# 接收消息 # TestMQMessageListenerConcurrently #  Receive New Messages:
[MessageExt [queueId=0, storeSize=537, queueOffset=0, sysFlag=0, bornTimestamp=1496052444869, bornHost=/172.16.10.129:51535,
 storeTimestamp=1496052244086, storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A934C9,

 commitLogOffset=27866313, bodyCRC=1590732530, reconsumeTimes=0, preparedTransactionOffset=0,
 toString()=Message [topic=msyt-mq-topic-dev-01, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=1, KEYS=128,
 CONSUME_START_TIME=1496052444876, UNIQ_KEY=AC100A8161CC14DAD5DC918270C40000, TAGS=Menu@Save}, body=360]]]
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Save ,msg : {"idx":128,"pidx":0,"code":"test-menu-110",
"name":"测试菜单-110","url":"","urlMapping":"","remark":"备注：测试菜单-110","zindex":1,"topShow":1,"level":1,
"iconUrl":"","menuCateIdx":0,"markButton":0,"status":1,"version":28,"createByMemberIdx":0,"updateByMemberIdx":0,
"createTime":1496052444611,"updateTime":1496052444611,"ext":"","id":"128","pid":"0"} ,keys=128，
msgId=AC100A8161CC14DAD5DC918270C40000，queueId=0，buyerId=null
-------------------------------
新增菜单 ## msOnionResult=MsOnionResult [status=200, msg=OK, data=Menu [Hash = 1421650289,
 idx=128, pidx=0, code=test-menu-110, name=测试菜单-110, url=, urlMapping=, remark=备注：测试菜单-110,
  zindex=1, topShow=1, level=1, iconUrl=, menuCateIdx=0, markButton=0, status=1, version=28, createByMemberIdx=0,
   updateByMemberIdx=0, createTime=Mon May 29 18:07:24 CST 2017, updateTime=Mon May 29 18:07:24 CST 2017, ext=,
   serialVersionUID=1, id=, pid=]]
休眠 2分钟 ！！！



=======================================



est.msonion.carambola.member.service.mq.rocketmq.TestRocketMQPushConsumerExt, 构造方法 # Concurrently # consumerGroup=msyt-mq-consumer-group-dev-01, rpcHook=null, allocateMessageQueueStrategy=null, namesrvAddr=rocketmq01-dev:9876;rocketmq02-dev:9876, topic=msyt-mq-topic-dev-01, subExpression=Menu@Save || Menu@Update, listenerConcurrently=test.msonion.carambola.member.service.mq.rocketmq.messagelistener.TestMQMessageListenerConcurrently@5f233b26
test.msonion.carambola.member.service.mq.rocketmq.TestRocketMQPushConsumerExt, 构造方法 # Concurrently # 准备开启 Consumer ...
test.msonion.carambola.member.service.mq.rocketmq.TestRocketMQPushConsumerExt, 构造方法 # Concurrently # 已经开启 Consumer ！！！

ConsumeMessageThread_2 消费者# 接收消息 # TestMQMessageListenerConcurrently #  Receive New Messages:
[MessageExt [queueId=2, storeSize=537, queueOffset=0, sysFlag=0, bornTimestamp=1496051208889, bornHost=/172.16.10.129:51081,
 storeTimestamp=1496051008132, storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A932B0,
  commitLogOffset=27865776, bodyCRC=563406326, reconsumeTimes=0, preparedTransactionOffset=0,
  toString()=Message [topic=msyt-mq-topic-dev-01, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=1,
  KEYS=126, CONSUME_START_TIME=1496052592083, UNIQ_KEY=AC100A81472814DAD5DC916F94B90000, TAGS=Menu@Save}, body=360]]]

ConsumeMessageThread_3 消费者# 接收消息 # TestMQMessageListenerConcurrently #
 Receive New Messages: [MessageExt [queueId=1, storeSize=558, queueOffset=3, sysFlag=0, bornTimestamp=1496051041202,
 bornHost=/172.16.10.129:50933, storeTimestamp=1496050840448, storeHost=/172.16.50.242:10911,
  msgId=AC1032F200002A9F0000000001A93082, commitLogOffset=27865218, bodyCRC=1129491134,
   reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=msyt-mq-topic-dev-01, flag=0,
    properties={MIN_OFFSET=0, MAX_OFFSET=4, KEYS=125, CONSUME_START_TIME=1496052592084,
    UNIQ_KEY=AC100A81397C14DAD5DC916D05B10000, TAGS=Menu@Update}, body=379]]]
ConsumeMessageThread_1 消费者# 接收消息 # TestMQMessageListenerConcurrently #
 Receive New Messages: [MessageExt [queueId=0, storeSize=537, queueOffset=0,
  sysFlag=0, bornTimestamp=1496052444869, bornHost=/172.16.10.129:51535, storeTimestamp=1496052244086,
  storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A934C9, commitLogOffset=27866313,
  bodyCRC=1590732530, reconsumeTimes=0, preparedTransactionOffset=0,
   toString()=Message [topic=msyt-mq-topic-dev-01, flag=0,
   properties={MIN_OFFSET=0, MAX_OFFSET=1, KEYS=128,
    CONSUME_START_TIME=1496052592084, UNIQ_KEY=AC100A8161CC14DAD5DC918270C40000, TAGS=Menu@Save}, body=360]]]
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Save ,
msg : {"idx":128,"pidx":0,"code":"test-menu-110","name":"测试菜单-110","url":"","urlMapping":"",
"remark":"备注：测试菜单-110","zindex":1,"topShow":1,"level":1,"iconUrl":"","menuCateIdx":0,"markButton":0,"status":1,"version":28,"createByMemberIdx":0,"updateByMemberIdx":0,"createTime":1496052444611,"updateTime":1496052444611,"ext":"","id":"128","pid":"0"} ,keys=128，msgId=AC100A8161CC14DAD5DC918270C40000，queueId=0，buyerId=null
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Save ,
msg : {"idx":126,"pidx":0,"code":"test-menu-108","name":"测试菜单-108",
"url":"","urlMapping":"","remark":"备注：测试菜单-108","zindex":1,"topShow":1,
"level":1,"iconUrl":"","menuCateIdx":0,"markButton":0,"status":1,"version":26,
"createByMemberIdx":0,"updateByMemberIdx":0,"createTime":1496051208662,"updateTime":1496051208662,
"ext":"","id":"126","pid":"0"} ,keys=126，msgId=AC100A81472814DAD5DC916F94B90000，queueId=2，buyerId=null
-------------------------------
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Update ,msg :
 {"idx":125,"pidx":0,"code":"test-menu-107","name":"测试菜单-107，修改3","url":"",
 "urlMapping":"","remark":"备注：测试菜单-107，修改3","zindex":2,"topShow":1,"level":1,"iconUrl":"",
 "menuCateIdx":0,"markButton":0,"status":1,"version":0,"createByMemberIdx":0,"updateByMemberIdx":0,
 "createTime":1496051040290,"updateTime":1496051040290,"ext":"","id":"125","pid":"0"} ,
 keys=125，msgId=AC100A81397C14DAD5DC916D05B10000，queueId=1，buyerId=null
-------------------------------
-------------------------------
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
result=1
休眠500 毫秒 。。。
ConsumeMessageThread_4 消费者# 接收消息 # TestMQMessageListenerConcurrently #  Receive New Messages: [MessageExt [queueId=1, storeSize=558, queueOffset=4, sysFlag=0, bornTimestamp=1496052597021, bornHost=/172.16.10.129:51618, storeTimestamp=1496052396235, storeHost=/172.16.50.242:10911, msgId=AC1032F200002A9F0000000001A936E2, commitLogOffset=27866850, bodyCRC=372238404, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=msyt-mq-topic-dev-01, flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=5, KEYS=125, CONSUME_START_TIME=1496052597025, UNIQ_KEY=AC100A813ABC14DAD5DC9184C31C0000, TAGS=Menu@Update}, body=379]]]
收到消息：  topic :msyt-mq-topic-dev-01  ,tags : Menu@Update ,msg : {"idx":125,"pidx":0,"code":"test-menu-107","name":"测试菜单-107，修改5","url":"","urlMapping":"","remark":"备注：测试菜单-107，修改5","zindex":2,"topShow":1,"level":1,"iconUrl":"","menuCateIdx":0,"markButton":0,"status":1,"version":0,"createByMemberIdx":0,"updateByMemberIdx":0,"createTime":1496052596164,"updateTime":1496052596164,"ext":"","id":"125","pid":"0"} ,keys=125，msgId=AC100A813ABC14DAD5DC9184C31C0000，queueId=1，buyerId=null
-------------------------------

Process finished with exit code 0






         */

    }
}
