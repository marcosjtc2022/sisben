package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.TipoJustificativaDto;
import com.bahiana.sisben.model.entity.TipoJustificativa;
import com.bahiana.sisben.model.entity.repository.TipoJustificativaRepository;
import com.bahiana.sisben.service.TipoJustificativaService;

public class TipoJustificativaServiceImpl implements TipoJustificativaService {
	
	@Autowired
	TipoJustificativaRepository tipoJustificativaRepository;

	@Override
	public Page<TipoJustificativa> listarPaginado(Pageable pageable) {
		return this.tipoJustificativaRepository.findAll(pageable);
	}

	@Override
	public Page<TipoJustificativa> listarPaginadoSimples() {
		return this.tipoJustificativaRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	@Transactional
	public TipoJustificativa salvar(TipoJustificativaDto tipoJustificativaDto) {
		TipoJustificativa tipoJustificativa = TipoJustificativaServiceImpl.from(tipoJustificativaDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 tipoJustificativa.setDataUltimaModificacao(dataModificacao);
		 return tipoJustificativaRepository.save(tipoJustificativa);
	}

	@Override
	@Transactional
	public TipoJustificativa alterar(TipoJustificativaDto tipoJustificativaDto) {
		TipoJustificativa tipoJustificativa = TipoJustificativaServiceImpl.from(tipoJustificativaDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 tipoJustificativa.setDataUltimaModificacao(dataModificacao);
		 //tipoJustificativa.setIdUsuarioUltimaModificacao(1L);
		 return tipoJustificativaRepository.save(tipoJustificativa);
	}

	@Override
	public void deletar(TipoJustificativa tipoJustificativa) {
		tipoJustificativaRepository.delete(tipoJustificativa);
	}

	@Override
	public Optional<TipoJustificativa> obterPorId(Long id) {
		return tipoJustificativaRepository.findById(id);
	}
	
	public static TipoJustificativa from(TipoJustificativaDto tipoJustificativaDto) {
		TipoJustificativa tipoJustificativa = new TipoJustificativa();
		LocalDateTime dataModificacao = LocalDateTime.now();
		tipoJustificativaDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(tipoJustificativaDto, tipoJustificativa);
		
		return tipoJustificativa;
	}
	

}
