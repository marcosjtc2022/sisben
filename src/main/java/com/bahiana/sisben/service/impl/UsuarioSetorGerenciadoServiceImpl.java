package com.bahiana.sisben.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.VwSisbenSetor;
import com.bahiana.sisben.model.entity.repository.UsuarioSetorGerenciadoRepository;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;
import com.bahiana.sisben.service.VwSisbenSetorService;

public class UsuarioSetorGerenciadoServiceImpl implements UsuarioSetorGerenciadoService  {
	
	@Autowired
	UsuarioSetorGerenciadoRepository usuarioSetorGerenciadoRepository;
	
	@Autowired
	VwSisbenSetorService vwSisbenSetorService;
	
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
		String[] tabelaSetor = usuarioSetorGerenciadoDto.getTabelaSetores().split(",");
		//LocalDateTime dataModificacao = LocalDateTime.now();
		
		
			 for (String codSetor : tabelaSetor) {
				
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
		String[] tabelaSetor = usuarioSetorGerenciadoDto.getTabelaSetores().split(",");
		
		for (String idSetor : tabelaSetor) {
				this.usuarioSetorGerenciadoRepository.deleteById(Long.valueOf(idSetor)); 
	    }
		
		
	}

	@Override
	public List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo() {
		return usuarioSetorGerenciadoRepository.listaSetorOrdenadoPorCodigo() ;
	}


	@Override
	public List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao() {
		return usuarioSetorGerenciadoRepository.listaSetorOrdenadoPorDescricao();
	}


}
