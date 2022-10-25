package com.bahiana.sisben.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDTO;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.specification.ProgramacaoEntregaSpecification;

@RestController
@RequestMapping(value = "/programacoes-entregas")
public class ProgramacaoEntregaController {
	
	@Autowired
	private ProgramacaoEntregaService programacaoEntregaService;
	
    @GetMapping(value =  "/teste" )
    @ResponseBody
    public ResponseEntity listar() {
    	return new ResponseEntity("OK", HttpStatus.CREATED);
  	  
    }
    
    @GetMapping(value =  "/paginacao-simples" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarPaginadoSimples() {
    	return this.programacaoEntregaService.listarPaginadoSimples();  	  
    }
    
    @GetMapping(value =  "/paginacao-com-parametros" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarPaginadoComParametros(Pageable pageable) {
    	return this.programacaoEntregaService.listarPaginado(pageable);   	  
    }
    
    @GetMapping(value =  "/paginacao-com-parametros-default" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarPaginadoComParametrosEOrenacao(Pageable pageable) {
    	return this.programacaoEntregaService.listarPaginadoEOrdenado(pageable);   	  
    }
    
    @GetMapping(value =  "/paginacao-query-dinamica" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarPaginadoQueryDinamica(ProgramacaoEntregaSpecification programacaoEntregaSpecification , Pageable pageable  ) {
    	return this.programacaoEntregaService.listarPaginadoQueryDinamica(programacaoEntregaSpecification, pageable);   	  
    }
    
    @GetMapping(value =  "/programacao-por-periodo" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarProgramacaoPorPeriodo(Pageable pageable,ProgramacaoEntregaDTO  programacaoEntregaDTO  ) {
    	return this.programacaoEntregaService.listarProgramacaoPorPeriodo(pageable,programacaoEntregaDTO);   	  
    }
    
    @GetMapping(value =  "/listarPaginado" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarPaginado(Pageable pageable) {
    	return this.programacaoEntregaService.listarPaginado(pageable);  	  
    }   
   
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ProgramacaoEntregaDTO programacaoEntregaDto) {
	  try {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntrega = programacaoEntregaService.salvar(programacaoEntregaDto);
			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	@PostMapping("/salva-lotes")
	public ResponseEntity salvarLote(@RequestBody ProgramacaoEntregaDTO programacaoEntregaDto) {
	  try {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntrega = programacaoEntregaService.salvarLote(programacaoEntregaDto);
			
			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaDTO programacaoEntregaDto) {
	  try {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntregaDto.setId(id);			
			programacaoEntrega = programacaoEntregaService.alterar(programacaoEntregaDto);
			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
//	//Usado para recuperar recurso no servidor.
//		//Quando o "id" é passado na url o valor é colocado na variável "id".
//		@PutMapping("atualiza-lotes")
//		public ResponseEntity atualizarLote(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaDTO programacaoEntregaDto) {
//		  try {
//				ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
//				programacaoEntregaDto.setId(id);			
//				programacaoEntrega = programacaoEntregaService.alterar(programacaoEntregaDto);
//				return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
//		     } catch (RegraNegocioException e) {
//			    return ResponseEntity.badRequest().body(e.getMessage());
//		     }
//	    }
	
	
  

}