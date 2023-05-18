package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.PerfilDto;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.UnidadeAcademica;


public interface UnidadeAcademicaRepository extends PagingAndSortingRepository<UnidadeAcademica, Long>, JpaRepository<UnidadeAcademica,Long> {
	
	List<UnidadeAcademica> findByOrderByDescricaoAsc();
	
	UnidadeAcademica findFirstByDescricaoContainingOrderByDescricao(String descricao);

}
