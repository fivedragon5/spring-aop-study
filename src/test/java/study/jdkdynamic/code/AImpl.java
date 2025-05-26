package study.jdkdynamic.code;

public class AImpl implements AInterface {
    @Override
    public String call() {
        System.out.println("A 호출");
        return "A";
    }
}
