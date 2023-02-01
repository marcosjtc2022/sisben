package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import com.bahiana.sisben.api.dto.VwSisbenElegibilidadeDto;
import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;

public interface VwSisbenElegibilidadeService {
	
	
    List<VwSisbenElegibilidade> pesquisarPorMatriculaOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
	List<VwSisbenElegibilidade> pesquisarPorNomeOrdenadoNome(VwSisbenElegibilidadeDto vwSisbenElegibilidadeDto);
	
	List<VwSisbenElegibilidade> listarElegivelOrdenadoNome();
	
	Optional<VwSisbenElegibilidade> ObterPorMatricula(String matriculaFuncionario);

}
