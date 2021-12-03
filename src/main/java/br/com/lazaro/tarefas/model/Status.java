package br.com.lazaro.tarefas.model;

public enum Status {
	
	CRIADA("Criada"),
	FINALIZADA("Finalizada");
	
	private final String nome;
	
	private Status(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
