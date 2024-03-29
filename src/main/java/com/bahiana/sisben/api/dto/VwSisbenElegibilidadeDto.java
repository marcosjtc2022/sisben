package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

public class VwSisbenElegibilidadeDto {
	
	private String matriculaFuncionario;

	private String nomeFuncionario;
	 
	private String emailFuncionario;
	 
	private Long codColigada;
	 
	private String codSecao;
	 
	private String descSecao;
	
	private String bairro;
	
    private String chefe1Elegivel;
	
    private String chefe2Elegivel;
	
    private String descricaoSituacao;
	
	private LocalDateTime dataAdmissao;
	
	private String idUsuarioLogado;
	
	public String getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(String idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getEmailFuncionario() {
		return emailFuncionario;
	}

	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}

	public Long getCodColigada() {
		return codColigada;
	}

	public void setCodColigada(Long codColigada) {
		this.codColigada = codColigada;
	}

	public String getCodSecao() {
		return codSecao;
	}

	public void setCodSecao(String codSecao) {
		this.codSecao = codSecao;
	}

	public String getDescSecao() {
		return descSecao;
	}

	public void setDescSecao(String descSecao) {
		this.descSecao = descSecao;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getChefe1Elegivel() {
		return chefe1Elegivel;
	}

	public void setChefe1Elegivel(String chefe1Elegivel) {
		this.chefe1Elegivel = chefe1Elegivel;
	}

	public String getChefe2Elegivel() {
		return chefe2Elegivel;
	}

	public void setChefe2Elegivel(String chefe2Elegivel) {
		this.chefe2Elegivel = chefe2Elegivel;
	}

	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	public LocalDateTime getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDateTime dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	
	
	public VwSisbenElegibilidadeDto(String matriculaFuncionario, String nomeFuncionario, String emailFuncionario,
			Long codColigada, String codSecao, String descSecao, String bairro, String chefe1Elegivel,
			String chefe2Elegivel, String descricaoSituacao, LocalDateTime dataAdmissao, String idUsuarioLogado) {
		super();
		this.matriculaFuncionario = matriculaFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.emailFuncionario = emailFuncionario;
		this.codColigada = codColigada;
		this.codSecao = codSecao;
		this.descSecao = descSecao;
		this.bairro = bairro;
		this.chefe1Elegivel = chefe1Elegivel;
		this.chefe2Elegivel = chefe2Elegivel;
		this.descricaoSituacao = descricaoSituacao;
		this.dataAdmissao = dataAdmissao;
		this.idUsuarioLogado = idUsuarioLogado;
	}

	public VwSisbenElegibilidadeDto() {
		
	}
	
	


}
