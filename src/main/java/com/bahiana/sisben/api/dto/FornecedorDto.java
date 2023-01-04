package com.bahiana.sisben.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FornecedorDto {
	
	    private Long id; 
		
		private String descricao;
		
		private LocalDate dataInicioVigenciaContrato;
		
		private LocalDate dataFimVigenciaContrato;
		
		private String cnpj;
		
		private String endereco;
		
		private String telefone;
		
		private String email;
		
		private String inscricaoEstadual;
		
		private String inscricaoMunicipal;
		
		private LocalDateTime dataUltimaModificacao;
		
		private Long idUsuarioUltimaModificacao;

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

		public LocalDate getDataInicioVigenciaContrato() {
			return dataInicioVigenciaContrato;
		}

		public void setDataInicioVigenciaContrato(LocalDate dataInicioVigenciaContrato) {
			this.dataInicioVigenciaContrato = dataInicioVigenciaContrato;
		}

		public LocalDate getDataFimVigenciaContrato() {
			return dataFimVigenciaContrato;
		}

		public void setDataFimVigenciaContrato(LocalDate dataFimVigenciaContrato) {
			this.dataFimVigenciaContrato = dataFimVigenciaContrato;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getInscricaoEstadual() {
			return inscricaoEstadual;
		}

		public void setInscricaoEstadual(String inscricaoEstadual) {
			this.inscricaoEstadual = inscricaoEstadual;
		}

		public String getInscricaoMunicipal() {
			return inscricaoMunicipal;
		}

		public void setInscricaoMunicipal(String inscricaoMunicipal) {
			this.inscricaoMunicipal = inscricaoMunicipal;
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

		public FornecedorDto(Long id, String descricao, LocalDate dataInicioVigenciaContrato,
				LocalDate dataFimVigenciaContrato, String cnpj, String endereco, String telefone, String email,
				String inscricaoEstadual, String inscricaoMunicipal, LocalDateTime dataUltimaModificacao,
				Long idUsuarioUltimaModificacao) {
			super();
			this.id = id;
			this.descricao = descricao;
			this.dataInicioVigenciaContrato = dataInicioVigenciaContrato;
			this.dataFimVigenciaContrato = dataFimVigenciaContrato;
			this.cnpj = cnpj;
			this.endereco = endereco;
			this.telefone = telefone;
			this.email = email;
			this.inscricaoEstadual = inscricaoEstadual;
			this.inscricaoMunicipal = inscricaoMunicipal;
			this.dataUltimaModificacao = dataUltimaModificacao;
			this.idUsuarioUltimaModificacao = idUsuarioUltimaModificacao;
		}
		
		public FornecedorDto() {
			
		}
		
		


}
