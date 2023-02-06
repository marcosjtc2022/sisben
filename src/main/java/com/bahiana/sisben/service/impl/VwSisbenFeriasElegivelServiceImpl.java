package com.bahiana.sisben.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.bahiana.sisben.model.entity.repository.VwSisbenFeriasElegivelRepository;
import com.bahiana.sisben.service.VwSisbenFeriasElegivelService;

public class VwSisbenFeriasElegivelServiceImpl implements VwSisbenFeriasElegivelService {

	@Autowired
	VwSisbenFeriasElegivelRepository vwSisbenFeriasElegivelRepository;
	
	
	@Override
	public Long pesquisarFeriasElegivel(LocalDate dataSolicitacao, String matriculaColaborador) {
		return vwSisbenFeriasElegivelRepository.
			pesquisarFeriasElegivel(dataSolicitacao, matriculaColaborador);
	}
;
}
