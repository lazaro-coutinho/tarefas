package br.com.lazaro.tarefas.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.lazaro.tarefas.exceptions.TarefaJaArquivadaException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarefa")
@NoArgsConstructor
@Getter
@Setter
public class Tarefa {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "data_criacao")
	private Calendar dataCriacao;
	
	public Tarefa(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public void criar() {
		this.status = Status.CRIADA;
	}
	
	public void finalizar() {
		this.status = Status.FINALIZADA;
	}
	
	public void arquivar() {
		if (Status.ARQUIVADA.equals(this.status)) throw new TarefaJaArquivadaException();
		this.status = Status.ARQUIVADA;
	}
	
}
