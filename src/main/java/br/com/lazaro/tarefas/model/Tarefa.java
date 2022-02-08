package br.com.lazaro.tarefas.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.lazaro.tarefas.domain.service.CriarTarefaAction;
import br.com.lazaro.tarefas.exceptions.TarefaJaArquivadaException;
import br.com.lazaro.tarefas.exceptions.TarefaJaFinalizadaException;
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
	@SequenceGenerator(name = "tarefa_id_seq", sequenceName = "tarefa_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_id_seq")
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
		this.ativo = true;
		this.dataCriacao = Calendar.getInstance();
	}
	
	public void alterarStatus(CriarTarefaAction criarTarefaAction) {
		status = criarTarefaAction.execute(this);
	}
	
	public void finalizar() {
		if (Status.FINALIZADA.equals(this.status)) throw new TarefaJaFinalizadaException();
		this.status = Status.FINALIZADA;
	}
	
	public void arquivar() {
		if (Status.ARQUIVADA.equals(this.status)) throw new TarefaJaArquivadaException();
		this.status = Status.ARQUIVADA;
	}
	
}
