package com.bahiana.sisben.service.impl;

//import java.text.SimpleDateFormat;
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
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bahiana.sisben.api.dto.ProgEntVigenteDto;
import com.bahiana.sisben.api.dto.ProgEntVigenteNpDto;
import com.bahiana.sisben.api.dto.ProgEntVigenteResumoDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaAvulsaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.RegistroEntregaDto;
import com.bahiana.sisben.api.response.ProgEntVigenteResponse;
import com.bahiana.sisben.api.response.RegistroEntregaResponse;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.model.entity.Calendario;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.UnidadeAcademica;
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
	
//	@Autowired
//	ProgramacaoEntregaAprovacaoService programacaoEntregaAprovacaoService;
	
	
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
			
		 
		//Recupera data da programação para verificar se tem menos de 24h.
		LocalDate dataProgramacao = 
		programacaoEntregaRepository.pesquisarDataProgramacao(programacaoEntregaDto.getId());
           
		//Verifica se a solicitação tem menos de 24h
		String tipoOperacao = this.verificaProgramacaoMenos24h(dataProgramacao, "A");
			
			 
	
	    if (tipoOperacao == "") {	
			programacaoEntregaRepository.atulizaProgramacaoEntrega(
					programacaoEntregaDto.getUaRealizada(),
					programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
					LocalDateTime.now(),
					programacaoEntregaDto.getIdUa(),
					programacaoEntregaDto.getId(),
					 null);
	    } else {
	    	programacaoEntregaRepository.atulizaProgEntParaAprovar(
	    			programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
	    			LocalDateTime.now(), 
	    			programacaoEntregaDto.getId(),					
	    			programacaoEntregaDto.getIdUa(),
	    			programacaoEntregaDto.getIdJustificativa(),
					 "A");
	    	
	    }
		
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
			
			
			//Recupera dados dos elegíveis na visão.
			Optional<VwSisbenElegibilidade> VwSisbenElegibilidade = vwSisbenElegibilidadeService.
			ObterPorMatricula(programacaoEntregaAvulsaDto.getMatriculaColaborador());
			
			//Converte a data da admissão para localdate.
			LocalDate dataAdmissaoLocalDate =  VwSisbenElegibilidade.get().getDataAdmissao().toLocalDate();

            //Alteração 18.07.2023
			
			//int mesAtual = LocalDate.now().getMonthValue();
			//int anoAtual = LocalDate.now().getYear();
				
				//Recupera o ano da admissão.
				Integer anoAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getYear();
				//Integer mesAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getMonthValue();
				
				//Verifica se o ano atual é igual ao ano da admissão.
				//if (anoAdmissao == anoAtual ) {
					//Verifica se a data da programação é menor que data da programação.
					if(programacaoEntregaAvulsaDto.getDataProgramacao().isBefore(dataAdmissaoLocalDate)) {
						throw new GlobalExceptionHandler("Data da programação deve ser maior ou igual à data da admissão!");
					}
				//}
				
				
			// fim alteração
			
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
			
			//Converte de dto para o objeto bean.
			ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaAvulsaDto);
			
			//Recupera dia da semana
			UtilSisben utilSisben = new UtilSisben(); 
			programacaoEntrega.setDiaDaSemana(utilSisben.getDiaDaSemana(programacaoEntrega.getDataProgramacao()));	
			
			//Recupera setor do usuário.
			VwSisbenFuncionario vwSisbenFuncionario = vwSisbenFuncionarioService.
			ObterPorMatricula(programacaoEntrega.getMatriculaColaborador()).get();
			
			programacaoEntrega.setCodSetor(vwSisbenFuncionario.getCodSecao());
			
			//programacaoEntregaAvulsaDto.setIdValor(programacaoEntregaAvulsaDto.getIdValor());
			
			LocalDateTime dataModificacao = LocalDateTime.now();
			programacaoEntrega.setDataUltimaModificacao(dataModificacao);
			
			Integer intMesProgAvulsa = programacaoEntrega.getDataProgramacao().getMonthValue();
			Integer intAnoProgAvulsa = programacaoEntrega.getDataProgramacao().getYear();
				
			String strMesProgAvulsa = intMesProgAvulsa.toString();
			if (strMesProgAvulsa.length()== 1) {
				strMesProgAvulsa = "0" + strMesProgAvulsa;
			}
				
			String strAnoMesProgAvulsa = intAnoProgAvulsa.toString() + strMesProgAvulsa; 
				
			programacaoEntrega.setAnoMes(strAnoMesProgAvulsa);
			
			//Converte de dto para o objeto bean.
			//ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaAvulsaDto);
			
			Calendario calendario = new Calendario();
			
			
			//Pesquisa existência de data especial.
			calendario = calendarioService.pesquisarPorData(programacaoEntrega.getDataProgramacao());
			programacaoEntrega.setDescricaoFeriado(null);
			if (calendario != null) {
				programacaoEntrega.setDescricaoFeriado(calendario.getDescricao());
			};
			
			//Pesquisa se existe valor marmita e recupera.
			ValorMarmita valorMarmita = valorMarmitaService.
					obterValorVigencia(programacaoEntrega.getDataProgramacao());
			
			if ( valorMarmita == null) {
				throw new GlobalExceptionHandler("Não existe vigência de valor"
						+ " da marmita cadastrado para a data da programação informada!");
			}
			
			//Recupera e atribui o valor da marmita.
			programacaoEntrega.setIdValor(valorMarmita.getId());
			
			//Verifica se a solicitação tem menos de 24h
			String tipoOperacao = this.verificaProgramacaoMenos24h(programacaoEntrega.getDataProgramacao(), "I");
			programacaoEntrega.setTipoSolicitacao(tipoOperacao);
			
			//Ua realizada é igual a prevista neste momento.
			programacaoEntrega.setUaRealizada(programacaoEntrega.getUaPrevista());
			programacaoEntrega.setIdJustificativa(programacaoEntrega.getIdJustificativa());
			
			
			
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
			LocalDate dataAdmissaoLocalDate = null;
			//Gera ano e mês correntes.
			//int mesAtual = LocalDate.now().getMonthValue();
			int anoAtual = LocalDate.now().getYear();
			
			
			//Verifica variável boolean que é utilizada na classe utilsisben, no método calculaDiasMes.
			if (mesCorrente == false) {
			   dataProgramacao = LocalDate.parse(programacaoEntregaDto.getMesAnoProgramacao().toString());
			} else {
			   //Despreza a data corrente e a data posterior getDataAtual().plusDays(2) . 
			   dataProgramacao = LocalDate.parse(programacaoEntregaDto.getDataAtual().plusDays(2).toString());
			   //Diminui dois dias por conta do cálculo acima.
			   diasProgramacaoMes = diasProgramacaoMes - 2;
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
			
			//Recupera dados dos elegíveis na visão.
			Optional<VwSisbenElegibilidade> VwSisbenElegibilidade = vwSisbenElegibilidadeService.
			ObterPorMatricula(programacaoEntregaDto.getMatriculaColaborador());
			
			//Converte a data da admissão para localdate.
			dataAdmissaoLocalDate =  VwSisbenElegibilidade.get().getDataAdmissao().toLocalDate();
			
			
			//dataSolicitacaoDateTime = LocalDateTime.parse(programacaoEntregaDto.getDataAtual().toString());
			
			UtilSisben utilSisben = new UtilSisben();
			boolean incluirData ;
			
			
			for (int i = 1; i <= diasProgramacaoMes; i ++){
				
				ProgramacaoEntrega programacaoInput = new ProgramacaoEntrega();
				incluirData = true;
				
				
				
				programacaoInput.setMatriculaColaborador(programacaoEntregaDto.getMatriculaColaborador());
				programacaoInput.setUaPrevista(programacaoEntregaDto.getUaPrevista());
				programacaoInput.setUaRealizada(programacaoEntregaDto.getUaPrevista());
				programacaoInput.setIdUa(programacaoEntregaDto.getIdUa());
				//programacaoInput.setIdUa(null);
			    programacaoInput.setIdData(null);
				//programacaoInput.setIdUsuario(programacaoEntregaDto.getIdUsuario());
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
				
				
				//Verifica se é para incluir sábados e domingos.
				if (programacaoEntregaDto.getNaoGerarFDS()== true) {
					if (programacaoInput.getDiaDaSemana().equalsIgnoreCase("Sábado")||
							programacaoInput.getDiaDaSemana().equalsIgnoreCase("Domingo")) {
						incluirData = false;
					}
				}
				
				//Pesquisa existência de data especial.
				calendario = calendarioService.pesquisarPorData(dataProgramacao);
				programacaoInput.setDescricaoFeriado(null);
				if (calendario != null) {
					programacaoInput.setDescricaoFeriado(calendario.getDescricao());
					incluirData = false;
				};
				
				//Alteração 18.07.2023
				
				//Recupera o ano da admissão.
				//Integer anoAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getYear();
				//Integer mesAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getMonthValue();
				
				//Verifica se o ano atual é igual ao ano da admissão.
				//if (anoAdmissao == anoAtual ) {
					//Verifica se a data da programação é menor que data da programação.
					if(dataProgramacao.isBefore(dataAdmissaoLocalDate)) {
						incluirData = false;
					}
				//}
				
				
				// fim alteração
				
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
				
				//Só insere se não existirem férias nem suspensão da eligibilidade,
				//ou quando for escolhido não gerar sábados,domingos e feriados.
				if ((contSusElegibilidade == 0)&&(contFerias == 0)&&(incluirData == true)) {
				//if ((contSusElegibilidade == 0)&&(contFerias == 0)) {	
					listaProgramacaoEntregaMes.add(programacaoInput);
					this.programacaoEntregaRepository.save(programacaoInput);
				}	
				
				dataProgramacao = dataProgramacao.plusDays(1);
				
			}
			
			if (listaProgramacaoEntregaMes.isEmpty()){
				throw new GlobalExceptionHandler("Atenção! Período com férias ou suspensão da elegibilidade!");
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
			
			// alteração - bairroSecaoElegivel
			
			//Pesquisa unidade acadêmcia, a partir do bairro da seção do elegível.
//			UnidadeAcademica unidadeAcademica = unidadeAcademicaService.
//			pesquisarPrimeiroPorDescricao(programacaoEntregaDto.getBairroSecaoElegivel());
//			
//			if ((unidadeAcademica == null)||(unidadeAcademica.getDescricao() == null) ||(unidadeAcademica.getDescricao() == "")) {
//				throw new GlobalExceptionHandler("Elegível sem o bairro da seção!");
//			}
			
			//Pesquisa unidade acadêmcia, a partir do id.
			UnidadeAcademica unidadeAcademica = unidadeAcademicaService.obterPorId(programacaoEntregaDto.getIdUa()).get();
			
			//Preenche a descrição e o id da ua.
			programacaoEntregaDto.setIdUa(unidadeAcademica.getId());
			programacaoEntregaDto.setUaPrevista(unidadeAcademica.getDescricao());
			
			//fim alteração - bairroSecaoElegivel
			
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
			
//			//Verifica se ano informado é menor que ano corrente.
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
			
			//alteração 18.07.2023
			//Recupera o ano e o mês da programação, e o ano e o mês da admissão.
			
			Integer anoAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getYear();
			Integer mesAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getMonthValue();
			
			
//			//Verifica se ano informado é menor que ano corrente.
//			if (anoProgramacao < anoAtual ) {
//				throw new GlobalExceptionHandler("Ano da programação deve ser maior ou igual ao ano corrente!");
//			} 
			
			if (anoAdmissao == anoAtual ) {
				//Verifica se mês informado é menor que mês corrente.
				if (mesProgramacao < mesAdmissao ) {
					throw new GlobalExceptionHandler("Mês da programação deve ser maior ou igual ao mês da admissão!");
				}
			}
			
			
			//fim alteração.
			
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
				  } else { //alteração %$
					 
//					  //Recupera data da programação para verificar se tem menos de 24h.
//					  LocalDate dataHoje = LocalDate.now();
//					  boolean igual = programacaoEntregaLinha.getDataProgramacao().equals(dataHoje);  
//					  
//					  if (!igual) {
					     this.programacaoEntregaRepository.save(programacaoEntregaLinha);
//					  } else {
//						//Caso seja com menos de 24h atualiza com o tipo de solicitação igual a "E"
//						 this.programacaoEntregaRepository.atulizarProgEntregaTipoSolicitacao("A",programacaoEntregaLinha.getId());
//					  }
					
					
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
				programacaoEntrega.setIdValor(idValor);
				programacaoEntrega.setDataUltimaModificacao(dataModificacao);
				programacaoEntrega.setIdUsuarioUltimaModificacao(idUsuarioUltimaModificacao);
				programacaoEntrega.setDataProgramacao(dataProgramacao);
				programacaoEntrega.setDiaDaSemana(diaDaSemana);
				
				// trecho repetido. colocar em uma função.
				
				  //
                
                
                /* if (valorMarmitaAtual != null) {
    				
    				
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
				*/
				
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
		//@Transactional
		public void apagarProgramacaoMes(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			String[] tabelaProgramacaoEntrega = programacaoEntregaDto.getTabelaProgramacaoEntrega().split(",");
			LocalDate dataProgramacao = null;
			
			for (String idProgramacao : tabelaProgramacaoEntrega){
				
				//Recupera data da programação para verificar se tem menos de 24h.
				dataProgramacao = 
				programacaoEntregaRepository.pesquisarDataProgramacao(Long.valueOf(idProgramacao));
				
				
//				ProgramacaoEntrega programacaoEntrega = 
//				programacaoEntregaRepository.obterProgrEntregaPorId(Long.valueOf(idProgramacao));
				
				//Verifica se a solicitação tem menos de 24h
				String tipoOperacao = this.verificaProgramacaoMenos24h(dataProgramacao, "E");
				
				  if (tipoOperacao == "") {	
					     programacaoEntregaRepository.apagarProgEntrega(Long.valueOf(idProgramacao));
					     //programacaoEntregaRepository.apagarProgEntrega(programacaoEntrega.getId());
				  } else {
				    	programacaoEntregaRepository.atulizaExcluirProgEntParaAprovar(
				    			programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
				    			LocalDateTime.now(), 
				    			Long.valueOf(idProgramacao),					
				    			programacaoEntregaDto.getIdJustificativa(),
								 "E");
				    	
				 }
				
		   } // End for
			
	   }

		@Override
		public void registrarEntrega(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			// validaFormRegistroEntrega(programacaoEntregaDto);
			 
			  programacaoEntregaRepository.registrarEntrega(programacaoEntregaDto.getUaRealizada(),
					 programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
					 LocalDateTime.now(),
					 programacaoEntregaDto.getDataEntrega(),
					 programacaoEntregaDto.getIdUa(),
					 programacaoEntregaDto.getIdJustificativa(),
					 programacaoEntregaDto.getId());
			 
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
	    	 
	    	 
	    	 //Recupera a programação por anoMes, matrícula e código do setor.
	    	 List<ProgEntVigenteDto> listarProgEntVigenteDto = programacaoEntregaRepository.
						listarProgramacaoEntregaVigente(matriculaColaborador,anoMes,codSetor);
	    	 
	    	 List<ProgEntVigenteResponse> listarProgEntVigenteResponse = new ArrayList(); 
	    	 
	    	 
	    	String descrSetor = ""; // (obs)
	    	String nomeFuncionario; // (obs)
	    	 
	    	for (ProgEntVigenteDto progEntrega : listarProgEntVigenteDto) {
					
	    		 nomeFuncionario = "";
	    		 
	    		 ProgEntVigenteResponse progEntVigenteResponse = new ProgEntVigenteResponse(); 
	    		 
//	    		 VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(progEntrega.getCodSetor()); (obs)
//	    		 progEntVigenteResponse.setDescrSetor(vwSisbenSetor.getDescrSetor()); (obs)
	    		
	    		 descrSetor = vwSisbenSetorService.ObterDescrSetor(progEntrega.getCodSetor()); //(obs)
	    		 progEntVigenteResponse.setDescrSetor(descrSetor); //(obs)
	    		 
//	    		 Optional<VwSisbenFuncionario>   funcionario =  vwSisbenFuncionarioService.ObterPorMatricula(progEntrega.getMatriculaColaborador());
//	    		//(obs) 
//	    		 if (funcionario.isPresent() ) {
//	    			 progEntVigenteResponse.setNomeColaborador(funcionario.get().getNomeFuncionario());	 
//	    		 } else {
//	    			 progEntVigenteResponse.setNomeColaborador("Funcionário excluído do TOTVS!");
//	    		 }

	    		 
	    		    nomeFuncionario =  vwSisbenFuncionarioService.ObterNomePorMatricula(progEntrega.getMatriculaColaborador());
		    		//(obs) 
		    		 if (nomeFuncionario != "" ) {
		    			 progEntVigenteResponse.setNomeColaborador(nomeFuncionario);	 
		    		 } else {
		    			 progEntVigenteResponse.setNomeColaborador("Funcionário excluído do TOTVS!");
		    		 }
	    		 
	    		 
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
				String anoMes,String codSetor, String idUsuarioLogado, String idUa) {
			
			if ((matriculaColaborador == "")||((matriculaColaborador == null))) {
	    		 matriculaColaborador = null;
			}
	    	 
	    	if ((anoMes == "")||((anoMes == null))) {
	    		 anoMes = null;
			}
	    	 
	    	if ((codSetor == "")||((codSetor == null))) {
	    		 codSetor = null;
			}
	    	Long idUaParam = null;
	    	if ((idUa != "")&&((idUa != null))) {
	    		idUaParam = Long.parseLong(idUa);
			}
	    	 
//	    	List<String> listStrCodSetor = usuarioSetorGerenciadoService. (obs)
//	    		     concatenaSetoresLider(idUsuarioLogado);
	    	  
//		    List<ProgEntVigenteDto> listarProgEntVigenteDto = programacaoEntregaRepository.
//							listarProgramacaoEntregaVigenteLiderSetor(matriculaColaborador,anoMes,codSetor,listStrCodSetor );

		    List<ProgEntVigenteResumoDto> listarProgEntVigenteDto = programacaoEntregaRepository.
					listarProgramacaoEntregaVigenteLiderSetorNovo(matriculaColaborador,anoMes,codSetor,Long.parseLong(idUsuarioLogado),idUaParam );
    
		    
		    List<ProgEntVigenteResponse> listarProgEntVigenteResponse = new ArrayList();
		    	 
		    	 
		    String descrSetor = ""; // (obs)
			String nomeFuncionario; // (obs)
	    	
	    	 
	    	for (ProgEntVigenteResumoDto progEntrega : listarProgEntVigenteDto) {
					
	    		 
	    		 ProgEntVigenteResponse progEntVigenteResponse = new ProgEntVigenteResponse();
	    		 
                 nomeFuncionario = "";
//	    		 VwSisbenSetor vwSisbenSetor = vwSisbenSetorService.ObterPorCodigo(progEntrega.getCodSetor()); (obs)
//	    		 progEntVigenteResponse.setDescrSetor(vwSisbenSetor.getDescrSetor()); (obs)
	    		
	    		 descrSetor = vwSisbenSetorService.ObterDescrSetor(progEntrega.getCodSetor()); //(obs)
	    		 progEntVigenteResponse.setDescrSetor(descrSetor); //(obs)
	    		 
//	    		 Optional<VwSisbenFuncionario>   funcionario =  vwSisbenFuncionarioService.ObterPorMatricula(progEntrega.getMatriculaColaborador());
//	    		//(obs) 
//	    		 if (funcionario.isPresent() ) {
//	    			 progEntVigenteResponse.setNomeColaborador(funcionario.get().getNomeFuncionario());	 
//	    		 } else {
//	    			 progEntVigenteResponse.setNomeColaborador("Funcionário excluído do TOTVS!");
//	    		 }

	    		 
	    		 nomeFuncionario =  vwSisbenFuncionarioService.ObterNomePorMatricula(progEntrega.getMatriculaColaborador());
		    		//(obs) 
		    	 if (nomeFuncionario != "" ) {
		    			 progEntVigenteResponse.setNomeColaborador(nomeFuncionario);	 
		    	 } else {
		    			 progEntVigenteResponse.setNomeColaborador("Funcionário excluído do TOTVS!");
		    	 }
	    		 
	    		 progEntVigenteResponse.setAnoMes(progEntrega.getAnoMes());
	    		 progEntVigenteResponse.setMatriculaColaborador(progEntrega.getMatriculaColaborador());
	    		 progEntVigenteResponse.setCodSetor(progEntrega.getCodSetor());
	    		 progEntVigenteResponse.setStatus("Programado");
	    		// progEntVigenteResponse.setLocalEntrega(progEntrega.getLocalEntrega());
	    		
				   
	    		 listarProgEntVigenteResponse.add(progEntVigenteResponse);
				    
			}
	    	
	    	//List<ProgEntVigenteNpDto> listarProgEntVigenteDtoNaoProg = programacaoEntregaRepository.listarProgramacaoEntregaVigenteNaoProgramadoLiderSetor(matriculaColaborador,anoMes, codSetor, listStrCodSetor );
	    	
	    	List<ProgEntVigenteNpDto> listarProgEntVigenteDtoNaoProg = programacaoEntregaRepository.listarProgramacaoEntregaVigenteNaoProgramadoLiderSetorNovo(matriculaColaborador,anoMes, codSetor, Long.parseLong(idUsuarioLogado) );
	    	
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
		public long pesquisarProgrEntregaDataMatr(String dataProgramacao,
				String matriculaColaborador) {
			    
			return programacaoEntregaRepository.
					pesquisarProgrEntregaDataMatr(dataProgramacao, matriculaColaborador);
		}

		@Override
		public List<ProgramacaoEntrega> recuperarProgrEntregaDataMatr(String dataProgramacao,
				String matriculaColaborador) {
			return programacaoEntregaRepository.
					recuperarProgrEntregaDataMatr(dataProgramacao, matriculaColaborador);
		}

		@Override
		public List<ProgramacaoEntrega> copiarProgramacaoEntrega(String dataProgramacao,
				                                                 String matriculaOrigem,
				                                                 String matriculaDestino,
				                                                 String idUsuarioLogado) {
			
			 List<ProgramacaoEntrega> listaProgCopia = new ArrayList();
			 
			 
			 Optional<VwSisbenFuncionario> funcionario = vwSisbenFuncionarioService.ObterPorMatricula(matriculaDestino.trim());
			 
			 
			 if (!funcionario.isPresent()) {
				 throw new GlobalExceptionHandler("Funcionário de matrícula " + matriculaDestino +  " não existe no TOTVS !" ); 
			 }
			 
	
			 List<ProgramacaoEntrega> listaProgramacaoEntrega = this.recuperarProgrEntregaDataMatr(dataProgramacao, matriculaOrigem);
			 
			 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
				 
				   ProgramacaoEntrega programacaoEntregaDestino = new  ProgramacaoEntrega();
				   
				   programacaoEntregaDestino.setMatriculaColaborador(matriculaDestino);
				   programacaoEntregaDestino.setAnoMes(programacaoEntregaLinha.getAnoMes());
				   programacaoEntregaDestino.setCodSetor(funcionario.get().getCodSecao());
				   programacaoEntregaDestino.setDataEntrega(programacaoEntregaLinha.getDataEntrega());
				   programacaoEntregaDestino.setDataProgramacao(programacaoEntregaLinha.getDataProgramacao());
				   programacaoEntregaDestino.setDataSolicitacao(programacaoEntregaLinha.getDataSolicitacao());
				   programacaoEntregaDestino.setDataUltimaModificacao(LocalDateTime.now());
				   programacaoEntregaDestino.setDescricaoFeriado(programacaoEntregaLinha.getDescricaoFeriado());
				   programacaoEntregaDestino.setDiaDaSemana(programacaoEntregaLinha.getDiaDaSemana());
				   programacaoEntregaDestino.setIdUa(programacaoEntregaLinha.getIdUa());
				   programacaoEntregaDestino.setIdUsuario(programacaoEntregaLinha.getIdUsuario());
				   programacaoEntregaDestino.setIdUsuarioUltimaModificacao(Long.parseLong(idUsuarioLogado));
				   programacaoEntregaDestino.setIdValor(programacaoEntregaLinha.getIdValor());
				   programacaoEntregaDestino.setUaPrevista(programacaoEntregaLinha.getUaPrevista());
				   
				   listaProgCopia.add(programacaoEntregaDestino);
				  
				   this.programacaoEntregaRepository.save(programacaoEntregaDestino);
			 }
			
			return listaProgCopia;
		}

		@Override
		public long pesquisarProgrEntregaUa(Long idUa) {
			return this.programacaoEntregaRepository.pesquisarProgrEntregaUa(idUa);
		}
		
		public String verificaProgramacaoMenos24h(LocalDate dataProgramacao, String tipoOperacao) {
			
			UtilSisben utilSisben = new UtilSisben();
			String tpOperacao = "";
			String descricaoTpOperacao = "";
			
		    switch (tipoOperacao) {
		            case "I":
		            	descricaoTpOperacao = "Inclusão";
		                break;
		            case "A":
		            	descricaoTpOperacao = "Alteração";
		                break;     
		            case "E":
		            	descricaoTpOperacao = "Exclusão";
		                break;     
		           
		    }

			
			//Verifica se a data de programação é igual a data corrente.
			if (dataProgramacao.equals(LocalDate.now())) {
				
				//Verifica se a hora da programação é menor que 13:00h. 
				if (utilSisben.verificaHoraLimiteSolicitacao("13:00")) {
					tpOperacao = tipoOperacao ;
				} else {
					throw new GlobalExceptionHandler("Hora limite esgotada para solicitar " + descricaoTpOperacao
							+ " para a data " + dataProgramacao );
				}
				
			}
			
			//Verifica se a data de programação é igual a data de amanhã.
			if (dataProgramacao.equals(LocalDate.now().plusDays(1))) {
				tpOperacao = tipoOperacao ;
			}	
			
			return tpOperacao;
		}

		@Override
		public List<ProgramacaoEntrega> listarProgEntAnalise24h(String matriculaColaborador, String anoMes,
				String codSetor,Long idUa) {
			
			 if ((matriculaColaborador == "")||((matriculaColaborador == null))) {
	    		 matriculaColaborador = null;
			 }
	    	 
	    	 if ((anoMes == "")||((anoMes == null))) {
	    		 anoMes = null;
			 }
	    	 
	    	 if ((codSetor == "")||((codSetor == null))) {
	    		 codSetor = null;
			 }
	    	 
	    	 if ((idUa == null)) {
	    		 idUa = null;
			 }
			
			
			 return this.programacaoEntregaRepository.listarProgEntAnalise24h(matriculaColaborador, anoMes, codSetor, idUa);
		}

		@Override
		public void atualizarStatusAnalise24h(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			//Recupera data da programação.
			LocalDate dataProgramacao = programacaoEntregaRepository.
			pesquisarDataProgramacao(programacaoEntregaDto.getId());

			//Verifica se a data de programação é anterior à data corrente.
			if(dataProgramacao.isBefore(LocalDate.now())){
				throw new GlobalExceptionHandler("Data de programação anterior à data corrente !");
			}
			
			 //Verifica se a data de programação é igual a data corrente.
            if (dataProgramacao.equals(LocalDate.now())) { //obs
            	 
            	 UtilSisben utilSisben = new UtilSisben();
            	 
				//Verifica se a hora da programação é menor que 23:59h. 
				if (!utilSisben.verificaHoraLimiteSolicitacao("23:59")) {
					throw new GlobalExceptionHandler("Hora limite esgotada para aprovar/reprovar ! ");
				}
				
			}
			
            //Verifica se a justificativa foi preenchida em caso de reprovação.
			if ((!programacaoEntregaDto.getStAprov())&((programacaoEntregaDto.getJustReprovacao() == null) || (programacaoEntregaDto.getJustReprovacao().isEmpty()))) {
				 throw new GlobalExceptionHandler(" Para reprovação informe o motivo !");
			}
			
			//Reprovação.
			if (!programacaoEntregaDto.getStAprov()){
			
				  this.programacaoEntregaRepository.atualizarStatusAnalise24h
				 (programacaoEntregaDto.getStAprov(), programacaoEntregaDto.getJustReprovacao(),
				  programacaoEntregaDto.getId(),programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
				  LocalDateTime.now());
			  
			 } else {
				 
				 ProgramacaoEntrega programacaoEntrega = programacaoEntregaRepository.obterProgrEntregaPorId(programacaoEntregaDto.getId());
				 
				 
				 //Se o tipo de solicitação for alteração.
				 if (programacaoEntrega.getTipoSolicitacao().charAt(0) == 'A' ) {
				 
					 String uaRealizada = "";
					 Long idUa = programacaoEntrega.getIdUaAlterar();
					 
					 //Pesquisa unidade acadêmcia, a partir do id.
					 UnidadeAcademica unidadeAcademica = unidadeAcademicaService.obterPorId(idUa).get();
					 
					 uaRealizada = unidadeAcademica.getDescricao();
					 this.programacaoEntregaRepository.atualizarStatusAnalise24hAlterarAprov(programacaoEntregaDto.getStAprov(),
								uaRealizada,
								programacaoEntregaDto.getId(),
								idUa,
								programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
								LocalDateTime.now());
				 
				 } else {
					 if (programacaoEntrega.getTipoSolicitacao().charAt(0) == 'I' ) {
						 this.programacaoEntregaRepository.atualizarStatusAnalise24h
						 (programacaoEntregaDto.getStAprov(),null,
						  programacaoEntregaDto.getId(),programacaoEntregaDto.getIdUsuarioUltimaModificacao(),
						  LocalDateTime.now());
					 } else {
						 if (programacaoEntrega.getTipoSolicitacao().charAt(0) == 'E' ) {
							 programacaoEntregaRepository.apagarProgEntrega(programacaoEntregaDto.getId());
						 }
					 }
					 
					 
				 }
					
				 
			 }
			
		}

		@Override
		public List<ProgramacaoEntrega> copiarProgramacaoEntregaVariasMatriculas(String dataProgramacao,
				String matriculaColaboradorOrigem, String matriculaColaboradoresDestino, String idUsuarioLogado) {
			
                List<ProgramacaoEntrega> listaProgCopia = new ArrayList();
			 
			 
//			 Optional<VwSisbenFuncionario> funcionario = vwSisbenFuncionarioService.ObterPorMatricula(matriculaDestino.trim());
//			 
//			 
//			 if (!funcionario.isPresent()) {
//				 throw new GlobalExceptionHandler("Funcionário de matrícula " + matriculaDestino +  " não existe no TOTVS !" ); 
//			 }
			 
			 String[] strMatriculasDestino = matriculaColaboradoresDestino.split(",");
			 
	
			 List<ProgramacaoEntrega> listaProgramacaoEntrega = this.recuperarProgrEntregaDataMatr(dataProgramacao, matriculaColaboradorOrigem);
			 
			 for (String matriculaDestino : strMatriculasDestino){
				 
				 
				     Long countProg = this.programacaoEntregaRepository.
				    		 pesquisarProgrEntregaAnoMesMatr(dataProgramacao,
				    				matriculaDestino);
					
					if ((countProg > 0)) {
						throw new GlobalExceptionHandler("Já existe programação para esta data e matrícula = " + matriculaDestino );
					} 
				 
			 
					 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
						 
						   ProgramacaoEntrega programacaoEntregaDestino = new  ProgramacaoEntrega();
						   
						   Optional<VwSisbenFuncionario> funcionario = vwSisbenFuncionarioService.ObterPorMatricula(matriculaDestino.trim());
						   
						   programacaoEntregaDestino.setMatriculaColaborador(matriculaDestino);
						   programacaoEntregaDestino.setAnoMes(programacaoEntregaLinha.getAnoMes());
						   programacaoEntregaDestino.setCodSetor(funcionario.get().getCodSecao());
						   programacaoEntregaDestino.setDataEntrega(programacaoEntregaLinha.getDataEntrega());
						   programacaoEntregaDestino.setDataProgramacao(programacaoEntregaLinha.getDataProgramacao());
						   programacaoEntregaDestino.setDataSolicitacao(programacaoEntregaLinha.getDataSolicitacao());
						   programacaoEntregaDestino.setDataUltimaModificacao(LocalDateTime.now());
						   programacaoEntregaDestino.setDescricaoFeriado(programacaoEntregaLinha.getDescricaoFeriado());
						   programacaoEntregaDestino.setDiaDaSemana(programacaoEntregaLinha.getDiaDaSemana());
						   programacaoEntregaDestino.setIdUa(programacaoEntregaLinha.getIdUa());
						   programacaoEntregaDestino.setIdUsuario(programacaoEntregaLinha.getIdUsuario());
						   programacaoEntregaDestino.setIdUsuarioUltimaModificacao(Long.parseLong(idUsuarioLogado));
						   programacaoEntregaDestino.setIdValor(programacaoEntregaLinha.getIdValor());
						   programacaoEntregaDestino.setUaPrevista(programacaoEntregaLinha.getUaPrevista());
						   
						   listaProgCopia.add(programacaoEntregaDestino);
						  
						   this.programacaoEntregaRepository.save(programacaoEntregaDestino);
					 }
			 }
			
			return listaProgCopia;

		}

		@Override
		public List<RegistroEntregaResponse> listarRegistroEntrega(String matriculaColaborador, String anoMes,
				String codSetor, String idUa, String dataProgramacao) {
			
				
				if ((matriculaColaborador == "")||((matriculaColaborador == null))) {
		    		 matriculaColaborador = null;
				}
		    	 
		    	if ((anoMes == "")||((anoMes == null))) {
		    		 anoMes = null;
				}
		    	 
		    	if ((codSetor == "")||((codSetor == null))) {
		    		 codSetor = null;
				}
		    	Long idUaParam = null;
		    	if ((idUa != "")&&((idUa != null))) {
		    		idUaParam = Long.parseLong(idUa);
				}
		    	
		    	LocalDate dataProgramacaoParam = null;
		    	if ((dataProgramacao != "")&&((dataProgramacao != null))) {	
		    		dataProgramacaoParam = LocalDate.parse(dataProgramacao);
				}
		    	
		    	 

			    List<RegistroEntregaDto> listaregistroEntregaDto = programacaoEntregaRepository.
			    		listarProgramacaoEntregaRegistroEntrega(matriculaColaborador,anoMes,codSetor,idUaParam, dataProgramacaoParam );
			    
			    List<RegistroEntregaResponse> listaRegistroEntregaResponse = new ArrayList();
		    	 
		    	 
			    String descrSetor = ""; 
				String nomeFuncionario; 
		    	
		    	 
		    	for (RegistroEntregaDto registroEntrega : listaregistroEntregaDto) {
						
		    		 
		    		RegistroEntregaResponse registroEntregaResponse = new RegistroEntregaResponse();
		    		 
	                 nomeFuncionario = "";
		    		
		    		 descrSetor = vwSisbenSetorService.ObterDescrSetor(registroEntrega.getCodSetor()); 
		    		 registroEntregaResponse.setDescrSetor(descrSetor); 
		    		 
		    		 nomeFuncionario =  vwSisbenFuncionarioService.ObterNomePorMatricula(registroEntrega.getMatriculaColaborador());
		    		 
			    	 if (nomeFuncionario != "" ) {
			    		 registroEntregaResponse.setNomeColaborador(nomeFuncionario);	 
			    	 } else {
			    		 registroEntregaResponse.setNomeColaborador("Funcionário excluído do TOTVS!");
			    	 }
		    		 
			    	 registroEntregaResponse.setAnoMes(registroEntrega.getAnoMes());
			    	 registroEntregaResponse.setMatriculaColaborador(registroEntrega.getMatriculaColaborador());
			    	 registroEntregaResponse.setCodSetor(registroEntrega.getCodSetor());
			    	 registroEntregaResponse.setLocalEntrega(registroEntrega.getLocalEntrega());
			    	 registroEntregaResponse.setDataProgramacao(registroEntrega.getDataProgramacao());
			    	 registroEntregaResponse.setId(registroEntrega.getId());
			    	 registroEntregaResponse.setDataEntrega(registroEntrega.getDataEntrega());
			    	 registroEntregaResponse.setIdJustificativa(registroEntrega.getIdJustificativa());
			    	 registroEntregaResponse.setEntrNaoProgramada(registroEntrega.getEntrNaoProgramada());
		    		
					   
		    		 listaRegistroEntregaResponse.add(registroEntregaResponse);
					    
				}
			    
			    return listaRegistroEntregaResponse;
	    

		}

		@Override
		public ProgramacaoEntrega pesquisarProgrEntregaDataMatrRegistroEntrega(String dataProgramacao,
				String matriculaColaborador) {
			return this.programacaoEntregaRepository.pesquisarProgrEntregaDataMatrRegistroEntrega(dataProgramacao, matriculaColaborador);
		}

		@Override
		public ProgramacaoEntrega registrarEntregaNprog(ProgramacaoEntregaDto programacaoEntregaDto) {
			ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
			
			programacaoEntrega.setIdUsuario(programacaoEntregaDto.getIdUsuario());
			
			
			//Recupera dados dos elegíveis na visão.
			Optional<VwSisbenElegibilidade> VwSisbenElegibilidade = vwSisbenElegibilidadeService.
			ObterPorMatricula(programacaoEntrega.getMatriculaColaborador());
			
			//Converte a data da admissão para localdate.
			LocalDate dataAdmissaoLocalDate =  VwSisbenElegibilidade.get().getDataAdmissao().toLocalDate();

            //Alteração 18.07.2023
			
			//int mesAtual = LocalDate.now().getMonthValue();
			//int anoAtual = LocalDate.now().getYear();
				
				//Recupera o ano da admissão.
				//Integer anoAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getYear();
				//Integer mesAdmissao = VwSisbenElegibilidade.get().getDataAdmissao().getMonthValue();
				
				//Verifica se o ano atual é igual ao ano da admissão.
				//if (anoAdmissao == anoAtual ) {
					//Verifica se a data da programação é menor que data da programação.
					if(programacaoEntrega.getDataProgramacao().isBefore(dataAdmissaoLocalDate)) {
						throw new GlobalExceptionHandler("Data da programação deve ser maior ou igual à data da admissão!");
					}
				//}
				
				
			// fim alteração

			
			Long contFerias = 0L; 			
			Long contSusElegibilidade = 0L;
			
			//Opção por este tipo de chamada para não trazer muitos objetos para a memória.
			//Pesquisa se existe suspensão da eligibilidade para o ano e mês.
			contSusElegibilidade = 0L;
			contSusElegibilidade = suspensaoElegibilidadeService.
				pesquisarSuspensao(programacaoEntrega.getDataProgramacao(), programacaoEntrega.getMatriculaColaborador());
			
			//Pesquisa se existem férias.
			contFerias = 0L;
			contFerias = vwSisbenFeriasElegivel.pesquisarFeriasElegivel(programacaoEntrega.getDataProgramacao(), programacaoEntrega.getMatriculaColaborador());
			
			//Só insere se não existirem férias nem suspensão da eligibilidade.
			if ((contSusElegibilidade > 0)||(contFerias > 0)) {
				throw new GlobalExceptionHandler("Data com férias ou suspensão da elegibilidade!");
			}
			
			//Pesquisa se existe valor marmita e recupera.
			ValorMarmita valorMarmita = valorMarmitaService.
					obterValorVigencia(programacaoEntrega.getDataProgramacao());
			
			if ( valorMarmita == null) {
				throw new GlobalExceptionHandler("Não existe vigência de valor"
						+ " da marmita cadastrado para a data da programação informada!");
			}
			
			//Recupera e atribui o valor da marmita.
			programacaoEntrega.setIdValor(valorMarmita.getId());
			
			//Recupera dia da semana
			UtilSisben utilSisben = new UtilSisben(); 
			programacaoEntrega.setDiaDaSemana(utilSisben.getDiaDaSemana(programacaoEntrega.getDataProgramacao()));
			
			//Recupera setor do usuário.
			VwSisbenFuncionario vwSisbenFuncionario = vwSisbenFuncionarioService.
			ObterPorMatricula(programacaoEntrega.getMatriculaColaborador()).get();
			
			programacaoEntrega.setCodSetor(vwSisbenFuncionario.getCodSecao());
			
			//Pesquisa existência de data especial.
			Calendario calendario = new Calendario();
			calendario = calendarioService.pesquisarPorData(programacaoEntrega.getDataProgramacao());
			programacaoEntrega.setDescricaoFeriado(null);
			if (calendario != null) {
				programacaoEntrega.setDescricaoFeriado(calendario.getDescricao());
			};
			
			Integer intMesRegEntr = programacaoEntrega.getDataProgramacao().getMonthValue();
			Integer intAnoRegEntr = programacaoEntrega.getDataProgramacao().getYear();
				
			String strMesRegEntr = intMesRegEntr.toString();
			if (strMesRegEntr.length()== 1) {
				strMesRegEntr = "0" + strMesRegEntr;
			}
				
			String strAnoMesRegEntr = intAnoRegEntr.toString() + strMesRegEntr; 
				
			programacaoEntrega.setAnoMes(strAnoMesRegEntr);
			
			//Ua realizada é igual a prevista neste momento.
			programacaoEntrega.setUaRealizada(programacaoEntrega.getUaPrevista());
			programacaoEntrega.setIdJustificativa(programacaoEntrega.getIdJustificativa());
			
			
			programacaoEntrega.setDataUltimaModificacao(LocalDateTime.now());
			
			//Registro de entrega não programada.
			programacaoEntrega.setEntrNaoProgramada(true);
			
			
			return programacaoEntregaRepository.save(programacaoEntrega);
		}

		@Override
		public Long pesquisarProgrEntregaPendente(String dataProgramacao, String matriculaColaborador) {
			return programacaoEntregaRepository.pesquisarProgrEntregaPendente(dataProgramacao, matriculaColaborador);
		}

		@Override
		public Long pesquisarProgrEntregaRepInc(String dataProgramacao, String matriculaColaborador) {
			return programacaoEntregaRepository.pesquisarProgrEntregaRepInc(dataProgramacao, matriculaColaborador);
		}
		
	
}