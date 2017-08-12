package test.msonion.carambola.parent.ext.netty.asyc.callable;

import cc.msonion.carambola.parent.interfaces.callable.MsOnionComplexPlusCallable;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import test.msonion.carambola.parent.ext.netty.upload.service.TestUploadService;

/**
 * Created by HeroCao on 2017/4/30.
 */
public class TestNettyUploadCallable implements MsOnionComplexPlusCallable<MsOnionNettyResponse, MsOnionNettyRequest, TestUploadService> {

    private MsOnionNettyClientAdapter client;
    private String methodName;
    private Object[] paramters;

   public TestNettyUploadCallable(MsOnionNettyClientAdapter client, String methodName, Object... parameters) {

       this.client = client;
       this.methodName = methodName;
       this.paramters = parameters;
   }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public MsOnionNettyResponse call() throws Exception {


        MsOnionNettyRequest request = new MsOnionNettyRequest(TestUploadService.class, this.methodName, this.paramters);

        System.out.println("call ## request=" + request);

        return client.sent(request);

        // RO：
//        return null;

        // return nettyClient.sent(new Request(TestService.class, "query", queryString));



    }
}
