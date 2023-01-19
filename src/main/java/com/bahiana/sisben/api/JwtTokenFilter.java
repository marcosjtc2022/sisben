package com.bahiana.sisben.api;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bahiana.sisben.service.JwtService;
import com.bahiana.sisben.service.impl.SecurityUserDetailsService;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private SecurityUserDetailsService userDetailsService;

	public JwtTokenFilter(
			JwtService jwtService,
			SecurityUserDetailsService userDetailsService
			) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	//Função recupera cabeçalho http.
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		//Front end envia um header com o nome Authorization 
		String authorization = request.getHeader("Authorization");
		
		//"Bearer","eyJhbGciOiJIUzUxMiJ9.eyJ..."
		
		//O token é autenticação do tipo Bearer
		if(authorization != null && authorization.startsWith("Bearer")) {
			
			//Separa a palavra Bearer da outra parte do token. Posição 1 é o token.
			String token = authorization.split(" ")[1];
			boolean isTokenValid = jwtService.isTokenValido(token);
			
			//Verifica se o token é valido.
			if(isTokenValid) {
				String login = jwtService.obterLoginUsuario(token);
				UserDetails usuarioAutenticado = userDetailsService.loadUserByUsername(login);
				
				//Coloca o usuário dentro do contexto do spring security.
				UsernamePasswordAuthenticationToken user = 
						new UsernamePasswordAuthenticationToken(
								usuarioAutenticado, null, usuarioAutenticado.getAuthorities());
				
				user.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );
				
				SecurityContextHolder.getContext().setAuthentication(user);
				
			}
		}
		
		//Dá continuidade à execução da requisição
		filterChain.doFilter(request, response);
	}

}
