package com.bahiana.sisben.api.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bahiana.sisben.api.dto.AutenticaApiDto;
import com.bahiana.sisben.api.dto.TokenDto;
import com.bahiana.sisben.api.dto.UsuarioDto;
import com.bahiana.sisben.exception.ErroAutenticacao;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.model.entity.VwSisbenFuncionario;
import com.bahiana.sisben.service.JwtService;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.service.RestApiAutenticaUsuarioService;
import com.bahiana.sisben.service.UsuarioService;
import com.bahiana.sisben.service.VwSisbenFuncionarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RestApiAutenticaUsuarioService restApiAutenticaUsuarioService;
	
	@Autowired
	private ProgramacaoEntregaService programacaoEntregaService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private VwSisbenFuncionarioService vwSisbenFuncionarioService;
	
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDto dto ) {
		
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmailUsuario(),dto.getSenhaUsuario());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch(RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/autenticarToken")
	public ResponseEntity<?> autenticarToken( @RequestBody UsuarioDto usuarioDto ) {
		
		 String emailUsuario = usuarioDto.getEmailUsuario();  
		if (usuarioDto.getExterno() == false) { //if 1		
		
			try {	
	//			final String uri2 = "http://10.71.50.57/Api.Fundacao/api/Autentica/Login";
	//			AutenticaApiDto autenticaApiDto = new AutenticaApiDto(usuarioDto.getMatriculaColaborador(), usuarioDto.getSenhaUsuario());
	//			RestTemplate restTemplate = new RestTemplate();
	//			ResponseEntity<AutenticaApiDto> result = restTemplate.postForEntity(uri2, autenticaApiDto, AutenticaApiDto.class);
	//			//String status = result.getStatusCode().toString();
				ResponseEntity<AutenticaApiDto> result1 = restApiAutenticaUsuarioService.AutenticarUsuarioInterno(usuarioDto);
			} catch (HttpClientErrorException ex) {
				return ResponseEntity.status(ex.getRawStatusCode()).headers(ex.getResponseHeaders())
	                    .body(ex.getResponseBodyAsString());
	
			}
		
		
//		try {
			
			// String emailUsuario = usuarioDto.getEmailUsuario();  
			
			 //Recuperar dados do funcionário.
//			 if (usuarioDto.getExterno() == false) {
				 Optional<VwSisbenFuncionario> funcionario = vwSisbenFuncionarioService.ObterPorMatricula(usuarioDto.getMatriculaColaborador());
				 
				 emailUsuario = funcionario.get().getEmailFuncionario(); 
	             		 
				 
				//Verificar se funcionário está no banco externo.
				if(funcionario.isPresent()) {
					
					//Verificar se existe no banco do sisben
					Long countUsuario = usuarioService.pesquisaUsuario(usuarioDto.getMatriculaColaborador());
					
					if ((countUsuario == 0)) {
						
////						 //Cria usuário do sisben a partir do funcionário.
						 
//						 if ((usuarioDto.getIdPerfil() != null) && (usuarioDto.getIdPerfil() != 0)) { 
//						     usuarioInterno.setIdPerfil(usuarioDto.getIdPerfil());
//						 }
//						 usuarioInterno.setMatriculaColaborador(funcionario.get().getMatriculaFuncionario());
//						 usuarioInterno.setNomeColaborador(funcionario.get().getNomeFuncionario());
//						 usuarioInterno.setSenhaUsuario(usuarioDto.getSenhaUsuario());
//						 usuarioInterno.setEmailUsuario(funcionario.get().getEmailFuncionario());
//						 usuarioInterno.setExterno(false);
						 
						 UsuarioDto usuarioInterno = new UsuarioDto();
						 usuarioInterno = usuarioService.criaUsuarioInterno(funcionario.get(), usuarioDto);
						 
						 //Salva no banco do sisben.
						 usuarioService.salvar(usuarioInterno);
						 
				  }	 
					 
				} else {
					return new ResponseEntity("Funcionário não encontrado para a matrícula informada!", HttpStatus.BAD_REQUEST);
				}
					
					
				 
		 } // End if 1
		
		try {
			
			Usuario usuarioAutenticado = usuarioService.autenticarToken(emailUsuario, usuarioDto.getSenhaUsuario());
			String token = jwtService.gerarToken(usuarioAutenticado);
			
			TokenDto tokenDto = new TokenDto( usuarioAutenticado.getNomeColaborador(),
					usuarioAutenticado.getId(), usuarioAutenticado.getEmailUsuario(),
					usuarioAutenticado.getIdPerfil(), usuarioAutenticado.getIdUa(), token );
			
			return ResponseEntity.ok(tokenDto);
		}catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping(value =  "/listarOrdenadoPorNome" )
    public List<Usuario> listarOrdenadoPorNome() {
    	return this.usuarioService.listarSimplesOrdenadoNome() ;	  
    }
	
	@GetMapping(value =  "/listarPorFiltroNome" )
	public ResponseEntity<List<Usuario>> listarPorFiltroNome(UsuarioDto usuarioDto) {
		 try {
				
			 //return new ResponseEntity(fornecedorService.listarPorDescricaoOrdenadoDescricao(fornecedorDto), HttpStatus.CREATED);
				return new ResponseEntity(usuarioService.listarPorNomeOrdenadoNome(usuarioDto), HttpStatus.CREATED);
		     } catch (RegraNegocioException e) {
			    return new ResponseEntity<List<Usuario>>(HttpStatus.BAD_REQUEST);
		     }
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDto usuarioDto) {
	  try {
		    Usuario usuario = new Usuario() ;
		    usuario = usuarioService.salvar(usuarioDto);
			return new ResponseEntity(usuario, HttpStatus.CREATED);
	     } catch (RegraNegocioException e) {
		    return ResponseEntity.badRequest().body(e.getMessage());
	     }
    }
	
	
	@Transactional
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UsuarioDto usuarioDto) {
			     try {
			    	        Usuario usuario = new Usuario() ;
			    	        usuarioDto.setId(id);			
			    	        usuario = usuarioService.alterar(usuarioDto);
							return new ResponseEntity(usuario, HttpStatus.CREATED);
					 } catch (RegraNegocioException e) {
						    return ResponseEntity.badRequest().body(e.getMessage());
				     }
   }
	
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		
		//Inserir dados na tabela usuario_setor e colocar pesquisa de registros.
		
		Long countUsuarioEntrega = programacaoEntregaService.pesquisaUsuarioEntrega(id);
		
		if ((countUsuarioEntrega > 0) && (countUsuarioEntrega != null)) {
			return new ResponseEntity("O usuário está vinculado a uma programação entrega!", HttpStatus.BAD_REQUEST);
		}
		
		//entity é o que retorna de ObterPorId
				return usuarioService.obterPorId(id).map(entity -> {					
					usuarioService.deletar(entity);
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet(() -> 
				    new ResponseEntity("Usuário não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping("/obterPorId/{id}")
	public Usuario obterPorId(@PathVariable("id") Long id) {
		
		Optional<Usuario> usuario = usuarioService.obterPorId(id);
		return  usuario.get();	
				
	}
	
	
	@GetMapping(value =  "/listarUsuarioFornecedor" )
    public List<Usuario> listarUsuarioFornecedor() {
    	return this.usuarioService.listarUsuarioFornecedor();  
    }
	
	
}
