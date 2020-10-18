package me.zw.step04.autowired;

import me.zw.step04.AutoAppConfig;
import me.zw.step04.discount.DiscountPolicy;
import me.zw.step04.member.Grade;
import me.zw.step04.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AllBeanTest {

    ApplicationContext applicationContext;

    @BeforeEach
    void init() {
        applicationContext = new AnnotationConfigApplicationContext(
                DiscountService.class,
                AutoAppConfig.class
        );
    }

    @Test
    void discountTest() {
        DiscountService discountService = applicationContext.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice1 = discountService.discount(member, 10000, "fixedDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice1).isEqualTo(1000);

        int discountPrice2 = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discountPrice2).isEqualTo(2000);
    }


    static class DiscountService {
        private final Map<String, DiscountPolicy> discountPolicyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> policyList) {
            this.discountPolicyMap = discountPolicyMap;
            this.policyList = policyList;

            System.out.println("discountPolicyMap = " + discountPolicyMap + ", policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
