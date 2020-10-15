package me.zw.step03.bean;

import me.zw.step03.discount.DiscountPolicy;
import me.zw.step03.discount.FixedDiscountPolicy;
import me.zw.step03.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void 부모_타입으로_조회시_자식이_여러개면_중복_오류_발생() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    void 자식이_여러개인_부모_타입으로_조회시_빈_이름을_지정하라() {
        DiscountPolicy discountPolicy = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    void 특정_하위_타입으로_지정하는_경우() {
        // 구체적인 클래스로 지정하는 건 좋지 않다.
        RateDiscountPolicy bean = applicationContext.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    void 부모_타입으로_모두_조회하기() {
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    void Object_타입으로_모두_조회하기() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        beansOfType.keySet().forEach(System.out::println);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixedDiscountPolicy();
        }
    }
}
