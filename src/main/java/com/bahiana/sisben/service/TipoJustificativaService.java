package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.TipoJustificativaDto;
import com.bahiana.sisben.model.entity.TipoJustificativa;
import com.bahiana.sisben.model.entity.UnidadeAcademica;

public interface TipoJustificativaService {
	
	    Page<TipoJustificativa> listarPaginado(Pageable pageable);
		
		Page<TipoJustificativa> listarPaginadoSimples( );
		
		TipoJustificativa salvar(TipoJustificativaDto TipoJustificativaDto);
		
		TipoJustificativa alterar(TipoJustificativaDto TipoJustificativaDto);
		
		void deletar(TipoJustificativa TipoJustificativa);
		
		Optional<TipoJustificativa> obterPorId(Long id);
		
		List<TipoJustificativa> listarSimplesOrdenadoDescricao();
		
		List<String> listarTelaFuncao();

}
