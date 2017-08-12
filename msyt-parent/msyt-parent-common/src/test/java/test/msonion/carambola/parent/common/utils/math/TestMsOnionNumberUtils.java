package test.msonion.carambola.parent.common.utils.math;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.math.MsOnionNumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeroCao on 2017/5/28.
 */
public class TestMsOnionNumberUtils {

    @Test
    public void testToIntPrice01() {


        String str1 = "100";

        int price1 = MsOnionNumberUtils.toIntForPrice(str1);

        System.out.println("price1=" + price1);


        String str2 = "190.00";

        int price2 = MsOnionNumberUtils.toIntForPrice(str2);

        System.out.println("price2=" + price2);


        String str3 = "188.85";

        int price3 = MsOnionNumberUtils.toIntForPrice(str3);

        System.out.println("price3=" + price3);

        /**
         * price1=10000
         price2=19000
         price3=18885
         */

    }

    @Test
    public void testToLongPrice01() {


        String str1 = "100";

        long price1 = MsOnionNumberUtils.toLongForPrice(str1);

        System.out.println("price1=" + price1);


        String str2 = "190.00";

        long price2 = MsOnionNumberUtils.toLongForPrice(str2);

        System.out.println("price2=" + price2);


        String str3 = "188.85";

        long price3 = MsOnionNumberUtils.toLongForPrice(str3);

        System.out.println("price3=" + price3);

        /**
         * price1=10000
         price2=19000
         price3=18885
         */

    }

    @Test
    public void testFormatCurrency() throws MsOnionException {
        Integer str1 = 10001001;

        String price1 = MsOnionNumberUtils.formatCurrency(str1, 2);
        System.out.println("price1=" + price1);
    }

    @Test
    public void testNumber02() {

//        String str1 = "198.58";

        String str1 = "1.98.58";



        try {
            int price1 = MsOnionNumberUtils.toIntForPrice(str1);

            System.out.println("price1=" + price1);

            Double d1 = Double.parseDouble(str1);

//            Float , double ,

            System.out.println("d1=" + d1);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

//       ThreadPoolExcutor


//        ExcutorS
    }

    @Test
    public void test05() {

        String str1 = "2.11";

        int price1 = MsOnionNumberUtils.toIntForPrice(str1);

        System.out.println("price1=" + price1);


        String str2 = "2.01";

        int price2 = MsOnionNumberUtils.toIntForPrice(str2);

        System.out.println("price2=" + price2);


        String str3 = "2.00";

        int price3 = MsOnionNumberUtils.toIntForPrice(str3);

        System.out.println("price3=" + price3);

    }


    @Test
    public void test06() {

        String str1 = "2.11";

        int price1 = MsOnionNumberUtils.toIntForPrice(str1);

//        System.out.println("price1=" + price1);

        System.out.println(String.format("小数点值：str1=%s，分为单位：price1=%s", str1, price1));

        String str1_1 = "3.11";

        int price1_1 = MsOnionNumberUtils.toIntForPrice(str1_1);

//        System.out.println("price1=" + price1);

        System.out.println(String.format("小数点值：str1_1=%s，分为单位：price1_1=%s", str1_1, price1_1));


        String str1_2 = "2.10";

        int price1_2 = MsOnionNumberUtils.toIntForPrice(str1_2);

//        System.out.println("price1=" + price1);

        System.out.println(String.format("小数点值：str1_2=%s，分为单位：price1_2=%s", str1_2, price1_2));

        String str2 = "2.01";

        int price2 = MsOnionNumberUtils.toIntForPrice(str2);

//        System.out.println("price2=" + price2);

        System.out.println(String.format("小数点值：str2=%s，分为单位：price2=%s", str2, price2));

        String str3 = "2.00";

        int price3 = MsOnionNumberUtils.toIntForPrice(str3);

//        System.out.println("price3=" + price3);

        System.out.println(String.format("小数点值：str3=%s，分为单位：price3=%s", str3, price3));
    }

    @Test
    public void test08() {

        /*
        C:\Java\jdk1.8.0_65

        有BUG：


        小数点值：str=2.08，==》分为单位：price=208

        ===============  以下有BUG

小数点值：str=2.09，==》分为单位：price=208
小数点值：str=2.10，==》分为单位：price=209
小数点值：str=2.11，==》分为单位：price=210
小数点值：str=2.12，==》分为单位：price=211

===============  以上有BUG

小数点值：str=2.13，==》分为单位：price=213
小数点值：str=2.14，==》分为单位：price=214
小数点值：str=2.15，==》分为单位：price=215
小数点值：str=2.16，==》分为单位：price=216
小数点值：str=2.17，==》分为单位：price=217
小数点值：str=2.18，==》分为单位：price=218
小数点值：str=2.19，==》分为单位：price=219
小数点值：str=2.20，==》分为单位：price=220
小数点值：str=2.21，==》分为单位：price=221
小数点值：str=2.22，==》分为单位：price=222




         */
        String[] strs = {"2.08", "2.09", "2.10", "2.11", "2.12", "2.13", "2.14",
                "2.15", "2.16", "2.17", "2.18", "2.19", "2.20", "2.21", "2.22"};

        int price = 0;
        for (String str : strs) {

            price = MsOnionNumberUtils.toIntForPrice(str);

            System.out.println(String.format("小数点值：str=%s，==》分为单位：price=%s", str, price));
        }


        System.out.println("The End !!!");

    }

    @Test
    public void test09() {


//        String[] strs = {"2.08", "2.09", "2.10", "2.11", "2.12", "2.13", "2.14",
//                "2.15", "2.16", "2.17", "2.18", "2.19", "2.20", "2.21", "2.22"};


        String[] strs = {"12.08", "12.09", "12.10", "12.11", "12.12", "12.13", "12.14",
                "12.15", "12.16", "12.17", "12.18", "12.19", "12.20", "12.21", "12.22"};

        List<String> data = new ArrayList<>();

        int price = 0;
        for (String str : strs) {

            price = MsOnionNumberUtils.toIntForPrice(str);

            System.out.println(String.format("小数点值：str=%s，==》分为单位：price=%s", str, price));
        }


        System.out.println("The End !!!");

    }

}
