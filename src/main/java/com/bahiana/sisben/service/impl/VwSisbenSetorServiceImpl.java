package com.bahiana.sisben.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bahiana.sisben.api.dto.VwSisbenSetorDto;
import com.bahiana.sisben.model.entity.VwSisbenSetor;
import com.bahiana.sisben.model.entity.repository.VwSisbenSetorRepository;
import com.bahiana.sisben.service.VwSisbenSetorService;

public class VwSisbenSetorServiceImpl implements VwSisbenSetorService {
	
	@Autowired
	VwSisbenSetorRepository vwSisbenSetorRepository;

	@Override
	public List<VwSisbenSetor> listaSetorOrdenadoPorCodigo() {
		return vwSisbenSetorRepository.listaSetorOrdenadoPorCodigo();
	}

	@Override
	public List<VwSisbenSetor> listaSetorOrdenadoPorDescricao() {
		return vwSisbenSetorRepository.listaSetorOrdenadoPorDescricao();
	}

	@Override
	public VwSisbenSetor ObterPorCodigo(String codSetor) {
		return vwSisbenSetorRepository.ObterPorCodigo(codSetor);
	}

	@Override
	public List<VwSisbenSetor> pesquisarComLikePorCodSetorOrdenadoPorCodSetor(VwSisbenSetorDto vwSisbenSetorDto) {
		return vwSisbenSetorRepository.findByCodSetorContainingOrderByCodSetor(vwSisbenSetorDto.getCodSetor());
	}

	@Override
	public List<VwSisbenSetor> pesquisarComLikePorDescrSetorOrdenadoPorDescrSetor(VwSisbenSetorDto vwSisbenSetorDto) {
		return vwSisbenSetorRepository.findByDescrSetorContainingOrderByDescrSetorDesc(vwSisbenSetorDto.getDescrSetor());
	}

	

}
