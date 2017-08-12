package test.msonion.carambola.parent.ext.netty.asyc.callable;

import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.interfaces.netty.callable.MsOnionNettyUploadCallable;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import test.msonion.carambola.parent.ext.netty.asyc.callable.upload.TestNettyAbstractUploadCallable2;
import test.msonion.carambola.parent.ext.netty.upload.service.TestUploadService;

/**
 * Created by HeroCao on 2017/5/1.
 */
public class TestNettyUploadCallable3 extends TestNettyAbstractUploadCallable2<MsOnionNettyResponse, MsOnionNettyRequest, TestUploadService> {
    /**
     * MsOnionNettyUploadCallable
     *
     * @param client     Netty客户端实例对象
     * @param method     SERVICE实现类中方法名称，例如：uploadFile、downloadFile、upload、download
     * @param parameters SERVICE实现类中方法的参数
     */
    public TestNettyUploadCallable3(MsOnionNettyClientAdapter client, String method, Object... parameters) {
        super(client, method, parameters);
    }

    /**
     * MsOnionNettyUploadCallable
     *
     * @param client     Netty客户端实例对象
     * @param method     SERVICE实现类中方法名称，例如：uploadFile、downloadFile、upload、download
     * @param parameters SERVICE实现类中方法的参数
     */
    public TestNettyUploadCallable3(String messageId,MsOnionNettyClientAdapter client, String method, Object... parameters) {
        super(messageId, client, method, parameters);
    }
}
