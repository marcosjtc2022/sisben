package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.UnidadeAcademicaDto;
import com.bahiana.sisben.model.entity.UnidadeAcademica;
import com.bahiana.sisben.model.entity.repository.UnidadeAcademicaRepository;
import com.bahiana.sisben.service.UnidadeAcademicaService;

public class UnidadeAcademicaServiceImpl implements UnidadeAcademicaService  {

	
	@Autowired
	UnidadeAcademicaRepository unidadeAcademicaRepository;
	
	@Override
	public UnidadeAcademica salvar(UnidadeAcademicaDto unidadeAcademicaForm) {
		 UnidadeAcademica unidadeAcademica = UnidadeAcademicaServiceImpl.from(unidadeAcademicaForm);
		 return unidadeAcademicaRepository.save(unidadeAcademica);
	}

	@Override
	@Transactional
	public UnidadeAcademica alterar(UnidadeAcademicaDto unidadeAcademicaForm) {
			
		UnidadeAcademica unidadeAcademica = UnidadeAcademicaServiceImpl.from(unidadeAcademicaForm);
 	    return unidadeAcademicaRepository.save(unidadeAcademica);
		
	}
	
	@Override
	public void deletar(UnidadeAcademica unidadeAcademica) {
		unidadeAcademicaRepository.delete(unidadeAcademica);	
	}
	
	@Override
	public Optional<UnidadeAcademica> obterPorId(Long id) {
		return unidadeAcademicaRepository.findById(id);
	}
	
	public static UnidadeAcademica from(UnidadeAcademicaDto unidadeAcademicaForm) {
		UnidadeAcademica unidadeAcademica = new UnidadeAcademica();
		LocalDateTime dataModificacao = LocalDateTime.now();
		unidadeAcademicaForm.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(unidadeAcademicaForm, unidadeAcademica);
		
		return unidadeAcademica;
	}

	@Override
	public List<UnidadeAcademica> listarSimples() {
		return this.unidadeAcademicaRepository.findAll();
	}

	@Override
	public Page<UnidadeAcademica> listarPaginadoSimples(Pageable pageable) {
		return this.unidadeAcademicaRepository.findAll(pageable);
	}

	@Override
	public List<UnidadeAcademica> listarSimplesOrdenadoDescricao() {
		return this.unidadeAcademicaRepository.findByOrderByDescricaoAsc();
	}

	

}
