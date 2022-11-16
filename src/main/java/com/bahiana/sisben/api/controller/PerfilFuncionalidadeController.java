package com.bahiana.sisben.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.model.entity.PerfilFuncionalidade;
import com.bahiana.sisben.service.PerfilFuncionalidadeService;

@RestController
@RequestMapping(value = "/perfis-funcionalidades")
public class PerfilFuncionalidadeController {
	
	@Autowired
	private PerfilFuncionalidadeService perfilFuncionalidadeService;
	
	
	@GetMapping(value =  "/listarPaginado" )
    //@ResponseBody
    public Page<PerfilFuncionalidade> listarPaginado(Pageable pageable) {
    	return this.perfilFuncionalidadeService.listarPaginado(pageable);  	  
    }

}
