package test.msonion.carambola.parent.ext.netty.upload;

import cc.msonion.carambola.parent.interfaces.callable.MsOnionComplexCallable;
import cc.msonion.carambola.parent.interfaces.callable.MsOnionComplexPlusCallable;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import test.msonion.carambola.parent.ext.netty.upload.service.TestUploadService;

/**
 * Created by HeroCao on 2017/4/30.
 */
public class TestUploadCallable implements MsOnionComplexPlusCallable<MsOnionNettyResponse, MsOnionNettyRequest, TestUploadService> {


    private final MsOnionNettyClientAdapter nettyClient;
    private final String parameter;
    private final String methodName;

    public TestUploadCallable(final MsOnionNettyClientAdapter nettyClient, final String methodName, final String parameter) {
        this.nettyClient = nettyClient;
        this.parameter = parameter;
        this.methodName = methodName;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public MsOnionNettyResponse call() throws Exception {


        // 		return nettyClient.sent(new Request(FooService.class, "query", queryString));

//        nettyClient.sent()

        return nettyClient.sent(new MsOnionNettyRequest(TestUploadService.class, methodName, parameter));

//        return null;
    }
}
