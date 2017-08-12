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

package cc.msonion.carambola.parent.ext.netty.server;

import cc.msonion.carambola.parent.common.exception.MsOnionRuntimeException;
import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.ext.netty.exception.MsOnionNettyServerExceptionMsOnion;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyServerAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * Netty服务端调度处理器
 *
 * @Title: MsOnionNettyServerDispatchHandler.java
 * @Package: cc.msonion.carambola.parent.ext.netty.server
 * @Description: Netty服务端调度处理器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月30日 下午1:48:16
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年4月30日 下午1:48:16
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：创建
 */

/**
 * Netty服务端调度处理器
 * <p>由于Netty 5.0+ 和 Netty 4.0+ 冲突了，因此改成抽象 abstract ，由子类去实现</p>
 *
 * @ClassName: MsOnionNettyServerDispatchHandler
 * @Description: Netty服务端调度处理器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月30日 下午1:48:16
 */
//@Component
//@ChannelHandler.Sharable
public abstract class MsOnionNettyServerDispatchHandler extends SimpleChannelInboundHandler<MsOnionNettyRequest> implements ApplicationContextAware {


    /**
     * ApplicationContext
     */
    private ApplicationContext applicationContext;

//    // Netty 5.0+
//    /**
//     * 接收信息
//     * @param ctx 上下文
//     * @param request 请求
//     */
//    @Override
//    protected void messageReceived(final ChannelHandlerContext ctx, final MsOnionNettyRequest request) {
//        try {
//            Object returnValue = execute(request);
//            ctx.writeAndFlush(new MsOnionNettyResponse(request.getMessageId(), returnValue));
//        } catch (Exception ex) {
//            // Runtime Exception
//            throw new MsOnionRuntimeException(ex);
//        }
//    }

    /**
     * 接收信息，Netty 5.0+
     *
     * @param ctx     上下文
     * @param request 请求
     */
    protected void messageReceivedForNetty5(final ChannelHandlerContext ctx, final MsOnionNettyRequest request) {
        try {
            Object returnValue = execute(request);
            ctx.writeAndFlush(new MsOnionNettyResponse(request.getMessageId(), returnValue));
        } catch (Exception ex) {
            // Runtime Exception
            throw new MsOnionRuntimeException(ex);
        }
    }

//    // Netty 4.0+
//    /**
//     * 接收信息
//     * @param ctx 上下文
//     * @param request 请求
//     */
//    @Override
//    protected void channelRead0(final ChannelHandlerContext ctx, final MsOnionNettyRequest request) throws Exception {
//        try {
//            Object returnValue = execute(request);
//            ctx.writeAndFlush(new MsOnionNettyResponse(request.getMessageId(), returnValue));
//        } catch (Exception ex) {
//            // Runtime Exception
//            throw new MsOnionRuntimeException(ex);
//        }
//    }

    /**
     * 接收信息 , Netty 4.0+
     *
     * @param ctx     上下文
     * @param request 请求
     */
    protected void channelRead0ForNetty4(final ChannelHandlerContext ctx, final MsOnionNettyRequest request) throws Exception {
        try {
            Object returnValue = execute(request);
            ctx.writeAndFlush(new MsOnionNettyResponse(request.getMessageId(), returnValue));
        } catch (Exception ex) {
            // Runtime Exception
            throw new MsOnionRuntimeException(ex);
        }
    }

    /**
     * 执行
     *
     * @param request MsOnionNettyRequest实例化对象
     * @return 返回 Object
     */
    private Object execute(final MsOnionNettyRequest request) {
        Object result;
        try {
            Object targetInstance = applicationContext.getBean(request.getTargetClass());
            Method method = targetInstance.getClass().getMethod(request.getMethod(), getParameterTypes(request.getParameters()));
            result = method.invoke(targetInstance, request.getParameters());
        } catch (final Exception ex) {
            throw new MsOnionNettyServerExceptionMsOnion(request.getMessageId(), ex);
        }
        return result;
    }

    /**
     * 获取参数类型
     *
     * @param parameters 参数
     * @return 返回参数类型
     */
    private Class<?>[] getParameterTypes(final Object[] parameters) {
//        Class<?>[] result = new Class<?>[parameters.length];
//        int i = 0;
//        for (Object each : parameters) {
//            result[i] = each.getClass();
//            i++;
//        }
//        return result;

        return MsOnionReflectionUtils.getParameterTypes(parameters);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        MsOnionNettyResponse response = null;
        cause.printStackTrace();
        if (cause instanceof MsOnionNettyServerExceptionMsOnion) {
            response = new MsOnionNettyResponse(((MsOnionNettyServerExceptionMsOnion) cause).getMessageId(), cause);
        } else {
            response = new MsOnionNettyResponse(MsOnionNettyServerAdapter.SYSTEM_MESSAGE_ID + "", cause);
        }
        ctx.writeAndFlush(response);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
