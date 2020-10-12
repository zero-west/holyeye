package me.zw.step02.order;

import me.zw.step02.AppConfig;
import me.zw.step02.member.Grade;
import me.zw.step02.member.Member;
import me.zw.step02.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    void 주문_생성_테스트() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }
}
