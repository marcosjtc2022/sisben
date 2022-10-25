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
@Table(name = "perfil_funcionalidade" , schema="dbo")
public class PerfilFuncionalidade implements Serializable {
	
	@Id
	@Column(name="id_perfil")
	private String idPerfil;
	
	@Column(name="id_funcionalidade")
	private String idFuncionalidade;
	
	//mapeando a classe funcionalidade
    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(updatable=false,insertable=false,name = "id_funcionalidade")
    Funcionalidade funcionalidade;
 
    //mapeando a classe perfil 
    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    @JoinColumn(updatable=false,insertable=false,name = "id_perfil")
    Perfil perfil;

    @Column(name = "data_ultima_modificacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;

	
}
