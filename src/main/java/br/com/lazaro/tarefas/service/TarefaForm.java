package br.com.lazaro.tarefas.service;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TarefaForm {
	
	@NotEmpty(message = "{tarefa.nome.notempty}")
	private String nome;
	
	@NotEmpty(message = "{tarefa.descricao.notempty}")
	private String descricao;
	
	public TarefaForm(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

}
