package com.bahiana.sisben.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValorMarmitaDto {
	
	
    private Long id; 

	private BigDecimal vlMarmita;	
	
	private LocalDateTime dataInicial;
		
	private LocalDateTime dataFinal;
	
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

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
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

	public ValorMarmitaDto(Long id, BigDecimal vlMarmita, LocalDateTime dataInicial, LocalDateTime dataFinal,
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
