package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.ProgEntVigenteDto;
import com.bahiana.sisben.api.dto.ProgEntVigenteNpDto;
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
			+ "AND Year(pe.dataProgramacao) = Year(:mesAnoProgramacao)"
	        + "AND pe.matriculaColaborador = :matriculaColaborador")
	List<ProgramacaoEntrega> listaProgramacaoEntregaAnoMesMatricula(@Param("mesAnoProgramacao") LocalDate mesAnoProgramacao,
			                              @Param("matriculaColaborador") String matriculaColaborador);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe"
	 	 + " Order by pe.matriculaColaborador, pe.dataProgramacao")
	List<ProgramacaoEntrega> listarProgramacaoEntrega();
	
	
	@Transactional
	@Modifying
	@Query("update ProgramacaoEntrega set dataEntrega = :dataEntrega,"
			+ " uaRealizada = :uaRealizada, "
			+ " idUsuario = :idUsuario,"
			+ " idUsuarioUltimaModificacao = :idUsuarioUltimaModificacao,"
			+ " idUa = :idUa "
			+ " where id = :id")
	void atulizaProgramacaoEntrega(@Param("dataEntrega") LocalDate dataEntrega,
			@Param("uaRealizada") String uaRealizada,
			@Param("idUsuario") Long idUsuario,
			@Param("idUsuarioUltimaModificacao") Long idUsuarioUltimaModificacao,
			@Param("idUa") Long idUa,
			@Param("id") Long id);
	
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
			//+ "       (:uaRealizada is null or pe.uaRealizada LIKE %:uaRealizada% ) and"
			+ "       pe.codSetor IN :strCodSetor "
			+ " order by pe.anoMes,pe.matriculaColaborador   ")
	List<ProgEntVigenteDto> listarProgramacaoEntregaVigenteLiderSetor(
			@Param("matriculaColaborador") String matriculaColaborador,
			@Param("anoMes") String anoMes,
			@Param("codSetor") String codSetor,
		//	@Param("uaRealizada") String uaRealizada,
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
	
	
	

}