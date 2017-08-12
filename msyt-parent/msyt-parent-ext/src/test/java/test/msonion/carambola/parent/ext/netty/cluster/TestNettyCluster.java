package test.msonion.carambola.parent.ext.netty.cluster;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.ext.netty.cluster.MsOnionNettyRegistry;
import cc.msonion.carambola.parent.ext.netty.cluster.MsOnionNettyRouter;
import cc.msonion.carambola.parent.ext.netty.server.MsOnionNettyServer;
import cc.msonion.carambola.parent.ext.spring.MsOnionSpringContainer;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.msonion.carambola.parent.ext.netty.upload.service.TestUploadService;

import java.net.InetSocketAddress;

/**
 * Created by HeroCao on 2017/4/30.
 */
public class TestNettyCluster {

    private AbstractApplicationContext context1;
    private AbstractApplicationContext context2;
    private AbstractApplicationContext context3;

//    private MsOnionNettyServerAdapter nettyServer1;
//    private MsOnionNettyServerAdapter nettyServer2;

    private MsOnionNettyServer nettyServer1;
    private MsOnionNettyServer nettyServer2;

//    private MsOnionNettyRegistryAdapter nettyRegistry;
//    private MsOnionNettyRouterAdapter nettyRouter;

    private MsOnionNettyRegistry nettyRegistry;
    private MsOnionNettyRouter nettyRouter;

    private int port1 = 8898;
    private int port2 = 8899;
    private String ip = "localhost";

    @Before
    public void setUp() throws MsOnionException {

//        MsOnionSpringContainer.

        context1 = new ClassPathXmlApplicationContext(MsOnionSpringContainer.CONFIG_FILE);

        nettyServer1 = context1.getBean(MsOnionNettyServer.class);

        context2 = new ClassPathXmlApplicationContext(MsOnionSpringContainer.CONFIG_FILE);
        nettyServer2 = context2.getBean(MsOnionNettyServer.class);

        context3 = new ClassPathXmlApplicationContext(MsOnionSpringContainer.CONFIG_FILE);
//        nettyRegistry = context3.getBean(MsOnionNettyRegistryAdapter.class);
//        nettyRouter = context3.getBean(MsOnionNettyRouterAdapter.class);

        // 不同的 上下文 ！！！
        nettyRegistry = context3.getBean(MsOnionNettyRegistry.class);
        nettyRouter = context3.getBean(MsOnionNettyRouter.class);

        nettyServer1.start(port1);
        nettyServer2.start(port2);
    }

    @After
    public void tearDown() {
        context1.close();
        context2.close();
        context3.close();
    }

    @Test
    public void queryForSync() throws MsOnionException {
        nettyRegistry.register(new InetSocketAddress(ip, port1));
        nettyRegistry.register(new InetSocketAddress(ip, port2));
        nettyRegistry.register(new InetSocketAddress(ip, port1));

        // nettyRegistry
//		nettyRegistry.getRegisteredClients()

        for (int i = 0; i < 100; i++) {

//            MsOnionNettyResponse
            MsOnionNettyResponse response = nettyRouter.sent(new MsOnionNettyRequest(TestUploadService.class,
                    "query", "filename-" + i));

            System.out.println("i=" + i + "response=" + response);




//			response.getMessageId();
        }
    }

}
