package com.bahiana.sisben.api.dto;

import com.bahiana.sisben.model.entity.Usuario;

public class TokenDto {
	
	private String nome;
	private Long id;
	private String emailUsuario;
	private Long idUa;
	private Long idPerfil;
	private String token;
	private Boolean stUsuarioExterno;
	
	
	
	public Boolean getStUsuarioExterno() {
		return stUsuarioExterno;
	}
	public void setStUsuarioExterno(Boolean stUsuarioExterno) {
		this.stUsuarioExterno = stUsuarioExterno;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getIdUa() {
		return idUa;
	}
	public void setIdUa(Long idUa) {
		this.idUa = idUa;
	}
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	public TokenDto(String nome, Long id, String emailUsuario, Long idUa, Long idPerfil,
			Boolean stUsuarioExterno,String token) {
		
		this.nome = nome;
		this.id = id;
		this.emailUsuario = emailUsuario;
		this.idUa = idUa;
		this.idPerfil = idPerfil;
		this.stUsuarioExterno = stUsuarioExterno;
		this.token = token;
		
	}
	
	public TokenDto() {
		
	}
	
	

}
