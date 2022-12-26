package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.TipoJustificativa;

public class JustificativaDto {
	
	
    private Long id; 
	
	
	private String descricao;
	
	
	private LocalDateTime dataUltimaModificacao;
	
	
	private Long idUsuarioUltimaModificacao;
	
	
	private Long idTipoJustificativa;


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


	public Long getIdTipoJustificativa() {
		return idTipoJustificativa;
	}


	public void setIdTipoJustificativa(Long idTipoJustificativa) {
		this.idTipoJustificativa = idTipoJustificativa;
	}


	
	
	
	public JustificativaDto(Long id, String descricao, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao, Long idTipoJustificativa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		this.idTipoJustificativa = idTipoJustificativa;
	}


	public JustificativaDto() {
		
	}
	
	
	
	
	
}
