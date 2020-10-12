package me.zw.step02.discount;

import me.zw.step02.member.Grade;
import me.zw.step02.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy {
    private int discountFixedAmount = 1000;


    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixedAmount;
        } else {
            return 0;
        }
    }
}
