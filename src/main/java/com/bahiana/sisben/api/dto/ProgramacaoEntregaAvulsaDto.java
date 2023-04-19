package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

public class ProgramacaoEntregaAvulsaDto {
	
	
	//@Id
    private Long id;	
	
	private String matriculaColaborador;
	
	private String uaPrevista;
	
	private String uaRealizada;
	
    private Long idData;
	 
    private Long idUa;
    
    private Long idJustificativa;
    
    private Long idValor;

    private Long idUsuario;
	
	private LocalDate dataEntrega;
	
	private LocalDate dataSolicitacao;	

	private Boolean solicExtra;
	
	private Boolean stAprov;
	
	private Long idUsuarioUltimaModificacao;
	
	private LocalDateTime dataUltimaModificacao;
	
	private String anoMes;
	
	private String codSetor;
	
    private LocalDate utlimaDataMes;
	
	private LocalDate primeiraDataMes;  
	
	private LocalDate dataProgramacao;
	
	public LocalDate getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(LocalDate dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public LocalDate getUtlimaDataMes() {
		return utlimaDataMes;
	}

	public void setUtlimaDataMes(LocalDate utlimaDataMes) {
		this.utlimaDataMes = utlimaDataMes;
	}

	public LocalDate getPrimeiraDataMes() {
		return primeiraDataMes;
	}

	public void setPrimeiraDataMes(LocalDate primeiraDataMes) {
		this.primeiraDataMes = primeiraDataMes;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculaColaborador() {
		return matriculaColaborador;
	}

	public void setMatriculaColaborador(String matriculaColaborador) {
		this.matriculaColaborador = matriculaColaborador;
	}

	public String getUaPrevista() {
		return uaPrevista;
	}

	public void setUaPrevista(String uaPrevista) {
		this.uaPrevista = uaPrevista;
	}

	public String getUaRealizada() {
		return uaRealizada;
	}

	public void setUaRealizada(String uaRealizada) {
		this.uaRealizada = uaRealizada;
	}

	public Long getIdData() {
		return idData;
	}

	public void setIdData(Long idData) {
		this.idData = idData;
	}

	public Long getIdUa() {
		return idUa;
	}

	public void setIdUa(Long idUa) {
		this.idUa = idUa;
	}

	public Long getIdJustificativa() {
		return idJustificativa;
	}

	public void setIdJustificativa(Long idJustificativa) {
		this.idJustificativa = idJustificativa;
	}

	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Boolean getSolicExtra() {
		return solicExtra;
	}

	public void setSolicExtra(Boolean solicExtra) {
		this.solicExtra = solicExtra;
	}

	public Boolean getStAprov() {
		return stAprov;
	}

	public void setStAprov(Boolean stAprov) {
		this.stAprov = stAprov;
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
	
	public ProgramacaoEntregaAvulsaDto(Long id, String matriculaColaborador, String uaPrevista, String uaRealizada,
			Long idData, Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDate dataEntrega,
			LocalDate dataSolicitacao, Boolean solicExtra, Boolean stAprov, Long idUsuarioUltimaModificacao,
			LocalDateTime dataUltimaModificacao, String anoMes, String codSetor, LocalDate utlimaDataMes,
			LocalDate primeiraDataMes) {
		super();
		this.id = id;
		this.matriculaColaborador = matriculaColaborador;
		this.uaPrevista = uaPrevista;
		this.uaRealizada = uaRealizada;
		this.idData = idData;
		this.idUa = idUa;
		this.idJustificativa = idJustificativa;
		this.idValor = idValor;
		this.idUsuario = idUsuario;
		this.dataEntrega = dataEntrega;
		this.dataSolicitacao = dataSolicitacao;
		this.solicExtra = solicExtra;
		this.stAprov = stAprov;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
		this.utlimaDataMes = utlimaDataMes;
		this.primeiraDataMes = primeiraDataMes;
	}

	public ProgramacaoEntregaAvulsaDto() {
		
	}
	
	


}
