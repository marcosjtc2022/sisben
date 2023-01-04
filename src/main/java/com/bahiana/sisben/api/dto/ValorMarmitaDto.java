package com.bahiana.sisben.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValorMarmitaDto {
	
	
    private Long id; 

	private BigDecimal vlMarmita;	
	
	private LocalDate dataInicial;
		
	private LocalDate dataFinal;
	
	private LocalDateTime dataUltimaModificacao;

	private Long idUsuarioUltimaModificacao;

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

	public ValorMarmitaDto(Long id, BigDecimal vlMarmita, LocalDate dataInicial, LocalDate dataFinal,
			LocalDateTime dataUltimaModificacao, Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.vlMarmita = vlMarmita;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public ValorMarmitaDto() {
		
	}
	
	


}
