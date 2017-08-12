package test.msonion.carambola.parent.ext.netty.asyc;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.spring.MsOnionSpringContainer;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyServerAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import test.msonion.carambola.parent.ext.netty.asyc.callable.TestNettyUploadCallable;
import test.msonion.carambola.parent.ext.netty.asyc.callable.TestNettyUploadCallable2;
import test.msonion.carambola.parent.ext.netty.asyc.callable.TestNettyUploadCallable3;
import test.msonion.carambola.parent.ext.netty.upload.service.TestUploadService;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HeroCao on 2017/4/30.
 */
@ContextConfiguration(locations = MsOnionSpringContainer.CONFIG_FILE)
public final class TestNettyAsync extends AbstractJUnit4SpringContextTests {



    private static volatile boolean serverStarted = false;

    private int port = 6099;
    private String ip = "localhost";

    @Resource
    private MsOnionNettyServerAdapter nettyServer;

    @Resource
    private MsOnionNettyClientAdapter nettyClient;

    @Before
    public void setUp() throws MsOnionException {
        if (!serverStarted) {
            nettyServer.start(port);
            serverStarted = true;
        }
        nettyClient.connect(new InetSocketAddress(ip, port));
    }


    @Test
    public void testQueryForSync() throws MsOnionException {
        for (int i = 0;i < 100; i++) {

            MsOnionNettyResponse response = nettyClient.sent(new MsOnionNettyRequest(TestUploadService.class,
                    "query", "test" + i));


            System.out.println(String.format("testQueryForSync ## i=%d , response=%s", i, response));


        }
    }

    @Test
    public void testQueryForAsync() throws InterruptedException, ExecutionException {

        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {

//            TestNettyUploadCallable
            MsOnionNettyResponse response = null;

            if (i % 5 == 0) {
                response = executorService.submit(new TestNettyUploadCallable(nettyClient, "query", 500 + "")).get();
            } else if (i % 8 == 0) {
                response = executorService.submit(new TestNettyUploadCallable(nettyClient, "query", 1000 + "")).get();
            } else {
                response = executorService.submit(new TestNettyUploadCallable(nettyClient, "query", "netty" + i)).get();

            }



            //
            System.out.println(String.format("testQueryForAsync ## 执行完成  ## i=%d , response=%s", i, response));

        }


        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }


    @Test
    public void testQueryForAsync2() throws InterruptedException, ExecutionException {

        System.out.println(String.format("testQueryForAsync2 ## 执行开始 # beginning ... "));


        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {

//            TestNettyUploadCallable
            MsOnionNettyResponse response = null;

            if (i % 5 == 0) {
                response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "query", 500 + "")).get();
            } else if (i % 8 == 0) {
                response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "query", 1000 + "")).get();
            } else {
                response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "query", "netty" + i)).get();

            }



            //
            System.out.println(String.format("testQueryForAsync2 ## 执行完成  ## i=%d , response=%s", i, response));

        }


        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }

    @Test
    public void testQueryForAsync2_UUID() throws InterruptedException, ExecutionException {

        System.out.println(String.format("testQueryForAsync2 ## 执行开始 # beginning ... "));


        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {

//            TestNettyUploadCallable
            MsOnionNettyResponse response = null;

            if (i % 5 == 0) {
                response = executorService.submit(new TestNettyUploadCallable2(i +"-netty", nettyClient, "query", 500 + "")).get();
            } else if (i % 8 == 0) {
                response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "query", 1000 + "")).get();
            } else {
                response = executorService.submit(new TestNettyUploadCallable2(i +"-haha", nettyClient, "query", "netty" + i)).get();

            }



            //
            System.out.println(String.format("testQueryForAsync2 ## 执行完成  ## i=%d , response=%s", i, response));

        }


        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }


    // 上传文件
    @Test
    public void testQueryForAsync2_UploadFile() throws InterruptedException, ExecutionException {

        System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行开始 # beginning ... "));


        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 100; i++) {
//
////            TestNettyUploadCallable
//            MsOnionNettyResponse response = null;
//
//            if (i % 5 == 0) {
//                response = executorService.submit(new TestNettyUploadCallable2(i +"-netty", nettyClient, "query", 500 + "")).get();
//            } else if (i % 8 == 0) {
//                response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "query", 1000 + "")).get();
//            } else {
//                response = executorService.submit(new TestNettyUploadCallable2(i +"-haha", nettyClient, "query", "netty" + i)).get();
//
//            }
//
//
//
//            //
//            System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行完成  ## i=%d , response=%s", i, response));
//
//        }


        // 上传文件

        String filename = "C:\\Users\\HeroCao\\Desktop\\洋桃\\旺店通2.0开放接口文档V2_0_5.rar";

        MsOnionNettyResponse response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "uploadFile", filename)).get();

        System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行完成  , response=%s", response));

        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }


    // 多次，上传文件
    @Test
    public void testQueryForAsync2_UploadFile_Multi() throws InterruptedException, ExecutionException {

        System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行开始 # beginning ... "));


        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) { // 100

//            TestNettyUploadCallable
//            MsOnionNettyResponse response = null;

//            if (i % 5 == 0) {
//                response = executorService.submit(new TestNettyUploadCallable2(i +"-netty", nettyClient, "query", 500 + "")).get();
//            } else if (i % 8 == 0) {
//                response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "query", 1000 + "")).get();
//            } else {
//                response = executorService.submit(new TestNettyUploadCallable2(i +"-haha", nettyClient, "query", "netty" + i)).get();
//
//            }

            // 上传文件

            // C:\Users\HeroCao\Desktop\洋桃
            String filename = "C:\\Users\\HeroCao\\Desktop\\洋桃\\旺店通2.0开放接口文档V2_0_5.rar";

            MsOnionNettyResponse response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "uploadFile", filename)).get();

//            System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行完成  , response=%s", response));


            //
            System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行完成  ## i=%d , response=%s", i, response));

        }


        // 上传文件
//
//        String filename = "C:\\Users\\HeroCao\\Desktop\\旺店通2.0开放接口文档V2_0_5.rar";
//
//        MsOnionNettyResponse response = executorService.submit(new TestNettyUploadCallable2(nettyClient, "uploadFile", filename)).get();
//
//        System.out.println(String.format("testQueryForAsync2_UploadFile ## 执行完成  , response=%s", response));

        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }

    @Test
    public void testQueryForAsync3() throws InterruptedException, ExecutionException {

        System.out.println(String.format("testQueryForAsync2 ## 执行开始 # beginning ... "));


        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {

//            TestNettyUploadCallable
            MsOnionNettyResponse response = null;

            if (i % 5 == 0) {
                response = executorService.submit(new TestNettyUploadCallable3(nettyClient, "query", 500 + "")).get();
            } else if (i % 8 == 0) {
                response = executorService.submit(new TestNettyUploadCallable3(nettyClient, "query", 1000 + "")).get();
            } else {
                response = executorService.submit(new TestNettyUploadCallable3(nettyClient, "query", "netty" + i)).get();

            }



            //
            System.out.println(String.format("testQueryForAsync2 ## 执行完成  ## i=%d , response=%s", i, response));

        }


        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }

    @Test
    public void testQueryForAsync3_UUID() throws InterruptedException, ExecutionException {

        System.out.println(String.format("testQueryForAsync2 ## 执行开始 # beginning ... "));


        // 固定的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {

//            TestNettyUploadCallable
            MsOnionNettyResponse response = null;

            if (i % 5 == 0) {
                response = executorService.submit(new TestNettyUploadCallable3(i + "-netty", nettyClient, "query", 500 + "")).get();
            } else if (i % 8 == 0) {
                response = executorService.submit(new TestNettyUploadCallable3(nettyClient, "query", 1000 + "")).get();
            } else {
                response = executorService.submit(new TestNettyUploadCallable3(nettyClient, "query", "netty" + i)).get();

            }



            //
            System.out.println(String.format("testQueryForAsync2 ## 执行完成  ## i=%d , response=%s", i, response));

        }


        // TODO: 将 集群 + 异步 一起实现
        // TODO: 上传文件 ， 下载文件 工具类 ！！！


    }

    @Test
    public void testQueryWithSystemException() throws MsOnionException {

        MsOnionNettyResponse response = nettyClient.sent(new MsOnionNettyRequest(TestUploadService.class, "queryWithSystemException", "bar"));
    }

    @Test
    public void testQueryMix() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        MsOnionNettyResponse normalQueryResponse = executorService.submit(new TestNettyUploadCallable(nettyClient, "normal")).get();
        MsOnionNettyResponse slowQueryResponse = executorService.submit(new TestNettyUploadCallable(nettyClient, "slow")).get();


        System.out.println("queryMix # normalQueryResponse=" + normalQueryResponse);

        System.out.println("queryMix # slowQueryResponse=" + slowQueryResponse);


    }

}
