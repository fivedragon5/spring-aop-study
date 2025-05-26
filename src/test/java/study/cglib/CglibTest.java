package study.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import study.cglib.code.ConcreteService;
import study.cglib.code.TimeMethodInterceptor;

public class CglibTest {
    @Test
    void cglibTest() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        proxy.call();
        System.out.println("targetClass = " + target.getClass());
        System.out.println("proxyClass = " + proxy.getClass());
        // 출력
        // Time Proxy 호출
        // ConcreteService 호출
        // Method call executed in 0 ms
        // Time Proxy 종료
        // targetClass = class study.cglib.code.ConcreteService
        // proxyClass = class study.cglib.code.ConcreteService$$EnhancerByCGLIB$$a73fc3a8
    }
}
