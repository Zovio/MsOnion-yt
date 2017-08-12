package test.msonion.carambola.fileresource.api.netty.asyc.callable.upload;

import org.junit.Test;

/**
 * Created by HeroCao on 2017/5/1.
 */
public class Test1 {


    @Test
    public void testGetPath01() {

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();


        // path=/C:/Users/HeroCao/Downloads/HeroCao/Code/GitLab/2017-03-22/Ms.Onion-YT/msyt-parent/msyt-parent-ext/target/test-classes/
        System.out.println("path=" + path);


    }


    @Test
    public void testGetPath02() {

        String path = Test1.class.getClassLoader().getResource("").getPath();


        // path=/C:/Users/HeroCao/Downloads/HeroCao/Code/GitLab/2017-03-22/Ms.Onion-YT/msyt-parent/msyt-parent-ext/target/test-classes/
        System.out.println("path=" + path);

    }

    @Test
    public void testGetPath03() {


        /*
        相对路径的获得
说明:相对路径(即不写明时候到底相对谁)均可通过以下方式获得（不论是一般的Java项目还是web项目）
String relativelyPath=System.getProperty("user.dir");
上述相对路径中，java项目中的文件是相对于项目的根目录
web项目中的文件路径视不同的web服务器不同而不同（tomcat是相对于 tomcat安装目录\bin）

         */


        String relativelyPath = System.getProperty("user.dir");


        /// relativelyPath=C:\Users\HeroCao\Downloads\HeroCao\Code\GitLab\2017-03-22\Ms.Onion-YT\msyt-parent\msyt-parent-ext

        System.out.println("relativelyPath=" + relativelyPath);

    }
}
