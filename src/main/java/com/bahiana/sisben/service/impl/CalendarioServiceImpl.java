package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.CalendarioDto;
import com.bahiana.sisben.api.dto.JustificativaDto;
import com.bahiana.sisben.model.entity.Calendario;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.repository.CalendarioRepository;
import com.bahiana.sisben.service.CalendarioService;

public class CalendarioServiceImpl implements CalendarioService {
	
	@Autowired
	CalendarioRepository calendarioRepository;

	@Override
	public Page<Calendario> listarPaginado(Pageable pageable) {
		return this.calendarioRepository.findAll(pageable);
	}

	@Override
	public Page<Calendario> listarPaginadoSimples() {
		return this.calendarioRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	public Calendario salvar(CalendarioDto calendarioDto) {
		Calendario calendario = CalendarioServiceImpl.from(calendarioDto);
		return calendarioRepository.save(calendario);
	}

	@Override
	public Calendario alterar(CalendarioDto calendarioDto) {
		Calendario calendario = CalendarioServiceImpl.from(calendarioDto);
		return calendarioRepository.save(calendario);
	}

	@Override
	public void deletar(Calendario calendario) {
		calendarioRepository.delete(calendario);
		
	}

	@Override
	public Optional<Calendario> obterPorId(Long id) {
		return calendarioRepository.findById(id);
	}

	@Override
	public List<Calendario> listarSimplesOrdenadoData() {
		return calendarioRepository.findByOrderByDataEspecialAsc();
	}
	
	public static Calendario from(CalendarioDto calendarioDto) {
		Calendario calendario = new Calendario();
		LocalDateTime dataModificacao = LocalDateTime.now();
		calendarioDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(calendarioDto, calendario);
		
		return calendario;
	}

}
