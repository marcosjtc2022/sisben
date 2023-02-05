package com.bahiana.sisben.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.ValorMarmitaDto;
import com.bahiana.sisben.model.entity.ValorMarmita;

public interface ValorMarmitaService {
	
	
	    Page<ValorMarmita> listarPaginado(Pageable pageable);
		
		Page<ValorMarmita> listarPaginadoSimples( );
		
		ValorMarmita salvar(ValorMarmitaDto valorMarmitaDto);
		
		ValorMarmita alterar(ValorMarmitaDto valorMarmitaDto);
		
		void deletar(ValorMarmita valorMarmita);
		
		Optional<ValorMarmita> obterPorId(Long id);
		
		List<ValorMarmita> listarSimplesOrdenadoValor();
		
		List<ValorMarmita> pesquisarValorMaisAtual(LocalDate dataFinal);

}
