package br.com.lazaro.tarefas.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorView {
	
	private String titulo;
	
	private String mensagem;
	
	private int status;
	
	private long timestamp;

}
