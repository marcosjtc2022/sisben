//package com.bahiana.sisben.model.entity;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name = "centro_custo_rateio_fixo_elegiveis" , schema="dbo")
//public class CentroCustoPercRateio implements Serializable {
//	
//	//@Id.
//	//private Long id;
//	
//	@Id
//	@Column(name = "matricula_colaborador")
//	private Long matriculaColaboradorCentro;
//	
//	@Column(name = "id_programacao_entrega")
//	private Long idProgEntrega;
//	
//	@Column(name="cod_reduzido")
//	private String codReduzido;
//	
//	@Column(name="nome_centro_custo")
//	private String nomeCentroCusto;
//	
//	@Column(name="integrcontabil")
//	private String integrContabil;
//	
//	@Column(name="descricao")
//	private String descricao;
//	
//	@Column(name = "valor")
//	private BigDecimal valor;
//	
//	//Mapeando a classe ProgramacaoEntrega
//	/*
//	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
//	@JoinColumn(name = "id_programacao_entrega") antes
//	ProgramacaoEntrega programacaoEntrega; */
//	
////	//Mapeando a classe ProgramacaoEntrega
////	@ManyToOne(fetch = FetchType.EAGER)
////	@JoinColumn(updatable=false,insertable=false,name = "id_programacao_entrega")
////	ProgramacaoEntrega programacaoEntrega;
//	
////	//Mapeando a classe Elegibilidade
////	@ManyToOne(fetch = FetchType.EAGER)
////	@JoinColumn(updatable=false,insertable=false,name = "matricula_colaborador")
////	Elegibilidade elegibilidade;
////	
//
//}
//
