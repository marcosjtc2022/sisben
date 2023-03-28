package com.bahiana.sisben.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;

@RestController
@RequestMapping(value = "/setores-gerenciados")
public class UsuarioSetorGerenciadoController {
	
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
	  try {
		  UsuarioSetorGerenciado usuarioSetorGerenciado = new UsuarioSetorGerenciado() ;
			//programacaoEntrega = programacaoEntregaService.salvarLote(programacaoEntregaDto,'I');
			
			return new ResponseEntity(usuarioSetorGerenciado, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	
	

}
