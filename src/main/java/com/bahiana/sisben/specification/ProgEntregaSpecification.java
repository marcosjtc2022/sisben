package com.bahiana.sisben.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.Usuario;

public class ProgEntregaSpecification implements Specification<ProgramacaoEntrega> {

	private ProgEntregaCriteria progEntregaCriteria;
	//private UsuarioCriteria usuarioCriteria;
	// private Long idUsuario;
	
	
	
	
	
	public ProgEntregaCriteria getProgEntregaCriteria() {
		return progEntregaCriteria;
	}

	public void setProgEntregaCriteria(ProgEntregaCriteria progEntregaCriteria) {
		this.progEntregaCriteria = progEntregaCriteria;
	}

//	public UsuarioCriteria getUsuarioCriteria() {
//		return usuarioCriteria;
//	}
//
//	public void setUsuarioCriteria(UsuarioCriteria usuarioCriteria) {
//		this.usuarioCriteria = usuarioCriteria;
//	}
//
//	public Long getIdUsuario() {
//		return idUsuario;
//	}
//
//	public void setIdUsuario(Long idUsuario) {
//		this.idUsuario = idUsuario;
//	}

	public ProgEntregaSpecification() {
		
	}
	
	
	

	@Override
	public Predicate toPredicate(Root<ProgramacaoEntrega> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (progEntregaCriteria.getIdUsuario() == null) {
			return null;
		}
		else {
		 Join<ProgramacaoEntrega, Usuario> join =  root.join("usuarioEntrega", JoinType.INNER);
		 predicates.add(cb.equal(join.get("id"), progEntregaCriteria.getIdUsuario()));
			
		}
		Predicate[] pre = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(pre));
	}

	public ProgEntregaSpecification(ProgEntregaCriteria progEntregaCriteria) {
		this.progEntregaCriteria = progEntregaCriteria;
	}
	
	

}
