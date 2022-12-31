package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;

public interface SuspensaoElegibilidadeRepository extends PagingAndSortingRepository<SuspensaoElegibilidade, Long>, JpaRepository<SuspensaoElegibilidade, Long> {
	
	
    List<SuspensaoElegibilidade> findByNomeColaboradorContainingOrderByNomeColaborador(String nomeColaborador);
	
    List<SuspensaoElegibilidade> findByMatriculaColaboradorOrderByNomeColaborador(Long matriculaColaborador); 	
	
	List<SuspensaoElegibilidade> findByOrderByNomeColaboradorAsc();

}
