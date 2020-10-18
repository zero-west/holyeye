package me.zw.step04;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        // 디폴트는 AutoAppConfig 자신이 있는 패키지부터 하위패키지까지 탐색
        basePackages = {"me.zw.step04"}
)
public class AutoAppConfig {
}

