package study.design.pattern.strategy;

// 고정 할인 - 구현체
public class FixedDiscountStrategy implements DiscountStrategy {
    @Override
    public int applyDiscount(int price) {
        return price - 1000;
    }
}
