package me.zw.step01.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void 회원가입_테스트() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);

        // then
        assertThat(memberA).isEqualTo(findMember);
    }
}
