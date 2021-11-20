package br.com.lazaro.tarefas.exceptions;

public class TarefaNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaNotFoundException() {
		super("Tarefa não encontrada");
	}

	public TarefaNotFoundException(String message) {
		super(message);
	}
	
}
