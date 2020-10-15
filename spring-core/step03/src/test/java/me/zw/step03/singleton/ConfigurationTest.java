package me.zw.step03.singleton;

import me.zw.step03.discount.DiscountPolicy;
import me.zw.step03.discount.RateDiscountPolicy;
import me.zw.step03.member.MemberRepository;
import me.zw.step03.member.MemberService;
import me.zw.step03.member.MemberServiceImpl;
import me.zw.step03.member.MemoryMemberRepository;
import me.zw.step03.order.OrderService;
import me.zw.step03.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(Config1.class);
    AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(Config2.class);


    @Configuration
    static class Config1 {
        @Bean
        public MemberService memberService() {
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }

    static class Config2 {
        @Bean
        public MemberService memberService() {
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }
}
