package com.bahiana.sisben.model.entity.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.TipoJustificativa;

public interface TipoJustificativaRepository extends PagingAndSortingRepository<TipoJustificativa, Long>, JpaSpecificationExecutor<Perfil> {

}
