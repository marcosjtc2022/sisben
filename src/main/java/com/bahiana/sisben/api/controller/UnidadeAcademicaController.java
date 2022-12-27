package com.bahiana.sisben.api.controller;

import java.util.List;

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

import com.bahiana.sisben.api.dto.UnidadeAcademicaDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Perfil;
import com.bahiana.sisben.model.entity.UnidadeAcademica;
import com.bahiana.sisben.service.UnidadeAcademicaService;

@RestController
@RequestMapping(value = "/unidades-academicas")
public class UnidadeAcademicaController {
	
	
	@Autowired
	private UnidadeAcademicaService unidadeAcademicaService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UnidadeAcademicaDto unidadeAcademicaForm) {
	  try {
		   UnidadeAcademica unidadeAcademica = new UnidadeAcademica() ;
		   unidadeAcademica = unidadeAcademicaService.salvar(unidadeAcademicaForm);
			return new ResponseEntity(unidadeAcademica, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	 //Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UnidadeAcademicaDto unidadeAcademicaForm) {
		  try {
			    UnidadeAcademica unidadeAcademica = new UnidadeAcademica() ;
				unidadeAcademicaForm.setId(id);			
				unidadeAcademica = unidadeAcademicaService.alterar(unidadeAcademicaForm);
				return new ResponseEntity(unidadeAcademica, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		
		//entity é o que retorna de ObterPorId
				return unidadeAcademicaService.obterPorId(id).map(entity -> {					
					unidadeAcademicaService.deletar(entity);
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
				    new ResponseEntity("Unidade acadêmica não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping(value =  "/listar" )
    public List<UnidadeAcademica> listar() {
    	return this.unidadeAcademicaService.listarSimples();  	  
    }
	
	@GetMapping(value =  "/listarPaginado" )
    public Page<UnidadeAcademica> listarPaginado(Pageable pageable) {
    	return this.unidadeAcademicaService.listarPaginadoSimples(pageable);  	  
    }
	
	@GetMapping(value =  "/listarOrdenadoDescricao" )
    public List<UnidadeAcademica> listarOrdenadoDescricao() {
    	return this.unidadeAcademicaService.listarSimplesOrdenadoDescricao();  	  
    }
	
	

}
