package com.bahiana.sisben.api.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

public class VwSisbenFeriasElegivelDto {
	
	private String matriculaFuncionario;
	
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataInicioFerias;
	
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataFimFerias;

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public LocalDate getDataInicioFerias() {
		return dataInicioFerias;
	}

	public void setDataInicioFerias(LocalDate dataInicioFerias) {
		this.dataInicioFerias = dataInicioFerias;
	}

	public LocalDate getDataFimFerias() {
		return dataFimFerias;
	}

	public void setDataFimFerias(LocalDate dataFimFerias) {
		this.dataFimFerias = dataFimFerias;
	}

	public VwSisbenFeriasElegivelDto(String matriculaFuncionario, LocalDate dataInicioFerias, LocalDate dataFimFerias) {
		super();
		this.matriculaFuncionario = matriculaFuncionario;
		this.dataInicioFerias = dataInicioFerias;
		this.dataFimFerias = dataFimFerias;
	}
	
	
	public VwSisbenFeriasElegivelDto() {
		
	}
	
	
	


}
