package com.bahiana.sisben.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaMenos24hDto;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.specification.ProgramacaoEntregaSpecification;

public interface ProgramacaoEntregaService {
	
	Page<ProgramacaoEntrega> listarPaginado(Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoEOrdenado(Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoQueryDinamica(ProgramacaoEntregaSpecification programacaoEntregaSpecification, Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoSimples( );
	
    Page<ProgramacaoEntrega> listarProgramacaoPorPeriodo(Pageable pageable, ProgramacaoEntregaDto programacaoEntregaDTO );
	
	ProgramacaoEntrega salvar(ProgramacaoEntregaDto programacaoEntregaDto);
	
	ProgramacaoEntrega salvarMenos24h(ProgramacaoEntregaMenos24hDto programacaoEntregaEntregaMenos24hDto);
	
	ProgramacaoEntrega alterar(ProgramacaoEntregaDto programacaoEntregaDto);
	
	ProgramacaoEntrega salvarLote(ProgramacaoEntregaDto programacaoEntregaDto, char operacao);
	
	ProgramacaoEntrega atualizarLote(ProgramacaoEntregaDto programacaoEntregaDto, char operacao);
	
	Optional<ProgramacaoEntrega> obterPorId(Long id);	
	
	//ProgramacaoEntrega alterarLote(ProgramacaoEntregaDTO programacaoEntregaDto);
	
	//ProgramacaoEntrega toProgramacaoEntrega(ProgramacaoEntregaDto programacaoEntregaDto);
	
	List<ProgramacaoEntrega> concatenaCamposTabela(ProgramacaoEntrega programacaoEntrega, char operacao);
	
	public Iterable<ProgramacaoEntrega> findAll();
	
	Iterable<ProgramacaoEntrega> programacaoDataTable(ProgramacaoEntregaDto programacaoEntregaDTO );
	
	public Iterable<ProgramacaoEntrega> findBymatriculaColaborador(ProgramacaoEntregaDto programacaoEntregaDTO );
	
	long pesquisaJustificativa(Long idJustificativa);
	
	long pesquisaValorMarmita(Long idValor);
	
	long pesquisaUsuarioEntrega(Long idUsuario);
	
	List<ProgramacaoEntrega> listaProgramacao24hOrdenadoData();
	
	List<ProgramacaoEntrega> pesquisaProgramacao24hPorDataOrdenadoData(LocalDate dataSolicitacao, Boolean solicExtra );
	
	
	
		
	

}