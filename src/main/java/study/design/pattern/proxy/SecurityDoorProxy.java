package study.design.pattern.proxy;

public class SecurityDoorProxy implements Door {

    private final RealDoor realDoor;

    public SecurityDoorProxy() {
        this.realDoor = new RealDoor();
    }

    @Override
    public void open() {
        System.out.println("SecurityDoorProxy: 문을 열기 전에 보안 확인 중...");
        realDoor.open();
    }

    @Override
    public void close() {
        System.out.println("SecurityDoorProxy: 문을 닫기 전에 보안 확인 중...");
        realDoor.close();
    }
}
