package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VwSisbenFeriasElegivelDto {
	
	private String matriculaFuncionario;
	
	
	private LocalDateTime dataInicioFerias;
	
	
	private LocalDateTime dataFimFerias;

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public LocalDateTime getDataInicioFerias() {
		return dataInicioFerias;
	}

	public void setDataInicioFerias(LocalDateTime dataInicioFerias) {
		this.dataInicioFerias = dataInicioFerias;
	}

	public LocalDateTime getDataFimFerias() {
		return dataFimFerias;
	}

	public void setDataFimFerias(LocalDateTime dataFimFerias) {
		this.dataFimFerias = dataFimFerias;
	}

	public VwSisbenFeriasElegivelDto(String matriculaFuncionario, LocalDateTime dataInicioFerias, LocalDateTime dataFimFerias) {
		super();
		this.matriculaFuncionario = matriculaFuncionario;
		this.dataInicioFerias = dataInicioFerias;
		this.dataFimFerias = dataFimFerias;
	}
	
	
	public VwSisbenFeriasElegivelDto() {
		
	}
	
	
	


}
