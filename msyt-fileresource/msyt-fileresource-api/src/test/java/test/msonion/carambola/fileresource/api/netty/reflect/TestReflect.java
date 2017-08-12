package test.msonion.carambola.fileresource.api.netty.reflect;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by HeroCao on 2017/5/1.
 */
public class TestReflect {

    @Test
    public void test01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        TestUser testUser = new TestUser();

        Method logout = testUser.getClass().getMethod("logout",
                String.class, String.class);

        Object obj = logout.invoke(testUser, "测试11", "哈哈22");


        System.out.println(String.format("test01 ## obj=%s", obj));


    }


    @Test
    public void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        TestUser testUser = new TestUser();

        // java.lang.NoSuchMethodException: test.msonion.carambola.parent.ext.netty.reflect.TestUser.login(java.lang.String, java.lang.Object)

//        Method logout = testUser.getClass().getMethod("login",
//                String.class, Object.class);

//        /*
        //获取method两种方式， 在method中 数组的的空间大小是可以随便写的不一定使用0
        /* 1 */
//        Method method = clazz.getDeclaredMethod("method", Array.newInstance(<img id="selectsearch-icon" src="https://gss0.bdstatic.com/70cFsjip0QIZ8tyhnq/img/iknow/qb/select-search.png" alt="搜索" class="selectsearch-hide">Object.class, 0).getClass());
        /* 2 */
//        method = clazz.getDeclaredMethod("method", (new Object[0]).getClass());



        //初始化参数
        /* 1 */
//        Object objs = Array.newInstance(Object.class, 2);
//        Array.set(objs, 0, list);
//        Array.set(objs, 1, "23");
//        method.invoke(single, objs);

        /* 2 */
//        Object[] objects = {1, "fuck", list};
//        method.invoke(single, new Object[]{ objects });

//         */


        // OK
//        Class<?> objArrayClass = Array.newInstance(String.class, 2).getClass();

        // Error
        // Object.class
//        Class<?> objArrayClass = Array.newInstance(Object.class, 2).getClass();
//
//        // Error
//        Method login = testUser.getClass().getMethod("login",
//                String.class, objArrayClass);


//        Class<?> objArrayClass = Array.newInstance(Object.class, 2).getClass();

//        String[] params = {"测试11", "22", "33"};

//        String[] params = {"测试11", "22"};

        String[] ss = {"11", "22"};

        // 必须拆开 ！！！
        Object[] params = {"测试11", ss};

        // Error
        Method login = testUser.getClass().getMethod("login",
                 getParameterTypes(params));

        // Error
//        Object obj = login.invoke(testUser, "测试22", "哈哈，呵呵", "22");


        // OK
//        Object obj = login.invoke(testUser, "测试22", new String[]{"11", "22"});


//        Object obj = login.invoke(testUser, "测试22", new Object[]{"11", "22"});


        Object obj = login.invoke(testUser, params);

        System.out.println(String.format("test02 ## obj=%s", obj));

//        MsOnionReflectionUtils

    }


    /**
     * 获取参数类型
     * @param parameters 参数
     * @return 返回参数类型
     */
    public static Class<?>[] getParameterTypes(final Object[] parameters) {
        Class<?>[] result = new Class<?>[parameters.length];
        int i = 0;
        for (Object each : parameters) {
            result[i] = each.getClass();
            i++;
        }
        return result;
    }

    /*

    Method method = targetInstance.getClass().getMethod(request.getMethod(), getParameterTypes(request.getParameters()));
            result = method.invoke(targetInstance, request.getParameters());

     */

}

class TestUser {

    public String logout(String username, String parameters) {

        System.out.println(String.format("登录 ## username=%s，parameters=%s", username, parameters));

        return String.format("username=%s，parameters=%s", username, parameters);
    }

    public String login(String username, String... parameters) {

        System.out.println(String.format("登录 ## username=%s，parameters=%s", username, parameters));

        return String.format("username=%s，parameters=%s", username, parameters);
    }

}