package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity

@Table(name = "justificativa" , schema="dbo")
public class Justificativa implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_justificativa")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "id_tipo_justificativa")
    private Long idTipoJustificativa;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
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

	public Long getIdUsuarioUltimaModificacao() {
		return idUsuarioUltimaModificacao;
	}

	public void setIdUsuarioUltimaModificacao(Long idUsuarioUltimaModificacao) {
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public LocalDateTime getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}
	
	public Long getIdTipoJustificativa() {
		return idTipoJustificativa;
	}

	public void setIdTipoJustificativa(Long idTipoJustificativa) {
		this.idTipoJustificativa = idTipoJustificativa;
	}
	
	
	
//	//Mapenado classe JusitificativaTipoJustificativa Antes
//	@OneToMany(mappedBy = "justificativa",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	Set<JustificativaTipoJustificativa> tiposJustificativa;
	
	
	



	





	//Mapeando a classe Tipo justificativa
	@ManyToOne(fetch = FetchType.EAGER  )
	@JoinColumn(updatable=false,insertable=false,name = "id_tipo_justificativa")
	TipoJustificativa tipoJustificativa;
		
	//Mapenado classe ProgramacaoEntrega 
	@OneToMany(mappedBy = "justificativa",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<ProgramacaoEntrega> programacaojustificativas;

	
	

	

	public Justificativa(Long id, String descricao, Long idTipoJustificativa, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.idTipoJustificativa = idTipoJustificativa;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public Justificativa() {
	}
	
	
	
	
	
}

