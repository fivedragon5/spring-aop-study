package spring.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

/*
    1. 특정 타입 내의 조인 포인트에 대한 매칭을 제한.
    2. 해당 타입이 매칭되면 그 안에 메서드 들이 자동으로 매칭
    3. execution에서 타입부분만 매칭
*/
@Slf4j
public class WithinTest {

    AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void withExact() {
        aspectJExpressionPointcut.setExpression("within(spring.aop.member.MemberServiceImpl)");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinStat() {
        aspectJExpressionPointcut.setExpression("within(spring.aop.member.*Service*)");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinSubPackage() {
        aspectJExpressionPointcut.setExpression("within(spring.aop..*)");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 선정하면 안된다.")
    void withinSuperTypeFalse() {
        aspectJExpressionPointcut.setExpression("within(spring.aop.member.MemberService)");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution은 타입 기반, 인터페이스 선정 가능")
    void executionSuperTypeTrue() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.MemberService.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
