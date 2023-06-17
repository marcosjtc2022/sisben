package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;



@Entity

@Table(name = "perfil" , schema="dbo")
public class Perfil implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
    private Long id; 
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "func_in_escala", columnDefinition="BIT")
	private Boolean funcEscala;
	
	@Column(name = "func_in_usuario", columnDefinition="BIT")
	private Boolean funcUsuario;
	
	@Column(name = "func_in_calendario", columnDefinition="BIT")
	private Boolean funcCalendario;
	
	@Column(name = "func_in_cardapio", columnDefinition="BIT")
	private Boolean funcCardapio;
	
	@Column(name = "func_in_beneficio", columnDefinition="BIT")
	private Boolean funcBeneficio;
	
	@Column(name = "func_in_entrega", columnDefinition="BIT")
	private Boolean funcEntrega;
	
	@Column(name = "in_prog_entrega_incluir", columnDefinition="BIT")
	private Boolean progEntregaIncluir;
	
	@Column(name = "in_prog_entrega_excluir", columnDefinition="BIT")
	private Boolean progEntregaExcluir;
	
	@Column(name = "in_prog_entrega_alterar", columnDefinition="BIT")
	private Boolean progEntregaAlterar;
	
	@Column(name = "in_prog_entrega_consultar", columnDefinition="BIT")
	private Boolean progEntregaConsultar;
	
	@Column(name = "in_prog_entrega_24h_consultar", columnDefinition="BIT")
	private Boolean progEntrega24hConsultar;
	
	@Column(name = "in_prog_entrega_24h_incluir", columnDefinition="BIT")
	private Boolean progEntrega24hIncluir;
	
	@Column(name = "in_prog_entrega_24h_alterar", columnDefinition="BIT")
	private Boolean progEntrega24hAlterar;
	
	@Column(name = "in_prog_entrega_24h_excluir", columnDefinition="BIT")
	private Boolean progEntrega24hExcluir;
	
	@Column(name = "in_ctrl_entrega_incluir", columnDefinition="BIT")
	private Boolean ctrlEntregaIncluir;
	
	@Column(name = "in_ctrl_entrega_alterar", columnDefinition="BIT")
	private Boolean ctrlEntregaAlterar;
	
	@Column(name = "in_ctrl_entrega_excluir", columnDefinition="BIT")
	private Boolean ctrlEntregaExcluir;
	
	@Column(name = "in_ctrl_entrega_consultar", columnDefinition="BIT")
	private Boolean ctrlEntregaConsultar;
	
	@Column(name = "in_ctrl_entrega_aprov_consultar", columnDefinition="BIT")
	private Boolean ctrlEntregaAprovConsultar;
	
	@Column(name = "in_ctrl_entrega_aprov_incluir", columnDefinition="BIT")
	private Boolean ctrlEntregaAprovIncluir;
	
	@Column(name = "in_config_perfil_usu_IEAC", columnDefinition="BIT")
	private Boolean configPerfilUsuIEAC;
	
	@Column(name = "in_config_perfil_usu_consultar", columnDefinition="BIT")
	private Boolean configPerfilUsuConsultar;
	
	@Column(name = "in_config_local_ent_IEAC", columnDefinition="BIT")
	private Boolean configLocalEntIEAC;
	
	@Column(name = "in_config_local_ent_consultar", columnDefinition="BIT")
	private Boolean configLocalEntConsultar;
	
	@Column(name = "in_config_vl_marmita_IEAC", columnDefinition="BIT")
	private Boolean configVlMarmitaIEAC;
	
	@Column(name = "in_config_vl_marmita_consultar", columnDefinition="BIT")
	private Boolean configVlMarmitaConsultar;
	
	@Column(name = "in_config_justificativa_IC", columnDefinition="BIT")
	private Boolean configJustificativaIC;
	
	@Column(name = "in_config_justificativa_IEAC", columnDefinition="BIT")
	private Boolean configJustificativaIEAC;
	
	@Column(name = "in_config_justificativa_consultar", columnDefinition="BIT")
	private Boolean configJustificativaConsultar;
	
	@Column(name = "in_config_calendario_IEAC", columnDefinition="BIT")
	private Boolean configCalendarioIEAC;
	
	@Column(name = "in_config_fornecedor_IEAC", columnDefinition="BIT")
	private Boolean configFornecedorIEAC;
	
	@Column(name = "in_config_fornecedor_consultar", columnDefinition="BIT")
	private Boolean configFornecedorConsultar;
	
	@Column(name = "in_config_relatorio", columnDefinition="BIT")
	private Boolean configRelatorio;
	

	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
//	//Mapenado classe PerfilFuncionalidade 
//	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
//	Set<PerfilFuncionalidade> perfis;	
	
//	//Mapenado classe usuario  man 17.06.2023
//	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
//	Set<Usuario> perflUsuarios;
	
	
	public Boolean getConfigRelatorio() {
		return configRelatorio;
	}


	public void setConfigRelatorio(Boolean configRelatorio) {
		this.configRelatorio = configRelatorio;
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


	public Boolean getConfigCalendarioIEAC() {
		return configCalendarioIEAC;
	}


	public void setConfigCalendarioIEAC(Boolean configCalendarioIEAC) {
		this.configCalendarioIEAC = configCalendarioIEAC;
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


	public Boolean getCtrlEntregaIncluir() {
		return ctrlEntregaIncluir;
	}


	public void setCtrlEntregaIncluir(Boolean ctrlEntregaIncluir) {
		this.ctrlEntregaIncluir = ctrlEntregaIncluir;
	}
	

	public Boolean getProgEntrega24hExcluir() {
		return progEntrega24hExcluir;
	}


	public void setProgEntrega24hExcluir(Boolean progEntrega24hExcluir) {
		this.progEntrega24hExcluir = progEntrega24hExcluir;
	}


	public Boolean getProgEntrega24hAlterar() {
		return progEntrega24hAlterar;
	}


	public void setProgEntrega24hAlterar(Boolean progEntrega24hAlterar) {
		this.progEntrega24hAlterar = progEntrega24hAlterar;
	}


	public Boolean getProgEntrega24hIncluir() {
		return progEntrega24hIncluir;
	}


	public void setProgEntrega24hIncluir(Boolean progEntrega24hIncluir) {
		this.progEntrega24hIncluir = progEntrega24hIncluir;
	}


	public Boolean getProgEntrega24hConsultar() {
		return progEntrega24hConsultar;
	}


	public void setProgEntrega24hConsultar(Boolean progEntrega24hConsultar) {
		this.progEntrega24hConsultar = progEntrega24hConsultar;
	}


	public Boolean getProgEntregaConsultar() {
		return progEntregaConsultar;
	}


	public void setProgEntregaConsultar(Boolean progEntregaConsultar) {
		this.progEntregaConsultar = progEntregaConsultar;
	}


	public Boolean getProgEntregaAlterar() {
		return progEntregaAlterar;
	}


	public void setProgEntregaAlterar(Boolean progEntregaAlterar) {
		this.progEntregaAlterar = progEntregaAlterar;
	}


	public Boolean getProgEntregaExcluir() {
		return progEntregaExcluir;
	}


	public void setProgEntregaExcluir(Boolean progEntregaExcluir) {
		this.progEntregaExcluir = progEntregaExcluir;
	}


	public Boolean getProgEntregaIncluir() {
		return progEntregaIncluir;
	}


	public void setProgEntregaIncluir(Boolean progEntregaIncluir) {
		this.progEntregaIncluir = progEntregaIncluir;
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


	public Perfil(Long id, String descricao, Boolean funcEscala, Boolean funcUsuario, Boolean funcCalendario,
			Boolean funcCardapio, Boolean funcBeneficio, Boolean funcEntrega, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
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
	}


	public Perfil() {
		
	}
}