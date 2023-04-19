package com.bahiana.sisben.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.VwSisbenElegibilidadeDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
import com.bahiana.sisben.service.VwSisbenElegibilidadeService;


@RestController
@RequestMapping(value = "/elegiveis")
public class VwSisbenElegibilidadeController {
	
	@Autowired
	private VwSisbenElegibilidadeService vwSisbenElegibilidadeService;
	
	@GetMapping(value =  "/pesquisarPorMatriculaOrdenadoNome" )
	public ResponseEntity<List<VwSisbenElegibilidade>> pesquisarPorMatriculaOrdenadoNome(VwSisbenElegibilidadeDto  vwSisbenElegibilidadeDto) {
		 try {
				
			// return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(vwSisbenElegibilidadeService.pesquisarPorMatriculaOrdenadoNome(vwSisbenElegibilidadeDto),HttpStatus.CREATED );
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/pesquisarPorNomeOrdenadoNome" )
	public ResponseEntity<List<VwSisbenElegibilidade>> pesquisarPorNomeOrdenadoNome(VwSisbenElegibilidadeDto  vwSisbenElegibilidadeDto) {
		 try {
				
			// return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(vwSisbenElegibilidadeService.pesquisarPorNomeOrdenadoNome(vwSisbenElegibilidadeDto),HttpStatus.CREATED );
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/listarElegivelOrdenadoNome" )
	public ResponseEntity<List<VwSisbenElegibilidade>> listarElegivelOrdenadoNome() {
		 try {
				
			// return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(vwSisbenElegibilidadeService.listarElegivelOrdenadoNome(),HttpStatus.CREATED );
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping("/obterPorMatricula/{matriculaFuncionario}")
	public VwSisbenElegibilidade obterPorMatricula(@PathVariable("matriculaFuncionario") String matriculaFuncionario) {
		
		//Optional<VwSisbenFuncionario> funcionario = new ArrayList();
		Optional<VwSisbenElegibilidade> elegivel = vwSisbenElegibilidadeService.ObterPorMatricula(matriculaFuncionario);
		return  elegivel.get();	
				
	}
	
	@GetMapping(value =  "/pesquisarPorMatriculaEliderSetorOrdenadoNome" )
	public ResponseEntity<List<VwSisbenElegibilidade>> pesquisarPorMatriculaEliderSetorOrdenadoNome(VwSisbenElegibilidadeDto  vwSisbenElegibilidadeDto) {
		 try {
				
			// return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(vwSisbenElegibilidadeService.pesquisarPorMatriculaEliderSetorOrdenadoNome(vwSisbenElegibilidadeDto),HttpStatus.CREATED );
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	
	@GetMapping(value =  "/pesquisarPorNomeEliderSetorOrdenadoNome" )
	public ResponseEntity<List<VwSisbenElegibilidade>> pesquisarPorNomeEliderSetorOrdenadoNome(VwSisbenElegibilidadeDto  vwSisbenElegibilidadeDto) {
		 try {
				
			// return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(vwSisbenElegibilidadeService.pesquisarPorNomeEliderSetorOrdenadoNome(vwSisbenElegibilidadeDto),HttpStatus.CREATED );
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@GetMapping(value =  "/listarElegivelPorSetorLiderOrdenadoNome" )
	public ResponseEntity<List<VwSisbenElegibilidade>> listarElegivelPorSetorLiderOrdenadoNome(VwSisbenElegibilidadeDto  vwSisbenElegibilidadeDto) {
		 try {
				
				return new ResponseEntity(vwSisbenElegibilidadeService.listarElegivelPorLiderSetorOrdenadoNome(vwSisbenElegibilidadeDto),HttpStatus.CREATED );
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<VwSisbenElegibilidade>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	
//	@GetMapping("/obterPorMatriculaEliderSetor/{matriculaFuncionario}/{idUsuarioLogado}")
//	public VwSisbenElegibilidade obterPorMatriculaEliderSetor(
//			@PathVariable("matriculaFuncionario") String matriculaFuncionario,
//			@PathVariable("idUsuarioLogado") String idUsuarioLogado) {
//		
//		//Optional<VwSisbenFuncionario> funcionario = new ArrayList();
//		Optional<VwSisbenElegibilidade> elegivel = vwSisbenElegibilidadeService.obterPorMatriculaEliderSetor(matriculaFuncionario, idUsuarioLogado);
//		return  elegivel.get();	
//				
//	}

	

}
