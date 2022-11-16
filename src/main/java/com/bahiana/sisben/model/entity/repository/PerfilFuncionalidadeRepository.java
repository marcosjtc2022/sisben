package com.bahiana.sisben.model.entity.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.PerfilFuncionalidade;

public interface PerfilFuncionalidadeRepository extends PagingAndSortingRepository<PerfilFuncionalidade, Long>, JpaSpecificationExecutor<PerfilFuncionalidade> {

}
