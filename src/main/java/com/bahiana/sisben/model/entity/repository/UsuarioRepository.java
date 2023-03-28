package com.bahiana.sisben.model.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bahiana.sisben.api.dto.FuncionarioDto;
import com.bahiana.sisben.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
     boolean existsByEmailUsuario(String emailUsuario);
	
	//Optional evita tratamento de null pointer exception
	Optional<Usuario> findByEmailUsuario(String emailUsuario);
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.idPerfil=:idPerfil")
	long pesquisaPerfil(@Param("idPerfil") Long idPerfil);
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.idFornecedor=:id")
	long pesquisaFornecedor(@Param("id") Long id);
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.matriculaColaborador=:matriculaColaborador")
	long pesquisaUsuario(@Param("matriculaColaborador") String matriculaColaborador);
	
    List<Usuario> findByNomeColaboradorContainingOrderByNomeColaborador(String nomeColaborador);
	
	List<Usuario> findByOrderByNomeColaboradorAsc();
		
	
	@Query(" select u "
		 + " from Usuario u, Fornecedor f "
		 + " where u.idFornecedor = f.id"
		 + " order by u.nomeColaborador  ")
	List<Usuario> listarUsuarioFornecedor();
	
	//Optional evita tratamento de null pointer exception
	Optional<Usuario> findByMatriculaColaborador(String matriculaColaborador);

}
