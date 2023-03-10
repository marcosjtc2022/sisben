package com.bahiana.sisben.api.dto;



public class VwSisbenSetorDto {
	
	
	 private String codSetor;
	
	 private String descrSetor;

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public String getDescrSetor() {
		return descrSetor;
	}

	public void setDescrSetor(String descrSetor) {
		this.descrSetor = descrSetor;
	}

	public VwSisbenSetorDto(String codSetor, String descrSetor) {
		super();
		this.codSetor = codSetor;
		this.descrSetor = descrSetor;
	}
	
	public VwSisbenSetorDto() {
		
	}

		 
	 

}
