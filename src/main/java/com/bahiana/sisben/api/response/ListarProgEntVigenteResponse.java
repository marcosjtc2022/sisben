package com.bahiana.sisben.api.response;

public class ListarProgEntVigenteResponse {
	
private String matriculaColaborador;
	
	private String anoMes;
	
	private String codSetor;
	
	private String descrSetor;

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

	public String getDescrSetor() {
		return descrSetor;
	}

	public void setDescrSetor(String descrSetor) {
		this.descrSetor = descrSetor;
	}

	public ListarProgEntVigenteResponse(String matriculaColaborador, String anoMes, String codSetor, String descrSetor) {
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.descrSetor = descrSetor;
	}
	
	
	public ListarProgEntVigenteResponse() {
		
	}

	
	

}
