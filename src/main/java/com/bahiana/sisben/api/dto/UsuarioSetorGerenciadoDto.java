package com.bahiana.sisben.api.dto;

import javax.persistence.Column;

public class UsuarioSetorGerenciadoDto {
	
    private Long id;
	
    private Long idUsuarioLider; 
	
	private String codSetor;
	
	private String descricao;
	
	private String tabelaCodSetores;
	
	private String tabelaIdUsuarioSetores;
	
	private String tabelaSetores;
	
	public String getTabelaCodSetores() {
		return tabelaCodSetores;
	}

	public void setTabelaCodSetores(String tabelaCodSetores) {
		this.tabelaCodSetores = tabelaCodSetores;
	}

	public String getTabelaIdUsuarioSetores() {
		return tabelaIdUsuarioSetores;
	}

	public void setTabelaIdUsuarioSetores(String tabelaIdUsuarioSetores) {
		this.tabelaIdUsuarioSetores = tabelaIdUsuarioSetores;
	}

	public String getTabelaSetores() {
		return tabelaSetores;
	}

	public void setTabelaSetores(String tabelaSetores) {
		this.tabelaSetores = tabelaSetores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuarioLider() {
		return idUsuarioLider;
	}

	public void setIdUsuarioLider(Long idUsuarioLider) {
		this.idUsuarioLider = idUsuarioLider;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UsuarioSetorGerenciadoDto(Long id, Long idUsuarioLider, String codSetor, String descricao) {
		this.id = id;
		this.idUsuarioLider = idUsuarioLider;
		this.codSetor = codSetor;
		this.descricao = descricao;
	}
	
	public UsuarioSetorGerenciadoDto() {
		
	}

	
	
	

}
