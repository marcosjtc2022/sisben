package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.util.Set;

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

@Entity
@Table(name = "usuario_setor_gerenciado" , schema="dbo")
public class UsuarioSetorGerenciado implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_setor")
    private Long id;
	
	@Column(name = "id_usuario")
    private Long idUsuarioLider; 
	
	@Column(name="cod_setor")
	private String codSetor;
	
	@Column(name="descricao")
	private String descricao;
	
//	//Mapenado classe usuariosetorgerenciado
//	@OneToMany(mappedBy = "setorGerenciado", fetch = FetchType.LAZY)
//	Set<UsuarioSetorGerenciado> setoresGerenciados;
	
//	//mapeando a classe Usuario (Usuario da entrega). man 17.06.2023
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(updatable=false,insertable=false,name = "id_usuario")
//    Usuario usuarioLider;

	public Long getIdUsuarioLider() {
		return idUsuarioLider;
	}

	public void setIdUsuarioLider(Long idUsuarioLider) {
		this.idUsuarioLider = idUsuarioLider;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public UsuarioSetorGerenciado(Long id, Long idUsuarioLider, String codSetor, String descricao) {
		this.id = id;
		this.idUsuarioLider = idUsuarioLider;
		this.codSetor = codSetor;
		this.descricao = descricao;
	}

	public UsuarioSetorGerenciado() {
		
	}

	
	
	

}
