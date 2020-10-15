package me.zw.step03.discount;

import me.zw.step03.member.Grade;
import me.zw.step03.member.Member;

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
