package study.design.pattern.proxy;

public class RealDoor implements Door {
    @Override
    public void open() {
        System.out.println("RealDoor 문이 열립니다.");
    }

    @Override
    public void close() {
        System.out.println("RealDoor 문이 닫힙니다.");
    }
}
