package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    // spring.aop.order 패키지와 하위 패키지를 전부 포함
    @Pointcut("execution(* spring.aop.order..*(..))")
    private void allOrder(){} // pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint.getSignature() : 메서드 시그니처를 가져옴
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
