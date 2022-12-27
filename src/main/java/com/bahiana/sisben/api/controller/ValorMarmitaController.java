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

import com.bahiana.sisben.api.dto.JustificativaDto;
import com.bahiana.sisben.api.dto.ValorMarmitaDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.ValorMarmita;
import com.bahiana.sisben.service.ValorMarmitaService;

@RestController
@RequestMapping(value = "/valores-marmitas")
public class ValorMarmitaController {
	
	@Autowired
	private ValorMarmitaService valorMarmitaService;
	
	

	@GetMapping(value =  "/paginacao-simples" )
    public Page<ValorMarmita> listarPaginadoSimples() {
    	return this.valorMarmitaService.listarPaginadoSimples();  	  
    }
	

	@GetMapping(value =  "/listarPaginado" )
    public Page<ValorMarmita> listarPaginado(Pageable pageable) {
    	return this.valorMarmitaService.listarPaginado(pageable);  	  
    }
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ValorMarmitaDto valorMarmitaDto) {
	  try {
		    ValorMarmita valorMarmita = new ValorMarmita() ;
		    valorMarmita = valorMarmitaService.salvar(valorMarmitaDto);
			return new ResponseEntity(valorMarmita, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ValorMarmitaDto valorMarmitaDto) {
	try {
		    ValorMarmita valorMarmita = new ValorMarmita() ;
		    valorMarmitaDto.setId(id);			
		    valorMarmita = valorMarmitaService.alterar(valorMarmitaDto);
			return new ResponseEntity(valorMarmita, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
				
			//entity é o que retorna de ObterPorId
			return valorMarmitaService.obterPorId(id).map(entity -> {					
				valorMarmitaService.deletar(entity);
		   return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
		   new ResponseEntity("Valor marmita não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}	
	
	@GetMapping(value =  "/listarOrdenadoValor" )
    public List<ValorMarmita> listarOrdenadoDescricao() {
    	return this.valorMarmitaService.listarSimplesOrdenadoValor();  	  
    }
	
	
	

}
