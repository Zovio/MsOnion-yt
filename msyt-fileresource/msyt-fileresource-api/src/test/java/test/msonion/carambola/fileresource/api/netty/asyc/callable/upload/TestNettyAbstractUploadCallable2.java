package test.msonion.carambola.fileresource.api.netty.asyc.callable.upload;

import cc.msonion.carambola.parent.common.utils.MsOnionReflectionUtils;
import cc.msonion.carambola.parent.interfaces.callable.MsOnionComplexPlusCallable;
import cc.msonion.carambola.parent.interfaces.netty.adapter.MsOnionNettyClientAdapter;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest;
import cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Created by HeroCao on 2017/4/30.
 */
public abstract class TestNettyAbstractUploadCallable2<RESPONSE extends MsOnionNettyResponse, REQUEST extends MsOnionNettyRequest, SERVICE extends Serializable> implements MsOnionComplexPlusCallable<RESPONSE, REQUEST, SERVICE> {

    /**
     * 消息Id
     */
    private String messageId;

    /**
     * Netty客户端适配器
     */
    private MsOnionNettyClientAdapter client;

    /**
     * SERVICE实现类中方法名称
     */
    private String method;

    /**
     * SERVICE实现类中方法的参数
     */
    private Object[] parameters;

    /**
     * 父类泛型类型的索引：0 ，也就是： RESPONSE
     */
    private static final int SUPERCLASS_PARAMETERIZED_TYPE_INDEX_0 = 0;

    /**
     * 父类泛型类型的索引：1 ，也就是： REQUEST
     */
    private static final int SUPERCLASS_PARAMETERIZED_TYPE_INDEX_1 = 1;

    /**
     * 父类泛型类型的索引：2 ，也就是： SERVICE
     */
    private static final int SUPERCLASS_PARAMETERIZED_TYPE_INDEX_2 = 2;

    /**
     * MsOnionNettyUploadCallable
     * @param client Netty客户端实例对象
     * @param method SERVICE实现类中方法名称，例如：uploadFile、downloadFile、upload、download
     * @param parameters SERVICE实现类中方法的参数
     */
    public TestNettyAbstractUploadCallable2(MsOnionNettyClientAdapter client, String method, Object... parameters) {

//        this.client = client;
//        this.method = method;
//        this.parameters = parameters;

        this(null, client, method, parameters);
    }

    /**
     * MsOnionNettyUploadCallable
     * @param client Netty客户端实例对象
     * @param method SERVICE实现类中方法名称，例如：uploadFile、downloadFile、upload、download
     * @param parameters SERVICE实现类中方法的参数
     */
    public TestNettyAbstractUploadCallable2(String messageId, MsOnionNettyClientAdapter client, String method, Object... parameters) {

        this.messageId = messageId;
        this.client = client;
        this.method = method;
        this.parameters = parameters;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public RESPONSE call() throws Exception {

        System.out.println("TestNettyAbstractUploadCallable2 # call # ...");

        Class<?> responseClass = MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), SUPERCLASS_PARAMETERIZED_TYPE_INDEX_0);
        Class<?> requestClass = MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), SUPERCLASS_PARAMETERIZED_TYPE_INDEX_1);
        Class<?> serviceClass = MsOnionReflectionUtils.getSuperclassParameterizedType(getClass(), SUPERCLASS_PARAMETERIZED_TYPE_INDEX_2);

        // MsOnionNettyRequest request = new MsOnionNettyRequest(TestUploadService.class, this.methodName, this.paramters);

//        MsOnionReflectionUtils.

        // 可变参数：Array.newInstance(Object.class).getClass()
//        Constructor<MsOnionNettyRequest> requestConstructor = (Constructor<MsOnionNettyRequest>) requestClass.getConstructor(serviceClass, String.class, Array.newInstance(Object.class).getClass());
//
//        return (RESPONSE) client.sent(requestConstructor.newInstance(method, parameters));

//        Constructor<?> requestConstructor = requestClass.getConstructor(serviceClass, String.class, Array.newInstance(Object.class).getClass());

        // 构造方法的参数数组
        // 注意，第二个不是 String.class , 而是 method
//        Object[] constructorParameters = {serviceClass, method, parameters};

        // 构造方法的参数数组
        Object[] constructorParameters = null;

        if (StringUtils.isNotBlank(messageId)) {
            constructorParameters = new Object[]{messageId, serviceClass, method, parameters};
        } else {
            constructorParameters = new Object[]{serviceClass, method, parameters};
        }

        // 构造方法的参数类型数组
        Class<?>[] constructorParameterClasses = MsOnionReflectionUtils.getParameterTypes(constructorParameters);

        // cc.msonion.carambola.parent.pojo.netty.exchange.MsOnionNettyRequest.<init>(java.lang.Class, java.lang.Class, [Ljava.lang.Object;)

        // final String messageId, final Class<?> targetClass, final String method, final Object... parameters

        Constructor<?>[] constructors = requestClass.getConstructors();

        for (Constructor<?> constructor : constructors) {


            System.out.println("构造器 # constructor=" + constructor);

            Class<?>[] parameterTypes = constructor.getParameterTypes();

            for (Class<?> ptype : parameterTypes) {
                System.out.println("构造器 # constructor=" + constructor + "， 参数类型ptype=" + ptype);
            }

//            constructor.getParameters();

        }


        // 根据参数类型 调用构造方法的
        Constructor<?> requestConstructor = requestClass.getConstructor(constructorParameterClasses);



        // 通过构造方法，创建实例对象

        // 构造方法参数：
//        Object request = requestConstructor.newInstance(serviceClass, method, parameters);

        // 通过构造方法，创建实例对象
        // 构造方法参数：
        Object request = null;

        if (StringUtils.isNotBlank(messageId)) {
            messageId = messageId.trim();
            request = requestConstructor.newInstance(messageId, serviceClass, method, parameters);
        } else {
            request = requestConstructor.newInstance(serviceClass, method, parameters);
        }

        MsOnionNettyResponse result = client.sent((MsOnionNettyRequest) request);

//        MsOnionNettyRequest

        return (RESPONSE) result;
    }
}
