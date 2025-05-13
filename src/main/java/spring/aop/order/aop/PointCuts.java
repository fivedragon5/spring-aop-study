package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class PointCuts {


    // spring.aop.order 패키지와 하위 패키지를 전부 포함
    @Pointcut("execution(* spring.aop.order..*(..))")
    public void allOrder(){} // pointcut signature

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    // allOrder() & allService()
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}

}
