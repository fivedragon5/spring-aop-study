package study.design.pattern.strategy;

// 할인 정책을 사용하는 결제 서비스 - Context
public class PaymentService {
    public void pay(DiscountStrategy strategy, int price) {
        int discounted = strategy.applyDiscount(price);
        System.out.println("기존 금액 : " + price + " | 최종 금액 : " + discounted);
    }
}
