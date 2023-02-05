package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.UnidadeAcademicaDto;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.UnidadeAcademica;

public interface UnidadeAcademicaService {
	
	
	UnidadeAcademica salvar(UnidadeAcademicaDto unidadeAcademicaForm);
	
	UnidadeAcademica alterar(UnidadeAcademicaDto unidadeAcademicaForm);
	
	void deletar(UnidadeAcademica unidadeAcademica);
	
	Optional<UnidadeAcademica> obterPorId(Long id);
	
	List<UnidadeAcademica> listarSimples();
	
	Page<UnidadeAcademica> listarPaginadoSimples(Pageable pageable);
	
	List<UnidadeAcademica> listarSimplesOrdenadoDescricao();
	
	UnidadeAcademica pesquisarPrimeiroPorDescricao(String descricao);
	

}
