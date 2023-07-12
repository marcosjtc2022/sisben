package com.bahiana.sisben.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.bahiana.sisben.api.dto.ProgramacaoEntregaAvulsaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.response.ProgEntVigenteResponse;
import com.bahiana.sisben.api.response.RegistroEntregaResponse;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
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
    
//    @GetMapping(value =  "/programacao-por-periodo" ) #
//    @ResponseBody
//    public Page<ProgramacaoEntrega> listarProgramacaoPorPeriodo(Pageable pageable,ProgramacaoEntregaDto  programacaoEntregaDTO  ) {
//    	return this.programacaoEntregaService.listarProgramacaoPorPeriodo(pageable,programacaoEntregaDTO);   	  
//    }
    
    @GetMapping(value =  "/listarPaginado" )
    @ResponseBody
    public Page<ProgramacaoEntrega> listarPaginado(Pageable pageable) {
    	return this.programacaoEntregaService.listarPaginado(pageable);  	  
    }   
   
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
	  try {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntrega = programacaoEntregaService.salvar(programacaoEntregaDto);
			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
//	@PostMapping("/salva-lotes")
//	public ResponseEntity salvarLote(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
//	  try {
//			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
//			programacaoEntrega = programacaoEntregaService.salvarLote(programacaoEntregaDto,'I');
//			
//			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
//	     } catch (RegraNegocioException e) {
//		    return ResponseEntity.badRequest().body(e.getMessage());
//	     }
//    }
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
	  try {
		  
		  //Colocar o excluir em caso de alteração da suspensão da exigibilidade.
		  
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntregaDto.setId(id);
			//programacaoEntregaDto
			 programacaoEntregaService.alterar(programacaoEntregaDto);
			//programacaoEntrega = programacaoEntregaService.alterar(programacaoEntregaDto);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			//return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
//	//Usado para recuperar recurso no servidor.
//		//Quando o "id" é passado na url o valor é colocado na variável "id".
//		@PutMapping("atualiza-lotes")
//		public ResponseEntity atualizarLote(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
//		  try {
//				ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
//				programacaoEntrega = programacaoEntregaService.atualizarLote(programacaoEntregaDto,'A');
//				return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
//		     } catch (RegraNegocioException e) {
//			    return ResponseEntity.badRequest().body(e.getMessage());
//		     }
//	    }
		
		
		
		 @GetMapping(value =  "/lista-data-table" )
		    @ResponseBody
		  public ResponseEntity<Iterable<ProgramacaoEntrega>> listarProgramacaoDataTable() {
			 try {
					
					return new ResponseEntity(programacaoEntregaService.findAll(), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<Iterable<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
			     }
		  }
		 
		 
		 @GetMapping(value =  "/lista-filtro-matricula" )
		    @ResponseBody
		  public ResponseEntity<Iterable<ProgramacaoEntrega>> listarPorMatricula(ProgramacaoEntregaDto  programacaoEntregaDTO) {
			 try {
					
					return new ResponseEntity(programacaoEntregaService.findBymatriculaColaborador(programacaoEntregaDTO), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<Iterable<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
			     }
		  }
		 
		
		 @PostMapping("/salvarProgramacaoAvulsa")
		 @Transactional
		 public ResponseEntity salvarProgramacaoAvulsa(@RequestBody ProgramacaoEntregaAvulsaDto programacaoEntregaAvulsaDto) {
			  try {
				  
				    Long countProgPend = programacaoEntregaService.
				    		pesquisarProgrEntregaPendente(programacaoEntregaAvulsaDto.getDataProgramacao().toString(),
				    				                      programacaoEntregaAvulsaDto.getMatriculaColaborador());
					
					if ((countProgPend > 0)) {
						throw new GlobalExceptionHandler("Existe uma programação pendente para esta data!");
					}  
				  
				    Long countProg = programacaoEntregaService.
				    		pesquisarProgrEntregaDataMatr(programacaoEntregaAvulsaDto.getDataProgramacao().toString(),
				    				                      programacaoEntregaAvulsaDto.getMatriculaColaborador());
					
					if ((countProg > 0)) {
						throw new GlobalExceptionHandler("Já existe programação para esta data!");
					} 
					
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
					programacaoEntrega = programacaoEntregaService.salvarProgramacaoAvulsa(programacaoEntregaAvulsaDto);
					
					return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		 }
		 
		 
		  @GetMapping(value =  "/listarProgramcaoEntregaMenos24h" )
		  @ResponseBody
		  public ResponseEntity<List<ProgramacaoEntrega>> listarProgramcaoEntregaMenos24h() {
			 try {
					
					return new ResponseEntity(programacaoEntregaService.listaProgramacao24hOrdenadoData(), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<List<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
			     }
		  }
		  
		  @GetMapping(value =  "/pesquisarProgramcaoEntregaMenos24hPorData" )
			public ResponseEntity<List<ProgramacaoEntrega>> pesquisarPorData(String dataSolicitacao) {
				 try {
					 
					 LocalDate dataSolicitacaoParam = LocalDate.parse(new String(dataSolicitacao));
						
					 return new ResponseEntity(programacaoEntregaService.
							 pesquisaProgramacao24hPorDataOrdenadoData(dataSolicitacaoParam, true),
							 HttpStatus.CREATED);
				     } catch (RegraNegocioException e) {
					    return new ResponseEntity<List<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
				     }
			}
		  
		  @GetMapping("/obterPorId/{id}")
		  public ProgramacaoEntrega obterPorId(@PathVariable("id") Long id) {
				
				Optional<ProgramacaoEntrega> programacaoEntrega = programacaoEntregaService.obterPorId(id);
				return  programacaoEntrega.get();	
						
		  }
		  
		  @Transactional
		  @PutMapping("/autorizar/{id}")
		  public ResponseEntity autorizar(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			  try {
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
					programacaoEntregaDto.setId(id);			
					programacaoEntregaDto.setStAprov(true);
					programacaoEntrega = programacaoEntregaService.autorizar(programacaoEntregaDto);
					return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		 }
		
		@Transactional
		@PutMapping("/atualizarMenos24h/{id}")
		public ResponseEntity atualizarMenos24h(@PathVariable("id") Long id,@RequestBody ProgramacaoEntregaAvulsaDto programacaoEntregaMenos24hDto) {
			  try {
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
					programacaoEntregaMenos24hDto.setId(id);	
					programacaoEntrega = programacaoEntregaService.atualizarMenos24h(programacaoEntregaMenos24hDto);
					return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		}
		
		@Transactional
		@DeleteMapping("/apagarMenos24h/{id}")
		public ResponseEntity apagar(@PathVariable("id") Long id) {
			
			   Long countProgAprovada = programacaoEntregaService.pesquisaProgramacaoEntregaMenos24hAprovada(id);
				
				if ((countProgAprovada > 0) && (countProgAprovada != null)) {
					return new ResponseEntity("Programação entrega com menos de 24h já aprovada!", HttpStatus.BAD_REQUEST);
				} 
			
			//entity é o que retorna de ObterPorId
					return programacaoEntregaService.obterPorId(id).map(entity -> {					
						programacaoEntregaService.apagar(entity);
						return new ResponseEntity(HttpStatus.NO_CONTENT);
					}).orElseGet(() -> 
					    new ResponseEntity("Programação entrega não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
		}
		
		
		 @PostMapping("/salvarProgramacaoMes")
		 @Transactional
		 public ResponseEntity salvarProgramacaoMes(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			  try {
					List<ProgramacaoEntrega> programacaoEntrega = new ArrayList() ;
					programacaoEntrega = programacaoEntregaService.salvarProgramacaoMes(programacaoEntregaDto);
					return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		 }
		
		 @PutMapping("/alterarProgramacaoMes")
		 @Transactional
		 public ResponseEntity alterarProgramacaoMes(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			  try {
					List<ProgramacaoEntrega> listaProgramacaoEntrega = new ArrayList() ;
					listaProgramacaoEntrega = programacaoEntregaService.alterarProgramacaoMes(programacaoEntregaDto);
					
					return new ResponseEntity(listaProgramacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		 }
		 
		 @GetMapping(value =  "/listarProgramacaoEntregaAnoMesMatricula" )
		 public ResponseEntity<List<ProgramacaoEntrega>> listarProgramacaoEntregaAnoMesMatricula(@RequestParam(required = true  ) String matriculaColaborador,
			         @RequestParam(required = true ) String mesAnoProgramacao ) {
			 try {
				 
				    ProgramacaoEntregaDto programacaoEntregaDto = new ProgramacaoEntregaDto();
				    LocalDate mesAnoProgramacaoDate = LocalDate.parse(mesAnoProgramacao);
				    
				    programacaoEntregaDto.setMesAnoProgramacao(mesAnoProgramacaoDate);
				    programacaoEntregaDto.setMatriculaColaborador(matriculaColaborador);
				    
					
					return new ResponseEntity(programacaoEntregaService.
							listaProgramacaoEntregaAnoMesMatricula(programacaoEntregaDto), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<List<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
			     }
		  }
		 
		 @DeleteMapping("/apagarProgramacaoMes")
		 @Transactional
		 public ResponseEntity apagarProgramacaoMes(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
			  try {
					 programacaoEntregaService.apagarProgramacaoMes(programacaoEntregaDto);
					 return new ResponseEntity(HttpStatus.NO_CONTENT);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
			  
		 }
		 
		 
		 @GetMapping(value =  "/listarProgramacaoEntrega" )
		  @ResponseBody
		  public ResponseEntity<List<ProgramacaoEntrega>> listarProgramacaoEntrega() {
			 try {
					
					return new ResponseEntity(programacaoEntregaService.listarProgramacaoEntrega(), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<List<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
			     }
		  }
		 
		 
		 @GetMapping(value =  "/listarComFiltros" )
		    @ResponseBody
		    //public List<ProgramacaoEntrega> listarComFiltros(ProgramacaoEntregaSpecification programacaoEntregaSpecification) {
			 public List<ProgramacaoEntrega> listarComFiltros(@RequestParam(required = false ) String uaRealizada,
					                                          @RequestParam(required = false ) String matriculaColaborador,
					                                          @RequestParam(required = false ) String anoMes,
					                                          @RequestParam(required = false ) String codSetor) {
		    	
				// Page<ProgramacaoEntrega>	listarComFiltros = new Page<ProgramacaoEntrega>();
			 
			     ProgramacaoEntregaSpecification programacaoEntregaSpecification = new ProgramacaoEntregaSpecification();
			     
                 if (uaRealizada != "") {
                	 programacaoEntregaSpecification.setUaRealizada(uaRealizada);	 
			     }
                 
                 if (matriculaColaborador != "") {
                	 programacaoEntregaSpecification.setMatriculaColaborador(matriculaColaborador);	 
			     }
                 
                 if (anoMes != "") {
                	 programacaoEntregaSpecification.setAnoMes(anoMes); 
			     }
                 
                 if (codSetor != "") {
            	   programacaoEntregaSpecification.setCodSetor(codSetor);
		         }
                 
//                 if (idUsuario != null) {
//          	        programacaoEntregaSpecification.setIdUsuario(idUsuario);
//		         }
                 
			     
			    
			    // programacaoEntregaSpecification.setCodSetor(dataProgramacao)
			    // LocalDate dataProgramacaoDate = LocalDate.parse(dataProgramacao);
			    // programacaoEntregaSpecification.setDataProgramacao(dataProgramacaoDate);
				 
				 return this.programacaoEntregaService.listarComFiltros(programacaoEntregaSpecification);
				 
				//return listarComFiltros;
		    	
		    	
		    }
		 
		 
		 @Transactional
			@DeleteMapping("/apagar/{id}")
			public ResponseEntity apagarProgramacaoEntrega(@PathVariable("id") Long id) {
				
//				   Long countProgAprovada = programacaoEntregaService.pesquisaProgramacaoEntregaMenos24hAprovada(id);
//					
//					if ((countProgAprovada > 0) && (countProgAprovada != null)) {
//						return new ResponseEntity("Programação entrega com menos de 24h já aprovada!", HttpStatus.BAD_REQUEST);
//					} 
				
				//entity é o que retorna de ObterPorId
						return programacaoEntregaService.obterPorId(id).map(entity -> {					
							programacaoEntregaService.apagar(entity);
							return new ResponseEntity(HttpStatus.NO_CONTENT);
						}).orElseGet(() -> 
						    new ResponseEntity("Programação entrega não encontrada na base de dados.", HttpStatus.BAD_REQUEST));
			}
		 
		 
//		 @GetMapping(value =  "/lista-filtro-descricao" )
//			public ResponseEntity<List<Fornecedor>> listarPorDescricaoOrdenadoDescricao(FornecedorDto  fornecedorDto) {
//				 try {
//						
//						return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
//				     } catch (RegraNegocioException e) {
//					    return new ResponseEntity<List<Fornecedor>>(HttpStatus.BAD_REQUEST);
//				     }
//			}
		 
		 
		    @GetMapping(value =  "/listarRegistroEntrega" )
		    @ResponseBody
		    List<RegistroEntregaResponse> listarRegistroEntrega(
                     @RequestParam(required = false  ) String matriculaColaborador,
                     @RequestParam(required = false ) String anoMes,
                     @RequestParam(required = false ) String codSetor,
                     @RequestParam(required = false ) String idUa,
                     @RequestParam(required = false ) String dataProgramacao) {
		    	
		    	return this.programacaoEntregaService.listarRegistroEntrega(matriculaColaborador, anoMes, codSetor,idUa, dataProgramacao);  
		    	
		    }
		    
		    
		    @GetMapping(value =  "/listarProgramacaoEntregaVigente" )
		    @ResponseBody
			 public List<ProgEntVigenteResponse> listarProgramacaoEntregaVigente(
                     @RequestParam(required = false  ) String matriculaColaborador,
                     @RequestParam(required = false ) String anoMes,
                     @RequestParam(required = false ) String codSetor) {
		    	
		    	return this.programacaoEntregaService.
		    			listarProgramacaoEntregaVigente(matriculaColaborador,anoMes,codSetor );
		    	
		    }
		    
		   
		
		    @GetMapping("/obterPorId2/{id}")
			public ProgramacaoEntrega obterPorId2(@PathVariable("id") Long id) {
					
					ProgramacaoEntrega programacaoEntrega = programacaoEntregaService.obterPorId2(id);
					return  programacaoEntrega;	
							
		    }	
		    
		    @GetMapping(value =  "/listarAnoMes" )
		    @ResponseBody
		    public List<String> listarAnoMes() {
		    	return this.programacaoEntregaService.listarAnoMes(); 	  
		    }
		    
		    @GetMapping(value =  "/listarProgramacaoEntregaVigenteLiderSetor" )
		    @ResponseBody
			 public List<ProgEntVigenteResponse> listarProgramacaoEntregaVigenteLiderSetor(
                     @RequestParam(required = false  ) String matriculaColaborador,
                     @RequestParam(required = false ) String anoMes,
                     @RequestParam(required = false ) String codSetor,
                     @RequestParam(required = false ) String idUsuarioLogado,
                     @RequestParam(required = false ) String idUa) {
		    	
		    	
		    	
		    	return this.programacaoEntregaService.
		    			listarProgramacaoEntregaVigenteLiderSetor(matriculaColaborador,anoMes, codSetor,idUsuarioLogado, idUa);
		    	
		    }
		    
		    @PostMapping(value =  "/copiarProgramacaoEntrega" )
		    @Transactional
			public List<ProgramacaoEntrega> copiarProgramacaoEntrega(
                     @RequestParam(required = true  ) String dataProgramacao, //dataProgramacao = YYYY-MM-DD
                     @RequestParam(required = true ) String matriculaOrigem,
                     @RequestParam(required = true ) String matriculaDestino,
                     @RequestParam(required = true ) String idUsuarioLogado ) {
		    	
		    	Long countProg = programacaoEntregaService.
			    		pesquisarProgrEntregaDataMatr(dataProgramacao,
			    				matriculaDestino);
				
				if ((countProg > 0)) {
					throw new GlobalExceptionHandler("Já existe programação para esta data e matrícula = " + matriculaDestino );
				} 
		    	
		    	return this.programacaoEntregaService.copiarProgramacaoEntrega(dataProgramacao
		    			,matriculaOrigem,matriculaDestino,idUsuarioLogado);
		    	
		    }
		    
		    @PostMapping(value =  "/copiarProgramacaoEntregaVariasMatriculas" )
		    @Transactional
			public List<ProgramacaoEntrega> copiarProgramacaoEntregaVariasMatriculas(
                     @RequestParam(required = true  ) String dataProgramacao, //dataProgramacao = YYYY-MM-DD
                     @RequestParam(required = true ) String matriculaOrigem,
                     @RequestParam(required = true ) String matriculasDestino,
                     @RequestParam(required = true ) String idUsuarioLogado ) {
//		    	
//		    	Long countProg = programacaoEntregaService.
//			    		pesquisarProgrEntregaDataMatr(dataProgramacao,
//			    				matriculaDestino);
//				
//				if ((countProg > 0)) {
//					throw new GlobalExceptionHandler("Já existe programação para esta data e matrícula = " + matriculaDestino );
//				} 
		    	
		    	return this.programacaoEntregaService.copiarProgramacaoEntregaVariasMatriculas(dataProgramacao
		    			,matriculaOrigem,matriculasDestino,idUsuarioLogado);
		    	
		    }
		    
		    @GetMapping("/pesquisarDataMatrRegEntr")
			public ProgramacaoEntrega pesquisarProgrEntregaDataMatrRegistroEntrega(@RequestParam(required = true  ) String matriculaColaborador,
			         @RequestParam(required = true ) String dataProgramacao) {
					
					return programacaoEntregaService.pesquisarProgrEntregaDataMatrRegistroEntrega(dataProgramacao, matriculaColaborador);
						
							
			}
	
	
  

}