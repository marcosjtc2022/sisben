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

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.api.dto.SuspensaoElegibilidadeDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;
import com.bahiana.sisben.service.SuspensaoElegibilidadeService;

@RestController
@RequestMapping(value = "/suspensoes-elegiblidades")
public class SuspensaoElegibilidadeController {
	
	@Autowired
	private SuspensaoElegibilidadeService suspensaoElegibilidadeService;
	
	@GetMapping(value =  "/listarSimplesPorNome" )
	public ResponseEntity<List<SuspensaoElegibilidade>> listarSimplesPorNome() {
		 try {
				
			 //return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(suspensaoElegibilidadeService.listarSimplesOrdenadoNomeColaborador(), HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<SuspensaoElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/listarPorFiltroNome" )
	public ResponseEntity<List<SuspensaoElegibilidade>> listarPorFiltroNome() {
		 try {
				
			 //return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(suspensaoElegibilidadeService.listarSimplesOrdenadoNomeColaborador(), HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<SuspensaoElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/listarPorFiltroMatricula" )
	public ResponseEntity<List<SuspensaoElegibilidade>> listarPorFiltroMatricula(SuspensaoElegibilidadeDto suspensaoElegibilidadeDto ) {
		 try {
				
			 //return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(suspensaoElegibilidadeService.listarPorMatriculaOrdenadoNome(suspensaoElegibilidadeDto), HttpStatus.CREATED);
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
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
	    try {
	    	  SuspensaoElegibilidade suspensaoElegibilidade = new SuspensaoElegibilidade() ;
			  //suspensaoElegibilidadeDto.setId(id);			
			  suspensaoElegibilidade = suspensaoElegibilidadeService.alterar(suspensaoElegibilidadeDto);
			  return new ResponseEntity(suspensaoElegibilidade, HttpStatus.CREATED);
		  } catch (RegraNegocioException e) {
			  return ResponseEntity.badRequest().body(e.getMessage());
		 }
	}
	
//	@DeleteMapping("{id}")
//	public ResponseEntity deletar(@PathVariable("id") Long id) {
//		
//		
//		Long countFornecedor = usuarioService.pesquisaFornecedor(id);
//		
//		if ((countFornecedor > 0) && (countFornecedor != null)) {
//			return new ResponseEntity("Fornecedor está vinculado a um usuário!", HttpStatus.BAD_REQUEST);
//		}
//		
//		//entity é o que retorna de ObterPorId
//				return fornecedorService.obterPorId(id).map(entity -> {					
//					fornecedorService.deletar(entity);
//					return new ResponseEntity(HttpStatus.NO_CONTENT);
//				}).orElseGet(() -> 
//				    new ResponseEntity("Fornecedor não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
//	}
	

}
