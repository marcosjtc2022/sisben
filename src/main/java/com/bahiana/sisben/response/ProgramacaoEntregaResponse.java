package com.bahiana.sisben.response;

import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.bahiana.sisben.model.entity.Elegibilidade;
import com.bahiana.sisben.model.entity.Setor;

public class ProgramacaoEntregaResponse {
	
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
	
	@Transient
	String tabelaProgramacaoEntrega;
	
	private Elegibilidade elegibilidade;
	
	private Setor setor;

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

	public String getTabelaProgramacaoEntrega() {
		return tabelaProgramacaoEntrega;
	}

	public void setTabelaProgramacaoEntrega(String tabelaProgramacaoEntrega) {
		this.tabelaProgramacaoEntrega = tabelaProgramacaoEntrega;
	}

	public Elegibilidade getElegibilidade() {
		return elegibilidade;
	}

	public void setElegibilidade(Elegibilidade elegibilidade) {
		this.elegibilidade = elegibilidade;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	

}
