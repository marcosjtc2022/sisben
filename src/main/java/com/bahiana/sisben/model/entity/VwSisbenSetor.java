package com.bahiana.sisben.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VW_SISBEN_SETOR" , schema="dbo")
public class VwSisbenSetor {
	
	@Id
	@Column(name = "CODIGO")
    private String codSetor;
	
	@Column(name = "DESCRICAO")
    private String descrSetor;

	public String getCodSetor() {
		return codSetor;
	}

	public String getDescrSetor() {
		return descrSetor;
	}

	public void setDescrSetor(String descrSetor) {
		this.descrSetor = descrSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public VwSisbenSetor(String codSetor, String descrSetor) {
		super();
		this.codSetor = codSetor;
		this.descrSetor = descrSetor;
	}

	public VwSisbenSetor() {
		
	}
	
}
