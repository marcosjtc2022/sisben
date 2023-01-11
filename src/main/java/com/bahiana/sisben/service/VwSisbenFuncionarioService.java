package com.bahiana.sisben.service;

import java.util.Optional;

import com.bahiana.sisben.model.entity.VwSisbenFuncionario;

public interface VwSisbenFuncionarioService {
	
	
	Optional<VwSisbenFuncionario> ObterPorMatricula(String matriculaFuncionario);
	
	//List<VwSisbenFuncionario> ObterPorMatricula(@Param("String matriculaFuncionario")  String matriculaFuncionario);
	

}
