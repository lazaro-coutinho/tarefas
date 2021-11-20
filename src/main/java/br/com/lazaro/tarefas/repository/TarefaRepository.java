package br.com.lazaro.tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lazaro.tarefas.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	@Query("select t from Tarefa t where upper(t.nome) like %:nome% and t.ativo = true order by id")
	public List<Tarefa> find(@Param("nome") String nome);
	
}
