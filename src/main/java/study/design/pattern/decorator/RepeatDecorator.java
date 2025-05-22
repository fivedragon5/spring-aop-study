package study.design.pattern.decorator;

// Concrete decorator (반복 데코레이터)
public class RepeatDecorator extends MessageDecorator {
    public RepeatDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        return message.getContent() + " " + message.getContent();
    }
}
