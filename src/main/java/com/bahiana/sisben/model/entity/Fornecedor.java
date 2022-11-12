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
@Table(name = "fornecedor" , schema="dbo")
public class Fornecedor implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "data_inico_vigencia_contrato")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataInicioVigenciaContrato;
	
	@Column(name="data_fim_vigencia_contrato")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private Long dataFimVigenciaContrato;
	
	@Column(name="cnpj")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private String cnpj;
	
	@Column(name="endereco")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private String endereco;
	
	@Column(name="telefone")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private String telefone;
	
	@Column(name="email")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private String email;
	
	@Column(name="inscricao_estadual")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private String inscricaoEstadual;
	
	@Column(name="inscricao_municipal")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private String inscricaoMunicipal;
	
	@Column(name = "data_ultima_modificacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	//Mapenado classe Usuario 
	@OneToMany(mappedBy = "fornecedor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<Usuario> usuarios;
	
}

