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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaMenos24hDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Fornecedor;
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
	
	@PostMapping("/salva-lotes")
	public ResponseEntity salvarLote(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
	  try {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntrega = programacaoEntregaService.salvarLote(programacaoEntregaDto,'I');
			
			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
	  try {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
			programacaoEntregaDto.setId(id);			
			programacaoEntrega = programacaoEntregaService.alterar(programacaoEntregaDto);
			return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
		//Quando o "id" é passado na url o valor é colocado na variável "id".
		@PutMapping("atualiza-lotes")
		public ResponseEntity atualizarLote(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto) {
		  try {
				ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
				programacaoEntrega = programacaoEntregaService.atualizarLote(programacaoEntregaDto,'A');
				return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return ResponseEntity.badRequest().body(e.getMessage());
		     }
	    }
		
		
		
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
		 
		
		 @PostMapping("/salvarMenos24h")
		 @Transactional
		 public ResponseEntity salvarMenos24h(@RequestBody ProgramacaoEntregaMenos24hDto programacaoEntregaMenos24hDto) {
			  try {
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
					programacaoEntrega = programacaoEntregaService.salvarMenos24h(programacaoEntregaMenos24hDto);
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
		  @PatchMapping("/autorizarMenos24h/{id}")
		  public ResponseEntity autorizarMenos24h(@PathVariable("id") Long id, @RequestBody ProgramacaoEntregaMenos24hDto programacaoEntregaMenos24hDto) {
			  try {
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega() ;
					programacaoEntregaMenos24hDto.setId(id);			
					programacaoEntregaMenos24hDto.setStAprov(true);
					programacaoEntrega = programacaoEntregaService.autorizarMenos24h(programacaoEntregaMenos24hDto);
					return new ResponseEntity(programacaoEntrega, HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return ResponseEntity.badRequest().body(e.getMessage());
			     }
		 }
		
		@Transactional
		@PutMapping("/atualizarMenos24h/{id}")
		public ResponseEntity atualizarMenos24h(@PathVariable("id") Long id,@RequestBody ProgramacaoEntregaMenos24hDto programacaoEntregaMenos24hDto) {
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
		
		  //@ResponseBody
		  public ResponseEntity<List<ProgramacaoEntrega>> listarProgramacaoEntregaAnoMesMatricula(@RequestBody ProgramacaoEntregaDto programacaoEntregaDto ) {
			 try {
					
					return new ResponseEntity(programacaoEntregaService.
							listaProgramacaoEntregaAnoMesMatricula(programacaoEntregaDto), HttpStatus.CREATED);
			     } catch (RegraNegocioException e) {
				    return new ResponseEntity<List<ProgramacaoEntrega>>(HttpStatus.BAD_REQUEST);
			     }
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
		  
		  
		  
		
	
  

}