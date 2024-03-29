package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.Justificativa;

public interface JustificativaRepository extends PagingAndSortingRepository<Justificativa, Long>, JpaRepository<Justificativa, Long> {
	
	List<Justificativa> findByOrderByDescricaoAsc();
	
	@Query("SELECT COUNT(j) FROM Justificativa j WHERE j.idTipoJustificativa=:idTipoJustificativa")
	long pesquisaTipoJustificativa(@Param("idTipoJustificativa") Long idTipoJustificativa);
	
	
	@Query("SELECT j FROM Justificativa j, TipoJustificativa tj"
		   + " WHERE j.idTipoJustificativa = tj.id and"
		   + "       tj.telaFuncao is not null and"
		   + "       tj.telaFuncao like %:telaFuncao%"
		   + " order by j.descricao")
	List<Justificativa> listarPorTelaFuncao(@Param("telaFuncao") String telaFuncao);

}
