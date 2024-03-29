package com.bahiana.sisben.api.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.response.ProgEntVigenteResponse;
import com.bahiana.sisben.api.response.RegistroEntregaResponse;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
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
				//return new ResponseEntity("Fornecedor está vinculado a um usuário!", HttpStatus.BAD_REQUEST);
				throw new GlobalExceptionHandler("Fornecedor está vinculado a um usuário!");
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
		public void registrarEntrega(@PathVariable("id") Long id,@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			 
					programacaoEntregaDto.setId(id);
					
					//man 08.08.2023
					
				    ProgramacaoEntrega programacaoEntrega =	programacaoEntregaService.obterPorId2(id);
				    
				    Long countProgCanc = programacaoEntregaService.
							pesquisarProgrEntregaRepInc(programacaoEntrega.getDataProgramacao().toString(),
			    				                      programacaoEntrega.getMatriculaColaborador());
				
					if ((countProgCanc > 0)) {
						throw new GlobalExceptionHandler("Existe uma programação de inclusão reprovada para esta data de programação!");
					}  
				     
				    
					Long countProgPend = programacaoEntregaService.
						    		pesquisarProgrEntregaPendente(programacaoEntrega.getDataProgramacao().toString(),
						    				                      programacaoEntrega.getMatriculaColaborador());
							
					if ((countProgPend > 0)) {
							throw new GlobalExceptionHandler("Existe uma programação pendente esta data de programação!");
					}
					
				    
					
					//man 08.08.2023
					
					
					
					programacaoEntregaService.registrarEntrega(programacaoEntregaDto);
			 
		}
		
		
		@GetMapping(value =  "/listarProgEntAnalise24h" )
		@ResponseBody
		public List<ProgramacaoEntrega> listarProgEntAprovar24h(
                  @RequestParam(required = false  ) String matriculaColaborador,
                  @RequestParam(required = false ) String anoMes,
                  @RequestParam(required = false ) String codSetor,
                  @RequestParam(required = false ) String idUa) {
			
			    Long idUaParam = null;
				if ((idUa != "")&((idUa != null))) {
					idUaParam = Long.valueOf(idUa);
				}
				
		    	return this.programacaoEntregaService.
		    			listarProgEntAnalise24h(matriculaColaborador,anoMes,codSetor,idUaParam);
		    	
	  }
		
	  @Transactional
	  @PutMapping("/atualizarStatusAnalise24h/{id}")
	  public void atualizarStatusAnalise24h(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
					
					programacaoEntregaDto.setId(id);			
					programacaoEntregaService.atualizarStatusAnalise24h(programacaoEntregaDto);
				
	 }	
	  
	  @GetMapping(value =  "/listarRegistroEntrega" )
	    @ResponseBody
	    List<RegistroEntregaResponse> listarRegistroEntrega(
               @RequestParam(required = false  ) String matriculaColaborador,
               @RequestParam(required = false ) String anoMes,
               @RequestParam(required = false ) String codSetor,
               @RequestParam(required = false ) String idUa,
               @RequestParam(required = false ) String dataProgramacao) {
	    	
	    	return this.programacaoEntregaService.listarRegistroEntrega(matriculaColaborador, anoMes, codSetor,idUa,dataProgramacao);  
	    	
	    }
	  
	    @PostMapping("/registrarEntregaNprog")
		@Transactional
		public ResponseEntity registrarEntregaNprog(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
		  try {
			  
			  
				Long countProgCanc = programacaoEntregaService.
						pesquisarProgrEntregaRepInc(programacaoEntregaDto.getDataProgramacao().toString(),
		    				                      programacaoEntregaDto.getMatriculaColaborador());
			
				if ((countProgCanc > 0)) {
					throw new GlobalExceptionHandler("Existe uma programação de inclusão reprovada para esta data!");
				}  
			  
			    Long countProgPend = programacaoEntregaService.
			    		pesquisarProgrEntregaPendente(programacaoEntregaDto.getDataProgramacao().toString(),
			    				                      programacaoEntregaDto.getMatriculaColaborador());
				
				if ((countProgPend > 0)) {
					throw new GlobalExceptionHandler("Existe uma programação pendente para esta data!");
				}  
			  
			    Long countProg = programacaoEntregaService.
			    		pesquisarProgrEntregaDataMatr(programacaoEntregaDto.getDataProgramacao().toString(),
			    				                      programacaoEntregaDto.getMatriculaColaborador());
				
				if ((countProg > 0)) {
					throw new GlobalExceptionHandler("Já existe programação para esta data!");
				} 
			  
				ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
				programacaoEntrega = programacaoEntregaService.registrarEntregaNprog(programacaoEntregaDto);
				return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
	    }
	    
	    
	    @Transactional
		@PutMapping("/excluirLogicaEntrega/{id}")
		public void excluirLogicaEntrega(@PathVariable("id") Long id,@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			 
					programacaoEntregaDto.setId(id);
					
					//man 24.08.2023
					
				    ProgramacaoEntrega programacaoEntrega =	programacaoEntregaService.obterPorId2(id);
				    
				    Long countProgCanc = programacaoEntregaService.
							pesquisarProgrEntregaRepInc(programacaoEntrega.getDataProgramacao().toString(),
			    				                      programacaoEntrega.getMatriculaColaborador());
				
					if ((countProgCanc > 0)) {
						throw new GlobalExceptionHandler("Existe uma programação de inclusão reprovada para esta data de programação!");
					}  
				     
				    
					Long countProgPend = programacaoEntregaService.
						    		pesquisarProgrEntregaPendente(programacaoEntrega.getDataProgramacao().toString(),
						    				                      programacaoEntrega.getMatriculaColaborador());
							
					if ((countProgPend > 0)) {
							throw new GlobalExceptionHandler("Existe uma programação pendente esta data de programação!");
					}
					
					if (programacaoEntrega.getDataEntrega() == null) {
						throw new GlobalExceptionHandler("Programação ainda não foi registrada!");
					}
					
				    
					
					//man 24.08.2023
					
					
					
					programacaoEntregaService.excluirLogicaEntrega(programacaoEntregaDto);
			 
		}
		
		

}
