package br.com.lazaro.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.lazaro.tarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.login = :login and u.ativo = true")
	public Usuario findUserByLogin(String login);

}
