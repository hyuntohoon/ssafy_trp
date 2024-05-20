package com.ssafy.config;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@MapperScan(basePackages = { "com.ssafy.*.model.dao" })
public class WebMvcConfiguration implements WebMvcConfigurer {

	// TODO: 10. Interceptor 등록

	// TODO: 12. CORS 설정
	// maxAge 관련 블로그
	// https://velog.io/@wjdwl002/CORS의-기본-개념과-동작-방식부제-Preflight-요청이란
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(),
						HttpMethod.HEAD.name(),
						HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
				.maxAge(1800); // 1800초 동안 preflight 결과를 캐시에 저장
	}
}
