package com.bahiana.sisben.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VW_SISBEN_ELEGIBILIDADE" , schema="dbo")
public class VwSisbenElegibilidade {
	
	@Id
	@Column(name = "FUNC_MAT")
	private String matriculaFuncionario;
	 
	@Column(name = "FUNC_NOME")
	private String nomeFuncionario;
	 
	@Column(name = "EMAIL")
	private String emailFuncionario;
	 
	@Column(name = "CODCOLIGADA")
	private Long codColigada;
	 
	@Column(name = "CODSECAO")
	private String codSecao;
	 
	@Column(name = "SECAO_Desc")
	private String descSecao;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "CHEFE01")
    private String chefe1Elegivel;
	
	@Column(name = "CHEFE02")
    private String chefe2Elegivel;
	
	@Column(name = "DESCRICAO_SITUACAO")
    private String descricaoSituacao;
	
	@Column(name = "DATAADMISSAO")
	private LocalDateTime dataAdmissao;

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

	public VwSisbenElegibilidade(String matriculaFuncionario, String nomeFuncionario, String emailFuncionario,
			Long codColigada, String codSecao, String descSecao, String bairro, String chefe1Elegivel,
			String chefe2Elegivel, String descricaoSituacao, LocalDateTime dataAdmissao) {
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
	}
	
	public VwSisbenElegibilidade() {
		
	}
	
	
	

}
