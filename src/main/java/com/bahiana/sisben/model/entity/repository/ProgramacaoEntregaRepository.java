package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.ProgramacaoEntrega;

public interface ProgramacaoEntregaRepository extends PagingAndSortingRepository<ProgramacaoEntrega, Long>, JpaSpecificationExecutor<ProgramacaoEntrega> {
	
//	Page<ProgramacaoEntrega> findAll(Pageable pageable);
	
//	Page<ProgramacaoEntrega> findAll(ProgramacaoEntregaSpecification programacaoEntregaSpecification, Pageable pageable);
	
	@Query(value =	"select el.gestor1 as gestor,pe.matriculaColaborador,"
                   + " el.nomeColaborador, pe.uaPrevista , "
                   + " pe.uaRealizada,s.descricao, ua.descricao "
                   + " from Elegibilidade el, ProgramacaoEntrega pe, Setor s, "
                   + " UnidadeAcademica ua"
	               + " where pe.matriculaColaborador = el.matriculaColaborador and "
	               + "       pe.idUa = ua.id and "
	               + "       pe.matriculaColaborador =:matriculaColaborador and "
	               + "       pe.uaPrevista =:uaPrevista and "
	               + "       s.codSetor =:codSetor and "
                   + " el.codSetor = s.codSetor ")
	Page<ProgramacaoEntrega> programacaoPorPeriodo(Pageable pageable, 
			                                       @Param("matriculaColaborador")Long matriculaColaborador ,
			                                       @Param("uaPrevista") String uaPrevista,
			                                       @Param("codSetor") String codSetor);
	
	
	@Query(value =	"select el.gestor1 as gestor,pe.matriculaColaborador,"
            + " el.nomeColaborador, pe.uaPrevista , "
            + " pe.uaRealizada,s.descricao, ua.descricao "
            + " from Elegibilidade el, ProgramacaoEntrega pe, Setor s, "
            + " UnidadeAcademica ua"
            + " where pe.matriculaColaborador = el.matriculaColaborador and "
            + "       pe.idUa = ua.id and "
            + "       pe.matriculaColaborador =:matriculaColaborador and "
            + "       pe.uaPrevista =:uaPrevista and "
            + "       s.codSetor =:codSetor and "
            + " el.codSetor = s.codSetor ")
	Iterable<ProgramacaoEntrega>  programacaoDataTable(@Param("matriculaColaborador")Long matriculaColaborador ,
		                                       @Param("uaPrevista") String uaPrevista,
		                                       @Param("codSetor") String codSetor);
	
	
	public Iterable<ProgramacaoEntrega> findBymatriculaColaboradorAndUaPrevista(Long matriculaColaborador,String uaPrevista );
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.idJustificativa=:idJustificativa")
	long pesquisaJustificativa(@Param("idJustificativa")Long idJustificativa);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.idValor=:idValor")
	long pesquisaValorMarmita(@Param("idValor") Long idValor);
	
	@Query("SELECT COUNT(pe) FROM ProgramacaoEntrega pe WHERE pe.idUsuario=:idUsuario")
	long pesquisaUsuarioEntrega(@Param("idUsuario") Long idUsuario);
	
	@Query("SELECT pe FROM ProgramacaoEntrega pe WHERE pe.solicExtra=1 Order by pe.dataSolicitacao")
	List<ProgramacaoEntrega> listaProgramacao24hOrdenadoData();
	
	List<ProgramacaoEntrega> findByDataSolicitacaoAndSolicExtraOrderByDataSolicitacao(LocalDate dataSolicitacao, Boolean solicExtra );
	
	
	 
	

}