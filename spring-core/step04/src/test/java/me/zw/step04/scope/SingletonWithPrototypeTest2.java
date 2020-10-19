package me.zw.step04.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonWithPrototypeTest2 {

    AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void init() {
        applicationContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
    }

    @Test
    void prototypeFind() {
        PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        ClientBean clientBean1 = applicationContext.getBean(ClientBean.class);
        assertThat(clientBean1.logic()).isEqualTo(1);

        ClientBean clientBean2 = applicationContext.getBean(ClientBean.class);
        assertThat(clientBean2.logic()).isEqualTo(1);
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        // 스프링에 의존적인 ObjectProvider
//        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // 자바 표준 Provider
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
}
