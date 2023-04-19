package com.bahiana.sisben.api.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.VwSisbenSetor;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;

@RestController
@RequestMapping(value = "/setores-gerenciados")
public class UsuarioSetorGerenciadoController {
	
	@Autowired
	private UsuarioSetorGerenciadoService usuarioSetorGerenciadoService;
	
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
	  try {
		 List<UsuarioSetorGerenciado> listUsuarioSetorGerenciado = 
				  usuarioSetorGerenciadoService.salvar(usuarioSetorGerenciadoDto);
			
			return new ResponseEntity(listUsuarioSetorGerenciado, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	@PostMapping("/alterar")
	public ResponseEntity alterar(@RequestBody UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
	  try {
		 List<UsuarioSetorGerenciado> listUsuarioSetorGerenciado = 
				  usuarioSetorGerenciadoService.alterar(usuarioSetorGerenciadoDto);
			
			return new ResponseEntity(listUsuarioSetorGerenciado, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	 @DeleteMapping("/apagar")
	 @Transactional
	 public ResponseEntity apagarProgramacaoMes(@RequestBody UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
		  try {
			  usuarioSetorGerenciadoService.apagar(usuarioSetorGerenciadoDto);
				 return new ResponseEntity(HttpStatus.NO_CONTENT);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
		  
	 }
	
	@GetMapping(value =  "/listarOrdenadoPorDescricao" )
    public List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
    	return usuarioSetorGerenciadoService.listaSetorOrdenadoPorDescricao(usuarioSetorGerenciadoDto.getIdUsuarioLider());  	  
    }
	
	@GetMapping(value =  "/listarOrdenadoPorCodigo" )
    public List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
    	return usuarioSetorGerenciadoService.listaSetorOrdenadoPorCodigo(usuarioSetorGerenciadoDto.getIdUsuarioLider()); 	  
    }
	
	@GetMapping("/obterPorId/{id}")
	public UsuarioSetorGerenciado obterPorId(@PathVariable("id") Long id) {
		
		Optional<UsuarioSetorGerenciado> usuario = usuarioSetorGerenciadoService.obterPorId(id);
		return  usuario.get();	
				
	}
	
	
	

}
