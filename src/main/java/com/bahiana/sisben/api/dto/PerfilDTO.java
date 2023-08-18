package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

public class PerfilDto {
	
	
	private Long id; 
	
	private String descricao;
	
	private Boolean funcEscala;
	
	private Boolean funcUsuario;
	
	private Boolean funcCalendario;
		
	private Boolean funcCardapio;
	
	private Boolean funcBeneficio;
	
	private Boolean funcEntrega;
	
	private LocalDateTime dataUltimaModificacao;
		
	private Long idUsuarioUltimaModificacao;
	
	//
	
	
	private Boolean progEntregaIncluir;
	
	
	private Boolean progEntregaExcluir;
	
	
	private Boolean progEntregaAlterar;
	
	
	private Boolean progEntregaConsultar;
	
	
	private Boolean progEntrega24hConsultar;
	
	
	private Boolean progEntrega24hIncluir;
	
	
	private Boolean progEntrega24hAlterar;
	
	
	private Boolean progEntrega24hExcluir;
	
	
	private Boolean ctrlEntregaIncluir;
	
	
	private Boolean ctrlEntregaAlterar;
	
	
	private Boolean ctrlEntregaExcluir;
	
	
	private Boolean ctrlEntregaConsultar;
	
	
	private Boolean ctrlEntregaAprovConsultar;
	
	
	private Boolean ctrlEntregaAprovIncluir;
	
	
	private Boolean configPerfilUsuIEAC;
	
	
	private Boolean configPerfilUsuConsultar;
	
	
	private Boolean configLocalEntIEAC;
	
	
	private Boolean configLocalEntConsultar;
	
	
	private Boolean configVlMarmitaIEAC;
	
	
	private Boolean configVlMarmitaConsultar;
	
	
	private Boolean configJustificativaIC;
	
	
	private Boolean configJustificativaIEAC;
	
	
	private Boolean configJustificativaConsultar;
	
	
	private Boolean configCalendarioIEAC;
	
	
	private Boolean configFornecedorIEAC;
	
	
	private Boolean configFornecedorConsultar;
	
	
	private Boolean configRelatorio;
	
	
	private Boolean configSuspensaoElegIEAC;
	
	
	private Boolean configSuspensaoElegConsultar;
		
	//
	

	public Boolean getConfigSuspensaoElegIEAC() {
		return configSuspensaoElegIEAC;
	}

	public Boolean getConfigSuspensaoElegConsultar() {
		return configSuspensaoElegConsultar;
	}

	public void setConfigSuspensaoElegConsultar(Boolean configSuspensaoElegConsultar) {
		this.configSuspensaoElegConsultar = configSuspensaoElegConsultar;
	}

	public void setConfigSuspensaoElegIEAC(Boolean configSuspensaoElegIEAC) {
		this.configSuspensaoElegIEAC = configSuspensaoElegIEAC;
	}

	public Boolean getProgEntregaIncluir() {
		return progEntregaIncluir;
	}

	public void setProgEntregaIncluir(Boolean progEntregaIncluir) {
		this.progEntregaIncluir = progEntregaIncluir;
	}

	public Boolean getProgEntregaExcluir() {
		return progEntregaExcluir;
	}

	public void setProgEntregaExcluir(Boolean progEntregaExcluir) {
		this.progEntregaExcluir = progEntregaExcluir;
	}

	public Boolean getProgEntregaAlterar() {
		return progEntregaAlterar;
	}

	public void setProgEntregaAlterar(Boolean progEntregaAlterar) {
		this.progEntregaAlterar = progEntregaAlterar;
	}

	public Boolean getProgEntregaConsultar() {
		return progEntregaConsultar;
	}

	public void setProgEntregaConsultar(Boolean progEntregaConsultar) {
		this.progEntregaConsultar = progEntregaConsultar;
	}

	public Boolean getProgEntrega24hConsultar() {
		return progEntrega24hConsultar;
	}

	public void setProgEntrega24hConsultar(Boolean progEntrega24hConsultar) {
		this.progEntrega24hConsultar = progEntrega24hConsultar;
	}

	public Boolean getProgEntrega24hIncluir() {
		return progEntrega24hIncluir;
	}

	public void setProgEntrega24hIncluir(Boolean progEntrega24hIncluir) {
		this.progEntrega24hIncluir = progEntrega24hIncluir;
	}

	public Boolean getProgEntrega24hAlterar() {
		return progEntrega24hAlterar;
	}

	public void setProgEntrega24hAlterar(Boolean progEntrega24hAlterar) {
		this.progEntrega24hAlterar = progEntrega24hAlterar;
	}

	public Boolean getProgEntrega24hExcluir() {
		return progEntrega24hExcluir;
	}

	public void setProgEntrega24hExcluir(Boolean progEntrega24hExcluir) {
		this.progEntrega24hExcluir = progEntrega24hExcluir;
	}

	public Boolean getCtrlEntregaIncluir() {
		return ctrlEntregaIncluir;
	}

	public void setCtrlEntregaIncluir(Boolean ctrlEntregaIncluir) {
		this.ctrlEntregaIncluir = ctrlEntregaIncluir;
	}

	public Boolean getCtrlEntregaAlterar() {
		return ctrlEntregaAlterar;
	}

	public void setCtrlEntregaAlterar(Boolean ctrlEntregaAlterar) {
		this.ctrlEntregaAlterar = ctrlEntregaAlterar;
	}

	public Boolean getCtrlEntregaExcluir() {
		return ctrlEntregaExcluir;
	}

	public void setCtrlEntregaExcluir(Boolean ctrlEntregaExcluir) {
		this.ctrlEntregaExcluir = ctrlEntregaExcluir;
	}

	public Boolean getCtrlEntregaConsultar() {
		return ctrlEntregaConsultar;
	}

	public void setCtrlEntregaConsultar(Boolean ctrlEntregaConsultar) {
		this.ctrlEntregaConsultar = ctrlEntregaConsultar;
	}

	public Boolean getCtrlEntregaAprovConsultar() {
		return ctrlEntregaAprovConsultar;
	}

	public void setCtrlEntregaAprovConsultar(Boolean ctrlEntregaAprovConsultar) {
		this.ctrlEntregaAprovConsultar = ctrlEntregaAprovConsultar;
	}

	public Boolean getCtrlEntregaAprovIncluir() {
		return ctrlEntregaAprovIncluir;
	}

	public void setCtrlEntregaAprovIncluir(Boolean ctrlEntregaAprovIncluir) {
		this.ctrlEntregaAprovIncluir = ctrlEntregaAprovIncluir;
	}

	public Boolean getConfigPerfilUsuIEAC() {
		return configPerfilUsuIEAC;
	}

	public void setConfigPerfilUsuIEAC(Boolean configPerfilUsuIEAC) {
		this.configPerfilUsuIEAC = configPerfilUsuIEAC;
	}

	public Boolean getConfigPerfilUsuConsultar() {
		return configPerfilUsuConsultar;
	}

	public void setConfigPerfilUsuConsultar(Boolean configPerfilUsuConsultar) {
		this.configPerfilUsuConsultar = configPerfilUsuConsultar;
	}

	public Boolean getConfigLocalEntIEAC() {
		return configLocalEntIEAC;
	}

	public void setConfigLocalEntIEAC(Boolean configLocalEntIEAC) {
		this.configLocalEntIEAC = configLocalEntIEAC;
	}

	public Boolean getConfigLocalEntConsultar() {
		return configLocalEntConsultar;
	}

	public void setConfigLocalEntConsultar(Boolean configLocalEntConsultar) {
		this.configLocalEntConsultar = configLocalEntConsultar;
	}

	public Boolean getConfigVlMarmitaIEAC() {
		return configVlMarmitaIEAC;
	}

	public void setConfigVlMarmitaIEAC(Boolean configVlMarmitaIEAC) {
		this.configVlMarmitaIEAC = configVlMarmitaIEAC;
	}

	public Boolean getConfigVlMarmitaConsultar() {
		return configVlMarmitaConsultar;
	}

	public void setConfigVlMarmitaConsultar(Boolean configVlMarmitaConsultar) {
		this.configVlMarmitaConsultar = configVlMarmitaConsultar;
	}

	public Boolean getConfigJustificativaIC() {
		return configJustificativaIC;
	}

	public void setConfigJustificativaIC(Boolean configJustificativaIC) {
		this.configJustificativaIC = configJustificativaIC;
	}

	public Boolean getConfigJustificativaIEAC() {
		return configJustificativaIEAC;
	}

	public void setConfigJustificativaIEAC(Boolean configJustificativaIEAC) {
		this.configJustificativaIEAC = configJustificativaIEAC;
	}

	public Boolean getConfigJustificativaConsultar() {
		return configJustificativaConsultar;
	}

	public void setConfigJustificativaConsultar(Boolean configJustificativaConsultar) {
		this.configJustificativaConsultar = configJustificativaConsultar;
	}

	public Boolean getConfigCalendarioIEAC() {
		return configCalendarioIEAC;
	}

	public void setConfigCalendarioIEAC(Boolean configCalendarioIEAC) {
		this.configCalendarioIEAC = configCalendarioIEAC;
	}

	public Boolean getConfigFornecedorIEAC() {
		return configFornecedorIEAC;
	}

	public void setConfigFornecedorIEAC(Boolean configFornecedorIEAC) {
		this.configFornecedorIEAC = configFornecedorIEAC;
	}

	public Boolean getConfigFornecedorConsultar() {
		return configFornecedorConsultar;
	}

	public void setConfigFornecedorConsultar(Boolean configFornecedorConsultar) {
		this.configFornecedorConsultar = configFornecedorConsultar;
	}

	public Boolean getConfigRelatorio() {
		return configRelatorio;
	}

	public void setConfigRelatorio(Boolean configRelatorio) {
		this.configRelatorio = configRelatorio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public Boolean getFuncEscala() {
		return funcEscala;
	}

	public void setFuncEscala(Boolean funcEscala) {
		this.funcEscala = funcEscala;
	}

	public Boolean getFuncUsuario() {
		return funcUsuario;
	}

	public void setFuncUsuario(Boolean funcUsuario) {
		this.funcUsuario = funcUsuario;
	}

	public Boolean getFuncCalendario() {
		return funcCalendario;
	}

	public void setFuncCalendario(Boolean funcCalendario) {
		this.funcCalendario = funcCalendario;
	}

	public Boolean getFuncCardapio() {
		return funcCardapio;
	}

	public void setFuncCardapio(Boolean funcCardapio) {
		this.funcCardapio = funcCardapio;
	}

	public Boolean getFuncBeneficio() {
		return funcBeneficio;
	}

	public void setFuncBeneficio(Boolean funcBeneficio) {
		this.funcBeneficio = funcBeneficio;
	}
	
	public Boolean getFuncEntrega() {
		return funcEntrega;
	}

	public void setFuncEntrega(Boolean funcEntrega) {
		this.funcEntrega = funcEntrega;
	}
	
	
	

//	public PerfilDto(Long id, String descricao, Boolean funcEscala, Boolean funcUsuario, Boolean funcCalendario,
//			Boolean funcCardapio, Boolean funcBeneficio, Boolean funcEntrega, LocalDateTime dataUltimaModificacao,
//			Long idUsuarioUltimaModificacao) {
//		super();
//		this.id = id;
//		this.descricao = descricao;
//		this.funcEscala = funcEscala;
//		this.funcUsuario = funcUsuario;
//		this.funcCalendario = funcCalendario;
//		this.funcCardapio = funcCardapio;
//		this.funcBeneficio = funcBeneficio;
//		this.funcEntrega = funcEntrega;
//		this.dataUltimaModificacao = dataUltimaModificacao;
//		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
//	}
	
	

	public PerfilDto() {
		
	}

public PerfilDto(Long id, String descricao, Boolean funcEscala, Boolean funcUsuario, Boolean funcCalendario,
		Boolean funcCardapio, Boolean funcBeneficio, Boolean funcEntrega, LocalDateTime dataUltimaModificacao,
		Long idUsuarioUltimaModificacao, Boolean progEntregaIncluir, Boolean progEntregaExcluir,
		Boolean progEntregaAlterar, Boolean progEntregaConsultar, Boolean progEntrega24hConsultar,
		Boolean progEntrega24hIncluir, Boolean progEntrega24hAlterar, Boolean progEntrega24hExcluir,
		Boolean ctrlEntregaIncluir, Boolean ctrlEntregaAlterar, Boolean ctrlEntregaExcluir,
		Boolean ctrlEntregaConsultar, Boolean ctrlEntregaAprovConsultar, Boolean ctrlEntregaAprovIncluir,
		Boolean configPerfilUsuIEAC, Boolean configPerfilUsuConsultar, Boolean configLocalEntIEAC,
		Boolean configLocalEntConsultar, Boolean configVlMarmitaIEAC, Boolean configVlMarmitaConsultar,
		Boolean configJustificativaIC, Boolean configJustificativaIEAC, Boolean configJustificativaConsultar,
		Boolean configCalendarioIEAC, Boolean configFornecedorIEAC, Boolean configFornecedorConsultar,
		Boolean configRelatorio, Boolean configSuspensaoElegIEAC, Boolean configSuspensaoElegConsultar) {
	super();
	this.id = id;
	this.descricao = descricao;
	this.funcEscala = funcEscala;
	this.funcUsuario = funcUsuario;
	this.funcCalendario = funcCalendario;
	this.funcCardapio = funcCardapio;
	this.funcBeneficio = funcBeneficio;
	this.funcEntrega = funcEntrega;
	this.dataUltimaModificacao = dataUltimaModificacao;
	this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	this.progEntregaIncluir = progEntregaIncluir;
	this.progEntregaExcluir = progEntregaExcluir;
	this.progEntregaAlterar = progEntregaAlterar;
	this.progEntregaConsultar = progEntregaConsultar;
	this.progEntrega24hConsultar = progEntrega24hConsultar;
	this.progEntrega24hIncluir = progEntrega24hIncluir;
	this.progEntrega24hAlterar = progEntrega24hAlterar;
	this.progEntrega24hExcluir = progEntrega24hExcluir;
	this.ctrlEntregaIncluir = ctrlEntregaIncluir;
	this.ctrlEntregaAlterar = ctrlEntregaAlterar;
	this.ctrlEntregaExcluir = ctrlEntregaExcluir;
	this.ctrlEntregaConsultar = ctrlEntregaConsultar;
	this.ctrlEntregaAprovConsultar = ctrlEntregaAprovConsultar;
	this.ctrlEntregaAprovIncluir = ctrlEntregaAprovIncluir;
	this.configPerfilUsuIEAC = configPerfilUsuIEAC;
	this.configPerfilUsuConsultar = configPerfilUsuConsultar;
	this.configLocalEntIEAC = configLocalEntIEAC;
	this.configLocalEntConsultar = configLocalEntConsultar;
	this.configVlMarmitaIEAC = configVlMarmitaIEAC;
	this.configVlMarmitaConsultar = configVlMarmitaConsultar;
	this.configJustificativaIC = configJustificativaIC;
	this.configJustificativaIEAC = configJustificativaIEAC;
	this.configJustificativaConsultar = configJustificativaConsultar;
	this.configCalendarioIEAC = configCalendarioIEAC;
	this.configFornecedorIEAC = configFornecedorIEAC;
	this.configFornecedorConsultar = configFornecedorConsultar;
	this.configRelatorio = configRelatorio;
	this.configSuspensaoElegIEAC = configSuspensaoElegIEAC;
	this.configSuspensaoElegConsultar = configSuspensaoElegConsultar;
}

          	
	
	
	
	

}
