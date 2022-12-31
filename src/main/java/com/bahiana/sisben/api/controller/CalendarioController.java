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

import com.bahiana.sisben.api.dto.CalendarioDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Calendario;
import com.bahiana.sisben.service.CalendarioService;

@RestController
@RequestMapping(value = "/calendarios")
public class CalendarioController {
	
	
	@Autowired
	CalendarioService calendarioService;
	
	@GetMapping(value =  "/listarpaginadoSimples" )
    public Page<Calendario> listarPaginadoSimples() {
    	return this.calendarioService.listarPaginadoSimples();  	  
    }
	
	@GetMapping(value =  "/listarPaginado" )
    public Page<Calendario> listarPaginado(Pageable pageable) {
    	return this.calendarioService.listarPaginado(pageable);  	  
    }
	
	@GetMapping(value =  "/listarOrdenadoData" )
    public List<Calendario> listarOrdenadoData() {
    	return this.calendarioService.listarSimplesOrdenadoData();
    }
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody CalendarioDto calendarioDto) {
	  try {
		    Calendario calendario = new Calendario() ;
		    calendario = calendarioService.salvar(calendarioDto);
			return new ResponseEntity(calendario, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	 //Usado para recuperar recurso no servidor.
	 //Quando o "id" é passado na url o valor é colocado na variável "id".
	 @PutMapping("{id}")
	 public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CalendarioDto calendarioDto) {
		try {
			Calendario calendario = new Calendario() ;
			calendarioDto.setId(id);			
			calendario = calendarioService.alterar(calendarioDto);
			 return new ResponseEntity(calendario, HttpStatus.CREATED);
		 } catch (RegraNegocioException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	 
	 @DeleteMapping("{id}")
		public ResponseEntity deletar(@PathVariable("id") Long id) {
			
			 
			
//			Long countJustificativa = programacaoEntregaService.pesquisaJustificativa(id);
//			
//			if ((countJustificativa > 0) && (countJustificativa != null)) {
//				return new ResponseEntity("Justificativa está vinculada a uma programação entrega!", HttpStatus.BAD_REQUEST);
//			}
//			   
					
				//entity é o que retorna de ObterPorId
				return calendarioService.obterPorId(id).map(entity -> {					
					calendarioService.deletar(entity);
			   return new ResponseEntity(HttpStatus.NO_CONTENT);
					}).orElseGet(() -> 
			   new ResponseEntity("Data não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
		}	
		 
	 

}
