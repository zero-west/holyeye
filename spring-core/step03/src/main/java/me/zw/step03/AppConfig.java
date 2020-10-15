package me.zw.step03;

import me.zw.step03.discount.DiscountPolicy;
import me.zw.step03.discount.RateDiscountPolicy;
import me.zw.step03.member.MemberRepository;
import me.zw.step03.member.MemberService;
import me.zw.step03.member.MemberServiceImpl;
import me.zw.step03.member.MemoryMemberRepository;
import me.zw.step03.order.OrderService;
import me.zw.step03.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
