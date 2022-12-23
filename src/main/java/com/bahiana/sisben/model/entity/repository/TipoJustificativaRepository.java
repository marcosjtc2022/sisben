package com.bahiana.sisben.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.TipoJustificativa;

public interface TipoJustificativaRepository extends PagingAndSortingRepository<TipoJustificativa, Long>, JpaRepository<TipoJustificativa, Long> {

}
