package com.bahiana.sisben.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.bahiana.sisben.exception.ErroAutenticacao;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.repository.UsuarioRepository;
import com.bahiana.sisben.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService  {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario  = usuarioRepository.findByEmailUsuario(email);
		
		
		
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado");
		}
		
		if(!usuario.get().getSenhaUsuario().equals(senha)) {	
			throw new ErroAutenticacao("Senha inválida");
		}
		
		return usuario.get();
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmailUsuario(String emailUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public long pesquisaPerfil(Long idPerfil) {
        Long countPerfil =  this.usuarioRepository.pesquisaPerfil(idPerfil);
		
	    return  countPerfil;
	}

	@Override
	public long pesquisaFornecedor(Long id) {
        Long countFornecedor =  this.usuarioRepository.pesquisaFornecedor(id);
		
	    return  countFornecedor;
	}

}
