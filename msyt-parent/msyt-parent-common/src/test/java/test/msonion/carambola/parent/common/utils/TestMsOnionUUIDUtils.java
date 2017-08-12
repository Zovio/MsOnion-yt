package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionUUIDUtils;
import io.netty.util.internal.ThreadLocalRandom;
import org.apache.shiro.session.mgt.eis.RandomSessionIdGenerator;
import org.junit.Test;

import java.util.Date;
import java.util.stream.LongStream;

/**
 * Created by HeroCao on 2017/4/10.
 */
public class TestMsOnionUUIDUtils {

    @Test
    public void test01() {


        String randomUUID = MsOnionUUIDUtils.randomUUID();

        String randomUUIDNotLine = MsOnionUUIDUtils.randomUUIDNotLine();

        String randomUUIDWithLast16 = MsOnionUUIDUtils.randomUUIDWithLast16();

        String randomUUIDForVersion = MsOnionUUIDUtils.randomUUIDForVersion();


        System.out.println("randomUUID=" + randomUUID);

        System.out.println("randomUUIDNotLine=" + randomUUIDNotLine);


        System.out.println("randomUUIDWithLast16=" + randomUUIDWithLast16);


        System.out.println("randomUUIDForVersion=" + randomUUIDForVersion);
    }


    public static String getByUUId() throws MsOnionException {
        int machineId = 1; // 最大支持1-9个集群机器部署
//        int hashCodeV = UUID.randomUUID().toString().hashCode();



        int hashCodeV = MsOnionUUIDUtils.randomUUID().toString().hashCode();

        String strDate = MsOnionDateUtils.formatYyyyMMddHHmmssSSSUnity(new Date());

        long nanoTime = System.nanoTime();

        long currentTimeMillis = System.currentTimeMillis();

        System.out.println("nanoTime=" + nanoTime);

        System.out.println("currentTimeMillis=" + currentTimeMillis);


        System.out.println("strDate=" + strDate);

        System.out.println("strDate.hashCode()=" + strDate.hashCode());

        System.out.println("hashCodeV=" + hashCodeV);

        if(hashCodeV < 0) { // 有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%012d", hashCodeV);
    }


    @Test
    public void testUUID02() throws MsOnionException {

//        System.out.println("getOrderIdByUUId=" + getOrderIdByUUId());

        long initialSeedUniquifier = ThreadLocalRandom.getInitialSeedUniquifier();

        LongStream longs = java.util.concurrent.ThreadLocalRandom.current().longs();


        System.out.println("结束了initialSeedUniquifier=" + initialSeedUniquifier);

        System.out.println("结束了initialSeedUniquifier.length()=" + String.valueOf(initialSeedUniquifier).length());


        for (int i = 0; i < 100; i++) {

//            System.out.println("getByUUId=" + getByUUId());

            long initialSeedUniquifier2 = ThreadLocalRandom.getInitialSeedUniquifier();


            System.out.println("initialSeedUniquifier2=" + initialSeedUniquifier2);

            System.out.println("结束了 initialSeedUniquifier2.length()=" + String.valueOf(initialSeedUniquifier2).length());


        }

        System.out.println("结束了！！！");
    }

    @Test
    public void testRandom01() {

        for (int i = 0; i < 100; i++) {

            int random = MsOnionRandomUtils.getRandom(1, Integer.MAX_VALUE);

            System.out.println("random=" + random);


        }

        //

    }



}
