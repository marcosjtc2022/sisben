package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;

public interface UsuarioSetorGerenciadoRepository extends JpaRepository<UsuarioSetorGerenciado,Long>  {
	
	@Query("SELECT sg FROM UsuarioSetorGerenciado sg Order by sg.codSetor")
	List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo();

	@Query("SELECT sg FROM UsuarioSetorGerenciado sg Order by sg.descricao")
	List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao();

}
