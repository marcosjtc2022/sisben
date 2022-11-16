package com.bahiana.sisben.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.FuncionalidadeDto;
import com.bahiana.sisben.api.dto.PerfilFuncionalidadeDto;
import com.bahiana.sisben.model.entity.Funcionalidade;
import com.bahiana.sisben.model.entity.PerfilFuncionalidade;

public interface PerfilFuncionalidadeService {
	
Page<PerfilFuncionalidade> listarPaginado(Pageable pageable);
	
	Page<PerfilFuncionalidade> listarPaginadoSimples( );
	
	PerfilFuncionalidade salvar(PerfilFuncionalidadeDto perfilFuncionalidadeDto);
	
	PerfilFuncionalidade alterar(PerfilFuncionalidadeDto perfilFuncionalidadeDto);
	
	void deletar(PerfilFuncionalidade perfilFuncionalidade);
	
	Optional<PerfilFuncionalidade> obterPorId(Long id);

}
