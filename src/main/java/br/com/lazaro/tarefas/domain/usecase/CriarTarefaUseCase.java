package br.com.lazaro.tarefas.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.lazaro.tarefas.domain.service.CriarTarefaAction;
import br.com.lazaro.tarefas.model.Tarefa;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CriarTarefaUseCase {
	
	private final List<TarefaEvent> tarefaEvents;
	
	public Tarefa execute(TarefaDto tarefaDto) {
		
		Tarefa tarefa = new Tarefa(
				tarefaDto.getNome(),
				tarefaDto.getDescricao());
		
		tarefa.alterarStatus(new CriarTarefaAction());
		
		tarefaEvents.forEach(e -> {
			System.out.println("Chamando serviços...");
			e.execute(tarefa);
		});
		
		return tarefa;
		
	}

}
