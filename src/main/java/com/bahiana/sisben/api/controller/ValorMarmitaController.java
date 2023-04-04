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
import org.springframework.web.bind.annotation.RestController;

import com.bahiana.sisben.api.dto.ValorMarmitaDto;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.ValorMarmita;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.service.ValorMarmitaService;
import com.bahiana.sisben.util.UtilSisben;

@RestController
@RequestMapping(value = "/valores-marmitas")
public class ValorMarmitaController {
	
	@Autowired
	private ValorMarmitaService valorMarmitaService;
	
	@Autowired
	private ProgramacaoEntregaService programacaoEntregaService;
	
	

	@GetMapping(value =  "/paginacao-simples" )
    public Page<ValorMarmita> listarPaginadoSimples() {
    	return this.valorMarmitaService.listarPaginadoSimples();  	  
    }
	

	@GetMapping(value =  "/listarPaginado" )
    public Page<ValorMarmita> listarPaginado(Pageable pageable) {
    	return this.valorMarmitaService.listarPaginado(pageable);  	  
    }
	
	@Transactional
	@PostMapping
	public ResponseEntity salvar(@RequestBody ValorMarmitaDto valorMarmitaDto) {
	  try {
		  
		    Integer countVlMarmita = valorMarmitaService.verificarValorVigencia(valorMarmitaDto.getDataInicial(), valorMarmitaDto.getDataFinal());
			
			if ((countVlMarmita > 0)) {
				throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
			}
			
			Integer countVlAtual = valorMarmitaService.verificarValorVigenciaAtual();
				
			if ((countVlAtual > 0)) {
					throw new GlobalExceptionHandler("Para inserir uma nova vigência,"
							+ " informe a data final da atual vigência !");
			}
			
		  
		    ValorMarmita valorMarmita = new ValorMarmita() ;
		    valorMarmita = valorMarmitaService.salvar(valorMarmitaDto);
			return new ResponseEntity(valorMarmita, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
	@Transactional
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ValorMarmitaDto valorMarmitaDto) {
	try {
		
//		    Integer countVlMarmita = valorMarmitaService.verificarValorVigencia(valorMarmitaDto.getDataInicial(), valorMarmitaDto.getDataFinal());
//			
//			if ((countVlMarmita > 0)) {
//				throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//			}  
			
     		//Caso o usuário altere apenas o valor o count não será zero.
//            Integer countVlMarmita = valorMarmitaService.verificarVigenciaParaDataInformada(valorMarmitaDto.getDataInicial(), valorMarmitaDto.getDataFinal());
			
            ValorMarmita valorMarmita = new ValorMarmita() ;
            Integer count = 0;
 		    count = valorMarmitaService.pesquisarValorVigenciaExcluiAlterada(valorMarmitaDto.getDataInicial(), valorMarmitaDto.getDataFinal(), id); 
			//Compara a vigência informada com as outras vigências
			if (count > 0) {
				throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
			}
				
				//valorMarmita = valorMarmitaService.obterPorId(id).get();
				
//				List<ValorMarmita> listValorMarmita = valorMarmitaService.verificarOutrasVigencias(id);
				
				//Verifica as outras vigências diferentes da data informada na alteração.
//				if (listValorMarmita != null) {
					
					LocalDate menorDataInicial = valorMarmitaService.pesquisarMenorDataInicial();
					LocalDate maiorDataFinal = valorMarmitaService.pesquisarMaiorDataFinal();
					
					UtilSisben utilSisben = new UtilSisben();
						
					
					Integer dataInvMenorInicial = utilSisben.inverterData(menorDataInicial);
					Integer dataInvMaiorFinal = utilSisben.inverterData(maiorDataFinal);
					
					
//					//Calcula os dias do mês.
//					UtilSisben utilSisben = new UtilSisben();
					
					Integer dataInvertidaInicialDto = utilSisben.inverterData(valorMarmitaDto.getDataInicial());
					Integer dataInvertidaFinalDto = utilSisben.inverterData(valorMarmitaDto.getDataFinal());
					
					if ((dataInvertidaInicialDto <= dataInvMenorInicial )&&
							(dataInvertidaFinalDto >= dataInvMaiorFinal )	) {
						throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
					}
					
					
					if ((dataInvertidaInicialDto >= dataInvMenorInicial )&&
							(dataInvertidaInicialDto <= dataInvMaiorFinal )&&
							(dataInvertidaFinalDto >= dataInvMaiorFinal )) {
						throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
					}
					
					if ((dataInvertidaFinalDto >= dataInvMenorInicial )&&
							(dataInvMaiorFinal <= dataInvMenorInicial )&&
							(dataInvertidaInicialDto <= dataInvMenorInicial )) {
						throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
					}
					
//					Integer dataInvertidaInicialBanco = null;
//					Integer dataInvertidaFinalBanco = null;
					
					
//					for (ValorMarmita valorMarmitaList : listValorMarmita) {
//						
//						//listValorMarmita = valorMarmitaService.pesquisarValorVigenciaExcluiAlterada();
//						
//						
//						
//						
//						dataInvertidaInicialBanco = utilSisben.inverterData(valorMarmitaList.getDataInicial());
//						dataInvertidaFinalBanco = utilSisben.inverterData(valorMarmitaList.getDataFinal());
//					}
//			 }		
						
						
//						
//						if ((dataInvertidaInicialDto >= dataInvertidaInicialBanco)&&
//							(dataInvertidaFinalDto <= dataInvertidaFinalBanco)) {
//							throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//						}
//						
//						if ((dataInvertidaInicialDto <= dataInvertidaInicialBanco)&&
//								(dataInvertidaInicialDto <= dataInvertidaFinalBanco)&&
//								(dataInvertidaFinalDto >= dataInvertidaFinalBanco)&&
//								(dataInvertidaFinalDto >= dataInvertidaInicialBanco)) {
//								throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//						}	
//						
//						if ((dataInvertidaInicialDto <= dataInvertidaInicialBanco)&&
//								(dataInvertidaInicialDto <= dataInvertidaFinalBanco)&&
//								(dataInvertidaFinalDto >= dataInvertidaFinalBanco)&&
//								(dataInvertidaFinalDto >= dataInvertidaInicialBanco)) {
//								throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//						}		
//			
						
//						if ((dataInvertidaInicialDto >= dataInvertidaInicialBanco)&&
//								(dataInvertidaFinalDto >= dataInvertidaFinalBanco)) {
//								throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//						}
//						
//						if ((dataInvertidaInicialDto >= dataInvertidaInicialBanco)&&
//								(dataInvertidaInicialDto <= dataInvertidaFinalBanco)) {
//								throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//						}
//						
//						if ((dataInvertidaFinalDto >= dataInvertidaInicialBanco)&&
//								(dataInvertidaFinalDto <= dataInvertidaFinalBanco)) {
//								throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//						}
//						
//						if (valorMarmitaList.getDataInicial().equals(valorMarmitaDto.getDataInicial())) {
//							 throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//				        }
//						if (valorMarmitaList.getDataFinal().equals(valorMarmitaDto.getDataFinal())) {
//							 throw new GlobalExceptionHandler("Já existe vigência para a data informada!");
//				        }
						
						
						
						
						
						
//					}
					
					
//				}
			    
//			}
			
			valorMarmitaDto.setId(id);			
		    valorMarmita = valorMarmitaService.alterar(valorMarmitaDto);
			return new ResponseEntity(valorMarmita, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		
       // ProgramacaoEntrega programacaoEntrega = programacaoEntregaService.BuscaPorIdValor(id);
		
        Long countValorMarmita = programacaoEntregaService.pesquisaValorMarmita(id);
		
		if ((countValorMarmita > 0) && (countValorMarmita != null)) {
			//return new ResponseEntity("Valor marmita está vinculado a uma programação entrega!", HttpStatus.BAD_REQUEST);
			throw new GlobalExceptionHandler("Valor da marmita está vinculado a uma programação entrega!");
		} 
				
			//entity é o que retorna de ObterPorId
			return valorMarmitaService.obterPorId(id).map(entity -> {					
				valorMarmitaService.deletar(entity);
		   return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
		   new ResponseEntity("Valor marmita não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}	
	
	@GetMapping(value =  "/listarOrdenadoValor" )
    public List<ValorMarmita> listarOrdenadoValor() {
    	return this.valorMarmitaService.listarSimplesOrdenadoValor();  	  
    }
	
	@GetMapping("/obterPorId/{id}")
	public ValorMarmita obterPorId(@PathVariable("id") Long id) {
		
		Optional<ValorMarmita> valorMarmita = valorMarmitaService.obterPorId(id);
		return  valorMarmita.get();	
				
	}
	
	
	

}
