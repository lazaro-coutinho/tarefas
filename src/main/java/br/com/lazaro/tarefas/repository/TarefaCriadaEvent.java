package br.com.lazaro.tarefas.repository;

import org.springframework.stereotype.Component;

import br.com.lazaro.tarefas.domain.usecase.TarefaEvent;
import br.com.lazaro.tarefas.model.Tarefa;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TarefaCriadaEvent implements TarefaEvent {
	
	private final TarefaRepository tarefaRepository;
	
	@Override
	public void execute(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}

}
