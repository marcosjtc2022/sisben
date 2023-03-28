package com.bahiana.sisben.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bahiana.sisben.api.dto.ProgramacaoEntregaDto;
import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.model.entity.Calendario;
import com.bahiana.sisben.model.entity.ProgramacaoEntrega;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.ValorMarmita;
import com.bahiana.sisben.service.UsuarioSetorGerenciadoService;

public class UsuarioSetorGerenciadoServiceImpl implements UsuarioSetorGerenciadoService  {

	@Override
	public ValorMarmita salvar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
		List<UsuarioSetorGerenciado> listaUsuarioSetorGerenciado = 
				concatenaCamposTabelaSetor(usuarioSetorGerenciadoDto);
		
		return null;
	}
	
	@SuppressWarnings("removal")
	public List<UsuarioSetorGerenciado> concatenaCamposTabelaSetor(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto) {
	
	List<UsuarioSetorGerenciado> listaUsuarioSetorGerenciado = new ArrayList<>();
	
	
	
	//Recupera os setores informados para a matrícula.
	String[] tabelaSetor = usuarioSetorGerenciadoDto.getTabelaSetores().split(",");
	String[] linha		= null;
	String matricula;
	//String codSetor;
	
	LocalDateTime dataModificacao = LocalDateTime.now();
	
	
		 for (String codSetor : tabelaSetor) {
			
			UsuarioSetorGerenciado usuarioSetorGerenciado = new UsuarioSetorGerenciado();
			
			usuarioSetorGerenciado.setCodSetor(codSetor);
			usuarioSetorGerenciado.setIdUsuarioLider(usuarioSetorGerenciadoDto.getIdUsuarioLider());
			
			listaUsuarioSetorGerenciado.add(usuarioSetorGerenciado);
			
		}
	
	    return listaUsuarioSetorGerenciado;
	    
  }


}
