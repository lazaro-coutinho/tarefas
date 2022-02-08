package br.com.lazaro.tarefas.domain.service;

import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.model.Status;

public class CriarTarefaAction {
	
	public Status execute(Tarefa tarefa) {
		Status statusAtual = tarefa.getStatus();
		if (Status.CRIADA.equals(statusAtual)) throw new RuntimeException();
		if (Status.FINALIZADA.equals(statusAtual)) throw new RuntimeException();
		if (Status.ARQUIVADA.equals(statusAtual)) throw new RuntimeException();
		return Status.CRIADA;
	}

}
