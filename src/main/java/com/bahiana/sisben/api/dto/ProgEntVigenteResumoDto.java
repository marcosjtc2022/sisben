package com.bahiana.sisben.api.dto;

public class ProgEntVigenteResumoDto {
	
private String matriculaColaborador;
	
	private String anoMes;
	
	private String codSetor;
	
	//private String localEntrega; //obs
	
	public String getMatriculaColaborador() {
		return matriculaColaborador;
	}

	public void setMatriculaColaborador(String matriculaColaborador) {
		this.matriculaColaborador = matriculaColaborador;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

//	public String getLocalEntrega() {
//		return localEntrega;
//	}
//
//	public void setLocalEntrega(String localEntrega) {
//		this.localEntrega = localEntrega;
//	}

//	public ProgEntVigenteResumoDto(String matriculaColaborador, String anoMes, String codSetor, String localEntrega) {
//		this.matriculaColaborador = matriculaColaborador;
//		this.anoMes = anoMes;
//		this.codSetor = codSetor;
//		this.localEntrega = localEntrega;
//		
//	}
	
	public ProgEntVigenteResumoDto(String matriculaColaborador, String anoMes, String codSetor) {
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		
	}

	
	
	

}
