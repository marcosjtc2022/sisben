package com.bahiana.sisben.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
import com.bahiana.sisben.service.VwSisbenFuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class VwSisbenFuncionarioController {
	
	
	@Autowired
	private VwSisbenFuncionarioService vwSisbenFuncionarioService;
	
	
	@GetMapping("/obterPorMatricula/{matriculaFuncionario}")
	public VwSisbenFuncionario obterPorMatricula(@PathVariable("matriculaFuncionario") String matriculaFuncionario) {
		
		//Optional<VwSisbenFuncionario> funcionario = new ArrayList();
		Optional<VwSisbenFuncionario> funcionario = vwSisbenFuncionarioService.ObterPorMatricula(matriculaFuncionario);
		return  funcionario.get();	
				
	}

}
