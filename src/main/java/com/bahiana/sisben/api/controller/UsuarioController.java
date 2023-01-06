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

import com.bahiana.sisben.api.dto.FornecedorDto;
import com.bahiana.sisben.api.dto.SuspensaoElegibilidadeDto;
import com.bahiana.sisben.api.dto.UsuarioDto;
import com.bahiana.sisben.exception.RegraNegocioException;
import com.bahiana.sisben.model.entity.Fornecedor;
import com.bahiana.sisben.model.entity.SuspensaoElegibilidade;
import com.bahiana.sisben.model.entity.Usuario;
import com.bahiana.sisben.service.ProgramacaoEntregaService;
import com.bahiana.sisben.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProgramacaoEntregaService programacaoEntregaService;
	
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDto dto ) {
		
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmailUsuario(),dto.getSenhaUsuario());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch(RegraNegocioException e) {
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
	
	//Usado para recuperar recurso no servidor.
	//Quando o "id" é passado na url o valor é colocado na variável "id".
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
	

}
