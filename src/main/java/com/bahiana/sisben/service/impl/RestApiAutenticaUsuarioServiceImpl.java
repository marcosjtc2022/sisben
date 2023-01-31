package com.bahiana.sisben.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bahiana.sisben.api.dto.AutenticaApiDto;
import com.bahiana.sisben.api.dto.UsuarioDto;
import com.bahiana.sisben.service.RestApiAutenticaUsuarioService;

public class RestApiAutenticaUsuarioServiceImpl implements RestApiAutenticaUsuarioService {
	
//	@Value("${app.uriApiAutentica}")
//    private String uri;
//	
	final String uri = "http://10.71.50.57/Api.Fundacao/api/Autentica/Login";

	@Override
	public ResponseEntity<AutenticaApiDto> AutenticarUsuarioInterno(UsuarioDto usuarioDto) throws HttpClientErrorException {
		
		RestTemplate restTemplate = new RestTemplate();
		AutenticaApiDto autenticaApiDto = new AutenticaApiDto(usuarioDto.getMatriculaColaborador(), usuarioDto.getSenhaUsuario());
		
		ResponseEntity<AutenticaApiDto> result = restTemplate.postForEntity(uri, autenticaApiDto, AutenticaApiDto.class);
		return result;
	}

}
