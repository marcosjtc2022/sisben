package com.bahiana.sisben.model.entity.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;
import com.bahiana.sisben.model.entity.ValorMarmita;

public interface SuspensaoElegibilidadeRepository extends PagingAndSortingRepository<SuspensaoElegibilidade, Long>, JpaRepository<SuspensaoElegibilidade, Long> {
	
	
    List<SuspensaoElegibilidade> findByNomeColaboradorContainingOrderByNomeColaboradorAsc(String nomeColaborador);
	
    Optional<SuspensaoElegibilidade> findByMatriculaColaborador(String matriculaColaborador); 	
   
    List<SuspensaoElegibilidade> findByOrderByNomeColaboradorAsc();
    
    @Query("select count(s) from SuspensaoElegibilidade s "
			+ "where :dataSolicitacao  BETWEEN dataInicial AND dataFinal  "
			+ "and  s.matriculaColaborador = :matriculaColaborador")
			Long pesquisarSuspensao(@Param("dataSolicitacao") LocalDate dataSolicitacao,
                    @Param("matriculaColaborador") String matriculaColaborador);

}
