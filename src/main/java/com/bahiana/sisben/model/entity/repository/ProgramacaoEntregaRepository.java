package com.bahiana.sisben.model.entity.repository;

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
	
	@Query(value =	"select el.gestor1,pe.matriculaColaborador,"
                   + " el.nomeColaborador, pe.uaPrevista , "
                   + " pe.uaRealizada,s.descricao "
                   + " from Elegibilidade el, ProgramacaoEntrega pe, Setor s "
	               + " where pe.matriculaColaborador = el.matriculaColaborador and "
	               + "       pe.matriculaColaborador =:matriculaColaborador and "
	               + "       pe.uaPrevista =:uaPrevista and "
	               + "       s.codSetor =:codSetor and "
                   + " el.codSetor = s.codSetor ")
	Page<ProgramacaoEntrega> programacaoPorPeriodo(Pageable pageable, 
			                                       @Param("matriculaColaborador")Long matriculaColaborador ,
			                                       @Param("uaPrevista") String uaPrevista,
			                                       @Param("codSetor") String codSetor);
	
//	@Query(value = " select sum(l.valor) from Lancamento l"
//			+ " where l.usuario =:idUsuario and l.tipo =:tipo and  "
//			+ " l.status =:status  "
//			+ "group by l.usuario ")
//	//BigDecimal obterSaldoPorTipoLancamentoEUsuario(@Param("idUsuario") Long idUsuario, @Param("tipo") String tipo);
//	BigDecimal obterSaldoPorTipoLancamentoEUsuarioEStatus(
//			@Param("idUsuario") Usuario idUsuario,
//			@Param("tipo") TipoLancamento tipo,
//			@Param("status") StatusLancamento status);

}