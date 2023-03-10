package com.bahiana.sisben.specification;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
	private String matriculaColaborador;
	
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
	
	private LocalDate dataProgramacao;
	
	private String anoMes;
	
	private String codSetor;
	
	
	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}

	public LocalDate getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(LocalDate dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculaColaborador() {
		return matriculaColaborador;
	}

	public void setMatriculaColaborador(String matriculaColaborador) {
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
	
	public ProgramacaoEntregaSpecification(Long id, String matriculaColaborador, String uaPrevista, String uaRealizada,
			Long idData, Long idUa, Long idJustificativa, Long idValor, Long idUsuario, LocalDateTime dataEntrega,
			LocalDateTime dataSolicitacao, Boolean solicExtra, Boolean stAprov, LocalDate dataProgramacao,
			String anoMes, String codSetor) {
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
		this.dataProgramacao = dataProgramacao;
		this.anoMes = anoMes;
		this.codSetor = codSetor;
	}

	public ProgramacaoEntregaSpecification() {
		
	}
	
	public Specification<ProgramacaoEntrega> toSpec(){
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<>();
			
			if (StringUtils.hasText(uaRealizada)) {
				Path<String> campoUaPrevista = root.<String>get("uaRealizada");
				Predicate predicadoLike =  builder.like(campoUaPrevista, "%"+uaRealizada+"%");
				predicados.add(predicadoLike);
			}
			
			if (StringUtils.hasText(matriculaColaborador)) {
				Path<String> campoMatriculaColaborador = root.<String>get("matriculaColaborador");
				Predicate predicadoMatricula =  builder.equal(campoMatriculaColaborador, matriculaColaborador);
				predicados.add(predicadoMatricula);
			}
			
			if (StringUtils.hasText(anoMes)) {
				Path<String> campoAnoMes = root.<String>get("anoMes");
				Predicate predicadoAnoMes =  builder.equal(campoAnoMes, anoMes);
				predicados.add(predicadoAnoMes);
			}
			
			if (StringUtils.hasText(codSetor)) {
				Path<String> campoCodSetor = root.<String>get("codSetor");
				Predicate predicadoCodSetor =  builder.equal(campoCodSetor, codSetor);
				predicados.add(predicadoCodSetor);
			}
			
			
			
			if(idUsuario != null) {
				Object teste = root.get("idUsuario").isNull();
			}
			
//			if (StringUtils.hasText(idUsuario)) {
//				Path<Long> campoIdUsuario = root.<Long>get("idUsuario");
//				Predicate predicadoIdusuario =  builder.equal(campoIdUsuario, idUsuario);
//				predicados.add(predicadoIdusuario);
//			}
			
			
			
			
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}

	

}
