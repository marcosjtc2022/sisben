package com.bahiana.sisben.api.dto;

public class ProgEntVigenteNpDto {
	
    private String matriculaColaborador;
	
	private String anoMes;
	
	private String codSetor;
	
	private String descrSetor;
	
	private String nomeColaborador;

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

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public ProgEntVigenteNpDto(String matriculaColaborador, String anoMes, String codSetor, String descrSetor,
			String nomeColaborador) {
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.descrSetor = descrSetor;
		this.nomeColaborador = nomeColaborador;
	}
	
	
	public ProgEntVigenteNpDto() {
		
	}

	
	

}
