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

package cc.msonion.carambola.parent.ext.service;

import cc.msonion.carambola.parent.common.constants.MsOnionMessageConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.enums.MsOnionCrudAction;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.ext.mq.rocketmq.MsOnionRocketMQConstants;
import cc.msonion.carambola.parent.ext.mq.rocketmq.MsOnionRocketMQProducer;
import cc.msonion.carambola.parent.ext.mq.rocketmq.simple.MsOnionSimpleRocketMQProducerExt;
import cc.msonion.carambola.parent.ext.utils.mq.MsOnionRocketMQUtils;
import cc.msonion.carambola.parent.ext.utils.zookeeper.MsOnionZookeeperUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBaseExampleAdapter;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.adapter.MsOnionParameterAdapter;
import cc.msonion.carambola.parent.pojo.zk.client.MsOnionCuratorZookeeperClient;
import cc.msonion.carambola.parent.pojo.zk.retrypolicy.MsOnionRetryNTimes;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 分布式底层架构公共Service扩展，封装RocketMQ操作
 *
 * @Title: MsOnionMQService.java
 * @Package: cc.msonion.carambola.parent.interfaces.service
 * @Description: 对Service封装
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午5:11:38
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月17日 下午5:11:38
 * @Modify-version: V2.0.0
 * @Modify-description: 修改：日志，使用MsOnionLog，throws MsOnionException
 */

/**
 * 分布式底层架构公共Service扩展，封装RocketMQ操作
 *
 * @param <T> 目标POJO
 * @param <E> 目标POJO的Example
 * @ClassName: MsOnionMQService
 * @Description: MsOnionMQService
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月17日 下午5:11:38
 */
public abstract class MsOnionMQService<T extends MsOnionBasePojoAdapter, E extends MsOnionBaseExampleAdapter> extends MsOnionBatchService<T, E> {

    /**
     * MsOnionServiceExt 当前实现类的实例对象，方便于多线程中获取当前this
     */
    private MsOnionMQService msOnionMQService = this;

    /**
     * 发送MQ线程数量
     */
    @Value("${rocketmq.producer.sendMQThreadCount}")
    private int sendMQThreadCount;

    /**
     * RocketMQ 生产者扩展实例对象
     */
//    @Autowired // 先注释掉！！！
    private MsOnionSimpleRocketMQProducerExt msOnionSimpleRocketMQProducerExt;

    /**
     * 发送MQ的线程池
     */
    private ExecutorService sendMQThreadPool;

    /**
     * 根据主键idx删除，（不允许直接删除数据、软删除，就是更新状态）
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public int deleteByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, Long idx)
            throws MsOnionIllegalArgumentException, MsOnionException {
        int result = super.deleteByPrimaryKey(apiVersion, parameter, idx);

        // 删除，需要发送 MQ
        // add RocketMQ
//        sendMQForDeleteByPrimaryKey(apiVersion, parameter, idx, result);

        return result;
    }

    /**
     * 根据主键idx集合进行批量删除（软删除）
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      主键idx集合
     * @return 返回删除记录行数
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年3月13日 下午6:27:37
     */
    @Override
    public int deleteByIdxes(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, List<Long> idxes)
            throws MsOnionIllegalArgumentException, MsOnionException {
        int result = super.deleteByIdxes(apiVersion, parameter, idxes);

        // 批量删除，需要发送 MQ
        // add RocketMQ，
        // 可能存在，删除操作影响行数 result 和 idxes 个数不匹配，目前也无法处理，除非把批量删除，换成遍历循环一个一个删除
//        sendMQForDeleteByIdxes(apiVersion, parameter, idxes, result);

        return result;
    }

    /**
     * 保存（新增）目标POJO的记录
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例对象
     * @return 如果返回1：成功，0：失败
     * @throws MsOnionIllegalArgumentException
     * @throws MsOnionException
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date: 2017年2月17日 下午6:30:12
     */
    @Override
    public int save(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException {
        // idxCode
        try {
            Field idxCodeField = record.getClass().getDeclaredField("idxCode");
            // 允许访问
            idxCodeField.setAccessible(true);
            // 动态生成idxCode
            long idxCode = MsOnionZookeeperUtils.getIdxCode(getMsOnionLogger(), getMsOnionDomain(), getMsOnionCuratorZookeeperClient(), getRetryPolicy());
            if (idxCode <= 0) {
                // 再次动态生成idxCode
                idxCode = MsOnionZookeeperUtils.getIdxCode(getMsOnionLogger(), getMsOnionDomain(), getMsOnionCuratorZookeeperClient(), getRetryPolicy());
            }
            idxCodeField.set(record, idxCode);
            // 打印日志
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(), String.format("save#动态生成idxCode=%s,record=%s", idxCode, record));
        } catch (Exception e) {
            throw new MsOnionException(MsOnionMessageConstants.MESSAGE_SET_IDX_CODE_ERROR, e, MsOnionStatusConstants.STATUS_400);
        }

        int result = super.save(apiVersion, parameter, record);

        // 新增记录（插入），需要发送 MQ
        // add RocketMQ
//        sendMQForSave(apiVersion, parameter, record, result);

        return result;
    }



    /**
     * 通过目标POJO的Example进行更新
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param record     目标POJO的实例
     * @param example    目标POJO的Example实例
     * @return 返回目标POJO的Example进行更新
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    @Override
    public int updateByExample(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record, E example)
            throws MsOnionIllegalArgumentException, MsOnionException {
        int result = super.updateByExample(apiVersion, parameter, record, example);
        // 新增记录（插入），需要发送 MQ
        // add RocketMQ
        // TODO: 批量更新，也就是可能同时更新多条记录，有问题！！！

        return result;
    }

    /**
     * 根据目标POJO的主键idx更新
     *
     * @param apiVersion api版本
     * @param parameter  参数适配器
     * @param record     目标POJO的实例
     * @return 返回数据库更新操作影响行数，1：更新成功，0：更新失败
     * @throws MsOnionIllegalArgumentException 非法参数异常
     * @throws MsOnionException                异常
     * @Author: HeroCao hero-cao@msyc.cc
     * @Date 2017年3月18日 下午1:00:56
     */
    @Override
    public int updateByPrimaryKey(MsOnionApiVersion apiVersion, MsOnionParameterAdapter parameter, T record) throws MsOnionIllegalArgumentException, MsOnionException {
        int result = super.updateByPrimaryKey(apiVersion, parameter, record);

        // 新增记录（插入），需要发送 MQ
        // add RocketMQ
//        sendMQForUpdateByPrimaryKey(apiVersion, parameter, record, result);

        return result;
    }

    ////////////////////  Send MQ ## Begin ////////////////////////

    /**
     * 发送MQ，Save
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param t          目标POJO实例对象
     * @param result     数据库删除操作影响行数
     */
    private void sendMQForSave(final MsOnionApiVersion apiVersion, final MsOnionParameterAdapter parameter,
                               final T t, final int result) {

        if (result <= 0 || null == t) {
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                    String.format("sendMQForSave # 参数不符合，不发送MQ，result=%s，pojo=%s", result, t));
            return;
        }

        getSendMQThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                long idx = 0;
                try {
                    // 将目标POJO实例对象转换成Json字符串
                    String json = MsOnionJsonUtils.objectToJson(t);

                    // 获取 Topic
                    String topic = msOnionSimpleRocketMQProducerExt.getTopic();
                    String keys = t.getId();
                    String simpleName = t.getClass().getSimpleName();
                    String tags = MsOnionRocketMQUtils.getTags(simpleName, MsOnionCrudAction.SAVE);

                    idx = Long.parseLong(t.getId());
                    // 发送消息，MQ
                    sendMessageWithMQ(json, idx, MsOnionCrudAction.SAVE, topic, tags, keys, t);
                } catch (MsOnionException e) {
                    getMsOnionLogger().error(msOnionMQService.getClass().getName(), e,
                            String.format("sendMQForSave # MQ发送消息失败 # idx=%s，pojo=%s", idx, t));
                }
            }
        });
    }

    /**
     * 发送MQ，DeleteByPrimaryKey
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idx        主键idx
     * @param result     数据库删除操作影响行数
     */
    private void sendMQForDeleteByPrimaryKey(final MsOnionApiVersion apiVersion, final MsOnionParameterAdapter parameter,
                                             final Long idx, final int result) {

        if (result <= 0 || idx <= 0) {
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                    String.format("sendMQForDeleteByPrimaryKey # 参数为0，不发送MQ，result=%s，idx=%s", result, idx));
            return;
        }

        getSendMQThreadPool().execute(new Runnable() {
            @Override
            public void run() {

                T t = null;
                try {
                    // 根据主键idx 查询目标POJO实例对象
                    t = queryByPrimaryKey(apiVersion, parameter, idx);
                    // 将目标POJO实例对象转换成Json字符串
                    String json = MsOnionJsonUtils.objectToJson(t);

                    // 获取 Topic
                    String topic = msOnionSimpleRocketMQProducerExt.getTopic();
                    String keys = String.valueOf(idx);
                    String simpleName = t.getClass().getSimpleName();
                    String tags = MsOnionRocketMQUtils.getTags(simpleName, MsOnionCrudAction.DELETE);
                    // 发送消息，MQ
                    sendMessageWithMQ(json, idx, MsOnionCrudAction.DELETE, topic, tags, keys, t);
                } catch (MsOnionException e) {
                    getMsOnionLogger().error(msOnionMQService.getClass().getName(), e,
                            String.format("sendMQForDeleteByPrimaryKey # MQ发送消息失败 # idx=%s，pojo=%s", idx, t));
                }
            }
        });
    }

    /**
     * 发送MQ，deleteByIdxes
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param idxes      删除记录主键idx的集合
     * @param result     数据库删除操作影响行数
     */
    private void sendMQForDeleteByIdxes(final MsOnionApiVersion apiVersion, final MsOnionParameterAdapter parameter,
                                        final List<Long> idxes, final int result) {

        if (result <= 0 || MsOnionCollectionUtils.isEmpty(idxes)) {
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                    String.format("sendMQForDeleteByIdxes # 参数不符合，不发送MQ，result=%s，已经删除主键idx集合idxes=%s", result, idxes));
            return;
        }

        getSendMQThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                T t = null;
                // 遍历 idxes
                for (long idx : idxes) {
                    try {
                        // 根据主键idx 查询目标POJO实例对象
                        t = queryByPrimaryKey(apiVersion, parameter, idx);
                        // 将目标POJO实例对象转换成Json字符串
                        String json = MsOnionJsonUtils.objectToJson(t);

                        // 获取 Topic
                        String topic = msOnionSimpleRocketMQProducerExt.getTopic();
                        String keys = String.valueOf(idx);
                        String simpleName = t.getClass().getSimpleName();
                        String tags = MsOnionRocketMQUtils.getTags(simpleName, MsOnionCrudAction.DELETE);
                        // 发送消息，MQ
                        sendMessageWithMQ(json, idx, MsOnionCrudAction.DELETE, topic, tags, keys, t);
                    } catch (MsOnionException e) {
                        getMsOnionLogger().error(msOnionMQService.getClass().getName(), e,
                                String.format("sendMQForDeleteByIdxes # MQ发送消息失败 # idx=%s，pojo=%s", idx, t));
                    }
                }
            }
        });
    }

    /**
     * 发送MQ，updateByPrimaryKey
     *
     * @param apiVersion api版本
     * @param parameter  参数
     * @param t          目标POJO实例对象
     * @param result     数据库删除操作影响行数
     */
    private void sendMQForUpdateByPrimaryKey(final MsOnionApiVersion apiVersion, final MsOnionParameterAdapter parameter,
                                             final T t, final int result) {

        if (result <= 0 || null == t) {
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                    String.format("sendMQForUpdateByPrimaryKey # 参数不符合，不发送MQ，result=%s，pojo=%s", result, t));
            return;
        }

        getSendMQThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                long idx = 0;
                try {
                    // 将目标POJO实例对象转换成Json字符串
                    String json = MsOnionJsonUtils.objectToJson(t);

                    // 获取 Topic
                    String topic = msOnionSimpleRocketMQProducerExt.getTopic();
                    String keys = t.getId();
                    String simpleName = t.getClass().getSimpleName();
                    String tags = MsOnionRocketMQUtils.getTags(simpleName, MsOnionCrudAction.UPDATE);

                    idx = Long.parseLong(t.getId());
                    // 发送消息，MQ
                    sendMessageWithMQ(json, idx, MsOnionCrudAction.UPDATE, topic, tags, keys, t);
                } catch (MsOnionException e) {
                    getMsOnionLogger().error(msOnionMQService.getClass().getName(), e,
                            String.format("sendMQForUpdateByPrimaryKey # MQ发送消息失败 # idx=%s，pojo=%s", idx, t));
                }
            }
        });
    }

    /**
     * 发生MQ消息
     *
     * @param data   需要发送数据
     * @param idx    主键idx
     * @param action 增、删、改、查操作动作
     * @param topic  Topic
     * @param tags   Tags
     * @param keys   Keys
     * @param t      目标POJO实例对象
     */
    protected final void sendMessageWithMQ(final String data, final long idx, final MsOnionCrudAction action,
                                           final String topic, final String tags, final String keys, final T t) {
        if (MsOnionStringUtils.isBlank(data) || idx <= 0 || null == action
                || MsOnionStringUtils.isBlank(topic) || MsOnionStringUtils.isBlank(tags)
                || MsOnionStringUtils.isBlank(keys) || null == t) {
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                    String.format("sendMessageWithMQ # 参数为空，不发送MQ，idx=%s，操作动作action=%s，topic=%s，tags=%s，keys=%s，需要发送数据data=%s，pojo=%s",
                            idx, action.getValue(), topic, tags, keys, data, t));
            return;
        }

        // 打印日志
        getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                String.format("sendMessageWithMQ # 准备发送MQ，idx=%s，操作动作action=%s，topic=%s，tags=%s，keys=%s，需要发送数据data=%s，pojo=%s",
                        idx, action.getValue(), topic, tags, keys, data, t));

        MsOnionRocketMQProducer rocketMQProducer = msOnionSimpleRocketMQProducerExt.getMsOnionRocketMQProducer();

//        msOnionRocketMQProducer.setInstanceName();

        // 创建 Message
        Message message = new Message();

        /*
            RocketMQ 支持定时消息，但是不支持任意时间精度，仅支持特定的 level，例如定时 5s， 10s， 1m 等。其中，level=0 级表示不延时，
            level=1 表示 1 级延时，level=2 表示 2 级延时，以此类推。

            如何配置
            在服务器端（rocketmq-broker端）的属性配置文件中加入以下行：

            messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            描述了各级别与延时时间的对应映射关系。

            这个配置项配置了从1级开始，各级延时的时间，可以修改这个指定级别的延时时间；
            时间单位支持：s、m、h、d，分别表示秒、分、时、天；
            默认值就是上面声明的，可手工调整；
            默认值已够用，不建议修改这个值。
            如何发送延时消息
            发送延时消息只需要在客户端（rocketmq-client端）待发送的消息（ com.alibaba.rocketmq.common.message.Message ）中设置延时级别即可。

            “设置消息延时 10s 消费”的 Producer 端代码如下：

            Message msg = newMessage(topic, tags, keys, body);
            msg.setDelayTimeLevel(3);
            ...
            SendResult sendResult = getMQProducer().send(msg);
            ...
         */

        // 设置消息延时
        if (msOnionSimpleRocketMQProducerExt.getDelayTimeLevel() > MsOnionRocketMQConstants.DEFAULT_DELAY_TIME_LEVEL) {
            message.setDelayTimeLevel(msOnionSimpleRocketMQProducerExt.getDelayTimeLevel());
        }

        try {
            // 设置发送消息Body
            message.setBody(data.getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 设置 topic
            message.setTopic(topic);
            // 设置 tags
            message.setTags(tags);
            // 设置 keys
            message.setKeys(keys);
            // 发送消息
            SendResult sendResult = rocketMQProducer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    /*
                    RocketMQ通过轮询所有队列的方式来确定消息被发送到哪一个队列（负载均衡策略）。
                    比如下面的示例中，订单号相同的消息会被先后发送到同一个队列中：

// RocketMQ通过MessageQueueSelector中实现的算法来确定消息发送到哪一个队列上
// RocketMQ默认提供了两种MessageQueueSelector实现：随机/Hash
// 当然可以根据业务实现自己的MessageQueueSelector来决定消息按照何种策略发送到消息队列中
                     */
                    long currentIdx = (long) arg;
                    // 是否出现超过 Int 最大值 ！！！
                    int index = (int) (currentIdx % mqs.size());
                    return mqs.get(index);
                }
            }, idx);

            // 打印日志
            getMsOnionLogger().debug(msOnionMQService.getClass().getName(),
                    String.format("sendMessageWithMQ # idx=%s，keys=%s，操作动作action=%s，tags=%s，发送MQ结果sendResult=%s，发送数据data=%s，pojo=%s",
                            idx, keys, action, tags, sendResult, data, t));

//        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException | UnsupportedEncodingException ex) {
        } catch (Exception ex) {
            // 打印日志
            getMsOnionLogger().error(msOnionMQService.getClass().getName(), ex,
                    String.format("sendMessageWithMQ # 异常发生MQ失败，idx=%s，操作动作action=%s，topic=%s，tags=%s，keys=%s，需要发送数据data=%s，pojo=%s",
                            idx, action.getValue(), topic, tags, keys, data, t));
        }
    }


    ////////////////////  Send MQ ## End  ////////////////////////

    ////////////////////  Setters 、 Getters ## Begin ////////////////////////

    /**
     * 获取发送MQ的线程池
     *
     * @return 返回发送MQ的线程池
     */
    public ExecutorService getSendMQThreadPool() {
        if (null == sendMQThreadPool) {
            sendMQThreadPool = Executors.newFixedThreadPool(sendMQThreadCount);
        }
        return sendMQThreadPool;
    }

    /**
     * 发送MQ的线程数量
     *
     * @return 返回发送MQ的线程数量
     */
    public int getSendMQThreadCount() {
        return sendMQThreadCount;
    }

    /**
     * RocketMQ 生产者扩展
     *
     * @return RocketMQ 生产者扩展
     */
    public MsOnionSimpleRocketMQProducerExt getMsOnionSimpleRocketMQProducerExt() {
        return msOnionSimpleRocketMQProducerExt;
    }

    ////////////////////  Setters 、 Getters ## End ////////////////////////

}
