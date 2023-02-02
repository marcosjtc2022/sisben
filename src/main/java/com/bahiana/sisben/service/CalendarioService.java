package com.bahiana.sisben.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.CalendarioDto;
import com.bahiana.sisben.model.entity.Calendario;

public interface CalendarioService {
	
    Page<Calendario> listarPaginado(Pageable pageable);
	
	Page<Calendario> listarPaginadoSimples( );
	
	Calendario salvar(CalendarioDto calendarioDto);
	
	Calendario alterar(CalendarioDto calendarioDto);
	
	void deletar(Calendario calendario);
	
	Optional<Calendario> obterPorId(Long id);
	
	List<Calendario> listarSimplesOrdenadoData();
	
	Calendario pesquisarPorData(LocalDate dataEspecial);


}
