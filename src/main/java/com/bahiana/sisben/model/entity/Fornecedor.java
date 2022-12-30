package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "fornecedor" , schema="dbo")
public class Fornecedor implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "data_inico_vigencia_contrato")
	private LocalDateTime dataInicioVigenciaContrato;
	
	@Column(name="data_fim_vigencia_contrato")
	private LocalDateTime dataFimVigenciaContrato;
	
	@Column(name="cnpj")
	private String cnpj;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="inscricao_estadual")
	private String inscricaoEstadual;
	
	@Column(name="inscricao_municipal")
	private String inscricaoMunicipal;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	//Mapenado classe Usuario 
	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.EAGER)
	Set<Usuario> usuarios;

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

	public LocalDateTime getDataInicioVigenciaContrato() {
		return dataInicioVigenciaContrato;
	}

	public void setDataInicioVigenciaContrato(LocalDateTime dataInicioVigenciaContrato) {
		this.dataInicioVigenciaContrato = dataInicioVigenciaContrato;
	}

	public LocalDateTime getDataFimVigenciaContrato() {
		return dataFimVigenciaContrato;
	}

	public void setDataFimVigenciaContrato(LocalDateTime dataFimVigenciaContrato) {
		this.dataFimVigenciaContrato = dataFimVigenciaContrato;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
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

	public Fornecedor(Long id, String descricao, LocalDateTime dataInicioVigenciaContrato,
			LocalDateTime dataFimVigenciaContrato, String cnpj, String endereco, String telefone, String email,
			String inscricaoEstadual, String inscricaoMunicipal, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataInicioVigenciaContrato = dataInicioVigenciaContrato;
		this.dataFimVigenciaContrato = dataFimVigenciaContrato;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.inscricaoEstadual = inscricaoEstadual;
		this.inscricaoMunicipal = inscricaoMunicipal;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public Fornecedor() {
		
	}
	
	
	
}

