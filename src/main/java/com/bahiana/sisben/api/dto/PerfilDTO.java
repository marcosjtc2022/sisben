package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

public class PerfilDto {
	
	
	private Long id; 
	
	private String descricao;
	
	private Boolean funcEscala;
	
	private Boolean funcUsuario;
	
	private Boolean funcCalendario;
		
	private Boolean funcCardapio;
	
	private Boolean funcBeneficio;
	
	private Boolean funcEntrega;
	
	private LocalDateTime dataUltimaModificacao;
		
	private Long idUsuarioUltimaModificacao;

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

	public Long getIdUsuarioUltimaModificacao() {
		return idUsuarioUltimaModificacao;
	}

	public void setIdUsuarioUltimaModificacao(Long idUsuarioUltimaModificacao) {
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public Boolean getFuncEscala() {
		return funcEscala;
	}

	public void setFuncEscala(Boolean funcEscala) {
		this.funcEscala = funcEscala;
	}

	public Boolean getFuncUsuario() {
		return funcUsuario;
	}

	public void setFuncUsuario(Boolean funcUsuario) {
		this.funcUsuario = funcUsuario;
	}

	public Boolean getFuncCalendario() {
		return funcCalendario;
	}

	public void setFuncCalendario(Boolean funcCalendario) {
		this.funcCalendario = funcCalendario;
	}

	public Boolean getFuncCardapio() {
		return funcCardapio;
	}

	public void setFuncCardapio(Boolean funcCardapio) {
		this.funcCardapio = funcCardapio;
	}

	public Boolean getFuncBeneficio() {
		return funcBeneficio;
	}

	public void setFuncBeneficio(Boolean funcBeneficio) {
		this.funcBeneficio = funcBeneficio;
	}
	
	public Boolean getFuncEntrega() {
		return funcEntrega;
	}

	public void setFuncEntrega(Boolean funcEntrega) {
		this.funcEntrega = funcEntrega;
	}
	

	public PerfilDto(Long id, String descricao, Boolean funcEscala, Boolean funcUsuario, Boolean funcCalendario,
			Boolean funcCardapio, Boolean funcBeneficio, Boolean funcEntrega, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.funcEscala = funcEscala;
		this.funcUsuario = funcUsuario;
		this.funcCalendario = funcCalendario;
		this.funcCardapio = funcCardapio;
		this.funcBeneficio = funcBeneficio;
		this.funcEntrega = funcEntrega;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public PerfilDto() {
		
	}
	
	
	
	
	

}
