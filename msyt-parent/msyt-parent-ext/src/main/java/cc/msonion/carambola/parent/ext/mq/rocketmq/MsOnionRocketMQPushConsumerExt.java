/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */

package cc.msonion.carambola.parent.ext.mq.rocketmq;

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerConcurrently;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerOrderly;
import com.alibaba.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.remoting.RPCHook;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: MsOnionRocketMQPushConsumerExt.java
 * @Package: cc.msonion.carambola.parent.ext.mq.rocketmq
 * @Description: MsOnionRocketMQPushConsumerExt
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月10日 下午3:24:42
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月10日 下午3:24:42
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * RocketMQ Push消费者扩展
 *
 * @ClassName: MsOnionRocketMQPushConsumerExt
 * @Description: MsOnionRocketMQPushConsumerExt
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月10日 下午3:24:42
 */
public abstract class MsOnionRocketMQPushConsumerExt {

    /**
     * MsOnionRocketMQPushConsumer
     */
    private MsOnionRocketMQPushConsumer msOnionRocketMQPushConsumer;

    /**
     * 日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * Topic
     */
    private String topic;

    /**
     * 订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     */
    private String subExpression;

    /**
     * Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     */
    private String consumerGroup;

    /**
     * Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     */
    private String namesrvAddr;

    /**
     * ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     */
    private String consumeFromWhere;

    /**
     * consumerGroup is null
     */
    private static final String CONSUMER_GROUP_NULL = "consumerGroup is null";

    /**
     * namesrvAddr is null
     */
    private static final String NAMESRV_ADDR_NULL = "namesrvAddr is null";

    /**
     * topic is null
     */
    private static final String TOPIC_NULL = "topic is null";

    /**
     * subExpression is null
     */
    private static final String SUB_EXPRESSION_NULL = "subExpression is null";

    /**
     * listenerConcurrently is null
     */
    private static final String LISTENER_CONCURRENTLY_NULL = "listenerConcurrently is null";

    /**
     * listenerOrderly is null
     */
    private static final String LISTENER_ORDERLY_NULL = "listenerOrderly is null";

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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy,
                                          String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerConcurrently listenerConcurrently, String consumeFromWhere) {
        inspectParameters(consumerGroup, namesrvAddr, topic, subExpression);
        if (null == listenerConcurrently) {
            throw new MsOnionRuntimeException(LISTENER_CONCURRENTLY_NULL, MsOnionStatusConstants.STATUS_400);
        }

        consumerGroup = consumerGroup.trim();
        namesrvAddr = namesrvAddr.trim();
        topic = topic.trim();
        subExpression = subExpression.trim();

        try {

            if (null != rpcHook && null != allocateMessageQueueStrategy) {
                msOnionRocketMQPushConsumer = new MsOnionRocketMQPushConsumer(consumerGroup, rpcHook, allocateMessageQueueStrategy);
            } else if (null != rpcHook) {
                msOnionRocketMQPushConsumer = new MsOnionRocketMQPushConsumer(rpcHook);
                msOnionRocketMQPushConsumer.setConsumerGroup(consumerGroup);
            } else {
                msOnionRocketMQPushConsumer = new MsOnionRocketMQPushConsumer(consumerGroup);
            }

            // 构造方法中，对象还没有创建， 字段 msOnionLogger 还没有值，不能使用！！！
            MsOnionLogger.doInfo(getClass().getName(), String.format("构造方法 # Concurrently # consumerGroup=%s, rpcHook=%s, allocateMessageQueueStrategy=%s, namesrvAddr=%s, topic=%s, subExpression=%s, listenerConcurrently=%s",
                    consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression, listenerConcurrently));
            this.topic = topic;
            this.subExpression = subExpression;
            this.consumerGroup = consumerGroup;
            this.namesrvAddr = namesrvAddr;
            // set namesrvAddr
            msOnionRocketMQPushConsumer.setNamesrvAddr(namesrvAddr);
            // subscribe
            msOnionRocketMQPushConsumer.subscribe(topic, subExpression);
            // 注册
            msOnionRocketMQPushConsumer.registerMessageListener(listenerConcurrently);
            // 设置 consumeFromWhere
            setConsumeFromWhere(consumeFromWhere);
            // 构造方法中，对象还没有创建， 字段 msOnionLogger 还没有值，不能使用！！！
            MsOnionLogger.doInfo(getClass().getName(), String.format("构造方法 # Concurrently # 准备开启 Consumer ..."));
            msOnionRocketMQPushConsumer.start();
            MsOnionLogger.doInfo(getClass().getName(), String.format("构造方法 # Concurrently # 已经开启 Consumer ！！！"));
        } catch (Exception ex) {
            throw new MsOnionRuntimeException(ex);
        }
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy,
                                          String namesrvAddr, String topic, String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently) {
        this(consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression,
                listenerConcurrently, MsOnionRocketMQConstants.CONSUME_FROM_FIRST_OFFSET);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic,
                                          String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently) {
        this(consumerGroup, rpcHook, null, namesrvAddr, topic, subExpression, listenerConcurrently);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerConcurrently listenerConcurrently) {
        this(consumerGroup, null, namesrvAddr, topic, subExpression, listenerConcurrently);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic,
                                          String subExpression, MsOnionRocketMQMessageListenerConcurrently listenerConcurrently,
                                          String consumeFromWhere) {
        this(consumerGroup, rpcHook, null, namesrvAddr, topic, subExpression,
                listenerConcurrently, consumeFromWhere);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerConcurrently listenerConcurrently, String consumeFromWhere) {
        this(consumerGroup, null, namesrvAddr, topic, subExpression,
                listenerConcurrently, consumeFromWhere);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy,
                                          String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere) {
        inspectParameters(consumerGroup, namesrvAddr, topic, subExpression);

        if (null == listenerOrderly) {
            throw new MsOnionRuntimeException(LISTENER_ORDERLY_NULL, MsOnionStatusConstants.STATUS_400);
        }

        consumerGroup = consumerGroup.trim();
        namesrvAddr = namesrvAddr.trim();
        topic = topic.trim();
        subExpression = subExpression.trim();

        try {

            if (null != rpcHook && null != allocateMessageQueueStrategy) {
                msOnionRocketMQPushConsumer = new MsOnionRocketMQPushConsumer(consumerGroup, rpcHook, allocateMessageQueueStrategy);
            } else if (null != rpcHook) {
                msOnionRocketMQPushConsumer = new MsOnionRocketMQPushConsumer(rpcHook);
                msOnionRocketMQPushConsumer.setConsumerGroup(consumerGroup);
            } else {
                msOnionRocketMQPushConsumer = new MsOnionRocketMQPushConsumer(consumerGroup);
            }

            // 构造方法中，对象还没有创建， 字段 msOnionLogger 还没有值，不能使用！！！
            MsOnionLogger.doInfo(getClass().getName(), String.format("构造方法 # Orderly # consumerGroup=%s, rpcHook=%s, allocateMessageQueueStrategy=%s, namesrvAddr=%s, topic=%s, subExpression=%s, listenerOrderly=%s",
                    consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression, listenerOrderly));
            this.topic = topic;
            this.subExpression = subExpression;
            this.consumerGroup = consumerGroup;
            this.namesrvAddr = namesrvAddr;
            // set namesrvAddr
            msOnionRocketMQPushConsumer.setNamesrvAddr(namesrvAddr);
            // subscribe
            msOnionRocketMQPushConsumer.subscribe(topic, subExpression);
            // 注册
            msOnionRocketMQPushConsumer.registerMessageListener(listenerOrderly);
            // 设置 consumeFromWhere
            setConsumeFromWhere(consumeFromWhere);
            // 构造方法中，对象还没有创建， 字段 msOnionLogger 还没有值，不能使用！！！
            MsOnionLogger.doInfo(getClass().getName(), String.format("构造方法 # Orderly # 准备开启 Consumer ..."));
            // 在构造方法中开启
            msOnionRocketMQPushConsumer.start();
            MsOnionLogger.doInfo(getClass().getName(), String.format("构造方法 # Orderly # 已经开启 Consumer ！！！"));
        } catch (Exception ex) {
            throw new MsOnionRuntimeException(ex);
        }
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, AllocateMessageQueueStrategy allocateMessageQueueStrategy,
                                          String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerOrderly listenerOrderly) {
        this(consumerGroup, rpcHook, allocateMessageQueueStrategy, namesrvAddr, topic, subExpression,
                listenerOrderly, MsOnionRocketMQConstants.CONSUME_FROM_FIRST_OFFSET);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic,
                                          String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly) {
        this(consumerGroup, rpcHook, null, namesrvAddr, topic, subExpression, listenerOrderly);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerOrderly listenerOrderly) {
        this(consumerGroup, null, namesrvAddr, topic, subExpression, listenerOrderly);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, RPCHook rpcHook, String namesrvAddr, String topic,
                                          String subExpression, MsOnionRocketMQMessageListenerOrderly listenerOrderly,
                                          String consumeFromWhere) {
        this(consumerGroup, rpcHook, null, namesrvAddr, topic,
                subExpression, listenerOrderly, consumeFromWhere);
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
    public MsOnionRocketMQPushConsumerExt(String consumerGroup, String namesrvAddr, String topic, String subExpression,
                                          MsOnionRocketMQMessageListenerOrderly listenerOrderly, String consumeFromWhere) {
        this(consumerGroup, null, namesrvAddr, topic, subExpression, listenerOrderly, consumeFromWhere);
    }

    /**
     * 关闭 Consumer
     */
    public void shutdown() {
        msOnionRocketMQPushConsumer.shutdown();
    }

    /**
     * 设置 consumeFromWhere
     *
     * @param consumeFromWhere consumeFromWhere
     */
    private void setConsumeFromWhere(String consumeFromWhere) {
        if (MsOnionStringUtils.isBlank(consumeFromWhere)) {
            consumeFromWhere = MsOnionRocketMQConstants.CONSUME_FROM_FIRST_OFFSET;
        }

        consumeFromWhere = consumeFromWhere.trim();
        this.consumeFromWhere = consumeFromWhere;

        if (consumeFromWhere.equalsIgnoreCase(MsOnionRocketMQConstants.CONSUME_FROM_LAST_OFFSET)) {
            this.msOnionRocketMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        } else if (consumeFromWhere.equalsIgnoreCase(MsOnionRocketMQConstants.CONSUME_FROM_TIMESTAMP)) {
            this.msOnionRocketMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        } else if (consumeFromWhere.equalsIgnoreCase(MsOnionRocketMQConstants.CONSUME_FROM_FIRST_OFFSET)) {
            this.msOnionRocketMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        }
    }

    //////////////////////////// 校验参数值  ## Begin  //////////////////////////

    /**
     * 检查参数
     *
     * @param consumerGroup Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     * @param namesrvAddr   Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     * @param topic         Topic
     * @param subExpression 订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     */
    private void inspectParameters(final String consumerGroup, final String namesrvAddr, final String topic,
                                   final String subExpression) {
        if (MsOnionStringUtils.isBlank(consumerGroup)) {
            throw new MsOnionRuntimeException(CONSUMER_GROUP_NULL, MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionStringUtils.isBlank(namesrvAddr)) {
            throw new MsOnionRuntimeException(NAMESRV_ADDR_NULL, MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionStringUtils.isBlank(topic)) {
            throw new MsOnionRuntimeException(TOPIC_NULL, MsOnionStatusConstants.STATUS_400);
        }

        if (MsOnionStringUtils.isBlank(subExpression)) {
            throw new MsOnionRuntimeException(TOPIC_NULL, MsOnionStatusConstants.STATUS_400);
        }
    }

    /////////////////////////// 校验参数值 ## End ///////////////////////////

    ////////////////// Setter、Getters  ### Begin ////////////////////////////////////

    /**
     * 日志
     *
     * @return 日志
     */
    public MsOnionLogger getMsOnionLogger() {
        return msOnionLogger;
    }

    /**
     * Topic
     *
     * @return 返回 Topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 订阅指定topic下tags，可以指定多个，也可以为*，*：表示订阅所有tags ，也可以 TagA、TagA || TagB || TagC
     *
     * @return 返回 订阅指定topic下tags
     */
    public String getSubExpression() {
        return subExpression;
    }

    /**
     * Consumer 组名，多个 Consumer 如果属于一个应用，订阅同样的消 息，且消费逻辑一致，则应该将它们归为同一组
     *
     * @return 返回 Consumer 组名
     */
    public String getConsumerGroup() {
        return consumerGroup;
    }

    /**
     * 返回 Name Server 地址列表，多个 NameServer 地址用分号 隔开 ，例如：rocketmq01-dev:9876;rocketmq02-dev:9876
     *
     * @return 返回 Name Server 地址列表
     */
    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    /**
     * MsOnionRocketMQPushConsumer
     *
     * @return MsOnionRocketMQPushConsumer
     */
    public MsOnionRocketMQPushConsumer getMsOnionRocketMQPushConsumer() {
        return msOnionRocketMQPushConsumer;
    }

    /**
     * ConsumeFromWhere：CONSUME_FROM_FIRST_OFFSET（默认）、CONSUME_FROM_LAST_OFFSET、CONSUME_FROM_TIMESTAMP
     *
     * @return ConsumeFromWhere
     */
    public String getConsumeFromWhere() {
        return consumeFromWhere;
    }

    ////////////////// Setter、Getters  ### End ////////////////////////////////////

}
