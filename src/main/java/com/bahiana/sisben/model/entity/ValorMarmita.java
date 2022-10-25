package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "valor_marmita" , schema="dbo")
public class ValorMarmita implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_valor")
    private Long id; 
	
	@Column(name = "valor")
	private BigDecimal vlMarmita;	
	
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
	
	//Mapenado classe ProgramacaoEntrega 
	@OneToMany(mappedBy = "valorMarmita",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<ProgramacaoEntrega> programacaoValores;
 
}
