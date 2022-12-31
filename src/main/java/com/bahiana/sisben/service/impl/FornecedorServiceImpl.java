package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.api.dto.PerfilDto;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.repository.FornecedorRepository;
import com.bahiana.sisben.service.FornecedorService;

public class FornecedorServiceImpl implements FornecedorService  {
	
	@Autowired
	FornecedorRepository fornecedorRepository;

	@Override
	public Page<Fornecedor> listarPaginado(Pageable pageable) {
		return this.fornecedorRepository.findAll(pageable);
	}

	@Override
	public Page<Fornecedor> listarPaginadoSimples() {
		return this.fornecedorRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	public Fornecedor salvar(FornecedorDto fornecedorDto) {
		Fornecedor fornecedor = FornecedorServiceImpl.from(fornecedorDto);
		 return fornecedorRepository.save(fornecedor);
	}

	@Override
	public Fornecedor alterar(FornecedorDto fornecedorDto) {
		Fornecedor fornecedor = FornecedorServiceImpl.from(fornecedorDto);
		 return fornecedorRepository.save(fornecedor);
	}

	@Override
	public void deletar(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}

	@Override
	public Optional<Fornecedor> obterPorId(Long id) {
		return fornecedorRepository.findById(id);
	}

	@Override
	public List<Fornecedor> listarPorDescricaoOrdenadoDescricao(FornecedorDto fornecedorDto ) {
		
		List<Fornecedor> fornecedorLista =  this.fornecedorRepository.findByDescricaoContainingOrderByDescricao(fornecedorDto.getDescricao());
		
		return fornecedorLista;
		
	}

	@Override
	public List<Fornecedor> listarSimplesOrdenadoDescricao() {
		return this.fornecedorRepository.findByOrderByDescricaoAsc();
	}
	
	public static Fornecedor from(FornecedorDto fornecedorDto) {
		Fornecedor fornecedor = new Fornecedor();
		LocalDateTime dataModificacao = LocalDateTime.now();
		fornecedorDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(fornecedorDto, fornecedor);
		
		return fornecedor;
	}

}
