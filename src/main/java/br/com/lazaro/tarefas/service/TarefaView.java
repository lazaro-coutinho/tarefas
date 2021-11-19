package br.com.lazaro.tarefas.service;

import br.com.lazaro.tarefas.model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaView {
	
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	public TarefaView(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.nome = tarefa.getNome();
		this.descricao = tarefa.getDescricao();
	}

}
