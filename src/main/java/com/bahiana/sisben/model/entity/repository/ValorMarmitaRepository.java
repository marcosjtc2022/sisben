package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.ValorMarmita;

public interface ValorMarmitaRepository extends PagingAndSortingRepository<ValorMarmita, Long>, JpaRepository<ValorMarmita, Long> {
	
	List<ValorMarmita> findByOrderByVlMarmitaAsc();
	
//	@Query("select v from ValorMarmita v "
//	+ "group by dataFinal, vlMarmita, id, dataInicial , dataFinal , dataUltimaModificacao, idUsuarioUltimaModificacao;  "
//	+ "having max(data_final) >= :dataFinal  "
//	+ "order by dataFinal, vlMarmita, id, dataInicial , dataFinal , dataUltimaModificacao, idUsuarioUltimaModificacao desc  ")
//	List<ValorMarmita> pesquisarValorMaisAtual(@Param("dataFinal") LocalDate dataFinal);
	
	@Query("select v from ValorMarmita v "
			+ "order by dataFinal desc  ")
			List<ValorMarmita> pesquisarValorMaisAtual(@Param("dataFinal") LocalDate dataFinal);
	
	

}
