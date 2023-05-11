package com.bahiana.sisben.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bahiana.sisben.model.entity.ProgramacaoEntregaAprovacao;
import com.bahiana.sisben.model.entity.repository.ProgramacaoEntregaAprovacaoRepository;
import com.bahiana.sisben.service.ProgramacaoEntregaAprovacaoService;

public class ProgramacaoEntregaAprovacaoServiceImpl implements  ProgramacaoEntregaAprovacaoService {
	
	@Autowired
	ProgramacaoEntregaAprovacaoRepository programacaoEntregaAprovacaoRepository;
	

	@Override
	public void salvar(ProgramacaoEntregaAprovacao ProgramacaoEntregaAprovacao) {
		  programacaoEntregaAprovacaoRepository.save(ProgramacaoEntregaAprovacao);
	}

	@Override
	public void alterar(ProgramacaoEntregaAprovacao ProgramacaoEntregaAprovacao) {
		 programacaoEntregaAprovacaoRepository.save(ProgramacaoEntregaAprovacao);
	}
	
	
	

}
