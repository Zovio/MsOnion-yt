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

package cc.msonion.carambola.system.service.mq.rocketmq.messagelistener;

import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.interfaces.mq.rocketmq.listener.MsOnionRocketMQMessageListenerConcurrently;
import cc.msonion.carambola.system.service.SysConsumeMessageService;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * RocketMQ Push 消费者，普通消息（无序消息）监听器
 *
 * @Title: SystemConcurrentlyMessageListener.java
 * @Package: cc.msonion.carambola.system.service.mq.rocketmq.messagelistener
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月29日 下午5:11:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年5月29日 下午5:11:38
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * RocketMQ Push 消费者，普通消息（无序消息）监听器
 * @ClassName: SystemConcurrentlyMessageListener
 * @Description: SystemConcurrentlyMessageListener
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年5月29日 下午5:11:38
 */
public class SystemConcurrentlyMessageListener implements MsOnionRocketMQMessageListenerConcurrently {

    /**
     * 日志
     */
    @Autowired
    private MsOnionLogger msOnionLogger;

    /**
     * SysConsumeMessageService
     */
    @Autowired
    private SysConsumeMessageService sysConsumeMessageService;

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
        // 打印日志
        msOnionLogger.info(getClass().getName(), String.format("consumeMessage # 消费信息# Thread.currentThread()=%s", Thread.currentThread()));

        // 遍历消息，进行处理
        for (MessageExt messageExt : msgs) {
            try {
                String topic = messageExt.getTopic();
                String keys = messageExt.getKeys();
                String msgId = messageExt.getMsgId();
                String tags = messageExt.getTags();
                // 必须注意，编码必须使用 RemotingHelper.DEFAULT_CHARSET
                String msgBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                // 打印日志
                this.msOnionLogger.info(getClass().getName(), String.format("consumeMessage # 接收消息 # topic=%s, keys=%s, msgId=%s, tags=%s, msgBody=%s, messageExt=%s",
                        topic, keys, msgId, tags, msgBody, messageExt));

                // 1、TODO: 解决重复消息！！！调用msyt-system中的消费消息服务，根据msgId查询，是否存在记录
                // TODO: 如果已经存在 msgId 记录，就不再处理当前消息！！！
//                if (sysConsumeMessageService.queryByP)

                // 2、TODO: 需要判断当前topic的 tags 哪些不需要订阅处理消息！！！
//                for (String tag : MsOnionRocketMQUtils.MANAGER_HOME_UNSUBSCRIBE_TAGS) {
//                    if (MsOnionStringUtils.isNotBlank(tags) && !tags.equalsIgnoreCase(tag)) {
//                        // TODO: 处理消息！！！
//                    }
//                }
            } catch (Exception ex) {
                // 打印日志
                this.msOnionLogger.error(getClass().getName(), ex, "consumeMessage # 接收消息出现异常，RECONSUME_LATER，messageExt=" + messageExt);
                // 消费失败，重新消费！！！
                // Failure consumption,later try to consume
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        // 已经成功消费
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
