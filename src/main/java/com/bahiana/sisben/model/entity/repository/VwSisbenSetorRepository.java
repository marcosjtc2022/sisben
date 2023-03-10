package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.VwSisbenSetor;

public interface VwSisbenSetorRepository extends JpaRepository<VwSisbenSetor,String> {
	
	@Query("SELECT vw FROM VwSisbenSetor vw Order by vw.codSetor")
	List<VwSisbenSetor> listaSetorOrdenadoPorCodigo();

	@Query("SELECT vw FROM VwSisbenSetor vw Order by vw.descrSetor")
	List<VwSisbenSetor> listaSetorOrdenadoPorDescricao();
	
	@Query("SELECT vw FROM VwSisbenSetor vw WHERE vw.codSetor=:codSetor")
	VwSisbenSetor ObterPorCodigo(@Param("codSetor") String codSetor);
	
	List<VwSisbenSetor> findByCodSetorContainingOrderByCodSetor(String codSetor);
	
	List<VwSisbenSetor> findByDescrSetorContainingOrderByDescrSetorDesc(String descrSetor);
	
	
	
	
	
	

}
