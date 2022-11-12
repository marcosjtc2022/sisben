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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.PerfilDTO;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.service.PerfilService;

@RestController
@RequestMapping(value = "/perfis")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping(value =  "/paginacao-simples" )
    @ResponseBody
    public Page<Perfil> listarPaginadoSimples() {
    	return this.perfilService.listarPaginadoSimples();  	  
    }
	
	@GetMapping(value =  "/listarPaginado" )
    @ResponseBody
    public Page<Perfil> listarPaginado(Pageable pageable) {
    	return this.perfilService.listarPaginado(pageable);  	  
    }
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody PerfilDTO perfilDTO) {
	  try {
			Perfil perfil = new Perfil() ;
			perfil = perfilService.salvar(perfilDTO);
			return new ResponseEntity(perfil, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
}
