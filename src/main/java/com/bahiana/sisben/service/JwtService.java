package com.bahiana.sisben.service;

import com.bahiana.sisben.model.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {
	
    String gerarToken(Usuario usuario);
	
    //Informações do token
    Claims obterClaims(String token) throws ExpiredJwtException;
	
	boolean isTokenValido(String token);
	
	String obterLoginUsuario( String token );

}
