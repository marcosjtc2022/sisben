package com.bahiana.sisben.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.PerfilDTO;
import com.bahiana.sisben.model.entity.Perfil;

public interface PerfilService {
	
    Page<Perfil> listarPaginado(Pageable pageable);
	
	Page<Perfil> listarPaginadoSimples( );
	
	Perfil salvar(PerfilDTO perfilDto);
	
}
