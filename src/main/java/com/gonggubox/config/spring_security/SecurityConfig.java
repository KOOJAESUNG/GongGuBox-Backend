package com.gonggubox.config.spring_security;


import com.gonggubox.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;

    private final MemberService memberService;

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)// UI 쪽 disable
                .csrf(AbstractHttpConfigurer::disable) //cross - site 기능
                .addFilter(corsConfig.corsFilter()) //cross-site에서 도메인 다를 때 허용
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/adminRole/**","/googleOtp/**")
                        .hasAnyRole("ADMIN")
                        .requestMatchers("/memberRole/**","/coolSms/**")
                        .hasAnyRole("MEMBER")
                        .requestMatchers("/sse/**")
                        .hasAnyRole("ADMIN","MEMBER")

                        .requestMatchers("/member/join", "/member/login" ).permitAll() //authorization 항상 null
                        .requestMatchers("/oauth2/code/kakao","/oauth2/code/google" ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").authenticated() //나머지 post 인증 필요
                        .anyRequest().permitAll()
                )
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class) //JwtFilter 먼저 처리

                .build();
    }
}

