package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity

@Table(name = "programacao_entrega" , schema="dbo" )
public class ProgramacaoEntrega implements Serializable{
	
	@Id //Colocar identity
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_programacao_entrega")
    private Long id;
	
	@Column(name="matricula_colaborador")
	private String matriculaColaborador;
	
	@Column(name="ua_prevista")
	private String uaPrevista;
	
	@Column(name="ua_realizada")
	private String uaRealizada;
	
	@Column(name = "id_data")
    private Long idData;
	 
    @Column(name = "id_ua")
    private Long idUa;
    
    @Column(name = "id_justificativa")
    private Long idJustificativa;
    
    @Column(name = "id_valor")
    private Long idValor;
    
    @Column(name = "id_usuario_entrega")
    private Long idUsuario;
	
//	@Column(name = "data_prevista")
//	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
//	private LocalDate dataPrevista;
//	
	@Column(name = "data_entrega")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataEntrega;
	
	@Column(name = "data_solicitacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDate dataSolicitacao;	
	
	@Column(name = "solic_extra", columnDefinition="BIT")
	private Boolean solicExtra;
	
	@Column(name = "st_ferias", columnDefinition="BIT")
	private Boolean stFerias;
	
	@Column(name = "status_aprov", columnDefinition="BIT")
	private Boolean stAprov;
	
	@Column(name = "exig_suspensa", columnDefinition="BIT")
	private Boolean exigSuspensa;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	@Column(name="dia_da_semana")
	private String diaDaSemana;
	
	@Column(name = "data_programacao")
	private LocalDate dataProgramacao;
	
	@Column(name="descricaoFeriado")
	private String descricaoFeriado;
	
	@Transient
	String tabelaProgramacaoEntrega;
	
	public Boolean getStFerias() {
		return stFerias;
	}

	public void setStFerias(Boolean stFerias) {
		this.stFerias = stFerias;
	}

	public Boolean getExigSuspensa() {
		return exigSuspensa;
	}

	public void setExigSuspensa(Boolean exigSuspensa) {
		this.exigSuspensa = exigSuspensa;
	}

	public String getTabelaProgramacaoEntrega() {
		return tabelaProgramacaoEntrega;
	}

	public void setTabelaProgramacaoEntrega(String tabelaProgramacaoEntrega) {
		this.tabelaProgramacaoEntrega = tabelaProgramacaoEntrega;
	}
	
//	public Calendario getCalendario() {
//		return calendario;
//	}
//
//	public void setCalendario(Calendario calendario) {
//		this.calendario = calendario;
//	}


// JPA não aceita mapeamento 0 x N. Nem tente!!!
//	//mapeando a classe Calendario
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(updatable=false,insertable=false,name = "id_data")
//    Calendario calendario;

    
//    //mapeando a classe Elegibilidade.
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(updatable=false,insertable=false,name = "matricula_colaborador")
//    Elegibilidade elegibilidade;
    
//    //mapeando a classe SuspensaoElegibilidade.
      //Não tem esta relação. Se estiver uma, não pode estar na outra.
      //JPA não tem relacionamento 0 x n.
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(updatable=false,insertable=false, name = "matricula_colaborador" )
//    SuspensaoEligibilidade suspensaoEligibilidade;
    
    //mapeando a classe UnidadeAcademica.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_ua")
    UnidadeAcademica unidadeAcademica;
    
//    //mapeando a classe Justificativa. voltar
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(updatable=false,insertable=false,name = "id_justificativa")
//    Justificativa justificativa;
    
  //mapeando a classe Justificativa.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_justificativa")
    Justificativa justificativa;
//    
    //mapeando a classe ValorMarmita.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_valor")
    ValorMarmita valorMarmita;
    
    //mapeando a classe Usuario (Usuario da entrega).
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_usuario_entrega")
    Usuario usuarioEntrega;
    
//  //mapeando a classe VwSisbenFuncionario.
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(updatable=false,insertable=false,name = "matricula_colaborador")
//    VwSisbenFuncionario funcionario;
    
    
    
    
//    //Mapenado classe CentroCusto. 
//  	@OneToMany(mappedBy = "programacaoEntrega", fetch = FetchType.EAGER)
//  	Set<CentroCustoPercRateio> centroCusto;

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

//	public Elegibilidade getElegibilidade() {
//		return elegibilidade;
//	}
//
//	public void setElegibilidade(Elegibilidade elegibilidade) {
//		this.elegibilidade = elegibilidade;
//	}
	
	public Justificativa getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}

	public UnidadeAcademica getUnidadeAcademica() {
		return unidadeAcademica;
	}

	public void setUnidadeAcademica(UnidadeAcademica unidadeAcademica) {
		this.unidadeAcademica = unidadeAcademica;
	}

//	public Justificativa getJustificativa() {
//		return justificativa;
//	}
//
//	public void setJustificativa(Justificativa justificativa) {
//		this.justificativa = justificativa;
//	}

	public ValorMarmita getValorMarmita() {
		return valorMarmita;
	}

	public void setValorMarmita(ValorMarmita valorMarmita) {
		this.valorMarmita = valorMarmita;
	}

	public Usuario getUsuarioEntrega() {
		return usuarioEntrega;
	}

	public void setUsuarioEntrega(Usuario usuarioEntrega) {
		this.usuarioEntrega = usuarioEntrega;
	}

//	public Set<CentroCustoPercRateio> getCentroCusto() {
//		return centroCusto;
//	}
//
//	public void setCentroCusto(Set<CentroCustoPercRateio> centroCusto) {
//		this.centroCusto = centroCusto;
//	}
	
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
	
	
	
	public ProgramacaoEntrega(Long id, String matriculaColaborador, String uaPrevista, String uaRealizada, Long idData,
			Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDate dataEntrega,
			LocalDate dataSolicitacao, Boolean solicExtra, Boolean stFerias, Boolean stAprov, Boolean exigSuspensa,
			LocalDateTime dataUltimaModificacao, Long idUsuarioUltimaModificacao, String diaDaSemana,
			LocalDate dataProgramacao, String descricaoFeriado) {
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
		this.stFerias = stFerias;
		this.stAprov = stAprov;
		this.exigSuspensa = exigSuspensa;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		this.diaDaSemana = diaDaSemana;
		this.dataProgramacao = dataProgramacao;
		this.descricaoFeriado = descricaoFeriado;
	}

	public ProgramacaoEntrega() {
		
		
		
	}
	
  	
  	
	
}

