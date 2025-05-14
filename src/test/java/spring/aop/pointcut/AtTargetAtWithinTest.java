package spring.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import spring.aop.member.annotation.ClassAop;

/**
 * @target, @within
 *  1. 이 포인트컷 지시자는 단독으로 사용하면 안된다.
 *  2. execution지시자를 통해 적용 대상을 줄이고 사용할것
 *   - 실행 시점에 일어나는 포인트컷 적용 여부도 결국 프록시가 있어야 실행 시점에 판단할 수 있음
 *     그런데 스프링 컨테이너가 프록시를 생성하는 시점은 스프링 컨테이너가 만들어지는 애플리케이션 로딩 시점에 적용할 수 있다.
 *     따라서 'args', '@args', '@target' 같은 포인트컷 지시자가 있으면 스프링은 모든 스프링 빈에 AOP를 적용하려고 시도
 *     스프링이 사용하는 빈들 중에서는 final 클래스가 있을 수 있고, final 클래스는 프록시를 만들 수 없기에 오류가 발생할 수 있음
 *   - 따라서 이런 표현삭은 최대한 프록시 적용 대상을 축소하는 표현식과 함께 사용해야함
 */
@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest {

    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child Proxy={}", child.getClass());
        child.childMethod();
        child.parentMethod();
    }

    static class Parent {
        void parentMethod() {} // 부모에만 있는 메서드
    }

    @ClassAop
    static class Child extends Parent {
        void childMethod() {} // 자식에만 있는 메서드
    }

    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }
        @Bean
        public Child child() {
            return new Child();
        }
        @Bean
        public AtTargetAtWithinTest.AtTargetAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinTest.AtTargetAspect();
        }
    }

    @Slf4j
    @Aspect
    static class AtTargetAspect {

        //@target : 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 적용
        @Around("execution(* spring.aop.pointcut..*(..)) && @target(spring.aop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //@within: 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용되지 않음
        @Around("execution(* spring.aop..*(..)) && @within(spring.aop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
