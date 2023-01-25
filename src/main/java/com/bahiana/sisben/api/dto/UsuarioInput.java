package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

public class UsuarioInput {
	
	//@Id
    private Long id;

    private Long idPerfil;
	
    private Long idUa;

    private Long idFornecedor;

    private String matriculaColaborador;
	
	private String nomeColaborador;
	
	private String emailUsuario;
	
	private String senhaUsuario;
	
	private Boolean stUsuarioExterno;
	
	private Boolean externo;
	
	private LocalDateTime dataUltimaModificacao;
	
	private Long idUsuarioUltimaModificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdUa() {
		return idUa;
	}

	public void setIdUa(Long idUa) {
		this.idUa = idUa;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getMatriculaColaborador() {
		return matriculaColaborador;
	}

	public void setMatriculaColaborador(String matriculaColaborador) {
		this.matriculaColaborador = matriculaColaborador;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Boolean getStUsuarioExterno() {
		return stUsuarioExterno;
	}

	public void setStUsuarioExterno(Boolean stUsuarioExterno) {
		this.stUsuarioExterno = stUsuarioExterno;
	}

	public Boolean getExterno() {
		return externo;
	}

	public void setExterno(Boolean externo) {
		this.externo = externo;
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

	public UsuarioInput(Long id, Long idPerfil, Long idUa, Long idFornecedor, String matriculaColaborador,
			String nomeColaborador, String emailUsuario, String senhaUsuario, Boolean stUsuarioExterno, Boolean externo,
			LocalDateTime dataUltimaModificacao, Long idUsuarioUltimaModificacao) {
		super();
		this.id = id;
		this.idPerfil = idPerfil;
		this.idUa = idUa;
		this.idFornecedor = idFornecedor;
		this.matriculaColaborador = matriculaColaborador;
		this.nomeColaborador = nomeColaborador;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.stUsuarioExterno = stUsuarioExterno;
		this.externo = externo;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}
	
	public UsuarioInput() {
		
	}
	
	


}
