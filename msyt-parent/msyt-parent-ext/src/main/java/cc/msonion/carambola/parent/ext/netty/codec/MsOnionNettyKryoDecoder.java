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

package cc.msonion.carambola.parent.ext.netty.codec;

import cc.msonion.carambola.parent.common.utils.MsOnionPropertiesUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionSystemUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Value;

/**
 * Netty序列化Kryo方式解码
 *
 * @Title: MsOnionNettyKryoDecoder.java
 * @Package: cc.msonion.carambola.parent.ext.netty.codec
 * @Description: Netty序列化Kryo方式解码
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
 * Netty序列化Kryo方式解码
 *
 * @ClassName: MsOnionNettyKryoDecoder
 * @Description: Netty序列化Kryo方式解码
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年4月30日 下午1:48:16
 */
public class MsOnionNettyKryoDecoder extends LengthFieldBasedFrameDecoder {

    /**
     * maxFrameLength  10485760 ，是构造方法中使用，不能通过注入，因为当前对象还没有创建
     */
//    @Value("${netty.codec.maxFrameLength}")
    private static int maxFrameLength;

    /**
     * Properties，这里还没有考虑环境：开发环境、测试环境、生产环境、预发布环境，
     * /properties/config-netty.properties
     */
    private static final String PROPS_NAME = "*/properties/config-netty.properties";

    /**
     * maxFrameLength  25MB
     * ## https://calc.itzmx.com/
     ## 10*1024*1024=10485760(10MB) ,  20*1024*1024=20971520(20MB)
     ## 15*1024*1024=15728640(15MB) ,  25*1024*1024=26214400(25MB)
     */
    private static final int DEFAULT_MAX_FRAME_LENGTH = 26214400;

    /*
     int maxFrameLength,
            int lengthFieldOffset, int lengthFieldLength,
            int lengthAdjustment, int initialBytesToStrip)
     */

    /**
     * lengthFieldOffset
     */
    private static final int DEFAULT_LENGTH_FIELD_OFFSET = 0;

    /**
     * lengthFieldLength
     */
    private static final int DEFAULT_LENGTH_FIELD_LENGTH = 4;

    /**
     * lengthAdjustment
     */
    private static final int DEFAULT_LENGTH_ADJUSTMENT = 0;

    /**
     * initialBytesToStrip
     */
    private static final int DEFAULT_INITIAL_BYTES_TO_STRIP = 4;

    /**
     * kryoPool
     */
    private final MsOnionNettyKryoPool kryoPool;

    public MsOnionNettyKryoDecoder(final MsOnionNettyKryoPool kryoPool) {
//        super(DEFAULT_MAX_FRAME_LENGTH, 0, 4, 0, 4);

        super(DEFAULT_MAX_FRAME_LENGTH, DEFAULT_LENGTH_FIELD_OFFSET, DEFAULT_LENGTH_FIELD_LENGTH, DEFAULT_LENGTH_ADJUSTMENT, DEFAULT_INITIAL_BYTES_TO_STRIP);
        this.kryoPool = kryoPool;
    }

    @Override
    protected Object decode(final ChannelHandlerContext ctx, final ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (null == frame) {
            return null;
        }
        try {
            return kryoPool.decode(frame);
        } finally {
            if (null != frame) {
                frame.release();
            }
        }
    }

    //////////////////// static value  ## Begin ///////////////////////////

    // 后续需要根据 环境来加载配置文件，这里需要考虑环境：开发环境、测试环境、生产环境、预发布环境
    // /resources/dev/properties/config-netty.properties
    // /resources/test/properties/config-netty.properties
    // 如果提供全局的，不方便管理！！！
//    protected static int getMaxFrameLength() {
//        if (maxFrameLength <= 0) {
//            maxFrameLength = MsOnionPropertiesUtils.loadProperties(PROPS_NAME)
//        }
//    }

    //////////////////// static value  ## End ///////////////////////////
}
