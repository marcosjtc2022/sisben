package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.model.entity.Fornecedor;


public interface FornecedorService {
	
    Page<Fornecedor> listarPaginado(Pageable pageable);
	
	Page<Fornecedor> listarPaginadoSimples( );
	
	Fornecedor salvar(FornecedorDto fornecedorDto);
	
	Fornecedor alterar(FornecedorDto fornecedorDto);
	
	void deletar(Fornecedor fornecedor);
	
	Optional<Fornecedor> obterPorId(Long id);
	
	List<Fornecedor> listarSimplesOrdenadoDescricao();
	
	List<Fornecedor> listarPorDescricaoOrdenadoDescricao(FornecedorDto FornecedorDto);

}
