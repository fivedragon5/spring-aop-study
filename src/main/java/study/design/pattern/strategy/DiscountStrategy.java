package study.design.pattern.strategy;

// 할인 정책 - 인터페이스
public interface DiscountStrategy {
    int applyDiscount(int price);
}
