package br.com.lazaro.tarefas.model;

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

	public Tarefa(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
}
