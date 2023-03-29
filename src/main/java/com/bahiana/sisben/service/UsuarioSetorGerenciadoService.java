package com.bahiana.sisben.service;

import java.util.List;

import com.bahiana.sisben.api.dto.UsuarioSetorGerenciadoDto;
import com.bahiana.sisben.model.entity.UsuarioSetorGerenciado;
import com.bahiana.sisben.model.entity.VwSisbenSetor;

public interface UsuarioSetorGerenciadoService {
	
	
			
	List<UsuarioSetorGerenciado> salvar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto);
	
    List<UsuarioSetorGerenciado> listaSetorOrdenadoPorCodigo();
	
	List<UsuarioSetorGerenciado> listaSetorOrdenadoPorDescricao();
		
//		ValorMarmita alterar(ValorMarmitaDto valorMarmitaDto);
//		
		void apagar(UsuarioSetorGerenciadoDto usuarioSetorGerenciadoDto);
//		
//		Optional<ValorMarmita> obterPorId(Long id);
//		
//		List<ValorMarmita> listarSimplesOrdenadoValor();
//		
//	
}
