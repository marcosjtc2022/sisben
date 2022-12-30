package com.bahiana.sisben.model.entity.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;

public interface PerfilRepository extends PagingAndSortingRepository<Perfil, Long>, JpaRepository<Perfil, Long> {
	
	List<Perfil> findByOrderByDescricaoAsc();

}