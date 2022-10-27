package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDTO;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.specification.ProgramacaoEntregaSpecification;

public interface ProgramacaoEntregaService {
	
	Page<ProgramacaoEntrega> listarPaginado(Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoEOrdenado(Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoQueryDinamica(ProgramacaoEntregaSpecification programacaoEntregaSpecification, Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoSimples( );
	
    Page<ProgramacaoEntrega> listarProgramacaoPorPeriodo(Pageable pageable, ProgramacaoEntregaDTO programacaoEntregaDTO );
	
	ProgramacaoEntrega salvar(ProgramacaoEntregaDTO programacaoEntregaDto);
	
	ProgramacaoEntrega alterar(ProgramacaoEntregaDTO programacaoEntregaDto);
	
	ProgramacaoEntrega salvarLote(ProgramacaoEntregaDTO programacaoEntregaDto, char operacao);
	
	ProgramacaoEntrega atualizarLote(ProgramacaoEntregaDTO programacaoEntregaDto, char operacao);
	
	Optional<ProgramacaoEntrega> obterPorId(Long id);	
	
	//ProgramacaoEntrega alterarLote(ProgramacaoEntregaDTO programacaoEntregaDto);
	
	ProgramacaoEntrega toProgramacaoEntrega(ProgramacaoEntregaDTO programacaoEntregaDto);
	
	List<ProgramacaoEntrega> concatenaCamposTabela(ProgramacaoEntrega programacaoEntrega, char operacao);

}