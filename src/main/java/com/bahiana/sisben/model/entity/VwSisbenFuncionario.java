package com.bahiana.sisben.model.entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VW_SISBEN_FUNCIONARIO" , schema="dbo")
public class VwSisbenFuncionario {
	
    @Id
	@Column(name = "FUNC_MAT")
    private String matriculaFuncionario;
	
	@Column(name = "CODCOLIGADA")
    private Long codColigada;
	
	@Column(name = "CODSECAO")
    private String codSecao;
	
	@Column(name = "SECAO_Desc")
    private String descSecao;
	
	@Column(name = "FUNC_NOME")
    private String nomeFuncionario;
	
	@Column(name = "EMAIL")
    private String emailFuncionario;
	
	@Column(name = "CHEFE01")
    private String chefe1Funcionario;
	
	@Column(name = "CHEFE02")
    private String chefe2Funcionario;

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
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

	public String getChefe1Funcionario() {
		return chefe1Funcionario;
	}

	public void setChefe1Funcionario(String chefe1Funcionario) {
		this.chefe1Funcionario = chefe1Funcionario;
	}

	public String getChefe2Funcionario() {
		return chefe2Funcionario;
	}

	public void setChefe2Funcionario(String chefe2Funcionario) {
		this.chefe2Funcionario = chefe2Funcionario;
	}
	
//	//Mapenado classe ProgramacaoEntrega 
//		@OneToMany(mappedBy = "funcionario", fetch = FetchType.EAGER)
//		Set<ProgramacaoEntrega> funcionarioProgEntregas;

	public VwSisbenFuncionario(String matriculaFuncionario, Long codColigada, String codSecao, String descSecao,
			String nomeFuncionario, String emailFuncionario, String chefe1Funcionario, String chefe2Funcionario) {
		super();
		this.matriculaFuncionario = matriculaFuncionario;
		this.codColigada = codColigada;
		this.codSecao = codSecao;
		this.descSecao = descSecao;
		this.nomeFuncionario = nomeFuncionario;
		this.emailFuncionario = emailFuncionario;
		this.chefe1Funcionario = chefe1Funcionario;
		this.chefe2Funcionario = chefe2Funcionario;
	}
	
	public VwSisbenFuncionario() {
		
	}
	
	
	
	
	

}
