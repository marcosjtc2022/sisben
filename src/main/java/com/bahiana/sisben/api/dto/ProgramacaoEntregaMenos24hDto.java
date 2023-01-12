package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

public class ProgramacaoEntregaMenos24hDto {
	
	
	@Id
    private Long id;	
	
	//@Column(name="matricula_colaborador")
	private Long matriculaColaborador;
	
	//@Column(name="ua_prevista")
	private String uaPrevista;
	
	//@Column(name="ua_realizada")
	private String uaRealizada;
	
//	@Column(name = "id_data")
    private Long idData;
	 
//    @Column(name = "id_ua")
    private Long idUa;
    
//    @Column(name = "id_justificativa")
    private Long idJustificativa;
    
//    @Column(name = "id_valor")
    private Long idValor;
    
//    @Column(name = "id_usuario")
    private Long idUsuario;
	
//	@Column(name = "data_entrega")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataEntrega;
	
//	@Column(name = "data_solicitacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataSolicitacao;	
	
//	@Column(name = "solic_extra", columnDefinition="BIT")
	private Boolean solicExtra;
	
//	@Column(name = "status_aprov", columnDefinition="BIT")
	private Boolean stAprov;
	
	private Long idUsuarioUltimaModificacao;
	
	//@Column(name = "data_ultima_modificacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataUltimaModificacao;

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
	
	
	public ProgramacaoEntregaMenos24hDto(Long id, Long matriculaColaborador, String uaPrevista, String uaRealizada,
			Long idData, Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDate dataEntrega,
			LocalDate dataSolicitacao, Boolean solicExtra, Boolean stAprov, Long idUsuarioUltimaModificacao,
			LocalDateTime dataUltimaModificacao) {
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
	}

	public ProgramacaoEntregaMenos24hDto() {
		
	}
	
	


}
