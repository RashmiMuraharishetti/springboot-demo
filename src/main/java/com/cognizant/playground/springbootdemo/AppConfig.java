package com.cognizant.playground.springbootdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

        @Bean
        public LazyService lazyService() {
            return new LazyService();
        }
}
