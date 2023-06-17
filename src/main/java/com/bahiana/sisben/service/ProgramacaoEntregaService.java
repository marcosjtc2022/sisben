package com.bahiana.sisben.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaAvulsaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.response.ProgEntVigenteResponse;
import com.bahiana.sisben.api.response.RegistroEntregaResponse;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.specification.ProgramacaoEntregaSpecification;

public interface ProgramacaoEntregaService {
	
	Page<ProgramacaoEntrega> listarPaginado(Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoEOrdenado(Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoQueryDinamica(ProgramacaoEntregaSpecification programacaoEntregaSpecification, Pageable pageable);
	
	Page<ProgramacaoEntrega> listarPaginadoSimples( );
	
	ProgramacaoEntrega salvar(ProgramacaoEntregaDto programacaoEntregaDto);
	
	ProgramacaoEntrega salvarProgramacaoAvulsa(ProgramacaoEntregaAvulsaDto programacaoEntregaAvulsaDto);
	
	ProgramacaoEntrega autorizar(ProgramacaoEntregaDto ProgramacaoEntregaDto);
	
	ProgramacaoEntrega atualizarMenos24h(ProgramacaoEntregaAvulsaDto programacaoEntregaMenos24hDto);
	
	void alterar(ProgramacaoEntregaDto programacaoEntregaDto);
	
	void apagar(ProgramacaoEntrega programacaoEntrega);
	
	Optional<ProgramacaoEntrega> obterPorId(Long id);	
	
	ProgramacaoEntrega obterPorId2(Long id);
	
	public Iterable<ProgramacaoEntrega> findAll();
	
	public Iterable<ProgramacaoEntrega> findBymatriculaColaborador(ProgramacaoEntregaDto programacaoEntregaDTO );
	
	long pesquisaJustificativa(Long idJustificativa);
	
	long pesquisaValorMarmita(Long idValor);
	
	long pesquisaUsuarioEntrega(Long idUsuario);
	
	long pesquisaProgramacaoEntregaMenos24hAprovada(Long id);
	
	List<ProgramacaoEntrega> listaProgramacao24hOrdenadoData();
	
	List<ProgramacaoEntrega> pesquisaProgramacao24hPorDataOrdenadoData(LocalDate dataSolicitacao, Boolean solicExtra );
	
	List<ProgramacaoEntrega> salvarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto);
	
	long pesquisaValorMarmita(String mesAnoProgramacao, String matriculaColaborador);
	
	List<ProgramacaoEntrega> alterarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto);
	
	List<ProgramacaoEntrega> listaProgramacaoEntregaAnoMesMatricula(ProgramacaoEntregaDto programacaoEntregaDto);
	
	void apagarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto);
	
	void registrarEntrega(ProgramacaoEntregaDto programacaoEntregaDto);
	
	List<ProgramacaoEntrega> listarProgramacaoEntrega();
	
	List<ProgramacaoEntrega> listarComFiltros(ProgramacaoEntregaSpecification programacaoEntregaSpecification);
	
	List<ProgramacaoEntrega> listarRegistroEntregaPorUsuario(ProgramacaoEntregaSpecification programacaoEntregaSpecification);
	
	List<ProgEntVigenteResponse> listarProgramacaoEntregaVigente(String matriculaColaborador, String anoMes,
			                                                      String codSetor);
	
	List<String> listarAnoMes();
	
	List<ProgEntVigenteResponse> listarProgramacaoEntregaVigenteLiderSetor(String matriculaColaborador, String anoMes,
           String codSetor, String idUsuarioLogado, String idUa);
	
	long pesquisarProgrEntregaDataMatr(String dataProgramacao,String matriculaColaborador);
	
	List<ProgramacaoEntrega>  recuperarProgrEntregaDataMatr(String dataProgramacao,String matriculaColaborador);
	
	List<ProgramacaoEntrega>  copiarProgramacaoEntrega(String dataProgramacao, 
			String matriculaColaboradorOrigem,
            String matriculaColaboradorDestino,
            String idUsuarioLogado);
	
	 long pesquisarProgrEntregaUa(Long idUa);
	 
	 List<ProgramacaoEntrega> listarProgEntAnalise24h(String matriculaColaborador, String anoMes,
             String codSetor, Long idUa);
	 
	 void atualizarStatusAnalise24h(ProgramacaoEntregaDto programacaoEntregaDto);
	 
	 List<ProgramacaoEntrega>  copiarProgramacaoEntregaVariasMatriculas(String dataProgramacao, 
				String matriculaColaboradorOrigem,
	            String matriculaColaboradoresDestino,
	            String idUsuarioLogado);
	 
	 List<RegistroEntregaResponse> listarRegistroEntrega(
             String matriculaColaborador,
             String anoMes,
             String codSetor,
             String idUa);
	 
	 
	

}