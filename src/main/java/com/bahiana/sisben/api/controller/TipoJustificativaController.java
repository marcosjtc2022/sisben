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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.TipoJustificativaDto;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Justificativa;
import com.bahiana.sisben.model.entity.TipoJustificativa;
import com.bahiana.sisben.model.entity.UnidadeAcademica;
import com.bahiana.sisben.service.JustificativaService;
import com.bahiana.sisben.service.TipoJustificativaService;

@RestController
@RequestMapping(value = "/tipos-justificativas")
public class TipoJustificativaController {
	
	@Autowired
	private TipoJustificativaService tipoJustificativaService;
	
	@Autowired
	private JustificativaService justificativaService;
	
	@GetMapping(value =  "/paginacao-simples" )
    //@ResponseBody
    public Page<TipoJustificativa> listarPaginadoSimples() {
    	return this.tipoJustificativaService.listarPaginadoSimples();  	  
    }
	
	@GetMapping(value =  "/listarPaginado" )
    //@ResponseBody
    public Page<TipoJustificativa> listarPaginado(Pageable pageable) {
    	return this.tipoJustificativaService.listarPaginado(pageable);  	  
    }
	
	@Transactional
	@PostMapping
	public ResponseEntity salvar(@RequestBody TipoJustificativaDto tipoJustificativaDTO) {
	  try {
			TipoJustificativa tipoJustificativa = new TipoJustificativa() ;
			tipoJustificativa = tipoJustificativaService.salvar(tipoJustificativaDTO);
			return new ResponseEntity(tipoJustificativa, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	    //Usado para recuperar recurso no servidor.
		//Quando o "id" é passado na url o valor é colocado na variável "id".
	    @Transactional
		@PutMapping("{id}")
		public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody TipoJustificativaDto tipoJustificativaDTO) {
		  try {
			  
			    Long countTipoJustificativa = justificativaService.pesquisaTipoJustificativa(id);
				
				if ((countTipoJustificativa > 0) && (countTipoJustificativa != null)) {
				//	return new ResponseEntity("Tipo de justificativa está vinculada a uma justificativa !", HttpStatus.BAD_REQUEST);
					throw new GlobalExceptionHandler("Tipo de justificativa está vinculada a uma justificativa !");
				}
				TipoJustificativa tipoJustificativa = new TipoJustificativa() ;
				tipoJustificativaDTO.setId(id);			
				tipoJustificativa = tipoJustificativaService.alterar(tipoJustificativaDTO);
				return new ResponseEntity(tipoJustificativa, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
	    }
		
	    @Transactional
		@DeleteMapping("{id}")
		public ResponseEntity deletar(@PathVariable("id") Long id) {
			
			Long countTipoJustificativa = justificativaService.pesquisaTipoJustificativa(id);
			
			if ((countTipoJustificativa > 0) && (countTipoJustificativa != null)) {
				//return new ResponseEntity("Tipo de justificativa está vinculada a uma justificativa !", HttpStatus.BAD_REQUEST);
				throw new GlobalExceptionHandler("Tipo de justificativa está vinculada a uma justificativa !");
			}
			
			
			
			//entity é o que retorna de ObterPorId
					return tipoJustificativaService.obterPorId(id).map(entity -> {					
						tipoJustificativaService.deletar(entity);
						return new ResponseEntity(HttpStatus.NO_CONTENT);
					}).orElseGet(() -> 
					    new ResponseEntity("TipoJustificativa não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
		}
		
		@GetMapping(value =  "/listarOrdenadoDescricao" )
	    public List<TipoJustificativa> listarOrdenadoDescricao() {
	    	return this.tipoJustificativaService.listarSimplesOrdenadoDescricao();  	  
	    }
		
		@GetMapping("/obterPorId/{id}")
		public TipoJustificativa obterPorId(@PathVariable("id") Long id) {
			
			Optional<TipoJustificativa> tipoJustificativa = tipoJustificativaService.obterPorId(id);
			return  tipoJustificativa.get();	
					
		}
		
		@GetMapping(value =  "/listarTelaFuncao" )
	    public List<String> listarTelaFuncao() {
	    	return this.tipoJustificativaService.listarTelaFuncao();  	  
	    }
		
		@GetMapping("/listarPorTelaFuncao")
		public List<TipoJustificativa> listarPorTelaFuncao(@RequestParam(required = true  ) String telaFuncao) {
			return  tipoJustificativaService.listarPorTelaFuncao(telaFuncao);	
		}
		


}
