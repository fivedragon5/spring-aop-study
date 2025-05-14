package spring.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import spring.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    public void printMethod() {
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    public void exactMatch() {
        // public java.lang.String spring.aop.member.MemberServiceImpl.hello(java.lang.String)
        aspectJExpressionPointcut.setExpression("execution(public String spring.aop.member.MemberServiceImpl.hello(String))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        /*
            1. 접근제어자 : public
            2. 반환타입 : String
            3. 선언타입 : Spring.aop.member.MemberServiceImpl
            4. 메서드이름 : hello
            5. 파라미터 : String
            6. 예외 : 생략
         */
    }

    @Test
    void allMatch() {
        aspectJExpressionPointcut.setExpression("execution(* *(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        /*
            1. 접근제어자 : 생략
            2. 반환타입 : *
            3. 선언타입 : 생략
            4. 메서드이름 : *
            5. 파라미터 : (..)
            6. 예외 : 생략
         */
    }

    @Test
    void nameMatch() {
        aspectJExpressionPointcut.setExpression("execution(* hello(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        aspectJExpressionPointcut.setExpression("execution(* hel*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        aspectJExpressionPointcut.setExpression("execution(* *el*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar3() {
        aspectJExpressionPointcut.setExpression("execution(* nono(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch1() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.MemberServiceImpl.hello(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.*.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactFalse() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.*.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    /*
        패키지에서 ., .. 의 차이
        1. .    : 정확하게 해당 위치의 패키지
        2. ..   : 해당 위치의 패키지와 그 하위 패키지도 포함
     */

    @Test
    void packageExactSubPackage1() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member..*.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactSubPackage2() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop..*.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.MemberServiceImpl.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        // 부모타입으로 해도 true
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.MemberService.*(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        // 자식타입으로 해도 true
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat(aspectJExpressionPointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        // 인터페이스에 선언한 것만 매칭가능
        aspectJExpressionPointcut.setExpression("execution(* spring.aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat(aspectJExpressionPointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    // String 타입의 파라미터 허용
    // (String)
    @Test
    void argsMatch() {
        aspectJExpressionPointcut.setExpression("execution(* *(String))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    // 파라미터가 없어야 함
    // ()
    @Test
    void argsMatchNoArgs() {
        aspectJExpressionPointcut.setExpression("execution(* *())");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    // 정확히 하나의 파라미터 허용, 모든 타입 허용
    // (Xxx)
    @Test
    void argsMatchStar() {
        aspectJExpressionPointcut.setExpression("execution(* *(*))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    // 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    // (), (Xxx), (Xxx, Xxx)
    @Test
    void argsMatchAll() {
        aspectJExpressionPointcut.setExpression("execution(* *(..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    // String 타입을 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    // (String), (String, Xxx), (String, Xxx, Xxx)
    @Test
    void argsMatchComplex() {
        aspectJExpressionPointcut.setExpression("execution(* *(String, ..))");
        Assertions.assertThat(aspectJExpressionPointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
