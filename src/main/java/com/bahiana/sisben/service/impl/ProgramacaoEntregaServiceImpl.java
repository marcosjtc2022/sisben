package com.bahiana.sisben.service.impl;

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

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDTO;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.repository.ProgramacaoEntregaRepository;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.specification.ProgramacaoEntregaSpecification;


@Service
public class ProgramacaoEntregaServiceImpl implements ProgramacaoEntregaService   {

	@Autowired
	ProgramacaoEntregaRepository programacaoEntregaRepository;
	
	
	@Override
	@Transactional //Abre uma transação. Ao final se ocorrer tudo bem faz commit. No caso de erro faz rollback.
	public ProgramacaoEntrega salvar(ProgramacaoEntregaDTO programacaoEntregaDto) {
		//ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
		 ProgramacaoEntrega programacaoEntrega = ProgramacaoEntregaServiceImpl.from(programacaoEntregaDto);
		 return programacaoEntregaRepository.save(programacaoEntrega);
	}
	
	@Override
	public ProgramacaoEntrega toProgramacaoEntrega(ProgramacaoEntregaDTO programacaoEntregaDto) {
		
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
	public ProgramacaoEntrega salvarLote(ProgramacaoEntregaDTO programacaoEntregaDto, char operacao) {
		
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
		Long matriculaColaborador = null;
		String uaPrevista = null;
		String uaRealizada = null;
		LocalDateTime dataEntrega = null;
		LocalDateTime dataSolicitacao = null;
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
				matriculaColaborador =  new Long(linha[0]);
				uaPrevista = linha[1];
				uaRealizada = linha[2];
				//dataEntrega = utilDataHora.trataDatas(linha[3]);   //linha[3];
				//dataSolicitacao = utilDataHora.trataDatas(linha[4]);
				//LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55");
//				dataEntrega2 = linha[3];
				dataEntrega = LocalDateTime.parse(linha[3]+ "T11:00:00");
				dataSolicitacao = LocalDateTime.parse(linha[4]+ "T11:00:00");
				idUa = new Long(linha[5]);
				idJustificativa = new Long(linha[6]);
				idUsuario = new Long(linha[7]);
				idValor = new Long(linha[8]);
				solicExtra = true; //linha[9];
				stAprov = true; //linha[10];
			} else {
				id = new Long(linha[0]);
				matriculaColaborador =  new Long(linha[1]);
				uaPrevista = linha[2];
				uaRealizada = linha[3];
				//dataEntrega = utilDataHora.trataDatas(linha[4]);   //linha[3];
				//dataSolicitacao = utilDataHora.trataDatas(linha[5]);
//				dataEntrega = LocalDateTime.parse(linha[4]);   //linha[3];
//				dataSolicitacao = LocalDateTime.parse(linha[5]);
				dataEntrega = LocalDateTime.parse(linha[4]+ "T11:00:00");
				dataSolicitacao = LocalDateTime.parse(linha[5]+ "T11:00:00");
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
	public ProgramacaoEntrega alterar(ProgramacaoEntregaDTO programacaoEntregaDto) {
		
		 ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
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

	@Override
	public Page<ProgramacaoEntrega> listarProgramacaoPorPeriodo(Pageable pageable , ProgramacaoEntregaDTO programacaoEntregaDTO) {
		
		Page<ProgramacaoEntrega> ProgramacaoEntregaLista =  this.programacaoEntregaRepository.programacaoPorPeriodo
				(pageable,programacaoEntregaDTO.getMatriculaColaborador(),
						  programacaoEntregaDTO.getUaPrevista(),
						  programacaoEntregaDTO.getCodSetor());
		return ProgramacaoEntregaLista;
	}

	@Override
	public ProgramacaoEntrega atualizarLote(ProgramacaoEntregaDTO programacaoEntregaDto, char operacao) {
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
		// TODO Auto-generated method stub
		//Optional<ProgramacaoEntrega> usuario = this.programacaoEntregaRepository.obterPorId(id);
		return this.programacaoEntregaRepository.findById(id);
	}
	
	
	
	// 944 - Método para conversão de classe.
		public static ProgramacaoEntrega from(ProgramacaoEntregaDTO programacaoEntregaDTO) {
			ProgramacaoEntrega programacaoEntrega = new ProgramacaoEntrega();
			BeanUtils.copyProperties(programacaoEntregaDTO, programacaoEntrega);
			
			return programacaoEntrega;
		}
	
//	@DeleteMapping("{id}")
//	public ResponseEntity deletar(@PathVariable("id") Long id) {
//		
//		//entity é o que retorna de ObterPorId
//				return service.obterPorId(id).map(entity -> {					
//					service.deletar(entity);
//					return new ResponseEntity(HttpStatus.NO_CONTENT);
//				}).orElseGet(() -> 
//				    new ResponseEntity("Lançamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
//		
//		
//	}

//	@Override.
//	@Transactional
//	public ProgramacaoEntrega alterarLote(ProgramacaoEntregaDTO programacaoEntregaDto) {
//		 ProgramacaoEntrega programacaoEntrega = toProgramacaoEntrega(programacaoEntregaDto);
//		 List<ProgramacaoEntrega> listaProgramacaoEntrega = concatenaCamposTabela(programacaoEntrega);
//		 for (ProgramacaoEntrega programacaoEntregaLinha : listaProgramacaoEntrega) {
//				//validaEPersisteInclusao(centro);	
//				this.programacaoEntregaRepository.save(programacaoEntregaLinha);
//		 }
//		 return programacaoEntrega;
//	}
	
	
	
	
}
