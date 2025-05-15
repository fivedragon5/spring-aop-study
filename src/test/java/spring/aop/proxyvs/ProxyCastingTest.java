package spring.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import spring.aop.member.MemberService;
import spring.aop.member.MemberServiceImpl;

/**
 * 프록시 캐스팅
 * 1. JDK 동적 프록시
 *  - 대상 객체 'MemberServiceImpl'로 캐스팅 할 수 없다.
 *  - 인터페이스를 가지고 프록시를 만들기 때문에 자식 타입으로 캐스팅 할 수 없다. (Impl 을 알지 못함)
 * 2. CGLIB 동적 프록시
 *  - 대상 객체 'MemberServiceImpl'로 캐스팅 할 수 있다.
 */

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        // 프록시를 인터페이스로 캐스팅 성공
        Object memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 발생
        org.junit.jupiter.api.Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGlib 동적 프록시

        // 프록시를 인터페이스로 캐스팅 성공
        Object memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy class={}", memberServiceProxy.getClass());

        // CGLIB 동적 프록시를 구현 클래스로 캐스팅 시도 성공, ClassCastException 발생
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }
}
