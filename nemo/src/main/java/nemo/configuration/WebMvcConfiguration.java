package nemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nemo.interceptor.AuthorizationInterceptor;
import nemo.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Autowired 
	Environment env;

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
		registry.addInterceptor(new AuthorizationInterceptor(env))
				.excludePathPatterns("/**");
//				.excludePathPatterns("/api/member/login")
//				.excludePathPatterns("/api/member/join")
//				.excludePathPatterns("/api/item/**")
//				.excludePathPatterns("/api/reivew/reviewWrite")
//				.excludePathPatterns("/api/reivew/reviewWrite")
//				.excludePathPatterns("/userstoreinfo");
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .exposedHeaders("jwtToken", "memberInfo");
    }
	

	
//	@Override 
//	public void addCorsMappings(CorsRegistry registry) {
//		
//		CorsConfiguration config = new CorsConfiguration();
//		
//		registry.addMapping("/**")
//			.allowedOrigins("*")
//			.allowedMethods("*")
//			.allowedHeaders("*")
//			.exposedHeaders("jwtToken");
//		
//		headers.add("Access-Control-Expose-Headers", "jwtToken");
//	}
	
	

	
}
