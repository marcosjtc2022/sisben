package com.bahiana.sisben.service;

import java.util.List;

import com.bahiana.sisben.api.dto.VwSisbenSetorDto;
import com.bahiana.sisben.model.entity.VwSisbenSetor;

public interface VwSisbenSetorService {
	
	List<VwSisbenSetor> listaSetorOrdenadoPorCodigo();
	
	List<VwSisbenSetor> listaSetorOrdenadoPorDescricao();

	VwSisbenSetor ObterPorCodigo(String codSetor);
	
	List<VwSisbenSetor> pesquisarComLikePorCodSetorOrdenadoPorCodSetor(VwSisbenSetorDto vwSisbenSetorDto);
	
	List<VwSisbenSetor> pesquisarComLikePorDescrSetorOrdenadoPorDescrSetor(VwSisbenSetorDto vwSisbenSetorDto);
	
	String ObterDescrSetor(String codSetor);
	

}
