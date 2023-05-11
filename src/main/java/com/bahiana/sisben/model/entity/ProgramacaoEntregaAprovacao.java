package com.bahiana.sisben.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "programacao_entrega_aprovacao" , schema="dbo")
public class ProgramacaoEntregaAprovacao {
	
	
	@Id
	@Column(name = "id_progrent_ap")
    private Long id;
	
	@Column(name="ua_realizada")
	private String uaRealizada;
	 
    @Column(name = "id_ua")
    private Long idUa;
    
    @Column(name = "id_usuario_entrega")
    private Long idUsuario;
    
//	@Column(name = "data_entrega")
//	private LocalDate dataEntrega;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUaRealizada() {
		return uaRealizada;
	}

	public void setUaRealizada(String uaRealizada) {
		this.uaRealizada = uaRealizada;
	}

	public Long getIdUa() {
		return idUa;
	}

	public void setIdUa(Long idUa) {
		this.idUa = idUa;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ProgramacaoEntregaAprovacao(Long id, String uaRealizada, Long idUa, Long idUsuario) {
		super();
		this.id = id;
		this.uaRealizada = uaRealizada;
		this.idUa = idUa;
		this.idUsuario = idUsuario;
	}

	public ProgramacaoEntregaAprovacao() {
		
	}
    
	

}
