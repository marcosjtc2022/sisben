package com.bahiana.sisben.api.response;

public class ProgEntVigenteResponse {
	
private String matriculaColaborador;

    private Long id;
	
	private String anoMes;
	
	private String codSetor;
	
	private String descrSetor;
	
	private String nomeColaborador;
	
	private String status;
	
	private String localEntrega;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public ProgEntVigenteResponse(String matriculaColaborador, String anoMes, String codSetor, String descrSetor,
			String nomeColaborador, String localEntrega) {
		this.matriculaColaborador = matriculaColaborador;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.descrSetor = descrSetor;
		this.nomeColaborador = nomeColaborador;
		this.localEntrega = localEntrega;
	}

	public ProgEntVigenteResponse() {
		
	}

	
	

}
