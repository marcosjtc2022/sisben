package com.bahiana.sisben.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity

@Table(name = "usuario" , schema="dbo")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
    private Long id;
	
	@Column(name = "id_perfil")
    private Long idPerfil;
	
//	@Column(name = "cod_setor") apaguei da tabela usuario.
//    private String codSetor; 
	
	@Column(name = "id_ua")
    private Long idUa;
	
	@Column(name = "id_fornecedor")
    private Long idFornecedor;
	
	@Column(name = "matricula_colaborador")
    private Long matriculaColaborador;
	
	@Column(name="nome_colaborador")
	private String nomeColaborador;
	
	@Column(name="email")
	private String emailUsuario;
	
	@Column(name="senha")
	private String senhaUsuario;
	
	@Column(name = "status_usuario_externo", columnDefinition="BIT")
	private Boolean stUsuarioExterno;
	
	@Column(name = "externo", columnDefinition="BIT")
	private Boolean externo;
	
	@Column(name = "data_ultima_modificacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataUltimaModificacao;
	
	@Column(name="id_usuario_ultima_modificacao")
	private Long idUsuarioUltimaModificacao;
	
	//Mapenado classe UsuarioSetor 
	@OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
	Set<UsuarioSetor> Usuariosetores;	
	
	//Mapeando a classe perfil
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable=false,insertable=false,name = "id_perfil")
	Perfil perfil;	
	
	//Mapeando a classe fornecedor
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable=false,insertable=false,name = "id_fornecedor")
	Fornecedor fornecedor;	
	
	//Mapenado classe ProgramacaoEntrega 
	@OneToMany(mappedBy = "usuarioEntrega", fetch = FetchType.EAGER)
	Set<ProgramacaoEntrega> usuarioProgEntregas;
	
	//mapeando a classe UnidadeAcademica. **
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable=false,insertable=false,name = "id_ua")
    UnidadeAcademica unidadeAcademica;

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public UnidadeAcademica getUnidadeAcademica() {
		return unidadeAcademica;
	}

	public void setUnidadeAcademica(UnidadeAcademica unidadeAcademica) {
		this.unidadeAcademica = unidadeAcademica;
	}

	public Usuario() {
		
	}
		

	
	
	public Usuario(Long id, Long idPerfil, Long idUa, Long idFornecedor, Long matriculaColaborador,
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

	@Override
	public String toString() {
		return String.format(
				"Usuario [id=%s, idPerfil=%s, idUa=%s, idFornecedor=%s, matriculaColaborador=%s, nomeColaborador=%s, emailUsuario=%s, senhaUsuario=%s, stUsuarioExterno=%s, dataUltimaModificacao=%s, idUsuarioUltimaModificacao=%s]",
				id, idPerfil, idUa, idFornecedor, matriculaColaborador, nomeColaborador, emailUsuario, senhaUsuario,
				stUsuarioExterno, dataUltimaModificacao, idUsuarioUltimaModificacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataUltimaModificacao, emailUsuario, id, idFornecedor, idPerfil, idUa,
				idUsuarioUltimaModificacao, matriculaColaborador, nomeColaborador, senhaUsuario, stUsuarioExterno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dataUltimaModificacao, other.dataUltimaModificacao)
				&& Objects.equals(emailUsuario, other.emailUsuario) && Objects.equals(id, other.id)
				&& Objects.equals(idFornecedor, other.idFornecedor) && Objects.equals(idPerfil, other.idPerfil)
				&& Objects.equals(idUa, other.idUa)
				&& Objects.equals(idUsuarioUltimaModificacao, other.idUsuarioUltimaModificacao)
				&& Objects.equals(matriculaColaborador, other.matriculaColaborador)
				&& Objects.equals(nomeColaborador, other.nomeColaborador)
				&& Objects.equals(senhaUsuario, other.senhaUsuario)
				&& Objects.equals(stUsuarioExterno, other.stUsuarioExterno);
	}
	
	
	
	

}
