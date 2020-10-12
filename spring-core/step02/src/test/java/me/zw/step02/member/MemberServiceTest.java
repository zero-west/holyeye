package me.zw.step02.member;

import me.zw.step02.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
