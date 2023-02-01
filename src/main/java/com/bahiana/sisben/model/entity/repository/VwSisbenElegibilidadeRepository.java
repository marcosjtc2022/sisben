package com.bahiana.sisben.model.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;

public interface VwSisbenElegibilidadeRepository extends JpaRepository<VwSisbenElegibilidade,Long> {
	
	//findByDescricaoContainingOrderByDescricao(String descricao);
	
	List<VwSisbenElegibilidade> findByMatriculaFuncionarioContainingOrderByNomeFuncionarioAsc(String matriculaFuncionario);
	
	List<VwSisbenElegibilidade> findByNomeFuncionarioContainingOrderByNomeFuncionarioAsc(String nomeFuncionario);
	
	List<VwSisbenElegibilidade> findByOrderByNomeFuncionarioAsc();
	
	@Query("SELECT eleg FROM VwSisbenElegibilidade eleg WHERE eleg.matriculaFuncionario=:matriculaFuncionario")
	Optional<VwSisbenElegibilidade> obterPorMatricula(@Param("matriculaFuncionario")  String matriculaFuncionario);
	

}
