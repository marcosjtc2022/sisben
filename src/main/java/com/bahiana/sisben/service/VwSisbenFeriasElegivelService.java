package com.bahiana.sisben.service;

import java.time.LocalDate;

public interface VwSisbenFeriasElegivelService {
	
	
	Long pesquisarFeriasElegivel(LocalDate dataSolicitacao, String matriculaColaborador );

}
