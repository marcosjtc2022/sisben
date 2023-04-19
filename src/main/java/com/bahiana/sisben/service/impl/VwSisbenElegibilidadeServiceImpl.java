package com.bahiana.sisben.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bahiana.sisben.api.dto.VwSisbenElegibilidadeDto;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
import com.bahiana.sisben.model.entity.repository.VwSisbenElegibilidadeRepository;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;
import com.bahiana.sisben.service.VwSisbenElegibilidadeService;

public class VwSisbenElegibilidadeServiceImpl implements VwSisbenElegibilidadeService {
	
	@Autowired
	VwSisbenElegibilidadeRepository vwSisbenElegibilidadeRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioSetorGerenciadoService usuarioSetorGerenciadoService;

	@Override
	public List<VwSisbenElegibilidade> pesquisarPorMatriculaOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto) {
           List<VwSisbenElegibilidade> elegivelLista =  this.vwSisbenElegibilidadeRepository.
        		   findByMatriculaFuncionarioContainingOrderByNomeFuncionarioAsc(vwSisbenElegibilidadeDto.getMatriculaFuncionario());
		
		return elegivelLista;
	}

	@Override
	public List<VwSisbenElegibilidade> pesquisarPorNomeOrdenadoNome(VwSisbenElegibilidadeDto VwSisbenElegibilidadeDto) {
		List<VwSisbenElegibilidade> elegivelLista =  this.vwSisbenElegibilidadeRepository.
				findByNomeFuncionarioContainingOrderByNomeFuncionarioAsc(VwSisbenElegibilidadeDto.getNomeFuncionario());
		
		return elegivelLista;
	}
	
	@Override
	public List<VwSisbenElegibilidade> listarElegivelOrdenadoNome() {
		List<VwSisbenElegibilidade> elegivelLista =  this.vwSisbenElegibilidadeRepository.
				findByOrderByNomeFuncionarioAsc();
		
		return elegivelLista;
	}

	@Override
	public Optional<VwSisbenElegibilidade> ObterPorMatricula(String matriculaFuncionario) {
		return vwSisbenElegibilidadeRepository.obterPorMatricula(matriculaFuncionario);
	}

	@Override
	public List<VwSisbenElegibilidade> pesquisarPorMatriculaEliderSetorOrdenadoNome(
			VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto) {
		
		     List<String> listStrCodSetor = usuarioSetorGerenciadoService.
		     concatenaSetoresLider(vwSisbenElegibilidadeDto.getIdUsuarioLogado());
		
			
			 List<VwSisbenElegibilidade> elegivelLista =  this.vwSisbenElegibilidadeRepository.
					pesquisarPorMatriculaEliderSetorOrdenadoNome(vwSisbenElegibilidadeDto.getMatriculaFuncionario(),
							                                     listStrCodSetor);
			 
			
			
		return elegivelLista;
	}

	@Override
	public List<VwSisbenElegibilidade> pesquisarPorNomeEliderSetorOrdenadoNome(
			VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto) {
		
		 List<String> listStrCodSetor = usuarioSetorGerenciadoService.
			     concatenaSetoresLider(vwSisbenElegibilidadeDto.getIdUsuarioLogado());
		
		
		 List<VwSisbenElegibilidade> elegivelLista =  this.vwSisbenElegibilidadeRepository.
				 pesquisarPorNomeEliderSetorOrdenadoNome(vwSisbenElegibilidadeDto.getNomeFuncionario(),
							                                     listStrCodSetor);
			 
			
			
		return elegivelLista;
	}

	@Override
	public List<VwSisbenElegibilidade> listarElegivelPorLiderSetorOrdenadoNome(
			VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto) {
           
		List<String> listStrCodSetor = usuarioSetorGerenciadoService.
			     concatenaSetoresLider(vwSisbenElegibilidadeDto.getIdUsuarioLogado());
		
		
		List<VwSisbenElegibilidade> elegivelLista =  vwSisbenElegibilidadeRepository.listarElegivelPorLiderSetorOrdenadoNome(listStrCodSetor);
		

		
		return elegivelLista;
	}

//	@Override
//	public Optional<VwSisbenElegibilidade> obterPorMatriculaEliderSetor(String matriculaFuncionario,
//			String idUsuarioLogado) {
//		
//		 List<String> listStrCodSetor = usuarioSetorGerenciadoService.
//			     concatenaSetoresLider(idUsuarioLogado);
//
//		
//		 return vwSisbenElegibilidadeRepository.obterPorMatriculaEliderSetor(matriculaFuncionario, listStrCodSetor);
//	}

}
