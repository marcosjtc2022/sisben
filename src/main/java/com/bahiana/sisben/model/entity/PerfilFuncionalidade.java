package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity

@Table(name = "perfil_funcionalidade" , schema="dbo")
public class PerfilFuncionalidade implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil_funcionalidade")
    private Long id; 
	
	@Column(name="id_perfil")
	private Long idPerfil;
	
	@Column(name="id_funcionalidade")
	private Long idFuncionalidade;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
//	//mapeando a classe funcionalidade
//    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH )
//    @JoinColumn(updatable=false,insertable=false,name = "id_funcionalidade")
//    Funcionalidade funcionalidade;
// 
//    //mapeando a classe perfil 
//    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
//    @JoinColumn(updatable=false,insertable=false,name = "id_perfil")
//    Perfil perfil;
	
	//mapeando a classe funcionalidade
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_funcionalidade")
    Funcionalidade funcionalidade;
 
    //mapeando a classe perfil 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_perfil")
    Perfil perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(Long idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
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

	public PerfilFuncionalidade(Long id, Long idPerfil, Long idFuncionalidade, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.idPerfil = idPerfil;
		this.idFuncionalidade = idFuncionalidade;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public PerfilFuncionalidade() {
		
	}
    
    
    

   
	
	

		
	

	
}
