package spring.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.aop.member.MemberService;
import spring.aop.member.MemberServiceImpl;
import spring.aop.proxyvs.code.ProxyDIAspect;

/**
 * 프록시 DI 테스트
 * 1. JDK 동적 프록시
 *  - 인터페이스를 기반으로 프록시를 생성하기때문에 ~~~Impl 같 구체 타입으로 캐스팅이 불가능 하다
 *
 * 2. CGLIB 동적 프록시
 *  - 클래스 기반으로 프록시를 생성하기 때문에 구현 클래스로 주입받아도 된다.
 *
 * JDK 동적 프록시는 MemberService 인터페이스를 기반으로 프록시를 생성하기 때문에 의존관게 주입이 불가능하다.
 *
 * Spring AOP - Proxy 생성시 기본적으로 CGLIB 동적 프록시를 사용한다.
 */
@Slf4j
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시
//@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB 동적 프록시
@SpringBootTest
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
