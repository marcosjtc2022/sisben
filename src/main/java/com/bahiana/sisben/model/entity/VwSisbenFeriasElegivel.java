package com.bahiana.sisben.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
@Table(name = "VW_SISBEN_FERIAS_ELEGIVEL" , schema="dbo")
public class VwSisbenFeriasElegivel {
	
	
	@Id
	@Column(name = "FUNC_MAT")
	private String matriculaFuncionario;
	
	@Column(name = "DATAINICIOFERIAS")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataInicioFerias;
	
	@Column(name = "DATAFIMFERIAS")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
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

	public VwSisbenFeriasElegivel(String matriculaFuncionario, LocalDateTime dataInicioFerias, LocalDateTime dataFimFerias) {
		super();
		this.matriculaFuncionario = matriculaFuncionario;
		this.dataInicioFerias = dataInicioFerias;
		this.dataFimFerias = dataFimFerias;
	}
	
	
	public VwSisbenFeriasElegivel() {
		
	}
	
	
	

}
