package spring.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.aop.internalcall.aop.CallLogAspect;

/**
 * Proxy 방식 AOP 한계
 *  - 스프링은 프록시 방식의 AOP를 사용
 *  - 프록시 방식의 AOP는 메서드 내부 호출에 프록시를 적용할 수 없다.
 */
@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    /**
     * 내부 메소드를 호출 할 경우 AOP가 적용 되지 않음
     *  - 외부에서 호출할땐 proxy를 호출해서 메소드에 접근
     *  - 내부에서 호출할땐 this.internal()을 호출해서 메소드에 접근
     *    - 즉 proxy를 거치지 않고 직접 호출하기에 어드바이스 적용되지 않음 -> AOP가 적용되지 않음
     */
    @Test
    void external() {
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}
