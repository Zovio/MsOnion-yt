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

package cc.msonion.carambola.parent.ext.netty.client;


import cc.msonion.carambola.parent.ext.netty.exception.MsOnionNettyClientExceptionMsOnion;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyServerAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * @Title: MsOnionNettyClientDispatchHandler.java
 * @Package: cc.msonion.carambola.parent.ext.netty.client
 * @Description: Netty客户端调度处理器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月30日 下午1:43:54
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月30日 下午1:43:54
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：实现相关方法
 */

/**
 * @ClassName: MsOnionNettyClientDispatchHandler
 * @Description: Netty客户端调度处理器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月30日 下午1:43:54
 */
//@Component
//@Sharable
public abstract class MsOnionNettyClientDispatchHandler extends SimpleChannelInboundHandler<MsOnionNettyResponse> {

    /**
     * 超时
     */
    private final long timeout = 5;

    /**
     * responseMap
     */
    private final ConcurrentHashMap<String, BlockingQueue<MsOnionNettyResponse>> responseMap = new ConcurrentHashMap<String, BlockingQueue<MsOnionNettyResponse>>();

    // Netty 5.0+
//    @Override
//    public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
//        if (msg instanceof MsOnionNettyRequest) {
//            MsOnionNettyRequest request = (MsOnionNettyRequest) msg;
//            responseMap.putIfAbsent(request.getMessageId(), new LinkedBlockingQueue<MsOnionNettyResponse>(1));
//        }
//        super.write(ctx, msg, promise);
//    }


    /**
     * Netty 5.0+ ## 写入
     *
     * @param ctx     上下文
     * @param msg     消息
     * @param promise ChannelPromise实例对象
     * @throws Exception 异常
     */
    protected void writeForNetty5(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
        if (msg instanceof MsOnionNettyRequest) {
            MsOnionNettyRequest request = (MsOnionNettyRequest) msg;
            responseMap.putIfAbsent(request.getMessageId(), new LinkedBlockingQueue<MsOnionNettyResponse>(1));
        }
//        super.write(ctx, msg, promise);
    }

    // Netty 5.0+
//    @Override
//    protected void messageReceived(final ChannelHandlerContext ctx, final MsOnionNettyResponse response) throws Exception {
//        BlockingQueue<MsOnionNettyResponse> queue = responseMap.get(response.getMessageId());
//        queue.add(response);
//    }

    /**
     * Netty 5.0+
     *
     * @param ctx      上下文
     * @param response MsOnionNettyResponse实例对象
     * @throws Exception 异常
     */
    protected void messageReceivedForNetty5(final ChannelHandlerContext ctx, final MsOnionNettyResponse response) throws Exception {
        BlockingQueue<MsOnionNettyResponse> queue = responseMap.get(response.getMessageId());
        queue.add(response);
    }

//    /**
//     * Netty 4.0+ ## channelRead0，Netty 5.0+ ## messageReceived
//     * @param ctx ChannelHandlerContext 上下文
//     * @param response MsOnionNettyResponse实例对象
//     * @throws Exception 异常
//     */
//    // Netty 4.0+
//    @Override
//    protected void channelRead0(final ChannelHandlerContext ctx, final MsOnionNettyResponse response) throws Exception {
//        BlockingQueue<MsOnionNettyResponse> queue = responseMap.get(response.getMessageId());
//        queue.add(response);
//    }

    /**
     * Netty 4.0+ ## channelRead0，Netty 5.0+ ## messageReceived
     *
     * @param ctx      ChannelHandlerContext 上下文
     * @param response MsOnionNettyResponse实例对象
     * @throws Exception 异常
     */
    protected void channelRead0ForNetty4(final ChannelHandlerContext ctx, final MsOnionNettyResponse response) throws Exception {
        BlockingQueue<MsOnionNettyResponse> queue = responseMap.get(response.getMessageId());
        queue.add(response);
    }

    /**
     * 根据消息Id获取响应 MsOnionNettyResponse实例对象
     *
     * @param messageId 消息id
     * @return
     * @throws MsOnionNettyClientExceptionMsOnion Netty客户端异常
     */
    public MsOnionNettyResponse getResponse(final String messageId) throws MsOnionNettyClientExceptionMsOnion {
        MsOnionNettyResponse result;
        responseMap.putIfAbsent(messageId, new LinkedBlockingQueue<MsOnionNettyResponse>(1));
        try {
            result = responseMap.get(messageId).take();
            if (null == result) {
                result = getSystemMessage();
            }
        } catch (final Exception ex) {
            throw new MsOnionNettyClientExceptionMsOnion(ex);
        } finally {
            responseMap.remove(messageId);
        }
        return result;
    }

    /**
     * 获取系统异常
     *
     * @return 返回系统异常
     * @throws MsOnionNettyClientExceptionMsOnion Netty客户端异常
     */
    private MsOnionNettyResponse getSystemMessage() throws MsOnionNettyClientExceptionMsOnion {
        try {
            return responseMap.get(MsOnionNettyServerAdapter.SYSTEM_MESSAGE_ID).poll(timeout, SECONDS);
        } catch (final InterruptedException ex) {
            throw new MsOnionNettyClientExceptionMsOnion(ex);
        }
    }
}
