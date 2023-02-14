package com.bahiana.sisben.api.controller;

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

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaMenos24hDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.service.FornecedorService;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.service.UsuarioService;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorController {
	
		@Autowired
		private FornecedorService fornecedorService;
		
		@Autowired
		private UsuarioService usuarioService;
		
		@Autowired
		private ProgramacaoEntregaService programacaoEntregaService;
		
		@GetMapping(value =  "/lista-filtro-descricao" )
		public ResponseEntity<List<Fornecedor>> listarPorDescricaoOrdenadoDescricao(FornecedorDto  fornecedorDto) {
			 try {
					
					return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<List<Fornecedor>>(HttpStatus.BAD_REQUEST);
			     }
		}
		 
		@GetMapping(value =  "/paginacao-simples" )
		public Page<Fornecedor> listarPaginadoSimples() {
		    	return this.fornecedorService.listarPaginadoSimples();  	  
		}
		
		@GetMapping(value =  "/listarPaginado" )
	    public Page<Fornecedor> listarPaginado(Pageable pageable) {
	    	return this.fornecedorService.listarPaginado(pageable);  	  
	    }
		
		@GetMapping(value =  "/listarOrdenadoDescricao" )
	    public List<Fornecedor> listarOrdenadoDescricao() {
	    	return this.fornecedorService.listarSimplesOrdenadoDescricao();  	  
	    }
		
		@Transactional
		@PostMapping
		public ResponseEntity salvar(@RequestBody FornecedorDto fornecedorDTO) {
		  try {
			    Fornecedor fornecedor = new Fornecedor() ;
			    fornecedor = fornecedorService.salvar(fornecedorDTO);
				return new ResponseEntity(fornecedor, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
	    }
		
		//Usado para recuperar recurso no servidor.
		//Quando o "id" é passado na url o valor é colocado na variável "id".
		@Transactional
		@PutMapping("{id}")
		public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FornecedorDto fornecedorDTO) {
		     try {
		    	        Fornecedor fornecedor = new Fornecedor() ;
		    	        fornecedorDTO.setId(id);			
		    	        fornecedor = fornecedorService.alterar(fornecedorDTO);
						return new ResponseEntity(fornecedor, HttpStatus.CREATED);
				 } catch (RegraNegocioException e) {
					    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		}
		
		@Transactional
		@DeleteMapping("{id}")
		public ResponseEntity deletar(@PathVariable("id") Long id) {
			
			
			Long countFornecedor = usuarioService.pesquisaFornecedor(id);
			
			if ((countFornecedor > 0) && (countFornecedor != null)) {
				return new ResponseEntity("Fornecedor está vinculado a um usuário!", HttpStatus.BAD_REQUEST);
			}
			
			//entity é o que retorna de ObterPorId
					return fornecedorService.obterPorId(id).map(entity -> {					
						fornecedorService.deletar(entity);
						return new ResponseEntity(HttpStatus.NO_CONTENT);
					}).orElseGet(() -> 
					    new ResponseEntity("Fornecedor não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
		}
		
		@GetMapping("/obterPorId/{id}")
		public Fornecedor obterPorId(@PathVariable("id") Long id) {
			
			Optional<Fornecedor> fornecedor = fornecedorService.obterPorId(id);
			return  fornecedor.get();	
					
		}
		
		@Transactional
		@PutMapping("/registrarEntrega/{id}")
		public ResponseEntity registrarEntrega(@PathVariable("id") Long id,@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			  try {
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
					programacaoEntregaDto.setId(id);	
					programacaoEntrega = programacaoEntregaService.registrarEntrega(programacaoEntregaDto);
					return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		}
		

}
