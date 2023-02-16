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
	
	
	
	@Query("select v from ValorMarmita v "
			+ "where dataInicial >= :dataInicial and dataFinal <= :dataFinal")
	         ValorMarmita pesquisarValorVigencia(@Param("dataInicial") LocalDate dataInicial,
					                       @Param("dataFinal") LocalDate dataFinal);
	
	@Query("select v from ValorMarmita v "
			+ "where :dataProgramacao >= dataInicial and :dataProgramacao <= dataFinal  ")
	      ValorMarmita obterValorVigencia(@Param("dataProgramacao") LocalDate dataProgramacao);
	
	
	@Query("select COUNT(v) from ValorMarmita v "
			+ "where dataInicial >= :dataInicial and dataFinal <= :dataFinal")
	         Integer verificarValorVigencia(@Param("dataInicial") LocalDate dataInicial,
					                       @Param("dataFinal") LocalDate dataFinal);
	
//	@Query("SELECT COUNT(v) from ValorMarmita v"
//			+ " WHERE  Month(v.dataFinal) = Month(:mesAnoProgramacao)"
//			+ "AND Year(v.dataFinal) = Year(:mesAnoProgramacao)")
//	Integer obterValorVigenciaPorAnoMes(@Param("mesAnoProgramacao") String mesAnoProgramacao);
	
//	@Query("SELECT COUNT(v) FROM ValorMarmita v "
//			+ " WHERE  Month(v.dataFinal) => Month(:mesAnoProgramacao)"
//			+ " AND Month(v.dataInicial) => Month(:mesAnoProgramacao)"
//			+ " AND Year(v.dataProgramacao) = Year(:mesAnoProgramacao)")
//	        
//	Integer obterValorVigenciaPorAnoMes(@Param("mesAnoProgramacao") LocalDate mesAnoProgramacao);
	
	

}
