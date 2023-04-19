package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.VwSisbenElegibilidadeDto;
import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;

public interface VwSisbenElegibilidadeService {
	
	
    List<VwSisbenElegibilidade> pesquisarPorMatriculaOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
	List<VwSisbenElegibilidade> pesquisarPorNomeOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
	List<VwSisbenElegibilidade> listarElegivelOrdenadoNome();
	
	Optional<VwSisbenElegibilidade> ObterPorMatricula(String matriculaFuncionario);
	
	List<VwSisbenElegibilidade> pesquisarPorMatriculaEliderSetorOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
	List<VwSisbenElegibilidade> pesquisarPorNomeEliderSetorOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
	List<VwSisbenElegibilidade> listarElegivelPorLiderSetorOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
//	Optional<VwSisbenElegibilidade> obterPorMatriculaEliderSetor(
//			@Param("matriculaFuncionario")  String matriculaFuncionario,
//			@Param("idUsuarioLogado") String idUsuarioLogado);
//	
	
	

}
