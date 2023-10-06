package com.gonggubox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //Auditing 사용
public class GongGuBoxApplication {

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}


	public static void main(String[] args) {
		SpringApplication.run(GongGuBoxApplication.class, args);
	}

	/**
	 * application.yml 설정으로 대체하였음!!
	 */
//	@Bean
//	RouterFunction<ServerResponse> routerFunction() {
//		return route(GET("/docs"), req ->
//				ServerResponse.temporaryRedirect(URI.create("swagger-ui/index.html")).build());
//	}
}
