package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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
	
//	@Query("SELECT pe FROM ProgramacaoEntrega pe"
//		 	 + " Group by pe.matriculaColaborador, pe.dataProgramacao , pe.uaPrevista, "
//		 	 + " pe.uaRealizada, pe.idData, pe.idUa, pe.idJustificativa, pe.idValor, pe.idUsuario, pe.id, pe.dataEntrega"	
//			 + " Order by pe.matriculaColaborador, pe.dataProgramacao , pe.uaPrevista, pe.dataSolicitacao, pe.solicExtra, pe.stFerias "
//			 + " pe.uaRealizada, pe.idData, pe.idUa, pe.idJustificativa, pe.idValor, pe.idUsuario, pe.id, pe.dataEntrega, "
//			 + " pe.dataSolicitacao, pe.solicExtra, pe.stFerias " )
//		List<ProgramacaoEntrega> listarProgramacaoEntrega();
	
//	@Query("SELECT pe.dataProgramacao, pe.matriculaColaborador,pe.solicExtra,"
//			+ " vwe.nomeFuncionario, pe.uaRealizada "
//			+ " FROM ProgramacaoEntrega pe, VwSisbenElegibilidade vwe "
//			+ " where vwe.matriculaFuncionario = pe.matriculaColaborador"
//			+ " group by pe.matriculaColaborador, pe.dataProgramacao, pe.uaRealizada,"
//			+ " pe.solicExtra, vwe.nomeFuncionario"
//			+ " order by pe.dataProgramacao,pe.matriculaColaborador, pe.uaRealizada,"
//			+ " pe.solicExtra, vwe.nomeFuncionario" )
//	List<ProgramacaoEntrega> listarProgramacaoEntrega();
	
	 
	

}