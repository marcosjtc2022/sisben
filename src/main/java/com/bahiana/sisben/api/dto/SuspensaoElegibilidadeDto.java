package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

public class SuspensaoElegibilidadeDto {

	private Long id;
	
    private Long matriculaColaborador;
	
	private String nomeColaborador;
	
	private LocalDateTime dataInicial;
	
	private LocalDateTime dataFinal;
	
	private String justificativaSuspensao;
	
	private LocalDateTime dataUltimaModificacao;
	
	private Long idUsuarioUltimaModificacao;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatriculaColaborador() {
		return matriculaColaborador;
	}

	public void setMatriculaColaborador(Long matriculaColaborador) {
		this.matriculaColaborador = matriculaColaborador;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
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

	public String getJustificativaSuspensao() {
		return justificativaSuspensao;
	}

	public void setJustificativaSuspensao(String justificativaSuspensao) {
		this.justificativaSuspensao = justificativaSuspensao;
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
	
	
	public SuspensaoElegibilidadeDto(Long id, Long matriculaColaborador, String nomeColaborador,
			LocalDateTime dataInicial, LocalDateTime dataFinal, String justificativaSuspensao,
			LocalDateTime dataUltimaModificacao, Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.matriculaColaborador = matriculaColaborador;
		this.nomeColaborador = nomeColaborador;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.justificativaSuspensao = justificativaSuspensao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public SuspensaoElegibilidadeDto() {
		
	}
	
	
	
	
	


}
