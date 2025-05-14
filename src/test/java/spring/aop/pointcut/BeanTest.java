package spring.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.aop.member.MemberService;
import spring.aop.order.OrderService;

/**
 * bean
 *  - 스프링 전용 포인트컷 지시자, 빈의 이름으로 지정한다.
 *  - 스프링 빈의 이름으로 AOP 적용 여부를 지정한다 (스프링에서만 사용 가능)
 *   - bean(orderService) || bean(*Repository) 처럼 사용 가능
 */
@Slf4j
@Import({BeanTest.BeanAspect.class})
@SpringBootTest
public class BeanTest {

    @Autowired
    OrderService orderService;

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Slf4j
    @Aspect
    static class BeanAspect {

        @Around("bean(orderService) || bean(*Repository))")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[bean] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
