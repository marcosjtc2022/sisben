package com.bahiana.sisben.api.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.PerfilFuncionalidadeDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.PerfilFuncionalidade;
//import com.bahiana.sisben.model.entity.repository.PerfilFuncionalidadeRepository;
import com.bahiana.sisben.service.PerfilFuncionalidadeService;

@RestController
@RequestMapping(value = "/perfis-funcionalidades")
public class PerfilFuncionalidadeController {
	
	@Autowired
	private PerfilFuncionalidadeService perfilFuncionalidadeService;
	
	//@Autowired
	//PerfilFuncionalidadeRepository perfilFuncionalidadeRepository;
	
	
	@GetMapping(value =  "/listarPaginado" )
    //@ResponseBody
    public Page<PerfilFuncionalidade> listarPaginado(Pageable pageable) {
    	return this.perfilFuncionalidadeService.listarPaginado(pageable);  	  
    }
	
	@PostMapping
	@Transactional
	public ResponseEntity salvar(@RequestBody PerfilFuncionalidadeDto perfilFuncionalidadeDto) {
	  try {
			PerfilFuncionalidade perfilFuncionalidade = new PerfilFuncionalidade();
			perfilFuncionalidade = perfilFuncionalidadeService.salvar(perfilFuncionalidadeDto);
			return new ResponseEntity(perfilFuncionalidade, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	@DeleteMapping("{idPerfil}/{idFuncionalidade}")
	@Transactional
	public ResponseEntity deletar(@PathVariable("idPerfil") Long idPerfil,
			                      @PathVariable("idFuncionalidade") Long idFuncionalidade) {
		
		       
//		perfilFuncionalidadeRepository.deleteById(23L);
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		        //entity é o que retorna de ObterPorId
				return perfilFuncionalidadeService.buscarPerfilFuncionalidade(idPerfil, idFuncionalidade).map(entity -> {					
					perfilFuncionalidadeService.deletar(entity);
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
				    new ResponseEntity("Perfil Funcionalidade não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping(value =  "/listarPaginadoDataTable" )
    //@ResponseBody
    public Page<PerfilFuncionalidade> listarPaginadoDataTable(Pageable pageable) {
    	return this.perfilFuncionalidadeService.listarPaginado(pageable);
    	
    	//Page<PerfilFuncionalidade> responseData = new Page<PerfilFuncionalidade>;
    			//this.perfilFuncionalidadeService.listarPaginado(pageable);
    	
    	//DataTable dataTable = new DataTable();
    	
    }
	
	

}
