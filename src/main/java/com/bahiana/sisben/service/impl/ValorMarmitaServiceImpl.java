package com.bahiana.sisben.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.ValorMarmitaDto;
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
		LocalDate dataInicial =  LocalDate.parse(valorMarmitaDto.getDataInicial().toString());
		valorMarmitaDto.setDataInicial(dataInicial);
		
		if (valorMarmitaDto.getDataFinal() != null) {
			LocalDate dataFinal = LocalDate.parse(valorMarmitaDto.getDataFinal().toString());
		    valorMarmitaDto.setDataFinal(dataFinal);
		}
		
		BeanUtils.copyProperties(valorMarmitaDto, valorMarmita);
		
		return valorMarmita;
	}

	@Override
	public ValorMarmita pesquisarValorVigencia(LocalDate dataInicial, LocalDate dataFinal) {
		return valorMarmitaRepository.pesquisarValorVigencia(dataInicial, dataFinal);
	}

	@Override
	public Integer verificarValorVigencia(LocalDate dataInicial, LocalDate dataFinal) {
		return valorMarmitaRepository.verificarValorVigencia(dataInicial, dataFinal);
	}

	@Override
	public Integer verificarValorVigenciaAtual() {
		return valorMarmitaRepository.verificarValorVigenciaAtual();
	}

	@Override
	public ValorMarmita pesquisarValorVigenciaAtual() {
		return valorMarmitaRepository.pesquisarValorVigenciaAtual();
	}

	@Override
	public ValorMarmita obterValorVigencia(LocalDate dataProgramacao) {
		return valorMarmitaRepository.obterValorVigencia(dataProgramacao);
	}

	@Override
	public Integer verificarVigenciaParaDataInformada(LocalDate dataInicial, LocalDate dataFinal) {
		return valorMarmitaRepository.verificarVigenciaParaDataInformada(dataInicial, dataFinal);
	}

	@Override
	public List<ValorMarmita> verificarOutrasVigencias(Long id) {
		return valorMarmitaRepository.verificarOutrasVigencias(id);
	}

	@Override
	public Integer pesquisarValorVigenciaExcluiAlterada(LocalDate dataInicial, LocalDate dataFinal, Long id) {
		return valorMarmitaRepository.pesquisarValorVigenciaExcluiAlterada(dataInicial, dataFinal, id);
	}

	@Override
	public LocalDate pesquisarMaiorDataFinal() {
		return valorMarmitaRepository.pesquisarMaiorDataFinal();
	}

	@Override
	public LocalDate pesquisarMenorDataInicial() {
		return valorMarmitaRepository.pesquisarMenorDataInicial();
	}
	

}
