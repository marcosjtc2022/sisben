package com.bahiana.sisben.api.dto;

public class FuncionarioDto {
	
	
	private String nomeColaborador;

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public FuncionarioDto(String nomeColaborador) {
		super();
		this.nomeColaborador = nomeColaborador;
	}
	
	public FuncionarioDto() {
		
	}
	
	

}
