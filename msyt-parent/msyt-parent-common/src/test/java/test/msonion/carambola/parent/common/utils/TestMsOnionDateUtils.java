package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by HeroCao on 2017/4/9.
 */
public class TestMsOnionDateUtils {

    @Test
    public void test01() throws MsOnionException {


        String date1 = MsOnionDateUtils.formatYyyyMMChinese(new Date());

        // date1=2017年04月
        System.out.println("date1=" + date1);

//        Date date2 = MsOnionDateUtils.parseDateWithYyyy_MM("2017-14");

//        Date date2 = MsOnionDateUtils.parseDateWithYyyy_MM("2017年04月");


//        Date date2 = MsOnionDateUtils.parseDateWithYyyy_MM("2017/04");
//
//        System.out.println("date2=" + date2);

//        new SimpleDateFormat("").format(date)

//        new SimpleDateFormat().parse()

    }

    @Test
    public void testForChineseDate1() throws MsOnionException {

        // 测试中文格式日期

        String strDate1 = MsOnionDateUtils.formatYyyyMMddHHmmssChinese(new Date());

        System.out.println("strDate1=" + strDate1);

        String strDate2 = MsOnionDateUtils.formatYyyyMMddHHmmChinese(new Date());

        System.out.println("strDate2=" + strDate2);


        String strDate3 = MsOnionDateUtils.formatYyyyMMddHHChinese(new Date());

        System.out.println("strDate3=" + strDate3);

        String strDate4 = MsOnionDateUtils.formatYyyyMMddChinese(new Date());

        System.out.println("strDate4=" + strDate4);


        String strDate5 = MsOnionDateUtils.formatYyyyMMChinese(new Date());

        System.out.println("strDate5=" + strDate5);

        String strDate6 = MsOnionDateUtils.formatYyyyChinese(new Date());

        System.out.println("strDate6=" + strDate6);


        /**
         * strDate1=2017年04月24日 15:05:02
         strDate2=2017年04月24日 15:05
         strDate3=2017年04月24日 15
         strDate4=2017年04月24日
         strDate5=2017年04月
         strDate6=2017年
         */


    }


    @Test
    public void testForFormatDate1() throws MsOnionException {

        // 测试中文格式日期

        String strDate1 = MsOnionDateUtils.formatYyyyMMddHHmmss(new Date());

        System.out.println("strDate1=" + strDate1);

        String strDate2 = MsOnionDateUtils.formatYyyyMMddHHmm(new Date());

        System.out.println("strDate2=" + strDate2);


        String strDate3 = MsOnionDateUtils.formatYyyyMMddHH(new Date());

        System.out.println("strDate3=" + strDate3);

        String strDate4 = MsOnionDateUtils.formatYyyyMMdd(new Date());

        System.out.println("strDate4=" + strDate4);


        String strDate5 = MsOnionDateUtils.formatYyyyMM(new Date());

        System.out.println("strDate5=" + strDate5);

        String strDate6 = MsOnionDateUtils.formatYyyy(new Date());

        System.out.println("strDate6=" + strDate6);

        String strDate7 = MsOnionDateUtils.formatHHmmss(new Date());

        System.out.println("strDate7=" + strDate7);

        String strDate8 = MsOnionDateUtils.formatHHmm(new Date());

        System.out.println("strDate8=" + strDate8);

        String strDate9 = MsOnionDateUtils.formatHH(new Date());

        System.out.println("strDate9=" + strDate9);

        String strDate10 = MsOnionDateUtils.formatMM(new Date());

        System.out.println("strDate10=" + strDate10);


        /**
         *
         *strDate1=2017-04-24 15:56:21
         strDate2=2017-04-24 15:56
         strDate3=2017-04-24 15
         strDate4=2017-04-24
         strDate5=2017-04
         strDate6=2017
         strDate7=15:56:21
         strDate8=15:56
         strDate9=15
         strDate10=56

         */


    }

    @Test
    public void testParseWithChinese1() throws MsOnionException {

        // parseWithYyyyMMddHHmmssChinese

//        String strDate1 = "2017-04-24 15:56:21";

        String strDate2 = "2017年04月24日 15:05:02";

        // 报错
//       Date date1 = MsOnionDateUtils.parseWithYyyyMMddHHmmssChinese(strDate1);

        // 报错： Unparseable date: "2017年04月24日 15:05:02"
        // OK
        Date date1 = MsOnionDateUtils.parseWithYyyyMMddHHmmssChinese(strDate2);

        System.out.println("date1=" + date1);

//        System.out.println("strDate10=" + strDate10);


    }

    @Test
    public void testParseWithChinese2() throws MsOnionException {

        // parseWithYyyyMMddHHmmssChinese

//        String strDate1 = "2017-04-24 15:56:21";

        String strDate1 = "2017年04月24日 15:05:02";

//        String strDate2 = "2017年04月24日";

        // 报错
        Date date1 = MsOnionDateUtils.parseWithYyyyMMddHHmmssChinese(strDate1);

        System.out.println("date1=" + date1);

        // 报错： Unparseable date: "2017年04月24日 15:05:02"

        String strDate2 = "2017年04月24日 15:05";

        // OK
        Date date2 = MsOnionDateUtils.parseWithYyyyMMddHHmmChinese(strDate2);

        System.out.println("date2=" + date2);

        String strDate3 = "2017年04月24日 15";

        // OK
        Date date3 = MsOnionDateUtils.parseWithYyyyMMddHHChinese(strDate3);

        System.out.println("date3=" + date3);

        String strDate4 = "2017年04月24日 ";  // 有空格

        // OK
        Date date4 = MsOnionDateUtils.parseWithYyyyMMddChinese(strDate4);

        System.out.println("date4=" + date4);


        String strDate5 = "2017年04月 ";  // 有空格

        // OK
        Date date5 = MsOnionDateUtils.parseWithYyyyMMChinese(strDate5);

        System.out.println("date5=" + date5);

        String strDate6 = "2017年 ";  // 有空格

        // OK
//        Date date6 = MsOnionDateUtils.parseWithYyyyChinese(strDate6);
//
//        System.out.println("date6=" + date6);

//        System.out.println("strDate10=" + strDate10);

        /*
        date1=Mon Apr 24 15:05:02 CST 2017
date2=Mon Apr 24 15:05:00 CST 2017
date3=Mon Apr 24 15:00:00 CST 2017
date4=Mon Apr 24 00:00:00 CST 2017
date5=Sat Apr 01 00:00:00 CST 2017
date6=Sun Jan 01 00:00:00 CST 2017

         */


    }

    @Test
    public void testParseWithChinese3() throws MsOnionException {

        // parseWithYyyyMMddHHmmssChinese

//        String strDate1 = "2017-04-24 15:56:21";

//        String strDate2 = "2017年04月24日 15:05:02";

        String strDate2 = "2017年04月24日";

        // 报错
//       Date date1 = MsOnionDateUtils.parseWithYyyyMMddHHmmssChinese(strDate1);

        // 报错： Unparseable date: "2017年04月24日 15:05:02"
        // OK
        Date date1 = MsOnionDateUtils.parseWithYyyyMMddChinese(strDate2);

        System.out.println("date1=" + date1);

//        System.out.println("strDate10=" + strDate10);


    }

    @Test
    public void testToStringDate() throws MsOnionException {

        long timeMillis = System.currentTimeMillis();

        String date1 = MsOnionDateUtils.toYyyyMMddHHmmssChinese(timeMillis);

        System.out.println("testToStringDate # date1=" + date1);

        String date2 = MsOnionDateUtils.toYyyyMMddHHmmChinese(timeMillis);

        System.out.println("testToStringDate # date2=" + date2);

        String date3 = MsOnionDateUtils.toYyyyMMddHHChinese(timeMillis);

        System.out.println("testToStringDate # date3=" + date3);


        String date4 = MsOnionDateUtils.toYyyyMMddChinese(timeMillis);

        System.out.println("testToStringDate # date4=" + date4);


        String date5 = MsOnionDateUtils.toYyyyMMChinese(timeMillis);

        System.out.println("testToStringDate # date5=" + date5);

        String date6 = MsOnionDateUtils.toYyyyChinese(timeMillis);

        System.out.println("testToStringDate # date6=" + date6);

        /*
        testToStringDate # date1=2017年04月24日 21:33:54
testToStringDate # date2=2017年04月24日 21:33
testToStringDate # date3=2017年04月24日 21
testToStringDate # date4=2017年04月24日
testToStringDate # date5=2017年04月
testToStringDate # date6=2017年
         */

    }

    @Test
    public void testToStringDate2() throws MsOnionException {

        long timeMillis = System.currentTimeMillis();

        String date1 = MsOnionDateUtils.toYyyyMMddHHmmss(timeMillis);

        System.out.println("testToStringDate # date1=" + date1);

        String date2 = MsOnionDateUtils.toYyyyMMddHHmm(timeMillis);

        System.out.println("testToStringDate # date2=" + date2);

        String date3 = MsOnionDateUtils.toYyyyMMddHH(timeMillis);

        System.out.println("testToStringDate # date3=" + date3);


        String date4 = MsOnionDateUtils.toYyyyMMdd(timeMillis);

        System.out.println("testToStringDate # date4=" + date4);


        String date5 = MsOnionDateUtils.toYyyyMM(timeMillis);

        System.out.println("testToStringDate # date5=" + date5);

        String date6 = MsOnionDateUtils.toYyyy(timeMillis);

        System.out.println("testToStringDate # date6=" + date6);

        /*
        testToStringDate # date1=2017-04-24 21:56:16
testToStringDate # date2=2017-04-24 21:56
testToStringDate # date3=2017-04-24 21
testToStringDate # date4=2017-04-24
testToStringDate # date5=2017-04
testToStringDate # date6=2017

         */

    }

    @Test
    public void test10() throws MsOnionException {

        // OK
        long timeMillis = System.currentTimeMillis();


        // 有问题 ！！！
//        long timeMillis = System.nanoTime();


        String date0 = MsOnionDateUtils.toYyyyMMddHHmmssSSSUnity(timeMillis);

        System.out.println("testToStringDate # date0=" + date0);

        String date1 = MsOnionDateUtils.toYyyyMMddHHmmssUnity(timeMillis);

        System.out.println("testToStringDate # date1=" + date1);

        String date2 = MsOnionDateUtils.toYyyyMMddHHmmUnity(timeMillis);

        System.out.println("testToStringDate # date2=" + date2);

        String date3 = MsOnionDateUtils.toYyyyMMddHHUnity(timeMillis);

        System.out.println("testToStringDate # date3=" + date3);


        String date4 = MsOnionDateUtils.toYyyyMMddUnity(timeMillis);

        System.out.println("testToStringDate # date4=" + date4);


        String date5 = MsOnionDateUtils.toYyyyMMUnity(timeMillis);

        System.out.println("testToStringDate # date5=" + date5);

        String date6 = MsOnionDateUtils.toYyyy(timeMillis);

        System.out.println("testToStringDate # date6=" + date6);

        /*

        testToStringDate # date0=20170501201811301
testToStringDate # date1=20170501201811
testToStringDate # date2=201705012018
testToStringDate # date3=2017050120
testToStringDate # date4=20170501
testToStringDate # date5=201705
testToStringDate # date6=2017

         */

    }

    @Test
    public void testGetNowDate() throws MsOnionException {

        Date nowDate = MsOnionDateUtils.getNowDate();


        System.out.println("当前日期时间 # nowDate=" + nowDate);


        Date date1 = new Date();

        System.out.println("当前日期时间 # date1=" + date1);

    }

    @Test
    public void testGetRandomDate01() throws MsOnionException {



        System.out.println("testGetRandomDate01 # nowDate=" + new Date());

        Date date = MsOnionDateUtils.randomDateForVerificationCode();


        System.out.println("testGetRandomDate01 # date=" + date);

        String str = MsOnionDateUtils.formatYyyyMMddHHmmss(date);

        System.out.println("testGetRandomDate01 # str=" + str);


    }


    @Test
    public void testGetRandomDate02() throws MsOnionException {



        System.out.println("testGetRandomDate02 # nowDate=" + new Date());

        for (int i = 0; i < 20; i++) {

            Date date = MsOnionDateUtils.randomDateForVerificationCode();


            System.out.println("testGetRandomDate02 # date=" + date);

            String str = MsOnionDateUtils.formatYyyyMMddHHmmss(date);

            System.out.println("testGetRandomDate02 # str=" + str);


        }

        System.out.println("testGetRandomDate02 # the End !!!");
    }


}
