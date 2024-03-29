package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;





@Entity

@Table(name = "tipo_justificativa" , schema="dbo")
public class TipoJustificativa implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_justificativa")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	@Column(name="tela_funcao")
	private String telaFuncao;
	
//	//Mapenado classe JusitificativaTipoJustificativa Antes
//	@OneToMany(mappedBy = "tipoJustificativa",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	Set<JustificativaTipoJustificativa> tiposJustificativa;
	
	//Mapenado classe JusitificativaTipoJustificativa man 17.06.2023
//	@OneToMany(mappedBy = "tipoJustificativa",fetch = FetchType.LAZY)
//	Set<Justificativa> justificativas;

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

	public String getTelaFuncao() {
		return telaFuncao;
	}

	public void setTelaFuncao(String telaFuncao) {
		this.telaFuncao = telaFuncao;
	}
	
	public TipoJustificativa(Long id, String descricao, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao, String telaFuncao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		this.telaFuncao = telaFuncao;
	}

	public TipoJustificativa() {
		
	}
	
	

}
