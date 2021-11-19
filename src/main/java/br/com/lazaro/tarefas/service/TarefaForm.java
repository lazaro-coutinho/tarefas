package br.com.lazaro.tarefas.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TarefaForm {
	
	private String nome;
	
	private String descricao;
	
	public TarefaForm(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

}
