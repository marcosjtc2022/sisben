package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.JustificativaDto;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.TipoJustificativa;

public interface JustificativaService {
	
	
	    Page<Justificativa> listarPaginado(Pageable pageable);
		
		Page<Justificativa> listarPaginadoSimples( );
		
		Justificativa salvar(JustificativaDto justificativaDto);
		
		Justificativa alterar(JustificativaDto justificativaDto);
		
		void deletar(Justificativa justificativa);
		
		Optional<Justificativa> obterPorId(Long id);
		
		List<Justificativa> listarSimplesOrdenadoDescricao();
		
		long pesquisaTipoJustificativa(Long idTipoJustificativa);
		
		List<Justificativa> listarPorTelaFuncao(String telaFuncao);

}
