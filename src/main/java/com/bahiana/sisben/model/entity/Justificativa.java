package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;



@Entity

@Table(name = "justificativa" , schema="dbo")
public class Justificativa implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_justificativa")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "data_ultima_modificacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	//Mapenado classe JusitificativaTipoJustificativa 
	@OneToMany(mappedBy = "justificativa",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<JustificativaTipoJustificativa> tiposJustificativa;
		
	//Mapenado classe ProgramacaoEntrega 
	@OneToMany(mappedBy = "justificativa",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<ProgramacaoEntrega> programacaojustificativas;
	
}

