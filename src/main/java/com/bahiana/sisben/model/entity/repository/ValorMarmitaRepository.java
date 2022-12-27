package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.bahiana.sisben.model.entity.ValorMarmita;

public interface ValorMarmitaRepository extends PagingAndSortingRepository<ValorMarmita, Long>, JpaRepository<ValorMarmita, Long> {
	
	List<ValorMarmita> findByOrderByVlMarmitaAsc();

}
