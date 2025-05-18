package study.design.pattern.templatemethod;

public class ReadData extends DatabaseProcessor {
    @Override
    protected void processData() {
        System.out.println("데이터 읽기");
    }
}
