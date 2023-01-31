package com.bahiana.sisben.service;

import org.springframework.http.ResponseEntity;

import com.bahiana.sisben.api.dto.AutenticaApiDto;
import com.bahiana.sisben.api.dto.UsuarioDto;

public interface RestApiAutenticaUsuarioService {
	
	ResponseEntity<AutenticaApiDto> AutenticarUsuarioInterno(UsuarioDto usuarioDto );

}
