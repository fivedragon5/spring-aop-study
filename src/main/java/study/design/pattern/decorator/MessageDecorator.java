package study.design.pattern.decorator;

// Decorator
public abstract class MessageDecorator implements Message {
    protected final Message message;

    public MessageDecorator(Message message) {
        this.message = message;
    }
}
