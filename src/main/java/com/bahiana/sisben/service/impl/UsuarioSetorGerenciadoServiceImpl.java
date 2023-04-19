package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.VwSisbenSetor;
import com.bahiana.sisben.model.entity.repository.UsuarioSetorGerenciadoRepository;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;
import com.bahiana.sisben.service.VwSisbenSetorService;

public class UsuarioSetorGerenciadoServiceImpl implements UsuarioSetorGerenciadoService  {
	
	@Autowired
	UsuarioSetorGerenciadoRepository usuarioSetorGerenciadoRepository;
	
	@Autowired
	VwSisbenSetorService vwSisbenSetorService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioSetorGerenciadoService usuarioSetorGerenciadoService;

	
	@GetMapping("/obterPorCodSetor/{codSetor}")
	public VwSisbenSetor obterPorCodSetor(@PathVariable("codSetor") String codSetor) {
		return vwSisbenSetorService.ObterPorCodigo(codSetor);
	}
	

	@Override
	public List<UsuarioSetorGerenciado> salvar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
		List<UsuarioSetorGerenciado> listaUsuarioSetorGerenciado = 
				concatenaCamposTabelaSetor(usuarioSetorGerenciadoDto);
		
		
		for (UsuarioSetorGerenciado usuarioSetorGerenciado : listaUsuarioSetorGerenciado) {
				this.usuarioSetorGerenciadoRepository.save(usuarioSetorGerenciado); 
	    }
		
		return listaUsuarioSetorGerenciado;
	}
	
	//this.programacaoEntregaRepository.deleteById(programacaoEntregaLinha.getId());
	
	
	@SuppressWarnings("removal")
	public List<UsuarioSetorGerenciado> concatenaCamposTabelaSetor(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
	
		List<UsuarioSetorGerenciado> listaUsuarioSetorGerenciado = new ArrayList<>();
		
		//Recupera os setores informados para a matrícula.
		String[] tabelaCodSetores = usuarioSetorGerenciadoDto.getTabelaCodSetores().split(",");
		//LocalDateTime dataModificacao = LocalDateTime.now();
		
		
			 for (String codSetor : tabelaCodSetores) {
				
				UsuarioSetorGerenciado usuarioSetorGerenciado = new UsuarioSetorGerenciado();
				
				VwSisbenSetor vwSisbenSetor  = vwSisbenSetorService.ObterPorCodigo(codSetor);
				usuarioSetorGerenciado.setCodSetor(codSetor);
				usuarioSetorGerenciado.setDescricao(vwSisbenSetor.getDescrSetor());
				usuarioSetorGerenciado.setIdUsuarioLider(usuarioSetorGerenciadoDto.getIdUsuarioLider());
				
				listaUsuarioSetorGerenciado.add(usuarioSetorGerenciado);
				
			}
		
	  return listaUsuarioSetorGerenciado;
	    
  }

	

	@Override
	public void apagar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
		
		//Recupera os setores informados para a matrícula.
		String[] tabelaIdUsuarioSetores = usuarioSetorGerenciadoDto.getTabelaIdUsuarioSetores().split(",");
		
		for (String idSetor : tabelaIdUsuarioSetores) {
				this.usuarioSetorGerenciadoRepository.deleteById(Long.valueOf(idSetor)); 
	    }
		
		
	}

	@Override
	public List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo(Long idUsuarioLider) {
		return usuarioSetorGerenciadoRepository.listaSetorOrdenadoPorCodigo(idUsuarioLider) ;
	}


	@Override
	public List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao(Long idUsuarioLider) {
		return usuarioSetorGerenciadoRepository.listaSetorOrdenadoPorDescricao(idUsuarioLider);
	}


	@Override
	public List<UsuarioSetorGerenciado> alterar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
		this.apagar(usuarioSetorGerenciadoDto);
		List<UsuarioSetorGerenciado> listaUsuarioSetorGerenciado = this.salvar(usuarioSetorGerenciadoDto);
		
		return listaUsuarioSetorGerenciado;
	}


	@Override
	public long pesquisaUsuarioSetorGerenciado(Long idUsuarioLider) {
		return usuarioSetorGerenciadoRepository.pesquisaUsuarioSetorGerenciado(idUsuarioLider);
	}


	@Override
	public Optional<UsuarioSetorGerenciado> obterPorId(Long id) {
		return usuarioSetorGerenciadoRepository.findById(id);
	}
	
	public List<String> concatenaSetoresLider(String strIdUsuarioLogado ){
		
         Long idUsuarioLogado = Long.valueOf(strIdUsuarioLogado);
		
		 Usuario usuario =  usuarioService.obterPorId(idUsuarioLogado).get();
   	 
	   	 List<String> listStrCodSetor = new ArrayList();
	   	 listStrCodSetor.add(usuario.getCodSetor());
	   	 
	   	 List<UsuarioSetorGerenciado> listaSetorGerenciado =
	   	 usuarioSetorGerenciadoService.listaSetorOrdenadoPorCodigo(idUsuarioLogado);
	   	 
	   	   if (listaSetorGerenciado != null) {
	   	
		    	 for (UsuarioSetorGerenciado setorGerenciado : listaSetorGerenciado) {
		    		 
		    		 listStrCodSetor.add(setorGerenciado.getCodSetor());
		    		 
		    	 }
		    	 
		  } 
	   	   
	   	 return listStrCodSetor;  
	}


}
