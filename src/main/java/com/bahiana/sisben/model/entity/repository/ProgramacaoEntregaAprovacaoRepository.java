package com.bahiana.sisben.model.entity.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bahiana.sisben.model.entity.ProgramacaoEntregaAprovacao;

public interface ProgramacaoEntregaAprovacaoRepository extends PagingAndSortingRepository<ProgramacaoEntregaAprovacao, Long>, JpaSpecificationExecutor<ProgramacaoEntregaAprovacao> {

}
