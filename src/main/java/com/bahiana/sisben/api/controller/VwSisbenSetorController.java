package com.bahiana.sisben.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.VwSisbenSetorDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.VwSisbenSetor;
import com.bahiana.sisben.service.VwSisbenSetorService;

@RestController
@RequestMapping(value = "/setores")
public class VwSisbenSetorController {
	
	@Autowired
	VwSisbenSetorService vwSisbenSetorService;
	
	@GetMapping("/obterPorCodSetor/{codSetor}")
	public VwSisbenSetor obterPorCodSetor(@PathVariable("codSetor") String codSetor) {
		return vwSisbenSetorService.ObterPorCodigo(codSetor);
	}
	
	@GetMapping(value =  "/listarOrdenadoPorDescricao" )
    public List<VwSisbenSetor> listaSetorOrdenadoPorDescricao() {
    	return vwSisbenSetorService.listaSetorOrdenadoPorDescricao();  	  
    }
	
	@GetMapping(value =  "/listarOrdenadoPorCodigo" )
    public List<VwSisbenSetor> listaSetorOrdenadoPorCodigo() {
    	return vwSisbenSetorService.listaSetorOrdenadoPorCodigo(); 	  
    }
	
	@GetMapping(value =  "/listarPorFiltroDescricao" )
	public ResponseEntity<List<VwSisbenSetor>> listarPorDescricaoOrdenadoPorDescricao(VwSisbenSetorDto  vwSisbenSetorDto) {
		 try {
				
				return new ResponseEntity(vwSisbenSetorService.pesquisarComLikePorDescrSetorOrdenadoPorDescrSetor(vwSisbenSetorDto)
						, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenSetor>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/listarPorFiltroCodigo" )
	public ResponseEntity<List<VwSisbenSetor>> listarPorDescricaoOrdenadoPorCodigo(VwSisbenSetorDto  vwSisbenSetorDto) {
		 try {
				
				return new ResponseEntity(vwSisbenSetorService.pesquisarComLikePorCodSetorOrdenadoPorCodSetor(vwSisbenSetorDto)
						, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenSetor>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	
	

}
