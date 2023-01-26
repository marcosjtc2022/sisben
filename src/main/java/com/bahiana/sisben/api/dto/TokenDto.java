package com.bahiana.sisben.api.dto;

import com.bahiana.sisben.model.entity.Usuario;

public class TokenDto {
	
	private String nome;
	private Long id;
	private String emailUsuario;
	private String token;
	
	
	
	
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
	public TokenDto(String nome, Long id,String emailUsuario, String token) {
		super();
		this.nome = nome;
		this.token = token;
		this.id = id;
		this.emailUsuario = emailUsuario;
	}
	
	public TokenDto() {
		
	}
	
	

}
