package spring.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Proxy 방식 AOP 한계 - 대안 1 자기 자신 주입
 *  - 자기 자신을 주입하여 내부 메소드 호출시 자기자신이더라도 프록시를 거치도록 만든다.
 *  - 자기 자신을 생성해서 주입하기 때문 권장 X
 */

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
