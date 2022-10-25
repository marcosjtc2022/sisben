package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ferias" , schema="dbo")
public class Ferias implements Serializable {
	
    @Id
	@Column(name="matricula_colaborador")
	private String matriculaColaborador;
	
	@Column(name = "data_inicial_ferias")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataInicialFerias;
		
	@Column(name = "data_final_ferias")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataFinalFerias;
	
	//Mapeando classe Elegibilidade 
	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
	@JoinColumn(updatable=false,insertable=false,name="matricula_colaborador")
	private Elegibilidade elegibilidade;	
	

}

