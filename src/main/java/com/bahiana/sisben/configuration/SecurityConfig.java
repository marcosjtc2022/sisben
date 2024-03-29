package com.bahiana.sisben.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import com.bahiana.sisben.api.JwtTokenFilter;
import com.bahiana.sisben.service.JwtService;
import com.bahiana.sisben.service.impl.SecurityUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	@Bean //Criptografa a senha do usuário.
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	//Vai ser executado uma vez por requisição. (OncePerRequestFilter)
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter(jwtService, userDetailsService);
	}
	
	//Responsável por verificar a senha.
	//Autenticação via UserDetails
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService) // Pega o usuário 
			.passwordEncoder(passwordEncoder()); //Compara a senha.
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				//.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				//.antMatchers(HttpMethod.POST, "/usuarios/autenticar").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios/autenticarToken").permitAll()
				//.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
				.anyRequest().authenticated()	
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.addFilterBefore( jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class )
			;

	}

}
