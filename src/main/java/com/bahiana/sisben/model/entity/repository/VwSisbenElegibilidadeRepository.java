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
	
	
	@Query("SELECT eleg FROM VwSisbenElegibilidade eleg WHERE matriculaFuncionario LIKE %:matriculaFuncionario%"
			+ " and eleg.codSecao IN :strCodSetor "
			+ " order by eleg.nomeFuncionario, eleg.matriculaFuncionario ")
	List<VwSisbenElegibilidade> pesquisarPorMatriculaEliderSetorOrdenadoNome(
			@Param("matriculaFuncionario")  String matriculaFuncionario,
			@Param("strCodSetor") List<String> strCodSetor);
	
	@Query("SELECT eleg FROM VwSisbenElegibilidade eleg WHERE nomeFuncionario LIKE %:nomeFuncionario%"
			+ " and eleg.codSecao IN :strCodSetor  "
			+ " order by eleg.nomeFuncionario, eleg.matriculaFuncionario ")
	List<VwSisbenElegibilidade> pesquisarPorNomeEliderSetorOrdenadoNome(
			@Param("nomeFuncionario")  String nomeFuncionario,
			@Param("strCodSetor") List<String> strCodSetor);
	
	
	@Query("SELECT eleg FROM VwSisbenElegibilidade eleg "
			+ "  WHERE eleg.codSecao IN :strCodSetor  "
			+ " order by eleg.nomeFuncionario, eleg.matriculaFuncionario ")
	List<VwSisbenElegibilidade> listarElegivelPorLiderSetorOrdenadoNome(
			@Param("strCodSetor") List<String> strCodSetor);
	
	@Query("SELECT eleg FROM VwSisbenElegibilidade eleg "
			+ "  WHERE eleg.codSecao IN (SELECT sg.codSetor FROM UsuarioSetorGerenciado sg "
			+ "						 WHERE sg.idUsuarioLider=:idUsuarioLider )and"
			+ " eleg.matriculaFuncionario not in (select pe.matriculaColaborador from ProgramacaoEntrega pe where pe.anoMes = :anoMes ) "
			+ " order by eleg.nomeFuncionario, eleg.matriculaFuncionario ")
	List<VwSisbenElegibilidade> listarElegivelNProgPorLiderSetorOrdenadoNome(
			@Param("anoMes") String anoMes,
			@Param("idUsuarioLider") Long idUsuarioLider);	

}
