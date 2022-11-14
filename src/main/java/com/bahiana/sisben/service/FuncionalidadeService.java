package com.bahiana.sisben.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.FuncionalidadeDto;
import com.bahiana.sisben.api.dto.PerfilDto;
import com.bahiana.sisben.model.entity.Funcionalidade;
import com.bahiana.sisben.model.entity.Perfil;

public interface FuncionalidadeService {
	
Page<Funcionalidade> listarPaginado(Pageable pageable);
	
	Page<Funcionalidade> listarPaginadoSimples( );
	
	Funcionalidade salvar(FuncionalidadeDto funcionalidadeDto);
	
	Funcionalidade alterar(FuncionalidadeDto funcionalidadeDto);
	
	void deletar(Funcionalidade funcionalidade);
	
	Optional<Funcionalidade> obterPorId(Long id);

}
