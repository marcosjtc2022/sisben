package com.bahiana.sisben.specification;

public class ProgEntregaCriteria {
	
	private Long id;
	
	private Long idUsuario;
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProgEntregaCriteria(Long id) {
		super();
		this.id = id;
	}
	
	public ProgEntregaCriteria() {
		
	}
	
	
	

}
