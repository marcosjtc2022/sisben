package com.bahiana.sisben.service;

import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.bahiana.sisben.model.entity.Usuario;

@Service
public interface UsuarioService {
	
Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmailUsuario(String emailUsuario);
	
	//Retorna um optiona vazio caso não exista.
	Optional<Usuario> obterPorId(Long id);
	
	long pesquisaPerfil(Long idPerfil);


}
