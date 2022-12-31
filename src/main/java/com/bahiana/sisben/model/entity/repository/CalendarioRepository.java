package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.Calendario;

public interface CalendarioRepository extends PagingAndSortingRepository<Calendario, Long>, JpaRepository<Calendario, Long> {
	
	List<Calendario> findByOrderByDataEspecialAsc();

}
