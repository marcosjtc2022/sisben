package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TipoJustificativaDto {
	
	
    private Long id; 
	
	private String descricao;
	
	private LocalDateTime dataUltimaModificacao;
	
	private Long idUsuarioUltimaModificacao;
	
	private String telaFuncao;

	public Long getIdUsuarioUltimaModificacao() {
		return idUsuarioUltimaModificacao;
	}

	public void setIdUsuarioUltimaModificacao(Long idUsuarioUltimaModificacao) {
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}
	
	public String getTelaFuncao() {
		return telaFuncao;
	}

	public void setTelaFuncao(String telaFuncao) {
		this.telaFuncao = telaFuncao;
	}

	public TipoJustificativaDto(Long id, String descricao, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao, String telaFuncao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		this.telaFuncao = telaFuncao;
	}

	public TipoJustificativaDto() {
		
	}
	
	
	
	
	

}
