package com.bahiana.sisben.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.JustificativaDto;
import com.bahiana.sisben.model.entity.Justificativa;

public interface JustificativaService {
	
	
	 Page<Justificativa> listarPaginado(Pageable pageable);
		
		Page<Justificativa> listarPaginadoSimples( );
		
		Justificativa salvar(JustificativaDto justificativaDto);
		
		Justificativa alterar(JustificativaDto justificativaDto);
		
		void deletar(Justificativa justificativa);
		
		Optional<Justificativa> obterPorId(Long id);

}
