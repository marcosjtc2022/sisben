package com.bahiana.sisben.api.dto;

public class TokenDto {
	
	private String nome;
	private String token;
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
	public TokenDto(String nome, String token) {
		super();
		this.nome = nome;
		this.token = token;
	}
	
	public TokenDto() {
		
	}
	
	

}
