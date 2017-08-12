package test.msonion.carambola.log.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/4/16.
 */
public class TestException {



    @Test
    public void test01() {

        try {

//            int valule = 0 / 0;

            String str = null;

            str.length();

        } catch (Exception ex) {
            ex.printStackTrace();

            String stackTrace = ExceptionUtils.getStackTrace(ex);


            System.out.println("========= ## Begin ## ==============");

            /**
             * stackTrace=java.lang.NullPointerException
             at test.msonion.carambola.log.service.TestException.test01(TestException.java:22)
             at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
             at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
             at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
             at java.lang.reflect.Method.invoke(Method.java:497)
             at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
             at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
             at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
             at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
             at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
             at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
             at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
             at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
             at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
             at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
             at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
             at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
             at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
             at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
             at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
             at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:51)
             at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:237)
             at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
             */
            System.out.println("stackTrace=" + stackTrace);

            System.out.println("========= ## Begin2 ## ==============");

            String rootCauseMessage = ExceptionUtils.getRootCauseMessage(ex);

            System.out.println("rootCauseMessage=" + rootCauseMessage);


            System.out.println("========= ## Begin3 ## ==============");

            String message = ExceptionUtils.getMessage(ex);

            System.out.println("message=" + message);
        }

    }


}
