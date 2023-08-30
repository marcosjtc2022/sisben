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
	
	@Query("select count(v) from VwSisbenFeriasElegivel v "
			+ "where matriculaFuncionario = :matriculaColaborador and"
			+ "(Month(dataInicioFerias) = Month(:dataProgramacao) AND Year(dataInicioFerias) = Year(:dataProgramacao) OR "
			+ " Month(dataFimFerias) = Month(:dataProgramacao) AND Year(dataFimFerias) = Year(:dataProgramacao)) ")
			Long pesquisarFeriasPorMatriculaMesAno(@Param("dataProgramacao") String dataProgramacao,
                    @Param("matriculaColaborador") String matriculaColaborador);

}
