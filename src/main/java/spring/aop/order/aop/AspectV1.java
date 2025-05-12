package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    // Pointcut execution(* spring.aop.order..*(..))
    @Around("execution(* spring.aop.order..*(..))")
    // Advisor : doLog()
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint.getSignature() : 메서드 시그니처를 가져옴
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
