package com.gonggubox.config.spring_security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.domain.UserEntity;
import com.gonggubox.repository.admin.AdminRepository;
import com.gonggubox.repository.member.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

// 인가
// BasicAuthenticationFilter는 권한이나 인증이 필요한 요청을 했을 때 무조건 타게 되어있음.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private AdminRepository adminRepository;
    private MemberRepository memberRepository;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, AdminRepository adminRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.adminRepository = adminRepository;
    }

    //인증이나 권한이 필요한 요청시 이 필터를 탐.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        String header = request.getHeader(JwtProperties.jwtHeaderString);
        if (header == null || !header.startsWith(JwtProperties.tokenPrefix)) {
            String refresh = request.getHeader(JwtProperties.refreshHeaderString);
            if (refresh != null) {

                String username = JWT.require(Algorithm.HMAC512(JwtProperties.refreshSecret)).build().verify(refresh)
                        .getClaim("username").asString();

                if (username != null) {
                    UserEntity user;
                    if (username.contains("admin_"))
                        user = adminRepository.findByUsername(username);
                    else user = memberRepository.findByUsername(username);


                    //jwt token 생성
                    String jwtToken = JWT.create()
                            .withSubject(user.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.jwtExpirationTime))
                            .withClaim("id", user.getId())
                            .withClaim("username", user.getUsername())
                            .sign(Algorithm.HMAC512(JwtProperties.jwtSecret));

                    //refresh token 생성
                    String refreshToken = JWT.create()
                            .withSubject(user.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.refreshExpirationTime))
                            .withClaim("id", user.getId())
                            .withClaim("username", user.getUsername())
                            .sign(Algorithm.HMAC512(JwtProperties.refreshSecret));

                    response.addHeader(JwtProperties.jwtHeaderString, jwtToken);
                    response.addHeader(JwtProperties.refreshHeaderString, refreshToken);

                    return;
                }
            }

            chain.doFilter(request, response);
            return;
        }
        System.out.println("header : " + header);
        String token = request.getHeader(JwtProperties.jwtHeaderString)
                .replace(JwtProperties.tokenPrefix, "");

        // 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
        // 내가 SecurityContext에 집적접근해서 세션을 만들때 자동으로 UserDetailsService에 있는 loadByUsername이 호출됨.
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.jwtSecret)).build().verify(token)
                .getClaim("username").asString();


        //세션에 한번 더 값을 저장하는 이유는 아마도 세션의 유지시간이 토큰 만료시간보다 짧기때문인듯??
        if (username != null) {
            UserEntity user;
            if (username.contains("admin_"))
                user = adminRepository.findByUsername(username);
            else user = memberRepository.findByUsername(username);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails, // 나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                    null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                    principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}
