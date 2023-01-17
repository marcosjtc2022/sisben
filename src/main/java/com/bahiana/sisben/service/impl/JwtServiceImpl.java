package com.bahiana.sisben.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public class JwtServiceImpl implements JwtService  {
	
	//Injeta valor do application.
	@Value("${jwt.expiracao}")
	private String expiracao;
	
	@Value("${jwt.chave-assinatura}")
	private String chaveAssinatura;

	@Override
	public String gerarToken(Usuario usuario) {
		long exp = Long.valueOf(expiracao);
		
		//Adicionar os minutos a hora atual.
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return null;
	}

	@Override
	public Claims obterClaims(String token) throws ExpiredJwtException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTokenValido(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String obterLoginUsuario(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
