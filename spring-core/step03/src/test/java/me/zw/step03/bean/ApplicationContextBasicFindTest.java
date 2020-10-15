package me.zw.step03.bean;

import me.zw.step03.AppConfig;
import me.zw.step03.member.MemberService;
import me.zw.step03.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    void 빈_이름으로_조회_실패() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("xxxx", MemberService.class));
    }


}
