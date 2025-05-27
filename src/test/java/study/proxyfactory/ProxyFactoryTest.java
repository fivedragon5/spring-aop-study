package study.proxyfactory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.test.context.SpringBootTest;
import study.proxyfactory.common.advice.TimeAdvice;
import study.proxyfactory.common.service.ConcreteService;
import study.proxyfactory.common.service.ServiceImpl;
import study.proxyfactory.common.service.ServiceInterface;

public class ProxyFactoryTest {
    @Test
    void 동적_프록시_테스트() {
        // 1. 프록시 대상 객체 생성 ( 인터페이스 기반 )
        ServiceInterface target = new ServiceImpl();
        // 2. ProxyFactory를 생성하고 프록시의 대상 객체로 target을 설정
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // 3. 프록시 객체가 호출될 때 적용할 공통 기능(Advice) 을 설정
        proxyFactory.addAdvice(new TimeAdvice());
        // 4. 프록시 객체를 생성
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("Target Class: " + target.getClass());
        System.out.println("Proxy Class: " + proxy.getClass());
        // Target Class: class study.proxyfactory.common.service.ServiceImpl
        // Proxy Class: class jdk.proxy3.$Proxy13
    }

    @Test
    void CGLIB_프록시_테스트() {
        // 1. 프록시 대상 객체 생성 ( 클래스 기반 )
        ConcreteService target = new ConcreteService();
        // 2. ProxyFactory를 생성하고 프록시의 대상 객체로 target을 설정
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // 3. 프록시 객체가 호출될 때 적용할 공통 기능(Advice) 을 설정
        proxyFactory.addAdvice(new TimeAdvice());
        // 4. 프록시 객체를 생성
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        System.out.println("Target Class: " + target.getClass());
        System.out.println("Proxy Class: " + proxy.getClass());
        // Target Class: class study.proxyfactory.common.service.ConcreteService
        // Proxy Class: class study.proxyfactory.common.service.ConcreteService$$SpringCGLIB$$0
    }

    @Test
    void CGLIB_프록시_강제_테스트() {
        // 1. 프록시 대상 객체 생성 ( 인터페이스 기반 )
        ServiceInterface target = new ServiceImpl();
        // 2. ProxyFactory를 생성하고 프록시의 대상 객체로 target을 설정
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        // 3. 프록시 객체가 호출될 때 적용할 공통 기능(Advice) 을 설정
        proxyFactory.addAdvice(new TimeAdvice());
        // 4. 프록시 객체를 생성
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        System.out.println("Target Class: " + target.getClass());
        System.out.println("Proxy Class: " + proxy.getClass());
        // Target Class: class study.proxyfactory.common.service.ServiceImpl
        // Proxy Class: class jdk.proxy3.$Proxy13

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    }
}
