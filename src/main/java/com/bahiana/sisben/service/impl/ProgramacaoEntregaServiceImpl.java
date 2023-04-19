package com.bahiana.sisben.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bahiana.sisben.api.dto.ProgEntVigenteDto;
import com.bahiana.sisben.api.dto.ProgEntVigenteNpDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaAvulsaDto;
import com.bahiana.sisben.api.response.ProgEntVigenteResponse;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.model.entity.Calendario;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.UnidadeAcademica;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.ValorMarmita;
import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
import com.bahiana.sisben.model.entity.VwSisbenSetor;
import com.bahiana.sisben.model.entity.repository.ProgramacaoEntregaRepository;
import com.bahiana.sisben.service.CalendarioService;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.service.SuspensaoElegibilidadeService;
import com.bahiana.sisben.service.UnidadeAcademicaService;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;
import com.bahiana.sisben.service.ValorMarmitaService;
import com.bahiana.sisben.service.VwSisbenElegibilidadeService;
import com.bahiana.sisben.service.VwSisbenFeriasElegivelService;
import com.bahiana.sisben.service.VwSisbenFuncionarioService;
import com.bahiana.sisben.service.VwSisbenSetorService;
import com.bahiana.sisben.specification.ProgramacaoEntregaSpecification;
import com.bahiana.sisben.util.UtilSisben;


@Service
public class ProgramacaoEntregaServiceImpl implements ProgramacaoEntregaService   {

	@Autowired
	ProgramacaoEntregaRepository programacaoEntregaRepository;
	
	@Autowired
	CalendarioService calendarioService;
	
	@Autowired
	VwSisbenElegibilidadeService vwSisbenElegibilidadeService;
	
	@Autowired
	UnidadeAcademicaService unidadeAcademicaService;
	
	@Autowired
	ValorMarmitaService valorMarmitaService;
	
	@Autowired
	SuspensaoElegibilidadeService suspensaoElegibilidadeService;
	
	@Autowired
	VwSisbenFuncionarioService vwSisbenFuncionarioService;
	
	@Autowired
	VwSisbenSetorService vwSisbenSetorService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioSetorGerenciadoService usuarioSetorGerenciadoService;
	
	
	private static boolean mesCorrente = false;
	
	
	
	@Autowired
	VwSisbenFeriasElegivelService vwSisbenFeriasElegivel;
	
	
	public static boolean isMesCorrente() {
		return mesCorrente;
	}

	public static void setMesCorrente(boolean mesCorrente) {
		ProgramacaoEntregaServiceImpl.mesCorrente = mesCorrente;
	}

	@Override
	@Transactional //Abre uma transação. Ao final se ocorrer tudo bem faz commit. No caso de erro faz rollback.
	public ProgramacaoEntrega salvar(ProgramacaoEntregaDto programacaoEntregaDto) {
		//ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
		 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
		 return programacaoEntregaRepository.save(programacaoEntrega);
	}
	
	//@Override
	public ProgramacaoEntrega toProgramacaoEntrega(ProgramacaoEntregaDto programacaoEntregaDto) {
		
		ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega();
		
		//gerar com método construtor.
		if ((programacaoEntregaDto.getId() != null)) {
			programacaoEntrega.setId(programacaoEntregaDto.getId());
		}
		programacaoEntrega.setIdJustificativa(programacaoEntregaDto.getIdJustificativa());
		programacaoEntrega.setUaPrevista(programacaoEntregaDto.getUaPrevista());
		programacaoEntrega.setUaRealizada(programacaoEntregaDto.getUaRealizada());
		programacaoEntrega.setMatriculaColaborador(programacaoEntregaDto.getMatriculaColaborador());
		programacaoEntrega.setDataEntrega(programacaoEntregaDto.getDataEntrega());
		programacaoEntrega.setDataSolicitacao(programacaoEntregaDto.getDataSolicitacao());
		programacaoEntrega.setIdUa(programacaoEntregaDto.getIdUa());
		programacaoEntrega.setIdUsuario(programacaoEntregaDto.getIdUsuario());
		//programacaoEntrega.setIdValor(4);
		programacaoEntrega.setIdValor(programacaoEntregaDto.getIdValor());
		programacaoEntrega.setIdUsuarioUltimaModificacao(programacaoEntregaDto.getIdUsuario());
		programacaoEntrega.setSolicExtra(programacaoEntregaDto.getSolicExtra());
		programacaoEntrega.setStAprov(programacaoEntregaDto.getStAprov());
		//programacaoEntrega.setTabelaProgramacaoEntrega(programacaoEntregaDto.getTabelaProgramacaoEntrega());
		programacaoEntrega.setDataUltimaModificacao(LocalDateTime.now());
		programacaoEntrega.setTabelaProgramacaoEntrega(programacaoEntregaDto.getTabelaProgramacaoEntrega());
		
		
		
		return programacaoEntrega;
	}


	@Override
	@Transactional
	public void alterar(ProgramacaoEntregaDto programacaoEntregaDto) {
		
		 //ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 programacaoEntregaDto.setDataUltimaModificacao(dataModificacao);
		 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
		 //return programacaoEntregaRepository.save(programacaoEntrega);
		  programacaoEntregaRepository.atulizaProgramacaoEntrega(programacaoEntrega.getDataEntrega(),
				 programacaoEntrega.getUaRealizada(),
				 programacaoEntrega.getIdUsuario(),
				 programacaoEntrega.getIdUsuarioUltimaModificacao(),
				 programacaoEntrega.getIdUa(),
				 programacaoEntrega.getId());
		
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	public Page<ProgramacaoEntrega> listarPaginado(Pageable pageable) {	
		return this.programacaoEntregaRepository.findAll(pageable);
	}

	@Override
	public Page<ProgramacaoEntrega> listarPaginadoSimples() {
		return this.programacaoEntregaRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override 
	public Page<ProgramacaoEntrega> listarPaginadoEOrdenado(@PageableDefault(direction = Direction.DESC, sort = "dataUltimaModificacao")Pageable pageable) {
		return this.programacaoEntregaRepository.findAll(pageable);
	}

	@Override
	public Page<ProgramacaoEntrega> listarPaginadoQueryDinamica(ProgramacaoEntregaSpecification programacaoEntregaSpecification, Pageable pageable) {
		Pageable wholePage = Pageable.unpaged();
		//return this.programacaoEntregaRepository.findAll(programacaoEntregaSpecification.toSpec(), pageable);
		return this.programacaoEntregaRepository.findAll(programacaoEntregaSpecification.toSpec(), wholePage);
	}

//	@Override #
//	public Page<ProgramacaoEntrega> listarProgramacaoPorPeriodo(Pageable pageable , ProgramacaoEntregaDto programacaoEntregaDTO) {
//		
//		Page<ProgramacaoEntrega> ProgramacaoEntregaLista =  this.programacaoEntregaRepository.programacaoPorPeriodo
//				(pageable,programacaoEntregaDTO.getMatriculaColaborador(),
//						  programacaoEntregaDTO.getUaPrevista(),
//						  programacaoEntregaDTO.getCodSetor());
//		return ProgramacaoEntregaLista;
//	}

//	@Override
//	public ProgramacaoEntrega atualizarLote(ProgramacaoEntregaDto programacaoEntregaDto, char operacao) {
//		 ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
//		 List<ProgramacaoEntrega> listaProgramacaoEntrega = concatenaCamposTabela(programacaoEntrega, operacao);
//		 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
//				//validaEPersisteInclusao(centro);	
//				this.programacaoEntregaRepository.save(programacaoEntregaLinha);
//		 }
//		 return programacaoEntrega;
//	}

	@Override
	public Optional<ProgramacaoEntrega> obterPorId(Long id) {
		return this.programacaoEntregaRepository.findById(id);
	}
	
	
	
	// 944 - Método para conversão de classe.
		public static ProgramacaoEntrega from(ProgramacaoEntregaDto programacaoEntregaDTO) {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega();
			BeanUtils.copyProperties(programacaoEntregaDTO, programacaoEntrega);
			
			return programacaoEntrega;
		}
		

		@Override
		public Iterable<ProgramacaoEntrega> findAll() {
			// TODO Auto-generated method stub
			return programacaoEntregaRepository.findAll();
		}

//		@Override
//		public Iterable<ProgramacaoEntrega> programacaoDataTable(ProgramacaoEntregaDto programacaoEntregaDTO) {
//			Iterable<ProgramacaoEntrega> ProgramacaoEntregaLista =  this.programacaoEntregaRepository.programacaoDataTable
//					(programacaoEntregaDTO.getMatriculaColaborador(),
//							  programacaoEntregaDTO.getUaPrevista(),
//							  programacaoEntregaDTO.getCodSetor());
//			return ProgramacaoEntregaLista;
//		}

		@Override
		public Iterable<ProgramacaoEntrega> findBymatriculaColaborador(ProgramacaoEntregaDto programacaoEntregaDTO) {
			Iterable<ProgramacaoEntrega> ProgramacaoEntregaLista =  this.programacaoEntregaRepository.findBymatriculaColaboradorAndUaPrevista(
					programacaoEntregaDTO.getMatriculaColaborador(), programacaoEntregaDTO.getUaPrevista());
			
		   return  ProgramacaoEntregaLista;
		}

		@Override
		public long pesquisaJustificativa(Long idJustificativa) {
			Long countJustificativa =  this.programacaoEntregaRepository.pesquisaJustificativa(idJustificativa);
			
		    return  countJustificativa;
		}

		@Override
		public long pesquisaValorMarmita(Long idValor) {
			Long countValorMarmita =  this.programacaoEntregaRepository.pesquisaValorMarmita(idValor);
			
		    return  countValorMarmita;
		}

		@Override
		public long pesquisaUsuarioEntrega(Long idUsuario) {
            Long countUsuarioEntrega =  this.programacaoEntregaRepository.pesquisaUsuarioEntrega(idUsuario);
			
		    return  countUsuarioEntrega;
		}

		@Override
		@Transactional
		public ProgramacaoEntrega salvarProgramacaoAvulsa(ProgramacaoEntregaAvulsaDto programacaoEntregaAvulsaDto) {
			
			Long contFerias = 0L; 			
			Long contSusElegibilidade = 0L;
			
			//Opção por este tipo de chamada para não trazer muitos objetos para a memória.
			//Pesquisa se existe suspensão da eligibilidade para o ano e mês.
			contSusElegibilidade = 0L;
			contSusElegibilidade = suspensaoElegibilidadeService.
				pesquisarSuspensao(programacaoEntregaAvulsaDto.getDataProgramacao(), programacaoEntregaAvulsaDto.getMatriculaColaborador());
			
			//Pesquisa se existem férias.
			contFerias = 0L;
			contFerias = vwSisbenFeriasElegivel.pesquisarFeriasElegivel(programacaoEntregaAvulsaDto.getDataProgramacao(), programacaoEntregaAvulsaDto.getMatriculaColaborador());
			
			//Só insere se não existirem férias nem suspensão da eligibilidade.
			if ((contSusElegibilidade > 0)||(contFerias > 0)) {
				throw new GlobalExceptionHandler("Data com férias ou suspensão da elegibilidade!");
			}
			
			//Recupera setor do usuário.
			VwSisbenFuncionario vwSisbenFuncionario = vwSisbenFuncionarioService.
			ObterPorMatricula(programacaoEntregaAvulsaDto.getMatriculaColaborador()).get();
			
			programacaoEntregaAvulsaDto.setCodSetor(vwSisbenFuncionario.getCodSecao());
			
			//programacaoEntregaAvulsaDto.setIdValor(programacaoEntregaAvulsaDto.getIdValor());
			
			LocalDateTime dataModificacao = LocalDateTime.now();
			programacaoEntregaAvulsaDto.setDataUltimaModificacao(dataModificacao);
			 
			
			Integer intMesProgAvulsa = programacaoEntregaAvulsaDto.getDataProgramacao().getMonthValue();
			Integer intAnoProgAvulsa = programacaoEntregaAvulsaDto.getDataProgramacao().getYear();
				
				
			String strMesProgAvulsa = intMesProgAvulsa.toString();
			if (strMesProgAvulsa.length()== 1) {
				strMesProgAvulsa = "0" + strMesProgAvulsa;
			}
				
			String strAnoMesProgAvulsa = intAnoProgAvulsa.toString() + strMesProgAvulsa; 
				
			programacaoEntregaAvulsaDto.setAnoMes(strAnoMesProgAvulsa);
			
			//Converte de dto para o objeto bean.
			ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaAvulsaDto);
			
			/// alteração começa aqui.
			Calendario calendario = new Calendario();
			
			
			//Pesquisa existência de data especial.
			calendario = calendarioService.pesquisarPorData(programacaoEntregaAvulsaDto.getDataProgramacao());
			programacaoEntrega.setDescricaoFeriado(null);
			if (calendario != null) {
				programacaoEntrega.setDescricaoFeriado(calendario.getDescricao());
			};
			
			
			/// alteração Termina aqui.
			
			
			return programacaoEntregaRepository.save(programacaoEntrega);
		}
		
		// 944 - Método para conversão de classe.
		public static ProgramacaoEntrega from(ProgramacaoEntregaAvulsaDto programacaoEntregaEntregaMenos24hDto) {
					ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega();
					
					BeanUtils.copyProperties(programacaoEntregaEntregaMenos24hDto, programacaoEntrega);
					
			return programacaoEntrega;
		}

		@Override
		public List<ProgramacaoEntrega> listaProgramacao24hOrdenadoData() {
			return programacaoEntregaRepository.listaProgramacao24hOrdenadoData();
		}

		@Override
		public List<ProgramacaoEntrega> pesquisaProgramacao24hPorDataOrdenadoData(LocalDate dataSolicitacao, Boolean solicExtra) {
			return programacaoEntregaRepository.findByDataSolicitacaoAndSolicExtraOrderByDataSolicitacao(dataSolicitacao, solicExtra);
		}

		@Override
		public ProgramacaoEntrega autorizar(
			 ProgramacaoEntregaDto programacaoEntregaDto) {
			 ProgramacaoEntrega programacaoEntrega =  programacaoEntregaRepository.findById(programacaoEntregaDto.getId()).get();
			 LocalDateTime dataModificacao = LocalDateTime.now();
			 programacaoEntregaDto.setDataUltimaModificacao(dataModificacao);
			 programacaoEntregaDto.setStAprov(programacaoEntregaDto.getStAprov());
			 programacaoEntregaDto.setIdUsuarioUltimaModificacao(programacaoEntregaDto.getIdUsuarioUltimaModificacao());
			 programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
			 return programacaoEntregaRepository.save(programacaoEntrega);
		}

		@Override
		public ProgramacaoEntrega atualizarMenos24h(ProgramacaoEntregaAvulsaDto programacaoEntregaMenos24hDto) {
			 LocalDateTime dataModificacao = LocalDateTime.now();
			 programacaoEntregaMenos24hDto.setDataUltimaModificacao(dataModificacao);
			 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaMenos24hDto);
			 return programacaoEntregaRepository.save(programacaoEntrega);
		}

		@Override
		public void apagar(ProgramacaoEntrega programacaoEntrega) {
			programacaoEntregaRepository.delete(programacaoEntrega);
		}

		@Override
		public long pesquisaProgramacaoEntregaMenos24hAprovada(Long id) {
			return programacaoEntregaRepository.pesquisaProgramacaoEntregaMenos24hAprovada(id);
		}

		@Override
		public List<ProgramacaoEntrega> salvarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			
			//Valida os campos do formulário e recupera valores
			//validaFormularioEobtemValores(programacaoEntregaDto);

			
			//Calcula os dias do mês.
			UtilSisben utilSisben = new UtilSisben();
			
			Integer diasProgramacaoMes = utilSisben.calculaDiasMes(programacaoEntregaDto.getMesAnoProgramacao(),
					                                               programacaoEntregaDto.getDataAtual());
			
			//Recupera o mês da programação.
			Integer mesProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getMonth().getValue();
			//Recupera o primeiro e o último dia do mês da programação.
			LocalDate utlimaDataMes = programacaoEntregaDto.getMesAnoProgramacao().withMonth(mesProgramacao).with(TemporalAdjusters.lastDayOfMonth());
			LocalDate primeiraDataMes = programacaoEntregaDto.getMesAnoProgramacao().withMonth(mesProgramacao).with(TemporalAdjusters.firstDayOfMonth());
			//Atribuição ao Dto
			programacaoEntregaDto.setPrimeiraDataMes(primeiraDataMes);
			programacaoEntregaDto.setUtlimaDataMes(utlimaDataMes);
			
			//Valida os campos do formulário e recupera valores
			validaFormularioEobtemValores(programacaoEntregaDto);
			
			
			//Salva registros.
			List<ProgramacaoEntrega> programacaoEntregaMes = salvar(programacaoEntregaDto,diasProgramacaoMes);
			
			
			return programacaoEntregaMes;
		}
		
		public List<ProgramacaoEntrega> salvar(ProgramacaoEntregaDto programacaoEntregaDto, Integer diasProgramacaoMes){
			
			List<ProgramacaoEntrega> listaProgramacaoEntregaMes = new ArrayList();
			Calendario calendario = new Calendario();
			LocalDate dataProgramacao = null;
			Long contFerias = 0L; 			
			Long contSusElegibilidade = 0L;
			
			
			//Verifica variável boolean que é utilizada na classe utilsisben, no método calculaDiasMes.
			if (mesCorrente == false) {
			   dataProgramacao = LocalDate.parse(programacaoEntregaDto.getMesAnoProgramacao().toString());
			} else {
			   dataProgramacao = LocalDate.parse(programacaoEntregaDto.getDataAtual().toString());
			}
			
			//Separa ano e mês da programação.
			Integer intMesProg = dataProgramacao.getMonthValue();
			Integer intAnoProg = dataProgramacao.getYear();
			
			
			String strMesProg = intMesProg.toString();
			if (strMesProg.length()== 1) {
				strMesProg = "0" + strMesProg;
			}
			String strAnoMesProg = intAnoProg.toString() + strMesProg ;
			
			//Recupera o setor do funcionário.#
			VwSisbenFuncionario funcionario = vwSisbenFuncionarioService.ObterPorMatricula(programacaoEntregaDto.getMatriculaColaborador()).get();
			
			
			//dataSolicitacaoDateTime = LocalDateTime.parse(programacaoEntregaDto.getDataAtual().toString());
			
			UtilSisben utilSisben = new UtilSisben();
			
			
			for (int i = 1; i <= diasProgramacaoMes; i ++){
				
				ProgramacaoEntrega programacaoInput = new ProgramacaoEntrega();
				
				
				
				programacaoInput.setMatriculaColaborador(programacaoEntregaDto.getMatriculaColaborador());
				programacaoInput.setUaPrevista(programacaoEntregaDto.getUaPrevista());
				programacaoInput.setUaRealizada(null);
				programacaoInput.setIdUa(programacaoEntregaDto.getIdUa());
			    programacaoInput.setIdData(null);
				programacaoInput.setIdUsuario(programacaoEntregaDto.getIdUsuario());
				programacaoInput.setIdValor(programacaoEntregaDto.getIdValor());
				programacaoInput.setDataEntrega(null);
				programacaoInput.setDataSolicitacao(programacaoEntregaDto.getDataAtual());
				programacaoInput.setSolicExtra(false);
				programacaoInput.setStAprov(null);
				programacaoInput.setDataUltimaModificacao(LocalDateTime.now());
				programacaoInput.setIdUsuarioUltimaModificacao(programacaoEntregaDto.getIdUsuarioUltimaModificacao());
				programacaoInput.setDataProgramacao(dataProgramacao);
				programacaoInput.setDiaDaSemana(utilSisben.getDiaDaSemana(dataProgramacao));
				programacaoInput.setAnoMes(strAnoMesProg);
				programacaoInput.setCodSetor(funcionario.getCodSecao());
				
				//Pesquisa existência de data especial.
				calendario = calendarioService.pesquisarPorData(dataProgramacao);
				programacaoInput.setDescricaoFeriado(null);
				if (calendario != null) {
					programacaoInput.setDescricaoFeriado(calendario.getDescricao());
				};
				
				//Opção por este tipo de chamada para não trazer muitos objetos para a memória.
				//Pesquisa se existe suspensão da eligibilidade para o ano e mês.
				contSusElegibilidade = 0L;
				contSusElegibilidade = suspensaoElegibilidadeService.
					pesquisarSuspensao(dataProgramacao, programacaoEntregaDto.getMatriculaColaborador());
				
//				programacaoInput.setExigSuspensa(false);
//				if ((contSusElegibilidade > 0) && (contSusElegibilidade != null)) {
//					programacaoInput.setExigSuspensa(true);
//				}
				
				
				//Pesquisa se existem férias.
				contFerias = 0L;
				contFerias = vwSisbenFeriasElegivel.pesquisarFeriasElegivel(dataProgramacao, programacaoEntregaDto.getMatriculaColaborador());
				
//				programacaoInput.setStFerias(false);
//				if ((contFerias > 0) && (contFerias != null)) {
//					programacaoInput.setStFerias(true);
//				}
				
				//Só insere se não existirem férias nem suspensão da eligibilidade.
				if ((contSusElegibilidade == 0)&&(contFerias == 0)) {
					listaProgramacaoEntregaMes.add(programacaoInput);
					this.programacaoEntregaRepository.save(programacaoInput);
				}	
				
				dataProgramacao = dataProgramacao.plusDays(1);
				
			}
			
			return listaProgramacaoEntregaMes;
		}

		@Override
		public long pesquisaValorMarmita(String mesAnoProgramacao, String matriculaColaborador ) {
			return programacaoEntregaRepository.
				   pesquisaProgramacaoEntregaAnoMesMatricula(mesAnoProgramacao, matriculaColaborador);
		}
		
		public void validaFormularioEobtemValores(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			boolean existeValor = false;
			
			//Pesquisa unidade acadêmcia, a partir do bairro da seção do elegível.
			UnidadeAcademica unidadeAcademica = unidadeAcademicaService.
			pesquisarPrimeiroPorDescricao(programacaoEntregaDto.getBairroSecaoElegivel());
			
			if ((unidadeAcademica == null)||(unidadeAcademica.getDescricao() == null) ||(unidadeAcademica.getDescricao() == "")) {
				throw new GlobalExceptionHandler("Elegível sem o bairro da seção!");
			}
			
			//Preenche a descrição e o id da ua.
			programacaoEntregaDto.setIdUa(unidadeAcademica.getId());
			programacaoEntregaDto.setUaPrevista(unidadeAcademica.getDescricao());
			
//			//Pesquisa se existe valor marmita e recupera o mais atual
//			List<ValorMarmita> listaValorMarmita = valorMarmitaService.
//			pesquisarValorMaisAtual(programacaoEntregaDto.getMesAnoProgramacao());
//			
//			//Integer countVlVigencia = valorMarmitaService.obterValorVigenciaPorAnoMes(programacaoEntregaDto.getMesAnoProgramacao());
//			
			//Pesquisa se existe valor marmita e recupera o mais atual
			ValorMarmita valorMarmitaAtual = valorMarmitaService.
					pesquisarValorVigenciaAtual();
			
			
			
			if (valorMarmitaAtual != null) {
				
				
				Integer intMesPrimeiraData = programacaoEntregaDto.getPrimeiraDataMes().getMonthValue();
				Integer intAnoPrimeiraData = programacaoEntregaDto.getPrimeiraDataMes().getYear();
				Integer intDiaPrimeiraData = programacaoEntregaDto.getPrimeiraDataMes().getDayOfMonth();
				
				Integer intMesUltimaData = programacaoEntregaDto.getUtlimaDataMes().getMonthValue();
				Integer intAnoUltimaData = programacaoEntregaDto.getUtlimaDataMes().getYear();
				Integer intDiaUltimaData = programacaoEntregaDto.getUtlimaDataMes().getDayOfMonth();
				
				
				Integer intMesVlMarmita = valorMarmitaAtual.getDataInicial().getMonthValue();
				Integer intAnoVlMarmita = valorMarmitaAtual.getDataInicial().getYear();
				Integer intDiaVlMarmita = valorMarmitaAtual.getDataInicial().getDayOfMonth();
				
				
				String strDiaPrimeiraData = intDiaPrimeiraData.toString();
				if (strDiaPrimeiraData.length()== 1) {
					strDiaPrimeiraData = "0" + strDiaPrimeiraData;
				}
				String strMesPrimeiraData = intMesPrimeiraData.toString();
				if (strMesPrimeiraData.length()== 1) {
					strMesPrimeiraData = "0" + strMesPrimeiraData;
				}
				String strAnoPrimeiraData = intAnoPrimeiraData.toString();
				
				String strDtInvPrDta = strAnoPrimeiraData + strMesPrimeiraData + strDiaPrimeiraData; 
				
				Integer dataInvertidaPrDta = Integer.valueOf(strDtInvPrDta);
				
				
				String strMesUltimaData = intMesUltimaData.toString();
				if (strMesUltimaData.length()== 1) {
					strMesUltimaData = "0" + strMesUltimaData;
				}
				String strDiaUltimaData = intDiaUltimaData.toString();
				if (strDiaUltimaData.length()== 1) {
					strDiaUltimaData = "0" + strDiaUltimaData;
				}
				String strAnoUltimaData = intAnoUltimaData.toString();
				
                String strDtInvUlDta = strAnoUltimaData + strMesUltimaData + strDiaUltimaData; 
				
				Integer dataInvertidaUlDta = Integer.valueOf(strDtInvUlDta);
				
				
				
				
				String strMesVlMarmita = intMesVlMarmita.toString();
				if (strMesVlMarmita.length()== 1) {
					strMesVlMarmita = "0" + strMesVlMarmita;
				}
				String strDiaVlMarmita = intDiaVlMarmita.toString();
				if (strDiaVlMarmita.length()== 1) {
					strDiaVlMarmita = "0" + strDiaVlMarmita;
				}
				String strAnoVlMarmita = intAnoVlMarmita.toString();
				
                String strDtInvVlMar = strAnoVlMarmita + strMesVlMarmita + strDiaVlMarmita; 
				
				Integer dataInvertidaVlMar = Integer.valueOf(strDtInvVlMar);
				
				
				
				
				
				
				
				
				
				
				
			// if (dataInvertidaPrDta >= dataInvertidaVlMar || dataInvertidaUlDta <= dataInvertidaVlMar   ){
			 if (dataInvertidaUlDta >= dataInvertidaVlMar ){	 
//			 if ((dataInvertidaUlDta.compareTo(dataInvertidaVlMar) > 0 )||
//				 (dataInvertidaUlDta.compareTo(dataInvertidaVlMar) == 0 ) ){
				 //Verificar quando o mês for fracionado na vigência
				 
				 //Integer.compare(x, y)	 
					
					  programacaoEntregaDto.setIdValor(valorMarmitaAtual.getId());
					  existeValor = true; 
					
				} else {
				
				ValorMarmita valorMarmitaAnterior = valorMarmitaService.
						pesquisarValorVigencia(programacaoEntregaDto.getPrimeiraDataMes(), programacaoEntregaDto.getUtlimaDataMes());
				
				 if ( valorMarmitaAnterior != null) {
				     programacaoEntregaDto.setIdValor(valorMarmitaAnterior.getId()); 				
				     existeValor = true; 
				 }
				 
			  }	 
				
			}
			
			ValorMarmita valorMarmita = valorMarmitaService.obterValorVigencia(programacaoEntregaDto.getMesAnoProgramacao());
			
			if (valorMarmita != null) {
				programacaoEntregaDto.setIdValor(valorMarmita.getId()); 				
			    existeValor = true; 
			}
			
			
			//listaValorMarmita = null;
			if (!existeValor) {
				throw new GlobalExceptionHandler("Não existe vigência de valor"
						+ " da marmita cadastrado para o período!");
			}
			
			
			
			
			
//			ValorMarmita valorMarmita = valorMarmitaService.obterValorVigencia(programacaoEntregaDto.getMesAnoProgramacao());
			
//			//listaValorMarmita = null;
//			if ((listaValorMarmita == null)||(listaValorMarmita.get(0) == null)) {
//				throw new GlobalExceptionHandler("Não existe valor marmita cadastrado para o ano e mês solicitado!");
//			}
			
			//Preenche o id do valor.
//			//programacaoEntregaDto.setIdValor(listaValorMarmita.get(0).getId());
			//programacaoEntregaDto.setIdValor(valorMarmita.getId());
			
			//Gera ano e mês correntes.
			int mesAtual = LocalDate.now().getMonthValue();
			int anoAtual = LocalDate.now().getYear();
			
			//Recupera ano e mês informados.
			int mesProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getMonthValue();
			int anoProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getYear();
			
			//Verifica se ano informado é menor que ano corrente.
			if (anoProgramacao < anoAtual ) {
				throw new GlobalExceptionHandler("Ano da programação deve ser maior ou igual ao ano corrente!");
			}
			
			if (anoProgramacao == anoAtual ) {
				//Verifica se mês informado é menor que mês corrente.
				if (mesProgramacao < mesAtual ) {
					throw new GlobalExceptionHandler("Mês da programação deve ser maior ou igual ao mês corrente!");
				}
			}
			
			
			//Recupera dados dos elegíveis na visão.
			Optional<VwSisbenElegibilidade> VwSisbenElegibilidade = vwSisbenElegibilidadeService.
			ObterPorMatricula(programacaoEntregaDto.getMatriculaColaborador());
			
			//Verifica se funcionário é elegível.
			if (!VwSisbenElegibilidade.isPresent()) {
				throw new GlobalExceptionHandler("Funcionário não elegível!");
			}
			
			//Verifica se para aquele mês, ano e matrícula já existe programação.
			Long contExisteProgramacao = programacaoEntregaRepository.
					    pesquisaProgramacaoEntregaAnoMesMatricula(programacaoEntregaDto.
					    		                         getMesAnoProgramacao().toString(),
					    		                         programacaoEntregaDto.getMatriculaColaborador());
			
			if (contExisteProgramacao > 0) {
				throw new GlobalExceptionHandler("Já existe programação para esta matrícula neste ano e mês !");
			}
			
		}

		@Override
		public List<ProgramacaoEntrega> alterarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto) {
			 List<ProgramacaoEntrega> listaProgramacaoEntrega = concatenaCamposTabelaProg(programacaoEntregaDto);
			 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
				  //Se existirem férias, ou suspensão da eligibilidade, apaga o registro.
				  if ((programacaoEntregaLinha.getStFerias() == true)||(programacaoEntregaLinha.getExigSuspensa() == true)) {
					this.programacaoEntregaRepository.deleteById(programacaoEntregaLinha.getId());
				  } else {
					this.programacaoEntregaRepository.save(programacaoEntregaLinha); 
				  }	
			 }
			 
			 return listaProgramacaoEntrega;
		}
		
		@SuppressWarnings("removal")
		public List<ProgramacaoEntrega> concatenaCamposTabelaProg(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			List<ProgramacaoEntrega> listaProgramacaoEntrega = new ArrayList<>();
			
			//Recupera as Strings preenchidas a partir dos campos das linhas das programações entrega.
			String[] tabelaProgramacaoEntrega = programacaoEntregaDto.getTabelaProgramacaoEntrega().split(",");
			String[] linha		= null;
			Long idProgramacao = null;
			String matriculaColaborador = null;
			String uaPrevista = null;
			String uaRealizada = null;
			LocalDate dataEntrega = null;
			LocalDate dataSolicitacao = null;
			LocalDate dataProgramacao = null;
			Long idUa = null;
			Long idUsuarioEntrega = null;
			Long idUsuarioUltimaModificacao = null;
			Long idValor = null;
			Boolean solicExtra = null;
			String diaDaSemana = null;
			LocalDateTime dataModificacao = LocalDateTime.now();
			
			//
			//Pesquisa se existe valor marmita e recupera o mais atual
			ValorMarmita valorMarmitaAtual = valorMarmitaService.
					pesquisarValorVigenciaAtual();
			//
			
			Calendario calendario = new Calendario();
			
			UtilSisben utilSisben = new UtilSisben();
			
			
			
			for (String linhaTabProgEntrega : tabelaProgramacaoEntrega) 
				{
				//"11999=3839=CABULA=CABULA=10022=4=8=62=2023-02-09=2023-06-01=2023-02-09=Quinta-Feira"
				
				//Separando a linha da tabela.
				linha 			= linhaTabProgEntrega.split("=");
				
				    //Separando os campos da linha.
					//idProgramacao = new Long(linha[0]);
					idProgramacao = Long.valueOf(linha[0]);
					matriculaColaborador = linha[1];
					uaPrevista = linha[2];
					uaRealizada = linha[3];
					//idUa = new Long(linha[4]);
					idUa = Long.valueOf(linha[4]);
					//idUsuarioEntrega = new Long(linha[5]);
					idUsuarioEntrega = Long.valueOf(linha[5]);
					//idValor = new Long(linha[6]);
					idValor = Long.valueOf(linha[6]);
					idUsuarioUltimaModificacao = Long.valueOf(linha[7]);
					dataEntrega = LocalDate.parse(linha[8]);
					dataProgramacao = LocalDate.parse(linha[9]);
					dataSolicitacao = LocalDate.parse(linha[10]);
                    diaDaSemana = new String(linha[11]).trim();
                    solicExtra = Boolean.parseBoolean(linha[12]);
                    
                    
                  	
				
				ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega();
				
				
				programacaoEntrega.setId(idProgramacao);
				programacaoEntrega.setMatriculaColaborador(matriculaColaborador);
				programacaoEntrega.setUaPrevista(uaPrevista);
				programacaoEntrega.setUaRealizada(uaRealizada);
				programacaoEntrega.setDataEntrega(dataEntrega);
				programacaoEntrega.setDataSolicitacao(dataSolicitacao);
				programacaoEntrega.setIdUsuario(idUsuarioEntrega);
				programacaoEntrega.setIdUa(idUa);
				//programacaoEntrega.setIdValor(idValor);
				programacaoEntrega.setDataUltimaModificacao(dataModificacao);
				programacaoEntrega.setIdUsuarioUltimaModificacao(idUsuarioUltimaModificacao);
				programacaoEntrega.setDataProgramacao(dataProgramacao);
				programacaoEntrega.setDiaDaSemana(diaDaSemana);
				
				// trecho repetido. colocar em uma função.
				
				  //
                
                
                if (valorMarmitaAtual != null) {
    				
    				
    				Integer intMesDataProg = dataProgramacao.getMonthValue();
    				Integer intAnoDataProg = dataProgramacao.getYear();
    				Integer intDiaDataProg = dataProgramacao.getDayOfMonth();
    				
    				Integer intMesVlMarmita = valorMarmitaAtual.getDataInicial().getMonthValue();
    				Integer intAnoVlMarmita = valorMarmitaAtual.getDataInicial().getYear();
    				Integer intDiaVlMarmita = valorMarmitaAtual.getDataInicial().getDayOfMonth();
    				
    				
    				String strDiaDataProg = intDiaDataProg.toString();
    				if (strDiaDataProg.length()== 1) {
    					strDiaDataProg = "0" + strDiaDataProg;
    				}
    				String strMesDataProg = intMesDataProg.toString();
    				if (strMesDataProg.length()== 1) {
    					strMesDataProg = "0" + strMesDataProg;
    				}
    				String strAnoDataProg = intAnoDataProg.toString();
    				
    				String strDtInvDtaProg = strAnoDataProg + strMesDataProg + strDiaDataProg; 
    				
    				Integer dataInvertidaDtaProg = Integer.valueOf(strDtInvDtaProg);
    				
    				
    				String strMesVlMarmita = intMesVlMarmita.toString();
    				if (strMesVlMarmita.length()== 1) {
    					strMesVlMarmita = "0" + strMesVlMarmita;
    				}
    				String strDiaVlMarmita = intDiaVlMarmita.toString();
    				if (strDiaVlMarmita.length()== 1) {
    					strDiaVlMarmita = "0" + strDiaVlMarmita;
    				}
    				String strAnoVlMarmita = intAnoVlMarmita.toString();
    				
                    String strDtInvVlMar = strAnoVlMarmita + strMesVlMarmita + strDiaVlMarmita; 
    				
    				Integer dataInvertidaVlMar = Integer.valueOf(strDtInvVlMar);
    				
    			 if (dataInvertidaDtaProg >= dataInvertidaVlMar ){	 
    					
    					  programacaoEntrega.setIdValor(valorMarmitaAtual.getId());
    					
    				} else {
    				
    				ValorMarmita valorMarmitaAnterior = valorMarmitaService.
    						pesquisarValorVigencia(dataProgramacao, dataProgramacao);
    				
    				 if ( valorMarmitaAnterior != null) {
    				     programacaoEntrega.setIdValor(valorMarmitaAnterior.getId()); 				
    				      
    				 }
    				 
    			  }	 
    				
    			}	
				
//				Calendario calendario = new Calendario();
				
				//Pesquisa existência de data especial.
				calendario = calendarioService.pesquisarPorData(dataProgramacao);
				
				programacaoEntrega.setDescricaoFeriado(null);
				if (calendario != null) {
					programacaoEntrega.setDescricaoFeriado(calendario.getDescricao());
				};
				
				//Opção por este tipo de chamada para não trazer muitos objetos para a memória.
				//Pesquisa se existe suspensão da eligibilidade para o ano e mês.
				Long contSusElegibilidade = suspensaoElegibilidadeService.
					pesquisarSuspensao(dataProgramacao, programacaoEntrega.getMatriculaColaborador());
				
				programacaoEntrega.setExigSuspensa(false);
				if ((contSusElegibilidade > 0) && (contSusElegibilidade != null)) {
					programacaoEntrega.setExigSuspensa(true);
				}
				
				
				//Pesquisa se existem férias.
				Long contFerias = vwSisbenFeriasElegivel.pesquisarFeriasElegivel(dataProgramacao, programacaoEntrega.getMatriculaColaborador());
				
				programacaoEntrega.setStFerias(false);
				if ((contFerias > 0) && (contFerias != null)) {
					programacaoEntrega.setStFerias(true);
				}
				
				//manutenção %
				//Long horas = utilSisben.diferencaEntreDatas();
				
				
				
				listaProgramacaoEntrega.add(programacaoEntrega);
				
				
				
				
			}
			return listaProgramacaoEntrega;
		}

		//?
		@Override
		public List<ProgramacaoEntrega> listaProgramacaoEntregaAnoMesMatricula(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			
             List<ProgramacaoEntrega> listaProgramacaoEntrega = programacaoEntregaRepository.
 					listaProgramacaoEntregaAnoMesMatricula(programacaoEntregaDto.getMesAnoProgramacao(),programacaoEntregaDto.getMatriculaColaborador());
	    	
	    	
	    	for (ProgramacaoEntrega programacaoEntrega : listaProgramacaoEntrega) { 
	    		 
	    		 VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(programacaoEntrega.getCodSetor());
	    		 programacaoEntrega.setDescrSetor(vwSisbenSetor.getDescrSetor());
	    		 
	    		 VwSisbenFuncionario   funcionario = vwSisbenFuncionarioService.ObterPorMatricula(programacaoEntrega.getMatriculaColaborador()).get();
	    		 programacaoEntrega.setNomeFuncionario(funcionario.getNomeFuncionario());
	    		 
				    
			}
	    	
	    	if (listaProgramacaoEntrega.isEmpty() ) {
	    		
	    		
	    		 Integer intMesProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getMonthValue();
				 Integer intAnoProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getYear();
				
				
				 String strMesProgramacao = intMesProgramacao.toString();
				 if (strMesProgramacao.length()== 1) {
					 strMesProgramacao = "0" + strMesProgramacao;
				 }
				 
				 String strMesAnoProgramcao = intAnoProgramacao.toString() + strMesProgramacao;
				 programacaoEntregaDto.setAnoMes(strMesAnoProgramcao);
	    		 
	    		 VwSisbenFuncionario   funcionario = vwSisbenFuncionarioService.ObterPorMatricula(programacaoEntregaDto.getMatriculaColaborador()).get();
	    		 programacaoEntregaDto.setNomeFuncionario(funcionario.getNomeFuncionario());
	    		 programacaoEntregaDto.setCodSetor(funcionario.getCodSecao());
	    		 
	    		 VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(funcionario.getCodSecao());
	    		 programacaoEntregaDto.setDescrSetor(vwSisbenSetor.getDescrSetor());
	    		 
	    		 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
	    		 
	    		 
	    		 listaProgramacaoEntrega.add(programacaoEntrega);
	    		
	    		
	    	}
			
			
			return listaProgramacaoEntrega; 
	    	
//			return programacaoEntregaRepository.
//					listaProgramacaoEntregaAnoMesMatricula(programacaoEntregaDto.getMesAnoProgramacao(),programacaoEntregaDto.getMatriculaColaborador());
		}

		@Override
		public void apagarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			String[] tabelaProgramacaoEntrega = programacaoEntregaDto.getTabelaProgramacaoEntrega().split(",");
			
			for (String idProgramacao : tabelaProgramacaoEntrega){
			    programacaoEntregaRepository.deleteById(Long.valueOf(idProgramacao));
		   }
	   }

		@Override
		public ProgramacaoEntrega registrarEntrega(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			 validaFormRegistroEntrega(programacaoEntregaDto);
			
			
			 ProgramacaoEntrega programacaoEntrega =  programacaoEntregaRepository.findById(programacaoEntregaDto.getId()).get();
			 LocalDateTime dataModificacao = LocalDateTime.now();
			 programacaoEntregaDto.setDataUltimaModificacao(dataModificacao);
			 programacaoEntrega.setDataEntrega(programacaoEntregaDto.getDataEntrega());
			 programacaoEntrega.setUaRealizada(programacaoEntregaDto.getUaRealizada());
			 programacaoEntrega.setIdUa(programacaoEntregaDto.getIdUa());
			 
			 if((programacaoEntregaDto.getIdJustificativa() != null)) {
				 programacaoEntrega.setIdJustificativa(programacaoEntregaDto.getIdJustificativa());
			 }
			 
			 return programacaoEntregaRepository.save(programacaoEntrega);
		}
		
        public void validaFormRegistroEntrega(ProgramacaoEntregaDto programacaoEntregaDto) {
        	
        	Integer diferencadias = 0;
        	if (programacaoEntregaDto.getDataProgramacao() != null){ 
        	    diferencadias = (int) ChronoUnit.DAYS.between(programacaoEntregaDto.getDataProgramacao(), programacaoEntregaDto.getDataEntrega() );
        	   if ((diferencadias != 0)) {
        		   throw new GlobalExceptionHandler("Data da entrega diferente da data da programação!");
        	   }
        	}
        	
        	
//
        	   
//        	int diaAtual = LocalDate.now().getDayOfMonth();
//        	int diaProgramacao = programacaoEntregaDto.getDataEntrega().getDayOfMonth();
//        	
//        	if (diferencadias > 3) {
//        		throw new GlobalExceptionHandler("Data programação retroativa não pode ser maior que 3 dias!");
//        	}
			
			//Gera ano e mês correntes.
			int mesAtual = LocalDate.now().getMonthValue();
			int anoAtual = LocalDate.now().getYear();
			
			//Recupera ano e mês informados.
			int mesProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getMonthValue();
			int anoProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getYear();
			
			//Verifica se ano informado é menor que ano corrente.
			if (anoProgramacao < anoAtual ) {
				throw new GlobalExceptionHandler("Ano da programação deve ser maior ou igual ao ano corrente!");
			}
			
			//Verifica se mês informado é menor que mês corrente.
			if (mesProgramacao < mesAtual ) {
				throw new GlobalExceptionHandler("Mês da programação deve ser maior ou igual ao mês corrente!");
			}
			
     }

		@Override
		public List<ProgramacaoEntrega> listarProgramacaoEntrega() {
			
			List<ProgramacaoEntrega> listProgEntrega = new ArrayList();
			
			listProgEntrega = programacaoEntregaRepository.listarProgramacaoEntrega();
			
			VwSisbenFuncionario funcionario = null;  
			
			for (ProgramacaoEntrega ProgEntrega : listProgEntrega) {
				
			    ProgEntrega.getMatriculaColaborador();
			    
			    funcionario = vwSisbenFuncionarioService.ObterPorMatricula(ProgEntrega.getMatriculaColaborador()).get();
			    ProgEntrega.setNomeFuncionario(funcionario.getNomeFuncionario());
			    
			}
			
			return listProgEntrega;
			
		}

		@Override
		public List<ProgramacaoEntrega> listarComFiltros(
				ProgramacaoEntregaSpecification programacaoEntregaSpecification) {
			
			///
			
			List<ProgramacaoEntrega> listProgEntrega = new ArrayList();
			
			VwSisbenFuncionario funcionario = null; 
			
			listProgEntrega = programacaoEntregaRepository.findAll(programacaoEntregaSpecification.toSpec());
			
            for (ProgramacaoEntrega ProgEntrega : listProgEntrega) {
				
			    funcionario = vwSisbenFuncionarioService.ObterPorMatricula(ProgEntrega.getMatriculaColaborador()).get();
			    ProgEntrega.setNomeFuncionario(funcionario.getNomeFuncionario());
			    
			}
			 
			
			///
			
			return listProgEntrega;
			
			
			
			//return this.programacaoEntregaRepository.findAll(programacaoEntregaSpecification.toSpec());
		}
		
		public Long retornaIdValorMarmita(LocalDate primeiraDataMes,  LocalDate ultimaDataMes) {
			
			Boolean existeValor = false; 
			
			
			//Pesquisa se existe valor marmita e recupera o mais atual
			ValorMarmita valorMarmitaAtual = valorMarmitaService.
					pesquisarValorVigenciaAtual();
			
			
			Long idValorMarmita = 0L;
			
			
			
			if (valorMarmitaAtual != null) {
				
				
				Integer intMesPrimeiraData = primeiraDataMes.getMonthValue();
				Integer intAnoPrimeiraData = primeiraDataMes.getYear();
				Integer intDiaPrimeiraData = primeiraDataMes.getDayOfMonth();
				
				Integer intMesUltimaData = ultimaDataMes.getMonthValue();
				Integer intAnoUltimaData = ultimaDataMes.getYear();
				Integer intDiaUltimaData = ultimaDataMes.getDayOfMonth();
				
				
				Integer intMesVlMarmita = valorMarmitaAtual.getDataInicial().getMonthValue();
				Integer intAnoVlMarmita = valorMarmitaAtual.getDataInicial().getYear();
				Integer intDiaVlMarmita = valorMarmitaAtual.getDataInicial().getDayOfMonth();
				
				
				String strDiaPrimeiraData = intDiaPrimeiraData.toString();
				if (strDiaPrimeiraData.length()== 1) {
					strDiaPrimeiraData = "0" + strDiaPrimeiraData;
				}
				String strMesPrimeiraData = intMesPrimeiraData.toString();
				if (strMesPrimeiraData.length()== 1) {
					strMesPrimeiraData = "0" + strMesPrimeiraData;
				}
				String strAnoPrimeiraData = intAnoPrimeiraData.toString();
				
				String strDtInvPrDta = strAnoPrimeiraData + strMesPrimeiraData + strDiaPrimeiraData; 
				
				Integer dataInvertidaPrDta = Integer.valueOf(strDtInvPrDta);
				
				
				String strMesUltimaData = intMesUltimaData.toString();
				if (strMesUltimaData.length()== 1) {
					strMesUltimaData = "0" + strMesUltimaData;
				}
				String strDiaUltimaData = intDiaUltimaData.toString();
				if (strDiaUltimaData.length()== 1) {
					strDiaUltimaData = "0" + strDiaUltimaData;
				}
				String strAnoUltimaData = intAnoUltimaData.toString();
				
                String strDtInvUlDta = strAnoUltimaData + strMesUltimaData + strDiaUltimaData; 
				
				Integer dataInvertidaUlDta = Integer.valueOf(strDtInvUlDta);
				
				
				
				
				String strMesVlMarmita = intMesVlMarmita.toString();
				if (strMesVlMarmita.length()== 1) {
					strMesVlMarmita = "0" + strMesVlMarmita;
				}
				String strDiaVlMarmita = intDiaVlMarmita.toString();
				if (strDiaVlMarmita.length()== 1) {
					strDiaVlMarmita = "0" + strDiaVlMarmita;
				}
				String strAnoVlMarmita = intAnoVlMarmita.toString();
				
                String strDtInvVlMar = strAnoVlMarmita + strMesVlMarmita + strDiaVlMarmita; 
				
				Integer dataInvertidaVlMar = Integer.valueOf(strDtInvVlMar);
				
				
				
				
			 if (dataInvertidaUlDta >= dataInvertidaVlMar ){	 
					  idValorMarmita = valorMarmitaAtual.getId();
					  
					  existeValor = true; 
					
				} else {
				
				ValorMarmita valorMarmitaAnterior = valorMarmitaService.
						pesquisarValorVigencia(primeiraDataMes, ultimaDataMes);
				
				 if ( valorMarmitaAnterior != null) {
					 idValorMarmita = valorMarmitaAnterior.getId(); 				
				     existeValor = true; 
				 }
				 
			  }	 
				
			}
			
			//listaValorMarmita = null;
			if (!existeValor) {
				throw new GlobalExceptionHandler("Não existe vigência de valor"
						+ " da marmita cadastrado para o período!");
			}
			
			
			
			return idValorMarmita;
		}

		@Override
		public List<ProgramacaoEntrega> listarRegistroEntregaPorUsuario(ProgramacaoEntregaSpecification programacaoEntregaSpecification) {
			
            List<ProgramacaoEntrega> listProgEntrega = new ArrayList();
            List<ProgramacaoEntrega> listRegistroEntregaUsuario = new ArrayList();
			
			VwSisbenFuncionario funcionario = null; 
			
			listProgEntrega = programacaoEntregaRepository.findAll(programacaoEntregaSpecification.toSpec());
			
			
            for (ProgramacaoEntrega ProgEntrega : listProgEntrega) {
				
//			    funcionario = vwSisbenFuncionarioService.ObterPorMatricula(ProgEntrega.getMatriculaColaborador()).get();
//			    ProgEntrega.setNomeFuncionario(funcionario.getNomeFuncionario());
//			   
			    listRegistroEntregaUsuario.add(ProgEntrega);
			    
			}
			 
			
			///
			
			return listProgEntrega;
		}

		@Override
		public List<ProgEntVigenteResponse> listarProgramacaoEntregaVigente(
				String matriculaColaborador, String anoMes, String codSetor) {
			

	    	 if ((matriculaColaborador == "")||((matriculaColaborador == null))) {
	    		 matriculaColaborador = null;
			 }
	    	 
	    	 if ((anoMes == "")||((anoMes == null))) {
	    		 anoMes = null;
			 }
	    	 
	    	 if ((codSetor == "")||((codSetor == null))) {
	    		 codSetor = null;
			 }
	    	 
	    	 
	    	 
	    	 List<ProgEntVigenteDto> listarProgEntVigenteDto = programacaoEntregaRepository.
						listarProgramacaoEntregaVigente(matriculaColaborador,anoMes,codSetor);
	    	 
	    	 List<ProgEntVigenteResponse> listarProgEntVigenteResponse = new ArrayList(); 
	    	 
	    	
	    	 
	    	for (ProgEntVigenteDto progEntrega : listarProgEntVigenteDto) {
					
	    		 
	    		 ProgEntVigenteResponse progEntVigenteResponse = new ProgEntVigenteResponse();
	    		 
	    		 VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(progEntrega.getCodSetor());
	    		 progEntVigenteResponse.setDescrSetor(vwSisbenSetor.getDescrSetor());
	    		 
	    		 VwSisbenFuncionario   funcionario = vwSisbenFuncionarioService.ObterPorMatricula(progEntrega.getMatriculaColaborador()).get();
	    		 progEntVigenteResponse.setNomeColaborador(funcionario.getNomeFuncionario());
	    		 
	    		 progEntVigenteResponse.setAnoMes(progEntrega.getAnoMes());
	    		 progEntVigenteResponse.setMatriculaColaborador(progEntrega.getMatriculaColaborador());
	    		 progEntVigenteResponse.setCodSetor(progEntrega.getCodSetor());
	    		 progEntVigenteResponse.setStatus("Programado");
				   
	    		 listarProgEntVigenteResponse.add(progEntVigenteResponse);
				    
			}
	    	
	    	List<ProgEntVigenteNpDto> listarProgEntVigenteDtoNaoProg = programacaoEntregaRepository.listarProgramacaoEntregaVigenteNaoProgramado(matriculaColaborador, codSetor, anoMes);
	    	
	    	
	    	for (ProgEntVigenteNpDto progEntregaNp : listarProgEntVigenteDtoNaoProg) {
				
	    		 
	    		 ProgEntVigenteResponse progEntVigenteResponse = new ProgEntVigenteResponse();
	    		 
	    		 progEntVigenteResponse.setDescrSetor(progEntregaNp.getDescrSetor());
	    		 progEntVigenteResponse.setMatriculaColaborador(progEntregaNp.getMatriculaColaborador());
	    		 progEntVigenteResponse.setCodSetor(progEntregaNp.getCodSetor());
	    		 progEntVigenteResponse.setNomeColaborador(progEntregaNp.getNomeColaborador());
	    		 progEntVigenteResponse.setAnoMes(anoMes);
	    		 progEntVigenteResponse.setStatus("Não Programado");
				   
	    		 listarProgEntVigenteResponse.add(progEntVigenteResponse);
				    
			}
	    	 
			
			
			return listarProgEntVigenteResponse;
		}

		@Override
		public ProgramacaoEntrega obterPorId2(Long id) {
			
			ProgramacaoEntrega programacaoEntrega = programacaoEntregaRepository.findById(id).get();	    	
	    		 
	    	VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(programacaoEntrega.getCodSetor());
	    	programacaoEntrega.setDescrSetor(vwSisbenSetor.getDescrSetor());
	    		 
	    	VwSisbenFuncionario   funcionario = vwSisbenFuncionarioService.ObterPorMatricula(programacaoEntrega.getMatriculaColaborador()).get();
	    	programacaoEntrega.setNomeFuncionario(funcionario.getNomeFuncionario());
	    	
	    	return programacaoEntrega;
	    	
		}

		@Override
		public List<String> listarAnoMes() {
			return programacaoEntregaRepository.listarAnoMes();
		}

		@Override
		public List<ProgEntVigenteResponse> listarProgramacaoEntregaVigenteLiderSetor(String matriculaColaborador,
				String anoMes,String codSetor, String idUsuarioLogado) {
			
			if ((matriculaColaborador == "")||((matriculaColaborador == null))) {
	    		 matriculaColaborador = null;
			 }
	    	 
	    	 if ((anoMes == "")||((anoMes == null))) {
	    		 anoMes = null;
			 }
	    	 
	    	 if ((codSetor == "")||((codSetor == null))) {
	    		 codSetor = null;
			 }
	    	 
	    	 
	    	 List<String> listStrCodSetor = usuarioSetorGerenciadoService.
	    		     concatenaSetoresLider(idUsuarioLogado);
	    	  
		    	 List<ProgEntVigenteDto> listarProgEntVigenteDto = programacaoEntregaRepository.
							listarProgramacaoEntregaVigenteLiderSetor(matriculaColaborador,anoMes,codSetor,listStrCodSetor);
		    	 
//		    	 List<ProgEntVigenteDto> listarProgEntVigenteDto = programacaoEntregaRepository.
//							listarProgramacaoEntregaVigenteLiderSetor(matriculaColaborador,anoMes,codSetor);
//		    	 
		    	 List<ProgEntVigenteResponse> listarProgEntVigenteResponse = new ArrayList();
		    	 
		    	 
		    	 
	    	
	    	 
	    	for (ProgEntVigenteDto progEntrega : listarProgEntVigenteDto) {
					
	    		 
	    		 ProgEntVigenteResponse progEntVigenteResponse = new ProgEntVigenteResponse();
	    		 
	    		 VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(progEntrega.getCodSetor());
	    		 progEntVigenteResponse.setDescrSetor(vwSisbenSetor.getDescrSetor());
	    		 
	    		 VwSisbenFuncionario   funcionario = vwSisbenFuncionarioService.ObterPorMatricula(progEntrega.getMatriculaColaborador()).get();
	    		 progEntVigenteResponse.setNomeColaborador(funcionario.getNomeFuncionario());
	    		 
	    		 progEntVigenteResponse.setAnoMes(progEntrega.getAnoMes());
	    		 progEntVigenteResponse.setMatriculaColaborador(progEntrega.getMatriculaColaborador());
	    		 progEntVigenteResponse.setCodSetor(progEntrega.getCodSetor());
	    		 progEntVigenteResponse.setStatus("Programado");
				   
	    		 listarProgEntVigenteResponse.add(progEntVigenteResponse);
				    
			}
	    	
	    	List<ProgEntVigenteNpDto> listarProgEntVigenteDtoNaoProg = programacaoEntregaRepository.listarProgramacaoEntregaVigenteNaoProgramadoLiderSetor(matriculaColaborador,anoMes, codSetor, listStrCodSetor );
	    	
//	    	List<ProgEntVigenteNpDto> listarProgEntVigenteDtoNaoProg = programacaoEntregaRepository.listarProgramacaoEntregaVigenteNaoProgramadoLiderSetor(matriculaColaborador,anoMes, codSetor );
	    	
	    	
	    	for (ProgEntVigenteNpDto progEntregaNp : listarProgEntVigenteDtoNaoProg) {
				
	    		 
	    		 ProgEntVigenteResponse progEntVigenteResponse = new ProgEntVigenteResponse();
	    		 
	    		 progEntVigenteResponse.setDescrSetor(progEntregaNp.getDescrSetor());
	    		 progEntVigenteResponse.setMatriculaColaborador(progEntregaNp.getMatriculaColaborador());
	    		 progEntVigenteResponse.setCodSetor(progEntregaNp.getCodSetor());
	    		 progEntVigenteResponse.setNomeColaborador(progEntregaNp.getNomeColaborador());
	    		 progEntVigenteResponse.setAnoMes(anoMes);
	    		 progEntVigenteResponse.setStatus("Não Programado");
				   
	    		 listarProgEntVigenteResponse.add(progEntVigenteResponse);
				    
			}
	    	 
			
			
			return listarProgEntVigenteResponse;

			
		}
		
		
	
	
}