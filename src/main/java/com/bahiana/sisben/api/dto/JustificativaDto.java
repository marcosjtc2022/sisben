package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.TipoJustificativa;

public class JustificativaDto {
	
	
    private Long id; 
	
	
	private String descricao;
	
	private LocalDateTime dataUltimaModificacao;
	
	private Long idUsuarioUltimaModificacao;
	
	private Long idTipoJustificativa;
	
	private TipoJustificativa tipoJustificativa;
	
	


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
	
	public TipoJustificativa getTipoJustificativa() {
		return tipoJustificativa;
	}


	public void setTipoJustificativa(TipoJustificativa tipoJustificativa) {
		this.tipoJustificativa = tipoJustificativa;
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
