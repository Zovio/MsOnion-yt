package test.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityBase64Utils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/4/12.
 */
public class TestMsOnionSecurityBase64Utils {

    @Test
    public void test01() throws MsOnionException {

        String str = "this is a example 洋桃";




        String encode = MsOnionSecurityBase64Utils.encode(str.getBytes());


        System.out.println("encode=" + encode);

        String decode = MsOnionSecurityBase64Utils.decode(encode);

        System.out.println("encode=" + decode);


    }

    @Test
    public void test02() throws MsOnionException {

        String data = "\"code\":\"1014000520\",\n" +
                "            \"countryCode\":\"JP\",\n" +
                "            \"countryName\":\"日本\",\n" +
                "            \"freePrice\":48.8,\n" +
                "            \"marketPrice\":104,\n" +
                "            \"menuId\":132,\n" +
                "            \"name\":\"Mandom曼丹 Bifesta温和即净卸妆水 145ml\",\n" +
                "            \"picUrl\":\"product/image/20150811/20150811094422795_400.jpg\"";


            System.out.println("data=" + data);


        String encode = MsOnionSecurityBase64Utils.encode(data);

        System.out.println("加密之后 # encode=" + encode);

        String decode = MsOnionSecurityBase64Utils.decode(encode);

        System.out.println("解密之后 # decode=" + decode);


        // 加密之后 # encode=ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2Rl IjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAog ICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFy a2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAg ICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z 5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InBy b2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpw ZyI=


        /**
         * http://tool.oschina.net/encrypt?type=3
         * 结果： 去掉空格
         *
         * ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2RlIjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAogICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFya2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAgICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InByb2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpwZyI=
         *
         */





    }


    @Test
    public void test03() throws MsOnionException {

        // 加密之后 # encode=ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2Rl IjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAog ICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFy a2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAg ICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z 5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InBy b2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpw ZyI=


        /**
         *
         * ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2RlIjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAogICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFya2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAgICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InByb2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpwZyI=
         *
         */

        String data = "ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2RlIjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAogICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFya2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAgICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InByb2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpwZyI=";


        String decode = MsOnionSecurityBase64Utils.decode(data);

        System.out.println("解密之后 # decode=" + decode);


    }


    @Test
    public void test05() throws MsOnionException {

        /**
         * {"code":"1014000520",
         "countryCode":"JP",
         "countryName":"日本",
         "freePrice":48.8,
         "marketPrice":104,
         "menuId":132,
         "name":"Mandom曼丹 Bifesta温和即净卸妆水 145ml",
         "picUrl":"product/image/20150811/20150811094422795_400.jpg"}
         *
         */

        String data = "{\"code\":\"1014000520\",\n" +
                "            \"countryCode\":\"JP\",\n" +
                "            \"countryName\":\"日本\",\n" +
                "            \"freePrice\":48.8,\n" +
                "            \"marketPrice\":104,\n" +
                "            \"menuId\":132,\n" +
                "            \"name\":\"Mandom曼丹 Bifesta温和即净卸妆水 145ml\",\n" +
                "            \"picUrl\":\"product/image/20150811/20150811094422795_400.jpg\"}";


        System.out.println("data=" + data);


        String encode = MsOnionSecurityUtils.base64EncodeWithReplace(data);

        System.out.println("base64EncodeWithReplace加密之后 # encode=" + encode);

        String decode = MsOnionSecurityUtils.base64DecodeWithReplace(encode);

        System.out.println("base64DecodeWithReplace # 解密之后 # decode=" + decode);


        // 加密之后 # encode=ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2Rl IjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAog ICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFy a2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAg ICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z 5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InBy b2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpw ZyI=


        /**
         * http://tool.oschina.net/encrypt?type=3
         * 结果： 去掉空格
         *
         * ImNvZGUiOiIxMDE0MDAwNTIwIiwKICAgICAgICAgICAgImNvdW50cnlDb2RlIjoiSlAiLAogICAgICAgICAgICAiY291bnRyeU5hbWUiOiLml6XmnKwiLAogICAgICAgICAgICAiZnJlZVByaWNlIjo0OC44LAogICAgICAgICAgICAibWFya2V0UHJpY2UiOjEwNCwKICAgICAgICAgICAgIm1lbnVJZCI6MTMyLAogICAgICAgICAgICAibmFtZSI6Ik1hbmRvbeabvOS4uSBCaWZlc3Rh5rip5ZKM5Y2z5YeA5Y245aaG5rC0IDE0NW1sIiwKICAgICAgICAgICAgInBpY1VybCI6InByb2R1Y3QvaW1hZ2UvMjAxNTA4MTEvMjAxNTA4MTEwOTQ0MjI3OTVfNDAwLmpwZyI=
         *
         */





    }

}
