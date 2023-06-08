package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

@Table(name = "valor_marmita" , schema="dbo")
public class ValorMarmita implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_valor")
    private Long id; 
	
	@Column(name = "valor")
	private BigDecimal vlMarmita;	
	
	@Column(name = "data_inicial")
	private LocalDate dataInicial;
		
	@Column(name = "data_final")
	private LocalDate dataFinal;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	//Mapenado classe ProgramacaoEntrega (obs)
//	@OneToMany(mappedBy = "valorMarmita", fetch = FetchType.LAZY)
//	Set<ProgramacaoEntrega> programacaoValores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getVlMarmita() {
		return vlMarmita;
	}

	public void setVlMarmita(BigDecimal vlMarmita) {
		this.vlMarmita = vlMarmita;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public LocalDateTime getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public Long getIdUsuarioUltimaModificacao() {
		return idUsuarioUltimaModificacao;
	}

	public void setIdUsuarioUltimaModificacao(Long idUsuarioUltimaModificacao) {
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public ValorMarmita(Long id, BigDecimal vlMarmita, LocalDate dataInicial, LocalDate dataFinal,
			LocalDateTime dataUltimaModificacao, Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.vlMarmita = vlMarmita;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public ValorMarmita() {
		
	}
	
	
 
}
