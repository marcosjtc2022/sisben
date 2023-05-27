package com.bahiana.sisben.api.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class ProgEntVigenteDto {
	
	
	private Long id;
	
	private String matriculaColaborador;
	
	private String anoMes;
	
	private String codSetor;
	
	private Long idUa;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUa() {
		return idUa;
	}

	public void setIdUa(Long idUa) {
		this.idUa = idUa;
	}

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

	public ProgEntVigenteDto(Long id, String matriculaColaborador, String anoMes, 
			                       String codSetor, Long idUa) {
		
		this.id = id;
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.idUa = idUa;
	}

	
	
	

}
