package study.design.pattern.decorator;

// Concrete decorator (별표 데코레이터)
public class StarDecorator extends MessageDecorator {
    public StarDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        return "*" + message.getContent() + "*";
    }
}
