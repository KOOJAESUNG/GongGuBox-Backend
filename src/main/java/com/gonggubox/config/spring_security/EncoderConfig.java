package com.gonggubox.config.spring_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfig와는 다른 클래스로 작성할 것 -> 이후 순환 참조 문제 발생할 수 있음
 */
@Configuration
public class EncoderConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
