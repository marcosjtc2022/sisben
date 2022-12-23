//package com.bahiana.sisben.model.entity;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
//
//
//
//@Entity
//
//@Table(name = "justificativa_tipo_justificativa" , schema="dbo")
//public class JustificativaTipoJustificativa implements Serializable {
//	
//	@Id
//	@Column(name="id_justificativa")
//	private String idJustificativa;
//
//	@Column(name="id_tipo_justificativa")
//	private String idTipoJustificativa;
//	
//	
////	//Mapeando a classe justificativa antes
////	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
////	@JoinColumn(updatable=false,insertable=false,name = "id_justificativa")
////	Justificativa justificativa;
////	
////	//Mapeando a classe tipojustificativa antes
////	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
////	@JoinColumn(updatable=false,insertable=false,name = "id_tipo_justificativa")
////	TipoJustificativa tipoJustificativa;
//	
//
//    @Column(name = "data_ultima_modificacao")
//	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
//	private LocalDate dataUltimaModificacao;
//	
//	@Column(name="id_usuario_ultima_modificacao")
//	private Long idUsuarioUltimaModificacao;
//	
//
//}
