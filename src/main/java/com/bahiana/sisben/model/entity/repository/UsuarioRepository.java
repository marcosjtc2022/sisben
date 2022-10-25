package com.bahiana.sisben.model.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bahiana.sisben.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
     boolean existsByEmailUsuario(String emailUsuario);
	
	//Optional evita tratamento de null pointer exception
	Optional<Usuario> findByEmailUsuario(String emailUsuario);

}
