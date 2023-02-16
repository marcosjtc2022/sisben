package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bahiana.sisben.api.dto.UsuarioDto;
import com.bahiana.sisben.exception.ErroAutenticacao;
import com.bahiana.sisben.exception.GlobalExceptionHandler;
//import com.bahiana.sisben.exception.GlobalExceptionHandler;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
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
	public Optional<Usuario> obterPorId(Long id) {
		return usuarioRepository.findById(id);
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

	@Override
	public Usuario salvar(UsuarioDto usuarioDto) {
		Usuario usuario = UsuarioServiceImpl.from(usuarioDto);
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario alterar(UsuarioDto usuarioDto) {
		Usuario usuario = UsuarioServiceImpl.from(usuarioDto);
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarSimplesOrdenadoNome() {
		return usuarioRepository.findByOrderByNomeColaboradorAsc();
	}

	@Override
	public List<Usuario> listarPorNomeOrdenadoNome(UsuarioDto usuarioDto) {
		return usuarioRepository.findByNomeColaboradorContainingOrderByNomeColaborador(usuarioDto.getNomeColaborador());
	}
	
	@Override
	public void deletar(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	
	public static Usuario from(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		LocalDateTime dataModificacao = LocalDateTime.now();
		usuarioDto.setDataUltimaModificacao(dataModificacao);
		BeanUtils.copyProperties(usuarioDto, usuario);
		//usuario.setMatriculaColaborador(Long.parseLong(usuarioDto.getMatriculaColaborador()));
		
		return usuario;
	}
	
	//@Override
	public void validarEmailUsuario(String emailUsuario) {
			// TODO Auto-generated method stub
	}

	@Override
	public Usuario autenticarToken(String email, String senha) {
     Optional<Usuario> usuario  = usuarioRepository.findByEmailUsuario(email);
     
		if (!usuario.isPresent()) {
			//throw new ErroAutenticacao("Usuário não encontrado para o email informado");
			throw new GlobalExceptionHandler("Usuário não encontrado para o email informado");
		}
		
		if(!usuario.get().getSenhaUsuario().equals(senha)) {	
			//throw new ErroAutenticacao("Senha inválida");
			throw new GlobalExceptionHandler("Senha inválida");
		}
		
		return usuario.get();
	}
	
//	public Usuario validarEinserirUsuarioInterno(Optional<VwSisbenFuncionario> funcionario,UsuarioDto usuarioDto) {
//		 UsuarioDto usuarioInterno = new UsuarioDto();
////		 usuarioInterno.setIdPerfil(usuarioDto.getIdPerfil());
////		 usuarioInterno.setMatriculaColaborador(Long.parseLong(funcionario.get().getMatriculaFuncionario()));
////		 usuarioInterno.setNomeColaborador(funcionario.get().getNomeFuncionario());
////		 usuarioInterno.setSenhaUsuario(usuarioDto.getSenhaUsuario());
////		 usuarioInterno.setEmailUsuario(funcionario.get().getEmailFuncionario());
////		 usuarioInterno.setExterno(false);
//		
//		return null;
//    }

	@Override
	public long pesquisaUsuario(String matriculaColaborador) {
		Long countUsuario =  this.usuarioRepository.pesquisaUsuario(matriculaColaborador);
	    return  countUsuario;
	}

	@Override
	public UsuarioDto criaUsuarioInterno(VwSisbenFuncionario funcionario, UsuarioDto usuarioDto) {
		 //Cria usuário do sisben a partir do funcionário.
		 UsuarioDto usuarioInterno = new UsuarioDto();
		 if ((usuarioDto.getIdPerfil() != null) && (usuarioDto.getIdPerfil() != 0)) { 
		     usuarioInterno.setIdPerfil(usuarioDto.getIdPerfil());
		 }
		 usuarioInterno.setMatriculaColaborador(funcionario.getMatriculaFuncionario());
		 usuarioInterno.setNomeColaborador(funcionario.getNomeFuncionario());
		 usuarioInterno.setSenhaUsuario(usuarioDto.getSenhaUsuario());
		 usuarioInterno.setEmailUsuario(funcionario.getEmailFuncionario());
		 usuarioInterno.setExterno(false);
		 
		return usuarioInterno;
	}

	

}
