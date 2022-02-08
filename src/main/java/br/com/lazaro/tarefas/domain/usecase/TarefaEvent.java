package br.com.lazaro.tarefas.domain.usecase;

import br.com.lazaro.tarefas.model.Tarefa;

public interface TarefaEvent {
	
	public void execute(Tarefa tarefa);

}
