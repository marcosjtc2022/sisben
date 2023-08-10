package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.JustificativaDto;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.repository.JustificativaRepository;
import com.bahiana.sisben.service.JustificativaService;

public class JustificativaServiceImpl implements JustificativaService {
	
	@Autowired
	JustificativaRepository justificativaRepository;

	@Override
	public Page<Justificativa> listarPaginado(Pageable pageable) {
		return this.justificativaRepository.findAll(pageable);
	}

	@Override
	public Page<Justificativa> listarPaginadoSimples() {
		return this.justificativaRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	public Justificativa salvar(JustificativaDto justificativaDto) {
		Justificativa justificativa = JustificativaServiceImpl.from(justificativaDto);
		 //LocalDateTime dataModificacao = LocalDateTime.now();
		 //tipoJustificativa.setDataUltimaModificacao(dataModificacao);
		 return justificativaRepository.save(justificativa);
	}

	@Override
	public Justificativa alterar(JustificativaDto justificativaDto) {
		 Justificativa justificativa = JustificativaServiceImpl.from(justificativaDto);
		 return justificativaRepository.save(justificativa);
	}

	@Override
	public void deletar(Justificativa justificativa) {
		justificativaRepository.delete(justificativa);
	}

	@Override
	public Optional<Justificativa> obterPorId(Long id) {
		return justificativaRepository.findById(id);
	}
	
	public static Justificativa from(JustificativaDto justificativaDto) {
		Justificativa justificativa = new Justificativa();
		LocalDateTime dataModificacao = LocalDateTime.now();
		justificativaDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(justificativaDto, justificativa);
		
		return justificativa;
	}

	@Override
	public List<Justificativa> listarSimplesOrdenadoDescricao() {
		return this.justificativaRepository.findByOrderByDescricaoAsc();
	}

	@Override
	public long pesquisaTipoJustificativa(Long idTipoJustificativa) {
		Long countTipoJustificativa =  this.justificativaRepository.pesquisaTipoJustificativa(idTipoJustificativa);
		
	    return  countTipoJustificativa;
	}

	@Override
	public List<Justificativa> listarPorTelaFuncao(String telaFuncao) {
		return this.justificativaRepository.listarPorTelaFuncao(telaFuncao);
	}

}
