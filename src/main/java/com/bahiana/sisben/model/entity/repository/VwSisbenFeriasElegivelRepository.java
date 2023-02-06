package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.VwSisbenFeriasElegivel;

public interface VwSisbenFeriasElegivelRepository extends JpaRepository<VwSisbenFeriasElegivel,Long> {
	
	
	@Query("select count(v) from VwSisbenFeriasElegivel v "
			+ "where :dataSolicitacao BETWEEN CONVERT(DATE, dataInicioFerias) AND CONVERT(DATE, dataFimFerias)   "
			+ "and  matriculaFuncionario = :matriculaColaborador")
			Long pesquisarFeriasElegivel(@Param("dataSolicitacao") LocalDate dataSolicitacao,
                    @Param("matriculaColaborador") String matriculaColaborador);


}
