package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.ValorMarmitaDto;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.ValorMarmita;
import com.bahiana.sisben.model.entity.repository.ValorMarmitaRepository;
import com.bahiana.sisben.service.ValorMarmitaService;

public class ValorMarmitaServiceImpl implements ValorMarmitaService {
	
	@Autowired
	ValorMarmitaRepository valorMarmitaRepository;

	@Override
	public Page<ValorMarmita> listarPaginado(Pageable pageable) {
		return this.valorMarmitaRepository.findAll(pageable);
	}

	@Override
	public Page<ValorMarmita> listarPaginadoSimples() {
		return this.valorMarmitaRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	public ValorMarmita salvar(ValorMarmitaDto valorMarmitaDto) {
		 ValorMarmita valorMarmita = ValorMarmitaServiceImpl.from(valorMarmitaDto);
		 return valorMarmitaRepository.save(valorMarmita);
	}

	@Override
	public ValorMarmita alterar(ValorMarmitaDto valorMarmitaDto) {
		ValorMarmita valorMarmita = ValorMarmitaServiceImpl.from(valorMarmitaDto);
		return valorMarmitaRepository.save(valorMarmita);

	}

	@Override
	public void deletar(ValorMarmita valorMarmita) {
		valorMarmitaRepository.delete(valorMarmita);
	}

	@Override
	public Optional<ValorMarmita> obterPorId(Long id) {
		return valorMarmitaRepository.findById(id);
	}

	@Override
	public List<ValorMarmita> listarSimplesOrdenadoValor() {
		return valorMarmitaRepository.findByOrderByVlMarmitaAsc();
	}
	
	
	public static ValorMarmita from(ValorMarmitaDto valorMarmitaDto) {
		ValorMarmita valorMarmita = new ValorMarmita();
		LocalDateTime dataModificacao = LocalDateTime.now();
		valorMarmitaDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(valorMarmitaDto, valorMarmita);
		
		return valorMarmita;
	}
	
	

}
