package com.bahiana.sisben.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.repository.UsuarioRepository;

//@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public SecurityUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public SecurityUserDetailsService() {
		// TODO Auto-generated constructor stub
	}
    
	//Recebe o email e pesquisa no banco. (Verfiicar quando informar a matrícula).
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = usuarioRepository
				.findByEmailUsuario(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado."));
		
		return User.builder() //Implementa UserDetails
				.username(usuarioEncontrado.getEmailUsuario())
				.password(usuarioEncontrado.getSenhaUsuario())
				.roles("USER") //ROLE padrão do UserDetails.
				.build();
	}

}
