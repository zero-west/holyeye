package me.zw.hellospring;

import me.zw.hellospring.aop.TimeTraceAop;
import me.zw.hellospring.repository.*;
import me.zw.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    private final EntityManager entityManager;

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager entityManager, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.entityManager = entityManager;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(entityManager);
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

}
