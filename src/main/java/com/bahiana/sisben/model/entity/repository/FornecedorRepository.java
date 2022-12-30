package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.Perfil;


public interface FornecedorRepository extends PagingAndSortingRepository<Fornecedor, Long>, JpaRepository<Fornecedor, Long> {
	
	List<Fornecedor> findByDescricaoContainingOrderByDescricao(String descricao);
	
	List<Fornecedor> findByOrderByDescricaoAsc();

}
