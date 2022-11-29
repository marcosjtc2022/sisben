package com.bahiana.sisben.model.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.PerfilFuncionalidade;

public interface PerfilFuncionalidadeRepository extends PagingAndSortingRepository<PerfilFuncionalidade, Long>, JpaSpecificationExecutor<PerfilFuncionalidade> {
	
	//Optional evita tratamento de null pointer exception
	Optional<PerfilFuncionalidade> findByIdPerfilAndIdFuncionalidade(Long idPerfil,Long idFuncionalidade );
	
	//Optional evita tratamento de null pointer exception
	Optional<PerfilFuncionalidade> findByIdPerfil(Long idPerfil);

}
