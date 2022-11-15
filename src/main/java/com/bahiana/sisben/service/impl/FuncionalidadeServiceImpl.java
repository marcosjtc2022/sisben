package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.FuncionalidadeDto;
import com.bahiana.sisben.model.entity.Funcionalidade;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.repository.FuncionalidadeRepository;
import com.bahiana.sisben.service.FuncionalidadeService;

public class FuncionalidadeServiceImpl implements FuncionalidadeService {
	
	@Autowired
	FuncionalidadeRepository funcionalidadeRepository;

	@Override
	public Page<Funcionalidade> listarPaginado(Pageable pageable) {
		return this.funcionalidadeRepository.findAll(pageable);
	}
	

	@Override
	public Page<Funcionalidade> listarPaginadoSimples() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionalidade salvar(FuncionalidadeDto funcionalidadeDto) {
		 Funcionalidade funcionalidade = FuncionalidadeServiceImpl.from(funcionalidadeDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 funcionalidade.setDataUltimaModificacao(dataModificacao);
		 return funcionalidadeRepository.save(funcionalidade);
	}

	@Override
	public Funcionalidade alterar(FuncionalidadeDto funcionalidadeDto) {
		 Funcionalidade funcionalidade = FuncionalidadeServiceImpl.from(funcionalidadeDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 funcionalidade.setDataUltimaModificacao(dataModificacao);
		 return funcionalidadeRepository.save(funcionalidade);
	}

	@Override
	public void deletar(Funcionalidade funcionalidade) {
		funcionalidadeRepository.delete(funcionalidade);
	}

	@Override
	public Optional<Funcionalidade> obterPorId(Long id) {
		return funcionalidadeRepository.findById(id);
	}
	
	public static Funcionalidade from(FuncionalidadeDto funcionalidadeDto) {
		Funcionalidade funcionalidade = new Funcionalidade();
		LocalDateTime dataModificacao = LocalDateTime.now();
		funcionalidadeDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(funcionalidadeDto, funcionalidade);
		
		return funcionalidade;
	}

}
