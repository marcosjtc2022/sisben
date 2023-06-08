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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;




@Entity

@Table(name = "setor" , schema="dbo")
public class Setor implements Serializable {
	
	@Id
	@Column(name = "cod_setor")
    private String codSetor; 
	
	@Column(name="descricao")
	private String descricao;
	
//	@Column(name = "data_ultima_modificacao")
//	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
//	private LocalDate dataUltimaModificacao;
//	
//	@Column(name="id_usuario_ultima_modificacao")
//	private Long idUsuarioUltimaModificacao;

			
//	//Mapenado classe UsuarioSetor 
//	@OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
//	Set<UsuarioSetor> perfisSetores;
//	
//	//Mapenado classe Elegibilidade 
//	@OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
//	Set<Elegibilidade> elegiveisSetores;
	
	
	

}
