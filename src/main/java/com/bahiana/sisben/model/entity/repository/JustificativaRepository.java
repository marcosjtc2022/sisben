package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.Justificativa;

public interface JustificativaRepository extends PagingAndSortingRepository<Justificativa, Long>, JpaRepository<Justificativa, Long> {
	
	List<Justificativa> findByOrderByDescricaoAsc();

}
