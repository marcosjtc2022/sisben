package com.bahiana.sisben;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SisbenApplication implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
				.allowedOrigins("*");
		        //.allowedOrigins("http://localhost:3000");
		
	}

	public static void main(String[] args) {
		SpringApplication.run(SisbenApplication.class, args);
	}

}
