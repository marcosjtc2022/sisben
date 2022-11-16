package com.bahiana.sisben.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bahiana.sisben.service.FuncionalidadeService;
import com.bahiana.sisben.service.PerfilFuncionalidadeService;
import com.bahiana.sisben.service.PerfilService;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.impl.FuncionalidadeServiceImpl;
import com.bahiana.sisben.service.impl.PerfilFuncionalidadeServiceImpl;
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

	@Bean
	public FuncionalidadeService getFuncionalidadeService() {
	    return new  FuncionalidadeServiceImpl();
	}
	
	@Bean
	public PerfilFuncionalidadeService getPerfilFuncionalidadeService() {
	    return new  PerfilFuncionalidadeServiceImpl();
	}
	
	
	
	
	

}