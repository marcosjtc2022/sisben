package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProgramacaoEntregaDto {
	
	@Id
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
	
//	private LocalDateTime dataSolicitacaoDateTime;
	
	private Boolean solicExtra;
	
	private Boolean stAprov;
	
	private LocalDateTime dataUltimaModificacao;
	
	//@Transient
	String tabelaProgramacaoEntrega;
	
	private String codSetor;
		
	private Long idUsuarioUltimaModificacao;
	
	private LocalDate dataAtual;
	
	private String diaDaSemana;
	
	private LocalDate dataProgramacao;
	
	private String descricaoFeriado;
	
	private LocalDate mesAnoProgramacao;
	
	private String bairroSecaoElegivel;
	
	private Boolean exigSuspensa;
	
	private Boolean stFerias;
	
	private LocalDate utlimaDataMes;
	
	private LocalDate primeiraDataMes;
	
	private String anoMes;
	
	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
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

	public Boolean getStFerias() {
		return stFerias;
	}

	public void setStFerias(Boolean stFerias) {
		this.stFerias = stFerias;
	}

//	public LocalDateTime getDataSolicitacaoDateTime() {
//		return dataSolicitacaoDateTime;
//	}
//
//	public void setDataSolicitacaoDateTime(LocalDateTime dataSolicitacaoDateTime) {
//		this.dataSolicitacaoDateTime = dataSolicitacaoDateTime;
//	}

	public Boolean getExigSuspensa() {
		return exigSuspensa;
	}

	public void setExigSuspensa(Boolean exigSuspensa) {
		this.exigSuspensa = exigSuspensa;
	}

	public String getBairroSecaoElegivel() {
		return bairroSecaoElegivel;
	}

	public void setBairroSecaoElegivel(String bairroSecaoElegivel) {
		this.bairroSecaoElegivel = bairroSecaoElegivel;
	}

	public LocalDate getMesAnoProgramacao() {
		return mesAnoProgramacao;
	}

	public void setMesAnoProgramacao(LocalDate mesAnoProgramacao) {
		this.mesAnoProgramacao = mesAnoProgramacao;
	}

	public LocalDate getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(LocalDate dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Long getIdUsuarioUltimaModificacao() {
		return idUsuarioUltimaModificacao;
	}

	public void setIdUsuarioUltimaModificacao(Long idUsuarioUltimaModificacao) {
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public LocalDateTime getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public String getTabelaProgramacaoEntrega() {
		return tabelaProgramacaoEntrega;
	}

	public void setTabelaProgramacaoEntrega(String tabelaProgramacaoEntrega) {
		this.tabelaProgramacaoEntrega = tabelaProgramacaoEntrega;
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
	
	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public LocalDate getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(LocalDate dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}
	
	
	
	public String getDescricaoFeriado() {
		return descricaoFeriado;
	}

	public void setDescricaoFeriado(String descricaoFeriado) {
		this.descricaoFeriado = descricaoFeriado;
	}
	
	public ProgramacaoEntregaDto(Long id, String matriculaColaborador, String uaPrevista, String uaRealizada,
			Long idData, Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDate dataEntrega,
			LocalDate dataSolicitacao, Boolean solicExtra, Boolean stAprov, LocalDateTime dataUltimaModificacao,
			String codSetor, Long idUsuarioUltimaModificacao, LocalDate dataAtual, String diaDaSemana,
			LocalDate dataProgramacao, String descricaoFeriado, LocalDate mesAnoProgramacao, String bairroSecaoElegivel,
			Boolean exigSuspensa, Boolean stFerias, LocalDate utlimaDataMes, LocalDate primeiraDataMes, String anoMes) {
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
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.codSetor = codSetor;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		this.dataAtual = dataAtual;
		this.diaDaSemana = diaDaSemana;
		this.dataProgramacao = dataProgramacao;
		this.descricaoFeriado = descricaoFeriado;
		this.mesAnoProgramacao = mesAnoProgramacao;
		this.bairroSecaoElegivel = bairroSecaoElegivel;
		this.exigSuspensa = exigSuspensa;
		this.stFerias = stFerias;
		this.utlimaDataMes = utlimaDataMes;
		this.primeiraDataMes = primeiraDataMes;
		this.anoMes = anoMes;
	}

	public ProgramacaoEntregaDto() {
		
	}
	
	

}

//