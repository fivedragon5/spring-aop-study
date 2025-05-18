package study.design.pattern.templatemethod;

public class SaveData extends DatabaseProcessor {
    @Override
    protected void processData() {
        System.out.println("데이터 저장");
    }
}
