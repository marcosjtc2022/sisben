package com.bahiana.sisben.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.ProgramacaoEntregaMenos24hDto;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.model.entity.Calendario;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.UnidadeAcademica;
import com.bahiana.sisben.model.entity.ValorMarmita;
import com.bahiana.sisben.model.entity.VwSisbenElegibilidade;
import com.bahiana.sisben.model.entity.repository.ProgramacaoEntregaRepository;
import com.bahiana.sisben.service.CalendarioService;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.service.SuspensaoElegibilidadeService;
import com.bahiana.sisben.service.UnidadeAcademicaService;
import com.bahiana.sisben.service.ValorMarmitaService;
import com.bahiana.sisben.service.VwSisbenElegibilidadeService;
import com.bahiana.sisben.service.VwSisbenFeriasElegivelService;
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
	
//	@Autowired
//	VwSisbenFeriasElegivelService vwSisbenFeriasElegivel;
//	
	
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
	public ProgramacaoEntrega salvarLote(ProgramacaoEntregaDto programacaoEntregaDto, char operacao) {
		
		 ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
		 List<ProgramacaoEntrega> listaProgramacaoEntrega = concatenaCamposTabela(programacaoEntrega, operacao);
		 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
				//validaEPersisteInclusao(centro);	
				this.programacaoEntregaRepository.save(programacaoEntregaLinha);
		 }
		 return programacaoEntrega;
	}
	
	@SuppressWarnings("removal")
	@Override
	public List<ProgramacaoEntrega> concatenaCamposTabela(ProgramacaoEntrega programacaoEntrega, char operacao) {
		
		List<ProgramacaoEntrega> listaProgramacaoEntrega = new ArrayList<>();
		
		//Recupera as Strings preenchidas a partir dos campos das linhas das programações entrega.
		String[] tabelaProgramacaoEntrega = programacaoEntrega.getTabelaProgramacaoEntrega().split(",");
		String[] linha		= null;
		Long id = null;
		String matriculaColaborador = null;
		String uaPrevista = null;
		String uaRealizada = null;
		LocalDate dataEntrega = null;
		LocalDate dataSolicitacao = null;
		Long idUa = null;
		Long idJustificativa = null;
		Long idUsuario = null;
		Long idValor = null;
		Boolean solicExtra = null;
		Boolean stAprov = null;
		Long usuarioModificacao = programacaoEntrega.getIdUsuario();
		LocalDateTime dataModificacao = LocalDateTime.now();
		//UtilDataHora utilDataHora = new UtilDataHora() ;
		String dataEntrega2 = null;
		String dataSolicitacao2 = null;
		
		DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		//String dateInString = "Mon, 05 May 1980";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy", Locale.ENGLISH);
//		LocalDate dateTime = LocalDate.parse(dateInString, formatter);
		
		
		for (String linhaTabProgEntrega : tabelaProgramacaoEntrega) 
			{
			//7352=BROTAS=CABULA=2022-09-29=2022-09-29=1=1=1=4=0=1
			//Separando a linha da tabela.
			linha 			= linhaTabProgEntrega.split("=");
			//Separando os campos da linha.
			
			if (operacao == 'I') {
				matriculaColaborador =  linha[0];
				uaPrevista = linha[1];
				uaRealizada = linha[2];
				//dataEntrega = utilDataHora.trataDatas(linha[3]);   //linha[3];
				//dataSolicitacao = utilDataHora.trataDatas(linha[4]);
				//LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
//				dataEntrega2 = linha[3];
				dataEntrega = LocalDate.parse(linha[3]);
				dataSolicitacao = LocalDate.parse(linha[4]);
				idUa = new Long(linha[5]);
				idJustificativa = new Long(linha[6]);
				idUsuario = new Long(linha[7]);
				idValor = new Long(linha[8]);
				solicExtra = true; //linha[9];
				stAprov = true; //linha[10];
			} else {
				id = new Long(linha[0]);
				matriculaColaborador =  linha[1];
				uaPrevista = linha[2];
				uaRealizada = linha[3];
				//dataEntrega = utilDataHora.trataDatas(linha[4]);   //linha[3];
				//dataSolicitacao = utilDataHora.trataDatas(linha[5]);
//				dataEntrega = LocalDateTime.parse(linha[4]);   //linha[3];
//				dataSolicitacao = LocalDateTime.parse(linha[5]);
				dataEntrega = LocalDate.parse(linha[4]);
				dataSolicitacao = LocalDate.parse(linha[5]);
				idUa = new Long(linha[6]);
				idJustificativa = new Long(linha[7]);
				idUsuario = new Long(linha[8]);
				idValor = new Long(linha[9]);
				solicExtra = true; //linha[9];
				stAprov = true; //linha[10];
			}
			
			programacaoEntrega = new ProgramacaoEntrega();
			
			if (id != null) {
				programacaoEntrega.setId(id);
			}
			programacaoEntrega.setMatriculaColaborador(matriculaColaborador);
			programacaoEntrega.setUaPrevista(uaPrevista);
			programacaoEntrega.setUaRealizada(uaRealizada);
			programacaoEntrega.setDataEntrega(dataEntrega);
			programacaoEntrega.setDataSolicitacao(dataSolicitacao);
			programacaoEntrega.setIdJustificativa(idJustificativa);
			programacaoEntrega.setIdUsuario(idUsuario);
			programacaoEntrega.setIdUa(idUa);
			programacaoEntrega.setSolicExtra(solicExtra);
			programacaoEntrega.setStAprov(stAprov);
			programacaoEntrega.setIdValor(idValor);
			programacaoEntrega.setDataUltimaModificacao(dataModificacao);
			programacaoEntrega.setIdUsuarioUltimaModificacao(usuarioModificacao);
			
			listaProgramacaoEntrega.add(programacaoEntrega);
			
		}
		return listaProgramacaoEntrega;
	}

	@Override
	@Transactional
	public ProgramacaoEntrega alterar(ProgramacaoEntregaDto programacaoEntregaDto) {
		
		 //ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
		 LocalDateTime dataModificacao = LocalDateTime.now();
		 programacaoEntregaDto.setDataUltimaModificacao(dataModificacao);
		 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
		 return programacaoEntregaRepository.save(programacaoEntrega);
		
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
		return this.programacaoEntregaRepository.findAll(programacaoEntregaSpecification.toSpec(), pageable);
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

	@Override
	public ProgramacaoEntrega atualizarLote(ProgramacaoEntregaDto programacaoEntregaDto, char operacao) {
		 ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
		 List<ProgramacaoEntrega> listaProgramacaoEntrega = concatenaCamposTabela(programacaoEntrega, operacao);
		 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
				//validaEPersisteInclusao(centro);	
				this.programacaoEntregaRepository.save(programacaoEntregaLinha);
		 }
		 return programacaoEntrega;
	}

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
		public ProgramacaoEntrega salvarMenos24h(ProgramacaoEntregaMenos24hDto programacaoEntregaMenos24hDto) {
			 LocalDateTime dataModificacao = LocalDateTime.now();
			 programacaoEntregaMenos24hDto.setDataUltimaModificacao(dataModificacao);
			 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaMenos24hDto);
			 return programacaoEntregaRepository.save(programacaoEntrega);
		}
		
		// 944 - Método para conversão de classe.
		public static ProgramacaoEntrega from(ProgramacaoEntregaMenos24hDto programacaoEntregaEntregaMenos24hDto) {
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
		public ProgramacaoEntrega autorizarMenos24h(
			 ProgramacaoEntregaMenos24hDto programacaoEntregaMenos24hDto) {
			 LocalDateTime dataModificacao = LocalDateTime.now();
			 programacaoEntregaMenos24hDto.setDataUltimaModificacao(dataModificacao);
			 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaMenos24hDto);
			 return programacaoEntregaRepository.save(programacaoEntrega);
		}

		@Override
		public ProgramacaoEntrega atualizarMenos24h(ProgramacaoEntregaMenos24hDto programacaoEntregaMenos24hDto) {
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
			
			
			//Valida os campos do formulário
			validaFormulario(programacaoEntregaDto);
			
//			//Pesquisa unidade acadêmcia, a partir do bairro da seção do elegível.
//			UnidadeAcademica unidadeAcademica = unidadeAcademicaService.
//			pesquisarPrimeiroPorDescricao(programacaoEntregaDto.getBairroSecaoElegivel());
//			
//			//Preenche a descrição e o id da ua.
//			programacaoEntregaDto.setIdUa(unidadeAcademica.getId());
//			programacaoEntregaDto.setUaPrevista(unidadeAcademica.getDescricao());
			
			//Calcula os dias do mês, a partir da data da solicitação.
			UtilSisben utilSisben = new UtilSisben();
			Integer diasProgramacaoMes = utilSisben.calculaDiasMes(programacaoEntregaDto.getMesAnoProgramacao(),
					                                               programacaoEntregaDto.getDataAtual());
			
			//Salva registros.
			List<ProgramacaoEntrega> programacaoEntregaMes = salvar(programacaoEntregaDto,diasProgramacaoMes);
			
			
			return programacaoEntregaMes;
		}
		
		public List<ProgramacaoEntrega> salvar(ProgramacaoEntregaDto programacaoEntregaDto, Integer diasProgramacaoMes){
			
			List<ProgramacaoEntrega> programacaoEntregaMes = new ArrayList();
			
			Calendario calendario = new Calendario();
			
			LocalDate dataSolicitacao = null;
			//LocalDateTime dataSolicitacaoDateTime = null;
//			Integer ano =  programacaoEntregaDto.getDataAtual().getYear();
//			Integer mes =  programacaoEntregaDto.getDataAtual().getMonthValue();
//			Integer dia =  programacaoEntregaDto.getDataAtual().getDayOfMonth();
			
			//Data que foi solicitada. 
			dataSolicitacao = LocalDate.parse(programacaoEntregaDto.getDataAtual().toString());
			//dataSolicitacaoDateTime = LocalDateTime.parse(programacaoEntregaDto.getDataAtual().toString());
			
			UtilSisben utilSisben = new UtilSisben();
			
			
			for (int i = 1; i <= diasProgramacaoMes; i ++){
				
				ProgramacaoEntrega programacaoInput = new ProgramacaoEntrega();
				
				
				
				programacaoInput.setMatriculaColaborador(programacaoEntregaDto.getMatriculaColaborador());
				programacaoInput.setUaPrevista(programacaoEntregaDto.getUaPrevista());
				programacaoInput.setUaRealizada(null);
				programacaoInput.setIdUa(programacaoEntregaDto.getIdUa());
				programacaoInput.setIdData(null);
				//Verificar a possibilidade de colocar uma justificativa "n se aplica"
				programacaoInput.setIdJustificativa(11L);
				//
				programacaoInput.setIdUsuario(programacaoEntregaDto.getIdUsuario());
				programacaoInput.setIdValor(programacaoEntregaDto.getIdValor());
				programacaoInput.setDataEntrega(null);
				programacaoInput.setDataSolicitacao(dataSolicitacao);
				programacaoInput.setSolicExtra(false);
				programacaoInput.setStAprov(null);
				programacaoInput.setDataUltimaModificacao(LocalDateTime.now());
				programacaoInput.setIdUsuarioUltimaModificacao(programacaoEntregaDto.getIdUsuarioUltimaModificacao());
				programacaoInput.setDataProgramacao(dataSolicitacao);
				programacaoInput.setDiaDaSemana(utilSisben.getDiaDaSemana(dataSolicitacao));
				
				//Pesquisa existência de data especial.
				calendario = calendarioService.pesquisarPorData(dataSolicitacao);
				
				if (calendario != null) {
					programacaoInput.setDescricaoFeriado(calendario.getDescricao());
				};
				
				
				//Pesquisa se existe suspensão da eligibilidade para o ano e mês.
				Long contSusElegibilidade = suspensaoElegibilidadeService.
					pesquisarSuspensao(dataSolicitacao, programacaoEntregaDto.getMatriculaColaborador());
				
				
				if ((contSusElegibilidade > 0) && (contSusElegibilidade != null)) {
					programacaoInput.setExigSuspensa(true);
				}
				
				
//				//Pesquisa se existem férias.
//				Long contFerias = vwSisbenFeriasElegivel.pesquisarFeriasElegivel(dataSolicitacaoDateTime, programacaoEntregaDto.getMatriculaColaborador());
//				
//				
//				if ((contFerias > 0) && (contFerias != null)) {
//					programacaoInput.setStFerias(true);
//				}
				
				this.programacaoEntregaRepository.save(programacaoInput);
				
				dataSolicitacao = dataSolicitacao.plusDays(1);
				//dataSolicitacaoDateTime = dataSolicitacaoDateTime.plusDays(1);

				
			}
			
			return programacaoEntregaMes;
		}

		@Override
		public long pesquisaValorMarmita(String mesAnoProgramacao, String matriculaColaborador ) {
			return programacaoEntregaRepository.
				   pesquisaProgramacaoEntregaAnoMesMatricula(mesAnoProgramacao, matriculaColaborador);
		}
		
		public void validaFormulario(ProgramacaoEntregaDto programacaoEntregaDto) {
			
			
			//Pesquisa unidade acadêmcia, a partir do bairro da seção do elegível.
			UnidadeAcademica unidadeAcademica = unidadeAcademicaService.
			pesquisarPrimeiroPorDescricao(programacaoEntregaDto.getBairroSecaoElegivel());
			
			if ((unidadeAcademica == null)||(unidadeAcademica.getDescricao() == null) ||(unidadeAcademica.getDescricao() == "")) {
				throw new GlobalExceptionHandler("Elegível sem o bairro da seção!", 0);
			}
			
			//Preenche a descrição e o id da ua.
			programacaoEntregaDto.setIdUa(unidadeAcademica.getId());
			programacaoEntregaDto.setUaPrevista(unidadeAcademica.getDescricao());
			
			//Pesquisa se existe valor marmita e recupera o mais atual
			List<ValorMarmita> listaValorMarmita = valorMarmitaService.
			pesquisarValorMaisAtual(programacaoEntregaDto.getMesAnoProgramacao());
			
			//listaValorMarmita = null;
			if ((listaValorMarmita == null)||(listaValorMarmita.get(0) == null)) {
				throw new GlobalExceptionHandler("Não existe valor marmita cadastrado para o ano e mês solicitado!", 0);
			}
			
			//Preenche o id do valor.
			programacaoEntregaDto.setIdValor(listaValorMarmita.get(0).getId());
			
			//Gera ano e mês correntes.
			int mesAtual = LocalDate.now().getMonthValue();
			int anoAtual = LocalDate.now().getYear();
			
			//Recupera ano e mês informados.
			int mesProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getMonthValue();
			int anoProgramacao = programacaoEntregaDto.getMesAnoProgramacao().getYear();
			
			//Verifica se ano informado é menor que ano corrente.
			if (anoProgramacao < anoAtual ) {
				throw new GlobalExceptionHandler("Ano da programção deve ser maior ou igual ao ano corrente!", 0);
			}
			
			//Verifica se mês informado é menor que mês corrente.
			if (mesProgramacao < mesAtual ) {
				throw new GlobalExceptionHandler("Mês da programção deve ser maior ou igual ao mês corrente!", 0);
			}
			
			
			//Recupera dados dos elegíveis na visão.
			Optional<VwSisbenElegibilidade> VwSisbenElegibilidade = vwSisbenElegibilidadeService.
			ObterPorMatricula(programacaoEntregaDto.getMatriculaColaborador());
			
			//Verifica se funcionário é elegível.
			if (!VwSisbenElegibilidade.isPresent()) {
				throw new GlobalExceptionHandler("Funcionário não elegível!", 0);
			}
			
			//Verifica se para aquele mês, ano e matrícula já existe programação.
			Long contExisteProgramacao = programacaoEntregaRepository.
					    pesquisaProgramacaoEntregaAnoMesMatricula(programacaoEntregaDto.
					    		                         getMesAnoProgramacao().toString(),
					    		                         programacaoEntregaDto.getMatriculaColaborador());
			
			if (contExisteProgramacao > 0) {
				throw new GlobalExceptionHandler("Já existe programação para esta matrícula neste ano e mês !", 0);
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
	
	
}
