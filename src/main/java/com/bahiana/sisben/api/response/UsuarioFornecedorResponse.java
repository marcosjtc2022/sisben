package com.bahiana.sisben.api.response;

public class UsuarioFornecedorResponse {
	
    private Long idUsuario;

    private Long idFornecedor;
	
	private String nomeColaborador;
	
	private String descricao;
	
	private String cnpj;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public UsuarioFornecedorResponse(Long idUsuario, Long idFornecedor, String nomeColaborador, String descricao,
			String cnpj) {
		super();
		this.idUsuario = idUsuario;
		this.idFornecedor = idFornecedor;
		this.nomeColaborador = nomeColaborador;
		this.descricao = descricao;
		this.cnpj = cnpj;
	}
	
	
	public UsuarioFornecedorResponse() {
		
	}
	
	


}
