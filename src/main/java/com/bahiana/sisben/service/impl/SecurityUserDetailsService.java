package com.bahiana.sisben.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.repository.UsuarioRepository;

public class SecurityUserDetailsService implements UserDetailsService {
	
	private UsuarioRepository usuarioRepository;

	public SecurityUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = usuarioRepository
				.findByEmailUsuario(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado."));
		
		return User.builder()
				.username(usuarioEncontrado.getEmailUsuario())
				.password(usuarioEncontrado.getSenhaUsuario())
				.roles("USER")
				.build();
	}

}
