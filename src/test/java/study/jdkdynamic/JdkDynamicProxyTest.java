package study.jdkdynamic;

import org.junit.jupiter.api.Test;
import study.jdkdynamic.code.AImpl;
import study.jdkdynamic.code.AInterface;
import study.jdkdynamic.code.TimeInvocationHandler;

import java.lang.reflect.Proxy;

public class JdkDynamicProxyTest {
    @Test
    void dynamicA() throws Throwable {
        AInterface target = new AImpl();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                timeInvocationHandler
        );
        proxy.call();
        System.out.println("targetClass: " + target.getClass().getName());
        System.out.println("proxyClass: " + proxy.getClass().getName());
        // Time Proxy 호출
        // A 호출
        // Method call executed in 0 ms
        // Time Proxy 종료
        // targetClass: study.jdkdynamic.code.AImpl
        // proxyClass: jdk.proxy3.$Proxy12
    }
}
