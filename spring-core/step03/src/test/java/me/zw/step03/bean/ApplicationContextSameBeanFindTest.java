package me.zw.step03.bean;

import me.zw.step03.member.MemberRepository;
import me.zw.step03.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    void 같은_타입이_둘_이상_있는_경우_테스트() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(MemberRepository.class));
    }

    @Test
    void 같은_타입이_둘_이상_있으면_빈_이름을_지정하라() {
        MemberRepository memberRepository
                = applicationContext.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    void 특정_타입을_모두_조회하기() {
        Map<String, MemberRepository> beansOfType = applicationContext.getBeansOfType(MemberRepository.class);
        beansOfType.keySet().forEach(System.out::println);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
