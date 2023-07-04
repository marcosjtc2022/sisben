package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.ProgEntVigenteDto;
import com.bahiana.sisben.api.dto.ProgEntVigenteNpDto;
import com.bahiana.sisben.api.dto.ProgEntVigenteResumoDto;
import com.bahiana.sisben.api.dto.RegistroEntregaDto;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;

public interface ProgramacaoEntregaRepository extends PagingAndSortingRepository<ProgramacaoEntrega, Long>, JpaSpecificationExecutor<ProgramacaoEntrega> {
	
//	Page<ProgramacaoEntrega> findAll(Pageable pageable);
	
//	Page<ProgramacaoEntrega> findAll(ProgramacaoEntregaSpecification programacaoEntregaSpecification, Pageable pageable);
	
//	@Query(value =	"select el.gestor1 as gestor,pe.matriculaColaborador,"
//                   + " el.nomeColaborador, pe.uaPrevista , "
//                   + " pe.uaRealizada,s.descricao, ua.descricao "
//                   + " from Elegibilidade el, ProgramacaoEntrega pe, Setor s, "
//                   + " UnidadeAcademica ua"
//	               + " where pe.matriculaColaborador = el.matriculaColaborador and "
//	               + "       pe.idUa = ua.id and "
//	               + "       pe.matriculaColaborador =:matriculaColaborador and "
//	               + "       pe.uaPrevista =:uaPrevista and "
//	               + "       s.codSetor =:codSetor and "
//                   + " el.codSetor = s.codSetor ")
//	Page<ProgramacaoEntrega> programacaoPorPeriodo(Pageable pageable, 
//			                                       @Param("matriculaColaborador")String matriculaColaborador ,
//			                                       @Param("uaPrevista") String uaPrevista,
//			                                       @Param("codSetor") String codSetor);
//	
	
//	@Query(value =	"select el.gestor1 as gestor,pe.matriculaColaborador,"
//            + " el.nomeColaborador, pe.uaPrevista , "
//            + " pe.uaRealizada,s.descricao, ua.descricao "
//            + " from Elegibilidade el, ProgramacaoEntrega pe, Setor s, "
//            + " UnidadeAcademica ua"
//            + " where pe.matriculaColaborador = el.matriculaColaborador and "
//            + "       pe.idUa = ua.id and "
//            + "       pe.matriculaColaborador =:matriculaColaborador and "
//            + "       pe.uaPrevista =:uaPrevista and "
//            + "       s.codSetor =:codSetor and "
//            + " el.codSetor = s.codSetor ")
//	Iterable<ProgramacaoEntrega>  programacaoDataTable(@Param("matriculaColaborador")String matriculaColaborador ,
//		                                       @Param("uaPrevista") String uaPrevista,
//		                                       @Param("codSetor") String codSetor);
	
	
	public Iterable<ProgramacaoEntrega> findBymatriculaColaboradorAndUaPrevista(String matriculaColaborador,String uaPrevista );
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.idJustificativa=:idJustificativa")
	long pesquisaJustificativa(@Param("idJustificativa")Long idJustificativa);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.idValor=:idValor")
	long pesquisaValorMarmita(@Param("idValor") Long idValor);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.idUsuario=:idUsuario")
	long pesquisaUsuarioEntrega(@Param("idUsuario") Long idUsuario);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe WHERE pe.solicExtra=1 Order by pe.dataSolicitacao")
	List<ProgramacaoEntrega> listaProgramacao24hOrdenadoData();
	
	List<ProgramacaoEntrega> findByDataSolicitacaoAndSolicExtraOrderByDataSolicitacao(LocalDate dataSolicitacao, Boolean solicExtra );
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.stAprov = 1 and pe.id=:id")
	long pesquisaProgramacaoEntregaMenos24hAprovada(@Param("id") Long id);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE  Month(pe.dataProgramacao) = Month(:mesAnoProgramacao)"
			+ "AND Year(pe.dataProgramacao) = Year(:mesAnoProgramacao)"
	        + "AND pe.matriculaColaborador = :matriculaColaborador")
	long pesquisaProgramacaoEntregaAnoMesMatricula(@Param("mesAnoProgramacao") String mesAnoProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe WHERE  Month(pe.dataProgramacao) = Month(:mesAnoProgramacao)"
			+ " AND Year(pe.dataProgramacao) = Year(:mesAnoProgramacao)"
	        + " AND pe.matriculaColaborador = :matriculaColaborador "
	        + " AND ((pe.tipoSolicitacao in ('I','A','E') and pe.stAprov = 1) or (pe.stAprov is null and ( pe.tipoSolicitacao is null or pe.tipoSolicitacao = '' ))  )")
	List<ProgramacaoEntrega> listaProgramacaoEntregaAnoMesMatricula(@Param("mesAnoProgramacao") LocalDate mesAnoProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe"
	 	 + " Order by pe.matriculaColaborador, pe.dataProgramacao")
	List<ProgramacaoEntrega> listarProgramacaoEntrega();
	
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set "
			+ " uaRealizada = :uaRealizada, "
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " dataUltimaModificacao = :dataUltimaModificacao,"
			+ " idUa = :idUa, "
			+ " tipoSolicitacao = :tipoSolicitacao "
			+ " where id = :id")
	void atulizaProgramacaoEntrega(
			@Param("uaRealizada") String uaRealizada,
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("dataUltimaModificacao") LocalDateTime dataUltimaModificacao,
			@Param("idUa") Long idUa,
			@Param("id") Long id,
			@Param("tipoSolicitacao") String tipoSolicitacao);
	
	@Query("select pe.dataEntrega, pe.matriculaColaborador, pe.codSetor, "
			+ " pe.uaRealizada, pe.dataProgramacao"
			+ " from ProgramacaoEntrega pe, Usuario u, Fornecedor f "
			+ " where pe.idUsuario = u.id and "
			+ "       u.idFornecedor = f.id and "
			+ "	      u.id = :id"
		 	+ " Order by pe.matriculaColaborador, pe.dataProgramacao")
		List<ProgramacaoEntrega> pesquisarRegistroEntregaPorUsuario(@Param("id") Long id);
	
	@Query("SELECT distinct new com.bahiana.sisben.api.dto.ProgEntVigenteDto(pe.matriculaColaborador, pe.anoMes,"
			+ " pe.codSetor) "
			+ " FROM ProgramacaoEntrega pe "
			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
			+ "       (:codSetor is null or pe.codSetor = :codSetor)"
			+ " order by pe.anoMes,pe.matriculaColaborador   ")
	List<ProgEntVigenteDto> listarProgramacaoEntregaVigente(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor);
	
	
	@Query("SELECT new com.bahiana.sisben.api.dto.ProgEntVigenteNpDto(vw.matriculaFuncionario, '',"
			+ " vw.codSecao,vw.descSecao,vw.nomeFuncionario  ) "
			+ " FROM VwSisbenElegibilidade vw "
			+ " where (:matriculaColaborador is null or vw.matriculaFuncionario = :matriculaColaborador) and "
			+ "       (:codSetor is null or vw.codSecao = :codSetor) and "
			+ " vw.matriculaFuncionario not in (select pe.matriculaColaborador from ProgramacaoEntrega pe where pe.anoMes = :anoMes )   "
			+ " order by vw.nomeFuncionario,vw.codSecao, vw.matriculaFuncionario    ")
	List<ProgEntVigenteNpDto> listarProgramacaoEntregaVigenteNaoProgramado(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("codSetor") String codSetor,
			@Param("anoMes") String anoMes);
	
	@Query("SELECT distinct pe.anoMes FROM ProgramacaoEntrega pe"
			 + " where pe.anoMes is not null "
		 	 + " Order by pe.anoMes")
	List<String> listarAnoMes();
	
	@Query("SELECT distinct new com.bahiana.sisben.api.dto.ProgEntVigenteDto(pe.matriculaColaborador, pe.anoMes,"
			+ " pe.codSetor) "
			+ " FROM ProgramacaoEntrega pe "
			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
			+ "       (:codSetor is null or pe.codSetor = :codSetor) and"
			+ "       pe.codSetor IN :strCodSetor "
			+ " order by pe.anoMes,pe.matriculaColaborador   ")
	List<ProgEntVigenteDto> listarProgramacaoEntregaVigenteLiderSetor(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
			@Param("strCodSetor") List<String> strCodSetor);
	
	
	@Query("SELECT new com.bahiana.sisben.api.dto.ProgEntVigenteNpDto(vw.matriculaFuncionario, '',"
			+ " vw.codSecao,vw.descSecao,vw.nomeFuncionario  ) "
			+ " FROM VwSisbenElegibilidade vw "
			+ " where (:matriculaColaborador is null or vw.matriculaFuncionario = :matriculaColaborador) and "
			+ "       (:codSetor is null or vw.codSecao = :codSetor) and "
			+ "      vw.codSecao IN :strCodSetor and "
			+ " vw.matriculaFuncionario not in (select pe.matriculaColaborador from ProgramacaoEntrega pe where pe.anoMes = :anoMes )   "
			+ " order by vw.nomeFuncionario,vw.codSecao, vw.matriculaFuncionario    ")
	List<ProgEntVigenteNpDto> listarProgramacaoEntregaVigenteNaoProgramadoLiderSetor(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
			@Param("strCodSetor") List<String> strCodSetor);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE  Month(pe.dataProgramacao) = Month(:dataProgramacao)"
			+ "AND Year(pe.dataProgramacao) = Year(:dataProgramacao)"
			+ "AND Day(pe.dataProgramacao) = Day(:dataProgramacao)"
	        + "AND pe.matriculaColaborador = :matriculaColaborador")
	long pesquisarProgrEntregaDataMatr(@Param("dataProgramacao") String dataProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set tipoSolicitacao = :tipoSolicitacao"
			+ " where id = :id")
	void atulizarProgEntregaTipoSolicitacao(@Param("tipoSolicitacao") String tipoSolicitacao,
			@Param("id") Long id);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe WHERE  Month(pe.dataProgramacao) = Month(:dataProgramacao)"
			+ "AND Year(pe.dataProgramacao) = Year(:dataProgramacao)"
	        + "AND pe.matriculaColaborador = :matriculaColaborador")
	List<ProgramacaoEntrega>  recuperarProgrEntregaDataMatr(@Param("dataProgramacao") String dataProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe   "
	+ " WHERE   pe.idUa = :idUa ")
    long pesquisarProgrEntregaUa(@Param("idUa") Long idUa);
	
	@Transactional
	@Modifying
	@Query("delete from ProgramacaoEntrega"
			+ " where id = :id")
	void apagarProgEntrega(@Param("id") Long id);
	
	@Query("SELECT pe.dataProgramacao FROM ProgramacaoEntrega pe   "
			+ " WHERE   pe.id = :id ")
		    LocalDate pesquisarDataProgramacao(@Param("id") Long id);
	
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe")
	List<ProgramacaoEntrega>  listarParaAprovacao();
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set "
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " dataUltimaModificacao = :dataUltimaModificacao,"
			+ " idUaAlterar = :idUaAlterar, "
			+ " idJustificativa = :idJustificativa, "
			+ " tipoSolicitacao = :tipoSolicitacao "
			+ " where id = :id")
	void atulizaProgEntParaAprovar(
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("dataUltimaModificacao") LocalDateTime dataUltimaModificacao,
			@Param("id") Long id,
			@Param("idUaAlterar") Long idUaAlterar,
			@Param("idJustificativa") Long idJustificativa,
			@Param("tipoSolicitacao") String tipoSolicitacao);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe "
			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
			+ "       (:codSetor is null or pe.codSetor = :codSetor) and"
			+ "       (:idUa is null or pe.idUa = :idUa) and"
			+ "     pe.tipoSolicitacao IN ('I','A','E') "
			+ " order by pe.anoMes,pe.matriculaColaborador ")
	List<ProgramacaoEntrega> listarProgEntAnalise24h(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
			@Param("idUa") Long idUa);
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set stAprov = :stAprov, "
			+ " justReprovacao = :justReprovacao, "
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " dataUltimaModificacao = :dataUltimaModificacao "
			+ " where id = :id")
	void atualizarStatusAnalise24h(
			@Param("stAprov") Boolean stAprov,
			@Param("justReprovacao") String justReprovacao,
			@Param("id") Long id,
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("dataUltimaModificacao") LocalDateTime dataUltimaModificacao);
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set stAprov = :stAprov, "
			+ " uaRealizada = :uaRealizada, "
			+ " idUa = :idUa, "
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " dataUltimaModificacao = :dataUltimaModificacao "
			+ " where id = :id")
	void atualizarStatusAnalise24hAlterarAprov(
			@Param("stAprov") Boolean stAprov,
			@Param("uaRealizada") String uaRealizada,
			@Param("id") Long id,
			@Param("idUa") Long idUa,
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("dataUltimaModificacao") LocalDateTime dataUltimaModificacao);
	
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe   "
			+ " WHERE   pe.id = :id ")
	ProgramacaoEntrega obterProgrEntregaPorId(@Param("id") Long id);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE  Month(pe.dataProgramacao) = Month(:dataProgramacao)"
			+ "AND Year(pe.dataProgramacao) = Year(:dataProgramacao)"
	        + "AND pe.matriculaColaborador = :matriculaColaborador")
	long pesquisarProgrEntregaAnoMesMatr(@Param("dataProgramacao") String dataProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	@Query("SELECT distinct new com.bahiana.sisben.api.dto.ProgEntVigenteResumoDto(pe.matriculaColaborador, pe.anoMes,"
			+ " pe.codSetor, ua.descricao) "
			+ " FROM ProgramacaoEntrega pe, UnidadeAcademica ua "
			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
			+ "       (:codSetor is null or pe.codSetor = :codSetor) and"
			+ "       (:idUa is null or pe.idUa = :idUa) and"
			+ "       pe.idUa = ua.id and"
			+ "       pe.codSetor IN (SELECT sg.codSetor FROM UsuarioSetorGerenciado sg "
			+ "			 WHERE sg.idUsuarioLider=:idUsuarioLider ) and "
			+ " ((pe.tipoSolicitacao in ('I','A','E') and pe.stAprov = 1) or (pe.stAprov is null and ( pe.tipoSolicitacao is null or pe.tipoSolicitacao = '' ))  )"
			+ " order by pe.anoMes,pe.matriculaColaborador   ")
	List<ProgEntVigenteResumoDto> listarProgramacaoEntregaVigenteLiderSetorNovo(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
			@Param("idUsuarioLider") Long idUsuarioLider,
			@Param("idUa") Long idUa
			
			);
	
	
//	@Query("SELECT distinct new com.bahiana.sisben.api.dto.ProgEntVigenteResumoDto(pe.matriculaColaborador, pe.anoMes,"
//			+ " pe.codSetor, ua.descricao) "
//			+ " FROM ProgramacaoEntrega pe, UnidadeAcademica ua "
//			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
//			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
//			+ "       (:codSetor is null or pe.codSetor = :codSetor) and"
//			+ "       (:idUa is null or pe.idUa = :idUa) and"
//			+ "       pe.idUa = ua.id and"
//			+ "       pe.codSetor IN (SELECT sg.codSetor FROM UsuarioSetorGerenciado sg "
//			+ "			 WHERE sg.idUsuarioLider=:idUsuarioLider ) "
//			+ " order by pe.anoMes,pe.matriculaColaborador   ")
//	List<ProgEntVigenteResumoDto> listarProgramacaoEntregaVigenteLiderSetorNovo(
//			@Param("matriculaColaborador") String matriculaColaborador,
//			@Param("anoMes") String anoMes,
//			@Param("codSetor") String codSetor,
//			@Param("idUsuarioLider") Long idUsuarioLider,
//			@Param("idUa") Long idUa
//			
//			);

	

	
	@Query("SELECT new com.bahiana.sisben.api.dto.ProgEntVigenteNpDto(vw.matriculaFuncionario, '',"
			+ " vw.codSecao,vw.descSecao,vw.nomeFuncionario  ) "
			+ " FROM VwSisbenElegibilidade vw "
			+ " where (:matriculaColaborador is null or vw.matriculaFuncionario = :matriculaColaborador) and "
			+ "       (:codSetor is null or vw.codSecao = :codSetor) and "
			+ "      vw.codSecao IN (SELECT sg.codSetor FROM UsuarioSetorGerenciado sg "
			+ "						 WHERE sg.idUsuarioLider=:idUsuarioLider ) and "
			+ " vw.matriculaFuncionario not in (select pe.matriculaColaborador from ProgramacaoEntrega pe where pe.anoMes = :anoMes and "
			+ "((pe.tipoSolicitacao in ('I','A','E') and pe.stAprov = 1) or (pe.stAprov is null and  ( pe.tipoSolicitacao is null or pe.tipoSolicitacao = '' ) ))  )   "
			+ " order by vw.nomeFuncionario,vw.codSecao, vw.matriculaFuncionario    ")
	List<ProgEntVigenteNpDto> listarProgramacaoEntregaVigenteNaoProgramadoLiderSetorNovo(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
			@Param("idUsuarioLider") Long idUsuarioLider);
	
	
//	@Query("SELECT new com.bahiana.sisben.api.dto.ProgEntVigenteNpDto(vw.matriculaFuncionario, '',"
//			+ " vw.codSecao,vw.descSecao,vw.nomeFuncionario  ) "
//			+ " FROM VwSisbenElegibilidade vw "
//			+ " where (:matriculaColaborador is null or vw.matriculaFuncionario = :matriculaColaborador) and "
//			+ "       (:codSetor is null or vw.codSecao = :codSetor) and "
//			+ "      vw.codSecao IN (SELECT sg.codSetor FROM UsuarioSetorGerenciado sg "
//			+ "						 WHERE sg.idUsuarioLider=:idUsuarioLider ) and "
//			+ " vw.matriculaFuncionario not in (select pe.matriculaColaborador from ProgramacaoEntrega pe where pe.anoMes = :anoMes )   "
//			+ " order by vw.nomeFuncionario,vw.codSecao, vw.matriculaFuncionario    ")
//	List<ProgEntVigenteNpDto> listarProgramacaoEntregaVigenteNaoProgramadoLiderSetorNovo(
//			@Param("matriculaColaborador") String matriculaColaborador,
//			@Param("anoMes") String anoMes,
//			@Param("codSetor") String codSetor,
//			@Param("idUsuarioLider") Long idUsuarioLider);
	
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set "
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " dataUltimaModificacao = :dataUltimaModificacao,"
			+ " idJustificativa = :idJustificativa, "
			+ " tipoSolicitacao = :tipoSolicitacao "
			+ " where id = :id")
	void atulizaExcluirProgEntParaAprovar(
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("dataUltimaModificacao") LocalDateTime dataUltimaModificacao,
			@Param("id") Long id,
			@Param("idJustificativa") Long idJustificativa,
			@Param("tipoSolicitacao") String tipoSolicitacao);
	
//	@Query("SELECT distinct new com.bahiana.sisben.api.dto.RegistroEntregaDto(pe.matriculaColaborador, pe.anoMes,"
//			+ " pe.codSetor, ua.descricao, pe.solicExtra) "
//			+ " FROM ProgramacaoEntrega pe, UnidadeAcademica ua "
//			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
//			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
//			+ "       (:codSetor is null or pe.codSetor = :codSetor) and"
//			+ "       (:idUa is null or pe.idUa = :idUa) and"
//			+ "       pe.idUa = ua.id  "
//			+ " order by pe.anoMes,pe.matriculaColaborador   ")
//	List<RegistroEntregaDto> listarProgramacaoEntregaRegistroEntrega(
//			@Param("matriculaColaborador") String matriculaColaborador,
//			@Param("anoMes") String anoMes,
//			@Param("codSetor") String codSetor,
//			@Param("idUa") Long idUa);
	
	@Query("SELECT new com.bahiana.sisben.api.dto.RegistroEntregaDto(pe.id,pe.matriculaColaborador, pe.anoMes,"
			+ " pe.codSetor, ua.descricao, pe.solicExtra, pe.dataProgramacao , pe.dataEntrega, pe.idJustificativa 	) "
			+ " FROM ProgramacaoEntrega pe, UnidadeAcademica ua "
			+ " where (:matriculaColaborador is null or pe.matriculaColaborador = :matriculaColaborador) and "
			+ "       (:anoMes is null or pe.anoMes = :anoMes) and"
			+ "       (:codSetor is null or pe.codSetor = :codSetor) and"
			+ "       (:idUa is null or pe.idUa = :idUa) and"
			+ "       pe.idUa = ua.id  "
			+ " order by pe.anoMes,pe.dataProgramacao,pe.matriculaColaborador   ")
	List<RegistroEntregaDto> listarProgramacaoEntregaRegistroEntrega(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
			@Param("idUa") Long idUa);
	
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set "
			+ " uaRealizada = :uaRealizada, "
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " dataUltimaModificacao = :dataUltimaModificacao,"
			+ " idUa = :idUa, "
			+ " idJustNprogramado = :idJustificativa, "
			+ " dataEntrega = :dataEntrega,"
			+ " idUsuario = :idUsuarioUltimaModificacao "
			+ " where id = :id")
	void registrarEntrega(
			@Param("uaRealizada") String uaRealizada,
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("dataUltimaModificacao") LocalDateTime dataUltimaModificacao,
			@Param("dataEntrega") LocalDate dataEntrega,
			@Param("idUa") Long idUa,
			@Param("idJustificativa") Long idJustificativa,
			@Param("id") Long id);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe WHERE  Month(pe.dataProgramacao) = Month(:dataProgramacao)"
			+ " AND Year(pe.dataProgramacao) = Year(:dataProgramacao)"
			+ " AND Day(pe.dataProgramacao) = Day(:dataProgramacao)"
	        + " AND pe.matriculaColaborador = :matriculaColaborador "
	        + " AND ((pe.tipoSolicitacao in ('I','A','E') and pe.stAprov = 1) or (pe.stAprov is null and ( pe.tipoSolicitacao is null or pe.tipoSolicitacao = '' ))  )")
	ProgramacaoEntrega pesquisarProgrEntregaDataMatrRegistroEntrega(@Param("dataProgramacao") String dataProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	
	
	

}