package com.bahiana.sisben.model.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.VwSisbenFuncionario;

public interface VwSisbenFuncionarioRepository extends JpaRepository<VwSisbenFuncionario,Long>{
	
	Optional<VwSisbenFuncionario> findByMatriculaFuncionarioOrderByNomeFuncionario(String matriculaFuncionario);
	
	@Query("SELECT func FROM VwSisbenFuncionario func WHERE func.matriculaFuncionario=:matriculaFuncionario")
	Optional<VwSisbenFuncionario> obterPorMatricula(@Param("matriculaFuncionario")  String matriculaFuncionario);
	
    
//	@Query(value = "select * from VwSisbenFuncionario", nativeQuery = true)
	@Query(value = "select * FROM [10.71.17.40].[Corpore].[dbo].[SISBEN_Funcionarios]", nativeQuery = true)
    Optional<VwSisbenFuncionario> obterPorMatricula();

}
