package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.PerfilDto;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.Perfil;

public interface PerfilService {
	
    Page<Perfil> listarPaginado(Pageable pageable);
	
	Page<Perfil> listarPaginadoSimples( );
	
	Perfil salvar(PerfilDto perfilDto);
	
	Perfil alterar(PerfilDto perfilDto);
	
	void deletar(Perfil perfil);
	
	Optional<Perfil> obterPorId(Long id);
	
	List<Perfil> listarSimplesOrdenadoDescricao();
	
	
	
}
