package me.zw.step04.lifecycle.method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class BeanLifeCycleTest {

    ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void init() {
        applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    }

    @Test
    public void lifeCycleTest() {
        NetworkClient networkClient = applicationContext.getBean(NetworkClient.class);
        applicationContext.close();
    }

    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
