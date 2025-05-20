package study.design.pattern.proxy;

import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    void test() {
        System.out.println("===== 실제 문 객체 호출 =====");
        Door realDoor = new RealDoor();
        realDoor.open();
        realDoor.close();
        // ===== 실제 문 객체 호출 =====
        // RealDoor 문이 열립니다.
        // RealDoor 문이 닫힙니다.

        System.out.println("===== 프록시 문 객체 호출 =====");
        Door securityDoorProxy = new SecurityDoorProxy();
        securityDoorProxy.open();
        securityDoorProxy.close();
        // ===== 프록시 문 객체 호출 =====
        //SecurityDoorProxy: 문을 열기 전에 보안 확인 중...
        //RealDoor 문이 열립니다.
        //SecurityDoorProxy: 문을 닫기 전에 보안 확인 중...
        //RealDoor 문이 닫힙니다.
    }
}
