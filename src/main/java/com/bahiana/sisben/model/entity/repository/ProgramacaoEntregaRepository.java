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
	

}