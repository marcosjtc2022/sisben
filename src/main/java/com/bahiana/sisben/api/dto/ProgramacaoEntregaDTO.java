package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.bahiana.sisben.model.entity.ProgramacaoEntrega;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@Builder
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class ProgramacaoEntregaDto {
	
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
	private LocalDateTime dataEntrega;
	
//	@Column(name = "data_solicitacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataSolicitacao;	
	
//	@Column(name = "solic_extra", columnDefinition="BIT")
	private Boolean solicExtra;
	
//	@Column(name = "status_aprov", columnDefinition="BIT")
	private Boolean stAprov;
	
	@Column(name = "data_ultima_modificacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataUltimaModificacao;
	
	//@Transient
	String tabelaProgramacaoEntrega;
	
	private String codSetor;
		
	private Long idUsuarioUltimaModificacao;
	
	
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

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
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

	
	
	
	public ProgramacaoEntregaDto(Long id, Long matriculaColaborador, String uaPrevista, String uaRealizada, Long idData,
			Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDateTime dataEntrega,
			LocalDateTime dataSolicitacao, Boolean solicExtra, Boolean stAprov, String tabelaProgramacaoEntrega) {
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
		this.tabelaProgramacaoEntrega = tabelaProgramacaoEntrega;
	}

	public ProgramacaoEntregaDto() {
		
	}
	
	@Override
	public String toString() {
		return "ProgramacaoEntregaDTO [id=" + id + ", matriculaColaborador=" + matriculaColaborador + ", uaPrevista="
				+ uaPrevista + ", uaRealizada=" + uaRealizada + ", idData=" + idData + ", idUa=" + idUa
				+ ", idJustificativa=" + idJustificativa + ", idValor=" + idValor + ", idUsuario=" + idUsuario
				+ ", dataEntrega=" + dataEntrega + ", dataSolicitacao=" + dataSolicitacao + ", solicExtra=" + solicExtra
				+ ", stAprov=" + stAprov + ", tabelaProgramacaoEntrega=" + tabelaProgramacaoEntrega + "]";
	}

	
	
	

	
	

}

//