package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.PerfilDto;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.repository.PerfilRepository;
import com.bahiana.sisben.service.PerfilService;

public class PerfilServiceImpl implements PerfilService {
	
	@Autowired
	PerfilRepository perfilRepository;

	@Override
	public Page<Perfil> listarPaginado(Pageable pageable) {
		return this.perfilRepository.findAll(pageable);
	}
	
	@Override
	public Page<Perfil> listarPaginadoSimples() {
		return this.perfilRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	public Perfil salvar(PerfilDto perfilDto) {
		 Perfil perfil = PerfilServiceImpl.from(perfilDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 perfil.setDataUltimaModificacao(dataModificacao);
		 return perfilRepository.save(perfil);
	}
	
	@Override
	@Transactional
	public void deletar(Perfil perfil) {
		perfilRepository.delete(perfil);
	}
	
	@Override
	@Transactional
	public Perfil alterar(PerfilDto perfilDto) {
		
		 Perfil perfil = from(perfilDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 perfil.setDataUltimaModificacao(dataModificacao);
		 return perfilRepository.save(perfil);
		
	}
	
	@Override
	public Optional<Perfil> obterPorId(Long id) {
		return perfilRepository.findById(id);
	}
	
	public static Perfil from(PerfilDto perfilDto) {
		Perfil perfil = new Perfil();
		LocalDateTime dataModificacao = LocalDateTime.now();
		perfilDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(perfilDto, perfil);
		
		return perfil;
	}

//	@Override
//	public Optional<Perfil> obterPorIdPerfileIdFuncionalidade(Long idPerfil, Long idFuncionalidade) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
	

}
