package com.bahiana.sisben.model.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;

public interface UsuarioSetorGerenciadoRepository extends JpaRepository<UsuarioSetorGerenciado,Long>  {
	
	@Query("SELECT sg FROM UsuarioSetorGerenciado sg "
			+ " WHERE sg.idUsuarioLider=:idUsuarioLider "
			+ " Order by sg.descricao")
	List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo(@Param("idUsuarioLider") Long idUsuarioLider);

	@Query("SELECT sg FROM UsuarioSetorGerenciado sg "
			+ " WHERE sg.idUsuarioLider=:idUsuarioLider "
			+ " Order by sg.descricao")
	List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao(@Param("idUsuarioLider") Long idUsuarioLider);
	
	@Query("SELECT COUNT(u) FROM UsuarioSetorGerenciado u WHERE u.idUsuarioLider=:idUsuarioLider")
	long pesquisaUsuarioSetorGerenciado(@Param("idUsuarioLider") Long idUsuarioLider);
	

}
