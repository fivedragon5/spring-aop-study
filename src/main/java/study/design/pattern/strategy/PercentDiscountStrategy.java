package study.design.pattern.strategy;

// 퍼센트 할인 - 구현체
public class PercentDiscountStrategy implements DiscountStrategy {
    @Override
    public int applyDiscount(int price) {
        return (int)(price * 0.9); // 10% 할인
    }
}
