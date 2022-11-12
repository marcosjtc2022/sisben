package com.bahiana.sisben.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bahiana.sisben.service.PerfilService;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.impl.PerfilServiceImpl;
import com.bahiana.sisben.service.impl.UsuarioServiceImpl;

@Configuration
public class SpringConfig {
	
	@Bean
	public UsuarioService getUsuarioService() {
	    return new  UsuarioServiceImpl();
	}
	
	@Bean
	public PerfilService getPerfilService() {
	    return new  PerfilServiceImpl();
	}
	
	
	
	
	

}