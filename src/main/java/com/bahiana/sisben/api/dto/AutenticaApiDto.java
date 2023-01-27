package com.bahiana.sisben.api.dto;

public class AutenticaApiDto {
	
	private String login;
	private String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public AutenticaApiDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AutenticaApiDto() {
		
	}
	
	
	
	

}
