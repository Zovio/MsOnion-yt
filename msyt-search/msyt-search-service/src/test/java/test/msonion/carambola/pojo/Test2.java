package test.msonion.carambola.pojo;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by HeroCao on 2017/7/13.
 */
public class Test2 {


    @Test
    public void test01() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        System.out.println("sdf.format(new Date()=" + sdf.format(new Date()));



    }

}
