package br.com.lazaro.tarefas.service;

import org.springframework.stereotype.Component;

import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.util.Mapper;

@Component
public class TarefaFormMapper implements Mapper<TarefaForm, Tarefa> {

	@Override
	public Tarefa map(TarefaForm tarefaForm) {
		return new Tarefa(tarefaForm.getNome(), tarefaForm.getDescricao());
	}

}
