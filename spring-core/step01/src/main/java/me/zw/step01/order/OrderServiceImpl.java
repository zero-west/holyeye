package me.zw.step01.order;

import me.zw.step01.discount.DiscountPolicy;
import me.zw.step01.discount.FixedDiscountPolicy;
import me.zw.step01.member.Member;
import me.zw.step01.member.MemberRepository;
import me.zw.step01.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
