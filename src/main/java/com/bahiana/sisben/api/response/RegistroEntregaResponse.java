package com.bahiana.sisben.api.response;

import java.time.LocalDate;

public class RegistroEntregaResponse {
	
private String matriculaColaborador;

    
	
    private Long id;
    
	private String anoMes;
	
	private String codSetor;
	
	private String descrSetor;
	
	private String nomeColaborador;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(LocalDate dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public Boolean getSolicExtra() {
		return solicExtra;
	}

	public void setSolicExtra(Boolean solicExtra) {
		this.solicExtra = solicExtra;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
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

	public String getDescrSetor() {
		return descrSetor;
	}

	public void setDescrSetor(String descrSetor) {
		this.descrSetor = descrSetor;
	}
	
	

	
	public RegistroEntregaResponse(String matriculaColaborador, Long id, String anoMes, String codSetor,
			String descrSetor, String nomeColaborador, String localEntrega, Boolean solicExtra,
			LocalDate dataProgramacao, LocalDate dataEntrega, Long idJustificativa) {
		this.matriculaColaborador = matriculaColaborador;
		this.id = id;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.descrSetor = descrSetor;
		this.nomeColaborador = nomeColaborador;
		this.localEntrega = localEntrega;
		this.solicExtra = solicExtra;
		this.dataProgramacao = dataProgramacao;
		this.dataEntrega = dataEntrega;
		this.idJustificativa = idJustificativa;
	}

	public RegistroEntregaResponse() {
		
	}



}
