package com.bahiana.sisben.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.SuspensaoElegibilidadeDto;
import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;

public interface SuspensaoElegibilidadeService {
	
    Page<SuspensaoElegibilidade> listarPaginado(Pageable pageable);
	
	Page<SuspensaoElegibilidade> listarPaginadoSimples( );
	
	SuspensaoElegibilidade salvar(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
	SuspensaoElegibilidade alterar(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
	void deletar(SuspensaoElegibilidade SuspensaoElegibilidade);
	
	Optional<SuspensaoElegibilidade> obterPorId(Long id);
	
	Optional<SuspensaoElegibilidade> obterPorMatriculaColaborador(String matriculaColaborador);
	
	//List<SuspensaoElegibilidade> findByOrderByNomeColaboradorAsc();
	List<SuspensaoElegibilidade> listarSimplesOrdenadoNomeColaborador();
	
    //List<SuspensaoElegibilidade> findByNomeColaboradorContainingOrderByNomeColaborador(String nomeColaborador);	
	List<SuspensaoElegibilidade> listarPorNomeOrdenadoNome(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
//    //List<SuspensaoElegibilidade> findByMatriculaColaboradorOrderByNomeColaborador(String matriculaColaborador); 	
//	SuspensaoElegibilidade pesquisarPorMatricula(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
	Long pesquisarSuspensao(LocalDate dataSolicitacao, String matriculaColaborador );
	


}
