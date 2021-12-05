package br.com.lazaro.tarefas.exceptions;

public class TarefaJaFinalizadaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaJaFinalizadaException() {
		super("Tarefa já finalizada");
	}

	public TarefaJaFinalizadaException(String message) {
		super(message);
	}

}
