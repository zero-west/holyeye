package me.zw.step03.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

    @Test
    void statefulService_싱글톤_테스트() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        // ThreadA: A 사용자 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB: B 사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();

        assertThat(price).isEqualTo(20000);
    }


    @Test
    void statelessService_싱글톤_테스트() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = applicationContext.getBean(StatelessService.class);
        StatelessService statelessService2 = applicationContext.getBean(StatelessService.class);

        int userAPrice = statelessService1.order("userA", 10000);
        int userBPrice = statelessService2.order("userB", 20000);

        assertThat(userAPrice).isEqualTo(10000);
        assertThat(userBPrice).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

}