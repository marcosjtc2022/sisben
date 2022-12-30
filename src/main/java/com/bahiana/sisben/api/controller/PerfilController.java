package com.bahiana.sisben.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.PerfilDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.service.PerfilService;
import com.bahiana.sisben.service.UsuarioService;

@RestController
@RequestMapping(value = "/perfis")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping(value =  "/paginacao-simples" )
    //@ResponseBody
    public Page<Perfil> listarPaginadoSimples() {
    	return this.perfilService.listarPaginadoSimples();  	  
    }
	
	@GetMapping(value =  "/listarPaginado" )
    //@ResponseBody
    public Page<Perfil> listarPaginado(Pageable pageable) {
    	return this.perfilService.listarPaginado(pageable);  	  
    }
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody PerfilDto perfilDTO) {
	  try {
			Perfil perfil = new Perfil() ;
			perfil = perfilService.salvar(perfilDTO);
			return new ResponseEntity(perfil, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
		//Quando o "id" é passado na url o valor é colocado na variável "id".
		@PutMapping("{id}")
		public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody PerfilDto perfilDTO) {
		  try {
				Perfil perfil = new Perfil() ;
				perfilDTO.setId(id);			
				perfil = perfilService.alterar(perfilDTO);
				return new ResponseEntity(perfil, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
	    }
		
		
		@DeleteMapping("{id}")
		public ResponseEntity deletar(@PathVariable("id") Long id) {
			
			
			Long countPerfil = usuarioService.pesquisaPerfil(id);
			
			if ((countPerfil > 0) && (countPerfil != null)) {
				return new ResponseEntity("Perfil está vinculada a um usuário!", HttpStatus.BAD_REQUEST);
			}
			
			//entity é o que retorna de ObterPorId
					return perfilService.obterPorId(id).map(entity -> {					
						perfilService.deletar(entity);
						return new ResponseEntity(HttpStatus.NO_CONTENT);
					}).orElseGet(() -> 
					    new ResponseEntity("Perfil não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
		}
		
		@GetMapping(value =  "/listarOrdenadoDescricao" )
	    public List<Perfil> listarOrdenadoDescricao() {
	    	return this.perfilService.listarSimplesOrdenadoDescricao();  	  
	    }
		
		
		
	
}
