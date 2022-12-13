package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.PerfilFuncionalidadeDto;
import com.bahiana.sisben.model.entity.PerfilFuncionalidade;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
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
		 PerfilFuncionalidade perfilFuncionalidade = PerfilFuncionalidadeServiceImpl.from(perfilFuncionalidadeDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 perfilFuncionalidade.setDataUltimaModificacao(dataModificacao);
		 return perfilFuncionalidadeRepository.save(perfilFuncionalidade);
	}

	@Override
	public PerfilFuncionalidade alterar(PerfilFuncionalidadeDto perfilFuncionalidadeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(PerfilFuncionalidade perfilFuncionalidade) {
		perfilFuncionalidadeRepository.delete(perfilFuncionalidade);
	}

	@Override
	public Optional<PerfilFuncionalidade> obterPorId(Long id) {
		return perfilFuncionalidadeRepository.findById(id);
	}
	
	public static PerfilFuncionalidade from(PerfilFuncionalidadeDto perfilFuncionalidadeDto) {
		PerfilFuncionalidade perfilFuncionalidade = new PerfilFuncionalidade();
		LocalDateTime dataModificacao = LocalDateTime.now();
		perfilFuncionalidadeDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(perfilFuncionalidadeDto, perfilFuncionalidade);
		
		return perfilFuncionalidade;
	}

	@Override
	public Optional<PerfilFuncionalidade> obterPorIdPerfil(Long idPerfil) {
		// TODO Auto-generated method stub
		return perfilFuncionalidadeRepository.findByIdPerfil(idPerfil);
	}

	@Override
	public Optional<PerfilFuncionalidade> obterPorIdFuncionalidade(Long idFuncionalidade) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void excluirPerfilFuncionalidade(PerfilFuncionalidade perfilFuncionalidade) {
		perfilFuncionalidadeRepository.excluirPerfilFuncionalidade(perfilFuncionalidade.getId());
	}

	@Override
	public Optional<PerfilFuncionalidade> buscarPerfilFuncionalidade(Long idPerfil,Long idFuncionalidade) {
		return	perfilFuncionalidadeRepository.findByIdPerfilAndIdFuncionalidade(idPerfil, idFuncionalidade);
	}

}
