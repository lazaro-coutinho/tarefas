package br.com.lazaro.tarefas.service;

import br.com.lazaro.tarefas.model.Tarefa;
import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty("Identificador da tarefa")
	private Long id;
	
	@ApiModelProperty("Nome da tarefa")
	private String nome;
	
	@ApiModelProperty("Descrição da tarefa")
	private String descricao;
	
	public TarefaView(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.nome = tarefa.getNome();
		this.descricao = tarefa.getDescricao();
	}

}
