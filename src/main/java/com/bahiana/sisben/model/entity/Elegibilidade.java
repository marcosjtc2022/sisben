package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "elegibilidade" , schema="dbo")
public class Elegibilidade implements Serializable {
	
	@Id
	@Column(name = "matricula_colaborador")
	private Long matriculaColaborador;
	
	@Column(name="nome_colaborador")
	private String nomeColaborador;
	
	@Column(name="cod_setor")
	private String codSetor;
	
	@Column(name = "data_admissao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataAdmissao;
	
	@Column(name="email")
	private String emailColaborador;
	
	@Column(name="gestor1")
	private String gestor1;
	
	@Column(name="gestor2")
	private String gestor2;
	
	@Column(name = "data_ultima_modificacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
		
	//Mapenado classe Ferias.
	@OneToMany(mappedBy = "elegibilidade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Ferias> feriasElegiveis;
	
	//Mapenado classe ProgramacaoEntrega.
	@OneToMany(mappedBy = "elegibilidade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ProgramacaoEntrega> programacaoElegiveis;
	
	//Mapenado classe CentroCustoPercRateio
	@OneToMany(mappedBy = "elegibilidade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CentroCustoPercRateio> centrosCusto;
	
	//Mapeando a classe setor 
    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(updatable=false,insertable=false,name = "cod_setor")
    Setor setor;
	
	


}
