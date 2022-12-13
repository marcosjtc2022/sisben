package com.bahiana.sisben.model.entity.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.PerfilFuncionalidade;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;

public interface PerfilFuncionalidadeRepository extends PagingAndSortingRepository<PerfilFuncionalidade, Long>, JpaSpecificationExecutor<PerfilFuncionalidade> {
	
	//Optional evita tratamento de null pointer exception
	Optional<PerfilFuncionalidade> findByIdPerfilAndIdFuncionalidade(Long idPerfil,Long idFuncionalidade );
	
	//Optional evita tratamento de null pointer exception
	Optional<PerfilFuncionalidade> findByIdPerfil(Long idPerfil);
	
	@Query(value =	"delete from PerfilFuncionalidade pf where pf.id =: id")
    void excluirPerfilFuncionalidade(@Param("id")Long id);
//	
//	@Query(value =	"select pf from PerfilFuncionalidade pf where pf.idPerfil=: idPerfil"
//			+ "and idFuncionalidade =: idFuncionalidade " )
//	PerfilFuncionalidade buscarPerfilFuncionalidade(@Param("idPerfil")Long idPerfil);
	
    

}
