package study.design.pattern.templatecallback;

import org.junit.jupiter.api.Test;
import study.design.pattern.strategy.DiscountStrategy;
import study.design.pattern.strategy.FixedDiscountStrategy;
import study.design.pattern.strategy.PaymentService;

class TemplateCallbackTest {

    @Test
    void test() {
        PaymentService paymentService = new PaymentService();
        // DiscountStrategy를 여기서 구현
        paymentService.pay(new DiscountStrategy() {
            @Override
            public int applyDiscount(int price) {
                return price - 2000;
            }
        }, 20000);

        // 람다 식으로 변경 가능
        paymentService.pay(price -> price - 2000, 20000);
    }
}