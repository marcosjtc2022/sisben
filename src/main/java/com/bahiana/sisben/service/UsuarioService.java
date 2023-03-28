package com.bahiana.sisben.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bahiana.sisben.api.dto.UsuarioDto;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;

@Service
public interface UsuarioService {
	
    Usuario autenticar(String email, String senha);
    
    Usuario autenticarToken(String email, String senha);
	
	Usuario salvar(UsuarioDto usuarioDto);
	
	Optional<Usuario> obterPorId(Long id);
	
	long pesquisaPerfil(Long idPerfil);
	
	long pesquisaFornecedor(Long id);
	
    long pesquisaUsuario(String matriculaColaborador);
	
	Usuario alterar(UsuarioDto usuarioDto);
	
	void deletar(Usuario usuario);
	
	List<Usuario> listarSimplesOrdenadoNome();
	
	List<Usuario> listarPorNomeOrdenadoNome(UsuarioDto usuarioDto);
	
	UsuarioDto criaUsuarioInterno(VwSisbenFuncionario funcionario, UsuarioDto usuarioDto);
	
	List<Usuario> listarUsuarioFornecedor();
	
	Usuario obterPorMatriculaColaborador(String matriculaColaborador);


}
