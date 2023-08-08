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

import com.bahiana.sisben.api.dto.JustificativaDto;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.TipoJustificativa;
import com.bahiana.sisben.service.JustificativaService;
import com.bahiana.sisben.service.ProgramacaoEntregaService;


@RestController
@RequestMapping(value = "/justificativas")
public class JustificativaController {
	
	@Autowired
	private JustificativaService justificativaService;
	
	@Autowired
	private ProgramacaoEntregaService programacaoEntregaService;
	
//	@Autowired
//	private TipoJustificativaService tipoJustificativaService;
	
	
	@GetMapping(value =  "/paginacao-simples" )
    public Page<Justificativa> listarPaginadoSimples() {
    	return this.justificativaService.listarPaginadoSimples();  	  
    }
	
	@GetMapping(value =  "/listarPaginado" )
    public Page<Justificativa> listarPaginado(Pageable pageable) {
    	return this.justificativaService.listarPaginado(pageable);  	  
    }
	
	@Transactional
	@PostMapping
	public ResponseEntity salvar(@RequestBody JustificativaDto justificativaDTO) {
	  try {
			Justificativa justificativa = new Justificativa() ;
			justificativa = justificativaService.salvar(justificativaDTO);
			return new ResponseEntity(justificativa, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	 //Usado para recuperar recurso no servidor.
	 //Quando o "id" é passado na url o valor é colocado na variável "id".
	 @Transactional
	 @PutMapping("{id}")
	 public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody JustificativaDto justificativaDTO) {
		try {
			
			 Long countJustificativa = programacaoEntregaService.pesquisaJustificativa(id);
			
			 if ((countJustificativa > 0) && (countJustificativa != null)) {
				//return new ResponseEntity("Justificativa está vinculada a uma programação entrega!", HttpStatus.BAD_REQUEST);
				throw new GlobalExceptionHandler("Justificativa está vinculada a uma programação entrega!");
				
			 } 
			
			 Justificativa justificativa = new Justificativa() ;
			 justificativaDTO.setId(id);			
			 justificativa = justificativaService.alterar(justificativaDTO);
			 return new ResponseEntity(justificativa, HttpStatus.CREATED);
		 } catch (RegraNegocioException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	 
	@Transactional		
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		
		 
		
		Long countJustificativa = programacaoEntregaService.pesquisaJustificativa(id);
		
		if ((countJustificativa > 0) && (countJustificativa != null)) {
			//return new ResponseEntity("Justificativa está vinculada a uma programação entrega!", HttpStatus.BAD_REQUEST);
			throw new GlobalExceptionHandler("Justificativa está vinculada a uma programação entrega!");
			
		}
		
//			Optional<Justificativa> justificativa = justificativaService.obterPorId(id);		
//			
//			if (justificativa.isPresent()) {
//				Optional<TipoJustificativa> tipoJustificativa =	tipoJustificativaService.obterPorId(justificativa.get().getIdTipoJustificativa());
//				if (tipoJustificativa.isPresent()) {
//					return new ResponseEntity("Justificativa já está vinculada a um tipo de justificativa.", HttpStatus.BAD_REQUEST);
//				}
//			}
		   
				
			//entity é o que retorna de ObterPorId
			return justificativaService.obterPorId(id).map(entity -> {					
				  justificativaService.deletar(entity);
		   return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
		   new ResponseEntity("Justificativa não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}	
	
	@GetMapping(value =  "/listarOrdenadoDescricao" )
    public List<Justificativa> listarOrdenadoDescricao() {
    	return this.justificativaService.listarSimplesOrdenadoDescricao();  	  
    }
	
	@GetMapping("/obterPorId/{id}")
	public Justificativa obterPorId(@PathVariable("id") Long id) {
		
		Optional<Justificativa> justificativa = justificativaService.obterPorId(id);
		return  justificativa.get();	
				
	}
	
	
	
			
	
	

}
