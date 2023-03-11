package com.bahiana.sisben.api.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class ListarProgEntVigenteDto {
	
	
	
	private String matriculaColaborador;
	
	private String anoMes;
	
	private String codSetor;
    

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

	public ListarProgEntVigenteDto(String matriculaColaborador, String anoMes, 
			                       String codSetor) {
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
	}

	
	
	

}
