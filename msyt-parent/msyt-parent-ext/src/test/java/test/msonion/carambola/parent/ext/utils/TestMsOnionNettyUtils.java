package test.msonion.carambola.parent.ext.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionFixedLengthUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.ext.utils.netty.MsOnionNettyUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by HeroCao on 2017/6/3.
 */
public class TestMsOnionNettyUtils {

    /**
     * 上传文件，MessageId最小随机值
     */
    private static final int MESSAGE_ID_RANDOM_MIN_VALUE = 0;

    /**
     * 上传文件，MessageId最大随机值
     */
    private static final int MESSAGE_ID_RANDOM_MAX_VALUE = 99;

    /**
     * MessageID随机位数：2
     */
    private static final int MESSAGE_ID_RANDOM_FIX_LENGTH = 2;

    @Test
    public void testGenerateMessageId() throws MsOnionException {

        for (int i = 100; i < 200; i++) {

//            long generateMessageId = MsOnionNettyUtils.generateMessageId();

            long generateMessageId = generateMessageId();

            System.out.println("i=" + i + "，generateMessageId=" + generateMessageId + "，generateMessageId.length=" + String.valueOf(generateMessageId).length());


        }



    }


    /**
     * 生成MessageId，可能出现重复，分布式架构中，不过后台管理系统，商品操作，出现同一个时间点并发概率很低
     *
     * @return 返回MessageId，例如：2017060321285928668
     * @throws MsOnionException 异常
     */
    public static long generateMessageId() throws MsOnionException {
        try {
            // 20170603212859298
            String time = MsOnionDateUtils.formatYyyyMMddHHmmssSSSUnity(new Date());
            // 随机
            int random = MsOnionRandomUtils.getRandom(MESSAGE_ID_RANDOM_MIN_VALUE, MESSAGE_ID_RANDOM_MAX_VALUE);
            String value = String.valueOf(random);
            // 固定2位
            if (value.length() < MESSAGE_ID_RANDOM_FIX_LENGTH) {
                value = MsOnionFixedLengthUtils.fixLength(random, MESSAGE_ID_RANDOM_FIX_LENGTH);
            }
            value = String.format("%s%s", time, value);
            return Long.parseLong(value);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }
}
