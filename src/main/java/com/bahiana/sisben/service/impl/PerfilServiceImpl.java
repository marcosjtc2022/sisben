package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bahiana.sisben.api.dto.PerfilDTO;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDTO;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
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
	public Perfil salvar(PerfilDTO perfilDto) {
		 Perfil perfil = PerfilServiceImpl.from(perfilDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 perfil.setDataUltimaModificacao(dataModificacao);
		 return perfilRepository.save(perfil);
	}
	
	public static Perfil from(PerfilDTO perfilDto) {
		Perfil perfil = new Perfil();
		BeanUtils.copyProperties(perfilDto, perfil);
		
		return perfil;
	}
	

}
