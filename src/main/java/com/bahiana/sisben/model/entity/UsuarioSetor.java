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
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;




@Entity

@Table(name = "usuario_setor" , schema="dbo")
public class UsuarioSetor implements Serializable {
	
//	@Id colocar identity
//	Long id;
	
	@Id
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "cod_setor")
	private String codSetor;
    
    @Column(name = "data_inicial")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataInicial;
		
	@Column(name = "data_final")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataFinal;
	
	@Column(name = "data_ultima_modificacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	
	//Mapeando a classe usuario
	@ManyToOne(fetch = FetchType.LAZY,  cascade=CascadeType.ALL)
    @JoinColumn(updatable=false,insertable=false,name = "id_usuario")
    Usuario usuario;
 
	//Mapeando a classe setor 
    @ManyToOne(fetch = FetchType.LAZY,  cascade=CascadeType.ALL)
    @JoinColumn(updatable=false,insertable=false,name = "cod_setor")
    Setor setor;

}
