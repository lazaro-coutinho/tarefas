package br.com.lazaro.tarefas.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
}
