package me.zw.step02;

import me.zw.step02.discount.DiscountPolicy;
import me.zw.step02.discount.FixedDiscountPolicy;
import me.zw.step02.discount.RateDiscountPolicy;
import me.zw.step02.member.MemberRepository;
import me.zw.step02.member.MemberService;
import me.zw.step02.member.MemberServiceImpl;
import me.zw.step02.member.MemoryMemberRepository;
import me.zw.step02.order.OrderService;
import me.zw.step02.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
