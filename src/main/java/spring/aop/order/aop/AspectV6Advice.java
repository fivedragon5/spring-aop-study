package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV6Advice {

    // spring.aop.order 하위패키지 이면서 클래스 이름 패턴이 *service
//    @Around("spring.aop.order.aop.Pointcuts.orderAndService()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            // @Before
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            // @AfterReturning
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            // @AfterThrowing
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
//            // @After
//            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
//        }
//    }

    @Before("spring.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    // joinPoint 를 인자로 받지 않아도 됨
//    @Before("spring.aop.order.aop.Pointcuts.orderAndService()")
//    public void doBefore() {
//        log.info("[before] 로그 실행");
//    }

    @AfterReturning(value = "spring.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "spring.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[AfterThrowing] {} ex={}", joinPoint.getSignature(), ex);
    }

    @After(value = "spring.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
