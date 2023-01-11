package com.bahiana.sisben.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
import com.bahiana.sisben.model.entity.repository.VwSisbenFuncionarioRepository;
import com.bahiana.sisben.service.VwSisbenFuncionarioService;

public class VwSisbenFuncionarioServiceImpl implements VwSisbenFuncionarioService {
	
	@Autowired
	VwSisbenFuncionarioRepository vwSisbenFuncionarioRepository;

	@Override
	public Optional<VwSisbenFuncionario> ObterPorMatricula(String matriculaFuncionario) {
		return vwSisbenFuncionarioRepository.obterPorMatricula(matriculaFuncionario);
	}

}
