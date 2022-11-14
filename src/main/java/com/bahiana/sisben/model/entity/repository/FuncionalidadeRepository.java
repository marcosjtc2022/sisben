package com.bahiana.sisben.model.entity.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.Funcionalidade;
import com.bahiana.sisben.model.entity.Perfil;

public interface FuncionalidadeRepository extends PagingAndSortingRepository<Funcionalidade, Long>, JpaSpecificationExecutor<Funcionalidade>{

}
