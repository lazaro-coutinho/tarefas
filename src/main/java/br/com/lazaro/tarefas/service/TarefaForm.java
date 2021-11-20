package br.com.lazaro.tarefas.service;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TarefaForm {
	
	@ApiModelProperty("Nome da tarefa")
	@NotEmpty(message = "{tarefa.nome.notempty}")
	private String nome;
	
	@ApiModelProperty("Descrição da tarefa")
	@NotEmpty(message = "{tarefa.descricao.notempty}")
	private String descricao;
	
	public TarefaForm(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

}
