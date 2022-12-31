package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bahiana.sisben.api.dto.SuspensaoElegibilidadeDto;
import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;

public interface SuspensaoElegibilidadeService {
	
    Page<SuspensaoElegibilidade> listarPaginado(Pageable pageable);
	
	Page<SuspensaoElegibilidade> listarPaginadoSimples( );
	
	SuspensaoElegibilidade salvar(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
	SuspensaoElegibilidade alterar(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
	void deletar(SuspensaoElegibilidade SuspensaoElegibilidade);
	
	Optional<SuspensaoElegibilidade> obterPorId(Long id);
	
	//List<SuspensaoElegibilidade> findByOrderByNomeColaboradorAsc();
	List<SuspensaoElegibilidade> listarSimplesOrdenadoNomeColaborador();
	
    //List<SuspensaoElegibilidade> findByNomeColaboradorContainingOrderByNomeColaborador(String nomeColaborador);	
	List<SuspensaoElegibilidade> listarPorNomeOrdenadoNome(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	
    //List<SuspensaoElegibilidade> findByMatriculaColaboradorOrderByNomeColaborador(String matriculaColaborador); 	
	List<SuspensaoElegibilidade> listarPorMatriculaOrdenadoNome(SuspensaoElegibilidadeDto SuspensaoElegibilidadeDto);
	


}
