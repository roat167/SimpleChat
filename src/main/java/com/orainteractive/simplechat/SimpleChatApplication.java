package com.orainteractive.simplechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.orainteractive.simplechat.security.JwtFilter;

//@EnableAutoConfiguration
//@ComponentScan
//@Configuration
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class SimpleChatApplication {
	@Value("${api.path}")
	private String versionpath;
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/"+versionpath+"/*", "/logout");		

		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(SimpleChatApplication.class, args);
	}	
}
