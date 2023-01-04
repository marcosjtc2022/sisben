package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity

@Table(name = "suspensao_elegibilidade" , schema="dbo")
public class SuspensaoElegibilidade implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_suspensao_elegibilidade")
    private Long id;
	
	@Column(name = "matricula_colaborador")
    private Long matriculaColaborador;
	
	@Column(name="nome_colaborador")
	private String nomeColaborador;
	
	@Column(name = "data_inicial")
	private LocalDate dataInicial;
		
	@Column(name = "data_final")
	private LocalDate dataFinal;
	
	@Column(name="justificativa")
	private String justificativaSuspensao;
	
	@Column(name = "data_ultima_modificacao")
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	//mapeando a classe SuspensaoElegibilidade.
    //Não tem esta relação. Se estiver uma, não pode estar na outra.
    //JPA não tem relacionamento 0 x n.
//	//Mapenado classe ProgramacaoEntrega não tem zero to many
//	@OneToMany(mappedBy = "suspensaoEligibilidade", fetch = FetchType.EAGER)
//	private Set<ProgramacaoEntrega> programacaoElegiveis;
	
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

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getJustificativaSuspensao() {
		return justificativaSuspensao;
	}

	public void setJustificativaSuspensao(String justificativaSuspensao) {
		this.justificativaSuspensao = justificativaSuspensao;
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

	
	
	

	public SuspensaoElegibilidade(Long id, Long matriculaColaborador, String nomeColaborador, LocalDate dataInicial,
			LocalDate dataFinal, String justificativaSuspensao, LocalDateTime dataUltimaModificacao,
			Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.matriculaColaborador = matriculaColaborador;
		this.nomeColaborador = nomeColaborador;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.justificativaSuspensao = justificativaSuspensao;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public SuspensaoElegibilidade() {
		
	}
	
	
	

	
	
	


}
