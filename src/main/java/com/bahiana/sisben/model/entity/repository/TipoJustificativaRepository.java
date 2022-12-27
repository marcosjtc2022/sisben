package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.TipoJustificativa;
import com.bahiana.sisben.model.entity.UnidadeAcademica;

public interface TipoJustificativaRepository extends PagingAndSortingRepository<TipoJustificativa, Long>, JpaRepository<TipoJustificativa, Long> {
	
	List<TipoJustificativa> findByOrderByDescricaoAsc();

}
