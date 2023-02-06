package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.VwSisbenFeriasElegivel;

public interface VwSisbenFeriasElegivelRepository extends JpaRepository<VwSisbenFeriasElegivel,Long> {
	
	
//	@Query("select count(v) from VwSisbenFeriasElegivel v "
//			+ "where :dataSolicitacaoDateTime  BETWEEN dataInicioFerias AND dataFimFerias  "
//			+ "and  matriculaColaborador = :matriculaColaborador")
//			Long pesquisarFeriasElegivel(@Param("dataSolicitacaoDateTime") LocalDateTime dataSolicitacaoDateTime,
//                    @Param("matriculaColaborador") String matriculaColaborador);


}
