package br.com.lazaro.tarefas.domain.usecase;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TarefaDto {
	
	private String nome;
	
	private String descricao;

}
