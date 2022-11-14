package com.bahiana.sisben.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.FuncionalidadeDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Funcionalidade;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.service.FuncionalidadeService;




@RestController
@RequestMapping(value = "/funcionalidades")
public class FuncionalidadeController {
	
	@Autowired
	private FuncionalidadeService funcionalidadeService;
	
	@GetMapping(value =  "/listarPaginado" )
    //@ResponseBody
    public Page<Funcionalidade> listarPaginado(Pageable pageable) {
    	return this.funcionalidadeService.listarPaginado(pageable);  	  
    }
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody FuncionalidadeDto funcionalidadeDto) {
	  try {
			Funcionalidade funcionalidade = new Funcionalidade() ;
			funcionalidade = funcionalidadeService.salvar(funcionalidadeDto);
			return new ResponseEntity(funcionalidade, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }

}
