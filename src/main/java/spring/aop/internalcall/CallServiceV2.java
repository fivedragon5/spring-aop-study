package spring.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Proxy 방식 AOP 한계 - 대안 2 지연 조회
 *  - 스프링 빈을 지연 조회해서 사용 ObjectProvider(Provider), ApplicationContext 를 사용
 *  - ObjectProvider는 객체를 스프링 컨테이너에서 조회하는 것을 스프링 빈 생성 시점이 아니 실제 객체를 사용하는 시점으로 지연할 수 있음
 *  - callServiceV2Provider.getObject()를 호출하는 시점에 스프링 컨테이에서 빈을 조회한다
 *  - 여기서는 자기 자신을 주입 받는 것이 아니기 때문에 순환 참조가 발생하지 않는다.
 */

@Slf4j
@Component
public class CallServiceV2 {

//    private final ApplicationContext applicationContext;
    // applicationContext 는 기능이 많고 무겁기 때문에 ObjectProvider 사용 권장
    private final ObjectProvider<CallServiceV2> callServiceV2Provider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceV2Provider) {
        this.callServiceV2Provider = callServiceV2Provider;
    }

    public void external() {
        log.info("call external");
        //CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class); // 외부 메서드 호출
        CallServiceV2 callServiceV2 = callServiceV2Provider.getObject(); // 외부 메서드 호출
        callServiceV2.internal(); // 외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
