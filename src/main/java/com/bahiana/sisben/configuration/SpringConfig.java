package com.bahiana.sisben.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bahiana.sisben.service.CalendarioService;
import com.bahiana.sisben.service.FornecedorService;
import com.bahiana.sisben.service.FuncionalidadeService;
import com.bahiana.sisben.service.JustificativaService;
import com.bahiana.sisben.service.JwtService;
import com.bahiana.sisben.service.PerfilFuncionalidadeService;
import com.bahiana.sisben.service.PerfilService;
import com.bahiana.sisben.service.ProgramacaoEntregaAprovacaoService;
import com.bahiana.sisben.service.RestApiAutenticaUsuarioService;
import com.bahiana.sisben.service.SuspensaoElegibilidadeService;
import com.bahiana.sisben.service.TipoJustificativaService;
import com.bahiana.sisben.service.UnidadeAcademicaService;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;
import com.bahiana.sisben.service.ValorMarmitaService;
import com.bahiana.sisben.service.VwSisbenElegibilidadeService;
import com.bahiana.sisben.service.VwSisbenFeriasElegivelService;
import com.bahiana.sisben.service.VwSisbenFuncionarioService;
import com.bahiana.sisben.service.VwSisbenSetorService;
import com.bahiana.sisben.service.impl.CalendarioServiceImpl;
import com.bahiana.sisben.service.impl.FornecedorServiceImpl;
import com.bahiana.sisben.service.impl.FuncionalidadeServiceImpl;
import com.bahiana.sisben.service.impl.JustificativaServiceImpl;
import com.bahiana.sisben.service.impl.JwtServiceImpl;
import com.bahiana.sisben.service.impl.PerfilFuncionalidadeServiceImpl;
import com.bahiana.sisben.service.impl.PerfilServiceImpl;
import com.bahiana.sisben.service.impl.ProgramacaoEntregaAprovacaoServiceImpl;
import com.bahiana.sisben.service.impl.RestApiAutenticaUsuarioServiceImpl;
import com.bahiana.sisben.service.impl.SecurityUserDetailsService;
import com.bahiana.sisben.service.impl.SuspensaoElegibilidadeServiceImpl;
import com.bahiana.sisben.service.impl.TipoJustificativaServiceImpl;
import com.bahiana.sisben.service.impl.UnidadeAcademicaServiceImpl;
import com.bahiana.sisben.service.impl.UsuarioServiceImpl;
import com.bahiana.sisben.service.impl.UsuarioSetorGerenciadoServiceImpl;
import com.bahiana.sisben.service.impl.ValorMarmitaServiceImpl;
import com.bahiana.sisben.service.impl.VwSisbenElegibilidadeServiceImpl;
import com.bahiana.sisben.service.impl.VwSisbenFeriasElegivelServiceImpl;
import com.bahiana.sisben.service.impl.VwSisbenFuncionarioServiceImpl;
import com.bahiana.sisben.service.impl.VwSisbenSetorServiceImpl;

@Configuration
//@ComponentScan("com.bahiana.sisben.model.entity.repository.FuncionarioInternoRepository")
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
	
	@Bean
	public UnidadeAcademicaService getUnidadeAcademicaService() {
	    return new  UnidadeAcademicaServiceImpl();
	}
	
	@Bean
	public TipoJustificativaService getTipoJustificativaService() {
	    return new  TipoJustificativaServiceImpl();
	}
	
	@Bean
	public JustificativaService getJustificativaService() {
	    return new  JustificativaServiceImpl();
	}
	
	@Bean
	public ValorMarmitaService getValorMarmitaService() {
	    return new  ValorMarmitaServiceImpl();
	}
	
	@Bean
	public FornecedorService getFornecedorService() {
	    return new  FornecedorServiceImpl();
	}
	
	@Bean
	public SuspensaoElegibilidadeService getSuspensaoElegibilidadeService() {
	    return new  SuspensaoElegibilidadeServiceImpl();
	}
	
	@Bean
	public CalendarioService getCalendarioService() {
	    return new  CalendarioServiceImpl();
	}
	
	@Bean
	public VwSisbenFuncionarioService getVwSisbenFuncionarioService() {
	    return new  VwSisbenFuncionarioServiceImpl();
	}
	
	@Bean
	public JwtService getJwtService() {
	    return new  JwtServiceImpl();
	}
	
	
	@Bean
	public SecurityUserDetailsService getSecurityUserDetailsService() {
		return new SecurityUserDetailsService();
		
	}
	
	
	@Bean
	public RestApiAutenticaUsuarioService getRestApiAutenticaUsuarioService() {
	    return new  RestApiAutenticaUsuarioServiceImpl();
	}
	
	@Bean
	public VwSisbenElegibilidadeService getVwSisbenElegibilidadeService() {
	    return new  VwSisbenElegibilidadeServiceImpl();
	}
	
	@Bean
	public VwSisbenFeriasElegivelService getVwSisbenFeriasElegivelService() {
	    return new  VwSisbenFeriasElegivelServiceImpl();
	}
	
	@Bean
	public VwSisbenSetorService getVwSisbenSetorService() {
	    return new  VwSisbenSetorServiceImpl();
	}
	
	@Bean
	public UsuarioSetorGerenciadoService getUsuarioSetorGerenciadoService() {
	    return new  UsuarioSetorGerenciadoServiceImpl();
	}
	
	@Bean
	public ProgramacaoEntregaAprovacaoService getProgramacaoEntregaAprovacaoService() {
	    return new  ProgramacaoEntregaAprovacaoServiceImpl();
	}
	

}