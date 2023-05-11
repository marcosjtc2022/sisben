package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.VwSisbenSetor;

public interface UsuarioSetorGerenciadoService {
	
	
			
	List<UsuarioSetorGerenciado> salvar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto);
	
    List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo(Long idUsuarioLider);
	
	List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao(Long idUsuarioLider);
	
	List<UsuarioSetorGerenciado> alterar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto);
		
	void apagar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto);
	
	long pesquisaUsuarioSetorGerenciado(Long idUsuarioLider);
	
	Optional<UsuarioSetorGerenciado> obterPorId(Long id);
	
	List<String> concatenaSetoresLider(String strIdUsuarioLogado );}
