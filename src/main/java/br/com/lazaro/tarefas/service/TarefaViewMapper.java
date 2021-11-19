package br.com.lazaro.tarefas.service;

import org.springframework.stereotype.Component;

import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.util.Mapper;

@Component
public class TarefaViewMapper implements Mapper<Tarefa, TarefaView> {

	@Override
	public TarefaView map(Tarefa tarefa) {
		return TarefaView.builder()
				.id(tarefa.getId())
				.nome(tarefa.getNome())
				.descricao(tarefa.getDescricao())
				.build();
	}
	
}
