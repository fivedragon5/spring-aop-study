package study.design.pattern.templatemethod;

// 추상 클래스
public abstract class DatabaseProcessor {
    // 템플릿 메서드
    public void execute() {
        System.out.println("데이터 베이스 연결");
        processData();
        System.out.println("데이터 베이스 해제");
    }
    // 추상 메서드
    protected abstract void processData();
}
