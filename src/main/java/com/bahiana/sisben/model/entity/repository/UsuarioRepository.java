package com.bahiana.sisben.model.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
     boolean existsByEmailUsuario(String emailUsuario);
	
	//Optional evita tratamento de null pointer exception
	Optional<Usuario> findByEmailUsuario(String emailUsuario);
	
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.idPerfil=:idPerfil")
	long pesquisaPerfil(@Param("idPerfil") Long idPerfil);
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.idFornecedor=:id")
	long pesquisaFornecedor(@Param("id") Long id);

}
