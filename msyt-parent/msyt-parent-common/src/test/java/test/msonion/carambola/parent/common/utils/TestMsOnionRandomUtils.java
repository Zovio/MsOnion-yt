package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.utils.MsOnionRandomUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/4/9.
 */
public class TestMsOnionRandomUtils {

    @Test
    public void test01() {

        String randomNumbersAndLetters = MsOnionRandomUtils.getRandomNumbersAndLetters(2);


        String randomNumbersAndLetters2 = MsOnionRandomUtils.getRandomNumbersAndLetters(5);


        String randomNumbersAndLetters3 = MsOnionRandomUtils.getRandomNumbersAndLetters(30);


        System.out.println("randomNumbersAndLetters=" + randomNumbersAndLetters);


        System.out.println("randomNumbersAndLetters2=" + randomNumbersAndLetters2);

        System.out.println("randomNumbersAndLetters3=" + randomNumbersAndLetters3);





    }

    @Test
    public void test02() {

        String randomNumbers = MsOnionRandomUtils.getRandomNumbers(3);

        String randomNumbers2 = MsOnionRandomUtils.getRandomNumbers(5);


        String randomNumbers3 = MsOnionRandomUtils.getRandomNumbers(10);

        System.out.println("randomNumbers=" + randomNumbers);

        System.out.println("randomNumbers2=" + randomNumbers2);

        System.out.println("randomNumbers3=" + randomNumbers3);



    }

    @Test
    public void test03() {

        String randomNumbers = MsOnionRandomUtils.getRandomNumbers(3);

        String randomNumbers2 = MsOnionRandomUtils.getRandomNumbers(5);


        String randomNumbers3 = MsOnionRandomUtils.getRandomNumbers(10);

        System.out.println("randomNumbers=" + randomNumbers);

        System.out.println("randomNumbers2=" + randomNumbers2);

        System.out.println("randomNumbers3=" + randomNumbers3);


        for (int i = 0; i < 100; i++) {
            String randomNumbers11 = MsOnionRandomUtils.getRandomNumbers(5);

            System.out.println("i=" + i + ", randomNumbers11=" + randomNumbers11);

        }

    }

}
