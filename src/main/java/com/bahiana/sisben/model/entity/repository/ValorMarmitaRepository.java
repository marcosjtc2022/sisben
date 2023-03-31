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
	
	@Query("select v from ValorMarmita v "
			+ "order by dataFinal desc  ")
			List<ValorMarmita> pesquisarValorMaisAtual(@Param("dataFinal") LocalDate dataFinal);
	
	@Query("select v from ValorMarmita v "
		    + "where :dataInicial BETWEEN dataInicial AND dataFinal Or "
			+ "      :dataFinal   BETWEEN dataInicial AND dataFinal ")
	         ValorMarmita pesquisarValorVigencia(@Param("dataInicial") LocalDate dataInicial,
	        		                             @Param("dataFinal") LocalDate dataFinal);
	
	@Query("select v from ValorMarmita v "
			+ "where :dataProgramacao BETWEEN dataInicial AND dataFinal")
	      ValorMarmita obterValorVigencia(@Param("dataProgramacao") LocalDate dataProgramacao);
	
	
	@Query("select COUNT(v) from ValorMarmita v "
			+ "where :dataInicial BETWEEN dataInicial AND dataFinal Or "
			+ "      :dataFinal   BETWEEN dataInicial AND dataFinal ")
	         Integer verificarValorVigencia(@Param("dataInicial") LocalDate dataInicial,
					                        @Param("dataFinal") LocalDate dataFinal);
	
	
	@Query("select v from ValorMarmita v "
		    + "where dataFinal is null ")
	         ValorMarmita pesquisarValorVigenciaAtual();
	
	
	@Query("select count(v) from ValorMarmita v "
		    + "where dataFinal is null ")
	         Integer verificarValorVigenciaAtual();
	
	
	@Query("select COUNT(v) from ValorMarmita v "
			+ "where :dataInicial = dataInicial and "
			+ "      :dataFinal = dataFinal ")
	         Integer verificarVigenciaParaDataInformada(@Param("dataInicial") LocalDate dataInicial,
					                        @Param("dataFinal") LocalDate dataFinal);
	
	
	@Query("select v from ValorMarmita v "
		    + "where dataInicial not BETWEEN :dataInicial AND :dataFinal and "
			+ "      dataFinal not   BETWEEN :dataInicial AND :dataFinal ")
	        List<ValorMarmita> verificarOutrasVigencias(@Param("dataInicial") LocalDate dataInicial,
	        		                             @Param("dataFinal") LocalDate dataFinal);
	

}
