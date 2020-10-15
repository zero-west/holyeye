package me.zw.step03.bean;

import me.zw.step03.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class XmlAppContextTest {

    ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    void xmlAppContext() {
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

}
