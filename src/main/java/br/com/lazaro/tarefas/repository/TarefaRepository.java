package br.com.lazaro.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lazaro.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
}
