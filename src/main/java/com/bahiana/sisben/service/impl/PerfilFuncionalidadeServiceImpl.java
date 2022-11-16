package com.bahiana.sisben.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.PerfilFuncionalidadeDto;
import com.bahiana.sisben.model.entity.PerfilFuncionalidade;
import com.bahiana.sisben.model.entity.repository.PerfilFuncionalidadeRepository;
import com.bahiana.sisben.service.PerfilFuncionalidadeService;

public class PerfilFuncionalidadeServiceImpl implements PerfilFuncionalidadeService {
	
	@Autowired
	PerfilFuncionalidadeRepository perfilFuncionalidadeRepository;

	@Override
	public Page<PerfilFuncionalidade> listarPaginado(Pageable pageable) {
		return this.perfilFuncionalidadeRepository.findAll(pageable);
	}

	@Override
	public Page<PerfilFuncionalidade> listarPaginadoSimples() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilFuncionalidade salvar(PerfilFuncionalidadeDto perfilFuncionalidadeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilFuncionalidade alterar(PerfilFuncionalidadeDto perfilFuncionalidadeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(PerfilFuncionalidade perfilFuncionalidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PerfilFuncionalidade> obterPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
