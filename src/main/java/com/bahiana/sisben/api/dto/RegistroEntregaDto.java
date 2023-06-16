package com.bahiana.sisben.api.dto;

import java.time.LocalDate;

public class RegistroEntregaDto {
	
	private Long id;
	
    private String matriculaColaborador;
	
	private String anoMes;
	
	private String codSetor;
	
	private String localEntrega; //obs
	
	private Boolean solicExtra;
	
	private LocalDate dataProgramacao;
	
	private LocalDate dataEntrega;
	
	private Long idJustificativa;
	
	
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getIdJustificativa() {
		return idJustificativa;
	}

	public void setIdJustificativa(Long idJustificativa) {
		this.idJustificativa = idJustificativa;
	}

	public LocalDate getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(LocalDate dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSolicExtra() {
		return solicExtra;
	}

	public void setSolicExtra(Boolean solicExtra) {
		this.solicExtra = solicExtra;
	}

	public String getMatriculaColaborador() {
		return matriculaColaborador;
	}

	public void setMatriculaColaborador(String matriculaColaborador) {
		this.matriculaColaborador = matriculaColaborador;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public RegistroEntregaDto(Long id, String matriculaColaborador, String anoMes, String codSetor, String localEntrega,
			Boolean solicExtra, LocalDate dataProgramacao, LocalDate dataEntrega, Long idJustificativa) {
		super();
		this.id = id;
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.localEntrega = localEntrega;
		this.solicExtra = solicExtra;
		this.dataProgramacao = dataProgramacao;
		this.dataEntrega = dataEntrega;
		this.idJustificativa = idJustificativa;
	}

	public RegistroEntregaDto() {
		
	}
	



}
