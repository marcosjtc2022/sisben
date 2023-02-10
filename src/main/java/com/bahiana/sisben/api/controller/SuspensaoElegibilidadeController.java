package com.bahiana.sisben.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

import com.bahiana.sisben.api.dto.SuspensaoElegibilidadeDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;
import com.bahiana.sisben.service.SuspensaoElegibilidadeService;

@RestController
@RequestMapping(value = "/suspensoes-elegiblidades")
public class SuspensaoElegibilidadeController {
	
	@Autowired
	private SuspensaoElegibilidadeService suspensaoElegibilidadeService;
	
	@GetMapping(value =  "/listarOrdenadoPorNome" )
	public ResponseEntity<List<SuspensaoElegibilidade>> listarOrdenadoPorNome() {
		 try {
				
			 //return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(suspensaoElegibilidadeService.listarSimplesOrdenadoNomeColaborador(), HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<SuspensaoElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/listarPorFiltroNome" )
	public ResponseEntity<List<SuspensaoElegibilidade>> listarPorFiltroNome(SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
		 try {
				
			 //return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(suspensaoElegibilidadeService.listarPorNomeOrdenadoNome(suspensaoElegibilidadeDto), HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<SuspensaoElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	
	
	@GetMapping(value =  "/paginarSimples" )
	public Page<SuspensaoElegibilidade> listarPaginadoSimples() {
	    	return this.suspensaoElegibilidadeService.listarPaginadoSimples();  	  
	}
	
	@GetMapping(value =  "/listarPaginado" )
    public Page<SuspensaoElegibilidade> listarPaginado(Pageable pageable) {
    	return this.suspensaoElegibilidadeService.listarPaginado(pageable);  	  
    }
	
	@Transactional
	@PostMapping
	public ResponseEntity salvar(@RequestBody SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
	  try {
		    SuspensaoElegibilidade suspensaoElegibilidade = new SuspensaoElegibilidade() ;
		    suspensaoElegibilidade = suspensaoElegibilidadeService.salvar(suspensaoElegibilidadeDto);
			return new ResponseEntity(suspensaoElegibilidade, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@Transactional
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
	    try {
	    	  SuspensaoElegibilidade suspensaoElegibilidade = new SuspensaoElegibilidade() ;
			  suspensaoElegibilidadeDto.setId(id);			
			  suspensaoElegibilidade = suspensaoElegibilidadeService.alterar(suspensaoElegibilidadeDto);
			  return new ResponseEntity(suspensaoElegibilidade, HttpStatus.CREATED);
		  } catch (RegraNegocioException e) {
			  return ResponseEntity.badRequest().body(e.getMessage());
		 }
	}
	
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		
		
//		Long countFornecedor = suspensaoElegibilidadeService.pesquisaFornecedor(id);
//		
//		if ((countFornecedor > 0) && (countFornecedor != null)) {
//			return new ResponseEntity("Fornecedor está vinculado a um usuário!", HttpStatus.BAD_REQUEST);
//		}
		
		//entity é o que retorna de ObterPorId
				return suspensaoElegibilidadeService.obterPorId(id).map(entity -> {					
					suspensaoElegibilidadeService.deletar(entity);
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
				    new ResponseEntity("Suspensão elegibilidade não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping("/obterPorId/{id}")
	public SuspensaoElegibilidade obterPorId(@PathVariable("id") Long id) {
			
			Optional<SuspensaoElegibilidade> suspensaoElegibilidade = suspensaoElegibilidadeService.obterPorId(id);
			return  suspensaoElegibilidade.get();	
					
	}
	
	@GetMapping("/obterPorMatricula/{matriculaColaborador}")
	public List<SuspensaoElegibilidade> obterPorMatricula(@PathVariable("matriculaColaborador") String matriculaColaborador) {
		
		List<SuspensaoElegibilidade> listaSuspensaoElig = suspensaoElegibilidadeService.obterListaPorMatricula(matriculaColaborador);
			
		return  listaSuspensaoElig;	
					
	}
	

}
