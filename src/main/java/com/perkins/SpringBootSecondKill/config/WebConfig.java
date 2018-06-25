package com.perkins.SpringBootSecondKill.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	UserArgumentResolver userArgumentResolver;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        //允许全部请求跨域
	        registry.addMapping("/**")
	        .allowCredentials(true)
	        .allowedHeaders("Content-Type, *")
	        .allowedMethods("POST","OPTIONS", "GET")
	        .allowedOrigins("http://127.0.0.1:8000")
	        .maxAge(3600);
	    }

}
