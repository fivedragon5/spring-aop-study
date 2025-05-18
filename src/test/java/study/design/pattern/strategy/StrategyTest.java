package study.design.pattern.strategy;

import org.junit.jupiter.api.Test;

public class StrategyTest {

    @Test
    void test() {
        // 할인 전략 설정
        PaymentService paymentService = new PaymentService();
        // 고정 할인 전략 실행
        paymentService.pay(new FixedDiscountStrategy(), 20000);
        // 퍼센트 할인 전략 실행
        paymentService.pay(new PercentDiscountStrategy(), 20000);
    }
}
