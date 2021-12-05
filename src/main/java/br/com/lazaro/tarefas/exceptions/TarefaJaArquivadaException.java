package br.com.lazaro.tarefas.exceptions;

public class TarefaJaArquivadaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaJaArquivadaException() {
		super("Tarefa já arquivada");
	}

	public TarefaJaArquivadaException(String message) {
		super(message);
	}

}
