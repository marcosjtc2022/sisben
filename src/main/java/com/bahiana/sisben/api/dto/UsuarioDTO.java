package com.bahiana.sisben.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Id;

public class UsuarioDto {
	
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
	
	private String codSetor;
	
	private LocalDateTime dataUltimaModificacao;
	
	private Long idUsuarioUltimaModificacao;
	
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

	public Boolean getExterno() {
		return externo;
	}

	public void setExterno(Boolean externo) {
		this.externo = externo;
	}
	
	public UsuarioDto(Long id, Long idPerfil, Long idUa, Long idFornecedor, String matriculaColaborador,
			String nomeColaborador, String emailUsuario, String senhaUsuario, Boolean stUsuarioExterno, Boolean externo,
			String codSetor, LocalDateTime dataUltimaModificacao, Long idUsuarioUltimaModificacao) {
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
		this.codSetor = codSetor;
		this.dataUltimaModificacao = dataUltimaModificacao;
		this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
	}

	public UsuarioDto() {
		
	}
	
	
	

}
