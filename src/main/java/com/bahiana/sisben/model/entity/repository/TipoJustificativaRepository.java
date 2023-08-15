package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.TipoJustificativa;
import com.bahiana.sisben.model.entity.UnidadeAcademica;

public interface TipoJustificativaRepository extends PagingAndSortingRepository<TipoJustificativa, Long>, JpaRepository<TipoJustificativa, Long> {
	
	List<TipoJustificativa> findByOrderByDescricaoAsc();
	
	//Carregar combo na tela de alteração do tipo da justificativa.
	@Query("SELECT distinct tpj.telaFuncao FROM TipoJustificativa tpj"
		 + " where telaFuncao is not null"
		 + " Order by tpj.telaFuncao")
	List<String> listarTelaFuncao();
	
	
	@Query("SELECT tj from TipoJustificativa tj"
			   + " WHERE tj.telaFuncao is not null and"
			   + "       tj.telaFuncao like %:telaFuncao%"
			   + " order by tj.descricao")
		List<TipoJustificativa> listarPorTelaFuncao(@Param("telaFuncao") String telaFuncao);
	
}
