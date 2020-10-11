package me.zw.step01.discount;

import me.zw.step01.member.Grade;
import me.zw.step01.member.Member;

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
