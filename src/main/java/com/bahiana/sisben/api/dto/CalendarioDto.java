package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CalendarioDto {
	
    private Long id; 

	private LocalDateTime dataEspecial;
	
	private String descricao;
	
	private LocalDateTime dataUltimaModificacao;
	
	private Long idUsuarioUltimaModificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataEspecial() {
		return dataEspecial;
	}

	public void setDataEspecial(LocalDateTime dataEspecial) {
		this.dataEspecial = dataEspecial;
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

	public Long getIdUsuarioUltimaModificacao() {
		return idUsuarioUltimaModificacao;
	}

	public void setIdUsuarioUltimaModificacao(Long idUsuarioUltimaModificacao) {
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public CalendarioDto(Long id, LocalDateTime dataEspecial, String descricao, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.dataEspecial = dataEspecial;
		this.descricao = descricao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public CalendarioDto() {
		
	}
	
	
	
	

}
