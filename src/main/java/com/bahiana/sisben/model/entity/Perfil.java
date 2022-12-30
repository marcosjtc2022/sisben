package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

@Table(name = "perfil" , schema="dbo")
public class Perfil implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "func_in_escala", columnDefinition="BIT")
	private Boolean funcEscala;
	
	@Column(name = "func_in_usuario", columnDefinition="BIT")
	private Boolean funcUsuario;
	
	@Column(name = "func_in_calendario", columnDefinition="BIT")
	private Boolean funcCalendario;
	
	@Column(name = "func_in_cardapio", columnDefinition="BIT")
	private Boolean funcCardapio;
	
	@Column(name = "func_in_beneficio", columnDefinition="BIT")
	private Boolean funcBeneficio;
	
	@Column(name = "func_in_entrega", columnDefinition="BIT")
	private Boolean funcEntrega;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
//	//Mapenado classe PerfilFuncionalidade 
//	@OneToMany(mappedBy = "perfil", fetch = FetchType.EAGER)
//	Set<PerfilFuncionalidade> perfis;	
	
	//Mapenado classe usuario 
	@OneToMany(mappedBy = "perfil", fetch = FetchType.EAGER)
	Set<Usuario> perflUsuarios;


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


	public Perfil(Long id, String descricao, Boolean funcEscala, Boolean funcUsuario, Boolean funcCalendario,
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


	public Perfil() {
		
	}
}