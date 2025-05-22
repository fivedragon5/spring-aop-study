package study.design.pattern.decorator;

import org.junit.jupiter.api.Test;

class DecoratorTest {
    @Test
    void test() {
        Message message = new SimpleMessage("Hello FIVE");

        // 반복 데코레이터
        Message repeatDecorator = new RepeatDecorator(message);
        System.out.println(repeatDecorator.getContent());
        // 출력 : Hello FIVE Hello FIVE

        // 별 데코레이터
        Message startDecorator = new StarDecorator(message);
        System.out.println(startDecorator.getContent());
        // 출력 : *Hello FIVE*

        // 중첩 데코레이터 - 1
        Message repeatAndStartDecorator = new RepeatDecorator(new StarDecorator(message));
        System.out.println(repeatAndStartDecorator.getContent());
        // 출력 : *Hello FIVE* *Hello FIVE*

        // 중첩 데코레이터 - 2
        Message startAndRepeatDecorator = new StarDecorator(new RepeatDecorator(message));
        System.out.println(startAndRepeatDecorator.getContent());
        // 출력 : *Hello FIVE Hello FIVE*
    }
}