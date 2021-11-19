package br.com.lazaro.tarefas.handler;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConstraintErrorView {
	
	private String titulo;
	
	private String mensagem;
	
	private int status;
	
	private long timestamp;

	private List<String> constraints;

}
