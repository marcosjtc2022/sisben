package com.bahiana.sisben.specification;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.bahiana.sisben.model.entity.ProgramacaoEntrega;

public class ProgramacaoEntregaSpecification {
	
	@Id
    private Long id;	
	
	//@Column(name="matricula_colaborador")
	private Long matriculaColaborador;
	
	//@Column(name="ua_prevista")
	private String uaPrevista;
	
	//@Column(name="ua_realizada")
	private String uaRealizada;
	
//	@Column(name = "id_data")
    private Long idData;
	 
//    @Column(name = "id_ua")
    private Long idUa;
    
//    @Column(name = "id_justificativa")
    private Long idJustificativa;
    
//    @Column(name = "id_valor")
    private Long idValor;
    
//    @Column(name = "id_usuario")
    private Long idUsuario;
	
//	@Column(name = "data_entrega")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataEntrega;
	
//	@Column(name = "data_solicitacao")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class   )
	private LocalDateTime dataSolicitacao;	
	
//	@Column(name = "solic_extra", columnDefinition="BIT")
	private Boolean solicExtra;
	
//	@Column(name = "status_aprov", columnDefinition="BIT")
	private Boolean stAprov;
	
	@Transient
	String tabelaProgramacaoEntrega;

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

	public String getUaPrevista() {
		return uaPrevista;
	}

	public void setUaPrevista(String uaPrevista) {
		this.uaPrevista = uaPrevista;
	}

	public String getUaRealizada() {
		return uaRealizada;
	}

	public void setUaRealizada(String uaRealizada) {
		this.uaRealizada = uaRealizada;
	}

	public Long getIdData() {
		return idData;
	}

	public void setIdData(Long idData) {
		this.idData = idData;
	}

	public Long getIdUa() {
		return idUa;
	}

	public void setIdUa(Long idUa) {
		this.idUa = idUa;
	}

	public Long getIdJustificativa() {
		return idJustificativa;
	}

	public void setIdJustificativa(Long idJustificativa) {
		this.idJustificativa = idJustificativa;
	}

	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Boolean getSolicExtra() {
		return solicExtra;
	}

	public void setSolicExtra(Boolean solicExtra) {
		this.solicExtra = solicExtra;
	}

	public Boolean getStAprov() {
		return stAprov;
	}

	public void setStAprov(Boolean stAprov) {
		this.stAprov = stAprov;
	}

	public String getTabelaProgramacaoEntrega() {
		return tabelaProgramacaoEntrega;
	}

	public void setTabelaProgramacaoEntrega(String tabelaProgramacaoEntrega) {
		this.tabelaProgramacaoEntrega = tabelaProgramacaoEntrega;
	}

	public ProgramacaoEntregaSpecification(Long id, Long matriculaColaborador, String uaPrevista, String uaRealizada,
			Long idData, Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDateTime dataEntrega,
			LocalDateTime dataSolicitacao, Boolean solicExtra, Boolean stAprov, String tabelaProgramacaoEntrega) {
		super();
		this.id = id;
		this.matriculaColaborador = matriculaColaborador;
		this.uaPrevista = uaPrevista;
		this.uaRealizada = uaRealizada;
		this.idData = idData;
		this.idUa = idUa;
		this.idJustificativa = idJustificativa;
		this.idValor = idValor;
		this.idUsuario = idUsuario;
		this.dataEntrega = dataEntrega;
		this.dataSolicitacao = dataSolicitacao;
		this.solicExtra = solicExtra;
		this.stAprov = stAprov;
		this.tabelaProgramacaoEntrega = tabelaProgramacaoEntrega;
	}
	
	
	public ProgramacaoEntregaSpecification() {
		
	}
	
	public Specification<ProgramacaoEntrega> toSpec(){
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<>();
			if (StringUtils.hasText(uaPrevista)) {
				Path<String> campoUaPrevista = root.<String>get("uaPrevista");
				//Predicate PredicadoNome =  builder.equal(campoUaPrevista, uaPrevista);
				Predicate PredicadoLike =  builder.like(campoUaPrevista, "%"+uaPrevista+"%");
				//predicados.add(PredicadoNome);
				predicados.add(PredicadoLike);
				
			}
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}

	

}
