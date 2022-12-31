package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.SuspensaoElegibilidadeDto;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;
import com.bahiana.sisben.model.entity.repository.SuspensaoElegibilidadeRepository;
import com.bahiana.sisben.service.SuspensaoElegibilidadeService;

public class SuspensaoElegibilidadeServiceImpl implements SuspensaoElegibilidadeService{
	
	@Autowired
	SuspensaoElegibilidadeRepository suspensaoElegibilidadeRepository;

	@Override
	public Page<SuspensaoElegibilidade> listarPaginado(Pageable pageable) {
		return this.suspensaoElegibilidadeRepository.findAll(pageable);
	}

	@Override
	public Page<SuspensaoElegibilidade> listarPaginadoSimples() {
		return this.suspensaoElegibilidadeRepository.findAll(PageRequest.of(0,10 ));
	}

	@Override
	public SuspensaoElegibilidade salvar(SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
		SuspensaoElegibilidade suspensaoElegibilidade = SuspensaoElegibilidadeServiceImpl.from(suspensaoElegibilidadeDto);
		return suspensaoElegibilidadeRepository.save(suspensaoElegibilidade);
	}

	@Override
	public SuspensaoElegibilidade alterar(SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
		SuspensaoElegibilidade suspensaoElegibilidade = SuspensaoElegibilidadeServiceImpl.from(suspensaoElegibilidadeDto);
		return suspensaoElegibilidadeRepository.save(suspensaoElegibilidade);
	}

	@Override
	public void deletar(SuspensaoElegibilidade suspensaoElegibilidade) {
		suspensaoElegibilidadeRepository.delete(suspensaoElegibilidade);
	}

	@Override
	public Optional<SuspensaoElegibilidade> obterPorId(Long id) {
		return suspensaoElegibilidadeRepository.findById(id);
	}

	@Override
	public List<SuspensaoElegibilidade> listarSimplesOrdenadoNomeColaborador() {
		
        List<SuspensaoElegibilidade> suspensaoElegibilidadeLista =  
        this.suspensaoElegibilidadeRepository.findByOrderByNomeColaboradorAsc();
        
		return suspensaoElegibilidadeLista;
	}

	@Override
	public List<SuspensaoElegibilidade> listarPorNomeOrdenadoNome(SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
		
        List<SuspensaoElegibilidade> suspensaoElegibilidadeLista =  
        this.suspensaoElegibilidadeRepository.
        findByNomeColaboradorContainingOrderByNomeColaboradorAsc(suspensaoElegibilidadeDto.getNomeColaborador());
        
		return suspensaoElegibilidadeLista;
	}

	@Override
	public List<SuspensaoElegibilidade> listarPorMatriculaOrdenadoNome(
			SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
		
		List<SuspensaoElegibilidade> suspensaoElegibilidadeLista =  
		        this.suspensaoElegibilidadeRepository.
		        findByMatriculaColaboradorOrderByNomeColaborador(suspensaoElegibilidadeDto.getMatriculaColaborador());
		        
		        
				return suspensaoElegibilidadeLista;
	}
	
	public static SuspensaoElegibilidade from(SuspensaoElegibilidadeDto suspensaoElegibilidadeDto) {
		SuspensaoElegibilidade suspensaoElegibilidade = new SuspensaoElegibilidade();
		LocalDateTime dataModificacao = LocalDateTime.now();
		suspensaoElegibilidadeDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(suspensaoElegibilidadeDto, suspensaoElegibilidade);
		
		return suspensaoElegibilidade;
	}

}
