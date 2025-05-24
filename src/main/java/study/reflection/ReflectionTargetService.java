package study.reflection;

public class ReflectionTargetService {
    public String publicName;
    private int age;
    protected int protectedYear;

    public void publicMethod() {
        System.out.println("Public 메소드를 실행했습니다.");
    }

    private void privateMethod() {
        System.out.println("Private 메소드를 실행했습니다.");
    }

    protected void protectedMethod() {
        System.out.println("Protected 메소드를 실행했습니다.");
    }
}
