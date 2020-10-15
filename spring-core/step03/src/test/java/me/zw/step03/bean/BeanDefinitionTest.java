package me.zw.step03.bean;

import me.zw.step03.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class BeanDefinitionTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//    GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    void 빈_설정_메타정보_확인() {
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(beanDefinitionName + ": " + beanDefinition);
            }
        }
    }
}
