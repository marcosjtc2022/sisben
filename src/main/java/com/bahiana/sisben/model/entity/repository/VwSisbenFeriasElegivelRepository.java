package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.VwSisbenFeriasElegivel;

public interface VwSisbenFeriasElegivelRepository extends JpaRepository<VwSisbenFeriasElegivel,Long> {
	
	
//	@Query("select count(s) from SuspensaoElegibilidade s "
//			+ "where :dataSolicitacao  BETWEEN dataInicial AND dataFinal  "
//			+ "and  s.matriculaColaborador = :matriculaColaborador")
//			Long pesquisarSuspensao(@Param("dataSolicitacao") LocalDate dataSolicitacao,
//                    @Param("matriculaColaborador") String matriculaColaborador);


}
